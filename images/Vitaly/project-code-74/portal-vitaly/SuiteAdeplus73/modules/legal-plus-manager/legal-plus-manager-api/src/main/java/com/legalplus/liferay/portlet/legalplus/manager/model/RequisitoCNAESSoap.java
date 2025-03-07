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

import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.RequisitoCNAESPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.legalplus.liferay.portlet.legalplus.manager.service.http.RequisitoCNAESServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RequisitoCNAESSoap implements Serializable {

	public static RequisitoCNAESSoap toSoapModel(RequisitoCNAES model) {
		RequisitoCNAESSoap soapModel = new RequisitoCNAESSoap();

		soapModel.setLegislacionId(model.getLegislacionId());
		soapModel.setRequisitoId(model.getRequisitoId());
		soapModel.setCnae(model.getCnae());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static RequisitoCNAESSoap[] toSoapModels(RequisitoCNAES[] models) {
		RequisitoCNAESSoap[] soapModels = new RequisitoCNAESSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RequisitoCNAESSoap[][] toSoapModels(
		RequisitoCNAES[][] models) {

		RequisitoCNAESSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new RequisitoCNAESSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RequisitoCNAESSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RequisitoCNAESSoap[] toSoapModels(
		List<RequisitoCNAES> models) {

		List<RequisitoCNAESSoap> soapModels = new ArrayList<RequisitoCNAESSoap>(
			models.size());

		for (RequisitoCNAES model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RequisitoCNAESSoap[soapModels.size()]);
	}

	public RequisitoCNAESSoap() {
	}

	public RequisitoCNAESPK getPrimaryKey() {
		return new RequisitoCNAESPK(_legislacionId, _requisitoId, _cnae);
	}

	public void setPrimaryKey(RequisitoCNAESPK pk) {
		setLegislacionId(pk.legislacionId);
		setRequisitoId(pk.requisitoId);
		setCnae(pk.cnae);
	}

	public String getLegislacionId() {
		return _legislacionId;
	}

	public void setLegislacionId(String legislacionId) {
		_legislacionId = legislacionId;
	}

	public String getRequisitoId() {
		return _requisitoId;
	}

	public void setRequisitoId(String requisitoId) {
		_requisitoId = requisitoId;
	}

	public String getCnae() {
		return _cnae;
	}

	public void setCnae(String cnae) {
		_cnae = cnae;
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

	private String _legislacionId;
	private String _requisitoId;
	private String _cnae;
	private Date _createDate;
	private Date _modifiedDate;

}