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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Vinculacion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Vinculacion
 * @generated
 */
public class VinculacionWrapper
	extends BaseModelWrapper<Vinculacion>
	implements ModelWrapper<Vinculacion>, Vinculacion {

	public VinculacionWrapper(Vinculacion vinculacion) {
		super(vinculacion);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("vinculacionId", getVinculacionId());
		attributes.put("compId", getCompId());
		attributes.put("nombre", getNombre());
		attributes.put("activo", isActivo());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long vinculacionId = (Long)attributes.get("vinculacionId");

		if (vinculacionId != null) {
			setVinculacionId(vinculacionId);
		}

		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		String nombre = (String)attributes.get("nombre");

		if (nombre != null) {
			setNombre(nombre);
		}

		Boolean activo = (Boolean)attributes.get("activo");

		if (activo != null) {
			setActivo(activo);
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
	 * Returns the activo of this vinculacion.
	 *
	 * @return the activo of this vinculacion
	 */
	@Override
	public boolean getActivo() {
		return model.getActivo();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return model.getAvailableLanguageIds();
	}

	/**
	 * Returns the comp ID of this vinculacion.
	 *
	 * @return the comp ID of this vinculacion
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the create date of this vinculacion.
	 *
	 * @return the create date of this vinculacion
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
	 * Returns the modified date of this vinculacion.
	 *
	 * @return the modified date of this vinculacion
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the nombre of this vinculacion.
	 *
	 * @return the nombre of this vinculacion
	 */
	@Override
	public String getNombre() {
		return model.getNombre();
	}

	/**
	 * Returns the localized nombre of this vinculacion in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized nombre of this vinculacion
	 */
	@Override
	public String getNombre(java.util.Locale locale) {
		return model.getNombre(locale);
	}

	/**
	 * Returns the localized nombre of this vinculacion in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized nombre of this vinculacion. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getNombre(java.util.Locale locale, boolean useDefault) {
		return model.getNombre(locale, useDefault);
	}

	/**
	 * Returns the localized nombre of this vinculacion in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized nombre of this vinculacion
	 */
	@Override
	public String getNombre(String languageId) {
		return model.getNombre(languageId);
	}

	/**
	 * Returns the localized nombre of this vinculacion in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized nombre of this vinculacion
	 */
	@Override
	public String getNombre(String languageId, boolean useDefault) {
		return model.getNombre(languageId, useDefault);
	}

	@Override
	public String getNombreCurrentLanguageId() {
		return model.getNombreCurrentLanguageId();
	}

	@Override
	public String getNombreCurrentValue() {
		return model.getNombreCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized nombres of this vinculacion.
	 *
	 * @return the locales and localized nombres of this vinculacion
	 */
	@Override
	public Map<java.util.Locale, String> getNombreMap() {
		return model.getNombreMap();
	}

	/**
	 * Returns the primary key of this vinculacion.
	 *
	 * @return the primary key of this vinculacion
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the vinculacion ID of this vinculacion.
	 *
	 * @return the vinculacion ID of this vinculacion
	 */
	@Override
	public long getVinculacionId() {
		return model.getVinculacionId();
	}

	/**
	 * Returns <code>true</code> if this vinculacion is activo.
	 *
	 * @return <code>true</code> if this vinculacion is activo; <code>false</code> otherwise
	 */
	@Override
	public boolean isActivo() {
		return model.isActivo();
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
	 * Sets whether this vinculacion is activo.
	 *
	 * @param activo the activo of this vinculacion
	 */
	@Override
	public void setActivo(boolean activo) {
		model.setActivo(activo);
	}

	/**
	 * Sets the comp ID of this vinculacion.
	 *
	 * @param compId the comp ID of this vinculacion
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the create date of this vinculacion.
	 *
	 * @param createDate the create date of this vinculacion
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the modified date of this vinculacion.
	 *
	 * @param modifiedDate the modified date of this vinculacion
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the nombre of this vinculacion.
	 *
	 * @param nombre the nombre of this vinculacion
	 */
	@Override
	public void setNombre(String nombre) {
		model.setNombre(nombre);
	}

	/**
	 * Sets the localized nombre of this vinculacion in the language.
	 *
	 * @param nombre the localized nombre of this vinculacion
	 * @param locale the locale of the language
	 */
	@Override
	public void setNombre(String nombre, java.util.Locale locale) {
		model.setNombre(nombre, locale);
	}

	/**
	 * Sets the localized nombre of this vinculacion in the language, and sets the default locale.
	 *
	 * @param nombre the localized nombre of this vinculacion
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setNombre(
		String nombre, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setNombre(nombre, locale, defaultLocale);
	}

	@Override
	public void setNombreCurrentLanguageId(String languageId) {
		model.setNombreCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized nombres of this vinculacion from the map of locales and localized nombres.
	 *
	 * @param nombreMap the locales and localized nombres of this vinculacion
	 */
	@Override
	public void setNombreMap(Map<java.util.Locale, String> nombreMap) {
		model.setNombreMap(nombreMap);
	}

	/**
	 * Sets the localized nombres of this vinculacion from the map of locales and localized nombres, and sets the default locale.
	 *
	 * @param nombreMap the locales and localized nombres of this vinculacion
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setNombreMap(
		Map<java.util.Locale, String> nombreMap,
		java.util.Locale defaultLocale) {

		model.setNombreMap(nombreMap, defaultLocale);
	}

	/**
	 * Sets the primary key of this vinculacion.
	 *
	 * @param primaryKey the primary key of this vinculacion
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the vinculacion ID of this vinculacion.
	 *
	 * @param vinculacionId the vinculacion ID of this vinculacion
	 */
	@Override
	public void setVinculacionId(long vinculacionId) {
		model.setVinculacionId(vinculacionId);
	}

	@Override
	protected VinculacionWrapper wrap(Vinculacion vinculacion) {
		return new VinculacionWrapper(vinculacion);
	}

}