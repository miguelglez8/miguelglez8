package com.preving.liferay.portlet.scheduler.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.preving.liferay.portlet.scheduler.web.constants.PrevingSchedulerPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.preving",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=PrevingScheduler",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PrevingSchedulerPortletKeys.PREVINGSCHEDULER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PrevingSchedulerPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(PrevingSchedulerPortlet.class);

	public void deleteScheduler(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String groupName 	= ParamUtil.getString(actionRequest, "groupName", "");
		String storageType 			= ParamUtil.getString(actionRequest, "storageType", "");

		if(_log.isDebugEnabled()){
			_log.debug("groupName: " + groupName);
			_log.debug("storageType: " + storageType);
		}
		StorageType st = null;

		if(storageType.equals(StorageType.MEMORY.toString())){
			st = StorageType.MEMORY;
		}else if(storageType.equals(StorageType.PERSISTED.toString())){
			st = StorageType.PERSISTED;
		}else if(storageType.equals(StorageType.MEMORY_CLUSTERED.toString())){
			st = StorageType.MEMORY_CLUSTERED;
		}

		SchedulerEngineHelperUtil.delete(groupName, st);

		System.out.println("Borrado proceso " + groupName);

	}

}