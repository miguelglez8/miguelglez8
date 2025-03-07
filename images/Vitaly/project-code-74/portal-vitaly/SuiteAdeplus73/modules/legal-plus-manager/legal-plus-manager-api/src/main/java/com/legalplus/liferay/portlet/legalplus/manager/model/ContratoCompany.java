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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the ContratoCompany service. Represents a row in the &quot;legalplus_ContratoCompany&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ContratoCompanyModel
 * @generated
 */
@ImplementationClassName(
	"com.legalplus.liferay.portlet.legalplus.manager.model.impl.ContratoCompanyImpl"
)
@ProviderType
public interface ContratoCompany extends ContratoCompanyModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.ContratoCompanyImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ContratoCompany, Long> CONTRACT_ID_ACCESSOR =
		new Accessor<ContratoCompany, Long>() {

			@Override
			public Long get(ContratoCompany contratoCompany) {
				return contratoCompany.getContractId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ContratoCompany> getTypeClass() {
				return ContratoCompany.class;
			}

		};
	public static final Accessor<ContratoCompany, Long> COMP_ID_ACCESSOR =
		new Accessor<ContratoCompany, Long>() {

			@Override
			public Long get(ContratoCompany contratoCompany) {
				return contratoCompany.getCompId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ContratoCompany> getTypeClass() {
				return ContratoCompany.class;
			}

		};

}