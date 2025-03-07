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
 * Provides a wrapper for {@link EstadoEmpresaService}.
 *
 * @author Brian Wing Shun Chan
 * @see EstadoEmpresaService
 * @generated
 */
public class EstadoEmpresaServiceWrapper
	implements EstadoEmpresaService, ServiceWrapper<EstadoEmpresaService> {

	public EstadoEmpresaServiceWrapper(
		EstadoEmpresaService estadoEmpresaService) {

		_estadoEmpresaService = estadoEmpresaService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _estadoEmpresaService.getOSGiServiceIdentifier();
	}

	@Override
	public EstadoEmpresaService getWrappedService() {
		return _estadoEmpresaService;
	}

	@Override
	public void setWrappedService(EstadoEmpresaService estadoEmpresaService) {
		_estadoEmpresaService = estadoEmpresaService;
	}

	private EstadoEmpresaService _estadoEmpresaService;

}