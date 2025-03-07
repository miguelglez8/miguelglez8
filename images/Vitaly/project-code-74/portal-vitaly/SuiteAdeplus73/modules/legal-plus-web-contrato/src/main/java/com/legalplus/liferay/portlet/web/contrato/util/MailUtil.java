package com.legalplus.liferay.portlet.web.contrato.util;

import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense;
import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.CompApplication;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLicenseLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.model.*;
import com.legalplus.liferay.portlet.legalplus.manager.service.*;
import com.legalplus.liferay.portlet.mailing.web.mail.ContratoMailing;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MailUtil {

    private static final String APP_ALIAS = "LEGAL_PLUS";

    public static void sendEmail(User user, long compId) {

        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", user.getLocale());

        //EMPRESA
        Comp comp = CompLocalServiceUtil.fetchComp(compId);
        String empresa = comp.getName();

        //TIPO
        Application app = ApplicationLocalServiceUtil.getApplicationByAlias(APP_ALIAS);
        CompApplication compApplication = CompApplicationLocalServiceUtil.getCompanyApplication(compId, app.getApplicationId());
        ApplicationLicense applicationLicense = ApplicationLicenseLocalServiceUtil.fetchByAppLicense(app.getApplicationId(), compApplication.getLicenseId());
        String tipo = applicationLicense.getName();

        //CONTRATO
        ContratoCompany contrato = ContratoCompanyLocalServiceUtil.fetchByCompId(compId);
        String cnaes = "";
        String familias = "";
        String ccaa = "";
        String aytos = "";

        if (Validator.isNotNull(contrato)) {

            //CNAES
            if (Validator.isNotNull(contrato.getCnaes())) {

                List<String> cnaesList = new ArrayList<>();
                String[] cnaesArray = StringUtil.split(contrato.getCnaes(), StringPool.SEMICOLON);

                for (String cnae : cnaesArray) {
                    CNAES curCnae = CNAESLocalServiceUtil.fetchCNAES(cnae);
                    cnaesList.add(" " + curCnae.getCnae() + " (" + curCnae.getNombre() + ")");
                }

                cnaes = String.join(StringPool.COMMA, cnaesList);
            }

            //CCAA
            if (Validator.isNotNull(contrato.getCcaa())) {

                StringBuilder sbCCAA = new StringBuilder();
                String[] ccaaArray = StringUtil.split(contrato.getCcaa(), StringPool.SEMICOLON);

                sbCCAA.append("<ul>");
                for(String ca : ccaaArray) {
                    CCAA curCa = CCAALocalServiceUtil.fetchCCAA(Long.parseLong(ca));
                    sbCCAA.append("<li>" + curCa.getNombre() + "</li>");
                }
                sbCCAA.append("</ul>");

                ccaa = sbCCAA.toString();
            }

            //AYTO
            if (Validator.isNotNull(contrato.getAyuntamiento())) {

                StringBuilder sbAyto = new StringBuilder();
                String[] aytoArray = StringUtil.split(contrato.getAyuntamiento(), StringPool.SEMICOLON);

                sbAyto.append("<ul>");
                for (String ayto : aytoArray) {
                    Ayuntamiento curAyto = AyuntamientoLocalServiceUtil.findByAyuntamiento(Long.parseLong(ayto));
                    sbAyto.append("<li>" + curAyto.getNombre() + "</li>");
                }
                sbAyto.append("</ul>");

                aytos = sbAyto.toString();
            }

            //FAMILIAS
            if (Validator.isNotNull(contrato.getFamilia())) {

                StringBuilder sbFamilia = new StringBuilder();
                String[] familiaArray = StringUtil.split(contrato.getFamilia(), StringPool.SEMICOLON);

                sbFamilia.append("<ul>");
                for (String familia : familiaArray) {
                    if ("0".equals(familia)) sbFamilia.append("<li>" + LanguageUtil.get(bundle, "contrato.familia.prl") + "</li>");
                    if ("1".equals(familia)) sbFamilia.append("<li>" + LanguageUtil.get(bundle, "contrato.familia.medioambiente") + "</li>");
                    if ("2".equals(familia)) sbFamilia.append("<li>" + LanguageUtil.get(bundle, "contrato.familia.seguridadIndustrial") + "</li>");
                    if ("3".equals(familia)) sbFamilia.append("<li>" + LanguageUtil.get(bundle, "contrato.familia.seguridadAlimentaria") + "</li>");
                }
                sbFamilia.append("</ul>");

                familias = sbFamilia.toString();
            }

        }

        //CONSULTOR/ES
        List<String> consultoresList = new ArrayList<>();
        List<ConsultorCompany> consultoresCompany = ConsultorCompanyLocalServiceUtil.fetchByCompId(compId);

        String nombre = "";
        String email = "";

        for (ConsultorCompany consultor : consultoresCompany) {
            User curConsultor = UserLocalServiceUtil.fetchUser(consultor.getUserId());

            nombre = curConsultor.getFullName();
            email = curConsultor.getEmailAddress();

            consultoresList.add(curConsultor.getFullName() + " - " + curConsultor.getEmailAddress());
        }
        String consultores =String.join(StringPool.SPACE + StringPool.COMMA, consultoresList);

        //EMAIL
        ContratoMailing.cambioContrato(
                user.getEmailAddress(),
                user,
                empresa,
                tipo,
                cnaes,
                consultores,
                familias,
                ccaa,
                aytos,
                nombre,
                email
        );
    }

}
