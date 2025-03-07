<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.UserData" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ include file="init.jsp" %>

<%
	UserData userData = UserDataLocalServiceUtil.getUserDataByGroupIdAndUserId(themeDisplay.getScopeGroupId(), themeDisplay.getUserId());

	Calendar notificationEndDate = Calendar.getInstance();
	boolean disabledNotificationEndDate = true;
	if (Validator.isNotNull(userData) && Validator.isNotNull(userData.getNotificationEndDate())) {
		disabledNotificationEndDate = false;
		notificationEndDate.setTime(userData.getNotificationEndDate());
	}else{
		notificationEndDate.setTime(new Date());
	}
%>

<liferay-portlet:actionURL name="save" var="saveURL"  />
<aui:form name="configuration_form" action="<%= saveURL.toString() %>" method="post">
	<div class="row">
		<div class="col-sm-4">
			<aui:fieldset label="notifications.view.notification.enddate" helpMessage="notifications.view.notification.enddate.help">
				<c:if test="<%=disabledNotificationEndDate%>">
					<liferay-ui:input-date name="notificationEndDate" nullable="true" disabled="<%=disabledNotificationEndDate%>"/>
				</c:if>
				<c:if test="<%=!disabledNotificationEndDate%>">
					<liferay-ui:input-date name="notificationEndDate" nullable="true"
										   yearValue="<%=notificationEndDate.get(Calendar.YEAR)%>"
										   monthValue="<%=notificationEndDate.get(Calendar.MONTH)%>"
										   dayValue="<%=notificationEndDate.get(Calendar.DAY_OF_MONTH)%>" />
				</c:if>
			</aui:fieldset>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<aui:button-row>
				<aui:button type="submit" />
			</aui:button-row>
		</div>
	</div>
</aui:form>