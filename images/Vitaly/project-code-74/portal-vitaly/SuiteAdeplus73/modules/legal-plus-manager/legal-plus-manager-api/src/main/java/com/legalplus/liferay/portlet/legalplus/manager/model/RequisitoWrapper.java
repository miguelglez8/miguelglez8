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

package com.legalplus.liferay.portlet.legalplus.manager.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Requisito}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Requisito
 * @generated
 */
public class RequisitoWrapper
	extends BaseModelWrapper<Requisito>
	implements ModelWrapper<Requisito>, Requisito {

	public RequisitoWrapper(Requisito requisito) {
		super(requisito);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("legislacionId", getLegislacionId());
		attributes.put("requisitoId", getRequisitoId());
		attributes.put("descripcion", getDescripcion());
		attributes.put("baja", getBaja());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String legislacionId = (String)attributes.get("legislacionId");

		if (legislacionId != null) {
			setLegislacionId(legislacionId);
		}

		String requisitoId = (String)attributes.get("requisitoId");

		if (requisitoId != null) {
			setRequisitoId(requisitoId);
		}

		String descripcion = (String)attributes.get("descripcion");

		if (descripcion != null) {
			setDescripcion(descripcion);
		}

		Date baja = (Date)attributes.get("baja");

		if (baja != null) {
			setBaja(baja);
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
	 * Returns the baja of this requisito.
	 *
	 * @return the baja of this requisito
	 */
	@Override
	public Date getBaja() {
		return model.getBaja();
	}

	/**
	 * Returns the create date of this requisito.
	 *
	 * @return the create date of this requisito
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the descripcion of this requisito.
	 *
	 * @return the descripcion of this requisito
	 */
	@Override
	public String getDescripcion() {
		return model.getDescripcion();
	}

	/**
	 * Returns the legislacion ID of this requisito.
	 *
	 * @return the legislacion ID of this requisito
	 */
	@Override
	public String getLegislacionId() {
		return model.getLegislacionId();
	}

	/**
	 * Returns the modified date of this requisito.
	 *
	 * @return the modified date of this requisito
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this requisito.
	 *
	 * @return the primary key of this requisito
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
		RequisitoPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the requisito ID of this requisito.
	 *
	 * @return the requisito ID of this requisito
	 */
	@Override
	public String getRequisitoId() {
		return model.getRequisitoId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the baja of this requisito.
	 *
	 * @param baja the baja of this requisito
	 */
	@Override
	public void setBaja(Date baja) {
		model.setBaja(baja);
	}

	/**
	 * Sets the create date of this requisito.
	 *
	 * @param createDate the create date of this requisito
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the descripcion of this requisito.
	 *
	 * @param descripcion the descripcion of this requisito
	 */
	@Override
	public void setDescripcion(String descripcion) {
		model.setDescripcion(descripcion);
	}

	/**
	 * Sets the legislacion ID of this requisito.
	 *
	 * @param legislacionId the legislacion ID of this requisito
	 */
	@Override
	public void setLegislacionId(String legislacionId) {
		model.setLegislacionId(legislacionId);
	}

	/**
	 * Sets the modified date of this requisito.
	 *
	 * @param modifiedDate the modified date of this requisito
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this requisito.
	 *
	 * @param primaryKey the primary key of this requisito
	 */
	@Override
	public void setPrimaryKey(
		com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
			RequisitoPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the requisito ID of this requisito.
	 *
	 * @param requisitoId the requisito ID of this requisito
	 */
	@Override
	public void setRequisitoId(String requisitoId) {
		model.setRequisitoId(requisitoId);
	}

	@Override
	protected RequisitoWrapper wrap(Requisito requisito) {
		return new RequisitoWrapper(requisito);
	}

}