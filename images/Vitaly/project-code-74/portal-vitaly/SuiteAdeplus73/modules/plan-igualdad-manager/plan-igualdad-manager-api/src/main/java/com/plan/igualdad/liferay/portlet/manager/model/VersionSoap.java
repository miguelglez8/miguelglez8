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

import com.plan.igualdad.liferay.portlet.manager.service.persistence.VersionPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.VersionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class VersionSoap implements Serializable {

	public static VersionSoap toSoapModel(Version model) {
		VersionSoap soapModel = new VersionSoap();

		soapModel.setVersionId(model.getVersionId());
		soapModel.setCompId(model.getCompId());
		soapModel.setNombre(model.getNombre());
		soapModel.setInicioPeriodoDatos(model.getInicioPeriodoDatos());
		soapModel.setFinPeriodoDatos(model.getFinPeriodoDatos());
		soapModel.setInicioPeriodoPlan(model.getInicioPeriodoPlan());
		soapModel.setFinPeriodoPlan(model.getFinPeriodoPlan());
		soapModel.setBaja(model.getBaja());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static VersionSoap[] toSoapModels(Version[] models) {
		VersionSoap[] soapModels = new VersionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VersionSoap[][] toSoapModels(Version[][] models) {
		VersionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VersionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VersionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VersionSoap[] toSoapModels(List<Version> models) {
		List<VersionSoap> soapModels = new ArrayList<VersionSoap>(
			models.size());

		for (Version model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VersionSoap[soapModels.size()]);
	}

	public VersionSoap() {
	}

	public VersionPK getPrimaryKey() {
		return new VersionPK(_versionId, _compId);
	}

	public void setPrimaryKey(VersionPK pk) {
		setVersionId(pk.versionId);
		setCompId(pk.compId);
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

	public Date getInicioPeriodoDatos() {
		return _inicioPeriodoDatos;
	}

	public void setInicioPeriodoDatos(Date inicioPeriodoDatos) {
		_inicioPeriodoDatos = inicioPeriodoDatos;
	}

	public Date getFinPeriodoDatos() {
		return _finPeriodoDatos;
	}

	public void setFinPeriodoDatos(Date finPeriodoDatos) {
		_finPeriodoDatos = finPeriodoDatos;
	}

	public Date getInicioPeriodoPlan() {
		return _inicioPeriodoPlan;
	}

	public void setInicioPeriodoPlan(Date inicioPeriodoPlan) {
		_inicioPeriodoPlan = inicioPeriodoPlan;
	}

	public Date getFinPeriodoPlan() {
		return _finPeriodoPlan;
	}

	public void setFinPeriodoPlan(Date finPeriodoPlan) {
		_finPeriodoPlan = finPeriodoPlan;
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

	private long _versionId;
	private long _compId;
	private String _nombre;
	private Date _inicioPeriodoDatos;
	private Date _finPeriodoDatos;
	private Date _inicioPeriodoPlan;
	private Date _finPeriodoPlan;
	private Date _baja;
	private Date _createDate;
	private Date _modifiedDate;

}