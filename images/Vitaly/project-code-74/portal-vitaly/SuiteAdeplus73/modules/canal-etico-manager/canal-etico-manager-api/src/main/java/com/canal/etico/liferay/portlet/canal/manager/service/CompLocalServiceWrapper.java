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

package com.canal.etico.liferay.portlet.canal.manager.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CompLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CompLocalService
 * @generated
 */
public class CompLocalServiceWrapper
	implements CompLocalService, ServiceWrapper<CompLocalService> {

	public CompLocalServiceWrapper(CompLocalService compLocalService) {
		_compLocalService = compLocalService;
	}

	/**
	 * Adds the comp to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param comp the comp
	 * @return the comp that was added
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comp addComp(
		com.canal.etico.liferay.portlet.canal.manager.model.Comp comp) {

		return _compLocalService.addComp(comp);
	}

	/**
	 * Creates a new comp with the primary key. Does not add the comp to the database.
	 *
	 * @param compId the primary key for the new comp
	 * @return the new comp
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comp createComp(
		long compId) {

		return _compLocalService.createComp(compId);
	}

	/**
	 * Deletes the comp from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param comp the comp
	 * @return the comp that was removed
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comp deleteComp(
		com.canal.etico.liferay.portlet.canal.manager.model.Comp comp) {

		return _compLocalService.deleteComp(comp);
	}

	/**
	 * Deletes the comp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param compId the primary key of the comp
	 * @return the comp that was removed
	 * @throws PortalException if a comp with the primary key could not be found
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comp deleteComp(
			long compId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _compLocalService.deleteComp(compId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _compLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _compLocalService.dynamicQuery();
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

		return _compLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.CompModelImpl</code>.
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

		return _compLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.CompModelImpl</code>.
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

		return _compLocalService.dynamicQuery(
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

		return _compLocalService.dynamicQueryCount(dynamicQuery);
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

		return _compLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comp fetchComp(
		long compId) {

		return _compLocalService.fetchComp(compId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _compLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the comp with the primary key.
	 *
	 * @param compId the primary key of the comp
	 * @return the comp
	 * @throws PortalException if a comp with the primary key could not be found
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comp getComp(
			long compId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _compLocalService.getComp(compId);
	}

	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Comp>
			getCompaniesByCIF(String cif) {

		return _compLocalService.getCompaniesByCIF(cif);
	}

	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comp
		getCompanyByCIF(String cif) {

		return _compLocalService.getCompanyByCIF(cif);
	}

	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comp
		getCompanyByFriendlyURL(String friendlyURL) {

		return _compLocalService.getCompanyByFriendlyURL(friendlyURL);
	}

	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comp
		getCompanyBySuiteCompId(long suiteCompId) {

		return _compLocalService.getCompanyBySuiteCompId(suiteCompId);
	}

	/**
	 * Returns a range of all the comps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.CompModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of comps
	 * @param end the upper bound of the range of comps (not inclusive)
	 * @return the range of comps
	 */
	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Comp> getComps(
			int start, int end) {

		return _compLocalService.getComps(start, end);
	}

	/**
	 * Returns the number of comps.
	 *
	 * @return the number of comps
	 */
	@Override
	public int getCompsCount() {
		return _compLocalService.getCompsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _compLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _compLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _compLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the comp in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CompLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param comp the comp
	 * @return the comp that was updated
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comp updateComp(
		com.canal.etico.liferay.portlet.canal.manager.model.Comp comp) {

		return _compLocalService.updateComp(comp);
	}

	@Override
	public CompLocalService getWrappedService() {
		return _compLocalService;
	}

	@Override
	public void setWrappedService(CompLocalService compLocalService) {
		_compLocalService = compLocalService;
	}

	private CompLocalService _compLocalService;

}