<%@ include file="/init.jsp" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.model.Comunicado"%>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.service.ComunicadoLocalServiceUtil"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.List" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.model.Comp" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.service.CompLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="java.util.ResourceBundle" %>
<%
ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

String codCom = ParamUtil.getString(request,"codigoComunicado", "");
Comunicado com=null;
Comp comp =null;
long idComp=0;
List<Comunicado> listaCom=ComunicadoLocalServiceUtil.getAllComunicadosByCodigo(codigo);
long idEstado=0;
if(listaCom.size()>=1){
    com=listaCom.get(0);
    idComp=com.getCompId();
    comp=CompLocalServiceUtil.fetchComp(idComp);
    idEstado=com.getEstado();
}
%>
<c:if test="<%=Validator.isNull(com)%>">
    <%@ include file="/error.jsp" %>
</c:if>
<c:if test="<%=Validator.isNotNull(comp)%>">
<liferay-portlet:actionURL name="/aditional/save" var="saveAditionalURL" />
<aui:form name="frmComunicado" action="<%=saveAditionalURL.toString()%>" method="post" enctype="multipart/form-data">
    <aui:input type="hidden" value="<%=codCom%>" name="codigoComunicado" >
    <label for="estado">Estado</label>
    <input type="text" disabled  id="estado"/>
    <aui:input type="text" name="asuntoAdicional">
        <aui:validator name="required" errorMessage="company.edit.validator.required" />
    </aui:input>
    <aui:input type="textarea" name="asuntoAdicional">
        <aui:validator name="required" errorMessage="company.edit.validator.required" />
    </aui:input>
</aui:form>


<script>
var array=[]
array.push('<%=LanguageUtil.get(bundle,"estado.datatable.filtro.activa.pendiente")%>');
array.push('<%=LanguageUtil.get(bundle,"estado.datatable.filtro.activa.admision")%>');
array.push('<%=LanguageUtil.get(bundle,"estado.datatable.filtro.activa.investigacion")%>');
array.push('<%=LanguageUtil.get(bundle,"estado.datatable.filtro.activa.finalizacion")%>');

$(document).ready( function () {
    idEstado="<%=idEstado%>"
    $('#estado').val(array[idEstado]);

});
</script>
</c:if>