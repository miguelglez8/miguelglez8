<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="promocion"/>

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
                                <label><liferay-ui:message key="estadistica.promocion.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}" class="display" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th><liferay-ui:message key="estadisticas.promocion.nTotal"/></th>
                                    <th data-id="nMujeres"><liferay-ui:message key="estadisticas.promocion.nMujeres"/></th>
                                    <th data-id="nHombres"><liferay-ui:message key="estadisticas.promocion.nHombres"/></th>
                                    <th><liferay-ui:message key="estadisticas.promocion.pMujeres"/></th>
                                    <th><liferay-ui:message key="estadisticas.promocion.pHombres"/></th>
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
                        <canvas id="estadistica_${seccionId}_promocion"></canvas>
                    </div>
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario.estadistica"/></label>
                        <textarea name="textarea_${seccionId}_comentario_promocion" id="textarea_${seccionId}_comentario_promocion"></textarea>
                    </div>
                    <div class="col-12 col-md-6">
                        <canvas id="estadistica_${seccionId}_personas"></canvas>
                    </div>
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario.estadistica"/></label>
                        <textarea name="textarea_${seccionId}_comentario_personas" id="textarea_${seccionId}_comentario_personas"></textarea>
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

<portlet:resourceURL id="findPromocion" var="findPromocionURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>
<script>
	var updateTablePromocion ="1";
    $(document).ready( function () {
    	$('[data-toggle="collapse"]').on("click", function () {
    		if(updateTablePromocion=="0"){
    			$('#prv-loading-container').addClass('d-flex');
    			seLlamoFuncionCanvas=true;
        		html2canvas(document.getElementById("estadistica_promocion_promocion")).then(function (canvas){
					saveImgCanvas('<%= PlanIgualdadEstadisticasPortletKeys.PROMOCION %>', canvas.toDataURL("image/jpeg"), '0', 'graphic15.1', 1, 0);
	    		});
        		
        		html2canvas(document.getElementById("estadistica_promocion_personas")).then(function (canvas){
    				saveImgCanvas('<%= PlanIgualdadEstadisticasPortletKeys.PROMOCION %>', canvas.toDataURL("image/jpeg"), '0', 'graphic15.2', 1, 1);
        		});
   	    		updateTablePromocion="1";
    		}
        });
    	
        $('#tabla_${seccionId}').on('preXhr.dt', function ( e, settings, data ) {
            $("#tabla_${seccionId}").show();
        }).DataTable({
            dom: "",
            "ajax": {
                "url": "<%= findPromocionURL %>",
                "dataSrc": function ( json ) {
                    return json.data;
                }
            },
            "columns": [
                {"data" : "promocion"},
                {"data" : "promocion", className: "text-right"},
                {"data" : "nMujeres", className: "text-right"},
                {"data" : "nHombres", className: "text-right"},
                {"data" : "promocion", className: "text-right"},
                {"data" : "promocion", className: "text-right"}
            ],
            "columnDefs": [
                {
                    "targets"  : [0],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<liferay-ui:message key="estadisticas.promocion.PRO"/>';
                              }
                },
                {
                    "targets"  : [1],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<span>0</span>';
                              }
                },
                {
                    "targets"  : [2],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<input type="number" class="promocion-field rounded text-right" min=0 value="' + data + '" />';
                              }
                },
                {
                    "targets"  : [3],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<input type="number" class="promocion-field rounded text-right" min=0 value="' + data + '" />';
                              }
                },
                {
                    "targets"  : [4],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<span>0%</span>';
                              }
                },
                {
                    "targets"  : [5],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<span>0%</span>';
                              }
                }
            ],
            "initComplete": function(settings, json) {

                <c:if test="<%= !hasRole %>">
                    $("#seccion-${seccionId} :input").prop("disabled", true);
                </c:if>

                var totalHombres = json.totalHombres;
                var totalMujeres = json.totalMujeres;

                initPromocion(totalMujeres, totalHombres);
                createEditor("${seccionId}", json.valoracion, savePromocion);
                createEditor("${seccionId}_comentario_promocion", json.comentarioPromocion, savePromocion);
                createEditor("${seccionId}_comentario_personas", json.comentarioPersonas, savePromocion);

            }
        });

    });

    function initPromocion(totalMujeres, totalHombres) {

        var labelsPromocion = ['<liferay-ui:message key="estadisticas.promocion.mujeresPromocionadas"/>',
                               '<liferay-ui:message key="estadisticas.promocion.hombresPromocionados"/>',
                               '<liferay-ui:message key="estadisticas.promocion.noPromocionadas"/>'];
        var labelsPersonas = ['<liferay-ui:message key="estadisticas.promocion.mujeresPromocionadas"/>',
                              '<liferay-ui:message key="estadisticas.promocion.hombresPromocionados"/>'];
        var colorsPromocion= ['<%= PlanIgualdadEstadisticasPortletKeys.COLOR_MUJERES %>',
                              '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_HOMBRES %>',
                              '#3e95cd'];
        var colorsPersonas = ['<%= PlanIgualdadEstadisticasPortletKeys.COLOR_MUJERES %>',
                              '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_HOMBRES %>'];
        var titlePromocion = '<liferay-ui:message key="estadisticas.promocion.promocion.title"/>';
        var titlePersonas = '<liferay-ui:message key="estadisticas.promocion.personas.title"/>';

        $('.promocion-field').on('input', function (e) {
            if(this.value < 0 || this.value == '')  this.value='';

            var row = $("#tabla_${seccionId} tbody tr:first");
            var nMujeres = row.find("td:eq(2) input").val();
            var nHombres = row.find("td:eq(3) input").val();
            if (parseInt(nMujeres) > parseInt(totalMujeres)) this.value='';
            if (parseInt(nHombres) > parseInt(totalHombres)) this.value='';

            calculateTablePromocion("${seccionId}", totalMujeres, totalHombres)
            createPromocionChart("${seccionId}", labelsPromocion, labelsPersonas, colorsPromocion, colorsPersonas, totalMujeres, totalHombres, titlePromocion, titlePersonas);
            savePromocion();
            updateTablePromocion="0"
        });

        calculateTablePromocion("${seccionId}", totalMujeres, totalHombres)
        createPromocionChart("${seccionId}", labelsPromocion, labelsPersonas, colorsPromocion, colorsPersonas, totalMujeres, totalHombres, titlePromocion, titlePersonas);

    }

    function savePromocion() {
        var table = tableObjectToJson("${seccionId}", {"promocion": "PRO"});
        var json = JSON.parse(table);
            json["valoracion"] =  CKEDITOR.instances.textarea_${seccionId}.getData();
            json["comentarioPromocion"] =  CKEDITOR.instances.textarea_${seccionId}_comentario_promocion.getData();
            json["comentarioPersonas"] =  CKEDITOR.instances.textarea_${seccionId}_comentario_personas.getData();

        saveDataEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.PROMOCION %>');
    }

</script>