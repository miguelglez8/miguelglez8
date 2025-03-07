package com.legalplus.liferay.portlet.admin.categorias.web.portlet.command;

import com.legalplus.liferay.portlet.admin.categorias.web.constants.AdminCategoriasPortletKeys;
import com.legalplus.liferay.portlet.legalplus.manager.model.Legislacion;
import com.legalplus.liferay.portlet.legalplus.manager.service.LegislacionLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.List;
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdminCategoriasPortletKeys.ADMINCATEGORIAS,
                "mvc.command.name=legislacionContainsEtiqueta"
        },
        service  = MVCResourceCommand.class
)
public class LegislacionesContainsEtiqueta extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        Long categoryDeleteId = ParamUtil.getLong(resourceRequest,"etiquetaId", 0);
        boolean existeLegislacionAsignada=LegislacionLocalServiceUtil.legislacionContainsEtiqueta(categoryDeleteId);
        //Se pone el error 404 para saber que no se debe eliminar ninguna etiqueta
        if(!existeLegislacionAsignada){
            resourceResponse.setProperty(ResourceResponse.HTTP_STATUS_CODE, "404");
        }
    }
}
