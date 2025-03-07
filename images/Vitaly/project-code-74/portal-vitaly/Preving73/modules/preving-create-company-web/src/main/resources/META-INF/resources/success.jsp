<%@ include file="/init.jsp" %>

<%
    String companyName = ParamUtil.get(request,CreateCompanyPortletKeys.COMPANY_NAME, "");
    String email = ParamUtil.get(request,CreateCompanyPortletKeys.COMPANY_ADMIN_EMAIL, "");

    String info[] = {companyName, email};
%>

<h2><liferay-ui:message key="createcompany.success.title"/></h2>

<div>
    <p><liferay-ui:message key="createcompany.success.message" arguments="<%=info%>"/></p>
</div>