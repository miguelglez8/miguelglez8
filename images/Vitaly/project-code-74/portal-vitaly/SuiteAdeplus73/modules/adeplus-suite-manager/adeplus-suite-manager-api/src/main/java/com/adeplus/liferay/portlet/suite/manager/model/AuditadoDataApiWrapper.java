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
 * This class is a wrapper for {@link AuditadoDataApi}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditadoDataApi
 * @generated
 */
public class AuditadoDataApiWrapper
	extends BaseModelWrapper<AuditadoDataApi>
	implements AuditadoDataApi, ModelWrapper<AuditadoDataApi> {

	public AuditadoDataApiWrapper(AuditadoDataApi auditadoDataApi) {
		super(auditadoDataApi);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("idDataTemporal", getIdDataTemporal());
		attributes.put("mensaje", getMensaje());
		attributes.put("procesadoDate", getProcesadoDate());
		attributes.put("procesado", getProcesado());
		attributes.put("evento", getEvento());
		attributes.put("cif", getCif());
		attributes.put("idClient", getIdClient());
		attributes.put("idContract", getIdContract());
		attributes.put("app", getApp());
		attributes.put("changeStateDate", getChangeStateDate());
		attributes.put("state", isState());
		attributes.put("userIdChangeState", getUserIdChangeState());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long idDataTemporal = (Long)attributes.get("idDataTemporal");

		if (idDataTemporal != null) {
			setIdDataTemporal(idDataTemporal);
		}

		String mensaje = (String)attributes.get("mensaje");

		if (mensaje != null) {
			setMensaje(mensaje);
		}

		Date procesadoDate = (Date)attributes.get("procesadoDate");

		if (procesadoDate != null) {
			setProcesadoDate(procesadoDate);
		}

		Integer procesado = (Integer)attributes.get("procesado");

		if (procesado != null) {
			setProcesado(procesado);
		}

		String evento = (String)attributes.get("evento");

		if (evento != null) {
			setEvento(evento);
		}

		String cif = (String)attributes.get("cif");

		if (cif != null) {
			setCif(cif);
		}

		Long idClient = (Long)attributes.get("idClient");

		if (idClient != null) {
			setIdClient(idClient);
		}

		Long idContract = (Long)attributes.get("idContract");

		if (idContract != null) {
			setIdContract(idContract);
		}

		String app = (String)attributes.get("app");

		if (app != null) {
			setApp(app);
		}

		Date changeStateDate = (Date)attributes.get("changeStateDate");

		if (changeStateDate != null) {
			setChangeStateDate(changeStateDate);
		}

		Boolean state = (Boolean)attributes.get("state");

		if (state != null) {
			setState(state);
		}

		Long userIdChangeState = (Long)attributes.get("userIdChangeState");

		if (userIdChangeState != null) {
			setUserIdChangeState(userIdChangeState);
		}
	}

	/**
	 * Returns the app of this auditado data api.
	 *
	 * @return the app of this auditado data api
	 */
	@Override
	public String getApp() {
		return model.getApp();
	}

	/**
	 * Returns the change state date of this auditado data api.
	 *
	 * @return the change state date of this auditado data api
	 */
	@Override
	public Date getChangeStateDate() {
		return model.getChangeStateDate();
	}

	/**
	 * Returns the cif of this auditado data api.
	 *
	 * @return the cif of this auditado data api
	 */
	@Override
	public String getCif() {
		return model.getCif();
	}

	/**
	 * Returns the evento of this auditado data api.
	 *
	 * @return the evento of this auditado data api
	 */
	@Override
	public String getEvento() {
		return model.getEvento();
	}

	/**
	 * Returns the id client of this auditado data api.
	 *
	 * @return the id client of this auditado data api
	 */
	@Override
	public long getIdClient() {
		return model.getIdClient();
	}

	/**
	 * Returns the id contract of this auditado data api.
	 *
	 * @return the id contract of this auditado data api
	 */
	@Override
	public long getIdContract() {
		return model.getIdContract();
	}

	/**
	 * Returns the id data temporal of this auditado data api.
	 *
	 * @return the id data temporal of this auditado data api
	 */
	@Override
	public long getIdDataTemporal() {
		return model.getIdDataTemporal();
	}

	/**
	 * Returns the mensaje of this auditado data api.
	 *
	 * @return the mensaje of this auditado data api
	 */
	@Override
	public String getMensaje() {
		return model.getMensaje();
	}

	/**
	 * Returns the primary key of this auditado data api.
	 *
	 * @return the primary key of this auditado data api
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the procesado of this auditado data api.
	 *
	 * @return the procesado of this auditado data api
	 */
	@Override
	public Integer getProcesado() {
		return model.getProcesado();
	}

	/**
	 * Returns the procesado date of this auditado data api.
	 *
	 * @return the procesado date of this auditado data api
	 */
	@Override
	public Date getProcesadoDate() {
		return model.getProcesadoDate();
	}

	/**
	 * Returns the state of this auditado data api.
	 *
	 * @return the state of this auditado data api
	 */
	@Override
	public boolean getState() {
		return model.getState();
	}

	/**
	 * Returns the user ID change state of this auditado data api.
	 *
	 * @return the user ID change state of this auditado data api
	 */
	@Override
	public long getUserIdChangeState() {
		return model.getUserIdChangeState();
	}

	/**
	 * Returns <code>true</code> if this auditado data api is state.
	 *
	 * @return <code>true</code> if this auditado data api is state; <code>false</code> otherwise
	 */
	@Override
	public boolean isState() {
		return model.isState();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the app of this auditado data api.
	 *
	 * @param app the app of this auditado data api
	 */
	@Override
	public void setApp(String app) {
		model.setApp(app);
	}

	/**
	 * Sets the change state date of this auditado data api.
	 *
	 * @param changeStateDate the change state date of this auditado data api
	 */
	@Override
	public void setChangeStateDate(Date changeStateDate) {
		model.setChangeStateDate(changeStateDate);
	}

	/**
	 * Sets the cif of this auditado data api.
	 *
	 * @param cif the cif of this auditado data api
	 */
	@Override
	public void setCif(String cif) {
		model.setCif(cif);
	}

	/**
	 * Sets the evento of this auditado data api.
	 *
	 * @param evento the evento of this auditado data api
	 */
	@Override
	public void setEvento(String evento) {
		model.setEvento(evento);
	}

	/**
	 * Sets the id client of this auditado data api.
	 *
	 * @param idClient the id client of this auditado data api
	 */
	@Override
	public void setIdClient(long idClient) {
		model.setIdClient(idClient);
	}

	/**
	 * Sets the id contract of this auditado data api.
	 *
	 * @param idContract the id contract of this auditado data api
	 */
	@Override
	public void setIdContract(long idContract) {
		model.setIdContract(idContract);
	}

	/**
	 * Sets the id data temporal of this auditado data api.
	 *
	 * @param idDataTemporal the id data temporal of this auditado data api
	 */
	@Override
	public void setIdDataTemporal(long idDataTemporal) {
		model.setIdDataTemporal(idDataTemporal);
	}

	/**
	 * Sets the mensaje of this auditado data api.
	 *
	 * @param mensaje the mensaje of this auditado data api
	 */
	@Override
	public void setMensaje(String mensaje) {
		model.setMensaje(mensaje);
	}

	/**
	 * Sets the primary key of this auditado data api.
	 *
	 * @param primaryKey the primary key of this auditado data api
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the procesado of this auditado data api.
	 *
	 * @param procesado the procesado of this auditado data api
	 */
	@Override
	public void setProcesado(Integer procesado) {
		model.setProcesado(procesado);
	}

	/**
	 * Sets the procesado date of this auditado data api.
	 *
	 * @param procesadoDate the procesado date of this auditado data api
	 */
	@Override
	public void setProcesadoDate(Date procesadoDate) {
		model.setProcesadoDate(procesadoDate);
	}

	/**
	 * Sets whether this auditado data api is state.
	 *
	 * @param state the state of this auditado data api
	 */
	@Override
	public void setState(boolean state) {
		model.setState(state);
	}

	/**
	 * Sets the user ID change state of this auditado data api.
	 *
	 * @param userIdChangeState the user ID change state of this auditado data api
	 */
	@Override
	public void setUserIdChangeState(long userIdChangeState) {
		model.setUserIdChangeState(userIdChangeState);
	}

	@Override
	protected AuditadoDataApiWrapper wrap(AuditadoDataApi auditadoDataApi) {
		return new AuditadoDataApiWrapper(auditadoDataApi);
	}

}