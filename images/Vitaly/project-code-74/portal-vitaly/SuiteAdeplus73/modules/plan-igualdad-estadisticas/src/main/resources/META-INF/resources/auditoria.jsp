<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="auditoria"/>

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
                                <label><liferay-ui:message key="estadistica.auditoria.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}" class="display" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th data-id="auditoria" data-group="tipo"></th>
                                    <th data-id="nMujeres"><liferay-ui:message key="estadisticas.auditoria.nMujeres"/></th>
                                    <th data-id="nHombres"><liferay-ui:message key="estadisticas.auditoria.nHombres"/></th>
                                    <th><liferay-ui:message key="estadisticas.auditoria.diferencia"/></th>
                                    <th data-id="conceptos"><liferay-ui:message key="estadisticas.auditoria.conceptos"/></th>
                                </tr>
                            </thead>
                        </table>
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
                    <div class="prv-form form-group col-md-6 col-6 required">
                        <fieldset>
                            <legend class="control-label"><liferay-ui:message key="estadisticas.aplicarMedidad"/></legend>
                            <div class="checksBorder">
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="medidasAuditoria" id="medidasAuditoria1" value="0" type="radio">
                                    <label class="form-check-label" for="medidasAuditoria1"><liferay-ui:message key="estadisticas.si"/></label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="medidasAuditoria" id="medidasAuditoria2" value="1" type="radio">
                                    <label class="form-check-label" for="medidasAuditoria2"><liferay-ui:message key="estadisticas.no"/></label>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>

<portlet:resourceURL id="findAuditoria" var="findAuditoriaURL">
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
                "url": "<%= findAuditoriaURL %>",
                "dataSrc": function ( json ) {
                    return json.data;
                }
            },
            "columns": [
                {"data" : "tipo"},
                {"data" : "auditoria"},
                {"data" : "nMujeres", className: "text-right"},
                {"data" : "nHombres", className: "text-right"},
                {"data" : "auditoria"},
                {"data" : "conceptos"}
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
                                 return getAuditoria(data, row.tipo);
                              }
                },
                {
                    "targets"  : [2],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<input type="number" class="auditoria-field rounded text-right" min=0 value="' + data + '" />';
                              }
                },
                {
                    "targets"  : [3],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<input type="number" class="auditoria-field rounded text-right" min=0 value="' + data + '" />';
                              }
                },
                {
                    "targets"  : [4],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<span>' + calculatePercentage(Math.abs(row.nHombres - row.nMujeres), Math.max(row.nHombres, row.nMujeres)) + '%</span>'
                              }
                },
                {
                    "targets"  : [5],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<input type="text" class="auditoria-field rounded" value="' + data + '" />';
                              }
                }
            ],
            orderFixed: [0, 'asc'],
            rowGroup: {
                dataSrc: "tipo",
                startRender: function (rows, group) {

                    rows.nodes().each(function (r) {
                        r.dataset.name = group;
                    });

                    return $('<tr/>')
                        .append('<td colspan="5">' + getSalario(group) + '<i class="icon-chevron-up float-right mt-1 pr-2"></td>')
                        .attr('data-name', group);
                }
            },
            "initComplete": function(settings, json) {

                <c:if test="<%= !hasRole %>">
                    $("#seccion-${seccionId} :input").prop("disabled", true);
                </c:if>

                initAuditoria();
                createEditor("${seccionId}", json.valoracion, saveAuditoria);
                createEditor("${seccionId}_comentario", json.comentario, saveAuditoria);

                $('[name=medidasAuditoria]').on('change', function() {
                    saveAuditoria('1');
                });
                $('input:radio[name=medidasAuditoria]').filter('[value=' + json.medida + ']').prop('checked', true);

            }
        });

    });

    function initAuditoria() {

        $('.auditoria-field').on('input', function (e) {
            if(this.value < 0 || this.value == '')  this.value='';
            calculateTableAuditoria("${seccionId}", $(this));
            saveAuditoria();
        });

        collapsedGroup("${seccionId}");

    }

    function saveAuditoria(changeInputRadio) {
        var table = tableGroupToJson("${seccionId}", "tipo");
        var json = JSON.parse(table);
            json["valoracion"] = CKEDITOR.instances.textarea_${seccionId}.getData();
            json["medida"] = $('input[name=medidasAuditoria]:checked').val();
            json["comentario"] = CKEDITOR.instances.textarea_${seccionId}_comentario.getData();

        saveEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.AUDITORIA %>', changeInputRadio);
    }
    
    function getAuditoria(auditoria, tipo) {
         if (tipo === "00" && auditoria === "PRT") return '<span data-id="PRT" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.auditoria.MSA.PRT"/></span>';
         if (tipo === "01" && auditoria === "SBA") return '<span data-id="SBA" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.auditoria.PRS.SBA"/></span>';
         if (tipo === "01" && auditoria === "CSA") return '<span data-id="CSA" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.auditoria.PRS.CSA"/></span>';
         if (tipo === "01" && auditoria === "CEX") return '<span data-id="CEX" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.auditoria.PRS.CEX"/></span>';
         if (tipo === "02" && auditoria === "SBA") return '<span data-id="SBA" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.auditoria.MRE.SBA"/></span>';
         if (tipo === "02" && auditoria === "CSA") return '<span data-id="CSA" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.auditoria.MRE.CSA"/></span>';
         if (tipo === "02" && auditoria === "CEX") return '<span data-id="CEX" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.auditoria.MRE.CEX"/></span>';
         else return auditoria;
    }

    function getSalario(tipo) {
		if (tipo === "00") return '<liferay-ui:message key="estadisticas.auditoria.MSA"/>';
        if (tipo === "01") return '<liferay-ui:message key="estadisticas.auditoria.PRS"/>';
        if (tipo === "02") return '<liferay-ui:message key="estadisticas.auditoria.MRE"/>'; 
		else return tipo;
     }

</script>