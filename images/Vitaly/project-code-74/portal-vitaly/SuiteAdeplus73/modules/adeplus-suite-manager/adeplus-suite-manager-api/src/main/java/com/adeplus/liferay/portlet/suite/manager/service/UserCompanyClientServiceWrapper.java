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
 * Provides a wrapper for {@link UserCompanyClientService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserCompanyClientService
 * @generated
 */
public class UserCompanyClientServiceWrapper
	implements ServiceWrapper<UserCompanyClientService>,
			   UserCompanyClientService {

	public UserCompanyClientServiceWrapper(
		UserCompanyClientService userCompanyClientService) {

		_userCompanyClientService = userCompanyClientService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userCompanyClientService.getOSGiServiceIdentifier();
	}

	@Override
	public UserCompanyClientService getWrappedService() {
		return _userCompanyClientService;
	}

	@Override
	public void setWrappedService(
		UserCompanyClientService userCompanyClientService) {

		_userCompanyClientService = userCompanyClientService;
	}

	private UserCompanyClientService _userCompanyClientService;

}