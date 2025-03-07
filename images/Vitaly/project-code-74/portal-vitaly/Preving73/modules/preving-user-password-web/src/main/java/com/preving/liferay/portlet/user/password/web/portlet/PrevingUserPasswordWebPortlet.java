package com.preving.liferay.portlet.user.password.web.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.exception.UserPasswordException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.preving.liferay.portlet.user.password.web.constants.PrevingUserPasswordWebPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.ProcessAction;
import java.io.IOException;

/**
 * @author agarciap
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.preving",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=false",
                "javax.portlet.display-name=PrevingUserPasswordWeb",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + PrevingUserPasswordWebPortletKeys.PREVINGUSERPASSWORDWEB,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class PrevingUserPasswordWebPortlet extends MVCPortlet {
    
    @ProcessAction(name = "updatePassword")
    public void updatePassword(ActionRequest actionRequest, ActionResponse actionResponse) {
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        _log.info(":::::::::::::calling updatePassword::::::::::::::::");

        String current = ParamUtil.getString(actionRequest, "current", "");
        String password1 = ParamUtil.getString(actionRequest, "password1", "");
        String password2 = ParamUtil.getString(actionRequest, "password2", "");
        String errorKey = "";

        try {
            String authType = themeDisplay.getCompany().getAuthType();
            String login = "";

            /**
             * authType can be of three types.
             * Therefore based on authType login can email address or
             * screen name or user id of the logged in user
             */
            if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
                login = themeDisplay.getUser().getEmailAddress();
            } else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
                login = themeDisplay.getUser().getScreenName();
            } else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
                login = String.valueOf(themeDisplay.getUser().getUserId());
            }

            /**
             * The method authenticateForBasic returns userId of the logged in user if all
             * the parameters in the method are correct. Otherwise it will return 0.
             * Notice the if condition
             */

            /*long userId = UserLocalServiceUtil.authenticateForBasic(themeDisplay.getCompanyId(), authType, login, current);
            if (themeDisplay.getUserId() != userId) {
                errorKey = "invalid-current-password";
                throw new Exception("Invalid current password.");
            }*/
            if (!password1.equals(password2)) {
                errorKey = "confirm-new-password";
                throw new Exception("Please confirm new password.");
            }
            
            UserLocalServiceUtil.updatePassword(themeDisplay.getUserId(), password1, password2, false);

        } catch (UserPasswordException e) {
            errorKey = "user-password-error";
            _log.error(e.getMessage(), e);
        } catch (PortalException e) {
            errorKey = "password-error";
            _log.error(e.getMessage(), e);
        } catch (SystemException e) {
            errorKey = "password-error";
            _log.error(e.getMessage(), e);
        } catch (Exception e) {
            errorKey = "password-error";
            _log.error(e.getMessage(), e);
        }

        if (Validator.isNull(errorKey)) {
            SessionMessages.add(actionRequest, "password-update-success");
            try {
				actionResponse.sendRedirect("/c/portal/login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
            SessionErrors.add(actionRequest, errorKey);
        }

    }

    private Log _log = LogFactoryUtil.getLog(PrevingUserPasswordWebPortlet.class.getName());
}