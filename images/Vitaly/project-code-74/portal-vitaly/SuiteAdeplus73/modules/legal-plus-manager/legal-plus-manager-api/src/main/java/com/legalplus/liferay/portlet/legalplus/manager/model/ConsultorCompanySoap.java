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

package com.legalplus.liferay.portlet.legalplus.manager.model;

import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.ConsultorCompanyPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.legalplus.liferay.portlet.legalplus.manager.service.http.ConsultorCompanyServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ConsultorCompanySoap implements Serializable {

	public static ConsultorCompanySoap toSoapModel(ConsultorCompany model) {
		ConsultorCompanySoap soapModel = new ConsultorCompanySoap();

		soapModel.setAppId(model.getAppId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCompId(model.getCompId());
		soapModel.setName(model.getName());
		soapModel.setLastName(model.getLastName());

		return soapModel;
	}

	public static ConsultorCompanySoap[] toSoapModels(
		ConsultorCompany[] models) {

		ConsultorCompanySoap[] soapModels =
			new ConsultorCompanySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ConsultorCompanySoap[][] toSoapModels(
		ConsultorCompany[][] models) {

		ConsultorCompanySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ConsultorCompanySoap[models.length][models[0].length];
		}
		else {
			soapModels = new ConsultorCompanySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ConsultorCompanySoap[] toSoapModels(
		List<ConsultorCompany> models) {

		List<ConsultorCompanySoap> soapModels =
			new ArrayList<ConsultorCompanySoap>(models.size());

		for (ConsultorCompany model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ConsultorCompanySoap[soapModels.size()]);
	}

	public ConsultorCompanySoap() {
	}

	public ConsultorCompanyPK getPrimaryKey() {
		return new ConsultorCompanyPK(_appId, _userId, _compId);
	}

	public void setPrimaryKey(ConsultorCompanyPK pk) {
		setAppId(pk.appId);
		setUserId(pk.userId);
		setCompId(pk.compId);
	}

	public long getAppId() {
		return _appId;
	}

	public void setAppId(long appId) {
		_appId = appId;
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

	private long _appId;
	private long _userId;
	private long _compId;
	private String _name;
	private String _lastName;

}