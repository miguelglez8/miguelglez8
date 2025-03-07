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
 * Provides the local service utility for Estado. This utility wraps
 * <code>com.plan.igualdad.liferay.portlet.manager.service.impl.EstadoLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see EstadoLocalService
 * @generated
 */
public class EstadoLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.plan.igualdad.liferay.portlet.manager.service.impl.EstadoLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the estado to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EstadoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param estado the estado
	 * @return the estado that was added
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.Estado
		addEstado(
			com.plan.igualdad.liferay.portlet.manager.model.Estado estado) {

		return getService().addEstado(estado);
	}

	public static Boolean allowManagementSettings(long estadoId) {
		return getService().allowManagementSettings(estadoId);
	}

	/**
	 * Creates a new estado with the primary key. Does not add the estado to the database.
	 *
	 * @param estadoId the primary key for the new estado
	 * @return the new estado
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.Estado
		createEstado(long estadoId) {

		return getService().createEstado(estadoId);
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
	 * Deletes the estado from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EstadoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param estado the estado
	 * @return the estado that was removed
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.Estado
		deleteEstado(
			com.plan.igualdad.liferay.portlet.manager.model.Estado estado) {

		return getService().deleteEstado(estado);
	}

	/**
	 * Deletes the estado with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EstadoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param estadoId the primary key of the estado
	 * @return the estado that was removed
	 * @throws PortalException if a estado with the primary key could not be found
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.Estado
			deleteEstado(long estadoId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteEstado(estadoId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.EstadoModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.EstadoModelImpl</code>.
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

	public static com.plan.igualdad.liferay.portlet.manager.model.Estado
		fetchEstado(long estadoId) {

		return getService().fetchEstado(estadoId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the estado with the primary key.
	 *
	 * @param estadoId the primary key of the estado
	 * @return the estado
	 * @throws PortalException if a estado with the primary key could not be found
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.Estado
			getEstado(long estadoId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getEstado(estadoId);
	}

	/**
	 * Returns a range of all the estados.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.EstadoModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of estados
	 * @param end the upper bound of the range of estados (not inclusive)
	 * @return the range of estados
	 */
	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Estado> getEstados(
			int start, int end) {

		return getService().getEstados(start, end);
	}

	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Estado>
			getEstadosByActive() {

		return getService().getEstadosByActive();
	}

	/**
	 * Returns the number of estados.
	 *
	 * @return the number of estados
	 */
	public static int getEstadosCount() {
		return getService().getEstadosCount();
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
	 * Updates the estado in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EstadoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param estado the estado
	 * @return the estado that was updated
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.Estado
		updateEstado(
			com.plan.igualdad.liferay.portlet.manager.model.Estado estado) {

		return getService().updateEstado(estado);
	}

	public static EstadoLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EstadoLocalService, EstadoLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(EstadoLocalService.class);

		ServiceTracker<EstadoLocalService, EstadoLocalService> serviceTracker =
			new ServiceTracker<EstadoLocalService, EstadoLocalService>(
				bundle.getBundleContext(), EstadoLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}