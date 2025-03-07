<%@ page import="com.adeplus.liferay.portlet.commons.web.role.AdeplusRoleUtil" %>
<%@ page import="com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Application" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.CompApplication" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.UserCompany" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.*" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Role" %>
<%@ include file="init.jsp" %>

<%
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    long userEditId = ParamUtil.getLong(request, AdeplusUsersPortletKeys.USER_EDIT_ID, 0);
    long companyEditId = ParamUtil.getLong(request, AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT, 0);


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
    List<CompApplication> applicationsFromCompany = CompApplicationLocalServiceUtil.getApplicationsFromCompany(companyEditId);

    List<Long> appIds = new ArrayList<>();
    for(CompApplication ca:applicationsFromCompany){
        appIds.add(ca.getApplicationId());
    }

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

<liferay-portlet:actionURL name="/user/save" var="saveUserURL" />
<aui:form  name="edit_form" action="<%=saveUserURL.toString()%>" method="post">

    <aui:input name="<%=AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT%>" value="<%=companyEditId%>" type="hidden" />
    <aui:input name="<%=AdeplusUsersPortletKeys.USER_EDIT_ID%>" value="<%=userEditId%>" type="hidden" />

    <div class="content">
        <div class="titulo-seccion">
            <h2><liferay-ui:message key="user.edit.title"/></h2>
        </div>
        <div class="formulario">
            <div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
                <div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">
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
                                <aui:option value="en_US" selected="<%=Validator.isNotNull(userEdit)?userEdit.getLocale().toString().equals("en_US"):false %>"><liferay-ui:message key="user.edit.language.eng"/></aui:option>

                            </aui:select>
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

                <div class="form-content form-aplicaciones">
                    <div class="row">
                        <div class="input-group mb-3">
                            <legend class="fieldset-title-section"><liferay-ui:message key="user.edit.applications.access"/></legend>
                            <c:forEach items="<%=appList%>" var="app" varStatus="item">
                                <%
                                    Application app = (Application) pageContext.getAttribute("app");
                                    boolean checked = false;
                                    if(Validator.isNotNull(userEdit)) {
                                        checked = UserApplicationLocalServiceUtil.hasUserApplication(userEdit.getUserId(), userCompanyFromUser.getCompId(), app.getApplicationId());

                                    }
                                    List<Role> roleByApplicationId = RoleLocalServiceUtil.getRoleByApplicationId(app.getApplicationId());

                                    /*boolean isAppDisabled = false;
                                    String classWarning = "";
                                    if(userEditId == 0 && appIds.contains(app.getApplicationId()) ){
                                        isAppDisabled = !AdeplusUserUtil.isEnabledSelectApp(companyEditId,app);
                                        if(isAppDisabled){
                                            classWarning = "alert alert-warning clsNoPermitidoPorLimite";
                                        }
                                    }else{
                                        isAppDisabled = !appIds.contains(app.getApplicationId());
                                    }*/
                                    boolean isAppDisabled = false;
                                    String classWarning = "";
                                    if(userEditId == 0 && appIds.contains(app.getApplicationId()) ){
                                        isAppDisabled = !AdeplusUserUtil.isEnabledSelectApp(companyEditId,app, isAdminNumber);
                                        if(isAppDisabled){
                                                classWarning = "alert alert-warning clsNoPermitidoPorLimite";
                                        }

                                    }else if(userEditId != 0 && appIds.contains(app.getApplicationId())  && !AdeplusUserUtil.isEnabledSelectApp(companyEditId, app, isAdminNumber)){
                                        if(checked){
                                            isAppDisabled = false;
                                        }else{
                                            isAppDisabled = true;
                                            classWarning = "alert alert-warning clsNoPermitidoPorLimite";
                                        }

                                    }else{
                                        isAppDisabled = !appIds.contains(app.getApplicationId());
                                    }
                                %>
                                <div class="custom-control custom-checkbox mb-3 <%=classWarning%>">
                                    <aui:input name="${app.applicationId}" type="checkbox" label="${app.name}" checked="<%=checked%>"
                                    disabled="<%=isAppDisabled%>" onClick="setEnabledApp(this, ${app.applicationId})" app-id="${app.applicationId}" cssClass="check-app" >
                                    </aui:input>

                                    <div class="application_role ml-5">
                                        <c:forEach items="<%=roleByApplicationId%>" var="role">
                                            <%
                                                Role role = (Role) pageContext.getAttribute("role");
                                                boolean roleChecked = Validator.isNotNull(UserRoleLocalServiceUtil.getUserRole(userEditId, companyEditId, role.getRoleId()));
                                                com.liferay.portal.kernel.model.Role rolLife=com.liferay.portal.kernel.service.RoleLocalServiceUtil.fetchRole(PortalUtil.getDefaultCompanyId(),role.getAlias());
                                            %>
                                            <aui:input name="app_${app.applicationId}" id="role_${role.roleId}" type="radio" label="${role.name}" value="${role.roleId}" checked="<%=roleChecked%>" disabled="<%=!checked%>" cssClass="check-app" required="<%=checked%>"></aui:input>

                                            <c:if test="<%=Validator.isNotNull(rolLife)%>">
                                               <liferay-ui:icon-help message="<%=rolLife.getDescription(PortalUtil.getLocale(request))%>" />
                                            </c:if>
                                        </c:forEach>
                                    </div>

                                </div>

                            </c:forEach>
                        </div>
                    </div>
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



<script>
     $(document).ready(function(){
            arrAppSelectedPrev = $(".check-app[type='checkbox']:checked"); // antes de cambios
            getcheckedDefault();
            var txtErrorAdminAndUser="<liferay-ui:message key="user.edit.limitOverAdminAndUsers"/>"
            if("<%=errorLimit%>" != ""){
                var txtError = "";
                var txtErrorUser="<liferay-ui:message key="validation.appLimits.user"/>"
                var txtErrorAdmin="<liferay-ui:message key="validation.appLimits.admin"/>"
                if("<%=errorLimit%>".indexOf("USER:") != -1){

                    txtError = txtErrorUser + " <strong><%=errorLimit%></strong>".replace("USER:","");
                }else{
                    txtError = txtErrorAdmin + " <strong><%=errorLimit%></strong>".replace("ADMIN:","");
                }
                $("#capaMensaje").removeClass("hide");
                $("#spantxtMensaje").html("<span class='lexicon-icon lexicon-icon-exclamation-full'>&nbsp;</span>" + txtError);
                console.log("errorLimit: <%=errorLimit%>" );
            }

            if($(".clsNoPermitidoPorLimite").length > 0){
                $("#capaMensaje").removeClass("hide");
                $("#spantxtMensaje").html('*'+txtErrorAdminAndUser);
            }

            moveHelpIcons();

        });

    function getcheckedDefault(){
        var longitud =$(".check-app[type='checkbox']:checked").length;
        var appChecked=$(".check-app[type='checkbox']:checked");
        for(var x=0;x<longitud;x++){
            setEnabledApp(appChecked[x],appChecked[x].getAttribute('app-id'));
        }

    }
    function validateOwn(){
        $("#capaMensaje").addClass("hide");
        $("#spantxtMensaje").html("");
        var txtErrorMail="<liferay-ui:message key="user.edit.email.error.administrador"/>";
        if($("#administrador1").prop("checked") == false && $("#administrador2").prop("checked") == false){
                $("#capaMensaje").removeClass("hide");
                $("#spantxtMensaje").html('* '+txtErrorMail);
            return false;
        }
        if($(".check-app[type='checkbox']:checked").length == 0){
            $("#capaMensaje").removeClass("hide");
            var warningApp="<liferay-ui:message key="user.edit.validator.checkbox.required"/>"

            $("#spantxtMensaje").html('* '+warningApp);
            return false;
        }
        if(!getCheckedRoleByApp()){
            $("#capaMensaje").removeClass("hide");
            var warningRole="<liferay-ui:message key="user.edit.validator.radio.required"/>"

            $("#spantxtMensaje").html('* '+warningRole);
            return false;
        }
        $(".btnGuardar")[0].click(); //llamar a guardar
    }

    deleteDisabledClass();
    function deleteDisabledClass() {
        var element = document.getElementById("_com_adeplus_liferay_portlet_user_web_AdeplusUsersPortlet_dateEnd").classList.remove("disabled");
        //element.classList.remove("disabled");
    }
    var arrSelectedApps = [];
    function getCheckedRoleByApp(){
        var arrNodes = null;
        var id = null;
        var isRadioSelected = false;
        var isOk = true;
        for(var i in arrSelectedApps){
            id = arrSelectedApps[i].substring(arrSelectedApps[i].lastIndexOf("_") + 1);
            console.log("arrSelectedApps[i]: " + arrSelectedApps[i]);
            console.log("id: " + id);

            arrNodes = document.getElementsByName("_com_adeplus_liferay_portlet_user_web_AdeplusUsersPortlet_app_" + id);
            isRadioSelected = false;
            for(var i = 0; i < arrNodes.length; i++){
                console.log("arrNodes[i].value: " + arrNodes[i].value);
                if(arrNodes[i].checked){
                    isRadioSelected = true;
                    break;
                }
            }
            if(!isRadioSelected){
                isOk = false;
                break;
            }
        }

        return isOk;

    }


    function setEnabledApp(_chk, id){
        var arrNodes = document.getElementsByName("_com_adeplus_liferay_portlet_user_web_AdeplusUsersPortlet_app_" + id);
        console.log(id);
        console.log("_com_adeplus_liferay_portlet_user_web_AdeplusUsersPortlet_app_" + id);
        console.log("arrNodes " + arrNodes.length);
        if(arrNodes.length > 0){
            if(_chk.checked){
                arrSelectedApps.push(_chk.name);
                for(var i = 0; i < arrNodes.length; i++){
                    arrNodes[i].disabled = false;
                }
            }else{
                arrSelectedApps.splice(arrSelectedApps.indexOf(_chk.name),1);
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

    function moveHelpIcons(){
            var allRadios=document.getElementsByClassName("radio");
            for(i=0;i<allRadios.length;i++){
                var y=allRadios[i].nextElementSibling
                if(y!=null){
                        allRadios[i].appendChild(y)
                }
            }
            $('.taglib-icon-help').last().css( "display", "none");
        }

</script>