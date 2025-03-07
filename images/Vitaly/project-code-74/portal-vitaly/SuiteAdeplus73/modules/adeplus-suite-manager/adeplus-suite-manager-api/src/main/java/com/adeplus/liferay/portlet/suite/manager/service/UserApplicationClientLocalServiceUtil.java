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

import com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for UserApplicationClient. This utility wraps
 * <code>com.adeplus.liferay.portlet.suite.manager.service.impl.UserApplicationClientLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserApplicationClientLocalService
 * @generated
 */
public class UserApplicationClientLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.adeplus.liferay.portlet.suite.manager.service.impl.UserApplicationClientLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the user application client to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserApplicationClientLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userApplicationClient the user application client
	 * @return the user application client that was added
	 */
	public static UserApplicationClient addUserApplicationClient(
		UserApplicationClient userApplicationClient) {

		return getService().addUserApplicationClient(userApplicationClient);
	}

	/**
	 * Creates a new user application client with the primary key. Does not add the user application client to the database.
	 *
	 * @param userApplicationClientPK the primary key for the new user application client
	 * @return the new user application client
	 */
	public static UserApplicationClient createUserApplicationClient(
		com.adeplus.liferay.portlet.suite.manager.service.persistence.
			UserApplicationClientPK userApplicationClientPK) {

		return getService().createUserApplicationClient(
			userApplicationClientPK);
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
	 * Deletes the user application client from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserApplicationClientLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userApplicationClient the user application client
	 * @return the user application client that was removed
	 */
	public static UserApplicationClient deleteUserApplicationClient(
		UserApplicationClient userApplicationClient) {

		return getService().deleteUserApplicationClient(userApplicationClient);
	}

	/**
	 * Deletes the user application client with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserApplicationClientLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userApplicationClientPK the primary key of the user application client
	 * @return the user application client that was removed
	 * @throws PortalException if a user application client with the primary key could not be found
	 */
	public static UserApplicationClient deleteUserApplicationClient(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				UserApplicationClientPK userApplicationClientPK)
		throws PortalException {

		return getService().deleteUserApplicationClient(
			userApplicationClientPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserApplicationClientModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserApplicationClientModelImpl</code>.
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

	public static boolean existUserByRole(
		long idUser, long licenseId, long idEmpresa) {

		return getService().existUserByRole(idUser, licenseId, idEmpresa);
	}

	public static UserApplicationClient fetchUserApplicationClient(
		com.adeplus.liferay.portlet.suite.manager.service.persistence.
			UserApplicationClientPK userApplicationClientPK) {

		return getService().fetchUserApplicationClient(userApplicationClientPK);
	}

	public static List<UserApplicationClient> findByCompClientContractId(
		long compId, long clientId, long contractId) {

		return getService().findByCompClientContractId(
			compId, clientId, contractId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static int getAdminsByAppComp(String appId, long compId) {
		return getService().getAdminsByAppComp(appId, compId);
	}

	public static int getAdminsCountByContracApp(
		Long clientId, Long contractId, Long idApplication, Long userId) {

		return getService().getAdminsCountByContracApp(
			clientId, contractId, idApplication, userId);
	}

	public static List<UserApplicationClient> getAllApplicationsFromUser(
		long userId, long compId) {

		return getService().getAllApplicationsFromUser(userId, compId);
	}

	public static List<UserApplicationClient> getAllUserApplicationsByCompany(
		long compid) {

		return getService().getAllUserApplicationsByCompany(compid);
	}

	public static List<UserApplicationClient> getAllUserApplicationsByEmpresa(
		long empresaId) {

		return getService().getAllUserApplicationsByEmpresa(empresaId);
	}

	public static List<UserApplicationClient> getApplicationsFromUser(
		long userId, long compId) {

		return getService().getApplicationsFromUser(userId, compId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static List<Long> getListApplicationsFromUser(
		long userId, long compId) {

		return getService().getListApplicationsFromUser(userId, compId);
	}

	public static List<UserApplicationClient>
		getListApplicationsFromUserCompanyClient(
			long userId, long compId, long clientId) {

		return getService().getListApplicationsFromUserCompanyClient(
			userId, compId, clientId);
	}

	public static int getOnlyAdminsFromCompanyEmpresaTotal(
		long clientId, long contractId) {

		return getService().getOnlyAdminsFromCompanyEmpresaTotal(
			clientId, contractId);
	}

	public static List<UserApplicationClient> getOnlyUsersFromCompanyEmpresa(
		long clientId, long contractId) {

		return getService().getOnlyUsersFromCompanyEmpresa(
			clientId, contractId);
	}

	public static int getOnlyUsersFromCompanyEmpresaTotal(
		long clientId, long contractId) {

		return getService().getOnlyUsersFromCompanyEmpresaTotal(
			clientId, contractId);
	}

	public static int getOnlyUsersFromCompanyEmpresaTotal(
		long clientId, long contractId, long appId) {

		return getService().getOnlyUsersFromCompanyEmpresaTotal(
			clientId, contractId, appId);
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
	 * Returns the user application client with the primary key.
	 *
	 * @param userApplicationClientPK the primary key of the user application client
	 * @return the user application client
	 * @throws PortalException if a user application client with the primary key could not be found
	 */
	public static UserApplicationClient getUserApplicationClient(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				UserApplicationClientPK userApplicationClientPK)
		throws PortalException {

		return getService().getUserApplicationClient(userApplicationClientPK);
	}

	/**
	 * Returns a range of all the user application clients.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserApplicationClientModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user application clients
	 * @param end the upper bound of the range of user application clients (not inclusive)
	 * @return the range of user application clients
	 */
	public static List<UserApplicationClient> getUserApplicationClients(
		int start, int end) {

		return getService().getUserApplicationClients(start, end);
	}

	/**
	 * Returns the number of user application clients.
	 *
	 * @return the number of user application clients
	 */
	public static int getUserApplicationClientsCount() {
		return getService().getUserApplicationClientsCount();
	}

	public static UserApplicationClient getUserCompanyApplication(
		long userId, long compId, String applicationId, long clientId,
		long contractId) {

		return getService().getUserCompanyApplication(
			userId, compId, applicationId, clientId, contractId);
	}

	public static int getUserCountByContracApp(
		Long clientId, Long contractId, Long idApplication, Long userId) {

		return getService().getUserCountByContracApp(
			clientId, contractId, idApplication, userId);
	}

	public static int getUsersByAppComp(String appId, long compId) {
		return getService().getUsersByAppComp(appId, compId);
	}

	public static List
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompany>
			getUsersByEmpresaIdAndApplicationWithLicense(
				long idApplication, long licenseId, long idEmpresa) {

		return getService().getUsersByEmpresaIdAndApplicationWithLicense(
			idApplication, licenseId, idEmpresa);
	}

	public static List<UserApplicationClient> getUsersFromApplication(
		long compId, String applicationId) {

		return getService().getUsersFromApplication(compId, applicationId);
	}

	public static boolean hasUserApplication(
		long userId, long compId, String applicationId, long clientId,
		long contractId) {

		return getService().hasUserApplication(
			userId, compId, applicationId, clientId, contractId);
	}

	public static boolean hasUserApplication31DaysAfterDeleted(
		long userId, long compId, String applicationId, long clientId,
		long contractId) {

		return getService().hasUserApplication31DaysAfterDeleted(
			userId, compId, applicationId, clientId, contractId);
	}

	/**
	 * Updates the user application client in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserApplicationClientLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userApplicationClient the user application client
	 * @return the user application client that was updated
	 */
	public static UserApplicationClient updateUserApplicationClient(
		UserApplicationClient userApplicationClient) {

		return getService().updateUserApplicationClient(userApplicationClient);
	}

	public static UserApplicationClientLocalService getService() {
		return _service;
	}

	private static volatile UserApplicationClientLocalService _service;

}