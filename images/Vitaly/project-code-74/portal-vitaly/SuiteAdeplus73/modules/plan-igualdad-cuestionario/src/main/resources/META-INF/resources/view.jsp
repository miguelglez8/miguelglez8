<%@ include file="/init.jsp" %>

<div class="content">
    <div class="formulario">

        <div class="accordion" id="cuestionario">
            <c:forEach var="seccion" items="${secciones}">
                <div class="card">
                    <div class="card-header card-header-icon" id="heading${seccion.key}">
                        <h2 class="mb-0">
                            <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse"
                                    data-target="#collapse${seccion.key}" aria-expanded="true" aria-controls="collapseOne">
                                <liferay-ui:message key="cuestionario.seccion${seccion.key}" />
                            </button>
                        </h2>
                    </div>
                    <div id="collapse${seccion.key}" class="collapse" aria-labelledby="heading${seccion.key}" data-parent="#cuestionario">
                        <form id="cuestionario-${seccion.key}">
                            <div class="card-body" id="seccion-${seccion.key}">
                                <c:forEach items="${seccion.value}" var="value">
                                    <c:if test="${value.tipo eq 'table'}">
                                        <plan:datatable
                                                id="${value.preguntaId}"
                                                form="cuestionario_form"
                                                value="${respuestas[0].respuestas}"
                                                title="cuestionario.table.title${value.preguntaId}"
                                                tooltip="cuestionario.table.tooltip${value.preguntaId}"
                                                editTitle="cuestionario.table.edit.title${value.preguntaId}"
                                                deleteTitle="cuestionario.table.delete.title${value.preguntaId}"
                                        />
                                        <script>
                                            $(document).ready(function() {
                                                $('.prv-save-modal, .prv-delete-modal').on('click', function(){
                                                   var json = tableToJson('table_${value.preguntaId}');
                                                   saveCuestionario(JSON.parse(json), '${seccion.key}');
                                                });
                                                $('#adeplus .card-header .btn-link').toggleClass("collapsed");
                                            });
                                        </script>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>

    </div>
</div>

<%@include file="complete.jspf" %>

<portlet:resourceURL id="saveCuestionario" var="saveCuestionarioURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>

<script src="<%= request.getContextPath()%>/js/language_${locale}.js"></script>
<script src="<%= request.getContextPath()%>/js/main.js"></script>
<script>

    var saveCuestionario = function(cuestionario, seccionId) {
        $.ajax({
            url: '${saveCuestionarioURL}',
            dataType: 'json',
            data: {
                'respuestas' : JSON.stringify(cuestionario),
                'seccionId' : seccionId
            },
            type: 'post',
            success: function (result) {
                if (result.estado == 0){
                	$('#heading' + result.seccion).removeClass('prv-not-empty');
                	completeFunction(false);
                }
                else {
                	$('#heading' + result.seccion).addClass('prv-not-empty');
                	completeFunction(true);
                }
            }
        });
        
    }
    
    function completeFunction(completeData) {
	  var complete = completeData;
       $('.card-header-icon').each(function(){
	       	if(!$(this).hasClass('prv-not-empty')){
	       		complete = false;
	       	}
       });
       if(complete){        	
       		$('#completeModal').removeClass('hide');
	    	$('#completeModal').addClass('show');
       }
	}

    <c:forEach items="${preguntas}" var="pregunta">
        createField('${pregunta.preguntaId}', '${pregunta.seccionId}', '${pregunta.tipo}', '${pregunta.pregunta}');
    </c:forEach>

    <c:forEach items="${respuestas}" var="respuesta">
        fillField('${respuesta.seccionId}', '${respuesta.respuestas}');
        fillEstado('${respuesta.seccionId}', '${respuesta.estado}');
    </c:forEach>

    $(document).on('click', '.hide-modal', function(e) {
    	e.preventDefault();
    	$('#completeModal').addClass('hide');
    });
</script>