package com.preving.liferay.portlet.commons.web.user;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.commons.web.constants.PrevingCommonsPortletKeys;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PrevingUserKeycloakUtil {

    public static Log _log = LogFactoryUtil.getLog(PrevingUserKeycloakUtil.class);

    public static List<Long> getCompaniesIdsFromUser(UserRepresentation userRepresentation){

        List<Long> companiesIds = new ArrayList<>();

        List<String> userCompaniesKC = userRepresentation.getGroups();

        for(String compName:userCompaniesKC){
            String[] compData = compName.split("_");
            if(compData.length == 3 && Validator.isNumber(compData[2])){
                companiesIds.add(Long.parseLong(compData[2]));
                if(_log.isDebugEnabled()){
                    _log.debug(" Company Id: " + compData[2]);
                }
            }
        }

        return companiesIds;
    }


    public static boolean hasApplicationFromCompanyByName(UserRepresentation userRepresentation, long companyId, String applicationName){

        List<String> applicationNamesFromCompany = userRepresentation.getAttributes().get(companyId  + PrevingCommonsPortletKeys.KEYCLOAK_NAME_APPS);
        for(String appName:applicationNamesFromCompany){
            if(applicationName.equals(appName)){
                if(_log.isDebugEnabled()){
                    _log.debug(" User has permission for  " + applicationName + " in " + companyId +".");
                }
                return true;
            }
        }
        return false;
    }

    public static Date hasApplicationDeletedFromCompanyByName(UserRepresentation userRepresentation, long companyId, String applicationName){

        List<String> applicationDeletedNamesFromCompany = userRepresentation.getAttributes().get(companyId + PrevingCommonsPortletKeys.KEYCLOAK_NAME_APPS_DELETED);
        for(String appDeleteName:applicationDeletedNamesFromCompany){

            String[] appData = appDeleteName.split("\\|");

            if(appData.length == 2 && applicationName.equals(appData[0])){

                if(_log.isDebugEnabled()){
                    _log.debug(" User has no permission for  " + applicationName + " in " + companyId +".");
                }

                return new Date(Long.parseLong(appData[1]));
            }

        }
        return null;
    }

    public static boolean hasUserAdminRole(UserRepresentation userRepresentation, long companyId){
        return userRepresentation.getRealmRoles().contains(companyId + PrevingCommonsPortletKeys.KEYCLOAK_ADMIN_ROL);
    }

    public static Date getDate31DaysBefore(){
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.MONTH, -1);

        return calendar.getTime();
    }

    private static String getCompanyNameDefault(UserRepresentation userRepresentation){
        return userRepresentation.getAttributes().get("suite_default_company").toString().replace("[","").replace("]","");
    }

    public static String getCompanyCifKC(GroupRepresentation groupRepresentation){
        return groupRepresentation.getAttributes().get("cif").get(0);
    }
}
