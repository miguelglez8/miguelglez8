package com.legalplus.liferay.portlet.admin.categorias.web.portlet.command;

import com.legalplus.liferay.portlet.admin.categorias.web.constants.AdminCategoriasPortletKeys;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + AdminCategoriasPortletKeys.ADMINCATEGORIAS,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class GoListCategoriasRenderCommand implements MVCRenderCommand {

        private static Log _log = LogFactoryUtil.getLog(GoListCategoriasRenderCommand.class);

        @Override
        public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
                try {
                        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
                        AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(themeDisplay.getScopeGroupId(), AdminCategoriasPortletKeys.VOCABULARIO_CONSULTOR);
                        List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getVocabularyCategories(vocabulary.getVocabularyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

                        renderRequest.setAttribute(AdminCategoriasPortletKeys.CATEGORIES_LIST, categories);

                        return "/view.jsp";
                } catch (PortalException e) {
                        _log.error(e, e);
                        throw  new PortletException(e);
                }
        }
}
