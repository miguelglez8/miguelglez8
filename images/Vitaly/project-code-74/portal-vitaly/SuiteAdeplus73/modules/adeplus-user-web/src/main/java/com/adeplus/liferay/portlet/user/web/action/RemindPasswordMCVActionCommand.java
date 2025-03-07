package com.adeplus.liferay.portlet.user.web.action;

import com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtil;
import com.adeplus.liferay.portlet.mailing.web.mail.UserMailing;
import com.adeplus.liferay.portlet.user.web.constants.AdeplusUsersPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdeplusUsersPortletKeys.ADEPLUSUSERS,
                "mvc.command.name=/user/remindpassword"
        },
        service = MVCActionCommand.class
)
public class RemindPasswordMCVActionCommand implements MVCActionCommand {

   private static Log _log = LogFactoryUtil.getLog(RemindPasswordMCVActionCommand.class);

   @Override
   public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

      long userId = ParamUtil.getLong(actionRequest, AdeplusUsersPortletKeys.USER_ID, 0);
      long userCompId = ParamUtil.getLong(actionRequest, AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT, 0);

      User user = null;
      try {
         user = UserLocalServiceUtil.getUser(userId);

         if(Validator.isNotNull(user)){
            //AdeplusUserUtil.remindPassword(user, userCompId);
            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
            UserMailing.userChangePass(ParamUtil.getString(actionRequest, "ticketKey"), themeDisplay);
            _log.info("RemindPasswordMCVActionCommand");
         }

      } catch (PortalException e) {
         _log.error(e);
         return false;
      }

      return true;
   }
}
