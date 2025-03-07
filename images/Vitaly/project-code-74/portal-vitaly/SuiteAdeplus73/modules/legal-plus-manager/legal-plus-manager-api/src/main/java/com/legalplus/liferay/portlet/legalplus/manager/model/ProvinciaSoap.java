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

import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.ProvinciaPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.legalplus.liferay.portlet.legalplus.manager.service.http.ProvinciaServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProvinciaSoap implements Serializable {

	public static ProvinciaSoap toSoapModel(Provincia model) {
		ProvinciaSoap soapModel = new ProvinciaSoap();

		soapModel.setCcaaId(model.getCcaaId());
		soapModel.setProvinciaId(model.getProvinciaId());
		soapModel.setNombre(model.getNombre());
		soapModel.setCapitalId(model.getCapitalId());

		return soapModel;
	}

	public static ProvinciaSoap[] toSoapModels(Provincia[] models) {
		ProvinciaSoap[] soapModels = new ProvinciaSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProvinciaSoap[][] toSoapModels(Provincia[][] models) {
		ProvinciaSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProvinciaSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProvinciaSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProvinciaSoap[] toSoapModels(List<Provincia> models) {
		List<ProvinciaSoap> soapModels = new ArrayList<ProvinciaSoap>(
			models.size());

		for (Provincia model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProvinciaSoap[soapModels.size()]);
	}

	public ProvinciaSoap() {
	}

	public ProvinciaPK getPrimaryKey() {
		return new ProvinciaPK(_ccaaId, _provinciaId);
	}

	public void setPrimaryKey(ProvinciaPK pk) {
		setCcaaId(pk.ccaaId);
		setProvinciaId(pk.provinciaId);
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

	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		_nombre = nombre;
	}

	public long getCapitalId() {
		return _capitalId;
	}

	public void setCapitalId(long capitalId) {
		_capitalId = capitalId;
	}

	private long _ccaaId;
	private long _provinciaId;
	private String _nombre;
	private long _capitalId;

}