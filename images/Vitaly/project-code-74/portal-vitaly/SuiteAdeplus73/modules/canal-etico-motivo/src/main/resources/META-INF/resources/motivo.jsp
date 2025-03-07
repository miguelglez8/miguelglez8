<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Motivo" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.MotivoLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.UserCompanyLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.motivo.web.constants.MotivoPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ include file="init.jsp" %>

<%
    long motivoEditId = ParamUtil.getLong(request, MotivoPortletKeys.MOTIVO_ID_EDIT, 0);
    long companyIdFromUser = UserCompanyLocalServiceUtil.getCompanyIdFromUser(themeDisplay.getUserId());

    Motivo motivo = null;

    if(motivoEditId > 0) {
        motivo = MotivoLocalServiceUtil.fetchMotivo(motivoEditId);
    }

    //System.out.println("Motivos finalizacion SAVE: " + user.getFullName() + ", compId: " + companyIdFromUser +", isOmniadmin " + permissionChecker.isOmniadmin());

%>


<liferay-portlet:actionURL name="/motivo/save" var="saveURL" />
<aui:form  name="edit_form" action="<%=saveURL.toString()%>" method="post">

    <aui:input name="<%=MotivoPortletKeys.MOTIVO_ID_EDIT%>" value="<%=motivoEditId%>" type="hidden" />
    <aui:input name="<%=MotivoPortletKeys.COMPANY_ID%>" value="<%=companyIdFromUser%>" type="hidden" />

    <div class="content">
        <div class="titulo-seccion">
            <h2><liferay-ui:message key="motivo.edit.title"/></h2>
        </div>
        <div class="formulario">
            <div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
                <div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">
                    <div class="row">
                        <div class="col-sm-5">
                            <aui:input name="<%=MotivoPortletKeys.NAME%>" type="text" label="motivo.edit.name" value="<%=Validator.isNotNull(motivo)?motivo.getNombre():""%>" >
                            </aui:input>
                        </div>
                        <div class="col-sm-4">
                            <aui:input name="<%=MotivoPortletKeys.OBSERVATIONS%>" type="text" label="motivo.edit.observations" value="<%=Validator.isNotNull(motivo)?motivo.getObservaciones():""%>" >
                            </aui:input>
                        </div>
                        <div class="col-sm-3">
                            <fieldset class="input-group">
                                <legend for="admin" class="control-label"><liferay-ui:message key="motivo.edit.active"/></legend>
                                <div class="custom-control custom-radio d-inline-block mr-3 mt-2">
                                    <div class="custom-control custom-radio d-inline-block mr-3 mt-2">
                                        <input class="custom-control-input" type="radio" id="active" name="<portlet:namespace/>active"
                                               value="active" <%=Validator.isNotNull(motivo)?motivo.getActivo()?"checked":"":"checked" %>>
                                        <label class="custom-control-label" for="active"><liferay-ui:message key="motivo.view.yes"/></label>

                                    </div>
                                    <div class="custom-control custom-radio d-inline-block mt-2">
                                        <input class="custom-control-input" type="radio" id="noactive" name="<portlet:namespace/>active"
                                               value="noactive" <%=Validator.isNotNull(motivo)?motivo.getActivo()?"":"checked":"" %>>
                                        <label class="custom-control-label" for="noactive"><liferay-ui:message key="motivo.view.no"/></label>
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
            <aui:button onClick="<%= cancelURL.toString() %>" value="motivo.edit.cancel"></aui:button>

        </aui:button-row>
    </div>

</aui:form>