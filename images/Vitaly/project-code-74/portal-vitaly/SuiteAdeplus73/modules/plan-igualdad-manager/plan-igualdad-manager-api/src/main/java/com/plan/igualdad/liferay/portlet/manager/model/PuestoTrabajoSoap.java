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

import com.plan.igualdad.liferay.portlet.manager.service.persistence.PuestoTrabajoPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.PuestoTrabajoServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class PuestoTrabajoSoap implements Serializable {

	public static PuestoTrabajoSoap toSoapModel(PuestoTrabajo model) {
		PuestoTrabajoSoap soapModel = new PuestoTrabajoSoap();

		soapModel.setPuestoId(model.getPuestoId());
		soapModel.setVersionId(model.getVersionId());
		soapModel.setCompId(model.getCompId());
		soapModel.setNombre(model.getNombre());
		soapModel.setAreaId(model.getAreaId());
		soapModel.setResponsabilidad(model.getResponsabilidad());
		soapModel.setNHombres(model.getNHombres());
		soapModel.setNMujeres(model.getNMujeres());
		soapModel.setBaja(model.getBaja());

		return soapModel;
	}

	public static PuestoTrabajoSoap[] toSoapModels(PuestoTrabajo[] models) {
		PuestoTrabajoSoap[] soapModels = new PuestoTrabajoSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PuestoTrabajoSoap[][] toSoapModels(PuestoTrabajo[][] models) {
		PuestoTrabajoSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PuestoTrabajoSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PuestoTrabajoSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PuestoTrabajoSoap[] toSoapModels(List<PuestoTrabajo> models) {
		List<PuestoTrabajoSoap> soapModels = new ArrayList<PuestoTrabajoSoap>(
			models.size());

		for (PuestoTrabajo model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PuestoTrabajoSoap[soapModels.size()]);
	}

	public PuestoTrabajoSoap() {
	}

	public PuestoTrabajoPK getPrimaryKey() {
		return new PuestoTrabajoPK(_puestoId, _versionId, _compId);
	}

	public void setPrimaryKey(PuestoTrabajoPK pk) {
		setPuestoId(pk.puestoId);
		setVersionId(pk.versionId);
		setCompId(pk.compId);
	}

	public long getPuestoId() {
		return _puestoId;
	}

	public void setPuestoId(long puestoId) {
		_puestoId = puestoId;
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

	public long getAreaId() {
		return _areaId;
	}

	public void setAreaId(long areaId) {
		_areaId = areaId;
	}

	public long getResponsabilidad() {
		return _responsabilidad;
	}

	public void setResponsabilidad(long responsabilidad) {
		_responsabilidad = responsabilidad;
	}

	public long getNHombres() {
		return _nHombres;
	}

	public void setNHombres(long nHombres) {
		_nHombres = nHombres;
	}

	public long getNMujeres() {
		return _nMujeres;
	}

	public void setNMujeres(long nMujeres) {
		_nMujeres = nMujeres;
	}

	public Date getBaja() {
		return _baja;
	}

	public void setBaja(Date baja) {
		_baja = baja;
	}

	private long _puestoId;
	private long _versionId;
	private long _compId;
	private String _nombre;
	private long _areaId;
	private long _responsabilidad;
	private long _nHombres;
	private long _nMujeres;
	private Date _baja;

}