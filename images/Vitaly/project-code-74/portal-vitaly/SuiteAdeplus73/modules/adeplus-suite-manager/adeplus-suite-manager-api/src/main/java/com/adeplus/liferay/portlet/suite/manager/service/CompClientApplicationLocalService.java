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

import com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication;

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

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for CompClientApplication. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see CompClientApplicationLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CompClientApplicationLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.adeplus.liferay.portlet.suite.manager.service.impl.CompClientApplicationLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the comp client application local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link CompClientApplicationLocalServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Adds the comp client application to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompClientApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param compClientApplication the comp client application
	 * @return the comp client application that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CompClientApplication addCompClientApplication(
		CompClientApplication compClientApplication);

	/**
	 * Creates a new comp client application with the primary key. Does not add the comp client application to the database.
	 *
	 * @param empresaId the primary key for the new comp client application
	 * @return the new comp client application
	 */
	@Transactional(enabled = false)
	public CompClientApplication createCompClientApplication(long empresaId);

	/**
	 * Deletes the comp client application from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompClientApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param compClientApplication the comp client application
	 * @return the comp client application that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public CompClientApplication deleteCompClientApplication(
		CompClientApplication compClientApplication);

	/**
	 * Deletes the comp client application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompClientApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param empresaId the primary key of the comp client application
	 * @return the comp client application that was removed
	 * @throws PortalException if a comp client application with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public CompClientApplication deleteCompClientApplication(long empresaId)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.CompClientApplicationModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.CompClientApplicationModelImpl</code>.
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
	public CompClientApplication fetchCompClientApplication(long empresaId);

	public boolean findByClient(long idCliente);

	public boolean findByClientCompany(long idComp, long idCliente);

	public boolean findByContractAndClient(long idContract, long idCliente);

	public boolean findByContractId(long contractId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CompClientApplication> getAllCompByIdClient(long idCliente);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CompClientApplication getClientByCompAndClient(
		long idComp, long idCliente);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CompClientApplication getClientByCompAndClientAndContract(
		long idComp, long idClient, long idContract);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CompClientApplication> getClientsApplicationsByCompAndClient(
		long idComp, long idCliente);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CompClientApplication> getClientsByCompanyId(long idComp);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CompClientApplication getCompClientAppByIdEmpresaIdApp(
		Long idEmpresa, Long idApp);

	/**
	 * Returns the comp client application with the primary key.
	 *
	 * @param empresaId the primary key of the comp client application
	 * @return the comp client application
	 * @throws PortalException if a comp client application with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CompClientApplication getCompClientApplication(long empresaId)
		throws PortalException;

	/**
	 * Returns a range of all the comp client applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.CompClientApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of comp client applications
	 * @param end the upper bound of the range of comp client applications (not inclusive)
	 * @return the range of comp client applications
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CompClientApplication> getCompClientApplications(
		int start, int end);

	/**
	 * Returns the number of comp client applications.
	 *
	 * @return the number of comp client applications
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCompClientApplicationsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CompClientApplication> getContractAndClient(
		long idCliente, long idContract);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CompClientApplication> getDiferentClientsByCompanyId(
		long idComp);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CompClientApplication> getIdCompanyByApplicationAndLicense(
		long idApplication, long licenseId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Long> getIdCompanyByApplicationId(long idApplication);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

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
	 * Updates the comp client application in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompClientApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param compClientApplication the comp client application
	 * @return the comp client application that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CompClientApplication updateCompClientApplication(
		CompClientApplication compClientApplication);

}