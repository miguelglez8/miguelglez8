package com.legalplus.liferay.portlet.web.evaluacion.utils;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.util.Map;

public class RedirectUtil {

    public static final String getRedirectURL(PortletRequest portletRequest, ThemeDisplay themeDisplay, Map<String, String> params) {
        String portletName = (String) portletRequest.getAttribute(WebKeys.PORTLET_ID);
        PortletURL redirectURL = PortletURLFactoryUtil.create(portletRequest, portletName, themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            redirectURL.setParameter(entry.getKey() , entry.getValue());
        }
        return redirectURL.toString();
    }
}
