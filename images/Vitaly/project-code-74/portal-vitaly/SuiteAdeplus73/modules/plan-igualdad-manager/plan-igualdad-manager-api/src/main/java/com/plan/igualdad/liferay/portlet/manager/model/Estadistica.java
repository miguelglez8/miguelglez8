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
 * The extended model interface for the Estadistica service. Represents a row in the &quot;planigualdad_Estadistica&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see EstadisticaModel
 * @generated
 */
@ImplementationClassName(
	"com.plan.igualdad.liferay.portlet.manager.model.impl.EstadisticaImpl"
)
@ProviderType
public interface Estadistica extends EstadisticaModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.plan.igualdad.liferay.portlet.manager.model.impl.EstadisticaImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Estadistica, Long> COMP_ID_ACCESSOR =
		new Accessor<Estadistica, Long>() {

			@Override
			public Long get(Estadistica estadistica) {
				return estadistica.getCompId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Estadistica> getTypeClass() {
				return Estadistica.class;
			}

		};
	public static final Accessor<Estadistica, Long> VERSION_ID_ACCESSOR =
		new Accessor<Estadistica, Long>() {

			@Override
			public Long get(Estadistica estadistica) {
				return estadistica.getVersionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Estadistica> getTypeClass() {
				return Estadistica.class;
			}

		};
	public static final Accessor<Estadistica, Long> SECCION_ID_ACCESSOR =
		new Accessor<Estadistica, Long>() {

			@Override
			public Long get(Estadistica estadistica) {
				return estadistica.getSeccionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Estadistica> getTypeClass() {
				return Estadistica.class;
			}

		};

}