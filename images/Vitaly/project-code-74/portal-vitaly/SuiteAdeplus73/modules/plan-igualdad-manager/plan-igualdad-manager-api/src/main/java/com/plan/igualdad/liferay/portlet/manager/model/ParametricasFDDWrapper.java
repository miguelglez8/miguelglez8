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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ParametricasFDD}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ParametricasFDD
 * @generated
 */
public class ParametricasFDDWrapper
	extends BaseModelWrapper<ParametricasFDD>
	implements ModelWrapper<ParametricasFDD>, ParametricasFDD {

	public ParametricasFDDWrapper(ParametricasFDD parametricasFDD) {
		super(parametricasFDD);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("compId", getCompId());
		attributes.put("versionId", getVersionId());
		attributes.put("idParametrica", getIdParametrica());
		attributes.put("tipo", getTipo());
		attributes.put("materia", getMateria());
		attributes.put("contenido", getContenido());
		attributes.put("seccionId", getSeccionId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		Long versionId = (Long)attributes.get("versionId");

		if (versionId != null) {
			setVersionId(versionId);
		}

		String idParametrica = (String)attributes.get("idParametrica");

		if (idParametrica != null) {
			setIdParametrica(idParametrica);
		}

		String tipo = (String)attributes.get("tipo");

		if (tipo != null) {
			setTipo(tipo);
		}

		Integer materia = (Integer)attributes.get("materia");

		if (materia != null) {
			setMateria(materia);
		}

		String contenido = (String)attributes.get("contenido");

		if (contenido != null) {
			setContenido(contenido);
		}

		Long seccionId = (Long)attributes.get("seccionId");

		if (seccionId != null) {
			setSeccionId(seccionId);
		}
	}

	/**
	 * Returns the comp ID of this parametricas fdd.
	 *
	 * @return the comp ID of this parametricas fdd
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the contenido of this parametricas fdd.
	 *
	 * @return the contenido of this parametricas fdd
	 */
	@Override
	public String getContenido() {
		return model.getContenido();
	}

	/**
	 * Returns the id parametrica of this parametricas fdd.
	 *
	 * @return the id parametrica of this parametricas fdd
	 */
	@Override
	public String getIdParametrica() {
		return model.getIdParametrica();
	}

	/**
	 * Returns the materia of this parametricas fdd.
	 *
	 * @return the materia of this parametricas fdd
	 */
	@Override
	public Integer getMateria() {
		return model.getMateria();
	}

	/**
	 * Returns the primary key of this parametricas fdd.
	 *
	 * @return the primary key of this parametricas fdd
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.service.persistence.
		ParametricasFDDPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the seccion ID of this parametricas fdd.
	 *
	 * @return the seccion ID of this parametricas fdd
	 */
	@Override
	public long getSeccionId() {
		return model.getSeccionId();
	}

	/**
	 * Returns the tipo of this parametricas fdd.
	 *
	 * @return the tipo of this parametricas fdd
	 */
	@Override
	public String getTipo() {
		return model.getTipo();
	}

	/**
	 * Returns the version ID of this parametricas fdd.
	 *
	 * @return the version ID of this parametricas fdd
	 */
	@Override
	public long getVersionId() {
		return model.getVersionId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the comp ID of this parametricas fdd.
	 *
	 * @param compId the comp ID of this parametricas fdd
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the contenido of this parametricas fdd.
	 *
	 * @param contenido the contenido of this parametricas fdd
	 */
	@Override
	public void setContenido(String contenido) {
		model.setContenido(contenido);
	}

	/**
	 * Sets the id parametrica of this parametricas fdd.
	 *
	 * @param idParametrica the id parametrica of this parametricas fdd
	 */
	@Override
	public void setIdParametrica(String idParametrica) {
		model.setIdParametrica(idParametrica);
	}

	/**
	 * Sets the materia of this parametricas fdd.
	 *
	 * @param materia the materia of this parametricas fdd
	 */
	@Override
	public void setMateria(Integer materia) {
		model.setMateria(materia);
	}

	/**
	 * Sets the primary key of this parametricas fdd.
	 *
	 * @param primaryKey the primary key of this parametricas fdd
	 */
	@Override
	public void setPrimaryKey(
		com.plan.igualdad.liferay.portlet.manager.service.persistence.
			ParametricasFDDPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the seccion ID of this parametricas fdd.
	 *
	 * @param seccionId the seccion ID of this parametricas fdd
	 */
	@Override
	public void setSeccionId(long seccionId) {
		model.setSeccionId(seccionId);
	}

	/**
	 * Sets the tipo of this parametricas fdd.
	 *
	 * @param tipo the tipo of this parametricas fdd
	 */
	@Override
	public void setTipo(String tipo) {
		model.setTipo(tipo);
	}

	/**
	 * Sets the version ID of this parametricas fdd.
	 *
	 * @param versionId the version ID of this parametricas fdd
	 */
	@Override
	public void setVersionId(long versionId) {
		model.setVersionId(versionId);
	}

	@Override
	protected ParametricasFDDWrapper wrap(ParametricasFDD parametricasFDD) {
		return new ParametricasFDDWrapper(parametricasFDD);
	}

}