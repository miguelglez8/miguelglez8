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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Respuesta}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Respuesta
 * @generated
 */
public class RespuestaWrapper
	extends BaseModelWrapper<Respuesta>
	implements ModelWrapper<Respuesta>, Respuesta {

	public RespuestaWrapper(Respuesta respuesta) {
		super(respuesta);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("compId", getCompId());
		attributes.put("versionId", getVersionId());
		attributes.put("seccionId", getSeccionId());
		attributes.put("respuestas", getRespuestas());
		attributes.put("estado", getEstado());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		Long versionId = (Long)attributes.get("versionId");

		if (versionId != null) {
			setVersionId(versionId);
		}

		Long seccionId = (Long)attributes.get("seccionId");

		if (seccionId != null) {
			setSeccionId(seccionId);
		}

		String respuestas = (String)attributes.get("respuestas");

		if (respuestas != null) {
			setRespuestas(respuestas);
		}

		Long estado = (Long)attributes.get("estado");

		if (estado != null) {
			setEstado(estado);
		}
	}

	/**
	 * Returns the comp ID of this respuesta.
	 *
	 * @return the comp ID of this respuesta
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the estado of this respuesta.
	 *
	 * @return the estado of this respuesta
	 */
	@Override
	public long getEstado() {
		return model.getEstado();
	}

	/**
	 * Returns the primary key of this respuesta.
	 *
	 * @return the primary key of this respuesta
	 */
	@Override
	public
		com.plan.igualdad.liferay.portlet.manager.service.persistence.
			RespuestaPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the respuestas of this respuesta.
	 *
	 * @return the respuestas of this respuesta
	 */
	@Override
	public String getRespuestas() {
		return model.getRespuestas();
	}

	/**
	 * Returns the seccion ID of this respuesta.
	 *
	 * @return the seccion ID of this respuesta
	 */
	@Override
	public long getSeccionId() {
		return model.getSeccionId();
	}

	/**
	 * Returns the version ID of this respuesta.
	 *
	 * @return the version ID of this respuesta
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
	 * Sets the comp ID of this respuesta.
	 *
	 * @param compId the comp ID of this respuesta
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the estado of this respuesta.
	 *
	 * @param estado the estado of this respuesta
	 */
	@Override
	public void setEstado(long estado) {
		model.setEstado(estado);
	}

	/**
	 * Sets the primary key of this respuesta.
	 *
	 * @param primaryKey the primary key of this respuesta
	 */
	@Override
	public void setPrimaryKey(
		com.plan.igualdad.liferay.portlet.manager.service.persistence.
			RespuestaPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the respuestas of this respuesta.
	 *
	 * @param respuestas the respuestas of this respuesta
	 */
	@Override
	public void setRespuestas(String respuestas) {
		model.setRespuestas(respuestas);
	}

	/**
	 * Sets the seccion ID of this respuesta.
	 *
	 * @param seccionId the seccion ID of this respuesta
	 */
	@Override
	public void setSeccionId(long seccionId) {
		model.setSeccionId(seccionId);
	}

	/**
	 * Sets the version ID of this respuesta.
	 *
	 * @param versionId the version ID of this respuesta
	 */
	@Override
	public void setVersionId(long versionId) {
		model.setVersionId(versionId);
	}

	@Override
	protected RespuestaWrapper wrap(Respuesta respuesta) {
		return new RespuestaWrapper(respuesta);
	}

}