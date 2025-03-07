<%@ page import="com.adeplus.liferay.portlet.menu.web.constants.MenuPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.model.Layout" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
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

String pagina = preferences.getValue(MenuPortletKeys.LAYOUT, StringPool.BLANK);
List<Layout> paginas = LayoutLocalServiceUtil.getLayouts(themeDisplay.getScopeGroupId(), false);

String paginaDefecto = preferences.getValue(MenuPortletKeys.LAYOUT_DEFECT, "");
String estadoDefecto = preferences.getValue(MenuPortletKeys.ESTADO_DEFECT, "0");

%>

<div class="lfr-form-content">
    <div class="sheet sheet-lg">
        <div class="row">
            <div class="col-sm-12">
                <liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
                <aui:form action="<%= configurationURL.toString() %>" method="post" >

                    <aui:select name="pagina" id="pagina" label="Pagina">
                        <aui:option selected="<%= Validator.isBlank(pagina) %>" value=""></aui:option>
                        <c:forEach items="<%= paginas %>" var="curPagina">
                            <%
                                Layout curPagina = (Layout) pageContext.getAttribute("curPagina");
                            %>
                            <aui:option selected="<%= Validator.isBlank(pagina) ? false : curPagina.getPlid() == Long.parseLong(pagina) %>" value="${curPagina.plid}">
                                ${curPagina.name}
                            </aui:option>
                        </c:forEach>
                    </aui:select>

                    <aui:input name="<%=MenuPortletKeys.LAYOUT_DEFECT %>" type="text" label="Pagina Defecto" value="<%=paginaDefecto%>">
                    </aui:input>

                    <aui:input name="<%=MenuPortletKeys.ESTADO_DEFECT %>" type="text" label="Estado Defecto(0 no muestra)" value="<%=estadoDefecto%>">
                    </aui:input>
                    <aui:button-row>
                        <aui:button type="submit" />
                    </aui:button-row>

                </aui:form>
            </div>
        </div>
    </div>
</div>