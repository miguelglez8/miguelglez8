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
 * This class is a wrapper for {@link PuestoTrabajo}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PuestoTrabajo
 * @generated
 */
public class PuestoTrabajoWrapper
	extends BaseModelWrapper<PuestoTrabajo>
	implements ModelWrapper<PuestoTrabajo>, PuestoTrabajo {

	public PuestoTrabajoWrapper(PuestoTrabajo puestoTrabajo) {
		super(puestoTrabajo);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("puestoId", getPuestoId());
		attributes.put("versionId", getVersionId());
		attributes.put("compId", getCompId());
		attributes.put("nombre", getNombre());
		attributes.put("areaId", getAreaId());
		attributes.put("responsabilidad", getResponsabilidad());
		attributes.put("nHombres", getNHombres());
		attributes.put("nMujeres", getNMujeres());
		attributes.put("baja", getBaja());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long puestoId = (Long)attributes.get("puestoId");

		if (puestoId != null) {
			setPuestoId(puestoId);
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

		Long areaId = (Long)attributes.get("areaId");

		if (areaId != null) {
			setAreaId(areaId);
		}

		Long responsabilidad = (Long)attributes.get("responsabilidad");

		if (responsabilidad != null) {
			setResponsabilidad(responsabilidad);
		}

		Long nHombres = (Long)attributes.get("nHombres");

		if (nHombres != null) {
			setNHombres(nHombres);
		}

		Long nMujeres = (Long)attributes.get("nMujeres");

		if (nMujeres != null) {
			setNMujeres(nMujeres);
		}

		Date baja = (Date)attributes.get("baja");

		if (baja != null) {
			setBaja(baja);
		}
	}

	/**
	 * Returns the area ID of this puesto trabajo.
	 *
	 * @return the area ID of this puesto trabajo
	 */
	@Override
	public long getAreaId() {
		return model.getAreaId();
	}

	/**
	 * Returns the baja of this puesto trabajo.
	 *
	 * @return the baja of this puesto trabajo
	 */
	@Override
	public Date getBaja() {
		return model.getBaja();
	}

	/**
	 * Returns the comp ID of this puesto trabajo.
	 *
	 * @return the comp ID of this puesto trabajo
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the n hombres of this puesto trabajo.
	 *
	 * @return the n hombres of this puesto trabajo
	 */
	@Override
	public long getNHombres() {
		return model.getNHombres();
	}

	/**
	 * Returns the n mujeres of this puesto trabajo.
	 *
	 * @return the n mujeres of this puesto trabajo
	 */
	@Override
	public long getNMujeres() {
		return model.getNMujeres();
	}

	/**
	 * Returns the nombre of this puesto trabajo.
	 *
	 * @return the nombre of this puesto trabajo
	 */
	@Override
	public String getNombre() {
		return model.getNombre();
	}

	/**
	 * Returns the primary key of this puesto trabajo.
	 *
	 * @return the primary key of this puesto trabajo
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.service.persistence.
		PuestoTrabajoPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the puesto ID of this puesto trabajo.
	 *
	 * @return the puesto ID of this puesto trabajo
	 */
	@Override
	public long getPuestoId() {
		return model.getPuestoId();
	}

	/**
	 * Returns the responsabilidad of this puesto trabajo.
	 *
	 * @return the responsabilidad of this puesto trabajo
	 */
	@Override
	public long getResponsabilidad() {
		return model.getResponsabilidad();
	}

	/**
	 * Returns the version ID of this puesto trabajo.
	 *
	 * @return the version ID of this puesto trabajo
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
	 * Sets the area ID of this puesto trabajo.
	 *
	 * @param areaId the area ID of this puesto trabajo
	 */
	@Override
	public void setAreaId(long areaId) {
		model.setAreaId(areaId);
	}

	/**
	 * Sets the baja of this puesto trabajo.
	 *
	 * @param baja the baja of this puesto trabajo
	 */
	@Override
	public void setBaja(Date baja) {
		model.setBaja(baja);
	}

	/**
	 * Sets the comp ID of this puesto trabajo.
	 *
	 * @param compId the comp ID of this puesto trabajo
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the n hombres of this puesto trabajo.
	 *
	 * @param nHombres the n hombres of this puesto trabajo
	 */
	@Override
	public void setNHombres(long nHombres) {
		model.setNHombres(nHombres);
	}

	/**
	 * Sets the n mujeres of this puesto trabajo.
	 *
	 * @param nMujeres the n mujeres of this puesto trabajo
	 */
	@Override
	public void setNMujeres(long nMujeres) {
		model.setNMujeres(nMujeres);
	}

	/**
	 * Sets the nombre of this puesto trabajo.
	 *
	 * @param nombre the nombre of this puesto trabajo
	 */
	@Override
	public void setNombre(String nombre) {
		model.setNombre(nombre);
	}

	/**
	 * Sets the primary key of this puesto trabajo.
	 *
	 * @param primaryKey the primary key of this puesto trabajo
	 */
	@Override
	public void setPrimaryKey(
		com.plan.igualdad.liferay.portlet.manager.service.persistence.
			PuestoTrabajoPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the puesto ID of this puesto trabajo.
	 *
	 * @param puestoId the puesto ID of this puesto trabajo
	 */
	@Override
	public void setPuestoId(long puestoId) {
		model.setPuestoId(puestoId);
	}

	/**
	 * Sets the responsabilidad of this puesto trabajo.
	 *
	 * @param responsabilidad the responsabilidad of this puesto trabajo
	 */
	@Override
	public void setResponsabilidad(long responsabilidad) {
		model.setResponsabilidad(responsabilidad);
	}

	/**
	 * Sets the version ID of this puesto trabajo.
	 *
	 * @param versionId the version ID of this puesto trabajo
	 */
	@Override
	public void setVersionId(long versionId) {
		model.setVersionId(versionId);
	}

	@Override
	protected PuestoTrabajoWrapper wrap(PuestoTrabajo puestoTrabajo) {
		return new PuestoTrabajoWrapper(puestoTrabajo);
	}

}