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

package com.adeplus.liferay.portlet.suite.manager.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserApplication}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserApplication
 * @generated
 */
public class UserApplicationWrapper
	extends BaseModelWrapper<UserApplication>
	implements ModelWrapper<UserApplication>, UserApplication {

	public UserApplicationWrapper(UserApplication userApplication) {
		super(userApplication);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userId", getUserId());
		attributes.put("compId", getCompId());
		attributes.put("applicationId", getApplicationId());
		attributes.put("admin", isAdmin());
		attributes.put("createDate", getCreateDate());
		attributes.put("deleteDate", getDeleteDate());
		attributes.put("modifiedDate", getModifiedDate());

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

		Long applicationId = (Long)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}

		Boolean admin = (Boolean)attributes.get("admin");

		if (admin != null) {
			setAdmin(admin);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date deleteDate = (Date)attributes.get("deleteDate");

		if (deleteDate != null) {
			setDeleteDate(deleteDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	/**
	 * Returns the admin of this user application.
	 *
	 * @return the admin of this user application
	 */
	@Override
	public boolean getAdmin() {
		return model.getAdmin();
	}

	/**
	 * Returns the application ID of this user application.
	 *
	 * @return the application ID of this user application
	 */
	@Override
	public long getApplicationId() {
		return model.getApplicationId();
	}

	/**
	 * Returns the comp ID of this user application.
	 *
	 * @return the comp ID of this user application
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the create date of this user application.
	 *
	 * @return the create date of this user application
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the delete date of this user application.
	 *
	 * @return the delete date of this user application
	 */
	@Override
	public Date getDeleteDate() {
		return model.getDeleteDate();
	}

	/**
	 * Returns the modified date of this user application.
	 *
	 * @return the modified date of this user application
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this user application.
	 *
	 * @return the primary key of this user application
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.service.persistence.
		UserApplicationPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this user application.
	 *
	 * @return the user ID of this user application
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this user application.
	 *
	 * @return the user uuid of this user application
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this user application is admin.
	 *
	 * @return <code>true</code> if this user application is admin; <code>false</code> otherwise
	 */
	@Override
	public boolean isAdmin() {
		return model.isAdmin();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this user application is admin.
	 *
	 * @param admin the admin of this user application
	 */
	@Override
	public void setAdmin(boolean admin) {
		model.setAdmin(admin);
	}

	/**
	 * Sets the application ID of this user application.
	 *
	 * @param applicationId the application ID of this user application
	 */
	@Override
	public void setApplicationId(long applicationId) {
		model.setApplicationId(applicationId);
	}

	/**
	 * Sets the comp ID of this user application.
	 *
	 * @param compId the comp ID of this user application
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the create date of this user application.
	 *
	 * @param createDate the create date of this user application
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the delete date of this user application.
	 *
	 * @param deleteDate the delete date of this user application
	 */
	@Override
	public void setDeleteDate(Date deleteDate) {
		model.setDeleteDate(deleteDate);
	}

	/**
	 * Sets the modified date of this user application.
	 *
	 * @param modifiedDate the modified date of this user application
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this user application.
	 *
	 * @param primaryKey the primary key of this user application
	 */
	@Override
	public void setPrimaryKey(
		com.adeplus.liferay.portlet.suite.manager.service.persistence.
			UserApplicationPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this user application.
	 *
	 * @param userId the user ID of this user application
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this user application.
	 *
	 * @param userUuid the user uuid of this user application
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected UserApplicationWrapper wrap(UserApplication userApplication) {
		return new UserApplicationWrapper(userApplication);
	}

}