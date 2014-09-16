package controlador;

import javax.swing.JDialog;
import javax.swing.JFrame;

import modelo.DAO.AlumnoDAO;
import modelo.DAO.GrupoDAO;
import modelo.VO.AlumnoVO;

public class Controlador {
	//vistas
	private JDialog buscarAlumnos;
	private JFrame  Login;
	
	//DAO
	private AlumnoDAO alumnos;
	private GrupoDAO grupos;
	
	public Integer [] getGrupos(){
		return grupos.getGrupos();
	}
	
	public AlumnoVO[] getAlumnos(AlumnoVO a){
		try{
			return alumnos.consultar(a);
		}catch(Exception e){
			return new AlumnoVO[1];
		}
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
