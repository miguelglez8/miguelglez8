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
 * The extended model interface for the Area service. Represents a row in the &quot;planigualdad_Area&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AreaModel
 * @generated
 */
@ImplementationClassName(
	"com.plan.igualdad.liferay.portlet.manager.model.impl.AreaImpl"
)
@ProviderType
public interface Area extends AreaModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.plan.igualdad.liferay.portlet.manager.model.impl.AreaImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Area, Long> AREA_ID_ACCESSOR =
		new Accessor<Area, Long>() {

			@Override
			public Long get(Area area) {
				return area.getAreaId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Area> getTypeClass() {
				return Area.class;
			}

		};
	public static final Accessor<Area, Long> VERSION_ID_ACCESSOR =
		new Accessor<Area, Long>() {

			@Override
			public Long get(Area area) {
				return area.getVersionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Area> getTypeClass() {
				return Area.class;
			}

		};
	public static final Accessor<Area, Long> COMP_ID_ACCESSOR =
		new Accessor<Area, Long>() {

			@Override
			public Long get(Area area) {
				return area.getCompId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Area> getTypeClass() {
				return Area.class;
			}

		};

}