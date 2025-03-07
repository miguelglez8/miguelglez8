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
 * Provides a wrapper for {@link TemporalDataApiLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TemporalDataApiLocalService
 * @generated
 */
public class TemporalDataApiLocalServiceWrapper
	implements ServiceWrapper<TemporalDataApiLocalService>,
			   TemporalDataApiLocalService {

	public TemporalDataApiLocalServiceWrapper(
		TemporalDataApiLocalService temporalDataApiLocalService) {

		_temporalDataApiLocalService = temporalDataApiLocalService;
	}

	/**
	 * Adds the temporal data api to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TemporalDataApiLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param temporalDataApi the temporal data api
	 * @return the temporal data api that was added
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.TemporalDataApi
		addTemporalDataApi(
			com.adeplus.liferay.portlet.suite.manager.model.TemporalDataApi
				temporalDataApi) {

		return _temporalDataApiLocalService.addTemporalDataApi(temporalDataApi);
	}

	/**
	 * Creates a new temporal data api with the primary key. Does not add the temporal data api to the database.
	 *
	 * @param idDataTemporal the primary key for the new temporal data api
	 * @return the new temporal data api
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.TemporalDataApi
		createTemporalDataApi(long idDataTemporal) {

		return _temporalDataApiLocalService.createTemporalDataApi(
			idDataTemporal);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _temporalDataApiLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the temporal data api with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TemporalDataApiLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param idDataTemporal the primary key of the temporal data api
	 * @return the temporal data api that was removed
	 * @throws PortalException if a temporal data api with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.TemporalDataApi
			deleteTemporalDataApi(long idDataTemporal)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _temporalDataApiLocalService.deleteTemporalDataApi(
			idDataTemporal);
	}

	/**
	 * Deletes the temporal data api from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TemporalDataApiLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param temporalDataApi the temporal data api
	 * @return the temporal data api that was removed
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.TemporalDataApi
		deleteTemporalDataApi(
			com.adeplus.liferay.portlet.suite.manager.model.TemporalDataApi
				temporalDataApi) {

		return _temporalDataApiLocalService.deleteTemporalDataApi(
			temporalDataApi);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _temporalDataApiLocalService.dynamicQuery();
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

		return _temporalDataApiLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.TemporalDataApiModelImpl</code>.
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

		return _temporalDataApiLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.TemporalDataApiModelImpl</code>.
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

		return _temporalDataApiLocalService.dynamicQuery(
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

		return _temporalDataApiLocalService.dynamicQueryCount(dynamicQuery);
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

		return _temporalDataApiLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.TemporalDataApi
		fetchTemporalDataApi(long idDataTemporal) {

		return _temporalDataApiLocalService.fetchTemporalDataApi(
			idDataTemporal);
	}

	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.TemporalDataApi>
			findProcesado(int procesado) {

		return _temporalDataApiLocalService.findProcesado(procesado);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _temporalDataApiLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _temporalDataApiLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _temporalDataApiLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _temporalDataApiLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the temporal data api with the primary key.
	 *
	 * @param idDataTemporal the primary key of the temporal data api
	 * @return the temporal data api
	 * @throws PortalException if a temporal data api with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.TemporalDataApi
			getTemporalDataApi(long idDataTemporal)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _temporalDataApiLocalService.getTemporalDataApi(idDataTemporal);
	}

	/**
	 * Returns a range of all the temporal data apis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.TemporalDataApiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of temporal data apis
	 * @param end the upper bound of the range of temporal data apis (not inclusive)
	 * @return the range of temporal data apis
	 */
	@Override
	public java.util.List
		<com.adeplus.liferay.portlet.suite.manager.model.TemporalDataApi>
			getTemporalDataApis(int start, int end) {

		return _temporalDataApiLocalService.getTemporalDataApis(start, end);
	}

	/**
	 * Returns the number of temporal data apis.
	 *
	 * @return the number of temporal data apis
	 */
	@Override
	public int getTemporalDataApisCount() {
		return _temporalDataApiLocalService.getTemporalDataApisCount();
	}

	/**
	 * Updates the temporal data api in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TemporalDataApiLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param temporalDataApi the temporal data api
	 * @return the temporal data api that was updated
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.TemporalDataApi
		updateTemporalDataApi(
			com.adeplus.liferay.portlet.suite.manager.model.TemporalDataApi
				temporalDataApi) {

		return _temporalDataApiLocalService.updateTemporalDataApi(
			temporalDataApi);
	}

	@Override
	public TemporalDataApiLocalService getWrappedService() {
		return _temporalDataApiLocalService;
	}

	@Override
	public void setWrappedService(
		TemporalDataApiLocalService temporalDataApiLocalService) {

		_temporalDataApiLocalService = temporalDataApiLocalService;
	}

	private TemporalDataApiLocalService _temporalDataApiLocalService;

}