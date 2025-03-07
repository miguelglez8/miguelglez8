<%@ include file="init.jsp" %>

<p>
	<h3><liferay-ui:message key="previngmailing.test.title"/></h3>
	<p><liferay-ui:message key="previngmailing.test.message"/><%=user.getEmailAddress()%></p>
</p>

<div class="row">
	<div class="col">
		<p>
			<h3><liferay-ui:message key="previngmailing.test.companies"/></h3>
		</p>
		<div>
			<liferay-portlet:actionURL name="createCompanyToCreatorTest" var="createCompanyToCreatorTestURL" />
			<a href="<%=createCompanyToCreatorTestURL%>">Test crear empresa al creador (Superadministrador)</a>
		</div>

		<div>
			<liferay-portlet:actionURL name="createCompanyToAdminTest" var="createCompanyToAdminTestURL" />
			<a href="<%=createCompanyToAdminTestURL%>">Test crear empresa al administrador de empresa</a>
		</div>

		<div>
			<liferay-portlet:actionURL name="deleteCompanyToAdminTest" var="deleteCompanyToAdminTestURL" />
			<a href="<%=deleteCompanyToAdminTestURL%>">Test borrar empresa al administrador de empresa</a>
		</div>
	</div>

	<div class="col">
		<p>
			<h3><liferay-ui:message key="previngmailing.test.users"/></h3>
		</p>
		<div>
			<liferay-portlet:actionURL name="createUserToUserTest" var="createUserToUserTestURL" />
			<a href="<%=createUserToUserTestURL%>">Test crear usuario</a>
		</div>
		<div>
			<liferay-portlet:actionURL name="deleteUserToUserTest" var="deleteUserToUserTestURL" />
			<a href="<%=deleteUserToUserTestURL%>">Test borrar usuario</a>
		</div>
		<div>
			<liferay-portlet:actionURL name="remindPasswordToUserTest" var="remindPasswordToUserTestURL" />
			<a href="<%=remindPasswordToUserTestURL%>">Test recordar contrase&ntilde;a</a>
		</div>
		<div>
			<liferay-portlet:actionURL name="activeUserToUserTest" var="activeUserToUserTestURL" />
			<a href="<%=activeUserToUserTestURL%>">Test activar usuario</a>
		</div>
	</div>

	<div class="col">
		<p>
			<h3><liferay-ui:message key="previngmailing.test.reports"/></h3>
		</p>
		<div>
			<liferay-portlet:actionURL name="signUnclosedToUserTest" var="signUnclosedToUserTestURL" />
			<a href="<%=signUnclosedToUserTestURL%>">Test registro sin cerrar</a>
		</div>
		<div>
			<liferay-portlet:actionURL name="signUnclosedToUserWeeklyTest" var="signUnclosedToUserWeeklyTestURL" />
			<a href="<%=signUnclosedToUserWeeklyTestURL%>">Test registro sin cerrar semanal</a>
		</div>
		<div>
			<liferay-portlet:actionURL name="withoutSignsToUserTest" var="withoutSignsToUserTestURL" />
			<a href="<%=withoutSignsToUserTestURL%>">Test usuario sin registros</a>
		</div>
		<div>
			<liferay-portlet:actionURL name="signReportToUserTest" var="signReportToUserTestURL" />
			<a href="<%=signReportToUserTestURL%>">Test informe de registros diario</a>
		</div>
		<div>
			<liferay-portlet:actionURL name="signReportToUserTestWeekly" var="signReportToUserTestWeeklyURL" />
			<a href="<%=signReportToUserTestWeeklyURL%>">Test informe de registros semanal</a>
		</div>
		<div>
			<liferay-portlet:actionURL name="signReportToUserTestMonthly" var="signReportToUserTestMonthlyURL" />
			<a href="<%=signReportToUserTestMonthlyURL%>">Test informe de registros mensual</a>
		</div>
	</div>
	<div class="col">
		<p>
		<h3><liferay-ui:message key="previngmailing.test.contact"/></h3>
		</p>
		<div>
			<liferay-portlet:actionURL name="contactToAdmin" var="contactToAdminURL" />
			<a href="<%=contactToAdminURL%>">Test contacto al administrador</a>
		</div>
		<div>
			<liferay-portlet:actionURL name="contactToUser" var="contactToUserURL" />
			<a href="<%=contactToUserURL%>">Test contacto al usuario</a>
		</div>
	</div>
</div>