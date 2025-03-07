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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.legalplus.liferay.portlet.legalplus.manager.service.http.CNAESServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CNAESSoap implements Serializable {

	public static CNAESSoap toSoapModel(CNAES model) {
		CNAESSoap soapModel = new CNAESSoap();

		soapModel.setCnae(model.getCnae());
		soapModel.setNombre(model.getNombre());

		return soapModel;
	}

	public static CNAESSoap[] toSoapModels(CNAES[] models) {
		CNAESSoap[] soapModels = new CNAESSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CNAESSoap[][] toSoapModels(CNAES[][] models) {
		CNAESSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CNAESSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CNAESSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CNAESSoap[] toSoapModels(List<CNAES> models) {
		List<CNAESSoap> soapModels = new ArrayList<CNAESSoap>(models.size());

		for (CNAES model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CNAESSoap[soapModels.size()]);
	}

	public CNAESSoap() {
	}

	public String getPrimaryKey() {
		return _cnae;
	}

	public void setPrimaryKey(String pk) {
		setCnae(pk);
	}

	public String getCnae() {
		return _cnae;
	}

	public void setCnae(String cnae) {
		_cnae = cnae;
	}

	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		_nombre = nombre;
	}

	private String _cnae;
	private String _nombre;

}