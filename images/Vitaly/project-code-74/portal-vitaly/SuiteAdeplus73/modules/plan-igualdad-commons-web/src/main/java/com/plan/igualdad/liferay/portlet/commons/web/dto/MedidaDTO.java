package com.plan.igualdad.liferay.portlet.commons.web.dto;

import java.io.Serializable;

public class MedidaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String idEstadosAplica;
	private String idEstadosAplicaSiempre;
	private String idEstadosAplicaNunca;
	private String materia;
	private String medida;
	private String objetivos;
	private String descripcion;
	private String prioridad;
	private String recurrente;
	private String recursosAsociados;
	private String indicadores;
	private String periocidad;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdEstadosAplica() {
		return idEstadosAplica;
	}

	public void setIdEstadosAplica(String idEstadosAplica) {
		this.idEstadosAplica = idEstadosAplica;
	}

	public String getIdEstadosAplicaSiempre() {
		return idEstadosAplicaSiempre;
	}

	public void setIdEstadosAplicaSiempre(String idEstadosAplicaSiempre) {
		this.idEstadosAplicaSiempre = idEstadosAplicaSiempre;
	}

	public String getIdEstadosAplicaNunca() {
		return idEstadosAplicaNunca;
	}

	public void setIdEstadosAplicaNunca(String idEstadosAplicaNunca) {
		this.idEstadosAplicaNunca = idEstadosAplicaNunca;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public String getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getRecurrente() {
		return recurrente;
	}

	public void setRecurrente(String recurrente) {
		this.recurrente = recurrente;
	}

	public String getRecursosAsociados() {
		return recursosAsociados;
	}

	public void setRecursosAsociados(String recursosAsociados) {
		this.recursosAsociados = recursosAsociados;
	}

	public String getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(String indicadores) {
		this.indicadores = indicadores;
	}

	public String getPeriocidad() {
		return periocidad;
	}

	public void setPeriocidad(String periocidad) {
		this.periocidad = periocidad;
	}

}
