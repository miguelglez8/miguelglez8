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
 * This class is used by SOAP remote services, specifically {@link com.adeplus.liferay.portlet.suite.manager.service.http.CompClientApplicationServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CompClientApplicationSoap implements Serializable {

	public static CompClientApplicationSoap toSoapModel(
		CompClientApplication model) {

		CompClientApplicationSoap soapModel = new CompClientApplicationSoap();

		soapModel.setEmpresaId(model.getEmpresaId());
		soapModel.setCif(model.getCif());
		soapModel.setCompId(model.getCompId());
		soapModel.setClientId(model.getClientId());
		soapModel.setContractId(model.getContractId());
		soapModel.setApplicationId(model.getApplicationId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setObservations(model.getObservations());
		soapModel.setIdEstado(model.getIdEstado());
		soapModel.setLogo(model.getLogo());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setDeleteDate(model.getDeleteDate());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static CompClientApplicationSoap[] toSoapModels(
		CompClientApplication[] models) {

		CompClientApplicationSoap[] soapModels =
			new CompClientApplicationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CompClientApplicationSoap[][] toSoapModels(
		CompClientApplication[][] models) {

		CompClientApplicationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CompClientApplicationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CompClientApplicationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CompClientApplicationSoap[] toSoapModels(
		List<CompClientApplication> models) {

		List<CompClientApplicationSoap> soapModels =
			new ArrayList<CompClientApplicationSoap>(models.size());

		for (CompClientApplication model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new CompClientApplicationSoap[soapModels.size()]);
	}

	public CompClientApplicationSoap() {
	}

	public long getPrimaryKey() {
		return _empresaId;
	}

	public void setPrimaryKey(long pk) {
		setEmpresaId(pk);
	}

	public long getEmpresaId() {
		return _empresaId;
	}

	public void setEmpresaId(long empresaId) {
		_empresaId = empresaId;
	}

	public String getCif() {
		return _cif;
	}

	public void setCif(String cif) {
		_cif = cif;
	}

	public long getCompId() {
		return _compId;
	}

	public void setCompId(long compId) {
		_compId = compId;
	}

	public long getClientId() {
		return _clientId;
	}

	public void setClientId(long clientId) {
		_clientId = clientId;
	}

	public long getContractId() {
		return _contractId;
	}

	public void setContractId(long contractId) {
		_contractId = contractId;
	}

	public String getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(String applicationId) {
		_applicationId = applicationId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getObservations() {
		return _observations;
	}

	public void setObservations(String observations) {
		_observations = observations;
	}

	public String getIdEstado() {
		return _idEstado;
	}

	public void setIdEstado(String idEstado) {
		_idEstado = idEstado;
	}

	public long getLogo() {
		return _logo;
	}

	public void setLogo(long logo) {
		_logo = logo;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getDeleteDate() {
		return _deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		_deleteDate = deleteDate;
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

	private long _empresaId;
	private String _cif;
	private long _compId;
	private long _clientId;
	private long _contractId;
	private String _applicationId;
	private String _name;
	private String _description;
	private String _observations;
	private String _idEstado;
	private long _logo;
	private Date _startDate;
	private Date _deleteDate;
	private Date _createDate;
	private Date _modifiedDate;

}