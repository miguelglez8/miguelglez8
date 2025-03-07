package com.legalplus.liferay.portlet.web.legislaciones.portlet.command;

import com.legalplus.liferay.portlet.legalplus.manager.model.ContratoCompany;
import com.legalplus.liferay.portlet.legalplus.manager.service.ContratoCompanyLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.LegislacionLocalServiceUtil;
import com.legalplus.liferay.portlet.web.legislaciones.constants.WebLegislacionesPortletKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + WebLegislacionesPortletKeys.WEBLEGISLACIONES,
                "mvc.command.name=buscarLegislaciones"
        },
        service = MVCResourceCommand.class
)
public class FindLegislacionResourceCommand extends BaseMVCResourceCommand {

    public static final String PARAM_ACTIVO         = "activo";
    public static final String PARAM_OCULTO         = "oculto";
    public static final String PARAM_CUMPLIMIENTO   = "cumplimiento";
    public static final String PARAM_TIPO           = "tipo";
    public static final String PARAM_FAMILIA        = "familia";
    public static final String PARAM_AMBITO         = "ambito";
    public static final String PARAM_CCAA           = "ccaa";
    public static final String PARAM_AYUNTAMIENTO   = "ayuntamiento";
    public static final String PARAM_FECHADESDE     = "fechaDesde";
    public static final String PARAM_FECHAHASTA     = "fechaHasta";
    public static final String PARAM_TEXTO          = "texto";
    public static final String PARAM_ETIQUETAS		= "etiquetas";

    private static Log _log = LogFactoryUtil.getLog(FindLegislacionResourceCommand.class);
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        PortletSession portletSession = resourceRequest.getPortletSession();
        Long compId = ParamUtil.getLong(resourceRequest,WebLegislacionesPortletKeys.COMPANY_ID,0);

        boolean oculto= false;
        String activo="0";
        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        //Debemos recoger el parametro de la siguiente forma ya que es posible que este vuelva nulo


            if (Validator.isNotNull(httpReq.getParameter(PARAM_OCULTO))) {
                oculto = Boolean.parseBoolean(httpReq.getParameter(PARAM_OCULTO));
            }
            if (Validator.isNotNull(httpReq.getParameter(PARAM_ACTIVO))) {
                activo = httpReq.getParameter(PARAM_ACTIVO);
            }

            JSONObject filtros = JSONFactoryUtil.createJSONObject();
            filtros.put(PARAM_ACTIVO, activo);
            filtros.put(PARAM_OCULTO, oculto);
            filtros.put(PARAM_CUMPLIMIENTO, httpReq.getParameter(PARAM_CUMPLIMIENTO));
            filtros.put(PARAM_TIPO, httpReq.getParameter(PARAM_TIPO));
            filtros.put(PARAM_FAMILIA, httpReq.getParameter(PARAM_FAMILIA));
            filtros.put(PARAM_AMBITO, httpReq.getParameter(PARAM_AMBITO));
            filtros.put(PARAM_CCAA, httpReq.getParameter(PARAM_CCAA));
            filtros.put(PARAM_AYUNTAMIENTO, httpReq.getParameter(PARAM_AYUNTAMIENTO));
            filtros.put(PARAM_FECHADESDE, httpReq.getParameter(PARAM_FECHADESDE));
            filtros.put(PARAM_FECHAHASTA, httpReq.getParameter(PARAM_FECHAHASTA));
            filtros.put(PARAM_TEXTO, httpReq.getParameter(PARAM_TEXTO));
            filtros.put(PARAM_ETIQUETAS, httpReq.getParameter(PARAM_ETIQUETAS));

            String resultados = LegislacionLocalServiceUtil.getLegislacionesEmpresaFiltros(compId, filtros.toString(), themeDisplay.getLanguageId());

        JSONObject json = JSONFactoryUtil.createJSONObject(resultados);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());

    }
}

