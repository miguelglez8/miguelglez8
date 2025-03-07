package com.plan.igualdad.liferay.generar.informe.dto;

import java.io.Serializable;

public class EstadisticaTablaHorarioTurnoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String horario;
	private String turno;
	private String nHombres;
	private String porcHombres;
	private String nMujeres;
	private String porcMujeres;
	private String total;
	private String porcTotal;
	private Boolean printHeader;
	private Boolean isComponentTotal;
	private Boolean isComponentTurno;
	private Boolean isComponentHorario;
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

	public Boolean getIsComponentHorario() {
		return isComponentHorario;
	}

	public void setIsComponentHorario(Boolean isComponentHorario) {
		this.isComponentHorario = isComponentHorario;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
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

	public Boolean getIsComponentTotal() {
		return isComponentTotal;
	}

	public void setIsComponentTotal(Boolean isComponentTotal) {
		this.isComponentTotal = isComponentTotal;
	}

	public Boolean getIsComponentTurno() {
		return isComponentTurno;
	}

	public void setIsComponentTurno(Boolean isComponentTurno) {
		this.isComponentTurno = isComponentTurno;
	}

}
