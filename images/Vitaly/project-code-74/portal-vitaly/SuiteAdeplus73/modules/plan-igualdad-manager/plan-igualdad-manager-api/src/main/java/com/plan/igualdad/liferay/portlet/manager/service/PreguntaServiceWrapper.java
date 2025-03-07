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
 * Provides a wrapper for {@link PreguntaService}.
 *
 * @author Brian Wing Shun Chan
 * @see PreguntaService
 * @generated
 */
public class PreguntaServiceWrapper
	implements PreguntaService, ServiceWrapper<PreguntaService> {

	public PreguntaServiceWrapper(PreguntaService preguntaService) {
		_preguntaService = preguntaService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _preguntaService.getOSGiServiceIdentifier();
	}

	@Override
	public PreguntaService getWrappedService() {
		return _preguntaService;
	}

	@Override
	public void setWrappedService(PreguntaService preguntaService) {
		_preguntaService = preguntaService;
	}

	private PreguntaService _preguntaService;

}