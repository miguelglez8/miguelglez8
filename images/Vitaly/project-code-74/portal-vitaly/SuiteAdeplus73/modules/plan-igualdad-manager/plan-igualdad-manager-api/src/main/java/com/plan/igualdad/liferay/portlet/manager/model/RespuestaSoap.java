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

import com.plan.igualdad.liferay.portlet.manager.service.persistence.RespuestaPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.RespuestaServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class RespuestaSoap implements Serializable {

	public static RespuestaSoap toSoapModel(Respuesta model) {
		RespuestaSoap soapModel = new RespuestaSoap();

		soapModel.setCompId(model.getCompId());
		soapModel.setVersionId(model.getVersionId());
		soapModel.setSeccionId(model.getSeccionId());
		soapModel.setRespuestas(model.getRespuestas());
		soapModel.setEstado(model.getEstado());

		return soapModel;
	}

	public static RespuestaSoap[] toSoapModels(Respuesta[] models) {
		RespuestaSoap[] soapModels = new RespuestaSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RespuestaSoap[][] toSoapModels(Respuesta[][] models) {
		RespuestaSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RespuestaSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RespuestaSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RespuestaSoap[] toSoapModels(List<Respuesta> models) {
		List<RespuestaSoap> soapModels = new ArrayList<RespuestaSoap>(
			models.size());

		for (Respuesta model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RespuestaSoap[soapModels.size()]);
	}

	public RespuestaSoap() {
	}

	public RespuestaPK getPrimaryKey() {
		return new RespuestaPK(_compId, _versionId, _seccionId);
	}

	public void setPrimaryKey(RespuestaPK pk) {
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

	public String getRespuestas() {
		return _respuestas;
	}

	public void setRespuestas(String respuestas) {
		_respuestas = respuestas;
	}

	public long getEstado() {
		return _estado;
	}

	public void setEstado(long estado) {
		_estado = estado;
	}

	private long _compId;
	private long _versionId;
	private long _seccionId;
	private String _respuestas;
	private long _estado;

}