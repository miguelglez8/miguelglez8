package com.legalplus.liferay.portlet.contact.form.web.portlet.command;

import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.legalplus.liferay.portlet.commons.web.role.LegalplusRoleUtil;
import com.legalplus.liferay.portlet.contact.form.web.constants.ContactPortletKeys;
import com.legalplus.liferay.portlet.mailing.web.mail.ContactFormMailing;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + ContactPortletKeys.CONTACT,
                "mvc.command.name=/contacto/save"
        },
        service = MVCActionCommand.class
)
public class SaveContactActionCommand implements MVCActionCommand {

        private static final Log _log = LogFactoryUtil.getLog(SaveContactActionCommand.class);

        @Override
        public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
                try {
                        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
                        UserCompany userCompany = LegalplusRoleUtil.getUserDefaultCompany(themeDisplay.getUserId());
                        Comp comp = Validator.isNotNull(userCompany) ? CompLocalServiceUtil.getComp(userCompany.getCompId()) : null;

                        String fieldsJSON = actionRequest.getPreferences().getValue(ContactPortletKeys.FIELDS, StringPool.BLANK);
                        String userEmailArticleIdRequerido = actionRequest.getPreferences().getValue(ContactPortletKeys.USER_EMAIL_ARTICLE_ID, StringPool.BLANK);
                        String respEmailArticleIdRequerido = actionRequest.getPreferences().getValue(ContactPortletKeys.RESP_EMAIL_ARTICLE_ID, StringPool.BLANK);
                        String adminEmails = actionRequest.getPreferences().getValue(ContactPortletKeys.EMAIL_TO, StringPool.BLANK);
                        boolean sendEmailToUser = GetterUtil.getBoolean(actionRequest.getPreferences().getValue(ContactPortletKeys.SEND_EMAIL_TO_USER, StringPool.FALSE));

                        JSONObject form = JSONFactoryUtil.createJSONObject(fieldsJSON);
                        JSONArray fields = form.getJSONArray(ContactPortletKeys.FIELDS);

                        File file = null;
                        String fileName = "";

                        Map<String, String> results = new HashMap<>();
                        results.put(ContactPortletKeys.KEYWORD_NAME, themeDisplay.getUser().getFullName());
                        results.put(ContactPortletKeys.KEYWORD_EMAIL, themeDisplay.getUser().getEmailAddress());
                        results.put(ContactPortletKeys.KEYWORD_COMPANY, Validator.isNotNull(comp) ? comp.getName() : StringPool.BLANK);

                        for (Object object : fields) {
                                String name = ((JSONObject) object).getString(ContactPortletKeys.FIELD_NAME);
                                String type = ((JSONObject) object).getString(ContactPortletKeys.FIELD_TYPE);
                                String value = "";

                                if (ContactPortletKeys.FILE_TYPE.equals(type)) {
                                        UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
                                        value = uploadRequest.getFileName(name);
                                        fileName = uploadRequest.getFileName(name);
                                        file = uploadRequest.getFile(name);
                                } else {
                                        value = ParamUtil.getString(actionRequest, name, StringPool.BLANK);
                                }

                                results.put(name, value);
                        }

                        //Send email to admins
                        JournalArticle adminArticle = JournalArticleLocalServiceUtil.getArticle(themeDisplay.getScopeGroupId(), respEmailArticleIdRequerido);
                        Document adminDoc = SAXReaderUtil.read(adminArticle.getContentByLocale(themeDisplay.getLanguageId()));
                        Node adminArticleContent = adminDoc.selectSingleNode("/root/dynamic-element[@name=\"content\"]");
                        String adminEmailBody = adminArticleContent.getStringValue();

                        for (Map.Entry<String, String> entry : results.entrySet()) {
                                adminEmailBody = StringUtil.replace(adminEmailBody, "[$" + entry.getKey() + "$]", entry.getValue());
                        }

                        String[] emails = StringUtil.split(adminEmails, StringPool.COMMA);
                        for (String email : emails) {
                                ContactFormMailing.contactToAdmin(email, adminArticle.getTitle(themeDisplay.getLocale()), adminEmailBody, file, fileName);
                        }

                        //Send email to user
                        if (sendEmailToUser) {
                                JournalArticle userArticle = JournalArticleLocalServiceUtil.getArticle(themeDisplay.getScopeGroupId(), userEmailArticleIdRequerido);
                                Document userDoc = SAXReaderUtil.read(userArticle.getContentByLocale(themeDisplay.getLanguageId()));
                                Node userArticleContent = userDoc.selectSingleNode("/root/dynamic-element[@name=\"content\"]");
                                String userEmailBody = userArticleContent.getStringValue();

                                for (Map.Entry<String, String> entry : results.entrySet()) {
                                        userEmailBody = StringUtil.replace(userEmailBody, "[$" + entry.getKey() + "$]", entry.getValue());
                                }

                                ContactFormMailing.contactToUser(themeDisplay.getUser(), userArticle.getTitle(themeDisplay.getLocale()), userEmailBody, file, fileName);
                        }

                        SessionMessages.add(actionRequest, "contacto-save-success");

                        String portletName = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
                        PortletURL redirectURL = PortletURLFactoryUtil.create(actionRequest, portletName, themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
                        actionResponse.sendRedirect(redirectURL.toString());

                        return false;
                } catch (PortalException | DocumentException | IOException e) {
                        _log.error(e, e);
                        throw new PortletException(e);
                }
        }

}
