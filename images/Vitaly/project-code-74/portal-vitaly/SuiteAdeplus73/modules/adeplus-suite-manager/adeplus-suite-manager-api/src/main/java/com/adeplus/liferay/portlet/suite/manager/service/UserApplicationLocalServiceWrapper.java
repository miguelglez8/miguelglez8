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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserApplicationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserApplicationLocalService
 * @generated
 */
public class UserApplicationLocalServiceWrapper
	implements ServiceWrapper<UserApplicationLocalService>,
			   UserApplicationLocalService {

	public UserApplicationLocalServiceWrapper(
		UserApplicationLocalService userApplicationLocalService) {

		_userApplicationLocalService = userApplicationLocalService;
	}

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
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserApplication
		addUserApplication(
			com.adeplus.liferay.portlet.suite.manager.model.UserApplication
				userApplication) {

		return _userApplicationLocalService.addUserApplication(userApplication);
	}

	/**
	 * Creates a new user application with the primary key. Does not add the user application to the database.
	 *
	 * @param userApplicationPK the primary key for the new user application
	 * @return the new user application
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserApplication
		createUserApplication(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				UserApplicationPK userApplicationPK) {

		return _userApplicationLocalService.createUserApplication(
			userApplicationPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userApplicationLocalService.deletePersistedModel(
			persistedModel);
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
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserApplication
		deleteUserApplication(
			com.adeplus.liferay.portlet.suite.manager.model.UserApplication
				userApplication) {

		return _userApplicationLocalService.deleteUserApplication(
			userApplication);
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
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserApplication
			deleteUserApplication(
				com.adeplus.liferay.portlet.suite.manager.service.persistence.
					UserApplicationPK userApplicationPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userApplicationLocalService.deleteUserApplication(
			userApplicationPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userApplicationLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _userApplicationLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _userApplicationLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _userApplicationLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _userApplicationLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _userApplicationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserApplication
		fetchUserApplication(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				UserApplicationPK userApplicationPK) {

		return _userApplicationLocalService.fetchUserApplication(
			userApplicationPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _userApplicationLocalService.getActionableDynamicQuery();
	}

	@Override
	public int getAdminsByAppComp(long appId, long compId) {
		return _userApplicationLocalService.getAdminsByAppComp(appId, compId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserApplication>
			getApplicationsFromUser(long userId, long compId) {

		return _userApplicationLocalService.getApplicationsFromUser(
			userId, compId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userApplicationLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userApplicationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userApplicationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the user application with the primary key.
	 *
	 * @param userApplicationPK the primary key of the user application
	 * @return the user application
	 * @throws PortalException if a user application with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserApplication
			getUserApplication(
				com.adeplus.liferay.portlet.suite.manager.service.persistence.
					UserApplicationPK userApplicationPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userApplicationLocalService.getUserApplication(
			userApplicationPK);
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
	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserApplication>
			getUserApplications(int start, int end) {

		return _userApplicationLocalService.getUserApplications(start, end);
	}

	/**
	 * Returns the number of user applications.
	 *
	 * @return the number of user applications
	 */
	@Override
	public int getUserApplicationsCount() {
		return _userApplicationLocalService.getUserApplicationsCount();
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserApplication
		getUserCompanyApplication(
			long userId, long compId, long applicationId) {

		return _userApplicationLocalService.getUserCompanyApplication(
			userId, compId, applicationId);
	}

	@Override
	public int getUsersByAppComp(long appId, long compId) {
		return _userApplicationLocalService.getUsersByAppComp(appId, compId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserApplication>
			getUsersFromApplication(long compId, long applicationId) {

		return _userApplicationLocalService.getUsersFromApplication(
			compId, applicationId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserApplication>
			getUsersFromApplicationAndCompany(long applicationId, long compId) {

		return _userApplicationLocalService.getUsersFromApplicationAndCompany(
			applicationId, compId);
	}

	@Override
	public boolean hasUserApplication(
		long userId, long compId, long applicationId) {

		return _userApplicationLocalService.hasUserApplication(
			userId, compId, applicationId);
	}

	@Override
	public boolean hasUserApplication31DaysAfterDeleted(
		long userId, long compId, long applicationId) {

		return _userApplicationLocalService.
			hasUserApplication31DaysAfterDeleted(userId, compId, applicationId);
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
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserApplication
		updateUserApplication(
			com.adeplus.liferay.portlet.suite.manager.model.UserApplication
				userApplication) {

		return _userApplicationLocalService.updateUserApplication(
			userApplication);
	}

	@Override
	public UserApplicationLocalService getWrappedService() {
		return _userApplicationLocalService;
	}

	@Override
	public void setWrappedService(
		UserApplicationLocalService userApplicationLocalService) {

		_userApplicationLocalService = userApplicationLocalService;
	}

	private UserApplicationLocalService _userApplicationLocalService;

}