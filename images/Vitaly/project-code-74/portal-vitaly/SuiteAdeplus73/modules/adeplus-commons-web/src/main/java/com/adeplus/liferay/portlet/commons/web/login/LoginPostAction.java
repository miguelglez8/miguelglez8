package com.adeplus.liferay.portlet.commons.web.login;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.*;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(
        immediate = true,
        property = {
                "key=login.events.post"
        },
        service = LifecycleAction.class
)
public class LoginPostAction implements LifecycleAction {

    private static Log _log = LogFactoryUtil.getLog(LoginPostAction.class);

    private static final String DEFAULT_LANDING_PAGE_PATH  = "/web/guest/dashboard";

    @Override
    public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {
        try {

            HttpServletRequest request = lifecycleEvent.getRequest();
            HttpServletResponse response = lifecycleEvent.getResponse();

            String isKeycloak = request.getPathInfo();
            if (Validator.isNotNull(isKeycloak)) {
                request.setAttribute(WebKeys.REFERER, DEFAULT_LANDING_PAGE_PATH);
                response.sendRedirect(DEFAULT_LANDING_PAGE_PATH);
            }

        } catch (IOException e) {
            _log.error(e, e);
            throw new ActionException(e);
        }
    }
}