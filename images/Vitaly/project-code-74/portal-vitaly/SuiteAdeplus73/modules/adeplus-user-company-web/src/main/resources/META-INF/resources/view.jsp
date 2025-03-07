<%@ page import="com.adeplus.liferay.portlet.commons.web.permission.AdeplusPermissionUserUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Comp" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.UserCompany" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.model.User" %>
<%@ page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ include file="/init.jsp" %>

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
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dateFormatSearch = new SimpleDateFormat("yyyyMMdd");

	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
%>

<div class="row">
	<div class="col">
		<table id="table_audit" class="display" style="width:100%">
			<thead>
			<tr>
				<th><liferay-ui:message key="view.liferay.user"/></th>
				<th><liferay-ui:message key="view.liferay.nif"/></th>
				<th><liferay-ui:message key="view.liferay.name"/></th>
				<th><liferay-ui:message key="view.comp.cif"/></th>
				<th><liferay-ui:message key="view.comp.comp.name"/></th>
				<th><liferay-ui:message key="view.comp.comp.end"/></th>
				<th><liferay-ui:message key="view.comp.email"/></th>
				<th><liferay-ui:message key="view.comp.name"/></th>
				<th><liferay-ui:message key="view.comp.nif"/></th>
				<th><liferay-ui:message key="view.comp.phone"/></th>
				<th><liferay-ui:message key="view.comp.admin"/></th>
				<th><liferay-ui:message key="view.comp.active"/></th>
				<th><liferay-ui:message key="view.comp.end"/></th>
				<th><liferay-ui:message key="view.comp.apps"/></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="<%=UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS)%>" var="user">
				<%
					User userAdeplus = (User) pageContext.getAttribute("user");
					List<UserCompany> userCompanies = UserCompanyLocalServiceUtil.getActiveUserCompaniesFromUser(userAdeplus.getUserId());

					for(UserCompany usu:userCompanies){
						Comp comp = CompLocalServiceUtil.fetchComp(usu.getCompId());
				%>
					<tr>
						<td><%=userAdeplus.getEmailAddress()%></td>
						<td><%=userAdeplus.getScreenName()%></td>
						<td><%=userAdeplus.getFullName()%></td>
						<td><%=Validator.isNotNull(comp)?comp.getCif():""%></td>
						<td><%=Validator.isNotNull(comp)?comp.getName():""%></td>
						<td  data-sort="<%=Validator.isNotNull(comp) && Validator.isNotNull(comp.getDeleteDate())?dateFormatSearch.format(comp.getDeleteDate()):""%>">
							<%=Validator.isNotNull(comp) && Validator.isNotNull(comp.getDeleteDate())?dateFormat.format(comp.getDeleteDate()):""%>
						</td>
						<td><%=usu.getEmail()%></td>
						<td><%=usu.getName()%> <%=usu.getLastName()%></td>
						<td><%=usu.getNif()%></td>
						<td><%=usu.getPhone()%></td>
						<td><%=usu.isAdmin()%></td>
						<td><%=userAdeplus.isActive()%></td>
						<td data-sort="<%=Validator.isNotNull(usu.getUserEndDate())?dateFormatSearch.format(usu.getUserEndDate()):""%>">
							<%=Validator.isNotNull(usu.getUserEndDate())?dateFormat.format(usu.getUserEndDate()):""%>
						</td>
						<td><%=AdeplusPermissionUserUtil.getUserApplicationNames(usu.getUserId(), usu.getCompId())%></td>
					</tr>
				<%}%>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<script>
	var tabla = null;
	$(document).ready( function () {

		tabla = $('#table_audit').DataTable( {
			dom: 'Bfrtip',
			responsive: true,
			order: [[ 0, "asc" ]],
			pageLength: 25,
			"language": {
				"sProcessing":     "<%=LanguageUtil.get(bundle, "view.datatable.sProcessing")%>",
				"sLengthMenu":     "<%=LanguageUtil.get(bundle, "view.datatable.sLengthMenu")%>",
				"sZeroRecords":    "<%=LanguageUtil.get(bundle, "view.datatable.sZeroRecords")%>",
				"sEmptyTable":     "<%=LanguageUtil.get(bundle, "view.datatable.sEmptyTable")%>",
				"sInfo":           "<%=LanguageUtil.get(bundle, "view.datatable.sInfo")%>",
				"sInfoEmpty":      "<%=LanguageUtil.get(bundle, "view.datatable.sInfoEmpty")%>",
				"sInfoFiltered":   "<%=LanguageUtil.get(bundle, "view.datatable.sInfoFiltered")%>",
				"sInfoPostFix":    "<%=LanguageUtil.get(bundle, "view.datatable.sInfoPostFix")%>",
				"sSearch":         "<%=LanguageUtil.get(bundle, "view.datatable.sSearch")%>",
				"sUrl":            "<%=LanguageUtil.get(bundle, "view.datatable.sUrl")%>",
				"sInfoThousands":  "<%=LanguageUtil.get(bundle, "view.datatable.sInfoThousands")%>,",
				"sLoadingRecords": "<%=LanguageUtil.get(bundle, "view.datatable.sLoadingRecords")%>",
				"oPaginate": {
					"sFirst":    "<%=LanguageUtil.get(bundle, "view.datatable.sFirst")%>",
					"sLast":     "<%=LanguageUtil.get(bundle, "view.datatable.sLast")%>",
					"sNext":     "<%=LanguageUtil.get(bundle, "view.datatable.sNext")%>",
					"sPrevious": "<%=LanguageUtil.get(bundle, "view.datatable.sPrevious")%>"
				},
				"oAria": {
					"sSortAscending":  "<%=LanguageUtil.get(bundle, "view.datatable.sSortAscending")%>",
					"sSortDescending": "<%=LanguageUtil.get(bundle, "view.datatable.sSortDescending")%>"
				},
				"buttons": {
					"copy": "<%=LanguageUtil.get(bundle, "view.datatable.copy")%>",
					"colvis": "<%=LanguageUtil.get(bundle, "view.datatable.colvis")%>"
				}
			}
		} );
		tabla.buttons( '.export' ).remove();
	} );

</script>
