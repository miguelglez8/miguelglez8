<%@ include file="/init.jsp" %>

<c:set var="seccionId" value="registro"/>

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

                <div class="row align-items-start mb-4">
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label">
                            <liferay-ui:message key="estadisticas.registro.conceptos"/>
                        </label>
                        <textarea name="textarea_${seccionId}" id="textarea_${seccionId}_conceptos"></textarea>
                    </div>
                </div>
                <div class="row align-items-start mb-4">
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label">
                            <liferay-ui:message key="estadisticas.registro.promedioGP"/>
                        </label>
                        <textarea name="textarea_${seccionId}" id="textarea_${seccionId}_promedioGP"></textarea>
                    </div>
                </div>
                <div class="row align-items-start mb-4">
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label">
                            <liferay-ui:message key="estadisticas.registro.medianaGP"/>
                        </label>
                        <textarea name="textarea_${seccionId}" id="textarea_${seccionId}_medianaGP"></textarea>
                    </div>
                </div>
                <div class="row align-items-start mb-4">
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label">
                            <liferay-ui:message key="estadisticas.registro.promedioEQ"/>
                        </label>
                        <textarea name="textarea_${seccionId}" id="textarea_${seccionId}_promedioEQ"></textarea>
                    </div>
                </div>
                <div class="row align-items-start mb-4">
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label">
                            <liferay-ui:message key="estadisticas.registro.medianaEQ"/>
                        </label>
                        <textarea name="textarea_${seccionId}" id="textarea_${seccionId}_medianaEQ"></textarea>
                    </div>
                </div>
                <div class="row align-items-start mb-4">
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label">
                            <liferay-ui:message key="estadisticas.registro.promedioPT"/>
                        </label>
                        <textarea name="textarea_${seccionId}" id="textarea_${seccionId}_promedioPT"></textarea>
                    </div>
                </div>
                <div class="row align-items-start mb-4">
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label">
                            <liferay-ui:message key="estadisticas.registro.medianaPT"/>
                        </label>
                        <textarea name="textarea_${seccionId}" id="textarea_${seccionId}_medianaPT"></textarea>
                    </div>
                </div>
                <div class="row align-items-start mb-4">
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label">
                            <liferay-ui:message key="estadisticas.registro.promedioVT"/>
                        </label>
                        <textarea name="textarea_${seccionId}" id="textarea_${seccionId}_promedioVT"></textarea>
                    </div>
                </div>
                <div class="row align-items-start mb-4">
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label">
                            <liferay-ui:message key="estadisticas.registro.medianaVT"/>
                        </label>
                        <textarea name="textarea_${seccionId}" id="textarea_${seccionId}_medianaVT"></textarea>
                    </div>
                </div>

                <div class="row align-items-start mb-4">
                    <div class="form-group col-md-12 col-12">
                        <label class="control-label"><liferay-ui:message key="estadisticas.comentario.estadistica"/></label>
                        <textarea name="textarea_${seccionId}" id="textarea_${seccionId}"></textarea>
                    </div>
                </div>

                <div class="row align-items-start mb-4 d-none">
                    <div class="prv-form form-group col-md-6 col-6 required">
                        <fieldset>
                            <legend class="control-label"><liferay-ui:message key="estadisticas.aplicarMedidad"/></legend>
                            <div class="checksBorder">
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="medidasRegistro" id="medidasRegistro1" value="0" type="radio">
                                    <label class="form-check-label" for="medidasRegistro1"><liferay-ui:message key="estadisticas.si"/></label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="medidasRegistro" id="medidasRegistro2" value="1" type="radio">
                                    <label class="form-check-label" for="medidasRegistro2"><liferay-ui:message key="estadisticas.no"/></label>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>

<portlet:resourceURL id="findRegistro" var="findRegistroURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>
<script>

    $(document).ready( function () {
    	
        $.ajax({
            url: '${findRegistroURL}',
            dataType: 'json',
            type: 'get',
            success: function(json){

                <c:if test="<%= !hasRole %>">
                    $("#seccion-${seccionId} :input").prop("disabled", true);
                </c:if>

                createEditor("${seccionId}_conceptos", json.conceptos, saveRegistro);
                createEditor("${seccionId}_promedioGP", json.promedioGP, saveRegistro);
                createEditor("${seccionId}_medianaGP", json.medianaGP, saveRegistro);
                createEditor("${seccionId}_promedioEQ", json.promedioEQ, saveRegistro);
                createEditor("${seccionId}_medianaEQ", json.medianaEQ, saveRegistro);
                createEditor("${seccionId}_promedioPT", json.promedioPT, saveRegistro);
                createEditor("${seccionId}_medianaPT", json.medianaPT, saveRegistro);
                createEditor("${seccionId}_promedioVT", json.promedioVT, saveRegistro);
                createEditor("${seccionId}_medianaVT", json.medianaVT, saveRegistro);
                createEditor("${seccionId}", json.comentario, saveRegistro);

                $('[name=medidasRegistro]').on('change', function() {
                    saveRegistro('1');
                });
                $('input:radio[name=medidasRegistro]').filter('[value=' + json.medida + ']').prop('checked', true);

            }
        });

    });

    function saveRegistro(changeInputRadio) {
        var json = {};
            json["conceptos"] =  CKEDITOR.instances.textarea_${seccionId}_conceptos.getData();
            json["promedioGP"] =  CKEDITOR.instances.textarea_${seccionId}_promedioGP.getData();
            json["medianaGP"] =  CKEDITOR.instances.textarea_${seccionId}_medianaGP.getData();
            json["promedioEQ"] =  CKEDITOR.instances.textarea_${seccionId}_promedioEQ.getData();
            json["medianaEQ"] =  CKEDITOR.instances.textarea_${seccionId}_medianaEQ.getData();
            json["promedioPT"] =  CKEDITOR.instances.textarea_${seccionId}_promedioPT.getData();
            json["medianaPT"] =  CKEDITOR.instances.textarea_${seccionId}_medianaPT.getData();
            json["promedioVT"] =  CKEDITOR.instances.textarea_${seccionId}_promedioVT.getData();
            json["medianaVT"] =  CKEDITOR.instances.textarea_${seccionId}_medianaVT.getData();
            json["comentario"] = CKEDITOR.instances.textarea_${seccionId}.getData();
            json["medida"] = $('input[name=medidasRegistro]:checked').val();

        saveEstadistica(json, '<%= PlanIgualdadEstadisticasPortletKeys.REGISTRO %>', changeInputRadio);
    }

</script>