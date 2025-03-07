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
 * Provides the local service utility for Observacion. This utility wraps
 * <code>com.canal.etico.liferay.portlet.canal.manager.service.impl.ObservacionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ObservacionLocalService
 * @generated
 */
public class ObservacionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.canal.etico.liferay.portlet.canal.manager.service.impl.ObservacionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the observacion to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ObservacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param observacion the observacion
	 * @return the observacion that was added
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.Observacion
			addObservacion(
				com.canal.etico.liferay.portlet.canal.manager.model.Observacion
					observacion) {

		return getService().addObservacion(observacion);
	}

	/**
	 * Creates a new observacion with the primary key. Does not add the observacion to the database.
	 *
	 * @param observacionId the primary key for the new observacion
	 * @return the new observacion
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.Observacion
			createObservacion(long observacionId) {

		return getService().createObservacion(observacionId);
	}

	/**
	 * Deletes the observacion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ObservacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param observacionId the primary key of the observacion
	 * @return the observacion that was removed
	 * @throws PortalException if a observacion with the primary key could not be found
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.Observacion
				deleteObservacion(long observacionId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteObservacion(observacionId);
	}

	/**
	 * Deletes the observacion from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ObservacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param observacion the observacion
	 * @return the observacion that was removed
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.Observacion
			deleteObservacion(
				com.canal.etico.liferay.portlet.canal.manager.model.Observacion
					observacion) {

		return getService().deleteObservacion(observacion);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.ObservacionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.ObservacionModelImpl</code>.
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
		com.canal.etico.liferay.portlet.canal.manager.model.Observacion
			fetchObservacion(long observacionId) {

		return getService().fetchObservacion(observacionId);
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
	 * Returns the observacion with the primary key.
	 *
	 * @param observacionId the primary key of the observacion
	 * @return the observacion
	 * @throws PortalException if a observacion with the primary key could not be found
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.Observacion
				getObservacion(long observacionId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getObservacion(observacionId);
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Observacion>
			getObservacionesFromDenuncia(long denunciaId) {

		return getService().getObservacionesFromDenuncia(denunciaId);
	}

	/**
	 * Returns a range of all the observacions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.ObservacionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of observacions
	 * @param end the upper bound of the range of observacions (not inclusive)
	 * @return the range of observacions
	 */
	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Observacion>
			getObservacions(int start, int end) {

		return getService().getObservacions(start, end);
	}

	/**
	 * Returns the number of observacions.
	 *
	 * @return the number of observacions
	 */
	public static int getObservacionsCount() {
		return getService().getObservacionsCount();
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
	 * Updates the observacion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ObservacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param observacion the observacion
	 * @return the observacion that was updated
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.Observacion
			updateObservacion(
				com.canal.etico.liferay.portlet.canal.manager.model.Observacion
					observacion) {

		return getService().updateObservacion(observacion);
	}

	public static ObservacionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ObservacionLocalService, ObservacionLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ObservacionLocalService.class);

		ServiceTracker<ObservacionLocalService, ObservacionLocalService>
			serviceTracker =
				new ServiceTracker
					<ObservacionLocalService, ObservacionLocalService>(
						bundle.getBundleContext(),
						ObservacionLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}