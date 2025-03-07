package com.preving.liferay.portlet.user.configuration.web.message;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.preving.liferay.portlet.commons.web.keycloak.PrevingKeycloakUtil;
import com.preving.liferay.portlet.user.configuration.web.constants.UserConfigurationPortletKeys;
import com.preving.liferay.portlet.user.configuration.web.util.PrevingUserUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.osgi.service.component.annotations.Component;

import java.util.Locale;

@Component(
        immediate = true,
        property = {"destination.name=" + UserConfigurationPortletKeys.MESSAGE_BUS_DESTINATION_NAME},
        service = MessageListener.class
)
public class PrevingMessageListener implements MessageListener {

    private static Log _log = LogFactoryUtil.getLog(PrevingMessageListener.class);

    @Override
    public void receive(Message message) throws MessageListenerException {

        //Create user for company.
        long companyId      = message.getLong(UserConfigurationPortletKeys.COMPANY_ID);
        long creatorUserId  = message.getLong(UserConfigurationPortletKeys.CREATOR_USER_ID);
        long groupId        = message.getLong(UserConfigurationPortletKeys.GROUP_ID);
        String portalURL    = message.getString(UserConfigurationPortletKeys.PORTAL_URL);
        String pathMain     = message.getString(UserConfigurationPortletKeys.PATH_MAIN);
        String name         = message.getString(UserConfigurationPortletKeys.USER_NAME);
        String lastName     = message.getString(UserConfigurationPortletKeys.USER_SURNAME);
        String nif          = message.getString(UserConfigurationPortletKeys.USER_NIF);
        String email        = message.getString(UserConfigurationPortletKeys.USER_EMAIL);
        String jobTitle     = message.getString(UserConfigurationPortletKeys.USER_JOB_TITLE);
        String strWorkCenter   = message.getString(UserConfigurationPortletKeys.USER_WORK_CENTER);
        String cmbWorkCenter =  message.getString(UserConfigurationPortletKeys.USER_WORK_CMB_CENTER);
        String workCenter   = message.getString(UserConfigurationPortletKeys.USER_WORK_CENTER);
        String salary       = message.getString(UserConfigurationPortletKeys.USER_SALARY);
        String genre        = message.getString(UserConfigurationPortletKeys.USER_GENRE);
        String endDate      = message.getString(UserConfigurationPortletKeys.USER_END_DATE);
        String active       = message.getString(UserConfigurationPortletKeys.USER_ACTIVE);
        String admin        = message.getString(UserConfigurationPortletKeys.USER_ADMIN);
        String password     = message.getString(UserConfigurationPortletKeys.USER_PASSWORD);

        String languageId = message.getString(UserConfigurationPortletKeys.LANGUAGE_ID);

        Locale locale = LocaleUtil.fromLanguageId(languageId);

        _log.info("companyId : " + companyId);
        _log.info("creatorUserId : " + creatorUserId);
        _log.info("groupId : " + groupId);
        _log.info("portalURL : " + portalURL);
        _log.info("pathMain : " + pathMain);
        _log.info("name : " + name);
        _log.info("lastName : " + lastName);
        _log.info("nif : " + nif);
        _log.info("email : " + email);
        _log.info("password : " + password);
        _log.info("jobTitle : " + jobTitle);
        _log.info("cmbWorkCenter : " + cmbWorkCenter);
        _log.info("salary : " + salary);
        _log.info("genre : " + genre);
        _log.info("endDate : " + endDate);
        _log.info("active : " + active);
        _log.info("admin : " + admin);
        _log.info("locale : " + locale);
        _log.info("strWorkCenter : " + strWorkCenter);

        long idWorkCenter = 0;
        try{
            idWorkCenter = Long.valueOf(cmbWorkCenter);
        }catch(Exception e){idWorkCenter = 0L;}

        User user = PrevingUserUtil.createUser(companyId, creatorUserId, groupId, portalURL, pathMain, name, lastName, nif, email, password, jobTitle,
                idWorkCenter, salary, genre, endDate, active, admin, locale, strWorkCenter);

    }


}
