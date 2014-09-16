package modelo.DAO;

import java.sql.*;

import modelo.Conexion;

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
			
		}catch(Exception e){
			System.out.println(e);
		}
		return grupos;
	}
}
