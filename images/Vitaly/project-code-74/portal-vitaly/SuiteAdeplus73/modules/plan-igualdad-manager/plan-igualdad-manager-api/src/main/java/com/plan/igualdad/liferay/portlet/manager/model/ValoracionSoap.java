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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.ValoracionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ValoracionSoap implements Serializable {

	public static ValoracionSoap toSoapModel(Valoracion model) {
		ValoracionSoap soapModel = new ValoracionSoap();

		soapModel.setValoracionId(model.getValoracionId());
		soapModel.setCompId(model.getCompId());
		soapModel.setVersionId(model.getVersionId());
		soapModel.setUserId(model.getUserId());
		soapModel.setRespuestasValoracion(model.getRespuestasValoracion());
		soapModel.setObservaciones(model.getObservaciones());
		soapModel.setVersionValoracion(model.getVersionValoracion());
		soapModel.setCreateDate(model.getCreateDate());

		return soapModel;
	}

	public static ValoracionSoap[] toSoapModels(Valoracion[] models) {
		ValoracionSoap[] soapModels = new ValoracionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ValoracionSoap[][] toSoapModels(Valoracion[][] models) {
		ValoracionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ValoracionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ValoracionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ValoracionSoap[] toSoapModels(List<Valoracion> models) {
		List<ValoracionSoap> soapModels = new ArrayList<ValoracionSoap>(
			models.size());

		for (Valoracion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ValoracionSoap[soapModels.size()]);
	}

	public ValoracionSoap() {
	}

	public long getPrimaryKey() {
		return _valoracionId;
	}

	public void setPrimaryKey(long pk) {
		setValoracionId(pk);
	}

	public long getValoracionId() {
		return _valoracionId;
	}

	public void setValoracionId(long valoracionId) {
		_valoracionId = valoracionId;
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

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getRespuestasValoracion() {
		return _respuestasValoracion;
	}

	public void setRespuestasValoracion(String respuestasValoracion) {
		_respuestasValoracion = respuestasValoracion;
	}

	public String getObservaciones() {
		return _observaciones;
	}

	public void setObservaciones(String observaciones) {
		_observaciones = observaciones;
	}

	public Integer getVersionValoracion() {
		return _versionValoracion;
	}

	public void setVersionValoracion(Integer versionValoracion) {
		_versionValoracion = versionValoracion;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	private long _valoracionId;
	private long _compId;
	private long _versionId;
	private long _userId;
	private String _respuestasValoracion;
	private String _observaciones;
	private Integer _versionValoracion;
	private Date _createDate;

}