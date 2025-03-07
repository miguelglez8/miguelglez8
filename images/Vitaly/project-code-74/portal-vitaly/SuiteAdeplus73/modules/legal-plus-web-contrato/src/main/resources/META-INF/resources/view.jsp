<%@ page import="com.legalplus.liferay.portlet.web.contrato.constants.WebContratoPortletKeys" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.ContratoCompany" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense" %>
<%@ page import="com.legalplus.liferay.portlet.commons.web.role.LegalplusRoleUtil" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.CCAA" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.CNAES" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.model.User" %>
<%@ page import="java.util.List" %>

<%@ include file="/init.jsp" %>
<%@ include file="contrato-info.jspf" %>

<%

List<ApplicationLicense> licenseList = (List<ApplicationLicense>) request.getAttribute(WebContratoPortletKeys.LICENSE_LIST);
List<ConsultorCompany> consultants = (List<ConsultorCompany>) request.getAttribute(WebContratoPortletKeys.USER_LIST);

ApplicationLicense license = (ApplicationLicense) request.getAttribute(WebContratoPortletKeys.LICENCE);
ContratoCompany contract = (ContratoCompany) request.getAttribute(WebContratoPortletKeys.CONTRACT);

List<CCAA> ccaaList = (List<CCAA>) request.getAttribute(WebContratoPortletKeys.CCAA);
List<CNAES> cnaesList = (List<CNAES>) request.getAttribute(WebContratoPortletKeys.CNAES_LIST);

boolean hasRole = LegalplusRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                    || LegalplusRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser());

%>

<c:choose>
    <c:when test="<%= hasRole %>">
        <%@include file="contrato-consultor.jsp" %>
    </c:when>
    <c:otherwise>
        <%@include file="contrato-cliente.jsp" %>
    </c:otherwise>
</c:choose>