package com.canal.etico.liferay.portlet.synchronization.web.listener;

import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserApplicationLocalServiceUtil;
import com.canal.etico.liferay.portlet.commons.web.company.CanalEticoCompUtil;
import com.canal.etico.liferay.portlet.commons.web.role.CanalEticoRoleUtil;
import com.canal.etico.liferay.portlet.commons.web.user.CanalEticoUserUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component(
        immediate = true,
        service = ModelListener.class
)
public class SuiteUserModelListener extends BaseModelListener<UserCompany> {

    private static final Log log = LogFactoryUtil.getLog(SuiteUserModelListener.class);

    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    @Override
    public void onAfterCreate(UserCompany userCompany) throws ModelListenerException {

        System.out.println(dateFormatLog.format(new Date()) + " Create user in canal etico : " + userCompany.getUserId());

        /*Comp comp = CompLocalServiceUtil.fetchComp(userCompany.getCompId());

        if (Validator.isNotNull(comp) && CanalEticoCompUtil.hasCompanyApplicationInSuite(comp)) {

            User user = UserLocalServiceUtil.fetchUser(userCompany.getUserId());

            if (Validator.isNotNull(user)) {

                try {

                    Group group = GroupLocalServiceUtil.getFriendlyURLGroup(PortalUtil.getDefaultCompanyId(), "/canal-etico");

                    Application canalEticoAplication = CanalEticoCompUtil.getCanalEticoAplication();

                    if (Validator.isNotNull(group)) {

                        if((Validator.isNull(userCompany.getUserEndDate()) || userCompany.getUserEndDate().after(new Date()))
                                && UserApplicationLocalServiceUtil.hasUserApplication(userCompany.getUserId(), userCompany.getCompId(),
                                Validator.isNotNull(canalEticoAplication)?canalEticoAplication.getApplicationId():0)){

                            CanalEticoUserUtil.createUser(userCompany.getUserId(), userCompany.getCompId());
                            GroupLocalServiceUtil.addUserGroup(user.getUserId(), group.getGroupId());
                            CanalEticoRoleUtil.setCompanyAdminRole(PortalUtil.getDefaultCompanyId(), user);

                            log.info(dateFormatLog.format(new Date()) + "  The user " + user.getEmailAddress() + " is active in Canal Etico now.");

                        }
                    }

                } catch (PortalException e) {
                    log.info(" Don't exist group with friendlyURL /canal-etico");
                }

            } else {

                System.out.println(dateFormatLog.format(new Date()) + "  The user " + user.getEmailAddress() + " created in Canal Etico.");

            }

        } else {
            System.out.println(dateFormatLog.format(new Date()) + " Copy user " + comp.getName() + " haven't Canal Etico aplication. User not updated.");
        }

        super.onAfterCreate(userCompany);

         */
    }

    @Override
    public void onAfterUpdate(UserCompany userCompany) throws ModelListenerException {

        System.out.println(dateFormatLog.format(new Date()) + " Update user model in canal etico : " + userCompany.getUserId());
/*
        Comp comp = CompLocalServiceUtil.fetchComp(userCompany.getCompId());

        if (Validator.isNotNull(comp) && CanalEticoCompUtil.hasCompanyApplicationInSuite(comp)) {

            User user = UserLocalServiceUtil.fetchUser(userCompany.getUserId());

            if (Validator.isNotNull(user)) {

                try {

                    Group group = GroupLocalServiceUtil.getFriendlyURLGroup(PortalUtil.getDefaultCompanyId(), "/canal-etico");

                    Application canalEticoAplication = CanalEticoCompUtil.getCanalEticoAplication();

                    if (Validator.isNotNull(group)) {

                        if((Validator.isNull(userCompany.getUserEndDate()) || userCompany.getUserEndDate().after(new Date()))
                                && UserApplicationLocalServiceUtil.hasUserApplication(userCompany.getUserId(), userCompany.getCompId(), Validator.isNotNull(canalEticoAplication)?canalEticoAplication.getApplicationId():0)){

                            CanalEticoUserUtil.createUser(userCompany.getUserId(), userCompany.getCompId());
                            GroupLocalServiceUtil.addUserGroup(user.getUserId(), group.getGroupId());
                            CanalEticoRoleUtil.setCompanyAdminRole(PortalUtil.getDefaultCompanyId(), user);

                            System.out.println(dateFormatLog.format(new Date()) + "  The user " + user.getEmailAddress() + " is active in Canal Etico now. (UserModel)");

                        }else{

                            CanalEticoUserUtil.setUserDeleteDate(userCompany.getUserId(), userCompany.getCompId(), new Date());
                            GroupLocalServiceUtil.deleteUserGroup(user.getUserId(), group.getGroupId());
                            CanalEticoRoleUtil.deleteCompanyAdminRole(PortalUtil.getDefaultCompanyId(), user);

                            System.out.println(dateFormatLog.format(new Date()) + "  The user " + user.getEmailAddress() + " is inactive in Canal Etico now. UserModel.");
                        }

                    }

                } catch (PortalException e) {
                    log.info(" Don't exist group with friendlyURL /canal-etico-denuncias");
                }


            } else {

                System.out.println(dateFormatLog.format(new Date()) + "  The user " + user.getEmailAddress() + " continue active in Canal Etico.");

            }

        } else {
            System.out.println(dateFormatLog.format(new Date()) + " Copy user " + comp.getName() + " haven't Canal Etico aplication. User not updated.");
        }


        super.onAfterUpdate(userCompany);

 */
    }
}
