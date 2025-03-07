<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="puestoEstudios"/>

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
                                <label><liferay-ui:message key="estadistica.puestoEtudios.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}" class="display scrollable" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th data-id="id" data-group="puesto"><liferay-ui:message key="estadisticas.puestoEtudios.puesto"/></th>
                                    <th data-id="hombres"><liferay-ui:message key="estadisticas.puestoEtudios.nHombres"/></th>
                                    <th data-id="mujeres"><liferay-ui:message key="estadisticas.puestoEtudios.nMujeres"/></th>
                                    <th><liferay-ui:message key="estadisticas.puestoEstudios.total"/></th>
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

                <div class="row align-items-start mb-4" id="valoresNivelEstudios">

                    <%-- MODA --%>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                         <div class="form-group input-text-wrapper">
                            <label class="control-label" for="nombre"><liferay-ui:message key="estadisticas.puestoEstudiso.modaMujer"/></label>
                            <input class="field form-control" disabled id="moda_mujeres" type="text" value="">
                         </div>
                        </div>
                    </div>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                            <div class="form-group input-text-wrapper">
                               <label class="control-label" for="nombre"><liferay-ui:message key="estadisticas.puestoEstudiso.modaHombre"/></label>
                               <input class="field form-control" disabled id="moda_hombres" type="text" value="">
                            </div>
                        </div>
                    </div>

                    <%-- PUESTO --%>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                         <div class="form-group input-text-wrapper">
                            <label class="control-label" for="nombre"><liferay-ui:message key="estadisticas.puestoEstudiso.puestoMujer"/></label>
                            <input class="field form-control" disabled id="puesto_mujeres" type="text" value="">
                         </div>
                        </div>
                    </div>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                            <div class="form-group input-text-wrapper">
                               <label class="control-label" for="nombre"><liferay-ui:message key="estadisticas.puestoEstudiso.puestoHombre"/></label>
                               <input class="field form-control" disabled id="puesto_hombres" type="text" value="">
                            </div>
                        </div>
                    </div>

                    <%-- ESTUDIOS SUPERIORES --%>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                         <div class="form-group input-text-wrapper">
                            <label class="control-label" for="nombre"><liferay-ui:message key="estadisticas.puestoEstudiso.superiorMujer"/></label>
                            <input class="field form-control" disabled id="superiores_mujeres" type="text" value="">
                         </div>
                        </div>
                    </div>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                            <div class="form-group input-text-wrapper">
                               <label class="control-label" for="nombre"><liferay-ui:message key="estadisticas.puestoEstudiso.superiroHombre"/></label>
                               <input class="field form-control" disabled id="superiores_hombres" type="text" value="">
                            </div>
                        </div>
                    </div>

                    <%-- ESTUDIOS INFERIORES --%>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                         <div class="form-group input-text-wrapper">
                            <label class="control-label" for="nombre"><liferay-ui:message key="estadisticas.puestoEstudiso.inferiorMujer"/></label>
                            <input class="field form-control" disabled id="inferiores_mujeres" type="text" value="">
                         </div>
                        </div>
                    </div>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                            <div class="form-group input-text-wrapper">
                               <label class="control-label" for="nombre"><liferay-ui:message key="estadisticas.puestoEstudiso.inferiorHombre"/></label>
                               <input class="field form-control" disabled id="inferiores_hombres" type="text" value="">
                            </div>
                        </div>
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

<portlet:resourceURL id="findPuestoEstudios" var="findPuestoEstudiosURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>
<script>
    $(document).ready( function () {
    	
        $('#tabla_${seccionId}').on('preXhr.dt', function ( e, settings, data ) {
            $("#tabla_${seccionId}").show();
        }).DataTable({
            dom: "",
            "language": {
                "sEmptyTable": "<liferay-ui:message key="planigualdad.view.datatable.sEmptyTable"/>",
            },
            "paging": false,
            "ajax": {
                "url": "<%= findPuestoEstudiosURL %>",
                "dataSrc": function ( json ) {
                    return json.data;
                }
            },
            "columns": [
                {"data" : "puesto"},
                {"data" : "id"},
                {"data" : "hombres"},
                {"data" : "mujeres"},
                {"data" : "id"}
            ],
            "columnDefs": [
                {
                    "targets"  : [0],
                    "visible" : false
                },
                {
                   "targets"  : [1],
                   "orderable": true,
                   "render": function (data, type, row) {
                                 return getEstudiosPuesto(data, row.puesto);
                             }
                },
                {
                   "targets"  : [2],
                   "orderable": false,
                   "render": function (data, type, row) {
                                return  '<input type="number" class="rounded text-right estudios-field" min=0 value="' + data + '" />';
                             }
                },
                {
                   "targets"  : [3],
                   "orderable": false,
                   "render": function (data, type, row) {
                                return  '<input type="number" class="rounded text-right estudios-field" min=0 value="' + data + '" />';
                             }
                },
                {
                   "targets"  : [4],
                   "orderable": false,
                   "render": function (data, type, row) {
                                var value = parseInt(row.hombres) + parseInt(row.mujeres);
                                return  '<input type="number" disabled class="rounded text-right" value="' + value + '" />';
                             }
                }
            ],
            orderFixed: [0, 'asc'],
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
            },
            "initComplete": function(settings, json) {

                <c:if test="<%= !hasRole %>">
                    $("#seccion-${seccionId} :input").prop("disabled", true);
                </c:if>

                initEstudios();
                createEditor("${seccionId}", json.valoracion, savePuestoEstudios);
                createEditor("${seccionId}_comentario", json.comentario, savePuestoEstudios);
                collapsedGroup("${seccionId}");
            }
        });

    });

    function initEstudios() {

        $('.estudios-field').on('input', function (e) {
            if(this.value < 0 || this.value == '')  this.value='0';
            calculateTableEstudios("${seccionId}", $(this));
            savePuestoEstudios();
        });

        calculateTableEstudios("${seccionId}", null);

    }

    function savePuestoEstudios() {
        var table = tableNotLastGroupToJson("${seccionId}", "puesto");
        var json = JSON.parse(table);
            json["valoracion"] = CKEDITOR.instances.textarea_${seccionId}.getData();
            json["comentario"] = CKEDITOR.instances.textarea_${seccionId}_comentario.getData();

        saveDataEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.PUESTO_ESTUDIOS %>');
    }

    function getEstudio(estudio) {
        if (estudio == "00") return '<liferay-ui:message key="estadisticas.puestoEstudios.00"/>';
        if (estudio == "01") return '<liferay-ui:message key="estadisticas.puestoEstudios.01"/>';
        if (estudio == "02") return '<liferay-ui:message key="estadisticas.puestoEstudios.02"/>';
        if (estudio == "03") return '<liferay-ui:message key="estadisticas.puestoEstudios.03"/>';
        if (estudio == "04") return '<liferay-ui:message key="estadisticas.puestoEstudios.04"/>';
        if (estudio == "05") return '<liferay-ui:message key="estadisticas.puestoEstudios.05"/>';
        if (estudio == "06") return '<liferay-ui:message key="estadisticas.puestoEstudios.06"/>';
        return estudios;
    }

    function getEstudiosPuesto(estudio, puesto) {
        if (estudio == "00") return '<span data-id="00" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.puestoEstudios.00"/></span>';
        if (estudio == "01") return '<span data-id="01" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.puestoEstudios.01"/></span>';
        if (estudio == "02") return '<span data-id="02" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.puestoEstudios.02"/></span>';
        if (estudio == "03") return '<span data-id="03" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.puestoEstudios.03"/></span>';
        if (estudio == "04") return '<span data-id="04" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.puestoEstudios.04"/></span>';
        if (estudio == "05") return '<span data-id="05" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.puestoEstudios.05"/></span>';
        if (estudio == "06") return '<span data-id="06" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.puestoEstudios.06"/></span>';
        return estudio;
    }

</script>