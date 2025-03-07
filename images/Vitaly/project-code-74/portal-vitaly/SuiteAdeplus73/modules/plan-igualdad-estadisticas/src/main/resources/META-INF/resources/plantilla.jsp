<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="plantilla"/>

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
                    <div class="col-12 col-md-8">
                        <div class="capaDatosTabla">
                        	<div class="dt-info">
                        		<label><liferay-ui:message key="estadistica.plantilla.table.title"/></label>
                        	</div>
                        </div>

                        <div id="spnCargando" class="loading-animation"></div>
                        <table id="tabla_${seccionId}" class="display" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th><liferay-ui:message key="estadistica.plantilla.sexo"/></th>
                                    <th><liferay-ui:message key="estadistica.plantilla.personas"/></th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>

                <c:if test="<%= hasRole %>">
                    <div class="row d-flex justify-content-center mb-4">
                        <div class="col-md-8 col-12">
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
                    <div class="col-12 col-md-6" id="canvas${seccionId}">
                        <canvas id="estadistica_${seccionId}"></canvas>
                    </div>
                </div>

                <div class="row align-items-start mb-4">
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario.estadistica"/></label>
                        <textarea name="textarea_${seccionId}_comentario" id="textarea_${seccionId}_comentario"></textarea>
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

<portlet:resourceURL id="findPlantillas" var="findPlantillasURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>
<script>

    $(document).ready( function () {
    	
    	$('[data-target="#collapseplantilla"]').on("click", function(){
    		if(plantillaSave==false){
    			setTimeout(function() { 
        			if($('[data-target="#collapseplantilla"]').attr('aria-expanded') == 'true'){
        				$('#prv-loading-container').addClass('d-flex');
                        seLlamoFuncionCanvas=true;
        				html2canvas(document.getElementById("canvasplantilla")).then(function (canvas){
        					saveImgCanvas('<%= PlanIgualdadEstadisticasPortletKeys.PLANTILLA %>', canvas.toDataURL("image/jpeg"), '0', 'graphic1', 1, 1);
        	    		});
        				plantillaSave=true;
        			}
        		}, 2000);
    		}
    		
    	});
		
        $('#tabla_${seccionId}').on('preXhr.dt', function ( e, settings, data ) {
            $("#spnCargando").hide();
            $("#tabla_${seccionId}").show();
        }).DataTable({
            dom: '',
            "ajax": {
                "url": "<%= findPlantillasURL %>",
                "dataSrc": function ( json ) {
                    return json.data;
                }
            },
            "ordering": false,
            "columns": [
                {"data" : "plantilla"},
                {"data" : "personas"}
            ],
            "initComplete": function(settings, json) {

                var labelHombres = '<liferay-ui:message key="estadisticas.hombres"/>';
                var labelMujeres = '<liferay-ui:message key="estadisticas.mujeres"/>';
                var colorHombres = '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_HOMBRES %>';
                var colorMujeres = '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_MUJERES %>';
                var title = '<liferay-ui:message key="estadisticas.plantilla"/>';

                createEditor("${seccionId}", json.valoracion, savePlantilla);
                createEditor("${seccionId}_comentario", json.comentario, savePlantilla);
                createPlantillaChart("${seccionId}", json.data, labelHombres, labelMujeres, colorHombres, colorMujeres, title)
            	
                tableTotals("${seccionId}");
            }
        });

    });

    function savePlantilla() {
        var json = {};
            json["valoracion"] = CKEDITOR.instances.textarea_${seccionId}.getData();
            json["comentario"] = CKEDITOR.instances.textarea_${seccionId}_comentario.getData();

        saveDataEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.PLANTILLA %>');
        
    }

</script>