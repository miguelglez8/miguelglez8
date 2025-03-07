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
 * This class is a wrapper for {@link Categoria}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Categoria
 * @generated
 */
public class CategoriaWrapper
	extends BaseModelWrapper<Categoria>
	implements Categoria, ModelWrapper<Categoria> {

	public CategoriaWrapper(Categoria categoria) {
		super(categoria);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("categoriaId", getCategoriaId());
		attributes.put("compId", getCompId());
		attributes.put("nombre", getNombre());
		attributes.put("tipo", getTipo());
		attributes.put("codigo", getCodigo());
		attributes.put("activo", isActivo());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long categoriaId = (Long)attributes.get("categoriaId");

		if (categoriaId != null) {
			setCategoriaId(categoriaId);
		}

		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		String nombre = (String)attributes.get("nombre");

		if (nombre != null) {
			setNombre(nombre);
		}

		String tipo = (String)attributes.get("tipo");

		if (tipo != null) {
			setTipo(tipo);
		}

		String codigo = (String)attributes.get("codigo");

		if (codigo != null) {
			setCodigo(codigo);
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
	 * Returns the activo of this categoria.
	 *
	 * @return the activo of this categoria
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
	 * Returns the categoria ID of this categoria.
	 *
	 * @return the categoria ID of this categoria
	 */
	@Override
	public long getCategoriaId() {
		return model.getCategoriaId();
	}

	/**
	 * Returns the codigo of this categoria.
	 *
	 * @return the codigo of this categoria
	 */
	@Override
	public String getCodigo() {
		return model.getCodigo();
	}

	/**
	 * Returns the comp ID of this categoria.
	 *
	 * @return the comp ID of this categoria
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the create date of this categoria.
	 *
	 * @return the create date of this categoria
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
	 * Returns the modified date of this categoria.
	 *
	 * @return the modified date of this categoria
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the nombre of this categoria.
	 *
	 * @return the nombre of this categoria
	 */
	@Override
	public String getNombre() {
		return model.getNombre();
	}

	/**
	 * Returns the localized nombre of this categoria in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized nombre of this categoria
	 */
	@Override
	public String getNombre(java.util.Locale locale) {
		return model.getNombre(locale);
	}

	/**
	 * Returns the localized nombre of this categoria in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized nombre of this categoria. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getNombre(java.util.Locale locale, boolean useDefault) {
		return model.getNombre(locale, useDefault);
	}

	/**
	 * Returns the localized nombre of this categoria in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized nombre of this categoria
	 */
	@Override
	public String getNombre(String languageId) {
		return model.getNombre(languageId);
	}

	/**
	 * Returns the localized nombre of this categoria in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized nombre of this categoria
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
	 * Returns a map of the locales and localized nombres of this categoria.
	 *
	 * @return the locales and localized nombres of this categoria
	 */
	@Override
	public Map<java.util.Locale, String> getNombreMap() {
		return model.getNombreMap();
	}

	/**
	 * Returns the primary key of this categoria.
	 *
	 * @return the primary key of this categoria
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the tipo of this categoria.
	 *
	 * @return the tipo of this categoria
	 */
	@Override
	public String getTipo() {
		return model.getTipo();
	}

	/**
	 * Returns <code>true</code> if this categoria is activo.
	 *
	 * @return <code>true</code> if this categoria is activo; <code>false</code> otherwise
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
	 * Sets whether this categoria is activo.
	 *
	 * @param activo the activo of this categoria
	 */
	@Override
	public void setActivo(boolean activo) {
		model.setActivo(activo);
	}

	/**
	 * Sets the categoria ID of this categoria.
	 *
	 * @param categoriaId the categoria ID of this categoria
	 */
	@Override
	public void setCategoriaId(long categoriaId) {
		model.setCategoriaId(categoriaId);
	}

	/**
	 * Sets the codigo of this categoria.
	 *
	 * @param codigo the codigo of this categoria
	 */
	@Override
	public void setCodigo(String codigo) {
		model.setCodigo(codigo);
	}

	/**
	 * Sets the comp ID of this categoria.
	 *
	 * @param compId the comp ID of this categoria
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the create date of this categoria.
	 *
	 * @param createDate the create date of this categoria
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the modified date of this categoria.
	 *
	 * @param modifiedDate the modified date of this categoria
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the nombre of this categoria.
	 *
	 * @param nombre the nombre of this categoria
	 */
	@Override
	public void setNombre(String nombre) {
		model.setNombre(nombre);
	}

	/**
	 * Sets the localized nombre of this categoria in the language.
	 *
	 * @param nombre the localized nombre of this categoria
	 * @param locale the locale of the language
	 */
	@Override
	public void setNombre(String nombre, java.util.Locale locale) {
		model.setNombre(nombre, locale);
	}

	/**
	 * Sets the localized nombre of this categoria in the language, and sets the default locale.
	 *
	 * @param nombre the localized nombre of this categoria
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
	 * Sets the localized nombres of this categoria from the map of locales and localized nombres.
	 *
	 * @param nombreMap the locales and localized nombres of this categoria
	 */
	@Override
	public void setNombreMap(Map<java.util.Locale, String> nombreMap) {
		model.setNombreMap(nombreMap);
	}

	/**
	 * Sets the localized nombres of this categoria from the map of locales and localized nombres, and sets the default locale.
	 *
	 * @param nombreMap the locales and localized nombres of this categoria
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setNombreMap(
		Map<java.util.Locale, String> nombreMap,
		java.util.Locale defaultLocale) {

		model.setNombreMap(nombreMap, defaultLocale);
	}

	/**
	 * Sets the primary key of this categoria.
	 *
	 * @param primaryKey the primary key of this categoria
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the tipo of this categoria.
	 *
	 * @param tipo the tipo of this categoria
	 */
	@Override
	public void setTipo(String tipo) {
		model.setTipo(tipo);
	}

	@Override
	protected CategoriaWrapper wrap(Categoria categoria) {
		return new CategoriaWrapper(categoria);
	}

}