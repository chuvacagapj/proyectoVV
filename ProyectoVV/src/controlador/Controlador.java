package controlador;

import javax.swing.JDialog;
import javax.swing.JFrame;

import modelo.DAO.AlumnoDAO;
import modelo.DAO.GrupoDAO;

public class Controlador {
	//vistas
	private JDialog buscarAlumnos;
	private JFrame  Login;
	
	//DAO
	private AlumnoDAO alumnos;
	private GrupoDAO grupos;
	
	public String [] getGrupos(){
		String [] grupo = null;
		int[] entrada = grupos.getGrupos();
		grupo = new String[entrada.length];
		for(int i = 0; i < entrada.length; i++){
			grupo[i] = Integer.toString(entrada[i]);
		}
		
		return grupo;
	}

	public JDialog getBuscarAlumnos() {
		return buscarAlumnos;
	}

	public void setBuscarAlumnos(JDialog buscarAlumnos) {
		this.buscarAlumnos = buscarAlumnos;
	}

	public JFrame getLogin() {
		return Login;
	}

	public void setLogin(JFrame login) {
		Login = login;
	}

	public AlumnoDAO getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(AlumnoDAO alumnos) {
		this.alumnos = alumnos;
	}

	public void setGrupos(GrupoDAO grupos) {
		this.grupos = grupos;
	}
	
}
