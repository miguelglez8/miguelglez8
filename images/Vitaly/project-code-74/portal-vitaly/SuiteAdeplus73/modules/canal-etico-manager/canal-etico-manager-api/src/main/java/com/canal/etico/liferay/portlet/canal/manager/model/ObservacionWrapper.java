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

package com.canal.etico.liferay.portlet.canal.manager.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Observacion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Observacion
 * @generated
 */
public class ObservacionWrapper
	extends BaseModelWrapper<Observacion>
	implements ModelWrapper<Observacion>, Observacion {

	public ObservacionWrapper(Observacion observacion) {
		super(observacion);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("observacionId", getObservacionId());
		attributes.put("denunciaId", getDenunciaId());
		attributes.put("userId", getUserId());
		attributes.put("descripcion", getDescripcion());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long observacionId = (Long)attributes.get("observacionId");

		if (observacionId != null) {
			setObservacionId(observacionId);
		}

		Long denunciaId = (Long)attributes.get("denunciaId");

		if (denunciaId != null) {
			setDenunciaId(denunciaId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String descripcion = (String)attributes.get("descripcion");

		if (descripcion != null) {
			setDescripcion(descripcion);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	/**
	 * Returns the create date of this observacion.
	 *
	 * @return the create date of this observacion
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the denuncia ID of this observacion.
	 *
	 * @return the denuncia ID of this observacion
	 */
	@Override
	public long getDenunciaId() {
		return model.getDenunciaId();
	}

	/**
	 * Returns the descripcion of this observacion.
	 *
	 * @return the descripcion of this observacion
	 */
	@Override
	public String getDescripcion() {
		return model.getDescripcion();
	}

	/**
	 * Returns the modified date of this observacion.
	 *
	 * @return the modified date of this observacion
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the observacion ID of this observacion.
	 *
	 * @return the observacion ID of this observacion
	 */
	@Override
	public long getObservacionId() {
		return model.getObservacionId();
	}

	/**
	 * Returns the primary key of this observacion.
	 *
	 * @return the primary key of this observacion
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this observacion.
	 *
	 * @return the user ID of this observacion
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this observacion.
	 *
	 * @return the user uuid of this observacion
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the create date of this observacion.
	 *
	 * @param createDate the create date of this observacion
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the denuncia ID of this observacion.
	 *
	 * @param denunciaId the denuncia ID of this observacion
	 */
	@Override
	public void setDenunciaId(long denunciaId) {
		model.setDenunciaId(denunciaId);
	}

	/**
	 * Sets the descripcion of this observacion.
	 *
	 * @param descripcion the descripcion of this observacion
	 */
	@Override
	public void setDescripcion(String descripcion) {
		model.setDescripcion(descripcion);
	}

	/**
	 * Sets the modified date of this observacion.
	 *
	 * @param modifiedDate the modified date of this observacion
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the observacion ID of this observacion.
	 *
	 * @param observacionId the observacion ID of this observacion
	 */
	@Override
	public void setObservacionId(long observacionId) {
		model.setObservacionId(observacionId);
	}

	/**
	 * Sets the primary key of this observacion.
	 *
	 * @param primaryKey the primary key of this observacion
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this observacion.
	 *
	 * @param userId the user ID of this observacion
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this observacion.
	 *
	 * @param userUuid the user uuid of this observacion
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected ObservacionWrapper wrap(Observacion observacion) {
		return new ObservacionWrapper(observacion);
	}

}