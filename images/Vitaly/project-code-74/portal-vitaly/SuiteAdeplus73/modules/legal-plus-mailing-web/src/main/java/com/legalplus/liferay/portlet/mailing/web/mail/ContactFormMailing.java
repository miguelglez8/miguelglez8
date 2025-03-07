package com.legalplus.liferay.portlet.mailing.web.mail;

import com.legalplus.liferay.portlet.mailing.web.util.PrevingMailUtil;
import com.liferay.portal.kernel.model.User;
import java.io.File;

public class ContactFormMailing {

    public static void contactToAdmin(String mailTo, String subject, String body, File file, String fileName){
        PrevingMailUtil.sendMailWithAttachment(mailTo, "", subject, body, file, fileName);
    }

    public static void contactToUser(User user, String subject, String body, File file, String fileName){
        PrevingMailUtil.sendMailWithAttachment(user.getEmailAddress(), user.getFullName(), subject, body, file, fileName);
    }

}
