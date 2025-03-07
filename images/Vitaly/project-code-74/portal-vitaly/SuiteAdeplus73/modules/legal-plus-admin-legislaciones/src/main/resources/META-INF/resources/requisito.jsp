<%@ taglib prefix="liferay-asset" uri="http://liferay.com/tld/ui" %>
<%@ page import="com.legalplus.liferay.portlet.admin.legislaciones.web.constants.AdminLegislacionesPortletKeys" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.Requisito" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.Legislacion" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.RequisitoCNAES" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.service.RequisitoCNAESLocalServiceUtil" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.service.RequisitoLocalServiceUtil" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.service.LegislacionLocalServiceUtil" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.service.LegislacionCNAESLocalServiceUtil" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.service.CNAESLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.service.*" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.*" %>
<%@ include file="/init.jsp" %>


<%
    String legislacionEditId = ParamUtil.getString(request, AdminLegislacionesPortletKeys.LEGISLACION_ID_EDIT, "");
    String requisitoEditId = ParamUtil.getString(request, AdminLegislacionesPortletKeys.REQUISITO_ID_EDIT, "");

    Calendar fechaPublicacionCalendar = Calendar.getInstance();
    fechaPublicacionCalendar.setTime(new Date());
    Calendar fechaBajaCalendar = Calendar.getInstance();
    fechaBajaCalendar.setTime(new Date());

    boolean disabledFechaBaja = true;

    Requisito requisito = null;
    Legislacion legislacion = null;

    Map<String,String> mCNAE = new HashMap<String,String>();
    List<LegislacionCNAES> legCNAES = null;
    CNAES cnae = null;
    String strCNAES = "";
    if(legislacionEditId != ""){

        legislacion = LegislacionLocalServiceUtil.fetchLegislacion(legislacionEditId);

    	if(requisitoEditId != ""){
    		requisito = RequisitoLocalServiceUtil.findByLegislacionRequisito(legislacionEditId, requisitoEditId);
    		List<RequisitoCNAES> lCNAES = RequisitoCNAESLocalServiceUtil.findByRequisito(legislacionEditId, requisitoEditId);
    		for(RequisitoCNAES reqCNAE : lCNAES){
    			if(!strCNAES.isEmpty()) strCNAES += ",";
    			strCNAES += reqCNAE.getCnae();
    		}
    		if(Validator.isNotNull(requisito)){
                if(Validator.isNotNull(requisito.getBaja())) {
                    fechaBajaCalendar.setTime(requisito.getBaja());
                    disabledFechaBaja = false;
                }
            }
    	}

        legCNAES = LegislacionCNAESLocalServiceUtil.findByLegislacion(legislacionEditId);
        for(LegislacionCNAES legisCNAE : legCNAES){

        	if(legisCNAE.getCnae().equals("99999") ){
        		mCNAE.put(legisCNAE.getCnae(), "Todos los CNAEs");
        	}else{
        		cnae = CNAESLocalServiceUtil.fetchBycnae(legisCNAE.getCnae());
            	if(cnae != null)  mCNAE.put(legisCNAE.getCnae(), cnae.getNombre());
        	}


        }
    }

    

   
%>

<!-- dependencias select2 -->
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<!-- Libreria espa�ol -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/i18n/es.js"></script>
<style>
	.clsSelector2Width{
		width : 99%;		
	}
	.clsRequiredSelect{
		display: none;
	}
	.clsRequiredSelect_visible{
		display: inline;
	}

</style>
<liferay-ui:success key="requisito-save-success" message="admin.legislaciones.edit.requisito.save.success" />

<liferay-ui:error key="error-create-requisito" message="admin.legislaciones.edit.requisito.error" />

<liferay-portlet:actionURL name="/requisito/save" var="saveURL" />
<aui:form  name="edit_form" action="<%=saveURL.toString()%>" method="post">

    <aui:input name="<%=AdminLegislacionesPortletKeys.LEGISLACION_ID_EDIT%>" value="<%=legislacionEditId%>" type="hidden" ignoreRequestValue="true"/>
    <aui:input name="<%=AdminLegislacionesPortletKeys.REQUISITO_ID_EDIT%>" value="<%=requisitoEditId%>" type="hidden" ignoreRequestValue="true"/>

    <div class="content form-datos prv-form">
        <div class="formulario">
            <div class="titulo-seccion">
                <h2><liferay-ui:message key="admin.legislaciones.edit.requisito"/> (<%=Validator.isNotNull(requisito)? requisito.getRequisitoId() : ""%>)</h2>
            </div>
            <div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
                <div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">
                    <div class="row">
                        <div class="form-group col-md-9 required">
                            <aui:input name="<%=AdminLegislacionesPortletKeys.REQUISITO%>" type="textarea" label="admin.legislaciones.view.requisito.nombre" value="<%=Validator.isNotNull(requisito)?requisito.getDescripcion():""%>">
                                <aui:validator name="required" errorMessage="admin.legislaciones.edit.requisito.validator.required" />
                            </aui:input>
                        </div>
                        <div class="form-group col-md-3">
                            <span id="spnFechaBaja"> <!-- para buscar el check -->
                                <label for="<%=AdminLegislacionesPortletKeys.FECHA_BAJA%>" class="control-label"><liferay-ui:message key="admin.legislaciones.edit.requisitos.fecha.baja"/></label>
                                <c:if test="<%= disabledFechaBaja %>">
                                    <liferay-ui:input-date name="<%=AdminLegislacionesPortletKeys.FECHA_BAJA%>" nullable="true" disabled="disabled"/>
                                </c:if>
                                <c:if test="<%= !disabledFechaBaja %>">
                                    <liferay-ui:input-date name="<%=AdminLegislacionesPortletKeys.FECHA_BAJA%>" nullable="true"
                                                           yearValue="<%=fechaBajaCalendar.get(Calendar.YEAR)%>"
                                                           monthValue="<%=fechaBajaCalendar.get(Calendar.MONTH)%>"
                                                           dayValue="<%=fechaBajaCalendar.get(Calendar.DAY_OF_MONTH)%>" />
                               </c:if>
                            </span>
                        </div>
                    </div>
                    <div class="row align-items-start mb-2">
                        <div class="col-lg-3 col-md-6 col-12">
                            <fieldset class="">
                                <div class="form-group input-select-wrapper">
                                    <select  id="cmb_cnae" name="cmb_cnae" class="js-select2-multi clsSelector2Width required" onChange="changeCNAE_2();" >
                                        <c:forEach var="entry" items="<%=mCNAE%>">
                                             <aui:option value="${entry.key}">
                                               ${entry.value} (${entry.key})
                                             </aui:option>

                                        </c:forEach>
                                    </select>
                                    <span style="display:none;">
                                        <aui:input name="<%=AdminLegislacionesPortletKeys.RCNAE%>" type="text" label="admin.legislaciones.view.requisito.cnae"
                                            value="<%=strCNAES%>">
                                        </aui:input>
                                    </span>
                                    <!-- obligatorio  -->
                                    <span id="spnRequiredCNAE" class="clsRequiredSelect">
                                        <span class="has-error">
                                            <div class="form-validator-stack help-block">
                                                <div role="alert" class=" required">
                                                        <liferay-ui:message key="admin.legislaciones.edit.validator.required" />
                                                </div>
                                            </div>
                                        </span>
                                    </span>
                                </div>
                            </fieldset>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="button-holder d-flex justify-content-center my-4">
        <aui:button-row>

            <portlet:renderURL var="cancelURL">
                <portlet:param name="<%=AdminLegislacionesPortletKeys.LEGISLACION_ID_EDIT%>" value="<%=String.valueOf(legislacionEditId)%>" />
                <portlet:param name="mvcPath" value="/legislacion.jsp"></portlet:param>
            </portlet:renderURL>
            <aui:button onClick="<%= cancelURL.toString() %>" value="back" cssClass="btn btn-outline-primary mr-4" primary="true"></aui:button>

            <aui:input type="hidden" name="<%=AdminLegislacionesPortletKeys.FORM_ACTION%>" value="<%=AdminLegislacionesPortletKeys.FORM_SAVE%>" />
            <aui:button type="submit" value="admin.legislaciones.edit.requisito.save.conitnue" onClick="checkSubmit('continue')" />
            <aui:button type="submit" onClick="checkSubmit('save')" />

        </aui:button-row>
    </div>
<script>
var nameCNAE = "#<portlet:namespace/><%=AdminLegislacionesPortletKeys.RCNAE%>";
var requisitoID = '<%=ParamUtil.getString(request, AdminLegislacionesPortletKeys.REQUISITO_ID_EDIT, "")%>';
var dateBaja = '<%=(Validator.isNotNull(requisito))? requisito.getBaja() : null %>';
var empresas = '<%= Validator.isNotNull(legislacion) && Validator.isNotNull(legislacion.getEmpresas()) %>';
var cmbCNAE = null;

$(document).ready( function () {

    if (dateBaja == "null") {
        $("#spnFechaBaja  input[type=checkbox]").click();
    }

	cmbCNAE = $('.js-select2-multi').select2({
	        placeholder: '<liferay-ui:message key="admin.legislaciones.view.select"/>',
	        multiple : true,    
	        language: "es",
	        allowClear: true
	        
	 });

	if(requisitoID == ""){
		cmbCNAE.find('option').prop("selected",true).trigger('change');
	}else{
		cmbCNAE.val( $(nameCNAE).val().split(",")).trigger('change');
	}
	
});

function checkSubmit(action){
	if($(nameCNAE).val() == "" && empresas != "true"){
		$("#spnRequiredCNAE").attr("class","clsRequiredSelect_visible");
		event.preventDefault();
		event.stopPropagation();
		return false;	
	}
	$("#<portlet:namespace/><%=AdminLegislacionesPortletKeys.FORM_ACTION%>").val(action);
	return true;
}

function changeCNAE_2(){	
	try{
		$(nameCNAE).val($("#cmb_cnae").val());
		$("#spnRequiredCNAE").attr("class","clsRequiredSelect");	
	}catch(e){
		console.error("changeCNAE_2(): " + e);
	}
		
}

</script>
</aui:form>