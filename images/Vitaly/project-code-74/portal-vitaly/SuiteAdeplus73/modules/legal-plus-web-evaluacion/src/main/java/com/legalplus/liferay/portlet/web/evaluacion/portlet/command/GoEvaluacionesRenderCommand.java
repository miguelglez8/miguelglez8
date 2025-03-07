package com.legalplus.liferay.portlet.web.evaluacion.portlet.command;

import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense;
import com.adeplus.liferay.portlet.suite.manager.model.CompApplication;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLicenseLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompApplicationLocalServiceUtil;
import com.legalplus.liferay.portlet.commons.web.constants.LegalPlusCommonsPortletKeys;
import com.legalplus.liferay.portlet.commons.web.role.LegalplusRoleUtil;
import com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion;
import com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito;
import com.legalplus.liferay.portlet.legalplus.manager.model.Legislacion;
import com.legalplus.liferay.portlet.legalplus.manager.model.Requisito;
import com.legalplus.liferay.portlet.legalplus.manager.service.EvalLegislacionLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.EvalRequisitoLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.LegislacionLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.RequisitoLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.EvalRequisitoPK;
import com.legalplus.liferay.portlet.web.evaluacion.constants.WebEvaluacionPortletKeys;
import com.legalplus.liferay.portlet.web.evaluacion.utils.EvaluacionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + WebEvaluacionPortletKeys.WEBEVALUACION,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class GoEvaluacionesRenderCommand implements MVCRenderCommand {

    private static Log _log = LogFactoryUtil.getLog(GoEvaluacionesRenderCommand.class);

    private static final String OPTIMO = "Cliente Optimo";
    private static final String ADVANCED = "Cliente Advanced";
    private static final String PREMIUM = "Cliente Premium";

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
            ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

            String compId = EvaluacionUtil.getCompany(themeDisplay, renderRequest);
            if (Validator.isBlank(compId)) return "/error.jsp";

            String legislacionId = EvaluacionUtil.getLegislacion(renderRequest, Long.parseLong(compId));
            if (Validator.isBlank(legislacionId)) return "/error.jsp";

            //ID
            Long companyId = Long.valueOf(compId);

            //Licencia
            Application application = ApplicationLocalServiceUtil.getApplicationByName(LegalPlusCommonsPortletKeys.PROPERTY_APP_LEGAL);
            //CompApplication compApp = CompApplicationLocalServiceUtil.getCompanyApplication(companyId, application.getApplicationId());
            //ApplicationLicense license = ApplicationLicenseLocalServiceUtil.getApplicationLicense(compApp.getLicenseId());
            ApplicationLicense license =LegalplusRoleUtil.getLicenseNew(Long.parseLong(compId));
            //Legislacion
            Legislacion legislacion = LegislacionLocalServiceUtil.getLegislacion(legislacionId);

            //URL back
            String backURL = themeDisplay.getScopeGroup().getDisplayURL(themeDisplay, false) +
                                    ( LegalplusRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser()) ||
                                      LegalplusRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser()) ?
                                        WebEvaluacionPortletKeys.CONSULTOR_BACK_URL + compId : WebEvaluacionPortletKeys.CLIENTE_BACK_URL );
            renderRequest.setAttribute(WebEvaluacionPortletKeys.BACK_URL, backURL);

            //URL ayuda
            String ayuda = LanguageUtil.get(bundle, "evaluacion.view.ayuda.url");
            String ayudaURL = themeDisplay.getScopeGroup().getDisplayURL(themeDisplay, false) + ayuda;
            renderRequest.setAttribute(WebEvaluacionPortletKeys.AYUDA_URL, ayudaURL);

            if (Validator.isNotNull(license)) {

                renderRequest.setAttribute(WebEvaluacionPortletKeys.LEGISLACION_PARAM, legislacion);
                renderRequest.setAttribute(WebEvaluacionPortletKeys.COMP_PARAM, compId);
                renderRequest.setAttribute(WebEvaluacionPortletKeys.ETIQUETAS, EvaluacionUtil.getEtiquetas(themeDisplay, legislacion.getEtiquetas()));

                EvalLegislacion evalLegislacion = EvalLegislacionLocalServiceUtil.getLastEvalLegislacion(Long.parseLong(compId), legislacionId);
                EvalLegislacion ultimaLegislacion = EvalLegislacionLocalServiceUtil.getLastEvalLegislacionCumplimentada(Long.parseLong(compId), legislacionId);

                String version = EvaluacionUtil.getVersion(renderRequest);
                String requisito = EvaluacionUtil.getRequisito(renderRequest);

                if (ADVANCED.equalsIgnoreCase(license.getName()) || PREMIUM.equalsIgnoreCase(license.getName())) {

                    if (Validator.isNotNull(requisito) && Validator.isNotNull(version)) {
                        return GoEvaluacionRequisitoRenderCommand.goEvaluacionRequisito(renderRequest);
                    }

                    List<Requisito> requisitos = RequisitoLocalServiceUtil.findRequisitoActivoByLegislacionAndCompId(legislacionId, Long.parseLong(compId));
                    renderRequest.setAttribute(WebEvaluacionPortletKeys.REQUISITOS, requisitos);
                    renderRequest.setAttribute(WebEvaluacionPortletKeys.ULITMA_EVALUACION_LEGISLACION,
                                                    EvaluacionUtil.isLastEvalRequisitoCompleted(Long.parseLong(compId), legislacionId, evalLegislacion));
                    return "/evaluaciones-requisitos.jsp";
                }

                if (OPTIMO.equalsIgnoreCase(license.getName())) {

                    if (Validator.isNotNull(version)) {
                        return GoEvaluacionLegislacionRenderCommand.goEvaluacionLegislacion(renderRequest);
                    }

                    renderRequest.setAttribute(WebEvaluacionPortletKeys.ULITMA_EVALUACION_LEGISLACION, evalLegislacion);
                    renderRequest.setAttribute(WebEvaluacionPortletKeys.ULITMA_EVALUACION_LEGISLACION_CUMPLIMENTADA, ultimaLegislacion);
                    return "/evaluaciones-legislacion.jsp";
                }

            }

            return "/view.jsp";
        } catch (PortalException e) {
            _log.error(e, e);
            throw new PortletException(e);
        }

    }

}
