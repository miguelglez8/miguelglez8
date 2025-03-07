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

package com.legalplus.liferay.portlet.legalplus.manager.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ContratoCompany. This utility wraps
 * <code>com.legalplus.liferay.portlet.legalplus.manager.service.impl.ContratoCompanyLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ContratoCompanyLocalService
 * @generated
 */
public class ContratoCompanyLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.legalplus.liferay.portlet.legalplus.manager.service.impl.ContratoCompanyLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the contrato company to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContratoCompanyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contratoCompany the contrato company
	 * @return the contrato company that was added
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.ContratoCompany
			addContratoCompany(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					ContratoCompany contratoCompany) {

		return getService().addContratoCompany(contratoCompany);
	}

	/**
	 * Creates a new contrato company with the primary key. Does not add the contrato company to the database.
	 *
	 * @param contratoCompanyPK the primary key for the new contrato company
	 * @return the new contrato company
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.ContratoCompany
			createContratoCompany(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.ContratoCompanyPK contratoCompanyPK) {

		return getService().createContratoCompany(contratoCompanyPK);
	}

	/**
	 * Deletes the contrato company from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContratoCompanyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contratoCompany the contrato company
	 * @return the contrato company that was removed
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.ContratoCompany
			deleteContratoCompany(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					ContratoCompany contratoCompany) {

		return getService().deleteContratoCompany(contratoCompany);
	}

	/**
	 * Deletes the contrato company with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContratoCompanyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contratoCompanyPK the primary key of the contrato company
	 * @return the contrato company that was removed
	 * @throws PortalException if a contrato company with the primary key could not be found
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.ContratoCompany
				deleteContratoCompany(
					com.legalplus.liferay.portlet.legalplus.manager.service.
						persistence.ContratoCompanyPK contratoCompanyPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteContratoCompany(contratoCompanyPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.ContratoCompanyModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.ContratoCompanyModelImpl</code>.
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
		com.legalplus.liferay.portlet.legalplus.manager.model.ContratoCompany
			fetchByCompId(Long compId) {

		return getService().fetchByCompId(compId);
	}

	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.ContratoCompany
			fetchByContractId(Long contractId) {

		return getService().fetchByContractId(contractId);
	}

	public static java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.ContratoCompany>
			fetchByLegislacion(
				String familias, long ccaa, long ayto, String cnaes) {

		return getService().fetchByLegislacion(familias, ccaa, ayto, cnaes);
	}

	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.ContratoCompany
			fetchContratoCompany(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.ContratoCompanyPK contratoCompanyPK) {

		return getService().fetchContratoCompany(contratoCompanyPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the contrato companies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.ContratoCompanyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contrato companies
	 * @param end the upper bound of the range of contrato companies (not inclusive)
	 * @return the range of contrato companies
	 */
	public static java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.ContratoCompany>
			getContratoCompanies(int start, int end) {

		return getService().getContratoCompanies(start, end);
	}

	/**
	 * Returns the number of contrato companies.
	 *
	 * @return the number of contrato companies
	 */
	public static int getContratoCompaniesCount() {
		return getService().getContratoCompaniesCount();
	}

	/**
	 * Returns the contrato company with the primary key.
	 *
	 * @param contratoCompanyPK the primary key of the contrato company
	 * @return the contrato company
	 * @throws PortalException if a contrato company with the primary key could not be found
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.ContratoCompany
				getContratoCompany(
					com.legalplus.liferay.portlet.legalplus.manager.service.
						persistence.ContratoCompanyPK contratoCompanyPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getContratoCompany(contratoCompanyPK);
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
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the contrato company in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContratoCompanyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contratoCompany the contrato company
	 * @return the contrato company that was updated
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.ContratoCompany
			updateContratoCompany(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					ContratoCompany contratoCompany) {

		return getService().updateContratoCompany(contratoCompany);
	}

	public static ContratoCompanyLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ContratoCompanyLocalService, ContratoCompanyLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ContratoCompanyLocalService.class);

		ServiceTracker<ContratoCompanyLocalService, ContratoCompanyLocalService>
			serviceTracker =
				new ServiceTracker
					<ContratoCompanyLocalService, ContratoCompanyLocalService>(
						bundle.getBundleContext(),
						ContratoCompanyLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}