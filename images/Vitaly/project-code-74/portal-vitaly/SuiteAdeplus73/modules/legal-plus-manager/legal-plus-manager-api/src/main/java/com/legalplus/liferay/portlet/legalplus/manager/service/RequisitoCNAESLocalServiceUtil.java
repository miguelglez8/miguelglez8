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
 * Provides the local service utility for RequisitoCNAES. This utility wraps
 * <code>com.legalplus.liferay.portlet.legalplus.manager.service.impl.RequisitoCNAESLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see RequisitoCNAESLocalService
 * @generated
 */
public class RequisitoCNAESLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.legalplus.liferay.portlet.legalplus.manager.service.impl.RequisitoCNAESLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the requisito cnaes to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RequisitoCNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param requisitoCNAES the requisito cnaes
	 * @return the requisito cnaes that was added
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
			addRequisitoCNAES(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					RequisitoCNAES requisitoCNAES) {

		return getService().addRequisitoCNAES(requisitoCNAES);
	}

	/**
	 * Creates a new requisito cnaes with the primary key. Does not add the requisito cnaes to the database.
	 *
	 * @param requisitoCNAESPK the primary key for the new requisito cnaes
	 * @return the new requisito cnaes
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
			createRequisitoCNAES(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.RequisitoCNAESPK requisitoCNAESPK) {

		return getService().createRequisitoCNAES(requisitoCNAESPK);
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
	 * Deletes the requisito cnaes from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RequisitoCNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param requisitoCNAES the requisito cnaes
	 * @return the requisito cnaes that was removed
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
			deleteRequisitoCNAES(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					RequisitoCNAES requisitoCNAES) {

		return getService().deleteRequisitoCNAES(requisitoCNAES);
	}

	/**
	 * Deletes the requisito cnaes with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RequisitoCNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param requisitoCNAESPK the primary key of the requisito cnaes
	 * @return the requisito cnaes that was removed
	 * @throws PortalException if a requisito cnaes with the primary key could not be found
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
				deleteRequisitoCNAES(
					com.legalplus.liferay.portlet.legalplus.manager.service.
						persistence.RequisitoCNAESPK requisitoCNAESPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteRequisitoCNAES(requisitoCNAESPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.RequisitoCNAESModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.RequisitoCNAESModelImpl</code>.
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
		com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
			fetchRequisitoCNAES(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.RequisitoCNAESPK requisitoCNAESPK) {

		return getService().fetchRequisitoCNAES(requisitoCNAESPK);
	}

	public static java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES>
			findByRequisito(String legislacionId, String requisitoId) {

		return getService().findByRequisito(legislacionId, requisitoId);
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
	 * Returns the requisito cnaes with the primary key.
	 *
	 * @param requisitoCNAESPK the primary key of the requisito cnaes
	 * @return the requisito cnaes
	 * @throws PortalException if a requisito cnaes with the primary key could not be found
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
				getRequisitoCNAES(
					com.legalplus.liferay.portlet.legalplus.manager.service.
						persistence.RequisitoCNAESPK requisitoCNAESPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getRequisitoCNAES(requisitoCNAESPK);
	}

	/**
	 * Returns a range of all the requisito cnaeses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.RequisitoCNAESModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of requisito cnaeses
	 * @param end the upper bound of the range of requisito cnaeses (not inclusive)
	 * @return the range of requisito cnaeses
	 */
	public static java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES>
			getRequisitoCNAESs(int start, int end) {

		return getService().getRequisitoCNAESs(start, end);
	}

	/**
	 * Returns the number of requisito cnaeses.
	 *
	 * @return the number of requisito cnaeses
	 */
	public static int getRequisitoCNAESsCount() {
		return getService().getRequisitoCNAESsCount();
	}

	/**
	 * Updates the requisito cnaes in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RequisitoCNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param requisitoCNAES the requisito cnaes
	 * @return the requisito cnaes that was updated
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
			updateRequisitoCNAES(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					RequisitoCNAES requisitoCNAES) {

		return getService().updateRequisitoCNAES(requisitoCNAES);
	}

	public static RequisitoCNAESLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<RequisitoCNAESLocalService, RequisitoCNAESLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			RequisitoCNAESLocalService.class);

		ServiceTracker<RequisitoCNAESLocalService, RequisitoCNAESLocalService>
			serviceTracker =
				new ServiceTracker
					<RequisitoCNAESLocalService, RequisitoCNAESLocalService>(
						bundle.getBundleContext(),
						RequisitoCNAESLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}