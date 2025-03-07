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

package com.canal.etico.liferay.portlet.canal.manager.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DenunciaAccionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DenunciaAccionLocalService
 * @generated
 */
public class DenunciaAccionLocalServiceWrapper
	implements DenunciaAccionLocalService,
			   ServiceWrapper<DenunciaAccionLocalService> {

	public DenunciaAccionLocalServiceWrapper(
		DenunciaAccionLocalService denunciaAccionLocalService) {

		_denunciaAccionLocalService = denunciaAccionLocalService;
	}

	/**
	 * Adds the denuncia accion to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DenunciaAccionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param denunciaAccion the denuncia accion
	 * @return the denuncia accion that was added
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
		addDenunciaAccion(
			com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
				denunciaAccion) {

		return _denunciaAccionLocalService.addDenunciaAccion(denunciaAccion);
	}

	/**
	 * Creates a new denuncia accion with the primary key. Does not add the denuncia accion to the database.
	 *
	 * @param denunciaAccionId the primary key for the new denuncia accion
	 * @return the new denuncia accion
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
		createDenunciaAccion(long denunciaAccionId) {

		return _denunciaAccionLocalService.createDenunciaAccion(
			denunciaAccionId);
	}

	/**
	 * Deletes the denuncia accion from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DenunciaAccionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param denunciaAccion the denuncia accion
	 * @return the denuncia accion that was removed
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
		deleteDenunciaAccion(
			com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
				denunciaAccion) {

		return _denunciaAccionLocalService.deleteDenunciaAccion(denunciaAccion);
	}

	/**
	 * Deletes the denuncia accion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DenunciaAccionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param denunciaAccionId the primary key of the denuncia accion
	 * @return the denuncia accion that was removed
	 * @throws PortalException if a denuncia accion with the primary key could not be found
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
			deleteDenunciaAccion(long denunciaAccionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _denunciaAccionLocalService.deleteDenunciaAccion(
			denunciaAccionId);
	}

	@Override
	public void deleteDenunciaAccionFromDenuncia(long denunciaId) {
		_denunciaAccionLocalService.deleteDenunciaAccionFromDenuncia(
			denunciaId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _denunciaAccionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _denunciaAccionLocalService.dynamicQuery();
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

		return _denunciaAccionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.DenunciaAccionModelImpl</code>.
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

		return _denunciaAccionLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.DenunciaAccionModelImpl</code>.
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

		return _denunciaAccionLocalService.dynamicQuery(
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

		return _denunciaAccionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _denunciaAccionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
		fetchDenunciaAccion(long denunciaAccionId) {

		return _denunciaAccionLocalService.fetchDenunciaAccion(
			denunciaAccionId);
	}

	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Accion>
			getAccionesFromDenuncia(long denunciaId) {

		return _denunciaAccionLocalService.getAccionesFromDenuncia(denunciaId);
	}

	@Override
	public String getAccionesHtmlFromDenuncia(long denunciaId) {
		return _denunciaAccionLocalService.getAccionesHtmlFromDenuncia(
			denunciaId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _denunciaAccionLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the denuncia accion with the primary key.
	 *
	 * @param denunciaAccionId the primary key of the denuncia accion
	 * @return the denuncia accion
	 * @throws PortalException if a denuncia accion with the primary key could not be found
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
			getDenunciaAccion(long denunciaAccionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _denunciaAccionLocalService.getDenunciaAccion(denunciaAccionId);
	}

	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion>
			getDenunciaAccionFromDenuncia(long denunciaId) {

		return _denunciaAccionLocalService.getDenunciaAccionFromDenuncia(
			denunciaId);
	}

	/**
	 * Returns a range of all the denuncia accions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.DenunciaAccionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of denuncia accions
	 * @param end the upper bound of the range of denuncia accions (not inclusive)
	 * @return the range of denuncia accions
	 */
	@Override
	public java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion>
			getDenunciaAccions(int start, int end) {

		return _denunciaAccionLocalService.getDenunciaAccions(start, end);
	}

	/**
	 * Returns the number of denuncia accions.
	 *
	 * @return the number of denuncia accions
	 */
	@Override
	public int getDenunciaAccionsCount() {
		return _denunciaAccionLocalService.getDenunciaAccionsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _denunciaAccionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _denunciaAccionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _denunciaAccionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the denuncia accion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DenunciaAccionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param denunciaAccion the denuncia accion
	 * @return the denuncia accion that was updated
	 */
	@Override
	public com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
		updateDenunciaAccion(
			com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
				denunciaAccion) {

		return _denunciaAccionLocalService.updateDenunciaAccion(denunciaAccion);
	}

	@Override
	public DenunciaAccionLocalService getWrappedService() {
		return _denunciaAccionLocalService;
	}

	@Override
	public void setWrappedService(
		DenunciaAccionLocalService denunciaAccionLocalService) {

		_denunciaAccionLocalService = denunciaAccionLocalService;
	}

	private DenunciaAccionLocalService _denunciaAccionLocalService;

}