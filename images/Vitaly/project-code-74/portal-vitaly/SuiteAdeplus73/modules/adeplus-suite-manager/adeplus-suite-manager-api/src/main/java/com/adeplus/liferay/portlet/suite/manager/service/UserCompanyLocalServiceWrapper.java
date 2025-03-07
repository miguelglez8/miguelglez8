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
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompany
		addUserCompany(
			com.adeplus.liferay.portlet.suite.manager.model.UserCompany
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
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompany
		createUserCompany(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
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
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompany
		deleteUserCompany(
			com.adeplus.liferay.portlet.suite.manager.model.UserCompany
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
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompany
			deleteUserCompany(
				com.adeplus.liferay.portlet.suite.manager.service.persistence.
					UserCompanyPK userCompanyPK)
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserCompanyModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserCompanyModelImpl</code>.
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
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompany
		fetchUserCompany(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
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
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompany>
			getActiveUserCompaniesFromUser(long userId) {

		return _userCompanyLocalService.getActiveUserCompaniesFromUser(userId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompany>
			getAdminUsersFromCompany(long compId) {

		return _userCompanyLocalService.getAdminUsersFromCompany(compId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompany>
			getCompaniesAdminFromUser(long userId) {

		return _userCompanyLocalService.getCompaniesAdminFromUser(userId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompany>
			getCompaniesFromUser(long userId) {

		return _userCompanyLocalService.getCompaniesFromUser(userId);
	}

	@Override
	public long getCountAdminCompany(long compId) {
		return _userCompanyLocalService.getCountAdminCompany(compId);
	}

	@Override
	public long getCountUserCompany(long compId) {
		return _userCompanyLocalService.getCountUserCompany(compId);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompany
		getFirstActiveUserCompanyFromUser(long userId) {

		return _userCompanyLocalService.getFirstActiveUserCompanyFromUser(
			userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userCompanyLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public String getLogoSrcFromUserCompany(long userId, long compId) {
		return _userCompanyLocalService.getLogoSrcFromUserCompany(
			userId, compId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompany>
			getOnlyUsersFromCompany(long compId) {

		return _userCompanyLocalService.getOnlyUsersFromCompany(compId);
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

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompany
		getSingleUserCompanyFromUser(long userId) {

		return _userCompanyLocalService.getSingleUserCompanyFromUser(userId);
	}

	/**
	 * Returns a range of all the user companies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserCompanyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user companies
	 * @param end the upper bound of the range of user companies (not inclusive)
	 * @return the range of user companies
	 */
	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompany>
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

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompany
		getUserCompany(long userId, long compId) {

		return _userCompanyLocalService.getUserCompany(userId, compId);
	}

	/**
	 * Returns the user company with the primary key.
	 *
	 * @param userCompanyPK the primary key of the user company
	 * @return the user company
	 * @throws PortalException if a user company with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompany
			getUserCompany(
				com.adeplus.liferay.portlet.suite.manager.service.persistence.
					UserCompanyPK userCompanyPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userCompanyLocalService.getUserCompany(userCompanyPK);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompany
		getUserDefaultCompany(long userId) {

		return _userCompanyLocalService.getUserDefaultCompany(userId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompany>
			getUsersByEmail(String email) {

		return _userCompanyLocalService.getUsersByEmail(email);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompany>
			getUsersByEmailCompId(String email, long compId) {

		return _userCompanyLocalService.getUsersByEmailCompId(email, compId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompany>
			getUsersByNif(String nif) {

		return _userCompanyLocalService.getUsersByNif(nif);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompany>
			getUsersByNifCompId(String nif, long compId) {

		return _userCompanyLocalService.getUsersByNifCompId(nif, compId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompany>
			getUsersByNifEmail(String nif, String email) {

		return _userCompanyLocalService.getUsersByNifEmail(nif, email);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompany>
			getUsersByNifEmailCompId(String nif, String email, long compId) {

		return _userCompanyLocalService.getUsersByNifEmailCompId(
			nif, email, compId);
	}

	@Override
	public long getUsersCountFromCompany(long compId) {
		return _userCompanyLocalService.getUsersCountFromCompany(compId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompany>
			getUsersFromCompany(long compId) {

		return _userCompanyLocalService.getUsersFromCompany(compId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompany>
			getUsersToInactive(java.util.Date endDate) {

		return _userCompanyLocalService.getUsersToInactive(endDate);
	}

	@Override
	public boolean hasUserMultiCompany(long userId) {
		return _userCompanyLocalService.hasUserMultiCompany(userId);
	}

	@Override
	public boolean isUserAdminInActiveComp(long userId) {
		return _userCompanyLocalService.isUserAdminInActiveComp(userId);
	}

	@Override
	public boolean isUserAdminInComp(long userId, long compId) {
		return _userCompanyLocalService.isUserAdminInComp(userId, compId);
	}

	@Override
	public void setUserDefaultCompany(long userId, long compId) {
		_userCompanyLocalService.setUserDefaultCompany(userId, compId);
	}

	@Override
	public void setUserDefaultCompany(
		long userId, long compId, long clientId, long contractId,
		long empresaId) {

		_userCompanyLocalService.setUserDefaultCompany(
			userId, compId, clientId, contractId, empresaId);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompany
		updateUserCompany(
			long userId, long compId, String name, String lastName, String nif,
			String email, String phone, boolean admin, java.util.Date dateEnd) {

		return _userCompanyLocalService.updateUserCompany(
			userId, compId, name, lastName, nif, email, phone, admin, dateEnd);
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
	public com.adeplus.liferay.portlet.suite.manager.model.UserCompany
		updateUserCompany(
			com.adeplus.liferay.portlet.suite.manager.model.UserCompany
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