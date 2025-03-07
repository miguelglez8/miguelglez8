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

package com.canal.etico.liferay.portlet.canal.manager.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Denuncia service. Represents a row in the &quot;canal_etico_Denuncia&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DenunciaModel
 * @generated
 */
@ImplementationClassName(
	"com.canal.etico.liferay.portlet.canal.manager.model.impl.DenunciaImpl"
)
@ProviderType
public interface Denuncia extends DenunciaModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.DenunciaImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Denuncia, Long> DENUNCIA_ID_ACCESSOR =
		new Accessor<Denuncia, Long>() {

			@Override
			public Long get(Denuncia denuncia) {
				return denuncia.getDenunciaId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Denuncia> getTypeClass() {
				return Denuncia.class;
			}

		};

}