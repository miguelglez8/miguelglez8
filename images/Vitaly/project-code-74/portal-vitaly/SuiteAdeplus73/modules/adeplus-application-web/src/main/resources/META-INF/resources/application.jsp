<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Role" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.ApplicationLicenseLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil" %>
<%@ page import="com.liferay.document.library.kernel.model.DLFileEntry" %>
<%@ page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil" %>
<%@ include file="init.jsp" %>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.5/js/dataTables.responsive.min.js">

<script>
    Liferay.Loader.define._amd = Liferay.Loader.define.amd;
    Liferay.Loader.define.amd = false;
</script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/responsive/2.5.0/js/dataTables.responsive.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.flash.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.html5.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.print.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json"></script>
<script>
    Liferay.Loader.define.amd = Liferay.Loader.define._amd;
</script>

<%
    long applicationEditId = ParamUtil.getLong(request, AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT, 0);

    Application app = null;

    if(applicationEditId > 0) {
        app = ApplicationLocalServiceUtil.getApplication(applicationEditId);
    }

    ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

    List<Role> roleByApplicationId = RoleLocalServiceUtil.getRoleByApplicationId(applicationEditId);
    List<ApplicationLicense> licenseByApplicationId = ApplicationLicenseLocalServiceUtil.findByApplicationId(applicationEditId);
%>
<liferay-portlet:actionURL name="saveApplication" var="saveApplicationURL"  />
<aui:form  name="edit_form" action="<%=saveApplicationURL.toString()%>" method="post" enctype="multipart/form-data">

    <aui:input name="<%=AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT%>" value="<%=applicationEditId%>" type="hidden" />

    <div class="content">
        <div class="titulo-seccion">
            <h2><liferay-ui:message key="application.edit.title"/></h2>
        </div>
        <div class="formulario">
            <div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
                <div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">

                    <div class="row">
                        <div class="form-group col-md-6 required">
                            <aui:input name="<%=AdeplusApplicationsPortletKeys.APPLICATION_NAME%>" type="text" label="application.view.name" value="<%=Validator.isNotNull(app)?app.getName():""%>">
                                <aui:validator name="required" errorMessage="application.edit.validator.required" />
                            </aui:input>
                        </div>
                        <div class="form-group col-md-6 required">
                            <aui:input name="<%=AdeplusApplicationsPortletKeys.APPLICATION_URL%>" type="text" label="application.view.url" value="<%=Validator.isNotNull(app)?app.getUrl():""%>" >
                                <aui:validator name="required" errorMessage="application.edit.validator.required" />
                            </aui:input>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12 required">
                            <aui:input name="<%=AdeplusApplicationsPortletKeys.APPLICATION_DESCRIPTION%>" type="text" label="application.view.description" value="<%=Validator.isNotNull(app)?app.getDescription():""%>" >
                            </aui:input>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-6">
                            <aui:input name="<%=AdeplusApplicationsPortletKeys.APPLICATION_ALIAS%>" type="text" label="application.view.alias" value="<%=Validator.isNotNull(app)?app.getAlias():""%>">
                                <%--<aui:validator name="required" errorMessage="application.edit.validator.required" />--%>
                            </aui:input>
                        </div>
                        <div class="form-group col-md-6">
                            <aui:input name="<%=AdeplusApplicationsPortletKeys.APPLICATION_CONFIGURATION%>" type="text" label="application.view.configuration" value="<%=Validator.isNotNull(app)?app.getConfiguration():""%>" >
                            </aui:input>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-3">
                            <aui:input name="<%=AdeplusApplicationsPortletKeys.LIMIT_ADMS%>" label="application.view.limitAdms" type="checkbox" value="<%=Validator.isNotNull(app)?app.getLimitAdmins():""%>" ></aui:input>
                        </div>
                        <div class="form-group col-md-3">
                            <span id="spnNumAdms" style="display:none;">
                                <aui:input name="<%=AdeplusApplicationsPortletKeys.LIMIT_NUM_ADMS%>" label="application.view.limitNumAdms" type="input" value="<%=Validator.isNotNull(app)?app.getLimitNumAdmins():""%>" ></aui:input>
                            </span>
                        </div>
                        <div class="form-group col-md-3">
                            <aui:input name="<%=AdeplusApplicationsPortletKeys.LIMIT_USERS%>" label="application.view.limitUsers" type="checkbox" value="<%=Validator.isNotNull(app)?app.getLimitUsers():""%>" ></aui:input>
                        </div>
                        <div class="form-group col-md-3">
                            <span id="spnNumUsers" style="display:none;">
                                <aui:input name="<%=AdeplusApplicationsPortletKeys.LIMIT_NUM_USERS%>" label="application.view.limitNumUsers" type="input" value="<%=Validator.isNotNull(app)?app.getLimitNumUsers():""%>" ></aui:input>
                            <span>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">

                            <h3><liferay-ui:message key="license.view.title"/></h3>

                            <portlet:renderURL var="addLicenseURL">
                                <portlet:param name="<%=AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT%>" value="<%=String.valueOf(applicationEditId)%>"></portlet:param>
                                <portlet:param name="mvcPath" value="/license.jsp"></portlet:param>
                            </portlet:renderURL>
                            <aui:button-row cssClass="button-holder m-2">
                                <aui:button onClick="<%= addLicenseURL.toString() %>" value="license.view.new" cssClass="btn btn-outline-primary btn-sm" primary="true"></aui:button>
                            </aui:button-row>

                            <table id="table_licenses" class="display" style="width:100%">
                                <thead>
                                <tr>
                                    <th><liferay-ui:message key="license.view.name"/></th>
                                    <th><liferay-ui:message key="license.view.key"/></th>
                                    <th><liferay-ui:message key="role.view.options"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="<%=licenseByApplicationId%>" var="license">
                                        <%
                                            ApplicationLicense license = (ApplicationLicense) pageContext.getAttribute("license");
                                        %>

                                        <tr>
                                            <td>${license.name}</td>
                                            <td>${license.permissionRoleKey}</td>
                                            <td>
                                                <liferay-portlet:renderURL varImpl="editLicenseURL">
                                                    <portlet:param name="<%=AdeplusApplicationsPortletKeys.LICENSE%>" value="<%=String.valueOf(license.getLicenseId())%>" />
                                                    <portlet:param name="<%=AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT%>" value="<%=String.valueOf(applicationEditId)%>" />
                                                    <portlet:param name="mvcPath" value="/license.jsp" />
                                                </liferay-portlet:renderURL>
                                                <a class="ico-acciones-tabla" href="<%=editLicenseURL.toString()%>">
                                                    <img src="<%=themeDisplay.getPathThemeImages()%>/ico_edit.png" alt="<liferay-ui:message key="edit" />" />
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>


                    <div class="row">
                        <div class="form-group col-md-12">

                            <h3><liferay-ui:message key="role.view.title"/></h3>

                            <portlet:renderURL var="addURL">
                                <portlet:param name="<%=AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT%>" value="<%=String.valueOf(applicationEditId)%>"></portlet:param>
                                <portlet:param name="mvcPath" value="/role.jsp"></portlet:param>
                            </portlet:renderURL>
                            <aui:button-row cssClass="button-holder m-2">
                                <aui:button onClick="<%= addURL.toString() %>" value="role.view.new" cssClass="btn btn-outline-primary btn-sm" primary="true"></aui:button>
                            </aui:button-row>

                            <table id="table_applications" class="display" style="width:100%">
                                <thead>
                                <tr>
                                    <th><liferay-ui:message key="role.view.name"/></th>
                                    <th><liferay-ui:message key="role.view.alias"/></th>
                                    <th><liferay-ui:message key="role.view.options"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="<%=roleByApplicationId%>" var="role">
                                        <%
                                            Role role = (Role) pageContext.getAttribute("role");
                                        %>

                                        <tr>
                                            <td>${role.name}</td>
                                            <td>${role.alias}</td>
                                            <td>
                                                <liferay-portlet:renderURL varImpl="editURL">
                                                    <portlet:param name="<%=AdeplusApplicationsPortletKeys.ROLE%>" value="<%=String.valueOf(role.getRoleId())%>" />
                                                    <portlet:param name="<%=AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT%>" value="<%=String.valueOf(applicationEditId)%>" />
                                                    <portlet:param name="mvcPath" value="/role.jsp" />
                                                </liferay-portlet:renderURL>
                                                <a class="ico-acciones-tabla" href="<%=editURL.toString()%>">
                                                    <img src="<%=themeDisplay.getPathThemeImages()%>/ico_edit.png" alt="<liferay-ui:message key="edit" />" />
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>

                <div class="form-content form-aplicaciones">
                    <legend class="fieldset-title-section"><liferay-ui:message key="application.edit.logo"/></legend>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <c:if test="<%=Validator.isNotNull(app) && app.getLogo() > 0%>">
                                <%
                                    DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(app.getLogo());
                                    String url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + "/" +
                                            dlFileEntry.getFolderId() +  "/" +dlFileEntry.getTitle() ;
                                %>
                                <div><img src="<%=url%>" class="img-thumbnail" /></div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-12">
                            <aui:input name="logo" label="application.edit.logo.select" type="file">
                                <aui:validator name="acceptFiles">'jpg,png'</aui:validator>
                            </aui:input>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="button-holder d-flex justify-content-center my-4">
         <aui:button-row>

             <portlet:actionURL name="cancel" var="cancelURL">
                 <portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
             </portlet:actionURL>
             <aui:button onClick="<%= cancelURL.toString() %>" value="application.edit.cancel" cssClass="btn btn-outline-primary mr-4" primary="true"></aui:button>

             <aui:button type="submit" cssClass="btn btn-primary" />

             <c:if test="<%=Validator.isNotNull(app)%>">
                <liferay-portlet:actionURL name="deleteApplication" var="deleteURL">
                    <portlet:param name="applicationId" value="<%=String.valueOf(app.getApplicationId())%>" />
                </liferay-portlet:actionURL>
                <aui:button onClick="<%= deleteURL.toString() %>" value="application.edit.delete"></aui:button>
            </c:if>
         </aui:button-row>
    </div>

</aui:form>

<script>
    var tabla = null;





    $(document).ready( function () {

    $("#<portlet:namespace/><%=AdeplusApplicationsPortletKeys.LIMIT_ADMS%>").on("change", function(e){
         if(e.target.checked){
            $("#spnNumAdms").hide();
         }else{
            $("#spnNumAdms").show();
         }
        $("#<portlet:namespace/><%=AdeplusApplicationsPortletKeys.LIMIT_NUM_ADMS%>").prop("disabled", e.target.checked );
    }).trigger("change");

    $("#<portlet:namespace/><%=AdeplusApplicationsPortletKeys.LIMIT_USERS%>").on("change", function(e){
        if(e.target.checked){
            $("#spnNumUsers").hide();
        }else{
            $("#spnNumUsers").show();
        }
        $("#<portlet:namespace/><%=AdeplusApplicationsPortletKeys.LIMIT_NUM_USERS%>").prop("disabled", e.target.checked );
    }).trigger("change");


        tabla = $('#table_applications').DataTable( {
            dom: 'Bfrtip',
            responsive: true,
            pageLength: 10,
            "language": {
                "sProcessing":     "<%=LanguageUtil.get(bundle, "application.view.datatable.sProcessing")%>",
                "sLengthMenu":     "<%=LanguageUtil.get(bundle, "application.view.datatable.sLengthMenu")%>",
                "sZeroRecords":    "<%=LanguageUtil.get(bundle, "application.view.datatable.sZeroRecords")%>",
                "sEmptyTable":     "<%=LanguageUtil.get(bundle, "application.view.datatable.sEmptyTable")%>",
                "sInfo":           "<%=LanguageUtil.get(bundle, "application.view.datatable.sInfo")%>",
                "sInfoEmpty":      "<%=LanguageUtil.get(bundle, "application.view.datatable.sInfoEmpty")%>",
                "sInfoFiltered":   "<%=LanguageUtil.get(bundle, "application.view.datatable.sInfoFiltered")%>",
                "sInfoPostFix":    "<%=LanguageUtil.get(bundle, "application.view.datatable.sInfoPostFix")%>",
                "sSearch":         "<%=LanguageUtil.get(bundle, "application.view.datatable.sSearch")%>",
                "sUrl":            "<%=LanguageUtil.get(bundle, "application.view.datatable.sUrl")%>",
                "sInfoThousands":  "<%=LanguageUtil.get(bundle, "application.view.datatable.sInfoThousands")%>,",
                "sLoadingRecords": "<%=LanguageUtil.get(bundle, "application.view.datatable.sLoadingRecords")%>",
                "oPaginate": {
                    "sFirst":    "<%=LanguageUtil.get(bundle, "application.view.datatable.sFirst")%>",
                    "sLast":     "<%=LanguageUtil.get(bundle, "application.view.datatable.sLast")%>",
                    "sNext":     "<%=LanguageUtil.get(bundle, "application.view.datatable.sNext")%>",
                    "sPrevious": "<%=LanguageUtil.get(bundle, "application.view.datatable.sPrevious")%>"
                },
                "oAria": {
                    "sSortAscending":  "<%=LanguageUtil.get(bundle, "application.view.datatable.sSortAscending")%>",
                    "sSortDescending": "<%=LanguageUtil.get(bundle, "application.view.datatable.sSortDescending")%>"
                },
                "buttons": {
                    "copy": "<%=LanguageUtil.get(bundle, "application.view.datatable.copy")%>",
                    "colvis": "<%=LanguageUtil.get(bundle, "application.view.datatable.colvis")%>"
                }
            }
        } );
    } );

</script>