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
 * Provides the local service utility for PuestoTrabajo. This utility wraps
 * <code>com.plan.igualdad.liferay.portlet.manager.service.impl.PuestoTrabajoLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PuestoTrabajoLocalService
 * @generated
 */
public class PuestoTrabajoLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.plan.igualdad.liferay.portlet.manager.service.impl.PuestoTrabajoLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the puesto trabajo to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PuestoTrabajoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param puestoTrabajo the puesto trabajo
	 * @return the puesto trabajo that was added
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
		addPuestoTrabajo(
			com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
				puestoTrabajo) {

		return getService().addPuestoTrabajo(puestoTrabajo);
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
	 * Creates a new puesto trabajo with the primary key. Does not add the puesto trabajo to the database.
	 *
	 * @param puestoTrabajoPK the primary key for the new puesto trabajo
	 * @return the new puesto trabajo
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
		createPuestoTrabajo(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				PuestoTrabajoPK puestoTrabajoPK) {

		return getService().createPuestoTrabajo(puestoTrabajoPK);
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
	 * Deletes the puesto trabajo from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PuestoTrabajoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param puestoTrabajo the puesto trabajo
	 * @return the puesto trabajo that was removed
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
		deletePuestoTrabajo(
			com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
				puestoTrabajo) {

		return getService().deletePuestoTrabajo(puestoTrabajo);
	}

	/**
	 * Deletes the puesto trabajo with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PuestoTrabajoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param puestoTrabajoPK the primary key of the puesto trabajo
	 * @return the puesto trabajo that was removed
	 * @throws PortalException if a puesto trabajo with the primary key could not be found
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
			deletePuestoTrabajo(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					PuestoTrabajoPK puestoTrabajoPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePuestoTrabajo(puestoTrabajoPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.PuestoTrabajoModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.PuestoTrabajoModelImpl</code>.
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

	public static com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
		fetchPuestoTrabajo(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				PuestoTrabajoPK puestoTrabajoPK) {

		return getService().fetchPuestoTrabajo(puestoTrabajoPK);
	}

	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo>
			findByPuestoArea(long compId, long versionId, long areaId) {

		return getService().findByPuestoArea(compId, versionId, areaId);
	}

	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo>
			findByPuestoResponsabilidad(long compId, long versionId) {

		return getService().findByPuestoResponsabilidad(compId, versionId);
	}

	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo>
			findPuestoTrabajo(long compId, long versionId) {

		return getService().findPuestoTrabajo(compId, versionId);
	}

	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo>
			findPuestoTrabajoActivo(long compId, long versionId) {

		return getService().findPuestoTrabajoActivo(compId, versionId);
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
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the puesto trabajo with the primary key.
	 *
	 * @param puestoTrabajoPK the primary key of the puesto trabajo
	 * @return the puesto trabajo
	 * @throws PortalException if a puesto trabajo with the primary key could not be found
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
			getPuestoTrabajo(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					PuestoTrabajoPK puestoTrabajoPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPuestoTrabajo(puestoTrabajoPK);
	}

	/**
	 * Returns a range of all the puesto trabajos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.PuestoTrabajoModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of puesto trabajos
	 * @param end the upper bound of the range of puesto trabajos (not inclusive)
	 * @return the range of puesto trabajos
	 */
	public static java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo>
			getPuestoTrabajos(int start, int end) {

		return getService().getPuestoTrabajos(start, end);
	}

	/**
	 * Returns the number of puesto trabajos.
	 *
	 * @return the number of puesto trabajos
	 */
	public static int getPuestoTrabajosCount() {
		return getService().getPuestoTrabajosCount();
	}

	/**
	 * Updates the puesto trabajo in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PuestoTrabajoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param puestoTrabajo the puesto trabajo
	 * @return the puesto trabajo that was updated
	 */
	public static com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
		updatePuestoTrabajo(
			com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
				puestoTrabajo) {

		return getService().updatePuestoTrabajo(puestoTrabajo);
	}

	public static PuestoTrabajoLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<PuestoTrabajoLocalService, PuestoTrabajoLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			PuestoTrabajoLocalService.class);

		ServiceTracker<PuestoTrabajoLocalService, PuestoTrabajoLocalService>
			serviceTracker =
				new ServiceTracker
					<PuestoTrabajoLocalService, PuestoTrabajoLocalService>(
						bundle.getBundleContext(),
						PuestoTrabajoLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}