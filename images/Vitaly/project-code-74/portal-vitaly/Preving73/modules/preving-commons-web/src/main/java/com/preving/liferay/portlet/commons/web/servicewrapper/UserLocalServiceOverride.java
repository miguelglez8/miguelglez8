package com.preving.liferay.portlet.commons.web.servicewrapper;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceWrapper;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.commons.web.keycloak.PrevingKeycloakUtil;
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

        //System.out.println("updatePassword password1 " + password1);
        //System.out.println("updatePassword password2 " + password2);

        User user = UserLocalServiceUtil.fetchUser(userId);

        if(Validator.isNotNull(user)) {
            PrevingKeycloakUtil.changeKCpass(user.getEmailAddress(), password2);
            System.out.println("updatePassword keycloak changed ");
        }

        return updatePassword(userId, password1, password2, passwordReset, true);
    }

    @Override
    public User updatePassword(long userId,  String password1, String password2, boolean passwordReset, boolean silentUpdate)
            throws com.liferay.portal.kernel.exception.PortalException {

        //System.out.println("updatePassword password1 silentUpdate " + password1);
        //System.out.println("updatePassword password2 silentUpdate " + password2);

        User user = UserLocalServiceUtil.fetchUser(userId);

        if(Validator.isNotNull(user)) {
            PrevingKeycloakUtil.changeKCpass(user.getEmailAddress(), password2);
            System.out.println("updatePassword keycloak changed ");
        }

        return super.updatePassword(userId, password1, password2,
                passwordReset, silentUpdate);
    }
}


