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
 * Provides a wrapper for {@link VersionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see VersionLocalService
 * @generated
 */
public class VersionLocalServiceWrapper
	implements ServiceWrapper<VersionLocalService>, VersionLocalService {

	public VersionLocalServiceWrapper(VersionLocalService versionLocalService) {
		_versionLocalService = versionLocalService;
	}

	/**
	 * Adds the version to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param version the version
	 * @return the version that was added
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Version addVersion(
		com.plan.igualdad.liferay.portlet.manager.model.Version version) {

		return _versionLocalService.addVersion(version);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _versionLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new version with the primary key. Does not add the version to the database.
	 *
	 * @param versionPK the primary key for the new version
	 * @return the new version
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Version
		createVersion(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				VersionPK versionPK) {

		return _versionLocalService.createVersion(versionPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _versionLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the version from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param version the version
	 * @return the version that was removed
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Version
		deleteVersion(
			com.plan.igualdad.liferay.portlet.manager.model.Version version) {

		return _versionLocalService.deleteVersion(version);
	}

	/**
	 * Deletes the version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param versionPK the primary key of the version
	 * @return the version that was removed
	 * @throws PortalException if a version with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Version
			deleteVersion(
				com.plan.igualdad.liferay.portlet.manager.service.persistence.
					VersionPK versionPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _versionLocalService.deleteVersion(versionPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _versionLocalService.dynamicQuery();
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

		return _versionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.VersionModelImpl</code>.
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

		return _versionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.VersionModelImpl</code>.
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

		return _versionLocalService.dynamicQuery(
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

		return _versionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _versionLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Version fetchVersion(
		com.plan.igualdad.liferay.portlet.manager.service.persistence.VersionPK
			versionPK) {

		return _versionLocalService.fetchVersion(versionPK);
	}

	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Version> findByComp(
			long compId) {

		return _versionLocalService.findByComp(compId);
	}

	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Version
		findVersionActiva(long compId) {

		return _versionLocalService.findVersionActiva(compId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _versionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _versionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _versionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _versionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the version with the primary key.
	 *
	 * @param versionPK the primary key of the version
	 * @return the version
	 * @throws PortalException if a version with the primary key could not be found
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Version getVersion(
			com.plan.igualdad.liferay.portlet.manager.service.persistence.
				VersionPK versionPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _versionLocalService.getVersion(versionPK);
	}

	/**
	 * Returns a range of all the versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.VersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of versions
	 * @param end the upper bound of the range of versions (not inclusive)
	 * @return the range of versions
	 */
	@Override
	public java.util.List
		<com.plan.igualdad.liferay.portlet.manager.model.Version> getVersions(
			int start, int end) {

		return _versionLocalService.getVersions(start, end);
	}

	/**
	 * Returns the number of versions.
	 *
	 * @return the number of versions
	 */
	@Override
	public int getVersionsCount() {
		return _versionLocalService.getVersionsCount();
	}

	/**
	 * Updates the version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param version the version
	 * @return the version that was updated
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.model.Version
		updateVersion(
			com.plan.igualdad.liferay.portlet.manager.model.Version version) {

		return _versionLocalService.updateVersion(version);
	}

	@Override
	public VersionLocalService getWrappedService() {
		return _versionLocalService;
	}

	@Override
	public void setWrappedService(VersionLocalService versionLocalService) {
		_versionLocalService = versionLocalService;
	}

	private VersionLocalService _versionLocalService;

}