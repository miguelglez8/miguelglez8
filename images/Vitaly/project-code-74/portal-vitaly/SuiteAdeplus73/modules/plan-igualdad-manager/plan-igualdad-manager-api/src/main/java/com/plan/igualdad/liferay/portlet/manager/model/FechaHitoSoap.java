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

import com.plan.igualdad.liferay.portlet.manager.service.persistence.FechaHitoPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.FechaHitoServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class FechaHitoSoap implements Serializable {

	public static FechaHitoSoap toSoapModel(FechaHito model) {
		FechaHitoSoap soapModel = new FechaHitoSoap();

		soapModel.setHitoId(model.getHitoId());
		soapModel.setVersionId(model.getVersionId());
		soapModel.setCompId(model.getCompId());
		soapModel.setFecha(model.getFecha());

		return soapModel;
	}

	public static FechaHitoSoap[] toSoapModels(FechaHito[] models) {
		FechaHitoSoap[] soapModels = new FechaHitoSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FechaHitoSoap[][] toSoapModels(FechaHito[][] models) {
		FechaHitoSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FechaHitoSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FechaHitoSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FechaHitoSoap[] toSoapModels(List<FechaHito> models) {
		List<FechaHitoSoap> soapModels = new ArrayList<FechaHitoSoap>(
			models.size());

		for (FechaHito model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FechaHitoSoap[soapModels.size()]);
	}

	public FechaHitoSoap() {
	}

	public FechaHitoPK getPrimaryKey() {
		return new FechaHitoPK(_hitoId, _versionId, _compId);
	}

	public void setPrimaryKey(FechaHitoPK pk) {
		setHitoId(pk.hitoId);
		setVersionId(pk.versionId);
		setCompId(pk.compId);
	}

	public long getHitoId() {
		return _hitoId;
	}

	public void setHitoId(long hitoId) {
		_hitoId = hitoId;
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

	public Date getFecha() {
		return _fecha;
	}

	public void setFecha(Date fecha) {
		_fecha = fecha;
	}

	private long _hitoId;
	private long _versionId;
	private long _compId;
	private Date _fecha;

}