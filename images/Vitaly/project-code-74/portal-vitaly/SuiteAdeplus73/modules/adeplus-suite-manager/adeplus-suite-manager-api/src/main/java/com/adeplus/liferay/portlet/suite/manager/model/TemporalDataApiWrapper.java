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
 * This class is a wrapper for {@link TemporalDataApi}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TemporalDataApi
 * @generated
 */
public class TemporalDataApiWrapper
	extends BaseModelWrapper<TemporalDataApi>
	implements ModelWrapper<TemporalDataApi>, TemporalDataApi {

	public TemporalDataApiWrapper(TemporalDataApi temporalDataApi) {
		super(temporalDataApi);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("idDataTemporal", getIdDataTemporal());
		attributes.put("typeEvent", getTypeEvent());
		attributes.put("dataApiEmpresa", getDataApiEmpresa());
		attributes.put("dataApiApplicaciones", getDataApiApplicaciones());
		attributes.put("dataApiUsuarios", getDataApiUsuarios());
		attributes.put("procesado", getProcesado());
		attributes.put("createDate", getCreateDate());
		attributes.put("procesadoDate", getProcesadoDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long idDataTemporal = (Long)attributes.get("idDataTemporal");

		if (idDataTemporal != null) {
			setIdDataTemporal(idDataTemporal);
		}

		String typeEvent = (String)attributes.get("typeEvent");

		if (typeEvent != null) {
			setTypeEvent(typeEvent);
		}

		String dataApiEmpresa = (String)attributes.get("dataApiEmpresa");

		if (dataApiEmpresa != null) {
			setDataApiEmpresa(dataApiEmpresa);
		}

		String dataApiApplicaciones = (String)attributes.get(
			"dataApiApplicaciones");

		if (dataApiApplicaciones != null) {
			setDataApiApplicaciones(dataApiApplicaciones);
		}

		String dataApiUsuarios = (String)attributes.get("dataApiUsuarios");

		if (dataApiUsuarios != null) {
			setDataApiUsuarios(dataApiUsuarios);
		}

		Integer procesado = (Integer)attributes.get("procesado");

		if (procesado != null) {
			setProcesado(procesado);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date procesadoDate = (Date)attributes.get("procesadoDate");

		if (procesadoDate != null) {
			setProcesadoDate(procesadoDate);
		}
	}

	/**
	 * Returns the create date of this temporal data api.
	 *
	 * @return the create date of this temporal data api
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the data api applicaciones of this temporal data api.
	 *
	 * @return the data api applicaciones of this temporal data api
	 */
	@Override
	public String getDataApiApplicaciones() {
		return model.getDataApiApplicaciones();
	}

	/**
	 * Returns the data api empresa of this temporal data api.
	 *
	 * @return the data api empresa of this temporal data api
	 */
	@Override
	public String getDataApiEmpresa() {
		return model.getDataApiEmpresa();
	}

	/**
	 * Returns the data api usuarios of this temporal data api.
	 *
	 * @return the data api usuarios of this temporal data api
	 */
	@Override
	public String getDataApiUsuarios() {
		return model.getDataApiUsuarios();
	}

	/**
	 * Returns the id data temporal of this temporal data api.
	 *
	 * @return the id data temporal of this temporal data api
	 */
	@Override
	public long getIdDataTemporal() {
		return model.getIdDataTemporal();
	}

	/**
	 * Returns the primary key of this temporal data api.
	 *
	 * @return the primary key of this temporal data api
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the procesado of this temporal data api.
	 *
	 * @return the procesado of this temporal data api
	 */
	@Override
	public Integer getProcesado() {
		return model.getProcesado();
	}

	/**
	 * Returns the procesado date of this temporal data api.
	 *
	 * @return the procesado date of this temporal data api
	 */
	@Override
	public Date getProcesadoDate() {
		return model.getProcesadoDate();
	}

	/**
	 * Returns the type event of this temporal data api.
	 *
	 * @return the type event of this temporal data api
	 */
	@Override
	public String getTypeEvent() {
		return model.getTypeEvent();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the create date of this temporal data api.
	 *
	 * @param createDate the create date of this temporal data api
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the data api applicaciones of this temporal data api.
	 *
	 * @param dataApiApplicaciones the data api applicaciones of this temporal data api
	 */
	@Override
	public void setDataApiApplicaciones(String dataApiApplicaciones) {
		model.setDataApiApplicaciones(dataApiApplicaciones);
	}

	/**
	 * Sets the data api empresa of this temporal data api.
	 *
	 * @param dataApiEmpresa the data api empresa of this temporal data api
	 */
	@Override
	public void setDataApiEmpresa(String dataApiEmpresa) {
		model.setDataApiEmpresa(dataApiEmpresa);
	}

	/**
	 * Sets the data api usuarios of this temporal data api.
	 *
	 * @param dataApiUsuarios the data api usuarios of this temporal data api
	 */
	@Override
	public void setDataApiUsuarios(String dataApiUsuarios) {
		model.setDataApiUsuarios(dataApiUsuarios);
	}

	/**
	 * Sets the id data temporal of this temporal data api.
	 *
	 * @param idDataTemporal the id data temporal of this temporal data api
	 */
	@Override
	public void setIdDataTemporal(long idDataTemporal) {
		model.setIdDataTemporal(idDataTemporal);
	}

	/**
	 * Sets the primary key of this temporal data api.
	 *
	 * @param primaryKey the primary key of this temporal data api
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the procesado of this temporal data api.
	 *
	 * @param procesado the procesado of this temporal data api
	 */
	@Override
	public void setProcesado(Integer procesado) {
		model.setProcesado(procesado);
	}

	/**
	 * Sets the procesado date of this temporal data api.
	 *
	 * @param procesadoDate the procesado date of this temporal data api
	 */
	@Override
	public void setProcesadoDate(Date procesadoDate) {
		model.setProcesadoDate(procesadoDate);
	}

	/**
	 * Sets the type event of this temporal data api.
	 *
	 * @param typeEvent the type event of this temporal data api
	 */
	@Override
	public void setTypeEvent(String typeEvent) {
		model.setTypeEvent(typeEvent);
	}

	@Override
	protected TemporalDataApiWrapper wrap(TemporalDataApi temporalDataApi) {
		return new TemporalDataApiWrapper(temporalDataApi);
	}

}