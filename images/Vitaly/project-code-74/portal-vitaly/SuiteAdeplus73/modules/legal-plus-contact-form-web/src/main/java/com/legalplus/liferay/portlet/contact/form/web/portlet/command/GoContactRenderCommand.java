package com.legalplus.liferay.portlet.contact.form.web.portlet.command;

import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense;
import com.adeplus.liferay.portlet.suite.manager.model.CompApplication;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLicenseLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.legalplus.liferay.portlet.commons.web.constants.LegalPlusCommonsPortletKeys;
import com.legalplus.liferay.portlet.commons.web.role.LegalplusRoleUtil;
import com.legalplus.liferay.portlet.contact.form.web.bean.Field;
import com.legalplus.liferay.portlet.contact.form.web.constants.ContactPortletKeys;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + ContactPortletKeys.CONTACT,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class GoContactRenderCommand implements MVCRenderCommand {

    private static final Log _log = LogFactoryUtil.getLog(GoContactRenderCommand.class);

    private static final String DEFAULT_ROL = "Default";

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) {
        try {

            ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
            String fieldsJSON = renderRequest.getPreferences().getValue(ContactPortletKeys.FIELDS, StringPool.BLANK);

            if (Validator.isBlank(fieldsJSON)) return "/error.jsp";

            //Query param
            HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
            String consulta = request.getParameter(ContactPortletKeys.PARAM_QUERY);
            if (Validator.isNotNull(consulta))
                renderRequest.setAttribute(ContactPortletKeys.PARAM_CONSULTA, consulta);

            //Rol
            String userRol = "";
            UserCompany company = LegalplusRoleUtil.getUserDefaultCompany(themeDisplay.getUserId());
            if (Validator.isNotNull(company)) {
                long compId = company.getCompId();
                _log.info("CompId: "+compId);
                Application application = ApplicationLocalServiceUtil.getApplicationByName(LegalPlusCommonsPortletKeys.PROPERTY_APP_LEGAL);
                //CompApplication compApp = CompApplicationLocalServiceUtil.getCompanyApplication(compId, application.getApplicationId());
                //ApplicationLicense license = ApplicationLicenseLocalServiceUtil.getApplicationLicense(compApp.getLicenseId());
                ApplicationLicense license = LegalplusRoleUtil.getLicenseNew(compId);
                if(Validator.isNotNull(license)) {
                    userRol = license.getName();
                }
            }

            JSONObject form = JSONFactoryUtil.createJSONObject(fieldsJSON);
            JSONArray fields = form.getJSONArray(ContactPortletKeys.FIELDS);
            List<Field> fieldList = new ArrayList<>();

            for (Object object : fields) {

                String type = ((JSONObject) object).getString(ContactPortletKeys.FIELD_TYPE);
                String name = ((JSONObject) object).getString(ContactPortletKeys.FIELD_NAME);
                boolean required = ((JSONObject) object).getBoolean(ContactPortletKeys.FIELD_REQUIRED);
                JSONObject label = ((JSONObject) object).getJSONObject(ContactPortletKeys.FIELD_LABEL);
                JSONObject values = ((JSONObject) object).getJSONObject(ContactPortletKeys.FIELD_VALUES);

                Field field = new Field();
                field.setType(type);
                field.setName(name);
                field.setRequired(required);
                field.setLabel(label.getString(themeDisplay.getLocale().getLanguage()));

                if (Validator.isNotNull(values)) {

                    Long vocabulary = values.getLong(userRol);
                    if (Validator.isNull(vocabulary)) {
                        vocabulary = values.getLong(DEFAULT_ROL);
                    }
                    List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getVocabularyCategories(vocabulary, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
                    List<String> fieldValues = categories.stream().map((AssetCategory category) -> category.getTitle(themeDisplay.getLocale())).collect(Collectors.toList());
                    field.setValues(fieldValues);

                }

                fieldList.add(field);
            }

            renderRequest.setAttribute(ContactPortletKeys.FIELDS, fieldList);

            return "/view.jsp";
        } catch (PortalException e) {
            _log.error(e, e);
            return "/error.jsp";
        }
    }

}
