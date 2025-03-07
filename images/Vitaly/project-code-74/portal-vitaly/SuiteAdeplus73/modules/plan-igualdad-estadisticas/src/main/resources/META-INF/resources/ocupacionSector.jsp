<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="ocupacion"/>

<div class="card">
    <div class="card-header" id="heading${seccionId}">
        <h2 class="mb-0">
            <button class="btn btn-link btn-block text-left butonPrentCollapsed" type="button" data-toggle="collapse"
                    data-target="#collapse${seccionId}" aria-expanded="false" aria-controls="collapseOne">
                <liferay-ui:message key="estadisticas.${seccionId}" />
            </button>
        </h2>
    </div>
    <div id="collapse${seccionId}" class="collapse" aria-labelledby="heading${seccionId}" data-parent="#cuestionario">
        <form id="estadistica-${seccionId}">
            <div class="card-body" id="seccion-${seccionId}">

                <div id="tabla_${seccionId}_wrapper" class="dataTables_wrapper no-footer mb-4">
                    <div class="capaDatosTabla">
                        <div class="dt-info">
                            <label><liferay-ui:message key="estadistica.ocupacion.title" /></label>
                        </div>
                        <c:if test="<%= hasRole %>">
                            <div class="dt-buttons">
                                <button class="dt-button buttons-addRow" aria-controls="tabla_${seccionId}" type="button">
                                    <span><liferay-ui:message key="organizacion.tabla.annadir" /></span>
                                </button>
                            </div>
                        </c:if>
                    </div>
                    <table id="tabla_${seccionId}" class="display dataTable no-footer dtr-inline scrollable"
                           style="width: 100%;" aria-describedby="tabla_${seccionId}_info" role="grid"
                           data-delete-title="<liferay-ui:message key="estadistica.delete.ocupacion"/>"
                           data-edit-title="<liferay-ui:message key="estadistica.edit.ocupacion"/>">
                        <thead>
                            <tr>
                                <th data-id="cnae" data-requiered="true"><liferay-ui:message key="estadistica.ocupacion.cnae"/></th>
                                <th data-id="hombres" data-requiered="true"><liferay-ui:message key="estadisticas.hombres"/></th>
                                <th data-id="mujeres" data-requiered="true"><liferay-ui:message key="estadisticas.mujeres"/></th>
                                <th data-id="total" data-requiered="true"><liferay-ui:message key="estadistica.ocupacion.total"/></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>

                <c:if test="<%= hasRole %>">
                    <div class="row align-items-start mb-4">
                        <div class="col-md-12 col-12">
                            <div class="card">
                                <div class="card-header" id="headingAyuda${seccionId}">
                                    <h2 class="mb-0">
                                        <button class="btn btn-link btn-block text-left pl-3" type="button" data-toggle="collapse"
                                                data-target="#collapseAyuda${seccionId}" aria-expanded="false">
                                            <span><i class="icon-question-sign"></i> <liferay-ui:message key="estadisticas.ayuda"/></span>
                                        </button>
                                    </h2>
                                </div>
                                <div id="collapseAyuda${seccionId}" class="collapse" aria-labelledby="headingAyuda${seccionId}">
                                    <div class="p-3 alert-secondary" role="alert">
                                        <liferay-ui:message key="estadistica.${seccionId}.tabla.info"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>

                <div class="row align-items-start" id="charts"></div>

                <div class="row align-items-start mb-4">
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario.estadistica"/></label>
                        <textarea name="textarea_${seccionId}_comentario" id="textarea_${seccionId}_comentario"></textarea>
                    </div>
                </div>

                <div class="row align-items-start mb-4">
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario"/></label>
                        <textarea id="textarea_${seccionId}" name="textarea_${seccionId}"></textarea>
                    </div>
                </div>

            </div>

            <%-- EDIT MODAL --%>
            <div class="modal show hide" id="rowEditModal">
                <div class="modal-backdrop show"></div>
                <div class="modal-dialog center">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="edit-modal-title"></h5>
                            <button type="button" class="close hide-modal">
                                <span aria-hidden="true">x</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="prv-form"></div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn btn-outline-primary mr-4 hide-modal btn-primary" type="button">
                                <span class="lfr-btn-label"><liferay-ui:message key="organizacion.cancelar" /></span>
                            </button>
                            <button class="btn btn btn-primary btn-primary prv-save-modal" type="button">
                                <span class="lfr-btn-label"><liferay-ui:message key="organizacion.guardar" /></span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <%-- DELETE MODAL --%>
            <div class="modal show hide" id="rowDeleteModal">
                <div class="modal-backdrop show"></div>
                <div class="modal-dialog center">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="delete-modal-title"></h5>
                            <button type="button" class="close hide-modal">
                                <span aria-hidden="true">x</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="prv-form">
                                <p><liferay-ui:message key="organizacion.tabla.confirmar"/></p>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn btn-outline-primary mr-4 hide-modal btn-primary" type="button">
                                <span class="lfr-btn-label">Cancelar</span>
                            </button>
                            <button class="btn btn btn-primary btn-primary prv-delete-modal" type="button">
                                <span class="lfr-btn-label">Aceptar</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

        </form>
    </div>
</div>

<portlet:resourceURL id="findOcupaciones" var="findOcupacionesURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>
<script>

    $(document).ready( function () {

        $('#tabla_${seccionId}').on('preXhr.dt', function ( e, settings, data ) {
            $("#spnCargando").hide();
            $("#tabla_${seccionId}").show();
        }).DataTable({
            dom: '',
            "ajax": {
                "url": "<%= findOcupacionesURL %>",
                "dataSrc": function ( json ) {

                    createEditor("${seccionId}", json.valoracion, saveOcupacionSector);
                    createEditor("${seccionId}_comentario", json.comentario, saveOcupacionSector);

                    for (var key in json.data) {
                        if (Object.keys( json.data[key] ).length != 0) {
                            var hombres = parseInt(json.data[key].hombres);
                            var mujeres = parseInt(json.data[key].mujeres);
                            var cnae = json.data[key].cnae;
                            createOcupacionSectorChart(cnae, cnae, [hombres, mujeres]);
                        } else {
                            return [];
                        }
                    }

                    return json.data;
                }
            },
            "columns": [
                {"data" : "cnae"},
                {"data" : "hombres"},
                {"data" : "mujeres"},
                {"data" : "total"},
                {"data" : ""}
            ],
            "createdRow": function( row, data, dataIndex ) {
                  $(row).attr('data-chart', data.cnae);
            },
            "columnDefs": [
                {
                      "targets"  : [4],
                      "orderable": false,
                      "visible": <%= hasRole %>,
                      "render": function (data, type, row) {
                                    <c:choose>
                                        <c:when test="<%= hasRole %>">
                                            return  '<div class="d-flex justify-content-end">' +
                                                        '<a href="javascript:void(0)" class="ico-acciones-tabla prv-edit-row"><span class="icon-pencil"></span></a>' +
                                                        '<a href="javascript:void(0)" class="ico-acciones-tabla prv-remove-row"><span class="icon-trash"></span></a>' +
                                                    '</div>';
                                        </c:when>
                                        <c:otherwise>
                                            return "";
                                        </c:otherwise>
                                    </c:choose>
                                }
                }
            ],
            "initComplete": function( settings, json ) {

                editRows = document.querySelectorAll('.prv-edit-row');
                deleteRows = document.querySelectorAll('.prv-remove-row');

                addRowsFunction();
                editRowsFunction();
                deleteRowsFunction();

                saveModalsFunction();
                deleteModalsFunction();
                hideModalFunction();
            },
			"language": {
				"sEmptyTable":     "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sEmptyTable")%>",
			}
        });

    });

    function saveOcupacionSector(updateTable) {
        var table = tableNotLastToJson("${seccionId}");
        var json = JSON.parse(table);
            json["valoracion"] = CKEDITOR.instances.textarea_${seccionId}.getData();
            json["comentario"] = CKEDITOR.instances.textarea_${seccionId}_comentario.getData();
       
		saveDataEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.OCUPACION_SECTOR %>');
		if(updateTable == '0'){
			$('#prv-loading-container').addClass('d-flex');
			var array=[];
			if($('#tabla_ocupacion tbody tr').length>0){
				var temp = 2000;
				for(var i=0; i<$( "#seccion-ocupacion #charts .chartjs-render-monitor").length; i++){
					createImageOcupacionSector(i, temp);
					temp = temp +2000;
				}

			}else{
				seLlamoFuncionCanvas=true;
				saveImgCanvas('<%= PlanIgualdadEstadisticasPortletKeys.OCUPACION_SECTOR %>', '' , '1', 'graphic0', '', '0');
			}
		}

    }
    
    function createImageOcupacionSector(i, temp){
     	seLlamoFuncionCanvas=true;
    	setTimeout(function() { 
    		html2canvas($( "#seccion-ocupacion #charts .chartjs-render-monitor")[i]).then(function (canvas){
 				saveImgCanvas('<%= PlanIgualdadEstadisticasPortletKeys.OCUPACION_SECTOR %>', canvas.toDataURL("image/jpeg"), '0', 'graphic0', i+1, $('#tabla_ocupacion tbody tr').length);
   			 });
    	}, temp);
    }

    function createOcupacionSectorChart(id, title, data) {
        var result = [];
        var total = data.reduce((sum, a) => sum + a, 0);
        data.forEach(function (item, index) {
            result.push(item);
        });

        var pie = {
            datasets: [{
                data: result,
                backgroundColor: ['<%= PlanIgualdadEstadisticasPortletKeys.COLOR_HOMBRES %>', '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_MUJERES %>']
            }],
            labels: ['<liferay-ui:message key="estadisticas.hombres"/>', '<liferay-ui:message key="estadisticas.mujeres"/>']
        };

        var config = pieChartConfig(pie, title, "%", total);
        var chart = createChart(id, config);
    }

</script>
<script src="<%=request.getContextPath()%>/js/tables-infinity.js"></script>