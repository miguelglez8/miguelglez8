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
 * Provides a wrapper for {@link MedidaLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MedidaLocalService
 * @generated
 */
public class MedidaLocalServiceWrapper
	implements MedidaLocalService, ServiceWrapper<MedidaLocalService> {

	public MedidaLocalServiceWrapper(MedidaLocalService medidaLocalService) {
		_medidaLocalService = medidaLocalService;
	}

	/**
	 * Adds the medida to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MedidaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param medida the medida
	 * @return the medida that was added
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Medida addMedida(
		com.plan.igualdad.liferay.portlet.manager.model.Medida medida) {

		return _medidaLocalService.addMedida(medida);
	}

	@Override
	public int countVersionsMedida(long versionOriginalMedidaId) {
		return _medidaLocalService.countVersionsMedida(versionOriginalMedidaId);
	}

	/**
	 * Creates a new medida with the primary key. Does not add the medida to the database.
	 *
	 * @param medidaId the primary key for the new medida
	 * @return the new medida
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Medida createMedida(
		long medidaId) {

		return _medidaLocalService.createMedida(medidaId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _medidaLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the medida with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MedidaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param medidaId the primary key of the medida
	 * @return the medida that was removed
	 * @throws PortalException if a medida with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Medida deleteMedida(
			long medidaId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _medidaLocalService.deleteMedida(medidaId);
	}

	/**
	 * Deletes the medida from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MedidaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param medida the medida
	 * @return the medida that was removed
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Medida deleteMedida(
		com.plan.igualdad.liferay.portlet.manager.model.Medida medida) {

		return _medidaLocalService.deleteMedida(medida);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _medidaLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _medidaLocalService.dynamicQuery();
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

		return _medidaLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.MedidaModelImpl</code>.
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

		return _medidaLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.MedidaModelImpl</code>.
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

		return _medidaLocalService.dynamicQuery(
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

		return _medidaLocalService.dynamicQueryCount(dynamicQuery);
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

		return _medidaLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Medida fetchMedida(
		long medidaId) {

		return _medidaLocalService.fetchMedida(medidaId);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Medida> findByComp(
			long compId) {

		return _medidaLocalService.findByComp(compId);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Medida>
			findByCompVersion(long compId, long versionId) {

		return _medidaLocalService.findByCompVersion(compId, versionId);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Medida
		findMedidaPredefinida(long compId, String medidaPredefinida) {

		return _medidaLocalService.findMedidaPredefinida(
			compId, medidaPredefinida);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _medidaLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _medidaLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the medida with the primary key.
	 *
	 * @param medidaId the primary key of the medida
	 * @return the medida
	 * @throws PortalException if a medida with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Medida getMedida(
			long medidaId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _medidaLocalService.getMedida(medidaId);
	}

	/**
	 * Returns a range of all the medidas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.MedidaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of medidas
	 * @param end the upper bound of the range of medidas (not inclusive)
	 * @return the range of medidas
	 */
	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Medida> getMedidas(
			int start, int end) {

		return _medidaLocalService.getMedidas(start, end);
	}

	/**
	 * Returns the number of medidas.
	 *
	 * @return the number of medidas
	 */
	@Override
	public int getMedidasCount() {
		return _medidaLocalService.getMedidasCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _medidaLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _medidaLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the medida in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MedidaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param medida the medida
	 * @return the medida that was updated
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Medida updateMedida(
		com.plan.igualdad.liferay.portlet.manager.model.Medida medida) {

		return _medidaLocalService.updateMedida(medida);
	}

	@Override
	public MedidaLocalService getWrappedService() {
		return _medidaLocalService;
	}

	@Override
	public void setWrappedService(MedidaLocalService medidaLocalService) {
		_medidaLocalService = medidaLocalService;
	}

	private MedidaLocalService _medidaLocalService;

}