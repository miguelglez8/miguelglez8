<%@page import="com.plan.igualdad.liferay.portlet.commons.web.role.PlanIgualdadRoleUtil"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="plan.igualdad.evaluacion.constants.PlanIgualdadEvaluacionPortletKeys"%>
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

<script>
	Liferay.Loader.define.amd = Liferay.Loader.define._amd;
</script>
<%
	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getLocale());
	String compId = (String) request.getAttribute(PlanIgualdadEvaluacionPortletKeys.COMPID_PARAM);

	int versions = (Integer) request.getAttribute(PlanIgualdadEvaluacionPortletKeys.VERSION_LIST);

	boolean hasRole = PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
            || PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser());

%>

<liferay-ui:success key="evaluation-add-success" message="planigualdadevaluacion.message.add-evaluation-success" />
<liferay-ui:error key="evaluation-add-error" message="planigualdadevaluacion.message.add-evaluation-error" />
<liferay-ui:error key="evaluation-view-error" message="planigualdadevaluacion.message.view-evaluation-error" />

<liferay-portlet:renderURL varImpl="addEvaluationURL">
	<portlet:param name="mvcPath" value="/evaluation.jsp" />
	<portlet:param name="<%=PlanIgualdadEvaluacionPortletKeys.COMPIDEVALUATION_PARAM %>" value="<%=compId %>" />
</liferay-portlet:renderURL>

<main class="content">
	<div class="formulario">
		<div class="title-group">
			<div class="row">
                <div class="col-md-6 col-12">
                    <h2><liferay-ui:message key="planigualdadevaluacion.title"/></h2>
                </div>
                <c:if test="<%= hasRole %>">
	                <div class="col-md-6 col-12 text-right">
	                    <a class="prv-iconLinks prv-iconLinks__plus" href="<%= addEvaluationURL.toString() %>">
	                        <liferay-ui:message key="planigualdadevaluacion.new"/>
	                    </a>
	                </div>
	           	</c:if>
            </div>
		</div>
		<div class="form-content prv-form">
			<div class="row align-items-start mb-4">
				<div class="col-lg-6 col-md-6 col-12">
					<fieldset class="">
                        <legend><liferay-ui:message key="planigualdadevaluacion.version"/></legend>
                        <aui:select name="<%=PlanIgualdadEvaluacionPortletKeys.VERSION%>" label="" onChange="tabla.draw();"> 
                            <aui:option value="">
                            	<liferay-ui:message key="planigualdadevaluacion.select"/>
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
            			<liferay-ui:message key="planigualdadevaluacion.search"/>            		
            		</label>
            		<input type="search" name="<%=PlanIgualdadEvaluacionPortletKeys.SEARCH %>" class="" placeholder="" aria-controls="table_evaluations" id="inpSearch">
				</div>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col">
				<div id="spnCargando" class="loading-animation"></div>
				<table id="table_evaluations" class="display" style="width:100%">
					<thead>
						<tr>
							<th><liferay-ui:message key="planigualdadevaluacion.evaluation-date"/></th>
							<th><liferay-ui:message key="planigualdadevaluacion.user"/></th>
							<th><liferay-ui:message key="planigualdadevaluacion.observations"/></th>
							<th><liferay-ui:message key="planigualdadevaluacion.version"/></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${evaluationsList}" var="evaluation">
							<tr>
								<td>${evaluation.dateEvaluation}</td>
								<td>${evaluation.userName}</td>
								<td>${evaluation.observations}</td>
								<td>${evaluation.versionEvaluacion}</td>
								<td>
									<liferay-portlet:renderURL  var="getEvaluationURL">
									    <portlet:param name="mvcRenderCommandName" value="goEvaluation" />
									    <portlet:param name="<%=PlanIgualdadEvaluacionPortletKeys.COMPIDEVALUATION_PARAM %>" value="<%=compId %>" />
									    <portlet:param name="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_ID %>" value="${evaluation.evaluationId }" />
									</liferay-portlet:renderURL>

									<div class="d-flex justify-content-end">
										<a href="<%=getEvaluationURL.toString()%>">
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
</main>

<script>
	var tabla = null;
	
	$(document).ready( function () {
		tabla = $('#table_evaluations').DataTable( {
			responsive: true,
			pageLength: 25,
			"initComplete": function( settings, json ) {
                $("#spnCargando").hide();
                $("#table_evaluations").show();
            },
            "columnDefs": [
                {
	                "targets"  : [4],
	                "orderable": false
                }
			],
			order: [[0, 'desc']],
			"language": {
				"sLengthMenu":     "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.sLengthMenu")%>",
				"sZeroRecords":    "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.sZeroRecords")%>",
				"sEmptyTable":     "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.sEmptyTable")%>",
				"sInfo":           "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.sInfo")%>",
				"sInfoEmpty":      "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.sInfoEmpty")%>",
				"sInfoFiltered":   "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.sInfoFiltered")%>",
				"sInfoPostFix":    "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.sInfoPostFix")%>",
				"sSearch":         "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.sSearch")%>",
				"sLoadingRecords": "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.sLoadingRecords")%>",
				"oPaginate": {
					"sFirst":    "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.sFirst")%>",
					"sLast":     "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.sLast")%>",
					"sNext":     "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.sNext")%>",
					"sPrevious": "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.sPrevious")%>"
				},
				"oAria": {
					"sSortAscending":  "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.sSortAscending")%>",
					"sSortDescending": "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.sSortDescending")%>"
				},
				"buttons": {
					"copy": "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.copy")%>",
					"colvis": "<%=LanguageUtil.get(bundle, "planigualdadevaluacion.datatable.colvis")%>"
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
                
                var version = $('#<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.VERSION%>').val();

                if (version != null && version !="") {
                    numCond++;
                    if (data[3]==version) cond++;
                }
         
                if(numCond == cond) return true;
                return false;
            }
        );
		
		$(document).on('keypress',function(e) {
		    if(e.which == 13) {
		   		tabla.draw() ;
		    }
		});
		
		$(document).on('keyup', '#inpSearch', function(e) {
	   		tabla.draw() ;
		});
		
		tabla.draw();
	});
</script>