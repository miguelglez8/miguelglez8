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
 * This class is used by SOAP remote services, specifically {@link com.canal.etico.liferay.portlet.canal.manager.service.http.VinculacionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class VinculacionSoap implements Serializable {

	public static VinculacionSoap toSoapModel(Vinculacion model) {
		VinculacionSoap soapModel = new VinculacionSoap();

		soapModel.setVinculacionId(model.getVinculacionId());
		soapModel.setCompId(model.getCompId());
		soapModel.setNombre(model.getNombre());
		soapModel.setActivo(model.isActivo());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static VinculacionSoap[] toSoapModels(Vinculacion[] models) {
		VinculacionSoap[] soapModels = new VinculacionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VinculacionSoap[][] toSoapModels(Vinculacion[][] models) {
		VinculacionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VinculacionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VinculacionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VinculacionSoap[] toSoapModels(List<Vinculacion> models) {
		List<VinculacionSoap> soapModels = new ArrayList<VinculacionSoap>(
			models.size());

		for (Vinculacion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VinculacionSoap[soapModels.size()]);
	}

	public VinculacionSoap() {
	}

	public long getPrimaryKey() {
		return _vinculacionId;
	}

	public void setPrimaryKey(long pk) {
		setVinculacionId(pk);
	}

	public long getVinculacionId() {
		return _vinculacionId;
	}

	public void setVinculacionId(long vinculacionId) {
		_vinculacionId = vinculacionId;
	}

	public long getCompId() {
		return _compId;
	}

	public void setCompId(long compId) {
		_compId = compId;
	}

	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		_nombre = nombre;
	}

	public boolean getActivo() {
		return _activo;
	}

	public boolean isActivo() {
		return _activo;
	}

	public void setActivo(boolean activo) {
		_activo = activo;
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

	private long _vinculacionId;
	private long _compId;
	private String _nombre;
	private boolean _activo;
	private Date _createDate;
	private Date _modifiedDate;

}