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
 * The extended model interface for the LegislacionCNAES service. Represents a row in the &quot;legalplus_LegislacionCNAES&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see LegislacionCNAESModel
 * @generated
 */
@ImplementationClassName(
	"com.legalplus.liferay.portlet.legalplus.manager.model.impl.LegislacionCNAESImpl"
)
@ProviderType
public interface LegislacionCNAES
	extends LegislacionCNAESModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.LegislacionCNAESImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LegislacionCNAES, String>
		LEGISLACION_ID_ACCESSOR = new Accessor<LegislacionCNAES, String>() {

			@Override
			public String get(LegislacionCNAES legislacionCNAES) {
				return legislacionCNAES.getLegislacionId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<LegislacionCNAES> getTypeClass() {
				return LegislacionCNAES.class;
			}

		};
	public static final Accessor<LegislacionCNAES, String> CNAE_ACCESSOR =
		new Accessor<LegislacionCNAES, String>() {

			@Override
			public String get(LegislacionCNAES legislacionCNAES) {
				return legislacionCNAES.getCnae();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<LegislacionCNAES> getTypeClass() {
				return LegislacionCNAES.class;
			}

		};

}