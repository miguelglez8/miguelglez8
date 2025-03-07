package com.plan.igualdad.liferay.generar.informe.dto;

import java.io.Serializable;

public class EstadisticasTablaAuditoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipoSalario;
	private String hombres;
	private String mujeres;
	private String diferencias;
	private String conceptos;
	private Boolean printHeader;
	private String salario;
	private Boolean isComponentSalario;
	private Boolean isComponentSalarioInfo;
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

	public String getTipoSalario() {
		return tipoSalario;
	}

	public void setTipoSalario(String tipoSalario) {
		this.tipoSalario = tipoSalario;
	}

	public String getHombres() {
		return hombres;
	}

	public void setHombres(String hombres) {
		this.hombres = hombres;
	}

	public String getMujeres() {
		return mujeres;
	}

	public void setMujeres(String mujeres) {
		this.mujeres = mujeres;
	}

	public String getDiferencias() {
		return diferencias;
	}

	public void setDiferencias(String diferencias) {
		this.diferencias = diferencias;
	}

	public String getConceptos() {
		return conceptos;
	}

	public void setConceptos(String conceptos) {
		this.conceptos = conceptos;
	}

	public Boolean getPrintHeader() {
		return printHeader;
	}

	public void setPrintHeader(Boolean printHeader) {
		this.printHeader = printHeader;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public Boolean getIsComponentSalario() {
		return isComponentSalario;
	}

	public void setIsComponentSalario(Boolean isComponentSalario) {
		this.isComponentSalario = isComponentSalario;
	}

	public Boolean getIsComponentSalarioInfo() {
		return isComponentSalarioInfo;
	}

	public void setIsComponentSalarioInfo(Boolean isComponentSalarioInfo) {
		this.isComponentSalarioInfo = isComponentSalarioInfo;
	}

}
