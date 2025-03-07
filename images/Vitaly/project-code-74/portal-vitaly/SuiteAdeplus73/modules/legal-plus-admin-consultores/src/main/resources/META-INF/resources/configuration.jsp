<%@ page import="com.legalplus.liferay.portlet.admin.consultores.web.constants.AdminConsultoresPortletKeys" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Comp" %>
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

String consultorRol = preferences.getValue(AdminConsultoresPortletKeys.CONSULTOR_ROL, StringPool.BLANK);
String clienteRol = preferences.getValue(AdminConsultoresPortletKeys.CLIENTE_ROL, StringPool.BLANK);
List<Role> roles = RoleLocalServiceUtil.getRoles(themeDisplay.getCompanyId());

String selectedEmpresa = preferences.getValue(AdminConsultoresPortletKeys.CONSULTOR_EMPRESA, StringPool.BLANK);
List<Comp> companies = CompLocalServiceUtil.getComps(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

%>

<div class="lfr-form-content">
    <div class="sheet sheet-lg">
        <div class="row">
            <div class="col-sm-12">
                <liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
                <aui:form action="<%= configurationURL.toString() %>" method="post" >

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

                     <aui:select name="rolCliente" id="rolCliente" label="Rol Cliente">
                        <aui:option selected="<%= Validator.isBlank(clienteRol) %>" value=""></aui:option>
                        <c:forEach items="<%= roles %>" var="rol">
                            <%
                                Role curRol = (Role) pageContext.getAttribute("rol");
                            %>
                            <aui:option selected="<%= Validator.isBlank(clienteRol) ? false : curRol.getRoleId() == Long.parseLong(clienteRol) %>" value="${rol.roleId}">
                                ${rol.name}
                            </aui:option>
                        </c:forEach>
                    </aui:select>

                    <aui:select name="empresa" id="empresa" label="Empresa">
                        <aui:option selected="<%= Validator.isBlank(selectedEmpresa) %>" value=""></aui:option>
                        <c:forEach items="<%= companies %>" var="comp">
                            <%
                                Comp curComp = (Comp) pageContext.getAttribute("comp");
                            %>
                            <aui:option selected="<%= Validator.isBlank(selectedEmpresa) ? false : curComp.getCompId() == Long.parseLong(selectedEmpresa) %>" value="${comp.compId}">
                                ${comp.name}
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