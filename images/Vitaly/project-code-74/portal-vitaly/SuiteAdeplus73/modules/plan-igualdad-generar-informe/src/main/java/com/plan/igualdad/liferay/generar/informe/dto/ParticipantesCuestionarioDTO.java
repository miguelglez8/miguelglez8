package com.plan.igualdad.liferay.generar.informe.dto;

import java.io.Serializable;

public class ParticipantesCuestionarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombreApellidos;
	private String puesto;
	private String organizacion;
	private String email;

	public String getNombreApellidos() {
		return nombreApellidos;
	}

	public void setNombreApellidos(String nombreApellidos) {
		this.nombreApellidos = nombreApellidos;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
