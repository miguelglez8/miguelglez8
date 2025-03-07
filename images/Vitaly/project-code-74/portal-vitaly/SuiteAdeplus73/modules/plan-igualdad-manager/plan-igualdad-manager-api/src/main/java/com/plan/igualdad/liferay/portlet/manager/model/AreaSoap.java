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

package com.plan.igualdad.liferay.portlet.manager.model;

import com.plan.igualdad.liferay.portlet.manager.service.persistence.AreaPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.AreaServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class AreaSoap implements Serializable {

	public static AreaSoap toSoapModel(Area model) {
		AreaSoap soapModel = new AreaSoap();

		soapModel.setAreaId(model.getAreaId());
		soapModel.setVersionId(model.getVersionId());
		soapModel.setCompId(model.getCompId());
		soapModel.setNombre(model.getNombre());
		soapModel.setBaja(model.getBaja());

		return soapModel;
	}

	public static AreaSoap[] toSoapModels(Area[] models) {
		AreaSoap[] soapModels = new AreaSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AreaSoap[][] toSoapModels(Area[][] models) {
		AreaSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AreaSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AreaSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AreaSoap[] toSoapModels(List<Area> models) {
		List<AreaSoap> soapModels = new ArrayList<AreaSoap>(models.size());

		for (Area model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AreaSoap[soapModels.size()]);
	}

	public AreaSoap() {
	}

	public AreaPK getPrimaryKey() {
		return new AreaPK(_areaId, _versionId, _compId);
	}

	public void setPrimaryKey(AreaPK pk) {
		setAreaId(pk.areaId);
		setVersionId(pk.versionId);
		setCompId(pk.compId);
	}

	public long getAreaId() {
		return _areaId;
	}

	public void setAreaId(long areaId) {
		_areaId = areaId;
	}

	public long getVersionId() {
		return _versionId;
	}

	public void setVersionId(long versionId) {
		_versionId = versionId;
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

	public Date getBaja() {
		return _baja;
	}

	public void setBaja(Date baja) {
		_baja = baja;
	}

	private long _areaId;
	private long _versionId;
	private long _compId;
	private String _nombre;
	private Date _baja;

}