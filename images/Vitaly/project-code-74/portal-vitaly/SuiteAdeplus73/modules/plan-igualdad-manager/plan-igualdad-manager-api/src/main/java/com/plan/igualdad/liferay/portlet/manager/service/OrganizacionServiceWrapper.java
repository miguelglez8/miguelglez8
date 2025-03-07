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
 * Provides a wrapper for {@link OrganizacionService}.
 *
 * @author Brian Wing Shun Chan
 * @see OrganizacionService
 * @generated
 */
public class OrganizacionServiceWrapper
	implements OrganizacionService, ServiceWrapper<OrganizacionService> {

	public OrganizacionServiceWrapper(OrganizacionService organizacionService) {
		_organizacionService = organizacionService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _organizacionService.getOSGiServiceIdentifier();
	}

	@Override
	public OrganizacionService getWrappedService() {
		return _organizacionService;
	}

	@Override
	public void setWrappedService(OrganizacionService organizacionService) {
		_organizacionService = organizacionService;
	}

	private OrganizacionService _organizacionService;

}