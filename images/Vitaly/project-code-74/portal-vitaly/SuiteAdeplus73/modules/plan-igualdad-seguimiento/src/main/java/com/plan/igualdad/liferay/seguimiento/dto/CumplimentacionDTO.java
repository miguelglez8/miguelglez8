package com.plan.igualdad.liferay.seguimiento.dto;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.plan.igualdad.liferay.seguimiento.constants.PlanIgualdadSeguimientoPortletKeys;

import java.io.Serializable;

public class CumplimentacionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String responsable;
	private String fechaPrevista;
	private String fechaImplantacion;
	private String fechaSeguimiento;
	private Integer nivelEjecucionId;
	private String nivelEjecucionNombre;
	private String motivoNoIniciado;
	private String motivoNoIniciadoText;
	private String adecuacionRecursos;
	private String dificultadesImplantacion;
	private String solucionesAdoptadas;
	private String reduccionDesigualdades;
	private String mejorasProductivas;
	private String propuestaDeFuturo;
	private String documentacionEjecucion;
	private Long documentoId;

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getFechaPrevista() {
		return fechaPrevista;
	}

	public void setFechaPrevista(String fechaPrevista) {
		this.fechaPrevista = fechaPrevista;
	}

	public String getFechaImplantacion() {
		return fechaImplantacion;
	}

	public void setFechaImplantacion(String fechaImplantacion) {
		this.fechaImplantacion = fechaImplantacion;
	}

	public String getFechaSeguimiento() {
		return fechaSeguimiento;
	}

	public void setFechaSeguimiento(String fechaSeguimiento) {
		this.fechaSeguimiento = fechaSeguimiento;
	}

	public String getMotivoNoIniciado() {
		return motivoNoIniciado;
	}

	public void setMotivoNoIniciado(String motivoNoIniciado) {
		this.motivoNoIniciado = motivoNoIniciado;
	}

	public String getAdecuacionRecursos() {
		return adecuacionRecursos;
	}

	public void setAdecuacionRecursos(String adecuacionRecursos) {
		this.adecuacionRecursos = StringUtil.replace(adecuacionRecursos,
				new String[]{StringPool.NEW_LINE, StringPool.QUOTE},
				new String[]{PlanIgualdadSeguimientoPortletKeys.LINE_BREAK, StringPool.APOSTROPHE});
	}

	public String getDificultadesImplantacion() {
		return dificultadesImplantacion;
	}

	public void setDificultadesImplantacion(String dificultadesImplantacion) {
		this.dificultadesImplantacion = StringUtil.replace(dificultadesImplantacion,
				new String[]{StringPool.NEW_LINE, StringPool.QUOTE},
				new String[]{PlanIgualdadSeguimientoPortletKeys.LINE_BREAK, StringPool.APOSTROPHE});
	}

	public String getSolucionesAdoptadas() {
		return solucionesAdoptadas;
	}

	public void setSolucionesAdoptadas(String solucionesAdoptadas) {
		this.solucionesAdoptadas = StringUtil.replace(solucionesAdoptadas,
				new String[]{StringPool.NEW_LINE, StringPool.QUOTE},
				new String[]{PlanIgualdadSeguimientoPortletKeys.LINE_BREAK, StringPool.APOSTROPHE});
	}

	public String getReduccionDesigualdades() {
		return reduccionDesigualdades;
	}

	public void setReduccionDesigualdades(String reduccionDesigualdades) {
		this.reduccionDesigualdades = StringUtil.replace(reduccionDesigualdades,
				new String[]{StringPool.NEW_LINE, StringPool.QUOTE},
				new String[]{PlanIgualdadSeguimientoPortletKeys.LINE_BREAK, StringPool.APOSTROPHE});
	}

	public String getMejorasProductivas() {
		return mejorasProductivas;
	}

	public void setMejorasProductivas(String mejorasProductivas) {
		this.mejorasProductivas = StringUtil.replace(mejorasProductivas,
				new String[]{StringPool.NEW_LINE, StringPool.QUOTE},
				new String[]{PlanIgualdadSeguimientoPortletKeys.LINE_BREAK, StringPool.APOSTROPHE});
	}

	public String getPropuestaDeFuturo() {
		return propuestaDeFuturo;
	}

	public void setPropuestaDeFuturo(String propuestaDeFuturo) {
		this.propuestaDeFuturo = StringUtil.replace(propuestaDeFuturo,
				new String[]{StringPool.NEW_LINE, StringPool.QUOTE},
				new String[]{PlanIgualdadSeguimientoPortletKeys.LINE_BREAK, StringPool.APOSTROPHE});
	}

	public String getDocumentacionEjecucion() {
		return documentacionEjecucion;
	}

	public void setDocumentacionEjecucion(String documentacionEjecucion) {
		this.documentacionEjecucion = StringUtil.replace(documentacionEjecucion,
				new String[]{StringPool.NEW_LINE, StringPool.QUOTE},
				new String[]{PlanIgualdadSeguimientoPortletKeys.LINE_BREAK, StringPool.APOSTROPHE});
	}

	public Integer getNivelEjecucionId() {
		return nivelEjecucionId;
	}

	public void setNivelEjecucionId(Integer nivelEjecucionId) {
		this.nivelEjecucionId = nivelEjecucionId;
	}

	public String getNivelEjecucionNombre() {
		return nivelEjecucionNombre;
	}

	public void setNivelEjecucionNombre(String nivelEjecucionNombre) {
		this.nivelEjecucionNombre = nivelEjecucionNombre;
	}

	public Long getDocumentoId() {
		return documentoId;
	}

	public void setDocumentoId(Long documentoId) {
		this.documentoId = documentoId;
	}

	public String getMotivoNoIniciadoText() {
		return motivoNoIniciadoText;
	}

	public void setMotivoNoIniciadoText(String motivoNoIniciadoText) {
		this.motivoNoIniciadoText = motivoNoIniciadoText;
	}

}
