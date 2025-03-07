package com.preving.liferay.portlet.scheduler.web.constants;

/**
 * @author agarciap
 */
public class PrevingSchedulerPortletKeys {

	public static final String PREVINGSCHEDULER =
		"com_preving_liferay_portlet_scheduler_web_PrevingSchedulerPortlet";

	public static final String PROPERTY_EXPANDO_USER_END_DATE = "preving.expando.user.end.date";

	public static final String PROPERTY_ROLE_PREVING_ADMIN 		= "preving.roles.preving.site.admin.name";
	public static final String PROPERTY_ROLE_COMPANY_ADMIN 		= "preving.roles.company.admin.name";
	public static final String PROPERTY_ROLE_COMPANY_SITE_ADMIN = "preving.roles.company.site.admin.name";
	public static final String PROPERTY_ROLE_COMPANY_SITE_USER 	= "preving.roles.company.site.user.name";

	public static final String NOTIFICATION_PERIODICITY_DAILY = "daily";
	public static final String NOTIFICATION_PERIODICITY_WEEKLY = "weekly";
	public static final String NOTIFICATION_PERIODICITY_MONTHLY = "monthly";
	public static final String NOTIFICATION_PERIODICITY_NONE = "notsend";

	public static final String MAIL_TEMPLATE_NAME_REGISTER_SUMMARY_DAILY = "/templates/RegisterSummaryDaily_";
	public static final String MAIL_TEMPLATE_NAME_REGISTER_SUMMARY_WEEKLY = "/templates/RegisterSummaryWeekly_";
	public static final String MAIL_TEMPLATE_NAME_REGISTER_SUMMARY_MONTHLY = "/templates/RegisterSummaryMonthly_";

	public static final String MAIL_TEMPLATE_NAME_NO_SIGNS_YESTERDAY = "/templates/NoSignsYesterday_";
	public static final String MAIL_TEMPLATE_NAME_UNCLOSED_SIGNS_YESTERDAY = "/templates/UnclosedSign_";
	public static final String MAIL_TEMPLATE_NAME_INACTIVE_USER = "/templates/InactiveUser_";

}