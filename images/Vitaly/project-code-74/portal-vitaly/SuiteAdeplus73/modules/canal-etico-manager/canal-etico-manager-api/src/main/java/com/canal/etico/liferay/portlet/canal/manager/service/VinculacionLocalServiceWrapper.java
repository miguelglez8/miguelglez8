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
 * Provides a wrapper for {@link VinculacionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see VinculacionLocalService
 * @generated
 */
public class VinculacionLocalServiceWrapper
	implements ServiceWrapper<VinculacionLocalService>,
			   VinculacionLocalService {

	public VinculacionLocalServiceWrapper(
		VinculacionLocalService vinculacionLocalService) {

		_vinculacionLocalService = vinculacionLocalService;
	}

	/**
	 * Adds the vinculacion to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VinculacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param vinculacion the vinculacion
	 * @return the vinculacion that was added
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
		addVinculacion(
			com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
				vinculacion) {

		return _vinculacionLocalService.addVinculacion(vinculacion);
	}

	/**
	 * Creates a new vinculacion with the primary key. Does not add the vinculacion to the database.
	 *
	 * @param vinculacionId the primary key for the new vinculacion
	 * @return the new vinculacion
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
		createVinculacion(long vinculacionId) {

		return _vinculacionLocalService.createVinculacion(vinculacionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _vinculacionLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the vinculacion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VinculacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param vinculacionId the primary key of the vinculacion
	 * @return the vinculacion that was removed
	 * @throws PortalException if a vinculacion with the primary key could not be found
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
			deleteVinculacion(long vinculacionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _vinculacionLocalService.deleteVinculacion(vinculacionId);
	}

	/**
	 * Deletes the vinculacion from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VinculacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param vinculacion the vinculacion
	 * @return the vinculacion that was removed
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
		deleteVinculacion(
			com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
				vinculacion) {

		return _vinculacionLocalService.deleteVinculacion(vinculacion);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _vinculacionLocalService.dynamicQuery();
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

		return _vinculacionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.VinculacionModelImpl</code>.
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

		return _vinculacionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.VinculacionModelImpl</code>.
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

		return _vinculacionLocalService.dynamicQuery(
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

		return _vinculacionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _vinculacionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
		fetchVinculacion(long vinculacionId) {

		return _vinculacionLocalService.fetchVinculacion(vinculacionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _vinculacionLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion>
			getAllVinculacionesActiveFromCompany(long compId) {

		return _vinculacionLocalService.getAllVinculacionesActiveFromCompany(
			compId);
	}

	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion>
			getAllVinculacionesFromCompany(long compId) {

		return _vinculacionLocalService.getAllVinculacionesFromCompany(compId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _vinculacionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _vinculacionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _vinculacionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the vinculacion with the primary key.
	 *
	 * @param vinculacionId the primary key of the vinculacion
	 * @return the vinculacion
	 * @throws PortalException if a vinculacion with the primary key could not be found
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
			getVinculacion(long vinculacionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _vinculacionLocalService.getVinculacion(vinculacionId);
	}

	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion>
			getVinculacionesFromCompany(long compId) {

		return _vinculacionLocalService.getVinculacionesFromCompany(compId);
	}

	/**
	 * Returns a range of all the vinculacions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.VinculacionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of vinculacions
	 * @param end the upper bound of the range of vinculacions (not inclusive)
	 * @return the range of vinculacions
	 */
	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion>
			getVinculacions(int start, int end) {

		return _vinculacionLocalService.getVinculacions(start, end);
	}

	/**
	 * Returns the number of vinculacions.
	 *
	 * @return the number of vinculacions
	 */
	@Override
	public int getVinculacionsCount() {
		return _vinculacionLocalService.getVinculacionsCount();
	}

	/**
	 * Updates the vinculacion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VinculacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param vinculacion the vinculacion
	 * @return the vinculacion that was updated
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
		updateVinculacion(
			com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion
				vinculacion) {

		return _vinculacionLocalService.updateVinculacion(vinculacion);
	}

	@Override
	public VinculacionLocalService getWrappedService() {
		return _vinculacionLocalService;
	}

	@Override
	public void setWrappedService(
		VinculacionLocalService vinculacionLocalService) {

		_vinculacionLocalService = vinculacionLocalService;
	}

	private VinculacionLocalService _vinculacionLocalService;

}