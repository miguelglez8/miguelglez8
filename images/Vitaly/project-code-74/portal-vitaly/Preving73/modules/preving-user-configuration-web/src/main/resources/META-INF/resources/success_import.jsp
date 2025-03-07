<%@ include file="init.jsp" %>
<%
    String csvCompanies = (String) request.getAttribute(UserConfigurationPortletKeys.USERS_CSV_USERS);
%>

<h2><liferay-ui:message key="user.configuration.view.title.import"/></h2>

<div>
    <p><liferay-ui:message key="user.configuration.view.message.import"/></p>
    <div><%=csvCompanies%></div>
</div>