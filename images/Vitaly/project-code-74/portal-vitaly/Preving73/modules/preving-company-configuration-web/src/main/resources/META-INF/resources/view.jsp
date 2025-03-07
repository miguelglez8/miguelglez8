<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ include file="/init.jsp" %>

<%
	CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCompanyIdAndGroupId(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    String nameUser = themeDisplay.getUser().getLastName() + ", " +  themeDisplay.getUser().getFirstName();

	boolean disabledStartDate = true;
	Calendar startDateCalendar = Calendar.getInstance();
	if(Validator.isNotNull(companyConf) && Validator.isNotNull(companyConf.getStartDate())){
		startDateCalendar.setTime(companyConf.getStartDate());
		disabledStartDate = false;
	}else{
		startDateCalendar.setTime(new Date());
	}

	boolean disabledImplantationDate = true;
	Calendar implantatioDateCalendar = Calendar.getInstance();
	if(Validator.isNotNull(companyConf) && Validator.isNotNull(companyConf.getImplantationDate())){
		startDateCalendar.setTime(companyConf.getImplantationDate());
		disabledImplantationDate = false;
	}else{
		implantatioDateCalendar.setTime(new Date());
	}
	java.util.ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
%>

<c:if test="<%=Validator.isNotNull(companyConf)%>">

	<liferay-portlet:actionURL name="save" var="saveURL"  />
	<aui:form name="configuration_form" action="<%= saveURL.toString() %>" method="post" enctype="multipart/form-data">

		<aui:input name="companyConfId" type="hidden" value="<%=companyConf.getCompanyConfId()%>" />

		<liferay-ui:tabs names="company.configuration.notifications.tab,company.configuration.configuration.tab,company.configuration.company.tab" refresh="false" >

			<liferay-ui:section>
				<%@include file="notifications.jspf" %>
			</liferay-ui:section>

			<liferay-ui:section>
				<%@include file="timesheet.jspf" %>
			</liferay-ui:section>

			<liferay-ui:section>
				<%@include file="company.jspf" %>
			</liferay-ui:section>

		</liferay-ui:tabs>

		<div class="row">
			<div class="col">
				<aui:button-row>
					<aui:button type="submit" />
				</aui:button-row>
			</div>
		</div>
	</aui:form>
</c:if>

