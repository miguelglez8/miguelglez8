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
 * This class is a wrapper for {@link Version}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Version
 * @generated
 */
public class VersionWrapper
	extends BaseModelWrapper<Version>
	implements ModelWrapper<Version>, Version {

	public VersionWrapper(Version version) {
		super(version);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("versionId", getVersionId());
		attributes.put("compId", getCompId());
		attributes.put("nombre", getNombre());
		attributes.put("inicioPeriodoDatos", getInicioPeriodoDatos());
		attributes.put("finPeriodoDatos", getFinPeriodoDatos());
		attributes.put("inicioPeriodoPlan", getInicioPeriodoPlan());
		attributes.put("finPeriodoPlan", getFinPeriodoPlan());
		attributes.put("baja", getBaja());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
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

		Date inicioPeriodoDatos = (Date)attributes.get("inicioPeriodoDatos");

		if (inicioPeriodoDatos != null) {
			setInicioPeriodoDatos(inicioPeriodoDatos);
		}

		Date finPeriodoDatos = (Date)attributes.get("finPeriodoDatos");

		if (finPeriodoDatos != null) {
			setFinPeriodoDatos(finPeriodoDatos);
		}

		Date inicioPeriodoPlan = (Date)attributes.get("inicioPeriodoPlan");

		if (inicioPeriodoPlan != null) {
			setInicioPeriodoPlan(inicioPeriodoPlan);
		}

		Date finPeriodoPlan = (Date)attributes.get("finPeriodoPlan");

		if (finPeriodoPlan != null) {
			setFinPeriodoPlan(finPeriodoPlan);
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
	 * Returns the baja of this version.
	 *
	 * @return the baja of this version
	 */
	@Override
	public Date getBaja() {
		return model.getBaja();
	}

	/**
	 * Returns the comp ID of this version.
	 *
	 * @return the comp ID of this version
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the create date of this version.
	 *
	 * @return the create date of this version
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the fin periodo datos of this version.
	 *
	 * @return the fin periodo datos of this version
	 */
	@Override
	public Date getFinPeriodoDatos() {
		return model.getFinPeriodoDatos();
	}

	/**
	 * Returns the fin periodo plan of this version.
	 *
	 * @return the fin periodo plan of this version
	 */
	@Override
	public Date getFinPeriodoPlan() {
		return model.getFinPeriodoPlan();
	}

	/**
	 * Returns the inicio periodo datos of this version.
	 *
	 * @return the inicio periodo datos of this version
	 */
	@Override
	public Date getInicioPeriodoDatos() {
		return model.getInicioPeriodoDatos();
	}

	/**
	 * Returns the inicio periodo plan of this version.
	 *
	 * @return the inicio periodo plan of this version
	 */
	@Override
	public Date getInicioPeriodoPlan() {
		return model.getInicioPeriodoPlan();
	}

	/**
	 * Returns the modified date of this version.
	 *
	 * @return the modified date of this version
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the nombre of this version.
	 *
	 * @return the nombre of this version
	 */
	@Override
	public String getNombre() {
		return model.getNombre();
	}

	/**
	 * Returns the primary key of this version.
	 *
	 * @return the primary key of this version
	 */
	@Override
	public
		com.plan.igualdad.liferay.portlet.manager.service.persistence.VersionPK
			getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the version ID of this version.
	 *
	 * @return the version ID of this version
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
	 * Sets the baja of this version.
	 *
	 * @param baja the baja of this version
	 */
	@Override
	public void setBaja(Date baja) {
		model.setBaja(baja);
	}

	/**
	 * Sets the comp ID of this version.
	 *
	 * @param compId the comp ID of this version
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the create date of this version.
	 *
	 * @param createDate the create date of this version
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the fin periodo datos of this version.
	 *
	 * @param finPeriodoDatos the fin periodo datos of this version
	 */
	@Override
	public void setFinPeriodoDatos(Date finPeriodoDatos) {
		model.setFinPeriodoDatos(finPeriodoDatos);
	}

	/**
	 * Sets the fin periodo plan of this version.
	 *
	 * @param finPeriodoPlan the fin periodo plan of this version
	 */
	@Override
	public void setFinPeriodoPlan(Date finPeriodoPlan) {
		model.setFinPeriodoPlan(finPeriodoPlan);
	}

	/**
	 * Sets the inicio periodo datos of this version.
	 *
	 * @param inicioPeriodoDatos the inicio periodo datos of this version
	 */
	@Override
	public void setInicioPeriodoDatos(Date inicioPeriodoDatos) {
		model.setInicioPeriodoDatos(inicioPeriodoDatos);
	}

	/**
	 * Sets the inicio periodo plan of this version.
	 *
	 * @param inicioPeriodoPlan the inicio periodo plan of this version
	 */
	@Override
	public void setInicioPeriodoPlan(Date inicioPeriodoPlan) {
		model.setInicioPeriodoPlan(inicioPeriodoPlan);
	}

	/**
	 * Sets the modified date of this version.
	 *
	 * @param modifiedDate the modified date of this version
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the nombre of this version.
	 *
	 * @param nombre the nombre of this version
	 */
	@Override
	public void setNombre(String nombre) {
		model.setNombre(nombre);
	}

	/**
	 * Sets the primary key of this version.
	 *
	 * @param primaryKey the primary key of this version
	 */
	@Override
	public void setPrimaryKey(
		com.plan.igualdad.liferay.portlet.manager.service.persistence.VersionPK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the version ID of this version.
	 *
	 * @param versionId the version ID of this version
	 */
	@Override
	public void setVersionId(long versionId) {
		model.setVersionId(versionId);
	}

	@Override
	protected VersionWrapper wrap(Version version) {
		return new VersionWrapper(version);
	}

}