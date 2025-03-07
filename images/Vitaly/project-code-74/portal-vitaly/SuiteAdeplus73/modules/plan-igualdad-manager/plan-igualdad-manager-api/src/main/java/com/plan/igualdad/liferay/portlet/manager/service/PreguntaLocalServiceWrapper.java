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
 * Provides a wrapper for {@link PreguntaLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PreguntaLocalService
 * @generated
 */
public class PreguntaLocalServiceWrapper
	implements PreguntaLocalService, ServiceWrapper<PreguntaLocalService> {

	public PreguntaLocalServiceWrapper(
		PreguntaLocalService preguntaLocalService) {

		_preguntaLocalService = preguntaLocalService;
	}

	/**
	 * Adds the pregunta to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PreguntaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pregunta the pregunta
	 * @return the pregunta that was added
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Pregunta addPregunta(
		com.plan.igualdad.liferay.portlet.manager.model.Pregunta pregunta) {

		return _preguntaLocalService.addPregunta(pregunta);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _preguntaLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new pregunta with the primary key. Does not add the pregunta to the database.
	 *
	 * @param preguntaPK the primary key for the new pregunta
	 * @return the new pregunta
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Pregunta
		createPregunta(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				PreguntaPK preguntaPK) {

		return _preguntaLocalService.createPregunta(preguntaPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _preguntaLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the pregunta from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PreguntaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pregunta the pregunta
	 * @return the pregunta that was removed
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Pregunta
		deletePregunta(
			com.plan.igualdad.liferay.portlet.manager.model.Pregunta pregunta) {

		return _preguntaLocalService.deletePregunta(pregunta);
	}

	/**
	 * Deletes the pregunta with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PreguntaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param preguntaPK the primary key of the pregunta
	 * @return the pregunta that was removed
	 * @throws PortalException if a pregunta with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Pregunta
			deletePregunta(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					PreguntaPK preguntaPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _preguntaLocalService.deletePregunta(preguntaPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _preguntaLocalService.dynamicQuery();
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

		return _preguntaLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.PreguntaModelImpl</code>.
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

		return _preguntaLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.PreguntaModelImpl</code>.
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

		return _preguntaLocalService.dynamicQuery(
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

		return _preguntaLocalService.dynamicQueryCount(dynamicQuery);
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

		return _preguntaLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Pregunta
		fetchPregunta(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				PreguntaPK preguntaPK) {

		return _preguntaLocalService.fetchPregunta(preguntaPK);
	}

	@Override
	public java.util.Map
		<Long,
		 java.util.List
			 <com.plan.igualdad.liferay.portlet.manager.model.Pregunta>>
				fetchSecciones() {

		return _preguntaLocalService.fetchSecciones();
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Pregunta>
			findBySeccion(Long seccionId) {

		return _preguntaLocalService.findBySeccion(seccionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _preguntaLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _preguntaLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _preguntaLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _preguntaLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the pregunta with the primary key.
	 *
	 * @param preguntaPK the primary key of the pregunta
	 * @return the pregunta
	 * @throws PortalException if a pregunta with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Pregunta getPregunta(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				PreguntaPK preguntaPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _preguntaLocalService.getPregunta(preguntaPK);
	}

	/**
	 * Returns a range of all the preguntas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.PreguntaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of preguntas
	 * @param end the upper bound of the range of preguntas (not inclusive)
	 * @return the range of preguntas
	 */
	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Pregunta> getPreguntas(
			int start, int end) {

		return _preguntaLocalService.getPreguntas(start, end);
	}

	/**
	 * Returns the number of preguntas.
	 *
	 * @return the number of preguntas
	 */
	@Override
	public int getPreguntasCount() {
		return _preguntaLocalService.getPreguntasCount();
	}

	/**
	 * Updates the pregunta in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PreguntaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pregunta the pregunta
	 * @return the pregunta that was updated
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Pregunta
		updatePregunta(
			com.plan.igualdad.liferay.portlet.manager.model.Pregunta pregunta) {

		return _preguntaLocalService.updatePregunta(pregunta);
	}

	@Override
	public PreguntaLocalService getWrappedService() {
		return _preguntaLocalService;
	}

	@Override
	public void setWrappedService(PreguntaLocalService preguntaLocalService) {
		_preguntaLocalService = preguntaLocalService;
	}

	private PreguntaLocalService _preguntaLocalService;

}