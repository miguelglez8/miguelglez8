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

import com.legalplus.liferay.portlet.legalplus.manager.model.LegislacionCNAES;
import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.LegislacionCNAESPK;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for LegislacionCNAES. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see LegislacionCNAESLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface LegislacionCNAESLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.legalplus.liferay.portlet.legalplus.manager.service.impl.LegislacionCNAESLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the legislacion cnaes local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link LegislacionCNAESLocalServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Adds the legislacion cnaes to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LegislacionCNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param legislacionCNAES the legislacion cnaes
	 * @return the legislacion cnaes that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public LegislacionCNAES addLegislacionCNAES(
		LegislacionCNAES legislacionCNAES);

	public LegislacionCNAES addLegislacionCNAES(
		String legislacionId, String cnaeId);

	/**
	 * Creates a new legislacion cnaes with the primary key. Does not add the legislacion cnaes to the database.
	 *
	 * @param legislacionCNAESPK the primary key for the new legislacion cnaes
	 * @return the new legislacion cnaes
	 */
	@Transactional(enabled = false)
	public LegislacionCNAES createLegislacionCNAES(
		LegislacionCNAESPK legislacionCNAESPK);

	/**
	 * Deletes the legislacion cnaes from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LegislacionCNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param legislacionCNAES the legislacion cnaes
	 * @return the legislacion cnaes that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public LegislacionCNAES deleteLegislacionCNAES(
		LegislacionCNAES legislacionCNAES);

	/**
	 * Deletes the legislacion cnaes with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LegislacionCNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param legislacionCNAESPK the primary key of the legislacion cnaes
	 * @return the legislacion cnaes that was removed
	 * @throws PortalException if a legislacion cnaes with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public LegislacionCNAES deleteLegislacionCNAES(
			LegislacionCNAESPK legislacionCNAESPK)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.LegislacionCNAESModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.LegislacionCNAESModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LegislacionCNAES fetchByLegislacionCNAE(
		String legislacionId, String cnaeId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LegislacionCNAES fetchLegislacionCNAES(
		LegislacionCNAESPK legislacionCNAESPK);

	public List<LegislacionCNAES> findByLegislacion(String legislacionId);

	/**
	 * Returns the legislacion cnaes with the primary key.
	 *
	 * @param legislacionCNAESPK the primary key of the legislacion cnaes
	 * @return the legislacion cnaes
	 * @throws PortalException if a legislacion cnaes with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LegislacionCNAES getLegislacionCNAES(
			LegislacionCNAESPK legislacionCNAESPK)
		throws PortalException;

	/**
	 * Returns a range of all the legislacion cnaeses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.LegislacionCNAESModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of legislacion cnaeses
	 * @param end the upper bound of the range of legislacion cnaeses (not inclusive)
	 * @return the range of legislacion cnaeses
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LegislacionCNAES> getLegislacionCNAESs(int start, int end);

	/**
	 * Returns the number of legislacion cnaeses.
	 *
	 * @return the number of legislacion cnaeses
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLegislacionCNAESsCount();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Updates the legislacion cnaes in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LegislacionCNAESLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param legislacionCNAES the legislacion cnaes
	 * @return the legislacion cnaes that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public LegislacionCNAES updateLegislacionCNAES(
		LegislacionCNAES legislacionCNAES);

}