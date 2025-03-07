package com.legalplus.liferay.portlet.importar.legislaciones.web.util;

import com.adeplus.liferay.portlet.suite.manager.model.Audit;
import com.adeplus.liferay.portlet.suite.manager.service.AuditLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;

import java.util.Date;

public class PrevingAuditUtil {

    public static void addAudit(long companyId, long groupId, long userId, String action, String description){

        Audit audit = AuditLocalServiceUtil.createAudit(CounterLocalServiceUtil.increment(Audit.class.getName()));

        audit.setCompanyId(companyId);
        audit.setGroupId(groupId);
        audit.setUserId(userId);
        audit.setAction(action);
        audit.setDescription(description);
        audit.setDate(new Date());

        AuditLocalServiceUtil.updateAudit(audit);

    }

}
