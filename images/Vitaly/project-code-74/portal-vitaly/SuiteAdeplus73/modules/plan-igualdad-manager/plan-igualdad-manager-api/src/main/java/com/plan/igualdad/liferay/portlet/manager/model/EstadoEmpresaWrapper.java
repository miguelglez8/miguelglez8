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
 * This class is a wrapper for {@link EstadoEmpresa}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EstadoEmpresa
 * @generated
 */
public class EstadoEmpresaWrapper
	extends BaseModelWrapper<EstadoEmpresa>
	implements EstadoEmpresa, ModelWrapper<EstadoEmpresa> {

	public EstadoEmpresaWrapper(EstadoEmpresa estadoEmpresa) {
		super(estadoEmpresa);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("compId", getCompId());
		attributes.put("estadoId", getEstadoId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		String estadoId = (String)attributes.get("estadoId");

		if (estadoId != null) {
			setEstadoId(estadoId);
		}
	}

	/**
	 * Returns the comp ID of this estado empresa.
	 *
	 * @return the comp ID of this estado empresa
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the estado ID of this estado empresa.
	 *
	 * @return the estado ID of this estado empresa
	 */
	@Override
	public String getEstadoId() {
		return model.getEstadoId();
	}

	/**
	 * Returns the primary key of this estado empresa.
	 *
	 * @return the primary key of this estado empresa
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the comp ID of this estado empresa.
	 *
	 * @param compId the comp ID of this estado empresa
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the estado ID of this estado empresa.
	 *
	 * @param estadoId the estado ID of this estado empresa
	 */
	@Override
	public void setEstadoId(String estadoId) {
		model.setEstadoId(estadoId);
	}

	/**
	 * Sets the primary key of this estado empresa.
	 *
	 * @param primaryKey the primary key of this estado empresa
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected EstadoEmpresaWrapper wrap(EstadoEmpresa estadoEmpresa) {
		return new EstadoEmpresaWrapper(estadoEmpresa);
	}

}