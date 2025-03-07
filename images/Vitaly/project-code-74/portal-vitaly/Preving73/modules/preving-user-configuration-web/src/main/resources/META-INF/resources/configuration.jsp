
<%@ page import="com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.LocalizationUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ include file="init.jsp" %>

<%
    PortletPreferences preferences = renderRequest.getPreferences();
    String portletResource = ParamUtil.getString(request, "portletResource");
    if (Validator.isNotNull(portletResource)) {
        preferences = (PortletPreferences) PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
    }

    String importHelpText = LocalizationUtil.getLocalizationXmlFromPreferences(preferences, renderRequest, "importHelpText");
%>

<div class="lfr-form-content">
    <div class="sheet sheet-lg">
        <div class="row">
            <h2><liferay-ui:message key="user.configuration.config.title" /></h2>
        </div>
        <div class="row">
            <liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
            <aui:form name="configuration_form" action="<%= configurationURL.toString() %>" method="post" >
                <aui:fieldset>
                    <aui:field-wrapper cssClass="lfr-input-text-container" label="title">
                        <liferay-ui:input-localized name="importHelpText" xml="<%=importHelpText%>" type="editor" label="import.text" />
                    </aui:field-wrapper>
                </aui:fieldset>

                <aui:button-row>
                    <aui:button type="submit" />
                </aui:button-row>
            </aui:form>
        </div>
    </div>
</div>