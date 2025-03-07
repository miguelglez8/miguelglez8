<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="edad"/>

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
                    <div class="col-12 col-md-12">
                        <div class="capaDatosTabla">
                            <div class="dt-info">
                                <label><liferay-ui:message key="estadistica.edad.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}" class="display" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th data-id="id"><liferay-ui:message key="estadisticas.edad.personas"/></th>
                                    <th data-id="hombres"><liferay-ui:message key="estadisticas.edad.hombres"/></th>
                                    <th data-id="mujeres"><liferay-ui:message key="estadisticas.edad.mujeres"/></th>
                                    <th></th>
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

<portlet:resourceURL id="findEdad" var="findEdadURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>
<script>
var updateTableEdad ="1";
    $(document).ready( function () {
    	$('[data-toggle="collapse"]').on("click", function () {
    		if(updateTableEdad=="0"){
				$('#prv-loading-container').addClass('d-flex');
                seLlamoFuncionCanvas=true;
	    		html2canvas(document.getElementById("estadistica_edad")).then(function (canvas){
	    			saveImgCanvas('<%= PlanIgualdadEstadisticasPortletKeys.EDAD %>', canvas.toDataURL("image/jpeg"), '0', 'graphic3', 1, 1);
		    	});
   	    		updateTableEdad="1";
    		}
        });
    	
        $('#tabla_${seccionId}').on('preXhr.dt', function ( e, settings, data ) {
            $("#tabla_${seccionId}").show();
        }).DataTable({
            dom: '',
            "ajax": {
                "url": "<%= findEdadURL %>",
                "dataSrc": function ( json ) {
                    return json.data;
                }
            },
            "columns": [
                {"data" : "id"},
                {"data" : "hombres"},
                {"data" : "mujeres"},
                {"data" : "", className: "actions"}
            ],
            "columnDefs": [
                {
                   "targets"  : [0],
                   "orderable": true,
                   "render": function (data, type, row) {
                                 return getFranjasEdad(data);
                             }
                },
                {
                   "targets"  : [1],
                   "orderable": false,
                   "render": function (data, type, row) {
                                return  '<input type="number" class="rounded text-right edad-field" min=0 max=100 value="' + data + '" />';
                             }
                },
                {
                   "targets"  : [2],
                   "orderable": false,
                   "render": function (data, type, row) {
                                return  '<input type="number" class="rounded text-right edad-field" min=0 max=100 value="' + data + '" />';
                             }
                },
                {
                   "targets"  : [3],
                   "orderable": false,
                   "visible": <%= hasRole %>,
                   "render": function (data, type, row) {
                                <c:choose>
                                    <c:when test="<%= hasRole %>">
                                        return  '<span class="ico-acciones-tabla refresh-edad"><span class="icon-refresh"></span></span>';
                                    </c:when>
                                    <c:otherwise>
                                        return "";
                                    </c:otherwise>
                                </c:choose>
                             }
                }
            ],
            "initComplete": function(settings, json) {

                <c:if test="<%= !hasRole %>">
                    $("#seccion-${seccionId} :input").prop("disabled", true);
                </c:if>

                createEditor("${seccionId}", json.valoracion, saveEdad);
                createEditor("${seccionId}_comentario", json.comentario, saveEdad);

                var labelHombres = '<liferay-ui:message key="estadisticas.hombres"/>';
                var labelMujeres = '<liferay-ui:message key="estadisticas.mujeres"/>';
                var colorHombres = '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_HOMBRES %>';
                var colorMujeres = '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_MUJERES %>';
                var title = '<liferay-ui:message key="estadisticas.edad.rango"/>';

                $('.edad-field').on('input', function (e) {

                    if(this.value > 100)                      this.value='100';
                    if(this.value < 0 || this.value == '')    this.value='0';

                    var value = $(this).val();
                    var input = $(this).closest('tr').find("input").not(this).first();
                        input.val(100 - value);
                    saveEdad();
                    updateTableEdad = "0";
                    createEdadChart("${seccionId}", labelHombres, labelMujeres, colorHombres, colorMujeres, title);
                });

                $('.refresh-edad').on('click', function (e) {
                    var inputs = $(this).closest('tr').find("input");
                        inputs.each( function() { $(this).val('0'); });

                    saveEdad();
                    createEdadChart("${seccionId}", labelHombres, labelMujeres, colorHombres, colorMujeres, title);
                });

                createEdadChart("${seccionId}", labelHombres, labelMujeres, colorHombres, colorMujeres, title);
            }
        });

    });

    function saveEdad() {
        var table = tableNotLastToJson("${seccionId}");
        var json = JSON.parse(table);
            json["valoracion"] = CKEDITOR.instances.textarea_${seccionId}.getData();
            json["comentario"] = CKEDITOR.instances.textarea_${seccionId}_comentario.getData();
		
		saveDataEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.EDAD %>');
    }
  
    function getFranjasEdad(franja) {
        if (franja == "0020") return '<span data-id="0020"><liferay-ui:message key="estadisticas.edad.0020"/></span>';
        if (franja == "2025") return '<span data-id="2025"><liferay-ui:message key="estadisticas.edad.2025"/></span>';
        if (franja == "2530") return '<span data-id="2530"><liferay-ui:message key="estadisticas.edad.2530"/></span>';
        if (franja == "3035") return '<span data-id="3035"><liferay-ui:message key="estadisticas.edad.3035"/></span>';
        if (franja == "3540") return '<span data-id="3540"><liferay-ui:message key="estadisticas.edad.3540"/></span>';
        if (franja == "4045") return '<span data-id="4045"><liferay-ui:message key="estadisticas.edad.4045"/></span>';
        if (franja == "4550") return '<span data-id="4550"><liferay-ui:message key="estadisticas.edad.4550"/></span>';
        if (franja == "5055") return '<span data-id="5055"><liferay-ui:message key="estadisticas.edad.5055"/></span>';
        if (franja == "5560") return '<span data-id="5560"><liferay-ui:message key="estadisticas.edad.5560"/></span>';
        if (franja == "6000") return '<span data-id="6000"><liferay-ui:message key="estadisticas.edad.6000"/></span>';
        else return franja;
    }

</script>