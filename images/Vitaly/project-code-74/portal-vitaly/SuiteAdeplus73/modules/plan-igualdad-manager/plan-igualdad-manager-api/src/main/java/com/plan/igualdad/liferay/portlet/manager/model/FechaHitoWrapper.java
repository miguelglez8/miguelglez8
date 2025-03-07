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
 * This class is a wrapper for {@link FechaHito}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FechaHito
 * @generated
 */
public class FechaHitoWrapper
	extends BaseModelWrapper<FechaHito>
	implements FechaHito, ModelWrapper<FechaHito> {

	public FechaHitoWrapper(FechaHito fechaHito) {
		super(fechaHito);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("hitoId", getHitoId());
		attributes.put("versionId", getVersionId());
		attributes.put("compId", getCompId());
		attributes.put("fecha", getFecha());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long hitoId = (Long)attributes.get("hitoId");

		if (hitoId != null) {
			setHitoId(hitoId);
		}

		Long versionId = (Long)attributes.get("versionId");

		if (versionId != null) {
			setVersionId(versionId);
		}

		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		Date fecha = (Date)attributes.get("fecha");

		if (fecha != null) {
			setFecha(fecha);
		}
	}

	/**
	 * Returns the comp ID of this fecha hito.
	 *
	 * @return the comp ID of this fecha hito
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the fecha of this fecha hito.
	 *
	 * @return the fecha of this fecha hito
	 */
	@Override
	public Date getFecha() {
		return model.getFecha();
	}

	/**
	 * Returns the hito ID of this fecha hito.
	 *
	 * @return the hito ID of this fecha hito
	 */
	@Override
	public long getHitoId() {
		return model.getHitoId();
	}

	/**
	 * Returns the primary key of this fecha hito.
	 *
	 * @return the primary key of this fecha hito
	 */
	@Override
	public
		com.plan.igualdad.liferay.portlet.manager.service.persistence.
			FechaHitoPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the version ID of this fecha hito.
	 *
	 * @return the version ID of this fecha hito
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
	 * Sets the comp ID of this fecha hito.
	 *
	 * @param compId the comp ID of this fecha hito
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the fecha of this fecha hito.
	 *
	 * @param fecha the fecha of this fecha hito
	 */
	@Override
	public void setFecha(Date fecha) {
		model.setFecha(fecha);
	}

	/**
	 * Sets the hito ID of this fecha hito.
	 *
	 * @param hitoId the hito ID of this fecha hito
	 */
	@Override
	public void setHitoId(long hitoId) {
		model.setHitoId(hitoId);
	}

	/**
	 * Sets the primary key of this fecha hito.
	 *
	 * @param primaryKey the primary key of this fecha hito
	 */
	@Override
	public void setPrimaryKey(
		com.plan.igualdad.liferay.portlet.manager.service.persistence.
			FechaHitoPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the version ID of this fecha hito.
	 *
	 * @param versionId the version ID of this fecha hito
	 */
	@Override
	public void setVersionId(long versionId) {
		model.setVersionId(versionId);
	}

	@Override
	protected FechaHitoWrapper wrap(FechaHito fechaHito) {
		return new FechaHitoWrapper(fechaHito);
	}

}