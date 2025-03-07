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
 * Provides a wrapper for {@link AreaService}.
 *
 * @author Brian Wing Shun Chan
 * @see AreaService
 * @generated
 */
public class AreaServiceWrapper
	implements AreaService, ServiceWrapper<AreaService> {

	public AreaServiceWrapper(AreaService areaService) {
		_areaService = areaService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _areaService.getOSGiServiceIdentifier();
	}

	@Override
	public AreaService getWrappedService() {
		return _areaService;
	}

	@Override
	public void setWrappedService(AreaService areaService) {
		_areaService = areaService;
	}

	private AreaService _areaService;

}