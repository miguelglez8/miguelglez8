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
 * This class is a wrapper for {@link EvalLegislacion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EvalLegislacion
 * @generated
 */
public class EvalLegislacionWrapper
	extends BaseModelWrapper<EvalLegislacion>
	implements EvalLegislacion, ModelWrapper<EvalLegislacion> {

	public EvalLegislacionWrapper(EvalLegislacion evalLegislacion) {
		super(evalLegislacion);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("version", getVersion());
		attributes.put("compId", getCompId());
		attributes.put("legislacionId", getLegislacionId());
		attributes.put("fecha", getFecha());
		attributes.put("proxima", getProxima());
		attributes.put("cumplimiento", getCumplimiento());
		attributes.put("usuario", getUsuario());
		attributes.put("adjunto", getAdjunto());
		attributes.put("observaciones", getObservaciones());

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
	}

	/**
	 * Returns the adjunto of this eval legislacion.
	 *
	 * @return the adjunto of this eval legislacion
	 */
	@Override
	public long getAdjunto() {
		return model.getAdjunto();
	}

	/**
	 * Returns the comp ID of this eval legislacion.
	 *
	 * @return the comp ID of this eval legislacion
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the cumplimiento of this eval legislacion.
	 *
	 * @return the cumplimiento of this eval legislacion
	 */
	@Override
	public int getCumplimiento() {
		return model.getCumplimiento();
	}

	/**
	 * Returns the fecha of this eval legislacion.
	 *
	 * @return the fecha of this eval legislacion
	 */
	@Override
	public Date getFecha() {
		return model.getFecha();
	}

	/**
	 * Returns the legislacion ID of this eval legislacion.
	 *
	 * @return the legislacion ID of this eval legislacion
	 */
	@Override
	public String getLegislacionId() {
		return model.getLegislacionId();
	}

	/**
	 * Returns the observaciones of this eval legislacion.
	 *
	 * @return the observaciones of this eval legislacion
	 */
	@Override
	public String getObservaciones() {
		return model.getObservaciones();
	}

	/**
	 * Returns the primary key of this eval legislacion.
	 *
	 * @return the primary key of this eval legislacion
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
		EvalLegislacionPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the proxima of this eval legislacion.
	 *
	 * @return the proxima of this eval legislacion
	 */
	@Override
	public Date getProxima() {
		return model.getProxima();
	}

	/**
	 * Returns the usuario of this eval legislacion.
	 *
	 * @return the usuario of this eval legislacion
	 */
	@Override
	public long getUsuario() {
		return model.getUsuario();
	}

	/**
	 * Returns the version of this eval legislacion.
	 *
	 * @return the version of this eval legislacion
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
	 * Sets the adjunto of this eval legislacion.
	 *
	 * @param adjunto the adjunto of this eval legislacion
	 */
	@Override
	public void setAdjunto(long adjunto) {
		model.setAdjunto(adjunto);
	}

	/**
	 * Sets the comp ID of this eval legislacion.
	 *
	 * @param compId the comp ID of this eval legislacion
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the cumplimiento of this eval legislacion.
	 *
	 * @param cumplimiento the cumplimiento of this eval legislacion
	 */
	@Override
	public void setCumplimiento(int cumplimiento) {
		model.setCumplimiento(cumplimiento);
	}

	/**
	 * Sets the fecha of this eval legislacion.
	 *
	 * @param fecha the fecha of this eval legislacion
	 */
	@Override
	public void setFecha(Date fecha) {
		model.setFecha(fecha);
	}

	/**
	 * Sets the legislacion ID of this eval legislacion.
	 *
	 * @param legislacionId the legislacion ID of this eval legislacion
	 */
	@Override
	public void setLegislacionId(String legislacionId) {
		model.setLegislacionId(legislacionId);
	}

	/**
	 * Sets the observaciones of this eval legislacion.
	 *
	 * @param observaciones the observaciones of this eval legislacion
	 */
	@Override
	public void setObservaciones(String observaciones) {
		model.setObservaciones(observaciones);
	}

	/**
	 * Sets the primary key of this eval legislacion.
	 *
	 * @param primaryKey the primary key of this eval legislacion
	 */
	@Override
	public void setPrimaryKey(
		com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
			EvalLegislacionPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the proxima of this eval legislacion.
	 *
	 * @param proxima the proxima of this eval legislacion
	 */
	@Override
	public void setProxima(Date proxima) {
		model.setProxima(proxima);
	}

	/**
	 * Sets the usuario of this eval legislacion.
	 *
	 * @param usuario the usuario of this eval legislacion
	 */
	@Override
	public void setUsuario(long usuario) {
		model.setUsuario(usuario);
	}

	/**
	 * Sets the version of this eval legislacion.
	 *
	 * @param version the version of this eval legislacion
	 */
	@Override
	public void setVersion(long version) {
		model.setVersion(version);
	}

	@Override
	protected EvalLegislacionWrapper wrap(EvalLegislacion evalLegislacion) {
		return new EvalLegislacionWrapper(evalLegislacion);
	}

}