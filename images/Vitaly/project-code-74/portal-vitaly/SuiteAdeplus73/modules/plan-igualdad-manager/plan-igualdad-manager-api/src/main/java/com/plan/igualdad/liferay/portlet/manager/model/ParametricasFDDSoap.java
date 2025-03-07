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

import com.plan.igualdad.liferay.portlet.manager.service.persistence.ParametricasFDDPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.ParametricasFDDServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ParametricasFDDSoap implements Serializable {

	public static ParametricasFDDSoap toSoapModel(ParametricasFDD model) {
		ParametricasFDDSoap soapModel = new ParametricasFDDSoap();

		soapModel.setCompId(model.getCompId());
		soapModel.setVersionId(model.getVersionId());
		soapModel.setIdParametrica(model.getIdParametrica());
		soapModel.setTipo(model.getTipo());
		soapModel.setMateria(model.getMateria());
		soapModel.setContenido(model.getContenido());
		soapModel.setSeccionId(model.getSeccionId());

		return soapModel;
	}

	public static ParametricasFDDSoap[] toSoapModels(ParametricasFDD[] models) {
		ParametricasFDDSoap[] soapModels =
			new ParametricasFDDSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ParametricasFDDSoap[][] toSoapModels(
		ParametricasFDD[][] models) {

		ParametricasFDDSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ParametricasFDDSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ParametricasFDDSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ParametricasFDDSoap[] toSoapModels(
		List<ParametricasFDD> models) {

		List<ParametricasFDDSoap> soapModels =
			new ArrayList<ParametricasFDDSoap>(models.size());

		for (ParametricasFDD model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ParametricasFDDSoap[soapModels.size()]);
	}

	public ParametricasFDDSoap() {
	}

	public ParametricasFDDPK getPrimaryKey() {
		return new ParametricasFDDPK(_compId, _versionId, _idParametrica);
	}

	public void setPrimaryKey(ParametricasFDDPK pk) {
		setCompId(pk.compId);
		setVersionId(pk.versionId);
		setIdParametrica(pk.idParametrica);
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

	public String getIdParametrica() {
		return _idParametrica;
	}

	public void setIdParametrica(String idParametrica) {
		_idParametrica = idParametrica;
	}

	public String getTipo() {
		return _tipo;
	}

	public void setTipo(String tipo) {
		_tipo = tipo;
	}

	public Integer getMateria() {
		return _materia;
	}

	public void setMateria(Integer materia) {
		_materia = materia;
	}

	public String getContenido() {
		return _contenido;
	}

	public void setContenido(String contenido) {
		_contenido = contenido;
	}

	public long getSeccionId() {
		return _seccionId;
	}

	public void setSeccionId(long seccionId) {
		_seccionId = seccionId;
	}

	private long _compId;
	private long _versionId;
	private String _idParametrica;
	private String _tipo;
	private Integer _materia;
	private String _contenido;
	private long _seccionId;

}