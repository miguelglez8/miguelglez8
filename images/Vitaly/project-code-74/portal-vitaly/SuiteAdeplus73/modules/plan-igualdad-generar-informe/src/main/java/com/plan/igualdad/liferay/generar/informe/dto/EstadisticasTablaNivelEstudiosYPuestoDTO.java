package com.plan.igualdad.liferay.generar.informe.dto;

import java.io.Serializable;

public class EstadisticasTablaNivelEstudiosYPuestoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String puesto;
	private String nivelEstudios;
	private String nHombres;
	private String nMujeres;
	private String total;
	private Boolean isComponentPuesto;
	private Boolean isComponentEstudios;
	private Boolean printHeader;
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

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getNivelEstudios() {
		return nivelEstudios;
	}

	public void setNivelEstudios(String nivelEstudios) {
		this.nivelEstudios = nivelEstudios;
	}

	public String getnHombres() {
		return nHombres;
	}

	public void setnHombres(String nHombres) {
		this.nHombres = nHombres;
	}

	public String getnMujeres() {
		return nMujeres;
	}

	public void setnMujeres(String nMujeres) {
		this.nMujeres = nMujeres;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Boolean getIsComponentPuesto() {
		return isComponentPuesto;
	}

	public void setIsComponentPuesto(Boolean isComponentPuesto) {
		this.isComponentPuesto = isComponentPuesto;
	}

	public Boolean getIsComponentEstudios() {
		return isComponentEstudios;
	}

	public void setIsComponentEstudios(Boolean isComponentEstudios) {
		this.isComponentEstudios = isComponentEstudios;
	}

	public Boolean getPrintHeader() {
		return printHeader;
	}

	public void setPrintHeader(Boolean printHeader) {
		this.printHeader = printHeader;
	}

}
