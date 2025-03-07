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

import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.EvalRequisitoPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.legalplus.liferay.portlet.legalplus.manager.service.http.EvalRequisitoServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EvalRequisitoSoap implements Serializable {

	public static EvalRequisitoSoap toSoapModel(EvalRequisito model) {
		EvalRequisitoSoap soapModel = new EvalRequisitoSoap();

		soapModel.setVersion(model.getVersion());
		soapModel.setCompId(model.getCompId());
		soapModel.setLegislacionId(model.getLegislacionId());
		soapModel.setRequisitoId(model.getRequisitoId());
		soapModel.setFecha(model.getFecha());
		soapModel.setProxima(model.getProxima());
		soapModel.setCumplimiento(model.getCumplimiento());
		soapModel.setUsuario(model.getUsuario());
		soapModel.setAdjunto(model.getAdjunto());
		soapModel.setObservaciones(model.getObservaciones());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static EvalRequisitoSoap[] toSoapModels(EvalRequisito[] models) {
		EvalRequisitoSoap[] soapModels = new EvalRequisitoSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EvalRequisitoSoap[][] toSoapModels(EvalRequisito[][] models) {
		EvalRequisitoSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EvalRequisitoSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EvalRequisitoSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EvalRequisitoSoap[] toSoapModels(List<EvalRequisito> models) {
		List<EvalRequisitoSoap> soapModels = new ArrayList<EvalRequisitoSoap>(
			models.size());

		for (EvalRequisito model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EvalRequisitoSoap[soapModels.size()]);
	}

	public EvalRequisitoSoap() {
	}

	public EvalRequisitoPK getPrimaryKey() {
		return new EvalRequisitoPK(
			_version, _compId, _legislacionId, _requisitoId);
	}

	public void setPrimaryKey(EvalRequisitoPK pk) {
		setVersion(pk.version);
		setCompId(pk.compId);
		setLegislacionId(pk.legislacionId);
		setRequisitoId(pk.requisitoId);
	}

	public long getVersion() {
		return _version;
	}

	public void setVersion(long version) {
		_version = version;
	}

	public long getCompId() {
		return _compId;
	}

	public void setCompId(long compId) {
		_compId = compId;
	}

	public String getLegislacionId() {
		return _legislacionId;
	}

	public void setLegislacionId(String legislacionId) {
		_legislacionId = legislacionId;
	}

	public String getRequisitoId() {
		return _requisitoId;
	}

	public void setRequisitoId(String requisitoId) {
		_requisitoId = requisitoId;
	}

	public Date getFecha() {
		return _fecha;
	}

	public void setFecha(Date fecha) {
		_fecha = fecha;
	}

	public Date getProxima() {
		return _proxima;
	}

	public void setProxima(Date proxima) {
		_proxima = proxima;
	}

	public int getCumplimiento() {
		return _cumplimiento;
	}

	public void setCumplimiento(int cumplimiento) {
		_cumplimiento = cumplimiento;
	}

	public long getUsuario() {
		return _usuario;
	}

	public void setUsuario(long usuario) {
		_usuario = usuario;
	}

	public long getAdjunto() {
		return _adjunto;
	}

	public void setAdjunto(long adjunto) {
		_adjunto = adjunto;
	}

	public String getObservaciones() {
		return _observaciones;
	}

	public void setObservaciones(String observaciones) {
		_observaciones = observaciones;
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

	private long _version;
	private long _compId;
	private String _legislacionId;
	private String _requisitoId;
	private Date _fecha;
	private Date _proxima;
	private int _cumplimiento;
	private long _usuario;
	private long _adjunto;
	private String _observaciones;
	private Date _createDate;
	private Date _modifiedDate;

}