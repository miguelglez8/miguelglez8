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

import com.plan.igualdad.liferay.portlet.manager.service.persistence.OrganizacionPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.OrganizacionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class OrganizacionSoap implements Serializable {

	public static OrganizacionSoap toSoapModel(Organizacion model) {
		OrganizacionSoap soapModel = new OrganizacionSoap();

		soapModel.setOrganizacionId(model.getOrganizacionId());
		soapModel.setVersionId(model.getVersionId());
		soapModel.setCompId(model.getCompId());
		soapModel.setAnnoConstitucion(model.getAnnoConstitucion());
		soapModel.setPaginaWeb(model.getPaginaWeb());
		soapModel.setDomicilioSocial(model.getDomicilioSocial());
		soapModel.setFacturacionAnual(model.getFacturacionAnual());
		soapModel.setFormaJuridica(model.getFormaJuridica());
		soapModel.setNombreResponsableEntidad(
			model.getNombreResponsableEntidad());
		soapModel.setTelefonoResponsableEntidad(
			model.getTelefonoResponsableEntidad());
		soapModel.setCargoResponsableEntidad(
			model.getCargoResponsableEntidad());
		soapModel.setEmailResponsableEntidad(
			model.getEmailResponsableEntidad());
		soapModel.setNombreResponsableIgualdad(
			model.getNombreResponsableIgualdad());
		soapModel.setTelefonoResponsableIgualdad(
			model.getTelefonoResponsableIgualdad());
		soapModel.setCargoResponsableIgualdad(
			model.getCargoResponsableIgualdad());
		soapModel.setEmailResponsableIgualdad(
			model.getEmailResponsableIgualdad());
		soapModel.setCnaes(model.getCnaes());
		soapModel.setNCentros(model.getNCentros());
		soapModel.setDescripcionActividad(model.getDescripcionActividad());
		soapModel.setConvenio(model.getConvenio());
		soapModel.setAmbito(model.getAmbito());
		soapModel.setComentarioAmbito(model.getComentarioAmbito());
		soapModel.setNHombresPlantilla(model.getNHombresPlantilla());
		soapModel.setNMujeresPlantilla(model.getNMujeresPlantilla());
		soapModel.setRepresentacionLegal(model.getRepresentacionLegal());
		soapModel.setNRepresentacionLegalHombres(
			model.getNRepresentacionLegalHombres());
		soapModel.setNRepresentacionLegalMujeres(
			model.getNRepresentacionLegalMujeres());
		soapModel.setRepresentaTotalidad(model.getRepresentaTotalidad());
		soapModel.setNNoRepresentados(model.getNNoRepresentados());
		soapModel.setComentarioRepresentacion(
			model.getComentarioRepresentacion());
		soapModel.setDepartamentoPersonal(model.getDepartamentoPersonal());
		soapModel.setSindicatos(model.getSindicatos());
		soapModel.setReprComisionOrganizacion(
			model.getReprComisionOrganizacion());
		soapModel.setReprComisionSocial(model.getReprComisionSocial());
		soapModel.setReprPlanOrganizacion(model.getReprPlanOrganizacion());
		soapModel.setReprPlanSociales(model.getReprPlanSociales());

		return soapModel;
	}

	public static OrganizacionSoap[] toSoapModels(Organizacion[] models) {
		OrganizacionSoap[] soapModels = new OrganizacionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OrganizacionSoap[][] toSoapModels(Organizacion[][] models) {
		OrganizacionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OrganizacionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OrganizacionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OrganizacionSoap[] toSoapModels(List<Organizacion> models) {
		List<OrganizacionSoap> soapModels = new ArrayList<OrganizacionSoap>(
			models.size());

		for (Organizacion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OrganizacionSoap[soapModels.size()]);
	}

	public OrganizacionSoap() {
	}

	public OrganizacionPK getPrimaryKey() {
		return new OrganizacionPK(_organizacionId, _versionId, _compId);
	}

	public void setPrimaryKey(OrganizacionPK pk) {
		setOrganizacionId(pk.organizacionId);
		setVersionId(pk.versionId);
		setCompId(pk.compId);
	}

	public long getOrganizacionId() {
		return _organizacionId;
	}

	public void setOrganizacionId(long organizacionId) {
		_organizacionId = organizacionId;
	}

	public long getVersionId() {
		return _versionId;
	}

	public void setVersionId(long versionId) {
		_versionId = versionId;
	}

	public long getCompId() {
		return _compId;
	}

	public void setCompId(long compId) {
		_compId = compId;
	}

	public String getAnnoConstitucion() {
		return _annoConstitucion;
	}

	public void setAnnoConstitucion(String annoConstitucion) {
		_annoConstitucion = annoConstitucion;
	}

	public String getPaginaWeb() {
		return _paginaWeb;
	}

	public void setPaginaWeb(String paginaWeb) {
		_paginaWeb = paginaWeb;
	}

	public String getDomicilioSocial() {
		return _domicilioSocial;
	}

	public void setDomicilioSocial(String domicilioSocial) {
		_domicilioSocial = domicilioSocial;
	}

	public String getFacturacionAnual() {
		return _facturacionAnual;
	}

	public void setFacturacionAnual(String facturacionAnual) {
		_facturacionAnual = facturacionAnual;
	}

	public String getFormaJuridica() {
		return _formaJuridica;
	}

	public void setFormaJuridica(String formaJuridica) {
		_formaJuridica = formaJuridica;
	}

	public String getNombreResponsableEntidad() {
		return _nombreResponsableEntidad;
	}

	public void setNombreResponsableEntidad(String nombreResponsableEntidad) {
		_nombreResponsableEntidad = nombreResponsableEntidad;
	}

	public String getTelefonoResponsableEntidad() {
		return _telefonoResponsableEntidad;
	}

	public void setTelefonoResponsableEntidad(
		String telefonoResponsableEntidad) {

		_telefonoResponsableEntidad = telefonoResponsableEntidad;
	}

	public String getCargoResponsableEntidad() {
		return _cargoResponsableEntidad;
	}

	public void setCargoResponsableEntidad(String cargoResponsableEntidad) {
		_cargoResponsableEntidad = cargoResponsableEntidad;
	}

	public String getEmailResponsableEntidad() {
		return _emailResponsableEntidad;
	}

	public void setEmailResponsableEntidad(String emailResponsableEntidad) {
		_emailResponsableEntidad = emailResponsableEntidad;
	}

	public String getNombreResponsableIgualdad() {
		return _nombreResponsableIgualdad;
	}

	public void setNombreResponsableIgualdad(String nombreResponsableIgualdad) {
		_nombreResponsableIgualdad = nombreResponsableIgualdad;
	}

	public String getTelefonoResponsableIgualdad() {
		return _telefonoResponsableIgualdad;
	}

	public void setTelefonoResponsableIgualdad(
		String telefonoResponsableIgualdad) {

		_telefonoResponsableIgualdad = telefonoResponsableIgualdad;
	}

	public String getCargoResponsableIgualdad() {
		return _cargoResponsableIgualdad;
	}

	public void setCargoResponsableIgualdad(String cargoResponsableIgualdad) {
		_cargoResponsableIgualdad = cargoResponsableIgualdad;
	}

	public String getEmailResponsableIgualdad() {
		return _emailResponsableIgualdad;
	}

	public void setEmailResponsableIgualdad(String emailResponsableIgualdad) {
		_emailResponsableIgualdad = emailResponsableIgualdad;
	}

	public String getCnaes() {
		return _cnaes;
	}

	public void setCnaes(String cnaes) {
		_cnaes = cnaes;
	}

	public long getNCentros() {
		return _nCentros;
	}

	public void setNCentros(long nCentros) {
		_nCentros = nCentros;
	}

	public String getDescripcionActividad() {
		return _descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		_descripcionActividad = descripcionActividad;
	}

	public String getConvenio() {
		return _convenio;
	}

	public void setConvenio(String convenio) {
		_convenio = convenio;
	}

	public String getAmbito() {
		return _ambito;
	}

	public void setAmbito(String ambito) {
		_ambito = ambito;
	}

	public String getComentarioAmbito() {
		return _comentarioAmbito;
	}

	public void setComentarioAmbito(String comentarioAmbito) {
		_comentarioAmbito = comentarioAmbito;
	}

	public long getNHombresPlantilla() {
		return _nHombresPlantilla;
	}

	public void setNHombresPlantilla(long nHombresPlantilla) {
		_nHombresPlantilla = nHombresPlantilla;
	}

	public long getNMujeresPlantilla() {
		return _nMujeresPlantilla;
	}

	public void setNMujeresPlantilla(long nMujeresPlantilla) {
		_nMujeresPlantilla = nMujeresPlantilla;
	}

	public String getRepresentacionLegal() {
		return _representacionLegal;
	}

	public void setRepresentacionLegal(String representacionLegal) {
		_representacionLegal = representacionLegal;
	}

	public long getNRepresentacionLegalHombres() {
		return _nRepresentacionLegalHombres;
	}

	public void setNRepresentacionLegalHombres(
		long nRepresentacionLegalHombres) {

		_nRepresentacionLegalHombres = nRepresentacionLegalHombres;
	}

	public long getNRepresentacionLegalMujeres() {
		return _nRepresentacionLegalMujeres;
	}

	public void setNRepresentacionLegalMujeres(
		long nRepresentacionLegalMujeres) {

		_nRepresentacionLegalMujeres = nRepresentacionLegalMujeres;
	}

	public String getRepresentaTotalidad() {
		return _representaTotalidad;
	}

	public void setRepresentaTotalidad(String representaTotalidad) {
		_representaTotalidad = representaTotalidad;
	}

	public long getNNoRepresentados() {
		return _nNoRepresentados;
	}

	public void setNNoRepresentados(long nNoRepresentados) {
		_nNoRepresentados = nNoRepresentados;
	}

	public String getComentarioRepresentacion() {
		return _comentarioRepresentacion;
	}

	public void setComentarioRepresentacion(String comentarioRepresentacion) {
		_comentarioRepresentacion = comentarioRepresentacion;
	}

	public String getDepartamentoPersonal() {
		return _departamentoPersonal;
	}

	public void setDepartamentoPersonal(String departamentoPersonal) {
		_departamentoPersonal = departamentoPersonal;
	}

	public String getSindicatos() {
		return _sindicatos;
	}

	public void setSindicatos(String sindicatos) {
		_sindicatos = sindicatos;
	}

	public String getReprComisionOrganizacion() {
		return _reprComisionOrganizacion;
	}

	public void setReprComisionOrganizacion(String reprComisionOrganizacion) {
		_reprComisionOrganizacion = reprComisionOrganizacion;
	}

	public String getReprComisionSocial() {
		return _reprComisionSocial;
	}

	public void setReprComisionSocial(String reprComisionSocial) {
		_reprComisionSocial = reprComisionSocial;
	}

	public String getReprPlanOrganizacion() {
		return _reprPlanOrganizacion;
	}

	public void setReprPlanOrganizacion(String reprPlanOrganizacion) {
		_reprPlanOrganizacion = reprPlanOrganizacion;
	}

	public String getReprPlanSociales() {
		return _reprPlanSociales;
	}

	public void setReprPlanSociales(String reprPlanSociales) {
		_reprPlanSociales = reprPlanSociales;
	}

	private long _organizacionId;
	private long _versionId;
	private long _compId;
	private String _annoConstitucion;
	private String _paginaWeb;
	private String _domicilioSocial;
	private String _facturacionAnual;
	private String _formaJuridica;
	private String _nombreResponsableEntidad;
	private String _telefonoResponsableEntidad;
	private String _cargoResponsableEntidad;
	private String _emailResponsableEntidad;
	private String _nombreResponsableIgualdad;
	private String _telefonoResponsableIgualdad;
	private String _cargoResponsableIgualdad;
	private String _emailResponsableIgualdad;
	private String _cnaes;
	private long _nCentros;
	private String _descripcionActividad;
	private String _convenio;
	private String _ambito;
	private String _comentarioAmbito;
	private long _nHombresPlantilla;
	private long _nMujeresPlantilla;
	private String _representacionLegal;
	private long _nRepresentacionLegalHombres;
	private long _nRepresentacionLegalMujeres;
	private String _representaTotalidad;
	private long _nNoRepresentados;
	private String _comentarioRepresentacion;
	private String _departamentoPersonal;
	private String _sindicatos;
	private String _reprComisionOrganizacion;
	private String _reprComisionSocial;
	private String _reprPlanOrganizacion;
	private String _reprPlanSociales;

}