<%@ page import="com.legalplus.liferay.portlet.web.evaluacion.constants.WebEvaluacionPortletKeys" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.Legislacion" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>

<%@ include file="/init.jsp" %>

<%

String legislacionId = (String) request.getAttribute(WebEvaluacionPortletKeys.LEGISLACION_PARAM);
String compId = (String) request.getAttribute(WebEvaluacionPortletKeys.COMP_PARAM);

EvalLegislacion evaluacion = (EvalLegislacion) request.getAttribute(WebEvaluacionPortletKeys.EVALUACION);
Legislacion legislacion = (Legislacion) request.getAttribute(WebEvaluacionPortletKeys.LEGISLACION);

Calendar fechaEvaluacion = Calendar.getInstance();
Calendar fechaProxima = Calendar.getInstance();

boolean emptyProximaFecha = true;

if (Validator.isNotNull(evaluacion)) {
    fechaEvaluacion.setTime(evaluacion.getFecha());

    if (Validator.isNotNull(evaluacion.getProxima())) {
        fechaProxima.setTime(evaluacion.getProxima());
        emptyProximaFecha = false;
    }
}

%>

<portlet:renderURL var="cancelURL">
    <portlet:param name="mvcRenderCommandName" value="/"></portlet:param>
    <portlet:param name="comp" value="<%=compId%>"></portlet:param>
    <portlet:param name="legislacion" value="<%=legislacionId%>"></portlet:param>
</portlet:renderURL>

<div class="content">
    <div class="formulario">
        <div class="title-group mt-3">
            <a href="<%= cancelURL.toString() %>" class="toBackView">
                <i class="icon-arrow-left"></i>
                <liferay-ui:message key="evaluacion.view.back"/>
            </a>
        </div>
        <div class="form-content prv-search prv-form mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">

            <liferay-portlet:actionURL name="/evaluacion/legislacion/save" var="evaluacionURL" />
            <aui:form  name="contract_form" action="<%=evaluacionURL.toString()%>" method="post" enctype="multipart/form-data">
                <aui:input type="hidden" name="<%= WebEvaluacionPortletKeys.COMP_PARAM %>" value="<%= compId %>"></aui:input>
                <aui:input type="hidden" name="<%= WebEvaluacionPortletKeys.LEGISLACION_PARAM %>" value="<%= legislacionId %>"></aui:input>
                <aui:input type="hidden" name="<%= WebEvaluacionPortletKeys.VERSION_PARAM %>"
                            value="<%= Validator.isNotNull(evaluacion) ? evaluacion.getVersion() : "" %>"></aui:input>

                <div class="row align-items-start mb-4">

                    <%-- LEGISLACION NOMBRE --%>
                    <div class="form-group col-md-12 col-12">
                        <div class="form-group input-text-wrapper">
                            <label class="control-label">
                                <liferay-ui:message key="legislacion.view.legislacion"/>
                            </label>
                            <p><%= legislacion.getNombre() %></p>
                        </div>
                    </div>

                    <%-- LEGISLACION DESCRIPCION --%>
                    <div class="form-group col-md-12 col-12">
                        <div class="form-group input-text-wrapper">
                            <label class="control-label">
                                <liferay-ui:message key="legislacion.view.descripcion"/>
                            </label>
                            <p><%= legislacion.getDescripcion() %></p>
                        </div>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- FECHA EVALUACION --%>
                    <div class="form-group col-md-3 col-12 required">
                        <div class="form-group input-text-wrapper">
                            <label class="control-label" for="<%= WebEvaluacionPortletKeys.FECHA_EVALUACION %>">
                                <liferay-ui:message key="evaluacion.view.fechaEvaluacion"/>
                            </label>
                            <liferay-ui:input-date name="<%= WebEvaluacionPortletKeys.FECHA_EVALUACION %>"
                                required="true"
                                yearValue="<%=fechaEvaluacion.get(Calendar.YEAR)%>"
                                monthValue="<%=fechaEvaluacion.get(Calendar.MONTH)%>"
                                dayValue="<%=fechaEvaluacion.get(Calendar.DAY_OF_MONTH)%>">
                            </liferay-ui:input-date>
                        </div>
                        <span id="requiredFechaEvaluacion" class="clsRequiredSelect">
                           <span class="has-error">
                               <div class="form-validator-stack help-block">
                                   <div role="alert" class=" required">
                                       <liferay-ui:message key="evaluacion.edit.error.fechaEvaluacion" />
                                   </div>
                               </div>
                           </span>
                        </span>
                    </div>

                    <%-- PROXIMA EVALUACION --%>
                    <div class="form-group col-md-3 col-12">
                        <div class="form-group input-text-wrapper">
                            <label class="control-label" for="<%= WebEvaluacionPortletKeys.FECHA_PROXIMA %>">
                                <liferay-ui:message key="evaluacion.view.proxEvaluacion"/>
                            </label>
                            <c:if test="<%=emptyProximaFecha%>">
                                <liferay-ui:input-date name="<%= WebEvaluacionPortletKeys.FECHA_PROXIMA %>" nullable="true" showDisableCheckbox="false"/>
                            </c:if>
                            <c:if test="<%=!emptyProximaFecha%>">
                                <liferay-ui:input-date name="<%= WebEvaluacionPortletKeys.FECHA_PROXIMA %>" nullable="true" showDisableCheckbox="false"
                                       yearValue="<%=fechaProxima.get(Calendar.YEAR)%>"
                                       monthValue="<%=fechaProxima.get(Calendar.MONTH)%>"
                                       dayValue="<%=fechaProxima.get(Calendar.DAY_OF_MONTH)%>" />
                            </c:if>
                        </div>
                    </div>

                    <%-- CUMPLIMIENTO --%>
                    <div class="form-group col-md-6 col-12 required">
                        <fieldset>
                            <legend>
                                <liferay-ui:message key="evaluacion.view.cumplimiento" />
                            </legend>
                            <div class="checksBorder">
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="<portlet:namespace/><%= WebEvaluacionPortletKeys.CUMPLIMIENTO %>"
                                        id="cumplimiento1" value="0" type="radio" <%= Validator.isNotNull(evaluacion) && evaluacion.getCumplimiento() == 0 ? "checked" : "" %> />
                                    <label class="form-check-label" for="cumplimiento1">Si</label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="<portlet:namespace/><%= WebEvaluacionPortletKeys.CUMPLIMIENTO %>"
                                        id="cumplimiento2" value="1" type="radio" <%= Validator.isNotNull(evaluacion) && evaluacion.getCumplimiento() == 1 ? "checked" : "" %> />
                                    <label class="form-check-label" for="cumplimiento2">No</label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input" name="<portlet:namespace/><%= WebEvaluacionPortletKeys.CUMPLIMIENTO %>"
                                        id="cumplimiento3" value="2" type="radio" <%= Validator.isNotNull(evaluacion) && evaluacion.getCumplimiento() == 2 ? "checked" : "" %> />
                                    <label class="form-check-label" for="cumplimiento3">No procede</label>
                                </div>
                            </div>
                        </fieldset>
                        <span id="requiredCumplimiento" class="clsRequiredSelect">
                           <span class="has-error">
                               <div class="form-validator-stack help-block">
                                   <div role="alert" class=" required">
                                       <liferay-ui:message key="evaluacion.edit.error" />
                                   </div>
                               </div>
                           </span>
                        </span>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- OBSERVACIONES --%>
                    <div class="form-group col-md-12 col-12">
                        <div class="form-group input-text-wrapper">
                            <aui:input name="<%= WebEvaluacionPortletKeys.OBSERVACIONES %>" type="textarea" label="evaluacion.view.observaciones"
                                value="<%= Validator.isNotNull(evaluacion) ?  evaluacion.getObservaciones() : "" %>" />
                        </div>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- FICHERO --%>
                    <div class="form-group col-12 col-md-6">
                        <div class="prv-file-container">
                            <aui:input name="<%= WebEvaluacionPortletKeys.FICHERO %>" label="evaluacion.view.adjunto" type="file" />

                            <div class="prv-input-file__span">
                                <span class="prv-input-file__btn" id="prv-input-file_evaluacion"><liferay-ui:message key="evaluacion.view.examinar"/></span>
                                <span class="prv-input-file__txt" id="prv-input-file_txt_evaluacion"></span>
                            </div>
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
</div>

<script>
    $(document).ready(function(){
        $("#prv-input-file_evaluacion").on("click", function(e){
            $('#<portlet:namespace/><%= WebEvaluacionPortletKeys.FICHERO %>').click();
        });

        $('#<portlet:namespace/><%= WebEvaluacionPortletKeys.FICHERO %>').on("change", function(e){
            var fileName = $('input[type=file]').val().split('\\').pop();
            $("#prv-input-file_txt_evaluacion").html("");
            $("#prv-input-file_txt_evaluacion").html(fileName);
        });
    });

    function checkForm(){
        let isOk = true;

        if( $('input:radio[name=<portlet:namespace/><%= WebEvaluacionPortletKeys.CUMPLIMIENTO %>]:checked').length == 0 ) {
            $("#requiredCumplimiento").attr("class", "clsRequiredSelect_visible");
            isOk = false;
        }

        var fechaEvaluacion = $('#<portlet:namespace/><%= WebEvaluacionPortletKeys.FECHA_EVALUACION %>').val();
        var [day, month, year] = fechaEvaluacion.split('/');
        if (new Date(year, month - 1, day).getTime() > new Date().setHours(0,0,0,0) ) {
            $("#requiredFechaEvaluacion").attr("class", "clsRequiredSelect_visible");
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