package com.plan.igualdad.liferay.generar.informe.utils;

import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany;
import com.legalplus.liferay.portlet.legalplus.manager.service.ConsultorCompanyLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.generar.informe.constants.PlanIgualdadGenerarInformePortletKeys;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.portlet.ResourceRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GeneralUtils {
	private static Log _log = LogFactoryUtil.getLog(GeneralUtils.class);
	
	private static final String ESPACIOS= "&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; ";
	private static final String POINT= ".";
			
	public static String deleteHTML(String texto, boolean guion, boolean point) {
		String text = texto.replaceAll("strong", "b").replaceAll("</em>", "</i>").replaceAll("</h2>", ESPACIOS + "</h2>").replaceAll("</h3>", ESPACIOS + "</h3>").replaceAll("</h4>", ESPACIOS + "</h4>").replaceAll("</h5>", ESPACIOS + "</h5>").replaceAll("</h1>", ESPACIOS + "</h1>").replaceAll("<em>", "<i>").replaceAll("</p>", ESPACIOS + "</p>");
		if(guion) {
			if(text.substring(0, 7).contains("<p>")) {
				text = text.replaceFirst("<p>", "<p>-");
			}else if(text.substring(0, 7).contains("<h1>")) {
				text = text.replaceFirst("<h1>", "<h1>-");
			}else if(text.substring(0, 7).contains("<h2>")) {
				text = text.replaceFirst("<h2>", "<h2>-");
			}else if(text.substring(0, 7).contains("<h3>")) {
				text = text.replaceFirst("<h3>", "<h3>-");
			}else if(text.substring(0, 7).contains("<h4>")) {
				text = text.replaceFirst("<h4>", "<h4>-");
			}else if(text.substring(0, 7).contains("<h5>")) {
				text = text.replaceFirst("<h5>", "<h5>-");
			}else {
				text = "-" + text;
			}
		}
		
		if(text.contains("\\r\\n") || text.contains("\\n")) {
			text = "<p>" + text.replace("\\r\\n", ESPACIOS+"</p><p>").replace("\\n", ESPACIOS+"</p><p>") + ESPACIOS+(point ? POINT : "") + "</p>";
		}

		return text;
	}
	
	public static Map<String, Object> setParameterComentario (Long sectionId, String comentario, String parte) throws PortalException, IOException {
		Map<String, Object> parameter = new HashMap<>();
		if(!comentario.isEmpty() && comentario.contains("data:image")) {
			parameter.put(PlanIgualdadGenerarInformePortletKeys.COMENTARIO+ sectionId + parte+ "img", getImagen(comentario));
			parameter.put(PlanIgualdadGenerarInformePortletKeys.COMENTARIO+ sectionId + parte+ "imgTag", comentario);
		}else {
			parameter.put(PlanIgualdadGenerarInformePortletKeys.COMENTARIO+ sectionId+parte, !comentario.isEmpty() ? comentario : null);
		}
		return parameter;
	}
	
	public static BufferedImage getImagen(String base64) throws PortalException, IOException {
		BufferedImage bufferedImage = null;
		if(Validator.isNotNull(base64) && !base64.isEmpty()) {
			String base64Image = base64.split(",")[1];
			byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
			bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
		}
		return bufferedImage;
	}
	
	/**
	 * 
	 * @param themeDisplay
	 * @param nameParentFolder
	 * @param nameFolder
	 * @param nameLogo
	 * @return
	 * @throws PortalException
	 * @throws IOException
	 */
	public static BufferedImage getLogo(Long compId, ThemeDisplay themeDisplay) throws PortalException, IOException {
		BufferedImage bufferedImage = null;
		Comp comp = CompLocalServiceUtil.fetchComp(compId);
		FileEntry fileEntry = null;
		
		if(Validator.isNotNull(comp.getLogo()) && comp.getLogo()>0) {
			fileEntry = DLAppServiceUtil.getFileEntry(comp.getLogo());
			bufferedImage = ImageIO.read(fileEntry.getContentStream());
		}

		return bufferedImage;
	}

	/**
	 * 
	 * @param parameter
	 * @return
	 */
	public static String checkParameter(String parameter) {
		return parameter != null && !parameter.isEmpty() ? parameter : PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
	}
	
	/**
	 * getConsultor
	 * @param compId
	 * @return
	 */
	public static String getConsultor(Long compId) {
		List<ConsultorCompany> consultores = ConsultorCompanyLocalServiceUtil.fetchByCompId(compId);
        String consultor = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
        if(Validator.isNotNull(consultores) && !consultores.isEmpty()) {
        	int countConsultor = 0;
        	for(ConsultorCompany consultorCompany : consultores) {
        		if(countConsultor>0) {
        			consultor = consultor + ", " + consultorCompany.getName() + " " + consultorCompany.getLastName();
        		}else {
        			consultor = consultorCompany.getName() + " " + consultorCompany.getLastName();
        		}
        		countConsultor++;
        	}
        }
        return consultor;
	}
	
	/**
	 * 
	 * @param resourceRequest
	 */
	public static void saveComment(ResourceRequest resourceRequest) {
        String problemasDetectados = ParamUtil.getString(resourceRequest, PlanIgualdadGenerarInformePortletKeys.PROBLEMAS_DETECTADOS);
        String problemasName = ParamUtil.getString(resourceRequest, "name");
        
        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        _log.debug("problemasDetectados: " + problemasDetectados + " | problemasName: " + problemasName);

        HttpSession pSession = httpReq.getSession();
        pSession.setAttribute(problemasName, problemasDetectados);
	}
	
	/**
	 * 
	 * @param resourceRequest
	 */
	public static void saveExtension(ResourceRequest resourceRequest) {
        String saveExtension = ParamUtil.getString(resourceRequest, "extension");
        
        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        _log.debug("saveExtension: " + saveExtension);

        HttpSession pSession = httpReq.getSession();
        pSession.setAttribute("extensionDownload", saveExtension);
	}
	
	public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
