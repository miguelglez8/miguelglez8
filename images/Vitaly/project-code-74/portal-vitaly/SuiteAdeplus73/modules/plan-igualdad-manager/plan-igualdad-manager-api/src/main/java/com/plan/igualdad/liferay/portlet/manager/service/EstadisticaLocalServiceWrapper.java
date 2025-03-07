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
 * Provides a wrapper for {@link EstadisticaLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EstadisticaLocalService
 * @generated
 */
public class EstadisticaLocalServiceWrapper
	implements EstadisticaLocalService,
			   ServiceWrapper<EstadisticaLocalService> {

	public EstadisticaLocalServiceWrapper(
		EstadisticaLocalService estadisticaLocalService) {

		_estadisticaLocalService = estadisticaLocalService;
	}

	/**
	 * Adds the estadistica to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EstadisticaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param estadistica the estadistica
	 * @return the estadistica that was added
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Estadistica
		addEstadistica(
			com.plan.igualdad.liferay.portlet.manager.model.Estadistica
				estadistica) {

		return _estadisticaLocalService.addEstadistica(estadistica);
	}

	/**
	 * Creates a new estadistica with the primary key. Does not add the estadistica to the database.
	 *
	 * @param estadisticaPK the primary key for the new estadistica
	 * @return the new estadistica
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Estadistica
		createEstadistica(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				EstadisticaPK estadisticaPK) {

		return _estadisticaLocalService.createEstadistica(estadisticaPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _estadisticaLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the estadistica from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EstadisticaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param estadistica the estadistica
	 * @return the estadistica that was removed
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Estadistica
		deleteEstadistica(
			com.plan.igualdad.liferay.portlet.manager.model.Estadistica
				estadistica) {

		return _estadisticaLocalService.deleteEstadistica(estadistica);
	}

	/**
	 * Deletes the estadistica with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EstadisticaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param estadisticaPK the primary key of the estadistica
	 * @return the estadistica that was removed
	 * @throws PortalException if a estadistica with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Estadistica
			deleteEstadistica(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					EstadisticaPK estadisticaPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _estadisticaLocalService.deleteEstadistica(estadisticaPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _estadisticaLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _estadisticaLocalService.dynamicQuery();
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

		return _estadisticaLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.EstadisticaModelImpl</code>.
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

		return _estadisticaLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.EstadisticaModelImpl</code>.
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

		return _estadisticaLocalService.dynamicQuery(
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

		return _estadisticaLocalService.dynamicQueryCount(dynamicQuery);
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

		return _estadisticaLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Estadistica
		fetchEstadistica(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				EstadisticaPK estadisticaPK) {

		return _estadisticaLocalService.fetchEstadistica(estadisticaPK);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Estadistica>
			fetchEstadistica(long compId, long versionId) {

		return _estadisticaLocalService.fetchEstadistica(compId, versionId);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Estadistica
		fetchEstadisticasBySection(
			long compId, long versionId, long seccionId) {

		return _estadisticaLocalService.fetchEstadisticasBySection(
			compId, versionId, seccionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _estadisticaLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the estadistica with the primary key.
	 *
	 * @param estadisticaPK the primary key of the estadistica
	 * @return the estadistica
	 * @throws PortalException if a estadistica with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Estadistica
			getEstadistica(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					EstadisticaPK estadisticaPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _estadisticaLocalService.getEstadistica(estadisticaPK);
	}

	/**
	 * Returns a range of all the estadisticas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.EstadisticaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of estadisticas
	 * @param end the upper bound of the range of estadisticas (not inclusive)
	 * @return the range of estadisticas
	 */
	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Estadistica>
			getEstadisticas(int start, int end) {

		return _estadisticaLocalService.getEstadisticas(start, end);
	}

	/**
	 * Returns the number of estadisticas.
	 *
	 * @return the number of estadisticas
	 */
	@Override
	public int getEstadisticasCount() {
		return _estadisticaLocalService.getEstadisticasCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _estadisticaLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _estadisticaLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _estadisticaLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the estadistica in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EstadisticaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param estadistica the estadistica
	 * @return the estadistica that was updated
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Estadistica
		updateEstadistica(
			com.plan.igualdad.liferay.portlet.manager.model.Estadistica
				estadistica) {

		return _estadisticaLocalService.updateEstadistica(estadistica);
	}

	@Override
	public EstadisticaLocalService getWrappedService() {
		return _estadisticaLocalService;
	}

	@Override
	public void setWrappedService(
		EstadisticaLocalService estadisticaLocalService) {

		_estadisticaLocalService = estadisticaLocalService;
	}

	private EstadisticaLocalService _estadisticaLocalService;

}