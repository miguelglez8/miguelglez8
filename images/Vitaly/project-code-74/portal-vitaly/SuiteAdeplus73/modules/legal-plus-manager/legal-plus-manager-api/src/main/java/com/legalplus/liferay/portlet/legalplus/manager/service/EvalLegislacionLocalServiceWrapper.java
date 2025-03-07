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

package com.legalplus.liferay.portlet.legalplus.manager.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EvalLegislacionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EvalLegislacionLocalService
 * @generated
 */
public class EvalLegislacionLocalServiceWrapper
	implements EvalLegislacionLocalService,
			   ServiceWrapper<EvalLegislacionLocalService> {

	public EvalLegislacionLocalServiceWrapper(
		EvalLegislacionLocalService evalLegislacionLocalService) {

		_evalLegislacionLocalService = evalLegislacionLocalService;
	}

	/**
	 * Adds the eval legislacion to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvalLegislacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evalLegislacion the eval legislacion
	 * @return the eval legislacion that was added
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
		addEvalLegislacion(
			com.legalplus.liferay.portlet.legalplus.manager.model.
				EvalLegislacion evalLegislacion) {

		return _evalLegislacionLocalService.addEvalLegislacion(evalLegislacion);
	}

	/**
	 * Creates a new eval legislacion with the primary key. Does not add the eval legislacion to the database.
	 *
	 * @param evalLegislacionPK the primary key for the new eval legislacion
	 * @return the new eval legislacion
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
		createEvalLegislacion(
			com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
				EvalLegislacionPK evalLegislacionPK) {

		return _evalLegislacionLocalService.createEvalLegislacion(
			evalLegislacionPK);
	}

	/**
	 * Deletes the eval legislacion from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvalLegislacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evalLegislacion the eval legislacion
	 * @return the eval legislacion that was removed
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
		deleteEvalLegislacion(
			com.legalplus.liferay.portlet.legalplus.manager.model.
				EvalLegislacion evalLegislacion) {

		return _evalLegislacionLocalService.deleteEvalLegislacion(
			evalLegislacion);
	}

	/**
	 * Deletes the eval legislacion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvalLegislacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evalLegislacionPK the primary key of the eval legislacion
	 * @return the eval legislacion that was removed
	 * @throws PortalException if a eval legislacion with the primary key could not be found
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
			deleteEvalLegislacion(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.EvalLegislacionPK evalLegislacionPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _evalLegislacionLocalService.deleteEvalLegislacion(
			evalLegislacionPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _evalLegislacionLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _evalLegislacionLocalService.dynamicQuery();
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

		return _evalLegislacionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.EvalLegislacionModelImpl</code>.
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

		return _evalLegislacionLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.EvalLegislacionModelImpl</code>.
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

		return _evalLegislacionLocalService.dynamicQuery(
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

		return _evalLegislacionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _evalLegislacionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
		fetchEvalLegislacion(
			com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
				EvalLegislacionPK evalLegislacionPK) {

		return _evalLegislacionLocalService.fetchEvalLegislacion(
			evalLegislacionPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _evalLegislacionLocalService.getActionableDynamicQuery();
	}

	@Override
	public String getCalendarioEvalLegislaciones(Long compId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _evalLegislacionLocalService.getCalendarioEvalLegislaciones(
			compId);
	}

	/**
	 * Returns the eval legislacion with the primary key.
	 *
	 * @param evalLegislacionPK the primary key of the eval legislacion
	 * @return the eval legislacion
	 * @throws PortalException if a eval legislacion with the primary key could not be found
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
			getEvalLegislacion(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.EvalLegislacionPK evalLegislacionPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _evalLegislacionLocalService.getEvalLegislacion(
			evalLegislacionPK);
	}

	/**
	 * Returns a range of all the eval legislacions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.EvalLegislacionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of eval legislacions
	 * @param end the upper bound of the range of eval legislacions (not inclusive)
	 * @return the range of eval legislacions
	 */
	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion>
			getEvalLegislacions(int start, int end) {

		return _evalLegislacionLocalService.getEvalLegislacions(start, end);
	}

	/**
	 * Returns the number of eval legislacions.
	 *
	 * @return the number of eval legislacions
	 */
	@Override
	public int getEvalLegislacionsCount() {
		return _evalLegislacionLocalService.getEvalLegislacionsCount();
	}

	@Override
	public String getEvaluacionesLegislacion(
		Long compId, String legislacionId) {

		return _evalLegislacionLocalService.getEvaluacionesLegislacion(
			compId, legislacionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _evalLegislacionLocalService.
			getIndexableActionableDynamicQuery();
	}

	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
		getLastEvalLegislacion(Long compId, String legislacionId) {

		return _evalLegislacionLocalService.getLastEvalLegislacion(
			compId, legislacionId);
	}

	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
		getLastEvalLegislacionCumplimentada(Long compId, String legislacionId) {

		return _evalLegislacionLocalService.getLastEvalLegislacionCumplimentada(
			compId, legislacionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _evalLegislacionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _evalLegislacionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the eval legislacion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvalLegislacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evalLegislacion the eval legislacion
	 * @return the eval legislacion that was updated
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion
		updateEvalLegislacion(
			com.legalplus.liferay.portlet.legalplus.manager.model.
				EvalLegislacion evalLegislacion) {

		return _evalLegislacionLocalService.updateEvalLegislacion(
			evalLegislacion);
	}

	@Override
	public EvalLegislacionLocalService getWrappedService() {
		return _evalLegislacionLocalService;
	}

	@Override
	public void setWrappedService(
		EvalLegislacionLocalService evalLegislacionLocalService) {

		_evalLegislacionLocalService = evalLegislacionLocalService;
	}

	private EvalLegislacionLocalService _evalLegislacionLocalService;

}