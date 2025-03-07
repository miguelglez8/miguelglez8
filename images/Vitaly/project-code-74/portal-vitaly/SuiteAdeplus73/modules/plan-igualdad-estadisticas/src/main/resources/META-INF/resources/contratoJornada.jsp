<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="contratoJornada"/>

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
                                <label><liferay-ui:message key="estadistica.contratoJornada.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}" class="display col-12 col-md-8" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th data-id="contrato" data-group="jornada"></th>
                                    <th data-id="hombres"><liferay-ui:message key="estadisticas.contratoJornada.nHombres"/></th>
                                    <th data-id="mujeres"><liferay-ui:message key="estadisticas.contratoJornada.nMujeres"/></th>
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
                    <div class="col-12 col-md-8">
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

                <div class="row align-items-start mb-4">
                    <div class="prv-form form-group col-md-6 col-6 required">
                        <fieldset>
                            <legend class="control-label"><liferay-ui:message key="estadisticas.aplicarMedidad"/></legend>
                            <div class="checksBorder">
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="medidasContratoJornada" id="medidasContratoJornada1" value="0" type="radio">
                                    <label class="form-check-label" for="medidasContratoJornada1"><liferay-ui:message key="estadisticas.si"/></label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="medidasContratoJornada" id="medidasContratoJornada2" value="1" type="radio">
                                    <label class="form-check-label" for="medidasContratoJornada2"><liferay-ui:message key="estadisticas.no"/></label>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>

<portlet:resourceURL id="findContratoJornada" var="findContratoJornadaURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>
<script>
var updateTableContratoJornada ="1";
        $(document).ready( function () {
        	$('[data-toggle="collapse"]').on("click", function () {
        		if(updateTableContratoJornada=="0"){
					$('#prv-loading-container').addClass('d-flex');
    	    		seLlamoFuncionCanvas=true;
    	    		html2canvas(document.getElementById("estadistica_contratoJornada")).then(function (canvas){
    	    			saveImgCanvas('<%= PlanIgualdadEstadisticasPortletKeys.CONTRATO_JORNADA %>', canvas.toDataURL("image/jpeg"), '0', 'graphic9', 1, 1);
    		    	});
       	    		updateTableContratoJornada="1";
        		}
            });
        	
            $('#tabla_${seccionId}').on('preXhr.dt', function ( e, settings, data ) {
                $("#tabla_${seccionId}").show();
            }).DataTable({
                dom: "",
                "ajax": {
                    "url": "<%= findContratoJornadaURL %>",
                    "dataSrc": function ( json ) {
                        return json.data;
                    }
                },
                "columns": [
                    {"data" : "jornada"},
                    {"data" : "contrato"},
                    {"data" : "hombres", className: "text-right"},
                    {"data" : "mujeres", className: "text-right"}
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
                                     return getContrato(data, row.jornada) ;
                                  }
                    },
                    {
                        "targets"  : [2],
                        "orderable" : true,
                        "render": function (data, type, row) {
                                      return '<input type="number" class="contrato-field rounded text-right" min=0 value="' + data + '" />';
                                  }
                    },
                    {
                        "targets"  : [3],
                        "orderable" : true,
                        "render": function (data, type, row) {
                                     return '<input type="number" class="contrato-field rounded text-right" min=0 value="' + data + '" />';
                                  }
                    }
                ],
                orderFixed: [0, 'asc'],
                rowGroup: {
                    dataSrc: "jornada",
                    startRender: function (rows, group) {

                        rows.nodes().each(function (r) {
                            r.dataset.name = group;
                        });

                        return $('<tr/>')
                            .append('<td colspan="3">' + getJornada(group) + '<i class="icon-chevron-upn float-right mt-1 pr-2"></td>')
                            .attr('data-name', group);
                    },
                },
                "initComplete": function(settings, json) {

                    <c:if test="<%= !hasRole %>">
                        $("#seccion-${seccionId} :input").prop("disabled", true);
                    </c:if>

                    initContratoJornada();
                    createEditor("${seccionId}", json.valoracion, saveContratoJornada);
                    createEditor("${seccionId}_comentario", json.comentario, saveContratoJornada);

                    $('[name=medidasContratoJornada]').on('change', function() {
                        saveContratoJornada('1');
                    });
                    $('input:radio[name=medidasContratoJornada]').filter('[value=' + json.medida + ']').prop('checked', true);
                }
            });

        });

        function initContratoJornada() {

            var dataLabels = ['<liferay-ui:message key="estadisticas.contratoJornada.IND"/>',
                              '<liferay-ui:message key="estadisticas.contratoJornada.TEM"/>',
                              '<liferay-ui:message key="estadisticas.contratoJornada.DIS"/>',
                              '<liferay-ui:message key="estadisticas.contratoJornada.IND"/>',
                              '<liferay-ui:message key="estadisticas.contratoJornada.TEM"/>',
                              '<liferay-ui:message key="estadisticas.contratoJornada.DIS"/>',
                              '<liferay-ui:message key="estadisticas.contratoJornada.IND"/>',
                              '<liferay-ui:message key="estadisticas.contratoJornada.TEM"/>',
                              '<liferay-ui:message key="estadisticas.contratoJornada.DIS"/>',];

            var configLabels = ['<liferay-ui:message key="estadisticas.contratoJornada.completa"/>',
                                '<liferay-ui:message key="estadisticas.contratoJornada.parcial"/>',
                                '<liferay-ui:message key="estadisticas.contratoJornada.nd"/>'];

            var labelHombres = '<liferay-ui:message key="estadisticas.hombres"/>';
            var labelMujeres = '<liferay-ui:message key="estadisticas.mujeres"/>';
            var colorHombres = '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_HOMBRES %>';
            var colorMujeres = '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_MUJERES %>';
            var title = '<liferay-ui:message key="estadisticas.contratoJornada.estadistica.title"/>';

            $('.contrato-field').on('input', function (e) {
                if(this.value < 0 || this.value == '')  this.value='';
                saveContratoJornada();
                updateTableContratoJornada = "0";
                createContratoJornadaChart("${seccionId}", dataLabels, configLabels, labelHombres, labelMujeres, colorHombres, colorMujeres, title);
            });

            createContratoJornadaChart("${seccionId}", dataLabels, configLabels, labelHombres, labelMujeres, colorHombres, colorMujeres, title);
            collapsedGroup("${seccionId}");
        }

        function saveContratoJornada(changeInputRadio) {
            var table = tableGroupToJson("${seccionId}", "jornada");
            var json = JSON.parse(table);
                json["valoracion"] = CKEDITOR.instances.textarea_${seccionId}.getData();
                json["comentario"] = CKEDITOR.instances.textarea_${seccionId}_comentario.getData();
                json["medida"] = $('input[name=medidasContratoJornada]:checked').val();

            saveEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.CONTRATO_JORNADA %>', changeInputRadio);

        }

        function getContrato(contrato, jornada) {
            if (contrato == "IND") return '<span data-id="IND" data-jornada="' + jornada + '"><liferay-ui:message key="estadisticas.contratoJornada.IND"/></span>';
            if (contrato == "TEM") return '<span data-id="TEM" data-jornada="' + jornada + '"><liferay-ui:message key="estadisticas.contratoJornada.TEM"/></span>';
            if (contrato == "DIS") return '<span data-id="DIS" data-jornada="' + jornada + '"><liferay-ui:message key="estadisticas.contratoJornada.DIS"/></span>';
            else return contrato;
        }

        function getJornada(jornada) {
            if (jornada == "0") return '<liferay-ui:message key="estadisticas.contratoJornada.completa"/>';
            if (jornada == "1") return '<liferay-ui:message key="estadisticas.contratoJornada.parcial"/>';
            if (jornada == "2") return '<liferay-ui:message key="estadisticas.contratoJornada.nd"/>';
            else return jornada;
        }

</script>