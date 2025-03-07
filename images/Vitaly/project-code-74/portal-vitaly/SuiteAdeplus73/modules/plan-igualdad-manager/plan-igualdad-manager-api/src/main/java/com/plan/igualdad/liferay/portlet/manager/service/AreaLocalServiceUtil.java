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
 * Provides the local service utility for Area. This utility wraps
 * <code>com.plan.igualdad.liferay.portlet.manager.service.impl.AreaLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AreaLocalService
 * @generated
 */
public class AreaLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.plan.igualdad.liferay.portlet.manager.service.impl.AreaLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the area to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AreaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param area the area
	 * @return the area that was added
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.Area addArea(
		com.plan.igualdad.liferay.portlet.manager.model.Area area) {

		return getService().addArea(area);
	}

	/**
	 * Creates a new area with the primary key. Does not add the area to the database.
	 *
	 * @param areaPK the primary key for the new area
	 * @return the new area
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.Area
		createArea(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.AreaPK
				areaPK) {

		return getService().createArea(areaPK);
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
	 * Deletes the area from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AreaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param area the area
	 * @return the area that was removed
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.Area
		deleteArea(com.plan.igualdad.liferay.portlet.manager.model.Area area) {

		return getService().deleteArea(area);
	}

	/**
	 * Deletes the area with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AreaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param areaPK the primary key of the area
	 * @return the area that was removed
	 * @throws PortalException if a area with the primary key could not be found
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.Area
			deleteArea(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					AreaPK areaPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteArea(areaPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.AreaModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.AreaModelImpl</code>.
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

	public static com.plan.igualdad.liferay.portlet.manager.model.Area
		fetchArea(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.AreaPK
				areaPK) {

		return getService().fetchArea(areaPK);
	}

	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Area> findByArea(
			long compId, long versionId) {

		return getService().findByArea(compId, versionId);
	}

	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Area> findByAreaActiva(
			long compId, long versionId) {

		return getService().findByAreaActiva(compId, versionId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the area with the primary key.
	 *
	 * @param areaPK the primary key of the area
	 * @return the area
	 * @throws PortalException if a area with the primary key could not be found
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.Area getArea(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.AreaPK
				areaPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getArea(areaPK);
	}

	/**
	 * Returns a range of all the areas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.AreaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of areas
	 * @param end the upper bound of the range of areas (not inclusive)
	 * @return the range of areas
	 */
	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Area> getAreas(
			int start, int end) {

		return getService().getAreas(start, end);
	}

	/**
	 * Returns the number of areas.
	 *
	 * @return the number of areas
	 */
	public static int getAreasCount() {
		return getService().getAreasCount();
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
	 * Updates the area in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AreaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param area the area
	 * @return the area that was updated
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.Area
		updateArea(com.plan.igualdad.liferay.portlet.manager.model.Area area) {

		return getService().updateArea(area);
	}

	public static AreaLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AreaLocalService, AreaLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AreaLocalService.class);

		ServiceTracker<AreaLocalService, AreaLocalService> serviceTracker =
			new ServiceTracker<AreaLocalService, AreaLocalService>(
				bundle.getBundleContext(), AreaLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}