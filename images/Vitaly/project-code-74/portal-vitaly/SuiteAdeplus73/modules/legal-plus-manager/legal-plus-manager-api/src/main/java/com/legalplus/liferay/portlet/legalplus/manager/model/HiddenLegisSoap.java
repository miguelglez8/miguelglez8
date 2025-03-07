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

import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.HiddenLegisPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.legalplus.liferay.portlet.legalplus.manager.service.http.HiddenLegisServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class HiddenLegisSoap implements Serializable {

	public static HiddenLegisSoap toSoapModel(HiddenLegis model) {
		HiddenLegisSoap soapModel = new HiddenLegisSoap();

		soapModel.setLegislacionId(model.getLegislacionId());
		soapModel.setEmpresaId(model.getEmpresaId());

		return soapModel;
	}

	public static HiddenLegisSoap[] toSoapModels(HiddenLegis[] models) {
		HiddenLegisSoap[] soapModels = new HiddenLegisSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HiddenLegisSoap[][] toSoapModels(HiddenLegis[][] models) {
		HiddenLegisSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HiddenLegisSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HiddenLegisSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HiddenLegisSoap[] toSoapModels(List<HiddenLegis> models) {
		List<HiddenLegisSoap> soapModels = new ArrayList<HiddenLegisSoap>(
			models.size());

		for (HiddenLegis model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HiddenLegisSoap[soapModels.size()]);
	}

	public HiddenLegisSoap() {
	}

	public HiddenLegisPK getPrimaryKey() {
		return new HiddenLegisPK(_legislacionId, _empresaId);
	}

	public void setPrimaryKey(HiddenLegisPK pk) {
		setLegislacionId(pk.legislacionId);
		setEmpresaId(pk.empresaId);
	}

	public String getLegislacionId() {
		return _legislacionId;
	}

	public void setLegislacionId(String legislacionId) {
		_legislacionId = legislacionId;
	}

	public long getEmpresaId() {
		return _empresaId;
	}

	public void setEmpresaId(long empresaId) {
		_empresaId = empresaId;
	}

	private String _legislacionId;
	private long _empresaId;

}