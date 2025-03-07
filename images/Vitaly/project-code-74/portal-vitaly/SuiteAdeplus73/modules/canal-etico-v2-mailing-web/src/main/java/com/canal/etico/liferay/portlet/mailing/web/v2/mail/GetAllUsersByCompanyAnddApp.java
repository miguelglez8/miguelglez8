package com.canal.etico.liferay.portlet.mailing.web.v2.mail;
import com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserApplicationClientLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;

import com.canal.etico.liferay.portlet.mailing.web.v2.constants.MailingPortletKeys;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

public class GetAllUsersByCompanyAnddApp {

    public static List<UserCompany> getAllUserActiveByAppCanal(long companyId){

            List<UserCompany> users = UserCompanyLocalServiceUtil.getUsersFromCompany(companyId);
            List<UserCompany> lstResultado = new ArrayList<UserCompany>();
            List<UserApplicationClient> lstApps = null;
            for(UserCompany u : users){
                if(Validator.isNull(u.getUserEndDate())){
                    lstApps = UserApplicationClientLocalServiceUtil.getApplicationsFromUser(u.getUserId(), companyId);
                    if(Validator.isNotNull(lstApps) && lstApps.size() > 0){
                        for(UserApplicationClient app : lstApps ){
                            if(app.getApplicationId().indexOf("appId\":\"" + MailingPortletKeys.ID_APP_CANAL + "\"") != -1){
                                lstResultado.add(u);
                            }
                        }
                    }
                }
            }
            return lstResultado;

        }




}
