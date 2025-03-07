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
 * Provides a wrapper for {@link AuditadoDataApiLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AuditadoDataApiLocalService
 * @generated
 */
public class AuditadoDataApiLocalServiceWrapper
	implements AuditadoDataApiLocalService,
			   ServiceWrapper<AuditadoDataApiLocalService> {

	public AuditadoDataApiLocalServiceWrapper(
		AuditadoDataApiLocalService auditadoDataApiLocalService) {

		_auditadoDataApiLocalService = auditadoDataApiLocalService;
	}

	/**
	 * Adds the auditado data api to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuditadoDataApiLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param auditadoDataApi the auditado data api
	 * @return the auditado data api that was added
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.AuditadoDataApi
		addAuditadoDataApi(
			com.adeplus.liferay.portlet.suite.manager.model.AuditadoDataApi
				auditadoDataApi) {

		return _auditadoDataApiLocalService.addAuditadoDataApi(auditadoDataApi);
	}

	/**
	 * Creates a new auditado data api with the primary key. Does not add the auditado data api to the database.
	 *
	 * @param idDataTemporal the primary key for the new auditado data api
	 * @return the new auditado data api
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.AuditadoDataApi
		createAuditadoDataApi(long idDataTemporal) {

		return _auditadoDataApiLocalService.createAuditadoDataApi(
			idDataTemporal);
	}

	/**
	 * Deletes the auditado data api from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuditadoDataApiLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param auditadoDataApi the auditado data api
	 * @return the auditado data api that was removed
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.AuditadoDataApi
		deleteAuditadoDataApi(
			com.adeplus.liferay.portlet.suite.manager.model.AuditadoDataApi
				auditadoDataApi) {

		return _auditadoDataApiLocalService.deleteAuditadoDataApi(
			auditadoDataApi);
	}

	/**
	 * Deletes the auditado data api with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuditadoDataApiLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param idDataTemporal the primary key of the auditado data api
	 * @return the auditado data api that was removed
	 * @throws PortalException if a auditado data api with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.AuditadoDataApi
			deleteAuditadoDataApi(long idDataTemporal)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditadoDataApiLocalService.deleteAuditadoDataApi(
			idDataTemporal);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditadoDataApiLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _auditadoDataApiLocalService.dynamicQuery();
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

		return _auditadoDataApiLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.AuditadoDataApiModelImpl</code>.
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

		return _auditadoDataApiLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.AuditadoDataApiModelImpl</code>.
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

		return _auditadoDataApiLocalService.dynamicQuery(
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

		return _auditadoDataApiLocalService.dynamicQueryCount(dynamicQuery);
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

		return _auditadoDataApiLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.AuditadoDataApi
		fetchAuditadoDataApi(long idDataTemporal) {

		return _auditadoDataApiLocalService.fetchAuditadoDataApi(
			idDataTemporal);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.AuditadoDataApi>
			findAuditadoError() {

		return _auditadoDataApiLocalService.findAuditadoError();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _auditadoDataApiLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the auditado data api with the primary key.
	 *
	 * @param idDataTemporal the primary key of the auditado data api
	 * @return the auditado data api
	 * @throws PortalException if a auditado data api with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.AuditadoDataApi
			getAuditadoDataApi(long idDataTemporal)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditadoDataApiLocalService.getAuditadoDataApi(idDataTemporal);
	}

	/**
	 * Returns a range of all the auditado data apis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.AuditadoDataApiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of auditado data apis
	 * @param end the upper bound of the range of auditado data apis (not inclusive)
	 * @return the range of auditado data apis
	 */
	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.AuditadoDataApi>
			getAuditadoDataApis(int start, int end) {

		return _auditadoDataApiLocalService.getAuditadoDataApis(start, end);
	}

	/**
	 * Returns the number of auditado data apis.
	 *
	 * @return the number of auditado data apis
	 */
	@Override
	public int getAuditadoDataApisCount() {
		return _auditadoDataApiLocalService.getAuditadoDataApisCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _auditadoDataApiLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _auditadoDataApiLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditadoDataApiLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the auditado data api in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuditadoDataApiLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param auditadoDataApi the auditado data api
	 * @return the auditado data api that was updated
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.AuditadoDataApi
		updateAuditadoDataApi(
			com.adeplus.liferay.portlet.suite.manager.model.AuditadoDataApi
				auditadoDataApi) {

		return _auditadoDataApiLocalService.updateAuditadoDataApi(
			auditadoDataApi);
	}

	@Override
	public AuditadoDataApiLocalService getWrappedService() {
		return _auditadoDataApiLocalService;
	}

	@Override
	public void setWrappedService(
		AuditadoDataApiLocalService auditadoDataApiLocalService) {

		_auditadoDataApiLocalService = auditadoDataApiLocalService;
	}

	private AuditadoDataApiLocalService _auditadoDataApiLocalService;

}