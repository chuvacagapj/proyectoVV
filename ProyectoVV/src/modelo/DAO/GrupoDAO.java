package modelo.DAO;

import java.sql.*;

import modelo.Conexion;

public class GrupoDAO {
	
	public int[] getGrupos(){
		int[] grupos = null;
		int n = 0;
		String query = "SELECT idGrupo FROM grupos ORDER BY idGrupo;";
		Statement consulta = Conexion.getConexion().hacerConsulta();
		ResultSet registros = null;
		
		try{
			registros = consulta.executeQuery(query);
			registros.last();
			n = registros.getRow();
			grupos = new int[n];
			registros.beforeFirst();
			
			for(int i = 0; i<n; i++){
				registros.next();
				grupos[i] = registros.getInt(1);
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
		return grupos;
	}
}
