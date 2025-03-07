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
 * Provides the local service utility for FechaHito. This utility wraps
 * <code>com.plan.igualdad.liferay.portlet.manager.service.impl.FechaHitoLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see FechaHitoLocalService
 * @generated
 */
public class FechaHitoLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.plan.igualdad.liferay.portlet.manager.service.impl.FechaHitoLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the fecha hito to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FechaHitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fechaHito the fecha hito
	 * @return the fecha hito that was added
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.FechaHito
		addFechaHito(
			com.plan.igualdad.liferay.portlet.manager.model.FechaHito
				fechaHito) {

		return getService().addFechaHito(fechaHito);
	}

	/**
	 * Creates a new fecha hito with the primary key. Does not add the fecha hito to the database.
	 *
	 * @param fechaHitoPK the primary key for the new fecha hito
	 * @return the new fecha hito
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.FechaHito
		createFechaHito(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				FechaHitoPK fechaHitoPK) {

		return getService().createFechaHito(fechaHitoPK);
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
	 * Deletes the fecha hito from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FechaHitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fechaHito the fecha hito
	 * @return the fecha hito that was removed
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.FechaHito
		deleteFechaHito(
			com.plan.igualdad.liferay.portlet.manager.model.FechaHito
				fechaHito) {

		return getService().deleteFechaHito(fechaHito);
	}

	/**
	 * Deletes the fecha hito with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FechaHitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fechaHitoPK the primary key of the fecha hito
	 * @return the fecha hito that was removed
	 * @throws PortalException if a fecha hito with the primary key could not be found
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.FechaHito
			deleteFechaHito(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					FechaHitoPK fechaHitoPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteFechaHito(fechaHitoPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.FechaHitoModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.FechaHitoModelImpl</code>.
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

	public static com.plan.igualdad.liferay.portlet.manager.model.FechaHito
		fetchFechaHito(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				FechaHitoPK fechaHitoPK) {

		return getService().fetchFechaHito(fechaHitoPK);
	}

	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.FechaHito>
			findByCompVersion(long compId, long versionId) {

		return getService().findByCompVersion(compId, versionId);
	}

	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.FechaHito>
			findByCompVersionEjecutados(long compId, long versionId) {

		return getService().findByCompVersionEjecutados(compId, versionId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the fecha hito with the primary key.
	 *
	 * @param fechaHitoPK the primary key of the fecha hito
	 * @return the fecha hito
	 * @throws PortalException if a fecha hito with the primary key could not be found
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.FechaHito
			getFechaHito(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					FechaHitoPK fechaHitoPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getFechaHito(fechaHitoPK);
	}

	/**
	 * Returns a range of all the fecha hitos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.FechaHitoModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fecha hitos
	 * @param end the upper bound of the range of fecha hitos (not inclusive)
	 * @return the range of fecha hitos
	 */
	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.FechaHito>
			getFechaHitos(int start, int end) {

		return getService().getFechaHitos(start, end);
	}

	/**
	 * Returns the number of fecha hitos.
	 *
	 * @return the number of fecha hitos
	 */
	public static int getFechaHitosCount() {
		return getService().getFechaHitosCount();
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
	 * Updates the fecha hito in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FechaHitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fechaHito the fecha hito
	 * @return the fecha hito that was updated
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.FechaHito
		updateFechaHito(
			com.plan.igualdad.liferay.portlet.manager.model.FechaHito
				fechaHito) {

		return getService().updateFechaHito(fechaHito);
	}

	public static FechaHitoLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FechaHitoLocalService, FechaHitoLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(FechaHitoLocalService.class);

		ServiceTracker<FechaHitoLocalService, FechaHitoLocalService>
			serviceTracker =
				new ServiceTracker
					<FechaHitoLocalService, FechaHitoLocalService>(
						bundle.getBundleContext(), FechaHitoLocalService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}