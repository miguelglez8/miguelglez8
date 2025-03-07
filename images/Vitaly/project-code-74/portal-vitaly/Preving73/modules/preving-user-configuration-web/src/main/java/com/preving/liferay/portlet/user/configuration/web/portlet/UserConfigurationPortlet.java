package com.preving.liferay.portlet.user.configuration.web.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.util.PrefsPropsUtil;
import com.opencsv.CSVReader;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.commons.web.keycloak.PrevingKeycloakUtil;
import com.preving.liferay.portlet.commons.web.keycloak.Realm;
import com.preving.liferay.portlet.user.configuration.web.bean.CSVData;
import com.preving.liferay.portlet.user.configuration.web.constants.UserConfigurationPortletKeys;
import com.preving.liferay.portlet.user.configuration.web.util.*;
//import com.preving.liferay.portlet.user.configuration.web.util.PrevingCSVValidatorUtil;
import com.preving.liferay.portlet.user.configuration.web.util.CSVUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * @author agarciap
 */
@Component(
	configurationPid = UserConfigurationPortletKeys.USERCONFIGURATION,
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.preving",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=UserConfiguration",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.name=" + UserConfigurationPortletKeys.USERCONFIGURATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UserConfigurationPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(UserConfigurationPortlet.class);

	private static final String REALNAME 	= com.liferay.portal.util.PrefsPropsUtil.getString("preving.keycloak.configuration.realname");
	private static final String URL 		= com.liferay.portal.util.PrefsPropsUtil.getString("preving.keycloak.configuration.url");
	private static final String USERNAME 	= com.liferay.portal.util.PrefsPropsUtil.getString("preving.keycloak.configuration.username");
	private static final String PASS 		= com.liferay.portal.util.PrefsPropsUtil.getString("preving.keycloak.configuration.pass");
	private static final String CLIENT_ID 	= com.liferay.portal.util.PrefsPropsUtil.getString("preving.keycloak.configuration.clientid");
	private static final String SECRET 		= PrefsPropsUtil.getString("preving.keycloak.configuration.secret");
	private static int len = 8; //longitud de la clave
	private static String charsDigits = "0123456789";
	private static String charsLowercase = "abcdefghijklmnopqrstuvwxyz";
	private static String charsUppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String chars = charsDigits + charsLowercase + charsUppercase;
	private static boolean hasDigits = false, hasLowercase = false, hasUppercase = false;
	private static Random rnd = new Random();
	private static StringBuilder sb = null;
	private static int[] arrPos = null;
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		PortletPreferences preferences = renderRequest.getPreferences();

		String importHelpText = LocalizationUtil.getPreferencesValue(preferences, UserConfigurationPortletKeys.CONFIGURATION_IMPORT_HELP_TEXT, themeDisplay.getLanguageId());

		List<User> userList = UserLocalServiceUtil.getGroupUsers(themeDisplay.getScopeGroupId());

		try {
			User userCreator = UserLocalServiceUtil.getUser(themeDisplay.getScopeGroup().getCreatorUserId());
			boolean hasSiteAdminRole = PrevingRoleUtil.hasSiteAdminRole(themeDisplay.getScopeGroupId(), userCreator.getUserId());
			boolean hasUserRole = PrevingRoleUtil.hasUserRole(themeDisplay.getScopeGroupId(), userCreator.getUserId());

			if(!(hasSiteAdminRole || hasUserRole)) {
				System.out.println(" + remove userCreator");
				userList.remove(userCreator);
			}
		} catch (PortalException e) {
			_log.error(e);
		}

		userList.sort(Comparator.comparing(User::getModifiedDate).reversed());

		renderRequest.setAttribute(UserConfigurationPortletKeys.USER_LIST, userList);
		renderRequest.setAttribute(UserConfigurationPortletKeys.CONFIGURATION_IMPORT_HELP_TEXT, importHelpText);

		super.doView(renderRequest, renderResponse);
	}

	public void create(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String name 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_NAME, "");
		String surname	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_SURNAME, "");
		String nif 		= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_NIF, "");
		String email 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_EMAIL, "");
		String salary 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_SALARY, "");
		String strWorkCenter 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_WORK_CENTER, "");
		String cmbWorkCenter =  ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_WORK_CMB_CENTER,"0");
		String jobTitle 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_JOB_TITLE, "");
		String endDate 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_END_DATE, "");
		String genre 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_GENRE, "");
		String active 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_ACTIVE, "");
		String admin 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_ADMIN, "");
		String language 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_LANGUAGE, "es_ES");

		if(_log.isDebugEnabled()){
			_log.debug("groupId: " + themeDisplay.getScopeGroupId());
			_log.debug("email: " + email);
		}

		UserData userData = PrevingUserDataUtil.getUserData(themeDisplay.getScopeGroupId(), email);
		if (Validator.isNotNull(userData)){
			SessionErrors.add(actionRequest, "create-user-duplicate-error");
			return ;
		}

		//Genre
		String genreUser = "";
		if(UserConfigurationPortletKeys.USER_GENRE_MALE.equals(genre)){
			genreUser = "male";
		}else if(UserConfigurationPortletKeys.USER_GENRE_FEMALE.equals(genre)){
			genreUser = "female";
		}

		//Is admin
		boolean isAdmin = false;
		if(UserConfigurationPortletKeys.USER_ADMIN_YES.equals(admin)){
			isAdmin = true;
		}

		String passwordRandon = RandomStringUtils.random(8,true, true);

		User user = PrevingUserUtil.createUser(themeDisplay.getCompanyId(), themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
				themeDisplay.getPortalURL(), themeDisplay.getPathMain(),
				name, surname,
				nif, email, passwordRandon, jobTitle, Long.valueOf(cmbWorkCenter), salary,
				genreUser, endDate, active, admin, LocaleUtil.fromLanguageId(language), strWorkCenter);

		//Keycloak create user
		PrevingKeycloakUtil.operationUser(true,
				themeDisplay.getScopeGroupId(), user.getUserId(), user.getEmailAddress(), name, surname, nif, "", passwordRandon, endDate, isAdmin);


		if (Validator.isNotNull(user)) {
			SessionMessages.add(actionRequest, "create-user-success");
		} else {
			SessionErrors.add(actionRequest, "create-user-error");
		}

	}

	public void saveUser(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long userId 	= ParamUtil.getLong(actionRequest, UserConfigurationPortletKeys.USER_ID, 0);
		String firstName = ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_NAME, "");
		String lastName	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_SURNAME, "");
		String nif 		= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_NIF, "");
		String email 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_EMAIL, "");
		String salary 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_SALARY, "");

		String strWorkCenter = ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_WORK_CENTER, "");

		String cmbWorkCenter = ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_WORK_CMB_CENTER, "0");

		String jobTitle = ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_JOB_TITLE, "");
		String endDate 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_END_DATE, "");
		String genre 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_GENRE, "");
		String active 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_ACTIVE, "");
		String admin 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_ADMIN, "");
		String language 	= ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_LANGUAGE, "es_ES");
		String notificationEndDate = ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.NOTIFICATION_END_DATE, "");

		PrevingUserUtil.editUser(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
				userId, firstName, lastName, nif, email, jobTitle, Long.valueOf(cmbWorkCenter), salary, genre, notificationEndDate, endDate, active, admin, language,strWorkCenter);


		_log.info("PASA !! EDIT");
		//Is admin
		boolean isAdmin = false;
		if(UserConfigurationPortletKeys.USER_ADMIN_YES.equals(admin)){
			isAdmin = true;
		}

		//Keycloak create user
		PrevingKeycloakUtil.operationUser(false,
				themeDisplay.getScopeGroupId(), userId, email, firstName, lastName, nif, "", null, endDate, isAdmin);
	}

	public void importUsers(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

		String errorKey = "";
		int row = 0;

		String sourceFileName = uploadRequest.getFileName("csv");
		File file = (File) uploadRequest.getFile("csv");

		if(_log.isDebugEnabled()){
			_log.debug("uploadRequest: " + uploadRequest.getSize("csv"));
			_log.debug("sourceFileName: " + sourceFileName);
		}

		List<CSVData> csvDataList = new ArrayList<>();
		CSVReader csvReader = new CSVReader(new FileReader(file),';');
		String[] values = null;
		while ((values = csvReader.readNext()) != null) {

			row++;

			if (_log.isDebugEnabled()) {
				String line = "";
				for(int i = 0; i<values.length; i++){
					line += i + "; " + values[i]+ ", ";
				}
				_log.debug("User: " + line);
			}

			if(values.length !=7) {
				errorKey = "invalid-csv-format";

/*				String subject = UserConfigurationPortletKeys.LANG_USER_IMPORT_ERROR_SUBJECT_es_ES;
				String body = UserConfigurationPortletKeys.LANG_WELCOME_es_ES
						+ UserConfigurationPortletKeys.LANG_USER_IMPORT_ERROR_BODY_es_ES;
				body += getCSVLineWithFormat(values, row);
				body += UserConfigurationPortletKeys.LANG_FOOTER_es_ES;*/

				String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", themeDisplay.getLocale()), "user.csv.import.validation.error.import");
				String body = LanguageUtil.get(ResourceBundle.getBundle("content/Language", themeDisplay.getLocale()), "user.csv.import.validation.error.message");
				body += getCSVLineWithFormat(values, row);

				NotificacionUtil.sendNotificacion(themeDisplay.getUserId(), themeDisplay.getUserId(), subject , body);
				PrevingMailUtil.sendMail(themeDisplay.getUserId(), subject, body );

				continue;
			}


			try {
				_log.info("entro1");
				List<String> companyDataCorrect = CSVUtil.isUserDataCorrect(themeDisplay.getCompanyId(), values[0], values[1], values[2], values[3], values[4], values[5], values[6]);

				if(companyDataCorrect.size() > 0){

					errorKey = "invalid-csv-errors";

					String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", themeDisplay.getLocale()), "user.csv.import.validation.error.title");
					String body = LanguageUtil.get(ResourceBundle.getBundle("content/Language", themeDisplay.getLocale()), "user.csv.import.validation.error");
					body += "<ul>";
					for(String errorMessage:companyDataCorrect){
						body += "<li>" + errorMessage + "</li>";
					}
					body += "</ul>";

					NotificacionUtil.sendNotificacion(themeDisplay.getUserId(), themeDisplay.getUserId(), subject , body);
					PrevingMailUtil.sendMail(themeDisplay.getUserId(), subject, body);

				}else {
					//Add if all data are correct.
					String locale = "es_ES";
					if(values[6].equals("ca") || values[6].equals("CA")){
						locale = "ca_ES";
					}else if(values[6].equals("es") || values[6].equals("ES")){
						locale = "es_ES";
					}
					csvDataList.add(new CSVData(values[0], values[1], values[2], values[3], values[4], values[5], locale));
				}


			} catch (Exception e) {
/*				String subject = UserConfigurationPortletKeys.LANG_COMPANY_IMPORT_FORMAT_ERROR_SUBJECT_es_ES;
				String body = UserConfigurationPortletKeys.LANG_WELCOME_es_ES
						+ UserConfigurationPortletKeys.LANG_COMPANY_IMPORT_FORMAT_ERROR_BODY_es_ES;
				body += getCSVLineWithFormat(values, row);
				body += UserConfigurationPortletKeys.LANG_FOOTER_es_ES;*/

				String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", themeDisplay.getLocale()), "user.csv.import.validation.error.title");
				String body = LanguageUtil.get(ResourceBundle.getBundle("content/Language", themeDisplay.getLocale()), "user.csv.import.validation.error");
				body += getCSVLineWithFormat(values, row);

				NotificacionUtil.sendNotificacion(themeDisplay.getUserId(), themeDisplay.getUserId(), subject , body);
				PrevingMailUtil.sendMail(themeDisplay.getUserId(), subject, body );

				_log.error(e);
			}
		}

		actionRequest.setAttribute(UserConfigurationPortletKeys.USERS_CSV_USERS, getCSVWithFormat(themeDisplay.getCompanyId(), file));


		List<String> errors = CSVUtil.duplicatedValues(csvDataList);
		if(errors.size() > 0){

			String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", themeDisplay.getLocale()), "user.csv.import.validation.error.repeated");
			String body = LanguageUtil.get(ResourceBundle.getBundle("content/Language", themeDisplay.getLocale()), "user.csv.import.validation.error.repeated.message");
			body += "<ul>";
			for(String errorMessage:errors){
				body += "<li>" + errorMessage + "</li>";
			}
			body += "</ul>";

			NotificacionUtil.sendNotificacion(themeDisplay.getUserId(), themeDisplay.getUserId(), subject , body);
			PrevingMailUtil.sendMail(themeDisplay.getUserId(), subject, body);

			SessionErrors.add(actionRequest, "create-user-error");
			actionResponse.setRenderParameter("mvcPath","/view.jsp");

		}else{

			String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", themeDisplay.getLocale()), "user.csv.import.validation.new.users");
			String body = LanguageUtil.get(ResourceBundle.getBundle("content/Language", themeDisplay.getLocale()), "user.csv.import.validation.new.users.message");
			body += getCSVWithFormat(themeDisplay.getCompanyId(), file);

			NotificacionUtil.sendNotificacion(themeDisplay.getUserId(), themeDisplay.getUserId(), subject , body);
			PrevingMailUtil.sendMail(themeDisplay.getUserId(), subject, body);

			for (CSVData data : csvDataList) {
				String passwordRandon = generatePasswordRandon();
				importUser(
						themeDisplay.getCompanyId(),
						themeDisplay.getUserId(),
						themeDisplay.getScopeGroupId(),
						themeDisplay.getPortalURL(),
						themeDisplay.getPathMain(),
						data.getName(),
						data.getLastName(),
						data.getNif(),
						data.getEmail(),
						passwordRandon,
						data.getJobTitle(),
						data.getWorkcenter(),
						UserConfigurationPortletKeys.USER_GENRE_MALE,
						null,
						UserConfigurationPortletKeys.USER_ACTIVE_YES,
						data.getLocale()
				);
			}

				SessionMessages.add(actionRequest, "import-success");
				actionResponse.setRenderParameter("mvcPath", "/success_import.jsp");

		}


	}

	private void importUser(long companyId, long creatorUserId, long groupId, String portalURL, String pathMain, String firstName, String lastName,
							String nif, String email, String password, String jobTitle, String workCenter,
							String genre, String endDate, String active, String languageId){

		Message message = new Message();

		//Set destination
		message.setDestinationName(UserConfigurationPortletKeys.MESSAGE_BUS_DESTINATION_NAME);

		//Add fields
		message.put(UserConfigurationPortletKeys.COMPANY_ID, companyId);
		message.put(UserConfigurationPortletKeys.CREATOR_USER_ID, creatorUserId);
		message.put(UserConfigurationPortletKeys.GROUP_ID, groupId);
		message.put(UserConfigurationPortletKeys.PORTAL_URL,portalURL);
		message.put(UserConfigurationPortletKeys.PATH_MAIN,pathMain);
		message.put(UserConfigurationPortletKeys.USER_NAME,firstName);
		message.put(UserConfigurationPortletKeys.USER_SURNAME, lastName);
		message.put(UserConfigurationPortletKeys.USER_NIF, nif);
		message.put(UserConfigurationPortletKeys.USER_EMAIL, email);
		message.put(UserConfigurationPortletKeys.USER_JOB_TITLE, jobTitle);
		message.put(UserConfigurationPortletKeys.USER_WORK_CENTER, workCenter);
		message.put(UserConfigurationPortletKeys.USER_GENRE, genre);
		message.put(UserConfigurationPortletKeys.USER_END_DATE, endDate);
		message.put(UserConfigurationPortletKeys.USER_ACTIVE, active);
		message.put(UserConfigurationPortletKeys.LANGUAGE_ID, languageId);
		message.put(UserConfigurationPortletKeys.USER_PASSWORD, password);

		//Send message
		_messageBus.sendMessage(message.getDestinationName(), message);
		_log.debug("message: " + message.getDestinationName());
		if(_log.isDebugEnabled()){
			_log.debug("message: " + message.getDestinationName());
		}

	}

	public void remindPassword(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		long userId = ParamUtil.getLong(actionRequest, UserConfigurationPortletKeys.USER_ID, 0);
		String mail = ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.USER_EMAIL, "");

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		PrevingUserUtil.remindPassword(userId);

		if(_log.isDebugEnabled()) {
			_log.debug("Remind password to user: " + userId + " " + mail + ".");
		}

		SessionMessages.add(actionRequest, "remind-password-success");

	}

	private String getCSVLineWithFormat(String values[], int position){
		String result = "";

		try {
			result = "Fila " + position +
					": NIF: " + (values.length > 0 ? values[0]:"") +
					", Nombre: " + (values.length > 1 ? values[1]:"")+
					", Apellidos: " + (values.length > 2 ? values[2]:"")+
					", Mail: " + (values.length > 3 ? values[3]:"")+
					", Puesto: " + (values.length > 4 ? values[4]:"")+
					", Sede: " + (values.length > 5 ? values[5]:"");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	private String getCSVWithFormat(long companyId, File file){

		StringBuilder sb = new StringBuilder(5);
		sb.append("<p><ul>");
		try {

			String dataMessageCorrect = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "user.configuration.view.import.correct");
			String dataMessageIncorrect = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "user.configuration.view.import.incorrect");

			CSVReader csvReader = new CSVReader(new FileReader(file),';');
			String[] values = null;
			while ((values = csvReader.readNext()) != null) {

				String row = "";
				String dataMessage = "<span class=\"text-success\">[" + dataMessageCorrect + "]</span>";
				String errors = "";

				List<String> companyDataCorrect = CSVUtil.isUserDataCorrect(companyId, values[0], values[1], values[2], values[3], values[4], values[5], values[6]);

				if(companyDataCorrect.size() > 0){
					dataMessage = "<span class=\"text-danger\">[" + dataMessageIncorrect + "]</span>";
					for(String errorMessage:companyDataCorrect){
						errors += "<span class=\"d-block pl-5\">"+errorMessage+"</span>";
					}
				}

				try {
					row += "<li><p>";
					row += dataMessage;
					row += " NIF " + (values.length > 0 ? values[0]:"");
					row += ", nombre: " + (values.length > 1 ? values[1]:"");
					row += ", apellidos: " + (values.length > 2 ? values[2]:"");
					row += ", email: " + (values.length > 3 ? values[3]:"");
					row += ", puesto: " + (values.length > 4 ? values[4]:"");
					row += ", sede: " + (values.length >5 ? values[5]:"");
					row += "</p>";
					if(companyDataCorrect.size() > 0) {
						row +=  errors;
					}
					row += "</li>";

				} catch (Exception e) {
					row = "";
					_log.error(e.getMessage());
				}

				sb.append(row);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		sb.append("</ul></p>");

		return sb.toString();
	}
	public static String generatePasswordRandon(){
		StringBuilder sb = new StringBuilder(len);
		positions(3); // 3 pos al azar
		int typeChar = 0; // 0: digit, 1: Upper, 2 : lower
		for (int i = 0; i < len; i++){
			if( arrPos[i] != 0 && typeChar == 0){
				sb.append(charsLowercase.charAt(rnd.nextInt(charsLowercase.length()))); //al menos siempre una minuscula
				typeChar++;
			}else if(arrPos[i] != 0 && typeChar == 1){
				sb.append(charsUppercase.charAt(rnd.nextInt(charsUppercase.length()))); //siempre al menos una mayuscula
				typeChar++;
			}else if( arrPos[i] != 0 && typeChar == 2){
				sb.append(charsDigits.charAt(rnd.nextInt(charsDigits.length()))); //siempre al menos un n�mero
				typeChar++;
			}else{
				sb.append(chars.charAt(rnd.nextInt(chars.length()))); // uno cualquiera
			}
		}
		return sb.toString();
	}
	private static void positions(int countPos){
		int pos = -1;
		arrPos = new int[len];
		while(countPos > 0){
			pos = rnd.nextInt(len);
			if(arrPos[pos] == 0){
				arrPos[pos] = 1;
				countPos--;
			}
		}
	}
	@Reference
	private MessageBus _messageBus;

}
