package com.legalplus.liferay.portlet.web.contrato.portlet.command;

import com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento;
import com.legalplus.liferay.portlet.legalplus.manager.service.AyuntamientoLocalServiceUtil;
import com.legalplus.liferay.portlet.web.contrato.constants.WebContratoPortletKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + WebContratoPortletKeys.WEBCONTRATO,
                "mvc.command.name=buscarAyuntamientos"
        },
        service = MVCResourceCommand.class
)
public class FindAyuntamientosResourceCommand extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(FindAyuntamientosResourceCommand.class);

    public static final String PARAM_QUERY = "ccaaIds";
    public static final String ARRAY_DELIMITER = ",";

    public static final String AYTO_ID = "ayuntamientoId";
    public static final String AYTO_NOMBRE = "nombre";

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        String ccaaIds = httpReq.getParameter(PARAM_QUERY);

        JSONArray json = JSONFactoryUtil.createJSONArray();

        if (Validator.isNotNull(ccaaIds)) {
            for (String ca : ccaaIds.split(ARRAY_DELIMITER)) {
                List<Ayuntamiento> aytos = AyuntamientoLocalServiceUtil.getAyuntamientosByCcaa(Long.valueOf(ca));

                for (Ayuntamiento ayto : aytos) {
                    JSONObject curAyto = JSONFactoryUtil.createJSONObject();
                    curAyto.put(AYTO_ID, ayto.getAyuntamientoId());
                    curAyto.put(AYTO_NOMBRE, ayto.getNombre());

                    json.put(curAyto);
                }

            }
        }

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());
    }

}
