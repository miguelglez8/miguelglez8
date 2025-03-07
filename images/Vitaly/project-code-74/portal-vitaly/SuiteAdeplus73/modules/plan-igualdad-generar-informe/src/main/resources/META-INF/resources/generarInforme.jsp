<%@page import="com.liferay.petra.string.StringPool"%>
<%@ include file="/init.jsp" %>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.plan.igualdad.liferay.generar.informe.constants.PlanIgualdadGenerarInformePortletKeys"%>

<%
String includeId = ParamUtil.getString(request, PlanIgualdadGenerarInformePortletKeys.INCLUDE_ID);
String title =  ParamUtil.getString(request, PlanIgualdadGenerarInformePortletKeys.INCLUDE_TITLE);
String generarVistaPrevia =  ParamUtil.getString(request, PlanIgualdadGenerarInformePortletKeys.INCLUDE_DOCUMENT);
String generarYEnviar =  ParamUtil.getString(request, PlanIgualdadGenerarInformePortletKeys.INCLUDE_DOCUMENT) + "YEnviar";

String disabled = ParamUtil.getString(request, PlanIgualdadGenerarInformePortletKeys.DISABLED);
String problemasDetectados = StringPool.BLANK;

String showModal = ParamUtil.getString(request, PlanIgualdadGenerarInformePortletKeys.INCLUDE_SHOW_MODAL);
%>

<portlet:resourceURL id="<%=generarVistaPrevia %>" var="generarVistaPreviaURL">
	<portlet:param name="compId" value="${compId}" />
	<portlet:param name="problemasDetectadosId" value="<%=PlanIgualdadGenerarInformePortletKeys.PROBLEMAS_DETECTADOS.concat(includeId)%>" />
</portlet:resourceURL>

<portlet:resourceURL id="<%=generarYEnviar %>" var="generarYEnviarURL">
	<portlet:param name="compId" value="${compId}" />
	<portlet:param name="problemasDetectadosId" value="<%=PlanIgualdadGenerarInformePortletKeys.PROBLEMAS_DETECTADOS.concat(includeId)%>" />
</portlet:resourceURL>

<div class="card">
	<div class="card-header" id="heading<%=includeId%>">
        <h2 class="mb-0">
            <button class="btn btn-link btn-block text-left <%=disabled %>" type="button" data-toggle="collapse"
                    data-target="#collapse<%=includeId%>" aria-expanded="true" aria-controls="collapseOne">
                <liferay-ui:message key="<%=title %>" />
            </button>
        </h2>
    </div>
   	<div id="collapse<%=includeId%>" class="collapse" aria-labelledby="heading<%=includeId%>" data-parent="#cuestionario">
    	<div class="row" style="margin:10px">
         	<div class="form-group col-md-12 col-12">
         		<aui:input type="textarea" label="informe.problemas-dificultades-detectados" data-idcomentario="<%=PlanIgualdadGenerarInformePortletKeys.PROBLEMAS_DETECTADOS.concat(includeId)%>" cssClass="dificultadesDetectados" name="<%=PlanIgualdadGenerarInformePortletKeys.PROBLEMAS_DETECTADOS.concat(includeId)%>">
         			<aui:validator name="maxLength">3000</aui:validator>
         		</aui:input>
         	</div>
       	</div>
       	<div class="button-holder d-flex justify-content-right my-4" style="justify-content: right!important">
			<aui:button-row>
				<%if(showModal.equals("0")){ %>
				<aui:button value="informe.generar-vista-previa" id="<%="btn-generar".concat(includeId) %>" cssClass="btn btn-outline-primary btn-sm btn-generar mr-4" primary="true"></aui:button>
				<aui:button cssClass="btn btn-primary btn-sm btn-generar btn-generar-enviar" id="<%="btn-generar-enviar".concat(includeId) %>" primary="true"  value="informe.generar-enviar-cliente" style="margin-right: 25px;"/>
				
				<%}else{ %>
	            <aui:button value="informe.generar-vista-previa"  href="<%=generarVistaPreviaURL.toString() %>" cssClass="btn btn-outline-primary btn-sm btn-generar mr-4" primary="true"></aui:button>
				<aui:button cssClass="btn btn-primary btn-sm btn-generar btn-generar-enviar" primary="true" href="<%=generarYEnviarURL.toString() %>" value="informe.generar-enviar-cliente" style="margin-right: 25px;"/>
				<%} %>
			</aui:button-row>
		</div>
    </div>
</div>

<div class="modal hide generarInforme" id="modalGenerarInforme<%=includeId%>">
	<div class="modal-backdrop show"></div>
    <div class="modal-dialog center">
    	<div class="modal-content">
	        <div class="modal-header">
	            <h5 class="modal-title"><liferay-ui:message key="informe.modal.titulo-1" /></h5>
	            <button type="button" class="close hide-modal">
	              <span aria-hidden="true">&times;</span>
	            </button>
	        </div>
	        <div class="modal-body">
	       		<p><liferay-ui:message key="informe.modal.campos-no-informados" /></p>
	            <p><liferay-ui:message key="informe.modal.formato-elegir" /></p>
	       		<div class="checksBorder">
	       			<div class="custom-control custom-radio mr-4">
                     	<aui:input cssClass="form-check-input filter-item" label="informe.modal.pdf" name="<%=PlanIgualdadGenerarInformePortletKeys.EXTENSION_PARAM %>" value="0" type="radio">
                     	</aui:input>
                 	</div>
	            	<div class="custom-control custom-radio mr-4">
                      	<aui:input cssClass="form-check-input filter-item" label="informe.modal.docx" name="<%=PlanIgualdadGenerarInformePortletKeys.EXTENSION_PARAM %>" value="1" type="radio">
                      	</aui:input>
              		</div>
              		<div class="custom-control custom-radio mr-4">
                      	<aui:input cssClass="form-check-input filter-item" label="informe.modal.odt" name="<%=PlanIgualdadGenerarInformePortletKeys.EXTENSION_PARAM %>" value="2" type="radio">
                      	</aui:input>
              		</div>
            	</div>
	        </div>
	        <div class="modal-footer">
	            <aui:button value="informe.modal.cancelar" cssClass="btn btn-outline-primary mr-4 hide-modal" primary="true"></aui:button>
	            <div id="generarInformeDiv<%=includeId%>">
	           		<aui:button value="informe.modal.generar" href="<%=generarVistaPreviaURL.toString() %>"  cssClass="btn btn-primary generarHref "/>
	            </div>
	             <div id="chooseFormatDiv<%=includeId%>">
	       			<aui:button value="informe.modal.generar" href="<%=generarYEnviarURL.toString() %>" cssClass="btn btn-primary generarHref button-disabled"/>
	            </div>
	        </div>
	   	</div>
    </div>
</div>

<script>
	$('#<portlet:namespace/>btn-generar<%=includeId %>').click(function(){
		showModalGenerar('0', '<%=includeId%>');
	});
	
	$('#<portlet:namespace/>btn-generar-enviar<%=includeId %>').click(function(){
		showModalGenerar('1', '<%=includeId%>');
	});
</script>