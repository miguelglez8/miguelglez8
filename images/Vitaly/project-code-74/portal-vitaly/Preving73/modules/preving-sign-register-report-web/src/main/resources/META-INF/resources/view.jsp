<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ page import="com.liferay.portal.kernel.model.User" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.Activity" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.Sign" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil" %>
<%@ page import="com.preving.liferay.portlet.sign.register.report.web.bean.PrevingSign" %>
<%@ page import="com.preving.liferay.portlet.sign.register.report.web.constants.SignRegisterReportPortletKeys" %>
<%@ page import="com.preving.liferay.portlet.sign.register.report.web.util.PrevingSignUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ include file="/init.jsp" %>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">

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
<script>
    Liferay.Loader.define.amd = Liferay.Loader.define._amd;
</script>

<%
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    List<PrevingSign> signsBetwenDates = (List<PrevingSign>) request.getAttribute(SignRegisterReportPortletKeys.SIGN_LIST);
    List<PrevingSign> jobTitlesFromCompany = (List<PrevingSign>) request.getAttribute(SignRegisterReportPortletKeys.JOBTITLE_LIST);
    String jobTitleSelected = (String) request.getAttribute(SignRegisterReportPortletKeys.JOBTITLE_SELECTED);
    List<User> users = (List<User>) request.getAttribute(SignRegisterReportPortletKeys.USER_LIST);

    String startDateStr = (String) request.getAttribute(SignRegisterReportPortletKeys.START_DATE);
    String endDateStr = (String) request.getAttribute(SignRegisterReportPortletKeys.END_DATE);

    Date start = dateFormat.parse(startDateStr);
    Date end = dateFormat.parse(endDateStr);

    Calendar calStart = Calendar.getInstance();
    calStart.add(Calendar.MONTH, -1);

    Calendar calEnd = Calendar.getInstance();

    long currentUserId = 0;
    String currentUserName = "";

    ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
%>

<liferay-portlet:actionURL name="search" var="searchURL"  />
<aui:form name="search_form" action="<%=searchURL.toString() %>" method="post">
    <div class="row">
        <div class="col-sm-2">
            <aui:fieldset label="signregisterreport.view.start">
                <liferay-ui:input-date name="startDate"
                                       yearValue="<%=calStart.get(Calendar.YEAR)%>"
                                       monthValue="<%=calStart.get(Calendar.MONTH)%>"
                                       dayValue="<%=calStart.get(Calendar.DAY_OF_MONTH)%>" />
            </aui:fieldset>
        </div>
        <div class="col-sm-2">
            <aui:fieldset label="signregisterreport.view.end">
                <liferay-ui:input-date name="endDate"
                                       yearValue="<%=calEnd.get(Calendar.YEAR)%>"
                                       monthValue="<%=calEnd.get(Calendar.MONTH)%>"
                                       dayValue="<%=calEnd.get(Calendar.DAY_OF_MONTH)%>" />
            </aui:fieldset>
        </div>
        <div class="col-sm-4">
            <aui:select name="userId" label="signregisterreport.view.user">
                <aui:option value="0"><liferay-ui:message key="signregisterreport.view.all"/></aui:option>
                <c:forEach var="user" items="${userList}">
                    <aui:option value="${user.userId}">
                        ${user.fullName}
                    </aui:option>
                </c:forEach>
            </aui:select>
        </div>
        <div class="col-sm-3">
            <aui:select name="jobtitle" label="signregisterreport.view.jobtitle">
                <aui:option value=""><liferay-ui:message key="signregisterreport.view.all"/></aui:option>
                <c:forEach var="jobtitle" items="${jobTitleList}">
                    <aui:option value="${jobtitle}" >
                        ${jobtitle}
                    </aui:option>
                </c:forEach>
            </aui:select>
        </div>
        <div class="col-sm-1">
            <aui:button-row>
                <aui:button type="submit" value="signregisterreport.view.search"/>
            </aui:button-row>
        </div>
    </div>
</aui:form>

<table id="table_id" class="display">
    <thead>
    <tr>
        <th><liferay-ui:message key="signregisterreport.view.date"/></th>
        <th><liferay-ui:message key="signregisterreport.view.userName"/></th>
        <th><liferay-ui:message key="signregisterreport.view.jobtitle"/></th>
        <th><liferay-ui:message key="signregisterreport.view.startTime"/></th>
        <th><liferay-ui:message key="signregisterreport.view.endTime"/></th>
        <th><liferay-ui:message key="signregisterreport.view.totalTime"/></th>
        <th><liferay-ui:message key="signregisterreport.view.extra"/></th>
        <th><liferay-ui:message key="signregisterreport.view.activity"/></th>
        <th><liferay-ui:message key="signregisterreport.view.observations"/></th>
        <th><liferay-ui:message key="signregisterreport.view.observations.admin"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="<%=signsBetwenDates%>" var="signSummary" varStatus="status">
        <%
            PrevingSign signSummary = (PrevingSign) pageContext.getAttribute("signSummary");
        %>

        <c:if test="<%=currentUserId !=0 && currentUserId != signSummary.getUserId() %>">
            <tr class="register-summary-month">
                <td><%=startDateStr + " - " + endDateStr%></td>
                <td><%=currentUserName%></td>
                <td></td>
                <td></td>
                <td></td>
                <td><%=PrevingSignUtil.getTotalHoursByUserBetweenDates(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), currentUserId, start, end)%></td>
                <td><%=PrevingSignUtil.getTotalHoursExtraByUserBetweenDates(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), currentUserId, start, end)%></td>
                <td><liferay-ui:message key="signregisterreport.view.total.month"/></td>
                <td></td>
                <td></td>
            </tr>
        </c:if>

        <tr class="register-summary">
            <td><%=dateFormat.format(signSummary.getDate())%></td>
            <td>${signSummary.userName}</td>
            <td>${signSummary.jobTitle}</td>
            <td></td>
            <td></td>
            <td>${signSummary.totalTime}</td>
            <td>${signSummary.totalExtraTime}</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>

        <c:forEach items="<%=signSummary.getSigns()%>" var="sign">
            <%
                Sign sign = null;
                String activityName = null;
                String workTime = null;
                String totalExtra=null;
                try {
                    sign = (Sign) pageContext.getAttribute("sign");
                    activityName = "";
                    workTime = "";
                    totalExtra="";
                    Activity activity = ActivityLocalServiceUtil.getActivity(sign.getActivityId());
                    if(Validator.isNotNull(activity)){
                        activityName = activity.getName(themeDisplay.getLocale());
                        workTime = activity.isWorkTime()?"signregisterreport.view.worktime.yes":"signregisterreport.view.worktime.no";
                        if(activity.getType()==3){
                            workTime= "signregisterreport.view.extra";
                            totalExtra=PrevingSign.getTimeElapsed(sign.getStartDate(), sign.getFinishDate());
                        }
                    }
                } catch (PortalException e) {
                    e.printStackTrace();
                }
            %>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td><%=PrevingSign.getHourMin(sign.getStartDate())%></td>
                <td><%=PrevingSign.getHourMin(sign.getFinishDate())%></td>
                <td><%=PrevingSign.getTimeElapsed(sign.getStartDate(), sign.getFinishDate())%></td>
                <td><%=totalExtra%></td>
                <td><%=activityName%> (<liferay-ui:message key="<%=workTime%>"/>)</td>
                <td><%=sign.getObservations()%></td>
                <td><%=sign.getObservationsAdmin()%></td>
            </tr>
        </c:forEach>

        <%
            currentUserId = signSummary.getUserId();
            currentUserName = signSummary.getUserName();
        %>

        <c:if test="${status.last}">
            <tr class="register-summary-month">
                <td><%=startDateStr + " - " + endDateStr%></td>
                <td>${signSummary.userName}</td>
                <td>${signSummary.jobTitle}</td>
                <td></td>
                <td></td>
                <td><%=PrevingSignUtil.getTotalHoursByUserBetweenDates(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), signSummary.getUserId(), start, end)%></td>
                <td><%=PrevingSignUtil.getTotalHoursExtraByUserBetweenDates(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), currentUserId, start, end)%></td>
                <td><liferay-ui:message key="signregisterreport.view.total.month"/></td>
                <td></td>
                <td></td>
            </tr>
        </c:if>

    </c:forEach>
    </tbody>
</table>

<img style="display:none;" id="group_logo" src="<%=themeDisplay.getScopeGroup().getLogoURL(themeDisplay, true)%>">


<script>
    function getBase64Image(img) {
        var canvas = document.createElement("canvas");
        canvas.width = img.width;
        canvas.height = img.height;
        var ctx = canvas.getContext("2d");
        ctx.drawImage(img, 0, 0);
        var dataURL = canvas.toDataURL("image/png");
        return dataURL;
    }
    $(document).ready( function () {
        $('#table_id').DataTable( {
            dom: 'Bfrtip',
            responsive: true,
            pageLength: 25,
            ordering: false,
            searching: false,
            buttons: [
                'csv', 'excel', 'print',
                {
                    text: 'PDF',
                    extend: 'pdfHtml5',
                    filename: 'firma_pdf',
                    orientation: 'landscape',//landscape o portrait
                    pageSize: 'A4', //A3 , A5 , A6 , legal , letter
                    exportOptions: {
                        columns: ':visible',
                        search: 'applied',
                        order: 'applied'
                    },
                    customize: function (doc) {
                        doc.content.splice(0,1);
                        var now = new Date();
                        var jsDate = now.getDate()+'-'+(now.getMonth()+1)+'-'+now.getFullYear();
                        var base64 = getBase64Image(document.getElementById("group_logo"));

                        doc.pageMargins = [20,60,20,100];
                        doc.defaultStyle.fontSize = 8;
                        doc.styles.tableHeader.fontSize = 8;
                        doc['header']=(function() {
                            return {
                                columns: [
                                    {
                                        image: base64,
                                        width: 40
                                    },
                                    {
                                        alignment: 'left',
                                        italics: true,
                                        text: '<%=themeDisplay.getScopeGroup().getName(locale)%>',
                                        fontSize: 16,
                                        margin: [10,0]
                                    },
                                    {
                                        alignment: 'right',
                                        fontSize: 14,
                                        text: '<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.from")%> ' + document.getElementById("<portlet:namespace/>startDate").value
                                            + ' <%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.to")%> '+ document.getElementById("<portlet:namespace/>endDate").value
                                    },
                                    {
                                        alignment: 'right',
                                        fontSize: 14,
                                        text: '<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.register")%>'
                                    }
                                ],
                                margin: 20
                            }
                        });
                        doc['footer']=(function(page, pages) {
                            return {
                                layout: 'noBorders',
                                table: {
                                    widths: ['*', 'auto'],
                                    body: [
                                        [ {
                                            alignment: 'center',
                                            margin: [ 0, 0, 0, 40 ],
                                            text: ["<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sign.company")%>"]
                                        }, {
                                            alignment: 'center',
                                            margin: [ 0, 0, 0, 40 ],
                                            text: ["<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sign.user")%>"]
                                        }],
                                        [{
                                            italics: true,
                                            colSpan: 2,
                                            fontSize: 8,
                                            alignment: 'center',
                                            text: ["<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.terms")%>"]
                                        }, {}]]
                                },
                                margin: 0
                            }
                        });
                    }
                }],
            "language": {
                "sProcessing":     "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sProcessing")%>",
                "sLengthMenu":     "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sLengthMenu")%>",
                "sZeroRecords":    "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sZeroRecords")%>",
                "sEmptyTable":     "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sEmptyTable")%>",
                "sInfo":           "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sInfo")%>",
                "sInfoEmpty":      "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sInfoEmpty")%>",
                "sInfoFiltered":   "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sInfoFiltered")%>",
                "sInfoPostFix":    "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sInfoPostFix")%>",
                "sSearch":         "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sSearch")%>",
                "sUrl":            "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sUrl")%>",
                "sInfoThousands":  "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sInfoThousands")%>,",
                "sLoadingRecords": "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sLoadingRecords")%>",
                "oPaginate": {
                    "sFirst":    "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sFirst")%>",
                    "sLast":     "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sLast")%>",
                    "sNext":     "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sNext")%>",
                    "sPrevious": "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sPrevious")%>"
                },
                "oAria": {
                    "sSortAscending":  "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sSortAscending")%>",
                    "sSortDescending": "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.sSortDescending")%>"
                },
                "buttons": {
                    "copy": "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.copy")%>",
                    "colvis": "<%=LanguageUtil.get(bundle, "signregisterreport.configuration.view.datatable.colvis")%>"
                }
            }
        } );

    } );
</script>
