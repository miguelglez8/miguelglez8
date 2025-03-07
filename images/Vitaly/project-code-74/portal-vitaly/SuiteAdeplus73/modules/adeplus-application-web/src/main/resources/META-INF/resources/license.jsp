<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.ApplicationLicenseLocalServiceUtil" %>
<%@ include file="init.jsp" %>

<%
    long licenseEditId = ParamUtil.getLong(request, AdeplusApplicationsPortletKeys.LICENSE, 0);
    long applicationEditId = ParamUtil.getLong(request, AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT, 0);

    ApplicationLicense license = null;

    if(licenseEditId > 0) {
        license = ApplicationLicenseLocalServiceUtil.fetchApplicationLicense(licenseEditId);
    }

%>
<liferay-portlet:actionURL name="/license/save" var="saveURL" />
<aui:form  name="edit_form" action="<%=saveURL.toString()%>" method="post">

    <aui:input name="<%=AdeplusApplicationsPortletKeys.LICENSE%>" value="<%=licenseEditId%>" type="hidden" />
    <aui:input name="<%=AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT%>" value="<%=applicationEditId%>" type="hidden" />

    <div class="content">
        <div class="titulo-seccion">
            <h2><liferay-ui:message key="license.edit.title"/></h2>
        </div>
        <div class="formulario">
            <div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
                <div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">

                    <div class="row">
                        <div class="form-group col-md-6 required">
                            <aui:input name="<%=AdeplusApplicationsPortletKeys.LICENSE_NAME%>" type="text" label="license.view.name" value="<%=Validator.isNotNull(license)?license.getName():""%>">
                                <aui:validator name="required" errorMessage="license.edit.validator.required" />
                            </aui:input>
                        </div>
                        <div class="form-group col-md-6 required">
                            <aui:input name="<%=AdeplusApplicationsPortletKeys.LICENSE_KEY%>" type="text" label="license.view.key" helpMessage="license.view.key.help" value="<%=Validator.isNotNull(license)?license.getPermissionRoleKey():""%>" >
                                <aui:validator name="required" errorMessage="license.edit.validator.required" />
                            </aui:input>
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
                <aui:button onClick="<%= cancelURL.toString() %>" value="license.edit.cancel" cssClass="btn btn-outline-primary mr-4" primary="true"></aui:button>

                <aui:button type="submit" cssClass="btn btn-primary" />

                <c:if test="<%=Validator.isNotNull(license)%>">
                    <liferay-portlet:actionURL name="deleteLicense" var="deleteURL">
                        <portlet:param name="<%=AdeplusApplicationsPortletKeys.LICENSE%>" value="<%=String.valueOf(license.getLicenseId())%>" />
                        <portlet:param name="<%=AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT%>" value="<%=String.valueOf(applicationEditId)%>" />
                    </liferay-portlet:actionURL>
                    <aui:button onClick="<%= deleteURL.toString() %>" value="license.edit.delete"></aui:button>
                </c:if>
            </aui:button-row>
        </div>

</aui:form>