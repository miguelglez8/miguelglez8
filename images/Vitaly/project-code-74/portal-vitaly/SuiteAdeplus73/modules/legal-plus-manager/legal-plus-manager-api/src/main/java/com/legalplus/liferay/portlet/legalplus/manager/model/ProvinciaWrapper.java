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
 * This class is a wrapper for {@link Provincia}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Provincia
 * @generated
 */
public class ProvinciaWrapper
	extends BaseModelWrapper<Provincia>
	implements ModelWrapper<Provincia>, Provincia {

	public ProvinciaWrapper(Provincia provincia) {
		super(provincia);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ccaaId", getCcaaId());
		attributes.put("provinciaId", getProvinciaId());
		attributes.put("nombre", getNombre());
		attributes.put("capitalId", getCapitalId());

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

		String nombre = (String)attributes.get("nombre");

		if (nombre != null) {
			setNombre(nombre);
		}

		Long capitalId = (Long)attributes.get("capitalId");

		if (capitalId != null) {
			setCapitalId(capitalId);
		}
	}

	/**
	 * Returns the capital ID of this provincia.
	 *
	 * @return the capital ID of this provincia
	 */
	@Override
	public long getCapitalId() {
		return model.getCapitalId();
	}

	/**
	 * Returns the ccaa ID of this provincia.
	 *
	 * @return the ccaa ID of this provincia
	 */
	@Override
	public long getCcaaId() {
		return model.getCcaaId();
	}

	/**
	 * Returns the nombre of this provincia.
	 *
	 * @return the nombre of this provincia
	 */
	@Override
	public String getNombre() {
		return model.getNombre();
	}

	/**
	 * Returns the primary key of this provincia.
	 *
	 * @return the primary key of this provincia
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
		ProvinciaPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the provincia ID of this provincia.
	 *
	 * @return the provincia ID of this provincia
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
	 * Sets the capital ID of this provincia.
	 *
	 * @param capitalId the capital ID of this provincia
	 */
	@Override
	public void setCapitalId(long capitalId) {
		model.setCapitalId(capitalId);
	}

	/**
	 * Sets the ccaa ID of this provincia.
	 *
	 * @param ccaaId the ccaa ID of this provincia
	 */
	@Override
	public void setCcaaId(long ccaaId) {
		model.setCcaaId(ccaaId);
	}

	/**
	 * Sets the nombre of this provincia.
	 *
	 * @param nombre the nombre of this provincia
	 */
	@Override
	public void setNombre(String nombre) {
		model.setNombre(nombre);
	}

	/**
	 * Sets the primary key of this provincia.
	 *
	 * @param primaryKey the primary key of this provincia
	 */
	@Override
	public void setPrimaryKey(
		com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
			ProvinciaPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the provincia ID of this provincia.
	 *
	 * @param provinciaId the provincia ID of this provincia
	 */
	@Override
	public void setProvinciaId(long provinciaId) {
		model.setProvinciaId(provinciaId);
	}

	@Override
	protected ProvinciaWrapper wrap(Provincia provincia) {
		return new ProvinciaWrapper(provincia);
	}

}