<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Role" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil" %>
<%@ include file="init.jsp" %>

<%
    long roleEditId = ParamUtil.getLong(request, AdeplusApplicationsPortletKeys.ROLE, 0);
    long applicationEditId = ParamUtil.getLong(request, AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT, 0);

    Role role = null;

    if(roleEditId > 0) {
        role = RoleLocalServiceUtil.fetchRole(roleEditId);
    }

%>
<liferay-portlet:actionURL name="/role/save" var="saveURL" />
<aui:form  name="edit_form" action="<%=saveURL.toString()%>" method="post">

    <aui:input name="<%=AdeplusApplicationsPortletKeys.ROLE%>" value="<%=roleEditId%>" type="hidden" />
    <aui:input name="<%=AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT%>" value="<%=applicationEditId%>" type="hidden" />

    <div class="content">
        <div class="titulo-seccion">
            <h2><liferay-ui:message key="role.edit.title"/></h2>
        </div>
        <div class="formulario">
            <div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
                <div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">

                    <div class="row">
                        <div class="form-group col-md-6 required">
                            <aui:input name="<%=AdeplusApplicationsPortletKeys.ROLE_NAME%>" type="text" label="role.view.name" value="<%=Validator.isNotNull(role)?role.getName():""%>">
                                <aui:validator name="required" errorMessage="role.edit.validator.required" />
                            </aui:input>
                        </div>
                        <div class="form-group col-md-6 required">
                            <aui:input name="<%=AdeplusApplicationsPortletKeys.ROLE_ALIAS%>" type="text" label="role.view.alias" value="<%=Validator.isNotNull(role)?role.getAlias():""%>" >
                                <aui:validator name="required" errorMessage="application.edit.validator.required" />
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
            <aui:button onClick="<%= cancelURL.toString() %>" value="role.edit.cancel" cssClass="btn btn-outline-primary mr-4" primary="true"></aui:button>

            <aui:button type="submit" cssClass="btn btn-primary" />

            <c:if test="<%=Validator.isNotNull(role)%>">
                <liferay-portlet:actionURL name="deleteRole" var="deleteURL">
                    <portlet:param name="<%=AdeplusApplicationsPortletKeys.ROLE%>" value="<%=String.valueOf(role.getRoleId())%>" />
                    <portlet:param name="<%=AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT%>" value="<%=String.valueOf(applicationEditId)%>" />
                </liferay-portlet:actionURL>
                <aui:button onClick="<%= deleteURL.toString() %>" value="application.edit.delete"></aui:button>
            </c:if>
        </aui:button-row>
    </div>

</aui:form>
