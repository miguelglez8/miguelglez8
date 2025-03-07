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
    
    if(companyEditId > 0) {
        comp = CompLocalServiceUtil.fetchComp(companyEditId);
        if(Validator.isNotNull(comp)){
            usersFromCompany = UserCompanyLocalServiceUtil.getUsersFromCompany(comp.getCompId());
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
%>

<liferay-ui:success key="company-save-success" message="company.view.save.success" />
<liferay-ui:success key="save-user-success" message="company.view.save.user.success" />

<liferay-ui:error key="error-create-company" message="company.view.save.error.cif" />
<liferay-ui:error key="error-create-nif" message="company.view.save.error.nif" />
<liferay-ui:error key="error-create-email" message="company.view.save.error.email" />

<liferay-portlet:actionURL name="/company/save" var="saveCompURL" />
<aui:form  name="edit_form" action="<%=saveCompURL.toString()%>" method="post">

    <aui:input name="<%=AdeplusCompaniesPortletKeys.COMPANY_ID_EDIT%>" value="<%=companyEditId%>" type="hidden" ignoreRequestValue="true"/>

    <div class="content">
        <div class="titulo-seccion">
            <h2><liferay-ui:message key="company.edit.title"/></h2>
        </div>
        
        <div class="formulario">
            <div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
                <div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">
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

                    <!-- <div class="row">
                        <div class="form-group col-md-6 required">
                            <aui:input name="<%=AdeplusCompaniesPortletKeys.USER_ID_CRM%>" type="text" label="user.edit.id.crm" value="<%=Validator.isNotNull(comp)?comp.getIdCrm():""%>">
                                <aui:validator name="required" errorMessage="user.edit.validator.required" />
                            </aui:input>
                        </div>
                        <div class="form-group col-md-6 required">
                            <aui:input name="<%=AdeplusCompaniesPortletKeys.USER_ID_CONTRATO%>" type="text" label="user.edit.id.contrato" value="<%=Validator.isNotNull(comp)?comp.getIdContrato():""%>">
                                <aui:validator name="required" errorMessage="user.edit.validator.required" />
                            </aui:input>
                        </div>
                    </div> -->



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
                    </div>

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

                <!-- Aplicaciones -->
                <div class="form-content form-aplicaciones">
                    <div class="row">
                        <div class="input-group mb-3">
                            <legend class="fieldset-title-section"><liferay-ui:message key="company.edit.applications"/></legend>
                            <c:forEach items="<%=appList%>" var="app" varStatus="item">
                                <%
                                    boolean checked = false;
                                    Application app = (Application) pageContext.getAttribute("app");
                                    if(Validator.isNotNull(comp) && AdeplusPermissionCompanyUtil.hasCompanyApplication(comp.getCompId(), app.getApplicationId())){
                                        checked = true;
                                    }
                                    List<ApplicationLicense> licensebyApplicationId = ApplicationLicenseLocalServiceUtil.findByApplicationId(app.getApplicationId());
                                %>
                                <div class="custom-control custom-checkbox mb-3">
                                    <aui:input name="${app.applicationId}" type="checkbox" label="${app.name}" checked="<%=checked%>" cssClass="check-app" >
                                        <c:if test="${item.last}">
                                            <aui:validator name="required" errorMessage="company.edit.validator.checkbox.required">
                                                        function() {
                                                            var correct = document.querySelectorAll('input[type="checkbox"]:checked.check-app').length == 0;
                                                            console.log("correct " + correct );
                                                            return correct;
                                                        }
                                            </aui:validator>
                                        </c:if>
                                    </aui:input> 

                                    <div class="application_license ml-5">
                                    	<%
                                    	ApplicationLicense appLicense = null;
                                        boolean licenseChecked = false;
                                        CompApplication companyApplication = null;
                                    	%>
                                        <c:forEach items="<%=licensebyApplicationId%>" var="applicationLicense">
                                            <%
                                                //ApplicationLicense appLicense = (ApplicationLicense) pageContext.getAttribute("applicationLicense");
                                                //boolean licenseChecked = false;
                                                //CompApplication companyApplication = CompApplicationLocalServiceUtil.getCompanyApplication(comp.getCompId(), app.getApplicationId());
                                                if(comp != null){
                                                	appLicense = (ApplicationLicense) pageContext.getAttribute("applicationLicense");
                                                    licenseChecked = false;
                                                    companyApplication = CompApplicationLocalServiceUtil.getCompanyApplication(comp.getCompId(), app.getApplicationId());
                                                    if(Validator.isNotNull(companyApplication) && companyApplication.getLicenseId() == appLicense.getLicenseId()){
                                                        licenseChecked = true;
                                                    }	
                                                }
                                                
                                            %>
                                            <aui:input name="app_${app.applicationId}" id="license_${applicationLicense.licenseId}" type="radio" label="${applicationLicense.name}" value="${applicationLicense.licenseId}" checked="<%=licenseChecked%>" cssClass="check-app"  ></aui:input>
                                        </c:forEach>
                                    </div>

                                </div>
                            </c:forEach>
                        </div>
                    </div>
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

            <aui:button type="submit" cssClass="btn btn-primary" />

        </aui:button-row>
    </div>

</aui:form>


<c:if test="<%=Validator.isNotNull(comp)%>">
    <%@include file="users.jspf" %>
</c:if>

<script>
    deleteDisabledClass();
    function deleteDisabledClass() {
        var element = document.getElementById("_com_adeplus_liferay_portlet_company_web_AdeplusCompaniesPortlet_companyDateEnd");
        element.classList.remove("disabled");
    }
</script>