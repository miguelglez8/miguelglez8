<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="valoracion"/>

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
                                <label><liferay-ui:message key="estadistica.valoracion.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}" class="display" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th data-id="puesto"><liferay-ui:message key="estadisticas.valoracion.puesto"/></th>
                                    <th data-id="convenio"><liferay-ui:message key="estadisticas.valoracion.convenio"/></th>
                                    <th data-id="area"><liferay-ui:message key="estadisticas.valoracion.area"/></th>
                                    <th data-id="departamento"><liferay-ui:message key="estadisticas.valoracion.departamento"/></th>
                                    <th data-id="centro"><liferay-ui:message key="estadisticas.valoracion.centro"/></th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>

                <div class="row d-flex justify-content-center mb-4">
                    <div class="col-md-12">
                        <div class="capaDatosTabla">
                            <div class="dt-info">
                                <label><liferay-ui:message key="estadistica.valoracion.agrupaciones.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}_agrupaciones" class="display" style="width:100%; display:none;">
                            <thead>
                                <tr>
                                    <th data-id="puesto"><liferay-ui:message key="estadisticas.valoracion.puesto"/></th>
                                    <th data-id="categoria"><liferay-ui:message key="estadisticas.valoracion.categoria"/></th>
                                    <th data-id="nHombres"><liferay-ui:message key="estadisticas.valoracion.nHombres"/></th>
                                    <th data-id="nMujeres"><liferay-ui:message key="estadisticas.valoracion.nMujeres"/></th>
                                    <th data-id="agrupacion"><liferay-ui:message key="estadisticas.valoracion.agrupacion"/></th>
                                    <th data-id="puntos"><liferay-ui:message key="estadisticas.valoracion.puntos"/></th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>

                <div class="row d-flex justify-content-center mb-4">
                    <div class="col-12 col-md-12">
                        <div class="capaDatosTabla">
                            <div class="dt-info">
                                <label><liferay-ui:message key="estadistica.valoracion.resumen.table.title"/> </label>
                            </div>
                        </div>

                        <table id="tabla_${seccionId}_resumen" class="display">
                            <thead>
                                <tr>
                                    <th><liferay-ui:message key="estadisticas.valoracion.agrupacion"/></th>
                                    <th><liferay-ui:message key="estadisticas.valoracion.puesto"/></th>
                                    <th><liferay-ui:message key="estadisticas.valoracion.puntos"/></th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>

                <div class="row d-flex justify-content-center mb-4">
                    <div class="col-12 col-md-8 chart-container" style="position: relative; ">
                        <div class="col-md-12 text-center">
                            <p><liferay-ui:message key="estadisticas.valoracion.puntuacion.title"/></p>
                            <span class="badge badge-masculinizado"><liferay-ui:message key="estadisticas.valoracion.M"/></span>
                            <span class="badge badge-feminizado"><liferay-ui:message key="estadisticas.valoracion.F"/></span>
                            <span class="badge badge-equilibrado"><liferay-ui:message key="estadisticas.valoracion.E"/></span>
                        </div>
                        <canvas id="estadistica_${seccionId}_puntuacion"></canvas>
                    </div>
                    <div class="form-group col-md-12 col-12 border-separate">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario.estadistica"/></label>
                        <textarea name="textarea_${seccionId}_comentario_puntuacion" id="textarea_${seccionId}_comentario_puntuacion"></textarea>
                    </div>
                    <div class="col-12 col-md-8">
                        <canvas id="estadistica_${seccionId}_agrupaciones"></canvas>
                    </div>
                    <div class="form-group col-md-12 col-12 border-separate">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario.estadistica"/></label>
                        <textarea name="textarea_${seccionId}_comentario_agrupaciones" id="textarea_${seccionId}_comentario_agrupaciones"></textarea>
                    </div>
                </div>

                <div class="row align-items-start mb-4">
                	<div class="form-group col-md-12 col-12">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario.categorias"/></label>
                        <textarea name="textarea_${seccionId}_comentario_categorias" id="textarea_${seccionId}_comentario_categorias"></textarea>
                    </div>
                    
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario.sexo"/></label>
                        <textarea name="textarea_${seccionId}_comentario_sexo" id="textarea_${seccionId}_comentario_sexo"></textarea>
                    </div>
                    
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
                                    <input class="form-check-input" name="medidasValoracion" id="medidasValoracion1" value="0" type="radio">
                                    <label class="form-check-label" for="medidasValoracion1"><liferay-ui:message key="estadisticas.si"/></label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="medidasValoracion" id="medidasValoracion2" value="1" type="radio">
                                    <label class="form-check-label" for="medidasValoracion2"><liferay-ui:message key="estadisticas.no"/></label>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>

<portlet:resourceURL id="findValoracion" var="findValoracionURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>
<script>
	var updateTableValoracion ="1";
    $(document).ready( function () {
    	$('[data-toggle="collapse"]').on("click", function () {
    		if(updateTableValoracion=="0"){
				$('#prv-loading-container').addClass('d-flex');
   	    		seLlamoFuncionCanvas=true;
   	    		html2canvas(document.getElementById("estadistica_valoracion_puntuacion")).then(function (canvas){
   	    			saveImgCanvas('<%= PlanIgualdadEstadisticasPortletKeys.VALORACION %>', canvas.toDataURL("image/jpeg"), '0', 'graphic16.1', 1, 2);
   		    	});
   	    		
   	    		html2canvas(document.getElementById("estadistica_valoracion_agrupaciones")).then(function (canvas){
   	    			saveImgCanvas('<%= PlanIgualdadEstadisticasPortletKeys.VALORACION %>', canvas.toDataURL("image/jpeg"), '0', 'graphic16.2', 1, 0);
   		    	});
                updateTableValoracion="1";
    		}
        });
    	
        $('#tabla_${seccionId}').on('preXhr.dt', function ( e, settings, data ) {
            $("#tabla_${seccionId}").show();
        }).DataTable({
            dom: "",
            "ajax": {
                "url": "<%= findValoracionURL %>",
                "dataSrc": function ( json ) {
                    return json.valoraciones;
                }
            },
            "language": {
                "sEmptyTable": "<liferay-ui:message key="planigualdad.view.datatable.sEmptyTable"/>",
            },
            "paging": false,
            "columns": [
                {"data" : "puesto"},
                {"data" : "convenio"},
                {"data" : "area"},
                {"data" : "departamento"},
                {"data" : "centro"}
            ],
            "columnDefs": [
                {
                    "targets"  : [0],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<span data-id="' + data + '">' + puestos.find(puesto => puesto.id === data + "").nombre + '</span>';
                              }
                },
                {
                    "targets"  : [1],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<input type="text" class="valoracion-field form-control" value="' + data + '" />';
                              }
                },
                {
                    "targets"  : [2],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return data;
                              }
                },
                {
                    "targets"  : [3],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<input type="text" class="valoracion-field form-control" value="' + data + '" />';
                              }
                },
                {
                    "targets"  : [4],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<input type="text" class="valoracion-field form-control" value="' + data + '" />';
                              }
                }
            ],
            "initComplete": function(settings, json) {

                createEditor("${seccionId}", json.valoracion, saveValoraciones);
                createEditor("${seccionId}_comentario_puntuacion", json.comentarioPuntuacion, saveValoraciones);
                createEditor("${seccionId}_comentario_agrupaciones", json.comentarioAgrupaciones, saveValoraciones);
                createEditor("${seccionId}_comentario_categorias", json.comentarioCategorias, saveValoraciones);
                createEditor("${seccionId}_comentario_sexo", json.comentarioSexo, saveValoraciones);

                $('.valoracion-field').on('input', function (e) {
                    saveValoraciones()
                });

                $('[name=medidasValoracion]').on('change', function() {
                    saveValoraciones('1');
                });
                $('input:radio[name=medidasValoracion]').filter('[value=' + json.medida + ']').prop('checked', true);

            }
        });

        $('#tabla_${seccionId}_agrupaciones').on('preXhr.dt', function ( e, settings, data ) {
            $("#tabla_${seccionId}_agrupaciones").show();
        }).DataTable({
            dom: "",
            "ajax": {
                "url": "<%= findValoracionURL %>",
                "dataSrc": function ( json ) {
                    return json.distribuciones;
                }
            },
            "language": {
                "sEmptyTable": "<liferay-ui:message key="planigualdad.view.datatable.sEmptyTable"/>",
            },
            "paging": false,
            "columns": [
                {"data" : "puesto"},
                {"data" : "categoria"},
                {"data" : "nHombres"},
                {"data" : "nMujeres"},
                {"data" : "agrupacion"},
                {"data" : "puntos"}
            ],
            "columnDefs": [
                {
                    "targets"  : [0],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<span data-id="' + data + '">' + puestos.find(puesto => puesto.id === data + "").nombre + '</span>';
                              }
                },
                {
                    "targets"  : [1],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return getCategoria(data);
                              }
                },
                {
                    "targets"  : [4],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<input type="text" class="valoracion-agrupacion-field form-control" value="' + data + '" />';
                              }
                },
                {
                    "targets"  : [5],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return '<input type="text" class="valoracion-agrupacion-field form-control" value="' + data + '" />';
                              }
                }
            ],
            "initComplete": function(settings, json) {

                <c:if test="<%= !hasRole %>">
                    $("#seccion-${seccionId} :input").prop("disabled", true);
                </c:if>

                var colorM = '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_HOMBRES %>';
                var colorF = '<%= PlanIgualdadEstadisticasPortletKeys.COLOR_MUJERES %>';
                var colorE = '#3e95cd';
                var labelHombres = '<liferay-ui:message key="estadisticas.hombres"/>';
                var labelMujeres = '<liferay-ui:message key="estadisticas.mujeres"/>';
                var titleAgrupaciones = '<liferay-ui:message key="estadisticas.valoracion.agrupacion.title"/>';

                $('.valoracion-agrupacion-field').on('input', function (e) {
                    saveValoraciones();
                    updateTableValoracion = "0";
                    createAgrupacionesBarChart("${seccionId}", labelHombres, labelMujeres, colorM, colorF, titleAgrupaciones);
                    createPuntuacionBarChart("${seccionId}", colorM, colorF, colorE);
                    createResumen(false);
                });

                createAgrupacionesBarChart("${seccionId}", labelHombres, labelMujeres, colorM, colorF, titleAgrupaciones);
                createPuntuacionBarChart("${seccionId}", colorM, colorF, colorE);

                createResumen(json.distribuciones.length == 0);

            }
        });

    });

    function saveValoraciones(changeInputRadio) {
        var valoraciones = tableToJson("${seccionId}");
        var agrupaciones = tableToJson("${seccionId}_agrupaciones");

        var json = {};
            json["valoraciones"] = JSON.parse(valoraciones).data;
            json["distribuciones"] = JSON.parse(agrupaciones).data;
            json["valoracion"] = CKEDITOR.instances.textarea_${seccionId}.getData();
            json["comentarioPuntuacion"] = CKEDITOR.instances.textarea_${seccionId}_comentario_puntuacion.getData();
            json["comentarioAgrupaciones"] = CKEDITOR.instances.textarea_${seccionId}_comentario_agrupaciones.getData();
            json["comentarioCategorias"] = CKEDITOR.instances.textarea_${seccionId}_comentario_categorias.getData();
            json["comentarioSexo"] = CKEDITOR.instances.textarea_${seccionId}_comentario_sexo.getData();
            json["medida"] = $('input[name=medidasValoracion]:checked').val();

        saveEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.VALORACION %>',changeInputRadio); 
    }
 
    function getCategoria(categoria) {
        return '<span data-id="' + categoria + '">' + getCategoriaLabel(categoria) + '</span>';
    }

    function getCategoriaLabel(categoria) {
        if (categoria == "M") return '<liferay-ui:message key="estadisticas.valoracion.M"/>';
        if (categoria == "E") return '<liferay-ui:message key="estadisticas.valoracion.E"/>';
        if (categoria == "F") return '<liferay-ui:message key="estadisticas.valoracion.F"/>';
        else return categoria;
    }

    function createResumen(init) {
        var json = JSON.parse( tableToJson("${seccionId}_agrupaciones") );

        $('#tabla_${seccionId}_resumen').dataTable().fnClearTable();
        $('#tabla_${seccionId}_resumen').dataTable().fnDestroy();
        $('#tabla_${seccionId}_resumen').DataTable( {
            dom: "",
            data:  init ? [] : json.data,
            columns: [
                { data: "agrupacion" },
                { data: "puesto" },
                { data: "puntos" }
            ],
            "language": {
                "sEmptyTable": "<liferay-ui:message key="planigualdad.view.datatable.sEmptyTable"/>",
            },
            "paging": false,
            "columnDefs": [
                {
                    "targets"  : [0],
                    "visible" : false
                },
                {
                    "targets"  : [1],
                    "orderable" : false,
                    "render": function (data, type, row) {
                                 return puestos.find(puesto => puesto.id === data + "").nombre;
                              }
                },
            ],
            orderFixed: [0, 'asc'],
            rowGroup: {
                dataSrc: "agrupacion",
                startRender: function (rows, group) {
                    return $('<tr/>')
                        .append('<td colspan="2"><liferay-ui:message key="estadisticas.valoracion.agrupacion"/> ' + group + '</td>');
                }
            }
        } );
    }

</script>