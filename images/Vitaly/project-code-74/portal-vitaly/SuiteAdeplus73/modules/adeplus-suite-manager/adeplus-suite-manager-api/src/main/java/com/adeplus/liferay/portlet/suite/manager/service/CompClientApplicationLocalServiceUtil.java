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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for CompClientApplication. This utility wraps
 * <code>com.adeplus.liferay.portlet.suite.manager.service.impl.CompClientApplicationLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CompClientApplicationLocalService
 * @generated
 */
public class CompClientApplicationLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.adeplus.liferay.portlet.suite.manager.service.impl.CompClientApplicationLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
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
	public static CompClientApplication addCompClientApplication(
		CompClientApplication compClientApplication) {

		return getService().addCompClientApplication(compClientApplication);
	}

	/**
	 * Creates a new comp client application with the primary key. Does not add the comp client application to the database.
	 *
	 * @param empresaId the primary key for the new comp client application
	 * @return the new comp client application
	 */
	public static CompClientApplication createCompClientApplication(
		long empresaId) {

		return getService().createCompClientApplication(empresaId);
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
	public static CompClientApplication deleteCompClientApplication(
		CompClientApplication compClientApplication) {

		return getService().deleteCompClientApplication(compClientApplication);
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
	public static CompClientApplication deleteCompClientApplication(
			long empresaId)
		throws PortalException {

		return getService().deleteCompClientApplication(empresaId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.CompClientApplicationModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.CompClientApplicationModelImpl</code>.
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

	public static CompClientApplication fetchCompClientApplication(
		long empresaId) {

		return getService().fetchCompClientApplication(empresaId);
	}

	public static boolean findByClient(long idCliente) {
		return getService().findByClient(idCliente);
	}

	public static boolean findByClientCompany(long idComp, long idCliente) {
		return getService().findByClientCompany(idComp, idCliente);
	}

	public static boolean findByContractAndClient(
		long idContract, long idCliente) {

		return getService().findByContractAndClient(idContract, idCliente);
	}

	public static boolean findByContractId(long contractId) {
		return getService().findByContractId(contractId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<CompClientApplication> getAllCompByIdClient(
		long idCliente) {

		return getService().getAllCompByIdClient(idCliente);
	}

	public static CompClientApplication getClientByCompAndClient(
		long idComp, long idCliente) {

		return getService().getClientByCompAndClient(idComp, idCliente);
	}

	public static CompClientApplication getClientByCompAndClientAndContract(
		long idComp, long idClient, long idContract) {

		return getService().getClientByCompAndClientAndContract(
			idComp, idClient, idContract);
	}

	public static List<CompClientApplication>
		getClientsApplicationsByCompAndClient(long idComp, long idCliente) {

		return getService().getClientsApplicationsByCompAndClient(
			idComp, idCliente);
	}

	public static List<CompClientApplication> getClientsByCompanyId(
		long idComp) {

		return getService().getClientsByCompanyId(idComp);
	}

	public static CompClientApplication getCompClientAppByIdEmpresaIdApp(
		Long idEmpresa, Long idApp) {

		return getService().getCompClientAppByIdEmpresaIdApp(idEmpresa, idApp);
	}

	/**
	 * Returns the comp client application with the primary key.
	 *
	 * @param empresaId the primary key of the comp client application
	 * @return the comp client application
	 * @throws PortalException if a comp client application with the primary key could not be found
	 */
	public static CompClientApplication getCompClientApplication(long empresaId)
		throws PortalException {

		return getService().getCompClientApplication(empresaId);
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
	public static List<CompClientApplication> getCompClientApplications(
		int start, int end) {

		return getService().getCompClientApplications(start, end);
	}

	/**
	 * Returns the number of comp client applications.
	 *
	 * @return the number of comp client applications
	 */
	public static int getCompClientApplicationsCount() {
		return getService().getCompClientApplicationsCount();
	}

	public static List<CompClientApplication> getContractAndClient(
		long idCliente, long idContract) {

		return getService().getContractAndClient(idCliente, idContract);
	}

	public static List<CompClientApplication> getDiferentClientsByCompanyId(
		long idComp) {

		return getService().getDiferentClientsByCompanyId(idComp);
	}

	public static List<CompClientApplication>
		getIdCompanyByApplicationAndLicense(
			long idApplication, long licenseId) {

		return getService().getIdCompanyByApplicationAndLicense(
			idApplication, licenseId);
	}

	public static List<Long> getIdCompanyByApplicationId(long idApplication) {
		return getService().getIdCompanyByApplicationId(idApplication);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
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
	 * Updates the comp client application in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompClientApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param compClientApplication the comp client application
	 * @return the comp client application that was updated
	 */
	public static CompClientApplication updateCompClientApplication(
		CompClientApplication compClientApplication) {

		return getService().updateCompClientApplication(compClientApplication);
	}

	public static CompClientApplicationLocalService getService() {
		return _service;
	}

	private static volatile CompClientApplicationLocalService _service;

}