<%@page import=" com.adeplus.liferay.portlet.company.web.constants.AdeplusCompaniesPortletKeys"%>
<%@page import="com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@ include file="init.jsp" %>

<%
	PortletPreferences preferences = renderRequest.getPreferences();
	String portletResource = ParamUtil.getString(request, "portletResource");
	if (Validator.isNotNull(portletResource)) {
        preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
    }
	
    String cc_email_cfg = preferences.getValue(AdeplusCompaniesPortletKeys.CC_EMAIL_CFG, "");


%>

<div class="lfr-form-content">
    <div class="sheet sheet-lg">
        <div class="row">
        	<h2><liferay-ui:message key="cfg.view.emails_cc" /></h2>
        </div>
        <div class="row">
            <div class="col-sm-12">
            	<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
            	<aui:form action="<%= configurationURL.toString() %>" method="post" >
            		<aui:input name="<%=AdeplusCompaniesPortletKeys.CC_EMAIL_CFG %>" type="text" label="Emails de CC de todas las comunicaciones, separados por comas" value="<%=cc_email_cfg%>">
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