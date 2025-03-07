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
 * Provides a wrapper for {@link ComunicacionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ComunicacionLocalService
 * @generated
 */
public class ComunicacionLocalServiceWrapper
	implements ComunicacionLocalService,
			   ServiceWrapper<ComunicacionLocalService> {

	public ComunicacionLocalServiceWrapper(
		ComunicacionLocalService comunicacionLocalService) {

		_comunicacionLocalService = comunicacionLocalService;
	}

	/**
	 * Adds the comunicacion to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ComunicacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param comunicacion the comunicacion
	 * @return the comunicacion that was added
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comunicacion
		addComunicacion(
			com.canal.etico.liferay.portlet.canal.manager.model.Comunicacion
				comunicacion) {

		return _comunicacionLocalService.addComunicacion(comunicacion);
	}

	/**
	 * Creates a new comunicacion with the primary key. Does not add the comunicacion to the database.
	 *
	 * @param comunicacionId the primary key for the new comunicacion
	 * @return the new comunicacion
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comunicacion
		createComunicacion(long comunicacionId) {

		return _comunicacionLocalService.createComunicacion(comunicacionId);
	}

	/**
	 * Deletes the comunicacion from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ComunicacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param comunicacion the comunicacion
	 * @return the comunicacion that was removed
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comunicacion
		deleteComunicacion(
			com.canal.etico.liferay.portlet.canal.manager.model.Comunicacion
				comunicacion) {

		return _comunicacionLocalService.deleteComunicacion(comunicacion);
	}

	/**
	 * Deletes the comunicacion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ComunicacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param comunicacionId the primary key of the comunicacion
	 * @return the comunicacion that was removed
	 * @throws PortalException if a comunicacion with the primary key could not be found
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comunicacion
			deleteComunicacion(long comunicacionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _comunicacionLocalService.deleteComunicacion(comunicacionId);
	}

	@Override
	public void deleteComunicacionesFromDenuncia(long denunciaId) {
		_comunicacionLocalService.deleteComunicacionesFromDenuncia(denunciaId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _comunicacionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _comunicacionLocalService.dynamicQuery();
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

		return _comunicacionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.ComunicacionModelImpl</code>.
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

		return _comunicacionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.ComunicacionModelImpl</code>.
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

		return _comunicacionLocalService.dynamicQuery(
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

		return _comunicacionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _comunicacionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comunicacion
		fetchComunicacion(long comunicacionId) {

		return _comunicacionLocalService.fetchComunicacion(comunicacionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _comunicacionLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the comunicacion with the primary key.
	 *
	 * @param comunicacionId the primary key of the comunicacion
	 * @return the comunicacion
	 * @throws PortalException if a comunicacion with the primary key could not be found
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comunicacion
			getComunicacion(long comunicacionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _comunicacionLocalService.getComunicacion(comunicacionId);
	}

	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Comunicacion>
			getComunicacionesFromDenuncia(long denunciaId) {

		return _comunicacionLocalService.getComunicacionesFromDenuncia(
			denunciaId);
	}

	/**
	 * Returns a range of all the comunicacions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.ComunicacionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of comunicacions
	 * @param end the upper bound of the range of comunicacions (not inclusive)
	 * @return the range of comunicacions
	 */
	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Comunicacion>
			getComunicacions(int start, int end) {

		return _comunicacionLocalService.getComunicacions(start, end);
	}

	/**
	 * Returns the number of comunicacions.
	 *
	 * @return the number of comunicacions
	 */
	@Override
	public int getComunicacionsCount() {
		return _comunicacionLocalService.getComunicacionsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _comunicacionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _comunicacionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _comunicacionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the comunicacion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ComunicacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param comunicacion the comunicacion
	 * @return the comunicacion that was updated
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Comunicacion
		updateComunicacion(
			com.canal.etico.liferay.portlet.canal.manager.model.Comunicacion
				comunicacion) {

		return _comunicacionLocalService.updateComunicacion(comunicacion);
	}

	@Override
	public ComunicacionLocalService getWrappedService() {
		return _comunicacionLocalService;
	}

	@Override
	public void setWrappedService(
		ComunicacionLocalService comunicacionLocalService) {

		_comunicacionLocalService = comunicacionLocalService;
	}

	private ComunicacionLocalService _comunicacionLocalService;

}