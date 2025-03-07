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

import com.adeplus.liferay.portlet.suite.manager.service.persistence.CompApplicationPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.adeplus.liferay.portlet.suite.manager.service.http.CompApplicationServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CompApplicationSoap implements Serializable {

	public static CompApplicationSoap toSoapModel(CompApplication model) {
		CompApplicationSoap soapModel = new CompApplicationSoap();

		soapModel.setCompId(model.getCompId());
		soapModel.setApplicationId(model.getApplicationId());
		soapModel.setLicenseId(model.getLicenseId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setDeleteDate(model.getDeleteDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static CompApplicationSoap[] toSoapModels(CompApplication[] models) {
		CompApplicationSoap[] soapModels =
			new CompApplicationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CompApplicationSoap[][] toSoapModels(
		CompApplication[][] models) {

		CompApplicationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CompApplicationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CompApplicationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CompApplicationSoap[] toSoapModels(
		List<CompApplication> models) {

		List<CompApplicationSoap> soapModels =
			new ArrayList<CompApplicationSoap>(models.size());

		for (CompApplication model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CompApplicationSoap[soapModels.size()]);
	}

	public CompApplicationSoap() {
	}

	public CompApplicationPK getPrimaryKey() {
		return new CompApplicationPK(_compId, _applicationId);
	}

	public void setPrimaryKey(CompApplicationPK pk) {
		setCompId(pk.compId);
		setApplicationId(pk.applicationId);
	}

	public long getCompId() {
		return _compId;
	}

	public void setCompId(long compId) {
		_compId = compId;
	}

	public long getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(long applicationId) {
		_applicationId = applicationId;
	}

	public long getLicenseId() {
		return _licenseId;
	}

	public void setLicenseId(long licenseId) {
		_licenseId = licenseId;
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

	private long _compId;
	private long _applicationId;
	private long _licenseId;
	private Date _createDate;
	private Date _deleteDate;
	private Date _modifiedDate;

}