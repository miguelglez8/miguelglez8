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
 * This class is a wrapper for {@link Legislacion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Legislacion
 * @generated
 */
public class LegislacionWrapper
	extends BaseModelWrapper<Legislacion>
	implements Legislacion, ModelWrapper<Legislacion> {

	public LegislacionWrapper(Legislacion legislacion) {
		super(legislacion);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("legislacionId", getLegislacionId());
		attributes.put("nombre", getNombre());
		attributes.put("familia", getFamilia());
		attributes.put("tipo", getTipo());
		attributes.put("ambito", getAmbito());
		attributes.put("publicacion", getPublicacion());
		attributes.put("derogacion", getDerogacion());
		attributes.put("descripcion", getDescripcion());
		attributes.put("etiquetas", getEtiquetas());
		attributes.put("enlace", getEnlace());
		attributes.put("ccaa", getCcaa());
		attributes.put("ayuntamiento", getAyuntamiento());
		attributes.put("urgente", getUrgente());
		attributes.put("empresas", getEmpresas());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String legislacionId = (String)attributes.get("legislacionId");

		if (legislacionId != null) {
			setLegislacionId(legislacionId);
		}

		String nombre = (String)attributes.get("nombre");

		if (nombre != null) {
			setNombre(nombre);
		}

		String familia = (String)attributes.get("familia");

		if (familia != null) {
			setFamilia(familia);
		}

		Long tipo = (Long)attributes.get("tipo");

		if (tipo != null) {
			setTipo(tipo);
		}

		Long ambito = (Long)attributes.get("ambito");

		if (ambito != null) {
			setAmbito(ambito);
		}

		Date publicacion = (Date)attributes.get("publicacion");

		if (publicacion != null) {
			setPublicacion(publicacion);
		}

		Date derogacion = (Date)attributes.get("derogacion");

		if (derogacion != null) {
			setDerogacion(derogacion);
		}

		String descripcion = (String)attributes.get("descripcion");

		if (descripcion != null) {
			setDescripcion(descripcion);
		}

		String etiquetas = (String)attributes.get("etiquetas");

		if (etiquetas != null) {
			setEtiquetas(etiquetas);
		}

		String enlace = (String)attributes.get("enlace");

		if (enlace != null) {
			setEnlace(enlace);
		}

		Long ccaa = (Long)attributes.get("ccaa");

		if (ccaa != null) {
			setCcaa(ccaa);
		}

		Long ayuntamiento = (Long)attributes.get("ayuntamiento");

		if (ayuntamiento != null) {
			setAyuntamiento(ayuntamiento);
		}

		Date urgente = (Date)attributes.get("urgente");

		if (urgente != null) {
			setUrgente(urgente);
		}

		String empresas = (String)attributes.get("empresas");

		if (empresas != null) {
			setEmpresas(empresas);
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
	 * Returns the ambito of this legislacion.
	 *
	 * @return the ambito of this legislacion
	 */
	@Override
	public long getAmbito() {
		return model.getAmbito();
	}

	/**
	 * Returns the ayuntamiento of this legislacion.
	 *
	 * @return the ayuntamiento of this legislacion
	 */
	@Override
	public long getAyuntamiento() {
		return model.getAyuntamiento();
	}

	/**
	 * Returns the ccaa of this legislacion.
	 *
	 * @return the ccaa of this legislacion
	 */
	@Override
	public long getCcaa() {
		return model.getCcaa();
	}

	/**
	 * Returns the create date of this legislacion.
	 *
	 * @return the create date of this legislacion
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the derogacion of this legislacion.
	 *
	 * @return the derogacion of this legislacion
	 */
	@Override
	public Date getDerogacion() {
		return model.getDerogacion();
	}

	/**
	 * Returns the descripcion of this legislacion.
	 *
	 * @return the descripcion of this legislacion
	 */
	@Override
	public String getDescripcion() {
		return model.getDescripcion();
	}

	/**
	 * Returns the empresas of this legislacion.
	 *
	 * @return the empresas of this legislacion
	 */
	@Override
	public String getEmpresas() {
		return model.getEmpresas();
	}

	/**
	 * Returns the enlace of this legislacion.
	 *
	 * @return the enlace of this legislacion
	 */
	@Override
	public String getEnlace() {
		return model.getEnlace();
	}

	/**
	 * Returns the etiquetas of this legislacion.
	 *
	 * @return the etiquetas of this legislacion
	 */
	@Override
	public String getEtiquetas() {
		return model.getEtiquetas();
	}

	/**
	 * Returns the familia of this legislacion.
	 *
	 * @return the familia of this legislacion
	 */
	@Override
	public String getFamilia() {
		return model.getFamilia();
	}

	/**
	 * Returns the legislacion ID of this legislacion.
	 *
	 * @return the legislacion ID of this legislacion
	 */
	@Override
	public String getLegislacionId() {
		return model.getLegislacionId();
	}

	/**
	 * Returns the modified date of this legislacion.
	 *
	 * @return the modified date of this legislacion
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the nombre of this legislacion.
	 *
	 * @return the nombre of this legislacion
	 */
	@Override
	public String getNombre() {
		return model.getNombre();
	}

	/**
	 * Returns the primary key of this legislacion.
	 *
	 * @return the primary key of this legislacion
	 */
	@Override
	public String getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the publicacion of this legislacion.
	 *
	 * @return the publicacion of this legislacion
	 */
	@Override
	public Date getPublicacion() {
		return model.getPublicacion();
	}

	/**
	 * Returns the tipo of this legislacion.
	 *
	 * @return the tipo of this legislacion
	 */
	@Override
	public long getTipo() {
		return model.getTipo();
	}

	/**
	 * Returns the urgente of this legislacion.
	 *
	 * @return the urgente of this legislacion
	 */
	@Override
	public Date getUrgente() {
		return model.getUrgente();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the ambito of this legislacion.
	 *
	 * @param ambito the ambito of this legislacion
	 */
	@Override
	public void setAmbito(long ambito) {
		model.setAmbito(ambito);
	}

	/**
	 * Sets the ayuntamiento of this legislacion.
	 *
	 * @param ayuntamiento the ayuntamiento of this legislacion
	 */
	@Override
	public void setAyuntamiento(long ayuntamiento) {
		model.setAyuntamiento(ayuntamiento);
	}

	/**
	 * Sets the ccaa of this legislacion.
	 *
	 * @param ccaa the ccaa of this legislacion
	 */
	@Override
	public void setCcaa(long ccaa) {
		model.setCcaa(ccaa);
	}

	/**
	 * Sets the create date of this legislacion.
	 *
	 * @param createDate the create date of this legislacion
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the derogacion of this legislacion.
	 *
	 * @param derogacion the derogacion of this legislacion
	 */
	@Override
	public void setDerogacion(Date derogacion) {
		model.setDerogacion(derogacion);
	}

	/**
	 * Sets the descripcion of this legislacion.
	 *
	 * @param descripcion the descripcion of this legislacion
	 */
	@Override
	public void setDescripcion(String descripcion) {
		model.setDescripcion(descripcion);
	}

	/**
	 * Sets the empresas of this legislacion.
	 *
	 * @param empresas the empresas of this legislacion
	 */
	@Override
	public void setEmpresas(String empresas) {
		model.setEmpresas(empresas);
	}

	/**
	 * Sets the enlace of this legislacion.
	 *
	 * @param enlace the enlace of this legislacion
	 */
	@Override
	public void setEnlace(String enlace) {
		model.setEnlace(enlace);
	}

	/**
	 * Sets the etiquetas of this legislacion.
	 *
	 * @param etiquetas the etiquetas of this legislacion
	 */
	@Override
	public void setEtiquetas(String etiquetas) {
		model.setEtiquetas(etiquetas);
	}

	/**
	 * Sets the familia of this legislacion.
	 *
	 * @param familia the familia of this legislacion
	 */
	@Override
	public void setFamilia(String familia) {
		model.setFamilia(familia);
	}

	/**
	 * Sets the legislacion ID of this legislacion.
	 *
	 * @param legislacionId the legislacion ID of this legislacion
	 */
	@Override
	public void setLegislacionId(String legislacionId) {
		model.setLegislacionId(legislacionId);
	}

	/**
	 * Sets the modified date of this legislacion.
	 *
	 * @param modifiedDate the modified date of this legislacion
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the nombre of this legislacion.
	 *
	 * @param nombre the nombre of this legislacion
	 */
	@Override
	public void setNombre(String nombre) {
		model.setNombre(nombre);
	}

	/**
	 * Sets the primary key of this legislacion.
	 *
	 * @param primaryKey the primary key of this legislacion
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the publicacion of this legislacion.
	 *
	 * @param publicacion the publicacion of this legislacion
	 */
	@Override
	public void setPublicacion(Date publicacion) {
		model.setPublicacion(publicacion);
	}

	/**
	 * Sets the tipo of this legislacion.
	 *
	 * @param tipo the tipo of this legislacion
	 */
	@Override
	public void setTipo(long tipo) {
		model.setTipo(tipo);
	}

	/**
	 * Sets the urgente of this legislacion.
	 *
	 * @param urgente the urgente of this legislacion
	 */
	@Override
	public void setUrgente(Date urgente) {
		model.setUrgente(urgente);
	}

	@Override
	protected LegislacionWrapper wrap(Legislacion legislacion) {
		return new LegislacionWrapper(legislacion);
	}

}