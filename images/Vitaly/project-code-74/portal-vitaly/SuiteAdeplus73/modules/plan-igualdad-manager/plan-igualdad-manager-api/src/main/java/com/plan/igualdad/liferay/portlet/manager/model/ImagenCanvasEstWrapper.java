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

package com.plan.igualdad.liferay.portlet.manager.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ImagenCanvasEst}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImagenCanvasEst
 * @generated
 */
public class ImagenCanvasEstWrapper
	extends BaseModelWrapper<ImagenCanvasEst>
	implements ImagenCanvasEst, ModelWrapper<ImagenCanvasEst> {

	public ImagenCanvasEstWrapper(ImagenCanvasEst imagenCanvasEst) {
		super(imagenCanvasEst);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("compId", getCompId());
		attributes.put("versionId", getVersionId());
		attributes.put("seccionId", getSeccionId());
		attributes.put("imgCanvasId", getImgCanvasId());
		attributes.put("parteImg", getParteImg());
		attributes.put("canvasImage", getCanvasImage());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long compId = (Long)attributes.get("compId");

		if (compId != null) {
			setCompId(compId);
		}

		Long versionId = (Long)attributes.get("versionId");

		if (versionId != null) {
			setVersionId(versionId);
		}

		Long seccionId = (Long)attributes.get("seccionId");

		if (seccionId != null) {
			setSeccionId(seccionId);
		}

		String imgCanvasId = (String)attributes.get("imgCanvasId");

		if (imgCanvasId != null) {
			setImgCanvasId(imgCanvasId);
		}

		Long parteImg = (Long)attributes.get("parteImg");

		if (parteImg != null) {
			setParteImg(parteImg);
		}

		String canvasImage = (String)attributes.get("canvasImage");

		if (canvasImage != null) {
			setCanvasImage(canvasImage);
		}
	}

	/**
	 * Returns the canvas image of this imagen canvas est.
	 *
	 * @return the canvas image of this imagen canvas est
	 */
	@Override
	public String getCanvasImage() {
		return model.getCanvasImage();
	}

	/**
	 * Returns the comp ID of this imagen canvas est.
	 *
	 * @return the comp ID of this imagen canvas est
	 */
	@Override
	public long getCompId() {
		return model.getCompId();
	}

	/**
	 * Returns the img canvas ID of this imagen canvas est.
	 *
	 * @return the img canvas ID of this imagen canvas est
	 */
	@Override
	public String getImgCanvasId() {
		return model.getImgCanvasId();
	}

	/**
	 * Returns the parte img of this imagen canvas est.
	 *
	 * @return the parte img of this imagen canvas est
	 */
	@Override
	public long getParteImg() {
		return model.getParteImg();
	}

	/**
	 * Returns the primary key of this imagen canvas est.
	 *
	 * @return the primary key of this imagen canvas est
	 */
	@Override
	public com.plan.igualdad.liferay.portlet.manager.service.persistence.
		ImagenCanvasEstPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the seccion ID of this imagen canvas est.
	 *
	 * @return the seccion ID of this imagen canvas est
	 */
	@Override
	public long getSeccionId() {
		return model.getSeccionId();
	}

	/**
	 * Returns the version ID of this imagen canvas est.
	 *
	 * @return the version ID of this imagen canvas est
	 */
	@Override
	public long getVersionId() {
		return model.getVersionId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the canvas image of this imagen canvas est.
	 *
	 * @param canvasImage the canvas image of this imagen canvas est
	 */
	@Override
	public void setCanvasImage(String canvasImage) {
		model.setCanvasImage(canvasImage);
	}

	/**
	 * Sets the comp ID of this imagen canvas est.
	 *
	 * @param compId the comp ID of this imagen canvas est
	 */
	@Override
	public void setCompId(long compId) {
		model.setCompId(compId);
	}

	/**
	 * Sets the img canvas ID of this imagen canvas est.
	 *
	 * @param imgCanvasId the img canvas ID of this imagen canvas est
	 */
	@Override
	public void setImgCanvasId(String imgCanvasId) {
		model.setImgCanvasId(imgCanvasId);
	}

	/**
	 * Sets the parte img of this imagen canvas est.
	 *
	 * @param parteImg the parte img of this imagen canvas est
	 */
	@Override
	public void setParteImg(long parteImg) {
		model.setParteImg(parteImg);
	}

	/**
	 * Sets the primary key of this imagen canvas est.
	 *
	 * @param primaryKey the primary key of this imagen canvas est
	 */
	@Override
	public void setPrimaryKey(
		com.plan.igualdad.liferay.portlet.manager.service.persistence.
			ImagenCanvasEstPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the seccion ID of this imagen canvas est.
	 *
	 * @param seccionId the seccion ID of this imagen canvas est
	 */
	@Override
	public void setSeccionId(long seccionId) {
		model.setSeccionId(seccionId);
	}

	/**
	 * Sets the version ID of this imagen canvas est.
	 *
	 * @param versionId the version ID of this imagen canvas est
	 */
	@Override
	public void setVersionId(long versionId) {
		model.setVersionId(versionId);
	}

	@Override
	protected ImagenCanvasEstWrapper wrap(ImagenCanvasEst imagenCanvasEst) {
		return new ImagenCanvasEstWrapper(imagenCanvasEst);
	}

}