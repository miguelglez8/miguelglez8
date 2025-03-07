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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.legalplus.liferay.portlet.legalplus.manager.service.http.LegislacionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LegislacionSoap implements Serializable {

	public static LegislacionSoap toSoapModel(Legislacion model) {
		LegislacionSoap soapModel = new LegislacionSoap();

		soapModel.setLegislacionId(model.getLegislacionId());
		soapModel.setNombre(model.getNombre());
		soapModel.setFamilia(model.getFamilia());
		soapModel.setTipo(model.getTipo());
		soapModel.setAmbito(model.getAmbito());
		soapModel.setPublicacion(model.getPublicacion());
		soapModel.setDerogacion(model.getDerogacion());
		soapModel.setDescripcion(model.getDescripcion());
		soapModel.setEtiquetas(model.getEtiquetas());
		soapModel.setEnlace(model.getEnlace());
		soapModel.setCcaa(model.getCcaa());
		soapModel.setAyuntamiento(model.getAyuntamiento());
		soapModel.setUrgente(model.getUrgente());
		soapModel.setEmpresas(model.getEmpresas());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static LegislacionSoap[] toSoapModels(Legislacion[] models) {
		LegislacionSoap[] soapModels = new LegislacionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LegislacionSoap[][] toSoapModels(Legislacion[][] models) {
		LegislacionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LegislacionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LegislacionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LegislacionSoap[] toSoapModels(List<Legislacion> models) {
		List<LegislacionSoap> soapModels = new ArrayList<LegislacionSoap>(
			models.size());

		for (Legislacion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LegislacionSoap[soapModels.size()]);
	}

	public LegislacionSoap() {
	}

	public String getPrimaryKey() {
		return _legislacionId;
	}

	public void setPrimaryKey(String pk) {
		setLegislacionId(pk);
	}

	public String getLegislacionId() {
		return _legislacionId;
	}

	public void setLegislacionId(String legislacionId) {
		_legislacionId = legislacionId;
	}

	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		_nombre = nombre;
	}

	public String getFamilia() {
		return _familia;
	}

	public void setFamilia(String familia) {
		_familia = familia;
	}

	public long getTipo() {
		return _tipo;
	}

	public void setTipo(long tipo) {
		_tipo = tipo;
	}

	public long getAmbito() {
		return _ambito;
	}

	public void setAmbito(long ambito) {
		_ambito = ambito;
	}

	public Date getPublicacion() {
		return _publicacion;
	}

	public void setPublicacion(Date publicacion) {
		_publicacion = publicacion;
	}

	public Date getDerogacion() {
		return _derogacion;
	}

	public void setDerogacion(Date derogacion) {
		_derogacion = derogacion;
	}

	public String getDescripcion() {
		return _descripcion;
	}

	public void setDescripcion(String descripcion) {
		_descripcion = descripcion;
	}

	public String getEtiquetas() {
		return _etiquetas;
	}

	public void setEtiquetas(String etiquetas) {
		_etiquetas = etiquetas;
	}

	public String getEnlace() {
		return _enlace;
	}

	public void setEnlace(String enlace) {
		_enlace = enlace;
	}

	public long getCcaa() {
		return _ccaa;
	}

	public void setCcaa(long ccaa) {
		_ccaa = ccaa;
	}

	public long getAyuntamiento() {
		return _ayuntamiento;
	}

	public void setAyuntamiento(long ayuntamiento) {
		_ayuntamiento = ayuntamiento;
	}

	public Date getUrgente() {
		return _urgente;
	}

	public void setUrgente(Date urgente) {
		_urgente = urgente;
	}

	public String getEmpresas() {
		return _empresas;
	}

	public void setEmpresas(String empresas) {
		_empresas = empresas;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	private String _legislacionId;
	private String _nombre;
	private String _familia;
	private long _tipo;
	private long _ambito;
	private Date _publicacion;
	private Date _derogacion;
	private String _descripcion;
	private String _etiquetas;
	private String _enlace;
	private long _ccaa;
	private long _ayuntamiento;
	private Date _urgente;
	private String _empresas;
	private Date _createDate;
	private Date _modifiedDate;

}