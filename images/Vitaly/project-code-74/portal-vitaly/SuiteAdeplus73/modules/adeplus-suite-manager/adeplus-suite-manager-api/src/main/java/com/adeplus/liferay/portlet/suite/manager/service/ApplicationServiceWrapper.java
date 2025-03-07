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

package com.adeplus.liferay.portlet.suite.manager.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ApplicationService}.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationService
 * @generated
 */
public class ApplicationServiceWrapper
	implements ApplicationService, ServiceWrapper<ApplicationService> {

	public ApplicationServiceWrapper(ApplicationService applicationService) {
		_applicationService = applicationService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _applicationService.getOSGiServiceIdentifier();
	}

	@Override
	public ApplicationService getWrappedService() {
		return _applicationService;
	}

	@Override
	public void setWrappedService(ApplicationService applicationService) {
		_applicationService = applicationService;
	}

	private ApplicationService _applicationService;

}