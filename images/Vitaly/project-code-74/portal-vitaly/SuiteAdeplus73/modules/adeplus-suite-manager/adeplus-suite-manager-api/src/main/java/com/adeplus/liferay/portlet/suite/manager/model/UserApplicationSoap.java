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

import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserApplicationPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.adeplus.liferay.portlet.suite.manager.service.http.UserApplicationServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserApplicationSoap implements Serializable {

	public static UserApplicationSoap toSoapModel(UserApplication model) {
		UserApplicationSoap soapModel = new UserApplicationSoap();

		soapModel.setUserId(model.getUserId());
		soapModel.setCompId(model.getCompId());
		soapModel.setApplicationId(model.getApplicationId());
		soapModel.setAdmin(model.isAdmin());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setDeleteDate(model.getDeleteDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static UserApplicationSoap[] toSoapModels(UserApplication[] models) {
		UserApplicationSoap[] soapModels =
			new UserApplicationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserApplicationSoap[][] toSoapModels(
		UserApplication[][] models) {

		UserApplicationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new UserApplicationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserApplicationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserApplicationSoap[] toSoapModels(
		List<UserApplication> models) {

		List<UserApplicationSoap> soapModels =
			new ArrayList<UserApplicationSoap>(models.size());

		for (UserApplication model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserApplicationSoap[soapModels.size()]);
	}

	public UserApplicationSoap() {
	}

	public UserApplicationPK getPrimaryKey() {
		return new UserApplicationPK(_userId, _compId, _applicationId);
	}

	public void setPrimaryKey(UserApplicationPK pk) {
		setUserId(pk.userId);
		setCompId(pk.compId);
		setApplicationId(pk.applicationId);
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
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

	public boolean getAdmin() {
		return _admin;
	}

	public boolean isAdmin() {
		return _admin;
	}

	public void setAdmin(boolean admin) {
		_admin = admin;
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

	private long _userId;
	private long _compId;
	private long _applicationId;
	private boolean _admin;
	private Date _createDate;
	private Date _deleteDate;
	private Date _modifiedDate;

}