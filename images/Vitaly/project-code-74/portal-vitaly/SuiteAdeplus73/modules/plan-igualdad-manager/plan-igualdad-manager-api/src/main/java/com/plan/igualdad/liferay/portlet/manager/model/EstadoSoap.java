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
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.EstadoServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class EstadoSoap implements Serializable {

	public static EstadoSoap toSoapModel(Estado model) {
		EstadoSoap soapModel = new EstadoSoap();

		soapModel.setEstadoId(model.getEstadoId());
		soapModel.setNombre(model.getNombre());
		soapModel.setActiva(model.getActiva());
		soapModel.setGestionParametrizaciones(
			model.getGestionParametrizaciones());

		return soapModel;
	}

	public static EstadoSoap[] toSoapModels(Estado[] models) {
		EstadoSoap[] soapModels = new EstadoSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EstadoSoap[][] toSoapModels(Estado[][] models) {
		EstadoSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EstadoSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EstadoSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EstadoSoap[] toSoapModels(List<Estado> models) {
		List<EstadoSoap> soapModels = new ArrayList<EstadoSoap>(models.size());

		for (Estado model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EstadoSoap[soapModels.size()]);
	}

	public EstadoSoap() {
	}

	public long getPrimaryKey() {
		return _estadoId;
	}

	public void setPrimaryKey(long pk) {
		setEstadoId(pk);
	}

	public long getEstadoId() {
		return _estadoId;
	}

	public void setEstadoId(long estadoId) {
		_estadoId = estadoId;
	}

	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		_nombre = nombre;
	}

	public Boolean getActiva() {
		return _activa;
	}

	public void setActiva(Boolean activa) {
		_activa = activa;
	}

	public Boolean getGestionParametrizaciones() {
		return _gestionParametrizaciones;
	}

	public void setGestionParametrizaciones(Boolean gestionParametrizaciones) {
		_gestionParametrizaciones = gestionParametrizaciones;
	}

	private long _estadoId;
	private String _nombre;
	private Boolean _activa;
	private Boolean _gestionParametrizaciones;

}