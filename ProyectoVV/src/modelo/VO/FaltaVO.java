package modelo.VO;

import java.util.Date;

public class FaltaVO {
	private Integer idFalta;
	private Date    hora;
	private Integer cantidad;
	private Integer justificante;
	
	public Integer getIdFalta() {
		return idFalta;
	}
	public void setIdFalta(Integer idFalta) {
		this.idFalta = idFalta;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getJustificante() {
		return justificante;
	}
	public void setJustificante(Integer justificante) {
		this.justificante = justificante;
	}
}
