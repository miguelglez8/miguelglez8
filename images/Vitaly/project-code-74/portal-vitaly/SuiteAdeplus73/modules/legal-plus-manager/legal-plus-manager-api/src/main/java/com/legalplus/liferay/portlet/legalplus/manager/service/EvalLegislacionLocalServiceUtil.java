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

package com.legalplus.liferay.portlet.legalplus.manager.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for EvalLegislacion. This utility wraps
 * <code>com.legalplus.liferay.portlet.legalplus.manager.service.impl.EvalLegislacionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see EvalLegislacionLocalService
 * @generated
 */
public class EvalLegislacionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.legalplus.liferay.portlet.legalplus.manager.service.impl.EvalLegislacionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the eval legislacion to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvalLegislacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evalLegislacion the eval legislacion
	 * @return the eval legislacion that was added
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
			addEvalLegislacion(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					EvalLegislacion evalLegislacion) {

		return getService().addEvalLegislacion(evalLegislacion);
	}

	/**
	 * Creates a new eval legislacion with the primary key. Does not add the eval legislacion to the database.
	 *
	 * @param evalLegislacionPK the primary key for the new eval legislacion
	 * @return the new eval legislacion
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
			createEvalLegislacion(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.EvalLegislacionPK evalLegislacionPK) {

		return getService().createEvalLegislacion(evalLegislacionPK);
	}

	/**
	 * Deletes the eval legislacion from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvalLegislacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evalLegislacion the eval legislacion
	 * @return the eval legislacion that was removed
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
			deleteEvalLegislacion(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					EvalLegislacion evalLegislacion) {

		return getService().deleteEvalLegislacion(evalLegislacion);
	}

	/**
	 * Deletes the eval legislacion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvalLegislacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evalLegislacionPK the primary key of the eval legislacion
	 * @return the eval legislacion that was removed
	 * @throws PortalException if a eval legislacion with the primary key could not be found
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
				deleteEvalLegislacion(
					com.legalplus.liferay.portlet.legalplus.manager.service.
						persistence.EvalLegislacionPK evalLegislacionPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteEvalLegislacion(evalLegislacionPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.EvalLegislacionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.EvalLegislacionModelImpl</code>.
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
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
			fetchEvalLegislacion(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.EvalLegislacionPK evalLegislacionPK) {

		return getService().fetchEvalLegislacion(evalLegislacionPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static String getCalendarioEvalLegislaciones(Long compId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCalendarioEvalLegislaciones(compId);
	}

	/**
	 * Returns the eval legislacion with the primary key.
	 *
	 * @param evalLegislacionPK the primary key of the eval legislacion
	 * @return the eval legislacion
	 * @throws PortalException if a eval legislacion with the primary key could not be found
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
				getEvalLegislacion(
					com.legalplus.liferay.portlet.legalplus.manager.service.
						persistence.EvalLegislacionPK evalLegislacionPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getEvalLegislacion(evalLegislacionPK);
	}

	/**
	 * Returns a range of all the eval legislacions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.EvalLegislacionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of eval legislacions
	 * @param end the upper bound of the range of eval legislacions (not inclusive)
	 * @return the range of eval legislacions
	 */
	public static java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion>
			getEvalLegislacions(int start, int end) {

		return getService().getEvalLegislacions(start, end);
	}

	/**
	 * Returns the number of eval legislacions.
	 *
	 * @return the number of eval legislacions
	 */
	public static int getEvalLegislacionsCount() {
		return getService().getEvalLegislacionsCount();
	}

	public static String getEvaluacionesLegislacion(
		Long compId, String legislacionId) {

		return getService().getEvaluacionesLegislacion(compId, legislacionId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
			getLastEvalLegislacion(Long compId, String legislacionId) {

		return getService().getLastEvalLegislacion(compId, legislacionId);
	}

	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
			getLastEvalLegislacionCumplimentada(
				Long compId, String legislacionId) {

		return getService().getLastEvalLegislacionCumplimentada(
			compId, legislacionId);
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
	 * Updates the eval legislacion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvalLegislacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evalLegislacion the eval legislacion
	 * @return the eval legislacion that was updated
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
			updateEvalLegislacion(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					EvalLegislacion evalLegislacion) {

		return getService().updateEvalLegislacion(evalLegislacion);
	}

	public static EvalLegislacionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<EvalLegislacionLocalService, EvalLegislacionLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			EvalLegislacionLocalService.class);

		ServiceTracker<EvalLegislacionLocalService, EvalLegislacionLocalService>
			serviceTracker =
				new ServiceTracker
					<EvalLegislacionLocalService, EvalLegislacionLocalService>(
						bundle.getBundleContext(),
						EvalLegislacionLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}