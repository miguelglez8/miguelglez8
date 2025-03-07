<%@ include file="init.jsp" %>

<div class="content">
	<div class="row">
		<div class="col">
			<h2><liferay-ui:message key="mailing.test.title"/></h2>
			<p><liferay-ui:message key="mailing.test.message"/> <%=user.getEmailAddress()%></p>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<p>
			<h3><liferay-ui:message key="mailing.test.contact"/></h3>
			</p>
			<div>
				<liferay-portlet:actionURL name="contactToAdmin" var="contactToAdminURL" />
				<a href="<%=contactToAdminURL%>"><liferay-ui:message key="mailing.test.contact.admin"/></a>
			</div>

			<div>
				<liferay-portlet:actionURL name="contactToUser" var="contactToUserURL" />
				<a href="<%=contactToUserURL%>"><liferay-ui:message key="mailing.test.contact.user"/></a>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<p>
			<h3><liferay-ui:message key="mailing.test.denuncia"/></h3>
			</p>
			<div>
				<liferay-portlet:actionURL name="sendDenunciaToAdmin" var="denunciaToAdminURL" />
				<a href="<%=denunciaToAdminURL%>"><liferay-ui:message key="mailing.test.denuncia.admin"/></a>
			</div>

			<div>
				<liferay-portlet:actionURL name="sendDenunciaToUser" var="denunciaToUserURL" />
				<a href="<%=denunciaToUserURL%>"><liferay-ui:message key="mailing.test.denuncia.user"/></a>
			</div>

			<div>
				<liferay-portlet:actionURL name="sendFinalizarDenunciaToAdmin" var="finalizarDenunciaToAdminURL" />
				<a href="<%=finalizarDenunciaToAdminURL%>"><liferay-ui:message key="mailing.test.finalizar.denuncia.admin"/></a>
			</div>
			<div>
				<liferay-portlet:actionURL name="sendFinalizarDenunciaToUser" var="finalizarDenunciaToUserURL" />
				<a href="<%=finalizarDenunciaToUserURL%>"><liferay-ui:message key="mailing.test.finalizar.denuncia.user"/></a>
			</div>

			<div>
				<liferay-portlet:actionURL name="sendFinalizarAvisoDenunciaToUser" var="finalizarAvisoDenunciaToUserURL" />
				<a href="<%=finalizarAvisoDenunciaToUserURL%>"><liferay-ui:message key="mailing.test.finalizar.denuncia.aviso.user"/></a>
			</div>
		</div>
	</div>
</div>