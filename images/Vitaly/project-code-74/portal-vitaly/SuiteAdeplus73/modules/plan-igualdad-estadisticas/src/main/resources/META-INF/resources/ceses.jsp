<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="ceses"/>

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
                                <label><liferay-ui:message key="estadistica.ceses.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}" class="display" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th rowspan="2" class="key" data-id="causa" data-group="tipo"><liferay-ui:message key="estadisticas.ceses.causas"/></th>
                                    <th colspan="2"><liferay-ui:message key="estadisticas.mujeres"/></th>
                                    <th colspan="2"><liferay-ui:message key="estadisticas.hombres"/></th>
                                    <th colspan="2"><liferay-ui:message key="estadisticas.ceses.totalGeneral"/></th>
                                </tr>
                                <tr>
                                    <th class="key" data-id="mujeres"><liferay-ui:message key="estadisticas.ceses.nMujeres"/></th>
                                    <th><liferay-ui:message key="estadisticas.ceses.pMujeres"/></th>
                                    <th class="key" data-id="hombres"><liferay-ui:message key="estadisticas.ceses.nHombres"/></th>
                                    <th><liferay-ui:message key="estadisticas.ceses.pHombres"/></th>
                                    <th><liferay-ui:message key="estadisticas.ceses.nTotal"/></th>
                                    <th><liferay-ui:message key="estadisticas.ceses.pTotal"/></th>
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

<portlet:resourceURL id="findCeses" var="findCesesURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>
<script>
    $(document).ready( function () {
    	
        $('#tabla_${seccionId}').on('preXhr.dt', function ( e, settings, data ) {
            $("#tabla_${seccionId}").show();
        }).DataTable({
            dom: "",
            "ajax": {
                "url": "<%= findCesesURL %>",
                "dataSrc": function ( json ) {
                    return json.data;
                }
            },
            "columns": [
                {"data" : "causa", className: "value"},
                {"data" : "mujeres", className: "value text-right"},
                {"data" : "mujeres", className: "text-right"},
                {"data" : "hombres", className: "value text-right"},
                {"data" : "hombres", className: "text-right"},
                {"data" : "causa", className: "text-right"},
                {"data" : "causa", className: "text-right"}
            ],
            "columnDefs": [
                {
                    "targets"  : [0],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return getCausa(data, row.tipo);
                              }
                },
                {
                   "targets"  : [1],
                   "orderable": false,
                   "render": function (data, type, row) {
                                 var key = "mujeres_" + row.tipo;
                                 return '<input type="number" class="ceses-field rounded text-right" data-id="' + key + '" min=0 value="' + data + '" />';
                             }
                },
                {
                   "targets"  : [2],
                   "orderable": false,
                   "render": function (data, type, row) {
                                var total = parseInt(row.hombres) + parseInt(row.mujeres)
                                return '<span>' + calculatePercentage(row.mujeres, total) + '%</span>';
                             }
                },
                {
                   "targets"  : [3],
                   "orderable": false,
                   "render": function (data, type, row) {
                                 var key = "hombres_" + row.tipo;
                                 return '<input type="number" class="ceses-field rounded text-right" data-id="' + key + '" min=0 value="' + data + '" />';
                             }
                },
                {
                   "targets"  : [4],
                   "orderable": false,
                   "render": function (data, type, row) {
                                var total = parseInt(row.hombres) + parseInt(row.mujeres)
                                return '<span>' +  calculatePercentage(row.hombres, total) + '%</span>';
                             }
                },
                {
                   "targets"  : [5],
                   "orderable": false,
                   "render": function (data, type, row) {
                                  var total = parseInt(row.hombres) + parseInt(row.mujeres);
                                  return '<input type="number" disabled class="ceses-total rounded text-right" value="' + total + '" />';
                             }
                },
                {
                   "targets"  : [6],
                   "orderable": false,
                   "render": function (data, type, row) {
                                return '<span>100%</span>';
                             }
                },
            ],
            rowGroup: {
                dataSrc: "tipo",
                endRender: function ( rows, group ) {
                    $('#tabla_${seccionId} .group-start').remove();

                   return $('<tr/>')
                        .append('<td><liferay-ui:message key="estadisticas.ceses.TOTAL"/></td>')
                        .append('<td class="text-right"><input type="number" id="mujeres_' + group + '" disabled class="rounded text-right" value="0" /></td>')
                        .append('<td class="text-right">0%</td>')
                        .append('<td class="text-right"><input type="number" id="hombres_' + group + '" disabled class="rounded text-right" value="0" /></td>')
                        .append('<td class="text-right">0%</td>')
                        .append('<td class="text-right"><input type="number" id="total_' + group + '" disabled class="rounded text-right" value="0" /></td>')
                        .append('<td class="text-right">0%</td>')
                }
            },
            "initComplete": function(settings, json) {

                <c:if test="<%= !hasRole %>">
                    $("#seccion-${seccionId} :input").prop("disabled", true);
                </c:if>

                initCeses();
                createEditor("${seccionId}", json.valoracion, saveCeses);
                createEditor("${seccionId}_comentario", json.comentario, saveCeses);
            }
        });

    });

    function initCeses() {

        $('.ceses-field').on('input', function (e) {
            if(this.value < 0 || this.value == '')  this.value='';
            calculateTableCeses("${seccionId}", $(this));
            saveCeses();
        });

        calculateTableCeses("${seccionId}", null);
    }

    function saveCeses() {
        var table = tableKeyValueToJson("${seccionId}", "tipo");
        var json = JSON.parse(table);
            json["valoracion"] = CKEDITOR.instances.textarea_${seccionId}.getData();
            json["comentario"] = CKEDITOR.instances.textarea_${seccionId}_comentario.getData();

        saveDataEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.CESES %>');
        
    }
    
    function getCausa(causa, tipo) {
        if (causa == "DESPIDO")      return '<span data-id="DESPIDO" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.ceses.DESPIDO"/></span>';
        if (causa == "DIMISION")     return '<span data-id="DIMISION" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.ceses.DIMISION"/></span>';
        if (causa == "FIN_CONTRATO") return '<span data-id="FIN_CONTRATO" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.ceses.FIN_CONTRATO"/></span>';
        if (causa == "OTRAS")        return '<span data-id="OTRAS" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.ceses.OTRAS"/></span>';
        else return causa;
    }

</script>