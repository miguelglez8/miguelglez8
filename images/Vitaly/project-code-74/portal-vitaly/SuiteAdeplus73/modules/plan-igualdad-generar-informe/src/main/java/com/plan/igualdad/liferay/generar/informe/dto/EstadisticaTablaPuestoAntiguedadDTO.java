package com.plan.igualdad.liferay.generar.informe.dto;

import java.io.Serializable;

public class EstadisticaTablaPuestoAntiguedadDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String puesto;
	private String rangoEdad;
	private String totalHombres;
	private String rFamiliarHombres;
	private String rFamiliarPorcHombres;
	private String totalMujeres;
	private String rFamiliarMujeres;
	private String rFamiliarPorcMujeres;
	private Boolean printHeader;
	private Boolean isComponentPuesto;
	private Boolean isComponentEdad;
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

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getRangoEdad() {
		return rangoEdad;
	}

	public void setRangoEdad(String rangoEdad) {
		this.rangoEdad = rangoEdad;
	}

	public String getTotalHombres() {
		return totalHombres;
	}

	public void setTotalHombres(String totalHombres) {
		this.totalHombres = totalHombres;
	}

	public String getrFamiliarHombres() {
		return rFamiliarHombres;
	}

	public void setrFamiliarHombres(String rFamiliarHombres) {
		this.rFamiliarHombres = rFamiliarHombres;
	}

	public String getrFamiliarPorcHombres() {
		return rFamiliarPorcHombres;
	}

	public void setrFamiliarPorcHombres(String rFamiliarPorcHombres) {
		this.rFamiliarPorcHombres = rFamiliarPorcHombres;
	}

	public String getTotalMujeres() {
		return totalMujeres;
	}

	public void setTotalMujeres(String totalMujeres) {
		this.totalMujeres = totalMujeres;
	}

	public String getrFamiliarMujeres() {
		return rFamiliarMujeres;
	}

	public void setrFamiliarMujeres(String rFamiliarMujeres) {
		this.rFamiliarMujeres = rFamiliarMujeres;
	}

	public String getrFamiliarPorcMujeres() {
		return rFamiliarPorcMujeres;
	}

	public void setrFamiliarPorcMujeres(String rFamiliarPorcMujeres) {
		this.rFamiliarPorcMujeres = rFamiliarPorcMujeres;
	}

	public Boolean getPrintHeader() {
		return printHeader;
	}

	public void setPrintHeader(Boolean printHeader) {
		this.printHeader = printHeader;
	}

	public Boolean getIsComponentPuesto() {
		return isComponentPuesto;
	}

	public void setIsComponentPuesto(Boolean isComponentPuesto) {
		this.isComponentPuesto = isComponentPuesto;
	}

	public Boolean getIsComponentEdad() {
		return isComponentEdad;
	}

	public void setIsComponentEdad(Boolean isComponentEdad) {
		this.isComponentEdad = isComponentEdad;
	}

	public Boolean getIsComponentTotal() {
		return isComponentTotal;
	}

	public void setIsComponentTotal(Boolean isComponentTotal) {
		this.isComponentTotal = isComponentTotal;
	}

}
