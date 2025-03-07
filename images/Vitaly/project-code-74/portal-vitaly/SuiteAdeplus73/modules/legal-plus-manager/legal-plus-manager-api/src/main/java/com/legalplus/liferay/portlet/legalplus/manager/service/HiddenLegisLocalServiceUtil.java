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
 * Provides the local service utility for HiddenLegis. This utility wraps
 * <code>com.legalplus.liferay.portlet.legalplus.manager.service.impl.HiddenLegisLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see HiddenLegisLocalService
 * @generated
 */
public class HiddenLegisLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.legalplus.liferay.portlet.legalplus.manager.service.impl.HiddenLegisLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the hidden legis to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HiddenLegisLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param hiddenLegis the hidden legis
	 * @return the hidden legis that was added
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
			addHiddenLegis(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					HiddenLegis hiddenLegis) {

		return getService().addHiddenLegis(hiddenLegis);
	}

	/**
	 * Creates a new hidden legis with the primary key. Does not add the hidden legis to the database.
	 *
	 * @param hiddenLegisPK the primary key for the new hidden legis
	 * @return the new hidden legis
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
			createHiddenLegis(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.HiddenLegisPK hiddenLegisPK) {

		return getService().createHiddenLegis(hiddenLegisPK);
	}

	/**
	 * Deletes the hidden legis from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HiddenLegisLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param hiddenLegis the hidden legis
	 * @return the hidden legis that was removed
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
			deleteHiddenLegis(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					HiddenLegis hiddenLegis) {

		return getService().deleteHiddenLegis(hiddenLegis);
	}

	/**
	 * Deletes the hidden legis with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HiddenLegisLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param hiddenLegisPK the primary key of the hidden legis
	 * @return the hidden legis that was removed
	 * @throws PortalException if a hidden legis with the primary key could not be found
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
				deleteHiddenLegis(
					com.legalplus.liferay.portlet.legalplus.manager.service.
						persistence.HiddenLegisPK hiddenLegisPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteHiddenLegis(hiddenLegisPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.HiddenLegisModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.HiddenLegisModelImpl</code>.
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
		com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
			fetchHiddenLegis(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.HiddenLegisPK hiddenLegisPK) {

		return getService().fetchHiddenLegis(hiddenLegisPK);
	}

	public static java.util.List<String> fetchLegisByEmpresaId(long empresaId) {
		return getService().fetchLegisByEmpresaId(empresaId);
	}

	/**
	 * Returns the hidden legis with the primary key.
	 *
	 * @param hiddenLegisPK the primary key of the hidden legis
	 * @return the hidden legis
	 * @throws PortalException if a hidden legis with the primary key could not be found
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
				getHiddenLegis(
					com.legalplus.liferay.portlet.legalplus.manager.service.
						persistence.HiddenLegisPK hiddenLegisPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getHiddenLegis(hiddenLegisPK);
	}

	/**
	 * Returns a range of all the hidden legises.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.HiddenLegisModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of hidden legises
	 * @param end the upper bound of the range of hidden legises (not inclusive)
	 * @return the range of hidden legises
	 */
	public static java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis>
			getHiddenLegises(int start, int end) {

		return getService().getHiddenLegises(start, end);
	}

	/**
	 * Returns the number of hidden legises.
	 *
	 * @return the number of hidden legises
	 */
	public static int getHiddenLegisesCount() {
		return getService().getHiddenLegisesCount();
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
	 * Updates the hidden legis in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HiddenLegisLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param hiddenLegis the hidden legis
	 * @return the hidden legis that was updated
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
			updateHiddenLegis(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					HiddenLegis hiddenLegis) {

		return getService().updateHiddenLegis(hiddenLegis);
	}

	public static HiddenLegisLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<HiddenLegisLocalService, HiddenLegisLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(HiddenLegisLocalService.class);

		ServiceTracker<HiddenLegisLocalService, HiddenLegisLocalService>
			serviceTracker =
				new ServiceTracker
					<HiddenLegisLocalService, HiddenLegisLocalService>(
						bundle.getBundleContext(),
						HiddenLegisLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}