package com.plan.igualdad.liferay.generar.informe.dto;

import java.io.Serializable;

public class EstadisticasTablaIncapacidadDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipoIncapacidad;
	private String nMujeres;
	private String diasMujeres;
	private String porcMujeres;
	private String promedioMujeres;
	private String nHombres;
	private String diasHombres;
	private String porcHombres;
	private String promedioHombres;
	private String diasTotal;
	private String porcTotal;
	private String promedioTotal;
	private Boolean printHeader;
	private Boolean isComponentTotal;
	private Boolean isComponentIncapacidad;
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

	public String getTipoIncapacidad() {
		return tipoIncapacidad;
	}

	public void setTipoIncapacidad(String tipoIncapacidad) {
		this.tipoIncapacidad = tipoIncapacidad;
	}

	public String getnMujeres() {
		return nMujeres;
	}

	public void setnMujeres(String nMujeres) {
		this.nMujeres = nMujeres;
	}

	public String getDiasMujeres() {
		return diasMujeres;
	}

	public void setDiasMujeres(String diasMujeres) {
		this.diasMujeres = diasMujeres;
	}

	public String getPorcMujeres() {
		return porcMujeres;
	}

	public void setPorcMujeres(String porcMujeres) {
		this.porcMujeres = porcMujeres;
	}

	public String getPromedioMujeres() {
		return promedioMujeres;
	}

	public void setPromedioMujeres(String promedioMujeres) {
		this.promedioMujeres = promedioMujeres;
	}

	public String getnHombres() {
		return nHombres;
	}

	public void setnHombres(String nHombres) {
		this.nHombres = nHombres;
	}

	public String getDiasHombres() {
		return diasHombres;
	}

	public void setDiasHombres(String diasHombres) {
		this.diasHombres = diasHombres;
	}

	public String getPorcHombres() {
		return porcHombres;
	}

	public void setPorcHombres(String porcHombres) {
		this.porcHombres = porcHombres;
	}

	public String getPromedioHombres() {
		return promedioHombres;
	}

	public void setPromedioHombres(String promedioHombres) {
		this.promedioHombres = promedioHombres;
	}

	public String getDiasTotal() {
		return diasTotal;
	}

	public void setDiasTotal(String diasTotal) {
		this.diasTotal = diasTotal;
	}

	public String getPorcTotal() {
		return porcTotal;
	}

	public void setPorcTotal(String porcTotal) {
		this.porcTotal = porcTotal;
	}

	public String getPromedioTotal() {
		return promedioTotal;
	}

	public void setPromedioTotal(String promedioTotal) {
		this.promedioTotal = promedioTotal;
	}

	public Boolean getPrintHeader() {
		return printHeader;
	}

	public void setPrintHeader(Boolean printHeader) {
		this.printHeader = printHeader;
	}

	public Boolean getIsComponentTotal() {
		return isComponentTotal;
	}

	public void setIsComponentTotal(Boolean isComponentTotal) {
		this.isComponentTotal = isComponentTotal;
	}

	public Boolean getIsComponentIncapacidad() {
		return isComponentIncapacidad;
	}

	public void setIsComponentIncapacidad(Boolean isComponentIncapacidad) {
		this.isComponentIncapacidad = isComponentIncapacidad;
	}

}
