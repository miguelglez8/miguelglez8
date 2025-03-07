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
 * This class is a wrapper for {@link Area}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Area
 * @generated
 */
public class AreaWrapper
	extends BaseModelWrapper<Area> implements Area, ModelWrapper<Area> {

	public AreaWrapper(Area area) {
		super(area);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("areaId", getAreaId());
		attributes.put("versionId", getVersionId());
		attributes.put("compId", getCompId());
		attributes.put("nombre", getNombre());
		attributes.put("baja", getBaja());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long areaId = (Long)attributes.get("areaId");

		if (areaId != null) {
			setAreaId(areaId);
		}

		Long versionId = (Long)attributes.get("versionId");

		if (versionId != null) {
			setVersionId(versionId);
		}

		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		String nombre = (String)attributes.get("nombre");

		if (nombre != null) {
			setNombre(nombre);
		}

		Date baja = (Date)attributes.get("baja");

		if (baja != null) {
			setBaja(baja);
		}
	}

	/**
	 * Returns the area ID of this area.
	 *
	 * @return the area ID of this area
	 */
	@Override
	public long getAreaId() {
		return model.getAreaId();
	}

	/**
	 * Returns the baja of this area.
	 *
	 * @return the baja of this area
	 */
	@Override
	public Date getBaja() {
		return model.getBaja();
	}

	/**
	 * Returns the comp ID of this area.
	 *
	 * @return the comp ID of this area
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the nombre of this area.
	 *
	 * @return the nombre of this area
	 */
	@Override
	public String getNombre() {
		return model.getNombre();
	}

	/**
	 * Returns the primary key of this area.
	 *
	 * @return the primary key of this area
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.service.persistence.AreaPK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the version ID of this area.
	 *
	 * @return the version ID of this area
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
	 * Sets the area ID of this area.
	 *
	 * @param areaId the area ID of this area
	 */
	@Override
	public void setAreaId(long areaId) {
		model.setAreaId(areaId);
	}

	/**
	 * Sets the baja of this area.
	 *
	 * @param baja the baja of this area
	 */
	@Override
	public void setBaja(Date baja) {
		model.setBaja(baja);
	}

	/**
	 * Sets the comp ID of this area.
	 *
	 * @param compId the comp ID of this area
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the nombre of this area.
	 *
	 * @param nombre the nombre of this area
	 */
	@Override
	public void setNombre(String nombre) {
		model.setNombre(nombre);
	}

	/**
	 * Sets the primary key of this area.
	 *
	 * @param primaryKey the primary key of this area
	 */
	@Override
	public void setPrimaryKey(
		com.plan.igualdad.liferay.portlet.manager.service.persistence.AreaPK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the version ID of this area.
	 *
	 * @param versionId the version ID of this area
	 */
	@Override
	public void setVersionId(long versionId) {
		model.setVersionId(versionId);
	}

	@Override
	protected AreaWrapper wrap(Area area) {
		return new AreaWrapper(area);
	}

}