package com.legalplus.liferay.portlet.scheduler.web.actions;

import com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserApplicationClientLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.legalplus.liferay.portlet.scheduler.web.constants.SchedulerPortletKeys;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

public class GetAllUsersByCompanyAndAppLegal {


    public static List<UserCompany> getAllUserActiveByApp(long companyId){

        List<UserCompany> users = UserCompanyLocalServiceUtil.getUsersFromCompany(companyId);
        List<UserCompany> lstResultado = new ArrayList<UserCompany>();
        List<UserApplicationClient> lstApps = null;
        for(UserCompany u : users){
            if(Validator.isNull(u.getUserEndDate())){
                lstApps = UserApplicationClientLocalServiceUtil.getApplicationsFromUser(u.getUserId(), companyId);
                if(Validator.isNotNull(lstApps) && lstApps.size() > 0){
                    for(UserApplicationClient app : lstApps ){
                        if(app.getApplicationId().indexOf("appId\":\"" + SchedulerPortletKeys.ID_APP_LEGAL + "\"") != -1){
                            lstResultado.add(u);
                        }
                    }
                }
            }
        }
        return lstResultado;

    }

}
