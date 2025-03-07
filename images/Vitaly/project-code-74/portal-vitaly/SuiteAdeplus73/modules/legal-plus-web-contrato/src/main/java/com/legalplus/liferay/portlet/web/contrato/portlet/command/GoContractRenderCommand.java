package com.legalplus.liferay.portlet.web.contrato.portlet.command;

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
import com.legalplus.liferay.portlet.web.contrato.constants.WebContratoPortletKeys;
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
import java.util.List;
import java.util.ResourceBundle;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + WebContratoPortletKeys.WEBCONTRATO,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class GoContractRenderCommand implements MVCRenderCommand  {

    private static Log _log = LogFactoryUtil.getLog(GoContractRenderCommand.class);

    public static final String ITEM_DELIMITER = ";";

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        try {

            ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
            ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
            String compId;

            if (LegalplusRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                    || LegalplusRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {

                HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
                compId = request.getParameter(WebContratoPortletKeys.QUERY_PARAM);

                if (Validator.isNull(compId)) {
                    compId = ParamUtil.getString(renderRequest, WebContratoPortletKeys.COMPANY_ID);
                }

                if (Validator.isNull(compId)) return "/error.jsp";

            } else {
                UserCompany company = LegalplusRoleUtil.getUserDefaultCompany(themeDisplay.getUserId());
                compId = String.valueOf(company.getCompId());
            }

            //ID
            Long companyId = Long.valueOf(compId);

            //Licencia
            Application application = ApplicationLocalServiceUtil.getApplicationByName(LegalPlusCommonsPortletKeys.PROPERTY_APP_LEGAL);
            CompApplication compApp = CompApplicationLocalServiceUtil.getCompanyApplication(companyId, application.getApplicationId());
            //ApplicationLicense license = ApplicationLicenseLocalServiceUtil.getApplicationLicense(compApp.getLicenseId());
            ApplicationLicense license =LegalplusRoleUtil.getLicenseNew(Long.parseLong(compId));
            List<ApplicationLicense> licenseList = ApplicationLicenseLocalServiceUtil.getApplicationLicenses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

            //Consultores
            List<ConsultorCompany> consultores = ConsultorCompanyLocalServiceUtil.fetchByCompId(companyId);

            //Contrato
            ContratoCompany contract = ContratoCompanyLocalServiceUtil.fetchByCompId(companyId);

            if (LegalplusRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                    || LegalplusRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {

                createContratoConsultor(renderRequest, themeDisplay, companyId);

            } else {

                createContratoCliente(renderRequest, contract);
            }

            //URL contacto
            String urlFamilias = LanguageUtil.get(bundle, "contrato.view.contacto.familias.url");
            String urlPlan = LanguageUtil.get(bundle, "contrato.view.contacto.plan.url");
            String contactoFamiliasUrl = themeDisplay.getScopeGroup().getDisplayURL(themeDisplay, false) + urlFamilias;
            String contactoPlanUrl = themeDisplay.getScopeGroup().getDisplayURL(themeDisplay, false) + urlPlan;
            renderRequest.setAttribute(WebContratoPortletKeys.CONTACTO_FAMILIAS_URL, contactoFamiliasUrl);
            renderRequest.setAttribute(WebContratoPortletKeys.CONTACTO_PLAN_URL, contactoPlanUrl);

            renderRequest.setAttribute(WebContratoPortletKeys.LICENCE, license);
            renderRequest.setAttribute(WebContratoPortletKeys.LICENSE_LIST, licenseList);
            renderRequest.setAttribute(WebContratoPortletKeys.USER_LIST, consultores);
            renderRequest.setAttribute(WebContratoPortletKeys.CONTRACT, contract);
            renderRequest.setAttribute(WebContratoPortletKeys.COMPANY_ID, compId);

        } catch (PortalException e) {
            _log.error(e, e);
            throw new PortletException(e);
        }

        return "/view.jsp";
    }

    private void createContratoConsultor(RenderRequest renderRequest, ThemeDisplay themeDisplay, Long compId) {

        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

        List<CCAA> ccaaList = CCAALocalServiceUtil.getCCAAs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        List<CNAES> cnaesList = CNAESLocalServiceUtil.getCNAESs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        renderRequest.setAttribute(WebContratoPortletKeys.CCAA, ccaaList);
        renderRequest.setAttribute(WebContratoPortletKeys.CNAES_LIST, cnaesList);

        String url = themeDisplay.getScopeGroup().getDisplayURL(themeDisplay, false);
        String legislacionesUrl = url + LanguageUtil.get(bundle, "/legislacion-consultor?query=") + compId;
        String contratoUrl = url + LanguageUtil.get(bundle, "/contrato-consultor?query=") + compId;
        String calendarioUrl = url + LanguageUtil.get(bundle, "/calendario-consultor?query=") + compId;
        String empresasUrl = url + LanguageUtil.get(bundle, "/empresas");

        renderRequest.setAttribute(WebContratoPortletKeys.TAB_ACTIVE, WebContratoPortletKeys.TAB_CONTRACT);
        renderRequest.setAttribute(WebContratoPortletKeys.TAB_LEGISLACIONES, legislacionesUrl);
        renderRequest.setAttribute(WebContratoPortletKeys.TAB_CONTRACT, contratoUrl);
        renderRequest.setAttribute(WebContratoPortletKeys.TAB_CALENDARIO, calendarioUrl);
        renderRequest.setAttribute(WebContratoPortletKeys.TAB_BACK, empresasUrl);
    }

    private void createContratoCliente(RenderRequest renderRequest, ContratoCompany contrato) throws PortalException {

        List<CCAA> ccaaList = new ArrayList<>();
        List<Ayuntamiento> aytoList = new ArrayList<>();
        List<CNAES> cnaesList = new ArrayList<>();

        if (Validator.isNotNull(contrato)) {

            if (Validator.isNotNull(contrato.getCcaa())) {
                for (String curCCAA : contrato.getCcaa().split(ITEM_DELIMITER)) {
                    CCAA ccaa = CCAALocalServiceUtil.getCCAA(Long.valueOf(curCCAA));
                    ccaaList.add(ccaa);
                }
            }

            if (Validator.isNotNull(contrato.getAyuntamiento())) {
                for (String curAyto : contrato.getAyuntamiento().split(ITEM_DELIMITER)) {
                    Ayuntamiento ayto = AyuntamientoLocalServiceUtil.findByAyuntamiento(Long.valueOf(curAyto));
                    aytoList.add(ayto);
                }
            }

            if (Validator.isNotNull(contrato.getCnaes())) {
                for (String curCnae : contrato.getCnaes().split(ITEM_DELIMITER)) {
                    CNAES cnae = CNAESLocalServiceUtil.getCNAES(curCnae);
                    cnaesList.add(cnae);
                }
            }

        }

        renderRequest.setAttribute(WebContratoPortletKeys.CCAA, ccaaList);
        renderRequest.setAttribute(WebContratoPortletKeys.AYTOS, aytoList);
        renderRequest.setAttribute(WebContratoPortletKeys.CNAES_LIST, cnaesList);

    }
}