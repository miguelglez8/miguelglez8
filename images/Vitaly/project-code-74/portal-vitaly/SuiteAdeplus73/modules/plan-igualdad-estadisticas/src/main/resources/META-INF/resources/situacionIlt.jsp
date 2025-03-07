<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="situacionIlt"/>

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
                                <label><liferay-ui:message key="estadistica.situacionIlt.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}" class="display" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th data-id="situacion" data-group="tipo"></th>
                                    <th data-id="nMujeres"><liferay-ui:message key="estadisticas.situacionIlt.nMujeres"/></th>
                                    <th data-id="dMujeres"><liferay-ui:message key="estadisticas.situacionIlt.dMujeres"/></th>
                                    <th><liferay-ui:message key="estadisticas.situacionIlt.pMujeres"/></th>
                                    <th><liferay-ui:message key="estadisticas.situacionIlt.mMujeres"/></th>
                                    <th data-id="nHombres"><liferay-ui:message key="estadisticas.situacionIlt.nHombres"/></th>
                                    <th data-id="dHombres"><liferay-ui:message key="estadisticas.situacionIlt.dHombres"/></th>
                                    <th><liferay-ui:message key="estadisticas.situacionIlt.pHombres"/></th>
                                    <th><liferay-ui:message key="estadisticas.situacionIlt.mHombres"/></th>
                                    <th><liferay-ui:message key="estadisticas.situacionIlt.dTotal"/></th>
                                    <th><liferay-ui:message key="estadisticas.situacionIlt.pTotal"/></th>
                                    <th><liferay-ui:message key="estadisticas.situacionIlt.mTotal"/></th>
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

<portlet:resourceURL id="findSituacionIlt" var="findSituacionIltURL">
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
                "url": "<%= findSituacionIltURL %>",
                "dataSrc": function ( json ) {
                    return json.data;
                }
            },
            "columns": [
                {"data" : "situacion"},
                {"data" : "nMujeres", className: "text-right"},
                {"data" : "dMujeres", className: "text-right"},
                {"data" : "situacion", className: "text-right"},
                {"data" : "situacion", className: "text-right"},
                {"data" : "nHombres", className: "text-right"},
                {"data" : "dHombres", className: "text-right"},
                {"data" : "situacion", className: "text-right"},
                {"data" : "situacion", className: "text-right"},
                {"data" : "situacion", className: "text-right"},
                {"data" : "situacion", className: "text-right"},
                {"data" : "situacion", className: "text-right"}
            ],
            "columnDefs": [
                {
                    "targets"  : [0],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return  getSituacionIlt(data, row.tipo);
                              }
                },
                {
                    "targets"  : [1],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 var key = "nMujeres_" + row.tipo;
                                 return '<input type="number" class="ilt-field rounded text-right" data-id="' + key + '" min=0 value="' + data + '" />';
                              }
                },
                {
                    "targets"  : [2],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 var key = "dMujeres_" + row.tipo;
                                 return '<input type="number" class="ilt-field rounded text-right" data-id="' + key + '" min=0 value="' + data + '" />';
                              }
                },
                {
                    "targets"  : [3],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 var dTotal = parseInt(row.dMujeres) + parseInt(row.dHombres);
                                 return '<span>' + calculatePercentage(row.dMujeres, dTotal)  + '%</span>';
                              }
                },
                {
                    "targets"  : [4],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 var mMujeres = (row.dMujeres / row.nMujeres).toFixed(0);
                                 return '<span>' +  (isFinite(mMujeres) ? mMujeres : '0') + '</span>';
                              }
                },
                {
                    "targets"  : [5],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 var key = "nHombres_" + row.tipo;
                                 return '<input type="number" class="ilt-field rounded text-right" data-id="' + key + '" min=0 value="' + data + '" />';
                              }
                },
                {
                    "targets"  : [6],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 var key = "dHombres_" + row.tipo;
                                 return '<input type="number" class="ilt-field rounded text-right" data-id="' + key + '" min=0 value="' + data + '" />';
                              }
                },
                {
                    "targets"  : [7],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 var dTotal = parseInt(row.dMujeres) + parseInt(row.dHombres);
                                 return '<span>' + calculatePercentage(row.dHombres, dTotal)  + '%</span>';
                              }
                },
                {
                    "targets"  : [8],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 var mHombres = (row.dHombres / row.nHombres).toFixed(0);
                                 return '<span>' +  (isFinite(mHombres) ? mHombres : '0') + '</span>';
                              }
                },
                {
                    "targets"  : [9],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 var dTotal = parseInt(row.dMujeres) + parseInt(row.dHombres)
                                 return '<span>' + dTotal + '</span>';
                              }
                },
                {
                    "targets"  : [10],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 var nTotal = parseInt(row.nMujeres) + parseInt(row.nHombres)
                                 return '<span>' + calculatePercentage(nTotal, nTotal) + '%</span>';
                              }
                },
                {
                    "targets"  : [11],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 var dTotal = parseInt(row.dMujeres) + parseInt(row.dHombres);
                                 var nTotal = parseInt(row.nMujeres) + parseInt(row.nHombres);
                                 return '<span>' + (dTotal / nTotal).toFixed(0) + '</span>';
                              }
                }
            ],
            rowGroup: {
                dataSrc: "tipo",
                endRender: function ( rows, group ) {
                    $('#tabla_${seccionId} .group-start').remove();

                   return $('<tr/>')
                        .append('<td><liferay-ui:message key="estadisticas.ceses.TOTAL"/></td>')
                        .append('<td class="text-right"><input type="number" id="nMujeres_' + group + '" disabled class="rounded text-right" value="0" /></td>')
                        .append('<td class="text-right"><input type="number" id="dMujeres_' + group + '" disabled class="rounded text-right" value="0" /></td>')
                        .append('<td class="text-right">0%</td>')
                        .append('<td class="text-right">0</td>')
                        .append('<td class="text-right"><input type="number" id="nHombres_' + group + '" disabled class="rounded text-right" value="0" /></td>')
                        .append('<td class="text-right"><input type="number" id="dHombres_' + group + '" disabled class="rounded text-right" value="0" /></td>')
                        .append('<td class="text-right">0%</td>')
                        .append('<td class="text-right">0</td>')
                        .append('<td class="text-right">0</td>')
                        .append('<td class="text-right">0%</td>')
                        .append('<td class="text-right">0</td>')
                }
            },
            "initComplete": function(settings, json) {

                <c:if test="<%= !hasRole %>">
                    $("#seccion-${seccionId} :input").prop("disabled", true);
                </c:if>

                initSituacionIlt();
                createEditor("${seccionId}", json.valoracion, saveSituacionIlt);
                createEditor("${seccionId}_comentario", json.comentario, saveSituacionIlt);
            }
        });

    });

    function initSituacionIlt() {

        $('.ilt-field').on('input', function (e) {
            if(this.value < 0 || this.value == '')  this.value='';
            calculateTableSituacionItl("${seccionId}", $(this))
            saveSituacionIlt();
        });

        calculateTableSituacionItl("${seccionId}", null);
    }

    function saveSituacionIlt() {
        var table = tableGroupToJson("${seccionId}", "tipo");
        var json = JSON.parse(table);
            json["valoracion"] =  CKEDITOR.instances.textarea_${seccionId}.getData();
            json["comentario"] = CKEDITOR.instances.textarea_${seccionId}_comentario.getData();

        saveDataEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.SITUACION_ILT %>');
    }

    function getSituacionIlt(situacion, tipo) {
        if (situacion == "AEP")     return '<span data-id="AEP" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.situacionIlt.AEP"/></span>';
        if (situacion == "COC")     return '<span data-id="COC" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.situacionIlt.COC"/></span>';
        else return situacion;
    }

</script>