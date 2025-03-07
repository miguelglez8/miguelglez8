<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="com.plan.igualdad.liferay.portlet.seguimiento.cuestionario.constants.PlanIgualdadSeguimientoCuestionarioPortletKeys"%>
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
	
	int versions = (Integer) request.getAttribute(PlanIgualdadSeguimientoCuestionarioPortletKeys.VERSIONS);
	String compId = (String) request.getAttribute(PlanIgualdadSeguimientoCuestionarioPortletKeys.COMPID_PARAM);
%>

<liferay-ui:success key="questionary-add-success" message="cuestionario.message.add-questionary-success" />
<liferay-ui:error key="questionary-add-error" message="cuestionario.message.add-questionary-error" />
<liferay-ui:error key="questionary-view-error" message="cuestionario.message.view-questionary-error" />

<liferay-portlet:renderURL varImpl="goCuestionarioURL">
	<portlet:param name="mvcPath" value="/cuestionario.jsp" />
	<portlet:param name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.COMPIDEVALUATION_PARAM %>" value="<%=compId %>" />
</liferay-portlet:renderURL>

<main class="content">
    <div class="formulario">
    	<div class="title-group">
			<div class="row">
                <div class="col-md-6 col-12">
                    <h2><liferay-ui:message key="cuestionario.title"/></h2>
                </div>
                
               	 <div class="col-md-6 col-12 text-right">
                    <a class="prv-iconLinks prv-iconLinks__plus" href="<%= goCuestionarioURL.toString() %>">
                        <liferay-ui:message key="cuestionario.nuevo-cuestionario"/>
                    </a>
                </div>
            </div>
		</div>
		<div class="form-content prv-form">
			<div class="row align-items-start mb-4">
				<div class="col-lg-4 col-md-4 col-12">
					<aui:input disabled="true" label="cuestionario.n-medidas-plan" type="text" name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.N_MEDIDAS_PLAN %>" value="${nMedidasPlan}"></aui:input>
				</div>
				<div class="col-lg-4 col-md-4 col-12">
					<aui:input disabled="true" label="cuestionario.n-medidas-ejecucion" type="text" name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.N_MEDIDAS_EJECUCION %>" value="${nMedidasEjecucion }"></aui:input>
				</div>
			</div>
			<div class="row align-items-start mb-4">
				<div class="col-lg-4 col-md-4 col-12">
					<aui:input disabled="true" label="cuestionario.n-medidas-informado" type="text" name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.N_MEDIDAS_SEGUIMIENTO %>" value="${nMedidasSeguimiento }"></aui:input>
				</div>
				<div class="col-lg-4 col-md-4 col-12">
					<aui:input disabled="true" label="cuestionario.n-medidas-finalizadas" type="text" name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.N_MEDIDAS_FINALIZADAS %>" value="${nMedidasFinalizadas }"></aui:input>
				</div>
			</div>
			<div class="row align-items-start mb-4">
				<div class="col-lg-4 col-md-4 col-12">
					<aui:input disabled="true" label="cuestionario.n-medidas-pendientes" type="text" name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.N_MEDIDAS_PENDIENTES %>" value="${nMedidasPendientes }"></aui:input>
				</div>
				<div class="col-lg-4 col-md-4 col-12">
					<aui:input disabled="true" label="cuestionario.n-medidas-prioritarias" type="text" name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.N_MEDIDAS_PRIORITARIAS %>" value="${nMedidasPrioritarias }"></aui:input>
				</div>
			</div>
			
			<div class="row align-items-start mb-4">
				<div class="col-lg-4 col-md-4 col-12">
					<fieldset class="">
						<legend><liferay-ui:message key="cuestionario.version"/></legend>
						<aui:select name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.FILTER_VERSION%>" label="" onChange="tabla.draw();"> 
                            <aui:option value="">
                            	<liferay-ui:message key="cuestionario.select"/>
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
            			<liferay-ui:message key="cuestionario.buscar"/>            		
            		</label>
            		<input type="search" name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.FILTER_SEARCH %>" class="" placeholder="" aria-controls="table_questionnaries" id="inpSearch">
				</div>
			</div>
		</div>
		
		<div class="row mt-3">
			<div class="col">
				<div id="spnCargando" class="loading-animation"></div>
				<div>
					<table id="table_questionnaries" class="display dataTable no-footer dtr-inline stripe " style="width:100%">
						<thead>
							<tr>
								<th><liferay-ui:message key="cuestionario.f-cuestionario"/></th>
								<th><liferay-ui:message key="cuestionario.usuario"/></th>
								<th><liferay-ui:message key="cuestionario.observaciones"/></th>
								<th><liferay-ui:message key="cuestionario.version"/></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${cuestionariosList}" var="cuestionario">
								<tr>
									<td>${cuestionario.fechaCuestionario }</td>
									<td>${cuestionario.usuario }</td>
									<td>${cuestionario.observaciones }</td>
									<td>${cuestionario.version }</td>
									<td>
										<liferay-portlet:renderURL  var="getQuestionaryURL">
										    <portlet:param name="mvcRenderCommandName" value="goQuestionary" />
										    <portlet:param name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.CUESTIONARIO_ID %>" value="${cuestionario.cuestionarioId }" />
											<portlet:param name="<%=PlanIgualdadSeguimientoCuestionarioPortletKeys.COMPIDEVALUATION_PARAM %>" value="<%=compId %>" />
										</liferay-portlet:renderURL>
										
										<div class="d-flex justify-content-end">
											<a href="<%=getQuestionaryURL.toString()%>">
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
		tabla = $('#table_questionnaries').DataTable( {
			responsive: true,
			pageLength: 20,
			"initComplete": function( settings, json ) {
                $("#spnCargando").hide();
                $("#table_questionnaries").show();
            },
            order: [[0, 'desc']],
            "columnDefs": [
                {
	                "targets"  : [4],
	                "orderable": false
                }
			],
			"initComplete": function( settings, json ) {
                $("#spnCargando").hide();
            },
            "language": {
				"sProcessing":     "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sProcessing")%>",
				"sLengthMenu":     "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sLengthMenu")%>",
				"sZeroRecords":    "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sZeroRecords")%>",
				"sEmptyTable":     "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sEmptyTable")%>",
				"sInfo":           "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sInfo")%>",
				"sInfoEmpty":      "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sInfoEmpty")%>",
				"sInfoFiltered":   "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sInfoFiltered")%>",
				"sInfoPostFix":    "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sInfoPostFix")%>",
				"sSearch":         "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sSearch")%>",
				"sUrl":            "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sUrl")%>",
				"sInfoThousands":  "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sInfoThousands")%>,",
				"sLoadingRecords": "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sLoadingRecords")%>",
				"oPaginate": {
					"sFirst":    "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sFirst")%>",
					"sLast":     "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sLast")%>",
					"sNext":     "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sNext")%>",
					"sPrevious": "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sPrevious")%>"
				},
				"oAria": {
					"sSortAscending":  "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sSortAscending")%>",
					"sSortDescending": "<%=LanguageUtil.get(bundle, "cuestionario.view.datatable.sSortDescending")%>"
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
				
                var version = $('#<portlet:namespace/><%=PlanIgualdadSeguimientoCuestionarioPortletKeys.FILTER_VERSION%>').val();

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