package com.plan.igualdad.liferay.portlet.valoracion.consultor.dto;

import java.io.Serializable;

public class RespuestasCuestionarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String pregunta1;
	private String pregunta2;
	private String pregunta3;
	private String pregunta4;
	private String pregunta5;
	private String pregunta6;
	

	public String getPregunta1() {
		return pregunta1;
	}

	public void setPregunta1(String pregunta1) {
		this.pregunta1 = pregunta1;
	}

	public String getPregunta2() {
		return pregunta2;
	}

	public void setPregunta2(String pregunta2) {
		this.pregunta2 = pregunta2;
	}

	public String getPregunta3() {
		return pregunta3;
	}

	public void setPregunta3(String pregunta3) {
		this.pregunta3 = pregunta3;
	}

	public String getPregunta4() {
		return pregunta4;
	}

	public void setPregunta4(String pregunta4) {
		this.pregunta4 = pregunta4;
	}

	public String getPregunta5() {
		return pregunta5;
	}

	public void setPregunta5(String pregunta5) {
		this.pregunta5 = pregunta5;
	}

	public String getPregunta6() {
		return pregunta6;
	}

	public void setPregunta6(String pregunta6) {
		this.pregunta6 = pregunta6;
	}
}
