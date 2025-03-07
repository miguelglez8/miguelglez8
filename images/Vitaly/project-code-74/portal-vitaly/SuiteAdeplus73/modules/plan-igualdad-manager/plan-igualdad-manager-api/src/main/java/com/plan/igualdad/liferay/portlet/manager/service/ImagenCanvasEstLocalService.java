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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
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

import com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.ImagenCanvasEstPK;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for ImagenCanvasEst. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ImagenCanvasEstLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface ImagenCanvasEstLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.plan.igualdad.liferay.portlet.manager.service.impl.ImagenCanvasEstLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the imagen canvas est local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link ImagenCanvasEstLocalServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Adds the imagen canvas est to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImagenCanvasEstLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param imagenCanvasEst the imagen canvas est
	 * @return the imagen canvas est that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public ImagenCanvasEst addImagenCanvasEst(ImagenCanvasEst imagenCanvasEst);

	public int countImagenesCanvasBySection(
		long compId, long versionId, long seccionId, String canvasId);

	/**
	 * Creates a new imagen canvas est with the primary key. Does not add the imagen canvas est to the database.
	 *
	 * @param imagenCanvasEstPK the primary key for the new imagen canvas est
	 * @return the new imagen canvas est
	 */
	@Transactional(enabled = false)
	public ImagenCanvasEst createImagenCanvasEst(
		ImagenCanvasEstPK imagenCanvasEstPK);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Deletes the imagen canvas est from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImagenCanvasEstLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param imagenCanvasEst the imagen canvas est
	 * @return the imagen canvas est that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public ImagenCanvasEst deleteImagenCanvasEst(
		ImagenCanvasEst imagenCanvasEst);

	/**
	 * Deletes the imagen canvas est with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImagenCanvasEstLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param imagenCanvasEstPK the primary key of the imagen canvas est
	 * @return the imagen canvas est that was removed
	 * @throws PortalException if a imagen canvas est with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public ImagenCanvasEst deleteImagenCanvasEst(
			ImagenCanvasEstPK imagenCanvasEstPK)
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.ImagenCanvasEstModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.ImagenCanvasEstModelImpl</code>.
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
	public List<ImagenCanvasEst> fetchImagenCanvas(long compId, long versionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ImagenCanvasEst fetchImagenCanvasEst(
		ImagenCanvasEstPK imagenCanvasEstPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ImagenCanvasEst> fetchImagenesCanvasBySection(
		long compId, long versionId, long seccionId, String canvasId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Returns the imagen canvas est with the primary key.
	 *
	 * @param imagenCanvasEstPK the primary key of the imagen canvas est
	 * @return the imagen canvas est
	 * @throws PortalException if a imagen canvas est with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ImagenCanvasEst getImagenCanvasEst(
			ImagenCanvasEstPK imagenCanvasEstPK)
		throws PortalException;

	/**
	 * Returns a range of all the imagen canvas ests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.plan.igualdad.liferay.portlet.manager.model.impl.ImagenCanvasEstModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of imagen canvas ests
	 * @param end the upper bound of the range of imagen canvas ests (not inclusive)
	 * @return the range of imagen canvas ests
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ImagenCanvasEst> getImagenCanvasEsts(int start, int end);

	/**
	 * Returns the number of imagen canvas ests.
	 *
	 * @return the number of imagen canvas ests
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getImagenCanvasEstsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

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
	 * Updates the imagen canvas est in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImagenCanvasEstLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param imagenCanvasEst the imagen canvas est
	 * @return the imagen canvas est that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public ImagenCanvasEst updateImagenCanvasEst(
		ImagenCanvasEst imagenCanvasEst);

}