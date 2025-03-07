<%

List<Ayuntamiento> aytoList = (List<Ayuntamiento>) request.getAttribute(WebContratoPortletKeys.AYTOS);

%>

<div class="title-group">
    <div class="row">
        <div class="col-md-6 col-12">
            <h2><liferay-ui:message key="contract.view.title"/></h2>
        </div>
    </div>
</div>

<div class="content">
    <div class="formulario">
        <div class="form-content prv-search prv-form mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">

             <div class="row align-items-start mb-4">

                 <%-- TIPO --%>
                 <div class="col-md-6 col-12">
                     <fieldset>
                         <legend><liferay-ui:message key="contract.view.tipos"/></legend>
                         <div class="checksBorder">
                             <c:forEach items="<%=licenseList%>" var="curLicence">
                                 <div class="custom-control custom-radio mr-4">
                                     <input class="form-check-input" name="contractType" id="contractType1" value="${curLicence.name}" type="radio"
                                             disabled="disabled" ${curLicence.name eq license.name ? "checked" : ""}>
                                     <label class="form-check-label" for="contractType1">${curLicence.name}</label>
                                 </div>
                             </c:forEach>
                         </div>
                     </fieldset>
                     <ul class="prv-linkInfo-content">
                         <li>
                             <a href="#" class="contract-info">
                                 <i class="icon-info-sign"></i>
                                 <liferay-ui:message key="contrato.view.incluye"/>
                             </a>
                         </li>
                         <li>
                             <a href="${contactoPlanUrl}">
                                 <i class="icon-envelope"></i>
                                 <liferay-ui:message key="contrato.view.plan"/>
                             </a>
                         </li>
                     </ul>
                 </div>

                 <%-- NORMAS --%>
                 <div class="col-md-6 col-12">
                     <fieldset>
                         <legend><liferay-ui:message key="contract.view.normas"/></legend>
                         <div class="checksBorder">
                             <div class="input-checkbox-wrapper">
                                 <aui:input name="<%=WebContratoPortletKeys.FAMILIA_PRL%>" type="checkbox"
                                     label="contract.view.familia.prl" checked="<%= Validator.isNotNull(contract) ? contract.getFamilia().contains("0") : false %>"
                                     cssClass="field check-app" disabled="true"></aui:input>
                             </div>
                             <div class="input-checkbox-wrapper">
                                 <aui:input name="<%=WebContratoPortletKeys.FAMILIA_MEDIO_AMBIENTE%>" type="checkbox"
                                     label="contract.view.familia.medioambiente" checked="<%= Validator.isNotNull(contract) ? contract.getFamilia().contains("1") : false%>"
                                     cssClass="field check-app"  disabled="true"></aui:input>
                             </div>
                             <div class="input-checkbox-wrapper">
                                 <aui:input name="<%=WebContratoPortletKeys.FAMILIA_SEGURIDAD_INDUSTRIAL%>" type="checkbox"
                                     label="contract.view.familia.seguridad.industrial" checked="<%= Validator.isNotNull(contract) ? contract.getFamilia().contains("2") : false%>"
                                     cssClass="field check-app" disabled="true"></aui:input>
                             </div>
                             <div class="input-checkbox-wrapper">
                                 <aui:input name="<%=WebContratoPortletKeys.FAMILIA_SEGURIDAD_ALIMENTARIA%>" type="checkbox"
                                     label="contract.view.familia.seguridad.alimentaria" checked="<%= Validator.isNotNull(contract) ? contract.getFamilia().contains("3") : false%>"
                                     cssClass="field check-app" disabled="true"></aui:input>
                             </div>
                         </div>
                     </fieldset>
                     <ul class="prv-linkInfo-content">
                         <li>
                             <a href="${contactoFamiliasUrl}">
                                 <i class="icon-envelope"></i>
                                 <liferay-ui:message key="contrato.view.ampliar" />
                             </a>
                         </li>
                     </ul>
                 </div>

             </div>
             <div class="row align-items-start mb-4">

                 <%-- LEGISLACIONES --%>
                 <div class="col-md-6 col-12">
                     <fieldset>
                         <legend><liferay-ui:message key="contract.view.legislaciones"/></legend>
                         <div class="checksBorder">
                             <div class="input-checkbox-wrapper">
                                 <aui:input name="Europea" type="checkbox" label="contract.view.europea" disabled="true" cssClass="check-app" checked="true"></aui:input>
                             </div>
                             <div class="input-checkbox-wrapper">
                                 <aui:input name="Nacional" type="checkbox" label="contract.view.nacional" disabled="true" cssClass="check-app" checked="true"></aui:input>
                             </div>
                             <div class="input-checkbox-wrapper">
                                 <aui:input name="CCAAs" type="checkbox" label="contract.view.ccaa" disabled="true" cssClass="check-app" checked="true"></aui:input>
                             </div>
                             <div class="input-checkbox-wrapper">
                                 <aui:input name="Ayuntamientos" type="checkbox" label="contract.view.ayuntamientos" disabled="true" cssClass="check-app" checked="true"></aui:input>
                             </div>
                         </div>
                     </fieldset>
                 </div>

                 <%-- CONSULTOR --%>
                 <div class="col-md-6 col-12">
                     <label><liferay-ui:message key="contract.view.consultores"/></label>
                     <ul class="prv-list">
                         <c:forEach items="<%=consultants%>" var="consultant">
                            <%
                                ConsultorCompany consultor = (ConsultorCompany) pageContext.getAttribute("consultant");
                                User userConsultor = UserLocalServiceUtil.fetchUser(consultor.getUserId());
                            %>
                            <li>${consultant.name} ${consultant.lastName} - <%=userConsultor.getEmailAddress()%></li>
                         </c:forEach>
                     </ul>
                 </div>

             </div>
             <div class="row align-items-start mb-4">

                 <%-- CCAA --%>
                 <div class="col-lg-3 col-md-6 col-12">
                      <label><liferay-ui:message key="contract.view.ccaa"/></label>
                      <ul class="prv-list">
                          <c:forEach items="<%=ccaaList%>" var="ccaa">
                              <li>${ccaa.nombre}</li>
                          </c:forEach>
                      </ul>
                 </div>

                 <%-- AYUNTAMIENTOS --%>
                 <div class="col-lg-3 col-md-6 col-12">
                       <label><liferay-ui:message key="contract.view.ayuntamientos"/></label>
                       <ul class="prv-list">
                           <c:forEach items="<%=aytoList%>" var="ayto">
                               <li>${ayto.nombre}</li>
                           </c:forEach>
                       </ul>
                 </div>

                 <%-- CNAEs --%>
                 <div class="col-lg-6 col-md-6 col-12">
                       <label><liferay-ui:message key="contract.view.cnaes"/></label>
                       <ul class="prv-list">
                           <c:forEach items="<%=cnaesList%>" var="cnae">
                               <li>${cnae.nombre} (${cnae.cnae})</li>
                           </c:forEach>
                       </ul>
                 </div>

             </div>

        </div>
    </div>
</div>

<script>

$(document).on('click', '.contract-info', function(e){
    e.preventDefault();
    $('#infoContrato').removeClass('hide');
    $('#infoContrato').addClass('show');
});

$(document).on('click', '.hide-modal', function(e) {
    e.preventDefault();
    $('#infoContrato').addClass('hide');
});

</script>