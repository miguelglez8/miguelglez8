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

package com.legalplus.liferay.portlet.legalplus.manager.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link EvalRequisito}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EvalRequisito
 * @generated
 */
public class EvalRequisitoWrapper
	extends BaseModelWrapper<EvalRequisito>
	implements EvalRequisito, ModelWrapper<EvalRequisito> {

	public EvalRequisitoWrapper(EvalRequisito evalRequisito) {
		super(evalRequisito);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("version", getVersion());
		attributes.put("compId", getCompId());
		attributes.put("legislacionId", getLegislacionId());
		attributes.put("requisitoId", getRequisitoId());
		attributes.put("fecha", getFecha());
		attributes.put("proxima", getProxima());
		attributes.put("cumplimiento", getCumplimiento());
		attributes.put("usuario", getUsuario());
		attributes.put("adjunto", getAdjunto());
		attributes.put("observaciones", getObservaciones());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long version = (Long)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		String legislacionId = (String)attributes.get("legislacionId");

		if (legislacionId != null) {
			setLegislacionId(legislacionId);
		}

		String requisitoId = (String)attributes.get("requisitoId");

		if (requisitoId != null) {
			setRequisitoId(requisitoId);
		}

		Date fecha = (Date)attributes.get("fecha");

		if (fecha != null) {
			setFecha(fecha);
		}

		Date proxima = (Date)attributes.get("proxima");

		if (proxima != null) {
			setProxima(proxima);
		}

		Integer cumplimiento = (Integer)attributes.get("cumplimiento");

		if (cumplimiento != null) {
			setCumplimiento(cumplimiento);
		}

		Long usuario = (Long)attributes.get("usuario");

		if (usuario != null) {
			setUsuario(usuario);
		}

		Long adjunto = (Long)attributes.get("adjunto");

		if (adjunto != null) {
			setAdjunto(adjunto);
		}

		String observaciones = (String)attributes.get("observaciones");

		if (observaciones != null) {
			setObservaciones(observaciones);
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
	 * Returns the adjunto of this eval requisito.
	 *
	 * @return the adjunto of this eval requisito
	 */
	@Override
	public long getAdjunto() {
		return model.getAdjunto();
	}

	/**
	 * Returns the comp ID of this eval requisito.
	 *
	 * @return the comp ID of this eval requisito
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the create date of this eval requisito.
	 *
	 * @return the create date of this eval requisito
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the cumplimiento of this eval requisito.
	 *
	 * @return the cumplimiento of this eval requisito
	 */
	@Override
	public int getCumplimiento() {
		return model.getCumplimiento();
	}

	/**
	 * Returns the fecha of this eval requisito.
	 *
	 * @return the fecha of this eval requisito
	 */
	@Override
	public Date getFecha() {
		return model.getFecha();
	}

	/**
	 * Returns the legislacion ID of this eval requisito.
	 *
	 * @return the legislacion ID of this eval requisito
	 */
	@Override
	public String getLegislacionId() {
		return model.getLegislacionId();
	}

	/**
	 * Returns the modified date of this eval requisito.
	 *
	 * @return the modified date of this eval requisito
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the observaciones of this eval requisito.
	 *
	 * @return the observaciones of this eval requisito
	 */
	@Override
	public String getObservaciones() {
		return model.getObservaciones();
	}

	/**
	 * Returns the primary key of this eval requisito.
	 *
	 * @return the primary key of this eval requisito
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
		EvalRequisitoPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the proxima of this eval requisito.
	 *
	 * @return the proxima of this eval requisito
	 */
	@Override
	public Date getProxima() {
		return model.getProxima();
	}

	/**
	 * Returns the requisito ID of this eval requisito.
	 *
	 * @return the requisito ID of this eval requisito
	 */
	@Override
	public String getRequisitoId() {
		return model.getRequisitoId();
	}

	/**
	 * Returns the usuario of this eval requisito.
	 *
	 * @return the usuario of this eval requisito
	 */
	@Override
	public long getUsuario() {
		return model.getUsuario();
	}

	/**
	 * Returns the version of this eval requisito.
	 *
	 * @return the version of this eval requisito
	 */
	@Override
	public long getVersion() {
		return model.getVersion();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the adjunto of this eval requisito.
	 *
	 * @param adjunto the adjunto of this eval requisito
	 */
	@Override
	public void setAdjunto(long adjunto) {
		model.setAdjunto(adjunto);
	}

	/**
	 * Sets the comp ID of this eval requisito.
	 *
	 * @param compId the comp ID of this eval requisito
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the create date of this eval requisito.
	 *
	 * @param createDate the create date of this eval requisito
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the cumplimiento of this eval requisito.
	 *
	 * @param cumplimiento the cumplimiento of this eval requisito
	 */
	@Override
	public void setCumplimiento(int cumplimiento) {
		model.setCumplimiento(cumplimiento);
	}

	/**
	 * Sets the fecha of this eval requisito.
	 *
	 * @param fecha the fecha of this eval requisito
	 */
	@Override
	public void setFecha(Date fecha) {
		model.setFecha(fecha);
	}

	/**
	 * Sets the legislacion ID of this eval requisito.
	 *
	 * @param legislacionId the legislacion ID of this eval requisito
	 */
	@Override
	public void setLegislacionId(String legislacionId) {
		model.setLegislacionId(legislacionId);
	}

	/**
	 * Sets the modified date of this eval requisito.
	 *
	 * @param modifiedDate the modified date of this eval requisito
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the observaciones of this eval requisito.
	 *
	 * @param observaciones the observaciones of this eval requisito
	 */
	@Override
	public void setObservaciones(String observaciones) {
		model.setObservaciones(observaciones);
	}

	/**
	 * Sets the primary key of this eval requisito.
	 *
	 * @param primaryKey the primary key of this eval requisito
	 */
	@Override
	public void setPrimaryKey(
		com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
			EvalRequisitoPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the proxima of this eval requisito.
	 *
	 * @param proxima the proxima of this eval requisito
	 */
	@Override
	public void setProxima(Date proxima) {
		model.setProxima(proxima);
	}

	/**
	 * Sets the requisito ID of this eval requisito.
	 *
	 * @param requisitoId the requisito ID of this eval requisito
	 */
	@Override
	public void setRequisitoId(String requisitoId) {
		model.setRequisitoId(requisitoId);
	}

	/**
	 * Sets the usuario of this eval requisito.
	 *
	 * @param usuario the usuario of this eval requisito
	 */
	@Override
	public void setUsuario(long usuario) {
		model.setUsuario(usuario);
	}

	/**
	 * Sets the version of this eval requisito.
	 *
	 * @param version the version of this eval requisito
	 */
	@Override
	public void setVersion(long version) {
		model.setVersion(version);
	}

	@Override
	protected EvalRequisitoWrapper wrap(EvalRequisito evalRequisito) {
		return new EvalRequisitoWrapper(evalRequisito);
	}

}