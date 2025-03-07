<%@ page import="com.preving.liferay.portlet.timesheet.report.web.util.PrevingSignUtil" %>
<%@ page import="com.preving.liferay.portlet.timesheet.report.web.bean.PrevingSign" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.Sign" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.Activity" %>
<%@ page import="java.util.*" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.preving.liferay.portlet.timesheet.report.web.constants.TimesheetReportPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.model.User" %>
<%@ page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.WorkCenters" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.service.WorkCentersLocalServiceUtil" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.UserData" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.CompanyConf" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil" %>
<%@ include file="/init.jsp" %>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.5/js/dataTables.responsive.min.js">
<script>
	Liferay.Loader.define._amd = Liferay.Loader.define.amd;
	Liferay.Loader.define.amd = false;
</script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="https://nightly.datatables.net/responsive/js/dataTables.responsive.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.flash.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.html5.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.print.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json"></script>

<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

<script>
	Liferay.Loader.define.amd = Liferay.Loader.define._amd;
</script>

<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dateFormatId = new SimpleDateFormat("ddMMyyyy");
	SimpleDateFormat dateFormatHour = new SimpleDateFormat("HH:mm");

/*	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MONTH, -1);
	Calendar finish = Calendar.getInstance();*/

	List<PrevingSign> signsBetwenDates = (List<PrevingSign>) request.getAttribute(TimesheetReportPortletKeys.SIGN_LIST);

	String startDateStr = (String) request.getAttribute(TimesheetReportPortletKeys.START_DATE);
	String endDateStr = (String) request.getAttribute(TimesheetReportPortletKeys.END_DATE);

	Calendar calStart = Calendar.getInstance();
	calStart.add(Calendar.MONTH, -1);

	Calendar calEnd = Calendar.getInstance();

	List<Activity> activityList = ActivityLocalServiceUtil.getActivitiesFromGroup(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());

	Map<Long, Activity> map = new HashMap<Long, Activity>();

	for(Activity act:activityList){
		map.put(act.getActivityId(),act);
	}

	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());


	List<UserData> listUserData = null;
	User userTable = null;
	UserData userData = null;
	String strWorkCenter = "";
	WorkCenters wk = null;
	PrevingSign sign = null;

	 boolean hasWorkCenters = false;

        CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCompanyIdAndGroupId(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());


            List<WorkCenters> wcRes = new ArrayList<WorkCenters>();
            Calendar hoy = Calendar.getInstance();
            hoy.set(Calendar.HOUR,0);
            hoy.set(Calendar.MINUTE,0);
            if(Validator.isNotNull(companyConf) && companyConf.isIsWorkCenters()){
                hasWorkCenters = true;
                List<WorkCenters> wc = WorkCentersLocalServiceUtil.getAllWorkCentersByCompanyId(companyConf.getCompanyConfId());
                for(WorkCenters w : wc){
                    if(Validator.isNull(w.getEndDate()) || w.getEndDate().getTime() >= hoy.getTime().getTime()){
                        wcRes.add(w);
                    }
                }

                Collections.sort(wcRes, Comparator.comparing(WorkCenters::getName,String.CASE_INSENSITIVE_ORDER));
            }

%>

<liferay-portlet:actionURL name="search" var="searchURL"  />
<aui:form name="search_form" action="<%=searchURL.toString() %>" method="post">
	<div class="row">
		<div class="col-sm-2">
			<aui:fieldset label="timesheetreport.view.start">
				<liferay-ui:input-date name="startDate"
									   yearValue="<%=calStart.get(Calendar.YEAR)%>"
									   monthValue="<%=calStart.get(Calendar.MONTH)%>"
									   dayValue="<%=calStart.get(Calendar.DAY_OF_MONTH)%>" />
			</aui:fieldset>
		</div>
		<div class="col-sm-2">
			<aui:fieldset label="timesheetreport.view.end">
				<liferay-ui:input-date name="endDate"
									   yearValue="<%=calEnd.get(Calendar.YEAR)%>"
									   monthValue="<%=calEnd.get(Calendar.MONTH)%>"
									   dayValue="<%=calEnd.get(Calendar.DAY_OF_MONTH)%>" />
			</aui:fieldset>
		</div>
		<div class="col-sm-2">

		     <c:if test="<%= hasWorkCenters %>">
		        <aui:fieldset label="timesheetreport.view.workCenter">
                                <select name="cmbWorkCenterSelect" id="cmbWorkCenterSelect" >
                                    <option value="0"></option>
                                    <c:forEach items="<%=wcRes%>" var="w">
                                        <option value="${w.workCenterId}" > ${w.name}</option>
                                    </c:forEach>
                                </select>


                </aui:fieldset>
            </c:if>

            <aui:input type="hidden" name="cmbWorkCenter" value="0"/>

        </div>
		<div class="col-sm-3">
			<aui:select name="jobtitle" label="timesheetreport.view.jobtitle">
				<aui:option value=""><liferay-ui:message key="timesheetreport.view.all"/></aui:option>
				<c:forEach var="jobtitle" items="${jobTitleList}">
					<aui:option value="${jobtitle}" >
						${jobtitle}
					</aui:option>
				</c:forEach>
			</aui:select>
		</div>
		<div class="col-sm-1">
			<aui:button-row>
				<aui:button type="submit" value="timesheetreport.view.search"/>
			</aui:button-row>
		</div>
	</div>
</aui:form>
<div class="row my-3 data-table-signs">
	<div class="col">
		<div class="data-table">
			<table id="table_id" class="display">
				<thead>
				<tr>
					<th><liferay-ui:message key="timesheetreport.view.userName"/></th>
					<th><liferay-ui:message key="timesheetreport.view.workCenter"/></th>
					<th><liferay-ui:message key="timesheetreport.view.jobtitle"/></th>
					<th><liferay-ui:message key="timesheetreport.view.date"/></th>
					<th><liferay-ui:message key="timesheetreport.view.startTime"/></th>
					<th><liferay-ui:message key="timesheetreport.view.endTime"/></th>
					<th><liferay-ui:message key="timesheetreport.view.totalTime"/></th>
                    <th><liferay-ui:message key="timesheetreport.view.extra"/></th>
					<th><liferay-ui:message key="timesheetreport.view.rest"/></th>
					<th><liferay-ui:message key="timesheetreport.view.observations"/></th>
					<th><liferay-ui:message key="timesheetreport.view.observations.admin"/></th>
					<th><liferay-ui:message key="timesheetreport.view.detail"/></th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="<%=signsBetwenDates%>" var="sign">
					<%
						sign = (PrevingSign) pageContext.getAttribute("sign");
                        userTable = UserLocalServiceUtil.getUser(sign.getUserId());
                        userData = UserDataLocalServiceUtil.getUserDataByGroupIdAndUserId(sign.getGroupId(),sign.getUserId());
                        strWorkCenter = "";
                        if(Validator.isNotNull(userData)){
                            wk = WorkCentersLocalServiceUtil.getWorkCenters(userData.getWorkCenterId());
                            if(Validator.isNotNull(wk)){
                                strWorkCenter = wk.getName();
                            }
                        }
					%>
					<tr>
						<td>${sign.userName}</td>
						<td><%= strWorkCenter %></td>
						<td>${sign.jobTitle}</td>
						<td><%=dateFormat.format(sign.getDate())%></td>
						<td>${sign.startTime}</td>
						<td>${sign.endTime}</td>
						<td>${sign.totalTime}</td>
						<td>${sign.totalExtraTime}</td>
						<td>${sign.rest}</td>
						<td>${sign.getObservations()}</td>
						<td>${sign.getObservationsAdmin()}</td>
						<td><a onclick="toggle('.sign-${sign.userId}-<%=dateFormatId.format(sign.getDate())%>')"><liferay-ui:message key="timesheetreport.view.detail"/></a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="m-3 signs-detail">
			<c:forEach items="<%=signsBetwenDates%>" var="sign2">
				<%
					PrevingSign sign2 = (PrevingSign) pageContext.getAttribute("sign2");
				%>
				<div class="sign-${sign2.userId}-<%=dateFormatId.format(sign2.getDate())%> sign-detail ">
					<div class="row border-bottom">
						<div class="col-1"><liferay-ui:message key="timesheetreport.view.start.hour"/></div>
						<div class="col-1"><liferay-ui:message key="timesheetreport.view.finish.hour"/></div>
						<div class="col-1"><liferay-ui:message key="timesheetreport.view.total"/></div>
						<div class="col-2"><liferay-ui:message key="timesheetreport.view.activity"/></div>
						<div class="col-3"><liferay-ui:message key="timesheetreport.view.observations"/></div>
						<div class="col-4"><liferay-ui:message key="timesheetreport.view.observations.admin"/></div>
					</div>
					<c:forEach items="${sign2.signs}" var="signDetail">
						<%
							Sign signDetail = (Sign) pageContext.getAttribute("signDetail");
						%>
						<div class="row py-2">
							<%
								String activityName = "";
								if(Validator.isNotNull(map.get(signDetail.getActivityId()))){
									activityName = map.get(signDetail.getActivityId()).getName();
								}
							%>
							<div class="col-1"><%=dateFormatHour.format(signDetail.getStartDate())%></div>
							<div class="col-1"><%=Validator.isNotNull(signDetail.getFinishDate())?dateFormatHour.format(signDetail.getFinishDate()):""%></div>
							<div class="col-1"><%=PrevingSign.getTimeElapsed(signDetail.getStartDate(),signDetail.getFinishDate())%></div>
							<div class="col-2"><%=activityName%></div>
							<div class="col-3">${signDetail.observations}</div>
							<div class="col-4">
								<div class="row">
									<div id="${signDetail.signId}" class="col-10 border-bottom" contenteditable="true" >${signDetail.observationsAdmin}</div>
									<div class="col-2"><button type="button" onClick="sendObs(${signDetail.signId})"><liferay-ui:message key="timesheetreport.view.observations.save"/></button></div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:forEach>
		</div>
	</div>
</div>


<script>
	$(document).ready( function () {

	     $('#cmbWorkCenterSelect').select2({
                    multiple : true,
                    language: "es",
                    placeholder: "",
                    closeOnSelect: false
                }).on("change", function(e){
                    /*console.log(e);
                    if($('#<portlet:namespace/>cmbWorkCenter').val().indexOf("#" + this.value + "#") != -1){

                    }else{
                        $('#<portlet:namespace/>cmbWorkCenter').val($('#<portlet:namespace/>cmbWorkCenter').val() + "#"+ this.value + "#")
                    }*/


                });


		$('#table_id').DataTable( {
			dom: 'Bfrtip',

			responsive: false,
			pageLength: 15,
			ordering: true,
			searching: true,
			buttons: [
                'csv', 'excel', 'print',
                {
                    text: 'PDF',
                    extend: 'pdfHtml5',
                    filename: 'informe_registros',
                    orientation: 'landscape',//landscape o portrait
                    pageSize: 'A4', //A3 , A5 , A6 , legal , letter
                    exportOptions: {
                        columns: [ 0, 1, 2, 3, 4, 5, 6, 7 ],
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
                                    text: ["<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.terms")%>"]
                                }],
                                margin: 0
                            }
                        });
                    }
                }],
			"language": {
				"sProcessing":     "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sProcessing")%>",
				"sLengthMenu":     "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sLengthMenu")%>",
				"sZeroRecords":    "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sZeroRecords")%>",
				"sEmptyTable":     "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sEmptyTable")%>",
				"sInfo":           "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sInfo")%>",
				"sInfoEmpty":      "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sInfoEmpty")%>",
				"sInfoFiltered":   "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sInfoFiltered")%>",
				"sInfoPostFix":    "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sInfoPostFix")%>",
				"sSearch":         "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sSearch")%>",
				"sUrl":            "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sUrl")%>",
				"sInfoThousands":  "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sInfoThousands")%>,",
				"sLoadingRecords": "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sLoadingRecords")%>",
				"oPaginate": {
					"sFirst":    "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sFirst")%>",
					"sLast":     "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sLast")%>",
					"sNext":     "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sNext")%>",
					"sPrevious": "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sPrevious")%>"
				},
				"oAria": {
					"sSortAscending":  "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sSortAscending")%>",
					"sSortDescending": "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.sSortDescending")%>"
				},
				"buttons": {
					"copy": "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.copy")%>",
					"colvis": "<%=LanguageUtil.get(bundle, "timesheetreport.configuration.view.datatable.colvis")%>"
				}
			}
		} );

	} );
</script>

<script>
	function toggle(signId) {
		$('.sign-detail').hide();
		$(signId).toggle();
	}

	const DOMAIN =  "<%=themeDisplay.getURLPortal()%>";
	const URL_COMMENTS =  "/api/jsonws/preving.sign/set-admin-observations/sign-id/#ID_SIGN#/admin-user-id/#USER_ID#/observation/#OBSERVATIONS#?p_auth=#P_AUTH#";
	const P_AUTH = Liferay.authToken;
	const USER_ID = "<%=themeDisplay.getUserId()%>";

	function sendObs(id){
		try{
			var node = document.getElementById(id);
			if(node.innerHTML.trim() == "") return; //no tiene observaciones
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function(){
				if (this.readyState == 4 && this.status == 200){
					//Correcto

				}
			};
			xhr.onerror = function(){
				//Error en la llamada
				alert (this.error);
			}
			var urlComments = DOMAIN  + URL_COMMENTS.replace("#ID_SIGN#",id).replace("#USER_ID#",USER_ID).replace("#OBSERVATIONS#",node.innerHTML).replace("#P_AUTH#",P_AUTH);
			xhr.open("POST", urlComments, true);
			xhr.send(null);
		}catch(e){
			console.log("sendObs(): " + e);
		}
	}

</script>
