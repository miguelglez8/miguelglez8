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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ImagenCanvasEst. This utility wraps
 * <code>com.plan.igualdad.liferay.portlet.manager.service.impl.ImagenCanvasEstLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ImagenCanvasEstLocalService
 * @generated
 */
public class ImagenCanvasEstLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.plan.igualdad.liferay.portlet.manager.service.impl.ImagenCanvasEstLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static
		com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
			addImagenCanvasEst(
				com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
					imagenCanvasEst) {

		return getService().addImagenCanvasEst(imagenCanvasEst);
	}

	public static int countImagenesCanvasBySection(
		long compId, long versionId, long seccionId, String canvasId) {

		return getService().countImagenesCanvasBySection(
			compId, versionId, seccionId, canvasId);
	}

	/**
	 * Creates a new imagen canvas est with the primary key. Does not add the imagen canvas est to the database.
	 *
	 * @param imagenCanvasEstPK the primary key for the new imagen canvas est
	 * @return the new imagen canvas est
	 */
	public static
		com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
			createImagenCanvasEst(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					ImagenCanvasEstPK imagenCanvasEstPK) {

		return getService().createImagenCanvasEst(imagenCanvasEstPK);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			createPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createPersistedModel(primaryKeyObj);
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
	public static
		com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
			deleteImagenCanvasEst(
				com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
					imagenCanvasEst) {

		return getService().deleteImagenCanvasEst(imagenCanvasEst);
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
	public static
		com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
				deleteImagenCanvasEst(
					com.plan.igualdad.liferay.portlet.manager.service.
						persistence.ImagenCanvasEstPK imagenCanvasEstPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteImagenCanvasEst(imagenCanvasEstPK);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst>
			fetchImagenCanvas(long compId, long versionId) {

		return getService().fetchImagenCanvas(compId, versionId);
	}

	public static
		com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
			fetchImagenCanvasEst(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					ImagenCanvasEstPK imagenCanvasEstPK) {

		return getService().fetchImagenCanvasEst(imagenCanvasEstPK);
	}

	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst>
			fetchImagenesCanvasBySection(
				long compId, long versionId, long seccionId, String canvasId) {

		return getService().fetchImagenesCanvasBySection(
			compId, versionId, seccionId, canvasId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the imagen canvas est with the primary key.
	 *
	 * @param imagenCanvasEstPK the primary key of the imagen canvas est
	 * @return the imagen canvas est
	 * @throws PortalException if a imagen canvas est with the primary key could not be found
	 */
	public static
		com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
				getImagenCanvasEst(
					com.plan.igualdad.liferay.portlet.manager.service.
						persistence.ImagenCanvasEstPK imagenCanvasEstPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getImagenCanvasEst(imagenCanvasEstPK);
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
	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst>
			getImagenCanvasEsts(int start, int end) {

		return getService().getImagenCanvasEsts(start, end);
	}

	/**
	 * Returns the number of imagen canvas ests.
	 *
	 * @return the number of imagen canvas ests
	 */
	public static int getImagenCanvasEstsCount() {
		return getService().getImagenCanvasEstsCount();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
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
	public static
		com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
			updateImagenCanvasEst(
				com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst
					imagenCanvasEst) {

		return getService().updateImagenCanvasEst(imagenCanvasEst);
	}

	public static ImagenCanvasEstLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ImagenCanvasEstLocalService, ImagenCanvasEstLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ImagenCanvasEstLocalService.class);

		ServiceTracker<ImagenCanvasEstLocalService, ImagenCanvasEstLocalService>
			serviceTracker =
				new ServiceTracker
					<ImagenCanvasEstLocalService, ImagenCanvasEstLocalService>(
						bundle.getBundleContext(),
						ImagenCanvasEstLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}