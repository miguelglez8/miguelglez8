package com.legalplus.liferay.portlet.admin.categorias.web.portlet.command;

import com.legalplus.liferay.portlet.admin.categorias.web.constants.AdminCategoriasPortletKeys;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.util.Locale;
import java.util.Map;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdminCategoriasPortletKeys.ADMINCATEGORIAS,
                "mvc.command.name=/category/save"
        },
        service = MVCActionCommand.class
)
public class SaveCategoriaActionCommand implements MVCActionCommand {

        private static Log _log = LogFactoryUtil.getLog(SaveCategoriaActionCommand.class);

        @Override
        public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
                try {
                        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
                        AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(themeDisplay.getScopeGroupId(), AdminCategoriasPortletKeys.VOCABULARIO_CONSULTOR);

                        long categoryEditId = ParamUtil.getLong(actionRequest, AdminCategoriasPortletKeys.CATEGORY_ID, 0);
                        Map<Locale, String> title = LocalizationUtil.getLocalizationMap(actionRequest, AdminCategoriasPortletKeys.CATEGORY_TITLE);
                        Map<Locale, String> description = LocalizationUtil.getLocalizationMap(actionRequest, AdminCategoriasPortletKeys.CATEGORY_DESCRIPTION);

                        AssetCategory category = AssetCategoryLocalServiceUtil.fetchCategory(categoryEditId);

                        if (Validator.isNull(category)) {

                                //Create new category
                                AssetCategoryLocalServiceUtil.addCategory(
                                        themeDisplay.getUserId(),
                                        themeDisplay.getScopeGroupId(),
                                        0,
                                        title,
                                        description,
                                        vocabulary.getVocabularyId(),
                                        new String[]{""},
                                        new ServiceContext()
                                );

                        } else {

                                category.setTitleMap(title);
                                category.setDescriptionMap(description);

                                AssetCategoryLocalServiceUtil.updateAssetCategory(category);

                        }

                        SessionMessages.add(actionRequest, "categoria-save-success");

                        return false;
                } catch (PortalException e) {
                        _log.error(e, e);
                        throw  new PortletException(e);
                }
        }

}
