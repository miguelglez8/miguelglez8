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
 * This class is a wrapper for {@link Comunicacion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Comunicacion
 * @generated
 */
public class ComunicacionWrapper
	extends BaseModelWrapper<Comunicacion>
	implements Comunicacion, ModelWrapper<Comunicacion> {

	public ComunicacionWrapper(Comunicacion comunicacion) {
		super(comunicacion);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("comunicacionId", getComunicacionId());
		attributes.put("denunciaId", getDenunciaId());
		attributes.put("userId", getUserId());
		attributes.put("descripcion", getDescripcion());
		attributes.put("adjunto", getAdjunto());
		attributes.put("observaciones", getObservaciones());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long comunicacionId = (Long)attributes.get("comunicacionId");

		if (comunicacionId != null) {
			setComunicacionId(comunicacionId);
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

		Long adjunto = (Long)attributes.get("adjunto");

		if (adjunto != null) {
			setAdjunto(adjunto);
		}

		String observaciones = (String)attributes.get("observaciones");

		if (observaciones != null) {
			setObservaciones(observaciones);
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
	 * Returns the adjunto of this comunicacion.
	 *
	 * @return the adjunto of this comunicacion
	 */
	@Override
	public long getAdjunto() {
		return model.getAdjunto();
	}

	/**
	 * Returns the comunicacion ID of this comunicacion.
	 *
	 * @return the comunicacion ID of this comunicacion
	 */
	@Override
	public long getComunicacionId() {
		return model.getComunicacionId();
	}

	/**
	 * Returns the create date of this comunicacion.
	 *
	 * @return the create date of this comunicacion
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the denuncia ID of this comunicacion.
	 *
	 * @return the denuncia ID of this comunicacion
	 */
	@Override
	public long getDenunciaId() {
		return model.getDenunciaId();
	}

	/**
	 * Returns the descripcion of this comunicacion.
	 *
	 * @return the descripcion of this comunicacion
	 */
	@Override
	public String getDescripcion() {
		return model.getDescripcion();
	}

	/**
	 * Returns the modified date of this comunicacion.
	 *
	 * @return the modified date of this comunicacion
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the observaciones of this comunicacion.
	 *
	 * @return the observaciones of this comunicacion
	 */
	@Override
	public String getObservaciones() {
		return model.getObservaciones();
	}

	/**
	 * Returns the primary key of this comunicacion.
	 *
	 * @return the primary key of this comunicacion
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this comunicacion.
	 *
	 * @return the user ID of this comunicacion
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this comunicacion.
	 *
	 * @return the user uuid of this comunicacion
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
	 * Sets the adjunto of this comunicacion.
	 *
	 * @param adjunto the adjunto of this comunicacion
	 */
	@Override
	public void setAdjunto(long adjunto) {
		model.setAdjunto(adjunto);
	}

	/**
	 * Sets the comunicacion ID of this comunicacion.
	 *
	 * @param comunicacionId the comunicacion ID of this comunicacion
	 */
	@Override
	public void setComunicacionId(long comunicacionId) {
		model.setComunicacionId(comunicacionId);
	}

	/**
	 * Sets the create date of this comunicacion.
	 *
	 * @param createDate the create date of this comunicacion
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the denuncia ID of this comunicacion.
	 *
	 * @param denunciaId the denuncia ID of this comunicacion
	 */
	@Override
	public void setDenunciaId(long denunciaId) {
		model.setDenunciaId(denunciaId);
	}

	/**
	 * Sets the descripcion of this comunicacion.
	 *
	 * @param descripcion the descripcion of this comunicacion
	 */
	@Override
	public void setDescripcion(String descripcion) {
		model.setDescripcion(descripcion);
	}

	/**
	 * Sets the modified date of this comunicacion.
	 *
	 * @param modifiedDate the modified date of this comunicacion
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the observaciones of this comunicacion.
	 *
	 * @param observaciones the observaciones of this comunicacion
	 */
	@Override
	public void setObservaciones(String observaciones) {
		model.setObservaciones(observaciones);
	}

	/**
	 * Sets the primary key of this comunicacion.
	 *
	 * @param primaryKey the primary key of this comunicacion
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this comunicacion.
	 *
	 * @param userId the user ID of this comunicacion
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this comunicacion.
	 *
	 * @param userUuid the user uuid of this comunicacion
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected ComunicacionWrapper wrap(Comunicacion comunicacion) {
		return new ComunicacionWrapper(comunicacion);
	}

}