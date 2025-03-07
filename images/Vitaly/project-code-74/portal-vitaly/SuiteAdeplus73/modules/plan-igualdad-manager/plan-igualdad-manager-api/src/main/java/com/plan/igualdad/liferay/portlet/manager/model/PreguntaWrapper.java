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
 * This class is a wrapper for {@link Pregunta}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Pregunta
 * @generated
 */
public class PreguntaWrapper
	extends BaseModelWrapper<Pregunta>
	implements ModelWrapper<Pregunta>, Pregunta {

	public PreguntaWrapper(Pregunta pregunta) {
		super(pregunta);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("preguntaId", getPreguntaId());
		attributes.put("seccionId", getSeccionId());
		attributes.put("tipo", getTipo());
		attributes.put("pregunta", getPregunta());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long preguntaId = (Long)attributes.get("preguntaId");

		if (preguntaId != null) {
			setPreguntaId(preguntaId);
		}

		Long seccionId = (Long)attributes.get("seccionId");

		if (seccionId != null) {
			setSeccionId(seccionId);
		}

		String tipo = (String)attributes.get("tipo");

		if (tipo != null) {
			setTipo(tipo);
		}

		String pregunta = (String)attributes.get("pregunta");

		if (pregunta != null) {
			setPregunta(pregunta);
		}
	}

	/**
	 * Returns the pregunta of this pregunta.
	 *
	 * @return the pregunta of this pregunta
	 */
	@Override
	public String getPregunta() {
		return model.getPregunta();
	}

	/**
	 * Returns the pregunta ID of this pregunta.
	 *
	 * @return the pregunta ID of this pregunta
	 */
	@Override
	public long getPreguntaId() {
		return model.getPreguntaId();
	}

	/**
	 * Returns the primary key of this pregunta.
	 *
	 * @return the primary key of this pregunta
	 */
	@Override
	public
		com.plan.igualdad.liferay.portlet.manager.service.persistence.PreguntaPK
			getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the seccion ID of this pregunta.
	 *
	 * @return the seccion ID of this pregunta
	 */
	@Override
	public long getSeccionId() {
		return model.getSeccionId();
	}

	/**
	 * Returns the tipo of this pregunta.
	 *
	 * @return the tipo of this pregunta
	 */
	@Override
	public String getTipo() {
		return model.getTipo();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the pregunta of this pregunta.
	 *
	 * @param pregunta the pregunta of this pregunta
	 */
	@Override
	public void setPregunta(String pregunta) {
		model.setPregunta(pregunta);
	}

	/**
	 * Sets the pregunta ID of this pregunta.
	 *
	 * @param preguntaId the pregunta ID of this pregunta
	 */
	@Override
	public void setPreguntaId(long preguntaId) {
		model.setPreguntaId(preguntaId);
	}

	/**
	 * Sets the primary key of this pregunta.
	 *
	 * @param primaryKey the primary key of this pregunta
	 */
	@Override
	public void setPrimaryKey(
		com.plan.igualdad.liferay.portlet.manager.service.persistence.PreguntaPK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the seccion ID of this pregunta.
	 *
	 * @param seccionId the seccion ID of this pregunta
	 */
	@Override
	public void setSeccionId(long seccionId) {
		model.setSeccionId(seccionId);
	}

	/**
	 * Sets the tipo of this pregunta.
	 *
	 * @param tipo the tipo of this pregunta
	 */
	@Override
	public void setTipo(String tipo) {
		model.setTipo(tipo);
	}

	@Override
	protected PreguntaWrapper wrap(Pregunta pregunta) {
		return new PreguntaWrapper(pregunta);
	}

}