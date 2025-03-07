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

import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserApplicationClientPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.adeplus.liferay.portlet.suite.manager.service.http.UserApplicationClientServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserApplicationClientSoap implements Serializable {

	public static UserApplicationClientSoap toSoapModel(
		UserApplicationClient model) {

		UserApplicationClientSoap soapModel = new UserApplicationClientSoap();

		soapModel.setEmpresaId(model.getEmpresaId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCompId(model.getCompId());
		soapModel.setClientId(model.getClientId());
		soapModel.setContractId(model.getContractId());
		soapModel.setApplicationId(model.getApplicationId());
		soapModel.setAdmin(model.isAdmin());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setDeleteDate(model.getDeleteDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static UserApplicationClientSoap[] toSoapModels(
		UserApplicationClient[] models) {

		UserApplicationClientSoap[] soapModels =
			new UserApplicationClientSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserApplicationClientSoap[][] toSoapModels(
		UserApplicationClient[][] models) {

		UserApplicationClientSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new UserApplicationClientSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserApplicationClientSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserApplicationClientSoap[] toSoapModels(
		List<UserApplicationClient> models) {

		List<UserApplicationClientSoap> soapModels =
			new ArrayList<UserApplicationClientSoap>(models.size());

		for (UserApplicationClient model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new UserApplicationClientSoap[soapModels.size()]);
	}

	public UserApplicationClientSoap() {
	}

	public UserApplicationClientPK getPrimaryKey() {
		return new UserApplicationClientPK(_empresaId, _userId);
	}

	public void setPrimaryKey(UserApplicationClientPK pk) {
		setEmpresaId(pk.empresaId);
		setUserId(pk.userId);
	}

	public long getEmpresaId() {
		return _empresaId;
	}

	public void setEmpresaId(long empresaId) {
		_empresaId = empresaId;
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

	public long getClientId() {
		return _clientId;
	}

	public void setClientId(long clientId) {
		_clientId = clientId;
	}

	public long getContractId() {
		return _contractId;
	}

	public void setContractId(long contractId) {
		_contractId = contractId;
	}

	public String getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(String applicationId) {
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

	private long _empresaId;
	private long _userId;
	private long _compId;
	private long _clientId;
	private long _contractId;
	private String _applicationId;
	private boolean _admin;
	private Date _createDate;
	private Date _deleteDate;
	private Date _modifiedDate;

}