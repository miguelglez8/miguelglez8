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
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.HitoServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class HitoSoap implements Serializable {

	public static HitoSoap toSoapModel(Hito model) {
		HitoSoap soapModel = new HitoSoap();

		soapModel.setHitoId(model.getHitoId());
		soapModel.setNombre(model.getNombre());
		soapModel.setPeso(model.getPeso());

		return soapModel;
	}

	public static HitoSoap[] toSoapModels(Hito[] models) {
		HitoSoap[] soapModels = new HitoSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HitoSoap[][] toSoapModels(Hito[][] models) {
		HitoSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HitoSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HitoSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HitoSoap[] toSoapModels(List<Hito> models) {
		List<HitoSoap> soapModels = new ArrayList<HitoSoap>(models.size());

		for (Hito model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HitoSoap[soapModels.size()]);
	}

	public HitoSoap() {
	}

	public long getPrimaryKey() {
		return _hitoId;
	}

	public void setPrimaryKey(long pk) {
		setHitoId(pk);
	}

	public long getHitoId() {
		return _hitoId;
	}

	public void setHitoId(long hitoId) {
		_hitoId = hitoId;
	}

	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		_nombre = nombre;
	}

	public String getPeso() {
		return _peso;
	}

	public void setPeso(String peso) {
		_peso = peso;
	}

	private long _hitoId;
	private String _nombre;
	private String _peso;

}