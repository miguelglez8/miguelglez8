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

import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserCompanyPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.adeplus.liferay.portlet.suite.manager.service.http.UserCompanyServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserCompanySoap implements Serializable {

	public static UserCompanySoap toSoapModel(UserCompany model) {
		UserCompanySoap soapModel = new UserCompanySoap();

		soapModel.setUserId(model.getUserId());
		soapModel.setCompId(model.getCompId());
		soapModel.setName(model.getName());
		soapModel.setLastName(model.getLastName());
		soapModel.setNif(model.getNif());
		soapModel.setEmail(model.getEmail());
		soapModel.setPhone(model.getPhone());
		soapModel.setAdmin(model.isAdmin());
		soapModel.setDefaultUserComp(model.isDefaultUserComp());
		soapModel.setDefaultClientId(model.getDefaultClientId());
		soapModel.setDefaultContractId(model.getDefaultContractId());
		soapModel.setDefaultEmpresaId(model.getDefaultEmpresaId());
		soapModel.setUserEndDate(model.getUserEndDate());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public String getNif() {
		return _nif;
	}

	public void setNif(String nif) {
		_nif = nif;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getPhone() {
		return _phone;
	}

	public void setPhone(String phone) {
		_phone = phone;
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

	public boolean getDefaultUserComp() {
		return _defaultUserComp;
	}

	public boolean isDefaultUserComp() {
		return _defaultUserComp;
	}

	public void setDefaultUserComp(boolean defaultUserComp) {
		_defaultUserComp = defaultUserComp;
	}

	public long getDefaultClientId() {
		return _defaultClientId;
	}

	public void setDefaultClientId(long defaultClientId) {
		_defaultClientId = defaultClientId;
	}

	public long getDefaultContractId() {
		return _defaultContractId;
	}

	public void setDefaultContractId(long defaultContractId) {
		_defaultContractId = defaultContractId;
	}

	public long getDefaultEmpresaId() {
		return _defaultEmpresaId;
	}

	public void setDefaultEmpresaId(long defaultEmpresaId) {
		_defaultEmpresaId = defaultEmpresaId;
	}

	public Date getUserEndDate() {
		return _userEndDate;
	}

	public void setUserEndDate(Date userEndDate) {
		_userEndDate = userEndDate;
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
	private String _name;
	private String _lastName;
	private String _nif;
	private String _email;
	private String _phone;
	private boolean _admin;
	private boolean _defaultUserComp;
	private long _defaultClientId;
	private long _defaultContractId;
	private long _defaultEmpresaId;
	private Date _userEndDate;
	private Date _createDate;
	private Date _modifiedDate;

}