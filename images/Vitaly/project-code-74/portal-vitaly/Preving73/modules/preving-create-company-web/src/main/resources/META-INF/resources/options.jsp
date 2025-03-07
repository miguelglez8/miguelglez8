<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.preving.liferay.portlet.create.company.web.bean.CompanyBean" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.Sign" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.service.SignLocalServiceUtil" %>
<%@ include file="/init.jsp" %>

<%
    boolean deleteCompany = false;
    CompanyBean companyBean = (CompanyBean) request.getAttribute("view.jsp");
    ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW );
    if (row != null) {
        companyBean = (CompanyBean)row.getObject();
    }

    List<Sign> signsByCompanyIdAndGroupId = SignLocalServiceUtil.getSignsByCompanyIdAndGroupId(companyBean.getCompanyId(), companyBean.getGroupId());
    if(signsByCompanyIdAndGroupId.isEmpty()){
        deleteCompany = true;
    }

%>

<liferay-ui:icon-menu>

    <liferay-portlet:renderURL varImpl="editURL">
        <portlet:param name="companyConfId" value="<%=String.valueOf(companyBean.getCompanyConfId())%>" />
        <portlet:param name="mvcPath" value="/edit.jsp" />
    </liferay-portlet:renderURL>
    <liferay-ui:icon message="edit" url="<%=editURL.toString().replace("+", "")%>" />

    <c:if test="<%=deleteCompany%>">
        <liferay-portlet:actionURL name="delete" var="deleteURL">
            <portlet:param name="companyConfId" value="<%=String.valueOf(companyBean.getCompanyConfId())%>" />
        </liferay-portlet:actionURL>
        <liferay-ui:icon-delete url="<%=deleteURL.toString()%>" />
    </c:if>

</liferay-ui:icon-menu>