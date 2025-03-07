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
 * This class is a wrapper for {@link Valoracion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Valoracion
 * @generated
 */
public class ValoracionWrapper
	extends BaseModelWrapper<Valoracion>
	implements ModelWrapper<Valoracion>, Valoracion {

	public ValoracionWrapper(Valoracion valoracion) {
		super(valoracion);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("valoracionId", getValoracionId());
		attributes.put("compId", getCompId());
		attributes.put("versionId", getVersionId());
		attributes.put("userId", getUserId());
		attributes.put("respuestasValoracion", getRespuestasValoracion());
		attributes.put("observaciones", getObservaciones());
		attributes.put("versionValoracion", getVersionValoracion());
		attributes.put("createDate", getCreateDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long valoracionId = (Long)attributes.get("valoracionId");

		if (valoracionId != null) {
			setValoracionId(valoracionId);
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

		String respuestasValoracion = (String)attributes.get(
			"respuestasValoracion");

		if (respuestasValoracion != null) {
			setRespuestasValoracion(respuestasValoracion);
		}

		String observaciones = (String)attributes.get("observaciones");

		if (observaciones != null) {
			setObservaciones(observaciones);
		}

		Integer versionValoracion = (Integer)attributes.get(
			"versionValoracion");

		if (versionValoracion != null) {
			setVersionValoracion(versionValoracion);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}
	}

	/**
	 * Returns the comp ID of this valoracion.
	 *
	 * @return the comp ID of this valoracion
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the create date of this valoracion.
	 *
	 * @return the create date of this valoracion
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the observaciones of this valoracion.
	 *
	 * @return the observaciones of this valoracion
	 */
	@Override
	public String getObservaciones() {
		return model.getObservaciones();
	}

	/**
	 * Returns the primary key of this valoracion.
	 *
	 * @return the primary key of this valoracion
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the respuestas valoracion of this valoracion.
	 *
	 * @return the respuestas valoracion of this valoracion
	 */
	@Override
	public String getRespuestasValoracion() {
		return model.getRespuestasValoracion();
	}

	/**
	 * Returns the user ID of this valoracion.
	 *
	 * @return the user ID of this valoracion
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this valoracion.
	 *
	 * @return the user uuid of this valoracion
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the valoracion ID of this valoracion.
	 *
	 * @return the valoracion ID of this valoracion
	 */
	@Override
	public long getValoracionId() {
		return model.getValoracionId();
	}

	/**
	 * Returns the version ID of this valoracion.
	 *
	 * @return the version ID of this valoracion
	 */
	@Override
	public long getVersionId() {
		return model.getVersionId();
	}

	/**
	 * Returns the version valoracion of this valoracion.
	 *
	 * @return the version valoracion of this valoracion
	 */
	@Override
	public Integer getVersionValoracion() {
		return model.getVersionValoracion();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the comp ID of this valoracion.
	 *
	 * @param compId the comp ID of this valoracion
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the create date of this valoracion.
	 *
	 * @param createDate the create date of this valoracion
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the observaciones of this valoracion.
	 *
	 * @param observaciones the observaciones of this valoracion
	 */
	@Override
	public void setObservaciones(String observaciones) {
		model.setObservaciones(observaciones);
	}

	/**
	 * Sets the primary key of this valoracion.
	 *
	 * @param primaryKey the primary key of this valoracion
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the respuestas valoracion of this valoracion.
	 *
	 * @param respuestasValoracion the respuestas valoracion of this valoracion
	 */
	@Override
	public void setRespuestasValoracion(String respuestasValoracion) {
		model.setRespuestasValoracion(respuestasValoracion);
	}

	/**
	 * Sets the user ID of this valoracion.
	 *
	 * @param userId the user ID of this valoracion
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this valoracion.
	 *
	 * @param userUuid the user uuid of this valoracion
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the valoracion ID of this valoracion.
	 *
	 * @param valoracionId the valoracion ID of this valoracion
	 */
	@Override
	public void setValoracionId(long valoracionId) {
		model.setValoracionId(valoracionId);
	}

	/**
	 * Sets the version ID of this valoracion.
	 *
	 * @param versionId the version ID of this valoracion
	 */
	@Override
	public void setVersionId(long versionId) {
		model.setVersionId(versionId);
	}

	/**
	 * Sets the version valoracion of this valoracion.
	 *
	 * @param versionValoracion the version valoracion of this valoracion
	 */
	@Override
	public void setVersionValoracion(Integer versionValoracion) {
		model.setVersionValoracion(versionValoracion);
	}

	@Override
	protected ValoracionWrapper wrap(Valoracion valoracion) {
		return new ValoracionWrapper(valoracion);
	}

}