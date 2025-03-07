package com.plan.igualdad.liferay.portlet.valoracion.consultor.dto;

import java.io.Serializable;

public class CuestionarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long cuestionarioId;
	private String fechaCuestionario;
	private String usuario;
	private RespuestasCuestionarioDTO respuestas;
	private int version;
	private String observaciones;

	public Long getCuestionarioId() {
		return cuestionarioId;
	}

	public void setCuestionarioId(Long cuestionarioId) {
		this.cuestionarioId = cuestionarioId;
	}

	public String getFechaCuestionario() {
		return fechaCuestionario;
	}

	public void setFechaCuestionario(String fechaCuestionario) {
		this.fechaCuestionario = fechaCuestionario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public RespuestasCuestionarioDTO getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(RespuestasCuestionarioDTO respuestas) {
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
