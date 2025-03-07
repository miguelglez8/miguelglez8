package com.plan.igualdad.liferay.portlet.commons.web.dto;

import java.io.Serializable;

public class EstadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;

	public EstadoDTO(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
