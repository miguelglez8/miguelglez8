package com.plan.igualdad.liferay.seguimiento.dto;

import java.io.Serializable;

public class PlanIgualdadMedidaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long medidaId;
	private Long userId;
	private Long versionId;
	private String versionName;
	private DatosGeneralesDTO datosGenerales;
	private CumplimentacionDTO cumplimentacion;
	private Boolean isMedidaPredefinida;
	private String medidaPredefinidaId;
	private String periocidad;
	private Integer versionMedida;
	private Long versionOriginalMedidaId;
	private Boolean lastVersion;

	public Long getMedidaId() {
		return medidaId;
	}

	public void setMedidaId(Long medidaId) {
		this.medidaId = medidaId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getVersionId() {
		return versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public DatosGeneralesDTO getDatosGenerales() {
		return datosGenerales;
	}

	public void setDatosGenerales(DatosGeneralesDTO datosGenerales) {
		this.datosGenerales = datosGenerales;
	}

	public CumplimentacionDTO getCumplimentacion() {
		return cumplimentacion;
	}

	public void setCumplimentacion(CumplimentacionDTO cumplimentacion) {
		this.cumplimentacion = cumplimentacion;
	}

	public String getMedidaPredefinidaId() {
		return medidaPredefinidaId;
	}

	public void setMedidaPredefinidaId(String medidaPredefinidaId) {
		this.medidaPredefinidaId = medidaPredefinidaId;
	}

	public Boolean getIsMedidaPredefinida() {
		return isMedidaPredefinida;
	}

	public void setIsMedidaPredefinida(Boolean isMedidaPredefinida) {
		this.isMedidaPredefinida = isMedidaPredefinida;
	}

	public String getPeriocidad() {
		return periocidad;
	}

	public void setPeriocidad(String periocidad) {
		this.periocidad = periocidad;
	}

	public Integer getVersionMedida() {
		return versionMedida;
	}

	public void setVersionMedida(Integer versionMedida) {
		this.versionMedida = versionMedida;
	}

	public Long getVersionOriginalMedidaId() {
		return versionOriginalMedidaId;
	}

	public void setVersionOriginalMedidaId(Long versionOriginalMedidaId) {
		this.versionOriginalMedidaId = versionOriginalMedidaId;
	}

	public Boolean getLastVersion() {
		return lastVersion;
	}

	public void setLastVersion(Boolean lastVersion) {
		this.lastVersion = lastVersion;
	}
	
}
