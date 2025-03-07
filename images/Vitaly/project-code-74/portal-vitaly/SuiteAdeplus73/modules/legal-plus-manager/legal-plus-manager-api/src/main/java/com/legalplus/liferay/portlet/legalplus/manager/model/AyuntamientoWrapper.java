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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Ayuntamiento}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Ayuntamiento
 * @generated
 */
public class AyuntamientoWrapper
	extends BaseModelWrapper<Ayuntamiento>
	implements Ayuntamiento, ModelWrapper<Ayuntamiento> {

	public AyuntamientoWrapper(Ayuntamiento ayuntamiento) {
		super(ayuntamiento);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ccaaId", getCcaaId());
		attributes.put("provinciaId", getProvinciaId());
		attributes.put("ayuntamientoId", getAyuntamientoId());
		attributes.put("nombre", getNombre());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ccaaId = (Long)attributes.get("ccaaId");

		if (ccaaId != null) {
			setCcaaId(ccaaId);
		}

		Long provinciaId = (Long)attributes.get("provinciaId");

		if (provinciaId != null) {
			setProvinciaId(provinciaId);
		}

		Long ayuntamientoId = (Long)attributes.get("ayuntamientoId");

		if (ayuntamientoId != null) {
			setAyuntamientoId(ayuntamientoId);
		}

		String nombre = (String)attributes.get("nombre");

		if (nombre != null) {
			setNombre(nombre);
		}
	}

	/**
	 * Returns the ayuntamiento ID of this ayuntamiento.
	 *
	 * @return the ayuntamiento ID of this ayuntamiento
	 */
	@Override
	public long getAyuntamientoId() {
		return model.getAyuntamientoId();
	}

	/**
	 * Returns the ccaa ID of this ayuntamiento.
	 *
	 * @return the ccaa ID of this ayuntamiento
	 */
	@Override
	public long getCcaaId() {
		return model.getCcaaId();
	}

	/**
	 * Returns the nombre of this ayuntamiento.
	 *
	 * @return the nombre of this ayuntamiento
	 */
	@Override
	public String getNombre() {
		return model.getNombre();
	}

	/**
	 * Returns the primary key of this ayuntamiento.
	 *
	 * @return the primary key of this ayuntamiento
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
		AyuntamientoPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the provincia ID of this ayuntamiento.
	 *
	 * @return the provincia ID of this ayuntamiento
	 */
	@Override
	public long getProvinciaId() {
		return model.getProvinciaId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the ayuntamiento ID of this ayuntamiento.
	 *
	 * @param ayuntamientoId the ayuntamiento ID of this ayuntamiento
	 */
	@Override
	public void setAyuntamientoId(long ayuntamientoId) {
		model.setAyuntamientoId(ayuntamientoId);
	}

	/**
	 * Sets the ccaa ID of this ayuntamiento.
	 *
	 * @param ccaaId the ccaa ID of this ayuntamiento
	 */
	@Override
	public void setCcaaId(long ccaaId) {
		model.setCcaaId(ccaaId);
	}

	/**
	 * Sets the nombre of this ayuntamiento.
	 *
	 * @param nombre the nombre of this ayuntamiento
	 */
	@Override
	public void setNombre(String nombre) {
		model.setNombre(nombre);
	}

	/**
	 * Sets the primary key of this ayuntamiento.
	 *
	 * @param primaryKey the primary key of this ayuntamiento
	 */
	@Override
	public void setPrimaryKey(
		com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
			AyuntamientoPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the provincia ID of this ayuntamiento.
	 *
	 * @param provinciaId the provincia ID of this ayuntamiento
	 */
	@Override
	public void setProvinciaId(long provinciaId) {
		model.setProvinciaId(provinciaId);
	}

	@Override
	protected AyuntamientoWrapper wrap(Ayuntamiento ayuntamiento) {
		return new AyuntamientoWrapper(ayuntamiento);
	}

}