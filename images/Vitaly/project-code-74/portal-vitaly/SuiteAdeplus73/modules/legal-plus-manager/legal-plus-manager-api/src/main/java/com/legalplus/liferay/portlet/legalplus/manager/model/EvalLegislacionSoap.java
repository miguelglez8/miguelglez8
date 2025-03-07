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

import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.EvalLegislacionPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.legalplus.liferay.portlet.legalplus.manager.service.http.EvalLegislacionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EvalLegislacionSoap implements Serializable {

	public static EvalLegislacionSoap toSoapModel(EvalLegislacion model) {
		EvalLegislacionSoap soapModel = new EvalLegislacionSoap();

		soapModel.setVersion(model.getVersion());
		soapModel.setCompId(model.getCompId());
		soapModel.setLegislacionId(model.getLegislacionId());
		soapModel.setFecha(model.getFecha());
		soapModel.setProxima(model.getProxima());
		soapModel.setCumplimiento(model.getCumplimiento());
		soapModel.setUsuario(model.getUsuario());
		soapModel.setAdjunto(model.getAdjunto());
		soapModel.setObservaciones(model.getObservaciones());

		return soapModel;
	}

	public static EvalLegislacionSoap[] toSoapModels(EvalLegislacion[] models) {
		EvalLegislacionSoap[] soapModels =
			new EvalLegislacionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EvalLegislacionSoap[][] toSoapModels(
		EvalLegislacion[][] models) {

		EvalLegislacionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new EvalLegislacionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EvalLegislacionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EvalLegislacionSoap[] toSoapModels(
		List<EvalLegislacion> models) {

		List<EvalLegislacionSoap> soapModels =
			new ArrayList<EvalLegislacionSoap>(models.size());

		for (EvalLegislacion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EvalLegislacionSoap[soapModels.size()]);
	}

	public EvalLegislacionSoap() {
	}

	public EvalLegislacionPK getPrimaryKey() {
		return new EvalLegislacionPK(_version, _compId, _legislacionId);
	}

	public void setPrimaryKey(EvalLegislacionPK pk) {
		setVersion(pk.version);
		setCompId(pk.compId);
		setLegislacionId(pk.legislacionId);
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

	private long _version;
	private long _compId;
	private String _legislacionId;
	private Date _fecha;
	private Date _proxima;
	private int _cumplimiento;
	private long _usuario;
	private long _adjunto;
	private String _observaciones;

}