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
 * This class is a wrapper for {@link Application}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Application
 * @generated
 */
public class ApplicationWrapper
	extends BaseModelWrapper<Application>
	implements Application, ModelWrapper<Application> {

	public ApplicationWrapper(Application application) {
		super(application);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("applicationId", getApplicationId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("url", getUrl());
		attributes.put("configuration", getConfiguration());
		attributes.put("roles", getRoles());
		attributes.put("alias", getAlias());
		attributes.put("keycloak_client", getKeycloak_client());
		attributes.put("keycloak_secret", getKeycloak_secret());
		attributes.put("logo", getLogo());
		attributes.put("order", getOrder());
		attributes.put("limitAdmins", isLimitAdmins());
		attributes.put("limitUsers", isLimitUsers());
		attributes.put("limitNumAdmins", getLimitNumAdmins());
		attributes.put("limitNumUsers", getLimitNumUsers());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long applicationId = (Long)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String configuration = (String)attributes.get("configuration");

		if (configuration != null) {
			setConfiguration(configuration);
		}

		String roles = (String)attributes.get("roles");

		if (roles != null) {
			setRoles(roles);
		}

		String alias = (String)attributes.get("alias");

		if (alias != null) {
			setAlias(alias);
		}

		String keycloak_client = (String)attributes.get("keycloak_client");

		if (keycloak_client != null) {
			setKeycloak_client(keycloak_client);
		}

		String keycloak_secret = (String)attributes.get("keycloak_secret");

		if (keycloak_secret != null) {
			setKeycloak_secret(keycloak_secret);
		}

		Long logo = (Long)attributes.get("logo");

		if (logo != null) {
			setLogo(logo);
		}

		Integer order = (Integer)attributes.get("order");

		if (order != null) {
			setOrder(order);
		}

		Boolean limitAdmins = (Boolean)attributes.get("limitAdmins");

		if (limitAdmins != null) {
			setLimitAdmins(limitAdmins);
		}

		Boolean limitUsers = (Boolean)attributes.get("limitUsers");

		if (limitUsers != null) {
			setLimitUsers(limitUsers);
		}

		Long limitNumAdmins = (Long)attributes.get("limitNumAdmins");

		if (limitNumAdmins != null) {
			setLimitNumAdmins(limitNumAdmins);
		}

		Long limitNumUsers = (Long)attributes.get("limitNumUsers");

		if (limitNumUsers != null) {
			setLimitNumUsers(limitNumUsers);
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
	 * Returns the alias of this application.
	 *
	 * @return the alias of this application
	 */
	@Override
	public String getAlias() {
		return model.getAlias();
	}

	/**
	 * Returns the application ID of this application.
	 *
	 * @return the application ID of this application
	 */
	@Override
	public long getApplicationId() {
		return model.getApplicationId();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return model.getAvailableLanguageIds();
	}

	/**
	 * Returns the configuration of this application.
	 *
	 * @return the configuration of this application
	 */
	@Override
	public String getConfiguration() {
		return model.getConfiguration();
	}

	/**
	 * Returns the create date of this application.
	 *
	 * @return the create date of this application
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return model.getDefaultLanguageId();
	}

	/**
	 * Returns the description of this application.
	 *
	 * @return the description of this application
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the localized description of this application in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this application
	 */
	@Override
	public String getDescription(java.util.Locale locale) {
		return model.getDescription(locale);
	}

	/**
	 * Returns the localized description of this application in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this application. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getDescription(java.util.Locale locale, boolean useDefault) {
		return model.getDescription(locale, useDefault);
	}

	/**
	 * Returns the localized description of this application in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this application
	 */
	@Override
	public String getDescription(String languageId) {
		return model.getDescription(languageId);
	}

	/**
	 * Returns the localized description of this application in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this application
	 */
	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return model.getDescription(languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return model.getDescriptionCurrentLanguageId();
	}

	@Override
	public String getDescriptionCurrentValue() {
		return model.getDescriptionCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized descriptions of this application.
	 *
	 * @return the locales and localized descriptions of this application
	 */
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return model.getDescriptionMap();
	}

	/**
	 * Returns the keycloak_client of this application.
	 *
	 * @return the keycloak_client of this application
	 */
	@Override
	public String getKeycloak_client() {
		return model.getKeycloak_client();
	}

	/**
	 * Returns the keycloak_secret of this application.
	 *
	 * @return the keycloak_secret of this application
	 */
	@Override
	public String getKeycloak_secret() {
		return model.getKeycloak_secret();
	}

	/**
	 * Returns the limit admins of this application.
	 *
	 * @return the limit admins of this application
	 */
	@Override
	public boolean getLimitAdmins() {
		return model.getLimitAdmins();
	}

	/**
	 * Returns the limit num admins of this application.
	 *
	 * @return the limit num admins of this application
	 */
	@Override
	public long getLimitNumAdmins() {
		return model.getLimitNumAdmins();
	}

	/**
	 * Returns the limit num users of this application.
	 *
	 * @return the limit num users of this application
	 */
	@Override
	public long getLimitNumUsers() {
		return model.getLimitNumUsers();
	}

	/**
	 * Returns the limit users of this application.
	 *
	 * @return the limit users of this application
	 */
	@Override
	public boolean getLimitUsers() {
		return model.getLimitUsers();
	}

	/**
	 * Returns the logo of this application.
	 *
	 * @return the logo of this application
	 */
	@Override
	public long getLogo() {
		return model.getLogo();
	}

	/**
	 * Returns the modified date of this application.
	 *
	 * @return the modified date of this application
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this application.
	 *
	 * @return the name of this application
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the order of this application.
	 *
	 * @return the order of this application
	 */
	@Override
	public Integer getOrder() {
		return model.getOrder();
	}

	/**
	 * Returns the primary key of this application.
	 *
	 * @return the primary key of this application
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the roles of this application.
	 *
	 * @return the roles of this application
	 */
	@Override
	public String getRoles() {
		return model.getRoles();
	}

	/**
	 * Returns the url of this application.
	 *
	 * @return the url of this application
	 */
	@Override
	public String getUrl() {
		return model.getUrl();
	}

	/**
	 * Returns <code>true</code> if this application is limit admins.
	 *
	 * @return <code>true</code> if this application is limit admins; <code>false</code> otherwise
	 */
	@Override
	public boolean isLimitAdmins() {
		return model.isLimitAdmins();
	}

	/**
	 * Returns <code>true</code> if this application is limit users.
	 *
	 * @return <code>true</code> if this application is limit users; <code>false</code> otherwise
	 */
	@Override
	public boolean isLimitUsers() {
		return model.isLimitUsers();
	}

	@Override
	public void persist() {
		model.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		model.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		model.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	 * Sets the alias of this application.
	 *
	 * @param alias the alias of this application
	 */
	@Override
	public void setAlias(String alias) {
		model.setAlias(alias);
	}

	/**
	 * Sets the application ID of this application.
	 *
	 * @param applicationId the application ID of this application
	 */
	@Override
	public void setApplicationId(long applicationId) {
		model.setApplicationId(applicationId);
	}

	/**
	 * Sets the configuration of this application.
	 *
	 * @param configuration the configuration of this application
	 */
	@Override
	public void setConfiguration(String configuration) {
		model.setConfiguration(configuration);
	}

	/**
	 * Sets the create date of this application.
	 *
	 * @param createDate the create date of this application
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this application.
	 *
	 * @param description the description of this application
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the localized description of this application in the language.
	 *
	 * @param description the localized description of this application
	 * @param locale the locale of the language
	 */
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		model.setDescription(description, locale);
	}

	/**
	 * Sets the localized description of this application in the language, and sets the default locale.
	 *
	 * @param description the localized description of this application
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescription(
		String description, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		model.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized descriptions of this application from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this application
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap) {

		model.setDescriptionMap(descriptionMap);
	}

	/**
	 * Sets the localized descriptions of this application from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this application
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap,
		java.util.Locale defaultLocale) {

		model.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	 * Sets the keycloak_client of this application.
	 *
	 * @param keycloak_client the keycloak_client of this application
	 */
	@Override
	public void setKeycloak_client(String keycloak_client) {
		model.setKeycloak_client(keycloak_client);
	}

	/**
	 * Sets the keycloak_secret of this application.
	 *
	 * @param keycloak_secret the keycloak_secret of this application
	 */
	@Override
	public void setKeycloak_secret(String keycloak_secret) {
		model.setKeycloak_secret(keycloak_secret);
	}

	/**
	 * Sets whether this application is limit admins.
	 *
	 * @param limitAdmins the limit admins of this application
	 */
	@Override
	public void setLimitAdmins(boolean limitAdmins) {
		model.setLimitAdmins(limitAdmins);
	}

	/**
	 * Sets the limit num admins of this application.
	 *
	 * @param limitNumAdmins the limit num admins of this application
	 */
	@Override
	public void setLimitNumAdmins(long limitNumAdmins) {
		model.setLimitNumAdmins(limitNumAdmins);
	}

	/**
	 * Sets the limit num users of this application.
	 *
	 * @param limitNumUsers the limit num users of this application
	 */
	@Override
	public void setLimitNumUsers(long limitNumUsers) {
		model.setLimitNumUsers(limitNumUsers);
	}

	/**
	 * Sets whether this application is limit users.
	 *
	 * @param limitUsers the limit users of this application
	 */
	@Override
	public void setLimitUsers(boolean limitUsers) {
		model.setLimitUsers(limitUsers);
	}

	/**
	 * Sets the logo of this application.
	 *
	 * @param logo the logo of this application
	 */
	@Override
	public void setLogo(long logo) {
		model.setLogo(logo);
	}

	/**
	 * Sets the modified date of this application.
	 *
	 * @param modifiedDate the modified date of this application
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this application.
	 *
	 * @param name the name of this application
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the order of this application.
	 *
	 * @param order the order of this application
	 */
	@Override
	public void setOrder(Integer order) {
		model.setOrder(order);
	}

	/**
	 * Sets the primary key of this application.
	 *
	 * @param primaryKey the primary key of this application
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the roles of this application.
	 *
	 * @param roles the roles of this application
	 */
	@Override
	public void setRoles(String roles) {
		model.setRoles(roles);
	}

	/**
	 * Sets the url of this application.
	 *
	 * @param url the url of this application
	 */
	@Override
	public void setUrl(String url) {
		model.setUrl(url);
	}

	@Override
	protected ApplicationWrapper wrap(Application application) {
		return new ApplicationWrapper(application);
	}

}