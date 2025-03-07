package com.legalplus.liferay.portlet.web.calendario.portlet.command;

import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense;
import com.adeplus.liferay.portlet.suite.manager.model.CompApplication;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLicenseLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.legalplus.liferay.portlet.commons.web.constants.LegalPlusCommonsPortletKeys;
import com.legalplus.liferay.portlet.commons.web.role.LegalplusRoleUtil;
import com.legalplus.liferay.portlet.legalplus.manager.model.*;
import com.legalplus.liferay.portlet.legalplus.manager.service.*;
import com.legalplus.liferay.portlet.web.calendario.constants.WebCalendarioPortletKeys;
import com.legalplus.liferay.portlet.web.calendario.enums.FamiliaNormativa;
import com.legalplus.liferay.portlet.web.calendario.portlet.WebCalendarioPortlet;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + WebCalendarioPortletKeys.WEBCALENDARIO,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class GoCalendarioRenderCommand implements MVCRenderCommand {

    private static Log _log = LogFactoryUtil.getLog(GoCalendarioRenderCommand.class);

    public static final String FIELD_SEPARATOR = ";";

    private static final String OPTIMO = "Cliente Optimo";
    private static final String ADVANCED = "Cliente Advanced";
    private static final String PREMIUM = "Cliente Premium";

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        try {

            ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
            ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

            HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
            String compId = request.getParameter(WebCalendarioPortletKeys.QUERY_PARAM);

            if (LegalplusRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                    || LegalplusRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {

                if (Validator.isNull(compId)) compId = ParamUtil.getString(renderRequest, WebCalendarioPortletKeys.COMPANY_ID);
                if (Validator.isNull(compId)) return "/error.jsp";

            } else {

                UserCompany company = LegalplusRoleUtil.getUserDefaultCompany(themeDisplay.getUserId());
                compId = String.valueOf(company.getCompId());

            }

            //ID
            Long companyId = Long.valueOf(compId);

            //DATA
            String startDate = request.getParameter(WebCalendarioPortletKeys.START_DATE_PARAM);
            String endDate = request.getParameter(WebCalendarioPortletKeys.END_DATE_PARAM);
            String eval = request.getParameter(WebCalendarioPortletKeys.EVAL_PARAM);

            renderRequest.setAttribute(WebCalendarioPortletKeys.START_DATE_PARAM, startDate);
            renderRequest.setAttribute(WebCalendarioPortletKeys.END_DATE_PARAM, endDate);
            renderRequest.setAttribute(WebCalendarioPortletKeys.EVAL_PARAM, eval);

            //Licencia
            Application application = ApplicationLocalServiceUtil.getApplicationByName(LegalPlusCommonsPortletKeys.PROPERTY_APP_LEGAL);
            //CompApplication compApp = CompApplicationLocalServiceUtil.getCompanyApplication(companyId, application.getApplicationId());
            //ApplicationLicense license = ApplicationLicenseLocalServiceUtil.getApplicationLicense(compApp.getLicenseId());
            _log.info("companyId: "+companyId);
            ApplicationLicense license =LegalplusRoleUtil.getLicenseNew(companyId);

            if (LegalplusRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                    || LegalplusRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {

                createMenuConsultor(renderRequest, themeDisplay, companyId);
            }

            if (Validator.isNotNull(license)) {

                //URL evaluaciones
                String url = LanguageUtil.get(bundle, "calendario.view.evaluaciones.url");
                String evaluacionUrl = themeDisplay.getScopeGroup().getDisplayURL(themeDisplay, false) + url;
                renderRequest.setAttribute(WebCalendarioPortletKeys.EVALUACION_URL, evaluacionUrl);

                createBuscador(renderRequest, companyId);
                renderRequest.setAttribute(WebCalendarioPortletKeys.COMPANY_ID, compId);

                if (ADVANCED.equalsIgnoreCase(license.getName()) || PREMIUM.equalsIgnoreCase(license.getName())) {
                    return "/calendario-requisitos.jsp";
                }

                if (OPTIMO.equalsIgnoreCase(license.getName())) {
                    return "/calendario-legislaciones.jsp";
                }

            }

            return "/view.jsp";
        } catch (Exception e) {
            _log.error(e, e);
            throw new PortletException(e);
        }
    }

    private void createMenuConsultor(RenderRequest renderRequest, ThemeDisplay themeDisplay, Long compId) {

        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

        String url = themeDisplay.getScopeGroup().getDisplayURL(themeDisplay, false);
        String legislacionesUrl = url + LanguageUtil.get(bundle, "/legislacion-consultor?query=") + compId;
        String contratoUrl = url + LanguageUtil.get(bundle, "/contrato-consultor?query=") + compId;
        String calendarioUrl = url + LanguageUtil.get(bundle, "/calendario-consultor?query=") + compId;
        String empresasUrl = url + LanguageUtil.get(bundle, "/empresas");

        renderRequest.setAttribute(WebCalendarioPortletKeys.TAB_ACTIVE, WebCalendarioPortletKeys.TAB_CALENDARIO);
        renderRequest.setAttribute(WebCalendarioPortletKeys.TAB_LEGISLACIONES, legislacionesUrl);
        renderRequest.setAttribute(WebCalendarioPortletKeys.TAB_CONTRACT, contratoUrl);
        renderRequest.setAttribute(WebCalendarioPortletKeys.TAB_CALENDARIO, calendarioUrl);
        renderRequest.setAttribute(WebCalendarioPortletKeys.TAB_BACK, empresasUrl);
    }

    private void createBuscador(RenderRequest renderRequest, Long compId) throws PortletException {
        try {
            ContratoCompany contrato = ContratoCompanyLocalServiceUtil.fetchByCompId(compId);

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
                        CCAA curCCAA = CCAALocalServiceUtil.getCCAA(Long.parseLong(ca));
                        ccaaList.add(curCCAA);
                    }
                }

                //AYUNTAMIENTOS
                String aytos = contrato.getAyuntamiento();
                List<Ayuntamiento> aytoList = new ArrayList<>();

                if (Validator.isNotNull(aytos)) {
                    for (String ayto : aytos.split(FIELD_SEPARATOR)) {
                        Ayuntamiento curAyto = AyuntamientoLocalServiceUtil.findByAyuntamiento(Long.parseLong(ayto));
                        aytoList.add(curAyto);
                    }
                }

                renderRequest.setAttribute(WebCalendarioPortletKeys.FAMILIA_LIST, familiaList);
                renderRequest.setAttribute(WebCalendarioPortletKeys.CCAA_LIST, ccaaList);
                renderRequest.setAttribute(WebCalendarioPortletKeys.AYTO_LIST, aytoList);

            }
        } catch (PortalException e) {
            _log.error(e, e);
            throw new PortletException(e);
        }
    }
}
