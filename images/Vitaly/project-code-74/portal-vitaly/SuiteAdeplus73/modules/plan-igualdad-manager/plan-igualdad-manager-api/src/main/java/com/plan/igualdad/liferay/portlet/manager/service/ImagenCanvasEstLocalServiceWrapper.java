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
 * Provides a wrapper for {@link ImagenCanvasEstLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ImagenCanvasEstLocalService
 * @generated
 */
public class ImagenCanvasEstLocalServiceWrapper
	implements ImagenCanvasEstLocalService,
			   ServiceWrapper<ImagenCanvasEstLocalService> {

	public ImagenCanvasEstLocalServiceWrapper(
		ImagenCanvasEstLocalService imagenCanvasEstLocalService) {

		_imagenCanvasEstLocalService = imagenCanvasEstLocalService;
	}

	/**
	 * Adds the imagen canvas est to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImagenCanvasEstLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param imagenCanvasEst the imagen canvas est
	 * @return the imagen canvas est that was added
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
		addImagenCanvasEst(
			com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
				imagenCanvasEst) {

		return _imagenCanvasEstLocalService.addImagenCanvasEst(imagenCanvasEst);
	}

	@Override
	public int countImagenesCanvasBySection(
		long compId, long versionId, long seccionId, String canvasId) {

		return _imagenCanvasEstLocalService.countImagenesCanvasBySection(
			compId, versionId, seccionId, canvasId);
	}

	/**
	 * Creates a new imagen canvas est with the primary key. Does not add the imagen canvas est to the database.
	 *
	 * @param imagenCanvasEstPK the primary key for the new imagen canvas est
	 * @return the new imagen canvas est
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
		createImagenCanvasEst(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				ImagenCanvasEstPK imagenCanvasEstPK) {

		return _imagenCanvasEstLocalService.createImagenCanvasEst(
			imagenCanvasEstPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imagenCanvasEstLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the imagen canvas est from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImagenCanvasEstLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param imagenCanvasEst the imagen canvas est
	 * @return the imagen canvas est that was removed
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
		deleteImagenCanvasEst(
			com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
				imagenCanvasEst) {

		return _imagenCanvasEstLocalService.deleteImagenCanvasEst(
			imagenCanvasEst);
	}

	/**
	 * Deletes the imagen canvas est with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImagenCanvasEstLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param imagenCanvasEstPK the primary key of the imagen canvas est
	 * @return the imagen canvas est that was removed
	 * @throws PortalException if a imagen canvas est with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
			deleteImagenCanvasEst(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					ImagenCanvasEstPK imagenCanvasEstPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imagenCanvasEstLocalService.deleteImagenCanvasEst(
			imagenCanvasEstPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imagenCanvasEstLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _imagenCanvasEstLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _imagenCanvasEstLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.ImagenCanvasEstModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _imagenCanvasEstLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.ImagenCanvasEstModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _imagenCanvasEstLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _imagenCanvasEstLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _imagenCanvasEstLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst>
			fetchImagenCanvas(long compId, long versionId) {

		return _imagenCanvasEstLocalService.fetchImagenCanvas(
			compId, versionId);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
		fetchImagenCanvasEst(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				ImagenCanvasEstPK imagenCanvasEstPK) {

		return _imagenCanvasEstLocalService.fetchImagenCanvasEst(
			imagenCanvasEstPK);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst>
			fetchImagenesCanvasBySection(
				long compId, long versionId, long seccionId, String canvasId) {

		return _imagenCanvasEstLocalService.fetchImagenesCanvasBySection(
			compId, versionId, seccionId, canvasId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _imagenCanvasEstLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the imagen canvas est with the primary key.
	 *
	 * @param imagenCanvasEstPK the primary key of the imagen canvas est
	 * @return the imagen canvas est
	 * @throws PortalException if a imagen canvas est with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
			getImagenCanvasEst(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					ImagenCanvasEstPK imagenCanvasEstPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imagenCanvasEstLocalService.getImagenCanvasEst(
			imagenCanvasEstPK);
	}

	/**
	 * Returns a range of all the imagen canvas ests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.ImagenCanvasEstModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of imagen canvas ests
	 * @param end the upper bound of the range of imagen canvas ests (not inclusive)
	 * @return the range of imagen canvas ests
	 */
	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst>
			getImagenCanvasEsts(int start, int end) {

		return _imagenCanvasEstLocalService.getImagenCanvasEsts(start, end);
	}

	/**
	 * Returns the number of imagen canvas ests.
	 *
	 * @return the number of imagen canvas ests
	 */
	@Override
	public int getImagenCanvasEstsCount() {
		return _imagenCanvasEstLocalService.getImagenCanvasEstsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _imagenCanvasEstLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _imagenCanvasEstLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imagenCanvasEstLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the imagen canvas est in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImagenCanvasEstLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param imagenCanvasEst the imagen canvas est
	 * @return the imagen canvas est that was updated
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
		updateImagenCanvasEst(
			com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
				imagenCanvasEst) {

		return _imagenCanvasEstLocalService.updateImagenCanvasEst(
			imagenCanvasEst);
	}

	@Override
	public ImagenCanvasEstLocalService getWrappedService() {
		return _imagenCanvasEstLocalService;
	}

	@Override
	public void setWrappedService(
		ImagenCanvasEstLocalService imagenCanvasEstLocalService) {

		_imagenCanvasEstLocalService = imagenCanvasEstLocalService;
	}

	private ImagenCanvasEstLocalService _imagenCanvasEstLocalService;

}