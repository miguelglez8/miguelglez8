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
 * This class is a wrapper for {@link HiddenLegis}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HiddenLegis
 * @generated
 */
public class HiddenLegisWrapper
	extends BaseModelWrapper<HiddenLegis>
	implements HiddenLegis, ModelWrapper<HiddenLegis> {

	public HiddenLegisWrapper(HiddenLegis hiddenLegis) {
		super(hiddenLegis);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("legislacionId", getLegislacionId());
		attributes.put("empresaId", getEmpresaId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String legislacionId = (String)attributes.get("legislacionId");

		if (legislacionId != null) {
			setLegislacionId(legislacionId);
		}

		Long empresaId = (Long)attributes.get("empresaId");

		if (empresaId != null) {
			setEmpresaId(empresaId);
		}
	}

	/**
	 * Returns the empresa ID of this hidden legis.
	 *
	 * @return the empresa ID of this hidden legis
	 */
	@Override
	public long getEmpresaId() {
		return model.getEmpresaId();
	}

	/**
	 * Returns the legislacion ID of this hidden legis.
	 *
	 * @return the legislacion ID of this hidden legis
	 */
	@Override
	public String getLegislacionId() {
		return model.getLegislacionId();
	}

	/**
	 * Returns the primary key of this hidden legis.
	 *
	 * @return the primary key of this hidden legis
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
		HiddenLegisPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the empresa ID of this hidden legis.
	 *
	 * @param empresaId the empresa ID of this hidden legis
	 */
	@Override
	public void setEmpresaId(long empresaId) {
		model.setEmpresaId(empresaId);
	}

	/**
	 * Sets the legislacion ID of this hidden legis.
	 *
	 * @param legislacionId the legislacion ID of this hidden legis
	 */
	@Override
	public void setLegislacionId(String legislacionId) {
		model.setLegislacionId(legislacionId);
	}

	/**
	 * Sets the primary key of this hidden legis.
	 *
	 * @param primaryKey the primary key of this hidden legis
	 */
	@Override
	public void setPrimaryKey(
		com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
			HiddenLegisPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected HiddenLegisWrapper wrap(HiddenLegis hiddenLegis) {
		return new HiddenLegisWrapper(hiddenLegis);
	}

}