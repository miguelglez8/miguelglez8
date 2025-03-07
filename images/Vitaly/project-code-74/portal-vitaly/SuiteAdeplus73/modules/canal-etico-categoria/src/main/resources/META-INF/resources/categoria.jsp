<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Categoria" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.CategoriaLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.categoria.web.constants.CategoriasPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.role.CanalEticoRoleUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.UserCompanyLocalServiceUtil" %>
<%@ include file="init.jsp" %>

<%
    long categoriaEditId = ParamUtil.getLong(request, CategoriasPortletKeys.CATEGORIA_ID_EDIT, 0);

    Categoria categoria = null;

    if(categoriaEditId > 0) {
        categoria = CategoriaLocalServiceUtil.fetchCategoria(categoriaEditId);
    }

    boolean companyAdminRole = CanalEticoRoleUtil.isCompanyAdminRole(themeDisplay.getCompanyId(), themeDisplay.getUser());

    long companyIdFromUser = UserCompanyLocalServiceUtil.getCompanyIdFromUser(themeDisplay.getUserId());

    System.out.println("categoriaEditId view " + categoriaEditId);
%>

<liferay-portlet:actionURL name="/categoria/save" var="saveURL" />
<aui:form  name="edit_form" action="<%=saveURL.toString()%>" method="post">

    <aui:input name="<%=CategoriasPortletKeys.CATEGORIA_ID_EDIT%>" value="<%=categoriaEditId%>" type="hidden" />
    <aui:input name="<%=CategoriasPortletKeys.COMPANY_ID%>" type="hidden" value="<%=companyIdFromUser%>"></aui:input>

    <div class="content">
        <div class="titulo-seccion">
            <h2><liferay-ui:message key="categoria.edit.title"/></h2>
        </div>
        <div class="formulario">
            <div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
                <div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">

                    <div class="row">
                        <div class="col-sm-12 required">
                            <aui:input name="<%=CategoriasPortletKeys.NAME%>" type="text" label="categoria.edit.name" value="<%=Validator.isNotNull(categoria)?categoria.getNombre():""%>" >
                            </aui:input>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <aui:select name="<%=CategoriasPortletKeys.AFECTA%>" label="categoria.edit.afecta" >
                                <aui:option value="0" selected="<%=Validator.isNotNull(categoria)?"0".equals(categoria.getTipo())?true:false:true%>"><liferay-ui:message key="categoria.edit.denuncia"/></aui:option>
                                <aui:option value="1" selected="<%=Validator.isNotNull(categoria)?"1".equals(categoria.getTipo())?true:false:false%>"><liferay-ui:message key="categoria.edit.consulta"/></aui:option>
                                <aui:option value="2" selected="<%=Validator.isNotNull(categoria)?"2".equals(categoria.getTipo())?true:false:false%>"><liferay-ui:message key="categoria.edit.denuncia.consulta"/></aui:option>
                            </aui:select>
                        </div>
                        <div class="col-sm-3">
                            <fieldset class="input-group">
                                <legend for="admin" class="control-label"><liferay-ui:message key="categoria.edit.active"/></legend>
                                <div class="custom-control custom-radio d-inline-block mr-3 mt-2">
                                    <div class="custom-control custom-radio d-inline-block mr-3 mt-2">
                                        <input class="custom-control-input" type="radio" id="active" name="<portlet:namespace/>active"
                                               value="active" <%=Validator.isNotNull(categoria)?categoria.getActivo()?"checked":"":"checked" %>>
                                        <label class="custom-control-label" for="active"><liferay-ui:message key="categoria.view.yes"/></label>

                                    </div>
                                    <div class="custom-control custom-radio d-inline-block mt-2">
                                        <input class="custom-control-input" type="radio" id="noactive" name="<portlet:namespace/>active"
                                               value="noactive" <%=Validator.isNotNull(categoria)?categoria.getActivo()?"":"checked":"" %>>
                                        <label class="custom-control-label" for="noactive"><liferay-ui:message key="categoria.view.no"/></label>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                        <div class="col-sm-3 required">
                            <aui:input name="<%=CategoriasPortletKeys.CODIGO%>" type="text" label="categoria.edit.codigo" value="<%=Validator.isNotNull(categoria)?categoria.getCodigo():""%>" >
                                <aui:validator name="required" errorMessage="categoria.edit.validator.required" />
                            </aui:input>
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
            <aui:button onClick="<%= cancelURL.toString() %>" value="categoria.edit.cancel"></aui:button>

        </aui:button-row>
    </div>

</aui:form>