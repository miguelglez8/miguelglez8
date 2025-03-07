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
 * Provides the local service utility for Denuncia. This utility wraps
 * <code>com.canal.etico.liferay.portlet.canal.manager.service.impl.DenunciaLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see DenunciaLocalService
 * @generated
 */
public class DenunciaLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.canal.etico.liferay.portlet.canal.manager.service.impl.DenunciaLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the denuncia to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DenunciaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param denuncia the denuncia
	 * @return the denuncia that was added
	 */
	public static com.canal.etico.liferay.portlet.canal.manager.model.Denuncia
		addDenuncia(
			com.canal.etico.liferay.portlet.canal.manager.model.Denuncia
				denuncia) {

		return getService().addDenuncia(denuncia);
	}

	/**
	 * Creates a new denuncia with the primary key. Does not add the denuncia to the database.
	 *
	 * @param denunciaId the primary key for the new denuncia
	 * @return the new denuncia
	 */
	public static com.canal.etico.liferay.portlet.canal.manager.model.Denuncia
		createDenuncia(long denunciaId) {

		return getService().createDenuncia(denunciaId);
	}

	/**
	 * Deletes the denuncia from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DenunciaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param denuncia the denuncia
	 * @return the denuncia that was removed
	 */
	public static com.canal.etico.liferay.portlet.canal.manager.model.Denuncia
		deleteDenuncia(
			com.canal.etico.liferay.portlet.canal.manager.model.Denuncia
				denuncia) {

		return getService().deleteDenuncia(denuncia);
	}

	/**
	 * Deletes the denuncia with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DenunciaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param denunciaId the primary key of the denuncia
	 * @return the denuncia that was removed
	 * @throws PortalException if a denuncia with the primary key could not be found
	 */
	public static com.canal.etico.liferay.portlet.canal.manager.model.Denuncia
			deleteDenuncia(long denunciaId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteDenuncia(denunciaId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.DenunciaModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.DenunciaModelImpl</code>.
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

	public static com.canal.etico.liferay.portlet.canal.manager.model.Denuncia
		fetchDenuncia(long denunciaId) {

		return getService().fetchDenuncia(denunciaId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the denuncia with the primary key.
	 *
	 * @param denunciaId the primary key of the denuncia
	 * @return the denuncia
	 * @throws PortalException if a denuncia with the primary key could not be found
	 */
	public static com.canal.etico.liferay.portlet.canal.manager.model.Denuncia
			getDenuncia(long denunciaId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getDenuncia(denunciaId);
	}

	/**
	 * Returns a range of all the denuncias.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.canal.etico.liferay.portlet.canal.manager.model.impl.DenunciaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of denuncias
	 * @param end the upper bound of the range of denuncias (not inclusive)
	 * @return the range of denuncias
	 */
	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Denuncia>
			getDenuncias(int start, int end) {

		return getService().getDenuncias(start, end);
	}

	/**
	 * Returns the number of denuncias.
	 *
	 * @return the number of denuncias
	 */
	public static int getDenunciasCount() {
		return getService().getDenunciasCount();
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Denuncia>
			getDenunciasFinishedInDate(java.util.Date finishedDate) {

		return getService().getDenunciasFinishedInDate(finishedDate);
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Denuncia>
			getDenunciasFromCompany(long compId) {

		return getService().getDenunciasFromCompany(compId);
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Denuncia>
			getDenunciasNotFinishedCreatedBeforeDate(
				java.util.Date createdDate) {

		return getService().getDenunciasNotFinishedCreatedBeforeDate(
			createdDate);
	}

	public static java.util.List
		<com.canal.etico.liferay.portlet.canal.manager.model.Denuncia>
			getDenunciasNotFinishedCreatedInDate(java.util.Date createdDate) {

		return getService().getDenunciasNotFinishedCreatedInDate(createdDate);
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
	 * Updates the denuncia in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DenunciaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param denuncia the denuncia
	 * @return the denuncia that was updated
	 */
	public static com.canal.etico.liferay.portlet.canal.manager.model.Denuncia
		updateDenuncia(
			com.canal.etico.liferay.portlet.canal.manager.model.Denuncia
				denuncia) {

		return getService().updateDenuncia(denuncia);
	}

	public static DenunciaLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DenunciaLocalService, DenunciaLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DenunciaLocalService.class);

		ServiceTracker<DenunciaLocalService, DenunciaLocalService>
			serviceTracker =
				new ServiceTracker<DenunciaLocalService, DenunciaLocalService>(
					bundle.getBundleContext(), DenunciaLocalService.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}