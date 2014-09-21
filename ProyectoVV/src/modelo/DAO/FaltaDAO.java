package modelo.DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import modelo.Conexion;
import modelo.VO.*;

public class FaltaDAO {
	@SuppressWarnings("deprecation")
	public boolean insertar(FaltaVO entrada){
		Statement consulta = Conexion.getConexion().hacerConsulta();
		boolean bandera    = false;
 		String columna     = "INSERT INTO faltas(";
		String valores     = "VALUES (";
		
		if(entrada.getAlumno() != null){
			columna += "alumno, ";
			valores += entrada.getAlumno() +", ";
			bandera  = true;
		}
		if(entrada.getFecha() != null){
			Date fecha = entrada.getFecha();
			columna += "fecha, ";
			valores += "'" + fecha.getYear() + "-" +fecha.getMonth()+ "-" + fecha.getDay() +"', ";
			bandera  = true;
		}
		if(entrada.getIdFalta() != null){
			columna += "idFalta, ";
			valores += entrada.getIdFalta() +", ";
			bandera  = true;
		}
		if(entrada.getJustificante() != null){
			columna += "justificante, ";
			valores += entrada.getJustificante() +", ";
			bandera  = true;
		}else{
			
		}
		if(entrada.getMateria() != null){
			columna += "materia, ";
			valores += entrada.getIdFalta() +", ";
			bandera  = true;
		}
		if(bandera){
			columna = columna.substring(0, columna.length()-2) +") ";
			valores = valores.substring(0, valores.length()-3) +");";
			try{
				consulta.executeQuery(columna + valores);
				consulta.close();
				return true;
			}catch(Exception e){
				return false;
			}
		}else{
			return false;
		}
	}

	public boolean eliminar(Integer clave  ){
		Statement consulta = Conexion.getConexion().hacerConsulta();
		try{
			consulta.executeUpdate("DELETE FROM faltas WHERE idFalta = " + clave.toString() + ";" );
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public ConcentradoFaltasVO[] faltasPorMateria(Integer matricula){
		String query = "SELECT t1.alumno as matricula, t2.nombre as materia, count(*) as faltas, count(t1.justificante) as justificadas, t2.maxFaltas as maximo_falas FROM faltas as t1, materias as t2 WHERE t1.materia = t2.idMateria AND t1.alumno = "+ matricula.toString() + " GROUP BY matricula, materia;";
		try{
			ResultSet resultado = Conexion.getConexion().hacerConsulta().executeQuery(query);
			
		}catch(Exception e){
			return null;
		}
		return null;
	}
	
	public FaltaVO[] consulta (FaltaVO falta){
		FaltaVO[] faltas = null;
		String quiry = "SELECT * FROM faltas WHERE";
		return faltas;
	}
	
	public FaltaVO[] faltasConsultaMaestros(Integer month, Integer grupo, Integer materia){
		FaltaVO[] faltas =  null;
		String query = "SELECT * FROM faltas WHERE MONTH(fecha) = "+ month + " AND materia = " + materia + " AND alumno IN (SELECT matricula FROM alumnos WHERE grupo = " + grupo + ");";
		try{
			faltas = this.recuperacion(Conexion.getConexion().hacerConsulta().executeQuery(query));
		}catch(Exception e){
			faltas = null;
		}
		return faltas;
	}
	
	public FaltaVO[] faltasConsultaMaestros(Integer year, Integer month, Integer day, Integer grupo, Integer materia){
		FaltaVO[] faltas =  null;
		String query = "SELECT * FROM faltas WHERE fecha '"+ year + "-" + month+ "-" + day + "' AND materia = " + materia + " AND alumno IN (SELECT matricula FROM alumnos WHERE grupo = " + grupo + ");";
		try{
			faltas = this.recuperacion(Conexion.getConexion().hacerConsulta().executeQuery(query));
		}catch(Exception e){
			faltas = null;
		}
		return faltas;
	}
	
	protected FaltaVO[] recuperacion(ResultSet consulta) throws Exception{
		FaltaVO[] faltas = null;
		int i = 0;
		String fecha;
		
		consulta.last();
		faltas = new FaltaVO[consulta.getRow()];
		consulta.beforeFirst();
		
		while(consulta.next()){
			faltas[i] = new FaltaVO();
			faltas[i].setAlumno(consulta.getInt("alumno"));
			faltas[i].setFecha(consulta.getDate("fecha"));
			faltas[i].setIdFalta(consulta.getInt("idFalta"));
			faltas[i].setJustificante(consulta.getInt("justificante"));
			faltas[i].setMateria(consulta.getInt("materia"));
			i++;
		}
		return faltas;
	}
	
	protected Integer checarJustificantes(Integer matricula, Date fecha){
		Integer a = null;
		try{
			ResultSet resultado = Conexion.getConexion().hacerConsulta().executeQuery("SELECT idJustificantes FROM justificantes WHERE '" + fecha.getYear() + "-" + fecha.getMonth() + "-" + fecha.getDay()+"' BETWEEN fechaInicio AND fechaFinal AND idJustificantes IN (SELECT documento FROM documentos_alumnos WHERE alumno = " + matricula + ");");
			if(resultado.last()){
				a = resultado.getInt(1);
				return a;
			}else return a;
		}catch(Exception e){
			return a;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Conexion.setInfo("root", "control", "chocolate4194", "127.0.0.1");
		FaltaDAO acceso = new FaltaDAO();
		FaltaVO consulta = new FaltaVO();
		consulta.setAlumno(12030161);
		consulta.setFecha(new Date (2014,9,20));
		consulta.setIdFalta(12);
		consulta.setJustificante(null);
		consulta.setMateria(2);
		acceso.insertar(consulta);
	}
}
