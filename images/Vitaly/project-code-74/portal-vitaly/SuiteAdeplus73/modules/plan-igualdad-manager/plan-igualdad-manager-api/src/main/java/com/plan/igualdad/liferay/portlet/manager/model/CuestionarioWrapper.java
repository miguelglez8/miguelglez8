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
 * This class is a wrapper for {@link Cuestionario}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Cuestionario
 * @generated
 */
public class CuestionarioWrapper
	extends BaseModelWrapper<Cuestionario>
	implements Cuestionario, ModelWrapper<Cuestionario> {

	public CuestionarioWrapper(Cuestionario cuestionario) {
		super(cuestionario);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("cuestionarioId", getCuestionarioId());
		attributes.put("compId", getCompId());
		attributes.put("versionId", getVersionId());
		attributes.put("userId", getUserId());
		attributes.put("respuestasCuestionario", getRespuestasCuestionario());
		attributes.put("observaciones", getObservaciones());
		attributes.put("versionCuestionario", getVersionCuestionario());
		attributes.put("createDate", getCreateDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long cuestionarioId = (Long)attributes.get("cuestionarioId");

		if (cuestionarioId != null) {
			setCuestionarioId(cuestionarioId);
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

		String respuestasCuestionario = (String)attributes.get(
			"respuestasCuestionario");

		if (respuestasCuestionario != null) {
			setRespuestasCuestionario(respuestasCuestionario);
		}

		String observaciones = (String)attributes.get("observaciones");

		if (observaciones != null) {
			setObservaciones(observaciones);
		}

		Integer versionCuestionario = (Integer)attributes.get(
			"versionCuestionario");

		if (versionCuestionario != null) {
			setVersionCuestionario(versionCuestionario);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}
	}

	/**
	 * Returns the comp ID of this cuestionario.
	 *
	 * @return the comp ID of this cuestionario
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the create date of this cuestionario.
	 *
	 * @return the create date of this cuestionario
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the cuestionario ID of this cuestionario.
	 *
	 * @return the cuestionario ID of this cuestionario
	 */
	@Override
	public long getCuestionarioId() {
		return model.getCuestionarioId();
	}

	/**
	 * Returns the observaciones of this cuestionario.
	 *
	 * @return the observaciones of this cuestionario
	 */
	@Override
	public String getObservaciones() {
		return model.getObservaciones();
	}

	/**
	 * Returns the primary key of this cuestionario.
	 *
	 * @return the primary key of this cuestionario
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the respuestas cuestionario of this cuestionario.
	 *
	 * @return the respuestas cuestionario of this cuestionario
	 */
	@Override
	public String getRespuestasCuestionario() {
		return model.getRespuestasCuestionario();
	}

	/**
	 * Returns the user ID of this cuestionario.
	 *
	 * @return the user ID of this cuestionario
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this cuestionario.
	 *
	 * @return the user uuid of this cuestionario
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the version cuestionario of this cuestionario.
	 *
	 * @return the version cuestionario of this cuestionario
	 */
	@Override
	public Integer getVersionCuestionario() {
		return model.getVersionCuestionario();
	}

	/**
	 * Returns the version ID of this cuestionario.
	 *
	 * @return the version ID of this cuestionario
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
	 * Sets the comp ID of this cuestionario.
	 *
	 * @param compId the comp ID of this cuestionario
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the create date of this cuestionario.
	 *
	 * @param createDate the create date of this cuestionario
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the cuestionario ID of this cuestionario.
	 *
	 * @param cuestionarioId the cuestionario ID of this cuestionario
	 */
	@Override
	public void setCuestionarioId(long cuestionarioId) {
		model.setCuestionarioId(cuestionarioId);
	}

	/**
	 * Sets the observaciones of this cuestionario.
	 *
	 * @param observaciones the observaciones of this cuestionario
	 */
	@Override
	public void setObservaciones(String observaciones) {
		model.setObservaciones(observaciones);
	}

	/**
	 * Sets the primary key of this cuestionario.
	 *
	 * @param primaryKey the primary key of this cuestionario
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the respuestas cuestionario of this cuestionario.
	 *
	 * @param respuestasCuestionario the respuestas cuestionario of this cuestionario
	 */
	@Override
	public void setRespuestasCuestionario(String respuestasCuestionario) {
		model.setRespuestasCuestionario(respuestasCuestionario);
	}

	/**
	 * Sets the user ID of this cuestionario.
	 *
	 * @param userId the user ID of this cuestionario
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this cuestionario.
	 *
	 * @param userUuid the user uuid of this cuestionario
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the version cuestionario of this cuestionario.
	 *
	 * @param versionCuestionario the version cuestionario of this cuestionario
	 */
	@Override
	public void setVersionCuestionario(Integer versionCuestionario) {
		model.setVersionCuestionario(versionCuestionario);
	}

	/**
	 * Sets the version ID of this cuestionario.
	 *
	 * @param versionId the version ID of this cuestionario
	 */
	@Override
	public void setVersionId(long versionId) {
		model.setVersionId(versionId);
	}

	@Override
	protected CuestionarioWrapper wrap(Cuestionario cuestionario) {
		return new CuestionarioWrapper(cuestionario);
	}

}