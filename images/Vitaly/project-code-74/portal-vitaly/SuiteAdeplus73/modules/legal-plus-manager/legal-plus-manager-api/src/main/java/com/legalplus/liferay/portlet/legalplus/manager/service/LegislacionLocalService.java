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

import com.legalplus.liferay.portlet.legalplus.manager.model.*;
import com.legalplus.liferay.portlet.legalplus.manager.model.Legislacion;

import com.liferay.portal.kernel.dao.orm.*;
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

import java.awt.*;

import java.io.Serializable;

import java.util.*;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for Legislacion. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see LegislacionLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface LegislacionLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.legalplus.liferay.portlet.legalplus.manager.service.impl.LegislacionLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the legislacion local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link LegislacionLocalServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Adds the legislacion to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LegislacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param legislacion the legislacion
	 * @return the legislacion that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Legislacion addLegislacion(Legislacion legislacion);

	/**
	 * Creates a new legislacion with the primary key. Does not add the legislacion to the database.
	 *
	 * @param legislacionId the primary key for the new legislacion
	 * @return the new legislacion
	 */
	@Transactional(enabled = false)
	public Legislacion createLegislacion(String legislacionId);

	/**
	 * Deletes the legislacion from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LegislacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param legislacion the legislacion
	 * @return the legislacion that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public Legislacion deleteLegislacion(Legislacion legislacion);

	/**
	 * Deletes the legislacion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LegislacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param legislacionId the primary key of the legislacion
	 * @return the legislacion that was removed
	 * @throws PortalException if a legislacion with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public Legislacion deleteLegislacion(String legislacionId)
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.LegislacionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.LegislacionModelImpl</code>.
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
	public Legislacion fetchLegislacion(String legislacionId);

	/**
	 * Returns the legislacion with the primary key.
	 *
	 * @param legislacionId the primary key of the legislacion
	 * @return the legislacion
	 * @throws PortalException if a legislacion with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Legislacion getLegislacion(String legislacionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Legislacion> getLegislacionesByAyuntamiento(
		long ayuntamientoId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Legislacion> getLegislacionesByCCAA(long ccaaId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getLegislacionesEmpresaFiltros(
		Long compId, String filtrosJSON, String languageId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getLegislacionesFiltradas(
		String filtrosJSON, String languageId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Legislacion> getLegislacionesMonthBefore(java.util.Date date);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Legislacion> getLegislacionesUrgentes();

	/**
	 * Returns a range of all the legislacions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.legalplus.liferay.portlet.legalplus.manager.model.impl.LegislacionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of legislacions
	 * @param end the upper bound of the range of legislacions (not inclusive)
	 * @return the range of legislacions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Legislacion> getLegislacions(int start, int end);

	/**
	 * Returns the number of legislacions.
	 *
	 * @return the number of legislacions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLegislacionsCount();

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

	public boolean legislacionContainsEtiqueta(long etiquetaId);

	/**
	 * Updates the legislacion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LegislacionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param legislacion the legislacion
	 * @return the legislacion that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Legislacion updateLegislacion(Legislacion legislacion);

}