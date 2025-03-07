package com.plan.igualdad.liferay.seguimiento.dto;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.plan.igualdad.liferay.seguimiento.constants.PlanIgualdadSeguimientoPortletKeys;

import java.io.Serializable;

public class DatosGeneralesDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer materiaId;
	private String deshabilitada;
	private String materiaNombre;
	private String nombreMedida;
	private String descripcionMedida;
	private String objetivos;
	private Integer prioridadId;
	private String prioridadNombre;
	private String aplica;
	private String recursosAsociados;
	private String indicadoresSeguimiento;

	public Integer getMateriaId() {
		return materiaId;
	}

	public void setMateriaId(Integer materiaId) {
		this.materiaId = materiaId;
	}

	public String getDeshabilitada() {
		return deshabilitada;
	}

	public void setDeshabilitada(String deshabilitada) {
		this.deshabilitada = deshabilitada;
	}

	public String getMateriaNombre() {
		return materiaNombre;
	}

	public void setMateriaNombre(String materiaNombre) {
		this.materiaNombre = materiaNombre;
	}

	public String getNombreMedida() {
		return nombreMedida;
	}

	public void setNombreMedida(String nombreMedida) {
		this.nombreMedida = nombreMedida;
	}

	public String getDescripcionMedida() {
		return descripcionMedida;
	}

	public void setDescripcionMedida(String descripcionMedida) {
		this.descripcionMedida = StringUtil.replace(descripcionMedida,
				new String[] { StringPool.NEW_LINE, StringPool.QUOTE },
				new String[] { PlanIgualdadSeguimientoPortletKeys.LINE_BREAK, StringPool.APOSTROPHE });
	}

	public String getAplica() {
		return aplica;
	}

	public void setAplica(String aplica) {
		this.aplica = aplica;
	}

	public String getRecursosAsociados() {
		return recursosAsociados;
	}

	public void setRecursosAsociados(String recursosAsociados) {
		this.recursosAsociados = StringUtil.replace(recursosAsociados,
				new String[] { StringPool.NEW_LINE, StringPool.QUOTE },
				new String[] { PlanIgualdadSeguimientoPortletKeys.LINE_BREAK, StringPool.APOSTROPHE });
	}

	public String getIndicadoresSeguimiento() {
		return indicadoresSeguimiento;
	}

	public void setIndicadoresSeguimiento(String indicadoresSeguimiento) {
		this.indicadoresSeguimiento = StringUtil.replace(indicadoresSeguimiento,
				new String[] { StringPool.NEW_LINE, StringPool.QUOTE },
				new String[] { PlanIgualdadSeguimientoPortletKeys.LINE_BREAK, StringPool.APOSTROPHE });
	}

	public Integer getPrioridadId() {
		return prioridadId;
	}

	public void setPrioridadId(Integer prioridadId) {
		this.prioridadId = prioridadId;
	}

	public String getPrioridadNombre() {
		return prioridadNombre;
	}

	public void setPrioridadNombre(String prioridadNombre) {
		this.prioridadNombre = prioridadNombre;
	}

	public String getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(String objetivos) {
		this.objetivos = StringUtil.replace(objetivos, new String[] { StringPool.NEW_LINE, StringPool.QUOTE },
				new String[] { PlanIgualdadSeguimientoPortletKeys.LINE_BREAK, StringPool.APOSTROPHE });
	}

}
