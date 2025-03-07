package com.plan.igualdad.liferay.generar.informe.dto;

import java.io.Serializable;

public class EstadisticasTablaDistribucionPuestoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean noTable;
	private String textNoTable;
	private Boolean printHeader;
	private Boolean isComponentAgrupacion;
	private Boolean isComponentDistribucion;
	private String tituloPuesto;
	private String categorizacion;
	private String nHombres;
	private String nMujeres;
	private String agrupacion;
	private String puntos;

	public Boolean getIsComponentAgrupacion() {
		return isComponentAgrupacion;
	}

	public void setIsComponentAgrupacion(Boolean isComponentAgrupacion) {
		this.isComponentAgrupacion = isComponentAgrupacion;
	}

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

	public Boolean getIsComponentDistribucion() {
		return isComponentDistribucion;
	}

	public void setIsComponentDistribucion(Boolean isComponentDistribucion) {
		this.isComponentDistribucion = isComponentDistribucion;
	}

	public String getTituloPuesto() {
		return tituloPuesto;
	}

	public void setTituloPuesto(String tituloPuesto) {
		this.tituloPuesto = tituloPuesto;
	}

	public String getCategorizacion() {
		return categorizacion;
	}

	public void setCategorizacion(String categorizacion) {
		this.categorizacion = categorizacion;
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

	public String getAgrupacion() {
		return agrupacion;
	}

	public void setAgrupacion(String agrupacion) {
		this.agrupacion = agrupacion;
	}

	public String getPuntos() {
		return puntos;
	}

	public void setPuntos(String puntos) {
		this.puntos = puntos;
	}

}
