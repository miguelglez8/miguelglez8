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
 * Provides a wrapper for {@link ConsultorCompanyLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ConsultorCompanyLocalService
 * @generated
 */
public class ConsultorCompanyLocalServiceWrapper
	implements ConsultorCompanyLocalService,
			   ServiceWrapper<ConsultorCompanyLocalService> {

	public ConsultorCompanyLocalServiceWrapper(
		ConsultorCompanyLocalService consultorCompanyLocalService) {

		_consultorCompanyLocalService = consultorCompanyLocalService;
	}

	/**
	 * Adds the consultor company to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ConsultorCompanyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param consultorCompany the consultor company
	 * @return the consultor company that was added
	 */
	@Override
	public
		com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany
			addConsultorCompany(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					ConsultorCompany consultorCompany) {

		return _consultorCompanyLocalService.addConsultorCompany(
			consultorCompany);
	}

	/**
	 * Creates a new consultor company with the primary key. Does not add the consultor company to the database.
	 *
	 * @param consultorCompanyPK the primary key for the new consultor company
	 * @return the new consultor company
	 */
	@Override
	public
		com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany
			createConsultorCompany(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.ConsultorCompanyPK consultorCompanyPK) {

		return _consultorCompanyLocalService.createConsultorCompany(
			consultorCompanyPK);
	}

	/**
	 * Deletes the consultor company from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ConsultorCompanyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param consultorCompany the consultor company
	 * @return the consultor company that was removed
	 */
	@Override
	public
		com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany
			deleteConsultorCompany(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					ConsultorCompany consultorCompany) {

		return _consultorCompanyLocalService.deleteConsultorCompany(
			consultorCompany);
	}

	/**
	 * Deletes the consultor company with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ConsultorCompanyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param consultorCompanyPK the primary key of the consultor company
	 * @return the consultor company that was removed
	 * @throws PortalException if a consultor company with the primary key could not be found
	 */
	@Override
	public
		com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany
				deleteConsultorCompany(
					com.legalplus.liferay.portlet.legalplus.manager.service.
						persistence.ConsultorCompanyPK consultorCompanyPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _consultorCompanyLocalService.deleteConsultorCompany(
			consultorCompanyPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _consultorCompanyLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _consultorCompanyLocalService.dynamicQuery();
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

		return _consultorCompanyLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.ConsultorCompanyModelImpl</code>.
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

		return _consultorCompanyLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.ConsultorCompanyModelImpl</code>.
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

		return _consultorCompanyLocalService.dynamicQuery(
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

		return _consultorCompanyLocalService.dynamicQueryCount(dynamicQuery);
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

		return _consultorCompanyLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany>
			fetchByCompId(Long compId) {

		return _consultorCompanyLocalService.fetchByCompId(compId);
	}

	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany>
			fetchByCompIdAndAppId(Long compId, Long appId) {

		return _consultorCompanyLocalService.fetchByCompIdAndAppId(
			compId, appId);
	}

	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany>
			fetchByUserId(Long userId) {

		return _consultorCompanyLocalService.fetchByUserId(userId);
	}

	@Override
	public
		com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany
			fetchConsultorCompany(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.ConsultorCompanyPK consultorCompanyPK) {

		return _consultorCompanyLocalService.fetchConsultorCompany(
			consultorCompanyPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _consultorCompanyLocalService.getActionableDynamicQuery();
	}

	@Override
	public String getCompByUserId(Long userId) {
		return _consultorCompanyLocalService.getCompByUserId(userId);
	}

	@Override
	public String getCompsByUserIdAndApp(Long userId, String app) {
		return _consultorCompanyLocalService.getCompsByUserIdAndApp(
			userId, app);
	}

	/**
	 * Returns a range of all the consultor companies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.ConsultorCompanyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of consultor companies
	 * @param end the upper bound of the range of consultor companies (not inclusive)
	 * @return the range of consultor companies
	 */
	@Override
	public java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany>
			getConsultorCompanies(int start, int end) {

		return _consultorCompanyLocalService.getConsultorCompanies(start, end);
	}

	/**
	 * Returns the number of consultor companies.
	 *
	 * @return the number of consultor companies
	 */
	@Override
	public int getConsultorCompaniesCount() {
		return _consultorCompanyLocalService.getConsultorCompaniesCount();
	}

	/**
	 * Returns the consultor company with the primary key.
	 *
	 * @param consultorCompanyPK the primary key of the consultor company
	 * @return the consultor company
	 * @throws PortalException if a consultor company with the primary key could not be found
	 */
	@Override
	public
		com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany
				getConsultorCompany(
					com.legalplus.liferay.portlet.legalplus.manager.service.
						persistence.ConsultorCompanyPK consultorCompanyPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _consultorCompanyLocalService.getConsultorCompany(
			consultorCompanyPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _consultorCompanyLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _consultorCompanyLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _consultorCompanyLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the consultor company in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ConsultorCompanyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param consultorCompany the consultor company
	 * @return the consultor company that was updated
	 */
	@Override
	public
		com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany
			updateConsultorCompany(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					ConsultorCompany consultorCompany) {

		return _consultorCompanyLocalService.updateConsultorCompany(
			consultorCompany);
	}

	@Override
	public ConsultorCompanyLocalService getWrappedService() {
		return _consultorCompanyLocalService;
	}

	@Override
	public void setWrappedService(
		ConsultorCompanyLocalService consultorCompanyLocalService) {

		_consultorCompanyLocalService = consultorCompanyLocalService;
	}

	private ConsultorCompanyLocalService _consultorCompanyLocalService;

}