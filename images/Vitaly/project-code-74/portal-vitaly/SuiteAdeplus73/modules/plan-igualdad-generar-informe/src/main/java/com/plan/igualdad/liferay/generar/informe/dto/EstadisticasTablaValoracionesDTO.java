package com.plan.igualdad.liferay.generar.informe.dto;

import java.io.Serializable;

public class EstadisticasTablaValoracionesDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean noTable;
	private String textNoTable;
	private Boolean printHeader;
	private Boolean isComponentValoracion;
	private String tituloPuesto;
	private String convenio;
	private String area;
	private String departamento;
	private String centroTrabajo;

	public Boolean getNoTable() {
		return noTable;
	}

	public void setNoTable(Boolean noTable) {
		this.noTable = noTable;
	}

	public String getTextNoTable() {
		return textNoTable;
	}

	public void setTextNoTable(String textNoTable) {
		this.textNoTable = textNoTable;
	}

	public Boolean getPrintHeader() {
		return printHeader;
	}

	public void setPrintHeader(Boolean printHeader) {
		this.printHeader = printHeader;
	}

	public Boolean getIsComponentValoracion() {
		return isComponentValoracion;
	}

	public void setIsComponentValoracion(Boolean isComponentValoracion) {
		this.isComponentValoracion = isComponentValoracion;
	}

	public String getTituloPuesto() {
		return tituloPuesto;
	}

	public void setTituloPuesto(String tituloPuesto) {
		this.tituloPuesto = tituloPuesto;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCentroTrabajo() {
		return centroTrabajo;
	}

	public void setCentroTrabajo(String centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

}
