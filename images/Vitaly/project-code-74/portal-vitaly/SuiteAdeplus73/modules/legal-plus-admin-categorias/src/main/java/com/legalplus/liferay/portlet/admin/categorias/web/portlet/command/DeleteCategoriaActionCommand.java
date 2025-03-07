package com.legalplus.liferay.portlet.admin.categorias.web.portlet.command;

import com.legalplus.liferay.portlet.admin.categorias.web.constants.AdminCategoriasPortletKeys;
import com.legalplus.liferay.portlet.legalplus.manager.model.Legislacion;
import com.legalplus.liferay.portlet.legalplus.manager.service.LegislacionLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdminCategoriasPortletKeys.ADMINCATEGORIAS,
                "mvc.command.name=/category/delete"
        },
        service = MVCActionCommand.class
)
public class DeleteCategoriaActionCommand implements MVCActionCommand {

        @Override
        public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
                Long categoryDeleteId = ParamUtil.getLong(actionRequest, AdminCategoriasPortletKeys.DELETE_ID, 0);
                AssetCategory category = AssetCategoryLocalServiceUtil.fetchCategory(categoryDeleteId);


                if (Validator.isNotNull(category)) {
                        AssetCategoryLocalServiceUtil.deleteAssetCategory(category);
                }

                SessionMessages.add(actionRequest, "categoria-delete-success");

                return false;
        }

}
