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
 * This class is a wrapper for {@link CNAES}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CNAES
 * @generated
 */
public class CNAESWrapper
	extends BaseModelWrapper<CNAES> implements CNAES, ModelWrapper<CNAES> {

	public CNAESWrapper(CNAES cnaes) {
		super(cnaes);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("cnae", getCnae());
		attributes.put("nombre", getNombre());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String cnae = (String)attributes.get("cnae");

		if (cnae != null) {
			setCnae(cnae);
		}

		String nombre = (String)attributes.get("nombre");

		if (nombre != null) {
			setNombre(nombre);
		}
	}

	/**
	 * Returns the cnae of this cnaes.
	 *
	 * @return the cnae of this cnaes
	 */
	@Override
	public String getCnae() {
		return model.getCnae();
	}

	/**
	 * Returns the nombre of this cnaes.
	 *
	 * @return the nombre of this cnaes
	 */
	@Override
	public String getNombre() {
		return model.getNombre();
	}

	/**
	 * Returns the primary key of this cnaes.
	 *
	 * @return the primary key of this cnaes
	 */
	@Override
	public String getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the cnae of this cnaes.
	 *
	 * @param cnae the cnae of this cnaes
	 */
	@Override
	public void setCnae(String cnae) {
		model.setCnae(cnae);
	}

	/**
	 * Sets the nombre of this cnaes.
	 *
	 * @param nombre the nombre of this cnaes
	 */
	@Override
	public void setNombre(String nombre) {
		model.setNombre(nombre);
	}

	/**
	 * Sets the primary key of this cnaes.
	 *
	 * @param primaryKey the primary key of this cnaes
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected CNAESWrapper wrap(CNAES cnaes) {
		return new CNAESWrapper(cnaes);
	}

}