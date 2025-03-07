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
 * Provides a wrapper for {@link RequisitoCNAESLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RequisitoCNAESLocalService
 * @generated
 */
public class RequisitoCNAESLocalServiceWrapper
	implements RequisitoCNAESLocalService,
			   ServiceWrapper<RequisitoCNAESLocalService> {

	public RequisitoCNAESLocalServiceWrapper(
		RequisitoCNAESLocalService requisitoCNAESLocalService) {

		_requisitoCNAESLocalService = requisitoCNAESLocalService;
	}

	/**
	 * Adds the requisito cnaes to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RequisitoCNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param requisitoCNAES the requisito cnaes
	 * @return the requisito cnaes that was added
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
		addRequisitoCNAES(
			com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
				requisitoCNAES) {

		return _requisitoCNAESLocalService.addRequisitoCNAES(requisitoCNAES);
	}

	/**
	 * Creates a new requisito cnaes with the primary key. Does not add the requisito cnaes to the database.
	 *
	 * @param requisitoCNAESPK the primary key for the new requisito cnaes
	 * @return the new requisito cnaes
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
		createRequisitoCNAES(
			com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
				RequisitoCNAESPK requisitoCNAESPK) {

		return _requisitoCNAESLocalService.createRequisitoCNAES(
			requisitoCNAESPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _requisitoCNAESLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the requisito cnaes from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RequisitoCNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param requisitoCNAES the requisito cnaes
	 * @return the requisito cnaes that was removed
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
		deleteRequisitoCNAES(
			com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
				requisitoCNAES) {

		return _requisitoCNAESLocalService.deleteRequisitoCNAES(requisitoCNAES);
	}

	/**
	 * Deletes the requisito cnaes with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RequisitoCNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param requisitoCNAESPK the primary key of the requisito cnaes
	 * @return the requisito cnaes that was removed
	 * @throws PortalException if a requisito cnaes with the primary key could not be found
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
			deleteRequisitoCNAES(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.RequisitoCNAESPK requisitoCNAESPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _requisitoCNAESLocalService.deleteRequisitoCNAES(
			requisitoCNAESPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _requisitoCNAESLocalService.dynamicQuery();
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

		return _requisitoCNAESLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.RequisitoCNAESModelImpl</code>.
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

		return _requisitoCNAESLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.RequisitoCNAESModelImpl</code>.
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

		return _requisitoCNAESLocalService.dynamicQuery(
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

		return _requisitoCNAESLocalService.dynamicQueryCount(dynamicQuery);
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

		return _requisitoCNAESLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
		fetchRequisitoCNAES(
			com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
				RequisitoCNAESPK requisitoCNAESPK) {

		return _requisitoCNAESLocalService.fetchRequisitoCNAES(
			requisitoCNAESPK);
	}

	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES>
			findByRequisito(String legislacionId, String requisitoId) {

		return _requisitoCNAESLocalService.findByRequisito(
			legislacionId, requisitoId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _requisitoCNAESLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _requisitoCNAESLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the requisito cnaes with the primary key.
	 *
	 * @param requisitoCNAESPK the primary key of the requisito cnaes
	 * @return the requisito cnaes
	 * @throws PortalException if a requisito cnaes with the primary key could not be found
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
			getRequisitoCNAES(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.RequisitoCNAESPK requisitoCNAESPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _requisitoCNAESLocalService.getRequisitoCNAES(requisitoCNAESPK);
	}

	/**
	 * Returns a range of all the requisito cnaeses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.RequisitoCNAESModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of requisito cnaeses
	 * @param end the upper bound of the range of requisito cnaeses (not inclusive)
	 * @return the range of requisito cnaeses
	 */
	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES>
			getRequisitoCNAESs(int start, int end) {

		return _requisitoCNAESLocalService.getRequisitoCNAESs(start, end);
	}

	/**
	 * Returns the number of requisito cnaeses.
	 *
	 * @return the number of requisito cnaeses
	 */
	@Override
	public int getRequisitoCNAESsCount() {
		return _requisitoCNAESLocalService.getRequisitoCNAESsCount();
	}

	/**
	 * Updates the requisito cnaes in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RequisitoCNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param requisitoCNAES the requisito cnaes
	 * @return the requisito cnaes that was updated
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
		updateRequisitoCNAES(
			com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES
				requisitoCNAES) {

		return _requisitoCNAESLocalService.updateRequisitoCNAES(requisitoCNAES);
	}

	@Override
	public RequisitoCNAESLocalService getWrappedService() {
		return _requisitoCNAESLocalService;
	}

	@Override
	public void setWrappedService(
		RequisitoCNAESLocalService requisitoCNAESLocalService) {

		_requisitoCNAESLocalService = requisitoCNAESLocalService;
	}

	private RequisitoCNAESLocalService _requisitoCNAESLocalService;

}