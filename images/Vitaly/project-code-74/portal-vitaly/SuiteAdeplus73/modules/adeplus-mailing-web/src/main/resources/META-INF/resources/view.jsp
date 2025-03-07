<%@ include file="/init.jsp" %>

<p>
	<h2><liferay-ui:message key="mailing.test.title"/></h2>
	<p><liferay-ui:message key="mailing.test.message"/> <%=user.getEmailAddress()%></p>
</p>

<div class="row">
	<div class="col">
		<p>
		<h3><liferay-ui:message key="mailing.test.users"/></h3>
		</p>
		<div>
			<liferay-portlet:actionURL name="createUser" var="createUserURL" />
			<a href="<%=createUserURL%>"><liferay-ui:message key="mailing.test.create.user"/></a>
		</div>

		<div>
			<liferay-portlet:actionURL name="deleteUser" var="deleteUserURL" />
			<a href="<%=deleteUserURL%>"><liferay-ui:message key="mailing.test.delete.user"/></a>
		</div>

		<div>
			<liferay-portlet:actionURL name="remindPassword" var="remindPasswordURL" />
			<a href="<%=remindPasswordURL%>"><liferay-ui:message key="mailing.test.remind.password"/></a>
		</div>
	</div>

</div>