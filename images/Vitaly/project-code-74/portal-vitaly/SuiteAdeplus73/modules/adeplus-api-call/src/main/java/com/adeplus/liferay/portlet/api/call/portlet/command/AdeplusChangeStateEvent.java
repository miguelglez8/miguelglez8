package com.adeplus.liferay.portlet.api.call.portlet.command;

import com.adeplus.liferay.portlet.api.call.constants.AdeplusApiCallPortletKeys;
import com.adeplus.liferay.portlet.suite.manager.model.AuditadoDataApi;
import com.adeplus.liferay.portlet.suite.manager.service.AuditadoDataApiLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;


@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" +  AdeplusApiCallPortletKeys.ADEPLUSAPICALL ,
                "mvc.command.name=changeStateEventError"
        },
        service = MVCResourceCommand.class
)
public class AdeplusChangeStateEvent extends BaseMVCResourceCommand {
        private static Log _log = LogFactoryUtil.getLog(AdeplusChangeStateEvent.class);

        @Override
        protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
                ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
                PortletSession portletSession = resourceRequest.getPortletSession();
                HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
                //long idTemporal = ParamUtil.getLong(resourceRequest,"idTemporal",0);

                if(Validator.isNull(httpReq.getParameter("idTemporal")) || httpReq.getParameter("idTemporal").isEmpty()) return;

                AuditadoDataApi audit = AuditadoDataApiLocalServiceUtil.fetchAuditadoDataApi(Long.valueOf(httpReq.getParameter("idTemporal")));

                if(Validator.isNull(audit)) return;

                audit.setState(!audit.getState());
                if(!audit.getState()){
                        audit.setChangeStateDate(null);
                        audit.setUserIdChangeState(0);
                }else{
                        audit.setChangeStateDate(Calendar.getInstance().getTime());
                        audit.setUserIdChangeState(themeDisplay.getUserId());
                }


                AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(audit);


        }
}
