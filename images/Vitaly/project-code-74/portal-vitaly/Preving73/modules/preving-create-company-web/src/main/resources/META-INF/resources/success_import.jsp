<%@ include file="/init.jsp" %>

<%
    String csvCompanies = (String) request.getAttribute(CreateCompanyPortletKeys.COMPANY_CSV_COMPANIES);
%>

<h2><liferay-ui:message key="createcompany.success.title.import"/></h2>

<div>
    <p><liferay-ui:message key="createcompany.success.message.import"/></p>
    <div><%=csvCompanies%></div>
</div>
