package com.plan.igualdad.liferay.generar.informe.dto;

import java.io.Serializable;

public class TableDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String label;
	private String valor;

	public TableDTO(String label, String valor) {
		this.label = label;
		this.valor = valor;
	}
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
