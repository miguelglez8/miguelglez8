<%@ include file="/init.jsp" %>

<main class="content">
    <div class="formulario">

        <div class="title-group">
            <div class="row">
                <div class="col-md-6 col-12">
                    <h2><liferay-ui:message key="indicadores.title"/></h2>
                </div>
            </div>
        </div>

        <div class="prv-search prv-form">

            <div class="row align-items-start mb-4">
                <div class="col-md-12">
                    <p><liferay-ui:message key="indicadores.disennoPlan"/></p>
                </div>
                <div class="col-md-10">
                    <div class="progress">
                        <div class="progress-bar progress-bar-striped bg-diseino" role="progressbar" style="width: ${indicadorDisennoPlan}%;"
                             aria-valuenow="${indicadorDisennoPlan}" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                </div>
                <div class="col-md-1 text-center p-2">
                    <p class="h6">${indicadorDisennoPlan}%</p>
                </div>
                <div class="col-md-1 text-center">
                    <a class="ico-acciones-tabla" href="${hitosUrl}">
                        <span class="icon-link"></span>
                    </a>
                </div>
            </div>

            <div class="row align-items-start mb-4">
                <div class="col-md-12">
                    <p><liferay-ui:message key="indicadores.seguimientoPlan"/></p>
                </div>
                <div class="col-md-10">
                    <div class="progress">
                        <div class="progress-bar progress-bar-striped bg-seguimiento" role="progressbar" style="width: ${indicadorSeguimientoPlan}%;"
                             aria-valuenow="${indicadorSeguimientoPlan}" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                </div>
                <div class="col-md-1 text-center p-2">
                    <p class="h6">${indicadorSeguimientoPlan}%</p>
                </div>
                <div class="col-md-1 text-center">
                   <a class="ico-acciones-tabla" href="${seguimientoUrl}">
                       <span class="icon-link"></span>
                   </a>
                </div>
            </div>

            <div class="row align-items-start mb-4">
                <div class="col-md-12">
                    <p><liferay-ui:message key="indicadores.evaluacionPlan"/></p>
                </div>
                <div class="col-md-10">
                    <div class="progress">
                        <div class="progress-bar progress-bar-striped bg-evaluacion" role="progressbar" style="width: ${indicadorEvaluacionPlan}%;"
                             aria-valuenow="${indicadorEvaluacionPlan}" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                </div>
                <div class="col-md-1 text-center p-2">
                    <p class="h6">${indicadorEvaluacionPlan}%</p>
                </div>
                <div class="col-md-1 text-center">
                    <a class="ico-acciones-tabla" href="${evaluacionUrl}">
                        <span class="icon-link"></span>
                    </a>
                </div>
            </div>

        </div>

    </div>
</main>