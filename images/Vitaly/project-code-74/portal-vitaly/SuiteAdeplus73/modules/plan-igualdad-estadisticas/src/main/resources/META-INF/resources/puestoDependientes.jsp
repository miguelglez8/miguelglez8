<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="puestoDependientes"/>

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
                                <label><liferay-ui:message key="estadistica.puestoDependientes.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}" class="display" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th data-id="id"><liferay-ui:message key="estadisticas.puestoDependientes.puesto"/></th>
                                    <th data-id="hombres"><liferay-ui:message key="estadisticas.puestoDependientes.nHombres"/></th>
                                    <th data-id="mujeres"><liferay-ui:message key="estadisticas.puestoDependientes.nMujeres"/></th>
                                    <th><liferay-ui:message key="estadisticas.puestoDependientes.total"/></th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>

                <div class="row d-flex justify-content-center mb-4">
                    <div class="col-md-12">
                        <table id="tabla_${seccionId}_summary" class="display" style="width:100%;">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th><liferay-ui:message key="estadisticas.puestoDependientes.nHombres"/></th>
                                    <th><liferay-ui:message key="estadisticas.puestoDependientes.pHombres"/></th>
                                    <th><liferay-ui:message key="estadisticas.puestoDependientes.nMujeres"/></th>
                                    <th><liferay-ui:message key="estadisticas.puestoDependientes.pMujeres"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><liferay-ui:message key="estadisticas.puestoDependientes.respCarga"/></td>
                                    <td><input type="number" id="hombresRespCarga" class="rounded text-right dependencia-field" min=0 value="0" /></td>
                                    <td><span id="hombresRespCargaP">0%</span></td>
                                    <td><input type="number" id="mujeresRespCarga" class="rounded text-right dependencia-field" min=0 value="0" /></td>
                                    <td><span id="mujeresRespCargaP">0%</span></td>
                                </tr>
                                <tr>
                                    <td><liferay-ui:message key="estadisticas.puestoDependientes.carga"/></td>
                                    <td><input type="number" id="hombresCarga" class="rounded text-right dependencia-field" min=0 value="0" /></td>
                                    <td><span id="hombresCargaP">0%</span></td>
                                    <td><input type="number" id="mujeresCarga" class="rounded text-right dependencia-field" min=0 value="0" /></td>
                                    <td><span id="mujeresCargaP">0%</span></td>
                                </tr>
                            </tbody>
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
                                    <input class="form-check-input" name="medidasPuestoDependientes" id="medidasPuestoDependientes1" value="0" type="radio">
                                    <label class="form-check-label" for="medidasPuestoDependientes1"><liferay-ui:message key="estadisticas.si"/></label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="medidasPuestoDependientes" id="medidasPuestoDependientes2" value="1" type="radio">
                                    <label class="form-check-label" for="medidasPuestoDependientes2"><liferay-ui:message key="estadisticas.no"/></label>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>

<portlet:resourceURL id="findPuestoDependientes" var="findPuestoDependientesURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>
<script>
    $(document).ready( function () {
    	
        var totalHombresDependientes = 0;
        var totalMujeresDependientes = 0;
        var totalHombresResponsablesDependientes = 0;
        var totalMujeresResponsablesDependientes = 0;

        $('#tabla_${seccionId}').on('preXhr.dt', function ( e, settings, data ) {
            $("#tabla_${seccionId}").show();
        }).DataTable({
            dom: "",
            "language": {
                "sEmptyTable": "<liferay-ui:message key="planigualdad.view.datatable.sEmptyTable"/>",
            },
            "paging": false,
            "ajax": {
                "url": "<%= findPuestoDependientesURL %>",
                "dataSrc": function ( json ) {
                    return json.data;
                }
            },
            "columns": [
                {"data" : "responsabilidad"},
                {"data" : "puesto"},
                {"data" : "hombres", className: "text-right"},
                {"data" : "mujeres", className: "text-right"},
                {"data" : "total", className: "text-right"}
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
                                     return puestos.find(puesto => puesto.id === data).nombre;
                              }
                },
            ],
            rowGroup: {
                dataSrc: "responsabilidad",
                startRender: function (rows, group) {

                    rows.nodes().each(function (r) {
                        r.dataset.name = group + "-" + "${seccionId}";
                    });

                    return $('<tr/>')
                        .append('<td colspan="4">' + getResponsabilidad(group) + '<i class="icon-chevron-up float-right mt-1 pr-2"></td>')
                        .attr('data-name', group + "-" + "${seccionId}");
                },
            },
            "initComplete": function(settings, json) {

                totalHombresDependientes = json.totalHombres;
                totalMujeresDependientes = json.totalMujeres;
                totalHombresResponsablesDependientes = json.nHombresResponsabilidad;
                totalMujeresResponsablesDependientes = json.nMujeresResponsabilidad;

                createEditor("${seccionId}", json.valoracion, savePuestoDependientes);
                createEditor("${seccionId}_comentario", json.comentario, savePuestoDependientes);
                collapsedGroup("${seccionId}");

                $('[name=medidasPuestoDependientes]').on('change', function() {
                    savePuestoDependientes('1');
                });
                $('input:radio[name=medidasPuestoDependientes]').filter('[value=' + json.medida + ']').prop('checked', true);

                $('#hombresRespCarga').val(json.hombresRespCarga);
                $('#mujeresRespCarga').val(json.mujeresRespCarga);
                $('#hombresCarga').val(json.hombresCarga);
                $('#mujeresCarga').val(json.mujeresCarga);

                $('#hombresRespCargaP').text(calculatePercentage(json.hombresRespCarga, totalHombresResponsablesDependientes) + '%');
                $('#mujeresRespCargaP').text(calculatePercentage(json.mujeresRespCarga, totalMujeresResponsablesDependientes) + '%');
                $('#hombresCargaP').text(calculatePercentage(json.hombresCarga, totalHombresDependientes) + '%');
                $('#mujeresCargaP').text(calculatePercentage(json.mujeresCarga, totalMujeresDependientes) + '%');
            }
        });

        $('#tabla_${seccionId}_summary').DataTable({
            dom: "",
            "ordering": false,
            "initComplete": function(settings, json) {

                <c:if test="<%= !hasRole %>">
                    $("#seccion-${seccionId} :input").prop("disabled", true);
                </c:if>

                $('.dependencia-field').on('input', function (e) {

                    if(this.value < 0 || this.value == '')    this.value = '';

                    var hombresRespCarga = parseInt( $('#hombresRespCarga').val() );
                    var mujeresRespCarga = parseInt( $('#mujeresRespCarga').val() );
                    var hombresCarga = parseInt( $('#hombresCarga').val() );
                    var mujeresCarga = parseInt( $('#mujeresCarga').val() );

                    if ( hombresRespCarga > totalHombresResponsablesDependientes ) $('#hombresRespCarga').val(0);
                    $('#hombresRespCargaP').text( calculatePercentage($('#hombresRespCarga').val(), totalHombresResponsablesDependientes) + '%' );

                    if ( mujeresRespCarga > totalMujeresResponsablesDependientes ) $('#mujeresRespCarga').val(0);
                    $('#mujeresRespCargaP').text( calculatePercentage($('#mujeresRespCarga').val(), totalMujeresResponsablesDependientes) + '%' );

                    if ( hombresCarga > totalHombresDependientes ) $('#hombresCarga').val(0);
                    $('#hombresCargaP').text( calculatePercentage($('#hombresCarga').val(), totalHombresDependientes) + '%' );

                    if ( mujeresCarga > totalMujeresDependientes ) $('#mujeresCarga').val(0);
                    $('#mujeresCargaP').text( calculatePercentage($('#mujeresCarga').val(), totalMujeresDependientes) + '%' );

                    savePuestoDependientes();

                });

            }
        });

    });

    function savePuestoDependientes(changeInputRadio) {
        var json = {};
            json["valoracion"] = CKEDITOR.instances.textarea_${seccionId}.getData();
            json["medida"] = $('input[name=medidasPuestoDependientes]:checked').val();
            json["hombresRespCarga"] = $('#hombresRespCarga').val();
            json["mujeresRespCarga"] = $('#mujeresRespCarga').val();
            json["hombresCarga"] = $('#hombresCarga').val();
            json["mujeresCarga"] = $('#mujeresCarga').val();
            json["comentario"] = CKEDITOR.instances.textarea_${seccionId}_comentario.getData();

        saveEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.PUESTO_DEPENDIENTES %>', changeInputRadio);
        
    }

    function getResponsabilidad(responsabilidad) {
        if (responsabilidad == "0") return '<liferay-ui:message key="estadisticas.puestoDependientes.0"/>';
        if (responsabilidad == "1") return '<liferay-ui:message key="estadisticas.puestoDependientes.1"/>';
        else return responsabilidad;
    }

</script>
