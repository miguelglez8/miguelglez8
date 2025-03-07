<%@ include file="/init.jsp" %>

<liferay-ui:tabs names="activity.configuration.view.title.tab,activity.configuration.create.title.tab" refresh="false">

    <liferay-ui:section>
        <%@include file="activities.jspf" %>
    </liferay-ui:section>

    <liferay-ui:section>
        <%@include file="create.jspf" %>
    </liferay-ui:section>

</liferay-ui:tabs>
