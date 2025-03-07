<%@ include file="/init.jsp" %>

<liferay-ui:tabs names="holiday.configuration.view.title.tab,holiday.configuration.create.title.tab" refresh="false">

	<liferay-ui:section>
		<%@include file="holidays.jspf" %>
	</liferay-ui:section>

	<liferay-ui:section>
		<%@include file="create.jspf" %>
	</liferay-ui:section>

</liferay-ui:tabs>