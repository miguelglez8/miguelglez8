package com.adeplus.liferay.portlet.application.web.util;

import com.adeplus.liferay.portlet.commons.web.logo.AdeplusLogoUtil;
import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;

public class AdeplusApplicationUtil {

    private static Log _log = LogFactoryUtil.getLog(AdeplusApplicationUtil.class);

    public static Application createApplication(long groupId, long userId, String name, String url, String description, String configuration, String roles, String alias, String keycloakClient, String keycloakSecret, String parentFolderName, String folderName, String sourceFileName, File logo, ServiceContext serviceContext){

        Application app = ApplicationLocalServiceUtil.createApplication(CounterLocalServiceUtil.increment(Application.class.getName()));

        app.setName(name);
        app.setUrl(url);
        app.setDescription(description);
        app.setConfiguration(configuration);

        app.setRoles(roles);
        app.setAlias(alias);
        app.setKeycloak_client(keycloakClient);
        app.setKeycloak_secret(keycloakSecret);

        //Save logo
        if(Validator.isNotNull(logo)) {
            DLFileEntry dlFileEntry = AdeplusLogoUtil.saveLogo(groupId, userId, name, parentFolderName, folderName, sourceFileName, logo, serviceContext);
            if (Validator.isNotNull(dlFileEntry)) {
                app.setLogo(dlFileEntry.getFileEntryId());
            }
        }

        ApplicationLocalServiceUtil.updateApplication(app);

        return app;
    }

    public static Application updateApplication(long groupId,
                                                long userId, long applicationId,
                                                String name, String url, String description, String configuration, String roles,
                                                String alias, String keycloakClient, String keycloakSecret,
                                                boolean isLimitAdmin, long limitNumAdms,
                                                boolean isLimitUsers, long limitNumUsers,
                                                String parentFolderName, String folderName, String sourceFileName, File logo,
                                                ServiceContext serviceContext){

        Application app = null;
        try {

            app = ApplicationLocalServiceUtil.getApplication(applicationId);

            if(Validator.isNotNull(app)) {
                app.setName(name);
                app.setUrl(url);
                app.setDescription(description);
                app.setConfiguration(configuration);

                app.setRoles(roles);
                app.setAlias(alias);
                app.setKeycloak_client(keycloakClient);
                app.setKeycloak_secret(keycloakSecret);

                app.setLimitAdmins(isLimitAdmin);
                app.setLimitNumAdmins(limitNumAdms);
                app.setLimitUsers(isLimitUsers);
                app.setLimitNumUsers(limitNumUsers);

                //Save logo
                if(Validator.isNotNull(sourceFileName) && !sourceFileName.isEmpty()) {
                    DLFileEntry dlFileEntry = AdeplusLogoUtil.saveLogo(groupId, userId, name, parentFolderName, folderName, sourceFileName, logo, serviceContext);
                    if (Validator.isNotNull(dlFileEntry)) {
                        //Delete the old logo.
                        if (app.getLogo() > 0) {
                            AdeplusLogoUtil.deleteFile(app.getLogo());
                        }

                        app.setLogo(dlFileEntry.getFileEntryId());
                    }
                }

                ApplicationLocalServiceUtil.updateApplication(app);
            }

        } catch (PortalException e) {
            _log.error(e);
        }

        return app;
    }


    public static void deleteApplication(long applicationId){

        try {

            if(applicationId >0 ) {
                ApplicationLocalServiceUtil.deleteApplication(applicationId);
            }

        } catch (PortalException e) {
            _log.error(e);
        }

    }
}
