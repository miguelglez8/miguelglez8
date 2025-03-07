<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="ingresos"/>

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
                                <label><liferay-ui:message key="estadistica.ingresos.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}" class="display" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th data-id="contrato" data-group="tipo"><liferay-ui:message key="estadisticas.ingresos.ultimoAnio"/></th>
                                    <th data-id="mujeres"><liferay-ui:message key="estadisticas.ingresos.nMujeres"/></th>
                                    <th><liferay-ui:message key="estadisticas.ingresos.pMujeres"/></th>
                                    <th data-id="hombres"><liferay-ui:message key="estadisticas.ingresos.nHombres"/></th>
                                    <th><liferay-ui:message key="estadisticas.ingresos.pHombres"/></th>
                                    <th><liferay-ui:message key="estadisticas.ingresos.nTotal"/></th>
                                    <th><liferay-ui:message key="estadisticas.ingresos.pTotal"/></th>
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

<portlet:resourceURL id="findIngresos" var="findIngresosURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>
<script>
var updateTableIngresos ="1";
     $(document).ready( function () {
    	 $('[data-toggle="collapse"]').on("click", function () {
     		if(updateTableIngresos=="0"){
 				$('#prv-loading-container').addClass('d-flex');
    	    		
				$('#prv-loading-container').addClass('d-flex');
	    		seLlamoFuncionCanvas=true;
	    		html2canvas(document.getElementById("estadistica_ingresos")).then(function (canvas){
	    			saveImgCanvas('<%= PlanIgualdadEstadisticasPortletKeys.INGRESOS %>', canvas.toDataURL("image/jpeg"), '0', 'graphic11', 1, 1);
		    	});

    	    	updateTableIngresos="1";
     		}
         });
        $('#tabla_${seccionId}').on('preXhr.dt', function ( e, settings, data ) {
            $("#tabla_${seccionId}").show();
        }).DataTable({
            dom: "",
            "ajax": {
                "url": "<%= findIngresosURL %>",
                "dataSrc": function ( json ) {
                    return json.data;
                }
            },
            "columns": [
                {"data" : "tipo"},
                {"data" : "contrato"},
                {"data" : "mujeres", className: "text-right"},
                {"data" : "mujeres", className: "text-right"},
                {"data" : "hombres", className: "text-right"},
                {"data" : "hombres", className: "text-right"},
                {"data" : "contrato", className: "text-right"},
                {"data" : "contrato", className: "text-right"}
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
                                     return getContratacion(data, row.tipo);
                                  }
                    },
                    {
                       "targets"  : [2],
                       "orderable": false,
                       "render": function (data, type, row) {
                                     var key = "mujeres_" + row.tipo;
                                     return '<input type="number" class="ingresos-field rounded text-right" data-id="' + key + '" min=0 value="' + data + '" />';
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
                                     var key = "hombres_" + row.tipo;
                                     return '<input type="number" class="ingresos-field rounded text-right" data-id="' + key + '" min=0 value="' + data + '" />';
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
                                     return '<input type="number" disabled class="ingresos-total rounded text-right" value="' + total + '" />';
                       }
                    },
                    {
                       "targets"  : [7],
                       "orderable": false,
                       "render": function (data, type, row) {
                                     return '<span>0%</span>';
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
                        .append('<td colspan="7">' + getTipo(group) + '<i class="icon-chevron-up float-right mt-1 pr-2"></td>')
                        .attr('data-name', group);
                },
                endRender: function ( rows, group ) {

                   return $('<tr/>')
                        .append('<td>Total</td>')
                        .append('<td class="text-right"><input type="number" id="mujeres_' + group + '" disabled class="rounded text-right" value="0" /></td>')
                        .append('<td class="text-right">0%</td>')
                        .append('<td class="text-right"><input type="number" id="hombres_' + group + '" disabled class="rounded text-right" value="0" /></td>')
                        .append('<td class="text-right">0%</td>')
                        .append('<td class="text-right"><input type="number" id="total_' + group + '" disabled class="rounded text-right" value="0" /></td>')
                        .append('<td class="text-right">0%</td>')
                        .attr('data-name', group);
                }
            },
            "initComplete": function(settings, json) {

                <c:if test="<%= !hasRole %>">
                    $("#seccion-${seccionId} :input").prop("disabled", true);
                </c:if>

                initIngresos();
                createEditor("${seccionId}", json.valoracion, saveIngresos);
                createEditor("${seccionId}_comentario", json.comentario, saveIngresos);
            }
        });
        
     });

     function initIngresos() {

        var  labels = ['<liferay-ui:message key="estadisticas.ingresos.indef"/>',
                       '<liferay-ui:message key="estadisticas.ingresos.temp"/>',
                       '<liferay-ui:message key="estadisticas.ingresos.fd"/>',
                       '<liferay-ui:message key="estadisticas.ingresos.completa"/>',
                       '<liferay-ui:message key="estadisticas.ingresos.parcial"/>',
                       '<liferay-ui:message key="estadisticas.ingresos.nd"/>'];

        var labelHombres = '<liferay-ui:message key="estadisticas.hombres"/>';
        var labelMujeres = '<liferay-ui:message key="estadisticas.mujeres"/>';
        var colorHombres = '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_HOMBRES %>';
        var colorMujeres = '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_MUJERES %>';
        var title = '<liferay-ui:message key="estadistica.ingresos.chart.title"/>';

        $('.ingresos-field').on('input', function (e) {
            if(this.value < 0 || this.value == '')  this.value='';
            calculateTableIngresos("${seccionId}", $(this));
            createIngresosChart("${seccionId}", labels, labelHombres, labelMujeres, colorHombres, colorMujeres, title);
            saveIngresos();
            updateTableIngresos="0"
        });

        hideTotal();
        collapsedGroup("${seccionId}");
        calculateTableIngresos("${seccionId}", null);
        createIngresosChart("${seccionId}", labels, labelHombres, labelMujeres, colorHombres, colorMujeres, title);

     }

    function saveIngresos() {
        var table = tableGroupToJson("${seccionId}", "tipo");
        var json = JSON.parse(table);
            json["valoracion"] = CKEDITOR.instances.textarea_${seccionId}.getData();
            json["comentario"] = CKEDITOR.instances.textarea_${seccionId}_comentario.getData();

        saveDataEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.INGRESOS %>');
    }

    function getContratacion(contrato, tipo) {
        if (tipo === "ICON" && contrato === "00") return '<span data-id="00" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.ingresos.ICON.00"/></span>';
        if (tipo === "ICON" && contrato === "01") return '<span data-id="01" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.ingresos.ICON.01"/></span>';
        if (tipo === "ICON" && contrato === "02") return '<span data-id="02" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.ingresos.ICON.02"/></span>';
        if (tipo === "IJOR" && contrato === "00") return '<span data-id="00" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.ingresos.IJOR.00"/></span>';
        if (tipo === "IJOR" && contrato === "01") return '<span data-id="01" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.ingresos.IJOR.01"/></span>';
        if (tipo === "IJOR" && contrato === "02") return '<span data-id="02" data-tipo="' + tipo + '"><liferay-ui:message key="estadisticas.ingresos.IJOR.02"/></span>';
        else return contrato;
    }

    function getTipo(tipo) {
        if (tipo == "ICON") return '<liferay-ui:message key="estadisticas.ingresos.ICON"/>';
        if (tipo == "IJOR") return '<liferay-ui:message key="estadisticas.ingresos.IJOR"/>';
        else return tipo;
    }

</script>