package com.preving.liferay.portlet.activity.configuration.web.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.preving.liferay.portlet.activity.configuration.web.constants.ActivityConfigurationPortletKeys;
import com.preving.liferay.portlet.activity.configuration.web.util.PrevingActivityUtil;
import com.preving.liferay.portlet.calendar.manager.model.Activity;
import com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.HolidayLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author agarciap
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.preving",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=false",
                "javax.portlet.display-name=ActivityConfiguration",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.init-param.add-process-action-success-action=false",
                "javax.portlet.name=" + ActivityConfigurationPortletKeys.ACTIVITY_CONFIGURATION,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class ActivityConfigurationPortlet extends MVCPortlet {

    private static Log _log = LogFactoryUtil.getLog(ActivityConfigurationPortlet.class);

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

        super.doView(renderRequest, renderResponse);
    }

    public void create(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        String name = ParamUtil.getString(actionRequest, ActivityConfigurationPortletKeys.ACTIVITY_NAME, "");
        Map<Locale, String> nameLocalized = LocalizationUtil.getLocalizationMap(actionRequest, ActivityConfigurationPortletKeys.ACTIVITY_NAME);

        boolean workTime =  Boolean.parseBoolean(ParamUtil.getString(actionRequest, ActivityConfigurationPortletKeys.ACTIVITY_WORK_TIME));
        int type =  Integer.parseInt(ParamUtil.getString(actionRequest, ActivityConfigurationPortletKeys.ACTIVITY_TYPE));
        String color = ParamUtil.getString(actionRequest, ActivityConfigurationPortletKeys.ACTIVITY_COLOR, "");
        boolean active =  Boolean.parseBoolean(ParamUtil.getString(actionRequest, ActivityConfigurationPortletKeys.ACTIVITY_ACTIVE));
        String observationType=ParamUtil.getString(actionRequest, "observationType", "");
        boolean respo=Boolean.parseBoolean(ParamUtil.getString(actionRequest, "notificate"));
        boolean infoUser=Boolean.parseBoolean(ParamUtil.getString(actionRequest, "notificateAdditional"));
        String usersToInform=ParamUtil.getString(actionRequest, "userAdditional", "");
        if(type==3){
            PrevingActivityUtil.createActivityExtra(
                    themeDisplay.getCompanyId(),
                    themeDisplay.getScopeGroupId(),
                    nameLocalized,
                    workTime,
                    type,
                    color,
                    active,
                    observationType,
                    respo,
                    infoUser,
                    usersToInform
            );
        }
        else {
            PrevingActivityUtil.createActivity(
                    themeDisplay.getCompanyId(),
                    themeDisplay.getScopeGroupId(),
                    nameLocalized,
                    workTime,
                    type,
                    color,
                    active
            );
        }
    }

    public void saveActivity(ActionRequest actionRequest,  ActionResponse actionResponse) {

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        long id = Long.parseLong(ParamUtil.getString(actionRequest, "activityId"));
        Map<Locale, String> nameLocalized = LocalizationUtil.getLocalizationMap(actionRequest, ActivityConfigurationPortletKeys.ACTIVITY_NAME);
        boolean workTime =  Boolean.parseBoolean(ParamUtil.getString(actionRequest, ActivityConfigurationPortletKeys.ACTIVITY_WORK_TIME));
        int type =  Integer.parseInt(ParamUtil.getString(actionRequest, ActivityConfigurationPortletKeys.ACTIVITY_TYPE));
        String color = ParamUtil.getString(actionRequest, ActivityConfigurationPortletKeys.ACTIVITY_COLOR, "");
        boolean active =  Boolean.parseBoolean(ParamUtil.getString(actionRequest, ActivityConfigurationPortletKeys.ACTIVITY_ACTIVE));
        String observationType=ParamUtil.getString(actionRequest, "observationType", "");
        boolean respo=Boolean.parseBoolean(ParamUtil.getString(actionRequest, "notificate"));
        boolean infoUser=Boolean.parseBoolean(ParamUtil.getString(actionRequest, "notificateAdditional"));
        String usersToInform=ParamUtil.getString(actionRequest, "userAdditional", "");

        if(type==3){
            if(!respo){
                infoUser=false;
                usersToInform="";
            }
            PrevingActivityUtil.updateActivityExtra(
                    id,
                    themeDisplay.getCompanyId(),
                    themeDisplay.getScopeGroupId(),
                    nameLocalized,
                    workTime,
                    type,
                    color,
                    active,
                    observationType,
                    respo,
                    infoUser,
                    usersToInform
            );


        }else {


            PrevingActivityUtil.updateActivity(
                    id,
                    themeDisplay.getCompanyId(),
                    themeDisplay.getScopeGroupId(),
                    nameLocalized,
                    workTime,
                    type,
                    color,
                    active
            );
        }
    }

    public void delete(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long activityId = ParamUtil.getLong(actionRequest, "activityId", 0);
        PrevingActivityUtil.deleteActivity(activityId);

    }

}