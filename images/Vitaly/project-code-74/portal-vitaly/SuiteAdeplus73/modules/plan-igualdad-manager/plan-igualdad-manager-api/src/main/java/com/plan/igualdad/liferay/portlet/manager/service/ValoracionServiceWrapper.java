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
 * Provides a wrapper for {@link ValoracionService}.
 *
 * @author Brian Wing Shun Chan
 * @see ValoracionService
 * @generated
 */
public class ValoracionServiceWrapper
	implements ServiceWrapper<ValoracionService>, ValoracionService {

	public ValoracionServiceWrapper(ValoracionService valoracionService) {
		_valoracionService = valoracionService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _valoracionService.getOSGiServiceIdentifier();
	}

	@Override
	public ValoracionService getWrappedService() {
		return _valoracionService;
	}

	@Override
	public void setWrappedService(ValoracionService valoracionService) {
		_valoracionService = valoracionService;
	}

	private ValoracionService _valoracionService;

}