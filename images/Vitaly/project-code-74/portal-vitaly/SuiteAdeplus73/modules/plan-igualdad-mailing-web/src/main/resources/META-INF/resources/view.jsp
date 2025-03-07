<%@ include file="/init.jsp" %>

<div class="content">
	<div class="row">
		<div class="col">
			<h2><liferay-ui:message key="mailing.test.title"/></h2>
			<p><liferay-ui:message key="mailing.test.message"/> <%=user.getEmailAddress()%></p>
		</div>
	</div>
	<div class="row">
		<div class="col">
            <div>
                <liferay-portlet:actionURL name="altaConsultor" var="altaConsultorURL" />
                <a href="<%=altaConsultorURL%>"><liferay-ui:message key="mailing.mail.subject.alta.consultor"/></a>
            </div>
		</div>
	</div>
</div>