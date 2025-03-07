package com.preving.liferay.portlet.create.company.web.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.CompanyConf;
import com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil;
import com.preving.liferay.portlet.create.company.web.constants.CreateCompanyPortletKeys;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PrevingCreateCompanyUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingCreateCompanyUtil.class);

    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    public static Group createCompany(long companyId, String companyName, User creatorUser, String cif, String source, Date stDate, Date impDate, Date delDate, long clientId, long contractId ){

        //Create a new site.
        String siteDescription = "Registro de horas de " + companyName+".";
        Group group = PrevingCompanyUtil.createCompany(companyName, creatorUser.getUserId(), cif, siteDescription, new ServiceContext());

        if (Validator.isNotNull(group)) {

            _log.debug(dateFormatLog.format(new Date()) + " Created company groupid " + group.getGroupId() + ", creatoruserid " + group.getCreatorUserId() + ", friendlyURL "+ group.getFriendlyURL());

            /** Operations in group: **/


            // Add the default activities for the company.
            PrevingActivityLocalizedUtil.createDefaultActivities(companyId, group.getGroupId());
            _log.debug(dateFormatLog.format(new Date()) + " Created activities ");

            // Add the default activities for the company.
            PrevingHolidayLocalizedUtil.createDefaultHolidays(companyId, group.getGroupId());
            _log.debug(dateFormatLog.format(new Date()) + " Created holidays ");

            // Add the default configuration of company.
            PrevingCompanyUtil.createCompanyConfiguration(companyId, group.getGroupId(), creatorUser.getUserId(), cif, source, stDate, impDate, delDate,  clientId,  contractId);
            _log.debug(dateFormatLog.format(new Date()) + " Created company configuration " + group.getGroupId() + ", user creator " + creatorUser.getEmailAddress());


        }

        return group;
    }

    public static User createUser(long companyId, long groupId, String name, String surname, String nif, String email, String password, String portalURL, String pathMain, Locale locale){

        /** Operations for user admin in group: **/

        //Get user or create new user if doesn't exists.
        User userAdmin = PrevingUserUtil.getUser(companyId, groupId, name, surname, nif, email, password, portalURL, pathMain, locale);
        _log.debug(dateFormatLog.format(new Date()) + " Admin User " + userAdmin.getEmailAddress());

        // Add user admin to new site.
        PrevingCompanyUtil.addUserToCompany(userAdmin.getUserId(), groupId);
        _log.debug(dateFormatLog.format(new Date()) + " Admin User to company " + groupId);

        //Add role for admin in portal.
        String roleAdminName = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_ROLE_COMPANY_ADMIN);
        if (!Validator.isBlank(roleAdminName)) {
            PrevingUserUtil.addRoleToUser(userAdmin, companyId, roleAdminName);
            // Add user to group with role admin for the site.
            PrevingCompanyUtil.addUserToGroup(userAdmin, companyId, groupId, roleAdminName);

            _log.debug(dateFormatLog.format(new Date()) + " Admin User role portal " + roleAdminName);
        }

        //Add role for admin in site.
        String roleSiteAdminName = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_ROLE_COMPANY_SITE_ADMIN);
        if (!Validator.isBlank(roleSiteAdminName)) {
            PrevingUserUtil.addRoleGroupToUser(groupId, userAdmin, companyId, roleSiteAdminName);
            _log.debug(dateFormatLog.format(new Date()) + " Admin User role site " + roleAdminName);
        }

        //Set creator userId in companyConf
        CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCompanyIdAndGroupId(companyId, groupId);
        if(Validator.isNotNull(companyConf)){
            companyConf.setUserId(userAdmin.getUserId());
            CompanyConfLocalServiceUtil.updateCompanyConf(companyConf);
        }
        _log.debug(dateFormatLog.format(new Date()) + " Admin User as creator user " + userAdmin.getEmailAddress());

        return userAdmin;

    }

    public static void selectSiteTemplateToGroup(Group group, long templateId){

        if(Validator.isNotNull(group) && templateId > 0) {

            Date startCreate = new Date();
            _log.debug(dateFormatLog.format(startCreate) + "   * START TO SELECT SITE TEMPLATE TO GROUP " + group.getFriendlyURL());

            // Use the site template in created site.
            PrevingCompanyUtil.addTemplateToCompany(group, templateId);

            Date endCreate = new Date();
            _log.debug(dateFormatLog.format(endCreate) + "   * END TO SELECT SITE TEMPLATE TO GROUP " + group.getFriendlyURL() + ", time " + ((endCreate.getTime() - startCreate.getTime())/1000) + " seconds.");

        }else{
            _log.error("Error selecting site template: groupId or templateId are incorrect.");
        }

    }

}
