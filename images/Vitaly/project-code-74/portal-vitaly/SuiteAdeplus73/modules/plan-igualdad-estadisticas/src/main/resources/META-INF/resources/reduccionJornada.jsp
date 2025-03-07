<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="reduccionJornada"/>

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
                                <label><liferay-ui:message key="estadistica.reduccionJornada.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}" class="display scrollable" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th data-id="puesto"><liferay-ui:message key="estadisticas.reduccionJornada.puesto"/></th>
                                    <th data-id="hombres"><liferay-ui:message key="estadisticas.reduccionJornada.nHombres"/></th>
                                    <th data-id="mujeres"><liferay-ui:message key="estadisticas.reduccionJornada.nMujeres"/></th>
                                    <th><liferay-ui:message key="estadisticas.reduccionJornada.total"/></th>
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
                    <div class="col-md-12 text-center">
                        <liferay-ui:message key="estadisticas.reduccionJornada.estadistica.title"/>
                    </div>
                    <div class="col-12 col-md-6">
                        <canvas id="estadistica_${seccionId}_plantillas"></canvas>
                    </div>
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario.estadistica"/></label>
                        <textarea name="textarea_${seccionId}_comentario_plantillas" id="textarea_${seccionId}_comentario_plantillas"></textarea>
                    </div>
                    <div class="col-12 col-md-6">
                        <canvas id="estadistica_${seccionId}_mujeres"></canvas>
                    </div>
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario.estadistica"/></label>
                        <textarea name="textarea_${seccionId}_comentario_mujeres" id="textarea_${seccionId}_comentario_mujeres"></textarea>
                    </div>
                    <div class="col-12 col-md-6">
                        <canvas id="estadistica_${seccionId}_hombres"></canvas>
                    </div>
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario.estadistica"/></label>
                        <textarea name="textarea_${seccionId}_comentario_hombres" id="textarea_${seccionId}_comentario_hombres"></textarea>
                    </div>
                </div>

                <div class="row align-items-start mb-4">
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario"/></label>
                        <textarea name="textarea_${seccionId}" id="textarea_${seccionId}"></textarea>
                    </div>
                </div>

                <div class="row align-items-start mb-4">
                    <div class="prv-form form-group col-md-6 col-6 required">
                        <fieldset>
                            <legend class="control-label"><liferay-ui:message key="estadisticas.aplicarMedidad"/></legend>
                            <div class="checksBorder">
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="medidasReduccionJornada" id="medidasReduccionJornada1" value="0" type="radio">
                                    <label class="form-check-label" for="medidasReduccionJornada1"><liferay-ui:message key="estadisticas.si"/></label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="medidasReduccionJornada" id="medidasReduccionJornada2" value="1" type="radio">
                                    <label class="form-check-label" for="medidasReduccionJornada2"><liferay-ui:message key="estadisticas.no"/></label>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>

<portlet:resourceURL id="findReduccionJornada" var="findReduccionJornadaURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>
<script>
	var updateTableReduccionJornada ="1";
    $(document).ready( function () {
    	
    	$('[data-toggle="collapse"]').on("click", function () {
    		if(updateTableReduccionJornada=="0"){
				$('#prv-loading-container').addClass('d-flex');
	    		seLlamoFuncionCanvas=true;
	    		html2canvas(document.getElementById("estadistica_reduccionJornada_plantillas")).then(function (canvas){
	    			saveImgCanvas('<%= PlanIgualdadEstadisticasPortletKeys.REDUCCION_JORNADA %>', canvas.toDataURL("image/jpeg"), '0', 'graphic8.1', 1, 3);
		    	});
	    		
	    		html2canvas(document.getElementById("estadistica_reduccionJornada_mujeres")).then(function (canvas){
	    			saveImgCanvas('<%= PlanIgualdadEstadisticasPortletKeys.REDUCCION_JORNADA %>', canvas.toDataURL("image/jpeg"), '0', 'graphic8.2', 1, 0);
		    	});
	    		
	    		html2canvas(document.getElementById("estadistica_reduccionJornada_hombres")).then(function (canvas){
	    			saveImgCanvas('<%= PlanIgualdadEstadisticasPortletKeys.REDUCCION_JORNADA %>', canvas.toDataURL("image/jpeg"), '0', 'graphic8.3', 1, 0);
		    	});
   	    		updateTableReduccionJornada="1";
    		}
        });
    	
        $('#tabla_${seccionId}').on('preXhr.dt', function ( e, settings, data ) {
            $("#tabla_${seccionId}").show();
        }).DataTable({
            dom: "",
            "language": {
                "sEmptyTable": "<liferay-ui:message key="planigualdad.view.datatable.sEmptyTable"/>",
            },
            "paging": false,
            "ajax": {
                "url": "<%= findReduccionJornadaURL %>",
                "dataSrc": function ( json ) {
                    return json.data;
                }
            },
            "columns": [
                {"data" : "puesto"},
                {"data" : "hombres", className: "text-right"},
                {"data" : "mujeres", className: "text-right"},
                {"data" : "puesto", className: "text-right"}
            ],
            "columnDefs": [
                {
                   "targets"  : [0],
                   "orderable": false,
                   "render": function (data, type, row) {
                                 return '<span data-id="' + data + '">' + puestos.find(puesto => puesto.id === data + "").nombre + '</span>';
                             }
                },
                {
                   "targets"  : [1],
                   "orderable": false,
                   "render": function (data, type, row) {
                                 return  '<input type="number" class="rounded text-right jornada-field" min=0 value="' + data + '" />';
                             }
                },
                {
                   "targets"  : [2],
                   "orderable": false,
                   "render": function (data, type, row) {
                                 return  '<input type="number" class="rounded text-right jornada-field" min=0 value="' + data + '" />';
                             }
                },
                {
                   "targets"  : [3],
                   "orderable": false,
                   "render": function (data, type, row) {
                                var total = parseInt(row.hombres) + parseInt(row.mujeres);
                                return  '<input type="number" disabled class="rounded text-right jornada-field" value="' + total + '" />';
                             }
                }
            ],
            "initComplete": function(settings, json) {

                <c:if test="<%= !hasRole %>">
                    $("#seccion-${seccionId} :input").prop("disabled", true);
                </c:if>

                var labelsHombres = ['<liferay-ui:message key="estadisticas.reduccionJornada.hombres.sinReduccion"/>',
                                     '<liferay-ui:message key="estadisticas.reduccionJornada.hombres.conReduccion"/>'];
                var labelsMujeres = ['<liferay-ui:message key="estadisticas.reduccionJornada.mujeres.sinReduccion"/>',
                                     '<liferay-ui:message key="estadisticas.reduccionJornada.mujeres.conReduccion"/>'];
                var labelsTotales = ['<liferay-ui:message key="estadisticas.reduccionJornada.totales.sinReduccion"/>',
                    '<liferay-ui:message key="estadisticas.reduccionJornada.totales.conReduccion"/>'];
                var colorHombres = '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_HOMBRES %>';
                var colorMujeres = '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_MUJERES %>';
                var titleHombres = '';
                var titleMujeres = '';
                var titleTotales = '';
                var totalHombres = json.totalHombres;
                var totalMujeres = json.totalMujeres;
                var totalPersonas = parseInt(totalHombres) + parseInt(totalMujeres);

                createEditor("${seccionId}", json.valoracion, saveReduccionJornada);
                createEditor("${seccionId}_comentario_plantillas", json.comentarioPlantillas, saveReduccionJornada);
                createEditor("${seccionId}_comentario_mujeres", json.comentarioMujeres, saveReduccionJornada);
                createEditor("${seccionId}_comentario_hombres", json.comentarioHombres, saveReduccionJornada);

                $('[name=medidasReduccionJornada]').on('change', function() {
                    saveReduccionJornada('1');
                });
                $('input:radio[name=medidasReduccionJornada]').filter('[value=' + json.medida + ']').prop('checked', true);

                $('.jornada-field').on('input', function (e) {
                    if(this.value < 0 || this.value == '')    this.value='';

                    var nMujeres = 0;
                    var nHombres = 0;
                    $("#tabla_${seccionId} tbody tr").each(function(index) {
                        var row = $(this);
                        nHombres = nHombres + parseInt(row.find("td:eq(1) input").val());
                        nMujeres = nMujeres + parseInt(row.find("td:eq(2) input").val());
                    });
                    if (totalHombres < nHombres) this.value= 0;
                    if (totalMujeres < nMujeres) this.value= 0;

                    calculateTableReduccionJornada($(this));
                    saveReduccionJornada();
                    updateTableReduccionJornada = "0";
                    createReduccionJornadaChart("${seccionId}", labelsHombres, labelsMujeres, labelsTotales, colorHombres, colorMujeres, totalMujeres, totalHombres, totalPersonas, titleMujeres, titleHombres, titleTotales);
                });

                createReduccionJornadaChart("${seccionId}", labelsHombres, labelsMujeres, labelsTotales, colorHombres, colorMujeres, totalMujeres, totalHombres, totalPersonas, titleMujeres, titleHombres, titleTotales);
            }
        });

    });

    function saveReduccionJornada(changeInputRadio) {
        var table = tableNotLastToJson("${seccionId}");
        var json = JSON.parse(table);
            json["valoracion"] = CKEDITOR.instances.textarea_${seccionId}.getData();
            json["comentarioPlantillas"] = CKEDITOR.instances.textarea_${seccionId}_comentario_plantillas.getData();
            json["comentarioMujeres"] = CKEDITOR.instances.textarea_${seccionId}_comentario_mujeres.getData();
            json["comentarioHombres"] = CKEDITOR.instances.textarea_${seccionId}_comentario_hombres.getData();
            json["medida"] = $('input[name=medidasReduccionJornada]:checked').val();

        saveEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.REDUCCION_JORNADA %>', changeInputRadio);
    }

</script>