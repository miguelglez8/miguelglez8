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

package com.plan.igualdad.liferay.portlet.manager.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link InformesLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see InformesLocalService
 * @generated
 */
public class InformesLocalServiceWrapper
	implements InformesLocalService, ServiceWrapper<InformesLocalService> {

	public InformesLocalServiceWrapper(
		InformesLocalService informesLocalService) {

		_informesLocalService = informesLocalService;
	}

	/**
	 * Adds the informes to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect InformesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param informes the informes
	 * @return the informes that was added
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Informes addInformes(
		com.plan.igualdad.liferay.portlet.manager.model.Informes informes) {

		return _informesLocalService.addInformes(informes);
	}

	@Override
	public int countVersionsInforme(
		long compId, long versionId, String tipoInforme) {

		return _informesLocalService.countVersionsInforme(
			compId, versionId, tipoInforme);
	}

	/**
	 * Creates a new informes with the primary key. Does not add the informes to the database.
	 *
	 * @param informeId the primary key for the new informes
	 * @return the new informes
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Informes
		createInformes(long informeId) {

		return _informesLocalService.createInformes(informeId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _informesLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the informes from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect InformesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param informes the informes
	 * @return the informes that was removed
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Informes
		deleteInformes(
			com.plan.igualdad.liferay.portlet.manager.model.Informes informes) {

		return _informesLocalService.deleteInformes(informes);
	}

	/**
	 * Deletes the informes with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect InformesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param informeId the primary key of the informes
	 * @return the informes that was removed
	 * @throws PortalException if a informes with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Informes
			deleteInformes(long informeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _informesLocalService.deleteInformes(informeId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _informesLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _informesLocalService.dynamicQuery();
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

		return _informesLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.InformesModelImpl</code>.
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

		return _informesLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.InformesModelImpl</code>.
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

		return _informesLocalService.dynamicQuery(
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

		return _informesLocalService.dynamicQueryCount(dynamicQuery);
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

		return _informesLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Informes
		fetchInformes(long informeId) {

		return _informesLocalService.fetchInformes(informeId);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Informes>
			findInformesByComp(long compId) {

		return _informesLocalService.findInformesByComp(compId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _informesLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _informesLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the informes with the primary key.
	 *
	 * @param informeId the primary key of the informes
	 * @return the informes
	 * @throws PortalException if a informes with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Informes getInformes(
			long informeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _informesLocalService.getInformes(informeId);
	}

	/**
	 * Returns a range of all the informeses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.InformesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of informeses
	 * @param end the upper bound of the range of informeses (not inclusive)
	 * @return the range of informeses
	 */
	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Informes>
			getInformeses(int start, int end) {

		return _informesLocalService.getInformeses(start, end);
	}

	/**
	 * Returns the number of informeses.
	 *
	 * @return the number of informeses
	 */
	@Override
	public int getInformesesCount() {
		return _informesLocalService.getInformesesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _informesLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _informesLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the informes in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect InformesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param informes the informes
	 * @return the informes that was updated
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Informes
		updateInformes(
			com.plan.igualdad.liferay.portlet.manager.model.Informes informes) {

		return _informesLocalService.updateInformes(informes);
	}

	@Override
	public InformesLocalService getWrappedService() {
		return _informesLocalService;
	}

	@Override
	public void setWrappedService(InformesLocalService informesLocalService) {
		_informesLocalService = informesLocalService;
	}

	private InformesLocalService _informesLocalService;

}