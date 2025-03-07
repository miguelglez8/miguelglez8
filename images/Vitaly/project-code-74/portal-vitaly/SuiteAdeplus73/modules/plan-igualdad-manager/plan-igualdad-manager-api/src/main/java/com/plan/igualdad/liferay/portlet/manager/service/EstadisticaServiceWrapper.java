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

package com.plan.igualdad.liferay.portlet.manager.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EstadisticaService}.
 *
 * @author Brian Wing Shun Chan
 * @see EstadisticaService
 * @generated
 */
public class EstadisticaServiceWrapper
	implements EstadisticaService, ServiceWrapper<EstadisticaService> {

	public EstadisticaServiceWrapper(EstadisticaService estadisticaService) {
		_estadisticaService = estadisticaService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _estadisticaService.getOSGiServiceIdentifier();
	}

	@Override
	public EstadisticaService getWrappedService() {
		return _estadisticaService;
	}

	@Override
	public void setWrappedService(EstadisticaService estadisticaService) {
		_estadisticaService = estadisticaService;
	}

	private EstadisticaService _estadisticaService;

}