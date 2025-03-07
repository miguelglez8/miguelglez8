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

package com.legalplus.liferay.portlet.legalplus.manager.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContratoCompanyService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContratoCompanyService
 * @generated
 */
public class ContratoCompanyServiceWrapper
	implements ContratoCompanyService, ServiceWrapper<ContratoCompanyService> {

	public ContratoCompanyServiceWrapper(
		ContratoCompanyService contratoCompanyService) {

		_contratoCompanyService = contratoCompanyService;
	}

	@Override
	public String contractType(long userId) {
		return _contratoCompanyService.contractType(userId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contratoCompanyService.getOSGiServiceIdentifier();
	}

	@Override
	public ContratoCompanyService getWrappedService() {
		return _contratoCompanyService;
	}

	@Override
	public void setWrappedService(
		ContratoCompanyService contratoCompanyService) {

		_contratoCompanyService = contratoCompanyService;
	}

	private ContratoCompanyService _contratoCompanyService;

}