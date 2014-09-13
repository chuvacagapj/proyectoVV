package modelo.VO;

public class GrupoVO {
	
	private Integer idGrupo;
	private String  turno;
	private Integer maxAlumnos;
	private Integer orientador;
	private Integer jefeGrupo;
	private Integer enAutorizado;
	
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public Integer getMaxAlumnos() {
		return maxAlumnos;
	}
	public void setMaxAlumnos(Integer maxAlumnos) {
		this.maxAlumnos = maxAlumnos;
	}
	public Integer getOrientador() {
		return orientador;
	}
	public void setOrientador(Integer orientador) {
		this.orientador = orientador;
	}
	public Integer getJefeGrupo() {
		return jefeGrupo;
	}
	public void setJefeGrupo(Integer jefeGrupo) {
		this.jefeGrupo = jefeGrupo;
	}
	public Integer getEnAutorizado() {
		return enAutorizado;
	}
	public void setEnAutorizado(Integer enAutorizado) {
		this.enAutorizado = enAutorizado;
	}
	
	
}
