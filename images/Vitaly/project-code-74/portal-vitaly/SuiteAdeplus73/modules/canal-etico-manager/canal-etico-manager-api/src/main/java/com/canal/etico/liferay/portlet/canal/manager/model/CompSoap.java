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

package com.canal.etico.liferay.portlet.canal.manager.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.canal.etico.liferay.portlet.canal.manager.service.http.CompServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CompSoap implements Serializable {

	public static CompSoap toSoapModel(Comp model) {
		CompSoap soapModel = new CompSoap();

		soapModel.setCompId(model.getCompId());
		soapModel.setSuiteCompId(model.getSuiteCompId());
		soapModel.setCif(model.getCif());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setObservations(model.getObservations());
		soapModel.setAgreement(model.getAgreement());
		soapModel.setFriendlyURL(model.getFriendlyURL());
		soapModel.setLogo(model.getLogo());
		soapModel.setActive(model.isActive());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setDeleteDate(model.getDeleteDate());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static CompSoap[] toSoapModels(Comp[] models) {
		CompSoap[] soapModels = new CompSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CompSoap[][] toSoapModels(Comp[][] models) {
		CompSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CompSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CompSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CompSoap[] toSoapModels(List<Comp> models) {
		List<CompSoap> soapModels = new ArrayList<CompSoap>(models.size());

		for (Comp model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CompSoap[soapModels.size()]);
	}

	public CompSoap() {
	}

	public long getPrimaryKey() {
		return _compId;
	}

	public void setPrimaryKey(long pk) {
		setCompId(pk);
	}

	public long getCompId() {
		return _compId;
	}

	public void setCompId(long compId) {
		_compId = compId;
	}

	public long getSuiteCompId() {
		return _suiteCompId;
	}

	public void setSuiteCompId(long suiteCompId) {
		_suiteCompId = suiteCompId;
	}

	public String getCif() {
		return _cif;
	}

	public void setCif(String cif) {
		_cif = cif;
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

	public String getAgreement() {
		return _agreement;
	}

	public void setAgreement(String agreement) {
		_agreement = agreement;
	}

	public String getFriendlyURL() {
		return _friendlyURL;
	}

	public void setFriendlyURL(String friendlyURL) {
		_friendlyURL = friendlyURL;
	}

	public long getLogo() {
		return _logo;
	}

	public void setLogo(long logo) {
		_logo = logo;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
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

	private long _compId;
	private long _suiteCompId;
	private String _cif;
	private String _name;
	private String _description;
	private String _observations;
	private String _agreement;
	private String _friendlyURL;
	private long _logo;
	private boolean _active;
	private Date _startDate;
	private Date _deleteDate;
	private Date _createDate;
	private Date _modifiedDate;

}