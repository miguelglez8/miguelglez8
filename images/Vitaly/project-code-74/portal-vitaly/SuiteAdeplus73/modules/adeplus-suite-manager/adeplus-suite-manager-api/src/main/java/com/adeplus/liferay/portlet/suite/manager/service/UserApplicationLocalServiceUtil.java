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

import com.adeplus.liferay.portlet.suite.manager.model.UserApplication;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for UserApplication. This utility wraps
 * <code>com.adeplus.liferay.portlet.suite.manager.service.impl.UserApplicationLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserApplicationLocalService
 * @generated
 */
public class UserApplicationLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.adeplus.liferay.portlet.suite.manager.service.impl.UserApplicationLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the user application to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userApplication the user application
	 * @return the user application that was added
	 */
	public static UserApplication addUserApplication(
		UserApplication userApplication) {

		return getService().addUserApplication(userApplication);
	}

	/**
	 * Creates a new user application with the primary key. Does not add the user application to the database.
	 *
	 * @param userApplicationPK the primary key for the new user application
	 * @return the new user application
	 */
	public static UserApplication createUserApplication(
		com.adeplus.liferay.portlet.suite.manager.service.persistence.
			UserApplicationPK userApplicationPK) {

		return getService().createUserApplication(userApplicationPK);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the user application from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userApplication the user application
	 * @return the user application that was removed
	 */
	public static UserApplication deleteUserApplication(
		UserApplication userApplication) {

		return getService().deleteUserApplication(userApplication);
	}

	/**
	 * Deletes the user application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userApplicationPK the primary key of the user application
	 * @return the user application that was removed
	 * @throws PortalException if a user application with the primary key could not be found
	 */
	public static UserApplication deleteUserApplication(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				UserApplicationPK userApplicationPK)
		throws PortalException {

		return getService().deleteUserApplication(userApplicationPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserApplicationModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserApplicationModelImpl</code>.
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

	public static UserApplication fetchUserApplication(
		com.adeplus.liferay.portlet.suite.manager.service.persistence.
			UserApplicationPK userApplicationPK) {

		return getService().fetchUserApplication(userApplicationPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static int getAdminsByAppComp(long appId, long compId) {
		return getService().getAdminsByAppComp(appId, compId);
	}

	public static List<UserApplication> getApplicationsFromUser(
		long userId, long compId) {

		return getService().getApplicationsFromUser(userId, compId);
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

	/**
	 * Returns the user application with the primary key.
	 *
	 * @param userApplicationPK the primary key of the user application
	 * @return the user application
	 * @throws PortalException if a user application with the primary key could not be found
	 */
	public static UserApplication getUserApplication(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				UserApplicationPK userApplicationPK)
		throws PortalException {

		return getService().getUserApplication(userApplicationPK);
	}

	/**
	 * Returns a range of all the user applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user applications
	 * @param end the upper bound of the range of user applications (not inclusive)
	 * @return the range of user applications
	 */
	public static List<UserApplication> getUserApplications(
		int start, int end) {

		return getService().getUserApplications(start, end);
	}

	/**
	 * Returns the number of user applications.
	 *
	 * @return the number of user applications
	 */
	public static int getUserApplicationsCount() {
		return getService().getUserApplicationsCount();
	}

	public static UserApplication getUserCompanyApplication(
		long userId, long compId, long applicationId) {

		return getService().getUserCompanyApplication(
			userId, compId, applicationId);
	}

	public static int getUsersByAppComp(long appId, long compId) {
		return getService().getUsersByAppComp(appId, compId);
	}

	public static List<UserApplication> getUsersFromApplication(
		long compId, long applicationId) {

		return getService().getUsersFromApplication(compId, applicationId);
	}

	public static List<UserApplication> getUsersFromApplicationAndCompany(
		long applicationId, long compId) {

		return getService().getUsersFromApplicationAndCompany(
			applicationId, compId);
	}

	public static boolean hasUserApplication(
		long userId, long compId, long applicationId) {

		return getService().hasUserApplication(userId, compId, applicationId);
	}

	public static boolean hasUserApplication31DaysAfterDeleted(
		long userId, long compId, long applicationId) {

		return getService().hasUserApplication31DaysAfterDeleted(
			userId, compId, applicationId);
	}

	/**
	 * Updates the user application in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userApplication the user application
	 * @return the user application that was updated
	 */
	public static UserApplication updateUserApplication(
		UserApplication userApplication) {

		return getService().updateUserApplication(userApplication);
	}

	public static UserApplicationLocalService getService() {
		return _service;
	}

	private static volatile UserApplicationLocalService _service;

}