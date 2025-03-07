<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="puestoEdad"/>

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
                                <label><liferay-ui:message key="estadistica.puestoEdad.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}" class="display scrollable" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th data-id="id" data-group="puesto"><liferay-ui:message key="estadisticas.puestoEdad.puesto"/></th>
                                    <th data-id="hombres"><liferay-ui:message key="estadisticas.puestoEdad.nHombres"/></th>
                                    <th data-id="mujeres"><liferay-ui:message key="estadisticas.puestoEdad.nMujeres"/></th>
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
                    <div class="col-12 col-md-8 chart-container" style="position: relative; ">
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

<portlet:resourceURL id="findPuestoEdad" var="findPuestoEdadURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>

<script>
	var updateTablePuestoEdad ="1";
    $(document).ready( function () { 
    	$('[data-toggle="collapse"]').on("click", function () {
    		if(updateTablePuestoEdad=="0"){
				$('#prv-loading-container').addClass('d-flex');
                seLlamoFuncionCanvas=true;
	    		html2canvas(document.getElementById("estadistica_puestoEdad")).then(function (canvas){
	    			saveImgCanvas('<%= PlanIgualdadEstadisticasPortletKeys.PUESTO_EDAD %>', canvas.toDataURL("image/jpeg"), '0', 'graphic4', 1, 1);
		    	});
   	    		updateTablePuestoEdad="1";
    		}
        });
    	
        $('#tabla_${seccionId}').on('preXhr.dt', function ( e, settings, data ) {
            $("#tabla_${seccionId}").show();
        }).DataTable({
            dom: "",
            "language": {
                "sEmptyTable": "<liferay-ui:message key="planigualdad.view.datatable.sEmptyTable"/>",
            },
            "paging": false,
            "ajax": {
                "url": "<%= findPuestoEdadURL %>",
                "dataSrc": function ( json ) {
                    return json.data;
                }
            },
            "columns": [
                {"data" : "puesto"},
                {"data" : "id"},
                {"data" : "hombres"},
                {"data" : "mujeres"}
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
                                 return getFranjasPuestoEdad(data, row.puesto);
                             }
                },
                {
                   "targets"  : [2],
                   "orderable": false,
                   "render": function (data, type, row) {
                                return  '<input type="number" class="rounded text-right puesto-field" min=0 value="' + data + '" />';
                             }
                },
                {
                   "targets"  : [3],
                   "orderable": false,
                   "render": function (data, type, row) {
                                return  '<input type="number" class="rounded text-right puesto-field" min=0 value="' + data + '" />';
                             }
                },
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
                
                initPuestoEdad();
                createEditor("${seccionId}", json.valoracion, savePuestoEdad);
                createEditor("${seccionId}_comentario", json.comentario, savePuestoEdad);
                collapsedGroup("${seccionId}");
            }
        });

    });

    function initPuestoEdad() {

        var labelHombres = '<liferay-ui:message key="estadisticas.hombres"/>';
        var labelMujeres = '<liferay-ui:message key="estadisticas.mujeres"/>';
        var colorHombres = '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_HOMBRES %>';
        var colorMujeres = '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_MUJERES %>';
        var title = '<liferay-ui:message key="estadisticas.puestoEdad.title"/>';

        $('.puesto-field').on('input', function (e) {
            if(this.value < 0 || this.value == '')    this.value='0';
            savePuestoEdad();
            updateTablePuestoEdad="0";
            createPuestoEdadChart("${seccionId}", labelHombres, labelMujeres, colorHombres, colorMujeres, title)
        });

        createPuestoEdadChart("${seccionId}", labelHombres, labelMujeres, colorHombres, colorMujeres, title);
    }

    function savePuestoEdad() {
        var table = tableGroupToJson("${seccionId}", "puesto");
        var json = JSON.parse(table);
            json["valoracion"] = CKEDITOR.instances.textarea_${seccionId}.getData();
            json["comentario"] = CKEDITOR.instances.textarea_${seccionId}_comentario.getData();

        saveDataEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.PUESTO_EDAD %>');
    }

    function getFranjasPuestoEdad(franja, puesto) {
        if (franja == "0020") return '<span data-id="0020" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.edad.0020"/></span>';
        if (franja == "2025") return '<span data-id="2025" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.edad.2025"/></span>';
        if (franja == "2530") return '<span data-id="2530" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.edad.2530"/></span>';
        if (franja == "3035") return '<span data-id="3035" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.edad.3035"/></span>';
        if (franja == "3540") return '<span data-id="3540" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.edad.3540"/></span>';
        if (franja == "4045") return '<span data-id="4045" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.edad.4045"/></span>';
        if (franja == "4550") return '<span data-id="4550" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.edad.4550"/></span>';
        if (franja == "5055") return '<span data-id="5055" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.edad.5055"/></span>';
        if (franja == "5560") return '<span data-id="5560" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.edad.5560"/></span>';
        if (franja == "6000") return '<span data-id="6000" data-puesto="' + puesto + '"><liferay-ui:message key="estadisticas.edad.6000"/></span>';
        return franja;
    }
</script>