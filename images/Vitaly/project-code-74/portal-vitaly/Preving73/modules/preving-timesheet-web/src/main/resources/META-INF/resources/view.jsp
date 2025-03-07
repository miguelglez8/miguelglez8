<%@ page import="com.preving.liferay.portlet.calendar.manager.model.Activity" %>

<%@ page import="com.preving.liferay.portlet.calendar.manager.service.UserDataCalendarsLocalServiceUtil" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.service.UserNameCalendarsLocalServiceUtil" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.UserDataCalendars" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.UserNameCalendars" %>

<%@ page import="java.util.List" %>
<%@ page import="com.preving.liferay.portlet.timesheet.web.constants.TimesheetPortletKeys" %>
<%@ include file="/init.jsp" %>
<%
    List<Activity> activityList = (List<Activity>) request.getAttribute(TimesheetPortletKeys.TIMESHEET_ACTIVITY_LIST);
    CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCompanyIdAndGroupId(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
    List<UserNameCalendars> lstUserNameCalendars = UserNameCalendarsLocalServiceUtil.getUserNamesCustomCalendars(companyConf.getCompanyConfId(),themeDisplay.getScopeGroupId(),themeDisplay.getUserId());

%>
<div class="container-fluid" >
    <div class="row">
        <div class="col-sm-5">
            <div class="row">
                <div class="col">
                    <div id="calendarioClicker" style="width:99%;min-width:200px;"></div>
                </div>
            </div>
        </div>
        <div class="col-sm-7">
            <div class="row timesheet-row">
                <div class="col-sm"><h2><liferay-ui:message key="timesheet.view.register" /> <span id="spFecha">--</span></h2></div>
            </div>
            <div class="row timesheet-row">
                <div class="col-sm-3" style="padding:0;"><span id="spHorasDia"></span></div>
                <div class="col-sm-3" style="padding:0;"><span id="spHorasSemana"></span></div>
            </div>
            <!-- calendarios personalizados -->
            <c:if test="<%=companyConf.isIsOwnCalendars() && lstUserNameCalendars.size() != 0 %>">
            <div class="row timesheet-row">
                <div class="col-sm-2"><liferay-ui:message key="timesheet.view.register.calendarioPersonalizado" />: </div>
                <div class="col-sm-10" style="padding:0;">
                    <select id="cmbUserCalendars" class="w-50">
                        <option value="">&nbsp;</option>
                        <%for(UserNameCalendars userNameCalendar:lstUserNameCalendars){%>
                                <option value="<%=userNameCalendar.getUserNameCalendarId()%>">
                                    <%=userNameCalendar.getName()%>
                                </option>

                        <%}%>
                    </select>
                </div>
            </div>
            </c:if>
            <!-- end calendarios personalizados -->
            <c:if test="<%=companyConf.getMaxHoursDay()%>"></c:if>
                <div class="row timesheet-row">
                    <div class="col-sm-2"><liferay-ui:message key="timesheet.view.activity" />: </div>
                    <div class="col-sm-10" style="padding:0;">
                        <select id="cmbActividad" onchange="getTypeActivity(this)">
                            <%for(Activity activity:activityList){%>
                                <c:if test="<%=activity.isActive()%>">
                                    <option value="<%=activity.getActivityId()%>" data-tipo="<%=activity.getType()%>">
                                        <%=activity.getName(user.getLocale())%>
                                    </option>
                                </c:if>
                            <%}%>
                        </select>
                    </div>
                </div>

            <div class="row timesheet-row">
                <div class="col-sm-2"><liferay-ui:message key="timesheet.view.register.start" />: </div>
                <div class="col-sm-10" style="padding:0; text-align:left;">
                    <input type="time" id="timeStart" name="timeStart" ></input>
                </div>
            </div>
            <div class="row timesheet-row">
                <div class="col-sm-2"><liferay-ui:message key="timesheet.view.register.end" />: </div>
                <div class="col-sm-10" style="padding:0; text-align:left;">
                    <input type="time" id="timeEnd" name="timeEnd"></input>
                </div>
            </div>
            <div class="row timesheet-row">
                <div class="col-sm-2"><liferay-ui:message key="timesheet.view.observations" /></div>
                <div class="col-sm-10" style="padding:0;">
                    <textarea id="inpObservaciones" style="width:100%;" maxlength="300"></textarea>
                </div>
            </div>
            <div class="row timesheet-row">
                <h4><span id="lblMensaje" style="background-color:crimson;color:honeydew;"><liferay-ui:message key="timesheet.view.message.select" /></span></h4>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-5"> </div>
        <div class="col-sm-7">
            <div class="row">
                <div class="col-sm">
                    <button type="button" id="btnGuardar" onclick="validateActivity()"><liferay-ui:message key="timesheet.view.save" /></button>
                    <button type="button" id="btnCancelar" onclick="calendarioPicker.cleanFormulario(1);"><liferay-ui:message key="timesheet.view.cancel" /></button>
                </div>
            </div>
        </div>
    </div>
    <div class="row"></div>
    <div class="row">&nbsp;</div>
    <!--detalles-->
</div>

<div id="capaDetalles"></div>


<div class="contentModal alert-modal warning" id="modalContainer" style="border-radius: 10px; width: 750px;">
    <div class="col-md-12 headerModal">
        <p id="addEntidad"><liferay-ui:message key="header.modal"/></p>
    </div>
    <div id="containerModal" style="margin-top: 30px;">
        <div class="container">
            <div class="row myrow">
                <div class="col-12 text-center">
                   <p class="text-center" id="containerJson"></p>
                </div>
            </div>
            <div class="row myrow hide" id="gestorInfo">
                <div class="col-2">
                </div>
                <div class="col-4">
                    <label class="labelInput"><liferay-ui:message key="mail.confirm"/>*</label>
                </div>
                <div class="col-5">
                    <aui:input type="text" name="mailGestor" label=""/>
                    <p class="text-danger hide" id="invalidMail"><liferay-ui:message key="mail.invalidate"/></p>
                </div>
            </div>
        </div>
        <div class="footerModal">

             <button type="button" class="btn-primary buttonModal" id="btnSaveCad" onclick="saveActivityExtra()" data-dismiss="modal" aria-label="Close">
                <liferay-ui:message key="btn.aceptar"/>
             </button>

            <button type="button" class="buttonModal closeMyModal " id="btnCancel"  style="background-color: buttonface;color: black;" data-dismiss="modal" aria-label="Close">
               <liferay-ui:message key="timesheet.view.cancel" />
            </button>
        </div>
    </div>
</div>
<div class="divModal" id="modalPortlet">
</div>

<portlet:resourceURL id="getActivityData" var="getActivityDataURL"/>
<portlet:resourceURL id="createExtraActivityNotification" var="createExtraActivityNotificationURL"/>
<portlet:resourceURL id="createSignsFromUserNameCalendar" var="createSignsFromUserNameCalendarURL"/>
<script>
    var tipo=1;
    var jsonData = null;
    var idActivity = null;
    var createUserNameCalendarURL = "<%=createSignsFromUserNameCalendarURL%>";
    $(document).ready(function(){
        //companyId,groupId,pAuth,idioma:
        calendarioPicker.init("<%=themeDisplay.getURLPortal()%>","<%=themeDisplay.getCompanyId()%>","<%=themeDisplay.getScopeGroupId()%>","<%=themeDisplay.getUserId()%>",Liferay.authToken,"<%=user.getLocale()%>");
        $('.closeMyModal').click(function(){
            $('.divModal').hide();
            $('.contentModal').hide();
            $('#<portlet:namespace/>mailGestor').val("")
            $("#wrapper").css("z-index","0");
            $('#invalidMail').addClass('hide');
        });
    });

    function getTypeActivity(selectObject) {
        tipo = $('option:selected',selectObject).attr('data-tipo');
        idActivity=selectObject.value;
        if(tipo==3){
            $.ajax({
                type: "POST",
                url: '${getActivityDataURL}',
                data: {
                    '<portlet:namespace/>idActivity':idActivity
                },
                success: function (result) {
                  console.log("Okay: "+result)
                  jsonData=result
                }
            });
        }
    }
    function mostrarModal(){
        $('#modalPortlet').css('display','block');
        $('#modalContainer').css('display','block');
    }

    function saveActivityExtra(){
        var mailGestor=$('#<portlet:namespace/>mailGestor').val()
        var horaIncio=$('#timeStart').val()
        var horaFin=$('#timeEnd').val()
        var fecha=calendarioPicker.fechaSeleccionada.replaceAll('.','/')
        var observaciones=$('#inpObservaciones').val()
        console.log(mailGestor)
        if(!jsonData.mailGestor){
             calendarioPicker.validate()
              $('.closeMyModal').click()
        }else if(mailGestor!="" && validarEmails(mailGestor) && jsonData.mailGestor){
             $('#invalidMail').addClass('hide');

             $.ajax({
                 type: "POST",
                 url: '${createExtraActivityNotificationURL}',
                 data: {
                     '<portlet:namespace/>idActivity':idActivity,
                     '<portlet:namespace/>mailGestor':mailGestor,
                     '<portlet:namespace/>fechaRegistro':fecha,
                     '<portlet:namespace/>horaIncio':horaIncio,
                     '<portlet:namespace/>horaFin':horaFin,
                     '<portlet:namespace/>observaciones':observaciones
                 },
                 success: function (result) {
                   console.log("Okay: "+result)

                 }
             });

             calendarioPicker.validate()
              $('.closeMyModal').click()
        }else{
            $('#invalidMail').removeClass('hide');
        }
    }

    function validateActivity(){
        var horaIncioValue=$('#timeStart').val()
        if(tipo==3 && jsonData!=null && horaIncioValue.length>0 ){
            $('#containerJson').html(jsonData.observaciones)
            if(jsonData.mailGestor){
                $('#gestorInfo').removeClass('hide')
            }else{
                $('#gestorInfo').addClass('hide')
            }
            mostrarModal();

        }else{
            if($("#cmbUserCalendars").length && $("#cmbUserCalendars").val() != ""){
                createUserNameCalendar();
            }else{
                calendarioPicker.validate();
            }
        }

    }

function createUserNameCalendar(){
    $("#lblMensaje").html('')
    if($("#tablaDetalles tr").length > 1){
        $("#lblMensaje").html(' <liferay-ui:message key="timesheet.view.register.calendarioPersonalizado.avisoVacio"/>');
        $("#cmbUserCalendars").val('');
        return;
    }
    var formData = new FormData();
    formData.append('companyId',"<%=companyConf.getCompanyConfId()%>");
    formData.append('groupId',"<%=themeDisplay.getScopeGroupId()%>");
    formData.append('userId',"<%=themeDisplay.getUserId()%>");
    formData.append('userNameCalendarId', $("#cmbUserCalendars").val());
    formData.append('date',calendarioPicker.fechaSeleccionada.replaceAll(".","-"));

    var auxSeleccionada = calendarioPicker.fechaSeleccionada;
    $.ajax({
        type: "POST",
        url: createUserNameCalendarURL,
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            console.log("Okay: " + result);
            $("#cmbUserCalendars").val('');
            calendarioPicker.init("<%=themeDisplay.getURLPortal()%>","<%=themeDisplay.getCompanyId()%>","<%=themeDisplay.getScopeGroupId()%>","<%=themeDisplay.getUserId()%>",Liferay.authToken,"<%=user.getLocale()%>");

            var arrDay = auxSeleccionada.split(".");
            setTimeout(function(){
                calendarioPicker.clickCalendario({label:auxSeleccionada});
                $(".calendar-frame td").each(function(i){
	                $(this).removeClass("selected");
	                if($(this).html() == arrDay[0] || $(this).html() == arrDay[0]  + '<span class="marcaCalendario">&nbsp;</span>'){
		                $(this).addClass("selected");
	                }

                });
            }, 500);
      }
    });

}

function validarEmails(mailGestor) {
  // Expresión regular para validar direcciones de correo electrónico
  const regex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

  let emailsValidos = [];
  let emailsInvalidos = [];


    if (regex.test(mailGestor.trim())) {
      emailsValidos.push(mailGestor.trim());
    } else {
      emailsInvalidos.push(mailGestor.trim());
    }

  if (emailsInvalidos.length === 0) {
    return true;
  } else {
    return false
  }
}
</script>