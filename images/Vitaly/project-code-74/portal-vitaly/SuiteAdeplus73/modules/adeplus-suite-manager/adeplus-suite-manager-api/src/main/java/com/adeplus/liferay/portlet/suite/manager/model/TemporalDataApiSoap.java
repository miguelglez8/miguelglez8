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
 * This class is used by SOAP remote services, specifically {@link com.adeplus.liferay.portlet.suite.manager.service.http.TemporalDataApiServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TemporalDataApiSoap implements Serializable {

	public static TemporalDataApiSoap toSoapModel(TemporalDataApi model) {
		TemporalDataApiSoap soapModel = new TemporalDataApiSoap();

		soapModel.setIdDataTemporal(model.getIdDataTemporal());
		soapModel.setTypeEvent(model.getTypeEvent());
		soapModel.setDataApiEmpresa(model.getDataApiEmpresa());
		soapModel.setDataApiApplicaciones(model.getDataApiApplicaciones());
		soapModel.setDataApiUsuarios(model.getDataApiUsuarios());
		soapModel.setProcesado(model.getProcesado());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setProcesadoDate(model.getProcesadoDate());

		return soapModel;
	}

	public static TemporalDataApiSoap[] toSoapModels(TemporalDataApi[] models) {
		TemporalDataApiSoap[] soapModels =
			new TemporalDataApiSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TemporalDataApiSoap[][] toSoapModels(
		TemporalDataApi[][] models) {

		TemporalDataApiSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new TemporalDataApiSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TemporalDataApiSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TemporalDataApiSoap[] toSoapModels(
		List<TemporalDataApi> models) {

		List<TemporalDataApiSoap> soapModels =
			new ArrayList<TemporalDataApiSoap>(models.size());

		for (TemporalDataApi model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TemporalDataApiSoap[soapModels.size()]);
	}

	public TemporalDataApiSoap() {
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

	public String getTypeEvent() {
		return _typeEvent;
	}

	public void setTypeEvent(String typeEvent) {
		_typeEvent = typeEvent;
	}

	public String getDataApiEmpresa() {
		return _dataApiEmpresa;
	}

	public void setDataApiEmpresa(String dataApiEmpresa) {
		_dataApiEmpresa = dataApiEmpresa;
	}

	public String getDataApiApplicaciones() {
		return _dataApiApplicaciones;
	}

	public void setDataApiApplicaciones(String dataApiApplicaciones) {
		_dataApiApplicaciones = dataApiApplicaciones;
	}

	public String getDataApiUsuarios() {
		return _dataApiUsuarios;
	}

	public void setDataApiUsuarios(String dataApiUsuarios) {
		_dataApiUsuarios = dataApiUsuarios;
	}

	public Integer getProcesado() {
		return _procesado;
	}

	public void setProcesado(Integer procesado) {
		_procesado = procesado;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getProcesadoDate() {
		return _procesadoDate;
	}

	public void setProcesadoDate(Date procesadoDate) {
		_procesadoDate = procesadoDate;
	}

	private long _idDataTemporal;
	private String _typeEvent;
	private String _dataApiEmpresa;
	private String _dataApiApplicaciones;
	private String _dataApiUsuarios;
	private Integer _procesado;
	private Date _createDate;
	private Date _procesadoDate;

}