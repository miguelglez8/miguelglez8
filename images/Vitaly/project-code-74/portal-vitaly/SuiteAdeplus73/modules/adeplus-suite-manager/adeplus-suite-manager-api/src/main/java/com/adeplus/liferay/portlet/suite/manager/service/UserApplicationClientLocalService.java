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
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserApplicationClientPK;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.*;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for UserApplicationClient. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserApplicationClientLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface UserApplicationClientLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.adeplus.liferay.portlet.suite.manager.service.impl.UserApplicationClientLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the user application client local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link UserApplicationClientLocalServiceUtil} if injection and service tracking are not available.
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
	@Indexable(type = IndexableType.REINDEX)
	public UserApplicationClient addUserApplicationClient(
		UserApplicationClient userApplicationClient);

	/**
	 * Creates a new user application client with the primary key. Does not add the user application client to the database.
	 *
	 * @param userApplicationClientPK the primary key for the new user application client
	 * @return the new user application client
	 */
	@Transactional(enabled = false)
	public UserApplicationClient createUserApplicationClient(
		UserApplicationClientPK userApplicationClientPK);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

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
	@Indexable(type = IndexableType.DELETE)
	public UserApplicationClient deleteUserApplicationClient(
		UserApplicationClient userApplicationClient);

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
	@Indexable(type = IndexableType.DELETE)
	public UserApplicationClient deleteUserApplicationClient(
			UserApplicationClientPK userApplicationClientPK)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	public boolean existUserByRole(long idUser, long licenseId, long idEmpresa);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserApplicationClient fetchUserApplicationClient(
		UserApplicationClientPK userApplicationClientPK);

	public List<UserApplicationClient> findByCompClientContractId(
		long compId, long clientId, long contractId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAdminsByAppComp(String appId, long compId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAdminsCountByContracApp(
		Long clientId, Long contractId, Long idApplication, Long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserApplicationClient> getAllApplicationsFromUser(
		long userId, long compId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserApplicationClient> getAllUserApplicationsByCompany(
		long compid);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserApplicationClient> getAllUserApplicationsByEmpresa(
		long empresaId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserApplicationClient> getApplicationsFromUser(
		long userId, long compId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Long> getListApplicationsFromUser(long userId, long compId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserApplicationClient> getListApplicationsFromUserCompanyClient(
		long userId, long compId, long clientId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOnlyAdminsFromCompanyEmpresaTotal(
		long clientId, long contractId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserApplicationClient> getOnlyUsersFromCompanyEmpresa(
		long clientId, long contractId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOnlyUsersFromCompanyEmpresaTotal(
		long clientId, long contractId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOnlyUsersFromCompanyEmpresaTotal(
		long clientId, long contractId, long appId);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Returns the user application client with the primary key.
	 *
	 * @param userApplicationClientPK the primary key of the user application client
	 * @return the user application client
	 * @throws PortalException if a user application client with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserApplicationClient getUserApplicationClient(
			UserApplicationClientPK userApplicationClientPK)
		throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserApplicationClient> getUserApplicationClients(
		int start, int end);

	/**
	 * Returns the number of user application clients.
	 *
	 * @return the number of user application clients
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserApplicationClientsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserApplicationClient getUserCompanyApplication(
		long userId, long compId, String applicationId, long clientId,
		long contractId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserCountByContracApp(
		Long clientId, Long contractId, Long idApplication, Long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUsersByAppComp(String appId, long compId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserCompany> getUsersByEmpresaIdAndApplicationWithLicense(
		long idApplication, long licenseId, long idEmpresa);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserApplicationClient> getUsersFromApplication(
		long compId, String applicationId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserApplication(
		long userId, long compId, String applicationId, long clientId,
		long contractId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserApplication31DaysAfterDeleted(
		long userId, long compId, String applicationId, long clientId,
		long contractId);

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
	@Indexable(type = IndexableType.REINDEX)
	public UserApplicationClient updateUserApplicationClient(
		UserApplicationClient userApplicationClient);

}