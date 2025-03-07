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
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.InformesServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class InformesSoap implements Serializable {

	public static InformesSoap toSoapModel(Informes model) {
		InformesSoap soapModel = new InformesSoap();

		soapModel.setInformeId(model.getInformeId());
		soapModel.setCompId(model.getCompId());
		soapModel.setUserId(model.getUserId());
		soapModel.setVersionId(model.getVersionId());
		soapModel.setTipoInforme(model.getTipoInforme());
		soapModel.setVersionInforme(model.getVersionInforme());
		soapModel.setParametrosInforme(model.getParametrosInforme());
		soapModel.setBeansInforme(model.getBeansInforme());
		soapModel.setFormato(model.getFormato());
		soapModel.setCreateDate(model.getCreateDate());

		return soapModel;
	}

	public static InformesSoap[] toSoapModels(Informes[] models) {
		InformesSoap[] soapModels = new InformesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static InformesSoap[][] toSoapModels(Informes[][] models) {
		InformesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new InformesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new InformesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static InformesSoap[] toSoapModels(List<Informes> models) {
		List<InformesSoap> soapModels = new ArrayList<InformesSoap>(
			models.size());

		for (Informes model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new InformesSoap[soapModels.size()]);
	}

	public InformesSoap() {
	}

	public long getPrimaryKey() {
		return _informeId;
	}

	public void setPrimaryKey(long pk) {
		setInformeId(pk);
	}

	public long getInformeId() {
		return _informeId;
	}

	public void setInformeId(long informeId) {
		_informeId = informeId;
	}

	public long getCompId() {
		return _compId;
	}

	public void setCompId(long compId) {
		_compId = compId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getVersionId() {
		return _versionId;
	}

	public void setVersionId(long versionId) {
		_versionId = versionId;
	}

	public String getTipoInforme() {
		return _tipoInforme;
	}

	public void setTipoInforme(String tipoInforme) {
		_tipoInforme = tipoInforme;
	}

	public Integer getVersionInforme() {
		return _versionInforme;
	}

	public void setVersionInforme(Integer versionInforme) {
		_versionInforme = versionInforme;
	}

	public String getParametrosInforme() {
		return _parametrosInforme;
	}

	public void setParametrosInforme(String parametrosInforme) {
		_parametrosInforme = parametrosInforme;
	}

	public String getBeansInforme() {
		return _beansInforme;
	}

	public void setBeansInforme(String beansInforme) {
		_beansInforme = beansInforme;
	}

	public String getFormato() {
		return _formato;
	}

	public void setFormato(String formato) {
		_formato = formato;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	private long _informeId;
	private long _compId;
	private long _userId;
	private long _versionId;
	private String _tipoInforme;
	private Integer _versionInforme;
	private String _parametrosInforme;
	private String _beansInforme;
	private String _formato;
	private Date _createDate;

}