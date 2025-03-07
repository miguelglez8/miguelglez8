<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.plan.igualdad.liferay.portlet.seguimiento.cuestionario.dto.CuestionarioDTO"%>
<%@page import="com.plan.igualdad.liferay.portlet.seguimiento.cuestionario.constants.PlanIgualdadSeguimientoCuestionarioPortletKeys"%>
<%@ include file="/init.jsp" %>

<%
CuestionarioDTO cuestionarioDTO = null;
boolean disabled =false;
if(request.getAttribute(PlanIgualdadSeguimientoCuestionarioPortletKeys.CUESTIONARIO_OBJECT) != null){
	cuestionarioDTO = (CuestionarioDTO) request.getAttribute(PlanIgualdadSeguimientoCuestionarioPortletKeys.CUESTIONARIO_OBJECT);
	disabled = true;
}
String compId = (String) request.getParameter(PlanIgualdadSeguimientoCuestionarioPortletKeys.COMPIDEVALUATION_PARAM);

%>
<liferay-ui:success key="questionary-view-success" message="cuestionario.message.view-questionary-success" />

<liferay-portlet:actionURL name="/planigualdad/saveQuestionary" var="saveQuestionaryURL"  />

<aui:form action="<%= saveQuestionaryURL.toString() %>" name="saveQuestionaryForm" method="post" enctype="multipart/form-data">
	<aui:input name="<%= PlanIgualdadSeguimientoCuestionarioPortletKeys.COMPID_PARAM %>" value="<%= compId %>" type="hidden" />
	<div class="content">
		<div class="titulo-seccion text-center">
			<h2><liferay-ui:message key="cuestionario.title"/></h2>
		</div>
		<div class="formulario">
			<div class="form-content mb-4">
				<aui:input type="hidden" value="<%=cuestionarioDTO!=null && cuestionarioDTO.getCuestionarioId()!=null ? cuestionarioDTO.getCuestionarioId() : StringPool.BLANK%>" name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.CUESTIONARIO_ID %>" />
				
				<div class="row mb-4">
					<div class="form-group col-md-10 col-lg-10 col-12 required">
						<aui:input type="textarea" label="cuestionario.form.question1" name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_1 %>" >
				           	<aui:validator name="required" />
				         	<aui:validator name="maxLength">3000</aui:validator>
				     	</aui:input>
					</div>
				</div>
				
				<div class="row mb-4">
					<div class="form-group col-md-10 col-lg-10 col-12 required">
						<aui:input type="textarea" label="cuestionario.form.question2" name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_2 %>" >
				           	<aui:validator name="required" />
				         	<aui:validator name="maxLength">3000</aui:validator>
				     	</aui:input>
					</div>
				</div>
				
				<div class="row mb-4">
					<div class="form-group col-md-10 col-lg-10 col-12 required">
						<aui:input type="textarea" label="cuestionario.form.question3" name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_3 %>" >
				           	<aui:validator name="required" />
				         	<aui:validator name="maxLength">3000</aui:validator>
				     	</aui:input>
					</div>
				</div>
				
				<div class="row mb-4">
					<div class="form-group col-md-10 col-lg-10 col-12 required">
						<aui:input type="textarea" label="cuestionario.form.question4" name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_4 %>" >
				           	<aui:validator name="required" />
				         	<aui:validator name="maxLength">3000</aui:validator>
				     	</aui:input>
					</div>
				</div>
				
				<div class="row mb-4">
					<div class="form-group col-md-10 col-lg-10 col-12 required">
						<aui:input type="textarea" label="cuestionario.form.question5" name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_5 %>" >
				           	<aui:validator name="required" />
				         	<aui:validator name="maxLength">3000</aui:validator>
				     	</aui:input>
					</div>
				</div>
				
				<div class="row mb-4">
					<div class="form-group col-md-10 col-lg-10 col-12 required">
						<aui:input type="textarea" label="cuestionario.form.question6" name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_6 %>" >
				           	<aui:validator name="required" />
				         	<aui:validator name="maxLength">3000</aui:validator>
				     	</aui:input>
					</div>
				</div>
				
				<div class="row mb-4">
					<div class="form-group col-md-10 col-lg-10 col-12">
						<aui:input type="textarea" label="cuestionario.observaciones" value="<%=cuestionarioDTO!=null && cuestionarioDTO.getObservaciones()!=null ? cuestionarioDTO.getObservaciones() : StringPool.BLANK%>"  name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.OBSERVATIONS %>" >
				         	<aui:validator name="maxLength">3000</aui:validator>
				     	</aui:input>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="button-holder d-flex justify-content-center my-4">
		<aui:button-row>
            <aui:button value="cuestionario.form.cancel" cssClass="btn btn-outline-primary btn-sm mr-4 cancel-questionary" primary="true"></aui:button>
			<aui:button type="submit" cssClass="btn btn-primary btn-sm" value="cuestionario.form.save" />
		</aui:button-row>
	</div>
</aui:form>

<%@include file="cancel.jspf" %>

<script>
	$(document).ready( function () {
		$(document).on('click', '.cancel-questionary', function(e){
		    e.preventDefault();
		    $('#cancelQuestionary').removeClass('hide');
		    $('#cancelQuestionary').addClass('show');
		});
		
		$(document).on('click', '.hide-modal', function(e) {
		    e.preventDefault();
		    $('#cancelQuestionary').addClass('hide');
		});
		
		<% if(cuestionarioDTO!=null && cuestionarioDTO.getRespuestas() != null){ %>
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_1 %>').val('<%=cuestionarioDTO.getRespuestas().getPregunta1()%>');
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_1 %>').attr('disabled', true);
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_2 %>').val('<%=cuestionarioDTO.getRespuestas().getPregunta2()%>');
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_2 %>').attr('disabled', true);
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_3 %>').val('<%=cuestionarioDTO.getRespuestas().getPregunta3()%>');
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_3 %>').attr('disabled', true);
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_4 %>').val('<%=cuestionarioDTO.getRespuestas().getPregunta4()%>');
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_4 %>').attr('disabled', true);
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_5 %>').val('<%=cuestionarioDTO.getRespuestas().getPregunta5()%>');
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_5 %>').attr('disabled', true);
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_6 %>').val('<%=cuestionarioDTO.getRespuestas().getPregunta6()%>');
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoCuestionarioPortletKeys.TEXTAREA_6 %>').attr('disabled', true);
		<% } %>
	});
</script>