package com.canal.etico.liferay.portlet.commons.web.company;

import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompApplicationLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.model.Comp;
import com.canal.etico.liferay.portlet.canal.manager.service.CompLocalServiceUtil;
import com.canal.etico.liferay.portlet.commons.web.constants.CanalEticoCommonsPortletKeys;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CanalEticoCompUtil {

    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    public static Comp createCompany(long suiteCompId, String name, String cif, String description, String observations, String agreement){

        Comp comp = CompLocalServiceUtil.getCompanyBySuiteCompId(suiteCompId);

        if(Validator.isNull(comp)) {
            comp = CompLocalServiceUtil.createComp(CounterLocalServiceUtil.increment(Comp.class.getName()));
        }

        comp.setSuiteCompId(suiteCompId);
        comp.setName(name);
        comp.setCif(cif);
        comp.setDescription(description);
        comp.setObservations(observations);
        comp.setAgreement(agreement);
        comp.setActive(true);
        comp.setStartDate(new Date());

        CompLocalServiceUtil.updateComp(comp);


        return comp;
    }

    public static Comp updateCompany(long suiteCompId, String name, String cif, String description, String observations, String agreement, boolean active){

        Comp comp = CompLocalServiceUtil.getCompanyBySuiteCompId(suiteCompId);

        if(Validator.isNull(comp)) {
            comp = CompLocalServiceUtil.createComp(CounterLocalServiceUtil.increment(Comp.class.getName()));
        }

        if(Validator.isNotNull(comp)) {

            comp.setSuiteCompId(suiteCompId);
            comp.setName(name);
            comp.setCif(cif);
            comp.setDescription(description);
            comp.setObservations(observations);
            comp.setAgreement(agreement);
            comp.setActive(active);
            comp.setStartDate(new Date());

            CompLocalServiceUtil.updateComp(comp);
        }

        return comp;
    }


    public static boolean hasCompanyApplicationInSuite(com.adeplus.liferay.portlet.suite.manager.model.Comp comp){

        Application applicationByName = getCanalEticoAplication();

        return CompApplicationLocalServiceUtil.hasCompanyApplication(comp.getCompId(), applicationByName.getApplicationId());
    }

    public static boolean isApplicationCanalEtico(long applicationId){

        Application applicationByName = getCanalEticoAplication();

        Application application = ApplicationLocalServiceUtil.fetchApplication(applicationId);

        return applicationByName.getApplicationId() == application.getApplicationId();
    }

    public static Application getCanalEticoAplication(){
        String applicationAlias = PrefsPropsUtil.getString(CanalEticoCommonsPortletKeys.PROPERTY_APPLICATION_CANAL_ETICO_NAME);
        if(Validator.isBlank(applicationAlias)){
            System.out.println(dateFormatLog.format(new Date()) + "No existe la propiedad " + CanalEticoCommonsPortletKeys.PROPERTY_APPLICATION_CANAL_ETICO_NAME + " en el portal-ext.properties con el alias del Canal Denuncias.");
            return null;
        }

        Application applicationByName = ApplicationLocalServiceUtil.getApplicationByAlias(applicationAlias);
        if(Validator.isBlank(applicationAlias)){
            System.out.println(dateFormatLog.format(new Date()) + "No existe la aplicacion con alias " + applicationAlias + " en la suite adeplus para la app canal denuncias.");
            return null;
        }
        return  applicationByName;
    }

}
