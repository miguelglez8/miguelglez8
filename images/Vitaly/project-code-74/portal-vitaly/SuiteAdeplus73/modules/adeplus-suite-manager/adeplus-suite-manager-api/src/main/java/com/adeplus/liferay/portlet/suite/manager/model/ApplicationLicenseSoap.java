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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.adeplus.liferay.portlet.suite.manager.service.http.ApplicationLicenseServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ApplicationLicenseSoap implements Serializable {

	public static ApplicationLicenseSoap toSoapModel(ApplicationLicense model) {
		ApplicationLicenseSoap soapModel = new ApplicationLicenseSoap();

		soapModel.setLicenseId(model.getLicenseId());
		soapModel.setApplicationId(model.getApplicationId());
		soapModel.setName(model.getName());
		soapModel.setPermissionRoleKey(model.getPermissionRoleKey());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setDeleteDate(model.getDeleteDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static ApplicationLicenseSoap[] toSoapModels(
		ApplicationLicense[] models) {

		ApplicationLicenseSoap[] soapModels =
			new ApplicationLicenseSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ApplicationLicenseSoap[][] toSoapModels(
		ApplicationLicense[][] models) {

		ApplicationLicenseSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ApplicationLicenseSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ApplicationLicenseSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ApplicationLicenseSoap[] toSoapModels(
		List<ApplicationLicense> models) {

		List<ApplicationLicenseSoap> soapModels =
			new ArrayList<ApplicationLicenseSoap>(models.size());

		for (ApplicationLicense model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new ApplicationLicenseSoap[soapModels.size()]);
	}

	public ApplicationLicenseSoap() {
	}

	public long getPrimaryKey() {
		return _licenseId;
	}

	public void setPrimaryKey(long pk) {
		setLicenseId(pk);
	}

	public long getLicenseId() {
		return _licenseId;
	}

	public void setLicenseId(long licenseId) {
		_licenseId = licenseId;
	}

	public long getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(long applicationId) {
		_applicationId = applicationId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getPermissionRoleKey() {
		return _permissionRoleKey;
	}

	public void setPermissionRoleKey(String permissionRoleKey) {
		_permissionRoleKey = permissionRoleKey;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getDeleteDate() {
		return _deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		_deleteDate = deleteDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	private long _licenseId;
	private long _applicationId;
	private String _name;
	private String _permissionRoleKey;
	private Date _createDate;
	private Date _deleteDate;
	private Date _modifiedDate;

}