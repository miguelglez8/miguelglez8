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
 * Provides a wrapper for {@link UserCompanyClientLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserCompanyClientLocalService
 * @generated
 */
public class UserCompanyClientLocalServiceWrapper
	implements ServiceWrapper<UserCompanyClientLocalService>,
			   UserCompanyClientLocalService {

	public UserCompanyClientLocalServiceWrapper(
		UserCompanyClientLocalService userCompanyClientLocalService) {

		_userCompanyClientLocalService = userCompanyClientLocalService;
	}

	/**
	 * Adds the user company client to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserCompanyClientLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userCompanyClient the user company client
	 * @return the user company client that was added
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompanyClient
		addUserCompanyClient(
			com.adeplus.liferay.portlet.suite.manager.model.UserCompanyClient
				userCompanyClient) {

		return _userCompanyClientLocalService.addUserCompanyClient(
			userCompanyClient);
	}

	/**
	 * Creates a new user company client with the primary key. Does not add the user company client to the database.
	 *
	 * @param userCompanyClientPK the primary key for the new user company client
	 * @return the new user company client
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompanyClient
		createUserCompanyClient(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				UserCompanyClientPK userCompanyClientPK) {

		return _userCompanyClientLocalService.createUserCompanyClient(
			userCompanyClientPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userCompanyClientLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the user company client from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserCompanyClientLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userCompanyClient the user company client
	 * @return the user company client that was removed
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompanyClient
		deleteUserCompanyClient(
			com.adeplus.liferay.portlet.suite.manager.model.UserCompanyClient
				userCompanyClient) {

		return _userCompanyClientLocalService.deleteUserCompanyClient(
			userCompanyClient);
	}

	/**
	 * Deletes the user company client with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserCompanyClientLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userCompanyClientPK the primary key of the user company client
	 * @return the user company client that was removed
	 * @throws PortalException if a user company client with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompanyClient
			deleteUserCompanyClient(
				com.adeplus.liferay.portlet.suite.manager.service.persistence.
					UserCompanyClientPK userCompanyClientPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userCompanyClientLocalService.deleteUserCompanyClient(
			userCompanyClientPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userCompanyClientLocalService.dynamicQuery();
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

		return _userCompanyClientLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserCompanyClientModelImpl</code>.
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

		return _userCompanyClientLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserCompanyClientModelImpl</code>.
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

		return _userCompanyClientLocalService.dynamicQuery(
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

		return _userCompanyClientLocalService.dynamicQueryCount(dynamicQuery);
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

		return _userCompanyClientLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompanyClient
		fetchUserCompanyClient(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				UserCompanyClientPK userCompanyClientPK) {

		return _userCompanyClientLocalService.fetchUserCompanyClient(
			userCompanyClientPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _userCompanyClientLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userCompanyClientLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userCompanyClientLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userCompanyClientLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the user company client with the primary key.
	 *
	 * @param userCompanyClientPK the primary key of the user company client
	 * @return the user company client
	 * @throws PortalException if a user company client with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompanyClient
			getUserCompanyClient(
				com.adeplus.liferay.portlet.suite.manager.service.persistence.
					UserCompanyClientPK userCompanyClientPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userCompanyClientLocalService.getUserCompanyClient(
			userCompanyClientPK);
	}

	/**
	 * Returns a range of all the user company clients.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserCompanyClientModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user company clients
	 * @param end the upper bound of the range of user company clients (not inclusive)
	 * @return the range of user company clients
	 */
	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompanyClient>
			getUserCompanyClients(int start, int end) {

		return _userCompanyClientLocalService.getUserCompanyClients(start, end);
	}

	/**
	 * Returns the number of user company clients.
	 *
	 * @return the number of user company clients
	 */
	@Override
	public int getUserCompanyClientsCount() {
		return _userCompanyClientLocalService.getUserCompanyClientsCount();
	}

	/**
	 * Updates the user company client in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserCompanyClientLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userCompanyClient the user company client
	 * @return the user company client that was updated
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompanyClient
		updateUserCompanyClient(
			com.adeplus.liferay.portlet.suite.manager.model.UserCompanyClient
				userCompanyClient) {

		return _userCompanyClientLocalService.updateUserCompanyClient(
			userCompanyClient);
	}

	@Override
	public UserCompanyClientLocalService getWrappedService() {
		return _userCompanyClientLocalService;
	}

	@Override
	public void setWrappedService(
		UserCompanyClientLocalService userCompanyClientLocalService) {

		_userCompanyClientLocalService = userCompanyClientLocalService;
	}

	private UserCompanyClientLocalService _userCompanyClientLocalService;

}