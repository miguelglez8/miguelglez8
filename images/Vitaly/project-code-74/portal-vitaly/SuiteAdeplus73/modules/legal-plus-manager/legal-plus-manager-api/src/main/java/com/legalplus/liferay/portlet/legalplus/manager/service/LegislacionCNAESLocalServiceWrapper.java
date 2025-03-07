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
 * Provides a wrapper for {@link LegislacionCNAESLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LegislacionCNAESLocalService
 * @generated
 */
public class LegislacionCNAESLocalServiceWrapper
	implements LegislacionCNAESLocalService,
			   ServiceWrapper<LegislacionCNAESLocalService> {

	public LegislacionCNAESLocalServiceWrapper(
		LegislacionCNAESLocalService legislacionCNAESLocalService) {

		_legislacionCNAESLocalService = legislacionCNAESLocalService;
	}

	/**
	 * Adds the legislacion cnaes to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LegislacionCNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param legislacionCNAES the legislacion cnaes
	 * @return the legislacion cnaes that was added
	 */
	@Override
	public
		com.legalplus.liferay.portlet.legalplus.manager.model.LegislacionCNAES
			addLegislacionCNAES(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					LegislacionCNAES legislacionCNAES) {

		return _legislacionCNAESLocalService.addLegislacionCNAES(
			legislacionCNAES);
	}

	@Override
	public
		com.legalplus.liferay.portlet.legalplus.manager.model.LegislacionCNAES
			addLegislacionCNAES(String legislacionId, String cnaeId) {

		return _legislacionCNAESLocalService.addLegislacionCNAES(
			legislacionId, cnaeId);
	}

	/**
	 * Creates a new legislacion cnaes with the primary key. Does not add the legislacion cnaes to the database.
	 *
	 * @param legislacionCNAESPK the primary key for the new legislacion cnaes
	 * @return the new legislacion cnaes
	 */
	@Override
	public
		com.legalplus.liferay.portlet.legalplus.manager.model.LegislacionCNAES
			createLegislacionCNAES(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.LegislacionCNAESPK legislacionCNAESPK) {

		return _legislacionCNAESLocalService.createLegislacionCNAES(
			legislacionCNAESPK);
	}

	/**
	 * Deletes the legislacion cnaes from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LegislacionCNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param legislacionCNAES the legislacion cnaes
	 * @return the legislacion cnaes that was removed
	 */
	@Override
	public
		com.legalplus.liferay.portlet.legalplus.manager.model.LegislacionCNAES
			deleteLegislacionCNAES(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					LegislacionCNAES legislacionCNAES) {

		return _legislacionCNAESLocalService.deleteLegislacionCNAES(
			legislacionCNAES);
	}

	/**
	 * Deletes the legislacion cnaes with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LegislacionCNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param legislacionCNAESPK the primary key of the legislacion cnaes
	 * @return the legislacion cnaes that was removed
	 * @throws PortalException if a legislacion cnaes with the primary key could not be found
	 */
	@Override
	public
		com.legalplus.liferay.portlet.legalplus.manager.model.LegislacionCNAES
				deleteLegislacionCNAES(
					com.legalplus.liferay.portlet.legalplus.manager.service.
						persistence.LegislacionCNAESPK legislacionCNAESPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _legislacionCNAESLocalService.deleteLegislacionCNAES(
			legislacionCNAESPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _legislacionCNAESLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _legislacionCNAESLocalService.dynamicQuery();
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

		return _legislacionCNAESLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.LegislacionCNAESModelImpl</code>.
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

		return _legislacionCNAESLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.LegislacionCNAESModelImpl</code>.
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

		return _legislacionCNAESLocalService.dynamicQuery(
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

		return _legislacionCNAESLocalService.dynamicQueryCount(dynamicQuery);
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

		return _legislacionCNAESLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public
		com.legalplus.liferay.portlet.legalplus.manager.model.LegislacionCNAES
			fetchByLegislacionCNAE(String legislacionId, String cnaeId) {

		return _legislacionCNAESLocalService.fetchByLegislacionCNAE(
			legislacionId, cnaeId);
	}

	@Override
	public
		com.legalplus.liferay.portlet.legalplus.manager.model.LegislacionCNAES
			fetchLegislacionCNAES(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.LegislacionCNAESPK legislacionCNAESPK) {

		return _legislacionCNAESLocalService.fetchLegislacionCNAES(
			legislacionCNAESPK);
	}

	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.LegislacionCNAES>
			findByLegislacion(String legislacionId) {

		return _legislacionCNAESLocalService.findByLegislacion(legislacionId);
	}

	/**
	 * Returns the legislacion cnaes with the primary key.
	 *
	 * @param legislacionCNAESPK the primary key of the legislacion cnaes
	 * @return the legislacion cnaes
	 * @throws PortalException if a legislacion cnaes with the primary key could not be found
	 */
	@Override
	public
		com.legalplus.liferay.portlet.legalplus.manager.model.LegislacionCNAES
				getLegislacionCNAES(
					com.legalplus.liferay.portlet.legalplus.manager.service.
						persistence.LegislacionCNAESPK legislacionCNAESPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _legislacionCNAESLocalService.getLegislacionCNAES(
			legislacionCNAESPK);
	}

	/**
	 * Returns a range of all the legislacion cnaeses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.LegislacionCNAESModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of legislacion cnaeses
	 * @param end the upper bound of the range of legislacion cnaeses (not inclusive)
	 * @return the range of legislacion cnaeses
	 */
	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.LegislacionCNAES>
			getLegislacionCNAESs(int start, int end) {

		return _legislacionCNAESLocalService.getLegislacionCNAESs(start, end);
	}

	/**
	 * Returns the number of legislacion cnaeses.
	 *
	 * @return the number of legislacion cnaeses
	 */
	@Override
	public int getLegislacionCNAESsCount() {
		return _legislacionCNAESLocalService.getLegislacionCNAESsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _legislacionCNAESLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _legislacionCNAESLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the legislacion cnaes in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LegislacionCNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param legislacionCNAES the legislacion cnaes
	 * @return the legislacion cnaes that was updated
	 */
	@Override
	public
		com.legalplus.liferay.portlet.legalplus.manager.model.LegislacionCNAES
			updateLegislacionCNAES(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					LegislacionCNAES legislacionCNAES) {

		return _legislacionCNAESLocalService.updateLegislacionCNAES(
			legislacionCNAES);
	}

	@Override
	public LegislacionCNAESLocalService getWrappedService() {
		return _legislacionCNAESLocalService;
	}

	@Override
	public void setWrappedService(
		LegislacionCNAESLocalService legislacionCNAESLocalService) {

		_legislacionCNAESLocalService = legislacionCNAESLocalService;
	}

	private LegislacionCNAESLocalService _legislacionCNAESLocalService;

}