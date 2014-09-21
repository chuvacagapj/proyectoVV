package modelo.VO;

import java.util.Date;

public class FaltaVO {
	private Integer idFalta;
	private Date    fecha;
	private Integer justificante;
	private Integer alumno;
	private Integer materia;
	
	public Integer getAlumno() {
		return alumno;
	}
	public void setAlumno(Integer alumno) {
		this.alumno = alumno;
	}
	public Integer getMateria() {
		return materia;
	}
	public void setMateria(Integer materia) {
		this.materia = materia;
	}
	public Integer getIdFalta() {
		return idFalta;
	}
	public void setIdFalta(Integer idFalta) {
		this.idFalta = idFalta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getJustificante() {
		return justificante;
	}
	public void setJustificante(Integer justificante) {
		this.justificante = justificante;
	}
}
