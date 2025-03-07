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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for UserCompany. This utility wraps
 * <code>com.canal.etico.liferay.portlet.canal.manager.service.impl.UserCompanyLocalServiceImpl</code> and
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
	 * Never modify this class directly. Add custom service methods to <code>com.canal.etico.liferay.portlet.canal.manager.service.impl.UserCompanyLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
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
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
			addUserCompany(
				com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
					userCompany) {

		return getService().addUserCompany(userCompany);
	}

	/**
	 * Creates a new user company with the primary key. Does not add the user company to the database.
	 *
	 * @param userCompanyPK the primary key for the new user company
	 * @return the new user company
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
			createUserCompany(
				com.canal.etico.liferay.portlet.canal.manager.service.
					persistence.UserCompanyPK userCompanyPK) {

		return getService().createUserCompany(userCompanyPK);
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
	 * Deletes the user company from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserCompanyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userCompany the user company
	 * @return the user company that was removed
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
			deleteUserCompany(
				com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
					userCompany) {

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
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
				deleteUserCompany(
					com.canal.etico.liferay.portlet.canal.manager.service.
						persistence.UserCompanyPK userCompanyPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteUserCompany(userCompanyPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.UserCompanyModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.UserCompanyModelImpl</code>.
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
		com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
			fetchUserCompany(
				com.canal.etico.liferay.portlet.canal.manager.service.
					persistence.UserCompanyPK userCompanyPK) {

		return getService().fetchUserCompany(userCompanyPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.UserCompany>
			getCompaniesFromUser(long userId) {

		return getService().getCompaniesFromUser(userId);
	}

	public static
		com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
			getCompanyFromUserCompany(long userId, long compId) {

		return getService().getCompanyFromUserCompany(userId, compId);
	}

	public static long getCompanyIdFromUser(long userId) {
		return getService().getCompanyIdFromUser(userId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static String getLogoSrcFromUserCompany(long userId) {
		return getService().getLogoSrcFromUserCompany(userId);
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
	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.UserCompany>
			getUserCompanies(int start, int end) {

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

	/**
	 * Returns the user company with the primary key.
	 *
	 * @param userCompanyPK the primary key of the user company
	 * @return the user company
	 * @throws PortalException if a user company with the primary key could not be found
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
				getUserCompany(
					com.canal.etico.liferay.portlet.canal.manager.service.
						persistence.UserCompanyPK userCompanyPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getUserCompany(userCompanyPK);
	}

	public static
		com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
			getUserCompanyFromUser(long userId) {

		return getService().getUserCompanyFromUser(userId);
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.UserCompany>
			getUsersFromCompany(long compId) {

		return getService().getUsersFromCompany(compId);
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
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
			updateUserCompany(
				com.canal.etico.liferay.portlet.canal.manager.model.UserCompany
					userCompany) {

		return getService().updateUserCompany(userCompany);
	}

	public static UserCompanyLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<UserCompanyLocalService, UserCompanyLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(UserCompanyLocalService.class);

		ServiceTracker<UserCompanyLocalService, UserCompanyLocalService>
			serviceTracker =
				new ServiceTracker
					<UserCompanyLocalService, UserCompanyLocalService>(
						bundle.getBundleContext(),
						UserCompanyLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}