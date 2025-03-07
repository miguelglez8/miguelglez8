<%@ page import="com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil" %>
<%@ include file="init.jsp" %>

<%
    long userId = ParamUtil.getLong(request, UserConfigurationPortletKeys.USER_ID, 0);

    User userEdit = UserLocalServiceUtil.getUser(userId);
    UserData userData = PrevingUserDataUtil.getUserData(scopeGroupId, userId);

    String nif = "";
    String salary = "";
    String workCenter = "";
    String fechaBaja = "";
    if(Validator.isNotNull(userEdit)){
        nif = userEdit.getExpandoBridge().hasAttribute("nif")? (String) userEdit.getExpandoBridge().getAttribute("nif") :"";
        salary = userEdit.getExpandoBridge().hasAttribute("salario")? (String) userEdit.getExpandoBridge().getAttribute("salario") :"";
        workCenter = userEdit.getExpandoBridge().hasAttribute("centroTrabajo")? (String) userEdit.getExpandoBridge().getAttribute("centroTrabajo") :"";
        fechaBaja = userEdit.getExpandoBridge().hasAttribute("fechaBaja")? (String) userEdit.getExpandoBridge().getAttribute("fechaBaja") :"";
    }

    boolean disabledEndDate = true;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Calendar endDateCalendar = Calendar.getInstance();
    endDateCalendar.setTime(new Date());
    if(Validator.isNotNull(userData) && Validator.isNotNull(userData.getDeleteDate())){
        endDateCalendar.setTime(userData.getDeleteDate());
        disabledEndDate = false;
    }else if(!Validator.isBlank(fechaBaja)){
        endDateCalendar.setTime(dateFormat.parse(fechaBaja));
        disabledEndDate = false;
    }

    Calendar notificationEndDate = Calendar.getInstance();
    boolean disabledNotificationEndDate = true;
    if (Validator.isNotNull(userData) && Validator.isNotNull(userData.getNotificationEndDate())) {
        disabledNotificationEndDate = false;
        notificationEndDate.setTime(userData.getNotificationEndDate());
    }else{
        notificationEndDate.setTime(new Date());
    }


    boolean hasWorkCenters = false;

    CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCompanyIdAndGroupId(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());


        List<WorkCenters> wcRes = null;
        Calendar hoy = Calendar.getInstance();
        hoy.set(Calendar.HOUR,0);
        hoy.set(Calendar.MINUTE,0);
        if(Validator.isNotNull(companyConf) && companyConf.isIsWorkCenters()){
            hasWorkCenters = true;
            wcRes = new ArrayList<WorkCenters>();
            List<WorkCenters> wc = WorkCentersLocalServiceUtil.getAllWorkCentersByCompanyId(companyConf.getCompanyConfId());
            for(WorkCenters w : wc){
                if(Validator.isNull(w.getEndDate()) || w.getEndDate().getTime() >= hoy.getTime().getTime()){
                    wcRes.add(w);
                }
            }
            Collections.sort(wcRes, Comparator.comparing(WorkCenters::getName,String.CASE_INSENSITIVE_ORDER));
        }
%>

<h2><liferay-ui:message key="user.configuration.view.edit.title"/></h2>

<c:if test="<%=Validator.isNotNull(userEdit)%>">
    <liferay-portlet:actionURL name="saveUser" var="saveUserURL"  />
    <aui:form  name="edit_form" action="<%= saveUserURL.toString() %>" method="post">

        <aui:input name="userId" value="<%=userEdit.getUserId()%>" type="hidden" />

        <aui:fieldset label="user.configuration.edit.user">
            <div class="row">
                <div class="col-sm-4 inputsCreate">
                    <label><liferay-ui:message key="user.configuration.view.genre"/></label>
                    <aui:field-wrapper cssClass="radio-button">
                        <aui:input name="genre" value="male" type="radio" label="user.configuration.view.genre.male" checked="<%=UserDataLocalServiceUtil.getMaleGenre(userData)%>"/>
                        <aui:input name="genre" value="female" type="radio" label="user.configuration.view.genre.female" checked="<%=UserDataLocalServiceUtil.getFemaleGenre(userData)%>"/>
                    </aui:field-wrapper>
                </div>
            </div>
            <div class="row" style="padding:0;">
                <div class="col-sm-4 inputsCreate">
                    <aui:input name="nif" type="text" label="user.configuration.view.nif" value="<%=Validator.isNotNull(userData)?userData.getNif():nif%>">
                        <aui:validator name="required" errorMessage="user.configuration.view.validator.required" />
                    </aui:input>
                </div>
                <div class="col-sm-4 inputsCreate">
                    <aui:input name="name" type="text" label="user.configuration.view.name" value="<%=Validator.isNotNull(userData)?userData.getName():userEdit.getFirstName()%>">
                        <aui:validator name="required" errorMessage="user.configuration.view.validator.required" />
                    </aui:input>
                </div>
                <div class="col-sm-4 inputsCreate" style="padding-right:1.5%;">
                    <aui:input name="surname" type="text" label="user.configuration.view.surname" value="<%=Validator.isNotNull(userData)?userData.getLastName():userEdit.getLastName()%>">
                        <aui:validator name="required" errorMessage="user.configuration.view.validator.required" />
                    </aui:input>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4 inputsCreate">
                    <aui:input name="email" type="text" label="user.configuration.view.email" value="<%=Validator.isNotNull(userData)?userData.getEmail():userEdit.getEmailAddress()%>">
                        <aui:validator name="required" errorMessage="user.configuration.view.email.error.required" />
                        <aui:validator name="email" errorMessage="user.configuration.view.email.error.format" />
                    </aui:input>
                </div>
                <div class="col-sm-4 inputsCreate">
                    <aui:input name="jobTitle" type="text" label="user.configuration.view.job.title" value="<%=Validator.isNotNull(userData)?userData.getJobTitle():userEdit.getJobTitle()%>"/>
                </div>
                <div class="col-sm-4 inputsCreate">
                    <c:if test="<%= hasWorkCenters %>">
                        <aui:select name="cmbWorkCenter" label="user.configuration.view.work.center">
                            <aui:option value="0"></aui:option>
                            <c:forEach items="<%=wcRes%>" var="w">
                                <aui:option value="${w.workCenterId}" > ${w.name}</aui:option>
                            </c:forEach>
                        </aui:select>
                     </c:if>
                     <c:if test="<%= !hasWorkCenters %>">
                        <aui:input name="workCenter" type="text" label="user.configuration.view.work.center" value="<%=Validator.isNotNull(userData)?userData.getWorkCenter():null%>" />
                     </c:if>
                </div>
            </div>
            <div class="row">
                <div id="salary" class="col-sm-4 inputsCreate">
                    <aui:input name="salary" type="text" label="user.configuration.view.salary" value="<%=Validator.isNotNull(userData)?Validator.isNotNull(userData.getSalary())?userData.getSalary():"":salary%>">
                        <aui:validator name="number" errorMessage="user.configuration.view.salary.error.number" />
                    </aui:input>
                </div>
                <div id="language" class="col-sm-4 inputsCreate">
                    <aui:select name="language" label="user.configuration.view.language">
                        <aui:option value="es_ES" selected="<%=userEdit.getLocale().toString().equals("es_ES")%>"><liferay-ui:message key="user.configuration.view.language.spanish"/></aui:option>
                        <aui:option value="ca_ES" selected="<%=userEdit.getLocale().toString().equals("ca_ES")%>"><liferay-ui:message key="user.configuration.view.language.cat"/></aui:option>
                    </aui:select>
                </div>
                <div class="col-sm-4">
                    <label><liferay-ui:message key="user.configuration.view.notification.enddate"/></label>
                    <c:if test="<%=disabledNotificationEndDate%>">
                        <liferay-ui:input-date name="notificationEndDate" nullable="true" disabled="<%=disabledNotificationEndDate%>"/>
                    </c:if>
                    <c:if test="<%=!disabledNotificationEndDate%>">
                        <liferay-ui:input-date name="notificationEndDate" nullable="true"
                                               yearValue="<%=notificationEndDate.get(Calendar.YEAR)%>"
                                               monthValue="<%=notificationEndDate.get(Calendar.MONTH)%>"
                                               dayValue="<%=notificationEndDate.get(Calendar.DAY_OF_MONTH)%>" />
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4 inputsCreate">
                    <label><liferay-ui:message key="user.configuration.view.admin"/></label>
                    <aui:field-wrapper  cssClass="radio-button">
                        <aui:input name="admin" value="admin" type="radio" label="user.configuration.view.admin.yes" checked="<%=PrevingRoleUtil.hasSiteAdminRole(themeDisplay.getScopeGroupId(), userEdit.getUserId())%>"/>
                        <aui:input name="admin" value="noAdmin" type="radio" label="user.configuration.view.admin.no" checked="<%=!PrevingRoleUtil.hasSiteAdminRole(themeDisplay.getScopeGroupId(), userEdit.getUserId())%>"/>
                    </aui:field-wrapper>
                </div>
                <div class="col-sm-4 inputsCreate">
                    <label><liferay-ui:message key="user.configuration.view.active"/></label>
                    <aui:field-wrapper  cssClass="radio-button">
                        <aui:input name="active" value="active" type="radio" label="user.configuration.view.active.yes" checked="<%=Validator.isNotNull(userData)?userData.getActive():userEdit.isActive()%>" disabled="true"/>
                        <aui:input name="active" value="inactive" type="radio" label="user.configuration.view.active.no" checked="<%=Validator.isNotNull(userData)?!userData.getActive():!userEdit.isActive()%>" disabled="true"/>
                    </aui:field-wrapper>
                </div>
                <div class="col-sm-4">
                    <label><liferay-ui:message key="user.configuration.view.date.end"/></label>
                    <c:if test="<%=disabledEndDate%>">
                        <liferay-ui:input-date name="endDate" nullable="true" disabled="<%=disabledEndDate%>"/>
                    </c:if>
                    <c:if test="<%=!disabledEndDate%>">
                        <liferay-ui:input-date name="endDate" nullable="true"
                                               yearValue="<%=endDateCalendar.get(Calendar.YEAR)%>"
                                               monthValue="<%=endDateCalendar.get(Calendar.MONTH)%>"
                                               dayValue="<%=endDateCalendar.get(Calendar.DAY_OF_MONTH)%>" />
                    </c:if>
                </div>
            </div>
        </aui:fieldset>

        <div class="row">
            <div class="col">
                <aui:button-row>
                    <aui:button type="submit" />
                    <portlet:renderURL portletMode="view" var="viewURL" />
                    <aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
                </aui:button-row>
            </div>
            <div class="col text-right">
                <liferay-portlet:actionURL name="remindPassword" var="passRemindURL">
                    <portlet:param name="userId" value="<%=String.valueOf(userEdit.getUserId())%>" />
                    <portlet:param name="email" value="<%=String.valueOf(userEdit.getEmailAddress())%>" />
                </liferay-portlet:actionURL>
                <a href="<%=passRemindURL.toString().replace("+", "")%>" class="dt-button" >
                    <span><liferay-ui:message key="user.configuration.edit.remind.password"/></span>
                </a>
            </div>
        </div>

    </aui:form>
</c:if>

<style>
    @media only screen and (max-width: 576px) {
        .inputsCreate{padding:0;width:100%;}
    }
    @media only screen and (min-width: 576px) {
        .inputsCreate{}
        #salary{ padding:0;}
    }
</style>

<script>
    $(document).ready(function(){
        $("#<portlet:namespace/>cmbWorkCenter").val("<%= userData.getWorkCenterId()%>")
    });
</script>