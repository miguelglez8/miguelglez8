package com.adeplus.liferay.portlet.commons.web.servicewrapper;

import com.adeplus.liferay.portlet.commons.web.keycloak.AdeplusKeycloakUtil;
import com.adeplus.liferay.portlet.mailing.web.mail.UserMailing;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceWrapper;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = {
        },
        service = ServiceWrapper.class)

public class UserLocalServiceOverride extends UserLocalServiceWrapper {

    public UserLocalServiceOverride() {
        super(null);
    }

    @Override
    public User updatePassword(long userId, String password1, String password2, boolean passwordReset)
            throws com.liferay.portal.kernel.exception.PortalException {

        User user = UserLocalServiceUtil.fetchUser(userId);

        return updatePassword(userId, password1, password2, passwordReset, true);
    }





    @Override
    public User updatePassword(long userId,  String password1, String password2, boolean passwordReset, boolean silentUpdate)
            throws com.liferay.portal.kernel.exception.PortalException {

        System.out.println("updatePassword password1 " + !Validator.isBlank(password1) );
        System.out.println("updatePassword password2 " + !Validator.isBlank(password2) );

        User user = UserLocalServiceUtil.fetchUser(userId);

        if(Validator.isNotNull(user)) {
            AdeplusKeycloakUtil.changeKCpass(user.getEmailAddress(), password2);
            System.out.println("  updatePassword keycloak changed ");

            UserMailing.userPasswordChanged(user);
            System.out.println("  updatePassword email sended.");

        }

        return super.updatePassword(userId, password1, password2, passwordReset, silentUpdate);
    }
}


