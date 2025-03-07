<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Comp" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.CompLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.company.configuration.web.constants.CompanyConfigurationPortletKeys" %>
<%@ page import="com.liferay.document.library.kernel.model.DLFileEntry" %>
<%@ page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.UserCompanyLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.user.CanalEticoUserUtil" %>
<%@ include file="init.jsp" %>
<%@ include file="js/main-js.jsp" %>
<%

	long companyEditId = UserCompanyLocalServiceUtil.getCompanyIdFromUser(themeDisplay.getUserId());

	Comp companyFromUser = CanalEticoUserUtil.getUserDefaultCompanyInCanalEtico(themeDisplay.getUserId());

	if(Validator.isNotNull(companyFromUser)){
		companyEditId = companyFromUser.getCompId();
	}

	boolean hasPermission = CanalEticoUserUtil.isUserInDefaultCompPermission(user.getUserId());

	Comp comp = null;
	if(companyEditId > 0 && hasPermission) {
		comp = CompLocalServiceUtil.fetchComp(companyEditId);
	}

%>

<c:if test="<%=!hasPermission%>">
	<div class="formulario">
		<div class="title-group">
			<h2><liferay-ui:message key="company.view.configuration"/></h2>
			<p><liferay-ui:message key="company.view.no.company.permission"/></p>
		</div>
	</div>
</c:if>

<c:if test="<%=Validator.isNotNull(comp)%>">
	<liferay-portlet:actionURL name="/canal/configuration/save" var="saveCompURL" />
	<aui:form  name="edit_form" action="<%=saveCompURL.toString()%>" method="post" enctype="multipart/form-data">

		<aui:input name="<%=CompanyConfigurationPortletKeys.COMPANY_ID_EDIT%>" value="<%=companyEditId%>" type="hidden" />
		<aui:input name="aaa" value="<%=comp%>" type="hidden" />
		<div class="content">
			<div class="titulo-seccion">
				<h2><liferay-ui:message key="company.view.configuration"/></h2>
			</div>
			<div class="formulario">
				<div class="d-flex flex-nowrap flex-sm-column flex-lg-canal">
					<div class="form-content form-datos-canal mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">
						<div class="row">
							<div class="form-group col-md-4 required">
								<c:if test="<%=Validator.isNotNull(comp) && comp.getLogo() > 0%>">
									<%
										DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(comp.getLogo()));
										String url = "";
										if(Validator.isNotNull(dlFileEntry)) {
											url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + "/" +
													dlFileEntry.getFolderId() + "/" + dlFileEntry.getTitle();
										}
									%>
									<div><img src="<%=url%>" class="img-thumbnail" /></div>
								</c:if>
							</div>

							<div class="col-11 col-md-7 pr-0" onclick="addLogoEticoSelect()">
								<fieldset class="input-group">
										<aui:input name="logo" label="company.view.logo.select" type="file">
											<aui:validator name="acceptFiles">'jpg,png,svg'</aui:validator>
										</aui:input>

										<aui:input name="selectedLogoEtico" id="selectedLogoEtico" type="hidden" value="false">
										</aui:input>
								</fieldset>
							</div>
							<div class="form-group col-1 p-0 d-flex align-items-start label-margin" id="deleteAddLogoEtico">
								<span class="btn-ico delete-ico" onclick="deleteLogoSelectEtico()"></span>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-4">
								<aui:input name="<%=CompanyConfigurationPortletKeys.COMPANY_CIF%>" type="text" label="company.view.cif" value="<%=Validator.isNotNull(comp)?comp.getCif():""%>" disabled="true">
									<aui:validator name="required" errorMessage="company.error.required" />
								</aui:input>
							</div>
							<div class="form-group col-md-8">
								<aui:input name="<%=CompanyConfigurationPortletKeys.COMPANY_NAME%>" type="text" label="company.view.name" value="<%=Validator.isNotNull(comp)?comp.getName():""%>" disabled="true" >
									<aui:validator name="required" errorMessage="company.error.required" />
								</aui:input>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12">
								<aui:input name="<%=CompanyConfigurationPortletKeys.COMPANY_FRIENDLYURL%>" type="text" label="company.view.friendlyURL" helpMessage="company.view.friendlyURL.help" value="<%=Validator.isNotNull(comp)?comp.getFriendlyURL():""%>" >
									<aui:validator name="alphanum" errorMessage="company.error.alfanum"></aui:validator>
								</aui:input>
								<fieldset class="input-group-inline mt-3 mb-2">
									<legend><liferay-ui:message key="company.view.link"/></legend>
									<a href="<%=themeDisplay.getPortalURL()%>/web/canal-etico-denuncias/registro?id=<%=(Validator.isNotNull(comp)&&!"".equals(comp.getFriendlyURL()))?comp.getFriendlyURL():comp.getCompId()%>" target="_blank">
										<%=(Validator.isNotNull(comp)&&!"".equals(comp.getFriendlyURL()))?themeDisplay.getPortalURL()+"/web/canal-etico-denuncias/registro?id="+comp.getFriendlyURL():themeDisplay.getPortalURL()+"/web/canal-etico-denuncias/registro?id="+comp.getCompId()%>
									</a>
								</fieldset>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12">
								<label class="control-label disabled" for="<%=CompanyConfigurationPortletKeys.COMPANY_DESCRIPTION%>">
									<liferay-ui:message key="company.view.descripcion" />
								</label>
								<liferay-ui:input-localized
										id="<%=CompanyConfigurationPortletKeys.COMPANY_DESCRIPTION%>"
										name="<%=CompanyConfigurationPortletKeys.COMPANY_DESCRIPTION%>"
										xml="<%=Validator.isNotNull(comp) ? comp.getDescription() : ""%>"
										type="editor" maxLength="500">
								</liferay-ui:input-localized>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12">
								<label class="control-label disabled" for="<%=CompanyConfigurationPortletKeys.COMPANY_OBSERVATION%>">
									<liferay-ui:message key="company.view.observations" />
								</label>
								<liferay-ui:input-localized
										id="<%=CompanyConfigurationPortletKeys.COMPANY_OBSERVATION%>"
										name="<%=CompanyConfigurationPortletKeys.COMPANY_OBSERVATION%>"
										xml="<%=Validator.isNotNull(comp) ? comp.getObservations() : ""%>"
										type="editor" maxLength="500">
								</liferay-ui:input-localized>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12">
								<label class="control-label disabled" for="<%=CompanyConfigurationPortletKeys.COMPANY_AGREEMENT%>">
									<liferay-ui:message key="company.view.agreement" />
								</label>
								<liferay-ui:input-localized
										id="<%=CompanyConfigurationPortletKeys.COMPANY_AGREEMENT%>"
										name="<%=CompanyConfigurationPortletKeys.COMPANY_AGREEMENT%>"
										xml="<%=Validator.isNotNull(comp) ? comp.getAgreement() : ""%>"
										type="editor" maxLength="500">
								</liferay-ui:input-localized>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>

		<div class="button-holder d-flex justify-content-center my-4">
			<aui:button-row>
				<aui:button type="submit" />
			</aui:button-row>
		</div>

	</aui:form>
</c:if>
