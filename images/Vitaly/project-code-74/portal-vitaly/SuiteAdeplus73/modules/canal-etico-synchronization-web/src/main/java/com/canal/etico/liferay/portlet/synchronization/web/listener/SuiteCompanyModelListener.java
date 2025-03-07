package com.canal.etico.liferay.portlet.synchronization.web.listener;

import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.canal.etico.liferay.portlet.commons.web.company.CanalEticoCompUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component(
        immediate = true,
        service = ModelListener.class
)
public class SuiteCompanyModelListener extends BaseModelListener<Comp> {

    private static final Log log = LogFactoryUtil.getLog(SuiteCompanyModelListener.class);

    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    @Override
    public void onAfterUpdate(Comp comp) throws ModelListenerException {
        /*
        if (Validator.isNotNull(comp) && CanalEticoCompUtil.hasCompanyApplicationInSuite(comp)) {

            System.out.println(dateFormatLog.format(new Date()) + " Update company in canal etico : " + comp.getName());

            boolean active = Validator.isNotNull(comp.getDeleteDate())?comp.getDeleteDate().after(new Date()): true;

            CanalEticoCompUtil.updateCompany(comp.getCompId(), comp.getName(), comp.getCif(), comp.getDescription(), comp.getObservations(), comp.getAgreement(), active);

            System.out.println(dateFormatLog.format(new Date()) + " The company " + comp.getName() + " updated in Canal Etico.");
        }
        */
        super.onAfterUpdate(comp);
    }


}
