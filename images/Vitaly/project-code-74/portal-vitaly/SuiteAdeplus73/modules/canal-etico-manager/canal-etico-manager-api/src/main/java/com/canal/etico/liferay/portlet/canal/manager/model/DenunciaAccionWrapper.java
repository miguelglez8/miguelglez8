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
 * This class is a wrapper for {@link DenunciaAccion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DenunciaAccion
 * @generated
 */
public class DenunciaAccionWrapper
	extends BaseModelWrapper<DenunciaAccion>
	implements DenunciaAccion, ModelWrapper<DenunciaAccion> {

	public DenunciaAccionWrapper(DenunciaAccion denunciaAccion) {
		super(denunciaAccion);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("denunciaAccionId", getDenunciaAccionId());
		attributes.put("denunciaId", getDenunciaId());
		attributes.put("accionId", getAccionId());
		attributes.put("userId", getUserId());
		attributes.put("observaciones", getObservaciones());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long denunciaAccionId = (Long)attributes.get("denunciaAccionId");

		if (denunciaAccionId != null) {
			setDenunciaAccionId(denunciaAccionId);
		}

		Long denunciaId = (Long)attributes.get("denunciaId");

		if (denunciaId != null) {
			setDenunciaId(denunciaId);
		}

		Long accionId = (Long)attributes.get("accionId");

		if (accionId != null) {
			setAccionId(accionId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
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
	 * Returns the accion ID of this denuncia accion.
	 *
	 * @return the accion ID of this denuncia accion
	 */
	@Override
	public long getAccionId() {
		return model.getAccionId();
	}

	/**
	 * Returns the create date of this denuncia accion.
	 *
	 * @return the create date of this denuncia accion
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the denuncia accion ID of this denuncia accion.
	 *
	 * @return the denuncia accion ID of this denuncia accion
	 */
	@Override
	public long getDenunciaAccionId() {
		return model.getDenunciaAccionId();
	}

	/**
	 * Returns the denuncia ID of this denuncia accion.
	 *
	 * @return the denuncia ID of this denuncia accion
	 */
	@Override
	public long getDenunciaId() {
		return model.getDenunciaId();
	}

	/**
	 * Returns the modified date of this denuncia accion.
	 *
	 * @return the modified date of this denuncia accion
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the observaciones of this denuncia accion.
	 *
	 * @return the observaciones of this denuncia accion
	 */
	@Override
	public String getObservaciones() {
		return model.getObservaciones();
	}

	/**
	 * Returns the primary key of this denuncia accion.
	 *
	 * @return the primary key of this denuncia accion
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this denuncia accion.
	 *
	 * @return the user ID of this denuncia accion
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this denuncia accion.
	 *
	 * @return the user uuid of this denuncia accion
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
	 * Sets the accion ID of this denuncia accion.
	 *
	 * @param accionId the accion ID of this denuncia accion
	 */
	@Override
	public void setAccionId(long accionId) {
		model.setAccionId(accionId);
	}

	/**
	 * Sets the create date of this denuncia accion.
	 *
	 * @param createDate the create date of this denuncia accion
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the denuncia accion ID of this denuncia accion.
	 *
	 * @param denunciaAccionId the denuncia accion ID of this denuncia accion
	 */
	@Override
	public void setDenunciaAccionId(long denunciaAccionId) {
		model.setDenunciaAccionId(denunciaAccionId);
	}

	/**
	 * Sets the denuncia ID of this denuncia accion.
	 *
	 * @param denunciaId the denuncia ID of this denuncia accion
	 */
	@Override
	public void setDenunciaId(long denunciaId) {
		model.setDenunciaId(denunciaId);
	}

	/**
	 * Sets the modified date of this denuncia accion.
	 *
	 * @param modifiedDate the modified date of this denuncia accion
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the observaciones of this denuncia accion.
	 *
	 * @param observaciones the observaciones of this denuncia accion
	 */
	@Override
	public void setObservaciones(String observaciones) {
		model.setObservaciones(observaciones);
	}

	/**
	 * Sets the primary key of this denuncia accion.
	 *
	 * @param primaryKey the primary key of this denuncia accion
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this denuncia accion.
	 *
	 * @param userId the user ID of this denuncia accion
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this denuncia accion.
	 *
	 * @param userUuid the user uuid of this denuncia accion
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected DenunciaAccionWrapper wrap(DenunciaAccion denunciaAccion) {
		return new DenunciaAccionWrapper(denunciaAccion);
	}

}