<%@ page import="com.legalplus.liferay.portlet.contact.form.web.constants.ContactPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="javax.portlet.PortletPreferences" %>

<%@ include file="init.jsp" %>

<%

    PortletPreferences preferences = renderRequest.getPreferences();
    String portletResource = ParamUtil.getString(request, "portletResource");
    if (Validator.isNotNull(portletResource)) {
        preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
    }

    String fields = preferences.getValue(ContactPortletKeys.FIELDS, "");
    String emailTo = preferences.getValue(ContactPortletKeys.EMAIL_TO, "");
    String userEmailArticleId = preferences.getValue(ContactPortletKeys.USER_EMAIL_ARTICLE_ID, "");
    String respEmailArticleId = preferences.getValue(ContactPortletKeys.RESP_EMAIL_ARTICLE_ID, "");
    boolean sendEmailToUser = GetterUtil.getBoolean(preferences.getValue(ContactPortletKeys.SEND_EMAIL_TO_USER, "false"));

%>

<div class="lfr-form-content">
    <div class="sheet sheet-lg">
        <div class="row">
            <div class="col-sm-12">
                <liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
                <aui:form action="<%= configurationURL.toString() %>" method="post" >

                    <aui:input name="fields" type="textarea" label="contact.form.config.fields" helpMessage="contact.form.config.fields.help" value="<%= fields %>">
                        <aui:validator name="required" />
                    </aui:input>

                    <aui:input name="emailTo" type="text" label="contact.form.config.emailTo" value="<%= emailTo %>">
                        <aui:validator name="required" />
                    </aui:input>

                    <aui:input name="respEmailArticleId" type="text" label="contact.form.config.respEmailArticleId" value="<%= respEmailArticleId %>">
                        <aui:validator name="required" />
                    </aui:input>

                    <aui:input name="sendEmailToUser" label="contact.form.config.sendUserMail" type="checkbox" checked="<%= sendEmailToUser %>" cssClass="mb-3"></aui:input>

                    <aui:input name="userEmailArticleId" type="text" label="contact.form.config.userEmailArticleId" value="<%= userEmailArticleId %>"></aui:input>

                    <aui:button-row>
                        <aui:button type="submit" />
                    </aui:button-row>

                </aui:form>
            </div>
        </div>
    </div>
</div>