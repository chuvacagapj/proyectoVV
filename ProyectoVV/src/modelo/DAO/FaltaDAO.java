package modelo.DAO;

import java.sql.*;
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
		String query = "SELECT t1.alumno as matricula, t2.nombre as materia, t2.idMateria as codigo, count(*) as faltas, count(t1.justificante) as justificadas, t2.maxFaltas as maximo_falas FROM faltas as t1, materias as t2 WHERE t1.materia = t2.idMateria AND t1.alumno = "+ matricula.toString() + " GROUP BY matricula, materia;";
		ConcentradoFaltasVO [] faltas = null;
		int registros, i = 0;
		try{
			ResultSet resultado = Conexion.getConexion().hacerConsulta().executeQuery(query);
			/*ResultSetMetaData columnas = resultado.getMetaData();
			while(true){
				System.out.println(columnas.getColumnName(++i));
			}*/
			
			resultado.last();
			registros = resultado.getRow();
			faltas = new ConcentradoFaltasVO [registros];
			resultado.beforeFirst();
			while(resultado.next()){
				faltas[i] = new ConcentradoFaltasVO();
				faltas[i].setIdMateria(resultado.getInt(3));
				faltas[i].setMateria(resultado.getNString(2));
				faltas[i].setMatricula(resultado.getInt(1));
				faltas[i].setMaximoFaltas(resultado.getInt(6));
				faltas[i].setNumeroFaltas(resultado.getInt(4));
				faltas[i].setNumeroJustificadas(resultado.getInt(5));
				faltas[i].setNoJustificadas(faltas[i].getNumeroFaltas().intValue() - faltas[i].getNumeroJustificadas().intValue());
				i++;
			}
			System.out.println(faltas[0].getNumeroFaltas());
		}catch(SQLException e){
			System.out.println(e);
			return faltas;
		}/*catch(Exception e){
			System.out.println(e);
			return faltas;
		}*/
		return faltas;
	}
	
	public FaltaVO[] consulta (FaltaVO falta){
		FaltaVO[] faltas = new FaltaVO[0];
		boolean bandera = false;
		String quiry = "SELECT * FROM faltas WHERE";
		if(falta.getAlumno() != null){
			quiry += " CAST(alumno AS CHAR) LIKE '" + falta.getAlumno() + "' AND";
			bandera = true;
		}
		if(falta.getFecha() != null){
			Date a = falta.getFecha();
			quiry += " fecha = '" + a.getYear() + "-" + a.getMonth() + "-"+ a.getDay() +"' AND";
			bandera = true;
		}
		if(falta.getIdFalta() != null){
			quiry += " CAST(idFalta AS CHAR) LIKE '" + falta.getIdFalta() + "' AND";
			bandera = true;
		}
		if(falta.getJustificante() != null){
			quiry += " CAST(justificante AS CHAR) LIKE '" + falta.getJustificante() + "' AND";
			bandera = true;
		}
		if(falta.getMateria() != null){
			quiry += " CAST(materia AS CHAR) LIKE '" + falta.getMateria() + "' AND";
			bandera = true;
		}
		
		if(bandera){
			quiry = quiry.substring(0, quiry.length()-4) + ";";
		}
		else{
			quiry = quiry.substring(0, quiry.length()-7) + ";";
		} 
		System.out.println(quiry);
		try{
			faltas = this.recuperacion(Conexion.getConexion().hacerConsulta().executeQuery(quiry));
			System.out.println(faltas);
		}catch(SQLException e){
			System.out.println(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		String query = "SELECT * FROM faltas WHERE fecha = '"+ year + "-" + month+ "-" + day + "' AND materia = " + materia + " AND alumno IN (SELECT matricula FROM alumnos WHERE grupo = " + grupo + ");";
		try{
			faltas = this.recuperacion(Conexion.getConexion().hacerConsulta().executeQuery(query));
		}catch(SQLException e){
			System.out.println(e);
			faltas = null;
		}
		catch(Exception e){
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
	
	public static void main(String[] args) {
		Conexion.setInfo("root", "control", "chocolate4194", "127.0.0.1");
		FaltaDAO acceso = new FaltaDAO();
		FaltaVO[] respuesta;
		respuesta = acceso.faltasConsultaMaestros(2014, 9, 17, 201, 5);
		for(FaltaVO i: respuesta){
			System.out.println(i);
		}
	}
}
