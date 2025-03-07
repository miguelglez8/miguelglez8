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
 * This class is used by SOAP remote services, specifically {@link com.canal.etico.liferay.portlet.canal.manager.service.http.ComunicacionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ComunicacionSoap implements Serializable {

	public static ComunicacionSoap toSoapModel(Comunicacion model) {
		ComunicacionSoap soapModel = new ComunicacionSoap();

		soapModel.setComunicacionId(model.getComunicacionId());
		soapModel.setDenunciaId(model.getDenunciaId());
		soapModel.setUserId(model.getUserId());
		soapModel.setDescripcion(model.getDescripcion());
		soapModel.setAdjunto(model.getAdjunto());
		soapModel.setObservaciones(model.getObservaciones());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static ComunicacionSoap[] toSoapModels(Comunicacion[] models) {
		ComunicacionSoap[] soapModels = new ComunicacionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ComunicacionSoap[][] toSoapModels(Comunicacion[][] models) {
		ComunicacionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ComunicacionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ComunicacionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ComunicacionSoap[] toSoapModels(List<Comunicacion> models) {
		List<ComunicacionSoap> soapModels = new ArrayList<ComunicacionSoap>(
			models.size());

		for (Comunicacion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ComunicacionSoap[soapModels.size()]);
	}

	public ComunicacionSoap() {
	}

	public long getPrimaryKey() {
		return _comunicacionId;
	}

	public void setPrimaryKey(long pk) {
		setComunicacionId(pk);
	}

	public long getComunicacionId() {
		return _comunicacionId;
	}

	public void setComunicacionId(long comunicacionId) {
		_comunicacionId = comunicacionId;
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

	public long getAdjunto() {
		return _adjunto;
	}

	public void setAdjunto(long adjunto) {
		_adjunto = adjunto;
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

	private long _comunicacionId;
	private long _denunciaId;
	private long _userId;
	private String _descripcion;
	private long _adjunto;
	private String _observaciones;
	private Date _createDate;
	private Date _modifiedDate;

}