package com.preving.liferay.portlet.mailing.web.constants;

/**
 * @author agarciap
 */
public class PrevingMailingPortletKeys {

	public static final String PREVINGMAILING =	"com_preving_liferay_portlet_mailing_web_PrevingMailingPortlet";


	//Mail template names
	//For company
	public static final String MAIL_TEMPLATE_COMPANY_CREATE_TO_CREATOR 	= "templates/company/CompanyCreateToCreatorTemplate";
	public static final String MAIL_TEMPLATE_COMPANY_CREATE_TO_ADMIN 	= "templates/company/CompanyCreateToAdminTemplate";
	public static final String MAIL_TEMPLATE_COMPANY_DELETE_TO_ADMIN 	= "templates/company/CompanyDeleteToAdminTemplate";

	//For user
	public static final String MAIL_TEMPLATE_USER_CREATE_TO_USER = "templates/user/UserCreateToUserTemplate";
	public static final String MAIL_TEMPLATE_USER_DELETE_TO_USER = "templates/user/UserDeleteToUserTemplate";
	public static final String MAIL_TEMPLATE_USER_REMIND_PASSWORD_TO_USER = "templates/user/UserRemingPasswordToUserTemplate";
	public static final String MAIL_TEMPLATE_USER_ACTIVATE_TO_USER = "templates/user/UserActivateToUserTemplate";

	public static final String MAIL_TEMPLATE_USER_EXTRA = "templates/user/UserExtraActivity";
	//For scheduler
	public static final String MAIL_TEMPLATE_SCHEDULER_USER_UNCLOSED_SIGN = "templates/report/SignUnclosedToUserTemplate";
	public static final String MAIL_TEMPLATE_SCHEDULER_USER_UNCLOSED_SIGN_WEEKLY = "templates/report/SignUnclosedToUserWeeklyTemplate";
	public static final String MAIL_TEMPLATE_SCHEDULER_USER_WITHOUT_SIGN  = "templates/report/UserWithoutSignToUserTemplate";
	public static final String MAIL_TEMPLATE_SCHEDULER_SIGN_REPORT  	  = "templates/report/SignReportToAdminTemplate";
	public static final String MAIL_TEMPLATE_SCHEDULER_SIGN_REPORT_WEEKLY = "templates/report/SignReportToAdminWeeklyTemplate";
	public static final String MAIL_TEMPLATE_SCHEDULER_SIGN_REPORT_MONTHLY= "templates/report/SignReportToAdminMonthlyTemplate";

	//For contact
	public static final String MAIL_TEMPLATE_CONTACT_TO_ADMIN  	  = "templates/contact/ContactToAdminTemplate";
	public static final String MAIL_TEMPLATE_CONTACT_TO_USER  	  = "templates/contact/ContactToUserTemplate";
}