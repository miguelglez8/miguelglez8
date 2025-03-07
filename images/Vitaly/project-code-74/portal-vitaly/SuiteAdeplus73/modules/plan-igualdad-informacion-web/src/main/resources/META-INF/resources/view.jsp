<%@ include file="/init.jsp" %>

<%

Organizacion organizacion = (Organizacion) request.getAttribute(PlanIgualdadInformacionWebPortletKeys.ORGANIZACION);

%>

<script>
if (!localStorage.getItem("reload")) {
    localStorage.setItem("reload", "true");
    location.reload();
}else {
    localStorage.removeItem("reload");
}
</script>

<liferay-ui:success key="organizacion-save-success" message="organizacion.view.save.success" />

<liferay-portlet:actionURL name="/organizacion/save" var="organizacionURL" />
<aui:form name="organizacion_form" action="<%= organizacionURL %>" method="post">

    <aui:input name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_COMPDID %>" value="${empresa.compId}" type="hidden" />
    <aui:input name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_VERSIONID %>" value="${version}" type="hidden" />
    <aui:input name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_ID %>"
            value="<%= Validator.isNotNull(organizacion) ? organizacion.getOrganizacionId() : StringPool.BLANK %>" type="hidden" />

    <div class="form-content form-datos">

        <fieldset class="border p-2 mb-4">
           <legend  class="w-auto"><liferay-ui:message key="organizacion.view.datos" /></legend>

           <div class="row align-items-start mb-4">

               <%-- RAZON SOCIAL --%>
               <div class="form-group col-md-6 col-6 required">
                   <div class="form-group input-text-wrapper">
                       <aui:input type="text" label="organizacion.view.razonSocial" name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_RAZON_SOCIAL %>"
                            value="${empresa.name}" disabled="true">
                            <aui:validator name="required" errorMessage="organizacion.view.validator.required" />
                       </aui:input>
                   </div>
               </div>

               <%-- CONSTITUCION --%>
               <div class="form-group col-md-6 col-6">
                    <div class="form-group input-text-wrapper">
                        <aui:input type="text" label="organizacion.view.constitucion" name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_ANNO_CONSTITUCION %>"
                            value="<%= Validator.isNotNull(organizacion) ? organizacion.getAnnoConstitucion() : StringPool.BLANK %>">
                        	<aui:validator name="maxLength">4</aui:validator>    
                        	<aui:validator name="digits"></aui:validator>    
                        </aui:input>
                    </div>
               </div>

           </div>
           <div class="row align-items-start mb-4">

               <%-- CIF --%>
               <div class="form-group col-md-6 col-6 required">
                    <div class="form-group input-text-wrapper">
                        <aui:input type="text" label="organizacion.view.cif" name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_CIF %>"
                            value="${empresa.cif}" disabled="true">
                            <aui:validator name="required" errorMessage="organizacion.view.validator.required" />
                        </aui:input>
                    </div>
               </div>

               <%-- WEB --%>
               <div class="form-group col-md-6 col-6">
                   <div class="form-group input-text-wrapper">
                       <aui:input type="text" label="organizacion.view.web" name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_PAGINA_WEB %>"
                            value="<%= Validator.isNotNull(organizacion) ? organizacion.getPaginaWeb() : StringPool.BLANK %>" />
                   </div>
               </div>

           </div>
           <div class="row align-items-start mb-4">

               <%-- DOMICILIO --%>
               <div class="form-group col-md-6 col-6">
                   <div class="form-group input-text-wrapper">
                       <aui:input type="text" label="organizacion.view.domicilio" name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_DOMICILIO %>"
                            value="<%= Validator.isNotNull(organizacion) ? organizacion.getDomicilioSocial() : StringPool.BLANK %>" />
                   </div>
               </div>

               <%-- FACTURACION --%>
               <div class="form-group col-md-6 col-6">
                  <div class="form-group input-text-wrapper">
                      <aui:input type="text" label="organizacion.view.facturacion" name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_FACTURACION %>"
                            value="<%= Validator.isNotNull(organizacion) ? organizacion.getFacturacionAnual() : StringPool.BLANK %>">
                      		<aui:validator name="digits"></aui:validator>         
                      </aui:input>
                  </div>
               </div>

           </div>
           <div class="row align-items-start mb-4">

               <%-- FORMA JURIDICA --%>
               <div class="form-group col-md-6 col-6">
                   <div class="form-group input-text-wrapper">
                       <aui:input type="text" label="organizacion.view.formaJuridica" name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_FORMA_JURIDICA %>"
                            value="<%= Validator.isNotNull(organizacion) ? organizacion.getFormaJuridica() : StringPool.BLANK %>" />
                   </div>
               </div>

           </div>

        </fieldset>
        <fieldset class="border p-2 mb-4">
            <legend  class="w-auto"><liferay-ui:message key="organizacion.view.responsable.entidad" /></legend>

                <div class="row align-items-start mb-4">

                    <%-- NOMBRE --%>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                            <aui:input type="text" label="organizacion.view.responsable.entidad.nombre"
                                name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_NOMBRE_RESPONSABLE_ENTIDAD %>"
                                value="<%= Validator.isNotNull(organizacion) ? organizacion.getNombreResponsableEntidad() : StringPool.BLANK %>" />
                        </div>
                    </div>

                    <%-- TELEFONO --%>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                            <aui:input type="text" label="organizacion.view.responsable.entidad.telefono"
                                name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_TELEFONO_RESPONSABLE_ENTIDAD %>"
                                value="<%= Validator.isNotNull(organizacion) ? organizacion.getTelefonoResponsableEntidad() : StringPool.BLANK %>">
                            	<aui:validator name="digits"></aui:validator>  
                            </aui:input>
                        </div>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- CARGO --%>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                            <aui:input type="text" label="organizacion.view.responsable.entidad.cargo"
                                name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_CARGO_RESPONSABLE_ENTIDAD %>"
                                value="<%= Validator.isNotNull(organizacion) ? organizacion.getCargoResponsableEntidad() : StringPool.BLANK %>" />
                        </div>
                    </div>

                    <%-- EMAIL --%>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                            <aui:input type="text" label="organizacion.view.responsable.entidad.email"
                                name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_EMAIL_RESPONSABLE_ENTIDAD %>"
                                value="<%= Validator.isNotNull(organizacion) ? organizacion.getEmailResponsableEntidad() : StringPool.BLANK %>">
                            	<aui:validator name="email"/>    
                           	</aui:input>
                        </div>
                    </div>

                </div>

        </fieldset>
        <fieldset class="border p-2 mb-4">
            <legend  class="w-auto"><liferay-ui:message key="organizacion.view.responsable.igualdad" /></legend>

                <div class="row align-items-start mb-4">

                    <%-- NOMBRE --%>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                            <aui:input type="text" label="organizacion.view.responsable.igualdad.nombre"
                                name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_NOMBRE_RESPONSABLE_IGUALDAD %>"
                                value="<%= Validator.isNotNull(organizacion) ? organizacion.getNombreResponsableIgualdad() : StringPool.BLANK %>" />
                        </div>
                    </div>

                    <%-- TELEFONO --%>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                            <aui:input type="text" label="organizacion.view.responsable.igualdad.telefono"
                                name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_TELEFONO_RESPONSABLE_IGUALDAD %>"
                                value="<%= Validator.isNotNull(organizacion) ? organizacion.getTelefonoResponsableIgualdad() : StringPool.BLANK %>">
                            	<aui:validator name="digits"></aui:validator>  
                            </aui:input>
                        </div>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- CARGO --%>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                            <aui:input type="text" label="organizacion.view.responsable.igualdad.cargo"
                                name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_CARGO_RESPONSABLE_IGUALDAD %>"
                                value="<%= Validator.isNotNull(organizacion) ? organizacion.getCargoResponsableIgualdad() : StringPool.BLANK %>" />
                        </div>
                    </div>

                    <%-- EMAIL --%>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                            <aui:input type="text" label="organizacion.view.responsable.igualdad.email"
                                name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_EMAIL_RESPONSABLE_IGUALDAD %>"
                                value="<%= Validator.isNotNull(organizacion) ? organizacion.getEmailResponsableIgualdad() : StringPool.BLANK %>">
                                <aui:validator name="email"/>   
                           	</aui:input>
                        </div>
                    </div>

                </div>

        </fieldset>
        <fieldset class="border p-2 mb-4">
            <legend  class="w-auto"><liferay-ui:message key="organizacion.view.gestion" /></legend>

                <div class="row align-items-start mb-4">

                    <%-- CNAES --%>
                    <div class="form-group col-md-6 col-6 prv-form">
                        <fieldset class="input-group-inline">
                            <div class="form-group input-select-wrapper required">
                                <label class="control-label" for="cnaes"><liferay-ui:message key="organizacion.view.gestion.cnaes"/></label>
                                <br/>
                                <select id="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_CANES %>" onchange="selectChange(this)"
                                        name="<portlet:namespace/><%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_CANES %>">
                                    <c:forEach items="${cnaes}" var="cnae">
                                        <aui:option value="${cnae.cnae}">${cnae.nombre} (${cnae.cnae})</aui:option>
                                    </c:forEach>
                                </select>
                            </div>
                        </fieldset>
                        <span id="requiredCNAES" class="clsRequiredSelect">
                           <span class="has-error">
                               <div class="form-validator-stack help-block">
                                   <div role="alert" class=" required">
                                       <liferay-ui:message key="organizacion.view.validator.required" />
                                   </div>
                               </div>
                           </span>
                        </span>
                    </div>

                    <%-- Nº CENTROS --%>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper required">
                            <aui:input type="number" label="organizacion.view.gestion.nCentros"
                                min="0" oninput="this.value = !!this.value && Math.abs(this.value) >= 0 ? Math.abs(this.value) : null"
                                name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_NCENTROS %>"
                                value="<%= Validator.isNotNull(organizacion) ? organizacion.getNCentros() : StringPool.BLANK %>">
                                <aui:validator name="required" errorMessage="organizacion.view.validator.required" />
                            </aui:input>
                        </div>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- DESCRIPCION ACTIVIDAD --%>
                    <div class="form-group col-md-12 col-12">
                        <div class="form-group input-text-wrapper required">
                            <aui:input type="textarea" label="organizacion.view.gestion.descripcionActividad"
                                name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_DESCRIPCION_ACTIVIDAD %>"
                                value="<%= Validator.isNotNull(organizacion) ? organizacion.getDescripcionActividad() : StringPool.BLANK %>">
                            </aui:input>
                        </div>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- CONVENIO --%>
                    <div class="form-group col-md-12 col-12">
                        <div class="form-group input-text-wrapper required">
                            <aui:input type="textarea" label="organizacion.view.gestion.convenio"
                                name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_CONVENIOS %>"
                                value="<%= Validator.isNotNull(organizacion) ? organizacion.getConvenio() : StringPool.BLANK %>">
                                <aui:validator name="required" errorMessage="organizacion.view.validator.required" />
                            </aui:input>
                        </div>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- AMBITO --%>
                    <div class="form-group col-md-6 col-6 prv-form">
                        <fieldset class="input-group-inline">
                            <div class="form-group input-select-wrapper required">
                                <label class="control-label" for="ambito"><liferay-ui:message key="organizacion.view.gestion.ambito"/></label>
                                <br/>
                                <select id="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_AMBITO %>"
                                        name="<portlet:namespace/><%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_AMBITO %>">
                                    <aui:option value=""></aui:option>
                                    <c:forEach items="<%= Ambito.values() %>" var="ambito">
                                        <option value="${ambito.codigo}"><liferay-ui:message key="${ambito.descripcion}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                        </fieldset>
                        <span id="requiredAmbito" class="clsRequiredSelect">
                           <span class="has-error">
                               <div class="form-validator-stack help-block">
                                   <div role="alert" class=" required">
                                       <liferay-ui:message key="organizacion.view.validator.required" />
                                   </div>
                               </div>
                           </span>
                        </span>
                    </div>

                    <%-- COMENTARIO AMBITO --%>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper">
                            <aui:input type="text" label="organizacion.view.gestion.comentarioAmbito"
                                name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_COMENTARIO_AMBITO %>"
                                value="<%= Validator.isNotNull(organizacion) ? organizacion.getComentarioAmbito() : StringPool.BLANK %>">
                            </aui:input>
                        </div>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- PLANTILLA --%>
                    <div class="form-group col-md-12 col-12">
                        <div class="form-group input-text-wrapper">
                            <label class="control-label" for="distribucion"><liferay-ui:message key="organizacion.view.gestion.distribucion"/></label>
                        </div>
                    </div>

                    <%-- Nº HOMBRES --%>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper required">
                            <aui:input type="number" label="organizacion.view.gestion.nHombrePlantilla"
                                min="0" oninput="this.value = !!this.value && Math.abs(this.value) >= 0 ? Math.abs(this.value) : null"
                                name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_NHOMBRES_PLANTILLA %>"
                                value="<%= Validator.isNotNull(organizacion) ? organizacion.getNHombresPlantilla() : 0 %>">
                                <aui:validator name="required" errorMessage="organizacion.view.validator.required" />
                            </aui:input>
                        </div>
                    </div>

                    <%-- Nº MUJERES --%>
                    <div class="form-group col-md-6 col-6">
                        <div class="form-group input-text-wrapper required">
                            <aui:input type="number" label="organizacion.view.gestion.nMujeresPlantilla"
                                min="0" oninput="this.value = !!this.value && Math.abs(this.value) >= 0 ? Math.abs(this.value) : null"
                                name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_NMUJERES_PLANTILLA %>"
                                value="<%= Validator.isNotNull(organizacion) ? organizacion.getNMujeresPlantilla() : 0 %>">
                                <aui:validator name="required" errorMessage="organizacion.view.validator.required" />
                            </aui:input>
                        </div>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- REPRESENTACION LEGAL --%>
                    <div class="prv-form form-group col-md-6 col-6 required">
                        <fieldset>
                            <legend class="control-label"><liferay-ui:message key="organizacion.view.representacionLegal" /></legend>
                            <div class="checksBorder">
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input reprLegal" name="<portlet:namespace/><%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_LEGAL %>"
                                           <%= Validator.isNotNull(organizacion) && "0".equals(organizacion.getRepresentacionLegal()) ? "checked" : "" %>
                                           id="representacionLegal1" value="0" type="radio" />
                                    <label class="form-check-label" for="representacionLegal1"><liferay-ui:message key="organizacion.view.si" /></label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input reprLegal" name="<portlet:namespace/><%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_LEGAL %>"
                                            <%= Validator.isNotNull(organizacion) && "1".equals(organizacion.getRepresentacionLegal()) ? "checked" : "" %>
                                            id="representacionLegal2" value="1" type="radio"  />
                                    <label class="form-check-label" for="representacionLegal2"><liferay-ui:message key="organizacion.view.no" /></label>
                                </div>
                            </div>
                        </fieldset>
                        <span id="requiredRepresentacionLegal" class="clsRequiredSelect">
                           <span class="has-error">
                               <div class="form-validator-stack help-block">
                                   <div role="alert" class=" required">
                                       <liferay-ui:message key="organizacion.view.validator.required" />
                                   </div>
                               </div>
                           </span>
                        </span>
                    </div>
                    <div id="nReprLegal" class="row col-md-6 col-6 <%= Validator.isNull(organizacion)? "hide" : "1".equals(organizacion.getRepresentacionLegal()) ? "hide" : "" %>">

                        <%-- Nº HOMBRES --%>
                        <div class="form-group col-md-6 col-6">
                            <div class="form-group input-text-wrapper">
                                <aui:input type="number" label="organizacion.view.gestion.nRepresentacionLegalHombres"
                                    min="0" oninput="this.value = !!this.value && Math.abs(this.value) >= 0 ? Math.abs(this.value) : null"
                                    name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_NHOMBRE %>"
                                    value="<%= Validator.isNotNull(organizacion) ? organizacion.getNRepresentacionLegalHombres() : StringPool.BLANK %>">
                                </aui:input>
                            </div>
                        </div>

                        <%-- Nº MUJERES --%>
                        <div class="form-group col-md-6 col-6">
                            <div class="form-group input-text-wrapper">
                                <aui:input type="number" label="organizacion.view.gestion.nRepresentacionLegalMujeres"
                                    min="0" oninput="this.value = !!this.value && Math.abs(this.value) >= 0 ? Math.abs(this.value) : null"
                                    name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_NMUJERES %>"
                                    value="<%= Validator.isNotNull(organizacion) ? organizacion.getNRepresentacionLegalMujeres() : StringPool.BLANK %>">
                                </aui:input>
                            </div>
                        </div>

                    </div>

                </div>
                <div id="nReprTotal" class="row align-items-start mb-4 <%= Validator.isNull(organizacion)? "hide" : "1".equals(organizacion.getRepresentacionLegal()) ? "hide" : "" %>">

                    <%-- REPRESENTACION TOTAL --%>
                    <div class="prv-form form-group col-md-6 col-6 required">
                        <fieldset>
                            <legend class="control-label"><liferay-ui:message key="organizacion.view.representacionTotal" /></legend>
                            <div class="checksBorder">
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input reprTotal" name="<portlet:namespace/><%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_RESPRESENTACION_TOTAL %>"
                                            <%= Validator.isNotNull(organizacion) && "0".equals(organizacion.getRepresentaTotalidad()) ? "checked" : "" %>
                                            id="representacionTotal1" value="0" type="radio" />
                                    <label class="form-check-label" for="representacionTotal1"><liferay-ui:message key="organizacion.view.si" /></label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input reprTotal" name="<portlet:namespace/><%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_RESPRESENTACION_TOTAL %>"
                                            <%= Validator.isNotNull(organizacion) && "1".equals(organizacion.getRepresentaTotalidad()) ? "checked" : "" %>
                                            id="representacionTotal2" value="1" type="radio"  />
                                    <label class="form-check-label" for="representacionTotal2"><liferay-ui:message key="organizacion.view.no" /></label>
                                </div>
                            </div>
                        </fieldset>
                        <span id="requiredRepresentacionTotal" class="clsRequiredSelect">
                           <span class="has-error">
                               <div class="form-validator-stack help-block">
                                   <div role="alert" class=" required">
                                       <liferay-ui:message key="organizacion.view.validator.required" />
                                   </div>
                               </div>
                           </span>
                        </span>
                    </div>
                    <div id="nNoRepr" class="row col-md-6 col-6 <%= Validator.isNull(organizacion)? "hide" : "1".equals(organizacion.getRepresentaTotalidad()) ? "" : "hide" %>">

                        <%-- Nº NO REPRESENTADOS --%>
                        <div class="form-group col-md-6 col-6">
                            <div class="form-group input-text-wrapper">
                                <aui:input type="number" label="organizacion.view.gestion.nNoRepresentados"
                                    min="0" oninput="this.value = !!this.value && Math.abs(this.value) >= 0 ? Math.abs(this.value) : null"
                                    name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_NNOREPRESENTADOS %>"
                                    value="<%= Validator.isNotNull(organizacion) ? organizacion.getNNoRepresentados() : StringPool.BLANK %>">
                                </aui:input>
                            </div>
                        </div>

                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- COMENTARIO REPRESENTADOS --%>
                    <div class="form-group col-md-12 col-12">
                        <div class="form-group input-text-wrapper">
                            <aui:input type="textarea" label="organizacion.view.gestion.comentarioRepresentacion"
                                name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_COMENTARIO_REPRESENTADOS %>"
                                value="<%= Validator.isNotNull(organizacion) ? organizacion.getComentarioRepresentacion() : StringPool.BLANK %>">
                            </aui:input>
                        </div>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- DEPARTAMENTO PERSONAS --%>
                    <div class="prv-form form-group col-md-6 col-6 required">
                        <fieldset>
                            <legend class="control-label"><liferay-ui:message key="organizacion.view.departamentoPersonas" /></legend>
                            <div class="checksBorder">
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="<portlet:namespace/><%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_DEPARTAMENTO_PERSONAS %>"
                                        <%= Validator.isNotNull(organizacion) && "0".equals(organizacion.getDepartamentoPersonal()) ? "checked" : "" %>
                                        id="departamentoPersonas1" value="0" type="radio" />
                                    <label class="form-check-label" for="departamentoPersonas1"><liferay-ui:message key="organizacion.view.si" /></label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="<portlet:namespace/><%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_DEPARTAMENTO_PERSONAS %>"
                                        <%= Validator.isNotNull(organizacion) && "1".equals(organizacion.getDepartamentoPersonal()) ? "checked" : "" %>
                                        id="departamentoPersonas2" value="1" type="radio"  />
                                    <label class="form-check-label" for="departamentoPersonas2"><liferay-ui:message key="organizacion.view.no" /></label>
                                </div>
                            </div>
                        </fieldset>
                        <span id="requiredDepartamentoPersonas" class="clsRequiredSelect">
                           <span class="has-error">
                               <div class="form-validator-stack help-block">
                                   <div role="alert" class=" required">
                                       <liferay-ui:message key="organizacion.view.validator.required" />
                                   </div>
                               </div>
                           </span>
                        </span>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- SINDICATOS --%>
                    <div class="form-group col-md-12 col-12">
                        <div class="form-group input-text-wrapper required">
                            <aui:input type="textarea" label="organizacion.view.gestion.sindicatos"
                                name="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_SINDICATOS %>"
                                value="<%= Validator.isNotNull(organizacion) ? organizacion.getSindicatos() : StringPool.BLANK %>">
                                <aui:validator name="required" errorMessage="organizacion.view.validator.required" />
                            </aui:input>
                        </div>
                    </div>

                </div>

        </fieldset>

        <%-- TABLES --%>
        <%@ include file="/tables.jsp" %>

        <div class="button-holder d-flex justify-content-end my-4">
            <aui:button-row>
                <aui:button type="submit" cssClass="btn btn-primary" onClick="checkForm()" />
            </aui:button-row>
        </div>

    </div>

</aui:form>

<script>

    $(document).ready(function (){

        $('#cnaes').select2({
            placeholder: "Seleccione ..",
            multiple : true,
            language: "es"
        }).val("<%= Validator.isNotNull(organizacion) ? organizacion.getCnaes() : StringPool.BLANK %>".split(";")).trigger("change");

        $('#ambito').select2({
            placeholder: "Seleccione ..",
            multiple : false,
            language: "es"
        }).val("<%= Validator.isNotNull(organizacion) ? organizacion.getAmbito() : StringPool.BLANK %>").trigger("change");

        $('.reprLegal').on('click', function() {
            var value = $(this).val();
            value == "1" ? $('#nReprLegal').addClass('hide') : $('#nReprLegal').removeClass('hide');
            value == "1" ? $('#nReprTotal').addClass('hide') : $('#nReprTotal').removeClass('hide');
        });

        $('.reprTotal').on('click', function() {
            var value = $(this).val();
            value == "1" ? $('#nNoRepr').removeClass('hide') : $('#nNoRepr').addClass('hide');
        });

        $(document).on('keypress',function(e) {
            if(e.which == 13) {
                e.preventDefault();
                if ( !$('#rowEditModal').hasClass('hide') )
                      $('.prv-save-modal').click();
            }
        });
        
    });

    function selectChange(_node) {
        var howMuch =$("#" + _node.id).val().length
        _node.nextElementSibling.style.marginBottom =  (30 * howMuch) + 3 + "px";
    }

    function checkForm(){
        let isOk = true;

        if( $('input:radio[name=<portlet:namespace/><%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_LEGAL %>]:checked').length == 0 ) {
            $("#requiredRepresentacionLegal").attr("class", "clsRequiredSelect_visible");
            isOk = false;
        }

        if ( $('input:radio[name=<portlet:namespace/><%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_LEGAL %>]:checked').val() == "0" &&
             $('input:radio[name=<portlet:namespace/><%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_RESPRESENTACION_TOTAL %>]:checked').length == 0 ) {
            $("#requiredRepresentacionTotal").attr("class", "clsRequiredSelect_visible");
            isOk = false;
        }

        if( $('input:radio[name=<portlet:namespace/><%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_DEPARTAMENTO_PERSONAS %>]:checked').length == 0 ) {
            $("#requiredDepartamentoPersonas").attr("class", "clsRequiredSelect_visible");
            isOk = false;
        }

        if($("#<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_AMBITO %>").val() == "") {
            $("#requiredAmbito").attr("class","clsRequiredSelect_visible");
            isOk = false;
        }

        if($("#<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_CANES %>").val() == "") {
            $("#requiredCNAES").attr("class","clsRequiredSelect_visible");
            isOk = false;
        }

        var form = Liferay.Form.get('<portlet:namespace />organizacion_form').formValidator;
        form.validate();

        if(!isOk || form.hasErrors()){
            event.preventDefault();
            event.stopPropagation();
            return false;
        }

        return true;
    }

</script>