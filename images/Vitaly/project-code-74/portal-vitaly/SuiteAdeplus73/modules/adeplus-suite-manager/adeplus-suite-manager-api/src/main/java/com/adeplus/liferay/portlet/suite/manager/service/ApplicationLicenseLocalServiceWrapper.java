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
 * Provides a wrapper for {@link ApplicationLicenseLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationLicenseLocalService
 * @generated
 */
public class ApplicationLicenseLocalServiceWrapper
	implements ApplicationLicenseLocalService,
			   ServiceWrapper<ApplicationLicenseLocalService> {

	public ApplicationLicenseLocalServiceWrapper(
		ApplicationLicenseLocalService applicationLicenseLocalService) {

		_applicationLicenseLocalService = applicationLicenseLocalService;
	}

	/**
	 * Adds the application license to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationLicenseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationLicense the application license
	 * @return the application license that was added
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense
		addApplicationLicense(
			com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense
				applicationLicense) {

		return _applicationLicenseLocalService.addApplicationLicense(
			applicationLicense);
	}

	/**
	 * Creates a new application license with the primary key. Does not add the application license to the database.
	 *
	 * @param licenseId the primary key for the new application license
	 * @return the new application license
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense
		createApplicationLicense(long licenseId) {

		return _applicationLicenseLocalService.createApplicationLicense(
			licenseId);
	}

	/**
	 * Deletes the application license from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationLicenseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationLicense the application license
	 * @return the application license that was removed
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense
		deleteApplicationLicense(
			com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense
				applicationLicense) {

		return _applicationLicenseLocalService.deleteApplicationLicense(
			applicationLicense);
	}

	/**
	 * Deletes the application license with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationLicenseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseId the primary key of the application license
	 * @return the application license that was removed
	 * @throws PortalException if a application license with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense
			deleteApplicationLicense(long licenseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationLicenseLocalService.deleteApplicationLicense(
			licenseId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationLicenseLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _applicationLicenseLocalService.dynamicQuery();
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

		return _applicationLicenseLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.ApplicationLicenseModelImpl</code>.
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

		return _applicationLicenseLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.ApplicationLicenseModelImpl</code>.
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

		return _applicationLicenseLocalService.dynamicQuery(
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

		return _applicationLicenseLocalService.dynamicQueryCount(dynamicQuery);
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

		return _applicationLicenseLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense
		fetchApplicationLicense(long licenseId) {

		return _applicationLicenseLocalService.fetchApplicationLicense(
			licenseId);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense
		fetchByAppLicense(long applicationId, long licenseId) {

		return _applicationLicenseLocalService.fetchByAppLicense(
			applicationId, licenseId);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense>
			findByApplicationId(long applicationId) {

		return _applicationLicenseLocalService.findByApplicationId(
			applicationId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _applicationLicenseLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the application license with the primary key.
	 *
	 * @param licenseId the primary key of the application license
	 * @return the application license
	 * @throws PortalException if a application license with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense
			getApplicationLicense(long licenseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationLicenseLocalService.getApplicationLicense(licenseId);
	}

	/**
	 * Returns a range of all the application licenses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.ApplicationLicenseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application licenses
	 * @param end the upper bound of the range of application licenses (not inclusive)
	 * @return the range of application licenses
	 */
	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense>
			getApplicationLicenses(int start, int end) {

		return _applicationLicenseLocalService.getApplicationLicenses(
			start, end);
	}

	/**
	 * Returns the number of application licenses.
	 *
	 * @return the number of application licenses
	 */
	@Override
	public int getApplicationLicensesCount() {
		return _applicationLicenseLocalService.getApplicationLicensesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _applicationLicenseLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _applicationLicenseLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationLicenseLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the application license in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationLicenseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationLicense the application license
	 * @return the application license that was updated
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense
		updateApplicationLicense(
			com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense
				applicationLicense) {

		return _applicationLicenseLocalService.updateApplicationLicense(
			applicationLicense);
	}

	@Override
	public ApplicationLicenseLocalService getWrappedService() {
		return _applicationLicenseLocalService;
	}

	@Override
	public void setWrappedService(
		ApplicationLicenseLocalService applicationLicenseLocalService) {

		_applicationLicenseLocalService = applicationLicenseLocalService;
	}

	private ApplicationLicenseLocalService _applicationLicenseLocalService;

}