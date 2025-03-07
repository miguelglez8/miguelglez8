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
 * Provides a wrapper for {@link UserApplicationClientLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserApplicationClientLocalService
 * @generated
 */
public class UserApplicationClientLocalServiceWrapper
	implements ServiceWrapper<UserApplicationClientLocalService>,
			   UserApplicationClientLocalService {

	public UserApplicationClientLocalServiceWrapper(
		UserApplicationClientLocalService userApplicationClientLocalService) {

		_userApplicationClientLocalService = userApplicationClientLocalService;
	}

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
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient
		addUserApplicationClient(
			com.adeplus.liferay.portlet.suite.manager.model.
				UserApplicationClient userApplicationClient) {

		return _userApplicationClientLocalService.addUserApplicationClient(
			userApplicationClient);
	}

	/**
	 * Creates a new user application client with the primary key. Does not add the user application client to the database.
	 *
	 * @param userApplicationClientPK the primary key for the new user application client
	 * @return the new user application client
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient
		createUserApplicationClient(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				UserApplicationClientPK userApplicationClientPK) {

		return _userApplicationClientLocalService.createUserApplicationClient(
			userApplicationClientPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userApplicationClientLocalService.deletePersistedModel(
			persistedModel);
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
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient
		deleteUserApplicationClient(
			com.adeplus.liferay.portlet.suite.manager.model.
				UserApplicationClient userApplicationClient) {

		return _userApplicationClientLocalService.deleteUserApplicationClient(
			userApplicationClient);
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
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient
			deleteUserApplicationClient(
				com.adeplus.liferay.portlet.suite.manager.service.persistence.
					UserApplicationClientPK userApplicationClientPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userApplicationClientLocalService.deleteUserApplicationClient(
			userApplicationClientPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userApplicationClientLocalService.dynamicQuery();
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

		return _userApplicationClientLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _userApplicationClientLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _userApplicationClientLocalService.dynamicQuery(
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

		return _userApplicationClientLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _userApplicationClientLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public boolean existUserByRole(
		long idUser, long licenseId, long idEmpresa) {

		return _userApplicationClientLocalService.existUserByRole(
			idUser, licenseId, idEmpresa);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient
		fetchUserApplicationClient(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				UserApplicationClientPK userApplicationClientPK) {

		return _userApplicationClientLocalService.fetchUserApplicationClient(
			userApplicationClientPK);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient>
			findByCompClientContractId(
				long compId, long clientId, long contractId) {

		return _userApplicationClientLocalService.findByCompClientContractId(
			compId, clientId, contractId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _userApplicationClientLocalService.getActionableDynamicQuery();
	}

	@Override
	public int getAdminsByAppComp(String appId, long compId) {
		return _userApplicationClientLocalService.getAdminsByAppComp(
			appId, compId);
	}

	@Override
	public int getAdminsCountByContracApp(
		Long clientId, Long contractId, Long idApplication, Long userId) {

		return _userApplicationClientLocalService.getAdminsCountByContracApp(
			clientId, contractId, idApplication, userId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient>
			getAllApplicationsFromUser(long userId, long compId) {

		return _userApplicationClientLocalService.getAllApplicationsFromUser(
			userId, compId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient>
			getAllUserApplicationsByCompany(long compid) {

		return _userApplicationClientLocalService.
			getAllUserApplicationsByCompany(compid);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient>
			getAllUserApplicationsByEmpresa(long empresaId) {

		return _userApplicationClientLocalService.
			getAllUserApplicationsByEmpresa(empresaId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient>
			getApplicationsFromUser(long userId, long compId) {

		return _userApplicationClientLocalService.getApplicationsFromUser(
			userId, compId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userApplicationClientLocalService.
			getIndexableActionableDynamicQuery();
	}

	@Override
	public java.util.List<Long> getListApplicationsFromUser(
		long userId, long compId) {

		return _userApplicationClientLocalService.getListApplicationsFromUser(
			userId, compId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient>
			getListApplicationsFromUserCompanyClient(
				long userId, long compId, long clientId) {

		return _userApplicationClientLocalService.
			getListApplicationsFromUserCompanyClient(userId, compId, clientId);
	}

	@Override
	public int getOnlyAdminsFromCompanyEmpresaTotal(
		long clientId, long contractId) {

		return _userApplicationClientLocalService.
			getOnlyAdminsFromCompanyEmpresaTotal(clientId, contractId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient>
			getOnlyUsersFromCompanyEmpresa(long clientId, long contractId) {

		return _userApplicationClientLocalService.
			getOnlyUsersFromCompanyEmpresa(clientId, contractId);
	}

	@Override
	public int getOnlyUsersFromCompanyEmpresaTotal(
		long clientId, long contractId) {

		return _userApplicationClientLocalService.
			getOnlyUsersFromCompanyEmpresaTotal(clientId, contractId);
	}

	@Override
	public int getOnlyUsersFromCompanyEmpresaTotal(
		long clientId, long contractId, long appId) {

		return _userApplicationClientLocalService.
			getOnlyUsersFromCompanyEmpresaTotal(clientId, contractId, appId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userApplicationClientLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userApplicationClientLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the user application client with the primary key.
	 *
	 * @param userApplicationClientPK the primary key of the user application client
	 * @return the user application client
	 * @throws PortalException if a user application client with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient
			getUserApplicationClient(
				com.adeplus.liferay.portlet.suite.manager.service.persistence.
					UserApplicationClientPK userApplicationClientPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userApplicationClientLocalService.getUserApplicationClient(
			userApplicationClientPK);
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
	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient>
			getUserApplicationClients(int start, int end) {

		return _userApplicationClientLocalService.getUserApplicationClients(
			start, end);
	}

	/**
	 * Returns the number of user application clients.
	 *
	 * @return the number of user application clients
	 */
	@Override
	public int getUserApplicationClientsCount() {
		return _userApplicationClientLocalService.
			getUserApplicationClientsCount();
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient
		getUserCompanyApplication(
			long userId, long compId, String applicationId, long clientId,
			long contractId) {

		return _userApplicationClientLocalService.getUserCompanyApplication(
			userId, compId, applicationId, clientId, contractId);
	}

	@Override
	public int getUserCountByContracApp(
		Long clientId, Long contractId, Long idApplication, Long userId) {

		return _userApplicationClientLocalService.getUserCountByContracApp(
			clientId, contractId, idApplication, userId);
	}

	@Override
	public int getUsersByAppComp(String appId, long compId) {
		return _userApplicationClientLocalService.getUsersByAppComp(
			appId, compId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserCompany>
			getUsersByEmpresaIdAndApplicationWithLicense(
				long idApplication, long licenseId, long idEmpresa) {

		return _userApplicationClientLocalService.
			getUsersByEmpresaIdAndApplicationWithLicense(
				idApplication, licenseId, idEmpresa);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient>
			getUsersFromApplication(long compId, String applicationId) {

		return _userApplicationClientLocalService.getUsersFromApplication(
			compId, applicationId);
	}

	@Override
	public boolean hasUserApplication(
		long userId, long compId, String applicationId, long clientId,
		long contractId) {

		return _userApplicationClientLocalService.hasUserApplication(
			userId, compId, applicationId, clientId, contractId);
	}

	@Override
	public boolean hasUserApplication31DaysAfterDeleted(
		long userId, long compId, String applicationId, long clientId,
		long contractId) {

		return _userApplicationClientLocalService.
			hasUserApplication31DaysAfterDeleted(
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
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient
		updateUserApplicationClient(
			com.adeplus.liferay.portlet.suite.manager.model.
				UserApplicationClient userApplicationClient) {

		return _userApplicationClientLocalService.updateUserApplicationClient(
			userApplicationClient);
	}

	@Override
	public UserApplicationClientLocalService getWrappedService() {
		return _userApplicationClientLocalService;
	}

	@Override
	public void setWrappedService(
		UserApplicationClientLocalService userApplicationClientLocalService) {

		_userApplicationClientLocalService = userApplicationClientLocalService;
	}

	private UserApplicationClientLocalService
		_userApplicationClientLocalService;

}