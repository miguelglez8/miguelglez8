package com.adeplus.liferay.portlet.user.web.action;

import com.adeplus.liferay.portlet.commons.web.bean.AppPermission;
import com.adeplus.liferay.portlet.commons.web.bean.RolePermission;
import com.adeplus.liferay.portlet.commons.web.keycloak.AdeplusKeycloakUtil;
import com.adeplus.liferay.portlet.commons.web.permission.AdeplusPermissionUserUtil;
import com.adeplus.liferay.portlet.commons.web.role.AdeplusRolePermissionUtil;
import com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtil;
import com.adeplus.liferay.portlet.mailing.web.mail.UserMailing;
import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.Role;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserCompanyPK;
import com.adeplus.liferay.portlet.user.web.constants.AdeplusUsersPortletKeys;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtil.isValidLimitsApp;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdeplusUsersPortletKeys.ADEPLUSUSERS,
                "mvc.command.name=/user/save"
        },
        service = MVCActionCommand.class
)
public class SaveUserMCVActionCommand implements MVCActionCommand {

   private static Log _log = LogFactoryUtil.getLog(SaveUserMCVActionCommand.class);

   private ThemeDisplay themeDisplay = null;
   private long companyEditId = -1;
   private long userEditId = -1;
   private String name = null;
   private String lastName = null;
   private String nif = null;
   private String email = null;
   private String phone = null;
   private String adminStr = null;
   private String dateEnd = null;
   private String language = null;
   private boolean admin = false;
   private List<Application> appList = null;
   private List<AppPermission> appPermissionList = null;
   private List<RolePermission> roleList = null;
   private boolean isNewUser = false;


   private static UserLocalService _userLocalService;
   @Reference(unbind = "-")
   protected void setUserLocalService(UserLocalService userLocalService) {
      _userLocalService = userLocalService;
   }

   @Override
   public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse)  {

      themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

      companyEditId 	= ParamUtil.getLong(actionRequest, AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT, 0);
      userEditId 	= ParamUtil.getLong(actionRequest, AdeplusUsersPortletKeys.USER_EDIT_ID, 0);
      name 		= ParamUtil.getString(actionRequest, AdeplusUsersPortletKeys.USER_NAME, "");
      lastName 	= ParamUtil.getString(actionRequest, AdeplusUsersPortletKeys.USER_LAST_NAME, "");
      nif		 	= ParamUtil.getString(actionRequest, AdeplusUsersPortletKeys.USER_NIF, "");
      email		= ParamUtil.getString(actionRequest, AdeplusUsersPortletKeys.USER_EMAIL, "");
      phone		= ParamUtil.getString(actionRequest, AdeplusUsersPortletKeys.USER_PHONE, "");
      adminStr		= ParamUtil.getString(actionRequest, AdeplusUsersPortletKeys.USER_ADMIN, "");
      dateEnd		= ParamUtil.getString(actionRequest, AdeplusUsersPortletKeys.USER_DATE_END, "");
      language 	= ParamUtil.getString(actionRequest, AdeplusUsersPortletKeys.USER_LOCALE, "es_ES");

      actionResponse.setRenderParameter(AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT, String.valueOf(companyEditId));
      actionResponse.setRenderParameter("jspPage", "/company.jsp");

      // Apps y roles
      appList = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
      appPermissionList = getAppPermisions(actionRequest, appList);
      roleList = getAppRoles(actionRequest, appList);

      admin = false;
      if(AdeplusUsersPortletKeys.ADMIN_YES.equalsIgnoreCase(adminStr.trim())) admin = true; // es administrador
      _log.info("AdeplusCompaniesPortletKeys.ADMIN_YES: " + AdeplusUsersPortletKeys.ADMIN_YES);
      _log.info("adminStr: '" + adminStr + "'");
      _log.info("admin: " + admin);

      User user = null;

      if(userEditId == 0){ // nuevo usuario
         isNewUser = true;
         if(Validator.isNull(_createNewUserProcess(themeDisplay, actionRequest, actionResponse))) return false;
      }else{ //Modificación usuario
         user = UserLocalServiceUtil.fetchUser(userEditId);
         if(Validator.isNull(user)){
            _log.info("user is null : " + email);
            //ERROR NO EXISTE USUARIO CON ESE ID
            return true;
         }
         if(email.toLowerCase().trim().equals(user.getEmailAddress())){
            //NO HA MODIFICADO EL EMAIL ... NORMAL
            isNewUser = false;
            if(Validator.isNull(user = _updateUserSameEmail(themeDisplay, actionRequest, actionResponse, user)) ) return false;
         }else{
            //HA MODIFICADO EL EMAIL ... DOS CASOS: EXISTE EL USUARIO CON ESE EMAIL O ES UNO NUEVO
            isNewUser = true;
            if(Validator.isNull(user = _updateUserDiferentEmail(themeDisplay, actionRequest, actionResponse, user))) return false;
         }
      }


      //Save permission of all applications.
      boolean changeAppList = AdeplusPermissionUserUtil.saveUserPermissionApplications(user, companyEditId, appPermissionList);
      //Save roles of user
      boolean roleChanged = AdeplusRolePermissionUtil.saveUserRolePermission(user, companyEditId, roleList);

      //Send mail to updated user.
      if(!isNewUser && changeAppList && user.isActive()){
         UserMailing.userUpdate(user,companyEditId ,false);
         //sendEmailBackground( user, BackTaskEmail.EMAIL_UPDATE_USER, null);
         _log.info("NUEVOS POSTACTION : MANDAR CORREO (2)");
      }

      SessionMessages.add(actionRequest, "save-user-success");

        /*actionResponse.setRenderParameter(AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT, String.valueOf(companyEditId));
        actionResponse.setRenderParameter("jspPage", "/success_user.jsp");*/
      actionResponse.setRenderParameter(AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT, String.valueOf(companyEditId));
      actionResponse.setRenderParameter(AdeplusUsersPortletKeys.USER_EDIT_ID, String.valueOf(userEditId));
      actionResponse.setRenderParameter("jspPage", "/success.jsp");
      return true;
   }

   private void sendEmailBackground(User user, int action, String passwordRandon){
      Map map = new HashMap();
      map.put("userId",user.getUserId());
      map.put("action",action);
      map.put("companyEditId",companyEditId);
      map.put("passwordRandon",passwordRandon);

      try {
            /*BackgroundTaskManagerUtil.addBackgroundTask(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
                    BackTaskEmail.class.getName(), BackTaskEmail.class.getName(), map,
                    new ServiceContext());*/
      }catch(Exception e){
         e.printStackTrace();
      }
   }

   //@see obtener las apps marcadas
   private List<AppPermission> getAppPermisions(ActionRequest actionRequest, List<Application> appList){
      List<AppPermission> appPermissionList = new ArrayList<>();
      for(Application app:appList){
         String checkBoxValue = ParamUtil.getString(actionRequest, String.valueOf(app.getApplicationId()));
         appPermissionList.add(new AppPermission(app,"true".equals(checkBoxValue), 0));
      }
      return appPermissionList;
   }

   //@see obtener los roles de las apps
   private List<RolePermission> getAppRoles(ActionRequest actionRequest, List<Application> appList){
      List<RolePermission> roleList = new ArrayList<>();
      List<Role> roleByApplicationId = null;
      String radioValue = null;
      Role role = null;
      for(Application app:appList){
         radioValue = ParamUtil.getString(actionRequest, "app_" + String.valueOf(app.getApplicationId()));
         if(!Validator.isBlank(radioValue) && Validator.isNumber(radioValue)){
            role = RoleLocalServiceUtil.fetchRole(Long.parseLong(radioValue));
            if(Validator.isNotNull(role)) {
               roleByApplicationId = RoleLocalServiceUtil.getRoleByApplicationId(app.getApplicationId());
               for (Role rol : roleByApplicationId) {
                  if (rol.getRoleId() == Long.parseLong(radioValue)) {
                     roleList.add(new RolePermission(rol, true));
                  } else {
                     roleList.add(new RolePermission(rol, false));
                  }
               }
            }
         }else{
            roleByApplicationId = RoleLocalServiceUtil.getRoleByApplicationId(app.getApplicationId());
            for (Role rol : roleByApplicationId) {
               roleList.add(new RolePermission(rol, false));
            }
         }
      }
      return roleList;
   }


   private User _updateUserSameEmail(ThemeDisplay themeDisplay, ActionRequest actionRequest, ActionResponse actionResponse, User user){
      //if(!isValidLimitsApp( themeDisplay.getCompanyId(), companyEditId,  admin, user,appPermissionList)){
      String result = isValidLimitsApp( themeDisplay.getCompanyId(), companyEditId,  admin, user,appPermissionList);
      if(!result.isEmpty()){
         SessionErrors.add(actionRequest, "error-create-limits");
         actionResponse.setRenderParameter(AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT, String.valueOf(companyEditId));
         actionResponse.setRenderParameter(AdeplusUsersPortletKeys.USER_EDIT_ID, String.valueOf(userEditId));
         actionResponse.setRenderParameter("error-limit-apps-name", result);
         actionResponse.setRenderParameter("jspPage", "/user.jsp");
         return null;
      }

      user = AdeplusUserUtil.updateUser(
              themeDisplay.getCompanyId(),
              userEditId, companyEditId,
              name, lastName, nif, email, phone, admin, dateEnd,
              LocaleUtil.fromLanguageId(language));
      //sendKeycloackUserBackground(user, nif, phone, false,"");
      return user;
   }


   private User _updateUserDiferentEmail(ThemeDisplay themeDisplay, ActionRequest actionRequest, ActionResponse actionResponse, User user){
      long userID_nowEdit = user.getUserId();
      String userEmail_nowEdit = user.getEmailAddress();
      User userEmail = UserLocalServiceUtil.fetchUserByEmailAddress(themeDisplay.getCompanyId(), email);


      //Es un cambio de email a uno nuevo¿?
      if(userEditId != 0){ // quitamos el actual
         quitUserFromCompany( user.getUserId(), user.getEmailAddress());
      }

      if(Validator.isNotNull(userEmail)) { //Existe ese usuario .. lo asociamos y actualizamos
         //if(!isValidLimitsApp( themeDisplay.getCompanyId(), companyEditId,  admin, user,appPermissionList)){
         String result = isValidLimitsApp( themeDisplay.getCompanyId(), companyEditId,  admin, user,appPermissionList);
         if(!result.isEmpty()){
            addUserFromCompany(user.getUserId(), user.getEmailAddress()); // lo dejamos como estaba
            SessionErrors.add(actionRequest, "error-create-limits");
            actionResponse.setRenderParameter(AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT, String.valueOf(companyEditId));
            actionResponse.setRenderParameter("error-limit-apps-name", result);
            actionResponse.setRenderParameter("jspPage", "/user.jsp");
            return null;
         }


         //asociar el que ya existe
         user = AdeplusUserUtil.updateUser(
                 themeDisplay.getCompanyId(),
                 userEmail.getUserId(), companyEditId,
                 name, lastName, nif, email, phone, admin, dateEnd,
                 LocaleUtil.fromLanguageId(language));
         //sendKeycloackUserBackground(user, nif, phone, false,"");
      }else{ // no exite ... crearlo
         isNewUser = true;
         _log.info("NO EXISTE, CREAR USUARIO Y CONTRASEÑA");
         String passwordRandon = AdeplusUserUtil.generatePasswordRandon();
         user = AdeplusUserUtil.createUser(
                 themeDisplay.getUserId(),
                 themeDisplay.getCompanyId(),
                 themeDisplay.getPortalURL(),
                 themeDisplay.getPathMain(),
                 name, lastName, nif, email, passwordRandon,
                 phone, admin, dateEnd,companyEditId,
                 LocaleUtil.fromLanguageId(language));
         //Crear en keycloak:
         AdeplusUserUtil.createUserKeycloak(companyEditId, user, nif, phone, passwordRandon, "", true,appPermissionList);
         //Send mail to created user.
         UserMailing.userCreate(user, companyEditId, passwordRandon,false);
         //sendEmailBackground( user, BackTaskEmail.EMAIL_NEW_USER, passwordRandon);
      }

      return user;
   }

   private void quitUserFromCompany(long userID_nowEdit, String userEmail_nowEdit){
      //QUITAR EL ACTUAL
      UserCompanyPK userpk = new UserCompanyPK();
      userpk.userId = userEditId;
      userpk.compId = companyEditId;
      try {
         UserCompanyLocalServiceUtil.deleteUserCompany(userpk);
      } catch (PortalException e) {
         e.printStackTrace();
      }
      //verificar si esta asoc a otra empresa, sino deshabilitarlo en KC
      List<UserCompany> userCompanyList = UserCompanyLocalServiceUtil.getCompaniesFromUser(userID_nowEdit);
      if(userCompanyList.size() == 0) {
         AdeplusKeycloakUtil.enableUserFromKC(userEmail_nowEdit, false);
      }else{
         AdeplusKeycloakUtil.enableUserFromKC(userEmail_nowEdit, true);
      }

   }

   private void addUserFromCompany(long userID_nowEdit,  String userEmail_nowEdit){
      UserCompanyPK userpk = new UserCompanyPK();
      userpk.userId = userEditId;
      userpk.compId = companyEditId;
      try {
         UserCompanyLocalServiceUtil.createUserCompany(userpk);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   //@see crear nuevo usuario en en la compañia
   private User  _createNewUserProcess(ThemeDisplay themeDisplay, ActionRequest actionRequest, ActionResponse actionResponse){
      boolean isNew = true;
      // Validar que no esta duplicado por nif o email dentro de la compañia
      List<UserCompany> usersByNifEmailCompId = UserCompanyLocalServiceUtil.getUsersByNifEmailCompId(nif, email, companyEditId);
      if(usersByNifEmailCompId.size() > 0){
         SessionErrors.add(actionRequest, "error-create-duplicated");
         actionResponse.setRenderParameter("jspPage", "/user.jsp");
         return null;
      }
      List<UserCompany> usersByNifCompId = UserCompanyLocalServiceUtil.getUsersByNifCompId(nif, companyEditId);
      if(usersByNifCompId.size() > 0){
         SessionErrors.add(actionRequest, "error-create-nif");
         actionResponse.setRenderParameter("jspPage", "/user.jsp");
         return null;
      }
      List<UserCompany> usersByEmailCompId = UserCompanyLocalServiceUtil.getUsersByEmailCompId(email, companyEditId);
      if(usersByEmailCompId.size() > 0){
         SessionErrors.add(actionRequest, "error-create-email");
         actionResponse.setRenderParameter("jspPage", "/user.jsp");
         return null;
      }

      //existe ese email
      User userByEmail = AdeplusUserUtil.getUserByEmail(companyEditId, email);
      if(Validator.isNotNull(userByEmail)) isNew = false;

      //Es un cambio de email a uno nuevo¿?
      if(isNew && userEditId != 0){ // quitamos el actual
         User userOld = UserLocalServiceUtil.fetchUser(userEditId);
         quitUserFromCompany( userOld.getUserId(), userOld.getEmailAddress());
      }


      //if(!isValidLimitsApp( themeDisplay.getCompanyId(), companyEditId,  admin, null,appPermissionList)){
      String result = isValidLimitsApp( themeDisplay.getCompanyId(), companyEditId,  admin, null,appPermissionList);
      if(!result.isEmpty()){
         SessionErrors.add(actionRequest, "error-create-limits");
         actionResponse.setRenderParameter(AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT, String.valueOf(companyEditId));
         actionResponse.setRenderParameter(AdeplusUsersPortletKeys.USER_EDIT_ID, String.valueOf(userEditId));
         actionResponse.setRenderParameter("error-limit-apps-name", result);
         actionResponse.setRenderParameter("jspPage", "/user.jsp");
         return null;
      }

      //crearlo
      String passwordRandon = AdeplusUserUtil.generatePasswordRandon();
      User user = AdeplusUserUtil.createUser(
              themeDisplay.getUserId(),
              themeDisplay.getCompanyId(),
              themeDisplay.getPortalURL(),
              themeDisplay.getPathMain(),
              name, lastName, nif, email, passwordRandon,
              phone, admin, dateEnd,companyEditId,
              LocaleUtil.fromLanguageId(language));


      //Save permission of all applications.
      boolean appChanged = AdeplusPermissionUserUtil.saveUserPermissionApplications(user, companyEditId, appPermissionList);

      //Save roles of user
      boolean roleChanged = AdeplusRolePermissionUtil.saveUserRolePermission(user, companyEditId, roleList);

      //Crear en keycloak:
      AdeplusUserUtil.createUserKeycloak(companyEditId, user, nif, phone, passwordRandon, "", true,appPermissionList);

      //Send mail to created user.
      if(isNew) {
         //sendEmailBackground( user, BackTaskEmail.EMAIL_NEW_USER, passwordRandon);
         UserMailing.userCreate(user, companyEditId, passwordRandon, false);
      }else if(appChanged){
         //sendEmailBackground( user, BackTaskEmail.EMAIL_UPDATE_USER, null);
         UserMailing.userUpdate(user, companyEditId,false);
      }

      return user;
   }

   private void  sendKeycloackUserBackground(User user, String nif, String phone, boolean nuevo, String passwordRandon){
      Map map = new HashMap();
      map.put("userId",user.getUserId());
      map.put("nif",nif);
      map.put("phone",phone);
      map.put("companyEditId",companyEditId);
      map.put("nuevo",nuevo);
      map.put("passwordRandon",passwordRandon);
      map.put("dateEnd",dateEnd);
      map.put("admin",admin);
      try {
            /*BackgroundTaskManagerUtil.addBackgroundTask(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
                    BackTaskKeycloakUser.class.getName(), BackTaskKeycloakUser.class.getName(), map,
                    new ServiceContext());*/
      }catch(Exception e){
         e.printStackTrace();
      }
   }


}