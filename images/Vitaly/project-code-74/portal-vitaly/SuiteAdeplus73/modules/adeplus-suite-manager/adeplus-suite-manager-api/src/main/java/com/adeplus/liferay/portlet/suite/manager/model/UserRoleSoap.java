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

import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserRolePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.adeplus.liferay.portlet.suite.manager.service.http.UserRoleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserRoleSoap implements Serializable {

	public static UserRoleSoap toSoapModel(UserRole model) {
		UserRoleSoap soapModel = new UserRoleSoap();

		soapModel.setUserId(model.getUserId());
		soapModel.setCompId(model.getCompId());
		soapModel.setRoleId(model.getRoleId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static UserRoleSoap[] toSoapModels(UserRole[] models) {
		UserRoleSoap[] soapModels = new UserRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserRoleSoap[][] toSoapModels(UserRole[][] models) {
		UserRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserRoleSoap[] toSoapModels(List<UserRole> models) {
		List<UserRoleSoap> soapModels = new ArrayList<UserRoleSoap>(
			models.size());

		for (UserRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserRoleSoap[soapModels.size()]);
	}

	public UserRoleSoap() {
	}

	public UserRolePK getPrimaryKey() {
		return new UserRolePK(_userId, _compId, _roleId);
	}

	public void setPrimaryKey(UserRolePK pk) {
		setUserId(pk.userId);
		setCompId(pk.compId);
		setRoleId(pk.roleId);
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

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
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

	private long _userId;
	private long _compId;
	private long _roleId;
	private Date _createDate;
	private Date _modifiedDate;

}