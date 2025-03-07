package com.preving.liferay.portlet.user.configuration.web.constants;

/**
 * @author agarciap
 */
public class UserConfigurationPortletKeys {

	public static final String USERCONFIGURATION =
		"com_preving_liferay_portlet_user_configuration_web_UserConfigurationPortlet";

	public static final String USER_ID = "userId";
	public static final String USER_NAME = "name";
	public static final String USER_SURNAME = "surname";
	public static final String USER_NIF = "nif";
	public static final String USER_EMAIL = "email";
	public static final String USER_SALARY = "salary";
	public static final String USER_WORK_CENTER = "workCenter";
	public static final String USER_WORK_CMB_CENTER = "cmbWorkCenter";

	public static final String USER_JOB_TITLE = "jobTitle";
	public static final String USER_END_DATE = "endDate";
	public static final String USER_GENRE = "genre";
	public static final String USER_ACTIVE = "active";
	public static final String USER_ADMIN = "admin";
	public static final String USER_LANGUAGE = "language";
	public static final String USER_PASSWORD = "password";
	public static final String NOTIFICATION_END_DATE = "notificationEndDate";

	//Message Bus
	public static final String MESSAGE_BUS_DESTINATION_NAME = "Preving/CreateUser";

	// Expando fields
	public static final String PROPERTY_EXPANDO_USER_NIF = "preving.expando.user.nif";
	public static final String PROPERTY_EXPANDO_USER_SALARY = "preving.expando.user.salary";
	public static final String PROPERTY_EXPANDO_USER_END_DATE = "preving.expando.user.end.date";
	public static final String PROPERTY_EXPANDO_WORK_CENTER = "preving.expando.user.work.center";

	public static final String PROPERTY_ROLE_COMPANY_ADMIN 		= "preving.roles.company.admin.name";
	public static final String PROPERTY_ROLE_COMPANY_SITE_ADMIN = "preving.roles.company.site.admin.name";
	public static final String PROPERTY_ROLE_COMPANY_SITE_USER 	= "preving.roles.company.site.user.name";

	public static final String USER_LIST = "userList";
	public static final String USERS_CSV_USERS = "userCSVList";
	public static final String WORKCENTER_LIST = "workcenterList";
	public static final String LANGUAGE_ID = "languageId";
	public static final String GROUP_ID = "groupId";
	public static final String COMPANY_ID = "companyId";
	public static final String PORTAL_URL = "portalURL";
	public static final String PATH_MAIN = "pathMain";
	public static final String CREATOR_USER_ID = "creatorUserId";

	public static final String USER_GENRE_MALE = "male";
	public static final String USER_GENRE_FEMALE = "female";
	public static final String USER_ACTIVE_YES = "active";
	public static final String USER_ACTIVE_NO = "inactive";
	public static final String USER_ADMIN_YES = "admin";
	public static final String USER_ADMIN_NO = "noAdmin";

	// Configuration
	public static final String CONFIGURATION_IMPORT_HELP_TEXT = "importHelpText";
    public static final String CONFIGURATION_USER_END_DATE_CRON_EXPRESION = "preving.user.date.end.cron.expression";


	//public static final String LANG_WELCOME_es_ES="Estimad@ Sr./ Sra.,";
	//public static final String LANG_GOODBYE_es_ES="Un saludo.";
	//public static final String LANG_FOOTER_es_ES="<p><span style=\"font-size:10px;\">Este e-mail ha sido emitido por Grupo Preving. A partir de la informaci&#243;n facilitada por su Empresa para la implementaci&#243;n del registro on-line de la jornada diaria. La informaci&#243;n contenida es CONFIDENCIAL, y tiene como &#250;nicos destinatarios las personas designadas en &#233;l. Si Usted lee este mensaje y no es uno de los destinatarios indicados, le informamos que est&#225; totalmente prohibida cualquier utilizaci&#243;n, divulgaci&#243;n, distribuci&#243;n y/o reproducci&#243;n de esta comunicaci&#243;n sin autorizaci&#243;n expresa en virtud de la legislaci&#243;n vigente. Si ha recibido este mensaje por error, le rogamos nos lo notifique inmediatamente por esta misma v&#237;a y proceda a su eliminaci&#243;n.</span></p>";
	//public static final String LANG_TRUSH_es_ES="<p>Confiamos que la utilizaci&#243;n de su aplicativo haya contribuido a dar una  mayor garant&#237;a a su relaci&#243;n laboral.</p>";

	/** template done **/
	public static final String LANG_CREATE_USER_SUBJECT_es_ES="Bienvenido a su Registro Horario. https://www.miregistrodehoras.com";
	public static final String LANG_CREATE_USER_BODY_es_ES="<p>En primer lugar, queremos darle la bienvenida a la Aplicaci&#243;n <a href=\"https://www.miregistrodehoras.com\">miregistrodehoras</a>, en el que ha sido dado de alta a instancias de su Empresa, para el registro diario de jornada establecido en el art. 34.9 del Estatuto de los trabajadores.</p>" +
			"<p>A partir de ahora deber&#225; acceder diariamente a miregistrodehoras.com con sus claves de acceso para informar su inicio y fin de jornada, pausas etc .. De manera on-line, f&#225;cil y segura. Asimismo podr&#225; introducir observaciones en sus registros y consultar las jornadas registradas. Le recomendamos que visualice este <a href=\"https://vimeo.com/461412233/7c70e3d4c3\">v&#237;deo explicativo</a> donde podr&#225; visualizar todas las funcionalidades que ponemos a su disposici&#243;n.</p>" +
			"<p>Pantalla de login: <a href=\"https://www.miregistrodehoras.com\">https://www.miregistrodehoras.com</a></p>";
	public static final String LANG_CREATE_USER_BODY_INFO_USER_es_ES="Usuario: ";
	public static final String LANG_CREATE_USER_BODY_INFO_PASSWORD_es_ES="Contrase&#241;a: ";
	public static final String LANG_CREATE_USER_REMIND_es_ES="<p>Para acceder a <a href=\"https://www.miregistrodehoras.com\">miregistrodehoras.com</a> por primera vez deber&#225; modificar la contrase&#241;a inicial y aceptar una serie de t&#233;rminos y condiciones de uso.</p>" +
			"<p>Si tiene dudas sobre este e-mail consulte con el responsable de su Organizaci&#243;n.</p>" +
			"<p>Confiamos que la utilizaci&#243;n de su aplicativo contribuya a dar una  mayor seguridad jur&#237;dica a su Organizaci&#243;n en el &#225;mbito laboral.</p>";

	/** template done **/
	public static final String LANG_REMIND_PASSWORD_USER_SUBJECT_es_ES="Cambio de contraseña en https://www.miregistrodehoras.com";
	public static final String LANG_REMIND_PASSWORD_USER_BODY_es_ES="<p>Queremos comunicarle que se ha solicitado un cambio en su contraseña de acceso a Aplicaci&#243;n <a href=\"https://www.miregistrodehoras.com\">miregistrodehoras</a>.</p>" +
			"<p>Los datos de acceso son los siguientes:</p>" +
			"<p>Pantalla de login: <a href=\"https://www.miregistrodehoras.com\">https://www.miregistrodehoras.com</a></p>";

	/** template done **/
	public static final String LANG_USER_ACTIVE_SUBJECT_es_ES="Alta en su Registro Horario. https://www.miregistrodehoras.com";
	public static final String LANG_USER_ACTIVE_BODY_es_ES="<p>Queremos darle la bienvenida a la Aplicaci&#243;n <a href=\"https://www.miregistrodehoras.com\">miregistrodehoras</a>, en el que ha sido dado de alta a instancias de su Empresa, para el registro diario de jornada establecido en el art. 34.9 del Estatuto de los trabajadores.</p>" +
			"<p>A partir de ahora deber&#225; acceder diariamente a miregistrodehoras.com con sus claves de acceso para informar su inicio y fin de jornada, pausas etc .. De manera on-line, f&#225;cil y segura. Asimismo podr&#225; introducir observaciones en sus registros y consultar las jornadas registradas. Le recomendamos que visualice este <a href=\"https://vimeo.com/461412233/7c70e3d4c3\">v&#237;deo explicativo</a> donde podr&#225; visualizar todas las funcionalidades que ponemos a su disposici&#243;n.</p>" +
			"<p>Pantalla de login: <a href=\"https://www.miregistrodehoras.com\">https://www.miregistrodehoras.com</a></p>";

	/** template done **/
	public static final String LANG_USER_INACTIVE_SUBJECT_es_ES="Baja en Registro horario. https://www.miregistrodehoras.com";
	public static final String LANG_USER_INACTIVE_BODY_es_ES="<p>Queremos notificarle que se ha procedido a dar de baja su usuario en la aplicaci&#243;n  <a href=\"https://www.miregistrodehoras.com\">Aplicaci&#243;n miregistrodehoras</a> por lo que a partir de ahora no podr&#225; acceder a dicha aplicaci&#243;n.</p>" +
			"<p>Si tiene dudas sobre este e-mail consulte con el responsable de su Organizaci&#243;n.</p>" +
			"<p>Confiamos que la utilizaci&#243;n de su aplicativo haya contribuido a dar una  mayor garant&#237;a a su relaci&#243;n laboral.</p>";


	public static final String LANG_CSV_VALIDATE_ERROR_SUBJECT_es_ES="Hay errores en los datos en el fichero CSV en la fila : ";
	public static final String LANG_CSV_VALIDATE_ERROR_BODY_es_ES="<p>A continuaci&#243;n se describen los errores encontrados en la fila del csv : </p>";

	public static final String LANG_CSV_VALIDATE_USER_NIF_EMPTY_es_ES="<p>No se ha indicado el NIF del usuario. Debe indicarse en el campo 1 del csv.</p>";
	public static final String LANG_CSV_VALIDATE_USER_NAME_EMPTY_es_ES="<p>No se ha indicado el nombre del usuario. Debe indicarse en el campo 2 del csv.</p>";
	public static final String LANG_CSV_VALIDATE_USER_SURNAME_EMPTY_es_ES="<p>No se ha indicado los apellidos del usuario. Debe indicarse en el campo 3 del csv.</p>";
	public static final String LANG_CSV_VALIDATE_USER_MAIL_EMPTY_es_ES="<p>No se ha indicado el mail del usuario. Debe indicarse en el campo 4 del csv.</p>";
	public static final String LANG_CSV_VALIDATE_USER_JOBTITLE_EMPTY_es_ES="<p>No se ha indicado el puesto del usuario. Debe indicarse en el campo 5 del csv.</p>";
	public static final String LANG_CSV_VALIDATE_USER_WORKPLACE_EMPTY_es_ES="<p>No se ha indicado el centro de trabajo del usuario. Debe indicarse en el campo 6 del csv.</p>";
	public static final String LANG_CSV_VALIDATE_USER_SALARY_EMPTY_es_ES="<p>No se ha indicado el salario del usuario. Debe indicarse en el campo 7 del csv.</p>";

	public static final String LANG_CSV_VALIDATE_COMPANY_NIF_DUPLICATED_es_ES="<p>El NIF del usuario ya existe. No se pueden crear dos usuarios con el mismo NIF.</p>";
	public static final String LANG_CSV_VALIDATE_ADMIN_EMAIL_INCORRECT_es_ES="<p>El mail del usuario no es correcto. Debe indicarse un mail correcto con el formato nombre@dominio.com.</p>";
	public static final String LANG_CSV_VALIDATE_ADMIN_EMAIL_DUPLICATED_es_ES="<p>El mail del usuario ya existe en el sistema. Es posible que ese mail ya se use como usuario o administrador en otra empresa.</p>";

	public static final String LANG_CSV_VALIDATE_NIF_DUPLICATED_es_ES="<p>El CSV contiene NIF de usuario repetidos.</p>";
	public static final String LANG_CSV_VALIDATE_EMAIL_DUPLICATED_es_ES="<p>El CSV contiene emails de usuario repetidos.</p>";

	public static final String LANG_USER_IMPORT_ERROR_SUBJECT_es_ES="Error de formato al importar usuarios desde fichero CSV en Registro horario. www.miregistrodehoras.com";
	public static final String LANG_USER_IMPORT_ERROR_BODY_es_ES="<p>Se han producido un error en el proceso de importaci&#243;n en la aplicaci&#243;n Aplicaci&#243;n <a href=\"https://www.miregistrodehoras.com\">miregistrodehoras</a>.</p>" +
			"<p>El formato no es correcto, el n&#250;mero de campos del ficheros CSV ha de ser 6, siguiendo el formato: NIF;NOMBRE;APELLIDOS;EMAIL;PUESTO DE TRABAJO;SEDE</p><p>Revise el csv en busca de errores.</p>";

	public static final String LANG_COMPANY_IMPORT_FORMAT_ERROR_SUBJECT_es_ES="Error al importar empresa desde fichero CSV en Registro horario. www.miregistrodehoras.com";
	public static final String LANG_COMPANY_IMPORT_FORMAT_ERROR_BODY_es_ES="<p>Se han producido un error en el proceso de importaci&#243;n en la aplicaci&#243;n Aplicaci&#243;n <a href=\"https://www.miregistrodehoras.com\">miregistrodehoras</a>.</p>" +
			"<p>Revise el fichero CSV y verifique que todos los datos son correctos, sin campos en blanco.</p>";

	public static final String LANG_CSV_VALIDATE_SUBJECT_ERROR_es_ES="Hay valores repetidos en el fichero CSV. No se han podido crear los usuarios.";
	public static final String LANG_CSV_VALIDATE_BODY_ERROR_es_ES="<p>Para evitar errores en la creaci&#243;n de usuarios, no se permiten NIF o emails duplicados. Debe corregir el CSV para realizar la importaci&#243;n.</p>";

	public static final String LANG_USER_IMPORT_SUBJECT_es_ES="Nuevos usuarios desde fichero CSV en Registro horario. www.miregistrodehoras.com";
	public static final String LANG_USER_IMPORT_BODY_es_ES="<p>Se han lanzado un proceso de importaci&#243;n en la aplicaci&#243;n Aplicaci&#243;n <a href=\"https://www.miregistrodehoras.com\">miregistrodehoras</a>.</p>" +
			"<p>Se le enviar&#225;n avisos con el resultado de la importaci&#243;n de cada usuario de forma individual. Si los datos de un usuario son incorrectos se enviar&#225; una notificaci&#243;n. </p><p>Los datos del csv son los siguientes: </p>";

}

