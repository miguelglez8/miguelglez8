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
 * Provides a wrapper for {@link ObservacionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ObservacionLocalService
 * @generated
 */
public class ObservacionLocalServiceWrapper
	implements ObservacionLocalService,
			   ServiceWrapper<ObservacionLocalService> {

	public ObservacionLocalServiceWrapper(
		ObservacionLocalService observacionLocalService) {

		_observacionLocalService = observacionLocalService;
	}

	/**
	 * Adds the observacion to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ObservacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param observacion the observacion
	 * @return the observacion that was added
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Observacion
		addObservacion(
			com.canal.etico.liferay.portlet.canal.manager.model.Observacion
				observacion) {

		return _observacionLocalService.addObservacion(observacion);
	}

	/**
	 * Creates a new observacion with the primary key. Does not add the observacion to the database.
	 *
	 * @param observacionId the primary key for the new observacion
	 * @return the new observacion
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Observacion
		createObservacion(long observacionId) {

		return _observacionLocalService.createObservacion(observacionId);
	}

	/**
	 * Deletes the observacion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ObservacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param observacionId the primary key of the observacion
	 * @return the observacion that was removed
	 * @throws PortalException if a observacion with the primary key could not be found
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Observacion
			deleteObservacion(long observacionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _observacionLocalService.deleteObservacion(observacionId);
	}

	/**
	 * Deletes the observacion from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ObservacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param observacion the observacion
	 * @return the observacion that was removed
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Observacion
		deleteObservacion(
			com.canal.etico.liferay.portlet.canal.manager.model.Observacion
				observacion) {

		return _observacionLocalService.deleteObservacion(observacion);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _observacionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _observacionLocalService.dynamicQuery();
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

		return _observacionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.ObservacionModelImpl</code>.
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

		return _observacionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.ObservacionModelImpl</code>.
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

		return _observacionLocalService.dynamicQuery(
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

		return _observacionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _observacionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Observacion
		fetchObservacion(long observacionId) {

		return _observacionLocalService.fetchObservacion(observacionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _observacionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _observacionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the observacion with the primary key.
	 *
	 * @param observacionId the primary key of the observacion
	 * @return the observacion
	 * @throws PortalException if a observacion with the primary key could not be found
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Observacion
			getObservacion(long observacionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _observacionLocalService.getObservacion(observacionId);
	}

	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Observacion>
			getObservacionesFromDenuncia(long denunciaId) {

		return _observacionLocalService.getObservacionesFromDenuncia(
			denunciaId);
	}

	/**
	 * Returns a range of all the observacions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.ObservacionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of observacions
	 * @param end the upper bound of the range of observacions (not inclusive)
	 * @return the range of observacions
	 */
	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Observacion>
			getObservacions(int start, int end) {

		return _observacionLocalService.getObservacions(start, end);
	}

	/**
	 * Returns the number of observacions.
	 *
	 * @return the number of observacions
	 */
	@Override
	public int getObservacionsCount() {
		return _observacionLocalService.getObservacionsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _observacionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _observacionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the observacion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ObservacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param observacion the observacion
	 * @return the observacion that was updated
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.Observacion
		updateObservacion(
			com.canal.etico.liferay.portlet.canal.manager.model.Observacion
				observacion) {

		return _observacionLocalService.updateObservacion(observacion);
	}

	@Override
	public ObservacionLocalService getWrappedService() {
		return _observacionLocalService;
	}

	@Override
	public void setWrappedService(
		ObservacionLocalService observacionLocalService) {

		_observacionLocalService = observacionLocalService;
	}

	private ObservacionLocalService _observacionLocalService;

}