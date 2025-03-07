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
 * This class is a wrapper for {@link Estadistica}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Estadistica
 * @generated
 */
public class EstadisticaWrapper
	extends BaseModelWrapper<Estadistica>
	implements Estadistica, ModelWrapper<Estadistica> {

	public EstadisticaWrapper(Estadistica estadistica) {
		super(estadistica);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("compId", getCompId());
		attributes.put("versionId", getVersionId());
		attributes.put("seccionId", getSeccionId());
		attributes.put("estadistica", getEstadistica());

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

		String estadistica = (String)attributes.get("estadistica");

		if (estadistica != null) {
			setEstadistica(estadistica);
		}
	}

	/**
	 * Returns the comp ID of this estadistica.
	 *
	 * @return the comp ID of this estadistica
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the estadistica of this estadistica.
	 *
	 * @return the estadistica of this estadistica
	 */
	@Override
	public String getEstadistica() {
		return model.getEstadistica();
	}

	/**
	 * Returns the primary key of this estadistica.
	 *
	 * @return the primary key of this estadistica
	 */
	@Override
	public
		com.plan.igualdad.liferay.portlet.manager.service.persistence.
			EstadisticaPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the seccion ID of this estadistica.
	 *
	 * @return the seccion ID of this estadistica
	 */
	@Override
	public long getSeccionId() {
		return model.getSeccionId();
	}

	/**
	 * Returns the version ID of this estadistica.
	 *
	 * @return the version ID of this estadistica
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
	 * Sets the comp ID of this estadistica.
	 *
	 * @param compId the comp ID of this estadistica
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the estadistica of this estadistica.
	 *
	 * @param estadistica the estadistica of this estadistica
	 */
	@Override
	public void setEstadistica(String estadistica) {
		model.setEstadistica(estadistica);
	}

	/**
	 * Sets the primary key of this estadistica.
	 *
	 * @param primaryKey the primary key of this estadistica
	 */
	@Override
	public void setPrimaryKey(
		com.plan.igualdad.liferay.portlet.manager.service.persistence.
			EstadisticaPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the seccion ID of this estadistica.
	 *
	 * @param seccionId the seccion ID of this estadistica
	 */
	@Override
	public void setSeccionId(long seccionId) {
		model.setSeccionId(seccionId);
	}

	/**
	 * Sets the version ID of this estadistica.
	 *
	 * @param versionId the version ID of this estadistica
	 */
	@Override
	public void setVersionId(long versionId) {
		model.setVersionId(versionId);
	}

	@Override
	protected EstadisticaWrapper wrap(Estadistica estadistica) {
		return new EstadisticaWrapper(estadistica);
	}

}