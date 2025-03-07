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
 * This class is a wrapper for {@link Comp}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Comp
 * @generated
 */
public class CompWrapper
	extends BaseModelWrapper<Comp> implements Comp, ModelWrapper<Comp> {

	public CompWrapper(Comp comp) {
		super(comp);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("compId", getCompId());
		attributes.put("cif", getCif());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("observations", getObservations());
		attributes.put("agreement", getAgreement());
		attributes.put("idCrm", getIdCrm());
		attributes.put("idContrato", getIdContrato());
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
		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		String cif = (String)attributes.get("cif");

		if (cif != null) {
			setCif(cif);
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

		String agreement = (String)attributes.get("agreement");

		if (agreement != null) {
			setAgreement(agreement);
		}

		String idCrm = (String)attributes.get("idCrm");

		if (idCrm != null) {
			setIdCrm(idCrm);
		}

		String idContrato = (String)attributes.get("idContrato");

		if (idContrato != null) {
			setIdContrato(idContrato);
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
	 * Returns the agreement of this comp.
	 *
	 * @return the agreement of this comp
	 */
	@Override
	public String getAgreement() {
		return model.getAgreement();
	}

	/**
	 * Returns the localized agreement of this comp in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized agreement of this comp
	 */
	@Override
	public String getAgreement(java.util.Locale locale) {
		return model.getAgreement(locale);
	}

	/**
	 * Returns the localized agreement of this comp in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized agreement of this comp. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getAgreement(java.util.Locale locale, boolean useDefault) {
		return model.getAgreement(locale, useDefault);
	}

	/**
	 * Returns the localized agreement of this comp in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized agreement of this comp
	 */
	@Override
	public String getAgreement(String languageId) {
		return model.getAgreement(languageId);
	}

	/**
	 * Returns the localized agreement of this comp in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized agreement of this comp
	 */
	@Override
	public String getAgreement(String languageId, boolean useDefault) {
		return model.getAgreement(languageId, useDefault);
	}

	@Override
	public String getAgreementCurrentLanguageId() {
		return model.getAgreementCurrentLanguageId();
	}

	@Override
	public String getAgreementCurrentValue() {
		return model.getAgreementCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized agreements of this comp.
	 *
	 * @return the locales and localized agreements of this comp
	 */
	@Override
	public Map<java.util.Locale, String> getAgreementMap() {
		return model.getAgreementMap();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return model.getAvailableLanguageIds();
	}

	/**
	 * Returns the cif of this comp.
	 *
	 * @return the cif of this comp
	 */
	@Override
	public String getCif() {
		return model.getCif();
	}

	/**
	 * Returns the comp ID of this comp.
	 *
	 * @return the comp ID of this comp
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the create date of this comp.
	 *
	 * @return the create date of this comp
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
	 * Returns the delete date of this comp.
	 *
	 * @return the delete date of this comp
	 */
	@Override
	public Date getDeleteDate() {
		return model.getDeleteDate();
	}

	/**
	 * Returns the description of this comp.
	 *
	 * @return the description of this comp
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the localized description of this comp in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this comp
	 */
	@Override
	public String getDescription(java.util.Locale locale) {
		return model.getDescription(locale);
	}

	/**
	 * Returns the localized description of this comp in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this comp. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getDescription(java.util.Locale locale, boolean useDefault) {
		return model.getDescription(locale, useDefault);
	}

	/**
	 * Returns the localized description of this comp in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this comp
	 */
	@Override
	public String getDescription(String languageId) {
		return model.getDescription(languageId);
	}

	/**
	 * Returns the localized description of this comp in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this comp
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
	 * Returns a map of the locales and localized descriptions of this comp.
	 *
	 * @return the locales and localized descriptions of this comp
	 */
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return model.getDescriptionMap();
	}

	/**
	 * Returns the id contrato of this comp.
	 *
	 * @return the id contrato of this comp
	 */
	@Override
	public String getIdContrato() {
		return model.getIdContrato();
	}

	/**
	 * Returns the id crm of this comp.
	 *
	 * @return the id crm of this comp
	 */
	@Override
	public String getIdCrm() {
		return model.getIdCrm();
	}

	/**
	 * Returns the id estado of this comp.
	 *
	 * @return the id estado of this comp
	 */
	@Override
	public String getIdEstado() {
		return model.getIdEstado();
	}

	/**
	 * Returns the logo of this comp.
	 *
	 * @return the logo of this comp
	 */
	@Override
	public long getLogo() {
		return model.getLogo();
	}

	/**
	 * Returns the modified date of this comp.
	 *
	 * @return the modified date of this comp
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this comp.
	 *
	 * @return the name of this comp
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the observations of this comp.
	 *
	 * @return the observations of this comp
	 */
	@Override
	public String getObservations() {
		return model.getObservations();
	}

	/**
	 * Returns the localized observations of this comp in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized observations of this comp
	 */
	@Override
	public String getObservations(java.util.Locale locale) {
		return model.getObservations(locale);
	}

	/**
	 * Returns the localized observations of this comp in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized observations of this comp. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getObservations(java.util.Locale locale, boolean useDefault) {
		return model.getObservations(locale, useDefault);
	}

	/**
	 * Returns the localized observations of this comp in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized observations of this comp
	 */
	@Override
	public String getObservations(String languageId) {
		return model.getObservations(languageId);
	}

	/**
	 * Returns the localized observations of this comp in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized observations of this comp
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
	 * Returns a map of the locales and localized observationses of this comp.
	 *
	 * @return the locales and localized observationses of this comp
	 */
	@Override
	public Map<java.util.Locale, String> getObservationsMap() {
		return model.getObservationsMap();
	}

	/**
	 * Returns the primary key of this comp.
	 *
	 * @return the primary key of this comp
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the start date of this comp.
	 *
	 * @return the start date of this comp
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
	 * Sets the agreement of this comp.
	 *
	 * @param agreement the agreement of this comp
	 */
	@Override
	public void setAgreement(String agreement) {
		model.setAgreement(agreement);
	}

	/**
	 * Sets the localized agreement of this comp in the language.
	 *
	 * @param agreement the localized agreement of this comp
	 * @param locale the locale of the language
	 */
	@Override
	public void setAgreement(String agreement, java.util.Locale locale) {
		model.setAgreement(agreement, locale);
	}

	/**
	 * Sets the localized agreement of this comp in the language, and sets the default locale.
	 *
	 * @param agreement the localized agreement of this comp
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setAgreement(
		String agreement, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setAgreement(agreement, locale, defaultLocale);
	}

	@Override
	public void setAgreementCurrentLanguageId(String languageId) {
		model.setAgreementCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized agreements of this comp from the map of locales and localized agreements.
	 *
	 * @param agreementMap the locales and localized agreements of this comp
	 */
	@Override
	public void setAgreementMap(Map<java.util.Locale, String> agreementMap) {
		model.setAgreementMap(agreementMap);
	}

	/**
	 * Sets the localized agreements of this comp from the map of locales and localized agreements, and sets the default locale.
	 *
	 * @param agreementMap the locales and localized agreements of this comp
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setAgreementMap(
		Map<java.util.Locale, String> agreementMap,
		java.util.Locale defaultLocale) {

		model.setAgreementMap(agreementMap, defaultLocale);
	}

	/**
	 * Sets the cif of this comp.
	 *
	 * @param cif the cif of this comp
	 */
	@Override
	public void setCif(String cif) {
		model.setCif(cif);
	}

	/**
	 * Sets the comp ID of this comp.
	 *
	 * @param compId the comp ID of this comp
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the create date of this comp.
	 *
	 * @param createDate the create date of this comp
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the delete date of this comp.
	 *
	 * @param deleteDate the delete date of this comp
	 */
	@Override
	public void setDeleteDate(Date deleteDate) {
		model.setDeleteDate(deleteDate);
	}

	/**
	 * Sets the description of this comp.
	 *
	 * @param description the description of this comp
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the localized description of this comp in the language.
	 *
	 * @param description the localized description of this comp
	 * @param locale the locale of the language
	 */
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		model.setDescription(description, locale);
	}

	/**
	 * Sets the localized description of this comp in the language, and sets the default locale.
	 *
	 * @param description the localized description of this comp
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
	 * Sets the localized descriptions of this comp from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this comp
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap) {

		model.setDescriptionMap(descriptionMap);
	}

	/**
	 * Sets the localized descriptions of this comp from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this comp
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap,
		java.util.Locale defaultLocale) {

		model.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	 * Sets the id contrato of this comp.
	 *
	 * @param idContrato the id contrato of this comp
	 */
	@Override
	public void setIdContrato(String idContrato) {
		model.setIdContrato(idContrato);
	}

	/**
	 * Sets the id crm of this comp.
	 *
	 * @param idCrm the id crm of this comp
	 */
	@Override
	public void setIdCrm(String idCrm) {
		model.setIdCrm(idCrm);
	}

	/**
	 * Sets the id estado of this comp.
	 *
	 * @param idEstado the id estado of this comp
	 */
	@Override
	public void setIdEstado(String idEstado) {
		model.setIdEstado(idEstado);
	}

	/**
	 * Sets the logo of this comp.
	 *
	 * @param logo the logo of this comp
	 */
	@Override
	public void setLogo(long logo) {
		model.setLogo(logo);
	}

	/**
	 * Sets the modified date of this comp.
	 *
	 * @param modifiedDate the modified date of this comp
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this comp.
	 *
	 * @param name the name of this comp
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the observations of this comp.
	 *
	 * @param observations the observations of this comp
	 */
	@Override
	public void setObservations(String observations) {
		model.setObservations(observations);
	}

	/**
	 * Sets the localized observations of this comp in the language.
	 *
	 * @param observations the localized observations of this comp
	 * @param locale the locale of the language
	 */
	@Override
	public void setObservations(String observations, java.util.Locale locale) {
		model.setObservations(observations, locale);
	}

	/**
	 * Sets the localized observations of this comp in the language, and sets the default locale.
	 *
	 * @param observations the localized observations of this comp
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
	 * Sets the localized observationses of this comp from the map of locales and localized observationses.
	 *
	 * @param observationsMap the locales and localized observationses of this comp
	 */
	@Override
	public void setObservationsMap(
		Map<java.util.Locale, String> observationsMap) {

		model.setObservationsMap(observationsMap);
	}

	/**
	 * Sets the localized observationses of this comp from the map of locales and localized observationses, and sets the default locale.
	 *
	 * @param observationsMap the locales and localized observationses of this comp
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setObservationsMap(
		Map<java.util.Locale, String> observationsMap,
		java.util.Locale defaultLocale) {

		model.setObservationsMap(observationsMap, defaultLocale);
	}

	/**
	 * Sets the primary key of this comp.
	 *
	 * @param primaryKey the primary key of this comp
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the start date of this comp.
	 *
	 * @param startDate the start date of this comp
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	@Override
	protected CompWrapper wrap(Comp comp) {
		return new CompWrapper(comp);
	}

}