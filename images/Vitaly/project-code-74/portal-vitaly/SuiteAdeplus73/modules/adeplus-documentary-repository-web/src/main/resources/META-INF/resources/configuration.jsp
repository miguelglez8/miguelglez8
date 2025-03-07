<%@page import="com.adeplus.liferay.portlet.documentary.repository.web.constants.AdeplusDocumentaryRepositoryPortletKeys"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ include file="init.jsp" %>

<%
	PortletPreferences preferences = renderRequest.getPreferences();
	String portletResource = ParamUtil.getString(request, "portletResource");
	if (Validator.isNotNull(portletResource)) {
        preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
    }
	
    String applicationName = preferences.getValue(AdeplusDocumentaryRepositoryPortletKeys.APPLICATION, "");

    String folderFiles = preferences.getValue(AdeplusDocumentaryRepositoryPortletKeys.FOLDER_FILES, "");

%>

<div class="lfr-form-content">
    <div class="sheet sheet-lg">
        <div class="row">
        	<h2><liferay-ui:message key="document.form.config.title" /></h2>
        </div>
        <div class="row">
            <div class="col-sm-12">
            	<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
            	<aui:form action="<%= configurationURL.toString() %>" method="post" >
            		<aui:input name="<%=AdeplusDocumentaryRepositoryPortletKeys.APPLICATION %>" type="text" label="document.form.config.app" helpMessage="document.form.config.app.help" value="<%=applicationName%>">
                        <aui:validator name="required" />
                    </aui:input>
            		<aui:input name="<%=AdeplusDocumentaryRepositoryPortletKeys.FOLDER_FILES %>" type="text" label="document.form.config.folder" helpMessage="document.form.config.folder.help" value="<%=folderFiles%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:button-row>
                        <aui:button type="submit" />
                    </aui:button-row>
            	</aui:form>
            </div>
       	</div>
  	</div>
</div>