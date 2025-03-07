<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@page import="com.plan.igualdad.liferay.generar.informe.constants.PlanIgualdadGenerarInformePortletKeys"%>
<%@ include file="/init.jsp" %>

<script src="<%=request.getContextPath()%>/js/main.js"></script>

<portlet:resourceURL id="<%=PlanIgualdadGenerarInformePortletKeys.SAVE_COMMENT %>" var="saveCommentURL" >
	<portlet:param name="compId" value="${compId}" />
</portlet:resourceURL>

<portlet:resourceURL id="<%=PlanIgualdadGenerarInformePortletKeys.SAVE_EXTENSION %>" var="saveExtensionURL" >
	<portlet:param name="compId" value="${compId}" />
</portlet:resourceURL>

<%
PortletPreferences preferences = renderRequest.getPreferences();
String portletResource = ParamUtil.getString(request, "portletResource");
if (Validator.isNotNull(portletResource)) {
    preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}
String planIgualdad = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.PLAN_IGUALDAD, "");
String diagnostico = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.INFORME_DIAGNOSTICO_PLAN_IGUALDAD, "");
String auditoria = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.INFORME_AUDITORIA_PLAN_IGUALDAD, "");
String seguimiento = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.INFORME_SEGUIMIENTO_PLAN_IGUALDAD, "");
String evaluacion = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.INFORME_EVALUACION_PLAN_IGUALDAD, "");
String cuestionario = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.DATOS_CUALITATIVOS_ORGANIZACION, "");
%>

<main class="content">
	<div class="formulario">
		<div class="title-group">
			<div class="row">
                <div class="col-md-6 col-12">
                    <h2><liferay-ui:message key="informe.titulo"/></h2>
                </div>
            </div>
		</div>		
		<div class="accordion" id="cuestionario">
			
			<liferay-util:include page="/generarInforme.jsp" servletContext="<%=application %>">
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_ID %>" value="1"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_TITLE %>" value="informe.plan-igualdad"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_SHOW_MODAL %>" value="${showModalParam}"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_DOCUMENT %>" value="<%=PlanIgualdadGenerarInformePortletKeys.PLAN_IGUALDAD %>"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.DISABLED %>" value="<%= planIgualdad.equals("1") ? PlanIgualdadGenerarInformePortletKeys.DISABLED : "" %>"/>
			</liferay-util:include>
			
			<liferay-util:include page="/generarInforme.jsp" servletContext="<%=application %>">
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_ID %>" value="2"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_TITLE %>" value="informe.informe-diagnostico"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_DOCUMENT %>" value="<%=PlanIgualdadGenerarInformePortletKeys.INFORME_DIAGNOSTICO_PLAN_IGUALDAD %>"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_SHOW_MODAL %>" value="${showModalParam}"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.DISABLED %>" value="<%= diagnostico.equals("1") ? PlanIgualdadGenerarInformePortletKeys.DISABLED : "" %>"/>
			</liferay-util:include>
			
			<liferay-util:include page="/generarInforme.jsp" servletContext="<%=application %>">
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_ID %>" value="3"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_SHOW_MODAL %>" value="${showModalParam}"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_TITLE %>" value="informe.auditoria-retributiva"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_DOCUMENT %>" value="<%=PlanIgualdadGenerarInformePortletKeys.INFORME_AUDITORIA_PLAN_IGUALDAD %>"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.DISABLED %>" value="<%= auditoria.equals("1") ? PlanIgualdadGenerarInformePortletKeys.DISABLED : "" %>"/>
			</liferay-util:include>
			
			<liferay-util:include page="/generarInforme.jsp" servletContext="<%=application %>">
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_ID %>" value="4"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_SHOW_MODAL %>" value="${showModalParam}"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_TITLE %>" value="informe.informe-seguimiento"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_DOCUMENT %>" value="<%=PlanIgualdadGenerarInformePortletKeys.INFORME_SEGUIMIENTO_PLAN_IGUALDAD %>"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.DISABLED %>" value="<%= seguimiento.equals("1") ? PlanIgualdadGenerarInformePortletKeys.DISABLED : "" %>"/>
			</liferay-util:include>
			
			<liferay-util:include page="/generarInforme.jsp" servletContext="<%=application %>">
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_ID %>" value="5"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_SHOW_MODAL %>" value="${showModalParam}"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_TITLE %>" value="informe.informe-evaluacion"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_DOCUMENT %>" value="<%=PlanIgualdadGenerarInformePortletKeys.INFORME_EVALUACION_PLAN_IGUALDAD %>"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.DISABLED %>" value="<%= evaluacion.equals("1") ? PlanIgualdadGenerarInformePortletKeys.DISABLED : "" %>"/>
			</liferay-util:include>
			
			<liferay-util:include page="/generarInforme.jsp" servletContext="<%=application %>">
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_ID %>" value="6"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_SHOW_MODAL %>" value="${showModalParam}"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_TITLE %>" value="informe.cuestionario-empresa"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.INCLUDE_DOCUMENT %>" value="<%=PlanIgualdadGenerarInformePortletKeys.DATOS_CUALITATIVOS_ORGANIZACION %>"/>
				<liferay-util:param name="<%=PlanIgualdadGenerarInformePortletKeys.DISABLED %>" value="<%= cuestionario.equals("1") ? PlanIgualdadGenerarInformePortletKeys.DISABLED : "" %>"/>
			</liferay-util:include>

		</div>
	</div>
</main>

<script>

	function showModalGenerar(modal, includeId){
		$('#modalGenerarInforme'+includeId).removeClass('hide');
		$('#modalGenerarInforme'+includeId).addClass('show')
		if(modal=="0"){
			$('#generarInformeDiv'+ includeId).css('display', 'block');
			$('#chooseFormatDiv' +includeId).css('display', 'none');
		}else{
			$('#generarInformeDiv'+ includeId).css('display', 'none');
			$('#chooseFormatDiv'+ includeId).css('display', 'block');
		}
	}
	
	$(document).ready( function () {
		
		$('.dificultadesDetectados').change(function(){
			var comentarioDefecto = $(this).val();
			var comentarioName = $(this).attr('data-idcomentario');
			$.ajax({
				url : '${saveCommentURL}',
				data: {
	                '<portlet:namespace/><%=PlanIgualdadGenerarInformePortletKeys.PROBLEMAS_DETECTADOS%>' : comentarioDefecto,
	                '<portlet:namespace/>name' : comentarioName
	            },
				  type: 'POST',
				  dataType : "json",
				  success : function(data) {}
			});
		});
		
		$('[name="<portlet:namespace/><%=PlanIgualdadGenerarInformePortletKeys.EXTENSION_PARAM %>"]').change(function(){
			var extension = $(this).val();

			$.ajax({
				url : '${saveExtensionURL}',
				data: {
	                '<portlet:namespace/>extension' : extension
	            },
				  type: 'POST',
				  dataType : "json",
				  success : function(data) {}
			});
		});
		
		$(document).on('click', '.hide-modal', function(e) {
		    e.preventDefault();
		    $('.generarInforme').addClass('hide');
		});
	});
</script>