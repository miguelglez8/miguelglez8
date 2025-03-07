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
 * This class is a wrapper for {@link CCAA}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CCAA
 * @generated
 */
public class CCAAWrapper
	extends BaseModelWrapper<CCAA> implements CCAA, ModelWrapper<CCAA> {

	public CCAAWrapper(CCAA ccaa) {
		super(ccaa);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ccaaId", getCcaaId());
		attributes.put("nombre", getNombre());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ccaaId = (Long)attributes.get("ccaaId");

		if (ccaaId != null) {
			setCcaaId(ccaaId);
		}

		String nombre = (String)attributes.get("nombre");

		if (nombre != null) {
			setNombre(nombre);
		}
	}

	/**
	 * Returns the ccaa ID of this ccaa.
	 *
	 * @return the ccaa ID of this ccaa
	 */
	@Override
	public long getCcaaId() {
		return model.getCcaaId();
	}

	/**
	 * Returns the nombre of this ccaa.
	 *
	 * @return the nombre of this ccaa
	 */
	@Override
	public String getNombre() {
		return model.getNombre();
	}

	/**
	 * Returns the primary key of this ccaa.
	 *
	 * @return the primary key of this ccaa
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
	 * Sets the ccaa ID of this ccaa.
	 *
	 * @param ccaaId the ccaa ID of this ccaa
	 */
	@Override
	public void setCcaaId(long ccaaId) {
		model.setCcaaId(ccaaId);
	}

	/**
	 * Sets the nombre of this ccaa.
	 *
	 * @param nombre the nombre of this ccaa
	 */
	@Override
	public void setNombre(String nombre) {
		model.setNombre(nombre);
	}

	/**
	 * Sets the primary key of this ccaa.
	 *
	 * @param primaryKey the primary key of this ccaa
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected CCAAWrapper wrap(CCAA ccaa) {
		return new CCAAWrapper(ccaa);
	}

}