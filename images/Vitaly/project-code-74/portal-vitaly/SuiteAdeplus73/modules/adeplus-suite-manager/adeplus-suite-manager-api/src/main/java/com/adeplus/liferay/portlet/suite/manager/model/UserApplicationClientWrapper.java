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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserApplicationClient}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserApplicationClient
 * @generated
 */
public class UserApplicationClientWrapper
	extends BaseModelWrapper<UserApplicationClient>
	implements ModelWrapper<UserApplicationClient>, UserApplicationClient {

	public UserApplicationClientWrapper(
		UserApplicationClient userApplicationClient) {

		super(userApplicationClient);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("empresaId", getEmpresaId());
		attributes.put("userId", getUserId());
		attributes.put("compId", getCompId());
		attributes.put("clientId", getClientId());
		attributes.put("contractId", getContractId());
		attributes.put("applicationId", getApplicationId());
		attributes.put("admin", isAdmin());
		attributes.put("createDate", getCreateDate());
		attributes.put("deleteDate", getDeleteDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long empresaId = (Long)attributes.get("empresaId");

		if (empresaId != null) {
			setEmpresaId(empresaId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		Long clientId = (Long)attributes.get("clientId");

		if (clientId != null) {
			setClientId(clientId);
		}

		Long contractId = (Long)attributes.get("contractId");

		if (contractId != null) {
			setContractId(contractId);
		}

		String applicationId = (String)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}

		Boolean admin = (Boolean)attributes.get("admin");

		if (admin != null) {
			setAdmin(admin);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date deleteDate = (Date)attributes.get("deleteDate");

		if (deleteDate != null) {
			setDeleteDate(deleteDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	/**
	 * Returns the admin of this user application client.
	 *
	 * @return the admin of this user application client
	 */
	@Override
	public boolean getAdmin() {
		return model.getAdmin();
	}

	/**
	 * Returns the application ID of this user application client.
	 *
	 * @return the application ID of this user application client
	 */
	@Override
	public String getApplicationId() {
		return model.getApplicationId();
	}

	/**
	 * Returns the client ID of this user application client.
	 *
	 * @return the client ID of this user application client
	 */
	@Override
	public long getClientId() {
		return model.getClientId();
	}

	/**
	 * Returns the comp ID of this user application client.
	 *
	 * @return the comp ID of this user application client
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the contract ID of this user application client.
	 *
	 * @return the contract ID of this user application client
	 */
	@Override
	public long getContractId() {
		return model.getContractId();
	}

	/**
	 * Returns the create date of this user application client.
	 *
	 * @return the create date of this user application client
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the delete date of this user application client.
	 *
	 * @return the delete date of this user application client
	 */
	@Override
	public Date getDeleteDate() {
		return model.getDeleteDate();
	}

	/**
	 * Returns the empresa ID of this user application client.
	 *
	 * @return the empresa ID of this user application client
	 */
	@Override
	public long getEmpresaId() {
		return model.getEmpresaId();
	}

	/**
	 * Returns the modified date of this user application client.
	 *
	 * @return the modified date of this user application client
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this user application client.
	 *
	 * @return the primary key of this user application client
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.service.persistence.
		UserApplicationClientPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this user application client.
	 *
	 * @return the user ID of this user application client
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this user application client.
	 *
	 * @return the user uuid of this user application client
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this user application client is admin.
	 *
	 * @return <code>true</code> if this user application client is admin; <code>false</code> otherwise
	 */
	@Override
	public boolean isAdmin() {
		return model.isAdmin();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this user application client is admin.
	 *
	 * @param admin the admin of this user application client
	 */
	@Override
	public void setAdmin(boolean admin) {
		model.setAdmin(admin);
	}

	/**
	 * Sets the application ID of this user application client.
	 *
	 * @param applicationId the application ID of this user application client
	 */
	@Override
	public void setApplicationId(String applicationId) {
		model.setApplicationId(applicationId);
	}

	/**
	 * Sets the client ID of this user application client.
	 *
	 * @param clientId the client ID of this user application client
	 */
	@Override
	public void setClientId(long clientId) {
		model.setClientId(clientId);
	}

	/**
	 * Sets the comp ID of this user application client.
	 *
	 * @param compId the comp ID of this user application client
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the contract ID of this user application client.
	 *
	 * @param contractId the contract ID of this user application client
	 */
	@Override
	public void setContractId(long contractId) {
		model.setContractId(contractId);
	}

	/**
	 * Sets the create date of this user application client.
	 *
	 * @param createDate the create date of this user application client
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the delete date of this user application client.
	 *
	 * @param deleteDate the delete date of this user application client
	 */
	@Override
	public void setDeleteDate(Date deleteDate) {
		model.setDeleteDate(deleteDate);
	}

	/**
	 * Sets the empresa ID of this user application client.
	 *
	 * @param empresaId the empresa ID of this user application client
	 */
	@Override
	public void setEmpresaId(long empresaId) {
		model.setEmpresaId(empresaId);
	}

	/**
	 * Sets the modified date of this user application client.
	 *
	 * @param modifiedDate the modified date of this user application client
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this user application client.
	 *
	 * @param primaryKey the primary key of this user application client
	 */
	@Override
	public void setPrimaryKey(
		com.adeplus.liferay.portlet.suite.manager.service.persistence.
			UserApplicationClientPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this user application client.
	 *
	 * @param userId the user ID of this user application client
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this user application client.
	 *
	 * @param userUuid the user uuid of this user application client
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected UserApplicationClientWrapper wrap(
		UserApplicationClient userApplicationClient) {

		return new UserApplicationClientWrapper(userApplicationClient);
	}

}