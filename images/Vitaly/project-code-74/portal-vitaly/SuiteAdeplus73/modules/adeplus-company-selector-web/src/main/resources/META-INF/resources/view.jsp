<%@ page import="com.adeplus.liferay.portlet.company.selector.web.constants.CompanySelectorPortletKeys" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Comp" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.List" %>
<%@ include file="/init.jsp" %>

<%
	long userCompId = (long) request.getAttribute(CompanySelectorPortletKeys.USER_COMP_ID);
	long userClientId= (long) request.getAttribute(CompanySelectorPortletKeys.USER_CLIENT_ID);
	long userContractId= (long) request.getAttribute(CompanySelectorPortletKeys.USER_CONTRACT_ID);
	String userName = (String) request.getAttribute(CompanySelectorPortletKeys.USER_NAME);
	String logoURL = (String) request.getAttribute(CompanySelectorPortletKeys.USER_LOGO_URL);
	List<Comp> companiesActiveFromUser = (List<Comp>) request.getAttribute(CompanySelectorPortletKeys.USER_COMPS_ACTIVE);
	List<CompClientApplication> clients =(List<CompClientApplication>)request.getAttribute(CompanySelectorPortletKeys.USER_CLIENTS_ACTIVE);
	String current=themeDisplay.getURLCurrent();
	boolean showInput=false;
	if(current.contains("canal-etico") || current.contains("legal")){
		showInput=true;
	}

%>

<div class="row usuario-header">
	<div class="usuario-wrapper">
		<div class="usuario-nombre"><%= userName %></div>
		<img class="usuario-ico dropdown-toggle"
			src="<%= themeDisplay.getPathThemeImages() + "/ico_Usuario.svg" %>"
			alt="usuario" id="dropdownMenuUsuario" data-toggle="dropdown"
			aria-haspopup="true" aria-expanded="false">
		<!-- SELECTOR IDIOMA -->
		<div class="header-separador"></div>
		<div class="selector-idioma">
			<liferay-portlet:runtime portletName="com_liferay_site_navigation_language_web_portlet_SiteNavigationLanguagePortlet">
			</liferay-portlet:runtime>
		</div>
	</div>

	<!-- EMPRESA: Logo y Nombre -->
	<div class="header-empresa">
		<div>
			<!--Prueba-->
			<c:if test="<%=clients.size() > 1 %>">
                <c:if test="<%=showInput == false %>">
                    <portlet:actionURL name="selectCompany" var="selectCompanylURL" />
                    <aui:form name="search_form" action="<%= selectCompanylURL.toString() %>" method="post">
                        <fieldset class="input-group-inline m-2">
                            <aui:select name="userCompClientId" label="" onChange="this.form.submit()">
                                <c:forEach var="comp" items="<%= clients %>">
                                    <% CompClientApplication client = (CompClientApplication) pageContext.getAttribute("comp");
                                        String nameComp=CompLocalServiceUtil.getComp(client.getCompId()).getName();
                                    %>
                                    <aui:option value="${comp.compId}-${comp.clientId}-${comp.contractId}" selected="<%=client.getClientId() == userClientId && client.getContractId() == userContractId%>">
                                        <%=nameComp%>-${comp.description}
                                    </aui:option>
                                </c:forEach>
                            </aui:select>
                        </fieldset>
                    </aui:form>
                </c:if>
                <c:if test="<%=showInput == true %>">
                    <c:forEach var="comp" items="<%= clients %>">
                        <% CompClientApplication comp = (CompClientApplication) pageContext.getAttribute("comp");
                        if(comp.getClientId() == userClientId){ %>
                            <div class="comp-title"> ${comp.description} </div>
                        <%} %>
                    </c:forEach>

                </c:if>
            </c:if>

            <c:if test="<%=clients.size() == 1 %>">
                <div class="comp-title"><%= clients.get(0).getDescription() %></div>
            </c:if>

            <c:if test="<%=!Validator.isBlank(logoURL)%>">
                <div class="user-comp-logo">
                    <img src="<%= logoURL %>">
                </div>
            </c:if>
		</div>
	</div>
</div>