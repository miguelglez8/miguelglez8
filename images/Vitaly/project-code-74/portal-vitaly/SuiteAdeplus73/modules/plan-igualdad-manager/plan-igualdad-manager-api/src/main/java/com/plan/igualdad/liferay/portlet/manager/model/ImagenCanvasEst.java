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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the ImagenCanvasEst service. Represents a row in the &quot;planigualdad_ImagenCanvasEst&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ImagenCanvasEstModel
 * @generated
 */
@ImplementationClassName(
	"com.plan.igualdad.liferay.portlet.manager.model.impl.ImagenCanvasEstImpl"
)
@ProviderType
public interface ImagenCanvasEst extends ImagenCanvasEstModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.plan.igualdad.liferay.portlet.manager.model.impl.ImagenCanvasEstImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ImagenCanvasEst, Long> COMP_ID_ACCESSOR =
		new Accessor<ImagenCanvasEst, Long>() {

			@Override
			public Long get(ImagenCanvasEst imagenCanvasEst) {
				return imagenCanvasEst.getCompId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ImagenCanvasEst> getTypeClass() {
				return ImagenCanvasEst.class;
			}

		};
	public static final Accessor<ImagenCanvasEst, Long> VERSION_ID_ACCESSOR =
		new Accessor<ImagenCanvasEst, Long>() {

			@Override
			public Long get(ImagenCanvasEst imagenCanvasEst) {
				return imagenCanvasEst.getVersionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ImagenCanvasEst> getTypeClass() {
				return ImagenCanvasEst.class;
			}

		};
	public static final Accessor<ImagenCanvasEst, Long> SECCION_ID_ACCESSOR =
		new Accessor<ImagenCanvasEst, Long>() {

			@Override
			public Long get(ImagenCanvasEst imagenCanvasEst) {
				return imagenCanvasEst.getSeccionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ImagenCanvasEst> getTypeClass() {
				return ImagenCanvasEst.class;
			}

		};
	public static final Accessor<ImagenCanvasEst, String>
		IMG_CANVAS_ID_ACCESSOR = new Accessor<ImagenCanvasEst, String>() {

			@Override
			public String get(ImagenCanvasEst imagenCanvasEst) {
				return imagenCanvasEst.getImgCanvasId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<ImagenCanvasEst> getTypeClass() {
				return ImagenCanvasEst.class;
			}

		};
	public static final Accessor<ImagenCanvasEst, Long> PARTE_IMG_ACCESSOR =
		new Accessor<ImagenCanvasEst, Long>() {

			@Override
			public Long get(ImagenCanvasEst imagenCanvasEst) {
				return imagenCanvasEst.getParteImg();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ImagenCanvasEst> getTypeClass() {
				return ImagenCanvasEst.class;
			}

		};

}