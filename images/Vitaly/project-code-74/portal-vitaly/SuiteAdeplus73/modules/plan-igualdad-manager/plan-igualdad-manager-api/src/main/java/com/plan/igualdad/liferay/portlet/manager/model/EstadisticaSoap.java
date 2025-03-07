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

import com.plan.igualdad.liferay.portlet.manager.service.persistence.EstadisticaPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.EstadisticaServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class EstadisticaSoap implements Serializable {

	public static EstadisticaSoap toSoapModel(Estadistica model) {
		EstadisticaSoap soapModel = new EstadisticaSoap();

		soapModel.setCompId(model.getCompId());
		soapModel.setVersionId(model.getVersionId());
		soapModel.setSeccionId(model.getSeccionId());
		soapModel.setEstadistica(model.getEstadistica());

		return soapModel;
	}

	public static EstadisticaSoap[] toSoapModels(Estadistica[] models) {
		EstadisticaSoap[] soapModels = new EstadisticaSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EstadisticaSoap[][] toSoapModels(Estadistica[][] models) {
		EstadisticaSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EstadisticaSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EstadisticaSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EstadisticaSoap[] toSoapModels(List<Estadistica> models) {
		List<EstadisticaSoap> soapModels = new ArrayList<EstadisticaSoap>(
			models.size());

		for (Estadistica model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EstadisticaSoap[soapModels.size()]);
	}

	public EstadisticaSoap() {
	}

	public EstadisticaPK getPrimaryKey() {
		return new EstadisticaPK(_compId, _versionId, _seccionId);
	}

	public void setPrimaryKey(EstadisticaPK pk) {
		setCompId(pk.compId);
		setVersionId(pk.versionId);
		setSeccionId(pk.seccionId);
	}

	public long getCompId() {
		return _compId;
	}

	public void setCompId(long compId) {
		_compId = compId;
	}

	public long getVersionId() {
		return _versionId;
	}

	public void setVersionId(long versionId) {
		_versionId = versionId;
	}

	public long getSeccionId() {
		return _seccionId;
	}

	public void setSeccionId(long seccionId) {
		_seccionId = seccionId;
	}

	public String getEstadistica() {
		return _estadistica;
	}

	public void setEstadistica(String estadistica) {
		_estadistica = estadistica;
	}

	private long _compId;
	private long _versionId;
	private long _seccionId;
	private String _estadistica;

}