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
 * Provides a wrapper for {@link ImagenCanvasEstService}.
 *
 * @author Brian Wing Shun Chan
 * @see ImagenCanvasEstService
 * @generated
 */
public class ImagenCanvasEstServiceWrapper
	implements ImagenCanvasEstService, ServiceWrapper<ImagenCanvasEstService> {

	public ImagenCanvasEstServiceWrapper(
		ImagenCanvasEstService imagenCanvasEstService) {

		_imagenCanvasEstService = imagenCanvasEstService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _imagenCanvasEstService.getOSGiServiceIdentifier();
	}

	@Override
	public ImagenCanvasEstService getWrappedService() {
		return _imagenCanvasEstService;
	}

	@Override
	public void setWrappedService(
		ImagenCanvasEstService imagenCanvasEstService) {

		_imagenCanvasEstService = imagenCanvasEstService;
	}

	private ImagenCanvasEstService _imagenCanvasEstService;

}