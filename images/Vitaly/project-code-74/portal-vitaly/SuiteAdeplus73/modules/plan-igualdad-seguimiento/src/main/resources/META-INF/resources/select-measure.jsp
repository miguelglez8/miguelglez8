<%@page import="java.util.ResourceBundle"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.plan.igualdad.liferay.seguimiento.constants.PlanIgualdadSeguimientoPortletKeys"%>
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

String compId = (String) request.getParameter(PlanIgualdadSeguimientoPortletKeys.COMPID_PARAM);

%>

<portlet:renderURL var="atrasURL">
    <portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
    <portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.QUERY_PARAM %>" value="<%=compId %>" />
</portlet:renderURL>
		
<liferay-portlet:actionURL name="/planigualdad/selectMeasure" var="selectMeasureURL"  />

<aui:form action="<%= selectMeasureURL.toString() %>" name="selectMeasureForm" method="post" enctype="multipart/form-data">
	<aui:input name="<%= PlanIgualdadSeguimientoPortletKeys.COMPID_PARAM %>" value="<%= compId %>" type="hidden" />
	<aui:input name="checkedInputs" value="" type="hidden" />

	<div class="content">
		<div class="title-group">
			<a href="<%= atrasURL.toString() %>" style="text-decoration: none; float: left; padding: 8px;">
                <i class="icon-arrow-left"></i>
          	</a>
          	<h2><liferay-ui:message key="planigualdadseguimiento.form.select-medidas"/></h2>
		</div>
		<div class="form-content prv-form">
			<div class="row align-items-start mb-4">
				<div class="col-lg-3 col-md-3 col-12">
					<label for="inpSearch">
            			<liferay-ui:message key="planigualdadseguimiento.search"/>            		
            		</label>
            		<input type="search" name="<%=PlanIgualdadSeguimientoPortletKeys.FILTER_SEARCH %>" class="" placeholder="" aria-controls="table_evaluations" id="inpSearch">
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
			</div>
		</div>
		<div class="row mt-3">
			<div class="col">
				<div id="spnCargando" class="loading-animation"></div>
				<div>
					<table id="table_measures" class="display dataTable no-footer dtr-inline stripe " style="width:100%">
						<thead>
							<tr>
								<th><aui:input name="checkAll" label=" " cssClass="form-check-input"  type="checkbox" value="all"></aui:input></th>
								<th class="display-none"></th>
								<th><liferay-ui:message key="planigualdadseguimiento.category"/></th>
								<th><liferay-ui:message key="planigualdadseguimiento.measure"/></th>
								<th><liferay-ui:message key="planigualdadseguimiento.form.description"/></th>
								<th class="display-none"></th>
								<th><liferay-ui:message key="planigualdadseguimiento.priority"/></th>
								<th></th>	
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${medidasListSelect}" var="medida">
								<tr>
									<td class="checkTd" style="padding-left:16px">
										<c:choose>
										    <c:when test="${medida.medidaId !=0}">
										  		<aui:input name="check" label=" " cssClass="form-check-input"  type="checkbox" value="${medida.medidaId}"></aui:input>
										    </c:when>    
										    <c:otherwise>
										        <aui:input name="check" label=" " cssClass="form-check-input"  type="checkbox" value="${medida.medidaPredefinidaId}"></aui:input>
										    </c:otherwise>
										</c:choose>
									</td>
									<td class="display-none">${medida.datosGenerales.materiaId }</td>
									<td><liferay-ui:message key="${medida.datosGenerales.materiaNombre }"/></td>
									<td>${medida.datosGenerales.nombreMedida }</td>
									<td>${medida.datosGenerales.descripcionMedida }</td>
									<td class="display-none">${medida.datosGenerales.prioridadId }</td>
									<td><liferay-ui:message key="${medida.datosGenerales.prioridadNombre }"/></td>
									<td>
										<liferay-portlet:renderURL  var="getSeguimientoURL">
										    <portlet:param name="mvcRenderCommandName" value="goSeguimiento" />
										    <portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.MEDIDA_ID %>" value="${medida.medidaId }" />
											<portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.MEDIDA_PREDEFINIDA_ID %>" value="${medida.medidaPredefinidaId }" />	
											<portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.TASK %>" value="view" />
											<portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.COMPID_PARAM %>" value="<%=compId %>" />
											<portlet:param name="<%=PlanIgualdadSeguimientoPortletKeys.PAGE_BACK_PARAM %>" value="/select-measure.jsp" />
										</liferay-portlet:renderURL>
	
										<div class="d-flex justify-content-end">
											<a href="<%=getSeguimientoURL.toString()%>">
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
	<div class="button-holder d-flex justify-content-center my-4">
		<aui:button-row>
			<aui:button onClick="<%= atrasURL.toString() %>" value="planigualdadseguimiento.form.cancel" cssClass="btn btn-outline-primary btn-sm mr-4" primary="true"></aui:button>
			<aui:button data-btn="submit" type="submit" cssClass="btn btn-primary btn-sm" value="planigualdadseguimiento.add" />
		</aui:button-row>
	</div>
</aui:form>

<script>
	var tabla = null;
	var checkedInputs = "";
	var addCheckedInputs = false;
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
                "targets"  : [0, 7],
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
               var cond = 0;
               var numCond = 0;
               var text = $('#inpSearch').val().toLowerCase();
               
               if (text != "") {
                   numCond++;
                   if ((data[2].toLowerCase().match(text) ||
                       data[2].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) || 
                       (data[3].toLowerCase().match(text) ||
                       data[3].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) ||
                       (data[4].toLowerCase().match(text) ||
                       data[4].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) || 
                       (data[6].toLowerCase().match(text) || 
                       data[6].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text))) cond++;
               }

               var prioridad = $('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.FILTER_PRIORITY%>').val();
               if (prioridad != null && prioridad !="") {
                   numCond++;
                   if (data[5]==prioridad) cond++;
               }
               
               var categoria = $('#<portlet:namespace/><%=PlanIgualdadSeguimientoPortletKeys.FILTER_CATEGORY%>').val();
               if (categoria != null && categoria !="") {
                   numCond++;
                 if (data[1]==categoria) cond++;
            	}
               
               if(numCond == cond) return true;
               return false;
           }
       );

		$('#table_measures tbody').on('click', '.form-check-input', function () {
	       if(addCheckedInputs==false){
	    	   $(this).toggleClass('checked');
		        
		        if($(this).hasClass("checked")){
		        	checkedInputs = checkedInputs + "-"+ $(this).val();
		        }else{
		        	if (checkedInputs.indexOf($(this).val()) > -1){
		        		checkedInputs = checkedInputs.replace("-"+$(this).val(), '');
		        	}
		        }
		        
		        $('[name="<portlet:namespace/>checkedInputs"]').val(checkedInputs);
	       }
	    });
		
		$('#table_measures thead').on('click', '.form-check-input', function () {
			var isCheckedOne=false;
			
			var isCheckedHead = $(this).is(":checked");
			tabla.rows().every( function ( rowIdx, tableLoop, rowLoop ) {
				addCheckedInputs=true;
				
				var changeChecked=false;
				if((isCheckedHead && !$(this.node()).first().find('input').is(":checked")) ||
						(!isCheckedHead && $(this.node()).first().find('input').is(":checked"))){
					changeChecked=true;
				}
				
				if(changeChecked){
					$(this.node()).first().find('input').click();
				    
				    $(this.node()).first().find('input').toggleClass('checked');
			        
			        if($(this.node()).first().find('input').hasClass("checked")){
			        	checkedInputs = checkedInputs + "-"+ $(this.node()).first().find('input').val();
			        }else{
			        	if (checkedInputs.indexOf($(this.node()).first().find('input').val()) > -1){
			        		checkedInputs = checkedInputs.replace("-"+$(this.node()).first().find('input').val(), '');
			        	}
			        }
			        
			        if($(this.node()).first().find('input').is(":checked")){
						isCheckedOne=true;
			        }
			        
			        $('[name="<portlet:namespace/>checkedInputs"]').val(checkedInputs);
				}
			} );
			
			if(!isCheckedOne) {
				$('[name="<portlet:namespace/>checkedInputs"]').val("");
			}
			addCheckedInputs=false;
		});
		
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