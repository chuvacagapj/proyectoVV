package controlador;

import modelo.Conexion;
import modelo.DAO.AlumnoDAO;
import modelo.VO.AlumnoVO;

public class Iniciadora {
	
    

	public static void main(String[] args) {
		Conexion.setInfo("root", "control", "chocolate4194", "127.0.0.1");
		AlumnoDAO a = new AlumnoDAO();
		System.out.print(Conexion.getConexion());
		try{
		AlumnoVO [] alumnos = a.ordenAsignacion(2);
		for(AlumnoVO i: alumnos){
			System.out.print(i);
		}}catch(Exception e){}
	}
	
	public static void fiinal(){
		Conexion.closeConexion();
	}

}
