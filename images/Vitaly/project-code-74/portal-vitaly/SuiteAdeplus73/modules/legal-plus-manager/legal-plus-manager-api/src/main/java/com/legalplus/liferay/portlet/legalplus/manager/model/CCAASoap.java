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
 * This class is used by SOAP remote services, specifically {@link com.legalplus.liferay.portlet.legalplus.manager.service.http.CCAAServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CCAASoap implements Serializable {

	public static CCAASoap toSoapModel(CCAA model) {
		CCAASoap soapModel = new CCAASoap();

		soapModel.setCcaaId(model.getCcaaId());
		soapModel.setNombre(model.getNombre());

		return soapModel;
	}

	public static CCAASoap[] toSoapModels(CCAA[] models) {
		CCAASoap[] soapModels = new CCAASoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CCAASoap[][] toSoapModels(CCAA[][] models) {
		CCAASoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CCAASoap[models.length][models[0].length];
		}
		else {
			soapModels = new CCAASoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CCAASoap[] toSoapModels(List<CCAA> models) {
		List<CCAASoap> soapModels = new ArrayList<CCAASoap>(models.size());

		for (CCAA model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CCAASoap[soapModels.size()]);
	}

	public CCAASoap() {
	}

	public long getPrimaryKey() {
		return _ccaaId;
	}

	public void setPrimaryKey(long pk) {
		setCcaaId(pk);
	}

	public long getCcaaId() {
		return _ccaaId;
	}

	public void setCcaaId(long ccaaId) {
		_ccaaId = ccaaId;
	}

	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		_nombre = nombre;
	}

	private long _ccaaId;
	private String _nombre;

}