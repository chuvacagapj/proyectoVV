package modelo.VO;

public class MateriaVO {
	private Integer idMaterias;
	private String  nombre;
	private Integer maxFaltas;
	private String  tipo;
	
	public Integer getIdMaterias() {
		return idMaterias;
	}
	public void setIdMaterias(Integer idMaterias) {
		this.idMaterias = idMaterias;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getMaxFaltas() {
		return maxFaltas;
	}
	public void setMaxFaltas(Integer maxFaltas) {
		this.maxFaltas = maxFaltas;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
