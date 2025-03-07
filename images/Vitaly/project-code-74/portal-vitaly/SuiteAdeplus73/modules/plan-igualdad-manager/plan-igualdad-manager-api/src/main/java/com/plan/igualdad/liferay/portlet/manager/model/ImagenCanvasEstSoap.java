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

import com.plan.igualdad.liferay.portlet.manager.service.persistence.ImagenCanvasEstPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.plan.igualdad.liferay.portlet.manager.service.http.ImagenCanvasEstServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ImagenCanvasEstSoap implements Serializable {

	public static ImagenCanvasEstSoap toSoapModel(ImagenCanvasEst model) {
		ImagenCanvasEstSoap soapModel = new ImagenCanvasEstSoap();

		soapModel.setCompId(model.getCompId());
		soapModel.setVersionId(model.getVersionId());
		soapModel.setSeccionId(model.getSeccionId());
		soapModel.setImgCanvasId(model.getImgCanvasId());
		soapModel.setParteImg(model.getParteImg());
		soapModel.setCanvasImage(model.getCanvasImage());

		return soapModel;
	}

	public static ImagenCanvasEstSoap[] toSoapModels(ImagenCanvasEst[] models) {
		ImagenCanvasEstSoap[] soapModels =
			new ImagenCanvasEstSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ImagenCanvasEstSoap[][] toSoapModels(
		ImagenCanvasEst[][] models) {

		ImagenCanvasEstSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ImagenCanvasEstSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ImagenCanvasEstSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ImagenCanvasEstSoap[] toSoapModels(
		List<ImagenCanvasEst> models) {

		List<ImagenCanvasEstSoap> soapModels =
			new ArrayList<ImagenCanvasEstSoap>(models.size());

		for (ImagenCanvasEst model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ImagenCanvasEstSoap[soapModels.size()]);
	}

	public ImagenCanvasEstSoap() {
	}

	public ImagenCanvasEstPK getPrimaryKey() {
		return new ImagenCanvasEstPK(
			_compId, _versionId, _seccionId, _imgCanvasId, _parteImg);
	}

	public void setPrimaryKey(ImagenCanvasEstPK pk) {
		setCompId(pk.compId);
		setVersionId(pk.versionId);
		setSeccionId(pk.seccionId);
		setImgCanvasId(pk.imgCanvasId);
		setParteImg(pk.parteImg);
	}

	public long getCompId() {
		return _compId;
	}

	public void setCompId(long compId) {
		_compId = compId;
	}

	public long getVersionId() {
		return _versionId;
	}

	public void setVersionId(long versionId) {
		_versionId = versionId;
	}

	public long getSeccionId() {
		return _seccionId;
	}

	public void setSeccionId(long seccionId) {
		_seccionId = seccionId;
	}

	public String getImgCanvasId() {
		return _imgCanvasId;
	}

	public void setImgCanvasId(String imgCanvasId) {
		_imgCanvasId = imgCanvasId;
	}

	public long getParteImg() {
		return _parteImg;
	}

	public void setParteImg(long parteImg) {
		_parteImg = parteImg;
	}

	public String getCanvasImage() {
		return _canvasImage;
	}

	public void setCanvasImage(String canvasImage) {
		_canvasImage = canvasImage;
	}

	private long _compId;
	private long _versionId;
	private long _seccionId;
	private String _imgCanvasId;
	private long _parteImg;
	private String _canvasImage;

}