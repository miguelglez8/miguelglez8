<%@ page import="com.adeplus.liferay.portlet.commons.web.permission.AdeplusPermissionCompanyUtil" %>
<%@ page import="com.adeplus.liferay.portlet.company.configuration.web.constants.AdeplusCompanyConfigurationPortletKeys" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Application" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Comp" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.UserCompany" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil" %>
<%@ page import="com.liferay.document.library.kernel.model.DLFileEntry" %>
<%@ page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.List" %>
<%@ include file="init.jsp" %>
<%@ include file="js/main-js.jsp" %>
<%
	long companyEditId = (long) request.getAttribute(AdeplusCompanyConfigurationPortletKeys.USER_COMPANY_ID_EDIT);

	UserCompany userCompany = UserCompanyLocalServiceUtil.getUserDefaultCompany(themeDisplay.getUserId());
	if(Validator.isNotNull(userCompany)){
		companyEditId = userCompany.getCompId();
	}



	Comp comp = CompLocalServiceUtil.getComp(companyEditId);
	List<Application> appList = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

	String fileName = "";
	String url = "";
%>

<c:if test="<%=companyEditId == 0%>">
	<h2><liferay-ui:message key="company.view.configuration"/></h2>
	<p><liferay-ui:message key="company.view.no.company"/></p>
</c:if>
<c:if test="<%=companyEditId > 0 && (Validator.isNull(userCompany) || !userCompany.isAdmin())%>">
	<h2><liferay-ui:message key="company.view.configuration"/></h2>
	<p><liferay-ui:message key="company.view.no.admin"/></p>
</c:if>


<!-- START : PLANTILLA DE HTML-->
        <span class="hide" id="_div_Plantilla">
        <div class="  border border-secondary   mt-5 ml-0 mb-5" >
            <div class="row mr-0 ml-1 mt-3">
                <div class="col-12">
                   <div id="spn#CLIENTE#-#CONTRATO#">
                        <span id="spnCliente#CLIENTE#" class="label label-primary">#CLIENTE#</span> - <span id="spnContrato#CONTRATO#" class="label label-warning">#CONTRATO#</span> - <span id="spnNombre#NOMBRE#" class="label label-success">#NOMBRE#</span>
                    </div>
                    <div class="hide" id="inpt#CLIENTE#-#CONTRATO#">
                    <input id="inptCliente#CLIENTE#" class="label label-primary" value="#CLIENTE#"></input> - <input id="inptContrato#CONTRATO#" class="label label-warning" value="#CONTRATO#"></input> - <input id="inptNombre#CLIENTE#-#CONTRATO#" class="label label-success" value="#NOMBRE#"></input>
                    </div>
                </div>
            </div>
            <div class="row mr-10 ml-5 mt-3 mb-3">
                    #APPS#
            </div>
        </div>
        </span>
<!-- END : PLANTILLA DE HTML-->


<c:if test="<%=companyEditId > 0 && Validator.isNotNull(userCompany) && userCompany.isAdmin()%>">

    <liferay-portlet:resourceURL  copyCurrentRenderParameters="false" id="getAllClients" var="getAllClientsURL" />
    <liferay-portlet:resourceURL  copyCurrentRenderParameters="false" id="getAllApplications" var="getAllApplicationsURL" />
	<liferay-portlet:actionURL var="saveCompURL" name="saveCompanyConfiguration" />

	<aui:form  name="edit_form" action="<%=saveCompURL.toString()%>" method="post" enctype="multipart/form-data">

		<aui:input name="<%=AdeplusCompanyConfigurationPortletKeys.COMPANY_ID_EDIT%>" value="<%=companyEditId%>" type="hidden" />

		<div class="content">
			<div class="titulo-seccion">
				<h2><liferay-ui:message key="company.view.configuration"/></h2>
			</div>
			<div class="formulario">
				<div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
					<div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">
						<div class="row">
							<div class="form-group col-md-4 required">
								<c:if test="<%=comp.getLogo() > 0%>">
									<%
										DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(comp.getLogo()));
										if(Validator.isNotNull(dlFileEntry)) {
											fileName = dlFileEntry.getFileName();
											url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + "/" +
													dlFileEntry.getFolderId() + "/" + dlFileEntry.getTitle();
										}
									%>
									<div id="img-thumbnail"><img src="<%=url%>" class="img-thumbnail" /></div>
								</c:if>
							</div>
							<div class="col-11 col-md-7 pr-0" onclick="addLogoSelect()">
								<fieldset class="input-group">
										<aui:input name="logo" label="company.view.logo.select" type="file" value="<%=fileName%>">
											<aui:validator name="acceptFiles">'jpg,png,svg'</aui:validator>
										</aui:input>
										
										<aui:input name="selectedLogo" id="selectedLogo" type="hidden" value="false">
										</aui:input>
								</fieldset>
							</div>
							<div class="form-group col-1 p-0 d-flex align-items-start label-margin delete-margin-top" id="deleteAddLogo">
								<span class="btn-ico delete-ico" onclick="deleteLogoSelect()"></span>
							</div>

						</div>

						<div class="row">
							<div class="form-group col-md-4">
								<aui:input name="<%=AdeplusCompanyConfigurationPortletKeys.COMPANY_CIF%>" type="text" label="company.view.cif" value="<%=Validator.isNotNull(comp)?comp.getCif():""%>" disabled="true" />
							</div>
							<div class="form-group col-md-8">
								<aui:input name="<%=AdeplusCompanyConfigurationPortletKeys.COMPANY_NAME%>" type="text" label="company.view.name" value="<%=Validator.isNotNull(comp)?comp.getName():""%>" disabled="true" />
							</div>
						</div>

						<div class="row">
							<div class="col-md-12">
								<label class="control-label disabled" for="<%=AdeplusCompanyConfigurationPortletKeys.COMPANY_DESCRIPTION%>">
									<liferay-ui:message key="company.view.descripcion" />
								</label>
								<liferay-ui:input-localized
									id="<%=AdeplusCompanyConfigurationPortletKeys.COMPANY_DESCRIPTION%>"
									name="<%=AdeplusCompanyConfigurationPortletKeys.COMPANY_DESCRIPTION%>"
									xml="<%=Validator.isNotNull(comp) ? comp.getDescription() : ""%>"
									type="editor" maxLength="500">
								</liferay-ui:input-localized>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12">
								<label class="control-label disabled" for="<%=AdeplusCompanyConfigurationPortletKeys.COMPANY_OBSERVATION%>">
									<liferay-ui:message key="company.view.observations" />
								</label>
								<liferay-ui:input-localized
									id="<%=AdeplusCompanyConfigurationPortletKeys.COMPANY_OBSERVATION%>"
									name="<%=AdeplusCompanyConfigurationPortletKeys.COMPANY_OBSERVATION%>"
									xml="<%=Validator.isNotNull(comp) ? comp.getObservations() : ""%>"
									type="editor" maxLength="500">
								</liferay-ui:input-localized>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12">
								<label class="control-label disabled" for="<%=AdeplusCompanyConfigurationPortletKeys.COMPANY_AGREEMENT%>">
									<liferay-ui:message key="company.view.agreement" />
								</label>
								<liferay-ui:input-localized
									id="<%=AdeplusCompanyConfigurationPortletKeys.COMPANY_AGREEMENT%>"
									name="<%=AdeplusCompanyConfigurationPortletKeys.COMPANY_AGREEMENT%>"
									xml="<%=Validator.isNotNull(comp) ? comp.getAgreement() : ""%>"
									type="editor" maxLength="500">
								</liferay-ui:input-localized>
							</div>
						</div>
					</div>

					<div class="form-content form-aplicaciones">
                        <span id="contenedorContratos">
						    xxx
                        </span>
				</div>
			</div>
		</div>

		<div class="button-holder d-flex justify-content-center my-4">
			<aui:button-row>
				<aui:button type="submit" />
			</aui:button-row>
		</div>

	</aui:form>

	<script>
	    var arrApps = [];
        var js_cliente = null;
        var js_update = null;
        var deleteClientContract = false;
        var updateClientContract = false;
        $(document).ready(function(){
            js_cliente = [];
            js_update = [];
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if (this.readyState == 4 && this.status == 200){
                    //console.log(this.responseText);
                    try{
                        arrApps = JSON.parse(this.responseText);
                        getAllClients(js_cliente);
                    }catch(e){
                        console.error("xhr.onreadystatechange :" + e);
                    }
                }
            };
            xhr.open("GET", "<%=getAllApplicationsURL%>", true);
            xhr.send(null);

            $('#<portlet:namespace />edit_form').submit(function(e){
                e.preventDefault(e);
                $("#<portlet:namespace/>jsonClientContractApps").val(JSON.stringify(js_cliente));

            })
        });


	 function getAllClients(js_cliente){
            var idCompany="<%=companyEditId%>";
             $.ajax({
                type: 'POST',
                url: '<%= getAllClientsURL %>',
                data: { '<portlet:namespace/>companyEditId' : idCompany},
                success: function (data){

                    for(var x=0;x<data.length; x++){
                        js_cliente.push(data[x]);
                    }
                    console.log(js_cliente);
                   repaintClienteContrato ();



                }
             });
        }

         function repaintClienteContrato(){
                var html = $("#contenedorContratos").html("");
                var plantilla = $("#_div_Plantilla").html();
                var generateHTML = "", capa = "";

                for(var i = js_cliente.length-1; i >= 0 ; i--){ //lifo
                            generateHTML = plantilla.replaceAll("#CLIENTE#",js_cliente[i].cliente);
                            generateHTML = generateHTML.replaceAll("#CONTRATO#",js_cliente[i].contrato);
                            generateHTML = generateHTML.replaceAll("#NOMBRE#",js_cliente[i].nombre);
                            generateHTML = generateHTML.replace("#APPS#",composeApps(js_cliente[i].cliente, js_cliente[i].contrato, js_cliente[i].apps));
                            capa += generateHTML;
                 }

                $("#contenedorContratos").html(capa);
                //disabledCheckedApp();

            }
            function composeApps(_cliente, _contrato, _apps){
                    var id_cont = 0;
                    var isSelected = false;
                    var html = "", htmlLicenses = "";
                    var inputHTML = '<div class="col-#ANCHO#">'
                                    + ' <label for="app_' + _cliente + '_' +  _contrato +  '_#ID#">'
                                    + '<input type="checkbox" value="#VALUE#" #CHECKED# #DISABLED# onclick="addApp(' + "'" + _cliente + "','" +_contrato + "','#ID#'" +  ')" id="app_'+ _cliente + '_' +  _contrato + '_#ID#"/>&nbsp;#LABEL# </label>'
                                    + '#LICENCIAS#'
                                    + ' </div>';
                    var licencias = '<label for="' + 'license' + _cliente + '_' +  _contrato +  '_#ID_LICENSE#" class="mr-3">'
                                    + '<input class="clslicense" type="radio" name="' + 'license' + _cliente + '_' +  _contrato +  '_#IDAPP#" #CHECKED#'
                                    + ' id="' + 'license' + _cliente + '_' +  _contrato +  '_#ID_LICENSE#' + '" onclick="addRoleApp(' + "'" + _cliente + "','" + _contrato  + "','#IDAPP#','#ID_LICENSE#'" + ')">'
                                    + '</input>#NAME#</label>';
                    var app = null, linc = null;
                    var ancho = 4;
                    for(var i = 0; i < arrApps.data.length; i++){
                        app = arrApps.data[i];
                        isSelected = false;
                        if(_apps && _apps != undefined && _apps != null){
                            for(var x = 0; x < _apps.length; x++){
                                if(_apps[x].appId == app.appId){
                                    isSelected = true;
                                    break;
                                }
                            }
                        }
                        ancho = 4;
                        html += inputHTML.replace("#LABEL#", app.name).replaceAll("#ID#",app.appId).replaceAll("#VALUE#", app.appId).replace("#CHECKED#", (isSelected)? "checked" : "" ).replace("#DISABLED#", "disabled" );
                        if(app.licenses && app.licenses.length > 0){
                            ancho = 12;
                            htmlLicenses = "";
                            isSelectedRole = false;

                            html = html.replace("#LICENCIAS#", '<div id="roles_' + _cliente + "_" +  _contrato + "_" +  app.appId + '" class="ml-5">' + htmlLicenses + "</div>");
                        }else{
                            html = html.replace("#LICENCIAS#","");
                        }
                        html = html.replace("#ANCHO#",ancho);
                        id_cont++;
                    }
                    return html;
                }
	</script>
</c:if>
