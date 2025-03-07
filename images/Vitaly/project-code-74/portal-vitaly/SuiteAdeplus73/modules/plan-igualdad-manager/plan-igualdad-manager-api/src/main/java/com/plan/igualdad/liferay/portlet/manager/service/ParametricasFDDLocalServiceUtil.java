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
 * Provides the local service utility for ParametricasFDD. This utility wraps
 * <code>com.plan.igualdad.liferay.portlet.manager.service.impl.ParametricasFDDLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ParametricasFDDLocalService
 * @generated
 */
public class ParametricasFDDLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.plan.igualdad.liferay.portlet.manager.service.impl.ParametricasFDDLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the parametricas fdd to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ParametricasFDDLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param parametricasFDD the parametricas fdd
	 * @return the parametricas fdd that was added
	 */
	public static
		com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD
			addParametricasFDD(
				com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD
					parametricasFDD) {

		return getService().addParametricasFDD(parametricasFDD);
	}

	/**
	 * Creates a new parametricas fdd with the primary key. Does not add the parametricas fdd to the database.
	 *
	 * @param parametricasFDDPK the primary key for the new parametricas fdd
	 * @return the new parametricas fdd
	 */
	public static
		com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD
			createParametricasFDD(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					ParametricasFDDPK parametricasFDDPK) {

		return getService().createParametricasFDD(parametricasFDDPK);
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
	 * Deletes the parametricas fdd from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ParametricasFDDLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param parametricasFDD the parametricas fdd
	 * @return the parametricas fdd that was removed
	 */
	public static
		com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD
			deleteParametricasFDD(
				com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD
					parametricasFDD) {

		return getService().deleteParametricasFDD(parametricasFDD);
	}

	/**
	 * Deletes the parametricas fdd with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ParametricasFDDLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param parametricasFDDPK the primary key of the parametricas fdd
	 * @return the parametricas fdd that was removed
	 * @throws PortalException if a parametricas fdd with the primary key could not be found
	 */
	public static
		com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD
				deleteParametricasFDD(
					com.plan.igualdad.liferay.portlet.manager.service.
						persistence.ParametricasFDDPK parametricasFDDPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteParametricasFDD(parametricasFDDPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.ParametricasFDDModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.ParametricasFDDModelImpl</code>.
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
		com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD
			fetchParametricasFDD(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					ParametricasFDDPK parametricasFDDPK) {

		return getService().fetchParametricasFDD(parametricasFDDPK);
	}

	public static
		com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD
			findParametrica(long compId, long versionId, String idParametrica) {

		return getService().findParametrica(compId, versionId, idParametrica);
	}

	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD>
			findParametricasByCompIdAndVersion(long compId, long versionId) {

		return getService().findParametricasByCompIdAndVersion(
			compId, versionId);
	}

	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD>
			findParametricasByMateria(
				long compId, long versionId, String tipo, Integer materia) {

		return getService().findParametricasByMateria(
			compId, versionId, tipo, materia);
	}

	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD>
			findParametricasByTipo(long compId, long versionId, String tipo) {

		return getService().findParametricasByTipo(compId, versionId, tipo);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
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
	 * Returns the parametricas fdd with the primary key.
	 *
	 * @param parametricasFDDPK the primary key of the parametricas fdd
	 * @return the parametricas fdd
	 * @throws PortalException if a parametricas fdd with the primary key could not be found
	 */
	public static
		com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD
				getParametricasFDD(
					com.plan.igualdad.liferay.portlet.manager.service.
						persistence.ParametricasFDDPK parametricasFDDPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getParametricasFDD(parametricasFDDPK);
	}

	/**
	 * Returns a range of all the parametricas fdds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.ParametricasFDDModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of parametricas fdds
	 * @param end the upper bound of the range of parametricas fdds (not inclusive)
	 * @return the range of parametricas fdds
	 */
	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD>
			getParametricasFDDs(int start, int end) {

		return getService().getParametricasFDDs(start, end);
	}

	/**
	 * Returns the number of parametricas fdds.
	 *
	 * @return the number of parametricas fdds
	 */
	public static int getParametricasFDDsCount() {
		return getService().getParametricasFDDsCount();
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
	 * Updates the parametricas fdd in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ParametricasFDDLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param parametricasFDD the parametricas fdd
	 * @return the parametricas fdd that was updated
	 */
	public static
		com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD
			updateParametricasFDD(
				com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD
					parametricasFDD) {

		return getService().updateParametricasFDD(parametricasFDD);
	}

	public static ParametricasFDDLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ParametricasFDDLocalService, ParametricasFDDLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ParametricasFDDLocalService.class);

		ServiceTracker<ParametricasFDDLocalService, ParametricasFDDLocalService>
			serviceTracker =
				new ServiceTracker
					<ParametricasFDDLocalService, ParametricasFDDLocalService>(
						bundle.getBundleContext(),
						ParametricasFDDLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}