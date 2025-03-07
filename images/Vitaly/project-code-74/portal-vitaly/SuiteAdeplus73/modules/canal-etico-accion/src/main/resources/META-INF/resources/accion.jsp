<%@ page import="com.canal.etico.liferay.portlet.accion.web.constants.AccionPortletKeys" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Accion" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.AccionLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.UserCompanyLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.role.CanalEticoRoleUtil" %>
<%@ include file="init.jsp" %>

<%
    long accionEditId = ParamUtil.getLong(request, AccionPortletKeys.ACCION_ID_EDIT, 0);

    Accion accion = null;

    if(accionEditId > 0) {
        accion = AccionLocalServiceUtil.fetchAccion(accionEditId);
    }

    boolean companyAdminRole = CanalEticoRoleUtil.isCompanyAdminRole(themeDisplay.getCompanyId(), themeDisplay.getUser());
    long companyIdFromUser = UserCompanyLocalServiceUtil.getCompanyIdFromUser(themeDisplay.getUserId());
%>


<liferay-portlet:actionURL name="/accion/save" var="saveURL" />
<aui:form  name="edit_form" action="<%=saveURL.toString()%>" method="post">

    <aui:input name="<%=AccionPortletKeys.ACCION_ID_EDIT%>" value="<%=accionEditId%>" type="hidden" />
    <aui:input name="<%=AccionPortletKeys.COMPANY_ID%>" type="hidden" value="<%=companyIdFromUser%>"></aui:input>

    <div class="content">
        <div class="titulo-seccion">
            <h2><liferay-ui:message key="accion.edit.title"/></h2>
        </div>
        <div class="formulario">
            <div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
                <div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">

<%--                    <c:if test="<%=companyAdminRole%>">
                        <div class="row">
                            <div class="col-sm-12">
                                <fieldset class="input-group-inline">
                                    <legend><liferay-ui:message key="accion.edit.ambito"/></legend>
                                    <aui:input name="<%=AccionPortletKeys.GLOBAL%>" type="checkbox" label="accion.edit.global" helpMessage="accion.edit.global.help" checked="<%=Validator.isNotNull(accion)?accion.getCompId()==0:true%>"></aui:input>
                                </fieldset>
                            </div>
                        </div>
                    </c:if>--%>

                    <div class="row">
                        <div class="col-sm-5">
                            <aui:input name="<%=AccionPortletKeys.NAME%>" type="text" label="accion.edit.name" value="<%=Validator.isNotNull(accion)?accion.getNombre():""%>" >
                                <aui:validator name="required" errorMessage="accion.edit.validator.required" />
                            </aui:input>
                        </div>
                        <div class="col-sm-4">
                            <aui:select name="<%=AccionPortletKeys.ESTADO%>" label="accion.edit.estado">
                                <aui:option value="1" selected="<%=Validator.isNotNull(accion)?accion.getEstado() == 1?true:false:false%>" ><liferay-ui:message key="accion.view.denuncia.estado.en.proceso"/></aui:option>
                            </aui:select>
                        </div>
                        <div class="col-sm-3">
                            <fieldset class="input-group">
                                <legend for="admin" class="control-label"><liferay-ui:message key="accion.edit.active"/></legend>
                                <div class="custom-control custom-radio d-inline-block mr-3 mt-2">
                                    <div class="custom-control custom-radio d-inline-block mr-3 mt-2">
                                        <input class="custom-control-input" type="radio" id="active" name="<portlet:namespace/>active"
                                               value="active" <%=Validator.isNotNull(accion)?accion.getActivo()?"checked":"":"checked" %>>
                                        <label class="custom-control-label" for="active"><liferay-ui:message key="accion.view.yes"/></label>

                                    </div>
                                    <div class="custom-control custom-radio d-inline-block mt-2">
                                        <input class="custom-control-input" type="radio" id="noactive" name="<portlet:namespace/>active"
                                               value="noactive" <%=Validator.isNotNull(accion)?accion.getActivo()?"":"checked":"" %>>
                                        <label class="custom-control-label" for="noactive"><liferay-ui:message key="accion.view.no"/></label>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="button-holder d-flex justify-content-center my-4">
        <aui:button-row>
            <aui:button type="submit" />

            <portlet:actionURL name="cancel" var="cancelURL">
                <portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
            </portlet:actionURL>
            <aui:button onClick="<%= cancelURL.toString() %>" value="accion.edit.cancel"></aui:button>

        </aui:button-row>
    </div>

</aui:form>