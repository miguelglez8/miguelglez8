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
 * This class is a wrapper for {@link Denuncia}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Denuncia
 * @generated
 */
public class DenunciaWrapper
	extends BaseModelWrapper<Denuncia>
	implements Denuncia, ModelWrapper<Denuncia> {

	public DenunciaWrapper(Denuncia denuncia) {
		super(denuncia);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("denunciaId", getDenunciaId());
		attributes.put("codigo", getCodigo());
		attributes.put("cif", getCif());
		attributes.put("compId", getCompId());
		attributes.put("vinculacion", getVinculacion());
		attributes.put("email", getEmail());
		attributes.put("nombre", getNombre());
		attributes.put("apellidos", getApellidos());
		attributes.put("nif", getNif());
		attributes.put("telefono", getTelefono());
		attributes.put("anonimo", isAnonimo());
		attributes.put("tipo", getTipo());
		attributes.put("categoria", getCategoria());
		attributes.put("asunto", getAsunto());
		attributes.put("descripcion", getDescripcion());
		attributes.put("estado", getEstado());
		attributes.put("motivoId", getMotivoId());
		attributes.put("observaciones", getObservaciones());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("endDate", getEndDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long denunciaId = (Long)attributes.get("denunciaId");

		if (denunciaId != null) {
			setDenunciaId(denunciaId);
		}

		String codigo = (String)attributes.get("codigo");

		if (codigo != null) {
			setCodigo(codigo);
		}

		String cif = (String)attributes.get("cif");

		if (cif != null) {
			setCif(cif);
		}

		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		String vinculacion = (String)attributes.get("vinculacion");

		if (vinculacion != null) {
			setVinculacion(vinculacion);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String nombre = (String)attributes.get("nombre");

		if (nombre != null) {
			setNombre(nombre);
		}

		String apellidos = (String)attributes.get("apellidos");

		if (apellidos != null) {
			setApellidos(apellidos);
		}

		String nif = (String)attributes.get("nif");

		if (nif != null) {
			setNif(nif);
		}

		String telefono = (String)attributes.get("telefono");

		if (telefono != null) {
			setTelefono(telefono);
		}

		Boolean anonimo = (Boolean)attributes.get("anonimo");

		if (anonimo != null) {
			setAnonimo(anonimo);
		}

		Long tipo = (Long)attributes.get("tipo");

		if (tipo != null) {
			setTipo(tipo);
		}

		Long categoria = (Long)attributes.get("categoria");

		if (categoria != null) {
			setCategoria(categoria);
		}

		String asunto = (String)attributes.get("asunto");

		if (asunto != null) {
			setAsunto(asunto);
		}

		String descripcion = (String)attributes.get("descripcion");

		if (descripcion != null) {
			setDescripcion(descripcion);
		}

		Long estado = (Long)attributes.get("estado");

		if (estado != null) {
			setEstado(estado);
		}

		Long motivoId = (Long)attributes.get("motivoId");

		if (motivoId != null) {
			setMotivoId(motivoId);
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

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}
	}

	/**
	 * Returns the anonimo of this denuncia.
	 *
	 * @return the anonimo of this denuncia
	 */
	@Override
	public boolean getAnonimo() {
		return model.getAnonimo();
	}

	/**
	 * Returns the apellidos of this denuncia.
	 *
	 * @return the apellidos of this denuncia
	 */
	@Override
	public String getApellidos() {
		return model.getApellidos();
	}

	/**
	 * Returns the asunto of this denuncia.
	 *
	 * @return the asunto of this denuncia
	 */
	@Override
	public String getAsunto() {
		return model.getAsunto();
	}

	/**
	 * Returns the categoria of this denuncia.
	 *
	 * @return the categoria of this denuncia
	 */
	@Override
	public long getCategoria() {
		return model.getCategoria();
	}

	/**
	 * Returns the cif of this denuncia.
	 *
	 * @return the cif of this denuncia
	 */
	@Override
	public String getCif() {
		return model.getCif();
	}

	/**
	 * Returns the codigo of this denuncia.
	 *
	 * @return the codigo of this denuncia
	 */
	@Override
	public String getCodigo() {
		return model.getCodigo();
	}

	/**
	 * Returns the comp ID of this denuncia.
	 *
	 * @return the comp ID of this denuncia
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the create date of this denuncia.
	 *
	 * @return the create date of this denuncia
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the denuncia ID of this denuncia.
	 *
	 * @return the denuncia ID of this denuncia
	 */
	@Override
	public long getDenunciaId() {
		return model.getDenunciaId();
	}

	/**
	 * Returns the descripcion of this denuncia.
	 *
	 * @return the descripcion of this denuncia
	 */
	@Override
	public String getDescripcion() {
		return model.getDescripcion();
	}

	/**
	 * Returns the email of this denuncia.
	 *
	 * @return the email of this denuncia
	 */
	@Override
	public String getEmail() {
		return model.getEmail();
	}

	/**
	 * Returns the end date of this denuncia.
	 *
	 * @return the end date of this denuncia
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the estado of this denuncia.
	 *
	 * @return the estado of this denuncia
	 */
	@Override
	public long getEstado() {
		return model.getEstado();
	}

	/**
	 * Returns the modified date of this denuncia.
	 *
	 * @return the modified date of this denuncia
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the motivo ID of this denuncia.
	 *
	 * @return the motivo ID of this denuncia
	 */
	@Override
	public long getMotivoId() {
		return model.getMotivoId();
	}

	/**
	 * Returns the nif of this denuncia.
	 *
	 * @return the nif of this denuncia
	 */
	@Override
	public String getNif() {
		return model.getNif();
	}

	/**
	 * Returns the nombre of this denuncia.
	 *
	 * @return the nombre of this denuncia
	 */
	@Override
	public String getNombre() {
		return model.getNombre();
	}

	/**
	 * Returns the observaciones of this denuncia.
	 *
	 * @return the observaciones of this denuncia
	 */
	@Override
	public String getObservaciones() {
		return model.getObservaciones();
	}

	/**
	 * Returns the primary key of this denuncia.
	 *
	 * @return the primary key of this denuncia
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the telefono of this denuncia.
	 *
	 * @return the telefono of this denuncia
	 */
	@Override
	public String getTelefono() {
		return model.getTelefono();
	}

	/**
	 * Returns the tipo of this denuncia.
	 *
	 * @return the tipo of this denuncia
	 */
	@Override
	public long getTipo() {
		return model.getTipo();
	}

	/**
	 * Returns the vinculacion of this denuncia.
	 *
	 * @return the vinculacion of this denuncia
	 */
	@Override
	public String getVinculacion() {
		return model.getVinculacion();
	}

	/**
	 * Returns <code>true</code> if this denuncia is anonimo.
	 *
	 * @return <code>true</code> if this denuncia is anonimo; <code>false</code> otherwise
	 */
	@Override
	public boolean isAnonimo() {
		return model.isAnonimo();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this denuncia is anonimo.
	 *
	 * @param anonimo the anonimo of this denuncia
	 */
	@Override
	public void setAnonimo(boolean anonimo) {
		model.setAnonimo(anonimo);
	}

	/**
	 * Sets the apellidos of this denuncia.
	 *
	 * @param apellidos the apellidos of this denuncia
	 */
	@Override
	public void setApellidos(String apellidos) {
		model.setApellidos(apellidos);
	}

	/**
	 * Sets the asunto of this denuncia.
	 *
	 * @param asunto the asunto of this denuncia
	 */
	@Override
	public void setAsunto(String asunto) {
		model.setAsunto(asunto);
	}

	/**
	 * Sets the categoria of this denuncia.
	 *
	 * @param categoria the categoria of this denuncia
	 */
	@Override
	public void setCategoria(long categoria) {
		model.setCategoria(categoria);
	}

	/**
	 * Sets the cif of this denuncia.
	 *
	 * @param cif the cif of this denuncia
	 */
	@Override
	public void setCif(String cif) {
		model.setCif(cif);
	}

	/**
	 * Sets the codigo of this denuncia.
	 *
	 * @param codigo the codigo of this denuncia
	 */
	@Override
	public void setCodigo(String codigo) {
		model.setCodigo(codigo);
	}

	/**
	 * Sets the comp ID of this denuncia.
	 *
	 * @param compId the comp ID of this denuncia
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the create date of this denuncia.
	 *
	 * @param createDate the create date of this denuncia
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the denuncia ID of this denuncia.
	 *
	 * @param denunciaId the denuncia ID of this denuncia
	 */
	@Override
	public void setDenunciaId(long denunciaId) {
		model.setDenunciaId(denunciaId);
	}

	/**
	 * Sets the descripcion of this denuncia.
	 *
	 * @param descripcion the descripcion of this denuncia
	 */
	@Override
	public void setDescripcion(String descripcion) {
		model.setDescripcion(descripcion);
	}

	/**
	 * Sets the email of this denuncia.
	 *
	 * @param email the email of this denuncia
	 */
	@Override
	public void setEmail(String email) {
		model.setEmail(email);
	}

	/**
	 * Sets the end date of this denuncia.
	 *
	 * @param endDate the end date of this denuncia
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the estado of this denuncia.
	 *
	 * @param estado the estado of this denuncia
	 */
	@Override
	public void setEstado(long estado) {
		model.setEstado(estado);
	}

	/**
	 * Sets the modified date of this denuncia.
	 *
	 * @param modifiedDate the modified date of this denuncia
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the motivo ID of this denuncia.
	 *
	 * @param motivoId the motivo ID of this denuncia
	 */
	@Override
	public void setMotivoId(long motivoId) {
		model.setMotivoId(motivoId);
	}

	/**
	 * Sets the nif of this denuncia.
	 *
	 * @param nif the nif of this denuncia
	 */
	@Override
	public void setNif(String nif) {
		model.setNif(nif);
	}

	/**
	 * Sets the nombre of this denuncia.
	 *
	 * @param nombre the nombre of this denuncia
	 */
	@Override
	public void setNombre(String nombre) {
		model.setNombre(nombre);
	}

	/**
	 * Sets the observaciones of this denuncia.
	 *
	 * @param observaciones the observaciones of this denuncia
	 */
	@Override
	public void setObservaciones(String observaciones) {
		model.setObservaciones(observaciones);
	}

	/**
	 * Sets the primary key of this denuncia.
	 *
	 * @param primaryKey the primary key of this denuncia
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the telefono of this denuncia.
	 *
	 * @param telefono the telefono of this denuncia
	 */
	@Override
	public void setTelefono(String telefono) {
		model.setTelefono(telefono);
	}

	/**
	 * Sets the tipo of this denuncia.
	 *
	 * @param tipo the tipo of this denuncia
	 */
	@Override
	public void setTipo(long tipo) {
		model.setTipo(tipo);
	}

	/**
	 * Sets the vinculacion of this denuncia.
	 *
	 * @param vinculacion the vinculacion of this denuncia
	 */
	@Override
	public void setVinculacion(String vinculacion) {
		model.setVinculacion(vinculacion);
	}

	@Override
	protected DenunciaWrapper wrap(Denuncia denuncia) {
		return new DenunciaWrapper(denuncia);
	}

}