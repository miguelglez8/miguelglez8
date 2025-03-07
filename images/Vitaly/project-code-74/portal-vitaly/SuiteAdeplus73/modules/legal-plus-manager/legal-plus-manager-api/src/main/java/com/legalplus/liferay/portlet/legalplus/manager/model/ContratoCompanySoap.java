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

import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.ContratoCompanyPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.legalplus.liferay.portlet.legalplus.manager.service.http.ContratoCompanyServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContratoCompanySoap implements Serializable {

	public static ContratoCompanySoap toSoapModel(ContratoCompany model) {
		ContratoCompanySoap soapModel = new ContratoCompanySoap();

		soapModel.setContractId(model.getContractId());
		soapModel.setCompId(model.getCompId());
		soapModel.setFamilia(model.getFamilia());
		soapModel.setCcaa(model.getCcaa());
		soapModel.setAyuntamiento(model.getAyuntamiento());
		soapModel.setCnaes(model.getCnaes());

		return soapModel;
	}

	public static ContratoCompanySoap[] toSoapModels(ContratoCompany[] models) {
		ContratoCompanySoap[] soapModels =
			new ContratoCompanySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContratoCompanySoap[][] toSoapModels(
		ContratoCompany[][] models) {

		ContratoCompanySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ContratoCompanySoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContratoCompanySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContratoCompanySoap[] toSoapModels(
		List<ContratoCompany> models) {

		List<ContratoCompanySoap> soapModels =
			new ArrayList<ContratoCompanySoap>(models.size());

		for (ContratoCompany model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ContratoCompanySoap[soapModels.size()]);
	}

	public ContratoCompanySoap() {
	}

	public ContratoCompanyPK getPrimaryKey() {
		return new ContratoCompanyPK(_contractId, _compId);
	}

	public void setPrimaryKey(ContratoCompanyPK pk) {
		setContractId(pk.contractId);
		setCompId(pk.compId);
	}

	public long getContractId() {
		return _contractId;
	}

	public void setContractId(long contractId) {
		_contractId = contractId;
	}

	public long getCompId() {
		return _compId;
	}

	public void setCompId(long compId) {
		_compId = compId;
	}

	public String getFamilia() {
		return _familia;
	}

	public void setFamilia(String familia) {
		_familia = familia;
	}

	public String getCcaa() {
		return _ccaa;
	}

	public void setCcaa(String ccaa) {
		_ccaa = ccaa;
	}

	public String getAyuntamiento() {
		return _ayuntamiento;
	}

	public void setAyuntamiento(String ayuntamiento) {
		_ayuntamiento = ayuntamiento;
	}

	public String getCnaes() {
		return _cnaes;
	}

	public void setCnaes(String cnaes) {
		_cnaes = cnaes;
	}

	private long _contractId;
	private long _compId;
	private String _familia;
	private String _ccaa;
	private String _ayuntamiento;
	private String _cnaes;

}