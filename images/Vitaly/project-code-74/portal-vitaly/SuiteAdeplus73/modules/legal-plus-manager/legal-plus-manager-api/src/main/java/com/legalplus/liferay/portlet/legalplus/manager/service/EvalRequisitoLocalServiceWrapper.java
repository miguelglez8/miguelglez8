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
 * Provides a wrapper for {@link EvalRequisitoLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EvalRequisitoLocalService
 * @generated
 */
public class EvalRequisitoLocalServiceWrapper
	implements EvalRequisitoLocalService,
			   ServiceWrapper<EvalRequisitoLocalService> {

	public EvalRequisitoLocalServiceWrapper(
		EvalRequisitoLocalService evalRequisitoLocalService) {

		_evalRequisitoLocalService = evalRequisitoLocalService;
	}

	/**
	 * Adds the eval requisito to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvalRequisitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evalRequisito the eval requisito
	 * @return the eval requisito that was added
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
		addEvalRequisito(
			com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
				evalRequisito) {

		return _evalRequisitoLocalService.addEvalRequisito(evalRequisito);
	}

	/**
	 * Creates a new eval requisito with the primary key. Does not add the eval requisito to the database.
	 *
	 * @param evalRequisitoPK the primary key for the new eval requisito
	 * @return the new eval requisito
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
		createEvalRequisito(
			com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
				EvalRequisitoPK evalRequisitoPK) {

		return _evalRequisitoLocalService.createEvalRequisito(evalRequisitoPK);
	}

	/**
	 * Deletes the eval requisito from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvalRequisitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evalRequisito the eval requisito
	 * @return the eval requisito that was removed
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
		deleteEvalRequisito(
			com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
				evalRequisito) {

		return _evalRequisitoLocalService.deleteEvalRequisito(evalRequisito);
	}

	/**
	 * Deletes the eval requisito with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvalRequisitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evalRequisitoPK the primary key of the eval requisito
	 * @return the eval requisito that was removed
	 * @throws PortalException if a eval requisito with the primary key could not be found
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
			deleteEvalRequisito(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.EvalRequisitoPK evalRequisitoPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _evalRequisitoLocalService.deleteEvalRequisito(evalRequisitoPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _evalRequisitoLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _evalRequisitoLocalService.dynamicQuery();
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

		return _evalRequisitoLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.EvalRequisitoModelImpl</code>.
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

		return _evalRequisitoLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.EvalRequisitoModelImpl</code>.
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

		return _evalRequisitoLocalService.dynamicQuery(
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

		return _evalRequisitoLocalService.dynamicQueryCount(dynamicQuery);
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

		return _evalRequisitoLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
		fetchEvalRequisito(
			com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
				EvalRequisitoPK evalRequisitoPK) {

		return _evalRequisitoLocalService.fetchEvalRequisito(evalRequisitoPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _evalRequisitoLocalService.getActionableDynamicQuery();
	}

	@Override
	public String getCalendarioEvalRequisitos(Long compId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _evalRequisitoLocalService.getCalendarioEvalRequisitos(compId);
	}

	/**
	 * Returns the eval requisito with the primary key.
	 *
	 * @param evalRequisitoPK the primary key of the eval requisito
	 * @return the eval requisito
	 * @throws PortalException if a eval requisito with the primary key could not be found
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
			getEvalRequisito(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.EvalRequisitoPK evalRequisitoPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _evalRequisitoLocalService.getEvalRequisito(evalRequisitoPK);
	}

	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito>
			getEvalRequisitoByFechaComp(java.util.Date fecha, long compId) {

		return _evalRequisitoLocalService.getEvalRequisitoByFechaComp(
			fecha, compId);
	}

	/**
	 * Returns a range of all the eval requisitos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.EvalRequisitoModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of eval requisitos
	 * @param end the upper bound of the range of eval requisitos (not inclusive)
	 * @return the range of eval requisitos
	 */
	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito>
			getEvalRequisitos(int start, int end) {

		return _evalRequisitoLocalService.getEvalRequisitos(start, end);
	}

	/**
	 * Returns the number of eval requisitos.
	 *
	 * @return the number of eval requisitos
	 */
	@Override
	public int getEvalRequisitosCount() {
		return _evalRequisitoLocalService.getEvalRequisitosCount();
	}

	@Override
	public String getEvaluacionesRequisitos(Long compId, String legislacionId) {
		return _evalRequisitoLocalService.getEvaluacionesRequisitos(
			compId, legislacionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _evalRequisitoLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
		getLastEvalRequisito(
			Long compId, String legislacionId, String requisitoId) {

		return _evalRequisitoLocalService.getLastEvalRequisito(
			compId, legislacionId, requisitoId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _evalRequisitoLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito>
			getPeriodoQuincenalByFechaComp(long compId) {

		return _evalRequisitoLocalService.getPeriodoQuincenalByFechaComp(
			compId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _evalRequisitoLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public Integer isEvalRequisitoVersionCompleted(
		long compId, String legislacionId, long version) {

		return _evalRequisitoLocalService.isEvalRequisitoVersionCompleted(
			compId, legislacionId, version);
	}

	/**
	 * Updates the eval requisito in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvalRequisitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evalRequisito the eval requisito
	 * @return the eval requisito that was updated
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
		updateEvalRequisito(
			com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
				evalRequisito) {

		return _evalRequisitoLocalService.updateEvalRequisito(evalRequisito);
	}

	@Override
	public EvalRequisitoLocalService getWrappedService() {
		return _evalRequisitoLocalService;
	}

	@Override
	public void setWrappedService(
		EvalRequisitoLocalService evalRequisitoLocalService) {

		_evalRequisitoLocalService = evalRequisitoLocalService;
	}

	private EvalRequisitoLocalService _evalRequisitoLocalService;

}