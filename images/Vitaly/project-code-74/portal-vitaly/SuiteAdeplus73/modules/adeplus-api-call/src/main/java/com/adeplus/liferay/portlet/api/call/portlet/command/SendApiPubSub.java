package com.adeplus.liferay.portlet.api.call.portlet.command;

import com.adeplus.liferay.portlet.api.call.constants.AdeplusApiCallPortletKeys;
import com.adeplus.liferay.portlet.api.call.portlet.PubSubSuscriber;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + AdeplusApiCallPortletKeys.ADEPLUSAPICALL ,
                "mvc.command.name=sendApiPubSub"
        },
        service = MVCResourceCommand.class
)
public class SendApiPubSub implements MVCResourceCommand{
    public static Log _log = LogFactoryUtil.getLog(SendApiPubSub.class);

    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException {
        long idRole = 0;
        boolean status=Boolean.TRUE;
        List<Role> roles = RoleLocalServiceUtil.getRoles(QueryUtil.ALL_POS,QueryUtil.ALL_POS);
        for (Role role : roles){
            if(role.getName().equals("Administrator")){
                idRole = role.getRoleId();
                break;
            }
        }
        _log.info(PubSubSuscriber.class.getName());
        List<User> lstUsers = UserLocalServiceUtil.getRoleUsers(idRole);
        User user = (lstUsers.size() > 0)? lstUsers.get(0) : null;
        Map map = new HashMap();
        _log.info("#### usuario admin envio : " + user.getEmailAddress());
        try {
            BackgroundTaskManagerUtil.addBackgroundTask(user.getUserId(), user.getGroupId(),
                    PubSubSuscriber.class.getName(), PubSubSuscriber.class.getName(),map, new ServiceContext());
        } catch (PortalException e) {
           status=Boolean.FALSE;
        }
        return status;
    }
}
