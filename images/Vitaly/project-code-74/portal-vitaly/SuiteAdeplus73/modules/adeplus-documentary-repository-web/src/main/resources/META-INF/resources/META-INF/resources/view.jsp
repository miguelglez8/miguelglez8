<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="planigualdadgenerarinforme.caption"/></b>
</p>

<portlet:resourceURL var="generateReportURL">
	<portlet:param name="action" value="generateReport" />
</portlet:resourceURL>

<a href="<%= generateReportURL.toString() %>">PRUEBA EXPORTAR DOCX</a>