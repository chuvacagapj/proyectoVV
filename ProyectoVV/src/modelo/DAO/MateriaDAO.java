package modelo.DAO;

import java.sql.*;

import modelo.Conexion;
import modelo.VO.*;

public class MateriaDAO {
	
	public MateriaVO[] gruposMaestrosMaterias(int grupo, Integer maestra){
		MateriaVO[] materias = null;
		Statement consulta = Conexion.getConexion().hacerConsulta();
		String query = "SELECT * FROM materias WHERE idMateria IN( SELECT maestro FROM asignacion WHERE grupo = " + grupo;
		
		if(maestra != null){
			query += " AND maestro = " + maestra.intValue();
		}
		
		query += ");";
		
		try{
			materias = this.recuperacion(consulta.executeQuery(query));
			consulta.close();
		}catch(SQLException e){
			System.out.println("Error tipo sql");
			System.out.println(e);
		}catch(Exception    e){
			System.out.println("Error tipo no sql");
			System.out.println(e);
		}
		
		return materias;
	}
	
	protected MateriaVO[] recuperacion (ResultSet info) throws SQLException{
		MateriaVO[] materias = null;
		int registros, i;
		
		info.last();
		registros = info.getRow();
		info.beforeFirst();
		
		i=0;
		materias = new MateriaVO[registros];
		
		while(info.next()){
			materias[i] = new MateriaVO();
			materias[i].setIdMaterias(info.getInt("idMateria"));
			materias[i].setMaxFaltas(info.getInt("maxFaltas"));
			materias[i].setNombre(info.getNString("nombre"));
			materias[i].setTipo(info.getNString("tipo"));
			i++;
		}
		
		return materias;
	}

	public static void main(String[] args) {
		MateriaDAO acceso =  new MateriaDAO();
		Conexion.setInfo("root", "control", "chocolate4194", "127.0.0.1");
		for(MateriaVO i: acceso.gruposMaestrosMaterias(201, 7)) System.out.println(i);
	}
}
