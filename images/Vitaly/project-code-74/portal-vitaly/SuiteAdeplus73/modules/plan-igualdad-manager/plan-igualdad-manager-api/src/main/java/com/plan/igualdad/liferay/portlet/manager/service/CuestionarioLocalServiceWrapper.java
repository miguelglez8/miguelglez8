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
 * Provides a wrapper for {@link CuestionarioLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CuestionarioLocalService
 * @generated
 */
public class CuestionarioLocalServiceWrapper
	implements CuestionarioLocalService,
			   ServiceWrapper<CuestionarioLocalService> {

	public CuestionarioLocalServiceWrapper(
		CuestionarioLocalService cuestionarioLocalService) {

		_cuestionarioLocalService = cuestionarioLocalService;
	}

	/**
	 * Adds the cuestionario to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CuestionarioLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cuestionario the cuestionario
	 * @return the cuestionario that was added
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Cuestionario
		addCuestionario(
			com.plan.igualdad.liferay.portlet.manager.model.Cuestionario
				cuestionario) {

		return _cuestionarioLocalService.addCuestionario(cuestionario);
	}

	@Override
	public int countByComp(long compId) {
		return _cuestionarioLocalService.countByComp(compId);
	}

	/**
	 * Creates a new cuestionario with the primary key. Does not add the cuestionario to the database.
	 *
	 * @param cuestionarioId the primary key for the new cuestionario
	 * @return the new cuestionario
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Cuestionario
		createCuestionario(long cuestionarioId) {

		return _cuestionarioLocalService.createCuestionario(cuestionarioId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cuestionarioLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the cuestionario from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CuestionarioLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cuestionario the cuestionario
	 * @return the cuestionario that was removed
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Cuestionario
		deleteCuestionario(
			com.plan.igualdad.liferay.portlet.manager.model.Cuestionario
				cuestionario) {

		return _cuestionarioLocalService.deleteCuestionario(cuestionario);
	}

	/**
	 * Deletes the cuestionario with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CuestionarioLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cuestionarioId the primary key of the cuestionario
	 * @return the cuestionario that was removed
	 * @throws PortalException if a cuestionario with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Cuestionario
			deleteCuestionario(long cuestionarioId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cuestionarioLocalService.deleteCuestionario(cuestionarioId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cuestionarioLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cuestionarioLocalService.dynamicQuery();
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

		return _cuestionarioLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.CuestionarioModelImpl</code>.
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

		return _cuestionarioLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.CuestionarioModelImpl</code>.
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

		return _cuestionarioLocalService.dynamicQuery(
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

		return _cuestionarioLocalService.dynamicQueryCount(dynamicQuery);
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

		return _cuestionarioLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Cuestionario
		fetchCuestionario(long cuestionarioId) {

		return _cuestionarioLocalService.fetchCuestionario(cuestionarioId);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Cuestionario>
			findByComp(long compId) {

		return _cuestionarioLocalService.findByComp(compId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _cuestionarioLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the cuestionario with the primary key.
	 *
	 * @param cuestionarioId the primary key of the cuestionario
	 * @return the cuestionario
	 * @throws PortalException if a cuestionario with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Cuestionario
			getCuestionario(long cuestionarioId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cuestionarioLocalService.getCuestionario(cuestionarioId);
	}

	/**
	 * Returns a range of all the cuestionarios.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.CuestionarioModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cuestionarios
	 * @param end the upper bound of the range of cuestionarios (not inclusive)
	 * @return the range of cuestionarios
	 */
	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Cuestionario>
			getCuestionarios(int start, int end) {

		return _cuestionarioLocalService.getCuestionarios(start, end);
	}

	/**
	 * Returns the number of cuestionarios.
	 *
	 * @return the number of cuestionarios
	 */
	@Override
	public int getCuestionariosCount() {
		return _cuestionarioLocalService.getCuestionariosCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _cuestionarioLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cuestionarioLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cuestionarioLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the cuestionario in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CuestionarioLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cuestionario the cuestionario
	 * @return the cuestionario that was updated
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Cuestionario
		updateCuestionario(
			com.plan.igualdad.liferay.portlet.manager.model.Cuestionario
				cuestionario) {

		return _cuestionarioLocalService.updateCuestionario(cuestionario);
	}

	@Override
	public CuestionarioLocalService getWrappedService() {
		return _cuestionarioLocalService;
	}

	@Override
	public void setWrappedService(
		CuestionarioLocalService cuestionarioLocalService) {

		_cuestionarioLocalService = cuestionarioLocalService;
	}

	private CuestionarioLocalService _cuestionarioLocalService;

}