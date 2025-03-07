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
 * Provides a wrapper for {@link AyuntamientoLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AyuntamientoLocalService
 * @generated
 */
public class AyuntamientoLocalServiceWrapper
	implements AyuntamientoLocalService,
			   ServiceWrapper<AyuntamientoLocalService> {

	public AyuntamientoLocalServiceWrapper(
		AyuntamientoLocalService ayuntamientoLocalService) {

		_ayuntamientoLocalService = ayuntamientoLocalService;
	}

	/**
	 * Adds the ayuntamiento to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AyuntamientoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ayuntamiento the ayuntamiento
	 * @return the ayuntamiento that was added
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento
		addAyuntamiento(
			com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento
				ayuntamiento) {

		return _ayuntamientoLocalService.addAyuntamiento(ayuntamiento);
	}

	/**
	 * Creates a new ayuntamiento with the primary key. Does not add the ayuntamiento to the database.
	 *
	 * @param ayuntamientoPK the primary key for the new ayuntamiento
	 * @return the new ayuntamiento
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento
		createAyuntamiento(
			com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
				AyuntamientoPK ayuntamientoPK) {

		return _ayuntamientoLocalService.createAyuntamiento(ayuntamientoPK);
	}

	/**
	 * Deletes the ayuntamiento from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AyuntamientoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ayuntamiento the ayuntamiento
	 * @return the ayuntamiento that was removed
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento
		deleteAyuntamiento(
			com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento
				ayuntamiento) {

		return _ayuntamientoLocalService.deleteAyuntamiento(ayuntamiento);
	}

	/**
	 * Deletes the ayuntamiento with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AyuntamientoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ayuntamientoPK the primary key of the ayuntamiento
	 * @return the ayuntamiento that was removed
	 * @throws PortalException if a ayuntamiento with the primary key could not be found
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento
			deleteAyuntamiento(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.AyuntamientoPK ayuntamientoPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ayuntamientoLocalService.deleteAyuntamiento(ayuntamientoPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ayuntamientoLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ayuntamientoLocalService.dynamicQuery();
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

		return _ayuntamientoLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.AyuntamientoModelImpl</code>.
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

		return _ayuntamientoLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.AyuntamientoModelImpl</code>.
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

		return _ayuntamientoLocalService.dynamicQuery(
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

		return _ayuntamientoLocalService.dynamicQueryCount(dynamicQuery);
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

		return _ayuntamientoLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento
		fetchAyuntamiento(
			com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
				AyuntamientoPK ayuntamientoPK) {

		return _ayuntamientoLocalService.fetchAyuntamiento(ayuntamientoPK);
	}

	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento
		findByAyuntamiento(long ayuntamientoId) {

		return _ayuntamientoLocalService.findByAyuntamiento(ayuntamientoId);
	}

	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento>
			findByCcaaNombre(long ccaaId, String nombre) {

		return _ayuntamientoLocalService.findByCcaaNombre(ccaaId, nombre);
	}

	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento>
			findByNombre(String nombre) {

		return _ayuntamientoLocalService.findByNombre(nombre);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _ayuntamientoLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the ayuntamiento with the primary key.
	 *
	 * @param ayuntamientoPK the primary key of the ayuntamiento
	 * @return the ayuntamiento
	 * @throws PortalException if a ayuntamiento with the primary key could not be found
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento
			getAyuntamiento(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.AyuntamientoPK ayuntamientoPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ayuntamientoLocalService.getAyuntamiento(ayuntamientoPK);
	}

	/**
	 * Returns a range of all the ayuntamientos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.AyuntamientoModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ayuntamientos
	 * @param end the upper bound of the range of ayuntamientos (not inclusive)
	 * @return the range of ayuntamientos
	 */
	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento>
			getAyuntamientos(int start, int end) {

		return _ayuntamientoLocalService.getAyuntamientos(start, end);
	}

	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento>
			getAyuntamientosByCcaa(long ccaaId) {

		return _ayuntamientoLocalService.getAyuntamientosByCcaa(ccaaId);
	}

	/**
	 * Returns the number of ayuntamientos.
	 *
	 * @return the number of ayuntamientos
	 */
	@Override
	public int getAyuntamientosCount() {
		return _ayuntamientoLocalService.getAyuntamientosCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _ayuntamientoLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _ayuntamientoLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ayuntamientoLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the ayuntamiento in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AyuntamientoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ayuntamiento the ayuntamiento
	 * @return the ayuntamiento that was updated
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento
		updateAyuntamiento(
			com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento
				ayuntamiento) {

		return _ayuntamientoLocalService.updateAyuntamiento(ayuntamiento);
	}

	@Override
	public AyuntamientoLocalService getWrappedService() {
		return _ayuntamientoLocalService;
	}

	@Override
	public void setWrappedService(
		AyuntamientoLocalService ayuntamientoLocalService) {

		_ayuntamientoLocalService = ayuntamientoLocalService;
	}

	private AyuntamientoLocalService _ayuntamientoLocalService;

}