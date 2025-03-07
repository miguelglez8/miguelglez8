package com.preving.liferay.portlet.create.company.web.util;


import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil;
import com.preving.liferay.portlet.create.company.web.bean.CSVData;
import com.preving.liferay.portlet.create.company.web.constants.CreateCompanyPortletKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PrevingCSVValidatorUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingCSVValidatorUtil.class);

    public static List<String> isCompanyDataCorrect(long companyId, String companyName, String cif, String adminName, String adminLastName, String nif, String email, String locale){

        List<String> errors = new ArrayList<>();

        //Company Name
        if(Validator.isBlank(companyName)){

            //errors.add(CreateCompanyPortletKeys.LANG_CSV_VALIDATE_COMPANY_NAME_EMPTY_es_ES);
            String errorMessage = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.csv.error.name.empty");
            errors.add(errorMessage);

        } else {

            List<Group> companyGroups = GroupLocalServiceUtil.getCompanyGroups(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            for (Group group : companyGroups) {
                 if (group.getName().toLowerCase().contains(companyName.toLowerCase())) {
                     //errors.add(CreateCompanyPortletKeys.LANG_CSV_VALIDATE_COMPANY_NAME_DUPLICATED_es_ES);
                     String errorMessage = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.csv.error.name.repeated");
                     errors.add(errorMessage);
                    break;
                }
            }

        }

        //CIF
        if(Validator.isBlank(cif)){
            //errors.add(CreateCompanyPortletKeys.LANG_CSV_VALIDATE_COMPANY_CIF_EMPTY_es_ES);
            String errorMessage = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.csv.error.cif.empty");
            errors.add(errorMessage);
        }
        if(Validator.isNotNull(CompanyConfLocalServiceUtil.getCompanyConfByCif(cif))){
            //errors.add(CreateCompanyPortletKeys.LANG_CSV_VALIDATE_COMPANY_CIF_DUPLICATED_es_ES);
            String errorMessage = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.csv.error.cif.repeated");
            errors.add(errorMessage);
        }

        //Admin
        if(Validator.isBlank(adminName)){
            //errors.add(CreateCompanyPortletKeys.LANG_CSV_VALIDATE_ADMIN_NAME_EMPTY_es_ES);
            String errorMessage = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.csv.error.admin.name");
            errors.add(errorMessage);
        }


        // Email
        if(Validator.isBlank(email)){
            //errors.add(CreateCompanyPortletKeys.LANG_CSV_VALIDATE_ADMIN_EMAIL_EMPTY_es_ES);
            String errorMessage = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.csv.error.admin.mail.empty");
            errors.add(errorMessage);
        }

        if(!Validator.isEmailAddress(email)){
            //errors.add(CreateCompanyPortletKeys.LANG_CSV_VALIDATE_ADMIN_EMAIL_INCORRECT_es_ES);
            String errorMessage = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.csv.error.admin.mail");
            errors.add(errorMessage);
        }

        //Locale
        if(!locale.equals("es_ES") && !locale.equals("ca_ES")
                && !locale.equals("es") && !locale.equals("ES")
                && !locale.equals("ca") && !locale.equals("CA")){
            String errorMessage = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.csv.error.locale");
            errors.add(errorMessage);
        }

        return errors;
    }


    public static List<String> duplicatedValues(List<CSVData> elements) {

        List<String> errors = new ArrayList<>();

        _log.info("duplicatedValues " + elements);

        List<String> cifList = getListCIFs(elements);
        if (haveListDuplicates(cifList)) {
            //errors.add(CreateCompanyPortletKeys.LANG_CSV_VALIDATE_CIF_DUPLICATED_es_ES);
            String errorMessage = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.csv.error.repeated.cif");
            errors.add(errorMessage);
        }

        List<String> nameList = getListNames(elements);
        if (haveListDuplicates(nameList)) {
            //errors.add(CreateCompanyPortletKeys.LANG_CSV_VALIDATE_NAME_DUPLICATED_es_ES);
            String errorMessage = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.csv.error.repeated.name");
            errors.add(errorMessage);
        }

        return errors;
    }

    private static boolean haveListDuplicates(List<String> listDuplicates){

        List<String> listWithoutDuplicates = listDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());

        _log.debug("Duplicated csv values: " + (listDuplicates.size() > listWithoutDuplicates.size()));

        return listDuplicates.size() > listWithoutDuplicates.size();

    }

    private static List<String> getListNames(List<CSVData> elements){
        List<String> result = new ArrayList<>();
        for(CSVData data:elements){
            result.add(data.getName());
        }
        _log.info("result" + result);
        return result;
    }
    private static List<String> getListCIFs(List<CSVData> elements){
        List<String> result = new ArrayList<>();
        for(CSVData data:elements){
            result.add(data.getCif());
        }
        return result;
    }
    private static List<String> getListNIFs(List<CSVData> elements){
        List<String> result = new ArrayList<>();
        for(CSVData data:elements){
            result.add(data.getAdminNIF());
        }
        return result;
    }
    private static List<String> getListEmails(List<CSVData> elements){
        List<String> result = new ArrayList<>();
        for(CSVData data:elements){
            result.add(data.getAdminEmail());
        }
        return result;
    }

}
