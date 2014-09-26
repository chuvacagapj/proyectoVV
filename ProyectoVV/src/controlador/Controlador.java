package controlador;

import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;

import modelo.DAO.*;
import modelo.VO.*;

public class Controlador {
	//vistas
	private JDialog buscarAlumnos;
	private JFrame  Login;
	
	//DAO
	private AlumnoDAO  alumnos;
	private GrupoDAO   grupos;
	private FaltaDAO   faltas;
	private MateriaDAO materias;
	
	public MateriaDAO getMaterias() {
		return materias;
	}

	public void setMaterias(MateriaDAO materias) {
		this.materias = materias;
	}
	
	public FaltaVO[] getFalta(Integer grupo, Integer materia, Integer day, Integer month){
		Date a = new Date();
		return this.faltas.faltasConsultaMaestros(a.getYear(), month, day, grupo, materia);
	}
	
	public FaltaVO[] getFalta(Integer grupo, Integer materia, Integer mes){
		return this.faltas.faltasConsultaMaestros(mes, grupo, materia);
	}
	
	public FaltaVO[] getFalta(FaltaVO entrada){
		return this.faltas.consulta(entrada);
	}
	
	public FaltaDAO getFaltas() {
		return faltas;
	}

	public void setFaltas(FaltaDAO faltas) {
		this.faltas = faltas;
	}

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

	public MateriaVO[] getMaterias(int grupo){
		return materias.gruposMaestrosMaterias(grupo, null);
	}
	
	public JDialog getBuscarAlumnos() {
		return buscarAlumnos;
	}

	public void setBuscarAlumnos(JDialog buscarAlumnos) {
		this.buscarAlumnos = buscarAlumnos;
	}
	
	public void RespuestaBucadorAlumnos(AlumnoVO[] alumnos){
		for(AlumnoVO i: alumnos){
			System.out.println(i.getMatricula());
		}
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