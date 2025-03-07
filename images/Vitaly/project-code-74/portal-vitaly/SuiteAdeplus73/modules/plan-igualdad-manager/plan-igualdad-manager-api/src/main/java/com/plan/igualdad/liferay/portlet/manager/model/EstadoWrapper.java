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
 * This class is a wrapper for {@link Estado}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Estado
 * @generated
 */
public class EstadoWrapper
	extends BaseModelWrapper<Estado> implements Estado, ModelWrapper<Estado> {

	public EstadoWrapper(Estado estado) {
		super(estado);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("estadoId", getEstadoId());
		attributes.put("nombre", getNombre());
		attributes.put("activa", getActiva());
		attributes.put(
			"gestionParametrizaciones", getGestionParametrizaciones());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long estadoId = (Long)attributes.get("estadoId");

		if (estadoId != null) {
			setEstadoId(estadoId);
		}

		String nombre = (String)attributes.get("nombre");

		if (nombre != null) {
			setNombre(nombre);
		}

		Boolean activa = (Boolean)attributes.get("activa");

		if (activa != null) {
			setActiva(activa);
		}

		Boolean gestionParametrizaciones = (Boolean)attributes.get(
			"gestionParametrizaciones");

		if (gestionParametrizaciones != null) {
			setGestionParametrizaciones(gestionParametrizaciones);
		}
	}

	/**
	 * Returns the activa of this estado.
	 *
	 * @return the activa of this estado
	 */
	@Override
	public Boolean getActiva() {
		return model.getActiva();
	}

	/**
	 * Returns the estado ID of this estado.
	 *
	 * @return the estado ID of this estado
	 */
	@Override
	public long getEstadoId() {
		return model.getEstadoId();
	}

	/**
	 * Returns the gestion parametrizaciones of this estado.
	 *
	 * @return the gestion parametrizaciones of this estado
	 */
	@Override
	public Boolean getGestionParametrizaciones() {
		return model.getGestionParametrizaciones();
	}

	/**
	 * Returns the nombre of this estado.
	 *
	 * @return the nombre of this estado
	 */
	@Override
	public String getNombre() {
		return model.getNombre();
	}

	/**
	 * Returns the primary key of this estado.
	 *
	 * @return the primary key of this estado
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
	 * Sets the activa of this estado.
	 *
	 * @param activa the activa of this estado
	 */
	@Override
	public void setActiva(Boolean activa) {
		model.setActiva(activa);
	}

	/**
	 * Sets the estado ID of this estado.
	 *
	 * @param estadoId the estado ID of this estado
	 */
	@Override
	public void setEstadoId(long estadoId) {
		model.setEstadoId(estadoId);
	}

	/**
	 * Sets the gestion parametrizaciones of this estado.
	 *
	 * @param gestionParametrizaciones the gestion parametrizaciones of this estado
	 */
	@Override
	public void setGestionParametrizaciones(Boolean gestionParametrizaciones) {
		model.setGestionParametrizaciones(gestionParametrizaciones);
	}

	/**
	 * Sets the nombre of this estado.
	 *
	 * @param nombre the nombre of this estado
	 */
	@Override
	public void setNombre(String nombre) {
		model.setNombre(nombre);
	}

	/**
	 * Sets the primary key of this estado.
	 *
	 * @param primaryKey the primary key of this estado
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected EstadoWrapper wrap(Estado estado) {
		return new EstadoWrapper(estado);
	}

}