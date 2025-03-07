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
 * Provides a wrapper for {@link HiddenLegisLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HiddenLegisLocalService
 * @generated
 */
public class HiddenLegisLocalServiceWrapper
	implements HiddenLegisLocalService,
			   ServiceWrapper<HiddenLegisLocalService> {

	public HiddenLegisLocalServiceWrapper(
		HiddenLegisLocalService hiddenLegisLocalService) {

		_hiddenLegisLocalService = hiddenLegisLocalService;
	}

	/**
	 * Adds the hidden legis to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HiddenLegisLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param hiddenLegis the hidden legis
	 * @return the hidden legis that was added
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
		addHiddenLegis(
			com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
				hiddenLegis) {

		return _hiddenLegisLocalService.addHiddenLegis(hiddenLegis);
	}

	/**
	 * Creates a new hidden legis with the primary key. Does not add the hidden legis to the database.
	 *
	 * @param hiddenLegisPK the primary key for the new hidden legis
	 * @return the new hidden legis
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
		createHiddenLegis(
			com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
				HiddenLegisPK hiddenLegisPK) {

		return _hiddenLegisLocalService.createHiddenLegis(hiddenLegisPK);
	}

	/**
	 * Deletes the hidden legis from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HiddenLegisLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param hiddenLegis the hidden legis
	 * @return the hidden legis that was removed
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
		deleteHiddenLegis(
			com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
				hiddenLegis) {

		return _hiddenLegisLocalService.deleteHiddenLegis(hiddenLegis);
	}

	/**
	 * Deletes the hidden legis with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HiddenLegisLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param hiddenLegisPK the primary key of the hidden legis
	 * @return the hidden legis that was removed
	 * @throws PortalException if a hidden legis with the primary key could not be found
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
			deleteHiddenLegis(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.HiddenLegisPK hiddenLegisPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _hiddenLegisLocalService.deleteHiddenLegis(hiddenLegisPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _hiddenLegisLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _hiddenLegisLocalService.dynamicQuery();
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

		return _hiddenLegisLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.HiddenLegisModelImpl</code>.
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

		return _hiddenLegisLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.HiddenLegisModelImpl</code>.
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

		return _hiddenLegisLocalService.dynamicQuery(
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

		return _hiddenLegisLocalService.dynamicQueryCount(dynamicQuery);
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

		return _hiddenLegisLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
		fetchHiddenLegis(
			com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
				HiddenLegisPK hiddenLegisPK) {

		return _hiddenLegisLocalService.fetchHiddenLegis(hiddenLegisPK);
	}

	@Override
	public java.util.List<String> fetchLegisByEmpresaId(long empresaId) {
		return _hiddenLegisLocalService.fetchLegisByEmpresaId(empresaId);
	}

	/**
	 * Returns the hidden legis with the primary key.
	 *
	 * @param hiddenLegisPK the primary key of the hidden legis
	 * @return the hidden legis
	 * @throws PortalException if a hidden legis with the primary key could not be found
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
			getHiddenLegis(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.HiddenLegisPK hiddenLegisPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _hiddenLegisLocalService.getHiddenLegis(hiddenLegisPK);
	}

	/**
	 * Returns a range of all the hidden legises.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.HiddenLegisModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of hidden legises
	 * @param end the upper bound of the range of hidden legises (not inclusive)
	 * @return the range of hidden legises
	 */
	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis>
			getHiddenLegises(int start, int end) {

		return _hiddenLegisLocalService.getHiddenLegises(start, end);
	}

	/**
	 * Returns the number of hidden legises.
	 *
	 * @return the number of hidden legises
	 */
	@Override
	public int getHiddenLegisesCount() {
		return _hiddenLegisLocalService.getHiddenLegisesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _hiddenLegisLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _hiddenLegisLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the hidden legis in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HiddenLegisLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param hiddenLegis the hidden legis
	 * @return the hidden legis that was updated
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
		updateHiddenLegis(
			com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis
				hiddenLegis) {

		return _hiddenLegisLocalService.updateHiddenLegis(hiddenLegis);
	}

	@Override
	public HiddenLegisLocalService getWrappedService() {
		return _hiddenLegisLocalService;
	}

	@Override
	public void setWrappedService(
		HiddenLegisLocalService hiddenLegisLocalService) {

		_hiddenLegisLocalService = hiddenLegisLocalService;
	}

	private HiddenLegisLocalService _hiddenLegisLocalService;

}