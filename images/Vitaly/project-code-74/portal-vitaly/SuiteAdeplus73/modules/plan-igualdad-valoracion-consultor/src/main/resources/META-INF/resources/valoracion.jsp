<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.plan.igualdad.liferay.portlet.valoracion.consultor.constants.PlanIgualdadValoracionConsultorPortletKeys"%>
<%@page import="com.plan.igualdad.liferay.portlet.valoracion.consultor.dto.ValoracionDTO"%>
<%@ include file="/init.jsp" %>

<%
ValoracionDTO valoracionDto = null;
boolean disabled =false;
if(request.getAttribute(PlanIgualdadValoracionConsultorPortletKeys.VALORACION_OBJECT) != null){
	valoracionDto = (ValoracionDTO) request.getAttribute(PlanIgualdadValoracionConsultorPortletKeys.VALORACION_OBJECT);
	disabled = true;
}

String compId = (String) request.getParameter(PlanIgualdadValoracionConsultorPortletKeys.COMPID_PARAM);

%>

<liferay-ui:success key="assessment-view-success" message="valoracion.message.view-assessment-success" />

<liferay-portlet:actionURL name="/planigualdad/saveAssessment" var="saveAssessmentURL"  />

<aui:form action="<%= saveAssessmentURL.toString() %>" name="saveAssessmentForm" method="post" enctype="multipart/form-data">
	<aui:input name="<%= PlanIgualdadValoracionConsultorPortletKeys.COMPID_PARAM %>" value="<%= compId %>" type="hidden" />
	
	<div class="content">
		<div class="titulo-seccion text-center">
			<h2><liferay-ui:message key="valoracion.title"/></h2>
		</div>
		<div class="formulario">
			<div class="form-content mb-4">
				<aui:input type="hidden" value="<%=valoracionDto!=null && valoracionDto.getValoracionId()!=null ? valoracionDto.getValoracionId(): StringPool.BLANK%>" name="<%=PlanIgualdadValoracionConsultorPortletKeys.VALORACION_ID %>" />
				
				<div class="row mb-4">
					<div class="form-group col-md-10 col-lg-10 col-12 required">
						<aui:input disabled="<%=disabled %>" type="textarea" label="valoracion.nivel.ejecucion" value="<%=valoracionDto!=null && valoracionDto.getRespuestas()!=null ? valoracionDto.getRespuestas().getRespuesta1() : StringPool.BLANK%>" name="<%=PlanIgualdadValoracionConsultorPortletKeys.ANSWER_1 %>" >
				           	<aui:validator name="required" />
				         	<aui:validator name="maxLength">3000</aui:validator>
				     	</aui:input>
					</div>
				</div>
				
				<div class="row mb-4">
					<div class="form-group col-md-10 col-lg-10 col-12 required">
						<aui:input disabled="<%=disabled %>" type="textarea" label="valoracion.planificacion" value="<%=valoracionDto!=null && valoracionDto.getRespuestas()!=null ? valoracionDto.getRespuestas().getRespuesta2() : StringPool.BLANK%>" name="<%=PlanIgualdadValoracionConsultorPortletKeys.ANSWER_2 %>" >
				           	<aui:validator name="required" />
				         	<aui:validator name="maxLength">3000</aui:validator>
				     	</aui:input>
					</div>
				</div>
				
				<div class="row mb-4">
					<div class="form-group col-md-10 col-lg-10 col-12 required">
						<aui:input disabled="<%=disabled %>" type="textarea" label="valoracion.objetivos" value="<%=valoracionDto!=null && valoracionDto.getRespuestas()!=null ? valoracionDto.getRespuestas().getRespuesta3() : StringPool.BLANK%>" name="<%=PlanIgualdadValoracionConsultorPortletKeys.ANSWER_3 %>" >
				           	<aui:validator name="required" />
				         	<aui:validator name="maxLength">3000</aui:validator>
				     	</aui:input>
					</div>
				</div>
				
				<div class="row mb-4">
					<div class="form-group col-md-10 col-lg-10 col-12">
						<aui:input type="textarea" label="valoracion.observaciones" value="<%=valoracionDto!=null && valoracionDto.getObservaciones()!=null ? valoracionDto.getObservaciones() : StringPool.BLANK%>" name="<%=PlanIgualdadValoracionConsultorPortletKeys.OBSERVATIONS %>" >
				         	<aui:validator name="maxLength">3000</aui:validator>
				     	</aui:input>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="button-holder d-flex justify-content-center my-4">
		<aui:button-row>
            <aui:button value="valoracion.cancelar" cssClass="btn btn-outline-primary btn-sm mr-4 cancel-assesstment" primary="true"></aui:button>
			<aui:button type="submit" cssClass="btn btn-primary btn-sm" value="valoracion.guardar" />
		</aui:button-row>
	</div>
</aui:form>

<%@include file="cancel.jspf" %>

<script>
	$(document).ready( function () {
		$(document).on('click', '.cancel-assesstment', function(e){
		    e.preventDefault();
		    $('#cancelAssesstment').removeClass('hide');
		    $('#cancelAssesstment').addClass('show');
		});
		
		$(document).on('click', '.hide-modal', function(e) {
		    e.preventDefault();
		    $('#cancelAssesstment').addClass('hide');
		});
	});
</script>