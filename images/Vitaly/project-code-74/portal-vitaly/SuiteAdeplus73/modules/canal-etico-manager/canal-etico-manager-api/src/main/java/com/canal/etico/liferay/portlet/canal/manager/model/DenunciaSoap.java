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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.canal.etico.liferay.portlet.canal.manager.service.http.DenunciaServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DenunciaSoap implements Serializable {

	public static DenunciaSoap toSoapModel(Denuncia model) {
		DenunciaSoap soapModel = new DenunciaSoap();

		soapModel.setDenunciaId(model.getDenunciaId());
		soapModel.setCodigo(model.getCodigo());
		soapModel.setCif(model.getCif());
		soapModel.setCompId(model.getCompId());
		soapModel.setVinculacion(model.getVinculacion());
		soapModel.setEmail(model.getEmail());
		soapModel.setNombre(model.getNombre());
		soapModel.setApellidos(model.getApellidos());
		soapModel.setNif(model.getNif());
		soapModel.setTelefono(model.getTelefono());
		soapModel.setAnonimo(model.isAnonimo());
		soapModel.setTipo(model.getTipo());
		soapModel.setCategoria(model.getCategoria());
		soapModel.setAsunto(model.getAsunto());
		soapModel.setDescripcion(model.getDescripcion());
		soapModel.setEstado(model.getEstado());
		soapModel.setMotivoId(model.getMotivoId());
		soapModel.setObservaciones(model.getObservaciones());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEndDate(model.getEndDate());

		return soapModel;
	}

	public static DenunciaSoap[] toSoapModels(Denuncia[] models) {
		DenunciaSoap[] soapModels = new DenunciaSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DenunciaSoap[][] toSoapModels(Denuncia[][] models) {
		DenunciaSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DenunciaSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DenunciaSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DenunciaSoap[] toSoapModels(List<Denuncia> models) {
		List<DenunciaSoap> soapModels = new ArrayList<DenunciaSoap>(
			models.size());

		for (Denuncia model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DenunciaSoap[soapModels.size()]);
	}

	public DenunciaSoap() {
	}

	public long getPrimaryKey() {
		return _denunciaId;
	}

	public void setPrimaryKey(long pk) {
		setDenunciaId(pk);
	}

	public long getDenunciaId() {
		return _denunciaId;
	}

	public void setDenunciaId(long denunciaId) {
		_denunciaId = denunciaId;
	}

	public String getCodigo() {
		return _codigo;
	}

	public void setCodigo(String codigo) {
		_codigo = codigo;
	}

	public String getCif() {
		return _cif;
	}

	public void setCif(String cif) {
		_cif = cif;
	}

	public long getCompId() {
		return _compId;
	}

	public void setCompId(long compId) {
		_compId = compId;
	}

	public String getVinculacion() {
		return _vinculacion;
	}

	public void setVinculacion(String vinculacion) {
		_vinculacion = vinculacion;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		_nombre = nombre;
	}

	public String getApellidos() {
		return _apellidos;
	}

	public void setApellidos(String apellidos) {
		_apellidos = apellidos;
	}

	public String getNif() {
		return _nif;
	}

	public void setNif(String nif) {
		_nif = nif;
	}

	public String getTelefono() {
		return _telefono;
	}

	public void setTelefono(String telefono) {
		_telefono = telefono;
	}

	public boolean getAnonimo() {
		return _anonimo;
	}

	public boolean isAnonimo() {
		return _anonimo;
	}

	public void setAnonimo(boolean anonimo) {
		_anonimo = anonimo;
	}

	public long getTipo() {
		return _tipo;
	}

	public void setTipo(long tipo) {
		_tipo = tipo;
	}

	public long getCategoria() {
		return _categoria;
	}

	public void setCategoria(long categoria) {
		_categoria = categoria;
	}

	public String getAsunto() {
		return _asunto;
	}

	public void setAsunto(String asunto) {
		_asunto = asunto;
	}

	public String getDescripcion() {
		return _descripcion;
	}

	public void setDescripcion(String descripcion) {
		_descripcion = descripcion;
	}

	public long getEstado() {
		return _estado;
	}

	public void setEstado(long estado) {
		_estado = estado;
	}

	public long getMotivoId() {
		return _motivoId;
	}

	public void setMotivoId(long motivoId) {
		_motivoId = motivoId;
	}

	public String getObservaciones() {
		return _observaciones;
	}

	public void setObservaciones(String observaciones) {
		_observaciones = observaciones;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	private long _denunciaId;
	private String _codigo;
	private String _cif;
	private long _compId;
	private String _vinculacion;
	private String _email;
	private String _nombre;
	private String _apellidos;
	private String _nif;
	private String _telefono;
	private boolean _anonimo;
	private long _tipo;
	private long _categoria;
	private String _asunto;
	private String _descripcion;
	private long _estado;
	private long _motivoId;
	private String _observaciones;
	private Date _createDate;
	private Date _modifiedDate;
	private Date _endDate;

}