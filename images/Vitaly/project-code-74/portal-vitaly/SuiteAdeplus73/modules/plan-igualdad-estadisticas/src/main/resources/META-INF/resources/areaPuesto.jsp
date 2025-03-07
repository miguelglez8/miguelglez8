<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="areaPuesto"/>

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
                                <label><liferay-ui:message key="estadistica.areaPuesto.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}" class="display" style="width:100%; display:none; font-size:16px;">
                            <thead>
                                <tr>
                                    <th rowspan="2"></th>
                                    <th rowspan="2"><liferay-ui:message key="estadistica.areaPuesto.nPersonas"/></th>
                                    <th colspan="2"><liferay-ui:message key="estadisticas.hombres"/></th>
                                    <th colspan="2"><liferay-ui:message key="estadisticas.mujeres"/></th>
                                    <th colspan="2"><liferay-ui:message key="estadistica.areaPuesto.total"/></th>
                                </tr>
                                <tr>
                                    <th><liferay-ui:message key="estadistica.areaPuesto.nHombres"/></th>
                                    <th><liferay-ui:message key="estadistica.areaPuesto.pHombres"/></th>
                                    <th><liferay-ui:message key="estadistica.areaPuesto.nMujeres"/></th>
                                    <th><liferay-ui:message key="estadistica.areaPuesto.pMujeres"/></th>
                                    <th><liferay-ui:message key="estadistica.areaPuesto.nTotal"/></th>
                                    <th><liferay-ui:message key="estadistica.areaPuesto.analisis"/></th>
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

                <div class="row align-items-start mb-4">
                    <div class="prv-form form-group col-md-6 col-6 required">
                        <fieldset>
                            <legend class="control-label"><liferay-ui:message key="estadisticas.aplicarMedidad"/></legend>
                            <div class="checksBorder">
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="medidasAreaPuesto" id="medidasAreaPuesto1" value="0" type="radio">
                                    <label class="form-check-label" for="medidasAreaPuesto1"><liferay-ui:message key="estadisticas.si"/></label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="medidasAreaPuesto" id="medidasAreaPuesto2" value="1" type="radio">
                                    <label class="form-check-label" for="medidasAreaPuesto2"><liferay-ui:message key="estadisticas.no"/></label>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>

<portlet:resourceURL id="findAreaPuesto" var="findAreaPuestoURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>
<script>
    $(document).ready( function () {

        $('#tabla_${seccionId}').on('preXhr.dt', function ( e, settings, data ) {
            $("#tabla_${seccionId}").show();
        }).DataTable({
            dom: '',
            "ajax": {
                "url": "<%= findAreaPuestoURL %>",
                "dataSrc": function ( json ) {
                    return json.data;
                }
            },
            "language": {
                "sEmptyTable": "<liferay-ui:message key="planigualdad.view.datatable.sEmptyTable"/>",
            },
            "paging": false,
            "columns": [
                {"data" : "area"},
                {"data" : "puesto"},
                {"data" : "nHombres", className: "text-right"},
                {"data" : "pHombres", className: "text-right"},
                {"data" : "nMujeres", className: "text-right"},
                {"data" : "pMujeres", className: "text-right"},
                {"data" : "personas", className: "text-right"},
                {"data" : "analisis"}
            ],
            "columnDefs": [
                {
                      "targets"  : [0],
                      "orderable": false,
                      "visible" : false
                }
            ],
            orderFixed: [0, 'asc'],
            rowGroup: {
                dataSrc: "area",
                startRender: function (rows, group) {

                    rows.nodes().each(function (r) {
                        r.dataset.name = group.replace(/ /g,"_");
                    });

                    return $('<tr/>')
                        .append('<td colspan="7">' + group + '<i class="icon-chevron-up float-right mt-1 pr-2"></i></td>')
                        .attr('data-name', group.replace(/ /g,"_"));
                },
            },
            "initComplete": function(settings, json) {

                <c:if test="<%= !hasRole %>">
                    $("#seccion-${seccionId} :input").prop("disabled", true);
                </c:if>

                createEditor("${seccionId}", json.valoracion, saveAreaPuesto);
                createEditor("${seccionId}_comentario", json.comentario, saveAreaPuesto);

                $('[name=medidasAreaPuesto]').on('change', function() {
                    saveAreaPuesto('1');
                });
                $('input:radio[name=medidasAreaPuesto]').filter('[value=' + json.medida + ']').prop('checked', true);
                
                tableTotals("${seccionId}");
                collapsedGroup("${seccionId}");

            }
        });
        
    });

    function saveAreaPuesto(changeInputRadio) {
        var json = {};
        json["valoracion"] = CKEDITOR.instances.textarea_${seccionId}.getData();
        json["medida"] = $('input[name=medidasAreaPuesto]:checked').val();
        json["comentario"] = CKEDITOR.instances.textarea_${seccionId}_comentario.getData();

        saveEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.AREA_PUESTO %>', changeInputRadio);

    }

</script>