<%@ include file="/init.jsp" %>

<%

Version version = (Version) request.getAttribute(PlanIgualdadVersionWebPortletKeys.VERSION_PARAM);
Long compId = (Long) request.getAttribute(PlanIgualdadVersionWebPortletKeys.COMPID_PARAM);

boolean emptyInicioPeriodoDatos = true;
boolean emptyFinPeriodoDatos = true;
boolean emptyInicioPeriodoIgualdad = true;
boolean emptyFinPeriodoIgualdad = true;
boolean emptyFechaBaja = true;

Calendar calendarInicioPeriodoDatos = Calendar.getInstance();
Calendar calendarFinPeriodoDatos = Calendar.getInstance();
Calendar calendarInicioPeriodoIgualdad = Calendar.getInstance();
Calendar calendarFinPeriodoIgualdad = Calendar.getInstance();
Calendar calendarFechaBaja = Calendar.getInstance();

if (Validator.isNotNull(version)) {

    if (Validator.isNotNull(version.getInicioPeriodoDatos())) {
        calendarInicioPeriodoDatos.setTime(version.getInicioPeriodoDatos());
        emptyInicioPeriodoDatos = false;
    }

    if (Validator.isNotNull(version.getFinPeriodoDatos())) {
        calendarFinPeriodoDatos.setTime(version.getFinPeriodoDatos());
        emptyFinPeriodoDatos = false;
    }

    if (Validator.isNotNull(version.getInicioPeriodoPlan())) {
        calendarInicioPeriodoIgualdad.setTime(version.getInicioPeriodoPlan());
        emptyInicioPeriodoIgualdad = false;
    }

    if (Validator.isNotNull(version.getFinPeriodoPlan())) {
        calendarFinPeriodoIgualdad.setTime(version.getFinPeriodoPlan());
        emptyFinPeriodoIgualdad = false;
    }

    if (Validator.isNotNull(version.getBaja())) {
        calendarFechaBaja.setTime(version.getBaja());
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
                <liferay-ui:message key="version.view.back"/>
            </a>
        </div>

        <div class="form-content prv-search prv-form mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">

            <liferay-portlet:actionURL name="/version/save" var="versionURL" />
            <aui:form  name="contract_form" action="<%=versionURL%>" method="post">

                <aui:input type="hidden" name="<%= PlanIgualdadVersionWebPortletKeys.COMPID_PARAM %>" value="<%= compId %>"></aui:input>
                <aui:input type="hidden" name="<%= PlanIgualdadVersionWebPortletKeys.VERSIONID_PARAM %>"
                    value="<%= Validator.isNotNull(version) ? version.getVersionId() : StringPool.BLANK %>"></aui:input>

                <div class="row align-items-start mb-4">

                    <%-- VERSION --%>
                    <div class="form-group col-md-6 col-6 required">
                        <div class="form-group input-text-wrapper">
                            <aui:input type="text" label="plan.view.version"
                                    name="<%= PlanIgualdadVersionWebPortletKeys.VERSION_NOMBRE %>"
                                    value="<%= Validator.isNotNull(version) ? version.getNombre() : StringPool.BLANK %>" >
                                <aui:validator name="required" errorMessage="plan.view.validator.required" />
                            </aui:input>
                        </div>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- PERIODO DATOS --%>
                    <div class="form-group col-md-3 col-12">
                        <div class="form-group input-text-wrapper">
                            <label class="control-label" for="inicioPeriodoDatos">
                                <liferay-ui:message key="plan.view.version.inicioPeriodoDatos"/>
                            </label>
                            <c:if test="<%= emptyInicioPeriodoDatos %>">
                                <liferay-ui:input-date name="<%= PlanIgualdadVersionWebPortletKeys.VERSION_INICIO_DATOS %>" nullable="true" showDisableCheckbox="false"/>
                            </c:if>
                            <c:if test="<%= !emptyInicioPeriodoDatos %>">
                                 <liferay-ui:input-date name="<%= PlanIgualdadVersionWebPortletKeys.VERSION_INICIO_DATOS %>" nullable="true" showDisableCheckbox="false"
                                        yearValue="<%= calendarInicioPeriodoDatos.get(Calendar.YEAR) %>"
                                        monthValue="<%= calendarInicioPeriodoDatos.get(Calendar.MONTH) %>"
                                        dayValue="<%= calendarInicioPeriodoDatos.get(Calendar.DAY_OF_MONTH) %>" />
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group col-md-3 col-12">
                        <div class="form-group input-text-wrapper">
                            <label class="control-label" for="finPeriodoDatos">
                                <liferay-ui:message key="plan.view.version.finPeriodoDatos"/>
                            </label>
                            <c:if test="<%= emptyFinPeriodoDatos %>">
                                <liferay-ui:input-date name="<%= PlanIgualdadVersionWebPortletKeys.VERSION_FIN_DATOS %>" nullable="true" showDisableCheckbox="false"/>
                            </c:if>
                            <c:if test="<%= !emptyFinPeriodoDatos %>">
                                <liferay-ui:input-date name="<%= PlanIgualdadVersionWebPortletKeys.VERSION_FIN_DATOS %>" nullable="true" showDisableCheckbox="false"
                                        yearValue="<%= calendarFinPeriodoDatos.get(Calendar.YEAR) %>"
                                        monthValue="<%= calendarFinPeriodoDatos.get(Calendar.MONTH) %>"
                                        dayValue="<%= calendarFinPeriodoDatos.get(Calendar.DAY_OF_MONTH) %>" />
                            </c:if>
                        </div>
                    </div>

                    <%-- PERIODO IGUALDAD --%>
                    <div class="form-group col-md-3 col-12">
                        <div class="form-group input-text-wrapper">
                            <label class="control-label" for="inicioPeriodoIgualdad">
                                <liferay-ui:message key="plan.view.version.inicioPeriodoIgualdad"/>
                            </label>
                            <c:if test="<%= emptyInicioPeriodoIgualdad %>">
                                <liferay-ui:input-date name="<%= PlanIgualdadVersionWebPortletKeys.VERSION_INICIO_IGUALDAD %>" nullable="true" showDisableCheckbox="false"/>
                            </c:if>
                            <c:if test="<%= !emptyInicioPeriodoIgualdad %>">
                                <liferay-ui:input-date name="<%= PlanIgualdadVersionWebPortletKeys.VERSION_INICIO_IGUALDAD %>" nullable="true" showDisableCheckbox="false"
                                        yearValue="<%= calendarInicioPeriodoIgualdad.get(Calendar.YEAR) %>"
                                        monthValue="<%= calendarInicioPeriodoIgualdad.get(Calendar.MONTH) %>"
                                        dayValue="<%= calendarInicioPeriodoIgualdad.get(Calendar.DAY_OF_MONTH) %>" />
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group col-md-3 col-12">
                        <div class="form-group input-text-wrapper">
                            <label class="control-label" for="finPeriodoIgualdad">
                                <liferay-ui:message key="plan.view.version.finPeriodoIgualdad"/>
                            </label>
                            <c:if test="<%= emptyFinPeriodoIgualdad %>">
                                <liferay-ui:input-date name="<%= PlanIgualdadVersionWebPortletKeys.VERSION_FIN_IGUALDAD %>" nullable="true" showDisableCheckbox="false"/>
                            </c:if>
                            <c:if test="<%= !emptyFinPeriodoIgualdad %>">
                                <liferay-ui:input-date name="<%= PlanIgualdadVersionWebPortletKeys.VERSION_FIN_IGUALDAD %>" nullable="true" showDisableCheckbox="false"
                                        yearValue="<%= calendarFinPeriodoIgualdad.get(Calendar.YEAR) %>"
                                        monthValue="<%= calendarFinPeriodoIgualdad.get(Calendar.MONTH) %>"
                                        dayValue="<%= calendarFinPeriodoIgualdad.get(Calendar.DAY_OF_MONTH) %>" />
                            </c:if>
                        </div>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- FECHA BAJA --%>
                    <div class="form-group col-md-3 col-12">
                        <div class="form-group input-text-wrapper">
                            <label class="control-label" for="fechaBaja">
                                <liferay-ui:message key="plan.view.version.fechaBaja"/>
                            </label>
                            <c:if test="<%= emptyFechaBaja %>">
                                <liferay-ui:input-date name="<%= PlanIgualdadVersionWebPortletKeys.VERSION_BAJA %>" nullable="true" showDisableCheckbox="false"/>
                            </c:if>
                            <c:if test="<%= !emptyFechaBaja %>">
                                <liferay-ui:input-date name="<%= PlanIgualdadVersionWebPortletKeys.VERSION_BAJA %>" nullable="true" showDisableCheckbox="false"
                                        yearValue="<%= calendarFechaBaja.get(Calendar.YEAR) %>"
                                        monthValue="<%= calendarFechaBaja.get(Calendar.MONTH) %>"
                                        dayValue="<%= calendarFechaBaja.get(Calendar.DAY_OF_MONTH) %>" />
                            </c:if>
                        </div>
                    </div>

                </div>

                <div class="button-holder d-flex justify-content-end my-4">
                    <aui:button-row>
                        <aui:button onClick="<%= cancelURL %>" value="back" cssClass="btn btn-outline-primary mr-4" primary="true"></aui:button>
                        <aui:button type="submit" cssClass="btn btn-primary" />
                    </aui:button-row>
                </div>

            </aui:form>

        </div>

    </div>
</div>