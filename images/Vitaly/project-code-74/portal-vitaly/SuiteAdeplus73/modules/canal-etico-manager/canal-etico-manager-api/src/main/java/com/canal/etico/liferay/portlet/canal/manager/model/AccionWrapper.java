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
 * This class is a wrapper for {@link Accion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Accion
 * @generated
 */
public class AccionWrapper
	extends BaseModelWrapper<Accion> implements Accion, ModelWrapper<Accion> {

	public AccionWrapper(Accion accion) {
		super(accion);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accionId", getAccionId());
		attributes.put("compId", getCompId());
		attributes.put("nombre", getNombre());
		attributes.put("estado", getEstado());
		attributes.put("activo", isActivo());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accionId = (Long)attributes.get("accionId");

		if (accionId != null) {
			setAccionId(accionId);
		}

		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		String nombre = (String)attributes.get("nombre");

		if (nombre != null) {
			setNombre(nombre);
		}

		Long estado = (Long)attributes.get("estado");

		if (estado != null) {
			setEstado(estado);
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
	 * Returns the accion ID of this accion.
	 *
	 * @return the accion ID of this accion
	 */
	@Override
	public long getAccionId() {
		return model.getAccionId();
	}

	/**
	 * Returns the activo of this accion.
	 *
	 * @return the activo of this accion
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
	 * Returns the comp ID of this accion.
	 *
	 * @return the comp ID of this accion
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the create date of this accion.
	 *
	 * @return the create date of this accion
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
	 * Returns the estado of this accion.
	 *
	 * @return the estado of this accion
	 */
	@Override
	public long getEstado() {
		return model.getEstado();
	}

	/**
	 * Returns the modified date of this accion.
	 *
	 * @return the modified date of this accion
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the nombre of this accion.
	 *
	 * @return the nombre of this accion
	 */
	@Override
	public String getNombre() {
		return model.getNombre();
	}

	/**
	 * Returns the localized nombre of this accion in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized nombre of this accion
	 */
	@Override
	public String getNombre(java.util.Locale locale) {
		return model.getNombre(locale);
	}

	/**
	 * Returns the localized nombre of this accion in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized nombre of this accion. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getNombre(java.util.Locale locale, boolean useDefault) {
		return model.getNombre(locale, useDefault);
	}

	/**
	 * Returns the localized nombre of this accion in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized nombre of this accion
	 */
	@Override
	public String getNombre(String languageId) {
		return model.getNombre(languageId);
	}

	/**
	 * Returns the localized nombre of this accion in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized nombre of this accion
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
	 * Returns a map of the locales and localized nombres of this accion.
	 *
	 * @return the locales and localized nombres of this accion
	 */
	@Override
	public Map<java.util.Locale, String> getNombreMap() {
		return model.getNombreMap();
	}

	/**
	 * Returns the primary key of this accion.
	 *
	 * @return the primary key of this accion
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns <code>true</code> if this accion is activo.
	 *
	 * @return <code>true</code> if this accion is activo; <code>false</code> otherwise
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
	 * Sets the accion ID of this accion.
	 *
	 * @param accionId the accion ID of this accion
	 */
	@Override
	public void setAccionId(long accionId) {
		model.setAccionId(accionId);
	}

	/**
	 * Sets whether this accion is activo.
	 *
	 * @param activo the activo of this accion
	 */
	@Override
	public void setActivo(boolean activo) {
		model.setActivo(activo);
	}

	/**
	 * Sets the comp ID of this accion.
	 *
	 * @param compId the comp ID of this accion
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the create date of this accion.
	 *
	 * @param createDate the create date of this accion
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the estado of this accion.
	 *
	 * @param estado the estado of this accion
	 */
	@Override
	public void setEstado(long estado) {
		model.setEstado(estado);
	}

	/**
	 * Sets the modified date of this accion.
	 *
	 * @param modifiedDate the modified date of this accion
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the nombre of this accion.
	 *
	 * @param nombre the nombre of this accion
	 */
	@Override
	public void setNombre(String nombre) {
		model.setNombre(nombre);
	}

	/**
	 * Sets the localized nombre of this accion in the language.
	 *
	 * @param nombre the localized nombre of this accion
	 * @param locale the locale of the language
	 */
	@Override
	public void setNombre(String nombre, java.util.Locale locale) {
		model.setNombre(nombre, locale);
	}

	/**
	 * Sets the localized nombre of this accion in the language, and sets the default locale.
	 *
	 * @param nombre the localized nombre of this accion
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
	 * Sets the localized nombres of this accion from the map of locales and localized nombres.
	 *
	 * @param nombreMap the locales and localized nombres of this accion
	 */
	@Override
	public void setNombreMap(Map<java.util.Locale, String> nombreMap) {
		model.setNombreMap(nombreMap);
	}

	/**
	 * Sets the localized nombres of this accion from the map of locales and localized nombres, and sets the default locale.
	 *
	 * @param nombreMap the locales and localized nombres of this accion
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setNombreMap(
		Map<java.util.Locale, String> nombreMap,
		java.util.Locale defaultLocale) {

		model.setNombreMap(nombreMap, defaultLocale);
	}

	/**
	 * Sets the primary key of this accion.
	 *
	 * @param primaryKey the primary key of this accion
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected AccionWrapper wrap(Accion accion) {
		return new AccionWrapper(accion);
	}

}