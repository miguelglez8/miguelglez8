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
 * This class is a wrapper for {@link UserRole}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserRole
 * @generated
 */
public class UserRoleWrapper
	extends BaseModelWrapper<UserRole>
	implements ModelWrapper<UserRole>, UserRole {

	public UserRoleWrapper(UserRole userRole) {
		super(userRole);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userId", getUserId());
		attributes.put("compId", getCompId());
		attributes.put("roleId", getRoleId());
		attributes.put("createDate", getCreateDate());
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

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
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
	 * Returns the comp ID of this user role.
	 *
	 * @return the comp ID of this user role
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the create date of this user role.
	 *
	 * @return the create date of this user role
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the modified date of this user role.
	 *
	 * @return the modified date of this user role
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this user role.
	 *
	 * @return the primary key of this user role
	 */
	@Override
	public
		com.adeplus.liferay.portlet.suite.manager.service.persistence.UserRolePK
			getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the role ID of this user role.
	 *
	 * @return the role ID of this user role
	 */
	@Override
	public long getRoleId() {
		return model.getRoleId();
	}

	/**
	 * Returns the user ID of this user role.
	 *
	 * @return the user ID of this user role
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this user role.
	 *
	 * @return the user uuid of this user role
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
	 * Sets the comp ID of this user role.
	 *
	 * @param compId the comp ID of this user role
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the create date of this user role.
	 *
	 * @param createDate the create date of this user role
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the modified date of this user role.
	 *
	 * @param modifiedDate the modified date of this user role
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this user role.
	 *
	 * @param primaryKey the primary key of this user role
	 */
	@Override
	public void setPrimaryKey(
		com.adeplus.liferay.portlet.suite.manager.service.persistence.UserRolePK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the role ID of this user role.
	 *
	 * @param roleId the role ID of this user role
	 */
	@Override
	public void setRoleId(long roleId) {
		model.setRoleId(roleId);
	}

	/**
	 * Sets the user ID of this user role.
	 *
	 * @param userId the user ID of this user role
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this user role.
	 *
	 * @param userUuid the user uuid of this user role
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected UserRoleWrapper wrap(UserRole userRole) {
		return new UserRoleWrapper(userRole);
	}

}