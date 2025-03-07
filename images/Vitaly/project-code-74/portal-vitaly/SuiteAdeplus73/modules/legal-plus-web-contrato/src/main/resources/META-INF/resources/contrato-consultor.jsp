<%@ include file="menu.jsp" %>

<liferay-ui:success key="contrato-save-success" message="contrato.edit.success" />

<div class="content">
    <div class="formulario">
        <div class="form-content prv-search prv-form mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">

            <liferay-portlet:actionURL name="/contract/save" var="contractURL" />
            <aui:form  name="contract_form" action="<%=contractURL%>" method="post">
                <aui:input type="hidden" name="<%=WebContratoPortletKeys.COMPANY_ID%>" value="${compId}"></aui:input>

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
                                        cssClass="field check-app"></aui:input>
                                </div>
                                <div class="input-checkbox-wrapper">
                                    <aui:input name="<%=WebContratoPortletKeys.FAMILIA_MEDIO_AMBIENTE%>" type="checkbox"
                                        label="contract.view.familia.medioambiente" checked="<%= Validator.isNotNull(contract) ? contract.getFamilia().contains("1") : false%>"
                                        cssClass="field check-app"></aui:input>
                                </div>
                                <div class="input-checkbox-wrapper">
                                    <aui:input name="<%=WebContratoPortletKeys.FAMILIA_SEGURIDAD_INDUSTRIAL%>" type="checkbox"
                                        label="contract.view.familia.seguridad.industrial" checked="<%= Validator.isNotNull(contract) ? contract.getFamilia().contains("2") : false%>"
                                        cssClass="field check-app"></aui:input>
                                </div>
                                <div class="input-checkbox-wrapper">
                                    <aui:input name="<%=WebContratoPortletKeys.FAMILIA_SEGURIDAD_ALIMENTARIA%>" type="checkbox"
                                        label="contract.view.familia.seguridad.alimentaria" checked="<%= Validator.isNotNull(contract) ? contract.getFamilia().contains("3") : false%>"
                                        cssClass="field check-app"></aui:input>
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

                <div class="row">

                    <%-- CCAA --%>
                    <div class="col-lg-3 col-md-6 col-12">
                        <fieldset class="input-group-inline">
                            <div class="form-group input-select-wrapper">
                                <label class="control-label" for="cmb_ccaa"><liferay-ui:message key="contract.view.ccaa"/></label>
                                <select id="cmb_ccaa" name="<portlet:namespace />cmb_ccaa" class="js-select2-multi clsSelector2Width" onchange="selectChange(this)">
                                    <c:forEach var="curCCAA" items="<%=ccaaList%>">
                                         <aui:option value="${curCCAA.ccaaId}">${curCCAA.nombre}</aui:option>
                                    </c:forEach>
                                </select>
                            </div>
                        </fieldset>
                    </div>

                    <%-- AYUNTAMIENTOS --%>
                    <div class="col-md-4">
                        <fieldset class="input-group-inline">
                            <div class="form-group input-select-wrapper">
                                <label class="control-label" for="ayuntamientos">
                                    <liferay-ui:message key="contract.view.ayuntamientos"/>
                                </label>
                                <select id="cmb_ayto" name="<portlet:namespace />cmb_ayto" class="js-select2-multi-ayto clsSelector2Width"
                                        onchange="selectChange(this)" disabled="true">
                                </select>
                            </div>
                        </fieldset>
                    </div>

                    <%-- CNAES --%>
                    <div class="col-md-4">
                        <fieldset class="input-group-inline">
                            <div class="form-group input-select-wrapper">
                                <label class="control-label" for="cnaes">
                                    <liferay-ui:message key="contract.view.cnaes"/>
                                </label>
                                <br />
                                <select id="cnaes" name="<portlet:namespace />cnaes" class="js-select2-multi clsSelector2Width" onchange="selectChange(this)">
                                    <c:forEach var="cnae" items="<%=cnaesList%>">
                                         <aui:option value="${cnae.cnae}">${cnae.nombre} (${cnae.cnae})</aui:option>
                                    </c:forEach>
                                </select>
                            </div>
                        </fieldset>
                    </div>

                </div>

                <div class="row justify-content-end">
                    <aui:button type="submit" cssClass="btn btn-primary"/>
                </div>

            </aui:form>

        </div>
    </div>
</div>

<portlet:resourceURL id="buscarAyuntamientos" var="buscarAyuntamientosURL"/>
<script>
$(document).ready( function () {

    $('#cnaes').select2({
        placeholder: "Seleccione ..",
        multiple : true,
        language: "es"
    });
    $('#cnaes').val("<%= Validator.isNotNull(contract) ? contract.getCnaes() : "" %>".split(';')).trigger("change");

    $('#cmb_ccaa').select2({
        placeholder: "Seleccione ..",
        multiple : true,
        language: "es"
    });
    $('#cmb_ccaa').val("<%= Validator.isNotNull(contract) ? contract.getCcaa(): "" %>".split(';')).trigger("change");

    $('#cmb_ayto').select2({
		placeholder: "Seleccione ..",
        multiple : true,
        language: "es"
	});
	initAyuntamientos();
});

$(document).on('click', '.contract-info', function(e){
    e.preventDefault();
    $('#infoContrato').removeClass('hide');
    $('#infoContrato').addClass('show');
});

$(document).on('click', '.hide-modal', function(e) {
    e.preventDefault();
    $('#infoContrato').addClass('hide');
});

$("#cmb_ccaa").on("change", function (e) {
    var ayuntamientos = $("#cmb_ayto").val();
    var ccaa = $("#cmb_ccaa").val();

    if (ayuntamientos) {
        $('#cmb_ayto').empty();
        findAyuntamientos(ayuntamientos, ccaa);
    }
});

function selectChange(_node) {
    var howMuch =$("#" + _node.id).val().length
    _node.nextElementSibling.style.marginBottom =  (30 * howMuch) + 3 + "px";
}

function initAyuntamientos() {
    var ayuntamientos = "<%= Validator.isNotNull(contract) ? contract.getAyuntamiento() : "" %>".split(";");
    var ccaa = "<%= Validator.isNotNull(contract) ? contract.getCcaa() : "" %>".split(";");
    findAyuntamientos(ayuntamientos, ccaa);
}

function findAyuntamientos(ayuntamientos, ccaa) {
    $("#cmb_ayto").prop("disabled", true);
    $.ajax( "<%= buscarAyuntamientosURL %>", {
        dataType: 'json',
        data: { ccaaIds: ccaa.toString() },
        success: function (data, status, xhr) {
            for(var i = 0; i < data.length; i++){
                if (ayuntamientos.includes(data[i].ayuntamientoId + '')) {
                    $("#cmb_ayto").append(new Option(data[i].nombre, data[i].ayuntamientoId, true, true));
                    $("#cmb_ayto").trigger("change");
                } else {
                    $("#cmb_ayto").append(new Option(data[i].nombre, data[i].ayuntamientoId, false, false));
                }
            }
            $("#cmb_ayto").prop("disabled", false);
        }
    });
}

</script>