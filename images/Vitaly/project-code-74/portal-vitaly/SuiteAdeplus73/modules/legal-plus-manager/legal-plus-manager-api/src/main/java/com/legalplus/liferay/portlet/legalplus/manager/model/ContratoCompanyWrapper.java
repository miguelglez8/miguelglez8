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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContratoCompany}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContratoCompany
 * @generated
 */
public class ContratoCompanyWrapper
	extends BaseModelWrapper<ContratoCompany>
	implements ContratoCompany, ModelWrapper<ContratoCompany> {

	public ContratoCompanyWrapper(ContratoCompany contratoCompany) {
		super(contratoCompany);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("contractId", getContractId());
		attributes.put("compId", getCompId());
		attributes.put("familia", getFamilia());
		attributes.put("ccaa", getCcaa());
		attributes.put("ayuntamiento", getAyuntamiento());
		attributes.put("cnaes", getCnaes());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long contractId = (Long)attributes.get("contractId");

		if (contractId != null) {
			setContractId(contractId);
		}

		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		String familia = (String)attributes.get("familia");

		if (familia != null) {
			setFamilia(familia);
		}

		String ccaa = (String)attributes.get("ccaa");

		if (ccaa != null) {
			setCcaa(ccaa);
		}

		String ayuntamiento = (String)attributes.get("ayuntamiento");

		if (ayuntamiento != null) {
			setAyuntamiento(ayuntamiento);
		}

		String cnaes = (String)attributes.get("cnaes");

		if (cnaes != null) {
			setCnaes(cnaes);
		}
	}

	/**
	 * Returns the ayuntamiento of this contrato company.
	 *
	 * @return the ayuntamiento of this contrato company
	 */
	@Override
	public String getAyuntamiento() {
		return model.getAyuntamiento();
	}

	/**
	 * Returns the ccaa of this contrato company.
	 *
	 * @return the ccaa of this contrato company
	 */
	@Override
	public String getCcaa() {
		return model.getCcaa();
	}

	/**
	 * Returns the cnaes of this contrato company.
	 *
	 * @return the cnaes of this contrato company
	 */
	@Override
	public String getCnaes() {
		return model.getCnaes();
	}

	/**
	 * Returns the comp ID of this contrato company.
	 *
	 * @return the comp ID of this contrato company
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the contract ID of this contrato company.
	 *
	 * @return the contract ID of this contrato company
	 */
	@Override
	public long getContractId() {
		return model.getContractId();
	}

	/**
	 * Returns the familia of this contrato company.
	 *
	 * @return the familia of this contrato company
	 */
	@Override
	public String getFamilia() {
		return model.getFamilia();
	}

	/**
	 * Returns the primary key of this contrato company.
	 *
	 * @return the primary key of this contrato company
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
		ContratoCompanyPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the ayuntamiento of this contrato company.
	 *
	 * @param ayuntamiento the ayuntamiento of this contrato company
	 */
	@Override
	public void setAyuntamiento(String ayuntamiento) {
		model.setAyuntamiento(ayuntamiento);
	}

	/**
	 * Sets the ccaa of this contrato company.
	 *
	 * @param ccaa the ccaa of this contrato company
	 */
	@Override
	public void setCcaa(String ccaa) {
		model.setCcaa(ccaa);
	}

	/**
	 * Sets the cnaes of this contrato company.
	 *
	 * @param cnaes the cnaes of this contrato company
	 */
	@Override
	public void setCnaes(String cnaes) {
		model.setCnaes(cnaes);
	}

	/**
	 * Sets the comp ID of this contrato company.
	 *
	 * @param compId the comp ID of this contrato company
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the contract ID of this contrato company.
	 *
	 * @param contractId the contract ID of this contrato company
	 */
	@Override
	public void setContractId(long contractId) {
		model.setContractId(contractId);
	}

	/**
	 * Sets the familia of this contrato company.
	 *
	 * @param familia the familia of this contrato company
	 */
	@Override
	public void setFamilia(String familia) {
		model.setFamilia(familia);
	}

	/**
	 * Sets the primary key of this contrato company.
	 *
	 * @param primaryKey the primary key of this contrato company
	 */
	@Override
	public void setPrimaryKey(
		com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
			ContratoCompanyPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected ContratoCompanyWrapper wrap(ContratoCompany contratoCompany) {
		return new ContratoCompanyWrapper(contratoCompany);
	}

}