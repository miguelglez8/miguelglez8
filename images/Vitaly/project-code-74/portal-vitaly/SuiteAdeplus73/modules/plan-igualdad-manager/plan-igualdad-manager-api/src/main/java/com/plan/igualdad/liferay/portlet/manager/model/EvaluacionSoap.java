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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.EvaluacionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class EvaluacionSoap implements Serializable {

	public static EvaluacionSoap toSoapModel(Evaluacion model) {
		EvaluacionSoap soapModel = new EvaluacionSoap();

		soapModel.setEvaluacionId(model.getEvaluacionId());
		soapModel.setCompId(model.getCompId());
		soapModel.setVersionId(model.getVersionId());
		soapModel.setUserId(model.getUserId());
		soapModel.setDatosGenerales(model.getDatosGenerales());
		soapModel.setInformacionResultados(model.getInformacionResultados());
		soapModel.setInformacionImplantacion(
			model.getInformacionImplantacion());
		soapModel.setInformacionImpacto(model.getInformacionImpacto());
		soapModel.setConclusion(model.getConclusion());
		soapModel.setObservaciones(model.getObservaciones());
		soapModel.setVersionEvaluacion(model.getVersionEvaluacion());
		soapModel.setCreateDate(model.getCreateDate());

		return soapModel;
	}

	public static EvaluacionSoap[] toSoapModels(Evaluacion[] models) {
		EvaluacionSoap[] soapModels = new EvaluacionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EvaluacionSoap[][] toSoapModels(Evaluacion[][] models) {
		EvaluacionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EvaluacionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EvaluacionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EvaluacionSoap[] toSoapModels(List<Evaluacion> models) {
		List<EvaluacionSoap> soapModels = new ArrayList<EvaluacionSoap>(
			models.size());

		for (Evaluacion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EvaluacionSoap[soapModels.size()]);
	}

	public EvaluacionSoap() {
	}

	public long getPrimaryKey() {
		return _evaluacionId;
	}

	public void setPrimaryKey(long pk) {
		setEvaluacionId(pk);
	}

	public long getEvaluacionId() {
		return _evaluacionId;
	}

	public void setEvaluacionId(long evaluacionId) {
		_evaluacionId = evaluacionId;
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

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getDatosGenerales() {
		return _datosGenerales;
	}

	public void setDatosGenerales(String datosGenerales) {
		_datosGenerales = datosGenerales;
	}

	public String getInformacionResultados() {
		return _informacionResultados;
	}

	public void setInformacionResultados(String informacionResultados) {
		_informacionResultados = informacionResultados;
	}

	public String getInformacionImplantacion() {
		return _informacionImplantacion;
	}

	public void setInformacionImplantacion(String informacionImplantacion) {
		_informacionImplantacion = informacionImplantacion;
	}

	public String getInformacionImpacto() {
		return _informacionImpacto;
	}

	public void setInformacionImpacto(String informacionImpacto) {
		_informacionImpacto = informacionImpacto;
	}

	public String getConclusion() {
		return _conclusion;
	}

	public void setConclusion(String conclusion) {
		_conclusion = conclusion;
	}

	public String getObservaciones() {
		return _observaciones;
	}

	public void setObservaciones(String observaciones) {
		_observaciones = observaciones;
	}

	public Integer getVersionEvaluacion() {
		return _versionEvaluacion;
	}

	public void setVersionEvaluacion(Integer versionEvaluacion) {
		_versionEvaluacion = versionEvaluacion;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	private long _evaluacionId;
	private long _compId;
	private long _versionId;
	private long _userId;
	private String _datosGenerales;
	private String _informacionResultados;
	private String _informacionImplantacion;
	private String _informacionImpacto;
	private String _conclusion;
	private String _observaciones;
	private Integer _versionEvaluacion;
	private Date _createDate;

}