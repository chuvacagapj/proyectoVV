package modelo.VO;

public class AlumnoVO {
  private Integer matricula;
  private String  nombre;
  private String  apellidoPaterno;
  private String  apellidoMaterno;
  private Integer capacitacion;
  private String  email;
  private Integer telefono;
  private Float   promedio;
  private Integer grupo;
  
  public AlumnoVO(){}
  
  public AlumnoVO(Integer matricula){
	  this.matricula = matricula;
  }
  
  public Integer getMatricula() {
    return matricula;
  }
  
  public void setMatricula(Integer matricula) {
    this.matricula = matricula;
  }
  
  public String getNombre() {
    return nombre;
  }
  
  @Override
public String toString() {
	return "" + matricula;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj) {
		return true;
	}
	if (obj == null) {
		return false;
	}
	if (!(obj instanceof AlumnoVO)) {
		return false;
	}
	AlumnoVO other = (AlumnoVO) obj;
	if (matricula == null) {
		if (other.matricula != null) {
			return false;
		}
	} else if (!matricula.equals(other.matricula)) {
		return false;
	}
	return true;
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
  
  public Integer getCapacitacion() {
    return capacitacion;
  }
  
  public void setCapacitacion(Integer capacitacion) {
    this.capacitacion = capacitacion;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public Integer getTelefono() {
    return telefono;
  }
  
  public void setTelefono(Integer telefono) {
    this.telefono = telefono;
  }
  
  public Float getPromedio() {
    return promedio;
  }
  
  public void setPromedio(Float promedio) {
    this.promedio = promedio;
  }
  
  public Integer getGrupo() {
    return grupo;
  }
  
  public void setGrupo(Integer grupo) {
    this.grupo = grupo;
  }
  
}
