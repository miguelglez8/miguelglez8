<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Comp" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.CompLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.VinculacionLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="com.liferay.document.library.kernel.model.DLFileEntry" %>
<%@ page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil" %>
<%@ page import="java.util.List" %>
<%@ include file="/init.jsp" %>

<%
    String compIdentification = PortalUtil.getOriginalServletRequest(request).getParameter("id");

    Comp comp = null;
    if(!"".equals(compIdentification)) {
        if (Validator.isNumber(compIdentification)) {
            comp = CompLocalServiceUtil.fetchComp(Long.parseLong(compIdentification));
        } else if(Validator.isNotNull(compIdentification)){
            comp = CompLocalServiceUtil.getCompanyByFriendlyURL(compIdentification);
        }
    }

    List<Vinculacion> vinculaciones = new ArrayList<>();
    long logo=0;
    if(Validator.isNotNull(comp)) {
        vinculaciones = VinculacionLocalServiceUtil.getAllVinculacionesActiveFromCompany(comp.getCompId());
        logo=comp.getLogo();
    }
%>

<section class="canal-etico-denuncia">
    <div class="container">
    	<div class="flex-cont">
    		<c:if test="<%=Validator.isNotNull(comp) && logo > 0%>">
					<%DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(comp.getLogo()));
					String url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + "/" +
							dlFileEntry.getFolderId() +  "/" +dlFileEntry.getTitle() ;
					%>				 
					<div class="logo-denuncia my-3"><img src="<%=url%>"/></div>
			</c:if>
			<div class="selector-idioma denuncia-idioma">
				<liferay-portlet:runtime portletName="com_liferay_site_navigation_language_web_portlet_SiteNavigationLanguagePortlet">
				</liferay-portlet:runtime>
			</div>
		</div>
        <div class="titulo-seccion">
            <h2><liferay-ui:message key="denuncia.view.step.title"/></h2>
           
        </div>

        <c:if test="<%=Validator.isNull(comp)%>">
            <div class="row">
                <div class="col-md-12">
                    <div class="text-center p-5 background-message">
                        <h3 class="p-2"><liferay-ui:message key="denuncia.view.company.validation.title"/></h3>
                        <p><liferay-ui:message key="denuncia.view.company.validation.message.1"/></p>
                        <p><liferay-ui:message key="denuncia.view.company.validation.message.2"/></p>
                        <p><liferay-ui:message key="denuncia.view.company.validation.message.3"/></p>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="<%=Validator.isNotNull(comp)%>">
            <div class="row d-flex justify-content-center">
                <div class="col-md-12">
                    <div class="wizard">
                        <div class="wizard-inner">
                            <div class="connecting-line"></div>
                            <ul class="nav nav-tabs" role="tablist">
                                <li role="presentation" class="active">
                                    <a href="#step1" data-toggle="tab" aria-controls="step1" role="tab" aria-expanded="true"><span class="round-tab">1</span> <i><liferay-ui:message key="denuncia.view.step.empresa"/></i></a>
                                </li>
                                <li role="presentation" class="disabled">
                                    <a href="#step2" data-toggle="tab" aria-controls="step2" role="tab" aria-expanded="false"><span class="round-tab">2</span> <i><liferay-ui:message key="denuncia.view.step.empleado"/></i></a>
                                </li>
                                <li role="presentation" class="disabled">
                                    <a href="#step3" data-toggle="tab" aria-controls="step3" role="tab"><span class="round-tab">3</span> <i><liferay-ui:message key="denuncia.view.step.denuncia"/></i></a>
                                </li>
                                <li role="presentation" class="disabled">
                                    <a href="#step4" data-toggle="tab" aria-controls="step4" role="tab"><span class="round-tab">4</span> <i><liferay-ui:message key="denuncia.view.step.detalle"/></i></a>
                                </li>
                                <li role="presentation" class="disabled">
                                    <a href="#step5" data-toggle="tab" aria-controls="step5" role="tab"><span class="round-tab">5</span> <i><liferay-ui:message key="denuncia.view.step.conf"/></i></a>
                                </li>
                                <li role="presentation" class="disabled">
                                    <a href="#step6" id="link_confirm" data-toggle="tab" aria-controls="step6" role="tab"><span class="round-tab">6</span> <i><liferay-ui:message key="denuncia.view.step.confirma"/></i></a>
                                </li>
                            </ul>
                        </div>

                        <portlet:actionURL name="/denuncia/save" var="saveURL" />
                        <aui:form  name="edit_form" action="<%=saveURL.toString()%>" method="post" enctype="multipart/form-data">
                            <div class="tab-content" id="main_form">

                                <div class="tab-pane active" role="tabpanel" id="step1">
                                    <%--<p class="text-center mb-5"><liferay-ui:message key="denuncia.view.step.empresa.help"/></p>--%>
                                    <div class="form-content">
                                        <h4 class="text-center"><liferay-ui:message key="denuncia.view.step.empresa.title"/></h4>
                                        <%@include file="empresa.jspf" %>
                                    </div>
                                    <ul class="button-holder d-flex justify-content-center my-4">
                                        <li><aui:button id="button-next-empresa" type="button" disabled="true" cssClass="btn btn-primary default-btn next-step" value="denuncia.view.step.next" /></li>
                                    </ul>

                                </div>

                                <div class="tab-pane" role="tabpanel" id="step2">
                                    <p class="text-center mb-5"><liferay-ui:message key="denuncia.view.step.empleado.help"/></p>
                                    <div class="form-content">
                                        <h4 class="text-center"><liferay-ui:message key="denuncia.view.step.empleado"/></h4>
                                        <p class="text-center"><liferay-ui:message key="denuncia.view.step.empleado.title"/></p>
                                        <%@include file="empleado.jspf" %>
                                    </div>
                                    <ul class="button-holder d-flex justify-content-center my-4">
                                        <li><aui:button type="button" cssClass="btn btn-primary default-btn prev-step mr-4" value="denuncia.view.step.back" /></li>
                                        <li><aui:button id="button-next-empleado" type="button" disabled="true"  cssClass="btn btn-primary default-btn next-step" value="denuncia.view.step.next" /></li>
                                    </ul>

                                </div>

                                <div class="tab-pane" role="tabpanel" id="step3">
                                    <p class="text-center mb-5"><liferay-ui:message key="denuncia.view.step.denuncia.help"/></p>
                                    <div class="form-content">
                                        <h4 class="text-center"><liferay-ui:message key="denuncia.view.step.denuncia.title"/></h4>
                                        <%@include file="denuncia.jspf" %>
                                    </div>
                                    <ul class="button-holder d-flex justify-content-center my-4">
                                        <li><aui:button type="button" cssClass="btn btn-primary default-btn prev-step mr-4" value="denuncia.view.step.back" /></li>
                                        <li><aui:button id="button-next-denuncia" type="button" disabled="true" cssClass="btn btn-primary default-btn next-step" value="denuncia.view.step.next" /></li>
                                    </ul>

                                </div>

                                <div class="tab-pane" role="tabpanel" id="step4">
                                    <p class="text-center mb-5"><liferay-ui:message key="denuncia.view.step.detalle.help"/></p>
                                    <div class="form-content">
                                        <h4 class="text-center"><liferay-ui:message key="denuncia.view.step.detalle.title"/></h4>
                                        <%@include file="detalle.jspf" %>
                                    </div>
                                    <ul class="button-holder d-flex justify-content-center my-4">
                                        <li><aui:button type="button" cssClass="btn btn-primary default-btn prev-step mr-4" value="denuncia.view.step.back" /></li>
                                        <li><aui:button type="button" id="send_acept" disabled="true" onclick="sendValuesDenuncias()" cssClass="btn btn-primary default-btn next-step" value="denuncia.view.step.next" /></li>
                                    </ul>

                                </div>

								<div class="tab-pane" role="tabpanel" id="step5">
                                    <p class="text-center mb-5"><liferay-ui:message key="denuncia.view.step.confirmation.help"/></p>
                                    <div class="form-content">
                                        <h4 class="text-center"><liferay-ui:message key="denuncia.view.step.confirmation.title"/></h4>
                                        <%@include file="terminos.jspf" %>
                                    </div>
                                    <ul class="button-holder d-flex justify-content-center my-4">
                                        <li><aui:button type="button" cssClass="btn btn-primary default-btn prev-step mr-4" value="denuncia.view.step.back" /></li>
                                        <li><aui:button id="button_confirm" type="button" disabled="true" cssClass="btn btn-primary default-btn next-step" value="denuncia.view.step.next" /></li>
                                    </ul>

                                </div>								
								
                                <div class="tab-pane" role="tabpanel" id="step6">
                                    <p class="text-center mb-5"><liferay-ui:message key="denuncia.view.step.confirma.help"/></p>
                                    <div class="form-content">
                                        <h4 class="text-center"><liferay-ui:message key="denuncia.view.step.confirma"/></h4>
                                        <%@include file="confirmar.jspf" %>
                                    </div>
                                    <ul class="button-holder d-flex justify-content-center my-4">
                                        <li><aui:button type="button" cssClass="btn btn-primary default-btn prev-step mr-4" value="denuncia.view.step.back" /></li>
                                        <li><aui:button type="submit"  cssClass="btn btn-primary default-btn next-step" value="denuncia.view.step.finish" /></li>
                                    </ul>

                                </div>

                                <div class="clearfix"></div>
                            </div>

                        </aui:form>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</section>

<%@ include file="js/view-js.jsp" %>