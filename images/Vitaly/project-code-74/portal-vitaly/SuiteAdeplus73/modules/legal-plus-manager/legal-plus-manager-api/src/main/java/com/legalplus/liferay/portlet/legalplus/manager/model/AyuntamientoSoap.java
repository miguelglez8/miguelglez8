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

package com.legalplus.liferay.portlet.legalplus.manager.model;

import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.AyuntamientoPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.legalplus.liferay.portlet.legalplus.manager.service.http.AyuntamientoServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AyuntamientoSoap implements Serializable {

	public static AyuntamientoSoap toSoapModel(Ayuntamiento model) {
		AyuntamientoSoap soapModel = new AyuntamientoSoap();

		soapModel.setCcaaId(model.getCcaaId());
		soapModel.setProvinciaId(model.getProvinciaId());
		soapModel.setAyuntamientoId(model.getAyuntamientoId());
		soapModel.setNombre(model.getNombre());

		return soapModel;
	}

	public static AyuntamientoSoap[] toSoapModels(Ayuntamiento[] models) {
		AyuntamientoSoap[] soapModels = new AyuntamientoSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AyuntamientoSoap[][] toSoapModels(Ayuntamiento[][] models) {
		AyuntamientoSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AyuntamientoSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AyuntamientoSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AyuntamientoSoap[] toSoapModels(List<Ayuntamiento> models) {
		List<AyuntamientoSoap> soapModels = new ArrayList<AyuntamientoSoap>(
			models.size());

		for (Ayuntamiento model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AyuntamientoSoap[soapModels.size()]);
	}

	public AyuntamientoSoap() {
	}

	public AyuntamientoPK getPrimaryKey() {
		return new AyuntamientoPK(_ccaaId, _provinciaId, _ayuntamientoId);
	}

	public void setPrimaryKey(AyuntamientoPK pk) {
		setCcaaId(pk.ccaaId);
		setProvinciaId(pk.provinciaId);
		setAyuntamientoId(pk.ayuntamientoId);
	}

	public long getCcaaId() {
		return _ccaaId;
	}

	public void setCcaaId(long ccaaId) {
		_ccaaId = ccaaId;
	}

	public long getProvinciaId() {
		return _provinciaId;
	}

	public void setProvinciaId(long provinciaId) {
		_provinciaId = provinciaId;
	}

	public long getAyuntamientoId() {
		return _ayuntamientoId;
	}

	public void setAyuntamientoId(long ayuntamientoId) {
		_ayuntamientoId = ayuntamientoId;
	}

	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		_nombre = nombre;
	}

	private long _ccaaId;
	private long _provinciaId;
	private long _ayuntamientoId;
	private String _nombre;

}