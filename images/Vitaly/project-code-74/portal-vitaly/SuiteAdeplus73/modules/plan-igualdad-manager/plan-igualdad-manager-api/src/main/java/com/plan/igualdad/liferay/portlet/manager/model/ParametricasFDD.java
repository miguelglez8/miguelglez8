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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the ParametricasFDD service. Represents a row in the &quot;planigualdad_ParametricasFDD&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ParametricasFDDModel
 * @generated
 */
@ImplementationClassName(
	"com.plan.igualdad.liferay.portlet.manager.model.impl.ParametricasFDDImpl"
)
@ProviderType
public interface ParametricasFDD extends ParametricasFDDModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.plan.igualdad.liferay.portlet.manager.model.impl.ParametricasFDDImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ParametricasFDD, Long> COMP_ID_ACCESSOR =
		new Accessor<ParametricasFDD, Long>() {

			@Override
			public Long get(ParametricasFDD parametricasFDD) {
				return parametricasFDD.getCompId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ParametricasFDD> getTypeClass() {
				return ParametricasFDD.class;
			}

		};
	public static final Accessor<ParametricasFDD, Long> VERSION_ID_ACCESSOR =
		new Accessor<ParametricasFDD, Long>() {

			@Override
			public Long get(ParametricasFDD parametricasFDD) {
				return parametricasFDD.getVersionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ParametricasFDD> getTypeClass() {
				return ParametricasFDD.class;
			}

		};
	public static final Accessor<ParametricasFDD, String>
		ID_PARAMETRICA_ACCESSOR = new Accessor<ParametricasFDD, String>() {

			@Override
			public String get(ParametricasFDD parametricasFDD) {
				return parametricasFDD.getIdParametrica();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<ParametricasFDD> getTypeClass() {
				return ParametricasFDD.class;
			}

		};

}