package com.plan.igualdad.liferay.generar.informe.dto;

import java.io.Serializable;

public class EstadisticaTablaAreaPuestoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean printHeader;
	private String area;
	private String puesto;
	private String nHombres;
	private String pHombres;
	private String nMujeres;
	private String pMujeres;
	private String personas;
	private String analisis;
	private Boolean isComponentArea;
	private Boolean isComponentPuesto;
	private Boolean isComponentTotal;
	private Boolean noTable;
	private String textNoTable;

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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getnHombres() {
		return nHombres;
	}

	public void setnHombres(String nHombres) {
		this.nHombres = nHombres;
	}

	public String getpHombres() {
		return pHombres;
	}

	public void setpHombres(String pHombres) {
		this.pHombres = pHombres;
	}

	public String getnMujeres() {
		return nMujeres;
	}

	public void setnMujeres(String nMujeres) {
		this.nMujeres = nMujeres;
	}

	public String getpMujeres() {
		return pMujeres;
	}

	public void setpMujeres(String pMujeres) {
		this.pMujeres = pMujeres;
	}

	public String getPersonas() {
		return personas;
	}

	public void setPersonas(String personas) {
		this.personas = personas;
	}

	public String getAnalisis() {
		return analisis;
	}

	public void setAnalisis(String analisis) {
		this.analisis = analisis;
	}

	public Boolean getIsComponentArea() {
		return isComponentArea;
	}

	public void setIsComponentArea(Boolean isComponentArea) {
		this.isComponentArea = isComponentArea;
	}

	public Boolean getIsComponentPuesto() {
		return isComponentPuesto;
	}

	public void setIsComponentPuesto(Boolean isComponentPuesto) {
		this.isComponentPuesto = isComponentPuesto;
	}

	public Boolean getIsComponentTotal() {
		return isComponentTotal;
	}

	public void setIsComponentTotal(Boolean isComponentTotal) {
		this.isComponentTotal = isComponentTotal;
	}
}
