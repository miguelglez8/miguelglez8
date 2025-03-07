package com.plan.igualdad.liferay.portlet.valoracion.consultor.dto;

import java.io.Serializable;

public class ValoracionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long valoracionId;
	private String fechaValoracion;
	private String usuario;
	private RespuestasDTO respuestas;
	private int version;
	private String observaciones;

	public Long getValoracionId() {
		return valoracionId;
	}

	public void setValoracionId(Long valoracionId) {
		this.valoracionId = valoracionId;
	}

	public String getFechaValoracion() {
		return fechaValoracion;
	}

	public void setFechaValoracion(String fechaValoracion) {
		this.fechaValoracion = fechaValoracion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public RespuestasDTO getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(RespuestasDTO respuestas) {
		this.respuestas = respuestas;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
