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
 * The extended model interface for the EvalRequisito service. Represents a row in the &quot;legalplus_EvalRequisito&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see EvalRequisitoModel
 * @generated
 */
@ImplementationClassName(
	"com.legalplus.liferay.portlet.legalplus.manager.model.impl.EvalRequisitoImpl"
)
@ProviderType
public interface EvalRequisito extends EvalRequisitoModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.EvalRequisitoImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<EvalRequisito, Long> VERSION_ACCESSOR =
		new Accessor<EvalRequisito, Long>() {

			@Override
			public Long get(EvalRequisito evalRequisito) {
				return evalRequisito.getVersion();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<EvalRequisito> getTypeClass() {
				return EvalRequisito.class;
			}

		};
	public static final Accessor<EvalRequisito, Long> COMP_ID_ACCESSOR =
		new Accessor<EvalRequisito, Long>() {

			@Override
			public Long get(EvalRequisito evalRequisito) {
				return evalRequisito.getCompId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<EvalRequisito> getTypeClass() {
				return EvalRequisito.class;
			}

		};
	public static final Accessor<EvalRequisito, String>
		LEGISLACION_ID_ACCESSOR = new Accessor<EvalRequisito, String>() {

			@Override
			public String get(EvalRequisito evalRequisito) {
				return evalRequisito.getLegislacionId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<EvalRequisito> getTypeClass() {
				return EvalRequisito.class;
			}

		};
	public static final Accessor<EvalRequisito, String> REQUISITO_ID_ACCESSOR =
		new Accessor<EvalRequisito, String>() {

			@Override
			public String get(EvalRequisito evalRequisito) {
				return evalRequisito.getRequisitoId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<EvalRequisito> getTypeClass() {
				return EvalRequisito.class;
			}

		};

}