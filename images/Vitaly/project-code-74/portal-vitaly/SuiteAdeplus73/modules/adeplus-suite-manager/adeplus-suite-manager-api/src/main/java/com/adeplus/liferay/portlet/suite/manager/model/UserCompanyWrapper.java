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
 * This class is a wrapper for {@link UserCompany}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserCompany
 * @generated
 */
public class UserCompanyWrapper
	extends BaseModelWrapper<UserCompany>
	implements ModelWrapper<UserCompany>, UserCompany {

	public UserCompanyWrapper(UserCompany userCompany) {
		super(userCompany);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userId", getUserId());
		attributes.put("compId", getCompId());
		attributes.put("name", getName());
		attributes.put("lastName", getLastName());
		attributes.put("nif", getNif());
		attributes.put("email", getEmail());
		attributes.put("phone", getPhone());
		attributes.put("admin", isAdmin());
		attributes.put("defaultUserComp", isDefaultUserComp());
		attributes.put("defaultClientId", getDefaultClientId());
		attributes.put("defaultContractId", getDefaultContractId());
		attributes.put("defaultEmpresaId", getDefaultEmpresaId());
		attributes.put("userEndDate", getUserEndDate());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String lastName = (String)attributes.get("lastName");

		if (lastName != null) {
			setLastName(lastName);
		}

		String nif = (String)attributes.get("nif");

		if (nif != null) {
			setNif(nif);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String phone = (String)attributes.get("phone");

		if (phone != null) {
			setPhone(phone);
		}

		Boolean admin = (Boolean)attributes.get("admin");

		if (admin != null) {
			setAdmin(admin);
		}

		Boolean defaultUserComp = (Boolean)attributes.get("defaultUserComp");

		if (defaultUserComp != null) {
			setDefaultUserComp(defaultUserComp);
		}

		Long defaultClientId = (Long)attributes.get("defaultClientId");

		if (defaultClientId != null) {
			setDefaultClientId(defaultClientId);
		}

		Long defaultContractId = (Long)attributes.get("defaultContractId");

		if (defaultContractId != null) {
			setDefaultContractId(defaultContractId);
		}

		Long defaultEmpresaId = (Long)attributes.get("defaultEmpresaId");

		if (defaultEmpresaId != null) {
			setDefaultEmpresaId(defaultEmpresaId);
		}

		Date userEndDate = (Date)attributes.get("userEndDate");

		if (userEndDate != null) {
			setUserEndDate(userEndDate);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	/**
	 * Returns the admin of this user company.
	 *
	 * @return the admin of this user company
	 */
	@Override
	public boolean getAdmin() {
		return model.getAdmin();
	}

	/**
	 * Returns the comp ID of this user company.
	 *
	 * @return the comp ID of this user company
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the create date of this user company.
	 *
	 * @return the create date of this user company
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the default client ID of this user company.
	 *
	 * @return the default client ID of this user company
	 */
	@Override
	public long getDefaultClientId() {
		return model.getDefaultClientId();
	}

	/**
	 * Returns the default contract ID of this user company.
	 *
	 * @return the default contract ID of this user company
	 */
	@Override
	public long getDefaultContractId() {
		return model.getDefaultContractId();
	}

	/**
	 * Returns the default empresa ID of this user company.
	 *
	 * @return the default empresa ID of this user company
	 */
	@Override
	public long getDefaultEmpresaId() {
		return model.getDefaultEmpresaId();
	}

	/**
	 * Returns the default user comp of this user company.
	 *
	 * @return the default user comp of this user company
	 */
	@Override
	public boolean getDefaultUserComp() {
		return model.getDefaultUserComp();
	}

	/**
	 * Returns the email of this user company.
	 *
	 * @return the email of this user company
	 */
	@Override
	public String getEmail() {
		return model.getEmail();
	}

	/**
	 * Returns the last name of this user company.
	 *
	 * @return the last name of this user company
	 */
	@Override
	public String getLastName() {
		return model.getLastName();
	}

	/**
	 * Returns the modified date of this user company.
	 *
	 * @return the modified date of this user company
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this user company.
	 *
	 * @return the name of this user company
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the nif of this user company.
	 *
	 * @return the nif of this user company
	 */
	@Override
	public String getNif() {
		return model.getNif();
	}

	/**
	 * Returns the phone of this user company.
	 *
	 * @return the phone of this user company
	 */
	@Override
	public String getPhone() {
		return model.getPhone();
	}

	/**
	 * Returns the primary key of this user company.
	 *
	 * @return the primary key of this user company
	 */
	@Override
	public
		com.adeplus.liferay.portlet.suite.manager.service.persistence.
			UserCompanyPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the user end date of this user company.
	 *
	 * @return the user end date of this user company
	 */
	@Override
	public Date getUserEndDate() {
		return model.getUserEndDate();
	}

	/**
	 * Returns the user ID of this user company.
	 *
	 * @return the user ID of this user company
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this user company.
	 *
	 * @return the user uuid of this user company
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this user company is admin.
	 *
	 * @return <code>true</code> if this user company is admin; <code>false</code> otherwise
	 */
	@Override
	public boolean isAdmin() {
		return model.isAdmin();
	}

	/**
	 * Returns <code>true</code> if this user company is default user comp.
	 *
	 * @return <code>true</code> if this user company is default user comp; <code>false</code> otherwise
	 */
	@Override
	public boolean isDefaultUserComp() {
		return model.isDefaultUserComp();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this user company is admin.
	 *
	 * @param admin the admin of this user company
	 */
	@Override
	public void setAdmin(boolean admin) {
		model.setAdmin(admin);
	}

	/**
	 * Sets the comp ID of this user company.
	 *
	 * @param compId the comp ID of this user company
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the create date of this user company.
	 *
	 * @param createDate the create date of this user company
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the default client ID of this user company.
	 *
	 * @param defaultClientId the default client ID of this user company
	 */
	@Override
	public void setDefaultClientId(long defaultClientId) {
		model.setDefaultClientId(defaultClientId);
	}

	/**
	 * Sets the default contract ID of this user company.
	 *
	 * @param defaultContractId the default contract ID of this user company
	 */
	@Override
	public void setDefaultContractId(long defaultContractId) {
		model.setDefaultContractId(defaultContractId);
	}

	/**
	 * Sets the default empresa ID of this user company.
	 *
	 * @param defaultEmpresaId the default empresa ID of this user company
	 */
	@Override
	public void setDefaultEmpresaId(long defaultEmpresaId) {
		model.setDefaultEmpresaId(defaultEmpresaId);
	}

	/**
	 * Sets whether this user company is default user comp.
	 *
	 * @param defaultUserComp the default user comp of this user company
	 */
	@Override
	public void setDefaultUserComp(boolean defaultUserComp) {
		model.setDefaultUserComp(defaultUserComp);
	}

	/**
	 * Sets the email of this user company.
	 *
	 * @param email the email of this user company
	 */
	@Override
	public void setEmail(String email) {
		model.setEmail(email);
	}

	/**
	 * Sets the last name of this user company.
	 *
	 * @param lastName the last name of this user company
	 */
	@Override
	public void setLastName(String lastName) {
		model.setLastName(lastName);
	}

	/**
	 * Sets the modified date of this user company.
	 *
	 * @param modifiedDate the modified date of this user company
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this user company.
	 *
	 * @param name the name of this user company
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the nif of this user company.
	 *
	 * @param nif the nif of this user company
	 */
	@Override
	public void setNif(String nif) {
		model.setNif(nif);
	}

	/**
	 * Sets the phone of this user company.
	 *
	 * @param phone the phone of this user company
	 */
	@Override
	public void setPhone(String phone) {
		model.setPhone(phone);
	}

	/**
	 * Sets the primary key of this user company.
	 *
	 * @param primaryKey the primary key of this user company
	 */
	@Override
	public void setPrimaryKey(
		com.adeplus.liferay.portlet.suite.manager.service.persistence.
			UserCompanyPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user end date of this user company.
	 *
	 * @param userEndDate the user end date of this user company
	 */
	@Override
	public void setUserEndDate(Date userEndDate) {
		model.setUserEndDate(userEndDate);
	}

	/**
	 * Sets the user ID of this user company.
	 *
	 * @param userId the user ID of this user company
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this user company.
	 *
	 * @param userUuid the user uuid of this user company
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected UserCompanyWrapper wrap(UserCompany userCompany) {
		return new UserCompanyWrapper(userCompany);
	}

}