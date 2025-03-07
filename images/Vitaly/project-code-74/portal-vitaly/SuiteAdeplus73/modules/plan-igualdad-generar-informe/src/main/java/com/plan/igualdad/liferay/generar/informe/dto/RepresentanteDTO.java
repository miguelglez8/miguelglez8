package com.plan.igualdad.liferay.generar.informe.dto;

import java.io.Serializable;

public class RepresentanteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String puestoTrabajo;
	private String nombreCompleto;
	private String email;
	private String nif;

	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}

	public void setPuestoTrabajo(String puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

}
