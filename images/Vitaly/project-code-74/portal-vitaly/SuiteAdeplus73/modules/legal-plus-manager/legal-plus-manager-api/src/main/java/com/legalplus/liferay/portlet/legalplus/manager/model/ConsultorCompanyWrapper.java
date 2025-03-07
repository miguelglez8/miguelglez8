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
 * This class is a wrapper for {@link ConsultorCompany}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ConsultorCompany
 * @generated
 */
public class ConsultorCompanyWrapper
	extends BaseModelWrapper<ConsultorCompany>
	implements ConsultorCompany, ModelWrapper<ConsultorCompany> {

	public ConsultorCompanyWrapper(ConsultorCompany consultorCompany) {
		super(consultorCompany);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("appId", getAppId());
		attributes.put("userId", getUserId());
		attributes.put("compId", getCompId());
		attributes.put("name", getName());
		attributes.put("lastName", getLastName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long appId = (Long)attributes.get("appId");

		if (appId != null) {
			setAppId(appId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String lastName = (String)attributes.get("lastName");

		if (lastName != null) {
			setLastName(lastName);
		}
	}

	/**
	 * Returns the app ID of this consultor company.
	 *
	 * @return the app ID of this consultor company
	 */
	@Override
	public long getAppId() {
		return model.getAppId();
	}

	/**
	 * Returns the comp ID of this consultor company.
	 *
	 * @return the comp ID of this consultor company
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the last name of this consultor company.
	 *
	 * @return the last name of this consultor company
	 */
	@Override
	public String getLastName() {
		return model.getLastName();
	}

	/**
	 * Returns the name of this consultor company.
	 *
	 * @return the name of this consultor company
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this consultor company.
	 *
	 * @return the primary key of this consultor company
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
		ConsultorCompanyPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this consultor company.
	 *
	 * @return the user ID of this consultor company
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this consultor company.
	 *
	 * @return the user uuid of this consultor company
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the app ID of this consultor company.
	 *
	 * @param appId the app ID of this consultor company
	 */
	@Override
	public void setAppId(long appId) {
		model.setAppId(appId);
	}

	/**
	 * Sets the comp ID of this consultor company.
	 *
	 * @param compId the comp ID of this consultor company
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the last name of this consultor company.
	 *
	 * @param lastName the last name of this consultor company
	 */
	@Override
	public void setLastName(String lastName) {
		model.setLastName(lastName);
	}

	/**
	 * Sets the name of this consultor company.
	 *
	 * @param name the name of this consultor company
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this consultor company.
	 *
	 * @param primaryKey the primary key of this consultor company
	 */
	@Override
	public void setPrimaryKey(
		com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
			ConsultorCompanyPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this consultor company.
	 *
	 * @param userId the user ID of this consultor company
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this consultor company.
	 *
	 * @param userUuid the user uuid of this consultor company
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected ConsultorCompanyWrapper wrap(ConsultorCompany consultorCompany) {
		return new ConsultorCompanyWrapper(consultorCompany);
	}

}