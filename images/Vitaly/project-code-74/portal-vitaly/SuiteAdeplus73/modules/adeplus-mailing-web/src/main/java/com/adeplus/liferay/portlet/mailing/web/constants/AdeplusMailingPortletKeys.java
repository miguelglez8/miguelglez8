package com.adeplus.liferay.portlet.mailing.web.constants;

/**
 * @author agarciap
 */
public class AdeplusMailingPortletKeys {

	public static final String ADEPLUSMAILING = "com_adeplus_liferay_portlet_mailing_web_AdeplusMailingPortlet";

	//For user
	public static final String MAIL_TEMPLATE_USER_CREATE = "templates/user/UserCreateTemplate";
	public static final String MAIL_TEMPLATE_USER_UPDATE = "templates/user/UserUpdateTemplate";
	public static final String MAIL_TEMPLATE_USER_DELETE = "templates/user/UserDeleteTemplate";
	public static final String MAIL_TEMPLATE_USER_DELETE_LAST_DAY = "templates/user/UserDeleteTemplate";
	public static final String MAIL_TEMPLATE_USER_REMIND_PASSWORD = "templates/user/UserRemindPasswordTemplate";	
	public static final String MAIL_TEMPLATE_USER_PASSWORD_CHANGED = "templates/user/UserPasswordChangedTemplate";

	//Company
	public static final String MAIL_TEMPLATE_COMPANY_DELETE = "templates/user/CompanyDeleteTemplate";
	public static final String MAIL_TEMPLATE_COMPANY_ADD_APPLICATION = "templates/user/CompanyAddApplicationTemplate";
	public static final String MAIL_TEMPLATE_COMPANY_DELETE_APPLICATION = "templates/user/CompanyDeleteApplicationTemplate";

	//Contact
	public static final String MAIL_TEMPLATE_CONTACT_ADMIN = "templates/contact/ContactFormToAdminTemplate";
	public static final String MAIL_TEMPLATE_CONTACT_USER = "templates/contact/ContactFormToUserTemplate";

	//Message Bus
	public static final String MESSAGE_BUS_DESTINATION_NAME = "Adeplus/sendMail";

	// Mailing
    public static final String MAIL_FROM = "from";
	public static final String MAIL_FROM_NAME = "fromName";
	public static final String MAIL_TO = "to";
	public static final String MAIL_TO_NAME = "toName";
	public static final String MAIL_SUBJECT = "subject";
	public static final String MAIL_BODY = "body";
}