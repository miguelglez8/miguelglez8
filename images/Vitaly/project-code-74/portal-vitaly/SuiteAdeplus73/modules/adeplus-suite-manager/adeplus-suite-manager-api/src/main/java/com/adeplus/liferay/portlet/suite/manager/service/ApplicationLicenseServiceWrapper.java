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
 * Provides a wrapper for {@link ApplicationLicenseService}.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationLicenseService
 * @generated
 */
public class ApplicationLicenseServiceWrapper
	implements ApplicationLicenseService,
			   ServiceWrapper<ApplicationLicenseService> {

	public ApplicationLicenseServiceWrapper(
		ApplicationLicenseService applicationLicenseService) {

		_applicationLicenseService = applicationLicenseService;
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense
		fetchByLicenseId(long licenseId) {

		return _applicationLicenseService.fetchByLicenseId(licenseId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _applicationLicenseService.getOSGiServiceIdentifier();
	}

	@Override
	public ApplicationLicenseService getWrappedService() {
		return _applicationLicenseService;
	}

	@Override
	public void setWrappedService(
		ApplicationLicenseService applicationLicenseService) {

		_applicationLicenseService = applicationLicenseService;
	}

	private ApplicationLicenseService _applicationLicenseService;

}