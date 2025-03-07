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
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.MedidaServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class MedidaSoap implements Serializable {

	public static MedidaSoap toSoapModel(Medida model) {
		MedidaSoap soapModel = new MedidaSoap();

		soapModel.setMedidaId(model.getMedidaId());
		soapModel.setCompId(model.getCompId());
		soapModel.setVersionId(model.getVersionId());
		soapModel.setUserId(model.getUserId());
		soapModel.setDatosGenerales(model.getDatosGenerales());
		soapModel.setCumplimentacion(model.getCumplimentacion());
		soapModel.setIsMedidaPredefinida(model.getIsMedidaPredefinida());
		soapModel.setMedidaPredefinidaId(model.getMedidaPredefinidaId());
		soapModel.setVersionMedida(model.getVersionMedida());
		soapModel.setVersionOriginalMedidaId(
			model.getVersionOriginalMedidaId());
		soapModel.setCreateDate(model.getCreateDate());

		return soapModel;
	}

	public static MedidaSoap[] toSoapModels(Medida[] models) {
		MedidaSoap[] soapModels = new MedidaSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MedidaSoap[][] toSoapModels(Medida[][] models) {
		MedidaSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MedidaSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MedidaSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MedidaSoap[] toSoapModels(List<Medida> models) {
		List<MedidaSoap> soapModels = new ArrayList<MedidaSoap>(models.size());

		for (Medida model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MedidaSoap[soapModels.size()]);
	}

	public MedidaSoap() {
	}

	public long getPrimaryKey() {
		return _medidaId;
	}

	public void setPrimaryKey(long pk) {
		setMedidaId(pk);
	}

	public long getMedidaId() {
		return _medidaId;
	}

	public void setMedidaId(long medidaId) {
		_medidaId = medidaId;
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

	public String getCumplimentacion() {
		return _cumplimentacion;
	}

	public void setCumplimentacion(String cumplimentacion) {
		_cumplimentacion = cumplimentacion;
	}

	public Boolean getIsMedidaPredefinida() {
		return _isMedidaPredefinida;
	}

	public void setIsMedidaPredefinida(Boolean isMedidaPredefinida) {
		_isMedidaPredefinida = isMedidaPredefinida;
	}

	public String getMedidaPredefinidaId() {
		return _medidaPredefinidaId;
	}

	public void setMedidaPredefinidaId(String medidaPredefinidaId) {
		_medidaPredefinidaId = medidaPredefinidaId;
	}

	public Integer getVersionMedida() {
		return _versionMedida;
	}

	public void setVersionMedida(Integer versionMedida) {
		_versionMedida = versionMedida;
	}

	public long getVersionOriginalMedidaId() {
		return _versionOriginalMedidaId;
	}

	public void setVersionOriginalMedidaId(long versionOriginalMedidaId) {
		_versionOriginalMedidaId = versionOriginalMedidaId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	private long _medidaId;
	private long _compId;
	private long _versionId;
	private long _userId;
	private String _datosGenerales;
	private String _cumplimentacion;
	private Boolean _isMedidaPredefinida;
	private String _medidaPredefinidaId;
	private Integer _versionMedida;
	private long _versionOriginalMedidaId;
	private Date _createDate;

}