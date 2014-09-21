package modelo.VO;

public class ConcentradoFaltasVO {
	private Integer numeroFaltas;
	private Integer numeroJustificadas;
	private Integer noJustificadas;
	private Integer maximoFaltas;
	private Integer matricula;
	private Integer idMateria; 
	private String  materia;
	
	public Integer getNumeroFaltas() {
		return numeroFaltas;
	}
	public void setNumeroFaltas(Integer numeroFaltas) {
		this.numeroFaltas = numeroFaltas;
	}
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	public Integer getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(Integer idMateria) {
		this.idMateria = idMateria;
	}
	public Integer getNumeroJustificadas() {
		return numeroJustificadas;
	}
	public void setNumeroJustificadas(Integer numeroJustificadas) {
		this.numeroJustificadas = numeroJustificadas;
	}
	public Integer getNoJustificadas() {
		return noJustificadas;
	}
	public void setNoJustificadas(Integer noJustificadas) {
		this.noJustificadas = noJustificadas;
	}
	public Integer getMaximoFaltas() {
		return maximoFaltas;
	}
	public void setMaximoFaltas(Integer maximoFaltas) {
		this.maximoFaltas = maximoFaltas;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	
	public FaltaVO consulta(){
		FaltaVO falta = new FaltaVO();
		falta.setAlumno(matricula);
		falta.setMateria(idMateria);
		return falta;
	}
	
}
