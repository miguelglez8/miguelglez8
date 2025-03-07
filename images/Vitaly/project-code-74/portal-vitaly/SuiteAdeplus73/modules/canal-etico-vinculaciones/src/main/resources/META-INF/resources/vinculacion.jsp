<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.UserCompanyLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.VinculacionLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.vinculaciones.web.constants.VinculacionesPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.role.CanalEticoRoleUtil" %>
<%@ include file="init.jsp" %>

<%
    long vinculacionEditId = ParamUtil.getLong(request, VinculacionesPortletKeys.VINCULACION_ID_EDIT, 0);

    Vinculacion vinculacion = null;

    if(vinculacionEditId > 0) {
        vinculacion = VinculacionLocalServiceUtil.fetchVinculacion(vinculacionEditId);
    }

    boolean isAdmin = CanalEticoRoleUtil.isCompanyAdminRole(themeDisplay.getCompanyId(), themeDisplay.getUser());

    long companyIdFromUser = UserCompanyLocalServiceUtil.getCompanyIdFromUser(themeDisplay.getUserId());
%>


<liferay-portlet:actionURL name="/vinculacion/save" var="saveURL" />
<aui:form  name="edit_form" action="<%=saveURL.toString()%>" method="post">

    <aui:input name="<%=VinculacionesPortletKeys.VINCULACION_ID_EDIT%>" value="<%=vinculacionEditId%>" type="hidden" />
    <aui:input name="<%=VinculacionesPortletKeys.COMPANY_ID%>" type="hidden" value="<%=companyIdFromUser%>"></aui:input>

    <div class="content">
        <div class="titulo-seccion">
            <h2><liferay-ui:message key="vinculacion.edit.title"/></h2>
        </div>
        <div class="formulario">
            <div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
                <div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">

<%--                    <c:if test="<%=isAdmin%>">
                        <div class="row">
                            <div class="col-sm-12">
                                <fieldset class="input-group-inline">
                                    <legend><liferay-ui:message key="vinculacion.edit.ambito"/></legend>
                                    <aui:input name="<%=VinculacionesPortletKeys.GLOBAL%>" type="checkbox" label="vinculacion.edit.global" helpMessage="categoria.edit.global.help" checked="<%=Validator.isNotNull(vinculacion)?vinculacion.getCompId()==0:true%>"></aui:input>
                                </fieldset>
                            </div>
                        </div>
                    </c:if>--%>

                    <div class="row">
                        <div class="col-sm-12">
                            <aui:input name="<%=VinculacionesPortletKeys.NAME%>" type="text" label="vinculacion.edit.name" value="<%=Validator.isNotNull(vinculacion)?vinculacion.getNombre():""%>" >
                            </aui:input>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <fieldset class="input-group">
                                <legend for="admin" class="control-label"><liferay-ui:message key="vinculacion.edit.active"/></legend>
                                <div class="custom-control custom-radio d-inline-block mr-3 mt-2">
                                    <div class="custom-control custom-radio d-inline-block mr-3 mt-2">
                                        <input class="custom-control-input" type="radio" id="active" name="<portlet:namespace/>active"
                                               value="active" <%=Validator.isNotNull(vinculacion)?vinculacion.getActivo()?"checked":"":"checked" %>>
                                        <label class="custom-control-label" for="active"><liferay-ui:message key="vinculacion.view.yes"/></label>

                                    </div>
                                    <div class="custom-control custom-radio d-inline-block mt-2">
                                        <input class="custom-control-input" type="radio" id="noactive" name="<portlet:namespace/>active"
                                               value="noactive" <%=Validator.isNotNull(vinculacion)?vinculacion.getActivo()?"":"checked":"" %>>
                                        <label class="custom-control-label" for="noactive"><liferay-ui:message key="vinculacion.view.no"/></label>
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
            <aui:button onClick="<%= cancelURL.toString() %>" value="vinculacion.edit.cancel"></aui:button>

        </aui:button-row>
    </div>

</aui:form>