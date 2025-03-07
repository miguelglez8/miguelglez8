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
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.EstadoEmpresaServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class EstadoEmpresaSoap implements Serializable {

	public static EstadoEmpresaSoap toSoapModel(EstadoEmpresa model) {
		EstadoEmpresaSoap soapModel = new EstadoEmpresaSoap();

		soapModel.setCompId(model.getCompId());
		soapModel.setEstadoId(model.getEstadoId());

		return soapModel;
	}

	public static EstadoEmpresaSoap[] toSoapModels(EstadoEmpresa[] models) {
		EstadoEmpresaSoap[] soapModels = new EstadoEmpresaSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EstadoEmpresaSoap[][] toSoapModels(EstadoEmpresa[][] models) {
		EstadoEmpresaSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EstadoEmpresaSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EstadoEmpresaSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EstadoEmpresaSoap[] toSoapModels(List<EstadoEmpresa> models) {
		List<EstadoEmpresaSoap> soapModels = new ArrayList<EstadoEmpresaSoap>(
			models.size());

		for (EstadoEmpresa model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EstadoEmpresaSoap[soapModels.size()]);
	}

	public EstadoEmpresaSoap() {
	}

	public long getPrimaryKey() {
		return _compId;
	}

	public void setPrimaryKey(long pk) {
		setCompId(pk);
	}

	public long getCompId() {
		return _compId;
	}

	public void setCompId(long compId) {
		_compId = compId;
	}

	public String getEstadoId() {
		return _estadoId;
	}

	public void setEstadoId(String estadoId) {
		_estadoId = estadoId;
	}

	private long _compId;
	private String _estadoId;

}