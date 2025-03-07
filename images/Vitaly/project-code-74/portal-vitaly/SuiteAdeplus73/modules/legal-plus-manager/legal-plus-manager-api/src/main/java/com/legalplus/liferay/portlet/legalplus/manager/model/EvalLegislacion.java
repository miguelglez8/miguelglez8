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
 * The extended model interface for the EvalLegislacion service. Represents a row in the &quot;legalplus_EvalLegislacion&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see EvalLegislacionModel
 * @generated
 */
@ImplementationClassName(
	"com.legalplus.liferay.portlet.legalplus.manager.model.impl.EvalLegislacionImpl"
)
@ProviderType
public interface EvalLegislacion extends EvalLegislacionModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.EvalLegislacionImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<EvalLegislacion, Long> VERSION_ACCESSOR =
		new Accessor<EvalLegislacion, Long>() {

			@Override
			public Long get(EvalLegislacion evalLegislacion) {
				return evalLegislacion.getVersion();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<EvalLegislacion> getTypeClass() {
				return EvalLegislacion.class;
			}

		};
	public static final Accessor<EvalLegislacion, Long> COMP_ID_ACCESSOR =
		new Accessor<EvalLegislacion, Long>() {

			@Override
			public Long get(EvalLegislacion evalLegislacion) {
				return evalLegislacion.getCompId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<EvalLegislacion> getTypeClass() {
				return EvalLegislacion.class;
			}

		};
	public static final Accessor<EvalLegislacion, String>
		LEGISLACION_ID_ACCESSOR = new Accessor<EvalLegislacion, String>() {

			@Override
			public String get(EvalLegislacion evalLegislacion) {
				return evalLegislacion.getLegislacionId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<EvalLegislacion> getTypeClass() {
				return EvalLegislacion.class;
			}

		};

}