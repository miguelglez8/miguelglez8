package com.preving.liferay.portlet.user.configuration.web.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.user.configuration.web.bean.CSVData;
import com.preving.liferay.portlet.user.configuration.web.constants.UserConfigurationPortletKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CSVUtil {

    private static Log _log = LogFactoryUtil.getLog(CSVUtil.class);

    public static List<String> isUserDataCorrect(long companyId, String nif, String name, String lastname, String mail, String jobTitle, String workplace, String locale){

        List<String> errors = new ArrayList<>();

        //NIF
        if(Validator.isBlank(nif)){
            errors.add(UserConfigurationPortletKeys.LANG_CSV_VALIDATE_USER_NIF_EMPTY_es_ES);
            //String message = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "user.mail.subject.active");
        }

        //Nombre
        if(Validator.isBlank(name)){
            errors.add(UserConfigurationPortletKeys.LANG_CSV_VALIDATE_USER_NAME_EMPTY_es_ES);
        }

        //Apellidos
        if(Validator.isBlank(lastname)){
            errors.add(UserConfigurationPortletKeys.LANG_CSV_VALIDATE_USER_SURNAME_EMPTY_es_ES);
        }

        //Mail
        if(Validator.isBlank(mail)){
            errors.add(UserConfigurationPortletKeys.LANG_CSV_VALIDATE_USER_MAIL_EMPTY_es_ES);
        }
        if(!Validator.isEmailAddress(mail)){
            errors.add(UserConfigurationPortletKeys.LANG_CSV_VALIDATE_ADMIN_EMAIL_INCORRECT_es_ES);
        }

/*
        //Nueva caracteristica: Cuando un mail ya existe se actualiza la info.
        long userId = 0;
        try {
            userId = UserLocalServiceUtil.getUserIdByEmailAddress(companyId, mail);
            if(userId != 0){
                errors.add(UserConfigurationPortletKeys.LANG_CSV_VALIDATE_ADMIN_EMAIL_DUPLICATED_es_ES);
            }
        } catch (PortalException e) {
            //_log.debug("No existe usuario con el mail.");
        }
*/
        //jobTitle
/*        if(Validator.isBlank(jobTitle)){
            errors.add(UserConfigurationPortletKeys.LANG_CSV_VALIDATE_USER_JOBTITLE_EMPTY_es_ES);
        }*/

        //workplace
/*        if(Validator.isBlank(workplace)){
            errors.add(UserConfigurationPortletKeys.LANG_CSV_VALIDATE_USER_WORKPLACE_EMPTY_es_ES);
        }*/

        //Locale
        if(!locale.equals("es_ES") && !locale.equals("ca_ES")
                && !locale.equals("es") && !locale.equals("ES")
                && !locale.equals("ca") && !locale.equals("CA")){
            String errorMessage = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "user.csv.import.validation.error.locale");
            errors.add(errorMessage);
        }

        return errors;
    }

    public static List<String> duplicatedValues(List<CSVData> elements) {

        List<String> errors = new ArrayList<>();

        _log.info("duplicatedValues " + elements);

        List<String> cifList = getListNIFs(elements);
        if (haveListDuplicates(cifList)) {
            errors.add(UserConfigurationPortletKeys.LANG_CSV_VALIDATE_NIF_DUPLICATED_es_ES);
        }

        List<String> emailList = getListEmails(elements);
        if (haveListDuplicates(emailList)) {
            errors.add(UserConfigurationPortletKeys.LANG_CSV_VALIDATE_EMAIL_DUPLICATED_es_ES);

        }

        return errors;
    }

    private static List<String> getListNIFs(List<CSVData> elements){
        List<String> result = new ArrayList<>();
        for(CSVData d:elements){
            result.add(d.getNif());
        }
        return result;
    }

    private static List<String> getListEmails(List<CSVData> elements){
        List<String> result = new ArrayList<>();
        for(CSVData d:elements){
            result.add(d.getEmail());
        }
        return result;
    }

    private static boolean haveListDuplicates(List<String> listDuplicates){

        List<String> listWithoutDuplicates = listDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());

        _log.debug("Duplicated csv values: " + (listDuplicates.size() > listWithoutDuplicates.size()));

        return listDuplicates.size() > listWithoutDuplicates.size();

    }

}
