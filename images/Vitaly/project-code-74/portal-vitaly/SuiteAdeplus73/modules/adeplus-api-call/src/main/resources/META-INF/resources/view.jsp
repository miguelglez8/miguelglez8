<%@ include file="/init.jsp" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="java.util.ResourceBundle" %>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.5/js/dataTables.responsive.min.js">
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/responsive/2.5.0/js/dataTables.responsive.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.flash.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.html5.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.print.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json"></script>
<%
ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
%>
<main class="content">
	<div class="formulario">
		<div class="form-content">
			<div class="row">
				<div class="col">
				    <div id="spnCargando" class="loading-animation"></div>
				    <div id="spnTabla" style="display:none">
					    <table id="table_audit" class="display" style="width:100%">
						    <thead>
						    <tr>
							    <th><liferay-ui:message key="api.identificator"/></th>
							    <th><liferay-ui:message key="api.mesagge"/></th>
							    <th><liferay-ui:message key="api.estado"/></th>
							    <th><liferay-ui:message key="api.data.idCliente"/></th>
                                <th><liferay-ui:message key="api.data.idContrato"/></th>
                                <th><liferay-ui:message key="api.data.evento"/></th>
                                <th><liferay-ui:message key="api.data.empresa"/></th>
						    </tr>
						    </thead>
						    <tbody></tbody>
					    </table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="formulario">
    		<div class="form-content">
    		<div class="row">
            				<fieldset class="input-group-inline mt-3 mb-2">
            					<legend>Ver solo:</legend>
            					<div class="custom-control custom-radio mr-4">
            						<input class="form-check-input" name="inpSearchActive" id="inpSearchActive1" value="PROCESADOS" type="radio" class="inpSearchActive"  onChange="tabla_error.draw();" />
            						<label class="form-check-label" for="inpSearchActive1">Procesados</label>
            					</div>
            					<div class="custom-control custom-radio mr-4">
            						<input class="form-check-input" name="inpSearchActive" id="inpSearchActive2" value="SIN_PROCESAR" type="radio" class="inpSearchActive" checked onChange="tabla_error.draw();" />
            						<label class="form-check-label" for="inpSearchActive2">No procesados</label>
            					</div>
            					<div class="custom-control custom-radio mr-4">
            						<input class="form-check-input" name="inpSearchActive" id="inpSearchActive3" value="TODOS" type="radio" class="inpSearchActive" onChange="tabla_error.draw();" />
            						<label class="form-check-label" for="inpSearchActive3">Todos</label>
            					</div>
            				</fieldset>
            			</div>
    			<div class="row">
    				<div class="col">
    				    <div id="spnCargando_error" class="loading-animation"></div>
    				    <div id="spnTabla_error" style="display:none">
    					    <table id="table_audit_error" class="display" style="width:100%">
    						    <thead>
    						    <tr>
    							    <th><liferay-ui:message key="api.identificator"/></th>
    							    <th><liferay-ui:message key="api.mesagge"/></th>
    							    <th><liferay-ui:message key="api.estado"/></th>
                                    <th><liferay-ui:message key="api.data.application"/></th>

    							    <th><liferay-ui:message key="api.procesadoAdmin"/></th>
    							    <th><liferay-ui:message key="api.procesadoUsuario"/></th>
    							    <th><liferay-ui:message key="api.data.fecha"/></th>
    							    <th><liferay-ui:message key=""/>Cambiar estado</th>

    							    <!-- <th><liferay-ui:message key="api.data.application"/></th>
                                    <th><liferay-ui:message key="api.data.usuario"/></th>
                                    <th><liferay-ui:message key="api.data.empresa"/></th> -->
    						    </tr>
    						    </thead>
    						    <tbody></tbody>
    					    </table>
    					</div>
    				</div>
    			</div>
    		</div>
    	</div>


</main>
<button onClick="sendApiSub()" value="Lanzar escucha">Lanzar escucha</button>

<portlet:resourceURL id="getAllAuditApi" var="getAllAuditApiURL"/>
<portlet:resourceURL id="getAllAuditApiError" var="getAllAuditApiErrorURL"/>
<portlet:resourceURL id="sendApiPubSub" var="sendApiPubSubURL"/>

<portlet:resourceURL id="changeStateEventError" var="changeStateEventErrorURL"/>

<script>
	var tabla = null;
	var urlAjaxBase = "<%=getAllAuditApiURL.toString()%>" ;
	$(document).ready( function () {

		tabla = $('#table_audit').on('preXhr.dt', function ( e, settings, data ) {
		                console.log("preXhr.dt load")
                        //$("#spnCargando").hide();
                        //$("#spnTabla").show();
            }).DataTable( {
            "initComplete": function(settings, json) {
                        console.log("initComplete load")
                        $("#spnCargando").hide();
                        $("#spnTabla").show();
            },
			dom: 'Bfrtip',
			ajax: {
                "url": urlAjaxBase,
                "dataSrc": function ( json ) {  return json.data; }
            },
            "columns": [
                {"data" : "idDataTemporal"},
                {"data" : "mensaje"},
                {"data" : "estado"},
                {"data" : "idCliente"},
                {"data" : "idContrato"},
                {"data" : "evento"},
                {"data" : "dataEmpresa"}
            ],
			responsive: true,
			pageLength: 25,
			buttons: [
				'csv', 'excel', 'print', 'pdf'
			],
			"language": {
				"sProcessing":     "<%=LanguageUtil.get(bundle, "api.view.datatable.sProcessing")%>",
				"sLengthMenu":     "<%=LanguageUtil.get(bundle, "api.view.datatable.sLengthMenu")%>",
				"sZeroRecords":    "<%=LanguageUtil.get(bundle, "api.view.datatable.sZeroRecords")%>",
				"sEmptyTable":     "<%=LanguageUtil.get(bundle, "api.view.datatable.sEmptyTable")%>",
				"sInfo":           "<%=LanguageUtil.get(bundle, "api.view.datatable.sInfo")%>",
				"sInfoEmpty":      "<%=LanguageUtil.get(bundle, "api.view.datatable.sInfoEmpty")%>",
				"sInfoFiltered":   "<%=LanguageUtil.get(bundle, "api.view.datatable.sInfoFiltered")%>",
				"sInfoPostFix":    "<%=LanguageUtil.get(bundle, "api.view.datatable.sInfoPostFix")%>",
				"sSearch":         "<%=LanguageUtil.get(bundle, "api.view.datatable.sSearch")%>",
				"sUrl":            "<%=LanguageUtil.get(bundle, "api.view.datatable.sUrl")%>",
				"sInfoThousands":  "<%=LanguageUtil.get(bundle, "api.view.datatable.sInfoThousands")%>,",
				"sLoadingRecords": "<%=LanguageUtil.get(bundle, "api.view.datatable.sLoadingRecords")%>",
				"oPaginate": {
					"sFirst":    "<%=LanguageUtil.get(bundle, "api.view.datatable.sFirst")%>",
					"sLast":     "<%=LanguageUtil.get(bundle, "api.view.datatable.sLast")%>",
					"sNext":     "<%=LanguageUtil.get(bundle, "api.view.datatable.sNext")%>",
					"sPrevious": "<%=LanguageUtil.get(bundle, "api.view.datatable.sPrevious")%>"
				},
				"oAria": {
					"sSortAscending":  "<%=LanguageUtil.get(bundle, "api.view.datatable.sSortAscending")%>",
					"sSortDescending": "<%=LanguageUtil.get(bundle, "api.view.datatable.sSortDescending")%>"
				},
				"buttons": {
					"copy": "<%=LanguageUtil.get(bundle, "api.view.datatable.copy")%>",
					"colvis": "<%=LanguageUtil.get(bundle, "api.view.datatable.colvis")%>"
				}
			}
		} );



		$('#inpSearchActive').change( function() {
			tabla.draw();
		} );

		$('.clsApps').change( function() {
			tabla.draw();
		} );

		tabla.draw();

	} );
    var publishDate = null;
	var tabla_error = null;
	var createDate = null;
    var urlAjaxBaseError = "<%=getAllAuditApiErrorURL.toString()%>" ;
    $(document).ready( function () {

        tabla_error = $('#table_audit_error').on('preXhr.dt', function ( e, settings, data ) {
                        console.log("preXhr.dt load")
                        //$("#spnCargando").hide();
                        //$("#spnTabla").show();
            }).DataTable( {
            "initComplete": function(settings, json) {
                        console.log("initComplete load")
                        $("#spnCargando_error").hide();
                        $("#spnTabla_error").show();
            },
            dom: 'Bfrtip',
            ajax: {
                "url": urlAjaxBaseError,
                "dataSrc": function ( json ) {  return json.data; }
            },
            "columns": [
                            {"data" : "idDataTemporal"},
                            {"data" : "mensaje"},
                            {"data" : "estado"},
                            {"data" : "dataTemporalApplication"}, //unificamos columnas
                            {"data" : "procesadoAdmin"}, //procesaso SI o NO
                            {"data" : "fechaEstadoAdmin"},//usuario y fecha cambio de estado
                            {"data" : "fechaCreacion"},//fecha creacion
                            {"data" : "idDataTemporal"} //boton cambio de estado

            ],
            "columnDefs": [
                            {
                                "targets"  : [3], //datos json por fila
                                "orderable": false,
                                "render": function (data, type, row) {
                                    return "<strong>Datos aplicaciones:</strong><br/>" + row.dataTemporalApplication
                                            + "<br/>"
                                            + "<strong>Datos usuario:</strong> <br/>" + row.dataTemporalUsuarios
                                            + "<br/>"
                                            + "<strong>Datos empresa:</strong> <br/>" + row.dataTemporalEmpresa;

                                }
                            },
                            {
                                "targets"  : [4], //procesadoAdmin Si o No
                                "orderable": false,
                                "visible": false,
                                "render": function (data, type, row) {
                                    if(data == true) return "Si"
                                    return "No";
                                }
                            },
                            {
                                "targets"  : [5], //datos procesado usuario / fecha
                                "orderable": false,
                                "render": function (data, type, row) {
                                    if(data != null && data != ""){
                                        console.log("row")
                                        console.log(row)
                                        publishDate = new Date(Number(data));
                                        return row.usuarioEstadoAdmin + "<br/>(" + publishDate.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' })  + ")"
                                    }
                                    return "-Pendiente-";
                                }
                            },
                            {
                                "targets"  : [6], //fechaCreacion
                                "orderable": false,
                                "render": function (data, type, row) {
                                       if(data != null && data != ""){
                                           createDate = new Date(Number(data));
                                           return createDate.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' })
                                       }
                                       return "";
                                }
                            },
                            {
                                "targets"  : [7], //boton cambio de estado
                                "orderable": false,
                                "render": function (data, type, row) {
                                        var txt = (row.procesadoAdmin == true)? 'Pasar pendiente' : 'Procesar'; // lo contrario
                                        return '<button type="button" class="btn btn-primary" onClick=changeState(' + data +  ')>'  + txt + '</button>'
                                },
                                "searchable": false
                            }
            ],
            responsive: true,
            pageLength: 25,
            buttons: [
                'csv', 'excel', 'print', 'pdf'
            ],
            "language": {
                "sProcessing":     "<%=LanguageUtil.get(bundle, "api.view.datatable.sProcessing")%>",
                "sLengthMenu":     "<%=LanguageUtil.get(bundle, "api.view.datatable.sLengthMenu")%>",
                "sZeroRecords":    "<%=LanguageUtil.get(bundle, "api.view.datatable.sZeroRecords")%>",
                "sEmptyTable":     "<%=LanguageUtil.get(bundle, "api.view.datatable.sEmptyTable")%>",
                "sInfo":           "<%=LanguageUtil.get(bundle, "api.view.datatable.sInfo")%>",
                "sInfoEmpty":      "<%=LanguageUtil.get(bundle, "api.view.datatable.sInfoEmpty")%>",
                "sInfoFiltered":   "<%=LanguageUtil.get(bundle, "api.view.datatable.sInfoFiltered")%>",
                "sInfoPostFix":    "<%=LanguageUtil.get(bundle, "api.view.datatable.sInfoPostFix")%>",
                "sSearch":         "<%=LanguageUtil.get(bundle, "api.view.datatable.sSearch")%>",
                "sUrl":            "<%=LanguageUtil.get(bundle, "api.view.datatable.sUrl")%>",
                "sInfoThousands":  "<%=LanguageUtil.get(bundle, "api.view.datatable.sInfoThousands")%>,",
                "sLoadingRecords": "<%=LanguageUtil.get(bundle, "api.view.datatable.sLoadingRecords")%>",
                "oPaginate": {
                    "sFirst":    "<%=LanguageUtil.get(bundle, "api.view.datatable.sFirst")%>",
                    "sLast":     "<%=LanguageUtil.get(bundle, "api.view.datatable.sLast")%>",
                    "sNext":     "<%=LanguageUtil.get(bundle, "api.view.datatable.sNext")%>",
                    "sPrevious": "<%=LanguageUtil.get(bundle, "api.view.datatable.sPrevious")%>"
                },
                "oAria": {
                    "sSortAscending":  "<%=LanguageUtil.get(bundle, "api.view.datatable.sSortAscending")%>",
                    "sSortDescending": "<%=LanguageUtil.get(bundle, "api.view.datatable.sSortDescending")%>"
                },
                "buttons": {
                    "copy": "<%=LanguageUtil.get(bundle, "api.view.datatable.copy")%>",
                    "colvis": "<%=LanguageUtil.get(bundle, "api.view.datatable.colvis")%>"
                }
            }
        } );

        tabla_error.draw();

        $.fn.dataTable.ext.search.push(
                			function( settings, data, dataIndex ) {
                			    if(settings.sTableId != "table_audit_error") return true;

                				if (document.querySelector("input[name='inpSearchActive']:checked").value  == "TODOS") return true;

                				if (document.querySelector("input[name='inpSearchActive']:checked").value  == "SIN_PROCESAR" && data[4] == "No") return true;
                				if (document.querySelector("input[name='inpSearchActive']:checked").value  == "PROCESADOS" && data[4] == "Si") return true;



                				return false;
                			}
                		);

    } );


    function sendApiSub(){
        $.ajax({
            type: 'POST',
            url: '<%= sendApiPubSubURL %>',
            success: function (data){
                console.log("iniciada escucha api/sub");
            }
        });
    }


    //llamada al servicio para cambiar de estado
    function changeState(_id){
        //llamada ajax cambiar estado y redibujar datatable o cambiar data del json en el local¿?


    $.ajax({
                type: 'POST',
                url: '<%=changeStateEventErrorURL%>',
                data: {
                    idTemporal : _id
                },
                success: function (data){
                    console.log("Change state");
                    tabla_error.ajax.reload();

                }
            });

    }
</script>