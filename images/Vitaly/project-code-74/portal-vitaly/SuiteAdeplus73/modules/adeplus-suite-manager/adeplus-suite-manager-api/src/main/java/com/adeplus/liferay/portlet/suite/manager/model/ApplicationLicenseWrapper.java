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
 * This class is a wrapper for {@link ApplicationLicense}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationLicense
 * @generated
 */
public class ApplicationLicenseWrapper
	extends BaseModelWrapper<ApplicationLicense>
	implements ApplicationLicense, ModelWrapper<ApplicationLicense> {

	public ApplicationLicenseWrapper(ApplicationLicense applicationLicense) {
		super(applicationLicense);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("licenseId", getLicenseId());
		attributes.put("applicationId", getApplicationId());
		attributes.put("name", getName());
		attributes.put("permissionRoleKey", getPermissionRoleKey());
		attributes.put("createDate", getCreateDate());
		attributes.put("deleteDate", getDeleteDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long licenseId = (Long)attributes.get("licenseId");

		if (licenseId != null) {
			setLicenseId(licenseId);
		}

		Long applicationId = (Long)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String permissionRoleKey = (String)attributes.get("permissionRoleKey");

		if (permissionRoleKey != null) {
			setPermissionRoleKey(permissionRoleKey);
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
	 * Returns the application ID of this application license.
	 *
	 * @return the application ID of this application license
	 */
	@Override
	public long getApplicationId() {
		return model.getApplicationId();
	}

	/**
	 * Returns the create date of this application license.
	 *
	 * @return the create date of this application license
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the delete date of this application license.
	 *
	 * @return the delete date of this application license
	 */
	@Override
	public Date getDeleteDate() {
		return model.getDeleteDate();
	}

	/**
	 * Returns the license ID of this application license.
	 *
	 * @return the license ID of this application license
	 */
	@Override
	public long getLicenseId() {
		return model.getLicenseId();
	}

	/**
	 * Returns the modified date of this application license.
	 *
	 * @return the modified date of this application license
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this application license.
	 *
	 * @return the name of this application license
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the permission role key of this application license.
	 *
	 * @return the permission role key of this application license
	 */
	@Override
	public String getPermissionRoleKey() {
		return model.getPermissionRoleKey();
	}

	/**
	 * Returns the primary key of this application license.
	 *
	 * @return the primary key of this application license
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
	 * Sets the application ID of this application license.
	 *
	 * @param applicationId the application ID of this application license
	 */
	@Override
	public void setApplicationId(long applicationId) {
		model.setApplicationId(applicationId);
	}

	/**
	 * Sets the create date of this application license.
	 *
	 * @param createDate the create date of this application license
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the delete date of this application license.
	 *
	 * @param deleteDate the delete date of this application license
	 */
	@Override
	public void setDeleteDate(Date deleteDate) {
		model.setDeleteDate(deleteDate);
	}

	/**
	 * Sets the license ID of this application license.
	 *
	 * @param licenseId the license ID of this application license
	 */
	@Override
	public void setLicenseId(long licenseId) {
		model.setLicenseId(licenseId);
	}

	/**
	 * Sets the modified date of this application license.
	 *
	 * @param modifiedDate the modified date of this application license
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this application license.
	 *
	 * @param name the name of this application license
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the permission role key of this application license.
	 *
	 * @param permissionRoleKey the permission role key of this application license
	 */
	@Override
	public void setPermissionRoleKey(String permissionRoleKey) {
		model.setPermissionRoleKey(permissionRoleKey);
	}

	/**
	 * Sets the primary key of this application license.
	 *
	 * @param primaryKey the primary key of this application license
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected ApplicationLicenseWrapper wrap(
		ApplicationLicense applicationLicense) {

		return new ApplicationLicenseWrapper(applicationLicense);
	}

}