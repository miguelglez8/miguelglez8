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

import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.RequisitoPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.legalplus.liferay.portlet.legalplus.manager.service.http.RequisitoServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RequisitoSoap implements Serializable {

	public static RequisitoSoap toSoapModel(Requisito model) {
		RequisitoSoap soapModel = new RequisitoSoap();

		soapModel.setLegislacionId(model.getLegislacionId());
		soapModel.setRequisitoId(model.getRequisitoId());
		soapModel.setDescripcion(model.getDescripcion());
		soapModel.setBaja(model.getBaja());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static RequisitoSoap[] toSoapModels(Requisito[] models) {
		RequisitoSoap[] soapModels = new RequisitoSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RequisitoSoap[][] toSoapModels(Requisito[][] models) {
		RequisitoSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RequisitoSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RequisitoSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RequisitoSoap[] toSoapModels(List<Requisito> models) {
		List<RequisitoSoap> soapModels = new ArrayList<RequisitoSoap>(
			models.size());

		for (Requisito model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RequisitoSoap[soapModels.size()]);
	}

	public RequisitoSoap() {
	}

	public RequisitoPK getPrimaryKey() {
		return new RequisitoPK(_legislacionId, _requisitoId);
	}

	public void setPrimaryKey(RequisitoPK pk) {
		setLegislacionId(pk.legislacionId);
		setRequisitoId(pk.requisitoId);
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

	public String getDescripcion() {
		return _descripcion;
	}

	public void setDescripcion(String descripcion) {
		_descripcion = descripcion;
	}

	public Date getBaja() {
		return _baja;
	}

	public void setBaja(Date baja) {
		_baja = baja;
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
	private String _descripcion;
	private Date _baja;
	private Date _createDate;
	private Date _modifiedDate;

}