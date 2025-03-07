package com.legalplus.liferay.portlet.web.legislaciones.portlet.command;

import com.adeplus.liferay.portlet.suite.manager.model.*;
import com.adeplus.liferay.portlet.suite.manager.service.*;
import com.legalplus.liferay.portlet.commons.web.constants.LegalPlusCommonsPortletKeys;
import com.legalplus.liferay.portlet.commons.web.role.LegalplusRoleUtil;
import com.legalplus.liferay.portlet.commons.web.version.LegalPlusVersion;
import com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento;
import com.legalplus.liferay.portlet.legalplus.manager.model.CCAA;
import com.legalplus.liferay.portlet.legalplus.manager.model.ContratoCompany;
import com.legalplus.liferay.portlet.legalplus.manager.service.AyuntamientoLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.CCAALocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.ContratoCompanyLocalServiceUtil;
import com.legalplus.liferay.portlet.web.legislaciones.enums.FamiliaNormativa;
import com.legalplus.liferay.portlet.web.legislaciones.constants.WebLegislacionesPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + WebLegislacionesPortletKeys.WEBLEGISLACIONES,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class GoLegislacionesRenderCommand  implements MVCRenderCommand {

    private static Log _log = LogFactoryUtil.getLog(GoLegislacionesRenderCommand.class);

    public static final String FIELD_SEPARATOR = ";";

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        try {

            ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
            ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
            String compId;
            String [] newParam;
            long clientId = 0; long contractId = 0;
            if (LegalplusRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                    || LegalplusRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {

                HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
                compId = request.getParameter(WebLegislacionesPortletKeys.QUERY_PARAM);
                if (Validator.isNull(compId))  return "/error.jsp";

            } else {

                UserCompany company = LegalplusRoleUtil.getUserDefaultCompany(themeDisplay.getUserId());
                compId = String.valueOf(company.getCompId());

            }

            //Licencia
            Application application = ApplicationLocalServiceUtil.getApplicationByName(LegalPlusCommonsPortletKeys.PROPERTY_APP_LEGAL);
            //CompApplication compApp = CompApplicationLocalServiceUtil.getCompanyApplication(Long.valueOf(compId), application.getApplicationId());
            //ApplicationLicense license = ApplicationLicenseLocalServiceUtil.getApplicationLicense(compApp.getLicenseId());
            ApplicationLicense license = LegalplusRoleUtil.getLicenseNew(Long.parseLong(compId));




            renderRequest.setAttribute(WebLegislacionesPortletKeys.LICENCE, license.getLicenseId());
            renderRequest.setAttribute(WebLegislacionesPortletKeys.LICENCE_OPTIMO_ID, 1);

            ContratoCompany contrato = ContratoCompanyLocalServiceUtil.fetchByCompId(Long.valueOf(compId));

            if (Validator.isNotNull(contrato)) {

                //FAMILIAS
                String familias = contrato.getFamilia();
                List<FamiliaNormativa> familiaList = new ArrayList<>();

                if (Validator.isNotNull(familias)) {
                    for (String familia : familias.split(FIELD_SEPARATOR)) {
                        FamiliaNormativa curFamilia = FamiliaNormativa.getFamilia(familia);
                        familiaList.add(curFamilia);
                    }
                }

                //CCAA
                String ccaa = contrato.getCcaa();
                List<CCAA> ccaaList = new ArrayList<>();

                if (Validator.isNotNull(ccaa)) {
                    for (String ca : ccaa.split(FIELD_SEPARATOR)) {
                        CCAA curCCAA = CCAALocalServiceUtil.getCCAA(Long.valueOf(ca));
                        ccaaList.add(curCCAA);
                    }
                }

                //AYUNTAMIENTOS
                String aytos = contrato.getAyuntamiento();
                List<Ayuntamiento> aytoList = new ArrayList<>();

                if (Validator.isNotNull(aytos)) {
                    for (String ayto : aytos.split(FIELD_SEPARATOR)) {
                        Ayuntamiento curAyto = AyuntamientoLocalServiceUtil.findByAyuntamiento(Long.valueOf(ayto));
                        aytoList.add(curAyto);
                    }
                }

                renderRequest.setAttribute(WebLegislacionesPortletKeys.FAMILIA_LIST, familiaList);
                renderRequest.setAttribute(WebLegislacionesPortletKeys.CCAA_LIST, ccaaList);
                renderRequest.setAttribute(WebLegislacionesPortletKeys.AYTO_LIST, aytoList);

            }
            renderRequest.setAttribute(WebLegislacionesPortletKeys.COMPANY_ID, compId);
            PortletSession portletSession = renderRequest.getPortletSession();
            portletSession.setAttribute(WebLegislacionesPortletKeys.COMPANY_ID, Long.valueOf(compId), PortletSession.PORTLET_SCOPE);

            String url = themeDisplay.getScopeGroup().getDisplayURL(themeDisplay, false);
            String legislacionesUrl = url + LanguageUtil.get(bundle, "tab.empresa.legislaciones") + compId;
            String contratoUrl = url + LanguageUtil.get(bundle, "tab.empresa.contrato") + compId;
            String calendarioUrl = url + LanguageUtil.get(bundle, "tab.empresa.calendario") + compId;
            String empresasUrl = url + LanguageUtil.get(bundle, "tab.empresa.back");

            renderRequest.setAttribute(WebLegislacionesPortletKeys.TAB_ACTIVE, WebLegislacionesPortletKeys.TAB_LEGISLACIONES);
            renderRequest.setAttribute(WebLegislacionesPortletKeys.TAB_LEGISLACIONES, legislacionesUrl);
            renderRequest.setAttribute(WebLegislacionesPortletKeys.TAB_CONTRACT, contratoUrl);
            renderRequest.setAttribute(WebLegislacionesPortletKeys.TAB_CALENDARIO, calendarioUrl);
            renderRequest.setAttribute(WebLegislacionesPortletKeys.TAB_BACK, empresasUrl);

            //URL evaluacion
            String evaluacionUrl = url + LanguageUtil.get(bundle, "empresa.legislaciones.view.evaluacion.url");
            renderRequest.setAttribute(WebLegislacionesPortletKeys.EVALUACION_URL, evaluacionUrl);

            return "/view.jsp";

        } catch (PortalException e) {
            _log.error(e, e);
            throw new PortletException(e);
        }
    }

}
