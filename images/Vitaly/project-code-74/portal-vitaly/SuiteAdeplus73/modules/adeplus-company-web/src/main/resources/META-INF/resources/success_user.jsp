<%@ page import="com.adeplus.liferay.portlet.company.web.constants.AdeplusCompaniesPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ include file="init.jsp" %>

<%
    long userCompId = ParamUtil.getLong(request, AdeplusCompaniesPortletKeys.COMPANY_ID_EDIT, 0);
%>

<liferay-ui:success key="save-user-success" message="user.view.save.user.success" />


<h2 class="text-center"><liferay-ui:message key="user.success.title"/></h2>

<div>
    <p class="text-center m-5"><liferay-ui:message key="user.success.message"/></p>
</div>

<div class="button-holder d-flex justify-content-center my-4">
    <aui:button-row>
        <portlet:actionURL name="search" var="backURL">
            <portlet:param name="<%=AdeplusCompaniesPortletKeys.COMPANY_ID_EDIT%>" value="<%=String.valueOf(userCompId)%>" />
        </portlet:actionURL>
        <aui:button onClick="<%= backURL.toString() %>" value="user.edit.back" cssClass="btn btn-outline-primary mr-4" primary="true"></aui:button>
    </aui:button-row>
</div>