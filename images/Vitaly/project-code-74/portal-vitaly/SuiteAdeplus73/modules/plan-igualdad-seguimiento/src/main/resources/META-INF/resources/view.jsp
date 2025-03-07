<%@page import="com.plan.igualdad.liferay.portlet.commons.web.role.PlanIgualdadRoleUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.plan.igualdad.liferay.seguimiento.constants.PlanIgualdadSeguimientoPortletKeys"%>
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
	String compId = (String) request.getAttribute(PlanIgualdadSeguimientoPortletKeys.COMPID_PARAM);

	boolean hasRole = PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
            || PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser());
%>

<liferay-ui:success key="measure-add-success" message="planigualdadseguimiento.message.add-measure-success" />
<liferay-ui:error key="measure-add-error" message="planigualdadseguimiento.message.add-measure-error" />
<liferay-ui:error key="measure-view-error" message="planigualdadseguimiento.message.view-measure-error" />

<liferay-portlet:renderURL varImpl="addMeasureURL">
	<portlet:param name="mvcPath" value="/measure.jsp" />
	<portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.COMPID_PARAM %>" value="<%=compId %>" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="selectMeasureURL">
	<portlet:param name="mvcPath" value="/select-measure.jsp" />
	<portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.COMPID_PARAM %>" value="<%=compId %>" />
</liferay-portlet:renderURL>

<main class="content">
	<div class="formulario">
		<div class="title-group">
			<div class="row">
                <div class="col-md-6 col-12">
                    <h2><liferay-ui:message key="planigualdadseguimiento.title"/></h2>
                </div>
                <c:if test="<%= hasRole %>">
	               	 <div class="col-md-6 col-12 text-right">
	                    <a class="prv-iconLinks prv-iconLinks__plus addMeasure cursor-btn" >
	                        <liferay-ui:message key="planigualdadseguimiento.new"/>
	                    </a>
	                </div>
                </c:if>
            </div>
		</div>
		<div class="form-content prv-form">
			<div class="row align-items-start mb-4">
				<div class="col-lg-3 col-md-3 col-12">
					<fieldset>
						<legend><liferay-ui:message key="planigualdadseguimiento.apply"/></legend>
						<div class="checksBorder">
							<div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" checked="checked" name="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_APPLY %>" id="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_APPLY %>1" value="Si" type="radio" onChange="tabla.draw();" />
								<label class="form-check-label" for="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_APPLY %>1">
									<liferay-ui:message key="planigualdadseguimiento.apply.yes"/>
								</label>
                            </div>
							<div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_APPLY %>" id="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_APPLY %>2" value="No" type="radio" onChange="tabla.draw();" />
								<label class="form-check-label" for="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_APPLY %>2">
									<liferay-ui:message key="planigualdadseguimiento.apply.no"/>
								</label>
                            </div>
							<div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_APPLY %>" id="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_APPLY %>3" value="all" type="radio" onChange="tabla.draw();" />
								<label class="form-check-label" for="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_APPLY %>3">
									<liferay-ui:message key="planigualdadseguimiento.apply.all"/>
								</label>
                            </div>
						</div>
					</fieldset>
				</div>
				<div class="col-lg-2 col-md-2 col-12">
					<fieldset class="">
						 <legend><liferay-ui:message key="planigualdadseguimiento.priority"/></legend>
						 <aui:select name="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_PRIORITY%>" label="" onChange="tabla.draw();"> 
                            <aui:option value="">
                            	<liferay-ui:message key="planigualdadseguimiento.select"/>
                            </aui:option>
                            <c:forEach items="${prioridadesList}" var="prioridad">
                            	<aui:option value="${prioridad.codigo }">
	                            	<liferay-ui:message key="${prioridad.descripcion }"/>
	                            </aui:option>
                            </c:forEach>
                        </aui:select>
					</fieldset>
				</div>
				<div class="col-lg-2 col-md-2 col-12">
					<fieldset class="">
						<legend><liferay-ui:message key="planigualdadseguimiento.category"/></legend>
						<aui:select name="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_CATEGORY%>" label="" onChange="tabla.draw();"> 
                            <aui:option value="">
                            	<liferay-ui:message key="planigualdadseguimiento.select"/>
                            </aui:option>
							<c:forEach items="${categoriasList}" var="categoria">
                            	<aui:option value="${categoria.codigo }">
	                            	<liferay-ui:message key="${categoria.descripcion }"/>
	                            </aui:option>
                            </c:forEach>
                        </aui:select>
					</fieldset>
				</div>
				<div class="col-lg-2 col-md-2 col-12">
					<fieldset class="">
						<legend><liferay-ui:message key="planigualdadseguimiento.level-execution"/></legend>
						<aui:select name="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_EXECUTION%>" label="" onChange="tabla.draw();"> 
                            <aui:option value="">
                            	<liferay-ui:message key="planigualdadseguimiento.select"/>
                            </aui:option>
                            <c:forEach items="${nivelesEjecucionList}" var="nivelEjecucion">
				            	<aui:option value="${nivelEjecucion.codigo }">
				             		<liferay-ui:message key="${nivelEjecucion.descripcionFiltro }"/>
				           		</aui:option>
				        	</c:forEach>
                        </aui:select>
					</fieldset>
				</div>
				<div class="col-lg-2 col-md-2 col-12">
					<fieldset class="">
						<legend><liferay-ui:message key="planigualdadseguimiento.version"/></legend>
						<aui:select name="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_VERSION%>" label="" onChange="tabla.draw();"> 
                            <aui:option value="">
                            	<liferay-ui:message key="planigualdadseguimiento.select"/>
                            </aui:option>
							<c:forEach items="${versionList}" var="version">
								<aui:option value="${version}">
	                            	${version}
	                            </aui:option>
							</c:forEach>
                        </aui:select>
					</fieldset>
				</div>
			</div>
			<div class="row align-items-start mb-4">
				<div class="col-lg-3 col-md-3 col-12">
					<label for="inpSearch">
            			<liferay-ui:message key="planigualdadseguimiento.search"/>            		
            		</label>
            		<input type="search" name="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_SEARCH %>" class="filter-item" placeholder="" aria-controls="table_evaluations" id="inpSearch">
				</div>
				<div class="col-lg-2 col-md-2 col-12">
					<div class="col-md-12 col-12 pl-0">
						<label for="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_PLANNED_DATE_START %>" class="control-label">
							<liferay-ui:message key="planigualdadseguimiento.date-planned-from"/>
						</label>
					 	<liferay-ui:input-date cssClass="filter-item datepicker" name="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_PLANNED_DATE_START %>" nullable="true" showDisableCheckbox="false" />
					</div>
				</div>
				<div class="col-lg-2 col-md-2 col-12">
					<div class="col-md-12 col-12 pl-0">
						<label for="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_PLANNED_DATE_END %>" class="control-label">
							<liferay-ui:message key="planigualdadseguimiento.date-planned-until"/>
						</label>
					 	<liferay-ui:input-date cssClass="filter-item datepicker" name="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_PLANNED_DATE_END %>" nullable="true" showDisableCheckbox="false" />
					</div>
				</div>
				<div class="col-lg-2 col-md-2 col-12">
					<div class="col-md-12 col-12 pl-0">
						<label for="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_IMPLANTATION_DATE_START %>" class="control-label">
							<liferay-ui:message key="planigualdadseguimiento.date-implantation-from"/>
						</label>
					 	<liferay-ui:input-date cssClass="filter-item datepicker" name="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_IMPLANTATION_DATE_START %>" nullable="true" showDisableCheckbox="false" />
					</div>
				</div>
				<div class="col-lg-2 col-md-2 col-12">
					<div class="col-md-12 col-12 pl-0">
						<label for="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_IMPLANTATION_DATE_END %>" class="control-label">
							<liferay-ui:message key="planigualdadseguimiento.date-planned-until"/>
						</label>
					 	<liferay-ui:input-date cssClass="filter-item datepicker" name="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_IMPLANTATION_DATE_END %>" nullable="true" showDisableCheckbox="false" />
					</div>
				</div>
			</div>
			<div class="row align-items-start mb-4">
				<div class="col-md-12 col-12 pr-0">
					<aui:input label="planigualdadseguimiento.show-last-version" name="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_LAST_VERSION %>" checked="true"  type="checkbox" cssClass="form-check-input" onChange="tabla.draw();"/>
				</div>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col">
				<div id="spnCargando" class="loading-animation"></div>
				<div>
					<table id="table_measures" class="display dataTable no-footer dtr-inline stripe " style="width:100%">
						<thead>
							<tr>
								<th class="display-none"></th>
								<th><liferay-ui:message key="planigualdadseguimiento.category"/></th>
								<th><liferay-ui:message key="planigualdadseguimiento.measure"/></th>
								<th class="display-none"></th>
								<th><liferay-ui:message key="planigualdadseguimiento.priority"/></th>
								<th class="display-none"></th>
								<th><liferay-ui:message key="planigualdadseguimiento.level-execution"/></th>
								<th><liferay-ui:message key="planigualdadseguimiento.date-planned"/></th>
								<th><liferay-ui:message key="planigualdadseguimiento.date-implantation"/></th>
								<th><liferay-ui:message key="planigualdadseguimiento.date-tracing"/></th>
								<th class="display-none"><liferay-ui:message key="planigualdadseguimiento.responsible"/></th>
								<th><liferay-ui:message key="planigualdadseguimiento.apply"/></th>
								<th><liferay-ui:message key="planigualdadseguimiento.version"/></th>
								<th class="display-none"></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${medidasList}" var="medida">
								<tr>
									<td class="display-none">${medida.datosGenerales.materiaId }</td>
									<td><liferay-ui:message key="${medida.datosGenerales.materiaNombre }"/></td>
									<td>${medida.datosGenerales.nombreMedida }</td>
									<td class="display-none">${medida.datosGenerales.prioridadId }</td>
									<td><liferay-ui:message key="${medida.datosGenerales.prioridadNombre }"/></td>
									<td class="display-none">${medida.cumplimentacion.nivelEjecucionId }</td>
									<td><liferay-ui:message key="${medida.cumplimentacion.nivelEjecucionNombre }"/></td>
									<td>${medida.cumplimentacion.fechaPrevista }</td>
									<td>${medida.cumplimentacion.fechaImplantacion }</td>
									<td>${medida.cumplimentacion.fechaSeguimiento }</td>
									<td class="display-none">${medida.cumplimentacion.responsable }</td>
									<td class="text-capitalize">${medida.datosGenerales.aplica }</td>
									<td>${medida.versionMedida }</td>
									<td class="display-none">${medida.lastVersion }</td>
									<td>
										<div class="d-flex justify-content-end">
											<c:if test="${medida.cumplimentacion.documentoId!= null && medida.cumplimentacion.documentoId != 0}">
												<portlet:resourceURL id="/planigualdad/downloadDocument" var="downloadDocumentURL">
													<portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.FILE_ID %>" value="${medida.cumplimentacion.documentoId}" />
												</portlet:resourceURL>
												
												<a class="ico-acciones-tabla prv-reverseHover-img" href="<%=downloadDocumentURL.toString()%>">
													<img src="<%=themeDisplay.getPathThemeImages()%>/ico-Download.png" alt="<liferay-ui:message key="dowload" />" />
												</a>
											</c:if>
											
											<c:choose>
												<c:when test="${medida.cumplimentacion.nivelEjecucionId == 3}">
													<liferay-portlet:renderURL  var="getSeguimientoURL">
													    <portlet:param name="mvcRenderCommandName" value="goSeguimiento" />
													    <portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.MEDIDA_ID %>" value="${medida.medidaId }" />
														<portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.TASK %>" value="view" />
														<portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.COMPID_PARAM %>" value="<%=compId %>" />
														<portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.PAGE_BACK_PARAM %>" value="/view.jsp" />
													</liferay-portlet:renderURL>
				
													<div class="d-flex justify-content-end">
														<a href="<%=getSeguimientoURL.toString()%>">
															<span class="ico-acciones-tabla" ><span class="viewDetail icon-eye-open" ></span></span>
														</a>
													</div>
												</c:when>
												<c:otherwise>
										            <liferay-portlet:renderURL  var="getSeguimientoURL">
													    <portlet:param name="mvcRenderCommandName" value="goSeguimiento" />
													    <portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.MEDIDA_ID %>" value="${medida.medidaId }" />
														<portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.TASK %>" value="edit" />
														<portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.COMPID_PARAM %>" value="<%=compId %>" />
													</liferay-portlet:renderURL>
				
													<div class="d-flex justify-content-end">
														<a href="<%=getSeguimientoURL.toString()%>">
															<span class="ico-acciones-tabla" ><span class="icon-pencil" ></span></span>
														</a>
													</div>
										       	</c:otherwise>
											</c:choose>
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

<div class="modal hide" id="addMeasure">
    <div class="modal-backdrop show"></div>
    <div class="modal-dialog center">
        <div class="modal-content">
	        <div class="modal-header">
	            <h5 class="modal-title"><liferay-ui:message key="planigualdadseguimiento.modal.add-title" /></h5>
	            <button type="button" class="close hide-modal">
	              <span aria-hidden="true">&times;</span>
	            </button>
	        </div>
	        <div class="modal-body">
				<div>
	            	<div class="custom-control custom-radio mr-4">
                      	<aui:input cssClass="form-check-input filter-item" label="planigualdadseguimiento.modal.add-1" name="<%=PlanIgualdadSeguimientoPortletKeys.ADD_PARAM %>" value="1" type="radio">
                      	</aui:input>
              		</div>
              		<div class="custom-control custom-radio mr-4">
                      	<aui:input cssClass="form-check-input filter-item" label="planigualdadseguimiento.modal.add-2" name="<%=PlanIgualdadSeguimientoPortletKeys.ADD_PARAM %>" value="2" type="radio">
                      	</aui:input>
              		</div>
            	</div>
	        </div>
	        <div class="modal-footer">
	        	<aui:button value="planigualdadseguimiento.form.cancel" cssClass="btn btn-outline-primary mr-4 hide-modal" primary="true"></aui:button>
	            <aui:button  value="planigualdadseguimiento.form.accept" cssClass="btn btn-primary add-medida-modal"/>
	        </div>
        </div>
    </div>
</div>

<script>
	var tabla = null;
	
	$(document).ready( function () {
		tabla = $('#table_measures').DataTable( {
			dom: '<"capaDatosTabla"iB>t<"capaPaginador"prl>',
			responsive: true,
			pageLength: 20,
			"initComplete": function( settings, json ) {
                $("#spnCargando").hide();
                $("#table_measures").show();
            },
            "columnDefs": [
                {
	                "targets"  : [14],
	                "orderable": false
                }
			],
			buttons: [
				'csv', 'excel', 'print', 'pdf'
			],
			"initComplete": function( settings, json ) {
                $("#spnCargando").hide();
            },
			"language": {
				"sProcessing":     "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sProcessing")%>",
				"sLengthMenu":     "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sLengthMenu")%>",
				"sZeroRecords":    "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sZeroRecords")%>",
				"sEmptyTable":     "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sEmptyTable")%>",
				"sInfo":           "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sInfo")%>",
				"sInfoEmpty":      "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sInfoEmpty")%>",
				"sInfoFiltered":   "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sInfoFiltered")%>",
				"sInfoPostFix":    "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sInfoPostFix")%>",
				"sSearch":         "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sSearch")%>",
				"sUrl":            "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sUrl")%>",
				"sInfoThousands":  "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sInfoThousands")%>,",
				"sLoadingRecords": "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sLoadingRecords")%>",
				"oPaginate": {
					"sFirst":    "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sFirst")%>",
					"sLast":     "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sLast")%>",
					"sNext":     "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sNext")%>",
					"sPrevious": "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sPrevious")%>"
				},
				"oAria": {
					"sSortAscending":  "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sSortAscending")%>",
					"sSortDescending": "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.sSortDescending")%>"
				},
				"buttons": {
					"copy": "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.copy")%>",
					"colvis": "<%=LanguageUtil.get(bundle, "planigualdadseguimiento.view.datatable.colvis")%>"
				}
			}
		});
		
		$.fn.dataTable.ext.search.push(
        	function( settings, data, dataIndex ) {    
        		var text = $('#inpSearch').val().toLowerCase();
                var cond = 0;
                var numCond = 0;
                
                if (text != "") {
                    numCond++;
                    if ((data[1].toLowerCase().match(text) ||
                        data[1].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) || 
                        (data[2].toLowerCase().match(text) ||
                        data[2].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) ||
                        (data[4].toLowerCase().match(text) ||
                        data[4].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) || 
                        (data[6].toLowerCase().match(text) || 
                        data[6].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) ||
                        (data[7].toLowerCase().match(text) ||
                        data[7].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) ||
                        (data[8].toLowerCase().match(text) ||
                        data[8].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) ||
                        (data[9].toLowerCase().match(text) ||
                        data[9].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) ||
                        (data[11].toLowerCase().match(text) ||
                        data[11].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) ||
                        (data[12].toLowerCase().match(text) ||
                        data[12].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text))) cond++;
                }
				
                var aplica = $('input:radio[name=<%=PlanIgualdadSeguimientoPortletKeys.FILTER_APPLY%>]:checked').val();
              	if (aplica != "all") {
                    numCond++;
                    if (aplica == "Si" && (data[11] == "si" || data[11] == "Si")) cond++;
                    if (aplica == "No" && (data[11] == "no" || data[11] == "No")) cond++;
                }
                
                var prioridad = $('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.FILTER_PRIORITY%>').val();
                if (prioridad != null && prioridad !="") {
                    numCond++;
                    if (data[3]==prioridad) cond++;
                }
                
                var categoria = $('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.FILTER_CATEGORY%>').val();
                if (categoria != null && categoria !="") {
                    numCond++;
                    if (data[0]==categoria) cond++;
                }
                
                var ejecucion = $('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.FILTER_EXECUTION%>').val();
                if (ejecucion != null && ejecucion !="") {
                    numCond++;
                    if (data[5]==ejecucion) cond++;
                }
                
                var version = $('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.FILTER_VERSION%>').val();
                if (version != null && version !="") {
                    numCond++;
                    if (data[12]==version) cond++;
                }
                
                if($('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.FILTER_LAST_VERSION %>').is(":checked") == true){
                	numCond++;
                	if(data[13] == 'true') cond++;
                }

                var arrDate = null;
                var dateFilter = null;
                var dateFilterCol = null;
                
                var fechaPrevistaDesde = $("#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.FILTER_PLANNED_DATE_START%>").val();
                var fechaPrevistaHasta = $("#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.FILTER_PLANNED_DATE_END%>").val();

                if(fechaPrevistaDesde.trim() != "" && isValidDate(fechaPrevistaDesde)){
                    numCond++;
                    arrDate = fechaPrevistaDesde.split("/");
                    dateFilter = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                    arrDate = data[7].split("/");
                    dateFilterCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                    if(dateFilterCol.getTime() >= dateFilter.getTime() ) cond++;
                }

                if(fechaPrevistaHasta.trim() != "" && isValidDate(fechaPrevistaHasta)){
                    numCond++;
                    arrDate = fechaPrevistaHasta.split("/");
                    dateFilter = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                    arrDate = data[7].split("/");
                    dateFilterCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                    if(dateFilterCol.getTime() <= dateFilter.getTime() ) cond++;
                }
                
                var fechaImplantacionDesde = $("#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.FILTER_IMPLANTATION_DATE_START%>").val();
                var fechaImplantacionHasta = $("#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.FILTER_IMPLANTATION_DATE_END%>").val();

                if(fechaImplantacionDesde.trim() != "" && isValidDate(fechaImplantacionDesde)){
                    numCond++;
                    arrDate = fechaImplantacionDesde.split("/");
                    dateFilter = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                    arrDate = data[8].split("/");
                    dateFilterCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                    if(dateFilterCol.getTime() >= dateFilter.getTime() ) cond++;
                }

                if(fechaImplantacionHasta.trim() != "" && isValidDate(fechaImplantacionHasta)){
                    numCond++;
                    arrDate = fechaImplantacionHasta.split("/");
                    dateFilter = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                    arrDate = data[8].split("/");
                    dateFilterCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                    if(dateFilterCol.getTime() <= dateFilter.getTime() ) cond++;
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
		
		YUI().use('aui-datepicker', function(Y) {
	        new Y.DatePicker({
	            trigger: '.datepicker',
	            mask: '%d/%m/%Y',
	            popover : {
	                zIndex : 1
	            },
	            on: {
	              selectionChange: function(event) {
	                tabla.draw();
	              }
	            }
	        });
	    });
	});
	
	$(document).on('click', '.addMeasure', function(e){
	    e.preventDefault();
	    $('#addMeasure').removeClass('hide');
	    $('#addMeasure').addClass('show');
	});
	
	$(document).on('click', '.add-medida-modal', function(e){
	    e.preventDefault();
	    $('#addMeasure').removeClass('show');
	    $('#addMeasure').addClass('hide');
		var urlParam = $('[name="<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.ADD_PARAM %>"]:checked').val();
		if(urlParam=="1"){
			window.location.href = "<%= addMeasureURL.toString() %>";
		}else if(urlParam=="2"){
			window.location.href = "<%= selectMeasureURL.toString() %>";
		}
	});

	$(document).on('click', '.hide-modal', function(e) {
	    e.preventDefault();
	    $('#addMeasure').addClass('hide');
	});
	
	function isValidDate(_date){
        var reg = /^(0[1-9]|[1-2]\d|3[01])(\/)(0[1-9]|1[012])\2(\d{4})$/;
        if(reg.test(_date)) return true;
        return false;
    }
</script>