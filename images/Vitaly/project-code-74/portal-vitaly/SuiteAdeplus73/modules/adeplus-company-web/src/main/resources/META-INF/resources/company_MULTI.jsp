<%@ page import="com.adeplus.liferay.portlet.commons.web.permission.AdeplusPermissionCompanyUtil" %>
<%@ page import="com.adeplus.liferay.portlet.company.web.constants.AdeplusCompaniesPortletKeys" %>
<%@ page import="com.liferay.counter.kernel.service.CounterLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.model.User" %>
<%@ page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.*" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.*" %>
<%@ include file="init.jsp" %>

<%
    long companyEditId = ParamUtil.getLong(request, AdeplusCompaniesPortletKeys.COMPANY_ID_EDIT, 0);
    long companyCIF = ParamUtil.getLong(request, AdeplusCompaniesPortletKeys.COMPANY_CIF, 0);

    List<Application> appList = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);


    boolean disabledEndDate = true;
    boolean addNewUser = true;
    Calendar endDateCalendar = Calendar.getInstance();
    endDateCalendar.setTime(new Date());

    Calendar firstEnabledDate = Calendar.getInstance();

    firstEnabledDate.set(Calendar.HOUR_OF_DAY, 0);
    firstEnabledDate.set(Calendar.MINUTE, 0);
    firstEnabledDate.set(Calendar.SECOND, 0);
    firstEnabledDate.set(Calendar.MILLISECOND, 0);

    firstEnabledDate.add(Calendar.DAY_OF_MONTH, -30);

    List<UserCompany> usersFromCompany = null;
    Comp comp = null;
    boolean exist=false;
    if(companyEditId > 0) {
        comp = CompLocalServiceUtil.getComp(companyEditId);
        if(Validator.isNotNull(comp)){
            usersFromCompany = UserCompanyLocalServiceUtil.getUsersFromCompany(comp.getCompId());
            exist=true;
        }
    }else{
        companyEditId = CounterLocalServiceUtil.increment(Comp.class.getName());
    }

    if(Validator.isNotNull(comp) && Validator.isNotNull(comp.getDeleteDate())){
        endDateCalendar.setTime(comp.getDeleteDate());
        disabledEndDate = false;

        if(comp.getDeleteDate().before(firstEnabledDate.getTime())) {
            addNewUser = false;
        }
    }
   boolean addNewUserMulti=true;
%>

<liferay-ui:success key="company-save-success" message="company.view.save.success" />
<liferay-ui:success key="save-user-success" message="company.view.save.user.success" />

<liferay-ui:error key="error-create-company" message="company.view.save.error.cif" />
<liferay-ui:error key="error-create-nif" message="company.view.save.error.nif" />
<liferay-ui:error key="error-create-email" message="company.view.save.error.email" />

<liferay-portlet:actionURL name="/company/saveMulti" var="saveCompURL" />

<h1>MULTI</h1>


<aui:form  name="edit_form" action="<%=saveCompURL.toString()%>" method="post">

    <aui:input name="<%=AdeplusCompaniesPortletKeys.COMPANY_ID_EDIT%>" value="<%=companyEditId%>" type="hidden" ignoreRequestValue="true"/>
    <aui:input name="jsonClientContractApps"  type="hidden" ignoreRequestValue="true" />
    <aui:input name="deleteClientContract"  type="hidden" ignoreRequestValue="true" />
    <aui:input name="modifiedClientContract"  type="hidden" ignoreRequestValue="true" />

    <div class="content">
        <div class="titulo-seccion">
            <h2><liferay-ui:message key="company.edit.title"/></h2>
        </div>
        
        <div class="formulario">
            <div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
                <div class="form-content w-100 mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">
                    <div class="row">
                        <div class="form-group col-md-6 required">
                            <aui:input name="<%=AdeplusCompaniesPortletKeys.COMPANY_CIF%>" type="text" label="company.view.cif" value="<%=Validator.isNotNull(comp)?comp.getCif():""%>" >
                                <aui:validator name="required" errorMessage="company.edit.validator.required" />
                                <aui:validator name="alphanum" errorMessage="company.error.alfanum"></aui:validator>
                            </aui:input>
                        </div>
                        <div class="form-group col-md-6 required">
                            <aui:input name="<%=AdeplusCompaniesPortletKeys.COMPANY_NAME%>" type="text" label="company.view.name" value="<%=Validator.isNotNull(comp)?comp.getName():""%>" >
                                <aui:validator name="required" errorMessage="company.edit.validator.required" />
                            </aui:input>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <aui:input name="<%=AdeplusCompaniesPortletKeys.COMPANY_DESCRIPTION%>" type="text" label="company.view.descripcion" value="<%=Validator.isNotNull(comp)?comp.getDescription():""%>" >
                            </aui:input>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <aui:input name="<%=AdeplusCompaniesPortletKeys.COMPANY_OBSERVATION%>" type="text" label="company.view.observations" value="<%=Validator.isNotNull(comp)?comp.getObservations():""%>" >
                            </aui:input>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <aui:input name="<%=AdeplusCompaniesPortletKeys.COMPANY_AGREEMENT%>" type="text" label="company.view.agreement" value="<%=Validator.isNotNull(comp)?comp.getAgreement():""%>">
                            </aui:input>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="companyDateEnd" class="control-label"><liferay-ui:message key="company.view.date.end"/></label>
                            <c:if test="<%=disabledEndDate%>">
                                <liferay-ui:input-date name="companyDateEnd" nullable="true" disabled="true" firstEnabledDate="<%=firstEnabledDate.getTime()%>" lastEnabledDate="<%=new Date()%>" />
                            </c:if>
                            <c:if test="<%=!disabledEndDate%>">
                                <liferay-ui:input-date name="companyDateEnd" nullable="true" firstEnabledDate="<%=firstEnabledDate.getTime()%>"  lastEnabledDate="<%=new Date()%>"
                                                       yearValue="<%=endDateCalendar.get(Calendar.YEAR)%>"
                                                       monthValue="<%=endDateCalendar.get(Calendar.MONTH)%>"
                                                       dayValue="<%=endDateCalendar.get(Calendar.DAY_OF_MONTH)%>" />
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">
                           <p class="alert alert-warning"> <liferay-ui:message key="bases.legales"/> <p>
                        </div>
                    </div>
                    <!-- SELECTOR CLIENTE CONTRATO -->
                     <div class="capaSelectorCliente">
                                <div class="row">
                                    <div class="col-2">
                                        <label class="control-label" for="inpCliente" >Cliente </label>
                                        <br />
                                        <input type="text" id="inpCliente" style="width:100%;" />
                                    </div>
                                    <div class="col-2">
                                        <label class="control-label" for="inpContrato" >Contrato </label>
                                        <br />
                                        <input type="text" id="inpContrato" style="width:100%;" />
                                    </div>
                                    <div class="col-6">
                                        <label class="control-label" for="inpNombreContrato" >Nombre </label>
                                        <br />
                                        <input type="text" id="inpNombreContrato" style="width:100%;" />
                                    </div>
                                    <div class="col-2">
                                        &nbsp;<br />
                                        <button type="button" class="btn btn-primary " onclick="addCliente()"> + </button>
                                    </div>

                                </div>
                            </div>
                            <br/>
                            <div id="contenedorContratos" class="">

                            </div>
                            <div id="capaMensaje" class="alert alert-warning hide">
                                <div class="col-12 col-sm-12">
                                    <span id="spantxtMensaje"> </span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <button id="btnVerJSON" type="button" class="btn btn-primary" onclick="viewJSON()">VER JSON - DESA</button>
                                    <textarea id="inpJSON" style="width:100%;height:200px;"></textarea>
                                </div>
                            </div>

                    <!-- END SELECTOR CLIENTE CONTRATO -->

                    <c:if test="<%=Validator.isNull(comp)%>">
                        <div class="row">
                            <div class="input-group mb-3">
                                <legend class="fieldset-title-section"><liferay-ui:message key="user.edit.user.admin"/></legend>
                                <div class="row">
                                    <div class="form-group col-md-6">
                                        <aui:input name="<%=AdeplusCompaniesPortletKeys.USER_NIF%>" type="text" label="user.edit.nif">
                                            <aui:validator name="alphanum" errorMessage="company.error.alfanum"></aui:validator>
                                        </aui:input>
                                    </div>
                                    <div class="form-group col-md-6 required">
                                        <aui:input name="<%=AdeplusCompaniesPortletKeys.USER_NAME%>" type="text" label="user.edit.name">
                                            <aui:validator name="required" errorMessage="user.edit.validator.required" />
                                        </aui:input>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-md-6 required">
                                        <aui:input name="<%=AdeplusCompaniesPortletKeys.USER_LAST_NAME%>" type="text" label="user.edit.last.name">
                                            <aui:validator name="required" errorMessage="user.edit.validator.required" />
                                        </aui:input>
                                    </div>
                                    <div class="form-group col-md-6 required">
                                        <aui:input name="<%=AdeplusCompaniesPortletKeys.USER_EMAIL%>" type="text" label="user.edit.email" >
                                            <aui:validator name="required" errorMessage="user.edit.validator.required" />
                                            <aui:validator name="email" errorMessage="user.edit.email.error.format" />
                                        </aui:input>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-md-6">
                                        <aui:select name="<%=AdeplusCompaniesPortletKeys.USER_LANGUAGE%>" label="user.edit.language">
                                            <aui:option value="es_ES" ><liferay-ui:message key="user.edit.language.spanish"/></aui:option>
                                            <aui:option value="ca_ES" ><liferay-ui:message key="user.edit.language.cat"/></aui:option>
                                        </aui:select>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </c:if>

                </div>

            </div>
        </div>
    </div>

    <div class="button-holder d-flex justify-content-center my-4">
        <aui:button-row>
            <portlet:actionURL name="cancel" var="cancelURL">
                <portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
            </portlet:actionURL>
            <aui:button onClick="<%= cancelURL.toString() %>" value="company.edit.cancel" cssClass="btn btn-outline-primary mr-4" primary="true"></aui:button>
            <button class="btn btn-primary" onClick="isLimitsCorrect()" type="button" >
                <span class="lfr-btn-label">
                    <liferay-ui:message key="user.edit.btnGuardar"/>
                </span>
            </button>
            <aui:button type="submit" cssClass="btn btn-primary btnGuardar hide" />

        </aui:button-row>
    </div>

</aui:form>

<!-- START : PLANTILLA DE HTML-->
        <span class="hide" id="_div_Plantilla">
        <div class="  border border-secondary   mt-5 ml-0 mb-5" >
            <div class="row mr-0 ml-1 mt-3">
                <div class="col-8">
                   <div id="spn#CLIENTE#-#CONTRATO#">
                        <span id="spnCliente#CLIENTE#" class="label label-primary">#CLIENTE#</span> - <span id="spnContrato#CONTRATO#" class="label label-warning">#CONTRATO#</span> - <span id="spnNombre#NOMBRE#" class="label label-success">#NOMBRE#</span>
                    </div>
                    <div class="hide" id="inpt#CLIENTE#-#CONTRATO#">
                    <input id="inptCliente#CLIENTE#" class="label label-primary" value="#CLIENTE#"></input> - <input id="inptContrato#CONTRATO#" class="label label-warning" value="#CONTRATO#"></input> - <input id="inptNombre#CLIENTE#-#CONTRATO#" class="label label-success" value="#NOMBRE#"></input>
                    </div>
                </div>
                <div class="col-4 text-right">
                    <button type="button" class="btn hide btn-outline-primary" id="save#CLIENTE#-#CONTRATO#" onclick="saveClienteContrato('#CLIENTE#','#CONTRATO#')">
                        <span class="lfr-btn-label">
                            <liferay-ui:message key="user.edit.btnGuardar"/>
                        </span>
                    </button>
                    <button type="button" class="btn btn-outline-primary " id="update#CLIENTE#-#CONTRATO#" onclick="updateClienteContrato('#CLIENTE#','#CONTRATO#')"><span class="icon-pencil"></span></button>
                    <button type="button" class="btn btn-outline-primary " onclick="quitClienteContrato('#CLIENTE#','#CONTRATO#')">X</button>
                </div>
            </div>
            <div class="row mr-10 ml-5 mt-3 mb-3">
                    #APPS#
            </div>
        </div>
        </span>
<!-- END : PLANTILLA DE HTML-->

<c:if test="<%=Validator.isNotNull(comp)%>">
    <%@include file="users_Multi.jspf" %>
</c:if>

<liferay-portlet:resourceURL  copyCurrentRenderParameters="false" id="getAllClients" var="getAllClientsURL" />
<liferay-portlet:resourceURL  copyCurrentRenderParameters="false" id="getAllApplications" var="getAllApplicationsURL" />
<liferay-portlet:resourceURL  copyCurrentRenderParameters="false" id="removeClient" var="removeClientURL" />
<script>
    /*deleteDisabledClass();
    function deleteDisabledClass() {
        var element = document.getElementById("_com_adeplus_liferay_portlet_company_web_AdeplusCompaniesPortlet_companyDateEnd");
        element.classList.remove("disabled");
    }*/
</script>
<script>

    var arrApps = [];
    var js_cliente = null;
    var js_update = null;
    var deleteClientContract = false;
    var updateClientContract = false;
    $(document).ready(function(){
        js_cliente = [];
        js_update = [];
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(){
            if (this.readyState == 4 && this.status == 200){
                arrApps = JSON.parse(this.responseText);
                if(<%=exist%> == true){
                    getAllClients(js_cliente);
                }
            }
        };
        xhr.open("GET", "<%=getAllApplicationsURL%>", true);
        xhr.send(null);

        $('#<portlet:namespace />edit_form').submit(function(e){
            e.preventDefault(e);
            $("#<portlet:namespace/>jsonClientContractApps").val(JSON.stringify(js_cliente));

        })
    });

    function disabledCheckedApp(){
        for(var i = 0; i <  js_cliente.length; i++){
            console.log( js_cliente[i] );


            for(var x = 0; x < js_cliente[i].apps.length; x++){
            var idApp = js_cliente[i].apps[x].appId;
            var anotherNode = $("#app_"+js_cliente[i].cliente +"_"+  js_cliente[i].contrato+"_"+idApp);
                if(anotherNode.is(':checked')){
                    anotherNode.attr('disabled',false);
                }
                for(var j = 0;j<js_cliente.length;j++){
                    var anotherNodeClient = $("#app_"+js_cliente[j].cliente +"_"+  js_cliente[j].contrato+"_"+idApp);
                    if(!anotherNodeClient.is(':checked') && (js_cliente[j].cliente == js_cliente[i].cliente && js_cliente[j].contrato != js_cliente[i].contrato)){
                        anotherNodeClient.attr('disabled',true);
                    }
                }
            }
        }
    }

    var idClinte = null, idContrato = null, NombreContrato = null;

    function addItemCL(){
        if(!validateFields()){
            alert("Error faltan campos o son incorrectos");
        }
    }

    $("#<portlet:namespace/>companyName").on("blur", function(){
        if(js_cliente == null ||  js_cliente.length == 0) $("#inpNombreContrato").val(this.value);
    })



    function validateFields(){
        var item = {};
        item.cliente = ($("#inpCliente").val() && $("#inpCliente").val().trim() != "")? $("#inpCliente").val().trim() : null;
        item.contrato = ($("#inpContrato").val() && $("#inpContrato").val().trim() != "")? $("#inpContrato").val().trim() : null;
        item.nombre = ($("#inpNombreContrato").val() && $("#inpNombreContrato").val().trim() != "")? $("#inpNombreContrato").val().trim() : null;

        //solo números
        if(isNaN(item.cliente) || isNaN(item.contrato)) return null;

        item.apps = [];

        if(item.cliente == null ||  item.contrato == null  || item.nombre == null) return null;
        return item;

    }

    function cleanFields(){
        $("#inpCliente").val("");
        $("#inpContrato").val("");
        $("#inpNombreContrato").val("");
    }


    function viewJSON(){
        $("#inpJSON").val(JSON.stringify(js_cliente));
        $("#<portlet:namespace/>jsonClientContractApps").val(JSON.stringify(js_cliente));
    }

    function isNoRepeatClienteContrato(_cliente, _contrato){
        var isNoRepeat = true;
        var item = null;
        for(var i = 0; i <  js_cliente.length; i++){
            item = js_cliente[i];
            if(item.cliente == _cliente && item.contrato == _contrato){
            //if( item.contrato == _contrato){
                isNoRepeat = false;
                break;
            }
        }
    return isNoRepeat;
    }

    function addCliente(){
        var item = null;
        if( (item = validateFields()) == null){
            alert("ERROR FALTAN CAMPOS");
            return;
        }else if(!isNoRepeatClienteContrato(item.cliente, item.contrato)){
            alert("ERROR Cliente Contrato ya introducido");
            return;
        }

        var html = $("#contenedorContratos").html();
        var plantilla = $("#_div_Plantilla").html();
        plantilla = plantilla.replaceAll("#CLIENTE#",item.cliente);
        plantilla = plantilla.replaceAll("#CONTRATO#",item.contrato);
        plantilla = plantilla.replaceAll("#NOMBRE#",item.nombre);

        plantilla = plantilla.replace("#APPS#",composeApps(item.cliente, item.contrato));

        js_cliente.push(item);

        $("#contenedorContratos").html(html + plantilla);

        cleanFields();
        repaintClienteContrato();
    }
    function isLimitsCorrect(){
        var totalWithRolesToSend = 0;
        var rolesToSend = 0
        var valueDeleteClientContract = $('#<portlet:namespace/>deleteClientContract').val();

        var licenciaAnadida = "[name=license";
        for(var i = 0; i <  js_cliente.length; i++){
            for(var x = 0; x < js_cliente[i].apps.length; x++){
                licenciaAnadida+=js_cliente[i].cliente+'_'+js_cliente[i].contrato+'_'+js_cliente[i].apps[x].appId+']'

                if( $("input"+licenciaAnadida).length>0){
                    totalWithRolesToSend++
                }
                if(js_cliente[i].apps[x].licenses.length>=1){
                    rolesToSend++;
                }
                licenciaAnadida = "[name=license";
            }
        }

         if(rolesToSend!=totalWithRolesToSend){
            $("#capaMensaje").removeClass("hide");
            $("#spantxtMensaje").html('* Debe indicar el rol de cada aplicaci&oacute;n en su cliente contrato');
            return false;
         }

         if(deleteClientContract){
             valueDeleteClientContract=valueDeleteClientContract.substring(valueDeleteClientContract,valueDeleteClientContract.length-1)
             $('#<portlet:namespace/>deleteClientContract').val(valueDeleteClientContract);
         }

         if(updateClientContract){
            $('#<portlet:namespace/>modifiedClientContract').val(JSON.stringify(js_update));
         }

         $(".btnGuardar")[0].click(); //llamar a guardar

    }
    function quitClienteContrato(_cliente, _contrato){
        deleteClientContract = true
        var valueDeleteClientContract = $('#<portlet:namespace/>deleteClientContract').val();
        $('#<portlet:namespace/>deleteClientContract').val(_cliente+"-"+_contrato+","+valueDeleteClientContract);
        for(var i = 0; i <  js_cliente.length; i++){
            if(js_cliente[i].cliente == _cliente && js_cliente[i].contrato == _contrato){
                js_cliente.splice(i,1);
                break;
            }
        }
        repaintClienteContrato();
    }

    function updateClienteContrato(_cliente, _contrato){

        $('#spn'+_cliente+'-'+_contrato).addClass('hide');
        $('#inpt'+_cliente+'-'+_contrato).removeClass('hide');
        $('#update'+_cliente+'-'+_contrato).addClass('hide');
        $('#save'+_cliente+'-'+_contrato).removeClass('hide');

    }

    function saveClienteContrato(_cliente, _contrato){
        var nuevoCliente = $('#inptCliente'+_cliente).val()
        var nuevoContrato = $('#inptContrato'+_contrato).val()
        var nuevoNombre= $('#inptNombre'+_cliente+'-'+_contrato).val()
        var item = {}
        if(nuevoCliente==_cliente && nuevoContrato==_contrato){
            for(var i = 0; i <  js_cliente.length; i++){
                if(js_cliente[i].cliente == _cliente && js_cliente[i].contrato == _contrato){
                    js_cliente[i].nombre=nuevoNombre;
                    break;
                }
            }
            item.clienteNuevo=nuevoCliente
            item.contratoNuevo=nuevoContrato
            item.nombreNuevo=nuevoNombre
            js_update.push(item)
            updateClientContract = true
            repaintClienteContrato();
            return;
        }
        if(!isNoRepeatClienteContrato(nuevoCliente, nuevoContrato)){
            alert("ERROR Cliente Contrato ya introducido");
            return;
        }

        if(isNaN(nuevoCliente) || isNaN(nuevoContrato)){
            alert("Error campos incorrectos");
            return;
        }


        if(nuevoCliente == "" ||  nuevoContrato == ""  || nuevoNombre == ""){
            alert("Error faltan campos");
            return;
        }

        item.clienteAnt=_cliente
        item.contratoAnt=_contrato

        for(var i = 0; i <  js_cliente.length; i++){
            if(js_cliente[i].cliente == _cliente && js_cliente[i].contrato == _contrato){
                js_cliente[i].cliente=nuevoCliente;
                js_cliente[i].contrato=nuevoContrato;
                item.nombreAnt=js_cliente[i].nombre;
                js_cliente[i].nombre=nuevoNombre;
                break;
            }
        }
        item.clienteNuevo=nuevoCliente
        item.contratoNuevo=nuevoContrato
        item.nombreNuevo=nuevoNombre
        js_update.push(item)
        updateClientContract = true


        repaintClienteContrato();
    }

    function repaintClienteContrato(){
        var html = $("#contenedorContratos").html("");
        var plantilla = $("#_div_Plantilla").html();
        var generateHTML = "", capa = "";

        for(var i = js_cliente.length-1; i >= 0 ; i--){ //lifo
                    generateHTML = plantilla.replaceAll("#CLIENTE#",js_cliente[i].cliente);
                    generateHTML = generateHTML.replaceAll("#CONTRATO#",js_cliente[i].contrato);
                    generateHTML = generateHTML.replaceAll("#NOMBRE#",js_cliente[i].nombre);
                    generateHTML = generateHTML.replace("#APPS#",composeApps(js_cliente[i].cliente, js_cliente[i].contrato, js_cliente[i].apps));
                    capa += generateHTML;
         }

        $("#contenedorContratos").html(capa);
        disabledCheckedApp();

    }





    function addApp(_cliente, _contrato,_id){
        var app = null;
        var valueNode = null;
        var node = $("#app_" + _cliente + "_" + _contrato + "_" + _id);
        var radioName = "[name=license"+_cliente+"_"+_contrato+"_"+_id+"]"
        if(node.is(':checked')){
            $('input'+radioName).removeAttr("disabled");
        }else{
            $('input'+radioName).each(function(){
                $(this).prop('checked', false);
            });
            $('input'+radioName).attr('disabled', 'disabled')
        }
        for(var i = 0; i <  js_cliente.length; i++){

            if(js_cliente[i].cliente == _cliente || js_cliente[i].contrato == _contrato){

                var anotherNode = $("#app_"+js_cliente[i].cliente +"_"+  js_cliente[i].contrato+"_"+_id);

                valueNode = node.val();
                if(node.is(':checked')){

                    anotherNode.attr('disabled',true);
                    if(js_cliente[i].cliente == _cliente && js_cliente[i].contrato==_contrato){
                        node.attr('disabled',false);

                        app = {};
                        app.appId = valueNode;
                        app.licenses = [];
                        js_cliente[i].apps.push(app);
                        console.log(app)
                    }
                }else{
                    anotherNode.attr('disabled',false);
                    var arrApps = js_cliente[i].apps;
                    for(var x = 0; x < arrApps.length; x++){
                        if(arrApps[x].appId == valueNode){
                            arrApps.splice(x,1);
                            break;
                        }
                    }
                }
            }
        }
    }

    function addRoleApp(_cliente, _contrato, _idApp, _idRoleApp){
        for(var i = 0; i <  js_cliente.length; i++){
            if(js_cliente[i].cliente == _cliente && js_cliente[i].contrato == _contrato){
                for(var x = 0; x < js_cliente[i].apps.length; x++){
                    //console.log("arrApps[x].appId : " + js_cliente[i].apps[x].appId + " / _idApp: " + _idApp)
                    if(js_cliente[i].apps[x].appId == _idApp){
                        js_cliente[i].apps[x].licenses = [];
                        js_cliente[i].apps[x].licenses.push(_idRoleApp);
                        break;
                    }
                }
            }
        }
    }

    //si ya esta seleccionada en otro cliente+contrato tiene que estar deshabilitado en los nuevos.
    function isSelectedApp(_appId){
        //NO LO TENGO CLARO: CUANDO DESHABILITAR APPS, POR AHORA TODAS ENABLED:

        return false;

        var isApp = false;
        for(var i = 0; i <  js_cliente.length; i++){
            for(var x = 0; x < js_cliente[i].apps.length; x++){
                if(js_cliente[i].apps[x].appId == _appId){
                    isApp = true;
                    break;
                }
            }
            if(isApp) break;
        }
        return isApp;
    }

    function composeApps(_cliente, _contrato, _apps){
        var id_cont = 0;
        var isSelected = false;
        var html = "", htmlLicenses = "";
        var inputHTML = '<div class="col-#ANCHO#">'
                        + ' <label for="app_' + _cliente + '_' +  _contrato +  '_#ID#">'
                        + '<input type="checkbox" value="#VALUE#" #CHECKED# #DISABLED# onclick="addApp(' + "'" + _cliente + "','" +_contrato + "','#ID#'" +  ')" id="app_'+ _cliente + '_' +  _contrato + '_#ID#"/>&nbsp;#LABEL# </label>'
                        + '#LICENCIAS#'
                        + ' </div>';
        var licencias = '<label for="' + 'license' + _cliente + '_' +  _contrato +  '_#ID_LICENSE#" class="mr-3">'
                        + '<input class="clslicense" type="radio" name="' + 'license' + _cliente + '_' +  _contrato +  '_#IDAPP#" #CHECKED#'
                        + ' id="' + 'license' + _cliente + '_' +  _contrato +  '_#ID_LICENSE#' + '" onclick="addRoleApp(' + "'" + _cliente + "','" + _contrato  + "','#IDAPP#','#ID_LICENSE#'" + ')">'
                        + '</input>#NAME#</label>';
        var app = null, linc = null;
        var ancho = 4;
        for(var i = 0; i < arrApps.data.length; i++){
            app = arrApps.data[i];
            isSelected = false;
            if(_apps && _apps != undefined && _apps != null){
                for(var x = 0; x < _apps.length; x++){
                    if(_apps[x].appId == app.appId){
                        isSelected = true;
                        break;
                    }
                }
            }
            ancho = 4;
            html += inputHTML.replace("#LABEL#", app.name).replaceAll("#ID#",app.appId).replaceAll("#VALUE#", app.appId).replace("#CHECKED#", (isSelected)? "checked" : "" ).replace("#DISABLED#", (isSelectedApp(app.appId))? "disabled" : "" );
            if(app.licenses && app.licenses.length > 0){
                ancho = 12;
                htmlLicenses = "";
                isSelectedRole = false;
                for(var x  = 0; x < app.licenses.length; x++){
                    linc = app.licenses[x];

                    if(!isSelected){
                        htmlLicenses += licencias.replaceAll("#IDAPP#", app.appId).replaceAll("#ID_LICENSE#", linc.licenseId).replaceAll("#NAME#", linc.name).replace("#CHECKED#", "disabled");
                    }else{
                        isSelectedRole = isRolSelected(_cliente, _contrato, app.appId , linc);
                        htmlLicenses += licencias.replaceAll("#IDAPP#", app.appId).replaceAll("#ID_LICENSE#", linc.licenseId).replaceAll("#NAME#", linc.name).replace("#CHECKED#", (isSelectedRole)? "checked" : "" );
                    }
                }
                html = html.replace("#LICENCIAS#", '<div id="roles_' + _cliente + "_" +  _contrato + "_" +  app.appId + '" class="ml-5">' + htmlLicenses + "</div>");
            }else{
                html = html.replace("#LICENCIAS#","");
            }
            html = html.replace("#ANCHO#",ancho);
            id_cont++;
        }
        return html;
    }

    function isRolSelected(_cliente, _contrato, _appId, _license){
        var arrApps = null;
        var isSelected = false;

        for(let i = 0; i < js_cliente.length; i++){
            if(js_cliente[i].cliente == _cliente && js_cliente[i].contrato == _contrato){
                arrApps = js_cliente[i].apps;
                for(let x = 0; x < arrApps.length; x++){
                    if(arrApps[x].licenses.length > 0 &&  arrApps[x].licenses[0]  == _license.licenseId){
                        isSelected = true;
                        break;
                    }
                }
            }
        }
        return isSelected;
    }


    function getAllClients(js_cliente){
        var idCompany=$('#<portlet:namespace />companyEditId').val();
         $.ajax({
            type: 'POST',
            url: '<%= getAllClientsURL %>',
            data: { '<portlet:namespace/>companyEditId' : idCompany},
            success: function (data){
                for(var x=0;x<data.length; x++){
                    js_cliente.push(data[x]);
                }
                repaintClienteContrato();

            }
         });
    }


</script>
