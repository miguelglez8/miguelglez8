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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Vinculacion. This utility wraps
 * <code>com.canal.etico.liferay.portlet.canal.manager.service.impl.VinculacionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see VinculacionLocalService
 * @generated
 */
public class VinculacionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.canal.etico.liferay.portlet.canal.manager.service.impl.VinculacionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the vinculacion to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VinculacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param vinculacion the vinculacion
	 * @return the vinculacion that was added
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
			addVinculacion(
				com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
					vinculacion) {

		return getService().addVinculacion(vinculacion);
	}

	/**
	 * Creates a new vinculacion with the primary key. Does not add the vinculacion to the database.
	 *
	 * @param vinculacionId the primary key for the new vinculacion
	 * @return the new vinculacion
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
			createVinculacion(long vinculacionId) {

		return getService().createVinculacion(vinculacionId);
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

	/**
	 * Deletes the vinculacion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VinculacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param vinculacionId the primary key of the vinculacion
	 * @return the vinculacion that was removed
	 * @throws PortalException if a vinculacion with the primary key could not be found
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
				deleteVinculacion(long vinculacionId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteVinculacion(vinculacionId);
	}

	/**
	 * Deletes the vinculacion from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VinculacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param vinculacion the vinculacion
	 * @return the vinculacion that was removed
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
			deleteVinculacion(
				com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
					vinculacion) {

		return getService().deleteVinculacion(vinculacion);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.VinculacionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.VinculacionModelImpl</code>.
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

	public static
		com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
			fetchVinculacion(long vinculacionId) {

		return getService().fetchVinculacion(vinculacionId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion>
			getAllVinculacionesActiveFromCompany(long compId) {

		return getService().getAllVinculacionesActiveFromCompany(compId);
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion>
			getAllVinculacionesFromCompany(long compId) {

		return getService().getAllVinculacionesFromCompany(compId);
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
	 * Returns the vinculacion with the primary key.
	 *
	 * @param vinculacionId the primary key of the vinculacion
	 * @return the vinculacion
	 * @throws PortalException if a vinculacion with the primary key could not be found
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
				getVinculacion(long vinculacionId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getVinculacion(vinculacionId);
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion>
			getVinculacionesFromCompany(long compId) {

		return getService().getVinculacionesFromCompany(compId);
	}

	/**
	 * Returns a range of all the vinculacions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.VinculacionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of vinculacions
	 * @param end the upper bound of the range of vinculacions (not inclusive)
	 * @return the range of vinculacions
	 */
	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion>
			getVinculacions(int start, int end) {

		return getService().getVinculacions(start, end);
	}

	/**
	 * Returns the number of vinculacions.
	 *
	 * @return the number of vinculacions
	 */
	public static int getVinculacionsCount() {
		return getService().getVinculacionsCount();
	}

	/**
	 * Updates the vinculacion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VinculacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param vinculacion the vinculacion
	 * @return the vinculacion that was updated
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
			updateVinculacion(
				com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
					vinculacion) {

		return getService().updateVinculacion(vinculacion);
	}

	public static VinculacionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<VinculacionLocalService, VinculacionLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(VinculacionLocalService.class);

		ServiceTracker<VinculacionLocalService, VinculacionLocalService>
			serviceTracker =
				new ServiceTracker
					<VinculacionLocalService, VinculacionLocalService>(
						bundle.getBundleContext(),
						VinculacionLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}