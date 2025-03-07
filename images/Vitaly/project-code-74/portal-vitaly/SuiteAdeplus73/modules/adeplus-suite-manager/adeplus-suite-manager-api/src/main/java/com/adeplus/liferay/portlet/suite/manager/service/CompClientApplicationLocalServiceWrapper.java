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
 * Provides a wrapper for {@link CompClientApplicationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CompClientApplicationLocalService
 * @generated
 */
public class CompClientApplicationLocalServiceWrapper
	implements CompClientApplicationLocalService,
			   ServiceWrapper<CompClientApplicationLocalService> {

	public CompClientApplicationLocalServiceWrapper(
		CompClientApplicationLocalService compClientApplicationLocalService) {

		_compClientApplicationLocalService = compClientApplicationLocalService;
	}

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
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication
		addCompClientApplication(
			com.adeplus.liferay.portlet.suite.manager.model.
				CompClientApplication compClientApplication) {

		return _compClientApplicationLocalService.addCompClientApplication(
			compClientApplication);
	}

	/**
	 * Creates a new comp client application with the primary key. Does not add the comp client application to the database.
	 *
	 * @param empresaId the primary key for the new comp client application
	 * @return the new comp client application
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication
		createCompClientApplication(long empresaId) {

		return _compClientApplicationLocalService.createCompClientApplication(
			empresaId);
	}

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
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication
		deleteCompClientApplication(
			com.adeplus.liferay.portlet.suite.manager.model.
				CompClientApplication compClientApplication) {

		return _compClientApplicationLocalService.deleteCompClientApplication(
			compClientApplication);
	}

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
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication
			deleteCompClientApplication(long empresaId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _compClientApplicationLocalService.deleteCompClientApplication(
			empresaId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _compClientApplicationLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _compClientApplicationLocalService.dynamicQuery();
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

		return _compClientApplicationLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _compClientApplicationLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _compClientApplicationLocalService.dynamicQuery(
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

		return _compClientApplicationLocalService.dynamicQueryCount(
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

		return _compClientApplicationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication
		fetchCompClientApplication(long empresaId) {

		return _compClientApplicationLocalService.fetchCompClientApplication(
			empresaId);
	}

	@Override
	public boolean findByClient(long idCliente) {
		return _compClientApplicationLocalService.findByClient(idCliente);
	}

	@Override
	public boolean findByClientCompany(long idComp, long idCliente) {
		return _compClientApplicationLocalService.findByClientCompany(
			idComp, idCliente);
	}

	@Override
	public boolean findByContractAndClient(long idContract, long idCliente) {
		return _compClientApplicationLocalService.findByContractAndClient(
			idContract, idCliente);
	}

	@Override
	public boolean findByContractId(long contractId) {
		return _compClientApplicationLocalService.findByContractId(contractId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _compClientApplicationLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication>
			getAllCompByIdClient(long idCliente) {

		return _compClientApplicationLocalService.getAllCompByIdClient(
			idCliente);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication
		getClientByCompAndClient(long idComp, long idCliente) {

		return _compClientApplicationLocalService.getClientByCompAndClient(
			idComp, idCliente);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication
		getClientByCompAndClientAndContract(
			long idComp, long idClient, long idContract) {

		return _compClientApplicationLocalService.
			getClientByCompAndClientAndContract(idComp, idClient, idContract);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication>
			getClientsApplicationsByCompAndClient(long idComp, long idCliente) {

		return _compClientApplicationLocalService.
			getClientsApplicationsByCompAndClient(idComp, idCliente);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication>
			getClientsByCompanyId(long idComp) {

		return _compClientApplicationLocalService.getClientsByCompanyId(idComp);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication
		getCompClientAppByIdEmpresaIdApp(Long idEmpresa, Long idApp) {

		return _compClientApplicationLocalService.
			getCompClientAppByIdEmpresaIdApp(idEmpresa, idApp);
	}

	/**
	 * Returns the comp client application with the primary key.
	 *
	 * @param empresaId the primary key of the comp client application
	 * @return the comp client application
	 * @throws PortalException if a comp client application with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication
			getCompClientApplication(long empresaId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _compClientApplicationLocalService.getCompClientApplication(
			empresaId);
	}

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
	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication>
			getCompClientApplications(int start, int end) {

		return _compClientApplicationLocalService.getCompClientApplications(
			start, end);
	}

	/**
	 * Returns the number of comp client applications.
	 *
	 * @return the number of comp client applications
	 */
	@Override
	public int getCompClientApplicationsCount() {
		return _compClientApplicationLocalService.
			getCompClientApplicationsCount();
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication>
			getContractAndClient(long idCliente, long idContract) {

		return _compClientApplicationLocalService.getContractAndClient(
			idCliente, idContract);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication>
			getDiferentClientsByCompanyId(long idComp) {

		return _compClientApplicationLocalService.getDiferentClientsByCompanyId(
			idComp);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication>
			getIdCompanyByApplicationAndLicense(
				long idApplication, long licenseId) {

		return _compClientApplicationLocalService.
			getIdCompanyByApplicationAndLicense(idApplication, licenseId);
	}

	@Override
	public java.util.List<Long> getIdCompanyByApplicationId(
		long idApplication) {

		return _compClientApplicationLocalService.getIdCompanyByApplicationId(
			idApplication);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _compClientApplicationLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _compClientApplicationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _compClientApplicationLocalService.getPersistedModel(
			primaryKeyObj);
	}

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
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication
		updateCompClientApplication(
			com.adeplus.liferay.portlet.suite.manager.model.
				CompClientApplication compClientApplication) {

		return _compClientApplicationLocalService.updateCompClientApplication(
			compClientApplication);
	}

	@Override
	public CompClientApplicationLocalService getWrappedService() {
		return _compClientApplicationLocalService;
	}

	@Override
	public void setWrappedService(
		CompClientApplicationLocalService compClientApplicationLocalService) {

		_compClientApplicationLocalService = compClientApplicationLocalService;
	}

	private CompClientApplicationLocalService
		_compClientApplicationLocalService;

}