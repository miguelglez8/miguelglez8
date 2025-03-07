<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="formacion"/>

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

                 <div class="row d-flex justify-content-center mb-4">
                    <div class="col-md-12">
                        <div class="capaDatosTabla">
                            <div class="dt-info">
                                <label><liferay-ui:message key="estadistica.formacion.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}" class="display" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th data-id="hMujeres"><liferay-ui:message key="estadisticas.formacion.hMujeres"/></th>
                                    <th data-id="hHombres"><liferay-ui:message key="estadisticas.formacion.hHombres"/></th>
                                    <th data-id="nMujeres"><liferay-ui:message key="estadisticas.formacion.nMujeres"/></th>
                                    <th data-id="nHombres"><liferay-ui:message key="estadisticas.formacion.nHombres"/></th>
                                    <th><liferay-ui:message key="estadisticas.formacion.mMujeres"/></th>
                                    <th><liferay-ui:message key="estadisticas.formacion.mHombres"/></th>
                                    <th><liferay-ui:message key="estadisticas.formacion.hTotal"/></th>
                                    <th><liferay-ui:message key="estadisticas.formacion.mTotal"/></th>
                                </tr>
                            </thead>
                        </table>
                    </div>
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

                <div class="row d-flex justify-content-center mb-4">
                    <div class="col-12 col-md-6">
                        <canvas id="estadistica_${seccionId}_formacion"></canvas>
                    </div>
                    <div class="form-group col-md-12 col-12 border-separate">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario.estadistica"/></label>
                        <textarea name="textarea_${seccionId}_comentario_formacion" id="textarea_${seccionId}_comentario_formacion"></textarea>
                    </div>
                    <div class="col-12 col-md-6">
                        <canvas id="estadistica_${seccionId}_duracion"></canvas>
                    </div>
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario.estadistica"/></label>
                        <textarea name="textarea_${seccionId}_comentario_duracion" id="textarea_${seccionId}_comentario_duracion"></textarea>
                    </div>
                </div>

                <div class="row align-items-start mb-4">
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario"/></label>
                        <textarea name="textarea_${seccionId}" id="textarea_${seccionId}"></textarea>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>

<portlet:resourceURL id="findFormacion" var="findFormacionURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>
<script>
var updateTableFormacion ="1";
    $(document).ready( function () {
    	$('[data-toggle="collapse"]').on("click", function () {
    		if(updateTableFormacion=="0"){
				$('#prv-loading-container').addClass('d-flex');
   	    		seLlamoFuncionCanvas=true;
   	    		html2canvas(document.getElementById("estadistica_formacion_formacion")).then(function (canvas){
   	    			saveImgCanvas('<%= PlanIgualdadEstadisticasPortletKeys.FORMACION %>', canvas.toDataURL("image/jpeg"), '0', 'graphic14.1', 1, 2);
   		    	});
   	    		
   	    		html2canvas(document.getElementById("estadistica_formacion_duracion")).then(function (canvas){
   	    			saveImgCanvas('<%= PlanIgualdadEstadisticasPortletKeys.FORMACION %>', canvas.toDataURL("image/jpeg"), '0', 'graphic14.2', 1, 0);
   		    	});
   	    		updateTableFormacion="1";
    		}
        });
    	
        $('#tabla_${seccionId}').on('preXhr.dt', function ( e, settings, data ) {
            $("#tabla_${seccionId}").show();
        }).DataTable({
            dom: "",
            "ajax": {
                "url": "<%= findFormacionURL %>",
                "dataSrc": function ( json ) {
                    return json.data;
                }
            },
            "columns": [
                {"data" : "formacion"},
                {"data" : "hMujeres", className: "text-right"},
                {"data" : "hHombres", className: "text-right"},
                {"data" : "nMujeres", className: "text-right"},
                {"data" : "nHombres", className: "text-right"},
                {"data" : "formacion", className: "text-right"},
                {"data" : "formacion", className: "text-right"},
                {"data" : "formacion", className: "text-right"},
                {"data" : "formacion", className: "text-right"}
            ],
            "columnDefs": [
                {
                    "targets"  : [0],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<liferay-ui:message key="estadisticas.formacion.FIO"/>';
                              }
                },
                {
                    "targets"  : [1],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<input type="number" class="formacion-field rounded text-right" min=0 value="' + data + '" />';
                              }
                },
                {
                    "targets"  : [2],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<input type="number" class="formacion-field rounded text-right" min=0 value="' + data + '" />';
                              }
                },
                {
                    "targets"  : [3],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<input type="number" class="formacion-field rounded text-right" min=0 value="' + data + '" />';
                              }
                },
                {
                    "targets"  : [4],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<input type="number" class="formacion-field rounded text-right" min=0 value="' + data + '" />';
                              }
                },
                {
                    "targets"  : [5],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<span>0</span>';
                              }
                },
                {
                    "targets"  : [6],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<span>0</span>';
                              }
                },
                {
                    "targets"  : [7],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<span>0</span>';
                              }
                },
                {
                    "targets"  : [8],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<span>0</span>';
                              }
                }
            ],
            "initComplete": function(settings, json) {

                <c:if test="<%= !hasRole %>">
                    $("#seccion-${seccionId} :input").prop("disabled", true);
                </c:if>

                var totalHombresFormacion = json.totalHombres;
                var totalMujeresFormacion = json.totalMujeres;

                initFormacion(totalMujeresFormacion, totalHombresFormacion);
                createEditor("${seccionId}", json.valoracion, saveFormacion);
                createEditor("${seccionId}_comentario_duracion", json.comentarioDuracion, saveFormacion);
                createEditor("${seccionId}_comentario_formacion", json.comentarioFormacion, saveFormacion);
            }
        });
    });

    function initFormacion(totalMujeresFormacion, totalHombresFormacion) {

        var labels = ['<liferay-ui:message key="estadisticas.formacion.pie.mujeresFormadas"/>',
                      '<liferay-ui:message key="estadisticas.formacion.pie.mujeresNoFormadas"/>',
                      '<liferay-ui:message key="estadisticas.formacion.pie.hombresFormados"/>',
                      '<liferay-ui:message key="estadisticas.formacion.pie.hombresNoFormados"/>'];
        var titlePie = '<liferay-ui:message key="estadisticas.formacion.pie.title"/>';

        var labelHombres = '<liferay-ui:message key="estadisticas.hombres"/>';
        var labelMujeres = '<liferay-ui:message key="estadisticas.mujeres"/>';
        var labelTotal = '<liferay-ui:message key="estadisticas.formacion.bar.total"/>';
        var colorHombres = '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_HOMBRES %>';
        var colorMujeres = '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_MUJERES %>';
        var colorTotal = '#3e95cd';
        var titleBar = '<liferay-ui:message key="estadisticas.formacion.bar.title"/>';

        var row = $("#tabla_${seccionId} tbody tr").first();
        var nMujeresFormadas = row.find("td:eq(3) input").val();
        var nHombresFormados = row.find("td:eq(4) input").val();
        
        if(nMujeresFormadas!=null && nMujeresFormadas > totalMujeresFormacion){
        	row.find("td:eq(3) input").val(totalMujeresFormacion);
        	row.find("td:eq(3) input").change();
        	nMujeresFormadas = totalMujeresFormacion;
        }
        
		if(nHombresFormados!=null && nHombresFormados > totalHombresFormacion){
			row.find("td:eq(4) input").val(totalHombresFormacion);
			row.find("td:eq(4) input").change();
			nHombresFormados = totalHombresFormacion;
        }
        $('.formacion-field').on('input', function (e) {
        	if(row.find("td:eq(3) input").val()!=null && row.find("td:eq(3) input").val() > totalMujeresFormacion){
            	row.find("td:eq(3) input").val(totalMujeresFormacion);
            	nMujeresFormadas = totalMujeresFormacion;
            }
            
    		if(row.find("td:eq(4) input").val()!=null && row.find("td:eq(4) input").val() > totalHombresFormacion){
    			row.find("td:eq(4) input").val(totalHombresFormacion);
    			nHombresFormados = totalHombresFormacion;
            }
    		
            if(this.value < 0 || this.value == '')  this.value='';
            calculateTableFormacion("${seccionId}");
            createFormacionBarChart("${seccionId}", labelHombres, labelMujeres, labelTotal, colorHombres, colorMujeres, colorTotal, titleBar);
            createFormacionPieChart("${seccionId}", labels, titlePie, totalMujeresFormacion, totalHombresFormacion);
            saveFormacion();
            updateTableFormacion="0";
        });

        calculateTableFormacion("${seccionId}");
        createFormacionBarChart("${seccionId}", labelHombres, labelMujeres, labelTotal, colorHombres, colorMujeres, colorTotal, titleBar);
        createFormacionPieChart("${seccionId}", labels, titlePie, totalMujeresFormacion, totalHombresFormacion);

    }

    function saveFormacion() {
        var table = tableObjectToJson("${seccionId}", {"formacion": "FIO"});
        var json = JSON.parse(table);
            json["valoracion"] =  CKEDITOR.instances.textarea_${seccionId}.getData();
            json["comentarioFormacion"] =  CKEDITOR.instances.textarea_${seccionId}_comentario_formacion.getData();
            json["comentarioDuracion"] =  CKEDITOR.instances.textarea_${seccionId}_comentario_duracion.getData();

        saveDataEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.FORMACION %>');
        
    }

</script>