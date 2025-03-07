package com.preving.liferay.portlet.create.company.web.constants;

import com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil;

/**
 * @author agarciap
 */
public class CreateCompanyPortletKeys {

	public static final String CREATECOMPANY =
		"com_preving_liferay_portlet_create_company_web_CreateCompanyPortlet";

	//Message Bus
	public static final String MESSAGE_BUS_DESTINATION_NAME = "Preving/CreateCompany";
	public static final String MESSAGE_BUS_DESTINATION_NAME_SITE_TEMPLATE = "Preving/Company/SelectSiteTemplate";

	// Entity names
	public static final String COMPANY_NAME = "companyName";
	public static final String COMPANY_CIF = "cif";
	public static final String COMPANY_CREATOR_USER_ID = "creatorUserId";
	public static final String COMPANY_ADMIN_USER_ID = "adminUserId";
	public static final String COMPANY_ADMIN_NAME = "adminName";
	public static final String COMPANY_ADMIN_SURNAME = "adminSurname";
	public static final String COMPANY_ADMIN_NIF = "nif";
	public static final String COMPANY_ADMIN_EMAIL = "email";
	public static final String COMPANY_ADMIN_PASSWORD = "password";
	public static final String COMPANY_START_DATE = "startDate";
	public static final String COMPANY_IMPLANTATION_DATE = "implantationDate";
	public static final String COMPANY_END_DATE = "deleteDate";
	public static final String COMPANY_FRIENDLY_URL = "friendlyURL";
	public static final String COMPANY_SITE_URL = "siteURL";
	public static final String COMPANY_SOURCE = "source";
	public static final String COMPANY_SOURCE_LIST = "sourceList";
	public static final String TEMPLATE_ID = "templateId";

	public static final String COMPANY_BEAN_LIST = "companyBeanList";
	public static final String COMPANY_CONF_ID = "companyConfId";
	public static final String COMPANY_CONF_GROUP_ID = "companyGroupId";
	public static final String COMPANY_CSV_COMPANIES = "companyCSVParsedString";
	public static final String COMPANY_CSV_COMPANIES_LIST = "companyCSVList";

	public static final String COMPANY_ID = "companyId";
	public static final String GROUP_ID = "groupId";
	public static final String USER_ID = "userId";
	public static final String PORTAL_URL = "portalURL";
	public static final String PATH_MAIN = "pathMain";
	public static final String LANGUAGE_ID = "languageId";

	// Configuration
	public static final String CONFIGURATION_TEMPLATE_ID = "templateId";
	public static final String CONFIGURATION_IMPORT_HELP_TEXT = "importHelpText";
	public static final String CONFIGURATION_MAIL_BODY = "mailBody";
	public static final String CONFIGURATION_MAIL_SUBJECT = "mailSubject";

	// Properties
	public static final String PROPERTY_ROLE_PREVING_ADMIN 		= "preving.roles.preving.site.admin.name";
	public static final String PROPERTY_ROLE_COMPANY_ADMIN 		= "preving.roles.company.admin.name";
	public static final String PROPERTY_ROLE_COMPANY_SITE_ADMIN = "preving.roles.company.site.admin.name";
	public static final String PROPERTY_ROLE_COMPANY_SITE_USER 	= "preving.roles.company.site.user.name";

	public static final String PROPERTY_EXPANDO_USER_NIF = "preving.expando.user.nif";
	public static final String PROPERTY_EXPANDO_USER_SALARY = "preving.expando.user.salary";
	public static final String PROPERTY_EXPANDO_USER_END_DATE = "preving.expando.user.end.date";

	public static final String PROPERTY_SOURCE_DEFAULT = "preving.source.default";


	// Group table fields
	public static final String GROUP_COMPANY_ID = "companyId";
	public static final String GROUP_GROUP_ID = "groupId";
	public static final String GROUP_NAME = "name";

	// User table fields
	public static final String USER_FIRST_NAME = "firstName";
	public static final String USER_LAST_NAME = "lastName";
	public static final String USER_EMAIL = "emailAdress";

	public static final String USER_ACTIVE_YES = "active";
	public static final String USER_ACTIVE_NO = "inactive";


	public static final String ACTIVITY_TYPE_OFFICE = "Jornada laboral en oficina";
	public static final String ACTIVITY_TYPE_EXTERIOR = "Jornada laboral en exterior";
	public static final String ACTIVITY_TYPE_TELEWORK = "Jornada laboral en teletrabajo";
	public static final String ACTIVITY_TYPE_OTHER_WORK = "Jornada laboral - otros";
	public static final String ACTIVITY_TYPE_BREAK = "Pausa - descanso";
	public static final String ACTIVITY_TYPE_LUNCH = "Pausa - comida";
	public static final String ACTIVITY_TYPE_HEALTH = "Pausa - visita m&#233;dica";
	public static final String ACTIVITY_TYPE_OTHER = "Pausa - otros";



	public static final String KEYCLOAK_CONFIGURATION_REALNAME  = "preving.keycloak.configuration.realname";
	public static final String KEYCLOAK_CONFIGURATION_URL 		= "preving.keycloak.configuration.url";
	public static final String KEYCLOAK_CONFIGURATION_USERNAME 	= "preving.keycloak.configuration.username";
	public static final String KEYCLOAK_CONFIGURATION_PASSWORD 	= "preving.keycloak.configuration.pass";
	public static final String KEYCLOAK_CONFIGURATION_CLIENT_ID 	= "preving.keycloak.configuration.clientid";
	public static final String KEYCLOAK_CONFIGURATION_SECRET 	= "preving.keycloak.configuration.secret";


}
