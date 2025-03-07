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
 * This class is a wrapper for {@link CompClientApplication}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CompClientApplication
 * @generated
 */
public class CompClientApplicationWrapper
	extends BaseModelWrapper<CompClientApplication>
	implements CompClientApplication, ModelWrapper<CompClientApplication> {

	public CompClientApplicationWrapper(
		CompClientApplication compClientApplication) {

		super(compClientApplication);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("empresaId", getEmpresaId());
		attributes.put("cif", getCif());
		attributes.put("compId", getCompId());
		attributes.put("clientId", getClientId());
		attributes.put("contractId", getContractId());
		attributes.put("applicationId", getApplicationId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("observations", getObservations());
		attributes.put("idEstado", getIdEstado());
		attributes.put("logo", getLogo());
		attributes.put("startDate", getStartDate());
		attributes.put("deleteDate", getDeleteDate());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long empresaId = (Long)attributes.get("empresaId");

		if (empresaId != null) {
			setEmpresaId(empresaId);
		}

		String cif = (String)attributes.get("cif");

		if (cif != null) {
			setCif(cif);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String observations = (String)attributes.get("observations");

		if (observations != null) {
			setObservations(observations);
		}

		String idEstado = (String)attributes.get("idEstado");

		if (idEstado != null) {
			setIdEstado(idEstado);
		}

		Long logo = (Long)attributes.get("logo");

		if (logo != null) {
			setLogo(logo);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date deleteDate = (Date)attributes.get("deleteDate");

		if (deleteDate != null) {
			setDeleteDate(deleteDate);
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
	 * Returns the application ID of this comp client application.
	 *
	 * @return the application ID of this comp client application
	 */
	@Override
	public String getApplicationId() {
		return model.getApplicationId();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return model.getAvailableLanguageIds();
	}

	/**
	 * Returns the cif of this comp client application.
	 *
	 * @return the cif of this comp client application
	 */
	@Override
	public String getCif() {
		return model.getCif();
	}

	/**
	 * Returns the client ID of this comp client application.
	 *
	 * @return the client ID of this comp client application
	 */
	@Override
	public long getClientId() {
		return model.getClientId();
	}

	/**
	 * Returns the comp ID of this comp client application.
	 *
	 * @return the comp ID of this comp client application
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the contract ID of this comp client application.
	 *
	 * @return the contract ID of this comp client application
	 */
	@Override
	public long getContractId() {
		return model.getContractId();
	}

	/**
	 * Returns the create date of this comp client application.
	 *
	 * @return the create date of this comp client application
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
	 * Returns the delete date of this comp client application.
	 *
	 * @return the delete date of this comp client application
	 */
	@Override
	public Date getDeleteDate() {
		return model.getDeleteDate();
	}

	/**
	 * Returns the description of this comp client application.
	 *
	 * @return the description of this comp client application
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the empresa ID of this comp client application.
	 *
	 * @return the empresa ID of this comp client application
	 */
	@Override
	public long getEmpresaId() {
		return model.getEmpresaId();
	}

	/**
	 * Returns the id estado of this comp client application.
	 *
	 * @return the id estado of this comp client application
	 */
	@Override
	public String getIdEstado() {
		return model.getIdEstado();
	}

	/**
	 * Returns the logo of this comp client application.
	 *
	 * @return the logo of this comp client application
	 */
	@Override
	public long getLogo() {
		return model.getLogo();
	}

	/**
	 * Returns the modified date of this comp client application.
	 *
	 * @return the modified date of this comp client application
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this comp client application.
	 *
	 * @return the name of this comp client application
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the observations of this comp client application.
	 *
	 * @return the observations of this comp client application
	 */
	@Override
	public String getObservations() {
		return model.getObservations();
	}

	/**
	 * Returns the localized observations of this comp client application in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized observations of this comp client application
	 */
	@Override
	public String getObservations(java.util.Locale locale) {
		return model.getObservations(locale);
	}

	/**
	 * Returns the localized observations of this comp client application in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized observations of this comp client application. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getObservations(java.util.Locale locale, boolean useDefault) {
		return model.getObservations(locale, useDefault);
	}

	/**
	 * Returns the localized observations of this comp client application in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized observations of this comp client application
	 */
	@Override
	public String getObservations(String languageId) {
		return model.getObservations(languageId);
	}

	/**
	 * Returns the localized observations of this comp client application in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized observations of this comp client application
	 */
	@Override
	public String getObservations(String languageId, boolean useDefault) {
		return model.getObservations(languageId, useDefault);
	}

	@Override
	public String getObservationsCurrentLanguageId() {
		return model.getObservationsCurrentLanguageId();
	}

	@Override
	public String getObservationsCurrentValue() {
		return model.getObservationsCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized observationses of this comp client application.
	 *
	 * @return the locales and localized observationses of this comp client application
	 */
	@Override
	public Map<java.util.Locale, String> getObservationsMap() {
		return model.getObservationsMap();
	}

	/**
	 * Returns the primary key of this comp client application.
	 *
	 * @return the primary key of this comp client application
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the start date of this comp client application.
	 *
	 * @return the start date of this comp client application
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
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
	 * Sets the application ID of this comp client application.
	 *
	 * @param applicationId the application ID of this comp client application
	 */
	@Override
	public void setApplicationId(String applicationId) {
		model.setApplicationId(applicationId);
	}

	/**
	 * Sets the cif of this comp client application.
	 *
	 * @param cif the cif of this comp client application
	 */
	@Override
	public void setCif(String cif) {
		model.setCif(cif);
	}

	/**
	 * Sets the client ID of this comp client application.
	 *
	 * @param clientId the client ID of this comp client application
	 */
	@Override
	public void setClientId(long clientId) {
		model.setClientId(clientId);
	}

	/**
	 * Sets the comp ID of this comp client application.
	 *
	 * @param compId the comp ID of this comp client application
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the contract ID of this comp client application.
	 *
	 * @param contractId the contract ID of this comp client application
	 */
	@Override
	public void setContractId(long contractId) {
		model.setContractId(contractId);
	}

	/**
	 * Sets the create date of this comp client application.
	 *
	 * @param createDate the create date of this comp client application
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the delete date of this comp client application.
	 *
	 * @param deleteDate the delete date of this comp client application
	 */
	@Override
	public void setDeleteDate(Date deleteDate) {
		model.setDeleteDate(deleteDate);
	}

	/**
	 * Sets the description of this comp client application.
	 *
	 * @param description the description of this comp client application
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the empresa ID of this comp client application.
	 *
	 * @param empresaId the empresa ID of this comp client application
	 */
	@Override
	public void setEmpresaId(long empresaId) {
		model.setEmpresaId(empresaId);
	}

	/**
	 * Sets the id estado of this comp client application.
	 *
	 * @param idEstado the id estado of this comp client application
	 */
	@Override
	public void setIdEstado(String idEstado) {
		model.setIdEstado(idEstado);
	}

	/**
	 * Sets the logo of this comp client application.
	 *
	 * @param logo the logo of this comp client application
	 */
	@Override
	public void setLogo(long logo) {
		model.setLogo(logo);
	}

	/**
	 * Sets the modified date of this comp client application.
	 *
	 * @param modifiedDate the modified date of this comp client application
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this comp client application.
	 *
	 * @param name the name of this comp client application
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the observations of this comp client application.
	 *
	 * @param observations the observations of this comp client application
	 */
	@Override
	public void setObservations(String observations) {
		model.setObservations(observations);
	}

	/**
	 * Sets the localized observations of this comp client application in the language.
	 *
	 * @param observations the localized observations of this comp client application
	 * @param locale the locale of the language
	 */
	@Override
	public void setObservations(String observations, java.util.Locale locale) {
		model.setObservations(observations, locale);
	}

	/**
	 * Sets the localized observations of this comp client application in the language, and sets the default locale.
	 *
	 * @param observations the localized observations of this comp client application
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setObservations(
		String observations, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setObservations(observations, locale, defaultLocale);
	}

	@Override
	public void setObservationsCurrentLanguageId(String languageId) {
		model.setObservationsCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized observationses of this comp client application from the map of locales and localized observationses.
	 *
	 * @param observationsMap the locales and localized observationses of this comp client application
	 */
	@Override
	public void setObservationsMap(
		Map<java.util.Locale, String> observationsMap) {

		model.setObservationsMap(observationsMap);
	}

	/**
	 * Sets the localized observationses of this comp client application from the map of locales and localized observationses, and sets the default locale.
	 *
	 * @param observationsMap the locales and localized observationses of this comp client application
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setObservationsMap(
		Map<java.util.Locale, String> observationsMap,
		java.util.Locale defaultLocale) {

		model.setObservationsMap(observationsMap, defaultLocale);
	}

	/**
	 * Sets the primary key of this comp client application.
	 *
	 * @param primaryKey the primary key of this comp client application
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the start date of this comp client application.
	 *
	 * @param startDate the start date of this comp client application
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	@Override
	protected CompClientApplicationWrapper wrap(
		CompClientApplication compClientApplication) {

		return new CompClientApplicationWrapper(compClientApplication);
	}

}