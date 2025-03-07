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
 * This class is used by SOAP remote services, specifically {@link com.adeplus.liferay.portlet.suite.manager.service.http.ApplicationServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ApplicationSoap implements Serializable {

	public static ApplicationSoap toSoapModel(Application model) {
		ApplicationSoap soapModel = new ApplicationSoap();

		soapModel.setApplicationId(model.getApplicationId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setUrl(model.getUrl());
		soapModel.setConfiguration(model.getConfiguration());
		soapModel.setRoles(model.getRoles());
		soapModel.setAlias(model.getAlias());
		soapModel.setKeycloak_client(model.getKeycloak_client());
		soapModel.setKeycloak_secret(model.getKeycloak_secret());
		soapModel.setLogo(model.getLogo());
		soapModel.setOrder(model.getOrder());
		soapModel.setLimitAdmins(model.isLimitAdmins());
		soapModel.setLimitUsers(model.isLimitUsers());
		soapModel.setLimitNumAdmins(model.getLimitNumAdmins());
		soapModel.setLimitNumUsers(model.getLimitNumUsers());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static ApplicationSoap[] toSoapModels(Application[] models) {
		ApplicationSoap[] soapModels = new ApplicationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ApplicationSoap[][] toSoapModels(Application[][] models) {
		ApplicationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ApplicationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ApplicationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ApplicationSoap[] toSoapModels(List<Application> models) {
		List<ApplicationSoap> soapModels = new ArrayList<ApplicationSoap>(
			models.size());

		for (Application model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ApplicationSoap[soapModels.size()]);
	}

	public ApplicationSoap() {
	}

	public long getPrimaryKey() {
		return _applicationId;
	}

	public void setPrimaryKey(long pk) {
		setApplicationId(pk);
	}

	public long getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(long applicationId) {
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

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public String getConfiguration() {
		return _configuration;
	}

	public void setConfiguration(String configuration) {
		_configuration = configuration;
	}

	public String getRoles() {
		return _roles;
	}

	public void setRoles(String roles) {
		_roles = roles;
	}

	public String getAlias() {
		return _alias;
	}

	public void setAlias(String alias) {
		_alias = alias;
	}

	public String getKeycloak_client() {
		return _keycloak_client;
	}

	public void setKeycloak_client(String keycloak_client) {
		_keycloak_client = keycloak_client;
	}

	public String getKeycloak_secret() {
		return _keycloak_secret;
	}

	public void setKeycloak_secret(String keycloak_secret) {
		_keycloak_secret = keycloak_secret;
	}

	public long getLogo() {
		return _logo;
	}

	public void setLogo(long logo) {
		_logo = logo;
	}

	public Integer getOrder() {
		return _order;
	}

	public void setOrder(Integer order) {
		_order = order;
	}

	public boolean getLimitAdmins() {
		return _limitAdmins;
	}

	public boolean isLimitAdmins() {
		return _limitAdmins;
	}

	public void setLimitAdmins(boolean limitAdmins) {
		_limitAdmins = limitAdmins;
	}

	public boolean getLimitUsers() {
		return _limitUsers;
	}

	public boolean isLimitUsers() {
		return _limitUsers;
	}

	public void setLimitUsers(boolean limitUsers) {
		_limitUsers = limitUsers;
	}

	public long getLimitNumAdmins() {
		return _limitNumAdmins;
	}

	public void setLimitNumAdmins(long limitNumAdmins) {
		_limitNumAdmins = limitNumAdmins;
	}

	public long getLimitNumUsers() {
		return _limitNumUsers;
	}

	public void setLimitNumUsers(long limitNumUsers) {
		_limitNumUsers = limitNumUsers;
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

	private long _applicationId;
	private String _name;
	private String _description;
	private String _url;
	private String _configuration;
	private String _roles;
	private String _alias;
	private String _keycloak_client;
	private String _keycloak_secret;
	private long _logo;
	private Integer _order;
	private boolean _limitAdmins;
	private boolean _limitUsers;
	private long _limitNumAdmins;
	private long _limitNumUsers;
	private Date _createDate;
	private Date _modifiedDate;

}