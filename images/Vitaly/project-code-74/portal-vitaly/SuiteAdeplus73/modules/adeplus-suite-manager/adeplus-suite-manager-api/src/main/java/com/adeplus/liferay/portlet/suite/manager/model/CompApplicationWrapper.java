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
 * This class is a wrapper for {@link CompApplication}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CompApplication
 * @generated
 */
public class CompApplicationWrapper
	extends BaseModelWrapper<CompApplication>
	implements CompApplication, ModelWrapper<CompApplication> {

	public CompApplicationWrapper(CompApplication compApplication) {
		super(compApplication);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("compId", getCompId());
		attributes.put("applicationId", getApplicationId());
		attributes.put("licenseId", getLicenseId());
		attributes.put("createDate", getCreateDate());
		attributes.put("deleteDate", getDeleteDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		Long applicationId = (Long)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}

		Long licenseId = (Long)attributes.get("licenseId");

		if (licenseId != null) {
			setLicenseId(licenseId);
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
	 * Returns the application ID of this comp application.
	 *
	 * @return the application ID of this comp application
	 */
	@Override
	public long getApplicationId() {
		return model.getApplicationId();
	}

	/**
	 * Returns the comp ID of this comp application.
	 *
	 * @return the comp ID of this comp application
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the create date of this comp application.
	 *
	 * @return the create date of this comp application
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the delete date of this comp application.
	 *
	 * @return the delete date of this comp application
	 */
	@Override
	public Date getDeleteDate() {
		return model.getDeleteDate();
	}

	/**
	 * Returns the license ID of this comp application.
	 *
	 * @return the license ID of this comp application
	 */
	@Override
	public long getLicenseId() {
		return model.getLicenseId();
	}

	/**
	 * Returns the modified date of this comp application.
	 *
	 * @return the modified date of this comp application
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this comp application.
	 *
	 * @return the primary key of this comp application
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.service.persistence.
		CompApplicationPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the application ID of this comp application.
	 *
	 * @param applicationId the application ID of this comp application
	 */
	@Override
	public void setApplicationId(long applicationId) {
		model.setApplicationId(applicationId);
	}

	/**
	 * Sets the comp ID of this comp application.
	 *
	 * @param compId the comp ID of this comp application
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the create date of this comp application.
	 *
	 * @param createDate the create date of this comp application
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the delete date of this comp application.
	 *
	 * @param deleteDate the delete date of this comp application
	 */
	@Override
	public void setDeleteDate(Date deleteDate) {
		model.setDeleteDate(deleteDate);
	}

	/**
	 * Sets the license ID of this comp application.
	 *
	 * @param licenseId the license ID of this comp application
	 */
	@Override
	public void setLicenseId(long licenseId) {
		model.setLicenseId(licenseId);
	}

	/**
	 * Sets the modified date of this comp application.
	 *
	 * @param modifiedDate the modified date of this comp application
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this comp application.
	 *
	 * @param primaryKey the primary key of this comp application
	 */
	@Override
	public void setPrimaryKey(
		com.adeplus.liferay.portlet.suite.manager.service.persistence.
			CompApplicationPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected CompApplicationWrapper wrap(CompApplication compApplication) {
		return new CompApplicationWrapper(compApplication);
	}

}