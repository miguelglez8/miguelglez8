<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Audit" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.AuditLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.model.User" %>
<%@ include file="/init.jsp" %>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">

<script>
	Liferay.Loader.define._amd = Liferay.Loader.define.amd;
	Liferay.Loader.define.amd = false;
</script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
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
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat dateFormatSearch = new SimpleDateFormat("yyyyMMdd");

	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

	List<Audit> audits = AuditLocalServiceUtil.getImportAuditList();
%>

<div class="row">
	<div class="col">
		<table id="table_audit" class="display" style="width:100%">
			<thead>
			<tr>
				<th><liferay-ui:message key="audit.view.user"/></th>
				<th><liferay-ui:message key="audit.view.action"/></th>
				<th><liferay-ui:message key="audit.view.descripcion"/></th>
				<th><liferay-ui:message key="audit.view.date"/></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="<%=audits%>" var="audit">
				<%
					Audit audit = (Audit) pageContext.getAttribute("audit");
					User userAudit = UserLocalServiceUtil.fetchUser(audit.getUserId());
					String userStr = String.valueOf(audit.getUserId());
					if(Validator.isNotNull(userAudit)){
						userStr = userAudit.getFullName() +" ("+userAudit.getEmailAddress()+")";
					}
				%>
				<tr>
					<td><%=userStr%></td>
					<td>${audit.action}</td>
					<td>${audit.description}</td>
					<td data-sort="<%=Validator.isNotNull(audit.getDate())?dateFormatSearch.format(audit.getDate()):""%>">
						<%=Validator.isNotNull(audit.getDate())?dateFormat.format(audit.getDate()):""%>
					</td>
				</tr>
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
			buttons: [
                'csv', 'excel', 'pdf', 'print'
            ],
			responsive: true,
			order: [[ 3, "desc" ]],
			pageLength: 25,
			"language": {
				"sProcessing":     "<%=LanguageUtil.get(bundle, "audit.view.datatable.sProcessing")%>",
				"sLengthMenu":     "<%=LanguageUtil.get(bundle, "audit.view.datatable.sLengthMenu")%>",
				"sZeroRecords":    "<%=LanguageUtil.get(bundle, "audit.view.datatable.sZeroRecords")%>",
				"sEmptyTable":     "<%=LanguageUtil.get(bundle, "audit.view.datatable.sEmptyTable")%>",
				"sInfo":           "<%=LanguageUtil.get(bundle, "audit.view.datatable.sInfo")%>",
				"sInfoEmpty":      "<%=LanguageUtil.get(bundle, "audit.view.datatable.sInfoEmpty")%>",
				"sInfoFiltered":   "<%=LanguageUtil.get(bundle, "audit.view.datatable.sInfoFiltered")%>",
				"sInfoPostFix":    "<%=LanguageUtil.get(bundle, "audit.view.datatable.sInfoPostFix")%>",
				"sSearch":         "<%=LanguageUtil.get(bundle, "audit.view.datatable.sSearch")%>",
				"sUrl":            "<%=LanguageUtil.get(bundle, "audit.view.datatable.sUrl")%>",
				"sInfoThousands":  "<%=LanguageUtil.get(bundle, "audit.view.datatable.sInfoThousands")%>,",
				"sLoadingRecords": "<%=LanguageUtil.get(bundle, "audit.view.datatable.sLoadingRecords")%>",
				"oPaginate": {
					"sFirst":    "<%=LanguageUtil.get(bundle, "audit.view.datatable.sFirst")%>",
					"sLast":     "<%=LanguageUtil.get(bundle, "audit.view.datatable.sLast")%>",
					"sNext":     "<%=LanguageUtil.get(bundle, "audit.view.datatable.sNext")%>",
					"sPrevious": "<%=LanguageUtil.get(bundle, "audit.view.datatable.sPrevious")%>"
				},
				"oAria": {
					"sSortAscending":  "<%=LanguageUtil.get(bundle, "audit.view.datatable.sSortAscending")%>",
					"sSortDescending": "<%=LanguageUtil.get(bundle, "audit.view.datatable.sSortDescending")%>"
				},
				"buttons": {
					"copy": "<%=LanguageUtil.get(bundle, "audit.view.datatable.copy")%>",
					"colvis": "<%=LanguageUtil.get(bundle, "audit.view.datatable.colvis")%>"
				}
			}
		} );
		tabla.buttons( '.export' ).remove();
	} );

</script>
