<%@ include file="/init.jsp" %>

<%

Long compId = (Long) request.getAttribute(PlanIgualdadPuestosWebPortletKeys.COMPID_PARAM);
Long versionId = (Long) request.getAttribute(PlanIgualdadPuestosWebPortletKeys.VERSIONID_PARAM);
PuestoTrabajo puesto = (PuestoTrabajo) request.getAttribute(PlanIgualdadPuestosWebPortletKeys.PUESTO_PARAM);

boolean emptyFechaBaja = true;
Calendar calendarFechaBaja = Calendar.getInstance();

if (Validator.isNotNull(puesto)) {

    if (Validator.isNotNull(puesto.getBaja())) {
        calendarFechaBaja.setTime(puesto.getBaja());
        emptyFechaBaja = false;
    }

}

%>

<portlet:renderURL var="cancelURL">
    <portlet:param name="compId" value="${compId}"></portlet:param>
    <portlet:param name="mvcRenderCommandName" value="/"></portlet:param>
</portlet:renderURL>

<div class="content">
    <div class="formulario">

        <div class="title-group mt-3">
            <a href="<%= cancelURL %>" class="toBackView">
                <i class="icon-arrow-left"></i>
                <liferay-ui:message key="puesto.view.back"/>
            </a>
        </div>

        <div class="form-content prv-search prv-form mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">

            <liferay-portlet:actionURL name="/puesto/save" var="puestoURL" />
            <aui:form  name="area_form" action="<%=puestoURL%>" method="post">

                <aui:input type="hidden" name="<%= PlanIgualdadPuestosWebPortletKeys.COMPID_PARAM %>" value="<%= compId %>"></aui:input>
                <aui:input type="hidden" name="<%= PlanIgualdadPuestosWebPortletKeys.VERSIONID_PARAM %>" value="<%= versionId %>"></aui:input>
                <aui:input type="hidden" name="<%= PlanIgualdadPuestosWebPortletKeys.PUESTOID_PARAM %>"
                    value="<%= Validator.isNotNull(puesto) ? puesto.getPuestoId() : StringPool.BLANK %>"></aui:input>

                <div class="row align-items-start mb-4">

                    <%-- PUESTO --%>
                    <div class="form-group col-md-6 col-6 required">
                        <div class="form-group input-text-wrapper">
                            <aui:input type="text" label="puesto.view.trabajo"
                                    name="<%= PlanIgualdadPuestosWebPortletKeys.PUESTO_NOMBRE %>"
                                    value="<%= Validator.isNotNull(puesto) ? puesto.getNombre() : StringPool.BLANK %>" >
                                <aui:validator name="required" errorMessage="puesto.view.validator.required" />
                            </aui:input>
                        </div>
                    </div>

                    <%-- AREA --%>
                    <div class="form-group col-md-6 required">
                        <aui:select name="<%=PlanIgualdadPuestosWebPortletKeys.PUESTO_AREA%>" label="puesto.view.area">
                            <aui:option value=""></aui:option>
                            <c:forEach items="${areas}" var="area">
                                <%
                                    Area area = (Area) pageContext.getAttribute("area");
                                %>
                                <aui:option value="${area.areaId}" selected="<%= Validator.isNotNull(puesto) ? puesto.getAreaId() == area.getAreaId() : false %>">
                                    ${area.nombre}
                                </aui:option>
                            </c:forEach>
                            <aui:validator name="required" errorMessage="puesto.view.validator.required" />
                        </aui:select>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- CUMPLIMIENTO --%>
                    <div class="form-group col-md-6 col-12 required">
                        <fieldset>
                            <legend>
                                <liferay-ui:message key="puesto.view.responsabilidad" />
                            </legend>
                            <div class="checksBorder">
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="<portlet:namespace/><%= PlanIgualdadPuestosWebPortletKeys.PUESTO_RESPONSABILIDAD %>"
                                        id="responsabilidad1" value="0" type="radio"
                                        <%= Validator.isNotNull(puesto) && puesto.getResponsabilidad() == 0 ? "checked" : "" %> />
                                    <label class="form-check-label" for="responsabilidad1"><liferay-ui:message key="puesto.view.activa.si" /></label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="<portlet:namespace/><%= PlanIgualdadPuestosWebPortletKeys.PUESTO_RESPONSABILIDAD %>"
                                        id="responsabilidad2" value="1" type="radio"
                                        <%= Validator.isNotNull(puesto) && puesto.getResponsabilidad() == 1 ? "checked" : "" %> />
                                    <label class="form-check-label" for="responsabilidad2"><liferay-ui:message key="puesto.view.activa.no" /></label>
                                </div>
                            </div>
                        </fieldset>
                        <span id="requiredResponsabilidad" class="clsRequiredSelect">
                           <span class="has-error">
                               <div class="form-validator-stack help-block">
                                   <div role="alert" class=" required">
                                       <liferay-ui:message key="puesto.view.validator.required" />
                                   </div>
                               </div>
                           </span>
                        </span>
                    </div>

                    <%-- NHOMBRE --%>
                    <div class="form-group col-md-3 col-3 required">
                        <div class="form-group input-text-wrapper">
                            <aui:input type="number" label="puesto.view.nHombre"
                                    min="0" oninput="this.value = !!this.value && Math.abs(this.value) >= 0 ? Math.abs(this.value) : null"
                                    name="<%= PlanIgualdadPuestosWebPortletKeys.PUESTO_NHOMBRES %>"
                                    value="<%= Validator.isNotNull(puesto) ? puesto.getNHombres() : 0 %>" >
                                <aui:validator name="required" errorMessage="puesto.view.validator.required" />
                            </aui:input>
                        </div>
                    </div>

                    <%-- NMUJERES --%>
                    <div class="form-group col-md-3 col-3 required">
                        <div class="form-group input-text-wrapper">
                            <aui:input type="number" label="puesto.view.nMujeres"
                                    min="0" oninput="this.value = !!this.value && Math.abs(this.value) >= 0 ? Math.abs(this.value) : null"
                                    name="<%= PlanIgualdadPuestosWebPortletKeys.PUESTO_NMUJERES %>"
                                    value="<%= Validator.isNotNull(puesto) ? puesto.getNMujeres() : 0 %>" >
                                <aui:validator name="required" errorMessage="puesto.view.validator.required" />
                            </aui:input>
                        </div>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- FECHA BAJA --%>
                    <div class="form-group col-md-3 col-12">
                        <div class="form-group input-text-wrapper">
                            <label class="control-label" for="fechaBaja">
                                <liferay-ui:message key="puesto.view.baja"/>
                            </label>
                            <c:if test="<%= emptyFechaBaja %>">
                                <liferay-ui:input-date name="<%= PlanIgualdadPuestosWebPortletKeys.PUESTO_BAJA %>" nullable="true" showDisableCheckbox="false"/>
                            </c:if>
                            <c:if test="<%= !emptyFechaBaja %>">
                                <liferay-ui:input-date name="<%= PlanIgualdadPuestosWebPortletKeys.PUESTO_BAJA %>" nullable="true" showDisableCheckbox="false"
                                        yearValue="<%= calendarFechaBaja.get(Calendar.YEAR) %>"
                                        monthValue="<%= calendarFechaBaja.get(Calendar.MONTH) %>"
                                        dayValue="<%= calendarFechaBaja.get(Calendar.DAY_OF_MONTH) %>" />
                            </c:if>
                        </div>
                    </div>

                </div>

                <div class="button-holder d-flex justify-content-end my-4">
                    <aui:button-row>
                        <aui:button onClick="<%= cancelURL.toString() %>" value="back" cssClass="btn btn-outline-primary mr-4" primary="true"></aui:button>
                        <aui:button type="submit" cssClass="btn btn-primary" onClick="checkForm()" />
                    </aui:button-row>
                </div>

            </aui:form>

    </div>
</div>

<script>

    function checkForm(){
        let isOk = true;

        if( $('input:radio[name=<portlet:namespace/><%= PlanIgualdadPuestosWebPortletKeys.PUESTO_RESPONSABILIDAD %>]:checked').length == 0 ) {
            $("#requiredResponsabilidad").attr("class", "clsRequiredSelect_visible");
            isOk = false;
        }

        if(!isOk){
            event.preventDefault();
            event.stopPropagation();
            return false;
        }
        return true;
    }

</script>
