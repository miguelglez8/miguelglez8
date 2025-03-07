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
 * Provides the local service utility for EvalRequisito. This utility wraps
 * <code>com.legalplus.liferay.portlet.legalplus.manager.service.impl.EvalRequisitoLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see EvalRequisitoLocalService
 * @generated
 */
public class EvalRequisitoLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.legalplus.liferay.portlet.legalplus.manager.service.impl.EvalRequisitoLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the eval requisito to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvalRequisitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evalRequisito the eval requisito
	 * @return the eval requisito that was added
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
			addEvalRequisito(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					EvalRequisito evalRequisito) {

		return getService().addEvalRequisito(evalRequisito);
	}

	/**
	 * Creates a new eval requisito with the primary key. Does not add the eval requisito to the database.
	 *
	 * @param evalRequisitoPK the primary key for the new eval requisito
	 * @return the new eval requisito
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
			createEvalRequisito(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.EvalRequisitoPK evalRequisitoPK) {

		return getService().createEvalRequisito(evalRequisitoPK);
	}

	/**
	 * Deletes the eval requisito from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvalRequisitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evalRequisito the eval requisito
	 * @return the eval requisito that was removed
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
			deleteEvalRequisito(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					EvalRequisito evalRequisito) {

		return getService().deleteEvalRequisito(evalRequisito);
	}

	/**
	 * Deletes the eval requisito with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvalRequisitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evalRequisitoPK the primary key of the eval requisito
	 * @return the eval requisito that was removed
	 * @throws PortalException if a eval requisito with the primary key could not be found
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
				deleteEvalRequisito(
					com.legalplus.liferay.portlet.legalplus.manager.service.
						persistence.EvalRequisitoPK evalRequisitoPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteEvalRequisito(evalRequisitoPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.EvalRequisitoModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.EvalRequisitoModelImpl</code>.
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
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
			fetchEvalRequisito(
				com.legalplus.liferay.portlet.legalplus.manager.service.
					persistence.EvalRequisitoPK evalRequisitoPK) {

		return getService().fetchEvalRequisito(evalRequisitoPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static String getCalendarioEvalRequisitos(Long compId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCalendarioEvalRequisitos(compId);
	}

	/**
	 * Returns the eval requisito with the primary key.
	 *
	 * @param evalRequisitoPK the primary key of the eval requisito
	 * @return the eval requisito
	 * @throws PortalException if a eval requisito with the primary key could not be found
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
				getEvalRequisito(
					com.legalplus.liferay.portlet.legalplus.manager.service.
						persistence.EvalRequisitoPK evalRequisitoPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getEvalRequisito(evalRequisitoPK);
	}

	public static java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito>
			getEvalRequisitoByFechaComp(java.util.Date fecha, long compId) {

		return getService().getEvalRequisitoByFechaComp(fecha, compId);
	}

	/**
	 * Returns a range of all the eval requisitos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.EvalRequisitoModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of eval requisitos
	 * @param end the upper bound of the range of eval requisitos (not inclusive)
	 * @return the range of eval requisitos
	 */
	public static java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito>
			getEvalRequisitos(int start, int end) {

		return getService().getEvalRequisitos(start, end);
	}

	/**
	 * Returns the number of eval requisitos.
	 *
	 * @return the number of eval requisitos
	 */
	public static int getEvalRequisitosCount() {
		return getService().getEvalRequisitosCount();
	}

	public static String getEvaluacionesRequisitos(
		Long compId, String legislacionId) {

		return getService().getEvaluacionesRequisitos(compId, legislacionId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
			getLastEvalRequisito(
				Long compId, String legislacionId, String requisitoId) {

		return getService().getLastEvalRequisito(
			compId, legislacionId, requisitoId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List
		<com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito>
			getPeriodoQuincenalByFechaComp(long compId) {

		return getService().getPeriodoQuincenalByFechaComp(compId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static Integer isEvalRequisitoVersionCompleted(
		long compId, String legislacionId, long version) {

		return getService().isEvalRequisitoVersionCompleted(
			compId, legislacionId, version);
	}

	/**
	 * Updates the eval requisito in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EvalRequisitoLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param evalRequisito the eval requisito
	 * @return the eval requisito that was updated
	 */
	public static
		com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito
			updateEvalRequisito(
				com.legalplus.liferay.portlet.legalplus.manager.model.
					EvalRequisito evalRequisito) {

		return getService().updateEvalRequisito(evalRequisito);
	}

	public static EvalRequisitoLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<EvalRequisitoLocalService, EvalRequisitoLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			EvalRequisitoLocalService.class);

		ServiceTracker<EvalRequisitoLocalService, EvalRequisitoLocalService>
			serviceTracker =
				new ServiceTracker
					<EvalRequisitoLocalService, EvalRequisitoLocalService>(
						bundle.getBundleContext(),
						EvalRequisitoLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}