package com.plan.igualdad.liferay.portlet.estadisticas.portlet.command;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.estadisticas.constants.PlanIgualdadEstadisticasPortletKeys;
import com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst;
import com.plan.igualdad.liferay.portlet.manager.service.ImagenCanvasEstLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.ImagenCanvasEstPK;

import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadEstadisticasPortletKeys.PLANIGUALDADESTADISTICAS,
                "mvc.command.name=saveCanvas"
        },
        service = MVCResourceCommand.class
)
public class SaveCanvasResourceCommand extends BaseMVCResourceCommand{

	private static Log _log = LogFactoryUtil.getLog(SaveCanvasResourceCommand.class);
	
	@Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

        Long compId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.COMPID_PARAM);
        Long versionId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.VERSIONID_PARAM);
        String seccionId = request.getParameter(PlanIgualdadEstadisticasPortletKeys.SECCIONID_PARAM);
        String canvasImage = request.getParameter(PlanIgualdadEstadisticasPortletKeys.CANVAS_IMAGE);
        String updateCanvas = request.getParameter(PlanIgualdadEstadisticasPortletKeys.UPDATE_CANVAS);
        String nImagen = request.getParameter(PlanIgualdadEstadisticasPortletKeys.N_CANVAS);
        String partImg = request.getParameter(PlanIgualdadEstadisticasPortletKeys.PART_IMG);
        String totalPartes = request.getParameter(PlanIgualdadEstadisticasPortletKeys.TOTAL_PARTES);

        _log.info("--seccionId: " + seccionId + " | " + partImg);
        
        int countImagenes = ImagenCanvasEstLocalServiceUtil.countImagenesCanvasBySection(compId, versionId, Long.parseLong(seccionId), nImagen);
        
        if(Validator.isNotNull(totalPartes) && !totalPartes.isEmpty() && countImagenes > Integer.parseInt(totalPartes)) {
        	List<ImagenCanvasEst> listCanvas = ImagenCanvasEstLocalServiceUtil.fetchImagenesCanvasBySection(compId, versionId,  Long.parseLong(seccionId), nImagen);

        	if(Validator.isNotNull(listCanvas)) {
        		for(ImagenCanvasEst canvasEst : listCanvas) {
        			if(canvasEst.getParteImg()> Long.parseLong(totalPartes)) {
        				deleteImagenCanvasEst(canvasEst.getCompId(), canvasEst.getVersionId(), canvasEst.getSeccionId(), 
            					canvasEst.getImgCanvasId(), canvasEst.getParteImg());
        			}
        		}
        	}
        }
        
        if("0".equals(updateCanvas)) {
        	
        	ImagenCanvasEstPK imagenCanvasEstPK = new ImagenCanvasEstPK();
        	imagenCanvasEstPK.setCompId(compId);
        	imagenCanvasEstPK.setVersionId(versionId);
        	imagenCanvasEstPK.setSeccionId(Long.parseLong(seccionId));
        	imagenCanvasEstPK.setImgCanvasId(nImagen);
        	imagenCanvasEstPK.setParteImg(Long.parseLong(partImg));
        	
        	ImagenCanvasEst canvasEst =  ImagenCanvasEstLocalServiceUtil.fetchImagenCanvasEst(imagenCanvasEstPK);
        	if(Validator.isNull(canvasEst)) { 
        		canvasEst = ImagenCanvasEstLocalServiceUtil.createImagenCanvasEst(imagenCanvasEstPK);
        	}
        	
        	canvasEst.setCanvasImage(canvasImage);
        	
        	ImagenCanvasEstLocalServiceUtil.updateImagenCanvasEst(canvasEst);
        	
        	_log.debug("GUARDADO");
        }else {
        	List<ImagenCanvasEst> listCanvas = ImagenCanvasEstLocalServiceUtil.fetchImagenesCanvasBySection(compId, versionId,  Long.parseLong(seccionId), nImagen);

        	if(Validator.isNotNull(listCanvas)) {
        		for(ImagenCanvasEst canvasEst : listCanvas) {
        			deleteImagenCanvasEst(canvasEst.getCompId(), canvasEst.getVersionId(), canvasEst.getSeccionId(), 
        					canvasEst.getImgCanvasId(), canvasEst.getParteImg());
        		}
        	}
        }
        
		JSONObject json = JSONFactoryUtil.createJSONObject();

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());
	}

	/**
	 * 
	 * @param compId
	 * @param versionId
	 * @param seccionId
	 * @param imgCanvasId
	 * @param parteImg
	 * @throws PortalException
	 */
	private void deleteImagenCanvasEst (long compId, long versionId, long seccionId, String imgCanvasId, long parteImg) throws PortalException {
		ImagenCanvasEstPK imagenCanvasEstPK = new ImagenCanvasEstPK();
    	imagenCanvasEstPK.setCompId(compId);
    	imagenCanvasEstPK.setVersionId(versionId);
    	imagenCanvasEstPK.setSeccionId(seccionId);
    	imagenCanvasEstPK.setImgCanvasId(imgCanvasId);
    	imagenCanvasEstPK.setParteImg(parteImg);
		ImagenCanvasEstLocalServiceUtil.deleteImagenCanvasEst(imagenCanvasEstPK);
	}
}
