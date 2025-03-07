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

package com.adeplus.liferay.portlet.suite.manager.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.adeplus.liferay.portlet.suite.manager.service.http.AuditadoDataApiServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AuditadoDataApiSoap implements Serializable {

	public static AuditadoDataApiSoap toSoapModel(AuditadoDataApi model) {
		AuditadoDataApiSoap soapModel = new AuditadoDataApiSoap();

		soapModel.setIdDataTemporal(model.getIdDataTemporal());
		soapModel.setMensaje(model.getMensaje());
		soapModel.setProcesadoDate(model.getProcesadoDate());
		soapModel.setProcesado(model.getProcesado());
		soapModel.setEvento(model.getEvento());
		soapModel.setCif(model.getCif());
		soapModel.setIdClient(model.getIdClient());
		soapModel.setIdContract(model.getIdContract());
		soapModel.setApp(model.getApp());
		soapModel.setChangeStateDate(model.getChangeStateDate());
		soapModel.setState(model.isState());
		soapModel.setUserIdChangeState(model.getUserIdChangeState());

		return soapModel;
	}

	public static AuditadoDataApiSoap[] toSoapModels(AuditadoDataApi[] models) {
		AuditadoDataApiSoap[] soapModels =
			new AuditadoDataApiSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AuditadoDataApiSoap[][] toSoapModels(
		AuditadoDataApi[][] models) {

		AuditadoDataApiSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new AuditadoDataApiSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AuditadoDataApiSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AuditadoDataApiSoap[] toSoapModels(
		List<AuditadoDataApi> models) {

		List<AuditadoDataApiSoap> soapModels =
			new ArrayList<AuditadoDataApiSoap>(models.size());

		for (AuditadoDataApi model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AuditadoDataApiSoap[soapModels.size()]);
	}

	public AuditadoDataApiSoap() {
	}

	public long getPrimaryKey() {
		return _idDataTemporal;
	}

	public void setPrimaryKey(long pk) {
		setIdDataTemporal(pk);
	}

	public long getIdDataTemporal() {
		return _idDataTemporal;
	}

	public void setIdDataTemporal(long idDataTemporal) {
		_idDataTemporal = idDataTemporal;
	}

	public String getMensaje() {
		return _mensaje;
	}

	public void setMensaje(String mensaje) {
		_mensaje = mensaje;
	}

	public Date getProcesadoDate() {
		return _procesadoDate;
	}

	public void setProcesadoDate(Date procesadoDate) {
		_procesadoDate = procesadoDate;
	}

	public Integer getProcesado() {
		return _procesado;
	}

	public void setProcesado(Integer procesado) {
		_procesado = procesado;
	}

	public String getEvento() {
		return _evento;
	}

	public void setEvento(String evento) {
		_evento = evento;
	}

	public String getCif() {
		return _cif;
	}

	public void setCif(String cif) {
		_cif = cif;
	}

	public long getIdClient() {
		return _idClient;
	}

	public void setIdClient(long idClient) {
		_idClient = idClient;
	}

	public long getIdContract() {
		return _idContract;
	}

	public void setIdContract(long idContract) {
		_idContract = idContract;
	}

	public String getApp() {
		return _app;
	}

	public void setApp(String app) {
		_app = app;
	}

	public Date getChangeStateDate() {
		return _changeStateDate;
	}

	public void setChangeStateDate(Date changeStateDate) {
		_changeStateDate = changeStateDate;
	}

	public boolean getState() {
		return _state;
	}

	public boolean isState() {
		return _state;
	}

	public void setState(boolean state) {
		_state = state;
	}

	public long getUserIdChangeState() {
		return _userIdChangeState;
	}

	public void setUserIdChangeState(long userIdChangeState) {
		_userIdChangeState = userIdChangeState;
	}

	private long _idDataTemporal;
	private String _mensaje;
	private Date _procesadoDate;
	private Integer _procesado;
	private String _evento;
	private String _cif;
	private long _idClient;
	private long _idContract;
	private String _app;
	private Date _changeStateDate;
	private boolean _state;
	private long _userIdChangeState;

}