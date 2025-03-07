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

package com.canal.etico.liferay.portlet.canal.manager.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DenunciaService}.
 *
 * @author Brian Wing Shun Chan
 * @see DenunciaService
 * @generated
 */
public class DenunciaServiceWrapper
	implements DenunciaService, ServiceWrapper<DenunciaService> {

	public DenunciaServiceWrapper(DenunciaService denunciaService) {
		_denunciaService = denunciaService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _denunciaService.getOSGiServiceIdentifier();
	}

	@Override
	public DenunciaService getWrappedService() {
		return _denunciaService;
	}

	@Override
	public void setWrappedService(DenunciaService denunciaService) {
		_denunciaService = denunciaService;
	}

	private DenunciaService _denunciaService;

}