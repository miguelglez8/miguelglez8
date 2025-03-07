package com.plan.igualdad.liferay.portlet.cuestionario.dto;

import java.io.Serializable;

public class ParametrizacionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idParametrica;
	private String materia;
	private String contenido;

	public String getIdParametrica() {
		return idParametrica;
	}

	public void setIdParametrica(String idParametrica) {
		this.idParametrica = idParametrica;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

}
