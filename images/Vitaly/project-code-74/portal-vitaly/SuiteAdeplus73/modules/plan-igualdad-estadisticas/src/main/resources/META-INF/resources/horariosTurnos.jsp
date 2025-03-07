<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="horariosTurnos"/>

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
                                <label><liferay-ui:message key="estadistica.horariosTurnos.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}" class="display" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th data-id="turno" data-group="jornada"></th>
                                    <th data-id="hombres">N Hombres</th>
                                    <th>% Hombres</th>
                                    <th data-id="mujeres">N Mujeres</th>
                                    <th>% Mujeres</th>
                                    <th>Total</th>
                                    <th>% Total</th>
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

<portlet:resourceURL id="findHorariosTurnos" var="findHorariosTurnosURL">
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
                    "url": "<%= findHorariosTurnosURL %>",
                    "dataSrc": function ( json ) {
                        return json.data;
                    }
                },
                "columns": [
                    {"data" : "jornada"},
                    {"data" : "turno"},
                    {"data" : "hombres", className: "text-right"},
                    {"data" : "hombres", className: "text-right"},
                    {"data" : "mujeres", className: "text-right"},
                    {"data" : "mujeres", className: "text-right"},
                    {"data" : "turno", className: "text-right"},
                    {"data" : "turno", className: "text-right"}
                ],
                "columnDefs": [
                    {
                        "targets"  : [0],
                        "visible" : false
                    },
                    {
                        "targets"  : [1],
                        "orderable" : false,
                        "render": function (data, type, row) {
                                     return getTurnos(data, row.jornada);
                                  }
                    },
                    {
                       "targets"  : [2],
                       "orderable": false,
                       "render": function (data, type, row) {
                                     var key = "hombres_" + row.jornada;
                                     return '<input type="number" class="turnos-field rounded text-right" data-id="' + key + '" min=0 value="' + data + '" />';
                                 }
                    },
                    {
                       "targets"  : [3],
                       "orderable": false,
                       "render": function (data, type, row) {
                                    return '<span>0%</span>';
                                 }
                    },
                    {
                       "targets"  : [4],
                       "orderable": false,
                       "render": function (data, type, row) {
                                     var key = "mujeres_" + row.jornada;
                                     return '<input type="number" class="turnos-field rounded text-right" data-id="' + key + '" min=0 value="' + data + '" />';
                       }
                    },
                    {
                       "targets"  : [5],
                       "orderable": false,
                       "render": function (data, type, row) {
                                    return '<span>0%</span>';
                                 }
                    },
                    {
                       "targets"  : [6],
                       "orderable": false,
                       "render": function (data, type, row) {
                                     var total = parseInt(row.hombres) + parseInt(row.mujeres);
                                     return '<input type="number" disabled class="turnos-total rounded text-right" value="' + total + '" />';
                       }
                    },
                    {
                       "targets"  : [7],
                       "orderable": false,
                       "render": function (data, type, row) {
                                     return '<span>0%</span>';
                       }
                    },
                ],
                orderFixed: [0, 'asc'],
                rowGroup: {
                    dataSrc: "jornada",
                    startRender: function (rows, group) {

                        rows.nodes().each(function (r) {
                            r.dataset.name = group;
                        });

                        return $('<tr/>')
                            .append('<td colspan="7">' + getHorarios(group) + '<i class="icon-chevron-up float-right mt-1 pr-2"></td>')
                            .attr('data-name', group);
                    },
                    endRender: function ( rows, group ) {

                       return $('<tr/>')
                            .append('<td>Total</td>')
                            .append('<td class="text-right"><input type="number" id="hombres_' + group + '" disabled class="rounded text-right" value="0" /></td>')
                            .append('<td class="text-right">0%</td>')
                            .append('<td class="text-right"><input type="number" id="mujeres_' + group + '" disabled class="rounded text-right" value="0" /></td>')
                            .append('<td class="text-right">0%</td>')
                            .append('<td class="text-right"><input type="number" disabled class="rounded text-right" value="0" /></td>')
                            .append('<td class="text-right">0%</td>')
                            .attr('data-name', group);
                    }
                },
                "initComplete": function(settings, json) {

                    <c:if test="<%= !hasRole %>">
                        $("#seccion-${seccionId} :input").prop("disabled", true);
                    </c:if>

                    initHorariosTurnos()
                    createEditor('${seccionId}', json.valoracion, saveHorariosTurnos);
                    createEditor("${seccionId}_comentario", json.comentario, saveHorariosTurnos);
                }
            });

        });

        function initHorariosTurnos() {

            $('.turnos-field').on('input', function (e) {
                if(this.value < 0 || this.value == '')  this.value='';
                calculateTableHorariosJornada("${seccionId}", $(this));
                saveHorariosTurnos();
            });

            hideTotal();
            collapsedGroup("${seccionId}");
            calculateTableHorariosJornada("${seccionId}", null);
        }

        function saveHorariosTurnos(updateTable) {
            var table = tableGroupToJson("${seccionId}", "jornada");
            var json = JSON.parse(table);
                json["valoracion"] = CKEDITOR.instances.textarea_${seccionId}.getData();
                json["comentario"] = CKEDITOR.instances.textarea_${seccionId}_comentario.getData();

            saveDataEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.HORARIO_TURNOS %>');
                 
            
        }

        function getTurnos(turno, jornada) {
            if (turno == "CTUR") return '<span data-id="CTUR" data-jornada="' + jornada + '"><liferay-ui:message key="estadisticas.horariosTurnos.CTUR"/></span>';
            if (turno == "STUR") return '<span data-id="STUR" data-jornada="' + jornada + '"><liferay-ui:message key="estadisticas.horariosTurnos.STUR"/></span>';
        }

        function getHorarios(horario) {
            if (horario == "CON") return '<liferay-ui:message key="estadisticas.horariosTurnos.CON"/>';
            if (horario == "PAR") return '<liferay-ui:message key="estadisticas.horariosTurnos.PAR"/>';
        }

</script>