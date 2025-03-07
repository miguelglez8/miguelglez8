package com.plan.igualdad.liferay.portlet.fechas.web.util;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.fechas.web.constants.PlanIgualdadFechasWebPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

public class FechasUtils {

	public static String redirectURL(ActionRequest actionRequest, ThemeDisplay themeDisplay, Long compId, Long versionId) {
        String portletName = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
        PortletURL redirectURL = PortletURLFactoryUtil.create(actionRequest, portletName, themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        redirectURL.setParameter("mvcRenderCommandName", "/");
        redirectURL.setParameter(PlanIgualdadFechasWebPortletKeys.COMPID_PARAM, String.valueOf(compId));
        redirectURL.setParameter(PlanIgualdadFechasWebPortletKeys.VERSIONID_PARAM, String.valueOf(versionId));
        return  redirectURL.toString();
    }
}
