package com.adeplus.liferay.portlet.commons.web.logout;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PrefsPropsUtil;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(
        immediate = true,
        property = {
                "key=logout.events.post"
        },
        service = LifecycleAction.class
)
public class LogoutPostAction implements LifecycleAction {

    private static Log _log = LogFactoryUtil.getLog(LogoutPostAction.class);

    @Override
    public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {

        /*try {

            HttpServletRequest request = lifecycleEvent.getRequest();
            HttpServletResponse response = lifecycleEvent.getResponse();

            long companyId = PortalUtil.getCompanyId(request);

            String path = PrefsPropsUtil.getString(
                    companyId, PropsKeys.DEFAULT_LOGOUT_PAGE_PATH);

            if (_log.isDebugEnabled()) {
                _log.debug("Running " + request.getRemoteUser());
                _log.debug(PropsKeys.DEFAULT_LOGOUT_PAGE_PATH + StringPool.EQUAL + path);
            }

            if (Validator.isNotNull(path)) {
                request.setAttribute(WebKeys.REFERER, path);
                response.sendRedirect(path);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}