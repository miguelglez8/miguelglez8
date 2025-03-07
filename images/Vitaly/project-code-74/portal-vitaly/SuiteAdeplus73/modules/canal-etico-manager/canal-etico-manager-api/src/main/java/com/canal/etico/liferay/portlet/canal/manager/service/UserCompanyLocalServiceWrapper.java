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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserCompanyLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserCompanyLocalService
 * @generated
 */
public class UserCompanyLocalServiceWrapper
	implements ServiceWrapper<UserCompanyLocalService>,
			   UserCompanyLocalService {

	public UserCompanyLocalServiceWrapper(
		UserCompanyLocalService userCompanyLocalService) {

		_userCompanyLocalService = userCompanyLocalService;
	}

	/**
	 * Adds the user company to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserCompanyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userCompany the user company
	 * @return the user company that was added
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
		addUserCompany(
			com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
				userCompany) {

		return _userCompanyLocalService.addUserCompany(userCompany);
	}

	/**
	 * Creates a new user company with the primary key. Does not add the user company to the database.
	 *
	 * @param userCompanyPK the primary key for the new user company
	 * @return the new user company
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
		createUserCompany(
			com.canal.etico.liferay.portlet.canal.manager.service.persistence.
				UserCompanyPK userCompanyPK) {

		return _userCompanyLocalService.createUserCompany(userCompanyPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userCompanyLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the user company from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserCompanyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userCompany the user company
	 * @return the user company that was removed
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
		deleteUserCompany(
			com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
				userCompany) {

		return _userCompanyLocalService.deleteUserCompany(userCompany);
	}

	/**
	 * Deletes the user company with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserCompanyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userCompanyPK the primary key of the user company
	 * @return the user company that was removed
	 * @throws PortalException if a user company with the primary key could not be found
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
			deleteUserCompany(
				com.canal.etico.liferay.portlet.canal.manager.service.
					persistence.UserCompanyPK userCompanyPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userCompanyLocalService.deleteUserCompany(userCompanyPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userCompanyLocalService.dynamicQuery();
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

		return _userCompanyLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.UserCompanyModelImpl</code>.
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

		return _userCompanyLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.UserCompanyModelImpl</code>.
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

		return _userCompanyLocalService.dynamicQuery(
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

		return _userCompanyLocalService.dynamicQueryCount(dynamicQuery);
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

		return _userCompanyLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
		fetchUserCompany(
			com.canal.etico.liferay.portlet.canal.manager.service.persistence.
				UserCompanyPK userCompanyPK) {

		return _userCompanyLocalService.fetchUserCompany(userCompanyPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _userCompanyLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.UserCompany>
			getCompaniesFromUser(long userId) {

		return _userCompanyLocalService.getCompaniesFromUser(userId);
	}

	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
		getCompanyFromUserCompany(long userId, long compId) {

		return _userCompanyLocalService.getCompanyFromUserCompany(
			userId, compId);
	}

	@Override
	public long getCompanyIdFromUser(long userId) {
		return _userCompanyLocalService.getCompanyIdFromUser(userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userCompanyLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public String getLogoSrcFromUserCompany(long userId) {
		return _userCompanyLocalService.getLogoSrcFromUserCompany(userId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userCompanyLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userCompanyLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the user companies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.UserCompanyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user companies
	 * @param end the upper bound of the range of user companies (not inclusive)
	 * @return the range of user companies
	 */
	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.UserCompany>
			getUserCompanies(int start, int end) {

		return _userCompanyLocalService.getUserCompanies(start, end);
	}

	/**
	 * Returns the number of user companies.
	 *
	 * @return the number of user companies
	 */
	@Override
	public int getUserCompaniesCount() {
		return _userCompanyLocalService.getUserCompaniesCount();
	}

	/**
	 * Returns the user company with the primary key.
	 *
	 * @param userCompanyPK the primary key of the user company
	 * @return the user company
	 * @throws PortalException if a user company with the primary key could not be found
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
			getUserCompany(
				com.canal.etico.liferay.portlet.canal.manager.service.
					persistence.UserCompanyPK userCompanyPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userCompanyLocalService.getUserCompany(userCompanyPK);
	}

	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
		getUserCompanyFromUser(long userId) {

		return _userCompanyLocalService.getUserCompanyFromUser(userId);
	}

	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.UserCompany>
			getUsersFromCompany(long compId) {

		return _userCompanyLocalService.getUsersFromCompany(compId);
	}

	/**
	 * Updates the user company in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserCompanyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userCompany the user company
	 * @return the user company that was updated
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
		updateUserCompany(
			com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
				userCompany) {

		return _userCompanyLocalService.updateUserCompany(userCompany);
	}

	@Override
	public UserCompanyLocalService getWrappedService() {
		return _userCompanyLocalService;
	}

	@Override
	public void setWrappedService(
		UserCompanyLocalService userCompanyLocalService) {

		_userCompanyLocalService = userCompanyLocalService;
	}

	private UserCompanyLocalService _userCompanyLocalService;

}