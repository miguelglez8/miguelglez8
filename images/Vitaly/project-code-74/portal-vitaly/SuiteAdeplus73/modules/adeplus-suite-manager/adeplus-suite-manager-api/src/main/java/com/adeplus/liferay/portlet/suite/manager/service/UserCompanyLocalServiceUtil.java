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

import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for UserCompany. This utility wraps
 * <code>com.adeplus.liferay.portlet.suite.manager.service.impl.UserCompanyLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserCompanyLocalService
 * @generated
 */
public class UserCompanyLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.adeplus.liferay.portlet.suite.manager.service.impl.UserCompanyLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static UserCompany addUserCompany(UserCompany userCompany) {
		return getService().addUserCompany(userCompany);
	}

	/**
	 * Creates a new user company with the primary key. Does not add the user company to the database.
	 *
	 * @param userCompanyPK the primary key for the new user company
	 * @return the new user company
	 */
	public static UserCompany createUserCompany(
		com.adeplus.liferay.portlet.suite.manager.service.persistence.
			UserCompanyPK userCompanyPK) {

		return getService().createUserCompany(userCompanyPK);
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
	 * Deletes the user company from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserCompanyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userCompany the user company
	 * @return the user company that was removed
	 */
	public static UserCompany deleteUserCompany(UserCompany userCompany) {
		return getService().deleteUserCompany(userCompany);
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
	public static UserCompany deleteUserCompany(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				UserCompanyPK userCompanyPK)
		throws PortalException {

		return getService().deleteUserCompany(userCompanyPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserCompanyModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserCompanyModelImpl</code>.
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

	public static UserCompany fetchUserCompany(
		com.adeplus.liferay.portlet.suite.manager.service.persistence.
			UserCompanyPK userCompanyPK) {

		return getService().fetchUserCompany(userCompanyPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<UserCompany> getActiveUserCompaniesFromUser(
		long userId) {

		return getService().getActiveUserCompaniesFromUser(userId);
	}

	public static List<UserCompany> getAdminUsersFromCompany(long compId) {
		return getService().getAdminUsersFromCompany(compId);
	}

	public static List<UserCompany> getCompaniesAdminFromUser(long userId) {
		return getService().getCompaniesAdminFromUser(userId);
	}

	public static List<UserCompany> getCompaniesFromUser(long userId) {
		return getService().getCompaniesFromUser(userId);
	}

	public static long getCountAdminCompany(long compId) {
		return getService().getCountAdminCompany(compId);
	}

	public static long getCountUserCompany(long compId) {
		return getService().getCountUserCompany(compId);
	}

	public static UserCompany getFirstActiveUserCompanyFromUser(long userId) {
		return getService().getFirstActiveUserCompanyFromUser(userId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static String getLogoSrcFromUserCompany(long userId, long compId) {
		return getService().getLogoSrcFromUserCompany(userId, compId);
	}

	public static List<UserCompany> getOnlyUsersFromCompany(long compId) {
		return getService().getOnlyUsersFromCompany(compId);
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

	public static UserCompany getSingleUserCompanyFromUser(long userId) {
		return getService().getSingleUserCompanyFromUser(userId);
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
	public static List<UserCompany> getUserCompanies(int start, int end) {
		return getService().getUserCompanies(start, end);
	}

	/**
	 * Returns the number of user companies.
	 *
	 * @return the number of user companies
	 */
	public static int getUserCompaniesCount() {
		return getService().getUserCompaniesCount();
	}

	public static UserCompany getUserCompany(long userId, long compId) {
		return getService().getUserCompany(userId, compId);
	}

	/**
	 * Returns the user company with the primary key.
	 *
	 * @param userCompanyPK the primary key of the user company
	 * @return the user company
	 * @throws PortalException if a user company with the primary key could not be found
	 */
	public static UserCompany getUserCompany(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				UserCompanyPK userCompanyPK)
		throws PortalException {

		return getService().getUserCompany(userCompanyPK);
	}

	public static UserCompany getUserDefaultCompany(long userId) {
		return getService().getUserDefaultCompany(userId);
	}

	public static List<UserCompany> getUsersByEmail(String email) {
		return getService().getUsersByEmail(email);
	}

	public static List<UserCompany> getUsersByEmailCompId(
		String email, long compId) {

		return getService().getUsersByEmailCompId(email, compId);
	}

	public static List<UserCompany> getUsersByNif(String nif) {
		return getService().getUsersByNif(nif);
	}

	public static List<UserCompany> getUsersByNifCompId(
		String nif, long compId) {

		return getService().getUsersByNifCompId(nif, compId);
	}

	public static List<UserCompany> getUsersByNifEmail(
		String nif, String email) {

		return getService().getUsersByNifEmail(nif, email);
	}

	public static List<UserCompany> getUsersByNifEmailCompId(
		String nif, String email, long compId) {

		return getService().getUsersByNifEmailCompId(nif, email, compId);
	}

	public static long getUsersCountFromCompany(long compId) {
		return getService().getUsersCountFromCompany(compId);
	}

	public static List<UserCompany> getUsersFromCompany(long compId) {
		return getService().getUsersFromCompany(compId);
	}

	public static List<UserCompany> getUsersToInactive(java.util.Date endDate) {
		return getService().getUsersToInactive(endDate);
	}

	public static boolean hasUserMultiCompany(long userId) {
		return getService().hasUserMultiCompany(userId);
	}

	public static boolean isUserAdminInActiveComp(long userId) {
		return getService().isUserAdminInActiveComp(userId);
	}

	public static boolean isUserAdminInComp(long userId, long compId) {
		return getService().isUserAdminInComp(userId, compId);
	}

	public static void setUserDefaultCompany(long userId, long compId) {
		getService().setUserDefaultCompany(userId, compId);
	}

	public static void setUserDefaultCompany(
		long userId, long compId, long clientId, long contractId,
		long empresaId) {

		getService().setUserDefaultCompany(
			userId, compId, clientId, contractId, empresaId);
	}

	public static UserCompany updateUserCompany(
		long userId, long compId, String name, String lastName, String nif,
		String email, String phone, boolean admin, java.util.Date dateEnd) {

		return getService().updateUserCompany(
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
	public static UserCompany updateUserCompany(UserCompany userCompany) {
		return getService().updateUserCompany(userCompany);
	}

	public static UserCompanyLocalService getService() {
		return _service;
	}

	private static volatile UserCompanyLocalService _service;

}