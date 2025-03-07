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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link LegislacionCNAES}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LegislacionCNAES
 * @generated
 */
public class LegislacionCNAESWrapper
	extends BaseModelWrapper<LegislacionCNAES>
	implements LegislacionCNAES, ModelWrapper<LegislacionCNAES> {

	public LegislacionCNAESWrapper(LegislacionCNAES legislacionCNAES) {
		super(legislacionCNAES);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("legislacionId", getLegislacionId());
		attributes.put("cnae", getCnae());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String legislacionId = (String)attributes.get("legislacionId");

		if (legislacionId != null) {
			setLegislacionId(legislacionId);
		}

		String cnae = (String)attributes.get("cnae");

		if (cnae != null) {
			setCnae(cnae);
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
	 * Returns the cnae of this legislacion cnaes.
	 *
	 * @return the cnae of this legislacion cnaes
	 */
	@Override
	public String getCnae() {
		return model.getCnae();
	}

	/**
	 * Returns the create date of this legislacion cnaes.
	 *
	 * @return the create date of this legislacion cnaes
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the legislacion ID of this legislacion cnaes.
	 *
	 * @return the legislacion ID of this legislacion cnaes
	 */
	@Override
	public String getLegislacionId() {
		return model.getLegislacionId();
	}

	/**
	 * Returns the modified date of this legislacion cnaes.
	 *
	 * @return the modified date of this legislacion cnaes
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this legislacion cnaes.
	 *
	 * @return the primary key of this legislacion cnaes
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
		LegislacionCNAESPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the cnae of this legislacion cnaes.
	 *
	 * @param cnae the cnae of this legislacion cnaes
	 */
	@Override
	public void setCnae(String cnae) {
		model.setCnae(cnae);
	}

	/**
	 * Sets the create date of this legislacion cnaes.
	 *
	 * @param createDate the create date of this legislacion cnaes
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the legislacion ID of this legislacion cnaes.
	 *
	 * @param legislacionId the legislacion ID of this legislacion cnaes
	 */
	@Override
	public void setLegislacionId(String legislacionId) {
		model.setLegislacionId(legislacionId);
	}

	/**
	 * Sets the modified date of this legislacion cnaes.
	 *
	 * @param modifiedDate the modified date of this legislacion cnaes
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this legislacion cnaes.
	 *
	 * @param primaryKey the primary key of this legislacion cnaes
	 */
	@Override
	public void setPrimaryKey(
		com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
			LegislacionCNAESPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected LegislacionCNAESWrapper wrap(LegislacionCNAES legislacionCNAES) {
		return new LegislacionCNAESWrapper(legislacionCNAES);
	}

}