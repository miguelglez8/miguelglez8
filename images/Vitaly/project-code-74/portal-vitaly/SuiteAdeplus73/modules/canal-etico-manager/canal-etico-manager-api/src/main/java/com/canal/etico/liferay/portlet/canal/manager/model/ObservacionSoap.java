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
 * This class is used by SOAP remote services, specifically {@link com.canal.etico.liferay.portlet.canal.manager.service.http.ObservacionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ObservacionSoap implements Serializable {

	public static ObservacionSoap toSoapModel(Observacion model) {
		ObservacionSoap soapModel = new ObservacionSoap();

		soapModel.setObservacionId(model.getObservacionId());
		soapModel.setDenunciaId(model.getDenunciaId());
		soapModel.setUserId(model.getUserId());
		soapModel.setDescripcion(model.getDescripcion());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static ObservacionSoap[] toSoapModels(Observacion[] models) {
		ObservacionSoap[] soapModels = new ObservacionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ObservacionSoap[][] toSoapModels(Observacion[][] models) {
		ObservacionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ObservacionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ObservacionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ObservacionSoap[] toSoapModels(List<Observacion> models) {
		List<ObservacionSoap> soapModels = new ArrayList<ObservacionSoap>(
			models.size());

		for (Observacion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ObservacionSoap[soapModels.size()]);
	}

	public ObservacionSoap() {
	}

	public long getPrimaryKey() {
		return _observacionId;
	}

	public void setPrimaryKey(long pk) {
		setObservacionId(pk);
	}

	public long getObservacionId() {
		return _observacionId;
	}

	public void setObservacionId(long observacionId) {
		_observacionId = observacionId;
	}

	public long getDenunciaId() {
		return _denunciaId;
	}

	public void setDenunciaId(long denunciaId) {
		_denunciaId = denunciaId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getDescripcion() {
		return _descripcion;
	}

	public void setDescripcion(String descripcion) {
		_descripcion = descripcion;
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

	private long _observacionId;
	private long _denunciaId;
	private long _userId;
	private String _descripcion;
	private Date _createDate;
	private Date _modifiedDate;

}