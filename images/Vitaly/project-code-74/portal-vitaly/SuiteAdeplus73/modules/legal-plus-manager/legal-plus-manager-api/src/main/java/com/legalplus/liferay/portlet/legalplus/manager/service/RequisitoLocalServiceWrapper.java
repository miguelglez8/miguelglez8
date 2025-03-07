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
 * Provides a wrapper for {@link RequisitoLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RequisitoLocalService
 * @generated
 */
public class RequisitoLocalServiceWrapper
	implements RequisitoLocalService, ServiceWrapper<RequisitoLocalService> {

	public RequisitoLocalServiceWrapper(
		RequisitoLocalService requisitoLocalService) {

		_requisitoLocalService = requisitoLocalService;
	}

	/**
	 * Adds the requisito to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RequisitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param requisito the requisito
	 * @return the requisito that was added
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
		addRequisito(
			com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
				requisito) {

		return _requisitoLocalService.addRequisito(requisito);
	}

	/**
	 * Creates a new requisito with the primary key. Does not add the requisito to the database.
	 *
	 * @param requisitoPK the primary key for the new requisito
	 * @return the new requisito
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
		createRequisito(
			com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
				RequisitoPK requisitoPK) {

		return _requisitoLocalService.createRequisito(requisitoPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _requisitoLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the requisito from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RequisitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param requisito the requisito
	 * @return the requisito that was removed
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
		deleteRequisito(
			com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
				requisito) {

		return _requisitoLocalService.deleteRequisito(requisito);
	}

	/**
	 * Deletes the requisito with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RequisitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param requisitoPK the primary key of the requisito
	 * @return the requisito that was removed
	 * @throws PortalException if a requisito with the primary key could not be found
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
			deleteRequisito(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.RequisitoPK requisitoPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _requisitoLocalService.deleteRequisito(requisitoPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _requisitoLocalService.dynamicQuery();
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

		return _requisitoLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.RequisitoModelImpl</code>.
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

		return _requisitoLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.RequisitoModelImpl</code>.
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

		return _requisitoLocalService.dynamicQuery(
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

		return _requisitoLocalService.dynamicQueryCount(dynamicQuery);
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

		return _requisitoLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
		fetchRequisito(
			com.legalplus.liferay.portlet.legalplus.manager.service.persistence.
				RequisitoPK requisitoPK) {

		return _requisitoLocalService.fetchRequisito(requisitoPK);
	}

	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.Requisito>
			findByLegislacion(String legislacionId) {

		return _requisitoLocalService.findByLegislacion(legislacionId);
	}

	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
		findByLegislacionRequisito(String legislacionId, String requisitoId) {

		return _requisitoLocalService.findByLegislacionRequisito(
			legislacionId, requisitoId);
	}

	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.Requisito>
			findRequisitoActivoByLegislacion(String legislacionId) {

		return _requisitoLocalService.findRequisitoActivoByLegislacion(
			legislacionId);
	}

	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.Requisito>
			findRequisitoActivoByLegislacionAndCompId(
				String legislacionId, Long compId) {

		return _requisitoLocalService.findRequisitoActivoByLegislacionAndCompId(
			legislacionId, compId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _requisitoLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _requisitoLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the requisito with the primary key.
	 *
	 * @param requisitoPK the primary key of the requisito
	 * @return the requisito
	 * @throws PortalException if a requisito with the primary key could not be found
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
			getRequisito(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.RequisitoPK requisitoPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _requisitoLocalService.getRequisito(requisitoPK);
	}

	/**
	 * Returns a range of all the requisitos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.RequisitoModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of requisitos
	 * @param end the upper bound of the range of requisitos (not inclusive)
	 * @return the range of requisitos
	 */
	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.Requisito>
			getRequisitos(int start, int end) {

		return _requisitoLocalService.getRequisitos(start, end);
	}

	/**
	 * Returns the number of requisitos.
	 *
	 * @return the number of requisitos
	 */
	@Override
	public int getRequisitosCount() {
		return _requisitoLocalService.getRequisitosCount();
	}

	/**
	 * Updates the requisito in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RequisitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param requisito the requisito
	 * @return the requisito that was updated
	 */
	@Override
	public com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
		updateRequisito(
			com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
				requisito) {

		return _requisitoLocalService.updateRequisito(requisito);
	}

	@Override
	public RequisitoLocalService getWrappedService() {
		return _requisitoLocalService;
	}

	@Override
	public void setWrappedService(RequisitoLocalService requisitoLocalService) {
		_requisitoLocalService = requisitoLocalService;
	}

	private RequisitoLocalService _requisitoLocalService;

}