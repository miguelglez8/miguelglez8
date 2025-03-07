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
 * Provides a wrapper for {@link EvaluacionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EvaluacionLocalService
 * @generated
 */
public class EvaluacionLocalServiceWrapper
	implements EvaluacionLocalService, ServiceWrapper<EvaluacionLocalService> {

	public EvaluacionLocalServiceWrapper(
		EvaluacionLocalService evaluacionLocalService) {

		_evaluacionLocalService = evaluacionLocalService;
	}

	/**
	 * Adds the evaluacion to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvaluacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evaluacion the evaluacion
	 * @return the evaluacion that was added
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Evaluacion
		addEvaluacion(
			com.plan.igualdad.liferay.portlet.manager.model.Evaluacion
				evaluacion) {

		return _evaluacionLocalService.addEvaluacion(evaluacion);
	}

	@Override
	public int countByComp(long compId) {
		return _evaluacionLocalService.countByComp(compId);
	}

	/**
	 * Creates a new evaluacion with the primary key. Does not add the evaluacion to the database.
	 *
	 * @param evaluacionId the primary key for the new evaluacion
	 * @return the new evaluacion
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Evaluacion
		createEvaluacion(long evaluacionId) {

		return _evaluacionLocalService.createEvaluacion(evaluacionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _evaluacionLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the evaluacion from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvaluacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evaluacion the evaluacion
	 * @return the evaluacion that was removed
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Evaluacion
		deleteEvaluacion(
			com.plan.igualdad.liferay.portlet.manager.model.Evaluacion
				evaluacion) {

		return _evaluacionLocalService.deleteEvaluacion(evaluacion);
	}

	/**
	 * Deletes the evaluacion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvaluacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evaluacionId the primary key of the evaluacion
	 * @return the evaluacion that was removed
	 * @throws PortalException if a evaluacion with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Evaluacion
			deleteEvaluacion(long evaluacionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _evaluacionLocalService.deleteEvaluacion(evaluacionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _evaluacionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _evaluacionLocalService.dynamicQuery();
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

		return _evaluacionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.EvaluacionModelImpl</code>.
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

		return _evaluacionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.EvaluacionModelImpl</code>.
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

		return _evaluacionLocalService.dynamicQuery(
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

		return _evaluacionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _evaluacionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Evaluacion
		fetchEvaluacion(long evaluacionId) {

		return _evaluacionLocalService.fetchEvaluacion(evaluacionId);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Evaluacion> findByComp(
			long compId) {

		return _evaluacionLocalService.findByComp(compId);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Evaluacion>
			findByCompVersion(long compId, long versionId) {

		return _evaluacionLocalService.findByCompVersion(compId, versionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _evaluacionLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the evaluacion with the primary key.
	 *
	 * @param evaluacionId the primary key of the evaluacion
	 * @return the evaluacion
	 * @throws PortalException if a evaluacion with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Evaluacion
			getEvaluacion(long evaluacionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _evaluacionLocalService.getEvaluacion(evaluacionId);
	}

	/**
	 * Returns a range of all the evaluacions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.EvaluacionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of evaluacions
	 * @param end the upper bound of the range of evaluacions (not inclusive)
	 * @return the range of evaluacions
	 */
	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Evaluacion>
			getEvaluacions(int start, int end) {

		return _evaluacionLocalService.getEvaluacions(start, end);
	}

	/**
	 * Returns the number of evaluacions.
	 *
	 * @return the number of evaluacions
	 */
	@Override
	public int getEvaluacionsCount() {
		return _evaluacionLocalService.getEvaluacionsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _evaluacionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _evaluacionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _evaluacionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the evaluacion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvaluacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evaluacion the evaluacion
	 * @return the evaluacion that was updated
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Evaluacion
		updateEvaluacion(
			com.plan.igualdad.liferay.portlet.manager.model.Evaluacion
				evaluacion) {

		return _evaluacionLocalService.updateEvaluacion(evaluacion);
	}

	@Override
	public EvaluacionLocalService getWrappedService() {
		return _evaluacionLocalService;
	}

	@Override
	public void setWrappedService(
		EvaluacionLocalService evaluacionLocalService) {

		_evaluacionLocalService = evaluacionLocalService;
	}

	private EvaluacionLocalService _evaluacionLocalService;

}