package modelo.DAO;

import java.sql.*;
import modelo.Conexion;
import modelo.VO.AlumnoVO;

public class AlumnoDAO {

	public boolean insertar(Object a) {
		String    columnas = "INSERT INTO alumnos(";
		String    valores  = "Values (";
		AlumnoVO  alumno   = (AlumnoVO)a;
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

	public boolean update(Object a) {
		String    query    = "UPDATE alumnos SET";
		AlumnoVO  alumno   = (AlumnoVO)a;
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

	public boolean eliminar(Object a) {
		Integer clave = (Integer)a;
		Statement consulta = Conexion.getConexion().hacerConsulta();
		try{
			consulta.executeUpdate("DELETE FROM alumnos WHERE matricula = " + clave.toString() +";");
		}catch(Exception e){
			return false;
		}
		return true;
	}


	public AlumnoVO[] consultar(AlumnoVO alumno) {
		String query = "SELECT * FROM alumnos WHERE ";
		Statement consulta = Conexion.getConexion().hacerConsulta();
		AlumnoVO[] a = null;
		
		if(alumno.getNombre() != null){
			query += " nombre LIKE '" + alumno.getNombre() + "%' AND";
		}
		if(alumno.getApellidoPaterno() != null){
			query += " apellidoPaterno LIKE '" + alumno.getApellidoPaterno() + "%' AND";
		}
		if(alumno.getApellidoMaterno() != null){
			query += " apellidoMaterno LIKE '" + alumno.getApellidoMaterno() + "%' AND";
		}
		if(alumno.getGrupo() != null){
			query += " grupo = "+ alumno.getGrupo().toString() + " AND";
		}
		query = query.substring(0, query.length() -5);
		query += ";";
		
		try{
			a = this.recuperacion(consulta.executeQuery(query));
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
		return a;
	}
	
	protected AlumnoVO[] recuperacion (ResultSet info) throws Exception{
		int registros = 0, columnas = 0;
		ResultSetMetaData metadata = null;
		metadata = info.getMetaData();
		info.last();
		registros = info.getRow();
		columnas  = metadata.getColumnCount();
		info.beforeFirst();
		
		AlumnoVO[] a = new AlumnoVO[registros];
		
		for(int i=1; i <= columnas;i++){
			switch(metadata.getColumnClassName(i)){
				case "matricula":
					for(int j=1; j<=registros; j++){
						info.next();
						a[j].setMatricula(info.getInt(i));
					}
					info.beforeFirst();
					break;
				case "nombre":
					for(int j=1; j<=registros; j++){
						info.next();
						a[j].setNombre(info.getNString(i));
					}
					info.beforeFirst();
					break;
				case "apellidoPaterno":
					for(int j=1; j<=registros; j++){
						info.next();
						a[j].setApellidoPaterno(info.getNString(i));
					}
					info.beforeFirst();
					break;
				case "apellidoMaterno":
					for(int j=1; j<=registros; j++){
						info.next();
						a[j].setApellidoMaterno(info.getNString(i));
					}
					info.beforeFirst();
					break;
				case "capacitacion":
					for(int j=1; j<=registros; j++){
						info.next();
						a[j].setCapacitacion(info.getInt(i));
					}
					info.beforeFirst();
					break;
				case "email":
					for(int j=1; j<=registros; j++){
						info.next();
						a[j].setEmail(info.getNString(i));
					}
					info.beforeFirst();
					break;
				case "telefono":
					for(int j=1; j<=registros; j++){
						info.next();
						a[j].setTelefono(info.getInt(i));
					}
					info.beforeFirst();
					break;
				case "promedio":
					for(int j=1; j<=registros; j++){
						info.next();
						a[j].setPromedio(info.getFloat(i));
					}
					info.beforeFirst();
					break;
				case "grupo":
					for(int j=1; j<=registros; j++){
						info.next();
						a[j].setGrupo(info.getInt(i));
					}
					info.beforeFirst();
					break;
				default:
			}
		}
		return a;
	}

}