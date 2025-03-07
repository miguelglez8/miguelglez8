<%@ page import="com.preving.liferay.portlet.create.company.web.bean.CSVData" %>
<%@ include file="/init.jsp" %>

<%
    String csvCompanies = (String) request.getAttribute(CreateCompanyPortletKeys.COMPANY_CSV_COMPANIES);
%>

<h2><liferay-ui:message key="createcompany.error.title.import"/></h2>

<div>
    <p><liferay-ui:message key="createcompany.error.message.import"/></p>
    <div><%=csvCompanies%></div>
</div>