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
 * Provides a wrapper for {@link UserRoleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserRoleLocalService
 * @generated
 */
public class UserRoleLocalServiceWrapper
	implements ServiceWrapper<UserRoleLocalService>, UserRoleLocalService {

	public UserRoleLocalServiceWrapper(
		UserRoleLocalService userRoleLocalService) {

		_userRoleLocalService = userRoleLocalService;
	}

	/**
	 * Adds the user role to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userRole the user role
	 * @return the user role that was added
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserRole addUserRole(
		com.adeplus.liferay.portlet.suite.manager.model.UserRole userRole) {

		return _userRoleLocalService.addUserRole(userRole);
	}

	/**
	 * Creates a new user role with the primary key. Does not add the user role to the database.
	 *
	 * @param userRolePK the primary key for the new user role
	 * @return the new user role
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserRole
		createUserRole(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				UserRolePK userRolePK) {

		return _userRoleLocalService.createUserRole(userRolePK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userRoleLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the user role from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userRole the user role
	 * @return the user role that was removed
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserRole
		deleteUserRole(
			com.adeplus.liferay.portlet.suite.manager.model.UserRole userRole) {

		return _userRoleLocalService.deleteUserRole(userRole);
	}

	/**
	 * Deletes the user role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userRolePK the primary key of the user role
	 * @return the user role that was removed
	 * @throws PortalException if a user role with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserRole
			deleteUserRole(
				com.adeplus.liferay.portlet.suite.manager.service.persistence.
					UserRolePK userRolePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userRoleLocalService.deleteUserRole(userRolePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userRoleLocalService.dynamicQuery();
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

		return _userRoleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserRoleModelImpl</code>.
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

		return _userRoleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserRoleModelImpl</code>.
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

		return _userRoleLocalService.dynamicQuery(
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

		return _userRoleLocalService.dynamicQueryCount(dynamicQuery);
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

		return _userRoleLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserRole
		fetchUserRole(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				UserRolePK userRolePK) {

		return _userRoleLocalService.fetchUserRole(userRolePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _userRoleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userRoleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userRoleLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userRoleLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserRole getUserRole(
		long userId, long compId, long roleId) {

		return _userRoleLocalService.getUserRole(userId, compId, roleId);
	}

	/**
	 * Returns the user role with the primary key.
	 *
	 * @param userRolePK the primary key of the user role
	 * @return the user role
	 * @throws PortalException if a user role with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserRole getUserRole(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				UserRolePK userRolePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userRoleLocalService.getUserRole(userRolePK);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserRole>
			getUserRoleByUserId(long userId) {

		return _userRoleLocalService.getUserRoleByUserId(userId);
	}

	/**
	 * Returns a range of all the user roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user roles
	 * @param end the upper bound of the range of user roles (not inclusive)
	 * @return the range of user roles
	 */
	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserRole> getUserRoles(
			int start, int end) {

		return _userRoleLocalService.getUserRoles(start, end);
	}

	/**
	 * Returns the number of user roles.
	 *
	 * @return the number of user roles
	 */
	@Override
	public int getUserRolesCount() {
		return _userRoleLocalService.getUserRolesCount();
	}

	/**
	 * Updates the user role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userRole the user role
	 * @return the user role that was updated
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserRole
		updateUserRole(
			com.adeplus.liferay.portlet.suite.manager.model.UserRole userRole) {

		return _userRoleLocalService.updateUserRole(userRole);
	}

	@Override
	public UserRoleLocalService getWrappedService() {
		return _userRoleLocalService;
	}

	@Override
	public void setWrappedService(UserRoleLocalService userRoleLocalService) {
		_userRoleLocalService = userRoleLocalService;
	}

	private UserRoleLocalService _userRoleLocalService;

}