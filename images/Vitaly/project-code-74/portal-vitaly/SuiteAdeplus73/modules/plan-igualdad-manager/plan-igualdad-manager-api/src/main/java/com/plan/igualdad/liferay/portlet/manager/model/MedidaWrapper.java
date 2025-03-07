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
 * This class is a wrapper for {@link Medida}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Medida
 * @generated
 */
public class MedidaWrapper
	extends BaseModelWrapper<Medida> implements Medida, ModelWrapper<Medida> {

	public MedidaWrapper(Medida medida) {
		super(medida);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("medidaId", getMedidaId());
		attributes.put("compId", getCompId());
		attributes.put("versionId", getVersionId());
		attributes.put("userId", getUserId());
		attributes.put("datosGenerales", getDatosGenerales());
		attributes.put("cumplimentacion", getCumplimentacion());
		attributes.put("isMedidaPredefinida", getIsMedidaPredefinida());
		attributes.put("medidaPredefinidaId", getMedidaPredefinidaId());
		attributes.put("versionMedida", getVersionMedida());
		attributes.put("versionOriginalMedidaId", getVersionOriginalMedidaId());
		attributes.put("createDate", getCreateDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long medidaId = (Long)attributes.get("medidaId");

		if (medidaId != null) {
			setMedidaId(medidaId);
		}

		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		Long versionId = (Long)attributes.get("versionId");

		if (versionId != null) {
			setVersionId(versionId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String datosGenerales = (String)attributes.get("datosGenerales");

		if (datosGenerales != null) {
			setDatosGenerales(datosGenerales);
		}

		String cumplimentacion = (String)attributes.get("cumplimentacion");

		if (cumplimentacion != null) {
			setCumplimentacion(cumplimentacion);
		}

		Boolean isMedidaPredefinida = (Boolean)attributes.get(
			"isMedidaPredefinida");

		if (isMedidaPredefinida != null) {
			setIsMedidaPredefinida(isMedidaPredefinida);
		}

		String medidaPredefinidaId = (String)attributes.get(
			"medidaPredefinidaId");

		if (medidaPredefinidaId != null) {
			setMedidaPredefinidaId(medidaPredefinidaId);
		}

		Integer versionMedida = (Integer)attributes.get("versionMedida");

		if (versionMedida != null) {
			setVersionMedida(versionMedida);
		}

		Long versionOriginalMedidaId = (Long)attributes.get(
			"versionOriginalMedidaId");

		if (versionOriginalMedidaId != null) {
			setVersionOriginalMedidaId(versionOriginalMedidaId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}
	}

	/**
	 * Returns the comp ID of this medida.
	 *
	 * @return the comp ID of this medida
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the create date of this medida.
	 *
	 * @return the create date of this medida
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the cumplimentacion of this medida.
	 *
	 * @return the cumplimentacion of this medida
	 */
	@Override
	public String getCumplimentacion() {
		return model.getCumplimentacion();
	}

	/**
	 * Returns the datos generales of this medida.
	 *
	 * @return the datos generales of this medida
	 */
	@Override
	public String getDatosGenerales() {
		return model.getDatosGenerales();
	}

	/**
	 * Returns the is medida predefinida of this medida.
	 *
	 * @return the is medida predefinida of this medida
	 */
	@Override
	public Boolean getIsMedidaPredefinida() {
		return model.getIsMedidaPredefinida();
	}

	/**
	 * Returns the medida ID of this medida.
	 *
	 * @return the medida ID of this medida
	 */
	@Override
	public long getMedidaId() {
		return model.getMedidaId();
	}

	/**
	 * Returns the medida predefinida ID of this medida.
	 *
	 * @return the medida predefinida ID of this medida
	 */
	@Override
	public String getMedidaPredefinidaId() {
		return model.getMedidaPredefinidaId();
	}

	/**
	 * Returns the primary key of this medida.
	 *
	 * @return the primary key of this medida
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this medida.
	 *
	 * @return the user ID of this medida
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this medida.
	 *
	 * @return the user uuid of this medida
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the version ID of this medida.
	 *
	 * @return the version ID of this medida
	 */
	@Override
	public long getVersionId() {
		return model.getVersionId();
	}

	/**
	 * Returns the version medida of this medida.
	 *
	 * @return the version medida of this medida
	 */
	@Override
	public Integer getVersionMedida() {
		return model.getVersionMedida();
	}

	/**
	 * Returns the version original medida ID of this medida.
	 *
	 * @return the version original medida ID of this medida
	 */
	@Override
	public long getVersionOriginalMedidaId() {
		return model.getVersionOriginalMedidaId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the comp ID of this medida.
	 *
	 * @param compId the comp ID of this medida
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the create date of this medida.
	 *
	 * @param createDate the create date of this medida
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the cumplimentacion of this medida.
	 *
	 * @param cumplimentacion the cumplimentacion of this medida
	 */
	@Override
	public void setCumplimentacion(String cumplimentacion) {
		model.setCumplimentacion(cumplimentacion);
	}

	/**
	 * Sets the datos generales of this medida.
	 *
	 * @param datosGenerales the datos generales of this medida
	 */
	@Override
	public void setDatosGenerales(String datosGenerales) {
		model.setDatosGenerales(datosGenerales);
	}

	/**
	 * Sets the is medida predefinida of this medida.
	 *
	 * @param isMedidaPredefinida the is medida predefinida of this medida
	 */
	@Override
	public void setIsMedidaPredefinida(Boolean isMedidaPredefinida) {
		model.setIsMedidaPredefinida(isMedidaPredefinida);
	}

	/**
	 * Sets the medida ID of this medida.
	 *
	 * @param medidaId the medida ID of this medida
	 */
	@Override
	public void setMedidaId(long medidaId) {
		model.setMedidaId(medidaId);
	}

	/**
	 * Sets the medida predefinida ID of this medida.
	 *
	 * @param medidaPredefinidaId the medida predefinida ID of this medida
	 */
	@Override
	public void setMedidaPredefinidaId(String medidaPredefinidaId) {
		model.setMedidaPredefinidaId(medidaPredefinidaId);
	}

	/**
	 * Sets the primary key of this medida.
	 *
	 * @param primaryKey the primary key of this medida
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this medida.
	 *
	 * @param userId the user ID of this medida
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this medida.
	 *
	 * @param userUuid the user uuid of this medida
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the version ID of this medida.
	 *
	 * @param versionId the version ID of this medida
	 */
	@Override
	public void setVersionId(long versionId) {
		model.setVersionId(versionId);
	}

	/**
	 * Sets the version medida of this medida.
	 *
	 * @param versionMedida the version medida of this medida
	 */
	@Override
	public void setVersionMedida(Integer versionMedida) {
		model.setVersionMedida(versionMedida);
	}

	/**
	 * Sets the version original medida ID of this medida.
	 *
	 * @param versionOriginalMedidaId the version original medida ID of this medida
	 */
	@Override
	public void setVersionOriginalMedidaId(long versionOriginalMedidaId) {
		model.setVersionOriginalMedidaId(versionOriginalMedidaId);
	}

	@Override
	protected MedidaWrapper wrap(Medida medida) {
		return new MedidaWrapper(medida);
	}

}