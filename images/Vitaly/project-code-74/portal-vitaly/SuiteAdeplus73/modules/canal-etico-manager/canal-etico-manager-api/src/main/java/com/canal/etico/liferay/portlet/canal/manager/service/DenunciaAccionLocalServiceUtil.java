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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for DenunciaAccion. This utility wraps
 * <code>com.canal.etico.liferay.portlet.canal.manager.service.impl.DenunciaAccionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see DenunciaAccionLocalService
 * @generated
 */
public class DenunciaAccionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.canal.etico.liferay.portlet.canal.manager.service.impl.DenunciaAccionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
			addDenunciaAccion(
				com.canal.etico.liferay.portlet.canal.manager.model.
					DenunciaAccion denunciaAccion) {

		return getService().addDenunciaAccion(denunciaAccion);
	}

	/**
	 * Creates a new denuncia accion with the primary key. Does not add the denuncia accion to the database.
	 *
	 * @param denunciaAccionId the primary key for the new denuncia accion
	 * @return the new denuncia accion
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
			createDenunciaAccion(long denunciaAccionId) {

		return getService().createDenunciaAccion(denunciaAccionId);
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
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
			deleteDenunciaAccion(
				com.canal.etico.liferay.portlet.canal.manager.model.
					DenunciaAccion denunciaAccion) {

		return getService().deleteDenunciaAccion(denunciaAccion);
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
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
				deleteDenunciaAccion(long denunciaAccionId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteDenunciaAccion(denunciaAccionId);
	}

	public static void deleteDenunciaAccionFromDenuncia(long denunciaId) {
		getService().deleteDenunciaAccionFromDenuncia(denunciaId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static
		com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
			fetchDenunciaAccion(long denunciaAccionId) {

		return getService().fetchDenunciaAccion(denunciaAccionId);
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Accion>
			getAccionesFromDenuncia(long denunciaId) {

		return getService().getAccionesFromDenuncia(denunciaId);
	}

	public static String getAccionesHtmlFromDenuncia(long denunciaId) {
		return getService().getAccionesHtmlFromDenuncia(denunciaId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the denuncia accion with the primary key.
	 *
	 * @param denunciaAccionId the primary key of the denuncia accion
	 * @return the denuncia accion
	 * @throws PortalException if a denuncia accion with the primary key could not be found
	 */
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
				getDenunciaAccion(long denunciaAccionId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getDenunciaAccion(denunciaAccionId);
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion>
			getDenunciaAccionFromDenuncia(long denunciaId) {

		return getService().getDenunciaAccionFromDenuncia(denunciaId);
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
	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion>
			getDenunciaAccions(int start, int end) {

		return getService().getDenunciaAccions(start, end);
	}

	/**
	 * Returns the number of denuncia accions.
	 *
	 * @return the number of denuncia accions
	 */
	public static int getDenunciaAccionsCount() {
		return getService().getDenunciaAccionsCount();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
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
	public static
		com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion
			updateDenunciaAccion(
				com.canal.etico.liferay.portlet.canal.manager.model.
					DenunciaAccion denunciaAccion) {

		return getService().updateDenunciaAccion(denunciaAccion);
	}

	public static DenunciaAccionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<DenunciaAccionLocalService, DenunciaAccionLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			DenunciaAccionLocalService.class);

		ServiceTracker<DenunciaAccionLocalService, DenunciaAccionLocalService>
			serviceTracker =
				new ServiceTracker
					<DenunciaAccionLocalService, DenunciaAccionLocalService>(
						bundle.getBundleContext(),
						DenunciaAccionLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}