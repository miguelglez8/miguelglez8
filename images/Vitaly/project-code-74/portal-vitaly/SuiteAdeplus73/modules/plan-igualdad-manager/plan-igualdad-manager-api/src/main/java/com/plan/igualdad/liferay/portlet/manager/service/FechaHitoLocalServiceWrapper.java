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
 * Provides a wrapper for {@link FechaHitoLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see FechaHitoLocalService
 * @generated
 */
public class FechaHitoLocalServiceWrapper
	implements FechaHitoLocalService, ServiceWrapper<FechaHitoLocalService> {

	public FechaHitoLocalServiceWrapper(
		FechaHitoLocalService fechaHitoLocalService) {

		_fechaHitoLocalService = fechaHitoLocalService;
	}

	/**
	 * Adds the fecha hito to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FechaHitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fechaHito the fecha hito
	 * @return the fecha hito that was added
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.FechaHito
		addFechaHito(
			com.plan.igualdad.liferay.portlet.manager.model.FechaHito
				fechaHito) {

		return _fechaHitoLocalService.addFechaHito(fechaHito);
	}

	/**
	 * Creates a new fecha hito with the primary key. Does not add the fecha hito to the database.
	 *
	 * @param fechaHitoPK the primary key for the new fecha hito
	 * @return the new fecha hito
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.FechaHito
		createFechaHito(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				FechaHitoPK fechaHitoPK) {

		return _fechaHitoLocalService.createFechaHito(fechaHitoPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fechaHitoLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the fecha hito from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FechaHitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fechaHito the fecha hito
	 * @return the fecha hito that was removed
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.FechaHito
		deleteFechaHito(
			com.plan.igualdad.liferay.portlet.manager.model.FechaHito
				fechaHito) {

		return _fechaHitoLocalService.deleteFechaHito(fechaHito);
	}

	/**
	 * Deletes the fecha hito with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FechaHitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fechaHitoPK the primary key of the fecha hito
	 * @return the fecha hito that was removed
	 * @throws PortalException if a fecha hito with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.FechaHito
			deleteFechaHito(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					FechaHitoPK fechaHitoPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fechaHitoLocalService.deleteFechaHito(fechaHitoPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fechaHitoLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _fechaHitoLocalService.dynamicQuery();
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

		return _fechaHitoLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.FechaHitoModelImpl</code>.
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

		return _fechaHitoLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.FechaHitoModelImpl</code>.
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

		return _fechaHitoLocalService.dynamicQuery(
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

		return _fechaHitoLocalService.dynamicQueryCount(dynamicQuery);
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

		return _fechaHitoLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.FechaHito
		fetchFechaHito(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				FechaHitoPK fechaHitoPK) {

		return _fechaHitoLocalService.fetchFechaHito(fechaHitoPK);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.FechaHito>
			findByCompVersion(long compId, long versionId) {

		return _fechaHitoLocalService.findByCompVersion(compId, versionId);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.FechaHito>
			findByCompVersionEjecutados(long compId, long versionId) {

		return _fechaHitoLocalService.findByCompVersionEjecutados(
			compId, versionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _fechaHitoLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the fecha hito with the primary key.
	 *
	 * @param fechaHitoPK the primary key of the fecha hito
	 * @return the fecha hito
	 * @throws PortalException if a fecha hito with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.FechaHito
			getFechaHito(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					FechaHitoPK fechaHitoPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fechaHitoLocalService.getFechaHito(fechaHitoPK);
	}

	/**
	 * Returns a range of all the fecha hitos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.FechaHitoModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fecha hitos
	 * @param end the upper bound of the range of fecha hitos (not inclusive)
	 * @return the range of fecha hitos
	 */
	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.FechaHito>
			getFechaHitos(int start, int end) {

		return _fechaHitoLocalService.getFechaHitos(start, end);
	}

	/**
	 * Returns the number of fecha hitos.
	 *
	 * @return the number of fecha hitos
	 */
	@Override
	public int getFechaHitosCount() {
		return _fechaHitoLocalService.getFechaHitosCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _fechaHitoLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _fechaHitoLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fechaHitoLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the fecha hito in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FechaHitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fechaHito the fecha hito
	 * @return the fecha hito that was updated
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.FechaHito
		updateFechaHito(
			com.plan.igualdad.liferay.portlet.manager.model.FechaHito
				fechaHito) {

		return _fechaHitoLocalService.updateFechaHito(fechaHito);
	}

	@Override
	public FechaHitoLocalService getWrappedService() {
		return _fechaHitoLocalService;
	}

	@Override
	public void setWrappedService(FechaHitoLocalService fechaHitoLocalService) {
		_fechaHitoLocalService = fechaHitoLocalService;
	}

	private FechaHitoLocalService _fechaHitoLocalService;

}