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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Categoria. This utility wraps
 * <code>com.canal.etico.liferay.portlet.canal.manager.service.impl.CategoriaLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CategoriaLocalService
 * @generated
 */
public class CategoriaLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.canal.etico.liferay.portlet.canal.manager.service.impl.CategoriaLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static com.canal.etico.liferay.portlet.canal.manager.model.Categoria
		addCategoria(
			com.canal.etico.liferay.portlet.canal.manager.model.Categoria
				categoria) {

		return getService().addCategoria(categoria);
	}

	/**
	 * Creates a new categoria with the primary key. Does not add the categoria to the database.
	 *
	 * @param categoriaId the primary key for the new categoria
	 * @return the new categoria
	 */
	public static com.canal.etico.liferay.portlet.canal.manager.model.Categoria
		createCategoria(long categoriaId) {

		return getService().createCategoria(categoriaId);
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
	public static com.canal.etico.liferay.portlet.canal.manager.model.Categoria
		deleteCategoria(
			com.canal.etico.liferay.portlet.canal.manager.model.Categoria
				categoria) {

		return getService().deleteCategoria(categoria);
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
	public static com.canal.etico.liferay.portlet.canal.manager.model.Categoria
			deleteCategoria(long categoriaId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCategoria(categoriaId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.canal.etico.liferay.portlet.canal.manager.model.Categoria
		fetchCategoria(long categoriaId) {

		return getService().fetchCategoria(categoriaId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Categoria>
			getAllCategoriasActiveFromCompany(long compId) {

		return getService().getAllCategoriasActiveFromCompany(compId);
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Categoria>
			getAllCategoriasActiveFromCompanyByTipo(long compId, String tipo) {

		return getService().getAllCategoriasActiveFromCompanyByTipo(
			compId, tipo);
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Categoria>
			getAllCategoriasFromCompany(long compId) {

		return getService().getAllCategoriasFromCompany(compId);
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Categoria>
			getAllConsultasActiveFromCompany(long compId) {

		return getService().getAllConsultasActiveFromCompany(compId);
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Categoria>
			getAllDenunciasActiveFromCompany(long compId) {

		return getService().getAllDenunciasActiveFromCompany(compId);
	}

	/**
	 * Returns the categoria with the primary key.
	 *
	 * @param categoriaId the primary key of the categoria
	 * @return the categoria
	 * @throws PortalException if a categoria with the primary key could not be found
	 */
	public static com.canal.etico.liferay.portlet.canal.manager.model.Categoria
			getCategoria(long categoriaId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCategoria(categoriaId);
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
	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Categoria>
			getCategorias(int start, int end) {

		return getService().getCategorias(start, end);
	}

	/**
	 * Returns the number of categorias.
	 *
	 * @return the number of categorias
	 */
	public static int getCategoriasCount() {
		return getService().getCategoriasCount();
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Categoria>
			getCategoriesFromCompany(long compId) {

		return getService().getCategoriesFromCompany(compId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
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
	public static com.canal.etico.liferay.portlet.canal.manager.model.Categoria
		updateCategoria(
			com.canal.etico.liferay.portlet.canal.manager.model.Categoria
				categoria) {

		return getService().updateCategoria(categoria);
	}

	public static CategoriaLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CategoriaLocalService, CategoriaLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CategoriaLocalService.class);

		ServiceTracker<CategoriaLocalService, CategoriaLocalService>
			serviceTracker =
				new ServiceTracker
					<CategoriaLocalService, CategoriaLocalService>(
						bundle.getBundleContext(), CategoriaLocalService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}