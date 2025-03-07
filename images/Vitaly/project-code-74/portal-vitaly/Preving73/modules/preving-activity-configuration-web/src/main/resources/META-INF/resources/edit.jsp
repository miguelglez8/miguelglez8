<%@ include file="/init.jsp" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.Activity" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<script type="text/javascript" charset="utf8" src="https://cdn.ckeditor.com/4.19.0/standard/ckeditor.js"></script>

<h2><liferay-ui:message key="activity.configuration.view.edit.title"/></h2>

<%
    long activityId = ParamUtil.getLong(request, "activityId", 0);
    Activity activityEdit = ActivityLocalServiceUtil.getActivity(activityId);

    CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCompanyIdAndGroupId(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());

%>
<c:if test="<%=Validator.isNotNull(activityEdit)%>">
    <liferay-portlet:actionURL name="saveActivity" var="saveActivityURL"  />
    <aui:form name="edit_form" action="<%= saveActivityURL.toString() %>" method="post">

        <aui:input name="activityId" value="<%=activityEdit.getActivityId()%>" type="hidden" />

        <div class="row">
            <div class="col-sm-6 inputCreateAct">
                <label><liferay-ui:message key="activity.configuration.view.name"/></label>
                <liferay-ui:input-localized id="inputCreateAct" name="name" CssClass="col-md-12" xml="<%=activityEdit.getName()%>"/>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <aui:field-wrapper label="activity.configuration.view.color">
                    <input type="color" id="inputColor" name="<portlet:namespace/>color" list="rainbow"
                           value="<%=activityEdit.getColor()%>" style="height:30px;">
                    <datalist id="rainbow">
                        <option value="#980000"></option>
                        <option value="#FF0000"></option>
                        <option value="#FF9900"></option>
                        <option value="#FFFF00"></option>
                        <option value="#00FF00"></option>
                        <option value="#00FFFF"></option>
                        <option value="#4A86E8"></option>
                        <option value="#0000FF"></option>
                        <option value="#9900FF"></option>
                        <option value="#FF00FF"></option>

                        <option value="#85200C"></option>
                        <option value="#E06666"></option>
                        <option value="#F6B26B"></option>
                        <option value="#BF9000"></option>
                        <option value="#93C47D"></option>
                        <option value="#76A5AF"></option>
                        <option value="#1155CC"></option>
                        <option value="#0B5394"></option>
                        <option value="#8E7CC3"></option>
                        <option value="#741B47"></option>

                        <option value="#A61C00"></option>
                        <option value="#CC0000"></option>
                        <option value="#E69138"></option>
                        <option value="#F1C232"></option>
                        <option value="#6AA84F"></option>
                        <option value="#45818E"></option>
                        <option value="#3C78D8"></option>
                        <option value="#3D85C6"></option>
                        <option value="#674EA7"></option>
                        <option value="#A64D79"></option>

                        <option value="#85200C"></option>
                        <option value="#990000"></option>
                        <option value="#B45F06"></option>
                        <option value="#BF9000"></option>
                        <option value="#38761D"></option>
                        <option value="#134F5C"></option>
                        <option value="#1155CC"></option>
                        <option value="#0B5394"></option>
                        <option value="#351C75"></option>
                        <option value="#741B47"></option>
                    </datalist>
                    </input>
                </aui:field-wrapper>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <aui:field-wrapper label="activity.configuration.view.edit.workTime">
                    <aui:input name="workTime" value="true" type="radio" label="activity.configuration.view.edit.yes" checked="<%=activityEdit.getWorkTime()==true ? true : false%>"/>
                    <aui:input name="workTime" value="false" type="radio" label="activity.configuration.view.edit.no" checked="<%=activityEdit.getWorkTime()==false ? true : false%>"/>
                </aui:field-wrapper>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <aui:field-wrapper label="activity.configuration.view.edit.type">
                    <aui:input name="type" value="1" type="radio" label="activity.configuration.view.edit.working" checked="<%=activityEdit.getType()==1 ? true : false%>"/>
                    <aui:input name="type" value="2" type="radio" label="activity.configuration.view.edit.absence" checked="<%=activityEdit.getType()==2 ? true : false%>"/>
                    <aui:input name="type" value="3" type="radio" label="activity.configuration.view.extra" checked="<%=activityEdit.getType()==3 ? true : false%>"/>
                </aui:field-wrapper>
            </div>
        </div>

        <div class="row hide lfr-ddm-field-group" id="obsevationsExtra">
            <div class="control-label"></div>
            <div class="col">
                <p class="textMoreBig"><strong><liferay-ui:message key="activity.configuration.observation"/></strong></p>
                <p class="textMoreBig"><liferay-ui:message key="activity.configuration.observation.text"/></p>
                <textarea name="<portlet:namespace />observationType" id="<portlet:namespace />observationType" style="width: 80%;height: 100px"></textarea>
                <p class="text-danger hide" id="invalidText"><liferay-ui:message key="text.invalidate"/></p>
                <p class="textMoreBig"><strong><liferay-ui:message key="activity.notification"/></strong></p>
                <p class="textMoreBig"><liferay-ui:message key="activity.notification.input.first"/></p>
                <p class="textLabel"><liferay-ui:message key="activity.notification.input.first.label"/></p>
                <c:if test="<%=activityEdit.getInfoRespo()==true %>">
                    <input name="<portlet:namespace/>notificate" value="true" type="radio" onclick="enableAdditionalMailing()" label="activity.notification.input.yes" checked ><liferay-ui:message key="activity.notification.input.yes"/></input>
                    <input name="<portlet:namespace/>notificate" class="d-inline addMargin" value="false"  type="radio" onclick="disableAdditionalMailing()" label="activity.notification.input.no"><liferay-ui:message key="activity.notification.input.no"/></input>
                </c:if>
                <c:if test="<%=activityEdit.getInfoRespo()==false %>">
                    <input name="<portlet:namespace/>notificate" value="true" type="radio" onclick="enableAdditionalMailing()" label="activity.notification.input.yes" ><liferay-ui:message key="activity.notification.input.yes"/></input>
                    <input name="<portlet:namespace/>notificate" class="d-inline addMargin" value="false" type="radio" onclick="disableAdditionalMailing()" label="activity.notification.input.no" checked><liferay-ui:message key="activity.notification.input.no"/></input>
                </c:if>
                <div id="additionalMailing" class="d-none">
                    <p class="textMoreBig" style="margin-top: 10px;"><liferay-ui:message key="activity.notification.input.second"/></p>
                    <p class="textLabel"><liferay-ui:message key="activity.notification.input.second.label.extra"/></p>
                    <c:if test="<%=activityEdit.getInfoUser()==true %>">
                        <input name="<portlet:namespace/>notificateAdditional" onclick="enableInput()" value="true" type="radio" label="activity.notification.input.yes" checked><liferay-ui:message key="activity.notification.input.yes"/></input>
                        <input name="<portlet:namespace/>notificateAdditional" onclick="disableInput()" class="d-inline addMargin" value="false" type="radio" label="activity.notification.input.no"><liferay-ui:message key="activity.notification.input.no"/></input>
                    </c:if>
                    <c:if test="<%=activityEdit.getInfoUser()==false %>">
                        <input name="<portlet:namespace/>notificateAdditional" onclick="enableInput()" value="true" type="radio" label="activity.notification.input.yes" ><liferay-ui:message key="activity.notification.input.yes"/></input>
                        <input name="<portlet:namespace/>notificateAdditional" onclick="disableInput()" class="d-inline addMargin" value="false" type="radio" label="activity.notification.input.no" checked><liferay-ui:message key="activity.notification.input.no"/></input>
                    </c:if>
                    <p class="textLabel" style="margin-top: 10px;"><liferay-ui:message key="activity.notification.input.second.label"/></p>
                    <aui:input type="text" name="userAdditional" label="" placeholder="activity.placeholder" value="<%=activityEdit.getUsersToInform()%>"/>
                    <p class="text-danger hide" id="invalidMail"><liferay-ui:message key="mail.invalidate"/></p>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <aui:field-wrapper label="activity.configuration.view.edit.active">
                    <aui:input name="active" value="true" type="radio" label="activity.configuration.view.edit.active.yes" checked="<%=activityEdit.getActive() ? true : false%>"/>
                    <aui:input name="active" value="false" type="radio" label="activity.configuration.view.edit.active.no" checked="<%=!activityEdit.getActive() ? true : false%>"/>
                </aui:field-wrapper>
            </div>
        </div>

        <aui:button-row>
            <aui:button value="activity.configuration.view.button.edit.accept" type="submit"/>
            <portlet:renderURL portletMode="view" var="viewURL" />
            <aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
        </aui:button-row>
    </aui:form>
</c:if>


<script>
    var actividadHorasExtra=false;

    function createEditor(value) {

        var editor = CKEDITOR.replace('<portlet:namespace />observationType', {
            on: {
                instanceReady: function() {

                }
            }
        });
        editor.setData(value);

    }


    $(document).ready(function() {
        mostrarExtras();
        $('#<portlet:namespace/>edit_form input').on('change',function(){
            mostrarExtras();
        });

        if(<%=activityEdit.getInfoRespo()%>){
            enableAdditionalMailing();
        }else{
            disableAdditionalMailing();
        }

        if(<%=activityEdit.getInfoUser()%>){
            enableInput()
        }else{
            disableInput()
        }
        createEditor('<%=activityEdit.getObservations()%>');
        setInterval(function(){
            $('button').each(function(){
                var target = $(this);
                // if(target.text() == 'Other...' || target.val() == 'Other...'){
                if(target.text() == 'Otros...' || target.val() == 'Otros...'){
                    target.remove();
                }
            });
        }, 300);
    } );

function mostrarExtras(){
    if($('input[name="<portlet:namespace/>type"]:checked','#<portlet:namespace/>edit_form').val()==3){
        $('#obsevationsExtra').removeClass('hide')
        actividadHorasExtra=true

    }else{
        $('#obsevationsExtra').addClass('hide')
        actividadHorasExtra=false
    }
}

function validarEmails(mailGestor) {

  const emails = mailGestor.split(';');

  // Expresión regular para validar direcciones de correo electrónico
  const regex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

  let emailsValidos = [];
  let emailsInvalidos = [];

  emails.forEach(email => {
    if (regex.test(email.trim())) {
      emailsValidos.push(email.trim());
    } else {
      emailsInvalidos.push(email.trim());
    }
  });

  if (emailsInvalidos.length === 0) {
    return true;
  } else {
    return false
  }
}

function enableInput() {
    document.getElementById('<portlet:namespace/>userAdditional').disabled = false
}
function disableInput() {
    document.getElementById('<portlet:namespace/>userAdditional').disabled = true
    document.getElementById('<portlet:namespace/>userAdditional').value = ""
}
function enableAdditionalMailing() {
     $('#additionalMailing').removeClass('d-none');
}

function disableAdditionalMailing() {
    $('#additionalMailing').addClass('d-none');
}



function validarForm(){
    var disabledInput=document.getElementById('<portlet:namespace/>userAdditional').disabled
    var dataCkeditor= CKEDITOR.instances._com_preving_liferay_portlet_activity_configuration_web_ActivityConfigurationPortlet_observationType.getData()
    var correosAdicionales=$('#<portlet:namespace/>userAdditional').val()
    if(actividadHorasExtra && dataCkeditor==""){
         $('#invalidText').removeClass('hide');
         return false;
    }
    if(!disabledInput && !validarEmails(correosAdicionales)){
        $('#invalidText').addClass('hide');
        $('#invalidMail').removeClass('hide');
       return false;
    }
    $('#invalidText').addClass('hide');
    $('#invalidMail').addClass('hide');
    $('#<portlet:namespace/>sumbitForm').click();
}
</script>

<style>
    @media only screen and (max-width: 576px) {
        #inputCreateAct{width:100%;}
        .inputCreateAct{margin-left:4%;}
    }
    @media only screen and (min-width: 576px) {
        #inputCreateAct{width:70%; margin-left:58.5%;}
        #inputColor{margin-left:0%;}
        .inputCreateAct{margin-left:1%;}
    }
</style>
