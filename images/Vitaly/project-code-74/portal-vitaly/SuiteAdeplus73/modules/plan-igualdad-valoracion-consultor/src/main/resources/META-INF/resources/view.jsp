<%@page import="com.plan.igualdad.liferay.portlet.valoracion.consultor.constants.PlanIgualdadValoracionConsultorPortletKeys"%>
<%@ page import="com.plan.igualdad.liferay.portlet.commons.web.role.PlanIgualdadRoleUtil" %>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="java.util.ResourceBundle"%>
<%@ include file="/init.jsp" %>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.5/js/dataTables.responsive.min.js">

<script>
	Liferay.Loader.define._amd = Liferay.Loader.define.amd;
	Liferay.Loader.define.amd = false;
</script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.flash.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.html5.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.print.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json"></script>

<%-- SELECT2 --%>
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/i18n/es.js"></script>

<script>
	Liferay.Loader.define.amd = Liferay.Loader.define._amd;
</script>

<%
	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getLocale());
	String compId = (String) request.getAttribute(PlanIgualdadValoracionConsultorPortletKeys.COMPID_PARAM);

	int versions = (Integer) request.getAttribute(PlanIgualdadValoracionConsultorPortletKeys.VERSIONS);

	boolean hasRole = PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                        || PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser());
%>

<liferay-ui:success key="assessment-add-success" message="valoracion.message.add-assessment-success" />
<liferay-ui:error key="assessment-add-error" message="valoracion.message.add-assessment-error" />
<liferay-ui:error key="assessment-view-error" message="valoracion.message.view-assessment-error" />

<liferay-portlet:renderURL varImpl="goValoracionURL">
	<portlet:param name="mvcPath" value="/valoracion.jsp" />
	<portlet:param name="<%=PlanIgualdadValoracionConsultorPortletKeys.COMPID_PARAM %>" value="<%=compId %>" />
</liferay-portlet:renderURL>

<main class="content">
    <div class="formulario">
    	<div class="title-group">
			<div class="row">
                <div class="col-md-6 col-12">
                    <h2><liferay-ui:message key="valoracion.title"/></h2>
                </div>

                <c:if test="<%= hasRole %>">
                     <div class="col-md-6 col-12 text-right">
                        <a class="prv-iconLinks prv-iconLinks__plus" href="<%= goValoracionURL.toString() %>">
                            <liferay-ui:message key="valoracion.nueva"/>
                        </a>
                    </div>
                </c:if>
            </div>
		</div>
		<div class="form-content prv-form">
			<div class="row align-items-start mb-4">
				<div class="col-lg-4 col-md-4 col-12">
					<aui:input disabled="true" label="valoracion.medidas-ejecutadas-total" type="text" name="<%=PlanIgualdadValoracionConsultorPortletKeys.MEDIDAS_EJECUTADAS_TOTAL %>" value="${medidasEjecutadasTotal}"></aui:input>
				</div>
				<div class="col-lg-4 col-md-8 col-12">
					 ${pregunta1}
				</div>
			</div>
			<div class="row align-items-start mb-4">
				<div class="col-lg-4 col-md-4 col-12">
					<aui:input disabled="true" label="valoracion.medidas-prioritarias-ejecutadas" type="text" name="<%=PlanIgualdadValoracionConsultorPortletKeys.MEDIDAS_PRIORITARIAS_EJECUTADAS %>" value="${medidasPrioritariasEjecutadas}"></aui:input>
				</div>
				<div class="col-lg-4 col-md-8 col-12">
					 ${pregunta2}<br>
					 ${pregunta3}
				</div>
			</div>
			<div class="row align-items-start mb-4">
				<div class="col-lg-4 col-md-4 col-12">
					<aui:input disabled="true" label="valoracion.medidas-repo-documental" type="text" name="<%=PlanIgualdadValoracionConsultorPortletKeys.MEDIDAS_REPO_DOCUMENTAL %>" value="${medidasRepoDocumental}"></aui:input>
				</div>
				<div class="col-lg-4 col-md-8 col-12">
					 ${pregunta5}
				</div>
			</div>
			
			<div class="row align-items-start mb-4">
				<div class="col-lg-4 col-md-4 col-12">
					<fieldset class="">
						<legend><liferay-ui:message key="valoracion.version"/></legend>
						<aui:select name="<%=PlanIgualdadValoracionConsultorPortletKeys.FILTER_VERSIONS%>" label="" onChange="tabla.draw();"> 
                            <aui:option value="">
                            	<liferay-ui:message key="valoracion.select"/>
                            </aui:option>
                            <% if(versions>0){
                            	for(int i=1; i<=versions; i++){ %>
                            <aui:option value="<%=i %>"> <%=i %> </aui:option>
                            <%}} %>
                        </aui:select>
					</fieldset>
				</div>
				<div class="col-lg-3 col-md-3 col-12">
					<label for="inpSearch">
            			<liferay-ui:message key="valoracion.buscar"/>            		
            		</label>
            		<input type="search" name="<%=PlanIgualdadValoracionConsultorPortletKeys.FILTER_SEARCH %>" class="" placeholder="" aria-controls="table_assessment" id="inpSearch">
				</div>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col">
				<div id="spnCargando" class="loading-animation"></div>
				<div>
					<table id="table_assessment" class="display dataTable no-footer dtr-inline stripe " style="width:100%">
						<thead>
							<tr>
								<th><liferay-ui:message key="valoracion.fecha"/></th>
								<th><liferay-ui:message key="valoracion.usuario"/></th>
								<th><liferay-ui:message key="valoracion.observaciones"/></th>
								<th><liferay-ui:message key="valoracion.version"/></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${valoracionesList}" var="valoracion">
								<tr>
									<td>${valoracion.fechaValoracion }</td>
									<td>${valoracion.usuario }</td>
									<td>${valoracion.observaciones }</td>
									<td>${valoracion.version }</td>
									<td>
										<liferay-portlet:renderURL  var="getAssessmentURL">
										    <portlet:param name="mvcRenderCommandName" value="goAssessment" />
										    <portlet:param name="<%=PlanIgualdadValoracionConsultorPortletKeys.VALORACION_ID %>" value="${valoracion.valoracionId }" />
											<portlet:param name="<%=PlanIgualdadValoracionConsultorPortletKeys.COMPID_PARAM %>" value="<%=compId %>" />
										</liferay-portlet:renderURL>
										
										<div class="d-flex justify-content-end">
											<a href="<%=getAssessmentURL.toString()%>">
												<span class="ico-acciones-tabla" ><span class="viewDetail icon-eye-open" ></span></span>
											</a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>				
			</div>
		</div>
	</div>
</main>

<script>
	var tabla = null;
	$(document).ready( function () {
		tabla = $('#table_assessment').DataTable( {
			responsive: true,
			pageLength: 20,
			"initComplete": function( settings, json ) {
                $("#spnCargando").hide();
                $("#table_assessment").show();
            },
            "columnDefs": [
                {
	                "targets"  : [4],
	                "orderable": false
                }
			],
			"initComplete": function( settings, json ) {
                $("#spnCargando").hide();
            },
            order: [[0, 'desc']],
            "language": {
				"sProcessing":     "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sProcessing")%>",
				"sLengthMenu":     "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sLengthMenu")%>",
				"sZeroRecords":    "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sZeroRecords")%>",
				"sEmptyTable":     "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sEmptyTable")%>",
				"sInfo":           "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sInfo")%>",
				"sInfoEmpty":      "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sInfoEmpty")%>",
				"sInfoFiltered":   "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sInfoFiltered")%>",
				"sInfoPostFix":    "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sInfoPostFix")%>",
				"sSearch":         "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sSearch")%>",
				"sUrl":            "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sUrl")%>",
				"sInfoThousands":  "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sInfoThousands")%>,",
				"sLoadingRecords": "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sLoadingRecords")%>",
				"oPaginate": {
					"sFirst":    "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sFirst")%>",
					"sLast":     "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sLast")%>",
					"sNext":     "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sNext")%>",
					"sPrevious": "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sPrevious")%>"
				},
				"oAria": {
					"sSortAscending":  "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sSortAscending")%>",
					"sSortDescending": "<%=LanguageUtil.get(bundle, "valoracion.view.datatable.sSortDescending")%>"
				},
				"buttons": {
					"copy": "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.copy")%>",
					"colvis": "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.colvis")%>"
				}
			}
		});
		
		$.fn.dataTable.ext.search.push(
        	function( settings, data, dataIndex ) {       
                var cond = 0;
                var numCond = 0;
                
                var text = $('#inpSearch').val().toLowerCase();
                if (text != "") {
                    numCond++;
                    if ((data[0].toLowerCase().match(text) ||
                        data[0].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) || 
                        (data[1].toLowerCase().match(text) ||
                        data[1].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) ||
                        (data[2].toLowerCase().match(text) ||
                        data[2].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) || 
                        (data[3].toLowerCase().match(text) || 
                        data[3].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text))) cond++;
                }
				
                var version = $('#<portlet:namespace/><%=PlanIgualdadValoracionConsultorPortletKeys.FILTER_VERSIONS%>').val();

                if (version != null && version !="") {
                    numCond++;
                    if (data[3]==version) cond++;
                }
         
                if(numCond == cond) return true;
                return false;
            }
        );
		
		$(document).on('keyup', '#inpSearch', function(e) {
	   		tabla.draw() ;
		});
		
		$(document).on('keypress',function(e) {
		    if(e.which == 13) {
		   		tabla.draw() ;
		    }
		});
		
		tabla.draw();
		
	});
</script>