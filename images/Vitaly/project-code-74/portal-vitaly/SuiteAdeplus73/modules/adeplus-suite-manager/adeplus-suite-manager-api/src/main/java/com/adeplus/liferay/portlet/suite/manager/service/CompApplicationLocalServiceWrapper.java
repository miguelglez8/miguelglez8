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
 * Provides a wrapper for {@link CompApplicationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CompApplicationLocalService
 * @generated
 */
public class CompApplicationLocalServiceWrapper
	implements CompApplicationLocalService,
			   ServiceWrapper<CompApplicationLocalService> {

	public CompApplicationLocalServiceWrapper(
		CompApplicationLocalService compApplicationLocalService) {

		_compApplicationLocalService = compApplicationLocalService;
	}

	/**
	 * Adds the comp application to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param compApplication the comp application
	 * @return the comp application that was added
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompApplication
		addCompApplication(
			com.adeplus.liferay.portlet.suite.manager.model.CompApplication
				compApplication) {

		return _compApplicationLocalService.addCompApplication(compApplication);
	}

	/**
	 * Creates a new comp application with the primary key. Does not add the comp application to the database.
	 *
	 * @param compApplicationPK the primary key for the new comp application
	 * @return the new comp application
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompApplication
		createCompApplication(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				CompApplicationPK compApplicationPK) {

		return _compApplicationLocalService.createCompApplication(
			compApplicationPK);
	}

	/**
	 * Deletes the comp application from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param compApplication the comp application
	 * @return the comp application that was removed
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompApplication
		deleteCompApplication(
			com.adeplus.liferay.portlet.suite.manager.model.CompApplication
				compApplication) {

		return _compApplicationLocalService.deleteCompApplication(
			compApplication);
	}

	/**
	 * Deletes the comp application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param compApplicationPK the primary key of the comp application
	 * @return the comp application that was removed
	 * @throws PortalException if a comp application with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompApplication
			deleteCompApplication(
				com.adeplus.liferay.portlet.suite.manager.service.persistence.
					CompApplicationPK compApplicationPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _compApplicationLocalService.deleteCompApplication(
			compApplicationPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _compApplicationLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _compApplicationLocalService.dynamicQuery();
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

		return _compApplicationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.CompApplicationModelImpl</code>.
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

		return _compApplicationLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.CompApplicationModelImpl</code>.
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

		return _compApplicationLocalService.dynamicQuery(
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

		return _compApplicationLocalService.dynamicQueryCount(dynamicQuery);
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

		return _compApplicationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompApplication
		fetchCompApplication(
			com.adeplus.liferay.portlet.suite.manager.service.persistence.
				CompApplicationPK compApplicationPK) {

		return _compApplicationLocalService.fetchCompApplication(
			compApplicationPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _compApplicationLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.CompApplication>
			getApplicationsFromCompany(long compId) {

		return _compApplicationLocalService.getApplicationsFromCompany(compId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.CompApplication>
			getCompaniesWithApplication(long applicationId) {

		return _compApplicationLocalService.getCompaniesWithApplication(
			applicationId);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompApplication
		getCompanyApplication(long companyId, long applicationId) {

		return _compApplicationLocalService.getCompanyApplication(
			companyId, applicationId);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompApplication
		getCompanyApplication(
			long companyId, long applicationId, boolean isMulti) {

		return _compApplicationLocalService.getCompanyApplication(
			companyId, applicationId, isMulti);
	}

	/**
	 * Returns the comp application with the primary key.
	 *
	 * @param compApplicationPK the primary key of the comp application
	 * @return the comp application
	 * @throws PortalException if a comp application with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompApplication
			getCompApplication(
				com.adeplus.liferay.portlet.suite.manager.service.persistence.
					CompApplicationPK compApplicationPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _compApplicationLocalService.getCompApplication(
			compApplicationPK);
	}

	/**
	 * Returns a range of all the comp applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.CompApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of comp applications
	 * @param end the upper bound of the range of comp applications (not inclusive)
	 * @return the range of comp applications
	 */
	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.CompApplication>
			getCompApplications(int start, int end) {

		return _compApplicationLocalService.getCompApplications(start, end);
	}

	/**
	 * Returns the number of comp applications.
	 *
	 * @return the number of comp applications
	 */
	@Override
	public int getCompApplicationsCount() {
		return _compApplicationLocalService.getCompApplicationsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _compApplicationLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _compApplicationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _compApplicationLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean hasCompanyApplication(long companyId, long applicationId) {
		return _compApplicationLocalService.hasCompanyApplication(
			companyId, applicationId);
	}

	/**
	 * Updates the comp application in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param compApplication the comp application
	 * @return the comp application that was updated
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.CompApplication
		updateCompApplication(
			com.adeplus.liferay.portlet.suite.manager.model.CompApplication
				compApplication) {

		return _compApplicationLocalService.updateCompApplication(
			compApplication);
	}

	@Override
	public CompApplicationLocalService getWrappedService() {
		return _compApplicationLocalService;
	}

	@Override
	public void setWrappedService(
		CompApplicationLocalService compApplicationLocalService) {

		_compApplicationLocalService = compApplicationLocalService;
	}

	private CompApplicationLocalService _compApplicationLocalService;

}