package com.adeplus.liferay.portlet.mailing.web.configuration;

import com.adeplus.liferay.portlet.mailing.web.mail.UserMailing;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import java.util.Map;

@Component(configurationPid = "com.adeplus.liferay.portlet.mailing.web.configuration.SuiteAdeplusConfiguration")

public class SuiteAdeplusConfigurationManager {
    //private static Log _log = LogFactoryUtil.getLog(SuiteAdeplusConfigurationManager.class);
    /*public String getEmailCC(){        ;
        return (String) _configuration.getEmailCC();
    }*/
    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        _configuration = ConfigurableUtil.createConfigurable(
                SuiteAdeplusConfiguration.class, properties);
        //_log.info("_configuration ACTIVE: " + _configuration);
    }

    public  static SuiteAdeplusConfiguration _configuration;
}
