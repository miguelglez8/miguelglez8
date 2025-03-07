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
 * Provides a wrapper for {@link PuestoTrabajoLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PuestoTrabajoLocalService
 * @generated
 */
public class PuestoTrabajoLocalServiceWrapper
	implements PuestoTrabajoLocalService,
			   ServiceWrapper<PuestoTrabajoLocalService> {

	public PuestoTrabajoLocalServiceWrapper(
		PuestoTrabajoLocalService puestoTrabajoLocalService) {

		_puestoTrabajoLocalService = puestoTrabajoLocalService;
	}

	/**
	 * Adds the puesto trabajo to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PuestoTrabajoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param puestoTrabajo the puesto trabajo
	 * @return the puesto trabajo that was added
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
		addPuestoTrabajo(
			com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
				puestoTrabajo) {

		return _puestoTrabajoLocalService.addPuestoTrabajo(puestoTrabajo);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _puestoTrabajoLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new puesto trabajo with the primary key. Does not add the puesto trabajo to the database.
	 *
	 * @param puestoTrabajoPK the primary key for the new puesto trabajo
	 * @return the new puesto trabajo
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
		createPuestoTrabajo(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				PuestoTrabajoPK puestoTrabajoPK) {

		return _puestoTrabajoLocalService.createPuestoTrabajo(puestoTrabajoPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _puestoTrabajoLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the puesto trabajo from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PuestoTrabajoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param puestoTrabajo the puesto trabajo
	 * @return the puesto trabajo that was removed
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
		deletePuestoTrabajo(
			com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
				puestoTrabajo) {

		return _puestoTrabajoLocalService.deletePuestoTrabajo(puestoTrabajo);
	}

	/**
	 * Deletes the puesto trabajo with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PuestoTrabajoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param puestoTrabajoPK the primary key of the puesto trabajo
	 * @return the puesto trabajo that was removed
	 * @throws PortalException if a puesto trabajo with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
			deletePuestoTrabajo(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					PuestoTrabajoPK puestoTrabajoPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _puestoTrabajoLocalService.deletePuestoTrabajo(puestoTrabajoPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _puestoTrabajoLocalService.dynamicQuery();
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

		return _puestoTrabajoLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.PuestoTrabajoModelImpl</code>.
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

		return _puestoTrabajoLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.PuestoTrabajoModelImpl</code>.
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

		return _puestoTrabajoLocalService.dynamicQuery(
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

		return _puestoTrabajoLocalService.dynamicQueryCount(dynamicQuery);
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

		return _puestoTrabajoLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
		fetchPuestoTrabajo(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				PuestoTrabajoPK puestoTrabajoPK) {

		return _puestoTrabajoLocalService.fetchPuestoTrabajo(puestoTrabajoPK);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo>
			findByPuestoArea(long compId, long versionId, long areaId) {

		return _puestoTrabajoLocalService.findByPuestoArea(
			compId, versionId, areaId);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo>
			findByPuestoResponsabilidad(long compId, long versionId) {

		return _puestoTrabajoLocalService.findByPuestoResponsabilidad(
			compId, versionId);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo>
			findPuestoTrabajo(long compId, long versionId) {

		return _puestoTrabajoLocalService.findPuestoTrabajo(compId, versionId);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo>
			findPuestoTrabajoActivo(long compId, long versionId) {

		return _puestoTrabajoLocalService.findPuestoTrabajoActivo(
			compId, versionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _puestoTrabajoLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _puestoTrabajoLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _puestoTrabajoLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _puestoTrabajoLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the puesto trabajo with the primary key.
	 *
	 * @param puestoTrabajoPK the primary key of the puesto trabajo
	 * @return the puesto trabajo
	 * @throws PortalException if a puesto trabajo with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
			getPuestoTrabajo(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					PuestoTrabajoPK puestoTrabajoPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _puestoTrabajoLocalService.getPuestoTrabajo(puestoTrabajoPK);
	}

	/**
	 * Returns a range of all the puesto trabajos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.PuestoTrabajoModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of puesto trabajos
	 * @param end the upper bound of the range of puesto trabajos (not inclusive)
	 * @return the range of puesto trabajos
	 */
	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo>
			getPuestoTrabajos(int start, int end) {

		return _puestoTrabajoLocalService.getPuestoTrabajos(start, end);
	}

	/**
	 * Returns the number of puesto trabajos.
	 *
	 * @return the number of puesto trabajos
	 */
	@Override
	public int getPuestoTrabajosCount() {
		return _puestoTrabajoLocalService.getPuestoTrabajosCount();
	}

	/**
	 * Updates the puesto trabajo in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PuestoTrabajoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param puestoTrabajo the puesto trabajo
	 * @return the puesto trabajo that was updated
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
		updatePuestoTrabajo(
			com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo
				puestoTrabajo) {

		return _puestoTrabajoLocalService.updatePuestoTrabajo(puestoTrabajo);
	}

	@Override
	public PuestoTrabajoLocalService getWrappedService() {
		return _puestoTrabajoLocalService;
	}

	@Override
	public void setWrappedService(
		PuestoTrabajoLocalService puestoTrabajoLocalService) {

		_puestoTrabajoLocalService = puestoTrabajoLocalService;
	}

	private PuestoTrabajoLocalService _puestoTrabajoLocalService;

}