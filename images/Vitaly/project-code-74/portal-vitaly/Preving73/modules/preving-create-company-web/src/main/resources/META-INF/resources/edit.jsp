<%@ page import="com.liferay.portal.kernel.model.Group" %>
<%@ page import="com.liferay.portal.kernel.model.User" %>
<%@ page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PrefsPropsUtil" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.CompanyConf" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.Sign" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.UserData" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.service.SignLocalServiceUtil" %>
<%@ page import="com.preving.liferay.portlet.create.company.web.util.PrevingUserDataUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.preving.liferay.portlet.create.company.web.constants.CreateCompanyPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.List" %>
<%@ page import="com.preving.liferay.portlet.create.company.web.util.PrevingUserUtil" %>
<%@ include file="init.jsp" %>

<%
	long companyConfId = ParamUtil.getLong(request, CreateCompanyPortletKeys.COMPANY_CONF_ID, 0);
	long companyGroupId = ParamUtil.getLong(request, CreateCompanyPortletKeys.COMPANY_CONF_GROUP_ID, 0);

	//System.out.println(companyConfId);
	//System.out.println(companyGroupId);

	CompanyConf companyConf = null;
	if(companyConfId > 0) {
		companyConf = CompanyConfLocalServiceUtil.fetchCompanyConf(companyConfId);
	}

	Group group = null;
	if(companyGroupId > 0) {
		group = GroupLocalServiceUtil.fetchGroup(companyGroupId);
	}

	UserData userData = null;
	User admin = null;
	long adminUserId = 0;
	String groupName = "";
	String firstName= "";
	String lastName= "";
	String emailAddress= "";
	String nif = "";
	String usersCount = "0";


	if(Validator.isNotNull(companyConf) && companyConf.getUserId() > 0) {
		admin = UserLocalServiceUtil.fetchUser(companyConf.getUserId());
	}else{
		List<User> listAdminIdFromGroup = PrevingUserUtil.getListAdminIdFromGroup(companyGroupId);

		if(listAdminIdFromGroup.size() >0){
			admin = listAdminIdFromGroup.get(0);
		}
	}

	if (Validator.isNotNull(admin)){

		userData = PrevingUserDataUtil.getUserData(companyGroupId, admin.getUserId());

		String expandoUserNifName = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_EXPANDO_USER_NIF);
		if(admin.getExpandoBridge().hasAttribute(expandoUserNifName)) {
			nif = (String) admin.getExpandoBridge().getAttribute(expandoUserNifName);
		}
		adminUserId = admin.getUserId();
		firstName = admin.getFirstName();
		lastName = admin.getLastName();
		emailAddress = admin.getEmailAddress();
	}

	if (Validator.isNotNull(userData)){
		firstName = userData.getName();
		lastName = userData.getLastName();
		emailAddress = userData.getEmail();
		nif = userData.getNif();
	}

	if(Validator.isNotNull(group)){
		groupName = group.getName(themeDisplay.getLocale());
		usersCount = String.valueOf(UserLocalServiceUtil.getGroupUsersCount(group.getGroupId()) - 1);
	}

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date today = new Date();

	Calendar startDateCalendar = Calendar.getInstance();
	startDateCalendar.setTime(today);
	if(Validator.isNotNull(companyConf) && Validator.isNotNull(companyConf.getStartDate())){

		startDateCalendar.setTime(companyConf.getStartDate());

	}else if(Validator.isNotNull(companyConf) && Validator.isNotNull(companyConf.getCreateDate())){

		startDateCalendar.setTime(companyConf.getCreateDate());

	}

	Calendar implantationDateCalendar = Calendar.getInstance();
	implantationDateCalendar.setTime(today);
	if(Validator.isNotNull(companyConf) && Validator.isNotNull(companyConf.getImplantationDate())){
		implantationDateCalendar.setTime(companyConf.getImplantationDate());
	}

	boolean disabledDeleteDate = true;
	Calendar deleteDateCalendar = Calendar.getInstance();
	deleteDateCalendar.setTime(today);
	if(Validator.isNotNull(companyConf) && Validator.isNotNull(companyConf.getDeleteDate())){
		deleteDateCalendar.setTime(companyConf.getDeleteDate());
		disabledDeleteDate = false;
	}

	boolean deleteCompany = false;

	if(Validator.isNotNull(companyConf)) {
		List<Sign> signsByCompanyIdAndGroupId = SignLocalServiceUtil.getSignsByCompanyIdAndGroupId(companyConf.getCompanyId(), companyConf.getGroupId());
		if (signsByCompanyIdAndGroupId.isEmpty()) {
			deleteCompany = true;
		}
	}

	String sources = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_SOURCE_DEFAULT);
	List<String> sourceList = Arrays.asList(sources.split(";"));
%>

<h2><liferay-ui:message key="createcompany.edit.title"/></h2>

<liferay-portlet:actionURL name="edit" var="editURL"  />
<aui:form action="<%= editURL.toString() %>" method="post">

	<aui:input name="companyConfId" type="hidden" value="<%=companyConfId%>" />
	<aui:input name="companyGroupId" type="hidden" value="<%=companyGroupId%>" />
	<aui:input name="adminUserId" type="hidden" value="<%=adminUserId%>" />

	<aui:fieldset label="createcompany.edit.company">
		<div class="row">
			<div class="col">
				<aui:input name="companyName" type="text" label="createcompany.edit.companyName" value="<%=groupName%>">
					<aui:validator name="required" />
				</aui:input>
			</div>
			<div class="col">
				<aui:input name="cif" type="text" label="createcompany.edit.cif" value="<%=Validator.isNotNull(companyConf)?companyConf.getCif():""%>">
					<aui:validator name="required" />
				</aui:input>
			</div>
			<div class="col">
				<c:set var="sourceConf" value="<%=Validator.isNotNull(companyConf)?companyConf.getSource():""%>"/>
				<aui:select name="source" label="createcompany.view.source">
					<aui:option value=""> </aui:option>
					<c:forEach var="src" items="<%=sourceList%>">
						<aui:option value="${src}" selected="${src == sourceConf?true:false}">
							${src}
						</aui:option>
					</c:forEach>
				</aui:select>
			</div>
		</div>
		<div class="row">
			<div class="col">
					<%--<aui:input name="startDate" type="date" label="createcompany.edit.startDate"  />--%>
				<aui:fieldset label="createcompany.edit.startDate">
					<liferay-ui:input-date name="startDate" nullable="false" disabled="true"
						   yearValue="<%=startDateCalendar.get(Calendar.YEAR)%>"
						   monthValue="<%=startDateCalendar.get(Calendar.MONTH)%>"
						   dayValue="<%=startDateCalendar.get(Calendar.DAY_OF_MONTH)%>" />

				</aui:fieldset>
			</div>
			<div class="col">
				<aui:fieldset label="createcompany.edit.implantation">
					<liferay-ui:input-date name="implantationDate" nullable="true"
										   yearValue="<%=implantationDateCalendar.get(Calendar.YEAR)%>"
										   monthValue="<%=implantationDateCalendar.get(Calendar.MONTH)%>"
										   dayValue="<%=implantationDateCalendar.get(Calendar.DAY_OF_MONTH)%>" />
				</aui:fieldset>
			</div>
			<div class="col">
				<aui:fieldset label="createcompany.edit.endDate">
					<c:if test="<%=disabledDeleteDate%>">
						<liferay-ui:input-date name="deleteDate" nullable="true" disabled="<%=disabledDeleteDate%>"/>
					</c:if>
					<c:if test="<%=!disabledDeleteDate%>">
						<liferay-ui:input-date name="deleteDate" nullable="true"
											   yearValue="<%=deleteDateCalendar.get(Calendar.YEAR)%>"
											   monthValue="<%=deleteDateCalendar.get(Calendar.MONTH)%>"
											   dayValue="<%=deleteDateCalendar.get(Calendar.DAY_OF_MONTH)%>" />
					</c:if>
				</aui:fieldset>
			</div>
		</div>
	</aui:fieldset>


	<aui:fieldset label="createcompany.edit.admin">
		<div class="row">
			<div class="col">
				<aui:input name="adminName" type="text" label="createcompany.edit.adminName" value="<%=firstName%>" >
				    <aui:validator name="required" />
				</aui:input>
			</div>
			<div class="col">
				<aui:input name="adminSurname" type="text" label="createcompany.edit.adminSurname" value="<%=lastName%>" >
				    <aui:validator name="required" />
                </aui:input>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<aui:input name="email" type="text" label="createcompany.edit.email" value="<%=emailAddress%>">
					<aui:validator name="required" />
					<aui:validator name="email" />
				</aui:input>
			</div>
			<div class="col">
				<aui:input name="nif" type="text" label="createcompany.edit.nif" value="<%=nif%>"/>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<p><liferay-ui:message key="createcompany.edit.user.count" arguments="<%=new String[]{String.valueOf(UserLocalServiceUtil.getGroupUsersCount(companyGroupId) - 1)}%>"/></p>
			</div>
		</div>
	</aui:fieldset>


	<div class="row">
		<div class="col">
			<aui:button-row>
				<aui:button type="submit" />
				<portlet:renderURL portletMode="view" var="viewURL" />
				<aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
			</aui:button-row>
		</div>

		<c:if test="<%=permissionChecker.isOmniadmin() && Validator.isNotNull(group)%>">
			<div class="col">
				<c:if test="<%=group.isActive()%>">
					<liferay-portlet:actionURL name="addUserRoleToCompany" var="addUserRoleToCompanyURL" >
						<portlet:param name="companyGroupId" value="<%=String.valueOf(companyGroupId)%>" />
					</liferay-portlet:actionURL>
					<a href="<%=addUserRoleToCompanyURL%>"><liferay-ui:message key="createcompany.edit.add.user.role" arguments="<%=new String[]{usersCount}%>"/> </a>
					<liferay-ui:icon-help message="createcompany.edit.add.user.role.help"/>
				</c:if>
			</div>
		</c:if>

		<c:if test="<%=permissionChecker.isOmniadmin() && Validator.isNotNull(group)%>">
			<div class="col">
				<liferay-portlet:actionURL name="addUserRoleToAllCompanies" var="addUserRoleToAllCompaniesURL" />
				<a href="<%=addUserRoleToAllCompaniesURL%>"><liferay-ui:message key="createcompany.edit.add.user.all.role"/> </a>
			</div>
			<div class="col">
				<liferay-portlet:actionURL name="addUserRoleToAllCompanies" var="addUserRoleToAllCompaniesURL" />
				<a href="/group<%=group.getFriendlyURL()%>" target="_blank"><%=group.getFriendlyURL()%></a>
			</div>
		</c:if>

		<div class="col">
			<c:if test="<%=deleteCompany%>">
				<liferay-ui:icon-list>
					<liferay-portlet:actionURL name="delete" var="deleteURL">
						<portlet:param name="companyConfId" value="<%=String.valueOf(companyConfId)%>" />
					</liferay-portlet:actionURL>
					<liferay-ui:icon-delete showIcon="<%= true %>" url="<%= deleteURL %>"/>
				</liferay-ui:icon-list>
			</c:if>
		</div>

	</div>

</aui:form>

