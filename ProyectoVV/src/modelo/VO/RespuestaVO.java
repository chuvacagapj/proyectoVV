package modelo.VO;

public class RespuestaVO {
	private Integer idRespuesta;
	private String  respuesta;
	private Integer alumno;
	private Integer encuesta;
	private Integer siguiente;
	
	public Integer getIdRespuesta() {
		return idRespuesta;
	}
	public void setIdRespuesta(Integer idRespuesta) {
		this.idRespuesta = idRespuesta;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public Integer getAlumno() {
		return alumno;
	}
	public void setAlumno(Integer alumno) {
		this.alumno = alumno;
	}
	public Integer getEncuesta() {
		return encuesta;
	}
	public void setEncuesta(Integer encuesta) {
		this.encuesta = encuesta;
	}
	public Integer getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(Integer siguiente) {
		this.siguiente = siguiente;
	}
	
}
