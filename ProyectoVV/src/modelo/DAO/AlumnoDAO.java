package modelo.DAO;

import java.sql.*;

import modelo.Conexion;
import modelo.VO.AlumnoVO;

public class AlumnoDAO {

	public boolean insertar(AlumnoVO alumno) {
		String    columnas = "INSERT INTO alumnos(";
		String    valores  = "Values (";
		Statement consulta = Conexion.getConexion().hacerConsulta();
		
		if(alumno.getMatricula() != null){
			columnas += " matricula,";
			valores  += " " + alumno.getMatricula().toString() + ",";
		}
		if(alumno.getNombre() != null){
			columnas += " nombre,";
			valores  += " " + alumno.getNombre() + ",";
		}
		if(alumno.getApellidoPaterno() != null){
			columnas += " apellidoPaterno,";
			valores  += " " + alumno.getApellidoPaterno() + ",";
		}
		if(alumno.getApellidoMaterno() != null){
			columnas += " apellidoMaterno,";
			valores  += " " + alumno.getApellidoMaterno() + ",";
		}
		if(alumno.getCapacitacion() != null){
			columnas += " capacitacion,";
			valores  += " " + alumno.getCapacitacion().toString() + ",";
		}
		if(alumno.getEmail() != null){
			columnas += " email,";
			valores  += " " + alumno.getCapacitacion().toString() + ",";
		}
		if(alumno.getTelefono() != null){
			columnas += " telefono,";
			valores  += " " + alumno.getTelefono().toString() + ",";
		}
		if(alumno.getPromedio() != null){
			columnas += " promedio,";
			valores  += " " + alumno.getPromedio().toString() + ",";
		}
		if(alumno.getGrupo() != null){
			columnas += " grupo,";
			valores  += " " + alumno.getGrupo().toString() + ",";
		}
		 
		columnas = columnas.substring(0, columnas.length()-2);
		valores  = valores. substring(0, columnas.length()-2);
		
		columnas += ") ";
		valores  += ");";
		try{
			consulta.executeUpdate(columnas + valores);
			consulta.close();
		}catch (Exception e){
			return false;
		}
		return true;
	}

	public boolean update(AlumnoVO alumno) {
		String    query    = "UPDATE alumnos SET";
		Statement consulta = Conexion.getConexion().hacerConsulta();
		
		if(alumno.getNombre() != null){
			query += " nombre = " + alumno.getNombre() + ",";
		}
		if(alumno.getApellidoPaterno() != null){
			query += " apellidoPaterno = " + alumno.getApellidoPaterno() + ",";
		}
		if(alumno.getApellidoMaterno() != null){
			query += " apellidoMaterno = " + alumno.getApellidoMaterno() + ",";
		}
		if(alumno.getCapacitacion() != null){
			query += " capacitacion = " + alumno.getCapacitacion().toString() + ",";
		}
		if(alumno.getEmail() != null){
			query += " email = " + alumno.getCapacitacion().toString() + ",";
		}
		if(alumno.getTelefono() != null){
			query += " telefono = " + alumno.getTelefono().toString() + ",";
		}
		if(alumno.getPromedio() != null){
			query += " promedio = " + alumno.getPromedio().toString() + ",";
		}
		if(alumno.getGrupo() != null){
			query += " grupo = "+ alumno.getGrupo().toString() + ",";
		}
		
		query = query.substring(0, query.length() -2);
		query += " WHERE matricula = " + alumno.getMatricula() + ";";
		
		try{
			consulta.executeUpdate(query);
			consulta.close();
		}catch (Exception e){
			return false;
		}
		return true;
	}

	public boolean eliminar(Integer clave) {
		Statement consulta = Conexion.getConexion().hacerConsulta();
		try{
			consulta.executeUpdate("DELETE FROM alumnos WHERE matricula = " + clave.toString() +";");
		}catch(Exception e){
			return false;
		}
		return true;
	}

	public AlumnoVO[] noEncuestados(Integer claveEncuesta){
		String query = "SELECT * FROM alumnos WHERE matricula NOT IN (SELECT matricula FROM respuestas WHERE encuesta = "+ claveEncuesta + " GRUOP BY matricula HAVING count(*) = 3) ;";
		Statement consulta = Conexion.getConexion().hacerConsulta();
		AlumnoVO[] alumnos = new AlumnoVO[1];
		try{
			alumnos = recuperacion(consulta.executeQuery(query));
			
		}catch(Exception e){
			System.out.println(e);
		}
		return alumnos;
	} 
	
	public AlumnoVO[] ordenAsignacion(Integer semestre){
		AlumnoVO[] alumnos = new AlumnoVO[1];
		String query = "SELECT * FROM alumnos WHERE CAST(grupo AS char) like '" +semestre+   "%' ORDER BY promedio;";
		Statement consulta = Conexion.getConexion().hacerConsulta();
		System.out.print(consulta);
		try{
			alumnos = this.recuperacion(consulta.executeQuery(query));
		}catch(SQLException e){
			System.out.println(e.getLocalizedMessage());
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
		return alumnos;
	}
	
	public AlumnoVO[] consultar(AlumnoVO alumno) {
		String query = "SELECT * FROM alumnos \n WHERE ";
		Statement consulta = Conexion.getConexion().hacerConsulta();
		AlumnoVO[] a = new AlumnoVO[1];
		
		if(alumno.getMatricula() != null){
			query += " CAST(matricula AS char) LIKE '" + alumno.getMatricula() + "%' AND";
		}
		
		if(alumno.getNombre() != null){
			query += "\n nombre LIKE '" + alumno.getNombre() + "%' AND";
		}
		if(alumno.getApellidoPaterno() != null){
			query += "\n apellidoPaterno LIKE '" + alumno.getApellidoPaterno() + "%' AND";
		}
		if(alumno.getApellidoMaterno() != null){
			query += "\n apellidoMaterno LIKE '" + alumno.getApellidoMaterno() + "%' AND";
		}
		if(alumno.getGrupo() != null){
			query += "\n (grupo = "+ alumno.getGrupo().toString() + " OR capacitacion ="+ alumno.getGrupo().toString() + ") AND";
		}
		query = query.substring(0, query.length() -3);
		query += ";";
		System.out.println(query);
		try{
			a = this.recuperacion(consulta.executeQuery(query));
		}catch(SQLException e){
			System.out.println("error tipo sql");
			System.out.println(e.getCause());
		}catch (NullPointerException e){
			System.out.println("DAO");
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
		return a;
	}
	
	protected AlumnoVO[] recuperacion (ResultSet info) throws Exception{
		int registros = 0, i;	
		info.last();
		registros = info.getRow();
		info.beforeFirst();
		
		AlumnoVO[] a = new AlumnoVO[registros];
		i=0;
		while(info.next()){
			a[i] = new AlumnoVO();
			a[i].setMatricula(info.getInt("matricula"));
			a[i].setNombre(info.getNString("nombre"));
			a[i].setApellidoMaterno(info.getNString("apellidoMaterno"));
			a[i].setApellidoPaterno(info.getNString("apellidoPaterno"));
			a[i].setGrupo(info.getInt("grupo"));
			a[i].setCapacitacion(info.getInt("capacitacion"));
			a[i].setEmail(info.getNString("email"));
			a[i].setTelefono(info.getInt("telefono"));
			a[i].setPromedio(info.getFloat("promedio"));
			i++;
		}
		return a;
	}

	public static void main(String[] args) {
		AlumnoDAO alumno = new AlumnoDAO();
		Conexion.setInfo("root", "control", "chocolate4194", "127.0.0.1");
		AlumnoVO[]  alumnos = alumno.ordenAsignacion(2);
		if (alumnos == null) System.out.println("no devuelve nada");
		for(AlumnoVO a: alumnos){
			System.out.println(a.getMatricula() + " " + a.getNombre() + " " +a.getApellidoPaterno() + " " + a.getApellidoMaterno() + " " + a.getPromedio());
		}
	}
}