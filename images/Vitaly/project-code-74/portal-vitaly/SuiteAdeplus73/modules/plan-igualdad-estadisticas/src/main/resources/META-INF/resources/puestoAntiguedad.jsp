<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="puestoAntiguedad"/>

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
                                <label><liferay-ui:message key="estadistica.puestoAntiguedad.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}" class="display scrollable" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th rowspan="2"></th>
                                    <th rowspan="2" class="key" data-id="id" data-group="puesto"><liferay-ui:message key="estadisticas.puestoAntiguedad.pueso"/></th>
                                    <th colspan="3"><liferay-ui:message key="estadisticas.hombres"/></th>
                                    <th colspan="3"><liferay-ui:message key="estadisticas.mujeres"/></th>
                                </tr>
                                <tr>
                                    <th class="key" data-id="hombres"><liferay-ui:message key="estadisticas.puestoAntiguedad.total"/></th>
                                    <th class="key" data-id="hombresR"><liferay-ui:message key="estadisticas.puestoAntiguedad.conRFamiliar"/></th>
                                    <th><liferay-ui:message key="estadisticas.puestoAntiguedad.conRFamiliarP"/></th>
                                    <th class="key" data-id="mujeres"><liferay-ui:message key="estadisticas.puestoAntiguedad.total"/></th>
                                    <th class="key" data-id="mujeresR"><liferay-ui:message key="estadisticas.puestoAntiguedad.conRFamiliar"/></th>
                                    <th><liferay-ui:message key="estadisticas.puestoAntiguedad.conRFamiliarP"/></th>
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

<portlet:resourceURL id="findPuestoAntiguedad" var="findPuestoAntiguedadURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>
<script>
     $(document).ready( function () {

        $('#tabla_${seccionId}').on('preXhr.dt', function ( e, settings, data ) {
            $("#tabla_${seccionId}").show();
        }).DataTable({
            dom: "",
            "autoWidth": false,
            "language": {
                "sEmptyTable": "<liferay-ui:message key="planigualdad.view.datatable.sEmptyTable"/>",
            },
            "paging": false,
            "ajax": {
                "url": "<%= findPuestoAntiguedadURL %>",
                "dataSrc": function ( json ) {
                    return json.data;
                }
            },
            "columns": [
                {"data" : "puesto"},
                {"data" : "id", className: "value"},
                {"data" : "hombres", className: "value"},
                {"data" : "hombresR", className: "value"},
                {"data" : "id", className: "text-right"},
                {"data" : "mujeres", className: "value"},
                {"data" : "mujeresR", className: "value"},
                {"data" : "id", className: "text-right"},
            ],
            "columnDefs": [
                {
                    "targets"  : [0],
                    "visible" : false
                },
                {
                    "targets"  : [1],
                    "orderable" : true,
                    "render": function (data, type, row) {
                                 return getAntiguedadPuesto(data, row.puesto);
                              }
                },
                {
                   "targets"  : [2],
                   "orderable": false,
                   "render": function (data, type, row) {
                                 var key = "hombres_" + row.puesto;
                                 return '<input type="number" class="antiguedad-field rounded text-right" data-id="' + key + '" min=0 value="' + data + '" />';
                             }
                },
                {
                   "targets"  : [3],
                   "orderable": false,
                   "render": function (data, type, row) {
                                 var key = "hombresR_" + row.puesto;
                                 return '<input type="number" class="antiguedad-field rounded text-right" data-id="' + key + '" min=0 value="' + data + '" />';
                             }
                },
                {
                   "targets"  : [4],
                   "orderable": false,
                   "render": function (data, type, row) {
                                return '<span class="hombres-percentage">' + calculatePercentage(row.hombresR, row.hombres) + '%</span>';
                             }
                },
                {
                   "targets"  : [5],
                   "orderable": false,
                   "render": function (data, type, row) {
                                var key = "mujeres_" + row.puesto;
                                return '<input type="number" class="antiguedad-field rounded text-right" data-id="' + key + '" min=0 value="' + data + '" />';
                             }
                },
                {
                   "targets"  : [6],
                   "orderable": false,
                   "render": function (data, type, row) {
                                var key = "mujeresR_" + row.puesto;
                                return '<input type="number" class="antiguedad-field rounded text-right" data-id="' + key + '" min=0 value="' + data + '" />';
                             }
                },
                {
                   "targets"  : [7],
                   "orderable": false,
                   "render": function (data, type, row) {
                                return '<span class="mujeres-percentage">' + calculatePercentage(row.mujeresR, row.mujeres) + '%</span>';
                             }
                }
            ],
            rowGroup: {
                dataSrc: "puesto",
                startRender: function (rows, group) {

                    rows.nodes().each(function (r) {
                        r.dataset.name = group + "-" + "${seccionId}";
                    });

                    return $('<tr/>')
                        .append('<td colspan="3">' + puestos.find(puesto => puesto.id === group).nombre + '<i class="icon-chevron-up float-right mt-1 pr-2"></td>')
                        .attr('data-name', group + "-" + "${seccionId}");
                },
                endRender: function ( rows, group ) {

                    var hombres = rows.data().pluck("hombres").reduce( function (a, b) { return a + b * 1; }, 0);
                    var mujeres = rows.data().pluck("mujeres").reduce( function (a, b) { return a + b * 1; }, 0);
                    var hombresR = rows.data().pluck("hombresR").reduce( function (a, b) { return a + b * 1; }, 0);
                    var mujeresR = rows.data().pluck("mujeresR").reduce( function (a, b) { return a + b * 1; }, 0);

                   return $('<tr/>')
                        .append('<td><liferay-ui:message key="estadisticas.puestoAntiguedad.total"/></td>')
                        .append('<td><input type="number" id="hombres_' + group + '" disabled class="rounded text-right" value="' + hombres + '" /></td>')
                        .append('<td><input type="number" id="hombresR_' + group + '" disabled class="rounded text-right" value="' + hombresR + '" /></td>')
                        .append('<td class="text-right"><span  class="hombres-percentage">' + calculatePercentage(hombresR, hombres) + '%</span></td>')
                        .append('<td><input type="number" id="mujeres_' + group + '" disabled class="rounded text-right" value="' + mujeres + '" /></td>')
                        .append('<td><input type="number" id="mujeresR_' + group + '" disabled class="rounded text-right" value="' + mujeresR + '" /></td>')
                        .append('<td class="text-right"><span class="mujeres-percentage">' + calculatePercentage(mujeresR, mujeres) + '%</span></td>')
                        .attr('data-name', group + "-" + "${seccionId}");
                }
            },
            "initComplete": function(settings, json) {

                <c:if test="<%= !hasRole %>">
                    $("#seccion-${seccionId} :input").prop("disabled", true);
                </c:if>

                initAntiguedades();
                createEditor("${seccionId}", json.valoracion, savePuestoAntiguedad);
                createEditor("${seccionId}_comentario", json.comentario, savePuestoAntiguedad);
            }
        });

     });

     function initAntiguedades() {

        $('.antiguedad-field').on('input', function (e) {
            if(this.value < 0 || this.value == '')    this.value='0';
            calculateTablePuestoAntiguedad( $(this) );
            savePuestoAntiguedad();
        });

        hideTotal();
        collapsedGroup("${seccionId}");
     }

     function savePuestoAntiguedad(updateTable) {
        var table = tableKeyValueToJson("${seccionId}", "puesto");
        var json = JSON.parse(table);
            json["valoracion"] = CKEDITOR.instances.textarea_${seccionId}.getData();
            json["comentario"] = CKEDITOR.instances.textarea_${seccionId}_comentario.getData();

        saveDataEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.PUESTO_ANTIGUEDAD %>');
     }

     function getAntiguedadPuesto(antiguedad, puesto) {
        if (antiguedad == "0001") return '<span data-id="0001" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.puestoAntiguedad.0001"/></span>';
        if (antiguedad == "0103") return '<span data-id="0103" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.puestoAntiguedad.0103"/></span>';
        if (antiguedad == "0305") return '<span data-id="0305" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.puestoAntiguedad.0305"/></span>';
        if (antiguedad == "0510") return '<span data-id="0510" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.puestoAntiguedad.0510"/></span>';
        if (antiguedad == "1015") return '<span data-id="1015" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.puestoAntiguedad.1015"/></span>';
        if (antiguedad == "1500") return '<span data-id="1500" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.puestoAntiguedad.1500"/></span>';
        else return antiguedad;
     }

</script>