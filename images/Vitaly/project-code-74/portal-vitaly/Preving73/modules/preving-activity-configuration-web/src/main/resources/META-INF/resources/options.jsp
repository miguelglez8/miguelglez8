<%@ include file="/init.jsp" %>

<%
    Activity activityPreving = (Activity) request.getAttribute("view.jsp");
    ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
    if (row != null) {
        activityPreving = (Activity) row.getObject();
    }
%>

<liferay-ui:icon-menu>

    <liferay-portlet:renderURL varImpl="editURL">
        <portlet:param name="activityId" value="<%=String.valueOf(activityPreving.getActivityId())%>"/>
        <portlet:param name="mvcPath" value="/edit.jsp"/>
    </liferay-portlet:renderURL>
    <liferay-ui:icon message="edit" url="<%=editURL.toString().replace("+", "")%>" icon="edit"/>
    <%
        if(activityPreving.getActive()==true) {
    %>
    <liferay-portlet:actionURL name="delete" var="deleteURL">
        <portlet:param name="activityId" value="<%=String.valueOf(activityPreving.getActivityId())%>"/>
    </liferay-portlet:actionURL>
    <liferay-ui:icon-delete confirmation="activity.configuration.view.alert.confirmation" showIcon="true" url="<%=deleteURL.toString()%>" message="delete" icon="trash" />
    <%
        }else{
    %>
    <liferay-ui:icon message="" />
    <%
        }
    %>
</liferay-ui:icon-menu>
