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
 * Provides a wrapper for {@link ProvinciaLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProvinciaLocalService
 * @generated
 */
public class ProvinciaLocalServiceWrapper
	implements ProvinciaLocalService, ServiceWrapper<ProvinciaLocalService> {

	public ProvinciaLocalServiceWrapper(
		ProvinciaLocalService provinciaLocalService) {

		_provinciaLocalService = provinciaLocalService;
	}

	/**
	 * Adds the provincia to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProvinciaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param provincia the provincia
	 * @return the provincia that was added
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Provincia
		addProvincia(
			com.legalplus.liferay.portlet.legalplus.manager.model.Provincia
				provincia) {

		return _provinciaLocalService.addProvincia(provincia);
	}

	/**
	 * Creates a new provincia with the primary key. Does not add the provincia to the database.
	 *
	 * @param provinciaPK the primary key for the new provincia
	 * @return the new provincia
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Provincia
		createProvincia(
			com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
				ProvinciaPK provinciaPK) {

		return _provinciaLocalService.createProvincia(provinciaPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _provinciaLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the provincia from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProvinciaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param provincia the provincia
	 * @return the provincia that was removed
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Provincia
		deleteProvincia(
			com.legalplus.liferay.portlet.legalplus.manager.model.Provincia
				provincia) {

		return _provinciaLocalService.deleteProvincia(provincia);
	}

	/**
	 * Deletes the provincia with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProvinciaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param provinciaPK the primary key of the provincia
	 * @return the provincia that was removed
	 * @throws PortalException if a provincia with the primary key could not be found
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Provincia
			deleteProvincia(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.ProvinciaPK provinciaPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _provinciaLocalService.deleteProvincia(provinciaPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _provinciaLocalService.dynamicQuery();
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

		return _provinciaLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.ProvinciaModelImpl</code>.
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

		return _provinciaLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.ProvinciaModelImpl</code>.
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

		return _provinciaLocalService.dynamicQuery(
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

		return _provinciaLocalService.dynamicQueryCount(dynamicQuery);
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

		return _provinciaLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Provincia
		fetchProvincia(
			com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
				ProvinciaPK provinciaPK) {

		return _provinciaLocalService.fetchProvincia(provinciaPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _provinciaLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _provinciaLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _provinciaLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _provinciaLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the provincia with the primary key.
	 *
	 * @param provinciaPK the primary key of the provincia
	 * @return the provincia
	 * @throws PortalException if a provincia with the primary key could not be found
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Provincia
			getProvincia(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.ProvinciaPK provinciaPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _provinciaLocalService.getProvincia(provinciaPK);
	}

	/**
	 * Returns a range of all the provincias.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.ProvinciaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of provincias
	 * @param end the upper bound of the range of provincias (not inclusive)
	 * @return the range of provincias
	 */
	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.Provincia>
			getProvincias(int start, int end) {

		return _provinciaLocalService.getProvincias(start, end);
	}

	/**
	 * Returns the number of provincias.
	 *
	 * @return the number of provincias
	 */
	@Override
	public int getProvinciasCount() {
		return _provinciaLocalService.getProvinciasCount();
	}

	/**
	 * Updates the provincia in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProvinciaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param provincia the provincia
	 * @return the provincia that was updated
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Provincia
		updateProvincia(
			com.legalplus.liferay.portlet.legalplus.manager.model.Provincia
				provincia) {

		return _provinciaLocalService.updateProvincia(provincia);
	}

	@Override
	public ProvinciaLocalService getWrappedService() {
		return _provinciaLocalService;
	}

	@Override
	public void setWrappedService(ProvinciaLocalService provinciaLocalService) {
		_provinciaLocalService = provinciaLocalService;
	}

	private ProvinciaLocalService _provinciaLocalService;

}