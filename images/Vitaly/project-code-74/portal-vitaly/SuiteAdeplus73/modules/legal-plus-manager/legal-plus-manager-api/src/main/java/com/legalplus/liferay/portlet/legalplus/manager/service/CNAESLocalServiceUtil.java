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
 * Provides the local service utility for CNAES. This utility wraps
 * <code>com.legalplus.liferay.portlet.legalplus.manager.service.impl.CNAESLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CNAESLocalService
 * @generated
 */
public class CNAESLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.legalplus.liferay.portlet.legalplus.manager.service.impl.CNAESLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the cnaes to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cnaes the cnaes
	 * @return the cnaes that was added
	 */
	public static com.legalplus.liferay.portlet.legalplus.manager.model.CNAES
		addCNAES(
			com.legalplus.liferay.portlet.legalplus.manager.model.CNAES cnaes) {

		return getService().addCNAES(cnaes);
	}

	/**
	 * Creates a new cnaes with the primary key. Does not add the cnaes to the database.
	 *
	 * @param cnae the primary key for the new cnaes
	 * @return the new cnaes
	 */
	public static com.legalplus.liferay.portlet.legalplus.manager.model.CNAES
		createCNAES(String cnae) {

		return getService().createCNAES(cnae);
	}

	/**
	 * Deletes the cnaes from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cnaes the cnaes
	 * @return the cnaes that was removed
	 */
	public static com.legalplus.liferay.portlet.legalplus.manager.model.CNAES
		deleteCNAES(
			com.legalplus.liferay.portlet.legalplus.manager.model.CNAES cnaes) {

		return getService().deleteCNAES(cnaes);
	}

	/**
	 * Deletes the cnaes with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cnae the primary key of the cnaes
	 * @return the cnaes that was removed
	 * @throws PortalException if a cnaes with the primary key could not be found
	 */
	public static com.legalplus.liferay.portlet.legalplus.manager.model.CNAES
			deleteCNAES(String cnae)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCNAES(cnae);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.CNAESModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.CNAESModelImpl</code>.
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

	public static com.legalplus.liferay.portlet.legalplus.manager.model.CNAES
		fetchBycnae(String cnae) {

		return getService().fetchBycnae(cnae);
	}

	public static com.legalplus.liferay.portlet.legalplus.manager.model.CNAES
		fetchCNAES(String cnae) {

		return getService().fetchCNAES(cnae);
	}

	/**
	 * Returns the cnaes with the primary key.
	 *
	 * @param cnae the primary key of the cnaes
	 * @return the cnaes
	 * @throws PortalException if a cnaes with the primary key could not be found
	 */
	public static com.legalplus.liferay.portlet.legalplus.manager.model.CNAES
			getCNAES(String cnae)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCNAES(cnae);
	}

	/**
	 * Returns a range of all the cnaeses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.CNAESModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cnaeses
	 * @param end the upper bound of the range of cnaeses (not inclusive)
	 * @return the range of cnaeses
	 */
	public static java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.CNAES> getCNAESs(
			int start, int end) {

		return getService().getCNAESs(start, end);
	}

	/**
	 * Returns the number of cnaeses.
	 *
	 * @return the number of cnaeses
	 */
	public static int getCNAESsCount() {
		return getService().getCNAESsCount();
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
	 * Updates the cnaes in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cnaes the cnaes
	 * @return the cnaes that was updated
	 */
	public static com.legalplus.liferay.portlet.legalplus.manager.model.CNAES
		updateCNAES(
			com.legalplus.liferay.portlet.legalplus.manager.model.CNAES cnaes) {

		return getService().updateCNAES(cnaes);
	}

	public static CNAESLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CNAESLocalService, CNAESLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CNAESLocalService.class);

		ServiceTracker<CNAESLocalService, CNAESLocalService> serviceTracker =
			new ServiceTracker<CNAESLocalService, CNAESLocalService>(
				bundle.getBundleContext(), CNAESLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}