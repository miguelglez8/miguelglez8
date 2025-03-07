<%@ page import="com.plan.igualdad.liferay.portlet.admin.empresas.web.constants.AdminEmpresasPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Application" %>
<%@ page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.model.Role" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.petra.string.StringPool" %>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="java.util.List" %>

<%@ include file="init.jsp" %>

<%

PortletPreferences preferences = renderRequest.getPreferences();
String portletResource = ParamUtil.getString(request, "portletResource");
if (Validator.isNotNull(portletResource)) {
    preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

String consultorRol = preferences.getValue(AdminEmpresasPortletKeys.CONSULTOR_ROL, StringPool.BLANK);
String adminRol = preferences.getValue(AdminEmpresasPortletKeys.ADMIN_ROL, StringPool.BLANK);
String appAlias = preferences.getValue(AdminEmpresasPortletKeys.APP_ALIAS, StringPool.BLANK);

List<Role> roles = RoleLocalServiceUtil.getRoles(themeDisplay.getCompanyId());
List<Application> applications = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);


%>

<div class="lfr-form-content">
    <div class="sheet sheet-lg">
        <div class="row">
            <div class="col-sm-12">
                <liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
                <aui:form action="<%= configurationURL.toString() %>" method="post" >

                    <aui:select name="rolAdmin" id="rolAdmin" label="Rol Administrador">
                        <aui:option selected="<%= Validator.isBlank(adminRol) %>" value=""></aui:option>
                        <c:forEach items="<%= roles %>" var="rol">
                            <%
                                Role curRol = (Role) pageContext.getAttribute("rol");
                            %>
                            <aui:option selected="<%= Validator.isBlank(adminRol) ? false : curRol.getRoleId() == Long.parseLong(adminRol) %>" value="${rol.roleId}">
                                ${rol.name}
                            </aui:option>
                        </c:forEach>
                    </aui:select>

                    <aui:select name="rolConsultor" id="rolConsultor" label="Rol Consultor">
                        <aui:option selected="<%= Validator.isBlank(consultorRol) %>" value=""></aui:option>
                        <c:forEach items="<%= roles %>" var="rol">
                            <%
                                Role curRol = (Role) pageContext.getAttribute("rol");
                            %>
                            <aui:option selected="<%= Validator.isBlank(consultorRol) ? false : curRol.getRoleId() == Long.parseLong(consultorRol) %>" value="${rol.roleId}">
                                ${rol.name}
                            </aui:option>
                        </c:forEach>
                    </aui:select>

                    <aui:select name="appAlias" id="appAlias" label="App Alias">
                        <aui:option selected="<%= Validator.isBlank(appAlias) %>" value=""></aui:option>
                        <c:forEach items="<%= applications %>" var="app">
                            <%
                                Application curApp = (Application) pageContext.getAttribute("app");
                            %>
                            <aui:option selected="<%= Validator.isBlank(appAlias) ? false : curApp.getAlias().equals(appAlias) %>" value="${app.alias}">
                                ${app.name}
                            </aui:option>
                        </c:forEach>
                    </aui:select>

                    <aui:button-row>
                        <aui:button type="submit" />
                    </aui:button-row>

                </aui:form>
            </div>
        </div>
    </div>
</div>