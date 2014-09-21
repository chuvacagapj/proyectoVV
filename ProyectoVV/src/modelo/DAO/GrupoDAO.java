package modelo.DAO;

import java.sql.*;

import modelo.Conexion;
import modelo.VO.GrupoVO;

public class GrupoDAO {
	
	public Integer[] getGrupos(){
		Integer[] grupos = null;
		int n = 0;
		String query = "SELECT idGrupo FROM grupos ORDER BY idGrupo;";
		Statement consulta = Conexion.getConexion().hacerConsulta();
		ResultSet registros = null;
		
		try{
			registros = consulta.executeQuery(query);
			registros.last();
			n = registros.getRow();
			grupos = new Integer[n+1];
			registros.beforeFirst();
			
			for(int i = 1; i<=n; i++){
				registros.next();
				grupos[i] = new Integer(registros.getInt(1));
			}
			registros.close();
			consulta.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return grupos;
	}
	
	public GrupoVO[] consultar (GrupoVO grupo){
		String query      = "SELECT * FROM grupos WHERE";
		Statement consulta = Conexion.getConexion().hacerConsulta();
		boolean bandera   = false;
		GrupoVO [] grupos = null;
		if(grupo.getEnAutorizado() != null){}
		if(grupo.getIdGrupo()      != null){}
		if(grupo.getJefeGrupo()    != null){}
		if(grupo.getMaxAlumnos()   != null){}
		if(grupo.getOrientador()   != null){}
		if(grupo.getTurno()        != null){}
		
		if(bandera){
			query.substring(0, query.length()-4);
		}else{
			query.substring(0, query.length()-6);
		}
		query +=";";
		System.out.println(query);
		return grupos;
	}
}
