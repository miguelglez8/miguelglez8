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
			<p>
			<h3><liferay-ui:message key="mailing.test.legislacion"/></h3>
			</p>
			<div>
				<liferay-portlet:actionURL name="nuevaLegislacion" var="nuevaLegislacionURL" />
				<a href="<%=nuevaLegislacionURL%>"><liferay-ui:message key="mailing.mail.subject.legislacion.new"/></a>
			</div>

			<div>
				<liferay-portlet:actionURL name="informeMensualLegislaciones" var="informeMensualLegislacionesURL" />
				<a href="<%=informeMensualLegislacionesURL%>"><liferay-ui:message key="mailing.mail.subject.legislacion.monthly.report"/></a>
			</div>
			<div>
                <liferay-portlet:actionURL name="importarLegislaciones" var="importarLegislacionesURL" />
                <a href="<%=importarLegislacionesURL%>"><liferay-ui:message key="mailing.mail.subject.importador.legislaciones"/></a>
            </div>
            <div>
                <liferay-portlet:actionURL name="importarRequisitos" var="importarRequisitosURL" />
                <a href="<%=importarRequisitosURL%>"><liferay-ui:message key="mailing.mail.subject.importador.requisitos"/></a>
            </div>
            <div>
                <liferay-portlet:actionURL name="newConsultor" var="newConsultorURL" />
                <a href="<%=newConsultorURL%>"><liferay-ui:message key="mailing.mail.subject.new.consultor"/></a>
            </div>
            <div>
                <liferay-portlet:actionURL name="altaConsultor" var="altaConsultorURL" />
                <a href="<%=altaConsultorURL%>"><liferay-ui:message key="mailing.mail.subject.alta.consultor"/></a>
            </div>
            <div>
                <liferay-portlet:actionURL name="nuevaEvaluacionCumplimientoLegislacion" var="nuevaEvaluacionCumplimientoLegislacionURL" />
                <a href="<%=nuevaEvaluacionCumplimientoLegislacionURL%>"><liferay-ui:message key="mailing.mail.subject.new.eval.legislacion"/></a>
            </div>
            <div>
                <liferay-portlet:actionURL name="nuevaEvaluacionCumplimientoRequisito" var="nuevaEvaluacionCumplimientoRequisitoURL" />
                <a href="<%=nuevaEvaluacionCumplimientoRequisitoURL%>"><liferay-ui:message key="mailing.mail.subject.new.eval.requisito"/></a>
            </div>
            <div>
                <liferay-portlet:actionURL name="proximaFechaEvaluacionRequisito" var="proximaFechaEvaluacionRequisitoURL" />
                <a href="<%=proximaFechaEvaluacionRequisitoURL%>"><liferay-ui:message key="mailing.mail.subject.prox.eval.requisito"/></a>
            </div>
            <div>
                <liferay-portlet:actionURL name="legislacionUrgente" var="legislacionUrgenteURL" />
                <a href="<%=legislacionUrgenteURL%>"><liferay-ui:message key="mailing.mail.subject.legislacion.urgente"/></a>
            </div>
            <div>
                <liferay-portlet:actionURL name="informeMensualEvaluacion" var="informeMensualEvaluacionURL" />
                <a href="<%=informeMensualEvaluacionURL%>"><liferay-ui:message key="mailing.mail.subject.informe.eval.requisito"/></a>
            </div>
            <div>
                <liferay-portlet:actionURL name="periodoFechaEvaluacion" var="periodoFechaEvaluacionURL" />
                <a href="<%=periodoFechaEvaluacionURL%>"><liferay-ui:message key="mailing.mail.subject.periodo.requisito"/></a>
            </div>
            <div>
                <liferay-portlet:actionURL name="cambioContrato" var="cambioContratoURL" />
                <a href="<%=cambioContratoURL%>"><liferay-ui:message key="mailing.mail.subject.cambios.contrato"/></a>
            </div>
		</div>
	</div>
</div>