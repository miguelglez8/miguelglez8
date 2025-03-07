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
 * Provides a wrapper for {@link CompClientApplicationService}.
 *
 * @author Brian Wing Shun Chan
 * @see CompClientApplicationService
 * @generated
 */
public class CompClientApplicationServiceWrapper
	implements CompClientApplicationService,
			   ServiceWrapper<CompClientApplicationService> {

	public CompClientApplicationServiceWrapper(
		CompClientApplicationService compClientApplicationService) {

		_compClientApplicationService = compClientApplicationService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _compClientApplicationService.getOSGiServiceIdentifier();
	}

	@Override
	public CompClientApplicationService getWrappedService() {
		return _compClientApplicationService;
	}

	@Override
	public void setWrappedService(
		CompClientApplicationService compClientApplicationService) {

		_compClientApplicationService = compClientApplicationService;
	}

	private CompClientApplicationService _compClientApplicationService;

}