package com.plan.igualdad.liferay.generar.informe.dto;

import java.io.Serializable;

public class EstadisticasTablaCeseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipoCese;
	private String nMujeres;
	private String porcMujeres;
	private String nHombres;
	private String porcHombres;
	private String total;
	private String porcTotal;
	private Boolean printHeader;
	private Boolean isComponentCese;
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

	public String getTipoCese() {
		return tipoCese;
	}

	public void setTipoCese(String tipoCese) {
		this.tipoCese = tipoCese;
	}

	public String getnMujeres() {
		return nMujeres;
	}

	public void setnMujeres(String nMujeres) {
		this.nMujeres = nMujeres;
	}

	public String getPorcMujeres() {
		return porcMujeres;
	}

	public void setPorcMujeres(String porcMujeres) {
		this.porcMujeres = porcMujeres;
	}

	public String getnHombres() {
		return nHombres;
	}

	public void setnHombres(String nHombres) {
		this.nHombres = nHombres;
	}

	public String getPorcHombres() {
		return porcHombres;
	}

	public void setPorcHombres(String porcHombres) {
		this.porcHombres = porcHombres;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPorcTotal() {
		return porcTotal;
	}

	public void setPorcTotal(String porcTotal) {
		this.porcTotal = porcTotal;
	}

	public Boolean getPrintHeader() {
		return printHeader;
	}

	public void setPrintHeader(Boolean printHeader) {
		this.printHeader = printHeader;
	}

	public Boolean getIsComponentCese() {
		return isComponentCese;
	}

	public void setIsComponentCese(Boolean isComponentCese) {
		this.isComponentCese = isComponentCese;
	}

	public Boolean getIsComponentTotal() {
		return isComponentTotal;
	}

	public void setIsComponentTotal(Boolean isComponentTotal) {
		this.isComponentTotal = isComponentTotal;
	}

}
