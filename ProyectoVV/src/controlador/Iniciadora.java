package controlador;

import modelo.*;
import modelo.DAO.*;
import modelo.VO.*;
import vista.*;
import javax.swing.*;

public class Iniciadora {

	public static void main(String[] args) {
		Principal vista = new Principal();
		vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		vista.setSize(500, 500);
		vista.setVisible(true);
	}
	
	public static void fiinal(){
		Conexion.closeConexion();
	}

}
