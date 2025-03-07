<%@ page import="com.liferay.portal.kernel.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="com.liferay.portal.kernel.util.PrefsPropsUtil" %>
<%@ page import="com.preving.liferay.portlet.user.report.web.constants.UserReportPortletKeys" %>
<%@ page import="com.preving.liferay.portlet.user.report.web.util.PrevingUserUtil" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.UserData" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ include file="init.jsp" %>

<script>
	Liferay.Loader.define._amd = Liferay.Loader.define.amd;
	Liferay.Loader.define.amd = false;
</script>

<script src= "<%=request.getContextPath()%>/js/jquery.dataTables.js"></script>
<script src= "<%=request.getContextPath()%>/js/dataTables.responsive.min.js"></script>
<script src= "<%=request.getContextPath()%>/js/dataTables.buttons.min.js"></script>
<script src= "<%=request.getContextPath()%>/js/buttons.flash.min.js"></script>
<script src= "<%=request.getContextPath()%>/js/jszip.min.js"></script>
<script src= "<%=request.getContextPath()%>/js/pdfmake.min.js"></script>
<script src= "<%=request.getContextPath()%>/js/vfs_fonts.js"></script>
<script src= "<%=request.getContextPath()%>/js/buttons.html5.min.js"></script>
<script src= "<%=request.getContextPath()%>/js/buttons.print.min.js"></script>
<script src= "<%=request.getContextPath()%>/js/Spanish.json"></script>

<script>
	Liferay.Loader.define.amd = Liferay.Loader.define._amd;
</script>

<%
	List<User> userList = UserLocalServiceUtil.getGroupUsers(themeDisplay.getScopeGroupId());

	try {
		User userCreator = UserLocalServiceUtil.getUser(themeDisplay.getScopeGroup().getCreatorUserId());
		userList.remove(userCreator);
	} catch (PortalException e) {
		e.printStackTrace();
	}

	userList.sort(Comparator.comparing(User::getModifiedDate).reversed());

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dateFormatSearch = new SimpleDateFormat("yyyyMMdd");

	String expandoUserNif = PrefsPropsUtil.getString(UserReportPortletKeys.PROPERTY_EXPANDO_USER_NIF);
	String expandoUserWorkCenter = PrefsPropsUtil.getString(UserReportPortletKeys.PROPERTY_EXPANDO_WORK_CENTER);
	String expandoUserSalary = PrefsPropsUtil.getString(UserReportPortletKeys.PROPERTY_EXPANDO_USER_SALARY);
	String expandoUserEndDate = PrefsPropsUtil.getString(UserReportPortletKeys.PROPERTY_EXPANDO_USER_END_DATE);

	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
%>

<div class="row">
	<div class="col pb-3">
		<label class="mr-2"><liferay-ui:message key="userreport.view.jobtitle"/></label>
		<input type="text" id="inpSearchJobtitle" onchange="tabla.draw();"/>
	</div>
</div>

<div class="row">
	<div class="col">
		<table id="table_id" class="display">
			<thead>
			<tr>
				<th><liferay-ui:message key="userreport.view.name"/></th>
				<th><liferay-ui:message key="userreport.view.genre"/></th>
				<th><liferay-ui:message key="userreport.view.nif"/></th>
				<th><liferay-ui:message key="userreport.view.email"/></th>
				<th><liferay-ui:message key="userreport.view.jobtitle"/></th>
				<th><liferay-ui:message key="userreport.view.work.center"/></th>
				<th><liferay-ui:message key="userreport.view.salary"/></th>
				<th><liferay-ui:message key="userreport.view.date.end"/></th>
			</tr>
			</thead>
			<tbody>
				<c:forEach items="<%=userList%>" var="userReport">
					<%
						User employee = (User) pageContext.getAttribute("userReport");
						UserData userData = null;
						List<UserData> list = UserDataLocalServiceUtil.findByGroupIdAndUserId(scopeGroupId, employee.getUserId());

						if(list.size()>0){
							userData = list.get(0);
						}
					%>
					<tr>
						<td><%=Validator.isNotNull(userData)?userData.getName() + " " + userData.getLastName():employee.getFullName()%></td>
						<td><liferay-ui:message key="<%=PrevingUserUtil.getGenreFromUser(themeDisplay.getScopeGroupId(), employee.getUserId())%>"/></td>
						<td><%=Validator.isNotNull(userData)?userData.getNif():PrevingUserUtil.getExpandoValueFromUser(employee, expandoUserNif)%></td>
						<td><%=Validator.isNotNull(userData)?userData.getEmail():employee.getEmailAddress()%></td>
						<td><%=Validator.isNotNull(userData)?Validator.isNotNull(userData.getJobTitle())?userData.getJobTitle():"":employee.getJobTitle()%></td>
						<td><%=Validator.isNotNull(userData)?Validator.isNotNull(userData.getWorkCenter())?userData.getWorkCenter():"":PrevingUserUtil.getExpandoValueFromUser(employee, expandoUserWorkCenter)%></td>
						<td><%=Validator.isNotNull(userData)?Validator.isNotNull(userData.getSalary())?userData.getSalary():"":PrevingUserUtil.getExpandoValueFromUser(employee, expandoUserSalary)%></td>
						<td data-sort="<%=Validator.isNotNull(userData)?Validator.isNotNull(userData.getDeleteDate())?dateFormatSearch.format(userData.getCreateDate()):"":PrevingUserUtil.getExpandoValueFromUser(employee, expandoUserEndDate)%>">
							<%=Validator.isNotNull(userData)?Validator.isNotNull(userData.getDeleteDate())?dateFormat.format(userData.getDeleteDate()):"":PrevingUserUtil.getExpandoValueFromUser(employee, expandoUserEndDate)%>
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

		tabla = $('#table_id').DataTable( {
			dom: 'Bfrtip',
			responsive: true,
			pageLength: 15,
            buttons: [
                'csv', 'excel', 'print',
                {
                    text: 'PDF',
                    extend: 'pdfHtml5',
                    filename: 'informe_usuarios',
                    orientation: 'landscape',//landscape o portrait
                    pageSize: 'A4', //A3 , A5 , A6 , legal , letter
                    exportOptions: {
                        columns: ':visible',
                        search: 'applied',
                        order: 'applied'
                    },
                    customize: function (doc) {
                        doc.content.splice(0,1);
                        doc.pageMargins = [20,60,20,50];

                        doc['footer']=(function(page, pages) {
                            return {
                                columns: [{
									italics: true,
									fontSize: 8,
                                    alignment: 'center',
                                    text: ["<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.terms")%>"]
                                }],
                                margin: 0
                            }
                        });
                    }
                }],
			"language": {
				"sProcessing":     "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sProcessing")%>",
				"sLengthMenu":     "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sLengthMenu")%>",
				"sZeroRecords":    "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sZeroRecords")%>",
				"sEmptyTable":     "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sEmptyTable")%>",
				"sInfo":           "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sInfo")%>",
				"sInfoEmpty":      "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sInfoEmpty")%>",
				"sInfoFiltered":   "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sInfoFiltered")%>",
				"sInfoPostFix":    "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sInfoPostFix")%>",
				"sSearch":         "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sSearch")%>",
				"sUrl":            "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sUrl")%>",
				"sInfoThousands":  "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sInfoThousands")%>,",
				"sLoadingRecords": "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sLoadingRecords")%>",
				"oPaginate": {
					"sFirst":    "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sFirst")%>",
					"sLast":     "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sLast")%>",
					"sNext":     "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sNext")%>",
					"sPrevious": "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sPrevious")%>"
				},
				"oAria": {
					"sSortAscending":  "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sSortAscending")%>",
					"sSortDescending": "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.sSortDescending")%>"
				},
				"buttons": {
					"copy": "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.copy")%>",
					"colvis": "<%=LanguageUtil.get(bundle, "userreport.configuration.view.datatable.colvis")%>"
				}
			}
		} );

		$.fn.dataTable.ext.search.push(
				function( settings, data, dataIndex ) {
					//CONDICIÓN MULTIPLE
					var numCond = 0;
					//inpSearchActive
					var arrFields = ["inpSearchJobtitle"];
					var arrColumnas = [4];
					for(var i = 0; i < arrFields.length; i++){

/*						console.log("i: " + i);
						console.log("  arrFields[i]: " + arrFields[i]);
						console.log("  arrColumnas[i]: " + arrColumnas[i]);
						console.log("  value: " + document.getElementById(arrFields[i]).value.trim());
						console.log("  numCond: " + numCond);
						console.log("----------------" );*/

						if(document.getElementById(arrFields[i]).value.trim() != ""){
							numCond++;
						}
						if(document.getElementById(arrFields[i]).value.trim() != ""
								&& data[arrColumnas[i]].toUpperCase().indexOf(document.getElementById(arrFields[i]).value.trim().toUpperCase().trim()) != -1 ){
							numCond--;
						}

					}

					if(numCond == 0) {
						return true;
					}

					return false;
				}
		);


		$('#inpSearchJobtitle').keyup( function() {
			tabla.draw();
		} );

	} );
</script>
