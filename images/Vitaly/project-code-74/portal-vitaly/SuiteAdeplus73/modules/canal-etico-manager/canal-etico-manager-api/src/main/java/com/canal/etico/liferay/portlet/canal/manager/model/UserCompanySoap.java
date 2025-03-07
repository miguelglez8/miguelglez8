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

import com.canal.etico.liferay.portlet.canal.manager.service.persistence.UserCompanyPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.canal.etico.liferay.portlet.canal.manager.service.http.UserCompanyServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserCompanySoap implements Serializable {

	public static UserCompanySoap toSoapModel(UserCompany model) {
		UserCompanySoap soapModel = new UserCompanySoap();

		soapModel.setUserId(model.getUserId());
		soapModel.setCompId(model.getCompId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDeleteDate(model.getDeleteDate());

		return soapModel;
	}

	public static UserCompanySoap[] toSoapModels(UserCompany[] models) {
		UserCompanySoap[] soapModels = new UserCompanySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserCompanySoap[][] toSoapModels(UserCompany[][] models) {
		UserCompanySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserCompanySoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserCompanySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserCompanySoap[] toSoapModels(List<UserCompany> models) {
		List<UserCompanySoap> soapModels = new ArrayList<UserCompanySoap>(
			models.size());

		for (UserCompany model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserCompanySoap[soapModels.size()]);
	}

	public UserCompanySoap() {
	}

	public UserCompanyPK getPrimaryKey() {
		return new UserCompanyPK(_userId, _compId);
	}

	public void setPrimaryKey(UserCompanyPK pk) {
		setUserId(pk.userId);
		setCompId(pk.compId);
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

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public Date getDeleteDate() {
		return _deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		_deleteDate = deleteDate;
	}

	private long _userId;
	private long _compId;
	private Date _createDate;
	private Date _modifiedDate;
	private Date _deleteDate;

}