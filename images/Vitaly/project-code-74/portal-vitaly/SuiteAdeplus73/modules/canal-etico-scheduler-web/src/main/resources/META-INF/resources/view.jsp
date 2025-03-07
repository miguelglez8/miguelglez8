<%@ page import="com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil" %>
<%@ page import="com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil" %>
<%@ page import="com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ include file="/init.jsp" %>

<%
	List<SchedulerResponse> scheduledJobs = SchedulerEngineHelperUtil.getScheduledJobs();

	scheduledJobs = scheduledJobs.stream().sorted((o1, o2)->o1.getGroupName()
			.compareTo(o2.getGroupName()))
			.collect(Collectors.toList());

	SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEEEEEE, d MMMMMMMMMMM yyyy HH:mm:ss");
	Date now = new Date();
%>
<div class="row" style="background:#2f4050;color: #a7b1c2;">
	<div class="col-sm-6">
		<p>Group</p>
	</div>
	<div class="col-sm-2">
		<p>Start Time</p>
	</div>
	<div class="col-sm-2">
		<p>Next Fire Time</p>
	</div>
	<div class="col-sm-2">
		<p>Time left</p>
	</div>
</div>
<c:forEach items="<%=scheduledJobs%>" var="schedule">
	<%
		SchedulerResponse schedule = (SchedulerResponse) pageContext.getAttribute("schedule");
		Date nextFireTime = SchedulerEngineHelperUtil.getNextFireTime(schedule.getJobName(), schedule.getGroupName(), schedule.getStorageType());
		Date startTime = SchedulerEngineHelperUtil.getStartTime(schedule.getJobName(), schedule.getGroupName(), schedule.getStorageType());

		long time = nextFireTime.getTime() - now.getTime();
	%>
	<c:if test="<%=schedule.getGroupName().startsWith("com.canal.etico.liferay.portlet.scheduler")%>">
		<div class="row">
			<div class="col-sm-6">
				<p>${schedule.groupName}</p>
			</div>
			<div class="col-sm-2">
				<p><%=dateFormat.format(startTime)%></p>
			</div>
			<div class="col-sm-2">
				<p><%=dateFormat.format(nextFireTime)%></p>
			</div>
			<div class="col-sm-2">
				<p><%=((time / (24 * 60 * 60 * 1000)) > 0 ? time / (24 * 60 * 60 * 1000) + " days, ":"") + (time / (60 * 60 * 1000)) % 24 + " hours, " + time / (60 * 1000) % 60 + " minutes." %></p>
			</div>
		</div>
	</c:if>
</c:forEach>