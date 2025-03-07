package com.canal.etico.liferay.portlet.synchronization.web.listener;

import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.CompApplication;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.canal.etico.liferay.portlet.commons.web.company.CanalEticoCompUtil;
import com.canal.etico.liferay.portlet.commons.web.company.CanalEticoCompUtilV2;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
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
public class SuiteComApplicationModelListener extends BaseModelListener<CompApplication> {

    private static final Log log = LogFactoryUtil.getLog(SuiteComApplicationModelListener.class);

    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    @Override
    public void onAfterCreate(CompApplication compApplication) throws ModelListenerException {

        if(CanalEticoCompUtil.isApplicationCanalEtico(compApplication.getApplicationId())) {
            Comp comp = null;
            try {
                comp = CompLocalServiceUtil.getComp(compApplication.getCompId());
            } catch (PortalException e) {}

            if (Validator.isNotNull(comp) && CanalEticoCompUtil.hasCompanyApplicationInSuite(comp)) {

                CanalEticoCompUtilV2.createCompany(comp.getCompId());

            }
        }

        super.onAfterCreate(compApplication);
    }

    @Override
    public void onAfterUpdate(CompApplication compApplication) throws ModelListenerException {

        if(CanalEticoCompUtil.isApplicationCanalEtico(compApplication.getApplicationId())) {

            Comp comp = null;
            try {
                comp = CompLocalServiceUtil.getComp(compApplication.getCompId());
            } catch (PortalException e) {}

            if (Validator.isNotNull(comp) && !CanalEticoCompUtil.hasCompanyApplicationInSuite(comp)) {

                CanalEticoCompUtilV2.updateCompany(comp.getCompId());

            }

        }

        super.onAfterUpdate(compApplication);
    }


}
