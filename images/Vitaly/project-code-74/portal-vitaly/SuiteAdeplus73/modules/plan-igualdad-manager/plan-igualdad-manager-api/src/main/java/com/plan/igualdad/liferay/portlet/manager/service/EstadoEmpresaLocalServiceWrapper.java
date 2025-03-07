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
 * Provides a wrapper for {@link EstadoEmpresaLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EstadoEmpresaLocalService
 * @generated
 */
public class EstadoEmpresaLocalServiceWrapper
	implements EstadoEmpresaLocalService,
			   ServiceWrapper<EstadoEmpresaLocalService> {

	public EstadoEmpresaLocalServiceWrapper(
		EstadoEmpresaLocalService estadoEmpresaLocalService) {

		_estadoEmpresaLocalService = estadoEmpresaLocalService;
	}

	/**
	 * Adds the estado empresa to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EstadoEmpresaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param estadoEmpresa the estado empresa
	 * @return the estado empresa that was added
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.EstadoEmpresa
		addEstadoEmpresa(
			com.plan.igualdad.liferay.portlet.manager.model.EstadoEmpresa
				estadoEmpresa) {

		return _estadoEmpresaLocalService.addEstadoEmpresa(estadoEmpresa);
	}

	/**
	 * Creates a new estado empresa with the primary key. Does not add the estado empresa to the database.
	 *
	 * @param compId the primary key for the new estado empresa
	 * @return the new estado empresa
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.EstadoEmpresa
		createEstadoEmpresa(long compId) {

		return _estadoEmpresaLocalService.createEstadoEmpresa(compId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _estadoEmpresaLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the estado empresa from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EstadoEmpresaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param estadoEmpresa the estado empresa
	 * @return the estado empresa that was removed
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.EstadoEmpresa
		deleteEstadoEmpresa(
			com.plan.igualdad.liferay.portlet.manager.model.EstadoEmpresa
				estadoEmpresa) {

		return _estadoEmpresaLocalService.deleteEstadoEmpresa(estadoEmpresa);
	}

	/**
	 * Deletes the estado empresa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EstadoEmpresaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param compId the primary key of the estado empresa
	 * @return the estado empresa that was removed
	 * @throws PortalException if a estado empresa with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.EstadoEmpresa
			deleteEstadoEmpresa(long compId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _estadoEmpresaLocalService.deleteEstadoEmpresa(compId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _estadoEmpresaLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _estadoEmpresaLocalService.dynamicQuery();
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

		return _estadoEmpresaLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.EstadoEmpresaModelImpl</code>.
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

		return _estadoEmpresaLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.EstadoEmpresaModelImpl</code>.
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

		return _estadoEmpresaLocalService.dynamicQuery(
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

		return _estadoEmpresaLocalService.dynamicQueryCount(dynamicQuery);
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

		return _estadoEmpresaLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.EstadoEmpresa
		fetchEstadoEmpresa(long compId) {

		return _estadoEmpresaLocalService.fetchEstadoEmpresa(compId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _estadoEmpresaLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the estado empresa with the primary key.
	 *
	 * @param compId the primary key of the estado empresa
	 * @return the estado empresa
	 * @throws PortalException if a estado empresa with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.EstadoEmpresa
			getEstadoEmpresa(long compId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _estadoEmpresaLocalService.getEstadoEmpresa(compId);
	}

	/**
	 * Returns a range of all the estado empresas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.EstadoEmpresaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of estado empresas
	 * @param end the upper bound of the range of estado empresas (not inclusive)
	 * @return the range of estado empresas
	 */
	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.EstadoEmpresa>
			getEstadoEmpresas(int start, int end) {

		return _estadoEmpresaLocalService.getEstadoEmpresas(start, end);
	}

	/**
	 * Returns the number of estado empresas.
	 *
	 * @return the number of estado empresas
	 */
	@Override
	public int getEstadoEmpresasCount() {
		return _estadoEmpresaLocalService.getEstadoEmpresasCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _estadoEmpresaLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _estadoEmpresaLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _estadoEmpresaLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the estado empresa in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EstadoEmpresaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param estadoEmpresa the estado empresa
	 * @return the estado empresa that was updated
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.EstadoEmpresa
		updateEstadoEmpresa(
			com.plan.igualdad.liferay.portlet.manager.model.EstadoEmpresa
				estadoEmpresa) {

		return _estadoEmpresaLocalService.updateEstadoEmpresa(estadoEmpresa);
	}

	@Override
	public EstadoEmpresaLocalService getWrappedService() {
		return _estadoEmpresaLocalService;
	}

	@Override
	public void setWrappedService(
		EstadoEmpresaLocalService estadoEmpresaLocalService) {

		_estadoEmpresaLocalService = estadoEmpresaLocalService;
	}

	private EstadoEmpresaLocalService _estadoEmpresaLocalService;

}