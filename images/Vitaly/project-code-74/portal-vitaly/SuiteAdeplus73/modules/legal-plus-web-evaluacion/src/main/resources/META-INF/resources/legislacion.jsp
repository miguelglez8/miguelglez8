<%-- DATOS GENERALES --%>
<div class="prv-search prv-form">

    <div class="row mb-3">
        <div class="col-md-6 col-12">
             <h5><liferay-ui:message key="legislacion.view.datosGenerales" /></h5>
        </div>
        <div class="col-md-6 col-12 text-right">
            <a class="prv-iconLinks prv-iconLinks__import" href="<%= legislacion.getEnlace() %>" target="_blank">
                <liferay-ui:message key="legislacion.view.descargar" />
            </a>
        </div>
    </div>
    <div class="row align-items-start mb-1">

        <%-- LEGISLACION --%>
        <div class="col-lg-12 col-md-12 col-12">
            <div class="form-group">
                <label for="legislacion"><liferay-ui:message key="legislacion.view.legislacion" /></label>
                <textarea class="form-control" id="legislacion" rows="3" disabled="disabled"><%= legislacion.getNombre() %></textarea>
            </div>
        </div>

    </div>
    <div class="row align-items-start mb-4">

        <%-- FAMILIA --%>
        <div class="col-lg-4 col-md-6 col-12">
            <label for="familia"> <liferay-ui:message key="legislacion.view.familia" /></label>

            <input type="text" id="familia" disabled="disabled" placeholder="<%= familias %>" >
        </div>

       <%-- AMBITO --%>
       <div class="col-lg-4 col-md-6 col-12">
           <label for="ambito"> <liferay-ui:message key="legislacion.view.ambito" /></label>

           <input type="text" id="ambito" disabled="disabled" placeholder="<%= ambito %>" >
       </div>

       <%-- TIPO --%>
       <div class="col-lg-4 col-md-6 col-12">
            <label for="tipo"> <liferay-ui:message key="legislacion.view.tipo" /></label>
            <input type="text" id="tipo" disabled="disabled" placeholder="<%= tipo %>">
       </div>



    </div>
    <div class="row align-items-start mb-4">

       <%-- ETIQUETAS --%>
       <div class="col-lg-4 col-md-6 col-12">
            <label for="etiquetas"> <liferay-ui:message key="legislacion.view.etiquetas" /></label>
            <input type="text" id="etiquetas" placeholder="${etiquetas}" disabled="disabled">
       </div>

       <%-- FECHAS --%>
       <div class="col-lg-3 col-md-6 col-12 d-flex">
           <div class="col-md-6 col-12 pl-0">
               <label for="publicacion"> <liferay-ui:message key="legislacion.view.fecha.publicacion" /></label>
               <input type="text" id="publicacion" placeholder="<%= publicacion %>" disabled="disabled">
           </div>

           <div class="col-md-6 col-12 pr-0">
               <label for="derogacion"><liferay-ui:message key="legislacion.view.fecha.derogacion" /></label>
               <input type="text" id="derogacion" placeholder="<%= derogacion %>" disabled="disabled">
           </div>
       </div>

        <%-- CCAA --%>
         <div class="col-lg-5 col-md-6 col-12 d-flex">
              <div class="col-md-6 col-12">
                  <label for="tipo"> <liferay-ui:message key="empresa.legislaciones.view.ccaa" /></label>
                  <input type="text" id="ccaa" disabled="disabled" placeholder="<%= ccaa %>">
             </div>

             <div class="col-md-6 col-12">
                  <label for="tipo"> <liferay-ui:message key="empresa.legislaciones.view.ayuntamientos" /></label>
                  <input type="text" id="municipioLegislacion" disabled="disabled" placeholder="<%= municipioLegislacion %>">
             </div>
        </div>
    </div>
    <div class="row align-items-start mb-4">

        <%-- DESCRIPCION --%>
        <div class="col-lg-12 col-md-12 col-12">
            <div class="form-group">
                <label for="descripcion"><liferay-ui:message key="legislacion.view.descripcion" /></label>
                <textarea class="form-control" id="descripcion" rows="3" disabled="disabled"><%= legislacion.getDescripcion() %></textarea>
            </div>
        </div>

    </div>
    <c:if test="${not empty requisitos}">
        <div class="row align-items-start mb-4">

            <%-- REQUISITOS --%>
            <div class="col-lg-12 col-md-12 col-12">
                <div class="card">
                    <div class="card-header collapsed" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false">
                        <span class="title"><liferay-ui:message key="legislacion.view.requisitos" /></span>
                        <span class="accicon"><i class="icon-list"></i></span>
                    </div>
                    <div id="collapseOne" class="collapse" data-parent="#accordionExample" style="">
                        <div class="card-body">
                            <ul class="prv-list">
                                <c:forEach items="${requisitos}" var="requisito">
                                    <li class="mb-3">${requisito.descripcion}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </c:if>

</div>