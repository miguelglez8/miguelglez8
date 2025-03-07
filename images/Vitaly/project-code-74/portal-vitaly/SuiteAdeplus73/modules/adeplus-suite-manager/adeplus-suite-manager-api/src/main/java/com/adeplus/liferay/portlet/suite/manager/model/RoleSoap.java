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
 * This class is used by SOAP remote services, specifically {@link com.adeplus.liferay.portlet.suite.manager.service.http.RoleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RoleSoap implements Serializable {

	public static RoleSoap toSoapModel(Role model) {
		RoleSoap soapModel = new RoleSoap();

		soapModel.setRoleId(model.getRoleId());
		soapModel.setApplicationId(model.getApplicationId());
		soapModel.setName(model.getName());
		soapModel.setAlias(model.getAlias());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static RoleSoap[] toSoapModels(Role[] models) {
		RoleSoap[] soapModels = new RoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RoleSoap[][] toSoapModels(Role[][] models) {
		RoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RoleSoap[] toSoapModels(List<Role> models) {
		List<RoleSoap> soapModels = new ArrayList<RoleSoap>(models.size());

		for (Role model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RoleSoap[soapModels.size()]);
	}

	public RoleSoap() {
	}

	public long getPrimaryKey() {
		return _roleId;
	}

	public void setPrimaryKey(long pk) {
		setRoleId(pk);
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
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

	public String getAlias() {
		return _alias;
	}

	public void setAlias(String alias) {
		_alias = alias;
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

	private long _roleId;
	private long _applicationId;
	private String _name;
	private String _alias;
	private Date _createDate;
	private Date _modifiedDate;

}