package com.legalplus.liferay.portlet.web.legislaciones.portlet.command;

import com.legalplus.liferay.portlet.legalplus.manager.model.HiddenLegis;
import com.legalplus.liferay.portlet.legalplus.manager.service.HiddenLegisLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.HiddenLegisPK;
import com.legalplus.liferay.portlet.web.legislaciones.constants.WebLegislacionesPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + WebLegislacionesPortletKeys.WEBLEGISLACIONES,
                "mvc.command.name=cambiarEstadoLegislaciones"
        },
        service = MVCResourceCommand.class
)

public class CreateHiddeLegislacionResourceCommand extends BaseMVCResourceCommand {
    private static Log _log = LogFactoryUtil.getLog(CreateHiddeLegislacionResourceCommand.class);

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        long compId = ParamUtil.getLong(resourceRequest, WebLegislacionesPortletKeys.COMPANY_ID, 0);
        String[] legislacionesIds= ParamUtil.getStringValues(resourceRequest, WebLegislacionesPortletKeys.LEGISLACIONESID,null);
        _log.info("String: "+ ParamUtil.getString(resourceRequest, WebLegislacionesPortletKeys.LEGISLACIONESID,""));
        if (Validator.isNotNull(legislacionesIds) && compId>0){
            HiddenLegisPK hiddenLegisPK=new HiddenLegisPK();
            hiddenLegisPK.empresaId=compId;
            _log.info("compId: "+compId);
            _log.info("legislacionesIds length: "+legislacionesIds.length);
            for(String legislacionId:legislacionesIds){
                _log.info("legislacionesIds: "+legislacionId);
                hiddenLegisPK.legislacionId=legislacionId;
                //Si no existe esta se tiene que crear para ocultarse
                if(Validator.isNull(HiddenLegisLocalServiceUtil.fetchHiddenLegis(hiddenLegisPK))){
                    HiddenLegis hl=HiddenLegisLocalServiceUtil.createHiddenLegis(hiddenLegisPK);
                    HiddenLegisLocalServiceUtil.updateHiddenLegis(hl);
                }else{
                    //Si ya existia entendemos que lo que quieren es que se vuelva visible una vez mas por lo
                    //tanto la eliminamos de la tabla para que vuelva a mostrarse
                    try {
                        HiddenLegisLocalServiceUtil.deleteHiddenLegis(hiddenLegisPK);
                    }catch(Exception e){
                        _log.error("No se elimino de la tabla ocultosdebido al error: "+e.getMessage());
                    }
                }
            }
        }else{
            _log.error("El id de la empresa o las legislaciones son nulas");
        }
    }
}
