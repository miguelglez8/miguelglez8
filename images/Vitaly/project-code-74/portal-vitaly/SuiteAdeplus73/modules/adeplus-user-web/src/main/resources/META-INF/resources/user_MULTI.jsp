<%@ page import="com.adeplus.liferay.portlet.commons.web.role.AdeplusRoleUtil" %>
<%@ page import="com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Application" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.CompApplication" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.UserCompany" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.*" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Role" %>
<%@ include file="init.jsp" %>

<%
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    long userEditId = ParamUtil.getLong(request, AdeplusUsersPortletKeys.USER_EDIT_ID, 0);
    long companyEditId = ParamUtil.getLong(request, AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT, 0);
    long userClientId= ParamUtil.getLong(request, AdeplusUsersPortletKeys.USER_CLIENT_ID_EDIT, 0);

    String errorLimit = ParamUtil.getString(request,"error-limit-apps-name","");
    User userEdit = null;
    UserCompany userCompanyFromUser = null;

    Calendar endDateCalendar = Calendar.getInstance();
    endDateCalendar.setTime(new Date());
    boolean disabledEndDate = true;

    int isAdminNumber = 0; // 0: ninguno, 1: admin , 2: user
    if(userEditId > 0){
        userEdit = UserLocalServiceUtil.getUser(userEditId);
        userCompanyFromUser = UserCompanyLocalServiceUtil.getUserCompany(userEditId, companyEditId);

        if(Validator.isNotNull(userEdit) && Validator.isNotNull(userCompanyFromUser) && Validator.isNotNull(userCompanyFromUser.getUserEndDate())){
            endDateCalendar.setTime(userCompanyFromUser.getUserEndDate());
            disabledEndDate = false;
        }
        isAdminNumber = 2;
        if(userCompanyFromUser.isAdmin()) isAdminNumber = 1;
    }

    List<Application> appList = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);


    boolean isCompanyAdminRole = false;
    if(Validator.isNotNull(userEdit)){
        isCompanyAdminRole = AdeplusRoleUtil.isCompanyAdminRole(themeDisplay.getCompanyId(), userEdit);
    }

    Calendar firstEnabledDate = Calendar.getInstance();

    firstEnabledDate.set(Calendar.HOUR_OF_DAY, 0);
    firstEnabledDate.set(Calendar.MINUTE, 0);
    firstEnabledDate.set(Calendar.SECOND, 0);
    firstEnabledDate.set(Calendar.MILLISECOND, 0);

    firstEnabledDate.add(Calendar.DAY_OF_MONTH, -30);
    
   
    
    
%>
<liferay-ui:error key="error-create-limits" message="company.view.save.error.limits" />
<liferay-ui:success key="save-user-success" message="user.view.save.user.success" />
<liferay-ui:error key="error-create-duplicated" message="user.edit.save.error.duplicated" />
<liferay-ui:error key="error-create-nif" message="user.view.save.error.nif" />
<liferay-ui:error key="error-create-email" message="user.view.save.error.email" />

<liferay-portlet:actionURL name="/user/saveMulti" var="saveUserURL" />
<aui:form  name="edit_form" action="<%=saveUserURL.toString()%>" method="post">

    <aui:input name="<%=AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT%>" value="<%=companyEditId%>" type="hidden" />
    <aui:input name="<%=AdeplusUsersPortletKeys.USER_CLIENT_ID_EDIT%>" value="<%=userClientId%>" type="hidden" />
    <aui:input name="<%=AdeplusUsersPortletKeys.USER_EDIT_ID%>" value="<%=userEditId%>" type="hidden" />
    <aui:input name="jsonClientContractApps"  type="hidden" ignoreRequestValue="true" />

    <div class="content">
        <div class="titulo-seccion">
            <h2><liferay-ui:message key="user.edit.title"/></h2>
        </div>
        <div class="formulario">
            <div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
                <div class="form-content  mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">
                    <div class="row">
                        <div class="form-group col-md-6 required">
                            <aui:input name="<%=AdeplusUsersPortletKeys.USER_NIF%>" type="text" label="user.edit.nif" value="<%=Validator.isNotNull(userCompanyFromUser)?userCompanyFromUser.getNif():""%>">
                                <aui:validator name="required" errorMessage="user.edit.validator.required" />
                                <aui:validator name="alphanum" errorMessage="company.error.alfanum"></aui:validator>
                            </aui:input>
                        </div>
                        <div class="form-group col-md-6 required">
                            <aui:input name="<%=AdeplusUsersPortletKeys.USER_NAME%>" type="text" label="user.edit.name" value="<%=Validator.isNotNull(userCompanyFromUser)?userCompanyFromUser.getName():""%>">
                                <aui:validator name="required" errorMessage="user.edit.validator.required" />
                            </aui:input>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6 required">
                            <aui:input name="<%=AdeplusUsersPortletKeys.USER_LAST_NAME%>" type="text" label="user.edit.last.name" value="<%=Validator.isNotNull(userCompanyFromUser)?userCompanyFromUser.getLastName():""%>">
                                <aui:validator name="required" errorMessage="user.edit.validator.required" />
                            </aui:input>
                        </div>
                        <div class="form-group col-md-6 required">
                            <aui:input name="<%=AdeplusUsersPortletKeys.USER_EMAIL%>" type="text" label="user.edit.email" value="<%=Validator.isNotNull(userCompanyFromUser)?userCompanyFromUser.getEmail():""%>">
                                <aui:validator name="required" errorMessage="user.edit.validator.required" />
                                <aui:validator name="email" errorMessage="user.edit.email.error.format" />
                            </aui:input>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <aui:input name="<%=AdeplusUsersPortletKeys.USER_PHONE%>" type="text" label="user.edit.phone" value="<%=Validator.isNotNull(userCompanyFromUser)?userCompanyFromUser.getPhone():""%>" />
                        </div>
                        <div class="form-group col-sm-6 required">
                            <fieldset class="input-group">
                                <legend for="admin" class="control-label"><liferay-ui:message key="user.edit.admin"/></legend>
                                <div class="custom-control custom-radio d-inline-block mr-3 mt-2">
                                    <div class="custom-control custom-radio d-inline-block mr-3 mt-2">
                                         <input class="custom-control-input" type="radio" id="administrador1" name="<portlet:namespace/>admin"
                                                                                                           value="admin" <%=(isAdminNumber == 1)? "checked" : ""%>>
                                                                                    <label class="custom-control-label" for="administrador1"><liferay-ui:message key="user.view.yes"/></label>
                                    </div>
                                    <div class="custom-control custom-radio d-inline-block mt-2">
                                        <input class="custom-control-input" type="radio" id="administrador2" name="<portlet:namespace/>admin"
                                                                                                               value="noAdmin" <%=(isAdminNumber == 2)? "checked":""%>>
                                                                                    <label class="custom-control-label" for="administrador2"><liferay-ui:message key="user.view.no"/></label>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="dateEnd" class="control-label"><liferay-ui:message key="user.edit.date.end"/></label>
                            <c:if test="<%=disabledEndDate%>">
                                <liferay-ui:input-date name="dateEnd" nullable="true" disabled="<%=disabledEndDate%>"
                                                       firstEnabledDate="<%=firstEnabledDate.getTime()%>"  lastEnabledDate="<%=new Date()%>"/>
                            </c:if>
                            <c:if test="<%=!disabledEndDate%>">
                                <liferay-ui:input-date name="dateEnd" nullable="true"
                                                       firstEnabledDate="<%=firstEnabledDate.getTime()%>"  lastEnabledDate="<%=new Date()%>"
                                                       yearValue="<%=endDateCalendar.get(Calendar.YEAR)%>"
                                                       monthValue="<%=endDateCalendar.get(Calendar.MONTH)%>"
                                                       dayValue="<%=endDateCalendar.get(Calendar.DAY_OF_MONTH)%>" />
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">
                            <aui:select name="<%=AdeplusUsersPortletKeys.USER_LOCALE%>" label="user.edit.language">
                                <aui:option value="es_ES" selected="<%=Validator.isNotNull(userEdit)?userEdit.getLocale().toString().equals("es_ES"):true %>"><liferay-ui:message key="user.edit.language.spanish"/></aui:option>
                                <aui:option value="ca_ES" selected="<%=Validator.isNotNull(userEdit)?userEdit.getLocale().toString().equals("ca_ES"):false %>"><liferay-ui:message key="user.edit.language.cat"/></aui:option>
                            </aui:select>
                        </div>
                    </div>
                    <div class="capaSelectorCliente">
                        <div id="contenedorContratos" class="">

                        </div>
                        <div class="row">
                            <div class="col-12">
                                <button id="btnVerJSON" type="button" class="btn btn-primary" onclick="viewJSON()">VER JSON - DESA</button>
                                <textarea id="inpJSON" style="width:100%;height:200px;"></textarea>
                            </div>
                        </div>
                    </div>
                    <!-- mensajes -->
                    <div id="capaMensaje" class="alert alert-warning  hide">
                        <div class="col-12 col-sm-12">
                            <span id="spantxtMensaje"> </span>
                        </div>
                    </div>
                    <!-- end mensajes -->
                </div>
            </div>
        </div>

    <div class="button-holder d-flex justify-content-center my-4">
        <aui:button-row>
            <portlet:actionURL name="search" var="cancelURL">
                <portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
            </portlet:actionURL>
            <aui:button onClick="<%= cancelURL.toString() %>" value="user.edit.cancel" cssClass="btn btn-outline-primary mr-4" primary="true"></aui:button>
            
            <button class="btn btn-primary" onClick="validateOwn()" type="button" >
                <span class="lfr-btn-label">
                    <liferay-ui:message key="user.edit.btnGuardar"/>
                </span>
            </button>

            <aui:button type="submit" cssClass="btn btn-primary btnGuardar hide"></aui:button>

            <c:if test="<%=Validator.isNotNull(userEdit)%>">
                <liferay-portlet:actionURL name="/user/remindpassword" var="passRemindURL" >
                    <portlet:param name="userId" value="<%=String.valueOf(userEdit.getUserId())%>" />
                </liferay-portlet:actionURL>
                <aui:button onClick="<%= passRemindURL.toString() %>" value="user.remind.password"></aui:button>
            </c:if>
        </aui:button-row>
    </div>

</aui:form>
<!-- START : PLANTILLA DE HTML-->
        <span class="hide" id="_div_Plantilla">
        <div class="  border border-secondary   mt-5 ml-0 mb-5" >
            <div class="row mr-0 ml-1 mt-3">
                <div class="col-10">
                    <span id="spnCliente" class="label label-primary">#CLIENTE#</span> - <span id="spnContrato" class="label label-warning">#CONTRATO#</span> - <span id="spnNombre" class="label label-success">#NOMBRE#</span>
                </div>
                <div class="col-2 text-right"><button type="button" class="btn btn-outline-primary " onclick="quitClienteContrato('#CLIENTE#','#CONTRATO#')">X</button></div>
            </div>
            <div class="row mr-10 ml-5 mt-3 mb-3">
                    #APPS#
            </div>
        </div>
        </span>
<!-- END : PLANTILLA DE HTML-->
<liferay-portlet:resourceURL  copyCurrentRenderParameters="false" id="getAllApplications" var="getAllApplicationsURL" />
<liferay-portlet:resourceURL  copyCurrentRenderParameters="false" id="getAllClients" var="getAllClientsURL" />
<liferay-portlet:resourceURL  copyCurrentRenderParameters="false" id="getAllApplicationUser" var="getAllApplicationUserURL" />

<script>
    var js_cliente = []; //cliente + contrato + apps del usuario
    var js_cliente_company = []; // cliente + contrato + apps de la empresa
    var arrApps = [];
     $(document).ready(function(){
            js_cliente = [];
            //Obtener todas las apps y sus limites
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if (this.readyState == 4 && this.status == 200){
                    arrApps = JSON.parse(this.responseText);
                    getAllClients();
                }
            };
            xhr.open("GET", "<%=getAllApplicationsURL%>", true);
            xhr.send(null);

            $('#<portlet:namespace />edit_form').submit(function(e){
                e.preventDefault(e);
                $("#<portlet:namespace/>jsonClientContractApps").val(JSON.stringify(js_cliente));

            });

            arrAppSelectedPrev = $(".check-app[type='checkbox']:checked"); // antes de cambios
            if("<%=errorLimit%>" != ""){
                var txtError = "";
                if("<%=errorLimit%>".indexOf("USER:") != -1){
                    txtError = '<liferay-ui:message key="validation.appLimits.user"/>: ' + " <strong><%=errorLimit%></strong>".replace("USER:","");
                }else{
                    txtError = '<liferay-ui:message key="validation.appLimits.admin"/>: ' + " <strong><%=errorLimit%></strong>".replace("ADMIN:","");;
                }
                $("#capaMensaje").removeClass("hide");
                $("#spantxtMensaje").html("<span class='lexicon-icon lexicon-icon-exclamation-full'>&nbsp;</span>" + txtError);
                console.log("errorLimit: <%=errorLimit%>" );
            }

            if($(".clsNoPermitidoPorLimite").length > 0){
                $("#capaMensaje").removeClass("hide");
                $("#spantxtMensaje").html('*<liferay-ui:message key="user.edit.limitOverAdminAndUsers"/>');
            }
        });


    function validateOwn(){
        $("#capaMensaje").addClass("hide");
        $("#spantxtMensaje").html("");
        var totalWithRolesToSend = 0;
        var rolesToSend = 0;
        if($("#administrador1").prop("checked") == false && $("#administrador2").prop("checked") == false){
                $("#capaMensaje").removeClass("hide");
                $("#spantxtMensaje").html('* <liferay-ui:message key="user.edit.email.error.administrador"/>');
            return false;
        }

        if(js_cliente.length < 1){
                $("#capaMensaje").removeClass("hide");
                $("#spantxtMensaje").html('* Debe indicar al menos una aplicaci&oacute;n para un cliente contrato');
                return false;
        }

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
            $("#spantxtMensaje").html('* Debe indicar el rol de cada usuario en su cliente contrato');
            return false;
        }


        $(".btnGuardar")[0].click(); //llamar a guardar
    }

    deleteDisabledClass();
    function deleteDisabledClass() {
        var element = document.getElementById("<portlet:namespace/>dateEnd").classList.remove("disabled");
        //element.classList.remove("disabled");
    }

    function setEnabledApp(_chk, id){
        var arrNodes = document.getElementsByName("_<portlet:namespace/>app_" + id);
        console.log(id);
        console.log("<portlet:namespace/>app_" + id);
        console.log("arrNodes " + arrNodes.length);
        if(arrNodes.length > 0){
            if(_chk.checked){
                for(var i = 0; i < arrNodes.length; i++){
                    arrNodes[i].disabled = false;
                }
                arrNodes[arrNodes.length - 1].checked = true;
            }else{
                for(var i = 0; i < arrNodes.length; i++){
                    arrNodes[i].checked = false;
                    arrNodes[i].disabled = true;
                }
            }
        }
    }

    async function validateEmailRepeated(val){

        var repeated = false;

        var DOMAIN =  "<%=themeDisplay.getURLPortal()%>";
        var URL_COMMENTS =  "/api/jsonws/user/get-user-by-email-address/company-id/#COMPANY_ID#/email-address/#USER_EMAIL#?p_auth=#P_AUTH#";
        var P_AUTH = Liferay.authToken;
        var COMPANY_ID = "<%=themeDisplay.getCompanyId()%>";

        try{

            var urlEmails = DOMAIN  + URL_COMMENTS.replace("#COMPANY_ID#",COMPANY_ID).replace("#USER_EMAIL#",val).replace("#P_AUTH#",P_AUTH);

            let call =
                await  fetch(urlEmails)
                    .then(response => response.json())
                    .then( (data) => {
                        console.log(data);
                        console.log(data.emailAddress);
                        if(data.emailAddress && data.emailAddress == val){
                            repeated = true;
                        }else{
                            repeated = false;
                        }
                        //console.log("procesada la llamada ..");
                    });

            var urlEmails = DOMAIN  + URL_COMMENTS.replace("#COMPANY_ID#",COMPANY_ID).replace("#USER_EMAIL#",val).replace("#P_AUTH#",P_AUTH);
            //console.log("procesada la llamada 2 ..")
            //xhr.open("GET", urlEmails, true);
            //xhr.send(null);
        }catch(e){
            console.error("sendObs(): " + e);
        }
        //console.log("repeated: " + repeated);

        return repeated;
    }


    function repaintClienteContratoCompany(){
        var html = $("#contenedorContratos").html("");
        var plantilla = $("#_div_Plantilla").html();
        var generateHTML = "", capa = "";

        for(var i = js_cliente_company.length-1; i >= 0 ; i--){ //lifo
                    generateHTML = plantilla.replaceAll("#CLIENTE#",js_cliente_company[i].cliente);
                    generateHTML = generateHTML.replaceAll("#CONTRATO#",js_cliente_company[i].contrato);
                    generateHTML = generateHTML.replaceAll("#NOMBRE#",js_cliente_company[i].nombre);
                    generateHTML = generateHTML.replace("#APPS#",composeApps(js_cliente_company[i].cliente, js_cliente_company[i].contrato, js_cliente_company[i].apps));
                    capa += generateHTML;
         }

        $("#contenedorContratos").html(capa);
    }

    function getAllClients(){
        var idCompany="<%=companyEditId%>";
        var jsonResponse = null;
        var param = "<portlet:namespace/>userCompId=" + idCompany;
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(){
            if (this.readyState == 4 && this.status == 200){
                jsonResponse = JSON.parse(this.responseText);

                console.log(jsonResponse)

                for(var x=0;x<jsonResponse.length; x++){
                   js_cliente_company.push(jsonResponse[x]);
                }
                getAllAppsUser();
                //repaintClienteContratoCompany();

            }
        };
        xhr.open("GET", "<%= getAllClientsURL %>" + "&" + param, true);
        xhr.send(null);
    }

    function getAllAppsUser(){
        var idCompany="<%=companyEditId%>";
        var idUser="<%=userEditId%>"

        var params = "<portlet:namespace/>userCompId=" + idCompany
                        + "&<portlet:namespace/>userEditId=" + idUser;
        var jsonResponse = null;;
        var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if (this.readyState == 4 && this.status == 200){

                    js_cliente = JSON.parse(this.responseText);
                   /*jsonResponse = JSON.parse(this.responseText);
                    for(x=0;x<jsonResponse.length;x++){
                        var clienteObten=jsonResponse[x].cliente;
                        var contratoObten=jsonResponse[x].contrato
                        var appsObten=jsonResponse[x].apps
                        for(i=0;i<appsObten.length;i++){
                            var appObten=appsObten[i].appId
                            var ident ="#app_"+clienteObten+"_"+contratoObten+"_"+appObten
                            $(ident).prop('checked',true)
                            addApp(clienteObten,contratoObten,appObten);
                        }

                    }*/
                repaintClienteContratoCompany()

                }
            };
            xhr.open("GET", "<%= getAllApplicationUserURL %>"  + "&" + params, true);
            xhr.send(null);

    }

    //el contrato + cliente tiene esa app ?
    function isSelectedApp(_cliente,_contrato,_appId){
        var isApp = true;
        //esta entre las permitidas de la empresa
        for(var i = 0; i <  js_cliente_company.length; i++){
            if(js_cliente_company[i].cliente == _cliente && js_cliente_company[i].contrato == _contrato){
                for(var x = 0; x < js_cliente_company[i].apps.length; x++){
                    if(js_cliente_company[i].apps[x].appId == _appId){
                        isApp = false;
                        break;
                    }
                }
            }

            if(!isApp) break;
        }
        return isApp;
    }

     function isRolSelected(_cliente, _contrato, _appId, _license){
                 var arrApps = null;
                 var isSelected = false;

                 for(let i = 0; i < js_cliente.length; i++){

                     if(js_cliente[i].cliente == _cliente && js_cliente[i].contrato == _contrato){
                         arrApps = js_cliente[i].apps;
                         for(let x = 0; x < arrApps.length; x++){

                             if(arrApps[x].appId == _appId && arrApps[x].licenses.length > 0 &&  arrApps[x].licenses[0]  == _license){
                                console.log("checked")
                                 isSelected = true;
                                 break;
                             }
                         }
                     }
                 }
                 return isSelected;
              }

    function composeApps(_cliente, _contrato, _apps){
        var id_cont = 0;
        var isSelected = false;
        var html = "", htmlLicenses = "";
        var inputHTML = '<div class="col-#ANCHO#">'
                        + ' <label for="app_' + _cliente + '_' +  _contrato +  '_#ID#">'
                        + '<input type="checkbox" value="#VALUE#" #CHECKED# #DISABLED# onclick="addApp(' + "'" + _cliente + "','" +_contrato + "','#ID#'" +  ')" '
                        + 'onchange="ajusta_controles_licencias(' + "'" + 'app_' + _cliente + '_' +  _contrato + '_#ID#'  + "'" + ')" id="app_'+ _cliente + '_' +  _contrato + '_#ID#"/>&nbsp;#LABEL# </label>'
                        + '#LICENCIAS#'
                        + ' </div>';
        var roles = '<label for="' + 'license' + _cliente + '_' +  _contrato +  '_#ID_ROLE#" class="mr-3">'
                        + '<input class="clsRole" type="radio" name="' + 'license' + _cliente + '_' +  _contrato +  '_#IDAPP#" #CHECKED# #DISABLED#'
                        + ' id="' + 'license' + _cliente + '_' +  _contrato +  '_#ID_ROLE#' + '" onclick="addRoleApp(' + "'" + _cliente + "','" + _contrato  + "','#IDAPP#','#ID_ROLE#'" + ')">'
                        + '</input>#NAME#</label>';
        var app = null, rol = null;
        var ancho = 4;
        for(var i = 0; i < arrApps.data.length; i++){
            app = arrApps.data[i];
            isSelected = false;
            for(let  c = 0; c < js_cliente.length; c++){
                if(_cliente == js_cliente[c].cliente && _contrato == js_cliente[c].contrato){
                    for(let y = 0; y < js_cliente[c].apps.length; y++){
                        if(app.appId == js_cliente[c].apps[y].appId){
                            isSelected = true;
                            break;
                        }
                    }

                }
            }
            console.log("Is selected: "+isSelected);
            ancho = 4;
            html += inputHTML.replace("#LABEL#", app.name).replaceAll("#ID#",app.appId).replaceAll("#VALUE#", app.appId).replace("#CHECKED#", (isSelected)? "checked" : "" ).replace("#DISABLED#", (isSelectedApp(_cliente, _contrato, app.appId))? "disabled" : "" );
            if(app.roles && app.roles.length > 0){
                ancho = 12;
                htmlLicenses = "";
                isSelectedRol = false;
                for(var x  = 0; x < app.roles.length; x++){
                    rol = app.roles[x];
                    isSelectedRol = isRolSelected(_cliente, _contrato, app.appId , rol.roleId);
                    htmlLicenses += roles.replaceAll("#IDAPP#", app.appId).replaceAll("#ID_ROLE#", rol.roleId).replaceAll("#NAME#", rol.name).replace("#CHECKED#", (isSelectedRol)? "checked" : "" ).replace("#DISABLED#", (!isSelected) ? "disabled" : "");
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

    function ajusta_controles_licencias(id){
            try{
                var contrato = document.querySelector("#"+id);
                var licencias = contrato.parentNode.parentNode.querySelectorAll("input[type=radio]");
                var nameLicense=id.replace('app_','license');

                for (var i=0; i<licencias.length; i++){
                    if (contrato.checked) {
                        licencias[i].removeAttribute("disabled");
                    } else {
                        licencias[i].setAttribute("disabled", "disabled");
                    }
                }
            } catch (e) { console.log("trycatch ajusta_controles_licencias", e); }
        }

    //añadir o quitar selección
    function addApp(_cliente, _contrato,_id){
        var node = $("#app_" + _cliente + "_" + _contrato + "_" + _id);
        var isIn = false;
        if(node.is(':checked')){
            for( let i = 0; i < js_cliente.length;  i++){
                if(js_cliente[i].cliente == _cliente && js_cliente[i].contrato == _contrato){
                    _addAppUser( i, _id);
                    isIn = true;
                    break;
                }
            }
            if(!isIn){ // no existia
                var clientContato = getClientContratoCompany(_cliente, _contrato, _id);
                js_cliente.push(clientContato);
            }
        }else{
            for( let i = 0; i < js_cliente.length;  i++){
                if(js_cliente[i].cliente == _cliente && js_cliente[i].contrato == _contrato){
                    _removeAppUser( i, _id);
                }
            }

        }

    }

    function _addAppUser(_index ,_id){
        var app = {};
        app.appId = _id;
        app.licenses = [];
        js_cliente[_index].apps.push(app);
    }

    function _removeAppUser(_index, _id){
        var arr = js_cliente[_index].apps;
        var pos = -1;

        console.log(arr)

        for(let i = 0; i < arr.length; i++){
            if(arr[i].appId == _id){
                pos = i;
                break;
            }
        }
        console.log("pos: " + pos)
        arr.splice(pos,1);


        if(arr.length == 0){
            //quitamos el cliente + contrato si esta vacio su apps
            js_cliente.splice(_index,1);
        }else{
            js_cliente[_index].apps = arr;
        }




    }

    //obtener del js_cliente_company el cliente + contrato
    function getClientContratoCompany(_cliente, _contrato, _id){
        var clienteContrato = null;
        for(var i = 0; i < js_cliente_company.length; i++){
            if(js_cliente_company[i].cliente == _cliente && js_cliente_company[i].contrato == _contrato){
                clienteContrato = {};
                clienteContrato.cliente =  js_cliente_company[i].cliente;
                clienteContrato.contrato =  js_cliente_company[i].contrato;
                clienteContrato.nombre =  js_cliente_company[i].nombre;
                clienteContrato.apps = [];
                var app = {};
                app.appId = _id;
                app.licenses = [];
                clienteContrato.apps.push(app);
            }
        }
        console.log("clienteContrato: " + clienteContrato)
        return clienteContrato;
    }





    function addRoleApp(_cliente, _contrato, _idApp, _idRoleApp){
        for(var i = 0; i <  js_cliente.length; i++){
            if(js_cliente[i].cliente == _cliente && js_cliente[i].contrato == _contrato){
                for(var x = 0; x < js_cliente[i].apps.length; x++){
                    if(js_cliente[i].apps[x].appId == _idApp){
                        js_cliente[i].apps[x].licenses = [];
                        js_cliente[i].apps[x].licenses.push(_idRoleApp);
                        break;
                    }
                }
            }
        }
    }
     function viewJSON(){
        console.log(JSON.stringify(js_cliente))
        $("#inpJSON").val(JSON.stringify(js_cliente));
        $("#<portlet:namespace/>jsonClientContractApps").val(JSON.stringify(js_cliente));
    }



</script>