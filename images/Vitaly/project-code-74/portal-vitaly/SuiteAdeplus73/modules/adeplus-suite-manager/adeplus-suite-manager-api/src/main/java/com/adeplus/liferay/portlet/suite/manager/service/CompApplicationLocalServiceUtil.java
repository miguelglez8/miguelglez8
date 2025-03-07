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

package com.adeplus.liferay.portlet.suite.manager.service;

import com.adeplus.liferay.portlet.suite.manager.model.CompApplication;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for CompApplication. This utility wraps
 * <code>com.adeplus.liferay.portlet.suite.manager.service.impl.CompApplicationLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CompApplicationLocalService
 * @generated
 */
public class CompApplicationLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.adeplus.liferay.portlet.suite.manager.service.impl.CompApplicationLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the comp application to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param compApplication the comp application
	 * @return the comp application that was added
	 */
	public static CompApplication addCompApplication(
		CompApplication compApplication) {

		return getService().addCompApplication(compApplication);
	}

	/**
	 * Creates a new comp application with the primary key. Does not add the comp application to the database.
	 *
	 * @param compApplicationPK the primary key for the new comp application
	 * @return the new comp application
	 */
	public static CompApplication createCompApplication(
		com.adeplus.liferay.portlet.suite.manager.service.persistence.
			CompApplicationPK compApplicationPK) {

		return getService().createCompApplication(compApplicationPK);
	}

	/**
	 * Deletes the comp application from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param compApplication the comp application
	 * @return the comp application that was removed
	 */
	public static CompApplication deleteCompApplication(
		CompApplication compApplication) {

		return getService().deleteCompApplication(compApplication);
	}

	/**
	 * Deletes the comp application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param compApplicationPK the primary key of the comp application
	 * @return the comp application that was removed
	 * @throws PortalException if a comp application with the primary key could not be found
	 */
	public static CompApplication deleteCompApplication(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				CompApplicationPK compApplicationPK)
		throws PortalException {

		return getService().deleteCompApplication(compApplicationPK);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.CompApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.CompApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
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
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static CompApplication fetchCompApplication(
		com.adeplus.liferay.portlet.suite.manager.service.persistence.
			CompApplicationPK compApplicationPK) {

		return getService().fetchCompApplication(compApplicationPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<CompApplication> getApplicationsFromCompany(
		long compId) {

		return getService().getApplicationsFromCompany(compId);
	}

	public static List<CompApplication> getCompaniesWithApplication(
		long applicationId) {

		return getService().getCompaniesWithApplication(applicationId);
	}

	public static CompApplication getCompanyApplication(
		long companyId, long applicationId) {

		return getService().getCompanyApplication(companyId, applicationId);
	}

	public static CompApplication getCompanyApplication(
		long companyId, long applicationId, boolean isMulti) {

		return getService().getCompanyApplication(
			companyId, applicationId, isMulti);
	}

	/**
	 * Returns the comp application with the primary key.
	 *
	 * @param compApplicationPK the primary key of the comp application
	 * @return the comp application
	 * @throws PortalException if a comp application with the primary key could not be found
	 */
	public static CompApplication getCompApplication(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				CompApplicationPK compApplicationPK)
		throws PortalException {

		return getService().getCompApplication(compApplicationPK);
	}

	/**
	 * Returns a range of all the comp applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.CompApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of comp applications
	 * @param end the upper bound of the range of comp applications (not inclusive)
	 * @return the range of comp applications
	 */
	public static List<CompApplication> getCompApplications(
		int start, int end) {

		return getService().getCompApplications(start, end);
	}

	/**
	 * Returns the number of comp applications.
	 *
	 * @return the number of comp applications
	 */
	public static int getCompApplicationsCount() {
		return getService().getCompApplicationsCount();
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
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static boolean hasCompanyApplication(
		long companyId, long applicationId) {

		return getService().hasCompanyApplication(companyId, applicationId);
	}

	/**
	 * Updates the comp application in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param compApplication the comp application
	 * @return the comp application that was updated
	 */
	public static CompApplication updateCompApplication(
		CompApplication compApplication) {

		return getService().updateCompApplication(compApplication);
	}

	public static CompApplicationLocalService getService() {
		return _service;
	}

	private static volatile CompApplicationLocalService _service;

}