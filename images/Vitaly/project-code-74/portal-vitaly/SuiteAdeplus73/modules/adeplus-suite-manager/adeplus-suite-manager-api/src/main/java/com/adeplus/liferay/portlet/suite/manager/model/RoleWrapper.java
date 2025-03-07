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
 * This class is a wrapper for {@link Role}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Role
 * @generated
 */
public class RoleWrapper
	extends BaseModelWrapper<Role> implements ModelWrapper<Role>, Role {

	public RoleWrapper(Role role) {
		super(role);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("roleId", getRoleId());
		attributes.put("applicationId", getApplicationId());
		attributes.put("name", getName());
		attributes.put("alias", getAlias());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}

		Long applicationId = (Long)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String alias = (String)attributes.get("alias");

		if (alias != null) {
			setAlias(alias);
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
	 * Returns the alias of this role.
	 *
	 * @return the alias of this role
	 */
	@Override
	public String getAlias() {
		return model.getAlias();
	}

	/**
	 * Returns the application ID of this role.
	 *
	 * @return the application ID of this role
	 */
	@Override
	public long getApplicationId() {
		return model.getApplicationId();
	}

	/**
	 * Returns the create date of this role.
	 *
	 * @return the create date of this role
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the modified date of this role.
	 *
	 * @return the modified date of this role
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this role.
	 *
	 * @return the name of this role
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this role.
	 *
	 * @return the primary key of this role
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the role ID of this role.
	 *
	 * @return the role ID of this role
	 */
	@Override
	public long getRoleId() {
		return model.getRoleId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the alias of this role.
	 *
	 * @param alias the alias of this role
	 */
	@Override
	public void setAlias(String alias) {
		model.setAlias(alias);
	}

	/**
	 * Sets the application ID of this role.
	 *
	 * @param applicationId the application ID of this role
	 */
	@Override
	public void setApplicationId(long applicationId) {
		model.setApplicationId(applicationId);
	}

	/**
	 * Sets the create date of this role.
	 *
	 * @param createDate the create date of this role
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the modified date of this role.
	 *
	 * @param modifiedDate the modified date of this role
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this role.
	 *
	 * @param name the name of this role
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this role.
	 *
	 * @param primaryKey the primary key of this role
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the role ID of this role.
	 *
	 * @param roleId the role ID of this role
	 */
	@Override
	public void setRoleId(long roleId) {
		model.setRoleId(roleId);
	}

	@Override
	protected RoleWrapper wrap(Role role) {
		return new RoleWrapper(role);
	}

}