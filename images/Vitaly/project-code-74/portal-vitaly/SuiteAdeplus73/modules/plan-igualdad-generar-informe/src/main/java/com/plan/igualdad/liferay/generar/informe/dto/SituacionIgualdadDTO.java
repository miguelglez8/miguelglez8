package com.plan.igualdad.liferay.generar.informe.dto;

import java.io.Serializable;

public class SituacionIgualdadDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String situacionIgualdad;

	public SituacionIgualdadDTO(String situacionIgualdad) {
		this.situacionIgualdad = situacionIgualdad;
	}

	public String getSituacionIgualdad() {
		return situacionIgualdad;
	}

	public void setSituacionIgualdad(String situacionIgualdad) {
		this.situacionIgualdad = situacionIgualdad;
	}

}
