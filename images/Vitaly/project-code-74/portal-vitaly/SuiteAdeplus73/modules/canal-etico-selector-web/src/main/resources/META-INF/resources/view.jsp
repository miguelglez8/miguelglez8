<%@ page import="canal.etico.selector.web.constants.CanalEticoSelectorWebPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ include file="/init.jsp" %>

<%
	long userCompId = ParamUtil.getLong(request, CanalEticoSelectorWebPortletKeys.USER_COMP_ID, 0);
	String userName = (String) request.getAttribute(CanalEticoSelectorWebPortletKeys.USER_NAME);
	String logoURL = (String) request.getAttribute(CanalEticoSelectorWebPortletKeys.USER_LOGO_URL);
	String compName = (String) request.getAttribute(CanalEticoSelectorWebPortletKeys.USER_COMP_NAME);
	
%>
<div class="row usuario-header">
	<div class="usuario-wrapper">
		<div class="m-2 usuario-nombre"><%=userName%></div>
		<img class="usuario-ico dropdown-toggle" src="<%=themeDisplay.getPathThemeImages() + "/ico_Usuario.svg"%>" alt="usuario"
			 id="dropdownMenuUsuario" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" />
		<!-- SELECTOR IDIOMA -->
		<div class="header-separador"></div>
		<div class="selector-idioma">
			<liferay-portlet:runtime portletName="com_liferay_site_navigation_language_web_portlet_SiteNavigationLanguagePortlet">
			</liferay-portlet:runtime>
		</div>
	</div>
	<div class="header-empresa">
		<div>
			<c:if test="<%=!Validator.isBlank(compName)%>">
				<div class="m-2 comp-title"><%=compName%></div>
			</c:if>
	
			<c:if test="<%=!Validator.isBlank(logoURL)%>">
				<div class="m-2">
					<div class="user-comp-logo"><img src="<%=logoURL%>"/></div>
				</div>
			</c:if>
		</div>
	</div>
</div>