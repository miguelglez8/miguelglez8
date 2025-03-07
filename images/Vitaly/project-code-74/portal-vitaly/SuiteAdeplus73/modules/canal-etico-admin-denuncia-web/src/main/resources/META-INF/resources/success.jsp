<%@ page import="com.canal.etico.liferay.portlet.admin.denuncia.web.constants.AdminDenunciaPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ include file="init.jsp" %>

<%
    long denunciaId = ParamUtil.getLong(request, AdminDenunciaPortletKeys.DENUNCIA_ID, 0);
%>

<h2 class="text-center"><liferay-ui:message key="denuncia.edit.finalizar.message.success.title"/></h2>

<div>
    <p class="text-center m-5"><liferay-ui:message key="denuncia.edit.finalizar.message.success"/></p>
</div>

<div class="button-holder d-flex justify-content-center my-4">
    <aui:button-row>
        <portlet:actionURL name="search" var="backURL">
            <portlet:param name="<%=AdminDenunciaPortletKeys.DENUNCIA_ID%>" value="<%=String.valueOf(denunciaId)%>" />
        </portlet:actionURL>
        <aui:button onClick="<%= backURL.toString() %>" value="denuncia.edit.back" cssClass="btn btn-outline-primary mr-4" primary="true"></aui:button>
    </aui:button-row>
</div>