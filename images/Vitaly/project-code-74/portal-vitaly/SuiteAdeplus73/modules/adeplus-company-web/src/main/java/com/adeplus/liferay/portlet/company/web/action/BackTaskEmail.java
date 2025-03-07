package com.adeplus.liferay.portlet.company.web.action;


import com.liferay.portal.kernel.backgroundtask.*;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.adeplus.liferay.portlet.mailing.web.mail.UserMailing;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import org.osgi.service.component.annotations.Component;


import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;



@Component(
        immediate = true,
        property = {"background.task.executor.class.name=com.adeplus.liferay.portlet.company.web.action.BackTaskEmail"},
        // Without this property osgi will not register this as background executor/handler
        service = BackgroundTaskExecutor.class
)
public class BackTaskEmail implements BackgroundTaskExecutor {

    public static final int EMAIL_NEW_USER = 1;
    public static final int EMAIL_UPDATE_USER = 2;

    private static Log _log = LogFactoryUtil.getLog(BackTaskEmail.class);


    @Override
    public BackgroundTaskExecutor clone() {
        return this;
    }

    @Override
    public BackgroundTaskResult execute(BackgroundTask backgroundTask)  {

        Map taskContextMap = backgroundTask.getTaskContextMap();
        long userId = (long)taskContextMap.get("userId");
        User user = null;
        try {
            user = UserLocalServiceUtil.getUser(userId);
        } catch (PortalException e) {
            e.printStackTrace();
        }
        int action = (int) taskContextMap.get("action");
        String passwordRandon = String.valueOf(taskContextMap.get("passwordRandon"));
        long companyEditId = (long) taskContextMap.get("companyEditId");

        _log.info("Mandar email de " + action + " a " + user.getEmailAddress());

        switch(action){
            case EMAIL_NEW_USER:
                UserMailing.userCreate(user, companyEditId, passwordRandon, true);
                break;
            case EMAIL_UPDATE_USER:
                UserMailing.userUpdate(user, companyEditId,true);
                break;
        }


        return BackgroundTaskResult.SUCCESS;
    }

    @Override
    public String generateLockKey(BackgroundTask backgroundTask) {
        return null;
    }


    public BackgroundTaskDisplay getBackgroundTaskDisplay(BackgroundTask backgroundTask) {
        return null;
    }

    @Override
    public BackgroundTaskStatusMessageTranslator getBackgroundTaskStatusMessageTranslator() {
        return null;
    }

    @Override
    public int getIsolationLevel() {
        return BackgroundTaskConstants.ISOLATION_LEVEL_TASK_NAME;
    }

    @Override
    public String handleException(BackgroundTask backgroundTask, Exception exception) {
        return null;
    }

    @Override
    public boolean isSerial() {
        return true;
    }


}
