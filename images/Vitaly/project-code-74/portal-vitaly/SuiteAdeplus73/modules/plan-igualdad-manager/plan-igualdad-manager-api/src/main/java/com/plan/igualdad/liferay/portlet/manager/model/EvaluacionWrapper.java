/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.plan.igualdad.liferay.portlet.manager.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Evaluacion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Evaluacion
 * @generated
 */
public class EvaluacionWrapper
	extends BaseModelWrapper<Evaluacion>
	implements Evaluacion, ModelWrapper<Evaluacion> {

	public EvaluacionWrapper(Evaluacion evaluacion) {
		super(evaluacion);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("evaluacionId", getEvaluacionId());
		attributes.put("compId", getCompId());
		attributes.put("versionId", getVersionId());
		attributes.put("userId", getUserId());
		attributes.put("datosGenerales", getDatosGenerales());
		attributes.put("informacionResultados", getInformacionResultados());
		attributes.put("informacionImplantacion", getInformacionImplantacion());
		attributes.put("informacionImpacto", getInformacionImpacto());
		attributes.put("conclusion", getConclusion());
		attributes.put("observaciones", getObservaciones());
		attributes.put("versionEvaluacion", getVersionEvaluacion());
		attributes.put("createDate", getCreateDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long evaluacionId = (Long)attributes.get("evaluacionId");

		if (evaluacionId != null) {
			setEvaluacionId(evaluacionId);
		}

		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		Long versionId = (Long)attributes.get("versionId");

		if (versionId != null) {
			setVersionId(versionId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String datosGenerales = (String)attributes.get("datosGenerales");

		if (datosGenerales != null) {
			setDatosGenerales(datosGenerales);
		}

		String informacionResultados = (String)attributes.get(
			"informacionResultados");

		if (informacionResultados != null) {
			setInformacionResultados(informacionResultados);
		}

		String informacionImplantacion = (String)attributes.get(
			"informacionImplantacion");

		if (informacionImplantacion != null) {
			setInformacionImplantacion(informacionImplantacion);
		}

		String informacionImpacto = (String)attributes.get(
			"informacionImpacto");

		if (informacionImpacto != null) {
			setInformacionImpacto(informacionImpacto);
		}

		String conclusion = (String)attributes.get("conclusion");

		if (conclusion != null) {
			setConclusion(conclusion);
		}

		String observaciones = (String)attributes.get("observaciones");

		if (observaciones != null) {
			setObservaciones(observaciones);
		}

		Integer versionEvaluacion = (Integer)attributes.get(
			"versionEvaluacion");

		if (versionEvaluacion != null) {
			setVersionEvaluacion(versionEvaluacion);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}
	}

	/**
	 * Returns the comp ID of this evaluacion.
	 *
	 * @return the comp ID of this evaluacion
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the conclusion of this evaluacion.
	 *
	 * @return the conclusion of this evaluacion
	 */
	@Override
	public String getConclusion() {
		return model.getConclusion();
	}

	/**
	 * Returns the create date of this evaluacion.
	 *
	 * @return the create date of this evaluacion
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the datos generales of this evaluacion.
	 *
	 * @return the datos generales of this evaluacion
	 */
	@Override
	public String getDatosGenerales() {
		return model.getDatosGenerales();
	}

	/**
	 * Returns the evaluacion ID of this evaluacion.
	 *
	 * @return the evaluacion ID of this evaluacion
	 */
	@Override
	public long getEvaluacionId() {
		return model.getEvaluacionId();
	}

	/**
	 * Returns the informacion impacto of this evaluacion.
	 *
	 * @return the informacion impacto of this evaluacion
	 */
	@Override
	public String getInformacionImpacto() {
		return model.getInformacionImpacto();
	}

	/**
	 * Returns the informacion implantacion of this evaluacion.
	 *
	 * @return the informacion implantacion of this evaluacion
	 */
	@Override
	public String getInformacionImplantacion() {
		return model.getInformacionImplantacion();
	}

	/**
	 * Returns the informacion resultados of this evaluacion.
	 *
	 * @return the informacion resultados of this evaluacion
	 */
	@Override
	public String getInformacionResultados() {
		return model.getInformacionResultados();
	}

	/**
	 * Returns the observaciones of this evaluacion.
	 *
	 * @return the observaciones of this evaluacion
	 */
	@Override
	public String getObservaciones() {
		return model.getObservaciones();
	}

	/**
	 * Returns the primary key of this evaluacion.
	 *
	 * @return the primary key of this evaluacion
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this evaluacion.
	 *
	 * @return the user ID of this evaluacion
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this evaluacion.
	 *
	 * @return the user uuid of this evaluacion
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the version evaluacion of this evaluacion.
	 *
	 * @return the version evaluacion of this evaluacion
	 */
	@Override
	public Integer getVersionEvaluacion() {
		return model.getVersionEvaluacion();
	}

	/**
	 * Returns the version ID of this evaluacion.
	 *
	 * @return the version ID of this evaluacion
	 */
	@Override
	public long getVersionId() {
		return model.getVersionId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the comp ID of this evaluacion.
	 *
	 * @param compId the comp ID of this evaluacion
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the conclusion of this evaluacion.
	 *
	 * @param conclusion the conclusion of this evaluacion
	 */
	@Override
	public void setConclusion(String conclusion) {
		model.setConclusion(conclusion);
	}

	/**
	 * Sets the create date of this evaluacion.
	 *
	 * @param createDate the create date of this evaluacion
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the datos generales of this evaluacion.
	 *
	 * @param datosGenerales the datos generales of this evaluacion
	 */
	@Override
	public void setDatosGenerales(String datosGenerales) {
		model.setDatosGenerales(datosGenerales);
	}

	/**
	 * Sets the evaluacion ID of this evaluacion.
	 *
	 * @param evaluacionId the evaluacion ID of this evaluacion
	 */
	@Override
	public void setEvaluacionId(long evaluacionId) {
		model.setEvaluacionId(evaluacionId);
	}

	/**
	 * Sets the informacion impacto of this evaluacion.
	 *
	 * @param informacionImpacto the informacion impacto of this evaluacion
	 */
	@Override
	public void setInformacionImpacto(String informacionImpacto) {
		model.setInformacionImpacto(informacionImpacto);
	}

	/**
	 * Sets the informacion implantacion of this evaluacion.
	 *
	 * @param informacionImplantacion the informacion implantacion of this evaluacion
	 */
	@Override
	public void setInformacionImplantacion(String informacionImplantacion) {
		model.setInformacionImplantacion(informacionImplantacion);
	}

	/**
	 * Sets the informacion resultados of this evaluacion.
	 *
	 * @param informacionResultados the informacion resultados of this evaluacion
	 */
	@Override
	public void setInformacionResultados(String informacionResultados) {
		model.setInformacionResultados(informacionResultados);
	}

	/**
	 * Sets the observaciones of this evaluacion.
	 *
	 * @param observaciones the observaciones of this evaluacion
	 */
	@Override
	public void setObservaciones(String observaciones) {
		model.setObservaciones(observaciones);
	}

	/**
	 * Sets the primary key of this evaluacion.
	 *
	 * @param primaryKey the primary key of this evaluacion
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this evaluacion.
	 *
	 * @param userId the user ID of this evaluacion
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this evaluacion.
	 *
	 * @param userUuid the user uuid of this evaluacion
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the version evaluacion of this evaluacion.
	 *
	 * @param versionEvaluacion the version evaluacion of this evaluacion
	 */
	@Override
	public void setVersionEvaluacion(Integer versionEvaluacion) {
		model.setVersionEvaluacion(versionEvaluacion);
	}

	/**
	 * Sets the version ID of this evaluacion.
	 *
	 * @param versionId the version ID of this evaluacion
	 */
	@Override
	public void setVersionId(long versionId) {
		model.setVersionId(versionId);
	}

	@Override
	protected EvaluacionWrapper wrap(Evaluacion evaluacion) {
		return new EvaluacionWrapper(evaluacion);
	}

}