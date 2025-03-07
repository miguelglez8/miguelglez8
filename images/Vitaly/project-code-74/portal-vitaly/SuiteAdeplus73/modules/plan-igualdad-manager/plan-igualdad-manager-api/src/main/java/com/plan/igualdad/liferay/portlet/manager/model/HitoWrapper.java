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
 * This class is a wrapper for {@link Hito}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Hito
 * @generated
 */
public class HitoWrapper
	extends BaseModelWrapper<Hito> implements Hito, ModelWrapper<Hito> {

	public HitoWrapper(Hito hito) {
		super(hito);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("hitoId", getHitoId());
		attributes.put("nombre", getNombre());
		attributes.put("peso", getPeso());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long hitoId = (Long)attributes.get("hitoId");

		if (hitoId != null) {
			setHitoId(hitoId);
		}

		String nombre = (String)attributes.get("nombre");

		if (nombre != null) {
			setNombre(nombre);
		}

		String peso = (String)attributes.get("peso");

		if (peso != null) {
			setPeso(peso);
		}
	}

	/**
	 * Returns the hito ID of this hito.
	 *
	 * @return the hito ID of this hito
	 */
	@Override
	public long getHitoId() {
		return model.getHitoId();
	}

	/**
	 * Returns the nombre of this hito.
	 *
	 * @return the nombre of this hito
	 */
	@Override
	public String getNombre() {
		return model.getNombre();
	}

	/**
	 * Returns the peso of this hito.
	 *
	 * @return the peso of this hito
	 */
	@Override
	public String getPeso() {
		return model.getPeso();
	}

	/**
	 * Returns the primary key of this hito.
	 *
	 * @return the primary key of this hito
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
	 * Sets the hito ID of this hito.
	 *
	 * @param hitoId the hito ID of this hito
	 */
	@Override
	public void setHitoId(long hitoId) {
		model.setHitoId(hitoId);
	}

	/**
	 * Sets the nombre of this hito.
	 *
	 * @param nombre the nombre of this hito
	 */
	@Override
	public void setNombre(String nombre) {
		model.setNombre(nombre);
	}

	/**
	 * Sets the peso of this hito.
	 *
	 * @param peso the peso of this hito
	 */
	@Override
	public void setPeso(String peso) {
		model.setPeso(peso);
	}

	/**
	 * Sets the primary key of this hito.
	 *
	 * @param primaryKey the primary key of this hito
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected HitoWrapper wrap(Hito hito) {
		return new HitoWrapper(hito);
	}

}