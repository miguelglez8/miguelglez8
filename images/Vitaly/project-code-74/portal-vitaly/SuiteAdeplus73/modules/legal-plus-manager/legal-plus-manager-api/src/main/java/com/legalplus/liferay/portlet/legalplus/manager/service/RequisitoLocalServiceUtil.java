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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Requisito. This utility wraps
 * <code>com.legalplus.liferay.portlet.legalplus.manager.service.impl.RequisitoLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see RequisitoLocalService
 * @generated
 */
public class RequisitoLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.legalplus.liferay.portlet.legalplus.manager.service.impl.RequisitoLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
			addRequisito(
				com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
					requisito) {

		return getService().addRequisito(requisito);
	}

	/**
	 * Creates a new requisito with the primary key. Does not add the requisito to the database.
	 *
	 * @param requisitoPK the primary key for the new requisito
	 * @return the new requisito
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
			createRequisito(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.RequisitoPK requisitoPK) {

		return getService().createRequisito(requisitoPK);
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
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
			deleteRequisito(
				com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
					requisito) {

		return getService().deleteRequisito(requisito);
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
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
				deleteRequisito(
					com.legalplus.liferay.portlet.legalplus.manager.service.
						persistence.RequisitoPK requisitoPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteRequisito(requisitoPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.RequisitoModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.RequisitoModelImpl</code>.
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
		com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
			fetchRequisito(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.RequisitoPK requisitoPK) {

		return getService().fetchRequisito(requisitoPK);
	}

	public static java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.Requisito>
			findByLegislacion(String legislacionId) {

		return getService().findByLegislacion(legislacionId);
	}

	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
			findByLegislacionRequisito(
				String legislacionId, String requisitoId) {

		return getService().findByLegislacionRequisito(
			legislacionId, requisitoId);
	}

	public static java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.Requisito>
			findRequisitoActivoByLegislacion(String legislacionId) {

		return getService().findRequisitoActivoByLegislacion(legislacionId);
	}

	public static java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.Requisito>
			findRequisitoActivoByLegislacionAndCompId(
				String legislacionId, Long compId) {

		return getService().findRequisitoActivoByLegislacionAndCompId(
			legislacionId, compId);
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
	 * Returns the requisito with the primary key.
	 *
	 * @param requisitoPK the primary key of the requisito
	 * @return the requisito
	 * @throws PortalException if a requisito with the primary key could not be found
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
				getRequisito(
					com.legalplus.liferay.portlet.legalplus.manager.service.
						persistence.RequisitoPK requisitoPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getRequisito(requisitoPK);
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
	public static java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.Requisito>
			getRequisitos(int start, int end) {

		return getService().getRequisitos(start, end);
	}

	/**
	 * Returns the number of requisitos.
	 *
	 * @return the number of requisitos
	 */
	public static int getRequisitosCount() {
		return getService().getRequisitosCount();
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
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
			updateRequisito(
				com.legalplus.liferay.portlet.legalplus.manager.model.Requisito
					requisito) {

		return getService().updateRequisito(requisito);
	}

	public static RequisitoLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RequisitoLocalService, RequisitoLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RequisitoLocalService.class);

		ServiceTracker<RequisitoLocalService, RequisitoLocalService>
			serviceTracker =
				new ServiceTracker
					<RequisitoLocalService, RequisitoLocalService>(
						bundle.getBundleContext(), RequisitoLocalService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}