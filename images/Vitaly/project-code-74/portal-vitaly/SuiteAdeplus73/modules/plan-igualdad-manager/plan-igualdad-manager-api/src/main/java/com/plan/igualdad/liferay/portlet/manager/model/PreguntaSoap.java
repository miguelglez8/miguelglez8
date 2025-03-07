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

import com.plan.igualdad.liferay.portlet.manager.service.persistence.PreguntaPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.PreguntaServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class PreguntaSoap implements Serializable {

	public static PreguntaSoap toSoapModel(Pregunta model) {
		PreguntaSoap soapModel = new PreguntaSoap();

		soapModel.setPreguntaId(model.getPreguntaId());
		soapModel.setSeccionId(model.getSeccionId());
		soapModel.setTipo(model.getTipo());
		soapModel.setPregunta(model.getPregunta());

		return soapModel;
	}

	public static PreguntaSoap[] toSoapModels(Pregunta[] models) {
		PreguntaSoap[] soapModels = new PreguntaSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PreguntaSoap[][] toSoapModels(Pregunta[][] models) {
		PreguntaSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PreguntaSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PreguntaSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PreguntaSoap[] toSoapModels(List<Pregunta> models) {
		List<PreguntaSoap> soapModels = new ArrayList<PreguntaSoap>(
			models.size());

		for (Pregunta model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PreguntaSoap[soapModels.size()]);
	}

	public PreguntaSoap() {
	}

	public PreguntaPK getPrimaryKey() {
		return new PreguntaPK(_preguntaId, _seccionId);
	}

	public void setPrimaryKey(PreguntaPK pk) {
		setPreguntaId(pk.preguntaId);
		setSeccionId(pk.seccionId);
	}

	public long getPreguntaId() {
		return _preguntaId;
	}

	public void setPreguntaId(long preguntaId) {
		_preguntaId = preguntaId;
	}

	public long getSeccionId() {
		return _seccionId;
	}

	public void setSeccionId(long seccionId) {
		_seccionId = seccionId;
	}

	public String getTipo() {
		return _tipo;
	}

	public void setTipo(String tipo) {
		_tipo = tipo;
	}

	public String getPregunta() {
		return _pregunta;
	}

	public void setPregunta(String pregunta) {
		_pregunta = pregunta;
	}

	private long _preguntaId;
	private long _seccionId;
	private String _tipo;
	private String _pregunta;

}