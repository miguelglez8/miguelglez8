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
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.CuestionarioServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class CuestionarioSoap implements Serializable {

	public static CuestionarioSoap toSoapModel(Cuestionario model) {
		CuestionarioSoap soapModel = new CuestionarioSoap();

		soapModel.setCuestionarioId(model.getCuestionarioId());
		soapModel.setCompId(model.getCompId());
		soapModel.setVersionId(model.getVersionId());
		soapModel.setUserId(model.getUserId());
		soapModel.setRespuestasCuestionario(model.getRespuestasCuestionario());
		soapModel.setObservaciones(model.getObservaciones());
		soapModel.setVersionCuestionario(model.getVersionCuestionario());
		soapModel.setCreateDate(model.getCreateDate());

		return soapModel;
	}

	public static CuestionarioSoap[] toSoapModels(Cuestionario[] models) {
		CuestionarioSoap[] soapModels = new CuestionarioSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CuestionarioSoap[][] toSoapModels(Cuestionario[][] models) {
		CuestionarioSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CuestionarioSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CuestionarioSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CuestionarioSoap[] toSoapModels(List<Cuestionario> models) {
		List<CuestionarioSoap> soapModels = new ArrayList<CuestionarioSoap>(
			models.size());

		for (Cuestionario model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CuestionarioSoap[soapModels.size()]);
	}

	public CuestionarioSoap() {
	}

	public long getPrimaryKey() {
		return _cuestionarioId;
	}

	public void setPrimaryKey(long pk) {
		setCuestionarioId(pk);
	}

	public long getCuestionarioId() {
		return _cuestionarioId;
	}

	public void setCuestionarioId(long cuestionarioId) {
		_cuestionarioId = cuestionarioId;
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

	public String getRespuestasCuestionario() {
		return _respuestasCuestionario;
	}

	public void setRespuestasCuestionario(String respuestasCuestionario) {
		_respuestasCuestionario = respuestasCuestionario;
	}

	public String getObservaciones() {
		return _observaciones;
	}

	public void setObservaciones(String observaciones) {
		_observaciones = observaciones;
	}

	public Integer getVersionCuestionario() {
		return _versionCuestionario;
	}

	public void setVersionCuestionario(Integer versionCuestionario) {
		_versionCuestionario = versionCuestionario;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	private long _cuestionarioId;
	private long _compId;
	private long _versionId;
	private long _userId;
	private String _respuestasCuestionario;
	private String _observaciones;
	private Integer _versionCuestionario;
	private Date _createDate;

}