<%@ include file="/init.jsp" %>

<portlet:resourceURL id="saveEstadistica" var="saveEstadisticaURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>


<portlet:resourceURL id="findPuestos" var="findPuestosURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>

<portlet:resourceURL id="saveCanvas" var="saveCanvasURL">
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
</portlet:resourceURL>

<portlet:resourceURL id="refreshToken" var="refreshTokenURL"></portlet:resourceURL>

<script>
	var plantillaSave = false;
	var areaPuestoSave = false;
    var dataSeccion = new Map();
    var puestos;
    var messageSave="${messageSave}"
    var send=true;
    var radio='0';

    $.ajax({
        url: '${findPuestosURL}',
        dataType: 'json',
        success: function(data) {
            puestos = data.puestos;
        }
    });

    function showMessage(){
        if(messageSave=="ok"){
          $('#mostrarMensaje').css("display","block");
        }else if(messageSave=="ko"){
          $('#mostrarMensaje').css("display","block");
          $('#tipoMensaje').addClass("alert-danger");
          $('#tipoMensaje').removeClass("alert-success");
          $('#mensajeGuardado').html('Error al guardar las estadisticas')
        }
    }
     $(document).ready(function(){


         var warning = true;
         window.onbeforeunload = function() {
             if (warning) {
                return "You have made changes on this page that you have not yet confirmed. If you navigate away from this page you will lose your unsaved changes";
             }
         }
         $('.butonPrentCollapsed').click(function(e){
                 var index = $('.butonPrentCollapsed').index(this);
                 var buttonParent = $('.butonPrentCollapsed');
                 for(var x=0; x<buttonParent.length; x++){
                     if(buttonParent[x].getAttribute('aria-expanded')==='true'){
                          refrescarTokenConnection();
                          if(dataSeccion.size>0){
                              dataSeccion.forEach(function(value, key) {
                                    sendEstadisticas(value,key,radio);
                               });
                           }
                     }
                 }
          })
        setInterval(refrescarTokenRecursivo, 240*1000)
        showMessage()
        $('#closeMessage').click(function(){
            $('#mostrarMensaje').css("display","none");
        })

        const elementosObservados = $('.butonPrentCollapsed');
        const observer = new MutationObserver(function(mutations) {
         mutations.forEach(function(mutation) {
              if (mutation.type === 'attributes' && mutation.attributeName === 'aria-expanded' && mutation.oldValue === 'false' && $(mutation.target).attr('aria-expanded') === 'true') {
                // El valor de aria-expanded cambió de false a true
                var buttonParent = $('.butonPrentCollapsed');
                for(var x=0; x<buttonParent.length; x++){
                     if(buttonParent[x].getAttribute('data-target')===mutation.target.getAttribute('data-target')){
                        if(x>0){
                            $('.butonPrentCollapsed')[x-1].focus();
                        }
                     }
                }
              }
            });
          });
        // Observa cambios en el atributo 'aria-expanded' de cada elemento
        elementosObservados.each(function() {
              observer.observe(this, { attributes: true, attributeOldValue: true });

        });


     })


    function saveImgCanvas(seccionId, canvasImage, updateCanvas, nCanvas, partImg, totalPartes){
    	<c:if test="<%= hasRole %>">
	        $.ajax({
	            url: '${saveCanvasURL}',
	            dataType: 'json',
	            data: {
	                'partImg' : partImg,
	                'totalPartes' : totalPartes,
	                'seccionId' : seccionId,
	                'canvasImage' : canvasImage,
	                'updateCanvas' : updateCanvas,
	                'nCanvas' : nCanvas
	            },
	            type: 'post'
	        }).done(function(data){

	        });
	        
	        if(totalPartes!=0){

		        	$('#prv-loading-container').removeClass('d-flex');

	        }
	        
	    </c:if>
    }

    function saveDataEstadistica(estadistica, seccionId) {
            <c:if test="<%= hasRole %>">

                dataSeccion.set(seccionId,estadistica)

            </c:if>
        }

    function sendEstadisticas(estadistica,seccionId,changeRadioButton){

        $.ajax({
            url: '${saveEstadisticaURL}',
            dataType: 'json',
            data: {
                'estadistica' : JSON.stringify(estadistica),
                'seccionId' : seccionId,
                'changeRadioButton' : changeRadioButton
            },
            type: 'post',
            beforeSend: function() {
                $('#prv-loading-async').removeClass('hide');
            },
            complete: function(data) {
                dataSeccion = new Map();
                $('#prv-loading-async').addClass('hide')
                radio='0'
            },
            error: function(data){
                dataSeccion = new Map();
                radio='0'
                alert("Error al guardar las estadisticas, por favor realice algun cambio e intentelo de nuevo");
            }
        });

    }

    function refrescarTokenConnection(){
        $.ajax({
            url: '${refreshTokenURL}',
            dataType: 'json',
            type: 'post',
            success: function(data) {
                console.log('llamada a refresh token ok');
            },
            error: function(data){

            }
        });
    }

    function refrescarTokenRecursivo(){
            $.ajax({
                url: '${refreshTokenURL}',
                dataType: 'json',
                type: 'post',
                success: function(data) {
                    console.log('llamada a refresh token ok');
                },
                error: function(data){
                    console.log('Es posible que la sesion este caducada')
                }
            });
    }

    function saveEstadistica(estadistica, seccionId, changeRadioButton) {
        <c:if test="<%= hasRole %>">

            dataSeccion.set(seccionId,estadistica)
            radio=changeRadioButton;
        </c:if>
    }

    <c:if test="<%= !hasRole %>">
        CKEDITOR.config.readOnly = true;
    </c:if>

</script>
<script src="<%=request.getContextPath()%>/js/main.js"></script>
<script src="<%=request.getContextPath()%>/js/maths.js"></script>
<script src="<%=request.getContextPath()%>/js/tables.js"></script>
<script src="<%=request.getContextPath()%>/js/charts.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>

<div class="content">
    <div class="mb-3" id="mostrarMensaje" style="display:none">
        <div class="alert alert-dismissible alert-success" role="alert" id="tipoMensaje">
            <div class="alert-autofit-row autofit-row">
                <div class="autofit-col">
                    <div class="autofit-section">
                        <span class="alert-indicator">
                            <svg class="lexicon-icon lexicon-icon-check-circle-full" role="presentation">
                                <use xlink:href="/o/adeplus-theme/images/clay/icons.svg#check-circle-full"></use>
                            </svg>
                        </span>
                    </div>
                </div>
            <div class="autofit-col autofit-col-expand">
                <div class="autofit-section">
                    <div><strong class="lead" id="mensajeGuardado">Se han guardado correctamente las estadisticas.</strong></div>
                </div>
            </div>
        </div>
        <button aria-label="Close" class="close" id="closeMessage" type="button"><svg class="lexicon-icon lexicon-icon-times" role="presentation">
            <use xlink:href="/o/adeplus-theme/images/clay/icons.svg#times"></use></svg>
        </button>
        </div>
    </div>
    <div class="formulario">
        <div class="accordion" id="cuestionario">

            <jsp:include page="/ocupacionSector.jsp" />
            <jsp:include page="/plantilla.jsp" />
            <jsp:include page="/areaPuesto.jsp" />
            <jsp:include page="/edad.jsp" />
            <jsp:include page="/puestoEdad.jsp" />
            <jsp:include page="/puestoAntiguedad.jsp" />
            <jsp:include page="/puestoEstudios.jsp" />
            <jsp:include page="/puestoDependientes.jsp" />
            <jsp:include page="/reduccionJornada.jsp" />
            <jsp:include page="/contratoJornada.jsp" />
            <jsp:include page="/horariosTurnos.jsp" />
            <jsp:include page="/ingresos.jsp" />
            <jsp:include page="/ceses.jsp" />
            <jsp:include page="/situacionIlt.jsp" />
            <jsp:include page="/promocion.jsp" />
            <jsp:include page="/formacion.jsp" />
            <jsp:include page="/valoracion.jsp" />
            <jsp:include page="/registro.jsp" />
            <jsp:include page="/auditoria.jsp" />

            <button id="prv-loading-async" class="btn btn-primary hide" type="button" disabled>
              <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
              <span class="sr-only">Loading...</span>
            </button>
        </div>
    </div>
</div>

<div id="prv-loading-container" class="justify-content-center align-items-center">
    <div class="loading-animation d-block"></div>
</div>