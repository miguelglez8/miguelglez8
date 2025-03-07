<%@ include file="/init.jsp" %>

<portlet:resourceURL id="auditNavigation" var="auditNavigationUrl"/>
<portlet:resourceURL id="auditApplication" var="auditApplicationUrl"/>
<script>

function captura_acceso_en_log(href){
    var link = href;
    $.ajax({
        type: "POST",
        url: '<%=auditNavigationUrl%>',
        data: { "link": link,
                "userId": themeDisplay.getUserId(),
                "url":themeDisplay.getLayoutURL()},
        success: function(response) {
          window.location = link;
        },
      });
    // PLAN B: el usuario puede acceder al enlace incluso si la llamada a AJAX falla o se retrasa
    setTimeout(function() {
        window.location = link;
    }, 1500);


    // Lanza la solicitud AJAX

}

function accesoApis(aplicacion){
    var aplicacionSeleccionada = aplicacion;
    $.ajax({
        type: "POST",
        url: '<%=auditApplicationUrl%>',
        data: { "aplicacion": aplicacionSeleccionada,
                "userId": themeDisplay.getUserId(),
                "url":themeDisplay.getLayoutURL()},
        success: function(response) {
          window.location = link;
        },
      });

}

$(document).ready(function() {
    var enlacesLateral=document.querySelectorAll(".list-group-item-action");
    var enlacesCajas=document.querySelectorAll(".btn-outline-light");

    enlacesLateral.forEach(link => {
      link.addEventListener('click', event => {
        const href = event.currentTarget.href;
        if(href.indexOf('#')<0){
        captura_acceso_en_log(href)
        }
      });
    });

    enlacesCajas.forEach(link => {
      link.addEventListener('click', event => {
        var padre=event.currentTarget.parentElement;
        var hermano=padre.previousElementSibling;

        if(event.currentTarget.parentElement.className.indexOf('dorso-active')>=0 && hermano.className.indexOf('frente-active')>=0){
            var aplicacion=hermano.innerText
            accesoApis(aplicacion);
        }
      });
    });
});


</script>