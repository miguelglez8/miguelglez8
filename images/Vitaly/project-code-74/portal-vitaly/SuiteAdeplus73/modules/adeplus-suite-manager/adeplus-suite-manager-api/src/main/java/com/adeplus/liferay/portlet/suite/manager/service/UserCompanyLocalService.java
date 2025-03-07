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
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserCompanyPK;

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

import java.util.Date;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for UserCompany. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserCompanyLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface UserCompanyLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.adeplus.liferay.portlet.suite.manager.service.impl.UserCompanyLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the user company local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link UserCompanyLocalServiceUtil} if injection and service tracking are not available.
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
	@Indexable(type = IndexableType.REINDEX)
	public UserCompany addUserCompany(UserCompany userCompany);

	/**
	 * Creates a new user company with the primary key. Does not add the user company to the database.
	 *
	 * @param userCompanyPK the primary key for the new user company
	 * @return the new user company
	 */
	@Transactional(enabled = false)
	public UserCompany createUserCompany(UserCompanyPK userCompanyPK);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

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
	@Indexable(type = IndexableType.DELETE)
	public UserCompany deleteUserCompany(UserCompany userCompany);

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
	@Indexable(type = IndexableType.DELETE)
	public UserCompany deleteUserCompany(UserCompanyPK userCompanyPK)
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserCompanyModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.UserCompanyModelImpl</code>.
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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserCompany fetchUserCompany(UserCompanyPK userCompanyPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserCompany> getActiveUserCompaniesFromUser(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserCompany> getAdminUsersFromCompany(long compId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserCompany> getCompaniesAdminFromUser(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserCompany> getCompaniesFromUser(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getCountAdminCompany(long compId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getCountUserCompany(long compId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserCompany getFirstActiveUserCompanyFromUser(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getLogoSrcFromUserCompany(long userId, long compId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserCompany> getOnlyUsersFromCompany(long compId);

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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserCompany getSingleUserCompanyFromUser(long userId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserCompany> getUserCompanies(int start, int end);

	/**
	 * Returns the number of user companies.
	 *
	 * @return the number of user companies
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserCompaniesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserCompany getUserCompany(long userId, long compId);

	/**
	 * Returns the user company with the primary key.
	 *
	 * @param userCompanyPK the primary key of the user company
	 * @return the user company
	 * @throws PortalException if a user company with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserCompany getUserCompany(UserCompanyPK userCompanyPK)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserCompany getUserDefaultCompany(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserCompany> getUsersByEmail(String email);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserCompany> getUsersByEmailCompId(String email, long compId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserCompany> getUsersByNif(String nif);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserCompany> getUsersByNifCompId(String nif, long compId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserCompany> getUsersByNifEmail(String nif, String email);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserCompany> getUsersByNifEmailCompId(
		String nif, String email, long compId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getUsersCountFromCompany(long compId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserCompany> getUsersFromCompany(long compId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserCompany> getUsersToInactive(Date endDate);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserMultiCompany(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isUserAdminInActiveComp(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isUserAdminInComp(long userId, long compId);

	public void setUserDefaultCompany(long userId, long compId);

	public void setUserDefaultCompany(
		long userId, long compId, long clientId, long contractId,
		long empresaId);

	public UserCompany updateUserCompany(
		long userId, long compId, String name, String lastName, String nif,
		String email, String phone, boolean admin, Date dateEnd);

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
	@Indexable(type = IndexableType.REINDEX)
	public UserCompany updateUserCompany(UserCompany userCompany);

}