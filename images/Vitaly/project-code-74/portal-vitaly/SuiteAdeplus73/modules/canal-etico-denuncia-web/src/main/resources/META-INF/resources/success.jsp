<%@ page import="com.canal.etico.liferay.portlet.denuncia.web.constants.DenunciaPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ include file="/init.jsp" %>

<%
    String compIdentification = ParamUtil.getString(request, DenunciaPortletKeys.COMPANY_IDENTIFICATION, "");
    String codigo = ParamUtil.getString(request, DenunciaPortletKeys.CODIGO, "codigo-autogenerado");
%>

<div class="titulo-seccion text-center mt-5 p-5 background-message">
    <h2><liferay-ui:message key="denuncia.success.title"/></h2>
    <p><liferay-ui:message key="denuncia.success.message"/></p>
    <p><liferay-ui:message key="denuncia.success.message.codigo" arguments="<%=new String[]{codigo}%>"/></p>
    <div class="text-center">
        <a class="btn btn-outline-primary btn-sm" href="/web/canal-etico-denuncias?id=<%=compIdentification%>">
            <liferay-ui:message key="denuncia.success.end"/>
        </a>
    </div>
</div>