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
 * Provides a wrapper for {@link RespuestaLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RespuestaLocalService
 * @generated
 */
public class RespuestaLocalServiceWrapper
	implements RespuestaLocalService, ServiceWrapper<RespuestaLocalService> {

	public RespuestaLocalServiceWrapper(
		RespuestaLocalService respuestaLocalService) {

		_respuestaLocalService = respuestaLocalService;
	}

	/**
	 * Adds the respuesta to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RespuestaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param respuesta the respuesta
	 * @return the respuesta that was added
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Respuesta
		addRespuesta(
			com.plan.igualdad.liferay.portlet.manager.model.Respuesta
				respuesta) {

		return _respuestaLocalService.addRespuesta(respuesta);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _respuestaLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new respuesta with the primary key. Does not add the respuesta to the database.
	 *
	 * @param respuestaPK the primary key for the new respuesta
	 * @return the new respuesta
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Respuesta
		createRespuesta(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				RespuestaPK respuestaPK) {

		return _respuestaLocalService.createRespuesta(respuestaPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _respuestaLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the respuesta from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RespuestaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param respuesta the respuesta
	 * @return the respuesta that was removed
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Respuesta
		deleteRespuesta(
			com.plan.igualdad.liferay.portlet.manager.model.Respuesta
				respuesta) {

		return _respuestaLocalService.deleteRespuesta(respuesta);
	}

	/**
	 * Deletes the respuesta with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RespuestaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param respuestaPK the primary key of the respuesta
	 * @return the respuesta that was removed
	 * @throws PortalException if a respuesta with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Respuesta
			deleteRespuesta(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					RespuestaPK respuestaPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _respuestaLocalService.deleteRespuesta(respuestaPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _respuestaLocalService.dynamicQuery();
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

		return _respuestaLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.RespuestaModelImpl</code>.
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

		return _respuestaLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.RespuestaModelImpl</code>.
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

		return _respuestaLocalService.dynamicQuery(
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

		return _respuestaLocalService.dynamicQueryCount(dynamicQuery);
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

		return _respuestaLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Respuesta
		fetchRespuesta(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				RespuestaPK respuestaPK) {

		return _respuestaLocalService.fetchRespuesta(respuestaPK);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Respuesta
		fetchRespuestaBySeccion(long compId, long versionId, long seccionId) {

		return _respuestaLocalService.fetchRespuestaBySeccion(
			compId, versionId, seccionId);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Respuesta>
			fetchRespuestas(long compId, long versionId) {

		return _respuestaLocalService.fetchRespuestas(compId, versionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _respuestaLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _respuestaLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _respuestaLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _respuestaLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the respuesta with the primary key.
	 *
	 * @param respuestaPK the primary key of the respuesta
	 * @return the respuesta
	 * @throws PortalException if a respuesta with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Respuesta
			getRespuesta(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					RespuestaPK respuestaPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _respuestaLocalService.getRespuesta(respuestaPK);
	}

	/**
	 * Returns a range of all the respuestas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.RespuestaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of respuestas
	 * @param end the upper bound of the range of respuestas (not inclusive)
	 * @return the range of respuestas
	 */
	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Respuesta>
			getRespuestas(int start, int end) {

		return _respuestaLocalService.getRespuestas(start, end);
	}

	/**
	 * Returns the number of respuestas.
	 *
	 * @return the number of respuestas
	 */
	@Override
	public int getRespuestasCount() {
		return _respuestaLocalService.getRespuestasCount();
	}

	/**
	 * Updates the respuesta in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RespuestaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param respuesta the respuesta
	 * @return the respuesta that was updated
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Respuesta
		updateRespuesta(
			com.plan.igualdad.liferay.portlet.manager.model.Respuesta
				respuesta) {

		return _respuestaLocalService.updateRespuesta(respuesta);
	}

	@Override
	public RespuestaLocalService getWrappedService() {
		return _respuestaLocalService;
	}

	@Override
	public void setWrappedService(RespuestaLocalService respuestaLocalService) {
		_respuestaLocalService = respuestaLocalService;
	}

	private RespuestaLocalService _respuestaLocalService;

}