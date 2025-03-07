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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.canal.etico.liferay.portlet.canal.manager.service.http.DenunciaAccionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DenunciaAccionSoap implements Serializable {

	public static DenunciaAccionSoap toSoapModel(DenunciaAccion model) {
		DenunciaAccionSoap soapModel = new DenunciaAccionSoap();

		soapModel.setDenunciaAccionId(model.getDenunciaAccionId());
		soapModel.setDenunciaId(model.getDenunciaId());
		soapModel.setAccionId(model.getAccionId());
		soapModel.setUserId(model.getUserId());
		soapModel.setObservaciones(model.getObservaciones());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static DenunciaAccionSoap[] toSoapModels(DenunciaAccion[] models) {
		DenunciaAccionSoap[] soapModels = new DenunciaAccionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DenunciaAccionSoap[][] toSoapModels(
		DenunciaAccion[][] models) {

		DenunciaAccionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new DenunciaAccionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DenunciaAccionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DenunciaAccionSoap[] toSoapModels(
		List<DenunciaAccion> models) {

		List<DenunciaAccionSoap> soapModels = new ArrayList<DenunciaAccionSoap>(
			models.size());

		for (DenunciaAccion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DenunciaAccionSoap[soapModels.size()]);
	}

	public DenunciaAccionSoap() {
	}

	public long getPrimaryKey() {
		return _denunciaAccionId;
	}

	public void setPrimaryKey(long pk) {
		setDenunciaAccionId(pk);
	}

	public long getDenunciaAccionId() {
		return _denunciaAccionId;
	}

	public void setDenunciaAccionId(long denunciaAccionId) {
		_denunciaAccionId = denunciaAccionId;
	}

	public long getDenunciaId() {
		return _denunciaId;
	}

	public void setDenunciaId(long denunciaId) {
		_denunciaId = denunciaId;
	}

	public long getAccionId() {
		return _accionId;
	}

	public void setAccionId(long accionId) {
		_accionId = accionId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getObservaciones() {
		return _observaciones;
	}

	public void setObservaciones(String observaciones) {
		_observaciones = observaciones;
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

	private long _denunciaAccionId;
	private long _denunciaId;
	private long _accionId;
	private long _userId;
	private String _observaciones;
	private Date _createDate;
	private Date _modifiedDate;

}