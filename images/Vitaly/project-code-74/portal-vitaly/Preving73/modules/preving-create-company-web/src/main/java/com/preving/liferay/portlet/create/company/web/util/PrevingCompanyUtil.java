package com.preving.liferay.portlet.create.company.web.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.DuplicateGroupException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.sites.kernel.util.SitesUtil;
import com.preving.liferay.portlet.calendar.manager.model.*;
import com.preving.liferay.portlet.calendar.manager.service.*;
import com.preving.liferay.portlet.create.company.web.bean.CompanyBean;
import com.preving.liferay.portlet.mailing.web.mail.CompanyMailing;

import java.util.*;

public class PrevingCompanyUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingCompanyUtil.class);

    public static Group createCompany(String name, long creatorUserId, String cif,  String description, ServiceContext serviceContext){

        Group group = null;
        User creatorUser = null;

        try {
            creatorUser = UserLocalServiceUtil.getUser(creatorUserId);
        } catch (PortalException e) {
            e.printStackTrace();
        }

        if(creatorUserId == 0 || Validator.isBlank(name) || Validator.isBlank(description) || Validator.isNull(creatorUser)){

            String info = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.csv.error.name.empty");
            NotificacionUtil.sendNotificacion(creatorUserId, creatorUserId, info , info);

            _log.error("Error: Incomplete data. UserId, name or description can't be empty.");
            return null;
        }


        /* En multi se permite repetir cif
        if(Validator.isNotNull(CompanyConfLocalServiceUtil.getCompanyConfByCif(cif))){

            String info = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.csv.error.cif.repeated");
            NotificacionUtil.sendNotificacion(creatorUserId, creatorUserId, info , info);

            _log.error("The company with cif " + cif + " already exists.");

            return null;
        }*/
        Map<Locale,String> nameMap = new HashMap<Locale, String>();
        Map<Locale,String> descriptionMap = new HashMap<Locale, String>();

        nameMap.put(LocaleUtil.getDefault(), name);
        descriptionMap.put(LocaleUtil.getDefault(), description);

        String friendlyURL = "/";

        String[] friendly = name.split(" ");

        int pos = 1;
        for(String s:friendly){
            friendlyURL += s.toLowerCase().replaceAll("[^a-zA-Z0-9]+","");
            if(pos<friendly.length){
                friendlyURL += "-";
            }
            pos++;
        }

        try {
            group = GroupLocalServiceUtil.fetchGroup(20101,name);
            if(Validator.isNotNull(group)) return group;

            group = GroupLocalServiceUtil.addGroup(
                    creatorUserId,
                    GroupConstants.DEFAULT_PARENT_GROUP_ID,
                    Group.class.getName(),
                    ClassNameLocalServiceUtil.getClassNameId(Group.class.getName()),
                    GroupConstants.DEFAULT_LIVE_GROUP_ID,
                    nameMap,
                    descriptionMap,
                    GroupConstants.TYPE_SITE_PRIVATE,
                    true,
                    GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION,
                    friendlyURL,
                    true,
                    true,
                    serviceContext
            );


            if(_log.isDebugEnabled()){
                _log.debug("Created new group: ");
                _log.debug("   - id: " + group.getGroupId());
                _log.debug("   - name: " + group.getName());
                _log.debug("   - friendlyURL: " + friendlyURL);
                _log.debug("   - friendlyURL: " + group.getFriendlyURL());
            }

        } catch (DuplicateGroupException dge) {

            String info = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.csv.error.name.repeated");
            NotificacionUtil.sendNotificacion(creatorUserId, creatorUserId, info , info);

            _log.error("The company already exists.", dge);

        } catch (PortalException e) {

            String info = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.csv.error.row.title");
            NotificacionUtil.sendNotificacion(creatorUserId, creatorUserId, info , info);

            _log.error("Error creating site.", e);
        }

        return group;
    }

    public static void addUserToCompany(long userId, long groupId){

        try {

            if(userId > 0 && groupId > 0){
                GroupLocalServiceUtil.addUserGroup(userId, groupId);
            }else{
                NotificacionUtil.sendNotificacion(userId, userId, "Error añadiendo el usuario a la empresa.", "No se ha podido añadir el usuario a la empresa.");
                _log.error("Error: Incomplete data. UserId can't be empty and group can't be null.");
            }

        } catch (Exception e) {
            NotificacionUtil.sendNotificacion(userId, userId, "Error añadiendo el usuario a la empresa.", "No se ha podido añadir el usuario " + userId + " a la empresa.");
            _log.error("Error adding user " + userId + " to group .");
        }
    }

    public static void addTemplateToCompany(Group group, long templateId) {

        if(Validator.isNull(group) || templateId == 0){
            _log.error("Error: Incomplete data. TemplateId can't be empty and group can't be null.");
            NotificacionUtil.sendNotificacion(group.getCreatorUserId(), group.getCreatorUserId(), "Error al obtener la plantilla de sitio web.", "Debe asegurarse que la plantilla de sitio web esta creada correctamente y configurada en el modulo de creación de empresas.");

            return ;
        }

        try {

            // Get the template.
            long layoutSetPrototypeId = templateId;
            LayoutSetPrototype layoutSetPrototype = LayoutSetPrototypeLocalServiceUtil.fetchLayoutSetPrototype(layoutSetPrototypeId);

            LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(group.getGroupId(), true);
            layoutSet.setLayoutSetPrototypeLinkEnabled(true);
            layoutSet.setLayoutSetPrototypeUuid(layoutSetPrototype.getUuid());

            SitesUtil.setMergeFailCount(layoutSetPrototype, 0);

            LayoutSetLocalServiceUtil.updateLayoutSet(layoutSet);

            // Merge Site template pages to organization site.
            SitesUtil.mergeLayoutSetPrototypeLayouts(group, layoutSet);

            if(_log.isDebugEnabled()) {
                _log.debug("layoutSetPrototypeId: " + layoutSetPrototypeId);
                _log.debug("layoutSetPrototype: " + (Validator.isNotNull(layoutSetPrototype)?layoutSetPrototype.getName():"Not found."));
            }

        } catch (Exception e) {
            NotificacionUtil.sendNotificacion(group.getCreatorUserId(), group.getCreatorUserId(), "Error creando el sitio con la plantilla proporcionada.", "No se ha podido crear el sitio con la plantilla.");
            _log.error("Failed trying a site template when creating group.", e);
        }

    }

    public static CompanyConf createCompanyConfiguration(long companyId, long groupId, long adminUserId, String cif, String source, Date startDate, Date implementationDate, Date endDate, long clientId, long contractId){

        CompanyConf companyConf = null;

        try {

            companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCompanyIdAndGroupId(companyId, groupId);
             if(Validator.isNull(companyConf)){
                 companyConf = CompanyConfLocalServiceUtil.createCompanyConf(CounterLocalServiceUtil.increment(CompanyConf.class.getName()));
             }

            companyConf.setCompanyId(companyId);
            companyConf.setGroupId(groupId);
            companyConf.setUserId(adminUserId);
            companyConf.setCif(cif);
            companyConf.setSource(source);

            if(clientId != 0L && contractId != 0L) {
                companyConf.setClientId(clientId);
                companyConf.setContracId(contractId);
            }


            companyConf.setNotificationNotWorkable(true);
            companyConf.setNotificationFinishDateSign(true);
            companyConf.setNotificationAdminPeriodicity("monthly");

            companyConf.setMaxHoursDay(false);
            companyConf.setMaxHoursDayValue(10);
            companyConf.setMaxHoursWeek(false);
            companyConf.setMaxHoursWeekValue(40);
            companyConf.setSavePastDays(true);
            companyConf.setSavePastDaysValue(7);
            companyConf.setEditSigns(true);
            companyConf.setEditSignsValue(15);

            Date date = new Date();

            companyConf.setStartDate(startDate);
            companyConf.setImplantationDate(implementationDate);
            companyConf.setDeleteDate(endDate);
            companyConf.setCreateDate(date);
            companyConf.setModifiedDate(date);

            CompanyConfLocalServiceUtil.updateCompanyConf(companyConf);

        } catch (Exception e) {
            _log.error("Failed creating a site configuration companyConf.", e);
        }

        return companyConf;
    }

    public static List<CompanyBean> getAllCompaniesConfiguration(long companyId){

        List<CompanyBean> companyBeanList = new ArrayList<>();

        List<Group> groupList = GroupLocalServiceUtil.getGroups(companyId, GroupConstants.DEFAULT_PARENT_GROUP_ID, true);

        for(Group group : groupList){

			//_log.debug(group.getGroupId() + " " + group.getName("es_ES"));
            if(("/global".equals(group.getFriendlyURL()) || ("/guest".equals(group.getFriendlyURL())))){
                continue;
            }

            // Get all configurations in bd.
            CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCompanyIdAndGroupId(companyId, group.getGroupId());

            CompanyBean companyBean = new CompanyBean();

            // Set the name.
            try {
                companyBean.setName(group.getName(PortalUtil.getSiteDefaultLocale(group)));
            } catch (PortalException e) {
                companyBean.setName(group.getName());
                _log.error("Error getting name.");
            }

            // Set active.
            companyBean.setActive(group.isActive());

            // Link with the friendlyURL.
            String friendlyURL = "<a href=\"/group" + group.getFriendlyURL()+"\" target=\"_blank\">" + group.getFriendlyURL() + "</a>";
            companyBean.setFriendlyURL(friendlyURL);

            //Set groupId.
            companyBean.setGroupId(group.getGroupId());

            if(Validator.isNotNull(companyConf)){
                companyBean.setCompanyConfId(companyConf.getCompanyConfId());
                companyBean.setCreatorUserId(companyConf.getUserId());

                companyBean.setCif(companyConf.getCif());
                companyBean.setNotificationNotWorkable(companyConf.getNotificationNotWorkable());
                companyBean.setNotificationAdminPeriodicity(companyConf.getNotificationAdminPeriodicity());
                companyBean.setMaxHoursDay(companyConf.getMaxHoursDayValue());
                companyBean.setMaxHoursWeek(companyConf.getMaxHoursWeekValue());
                companyBean.setStartDate(companyConf.getStartDate());
                companyBean.setSource(companyConf.getSource());
                companyBean.setDescription(companyConf.getDescription());
                companyBean.setAgreement(companyConf.getAgreement());
                companyBean.setObservations(companyConf.getObservations());
                companyBean.setCreateDate(companyConf.getCreateDate());
                companyBean.setModifiedDate(companyConf.getModifiedDate());
                companyBean.setDeleteDate(companyConf.getDeleteDate());

                UserData userData = getUserData(companyConf.getGroupId(), companyConf.getUserId());
                String fullName = Validator.isNotNull(userData)?userData.getName() + " " + userData.getLastName():Validator.isNotNull(companyBean.getUser())?companyBean.getUser().getFullName():"";
                String emailAddress = Validator.isNotNull(userData)?userData.getEmail() :Validator.isNotNull(companyBean.getUser())?companyBean.getUser().getEmailAddress():"";

                companyBean.setAdminFullName(fullName);
                companyBean.setAdminEmail(emailAddress);
            }

            companyBeanList.add(companyBean);
        }

        return companyBeanList;
    }

    public static UserData getUserData(long groupId, long userId){

        List<UserData> list = UserDataLocalServiceUtil.findByGroupIdAndUserId(groupId, userId);

        /*if(_log.isDebugEnabled()){
            _log.debug("groupId " + groupId + " userId " + userId +" UserData size " + list.size());
        }*/

        if(list.size() > 0){
            return list.get(0);
        }

        return null;
    }

    public static void addUserToGroup(User user, long companyId, long groupId, String roleName){

        if(Validator.isNotNull(user) && companyId > 0 && groupId > 0 && !Validator.isBlank(roleName)) {

            try {

                Role role = RoleLocalServiceUtil.fetchRole(companyId, roleName);

                UserGroupRoleLocalServiceUtil.addUserGroupRoles(user.getUserId(), groupId, new long[]{role.getRoleId()});

                if (_log.isDebugEnabled()) {
                    _log.debug(" companyId : " + companyId);
                    _log.debug(" groupId : " + groupId);
                    _log.debug(" roleName : " + roleName);
                    _log.debug(" role : " + role.getRoleId());
                    _log.debug(" user email: " + user.getEmailAddress());
                }

            } catch (Exception e) {
                _log.error("Error adding role to user.", e);
            }

        }else{
            _log.error("Role is not assigned to user.");
        }
    }

    public static void updateCompany(long companyConfId, String name, String cif, Date startDate, Date implementationDate, Date endDate, String source, long clientId, long contractId){

        try {

            CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConf(companyConfId);

            if(Validator.isNotNull(companyConf)) {
                Date lastDeleteDate=companyConf.getDeleteDate();
                companyConf.setCif(cif);
                companyConf.setDeleteDate(endDate);
                companyConf.setSource(source);
                companyConf.setStartDate(startDate);
                companyConf.setImplantationDate(implementationDate);

                CompanyConfLocalServiceUtil.updateCompanyConf(companyConf);
                Group group = GroupLocalServiceUtil.getGroup(companyConf.getGroupId());

                group.setName(name);

                GroupLocalServiceUtil.updateGroup(group);

                //Send mail
                if(Validator.isNotNull(endDate) && endDate.before(new Date()) && !lastDeleteDate.equals(endDate) ){

                    if(_log.isDebugEnabled()){
                        _log.debug("DeleteUserRolUsers. End date before today.");
                    }

                    PrevingRoleUtil.deleteUserRolUsers(group.getGroupId(), UserLocalServiceUtil.getGroupUsers(companyConf.getGroupId()));

                    //Send mail to administrators.
                    List<User> listAdminIdFromGroup = PrevingUserUtil.getListAdminIdFromGroup(companyConf.getGroupId());

                    if(_log.isDebugEnabled()){
                        _log.debug("adminList size " + listAdminIdFromGroup.size());
                    }

                    for(User user: listAdminIdFromGroup){

                        CompanyMailing.companyDeleteToAdministrator(user, name, cif);

                        if(_log.isDebugEnabled()){
                            _log.debug("admin user " + user.getEmailAddress() + " mail notification " /* + companyEndDateTemplate*/);
                        }
                    }


                }
            }

        } catch (PortalException e) {
            _log.error("Error updating company data.", e);
        }

    }


    public static void deleteCompany(long companyConfId, long userId){

        String companyName = "";
        String companyCIF = "";

        User user = null;

        try {
            user = UserLocalServiceUtil.getUser(userId);
        } catch (PortalException e) {
            e.printStackTrace();
        }

        try {

            CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConf(companyConfId);
            if(Validator.isNotNull(companyConf)) {

                Group group = GroupLocalServiceUtil.getGroup(companyConf.getGroupId());
                companyName = group.getName(group.getDefaultLanguageId());
                companyCIF = companyConf.getCif();

                List<Sign> signsByCompanyIdAndGroupId = SignLocalServiceUtil.getSignsByCompanyIdAndGroupId(companyConf.getCompanyId(), companyConf.getGroupId());
                if(!signsByCompanyIdAndGroupId.isEmpty()){

                    String info = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.mail.subject.error.deleting");
                    NotificacionUtil.sendNotificacion(companyConf.getUserId(), companyConf.getUserId(), info , info);

                    return;
                }

                // Delete all holidays for this company.
                List<Holiday> holidayList = HolidayLocalServiceUtil.getHolidaysFromGroup(companyConf.getCompanyId(), companyConf.getGroupId());
                for(Holiday holiday:holidayList){
                    HolidayLocalServiceUtil.deleteHoliday(holiday);
                }

                // Delete all activities for this company.
                List<Activity> activityList = ActivityLocalServiceUtil.getActivitiesFromGroup(companyConf.getCompanyId(), companyConf.getGroupId());
                for(Activity activity:activityList){
                    ActivityLocalServiceUtil.deleteActivity(activity);
                }

                // Delete group.
                GroupLocalServiceUtil.deleteGroup(companyConf.getGroupId());

                // Delete configuration
                CompanyConfLocalServiceUtil.deleteCompanyConf(companyConf);

                CompanyMailing.companyDeleteToAdministrator(user, companyName, companyCIF);

                // Delete users from group.
                //UserLocalServiceUtil.deleteUser(companyConf.getUserId());

                //Send mail
                CompanyMailing.companyDeleteToAdministrator(user,companyName, companyCIF );

            }

        } catch (PortalException e) {

            String info = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.mail.subject.error.deleting");
            NotificacionUtil.sendNotificacion(userId, userId, info , info);

            _log.error("Error deleting company.", e);
        }

    }

}
