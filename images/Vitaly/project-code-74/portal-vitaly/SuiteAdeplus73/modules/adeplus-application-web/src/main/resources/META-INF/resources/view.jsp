<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil" %>
<%@ page import="com.liferay.document.library.kernel.model.DLFileEntry" %>
<%@ page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Role" %>
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
	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

	List<Application> applicationList = (List<Application>) request.getAttribute(AdeplusApplicationsPortletKeys.APPLICATIONS_LIST);
%>

<portlet:renderURL var="addApplicationURL">
	<portlet:param name="mvcPath" value="/application.jsp"></portlet:param>
</portlet:renderURL>
<main class="content">
	<div class="formulario">
		<div class="title-group">
			<h2><liferay-ui:message key="application.view.title"/></h2>
			<aui:button-row>
				<aui:button onClick="<%= addApplicationURL.toString() %>" value="application.view.new.application" cssClass="btn btn-outline-primary btn-sm" primary="true"></aui:button>
			</aui:button-row>
		</div>

		<div class="row">
			<div class="col">
				<table id="table_applications" class="display" style="width:100%">
					<thead>
					<tr>
						<th><liferay-ui:message key="application.view.logo"/></th>
						<th><liferay-ui:message key="application.view.name"/></th>
						<th><liferay-ui:message key="application.view.url"/></th>
						<th><liferay-ui:message key="application.view.roles"/></th>
						<th><liferay-ui:message key="application.view.alias"/></th>
						<th><liferay-ui:message key="application.view.options"/></th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="<%=applicationList%>" var="application">
						<%
							Application applicationDetail = (Application) pageContext.getAttribute("application");
							List<Role> roleByApplicationId = RoleLocalServiceUtil.getRoleByApplicationId(applicationDetail.getApplicationId());
							String roleNames = "";
							String urlApp = Validator.isBlank(applicationDetail.getUrl())?"":"<a href=\"" + applicationDetail.getUrl() + "\" target=\"_blank\">" + applicationDetail.getUrl() +"</a>";
							for(int i =0 ; i < roleByApplicationId.size(); i++){
								roleNames += roleByApplicationId.get(i).getName();
								if(i == roleByApplicationId.size() -1){
									roleNames +=  ".";
								}else{
									roleNames +=  ", ";
								}
							}
						%>
						<liferay-portlet:renderURL varImpl="editURL">
							<portlet:param name="applicationEditId" value="<%=String.valueOf(applicationDetail.getApplicationId())%>" />
							<portlet:param name="mvcPath" value="/application.jsp" />
						</liferay-portlet:renderURL>
						<tr>
							<td>
								<c:if test="<%=Validator.isNotNull(applicationDetail) && applicationDetail.getLogo() > 0%>">
									<%
										DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(applicationDetail.getLogo());
										String url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + "/" +
												dlFileEntry.getFolderId() +  "/" +dlFileEntry.getTitle() ;
									%>
									<div><img src="<%=url%>" class="rounded" height="50px"/></div>
								</c:if>
							</td>
							<td>${application.name}</td>
							<td><%=urlApp%></td>
							<td><%=roleNames%></td>
							<td>${application.alias}</td>
							<td>
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
</main>

<script>
	var tabla = null;
	$(document).ready( function () {
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
