package controlador;

import vista.Login;
import modelo.Conexion;

public class Iniciadora {
	
    

	public static void main(String[] args) {
		Login a = new Login();
		a.setVisible(true);
	}
	
	public static void fiinal(){
		Conexion.closeConexion();
	}

}
