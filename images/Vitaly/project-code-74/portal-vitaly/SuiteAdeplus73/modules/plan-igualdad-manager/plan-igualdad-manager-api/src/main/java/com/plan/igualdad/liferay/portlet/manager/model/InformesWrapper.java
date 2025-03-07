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

package com.plan.igualdad.liferay.portlet.manager.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Informes}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Informes
 * @generated
 */
public class InformesWrapper
	extends BaseModelWrapper<Informes>
	implements Informes, ModelWrapper<Informes> {

	public InformesWrapper(Informes informes) {
		super(informes);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("informeId", getInformeId());
		attributes.put("compId", getCompId());
		attributes.put("userId", getUserId());
		attributes.put("versionId", getVersionId());
		attributes.put("tipoInforme", getTipoInforme());
		attributes.put("versionInforme", getVersionInforme());
		attributes.put("parametrosInforme", getParametrosInforme());
		attributes.put("beansInforme", getBeansInforme());
		attributes.put("formato", getFormato());
		attributes.put("createDate", getCreateDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long informeId = (Long)attributes.get("informeId");

		if (informeId != null) {
			setInformeId(informeId);
		}

		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long versionId = (Long)attributes.get("versionId");

		if (versionId != null) {
			setVersionId(versionId);
		}

		String tipoInforme = (String)attributes.get("tipoInforme");

		if (tipoInforme != null) {
			setTipoInforme(tipoInforme);
		}

		Integer versionInforme = (Integer)attributes.get("versionInforme");

		if (versionInforme != null) {
			setVersionInforme(versionInforme);
		}

		String parametrosInforme = (String)attributes.get("parametrosInforme");

		if (parametrosInforme != null) {
			setParametrosInforme(parametrosInforme);
		}

		String beansInforme = (String)attributes.get("beansInforme");

		if (beansInforme != null) {
			setBeansInforme(beansInforme);
		}

		String formato = (String)attributes.get("formato");

		if (formato != null) {
			setFormato(formato);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}
	}

	/**
	 * Returns the beans informe of this informes.
	 *
	 * @return the beans informe of this informes
	 */
	@Override
	public String getBeansInforme() {
		return model.getBeansInforme();
	}

	/**
	 * Returns the comp ID of this informes.
	 *
	 * @return the comp ID of this informes
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the create date of this informes.
	 *
	 * @return the create date of this informes
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the formato of this informes.
	 *
	 * @return the formato of this informes
	 */
	@Override
	public String getFormato() {
		return model.getFormato();
	}

	/**
	 * Returns the informe ID of this informes.
	 *
	 * @return the informe ID of this informes
	 */
	@Override
	public long getInformeId() {
		return model.getInformeId();
	}

	/**
	 * Returns the parametros informe of this informes.
	 *
	 * @return the parametros informe of this informes
	 */
	@Override
	public String getParametrosInforme() {
		return model.getParametrosInforme();
	}

	/**
	 * Returns the primary key of this informes.
	 *
	 * @return the primary key of this informes
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the tipo informe of this informes.
	 *
	 * @return the tipo informe of this informes
	 */
	@Override
	public String getTipoInforme() {
		return model.getTipoInforme();
	}

	/**
	 * Returns the user ID of this informes.
	 *
	 * @return the user ID of this informes
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this informes.
	 *
	 * @return the user uuid of this informes
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the version ID of this informes.
	 *
	 * @return the version ID of this informes
	 */
	@Override
	public long getVersionId() {
		return model.getVersionId();
	}

	/**
	 * Returns the version informe of this informes.
	 *
	 * @return the version informe of this informes
	 */
	@Override
	public Integer getVersionInforme() {
		return model.getVersionInforme();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the beans informe of this informes.
	 *
	 * @param beansInforme the beans informe of this informes
	 */
	@Override
	public void setBeansInforme(String beansInforme) {
		model.setBeansInforme(beansInforme);
	}

	/**
	 * Sets the comp ID of this informes.
	 *
	 * @param compId the comp ID of this informes
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the create date of this informes.
	 *
	 * @param createDate the create date of this informes
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the formato of this informes.
	 *
	 * @param formato the formato of this informes
	 */
	@Override
	public void setFormato(String formato) {
		model.setFormato(formato);
	}

	/**
	 * Sets the informe ID of this informes.
	 *
	 * @param informeId the informe ID of this informes
	 */
	@Override
	public void setInformeId(long informeId) {
		model.setInformeId(informeId);
	}

	/**
	 * Sets the parametros informe of this informes.
	 *
	 * @param parametrosInforme the parametros informe of this informes
	 */
	@Override
	public void setParametrosInforme(String parametrosInforme) {
		model.setParametrosInforme(parametrosInforme);
	}

	/**
	 * Sets the primary key of this informes.
	 *
	 * @param primaryKey the primary key of this informes
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the tipo informe of this informes.
	 *
	 * @param tipoInforme the tipo informe of this informes
	 */
	@Override
	public void setTipoInforme(String tipoInforme) {
		model.setTipoInforme(tipoInforme);
	}

	/**
	 * Sets the user ID of this informes.
	 *
	 * @param userId the user ID of this informes
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this informes.
	 *
	 * @param userUuid the user uuid of this informes
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the version ID of this informes.
	 *
	 * @param versionId the version ID of this informes
	 */
	@Override
	public void setVersionId(long versionId) {
		model.setVersionId(versionId);
	}

	/**
	 * Sets the version informe of this informes.
	 *
	 * @param versionInforme the version informe of this informes
	 */
	@Override
	public void setVersionInforme(Integer versionInforme) {
		model.setVersionInforme(versionInforme);
	}

	@Override
	protected InformesWrapper wrap(Informes informes) {
		return new InformesWrapper(informes);
	}

}