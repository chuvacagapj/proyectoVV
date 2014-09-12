package modelo.VO;

import java.util.Date;

public class DocumentoVO {
	private Integer idDocumentos;
	private String  causa;
	private Date    fechaExpedicion;
	
	public Integer getIdDocumentos() {
		return idDocumentos;
	}
	public void setIdDocumentos(Integer idDocumentos) {
		this.idDocumentos = idDocumentos;
	}
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	public Date getFechaExpedicion() {
		return fechaExpedicion;
	}
	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}
}
