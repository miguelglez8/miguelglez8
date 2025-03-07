package com.legalplus.liferay.portlet.web.evaluacion.utils;

import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.legalplus.liferay.portlet.commons.web.role.LegalplusRoleUtil;
import com.legalplus.liferay.portlet.legalplus.manager.model.*;
import com.legalplus.liferay.portlet.legalplus.manager.service.*;
import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.EvalRequisitoPK;
import com.legalplus.liferay.portlet.web.evaluacion.constants.WebEvaluacionPortletKeys;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EvaluacionUtil {

    public static final String getCompany(ThemeDisplay themeDisplay, PortletRequest portletRequest) {
        HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(portletRequest));
        String compId = StringPool.BLANK;

        if (LegalplusRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                || LegalplusRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {

            compId = request.getParameter(WebEvaluacionPortletKeys.COMP_PARAM);
            if (Validator.isNull(compId)) {
                compId = ParamUtil.getString(portletRequest, WebEvaluacionPortletKeys.COMP_PARAM);
            }

        } else {

            UserCompany company = LegalplusRoleUtil.getUserDefaultCompany(themeDisplay.getUserId());
            compId = String.valueOf(company.getCompId());

        }

        return compId;
    }

    public static final String getLegislacion(PortletRequest portletRequest, long compId) {
        HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(portletRequest));
        String legislacionId = request.getParameter(WebEvaluacionPortletKeys.LEGISLACION_PARAM);

        if (Validator.isNull(legislacionId)) {
            legislacionId = ParamUtil.getString(portletRequest, WebEvaluacionPortletKeys.LEGISLACION_PARAM);
        }

        if (!Validator.isBlank(legislacionId)) {
            Legislacion legislacion = LegislacionLocalServiceUtil.fetchLegislacion(legislacionId);
            List<LegislacionCNAES> cnaes = LegislacionCNAESLocalServiceUtil.findByLegislacion(legislacionId);

            List<ContratoCompany> companies = ContratoCompanyLocalServiceUtil.fetchByLegislacion(
                                                    legislacion.getFamilia(),
                                                    legislacion.getCcaa(),
                                                    legislacion.getAyuntamiento(),
                                                    cnaes.stream().map(cnae -> cnae.getCnae()).collect(Collectors.joining(StringPool.SEMICOLON)));

            if (!companies.stream().anyMatch(comp -> comp.getCompId() == compId)) {
                return StringPool.BLANK;
            }
        }

        return  legislacionId;
    }

    public static final String getVersion(PortletRequest portletRequest) {
        HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(portletRequest));
        String versionId = request.getParameter(WebEvaluacionPortletKeys.VERSION_PARAM);
        if (Validator.isNull(versionId)) {
            versionId = ParamUtil.getString(portletRequest, WebEvaluacionPortletKeys.VERSION_PARAM);
        }
        return  versionId;
    }

    public static final String getRequisito(PortletRequest portletRequest) {
        HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(portletRequest));
        String requisitoId = request.getParameter(WebEvaluacionPortletKeys.REQUISITO_PARAM);
        if (Validator.isNull(requisitoId)) {
            requisitoId = ParamUtil.getString(portletRequest, WebEvaluacionPortletKeys.REQUISITO_PARAM);
        }
        return  requisitoId;
    }

    public static final String getEtiquetas(ThemeDisplay themeDisplay, String value)  {
        try {
            List<String> result = new ArrayList<>();
            String[] categories = StringUtil.split(value, StringPool.SEMICOLON);

            for (String category : categories) {
                AssetCategory curCategory = AssetCategoryLocalServiceUtil.getCategory(Long.parseLong(category));
                result.add(curCategory.getTitle(themeDisplay.getLocale()));
            }

            return String.join(StringPool.COMMA + StringPool.SPACE, result);
        } catch (PortalException e) {
            return StringPool.BLANK;
        }
    }

    public static String getCumplimientoStringValue(int cumplimiento, ResourceBundle bundle){
        if (cumplimiento == 0) return LanguageUtil.get(bundle, "evaluacion.view.si");
        if (cumplimiento == 1) return LanguageUtil.get(bundle, "evaluacion.view.no");
        if (cumplimiento == 2) return LanguageUtil.get(bundle, "evaluacion.view.noProcede");
        return StringPool.BLANK;
    }

    public static boolean isLastEvalRequisitoCompleted(Long compId, String legislacionId, EvalLegislacion evalLegislacion) {
        List<Requisito> requisitosList = RequisitoLocalServiceUtil.findRequisitoActivoByLegislacionAndCompId(legislacionId, compId);
        for (Requisito requisito : requisitosList) {
            EvalRequisitoPK evalRequisitoPK = new EvalRequisitoPK();
            evalRequisitoPK.setCompId(compId);
            evalRequisitoPK.setLegislacionId(legislacionId);
            evalRequisitoPK.setRequisitoId(requisito.getRequisitoId());
            evalRequisitoPK.setVersion( Validator.isNotNull(evalLegislacion) ? evalLegislacion.getVersion() + 1 : 1 );

            EvalRequisito evalRequisito = EvalRequisitoLocalServiceUtil.fetchEvalRequisito(evalRequisitoPK);
            if (Validator.isNull(evalRequisito)) {
                return true;
            }
        }
        return false;

    }
}
