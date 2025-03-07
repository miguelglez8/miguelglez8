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
 * This class is used by SOAP remote services, specifically {@link com.adeplus.liferay.portlet.suite.manager.service.http.AuditServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AuditSoap implements Serializable {

	public static AuditSoap toSoapModel(Audit model) {
		AuditSoap soapModel = new AuditSoap();

		soapModel.setAuditId(model.getAuditId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setAction(model.getAction());
		soapModel.setDescription(model.getDescription());
		soapModel.setDate(model.getDate());

		return soapModel;
	}

	public static AuditSoap[] toSoapModels(Audit[] models) {
		AuditSoap[] soapModels = new AuditSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AuditSoap[][] toSoapModels(Audit[][] models) {
		AuditSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AuditSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AuditSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AuditSoap[] toSoapModels(List<Audit> models) {
		List<AuditSoap> soapModels = new ArrayList<AuditSoap>(models.size());

		for (Audit model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AuditSoap[soapModels.size()]);
	}

	public AuditSoap() {
	}

	public long getPrimaryKey() {
		return _auditId;
	}

	public void setPrimaryKey(long pk) {
		setAuditId(pk);
	}

	public long getAuditId() {
		return _auditId;
	}

	public void setAuditId(long auditId) {
		_auditId = auditId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getAction() {
		return _action;
	}

	public void setAction(String action) {
		_action = action;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public Date getDate() {
		return _date;
	}

	public void setDate(Date date) {
		_date = date;
	}

	private long _auditId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _action;
	private String _description;
	private Date _date;

}