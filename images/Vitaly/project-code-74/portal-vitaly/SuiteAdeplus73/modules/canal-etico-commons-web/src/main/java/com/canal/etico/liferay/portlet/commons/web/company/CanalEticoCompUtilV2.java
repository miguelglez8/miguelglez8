package com.canal.etico.liferay.portlet.commons.web.company;


import com.canal.etico.liferay.portlet.commons.web.util.CanalEticoUtil;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.Comp;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.CompLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;

public class CanalEticoCompUtilV2 {
    public static Comp createCompany(long suiteCompId){

        Comp comp = CompLocalServiceUtil.fetchComp(suiteCompId);

        if(Validator.isNull(comp)) {
            comp = CompLocalServiceUtil.createComp(suiteCompId);
            comp.setUrlId(CanalEticoUtil.generatePasswordRandon());
        }

        CompLocalServiceUtil.updateComp(comp);


        return comp;
    }

    public static Comp updateCompany(long suiteCompId){

       Comp comp = CompLocalServiceUtil.fetchComp(suiteCompId);

        if(Validator.isNull(comp)) {
            comp = CompLocalServiceUtil.createComp(suiteCompId);
        }

        CompLocalServiceUtil.updateComp(comp);



        return comp;


    }


}
