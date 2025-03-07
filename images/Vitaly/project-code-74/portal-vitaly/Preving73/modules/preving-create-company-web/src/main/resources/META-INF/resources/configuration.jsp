<%@ page import="com.liferay.portal.kernel.util.LocalizationUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ include file="init.jsp" %>

<%
    PortletPreferences preferences = renderRequest.getPreferences();
    String portletResource = ParamUtil.getString(request, "portletResource");
    if (Validator.isNotNull(portletResource)) {
        preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
    }

    String templateId = preferences.getValue(CreateCompanyPortletKeys.CONFIGURATION_TEMPLATE_ID, "0");

    List<LayoutSetPrototype> templateList = LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototypes(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

    String importHelpText = LocalizationUtil.getLocalizationXmlFromPreferences(preferences, renderRequest, "importHelpText");

    String mailSubject = LocalizationUtil.getLocalizationXmlFromPreferences(preferences, renderRequest, CreateCompanyPortletKeys.CONFIGURATION_MAIL_SUBJECT);
    String mailBody = LocalizationUtil.getLocalizationXmlFromPreferences(preferences, renderRequest, CreateCompanyPortletKeys.CONFIGURATION_MAIL_BODY);

%>

<c:set var="templateId" value="<%=templateId%>" />

<div class="lfr-form-content">
    <div class="sheet sheet-lg">
        <div class="row">
            <h2><liferay-ui:message key="createcompany.config.title" /></h2>
        </div>
        <div class="row">
            <liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
            <aui:form action="<%= configurationURL.toString() %>" method="post" >

                <aui:fieldset label="createcompany.config.template">
                    <aui:select name="templateId" label="createcompany.config.template.title" helpMessage="createcompany.config.template.help">
                        <c:forEach var="template" items="<%=templateList%>">
                            <p>t: ${template.layoutSetPrototypeId} </p>
                            <aui:option value="${template.layoutSetPrototypeId}" selected="${template.layoutSetPrototypeId == templateId?true:false}">
                                ${template.getName(themeDisplay.locale)}
                            </aui:option>
                        </c:forEach>
                    </aui:select>
                </aui:fieldset>

                <aui:fieldset label="createcompany.config.template.importation">
                    <aui:field-wrapper cssClass="lfr-input-text-container" label="createcompany.config.template.importation.title">
                        <liferay-ui:input-localized name="importHelpText" xml="<%=importHelpText%>" type="editor" label="import.text" />
                    </aui:field-wrapper>
                </aui:fieldset>

                <%--<aui:fieldset label="createcompany.config.mail.date.end">
                    <aui:field-wrapper cssClass="lfr-input-text-container" label="createcompany.config.mail.subject">
                        <liferay-ui:input-localized name="mailSubject" xml="<%=mailSubject%>" type="editor" />
                    </aui:field-wrapper>

                    <aui:field-wrapper cssClass="lfr-input-text-container" label="createcompany.config.mail.date.end.body">
                        <liferay-ui:input-localized name="mailBody" xml="<%=mailBody%>" type="editor" />
                    </aui:field-wrapper>
                </aui:fieldset>--%>

                <aui:button-row>
                    <aui:button type="submit" />
                </aui:button-row>
            </aui:form>
        </div>

    </div>
</div>