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

package com.adeplus.liferay.portlet.suite.manager.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AuditLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AuditLocalService
 * @generated
 */
public class AuditLocalServiceWrapper
	implements AuditLocalService, ServiceWrapper<AuditLocalService> {

	public AuditLocalServiceWrapper(AuditLocalService auditLocalService) {
		_auditLocalService = auditLocalService;
	}

	/**
	 * Adds the audit to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuditLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param audit the audit
	 * @return the audit that was added
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.Audit addAudit(
		com.adeplus.liferay.portlet.suite.manager.model.Audit audit) {

		return _auditLocalService.addAudit(audit);
	}

	/**
	 * Creates a new audit with the primary key. Does not add the audit to the database.
	 *
	 * @param auditId the primary key for the new audit
	 * @return the new audit
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.Audit createAudit(
		long auditId) {

		return _auditLocalService.createAudit(auditId);
	}

	/**
	 * Deletes the audit from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuditLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param audit the audit
	 * @return the audit that was removed
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.Audit deleteAudit(
		com.adeplus.liferay.portlet.suite.manager.model.Audit audit) {

		return _auditLocalService.deleteAudit(audit);
	}

	/**
	 * Deletes the audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuditLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param auditId the primary key of the audit
	 * @return the audit that was removed
	 * @throws PortalException if a audit with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.Audit deleteAudit(
			long auditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditLocalService.deleteAudit(auditId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _auditLocalService.dynamicQuery();
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

		return _auditLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.AuditModelImpl</code>.
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

		return _auditLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.AuditModelImpl</code>.
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

		return _auditLocalService.dynamicQuery(
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

		return _auditLocalService.dynamicQueryCount(dynamicQuery);
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

		return _auditLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.Audit fetchAudit(
		long auditId) {

		return _auditLocalService.fetchAudit(auditId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _auditLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the audit with the primary key.
	 *
	 * @param auditId the primary key of the audit
	 * @return the audit
	 * @throws PortalException if a audit with the primary key could not be found
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.Audit getAudit(
			long auditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditLocalService.getAudit(auditId);
	}

	/**
	 * Returns a range of all the audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.adeplus.liferay.portlet.suite.manager.model.impl.AuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audits
	 * @param end the upper bound of the range of audits (not inclusive)
	 * @return the range of audits
	 */
	@Override
	public java.util.List<com.adeplus.liferay.portlet.suite.manager.model.Audit>
		getAudits(int start, int end) {

		return _auditLocalService.getAudits(start, end);
	}

	/**
	 * Returns the number of audits.
	 *
	 * @return the number of audits
	 */
	@Override
	public int getAuditsCount() {
		return _auditLocalService.getAuditsCount();
	}

	@Override
	public java.util.List<com.adeplus.liferay.portlet.suite.manager.model.Audit>
		getImportAuditList() {

		return _auditLocalService.getImportAuditList();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _auditLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _auditLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuditLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param audit the audit
	 * @return the audit that was updated
	 */
	@Override
	public com.adeplus.liferay.portlet.suite.manager.model.Audit updateAudit(
		com.adeplus.liferay.portlet.suite.manager.model.Audit audit) {

		return _auditLocalService.updateAudit(audit);
	}

	@Override
	public AuditLocalService getWrappedService() {
		return _auditLocalService;
	}

	@Override
	public void setWrappedService(AuditLocalService auditLocalService) {
		_auditLocalService = auditLocalService;
	}

	private AuditLocalService _auditLocalService;

}