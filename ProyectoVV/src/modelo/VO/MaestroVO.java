package modelo.VO;

public class MaestroVO {
	private Integer idMaestros;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String orientador;

	public Integer getIdMaestros() {
		return idMaestros;
	}

	public void setIdMaestros(Integer idMaestros) {
		this.idMaestros = idMaestros;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getOrientador() {
		return orientador;
	}

	public void setOrientador(String orientador) {
		this.orientador = orientador;
	}

}
