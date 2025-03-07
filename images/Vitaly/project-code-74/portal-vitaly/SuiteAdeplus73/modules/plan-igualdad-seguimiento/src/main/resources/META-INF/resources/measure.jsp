<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.repository.model.FileEntry"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.plan.igualdad.liferay.seguimiento.dto.PlanIgualdadMedidaDTO"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="com.plan.igualdad.liferay.seguimiento.constants.PlanIgualdadSeguimientoPortletKeys"%>
<%@ include file="/init.jsp" %>

<%
ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

boolean clienteRole = (Boolean) request.getAttribute(PlanIgualdadSeguimientoPortletKeys.CLIENTE_ROLE);

boolean view = Boolean.FALSE;
PlanIgualdadMedidaDTO medida = null;

FileEntry documentoMedida = null;

if(request.getAttribute(PlanIgualdadSeguimientoPortletKeys.VIEW)!= null && 
	request.getAttribute(PlanIgualdadSeguimientoPortletKeys.MEDIDA_OBJECT) != null){
	view = (Boolean) request.getAttribute(PlanIgualdadSeguimientoPortletKeys.VIEW);
	medida = (PlanIgualdadMedidaDTO) request.getAttribute(PlanIgualdadSeguimientoPortletKeys.MEDIDA_OBJECT);
}

if(request.getAttribute(PlanIgualdadSeguimientoPortletKeys.DOCUMENT_MEDIDA)!=null){
	documentoMedida = (FileEntry)request.getAttribute(PlanIgualdadSeguimientoPortletKeys.DOCUMENT_MEDIDA);
}

String compId = (String) request.getParameter(PlanIgualdadSeguimientoPortletKeys.COMPID_PARAM);
String pageBack = (String) request.getParameter(PlanIgualdadSeguimientoPortletKeys.PAGE_BACK_PARAM);
%>

<liferay-ui:success key="measure-view-success" message="planigualdadseguimiento.message.view-measure-success" />

<liferay-portlet:actionURL name="/planigualdad/saveMeasure" var="saveMeasureURL"  />

<portlet:renderURL var="atrasURL">
    <portlet:param name="mvcPath" value="<%=pageBack %>"></portlet:param>
    <portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.QUERY_PARAM %>" value="<%=compId %>" />
</portlet:renderURL>
				
<aui:form action="<%= saveMeasureURL.toString() %>" name="saveMeasureForm" method="post" enctype="multipart/form-data">
	<aui:input name="<%= PlanIgualdadSeguimientoPortletKeys.COMPID_PARAM %>" value="<%= compId %>" type="hidden" />
	<div class="content">
		<div class="title-group">
			<a href="<%= atrasURL.toString() %>" style="text-decoration: none; float: left; padding: 8px;">
                <i class="icon-arrow-left"></i>
          	</a>
			<h2><liferay-ui:message key="planigualdadseguimiento.form.evaluation-measures"/></h2>
		</div>
		<div class="formulario">
			<div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
                <div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">
	                <aui:input type="hidden" name="<%=PlanIgualdadSeguimientoPortletKeys.ID_MEASURE %>" />
                	<liferay-ui:tabs names="planigualdadseguimiento.form.general-data,planigualdadseguimiento.form.cumplimentation" refresh="false">
						<div id="formMeasure">
							<liferay-ui:section>
						        <%@include file="generalData.jspf" %>
						    </liferay-ui:section>
						
						    <liferay-ui:section>
						        <%@include file="completion.jspf" %>
							</liferay-ui:section>
						</div>
					</liferay-ui:tabs>
				</div>
			</div>
		</div>
	</div>
	<div class="button-holder d-flex justify-content-center my-4">
		<c:choose>
			<c:when test="<%=!view %>">
				<aui:button-row>
		            <aui:button value="planigualdadseguimiento.form.cancel" cssClass="btn btn-outline-primary btn-sm mr-4 cancel-measure" primary="true"></aui:button>
					<aui:button data-btn="submit" cssClass="btn btn-primary btn-sm" value="planigualdadseguimiento.form.save" />
				</aui:button-row>
			</c:when>
			<c:otherwise>
				<aui:button-row>
		            <aui:button onClick="<%= atrasURL.toString() %>" value="planigualdadseguimiento.form.back" cssClass="btn btn-outline-primary btn-sm mr-4" primary="true"></aui:button>
				</aui:button-row>
	       	</c:otherwise>
		</c:choose>
	</div>
</aui:form>

<%@include file="cancel.jspf" %>
<%@include file="error.jspf" %>

<script>
	
	$(document).ready( function () {
		
		$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.REASON%>').change(function(){
			if($(this).val() == "7"){
				$('#otherReasonInput').removeClass("display-none");
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.REASON_OTHER%>').attr('required', true);
			}else{
				$('#otherReasonInput').addClass("display-none");
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.REASON_OTHER%>').removeAttr('required');
			}
		});
		
		$('[data-btn="submit"]').on('click', function() {
			if($('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.MATTER%>').val() == "" || $('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.MEASURE_NAME%>').val() == ""
		   		|| $('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.MEASURE_DESCRIPTION%>').val() == "" || $('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.OBJECTIVES_PURSUED%>').val() == ""
		   			|| $('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.ASSOCIATED_RESOURCES%>').val() == "" || $('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.MONITORING_INDICATORS%>').val() == ""){
		   		$('#errorMeasure').removeClass('hide');
			    $('#errorMeasure').addClass('show');
		   	}else{
		   		$('#<portlet:namespace/>saveMeasureForm').submit();
		   	}
		});
		
		$(document).on('click', '.cancel-measure', function(e){
		    e.preventDefault();
		    $('#cancelMeasure').removeClass('hide');
		    $('#cancelMeasure').addClass('show');
		});
		
		$(document).on('click', '.hide-modal', function(e) {
		    e.preventDefault();
		    $('#cancelMeasure').addClass('hide');
		    $('#errorMeasure').addClass('hide');
		});
		
		<%if(medida != null){ %>
			<%
			if(clienteRole){
			%>
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.MATTER%>').attr("disabled", true);
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.MEASURE_NAME%>').attr("disabled", true);
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.MEASURE_DESCRIPTION%>').attr("disabled", true);
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.OBJECTIVES_PURSUED%>').attr("disabled", true);
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.PRIORITY%>').attr("disabled", true);
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.ASSOCIATED_RESOURCES%>').attr("disabled", true);
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.MONITORING_INDICATORS%>').attr("disabled", true);
				$("input[name=<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.APPLY%>]").attr("disabled", true);
			<%}%>
			
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.ID_MEASURE%>').val('<%= medida.getMedidaId()!=null?medida.getMedidaId():""%>');

			<%if(medida.getDatosGenerales()!=null){%>
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.MATTER%>').val("<%= medida.getDatosGenerales().getMateriaId()%>");
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.MEASURE_NAME%>').val("<%= medida.getDatosGenerales().getNombreMedida()%>");
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.MEASURE_DESCRIPTION%>').val("<%= medida.getDatosGenerales().getDescripcionMedida()%>");
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.OBJECTIVES_PURSUED%>').val("<%= medida.getDatosGenerales().getObjetivos()%>");
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.PRIORITY%>').val("<%= medida.getDatosGenerales().getPrioridadId()%>");
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.ASSOCIATED_RESOURCES%>').val("<%= medida.getDatosGenerales().getRecursosAsociados()%>");
			$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.MONITORING_INDICATORS%>').val("<%= medida.getDatosGenerales().getIndicadoresSeguimiento()%>");
				<%if(medida.getDatosGenerales().getAplica()!=null){%>
				$("input[name=<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.APPLY%>][value='<%=medida.getDatosGenerales().getAplica()%>']").prop("checked",true);
				$("input[name=<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.APPLY%>][value='<%=medida.getDatosGenerales().getAplica()%>']").change();
				<%}%>
			<%}%>
			
			<%if(medida.getCumplimentacion()!=null){%>
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.RESPONSIBLE%>').val("<%= medida.getCumplimentacion().getResponsable()!=null?medida.getCumplimentacion().getResponsable():""%>");
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.PLANNED_DATE%>').val("<%= medida.getCumplimentacion().getFechaPrevista()!=null?medida.getCumplimentacion().getFechaPrevista():""%>");
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.IMPLANTATION_DATE%>').val("<%= medida.getCumplimentacion().getFechaImplantacion()!=null?medida.getCumplimentacion().getFechaImplantacion():""%>");
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.TRACING_DATE%>').val("<%= medida.getCumplimentacion().getFechaSeguimiento()!=null?medida.getCumplimentacion().getFechaSeguimiento():""%>");
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.EXECUTION%>').val("<%= medida.getCumplimentacion().getNivelEjecucionId()!=null?medida.getCumplimentacion().getNivelEjecucionId():""%>");
				<%if(medida.getCumplimentacion().getNivelEjecucionId()!=null){%>
				$("input[name=<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.EXECUTION_RADIO%>][value='<%=medida.getCumplimentacion().getNivelEjecucionId()%>']").prop("checked",true);
				<%}%>
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.REASON%>').val('<%= medida.getCumplimentacion().getMotivoNoIniciado()!=null?medida.getCumplimentacion().getMotivoNoIniciado():""%>');
				
				<%if(medida.getCumplimentacion().getMotivoNoIniciadoText()!=null && !medida.getCumplimentacion().getMotivoNoIniciadoText().isEmpty()){%>
					$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.REASON_OTHER%>').val('<%= medida.getCumplimentacion().getMotivoNoIniciadoText()!=null?medida.getCumplimentacion().getMotivoNoIniciadoText():""%>');
					$('#otherReasonInput').removeClass("display-none");
				<%}%>
				
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.RESOURCES_ASSIGNS%>').val("<%= medida.getCumplimentacion().getAdecuacionRecursos()!=null?medida.getCumplimentacion().getAdecuacionRecursos():""%>");
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.DIFFICULTIES_IMPLANTATION%>').val("<%= medida.getCumplimentacion().getDificultadesImplantacion()!=null?medida.getCumplimentacion().getDificultadesImplantacion(): ""%>");
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.SOLUTIONS_ADOPTED%>').val("<%= medida.getCumplimentacion().getSolucionesAdoptadas()!=null?medida.getCumplimentacion().getSolucionesAdoptadas():""%>");
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.REDUCTION_INEQUALITIES%>').val("<%= medida.getCumplimentacion().getReduccionDesigualdades()!=null?medida.getCumplimentacion().getReduccionDesigualdades():""%>");
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.PRODUCED_IMPROVEMENTS%>').val("<%= medida.getCumplimentacion().getMejorasProductivas()!=null?medida.getCumplimentacion().getMejorasProductivas():""%>");
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.PROPOSAL_FUTURE%>').val("<%= medida.getCumplimentacion().getPropuestaDeFuturo()!=null?medida.getCumplimentacion().getPropuestaDeFuturo():""%>");
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.DOCUMENTATION_TEXT%>').val("<%= medida.getCumplimentacion().getDocumentacionEjecucion()!=null?medida.getCumplimentacion().getDocumentacionEjecucion():""%>");
				<%
				if(Validator.isNotNull(medida.getCumplimentacion().getDocumentoId()) && medida.getCumplimentacion().getDocumentoId() != 0L){
				%>
					$('.fileUploadd').addClass('display-none');
					$('#filedd').removeClass('display-none');
					$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.FILE %>').val('<%= documentoMedida.getFileName() %>'); 				
				<%
				} 
				%>
				
			<%}%>
		<% }%>
		
		<%if(view){ %>
			$("#formMeasure input, #formMeasure textarea, #formMeasure select").each(function(){
	   		    $(this).attr('disabled', true);
	   		});
		<%}%>
		
		<%if(!view){ %>
			changeLevelExecution();
		<%}%>
		
	});
	
	function calculateTracingDate(){
		<%if(medida != null && medida.getPeriocidad()!=null && !medida.getPeriocidad().isEmpty()){ %>

		if($('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.IMPLANTATION_DATE %>')!=''){
			var implantationString = $('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.IMPLANTATION_DATE %>').val().split("/");
			var implantationDate = new Date(implantationString[2], implantationString[1], implantationString[0]);
			var medidaMonth = "<%=medida.getPeriocidad()%>".split(' ')[0];

			implantationDate.setMonth(implantationDate.getMonth() + parseInt(medidaMonth));

			if(implantationDate!='Invalid Date'){
				$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.TRACING_DATE %>').val(implantationDate.getDate()+'/'+implantationDate.getMonth()+'/'+implantationDate.getFullYear());
			}
		}
		<% }%>
		changeLevelExecution();
	}
	
	function changeLevelExecution(){
		var plannedDate = $('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.PLANNED_DATE %>').val();
		var implantationDate = $('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.IMPLANTATION_DATE %>').val();
		var tracingDate = $('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.TRACING_DATE %>').val();
	
		if((plannedDate == '' || plannedDate == ' ') && (implantationDate == '' || implantationDate == ' ')
				&& (tracingDate == '' || tracingDate == ' ')){
			$("input[name=<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.EXECUTION_RADIO%>][value='1']").prop("checked",true);
			$("input[name=<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.EXECUTION%>]").val('1');
		}else if ((plannedDate != '' || plannedDate != ' ') && (implantationDate == '' || implantationDate == ' ')
				&& (tracingDate == '' || tracingDate == ' ')){
			$("input[name=<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.EXECUTION_RADIO%>][value='2']").prop("checked",true);
			$("input[name=<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.EXECUTION%>]").val('2');
		}else if((implantationDate != '' || implantationDate != ' ')){
			$("input[name=<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.EXECUTION_RADIO%>][value='3']").prop("checked",true);
			$("input[name=<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.EXECUTION%>]").val('3');
		}
	}
	
	$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.PLANNED_DATE %>, #<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.TRACING_DATE %> ').change(function(){
		changeLevelExecution();
	});
	
	$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.PLANNED_DATE %>, #<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.TRACING_DATE %> ').focus(function(){
		changeLevelExecution();
	});
	
	$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.IMPLANTATION_DATE %>').change(function(){
		calculateTracingDate();
	});
	
	$('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.IMPLANTATION_DATE %>').focus(function(){
		calculateTracingDate();
	});
	
</script>