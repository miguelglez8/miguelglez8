package com.plan.igualdad.liferay.portlet.valoracion.consultor.dto;

import java.io.Serializable;

public class RespuestasDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String respuesta1;
	private String respuesta2;
	private String respuesta3;

	public String getRespuesta1() {
		return respuesta1;
	}

	public void setRespuesta1(String respuesta1) {
		this.respuesta1 = respuesta1;
	}

	public String getRespuesta2() {
		return respuesta2;
	}

	public void setRespuesta2(String respuesta2) {
		this.respuesta2 = respuesta2;
	}

	public String getRespuesta3() {
		return respuesta3;
	}

	public void setRespuesta3(String respuesta3) {
		this.respuesta3 = respuesta3;
	}

}
