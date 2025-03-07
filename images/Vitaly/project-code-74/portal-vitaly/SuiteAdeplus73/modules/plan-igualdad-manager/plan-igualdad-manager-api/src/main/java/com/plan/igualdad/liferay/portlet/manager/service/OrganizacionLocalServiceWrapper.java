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
 * Provides a wrapper for {@link OrganizacionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see OrganizacionLocalService
 * @generated
 */
public class OrganizacionLocalServiceWrapper
	implements OrganizacionLocalService,
			   ServiceWrapper<OrganizacionLocalService> {

	public OrganizacionLocalServiceWrapper(
		OrganizacionLocalService organizacionLocalService) {

		_organizacionLocalService = organizacionLocalService;
	}

	/**
	 * Adds the organizacion to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrganizacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param organizacion the organizacion
	 * @return the organizacion that was added
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Organizacion
		addOrganizacion(
			com.plan.igualdad.liferay.portlet.manager.model.Organizacion
				organizacion) {

		return _organizacionLocalService.addOrganizacion(organizacion);
	}

	/**
	 * Creates a new organizacion with the primary key. Does not add the organizacion to the database.
	 *
	 * @param organizacionPK the primary key for the new organizacion
	 * @return the new organizacion
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Organizacion
		createOrganizacion(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				OrganizacionPK organizacionPK) {

		return _organizacionLocalService.createOrganizacion(organizacionPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _organizacionLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the organizacion from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrganizacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param organizacion the organizacion
	 * @return the organizacion that was removed
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Organizacion
		deleteOrganizacion(
			com.plan.igualdad.liferay.portlet.manager.model.Organizacion
				organizacion) {

		return _organizacionLocalService.deleteOrganizacion(organizacion);
	}

	/**
	 * Deletes the organizacion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrganizacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param organizacionPK the primary key of the organizacion
	 * @return the organizacion that was removed
	 * @throws PortalException if a organizacion with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Organizacion
			deleteOrganizacion(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					OrganizacionPK organizacionPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _organizacionLocalService.deleteOrganizacion(organizacionPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _organizacionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _organizacionLocalService.dynamicQuery();
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

		return _organizacionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.OrganizacionModelImpl</code>.
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

		return _organizacionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.OrganizacionModelImpl</code>.
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

		return _organizacionLocalService.dynamicQuery(
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

		return _organizacionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _organizacionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Organizacion
		fetchOrganizacion(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				OrganizacionPK organizacionPK) {

		return _organizacionLocalService.fetchOrganizacion(organizacionPK);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Organizacion
		findByOrganizacion(long compId, long versionId) {

		return _organizacionLocalService.findByOrganizacion(compId, versionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _organizacionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _organizacionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the organizacion with the primary key.
	 *
	 * @param organizacionPK the primary key of the organizacion
	 * @return the organizacion
	 * @throws PortalException if a organizacion with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Organizacion
			getOrganizacion(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					OrganizacionPK organizacionPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _organizacionLocalService.getOrganizacion(organizacionPK);
	}

	/**
	 * Returns a range of all the organizacions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.OrganizacionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of organizacions
	 * @param end the upper bound of the range of organizacions (not inclusive)
	 * @return the range of organizacions
	 */
	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Organizacion>
			getOrganizacions(int start, int end) {

		return _organizacionLocalService.getOrganizacions(start, end);
	}

	/**
	 * Returns the number of organizacions.
	 *
	 * @return the number of organizacions
	 */
	@Override
	public int getOrganizacionsCount() {
		return _organizacionLocalService.getOrganizacionsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _organizacionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _organizacionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the organizacion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrganizacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param organizacion the organizacion
	 * @return the organizacion that was updated
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Organizacion
		updateOrganizacion(
			com.plan.igualdad.liferay.portlet.manager.model.Organizacion
				organizacion) {

		return _organizacionLocalService.updateOrganizacion(organizacion);
	}

	@Override
	public OrganizacionLocalService getWrappedService() {
		return _organizacionLocalService;
	}

	@Override
	public void setWrappedService(
		OrganizacionLocalService organizacionLocalService) {

		_organizacionLocalService = organizacionLocalService;
	}

	private OrganizacionLocalService _organizacionLocalService;

}