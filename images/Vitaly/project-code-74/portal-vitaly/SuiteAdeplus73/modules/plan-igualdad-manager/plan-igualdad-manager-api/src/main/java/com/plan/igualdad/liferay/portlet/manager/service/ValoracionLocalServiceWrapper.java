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
 * Provides a wrapper for {@link ValoracionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ValoracionLocalService
 * @generated
 */
public class ValoracionLocalServiceWrapper
	implements ServiceWrapper<ValoracionLocalService>, ValoracionLocalService {

	public ValoracionLocalServiceWrapper(
		ValoracionLocalService valoracionLocalService) {

		_valoracionLocalService = valoracionLocalService;
	}

	/**
	 * Adds the valoracion to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ValoracionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param valoracion the valoracion
	 * @return the valoracion that was added
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Valoracion
		addValoracion(
			com.plan.igualdad.liferay.portlet.manager.model.Valoracion
				valoracion) {

		return _valoracionLocalService.addValoracion(valoracion);
	}

	@Override
	public int countByComp(long compId) {
		return _valoracionLocalService.countByComp(compId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _valoracionLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new valoracion with the primary key. Does not add the valoracion to the database.
	 *
	 * @param valoracionId the primary key for the new valoracion
	 * @return the new valoracion
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Valoracion
		createValoracion(long valoracionId) {

		return _valoracionLocalService.createValoracion(valoracionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _valoracionLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the valoracion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ValoracionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param valoracionId the primary key of the valoracion
	 * @return the valoracion that was removed
	 * @throws PortalException if a valoracion with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Valoracion
			deleteValoracion(long valoracionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _valoracionLocalService.deleteValoracion(valoracionId);
	}

	/**
	 * Deletes the valoracion from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ValoracionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param valoracion the valoracion
	 * @return the valoracion that was removed
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Valoracion
		deleteValoracion(
			com.plan.igualdad.liferay.portlet.manager.model.Valoracion
				valoracion) {

		return _valoracionLocalService.deleteValoracion(valoracion);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _valoracionLocalService.dynamicQuery();
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

		return _valoracionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.ValoracionModelImpl</code>.
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

		return _valoracionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.ValoracionModelImpl</code>.
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

		return _valoracionLocalService.dynamicQuery(
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

		return _valoracionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _valoracionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Valoracion
		fetchValoracion(long valoracionId) {

		return _valoracionLocalService.fetchValoracion(valoracionId);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Valoracion> findByComp(
			long compId) {

		return _valoracionLocalService.findByComp(compId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _valoracionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _valoracionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _valoracionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _valoracionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the valoracion with the primary key.
	 *
	 * @param valoracionId the primary key of the valoracion
	 * @return the valoracion
	 * @throws PortalException if a valoracion with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Valoracion
			getValoracion(long valoracionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _valoracionLocalService.getValoracion(valoracionId);
	}

	/**
	 * Returns a range of all the valoracions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.ValoracionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of valoracions
	 * @param end the upper bound of the range of valoracions (not inclusive)
	 * @return the range of valoracions
	 */
	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Valoracion>
			getValoracions(int start, int end) {

		return _valoracionLocalService.getValoracions(start, end);
	}

	/**
	 * Returns the number of valoracions.
	 *
	 * @return the number of valoracions
	 */
	@Override
	public int getValoracionsCount() {
		return _valoracionLocalService.getValoracionsCount();
	}

	/**
	 * Updates the valoracion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ValoracionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param valoracion the valoracion
	 * @return the valoracion that was updated
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Valoracion
		updateValoracion(
			com.plan.igualdad.liferay.portlet.manager.model.Valoracion
				valoracion) {

		return _valoracionLocalService.updateValoracion(valoracion);
	}

	@Override
	public ValoracionLocalService getWrappedService() {
		return _valoracionLocalService;
	}

	@Override
	public void setWrappedService(
		ValoracionLocalService valoracionLocalService) {

		_valoracionLocalService = valoracionLocalService;
	}

	private ValoracionLocalService _valoracionLocalService;

}