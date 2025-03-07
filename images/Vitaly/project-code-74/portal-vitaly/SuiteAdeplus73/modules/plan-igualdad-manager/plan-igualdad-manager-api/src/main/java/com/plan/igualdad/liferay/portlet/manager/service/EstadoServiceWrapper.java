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
 * Provides a wrapper for {@link EstadoService}.
 *
 * @author Brian Wing Shun Chan
 * @see EstadoService
 * @generated
 */
public class EstadoServiceWrapper
	implements EstadoService, ServiceWrapper<EstadoService> {

	public EstadoServiceWrapper(EstadoService estadoService) {
		_estadoService = estadoService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _estadoService.getOSGiServiceIdentifier();
	}

	@Override
	public EstadoService getWrappedService() {
		return _estadoService;
	}

	@Override
	public void setWrappedService(EstadoService estadoService) {
		_estadoService = estadoService;
	}

	private EstadoService _estadoService;

}