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

package com.canal.etico.liferay.portlet.canal.manager.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserCompany}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserCompany
 * @generated
 */
public class UserCompanyWrapper
	extends BaseModelWrapper<UserCompany>
	implements ModelWrapper<UserCompany>, UserCompany {

	public UserCompanyWrapper(UserCompany userCompany) {
		super(userCompany);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userId", getUserId());
		attributes.put("compId", getCompId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("deleteDate", getDeleteDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Date deleteDate = (Date)attributes.get("deleteDate");

		if (deleteDate != null) {
			setDeleteDate(deleteDate);
		}
	}

	/**
	 * Returns the comp ID of this user company.
	 *
	 * @return the comp ID of this user company
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the create date of this user company.
	 *
	 * @return the create date of this user company
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the delete date of this user company.
	 *
	 * @return the delete date of this user company
	 */
	@Override
	public Date getDeleteDate() {
		return model.getDeleteDate();
	}

	/**
	 * Returns the modified date of this user company.
	 *
	 * @return the modified date of this user company
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this user company.
	 *
	 * @return the primary key of this user company
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.service.persistence.
		UserCompanyPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this user company.
	 *
	 * @return the user ID of this user company
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this user company.
	 *
	 * @return the user uuid of this user company
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
	 * Sets the comp ID of this user company.
	 *
	 * @param compId the comp ID of this user company
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the create date of this user company.
	 *
	 * @param createDate the create date of this user company
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the delete date of this user company.
	 *
	 * @param deleteDate the delete date of this user company
	 */
	@Override
	public void setDeleteDate(Date deleteDate) {
		model.setDeleteDate(deleteDate);
	}

	/**
	 * Sets the modified date of this user company.
	 *
	 * @param modifiedDate the modified date of this user company
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this user company.
	 *
	 * @param primaryKey the primary key of this user company
	 */
	@Override
	public void setPrimaryKey(
		com.canal.etico.liferay.portlet.canal.manager.service.persistence.
			UserCompanyPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this user company.
	 *
	 * @param userId the user ID of this user company
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this user company.
	 *
	 * @param userUuid the user uuid of this user company
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected UserCompanyWrapper wrap(UserCompany userCompany) {
		return new UserCompanyWrapper(userCompany);
	}

}