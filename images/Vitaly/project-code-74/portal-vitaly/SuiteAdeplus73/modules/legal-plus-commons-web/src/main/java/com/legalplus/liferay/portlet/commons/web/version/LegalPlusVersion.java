package com.legalplus.liferay.portlet.commons.web.version;

import com.legalplus.liferay.portlet.commons.web.constants.LegalPlusCommonsPortletKeys;
import com.liferay.portal.kernel.util.PrefsPropsUtil;

public class LegalPlusVersion {

    public static boolean itsNewVersion() {
        return LegalPlusCommonsPortletKeys.NEW_VERSION;
    }
}
