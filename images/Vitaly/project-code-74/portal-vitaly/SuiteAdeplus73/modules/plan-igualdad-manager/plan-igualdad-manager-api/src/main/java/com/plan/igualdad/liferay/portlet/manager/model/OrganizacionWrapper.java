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
 * This class is a wrapper for {@link Organizacion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Organizacion
 * @generated
 */
public class OrganizacionWrapper
	extends BaseModelWrapper<Organizacion>
	implements ModelWrapper<Organizacion>, Organizacion {

	public OrganizacionWrapper(Organizacion organizacion) {
		super(organizacion);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("organizacionId", getOrganizacionId());
		attributes.put("versionId", getVersionId());
		attributes.put("compId", getCompId());
		attributes.put("annoConstitucion", getAnnoConstitucion());
		attributes.put("paginaWeb", getPaginaWeb());
		attributes.put("domicilioSocial", getDomicilioSocial());
		attributes.put("facturacionAnual", getFacturacionAnual());
		attributes.put("formaJuridica", getFormaJuridica());
		attributes.put(
			"nombreResponsableEntidad", getNombreResponsableEntidad());
		attributes.put(
			"telefonoResponsableEntidad", getTelefonoResponsableEntidad());
		attributes.put("cargoResponsableEntidad", getCargoResponsableEntidad());
		attributes.put("emailResponsableEntidad", getEmailResponsableEntidad());
		attributes.put(
			"nombreResponsableIgualdad", getNombreResponsableIgualdad());
		attributes.put(
			"telefonoResponsableIgualdad", getTelefonoResponsableIgualdad());
		attributes.put(
			"cargoResponsableIgualdad", getCargoResponsableIgualdad());
		attributes.put(
			"emailResponsableIgualdad", getEmailResponsableIgualdad());
		attributes.put("cnaes", getCnaes());
		attributes.put("nCentros", getNCentros());
		attributes.put("descripcionActividad", getDescripcionActividad());
		attributes.put("convenio", getConvenio());
		attributes.put("ambito", getAmbito());
		attributes.put("comentarioAmbito", getComentarioAmbito());
		attributes.put("nHombresPlantilla", getNHombresPlantilla());
		attributes.put("nMujeresPlantilla", getNMujeresPlantilla());
		attributes.put("representacionLegal", getRepresentacionLegal());
		attributes.put(
			"nRepresentacionLegalHombres", getNRepresentacionLegalHombres());
		attributes.put(
			"nRepresentacionLegalMujeres", getNRepresentacionLegalMujeres());
		attributes.put("representaTotalidad", getRepresentaTotalidad());
		attributes.put("nNoRepresentados", getNNoRepresentados());
		attributes.put(
			"comentarioRepresentacion", getComentarioRepresentacion());
		attributes.put("departamentoPersonal", getDepartamentoPersonal());
		attributes.put("sindicatos", getSindicatos());
		attributes.put(
			"reprComisionOrganizacion", getReprComisionOrganizacion());
		attributes.put("reprComisionSocial", getReprComisionSocial());
		attributes.put("reprPlanOrganizacion", getReprPlanOrganizacion());
		attributes.put("reprPlanSociales", getReprPlanSociales());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long organizacionId = (Long)attributes.get("organizacionId");

		if (organizacionId != null) {
			setOrganizacionId(organizacionId);
		}

		Long versionId = (Long)attributes.get("versionId");

		if (versionId != null) {
			setVersionId(versionId);
		}

		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		String annoConstitucion = (String)attributes.get("annoConstitucion");

		if (annoConstitucion != null) {
			setAnnoConstitucion(annoConstitucion);
		}

		String paginaWeb = (String)attributes.get("paginaWeb");

		if (paginaWeb != null) {
			setPaginaWeb(paginaWeb);
		}

		String domicilioSocial = (String)attributes.get("domicilioSocial");

		if (domicilioSocial != null) {
			setDomicilioSocial(domicilioSocial);
		}

		String facturacionAnual = (String)attributes.get("facturacionAnual");

		if (facturacionAnual != null) {
			setFacturacionAnual(facturacionAnual);
		}

		String formaJuridica = (String)attributes.get("formaJuridica");

		if (formaJuridica != null) {
			setFormaJuridica(formaJuridica);
		}

		String nombreResponsableEntidad = (String)attributes.get(
			"nombreResponsableEntidad");

		if (nombreResponsableEntidad != null) {
			setNombreResponsableEntidad(nombreResponsableEntidad);
		}

		String telefonoResponsableEntidad = (String)attributes.get(
			"telefonoResponsableEntidad");

		if (telefonoResponsableEntidad != null) {
			setTelefonoResponsableEntidad(telefonoResponsableEntidad);
		}

		String cargoResponsableEntidad = (String)attributes.get(
			"cargoResponsableEntidad");

		if (cargoResponsableEntidad != null) {
			setCargoResponsableEntidad(cargoResponsableEntidad);
		}

		String emailResponsableEntidad = (String)attributes.get(
			"emailResponsableEntidad");

		if (emailResponsableEntidad != null) {
			setEmailResponsableEntidad(emailResponsableEntidad);
		}

		String nombreResponsableIgualdad = (String)attributes.get(
			"nombreResponsableIgualdad");

		if (nombreResponsableIgualdad != null) {
			setNombreResponsableIgualdad(nombreResponsableIgualdad);
		}

		String telefonoResponsableIgualdad = (String)attributes.get(
			"telefonoResponsableIgualdad");

		if (telefonoResponsableIgualdad != null) {
			setTelefonoResponsableIgualdad(telefonoResponsableIgualdad);
		}

		String cargoResponsableIgualdad = (String)attributes.get(
			"cargoResponsableIgualdad");

		if (cargoResponsableIgualdad != null) {
			setCargoResponsableIgualdad(cargoResponsableIgualdad);
		}

		String emailResponsableIgualdad = (String)attributes.get(
			"emailResponsableIgualdad");

		if (emailResponsableIgualdad != null) {
			setEmailResponsableIgualdad(emailResponsableIgualdad);
		}

		String cnaes = (String)attributes.get("cnaes");

		if (cnaes != null) {
			setCnaes(cnaes);
		}

		Long nCentros = (Long)attributes.get("nCentros");

		if (nCentros != null) {
			setNCentros(nCentros);
		}

		String descripcionActividad = (String)attributes.get(
			"descripcionActividad");

		if (descripcionActividad != null) {
			setDescripcionActividad(descripcionActividad);
		}

		String convenio = (String)attributes.get("convenio");

		if (convenio != null) {
			setConvenio(convenio);
		}

		String ambito = (String)attributes.get("ambito");

		if (ambito != null) {
			setAmbito(ambito);
		}

		String comentarioAmbito = (String)attributes.get("comentarioAmbito");

		if (comentarioAmbito != null) {
			setComentarioAmbito(comentarioAmbito);
		}

		Long nHombresPlantilla = (Long)attributes.get("nHombresPlantilla");

		if (nHombresPlantilla != null) {
			setNHombresPlantilla(nHombresPlantilla);
		}

		Long nMujeresPlantilla = (Long)attributes.get("nMujeresPlantilla");

		if (nMujeresPlantilla != null) {
			setNMujeresPlantilla(nMujeresPlantilla);
		}

		String representacionLegal = (String)attributes.get(
			"representacionLegal");

		if (representacionLegal != null) {
			setRepresentacionLegal(representacionLegal);
		}

		Long nRepresentacionLegalHombres = (Long)attributes.get(
			"nRepresentacionLegalHombres");

		if (nRepresentacionLegalHombres != null) {
			setNRepresentacionLegalHombres(nRepresentacionLegalHombres);
		}

		Long nRepresentacionLegalMujeres = (Long)attributes.get(
			"nRepresentacionLegalMujeres");

		if (nRepresentacionLegalMujeres != null) {
			setNRepresentacionLegalMujeres(nRepresentacionLegalMujeres);
		}

		String representaTotalidad = (String)attributes.get(
			"representaTotalidad");

		if (representaTotalidad != null) {
			setRepresentaTotalidad(representaTotalidad);
		}

		Long nNoRepresentados = (Long)attributes.get("nNoRepresentados");

		if (nNoRepresentados != null) {
			setNNoRepresentados(nNoRepresentados);
		}

		String comentarioRepresentacion = (String)attributes.get(
			"comentarioRepresentacion");

		if (comentarioRepresentacion != null) {
			setComentarioRepresentacion(comentarioRepresentacion);
		}

		String departamentoPersonal = (String)attributes.get(
			"departamentoPersonal");

		if (departamentoPersonal != null) {
			setDepartamentoPersonal(departamentoPersonal);
		}

		String sindicatos = (String)attributes.get("sindicatos");

		if (sindicatos != null) {
			setSindicatos(sindicatos);
		}

		String reprComisionOrganizacion = (String)attributes.get(
			"reprComisionOrganizacion");

		if (reprComisionOrganizacion != null) {
			setReprComisionOrganizacion(reprComisionOrganizacion);
		}

		String reprComisionSocial = (String)attributes.get(
			"reprComisionSocial");

		if (reprComisionSocial != null) {
			setReprComisionSocial(reprComisionSocial);
		}

		String reprPlanOrganizacion = (String)attributes.get(
			"reprPlanOrganizacion");

		if (reprPlanOrganizacion != null) {
			setReprPlanOrganizacion(reprPlanOrganizacion);
		}

		String reprPlanSociales = (String)attributes.get("reprPlanSociales");

		if (reprPlanSociales != null) {
			setReprPlanSociales(reprPlanSociales);
		}
	}

	/**
	 * Returns the ambito of this organizacion.
	 *
	 * @return the ambito of this organizacion
	 */
	@Override
	public String getAmbito() {
		return model.getAmbito();
	}

	/**
	 * Returns the anno constitucion of this organizacion.
	 *
	 * @return the anno constitucion of this organizacion
	 */
	@Override
	public String getAnnoConstitucion() {
		return model.getAnnoConstitucion();
	}

	/**
	 * Returns the cargo responsable entidad of this organizacion.
	 *
	 * @return the cargo responsable entidad of this organizacion
	 */
	@Override
	public String getCargoResponsableEntidad() {
		return model.getCargoResponsableEntidad();
	}

	/**
	 * Returns the cargo responsable igualdad of this organizacion.
	 *
	 * @return the cargo responsable igualdad of this organizacion
	 */
	@Override
	public String getCargoResponsableIgualdad() {
		return model.getCargoResponsableIgualdad();
	}

	/**
	 * Returns the cnaes of this organizacion.
	 *
	 * @return the cnaes of this organizacion
	 */
	@Override
	public String getCnaes() {
		return model.getCnaes();
	}

	/**
	 * Returns the comentario ambito of this organizacion.
	 *
	 * @return the comentario ambito of this organizacion
	 */
	@Override
	public String getComentarioAmbito() {
		return model.getComentarioAmbito();
	}

	/**
	 * Returns the comentario representacion of this organizacion.
	 *
	 * @return the comentario representacion of this organizacion
	 */
	@Override
	public String getComentarioRepresentacion() {
		return model.getComentarioRepresentacion();
	}

	/**
	 * Returns the comp ID of this organizacion.
	 *
	 * @return the comp ID of this organizacion
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the convenio of this organizacion.
	 *
	 * @return the convenio of this organizacion
	 */
	@Override
	public String getConvenio() {
		return model.getConvenio();
	}

	/**
	 * Returns the departamento personal of this organizacion.
	 *
	 * @return the departamento personal of this organizacion
	 */
	@Override
	public String getDepartamentoPersonal() {
		return model.getDepartamentoPersonal();
	}

	/**
	 * Returns the descripcion actividad of this organizacion.
	 *
	 * @return the descripcion actividad of this organizacion
	 */
	@Override
	public String getDescripcionActividad() {
		return model.getDescripcionActividad();
	}

	/**
	 * Returns the domicilio social of this organizacion.
	 *
	 * @return the domicilio social of this organizacion
	 */
	@Override
	public String getDomicilioSocial() {
		return model.getDomicilioSocial();
	}

	/**
	 * Returns the email responsable entidad of this organizacion.
	 *
	 * @return the email responsable entidad of this organizacion
	 */
	@Override
	public String getEmailResponsableEntidad() {
		return model.getEmailResponsableEntidad();
	}

	/**
	 * Returns the email responsable igualdad of this organizacion.
	 *
	 * @return the email responsable igualdad of this organizacion
	 */
	@Override
	public String getEmailResponsableIgualdad() {
		return model.getEmailResponsableIgualdad();
	}

	/**
	 * Returns the facturacion anual of this organizacion.
	 *
	 * @return the facturacion anual of this organizacion
	 */
	@Override
	public String getFacturacionAnual() {
		return model.getFacturacionAnual();
	}

	/**
	 * Returns the forma juridica of this organizacion.
	 *
	 * @return the forma juridica of this organizacion
	 */
	@Override
	public String getFormaJuridica() {
		return model.getFormaJuridica();
	}

	/**
	 * Returns the n centros of this organizacion.
	 *
	 * @return the n centros of this organizacion
	 */
	@Override
	public long getNCentros() {
		return model.getNCentros();
	}

	/**
	 * Returns the n hombres plantilla of this organizacion.
	 *
	 * @return the n hombres plantilla of this organizacion
	 */
	@Override
	public long getNHombresPlantilla() {
		return model.getNHombresPlantilla();
	}

	/**
	 * Returns the n mujeres plantilla of this organizacion.
	 *
	 * @return the n mujeres plantilla of this organizacion
	 */
	@Override
	public long getNMujeresPlantilla() {
		return model.getNMujeresPlantilla();
	}

	/**
	 * Returns the n no representados of this organizacion.
	 *
	 * @return the n no representados of this organizacion
	 */
	@Override
	public long getNNoRepresentados() {
		return model.getNNoRepresentados();
	}

	/**
	 * Returns the nombre responsable entidad of this organizacion.
	 *
	 * @return the nombre responsable entidad of this organizacion
	 */
	@Override
	public String getNombreResponsableEntidad() {
		return model.getNombreResponsableEntidad();
	}

	/**
	 * Returns the nombre responsable igualdad of this organizacion.
	 *
	 * @return the nombre responsable igualdad of this organizacion
	 */
	@Override
	public String getNombreResponsableIgualdad() {
		return model.getNombreResponsableIgualdad();
	}

	/**
	 * Returns the n representacion legal hombres of this organizacion.
	 *
	 * @return the n representacion legal hombres of this organizacion
	 */
	@Override
	public long getNRepresentacionLegalHombres() {
		return model.getNRepresentacionLegalHombres();
	}

	/**
	 * Returns the n representacion legal mujeres of this organizacion.
	 *
	 * @return the n representacion legal mujeres of this organizacion
	 */
	@Override
	public long getNRepresentacionLegalMujeres() {
		return model.getNRepresentacionLegalMujeres();
	}

	/**
	 * Returns the organizacion ID of this organizacion.
	 *
	 * @return the organizacion ID of this organizacion
	 */
	@Override
	public long getOrganizacionId() {
		return model.getOrganizacionId();
	}

	/**
	 * Returns the pagina web of this organizacion.
	 *
	 * @return the pagina web of this organizacion
	 */
	@Override
	public String getPaginaWeb() {
		return model.getPaginaWeb();
	}

	/**
	 * Returns the primary key of this organizacion.
	 *
	 * @return the primary key of this organizacion
	 */
	@Override
	public
		com.plan.igualdad.liferay.portlet.manager.service.persistence.
			OrganizacionPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the repr comision organizacion of this organizacion.
	 *
	 * @return the repr comision organizacion of this organizacion
	 */
	@Override
	public String getReprComisionOrganizacion() {
		return model.getReprComisionOrganizacion();
	}

	/**
	 * Returns the repr comision social of this organizacion.
	 *
	 * @return the repr comision social of this organizacion
	 */
	@Override
	public String getReprComisionSocial() {
		return model.getReprComisionSocial();
	}

	/**
	 * Returns the representacion legal of this organizacion.
	 *
	 * @return the representacion legal of this organizacion
	 */
	@Override
	public String getRepresentacionLegal() {
		return model.getRepresentacionLegal();
	}

	/**
	 * Returns the representa totalidad of this organizacion.
	 *
	 * @return the representa totalidad of this organizacion
	 */
	@Override
	public String getRepresentaTotalidad() {
		return model.getRepresentaTotalidad();
	}

	/**
	 * Returns the repr plan organizacion of this organizacion.
	 *
	 * @return the repr plan organizacion of this organizacion
	 */
	@Override
	public String getReprPlanOrganizacion() {
		return model.getReprPlanOrganizacion();
	}

	/**
	 * Returns the repr plan sociales of this organizacion.
	 *
	 * @return the repr plan sociales of this organizacion
	 */
	@Override
	public String getReprPlanSociales() {
		return model.getReprPlanSociales();
	}

	/**
	 * Returns the sindicatos of this organizacion.
	 *
	 * @return the sindicatos of this organizacion
	 */
	@Override
	public String getSindicatos() {
		return model.getSindicatos();
	}

	/**
	 * Returns the telefono responsable entidad of this organizacion.
	 *
	 * @return the telefono responsable entidad of this organizacion
	 */
	@Override
	public String getTelefonoResponsableEntidad() {
		return model.getTelefonoResponsableEntidad();
	}

	/**
	 * Returns the telefono responsable igualdad of this organizacion.
	 *
	 * @return the telefono responsable igualdad of this organizacion
	 */
	@Override
	public String getTelefonoResponsableIgualdad() {
		return model.getTelefonoResponsableIgualdad();
	}

	/**
	 * Returns the version ID of this organizacion.
	 *
	 * @return the version ID of this organizacion
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
	 * Sets the ambito of this organizacion.
	 *
	 * @param ambito the ambito of this organizacion
	 */
	@Override
	public void setAmbito(String ambito) {
		model.setAmbito(ambito);
	}

	/**
	 * Sets the anno constitucion of this organizacion.
	 *
	 * @param annoConstitucion the anno constitucion of this organizacion
	 */
	@Override
	public void setAnnoConstitucion(String annoConstitucion) {
		model.setAnnoConstitucion(annoConstitucion);
	}

	/**
	 * Sets the cargo responsable entidad of this organizacion.
	 *
	 * @param cargoResponsableEntidad the cargo responsable entidad of this organizacion
	 */
	@Override
	public void setCargoResponsableEntidad(String cargoResponsableEntidad) {
		model.setCargoResponsableEntidad(cargoResponsableEntidad);
	}

	/**
	 * Sets the cargo responsable igualdad of this organizacion.
	 *
	 * @param cargoResponsableIgualdad the cargo responsable igualdad of this organizacion
	 */
	@Override
	public void setCargoResponsableIgualdad(String cargoResponsableIgualdad) {
		model.setCargoResponsableIgualdad(cargoResponsableIgualdad);
	}

	/**
	 * Sets the cnaes of this organizacion.
	 *
	 * @param cnaes the cnaes of this organizacion
	 */
	@Override
	public void setCnaes(String cnaes) {
		model.setCnaes(cnaes);
	}

	/**
	 * Sets the comentario ambito of this organizacion.
	 *
	 * @param comentarioAmbito the comentario ambito of this organizacion
	 */
	@Override
	public void setComentarioAmbito(String comentarioAmbito) {
		model.setComentarioAmbito(comentarioAmbito);
	}

	/**
	 * Sets the comentario representacion of this organizacion.
	 *
	 * @param comentarioRepresentacion the comentario representacion of this organizacion
	 */
	@Override
	public void setComentarioRepresentacion(String comentarioRepresentacion) {
		model.setComentarioRepresentacion(comentarioRepresentacion);
	}

	/**
	 * Sets the comp ID of this organizacion.
	 *
	 * @param compId the comp ID of this organizacion
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the convenio of this organizacion.
	 *
	 * @param convenio the convenio of this organizacion
	 */
	@Override
	public void setConvenio(String convenio) {
		model.setConvenio(convenio);
	}

	/**
	 * Sets the departamento personal of this organizacion.
	 *
	 * @param departamentoPersonal the departamento personal of this organizacion
	 */
	@Override
	public void setDepartamentoPersonal(String departamentoPersonal) {
		model.setDepartamentoPersonal(departamentoPersonal);
	}

	/**
	 * Sets the descripcion actividad of this organizacion.
	 *
	 * @param descripcionActividad the descripcion actividad of this organizacion
	 */
	@Override
	public void setDescripcionActividad(String descripcionActividad) {
		model.setDescripcionActividad(descripcionActividad);
	}

	/**
	 * Sets the domicilio social of this organizacion.
	 *
	 * @param domicilioSocial the domicilio social of this organizacion
	 */
	@Override
	public void setDomicilioSocial(String domicilioSocial) {
		model.setDomicilioSocial(domicilioSocial);
	}

	/**
	 * Sets the email responsable entidad of this organizacion.
	 *
	 * @param emailResponsableEntidad the email responsable entidad of this organizacion
	 */
	@Override
	public void setEmailResponsableEntidad(String emailResponsableEntidad) {
		model.setEmailResponsableEntidad(emailResponsableEntidad);
	}

	/**
	 * Sets the email responsable igualdad of this organizacion.
	 *
	 * @param emailResponsableIgualdad the email responsable igualdad of this organizacion
	 */
	@Override
	public void setEmailResponsableIgualdad(String emailResponsableIgualdad) {
		model.setEmailResponsableIgualdad(emailResponsableIgualdad);
	}

	/**
	 * Sets the facturacion anual of this organizacion.
	 *
	 * @param facturacionAnual the facturacion anual of this organizacion
	 */
	@Override
	public void setFacturacionAnual(String facturacionAnual) {
		model.setFacturacionAnual(facturacionAnual);
	}

	/**
	 * Sets the forma juridica of this organizacion.
	 *
	 * @param formaJuridica the forma juridica of this organizacion
	 */
	@Override
	public void setFormaJuridica(String formaJuridica) {
		model.setFormaJuridica(formaJuridica);
	}

	/**
	 * Sets the n centros of this organizacion.
	 *
	 * @param nCentros the n centros of this organizacion
	 */
	@Override
	public void setNCentros(long nCentros) {
		model.setNCentros(nCentros);
	}

	/**
	 * Sets the n hombres plantilla of this organizacion.
	 *
	 * @param nHombresPlantilla the n hombres plantilla of this organizacion
	 */
	@Override
	public void setNHombresPlantilla(long nHombresPlantilla) {
		model.setNHombresPlantilla(nHombresPlantilla);
	}

	/**
	 * Sets the n mujeres plantilla of this organizacion.
	 *
	 * @param nMujeresPlantilla the n mujeres plantilla of this organizacion
	 */
	@Override
	public void setNMujeresPlantilla(long nMujeresPlantilla) {
		model.setNMujeresPlantilla(nMujeresPlantilla);
	}

	/**
	 * Sets the n no representados of this organizacion.
	 *
	 * @param nNoRepresentados the n no representados of this organizacion
	 */
	@Override
	public void setNNoRepresentados(long nNoRepresentados) {
		model.setNNoRepresentados(nNoRepresentados);
	}

	/**
	 * Sets the nombre responsable entidad of this organizacion.
	 *
	 * @param nombreResponsableEntidad the nombre responsable entidad of this organizacion
	 */
	@Override
	public void setNombreResponsableEntidad(String nombreResponsableEntidad) {
		model.setNombreResponsableEntidad(nombreResponsableEntidad);
	}

	/**
	 * Sets the nombre responsable igualdad of this organizacion.
	 *
	 * @param nombreResponsableIgualdad the nombre responsable igualdad of this organizacion
	 */
	@Override
	public void setNombreResponsableIgualdad(String nombreResponsableIgualdad) {
		model.setNombreResponsableIgualdad(nombreResponsableIgualdad);
	}

	/**
	 * Sets the n representacion legal hombres of this organizacion.
	 *
	 * @param nRepresentacionLegalHombres the n representacion legal hombres of this organizacion
	 */
	@Override
	public void setNRepresentacionLegalHombres(
		long nRepresentacionLegalHombres) {

		model.setNRepresentacionLegalHombres(nRepresentacionLegalHombres);
	}

	/**
	 * Sets the n representacion legal mujeres of this organizacion.
	 *
	 * @param nRepresentacionLegalMujeres the n representacion legal mujeres of this organizacion
	 */
	@Override
	public void setNRepresentacionLegalMujeres(
		long nRepresentacionLegalMujeres) {

		model.setNRepresentacionLegalMujeres(nRepresentacionLegalMujeres);
	}

	/**
	 * Sets the organizacion ID of this organizacion.
	 *
	 * @param organizacionId the organizacion ID of this organizacion
	 */
	@Override
	public void setOrganizacionId(long organizacionId) {
		model.setOrganizacionId(organizacionId);
	}

	/**
	 * Sets the pagina web of this organizacion.
	 *
	 * @param paginaWeb the pagina web of this organizacion
	 */
	@Override
	public void setPaginaWeb(String paginaWeb) {
		model.setPaginaWeb(paginaWeb);
	}

	/**
	 * Sets the primary key of this organizacion.
	 *
	 * @param primaryKey the primary key of this organizacion
	 */
	@Override
	public void setPrimaryKey(
		com.plan.igualdad.liferay.portlet.manager.service.persistence.
			OrganizacionPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the repr comision organizacion of this organizacion.
	 *
	 * @param reprComisionOrganizacion the repr comision organizacion of this organizacion
	 */
	@Override
	public void setReprComisionOrganizacion(String reprComisionOrganizacion) {
		model.setReprComisionOrganizacion(reprComisionOrganizacion);
	}

	/**
	 * Sets the repr comision social of this organizacion.
	 *
	 * @param reprComisionSocial the repr comision social of this organizacion
	 */
	@Override
	public void setReprComisionSocial(String reprComisionSocial) {
		model.setReprComisionSocial(reprComisionSocial);
	}

	/**
	 * Sets the representacion legal of this organizacion.
	 *
	 * @param representacionLegal the representacion legal of this organizacion
	 */
	@Override
	public void setRepresentacionLegal(String representacionLegal) {
		model.setRepresentacionLegal(representacionLegal);
	}

	/**
	 * Sets the representa totalidad of this organizacion.
	 *
	 * @param representaTotalidad the representa totalidad of this organizacion
	 */
	@Override
	public void setRepresentaTotalidad(String representaTotalidad) {
		model.setRepresentaTotalidad(representaTotalidad);
	}

	/**
	 * Sets the repr plan organizacion of this organizacion.
	 *
	 * @param reprPlanOrganizacion the repr plan organizacion of this organizacion
	 */
	@Override
	public void setReprPlanOrganizacion(String reprPlanOrganizacion) {
		model.setReprPlanOrganizacion(reprPlanOrganizacion);
	}

	/**
	 * Sets the repr plan sociales of this organizacion.
	 *
	 * @param reprPlanSociales the repr plan sociales of this organizacion
	 */
	@Override
	public void setReprPlanSociales(String reprPlanSociales) {
		model.setReprPlanSociales(reprPlanSociales);
	}

	/**
	 * Sets the sindicatos of this organizacion.
	 *
	 * @param sindicatos the sindicatos of this organizacion
	 */
	@Override
	public void setSindicatos(String sindicatos) {
		model.setSindicatos(sindicatos);
	}

	/**
	 * Sets the telefono responsable entidad of this organizacion.
	 *
	 * @param telefonoResponsableEntidad the telefono responsable entidad of this organizacion
	 */
	@Override
	public void setTelefonoResponsableEntidad(
		String telefonoResponsableEntidad) {

		model.setTelefonoResponsableEntidad(telefonoResponsableEntidad);
	}

	/**
	 * Sets the telefono responsable igualdad of this organizacion.
	 *
	 * @param telefonoResponsableIgualdad the telefono responsable igualdad of this organizacion
	 */
	@Override
	public void setTelefonoResponsableIgualdad(
		String telefonoResponsableIgualdad) {

		model.setTelefonoResponsableIgualdad(telefonoResponsableIgualdad);
	}

	/**
	 * Sets the version ID of this organizacion.
	 *
	 * @param versionId the version ID of this organizacion
	 */
	@Override
	public void setVersionId(long versionId) {
		model.setVersionId(versionId);
	}

	@Override
	protected OrganizacionWrapper wrap(Organizacion organizacion) {
		return new OrganizacionWrapper(organizacion);
	}

}