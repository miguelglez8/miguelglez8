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
 * Provides a wrapper for {@link CategoriaLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CategoriaLocalService
 * @generated
 */
public class CategoriaLocalServiceWrapper
	implements CategoriaLocalService, ServiceWrapper<CategoriaLocalService> {

	public CategoriaLocalServiceWrapper(
		CategoriaLocalService categoriaLocalService) {

		_categoriaLocalService = categoriaLocalService;
	}

	/**
	 * Adds the categoria to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CategoriaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param categoria the categoria
	 * @return the categoria that was added
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Categoria
		addCategoria(
			com.canal.etico.liferay.portlet.canal.manager.model.Categoria
				categoria) {

		return _categoriaLocalService.addCategoria(categoria);
	}

	/**
	 * Creates a new categoria with the primary key. Does not add the categoria to the database.
	 *
	 * @param categoriaId the primary key for the new categoria
	 * @return the new categoria
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Categoria
		createCategoria(long categoriaId) {

		return _categoriaLocalService.createCategoria(categoriaId);
	}

	/**
	 * Deletes the categoria from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CategoriaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param categoria the categoria
	 * @return the categoria that was removed
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Categoria
		deleteCategoria(
			com.canal.etico.liferay.portlet.canal.manager.model.Categoria
				categoria) {

		return _categoriaLocalService.deleteCategoria(categoria);
	}

	/**
	 * Deletes the categoria with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CategoriaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param categoriaId the primary key of the categoria
	 * @return the categoria that was removed
	 * @throws PortalException if a categoria with the primary key could not be found
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Categoria
			deleteCategoria(long categoriaId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _categoriaLocalService.deleteCategoria(categoriaId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _categoriaLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _categoriaLocalService.dynamicQuery();
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

		return _categoriaLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.CategoriaModelImpl</code>.
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

		return _categoriaLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.CategoriaModelImpl</code>.
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

		return _categoriaLocalService.dynamicQuery(
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

		return _categoriaLocalService.dynamicQueryCount(dynamicQuery);
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

		return _categoriaLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Categoria
		fetchCategoria(long categoriaId) {

		return _categoriaLocalService.fetchCategoria(categoriaId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _categoriaLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Categoria>
			getAllCategoriasActiveFromCompany(long compId) {

		return _categoriaLocalService.getAllCategoriasActiveFromCompany(compId);
	}

	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Categoria>
			getAllCategoriasActiveFromCompanyByTipo(long compId, String tipo) {

		return _categoriaLocalService.getAllCategoriasActiveFromCompanyByTipo(
			compId, tipo);
	}

	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Categoria>
			getAllCategoriasFromCompany(long compId) {

		return _categoriaLocalService.getAllCategoriasFromCompany(compId);
	}

	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Categoria>
			getAllConsultasActiveFromCompany(long compId) {

		return _categoriaLocalService.getAllConsultasActiveFromCompany(compId);
	}

	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Categoria>
			getAllDenunciasActiveFromCompany(long compId) {

		return _categoriaLocalService.getAllDenunciasActiveFromCompany(compId);
	}

	/**
	 * Returns the categoria with the primary key.
	 *
	 * @param categoriaId the primary key of the categoria
	 * @return the categoria
	 * @throws PortalException if a categoria with the primary key could not be found
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Categoria
			getCategoria(long categoriaId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _categoriaLocalService.getCategoria(categoriaId);
	}

	/**
	 * Returns a range of all the categorias.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.CategoriaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of categorias
	 * @param end the upper bound of the range of categorias (not inclusive)
	 * @return the range of categorias
	 */
	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Categoria>
			getCategorias(int start, int end) {

		return _categoriaLocalService.getCategorias(start, end);
	}

	/**
	 * Returns the number of categorias.
	 *
	 * @return the number of categorias
	 */
	@Override
	public int getCategoriasCount() {
		return _categoriaLocalService.getCategoriasCount();
	}

	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Categoria>
			getCategoriesFromCompany(long compId) {

		return _categoriaLocalService.getCategoriesFromCompany(compId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _categoriaLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _categoriaLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _categoriaLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the categoria in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CategoriaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param categoria the categoria
	 * @return the categoria that was updated
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Categoria
		updateCategoria(
			com.canal.etico.liferay.portlet.canal.manager.model.Categoria
				categoria) {

		return _categoriaLocalService.updateCategoria(categoria);
	}

	@Override
	public CategoriaLocalService getWrappedService() {
		return _categoriaLocalService;
	}

	@Override
	public void setWrappedService(CategoriaLocalService categoriaLocalService) {
		_categoriaLocalService = categoriaLocalService;
	}

	private CategoriaLocalService _categoriaLocalService;

}