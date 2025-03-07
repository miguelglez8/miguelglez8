package com.preving.liferay.portlet.company.configuration.web.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.model.CompanyConf;
import com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil;

import java.io.File;
import java.util.Date;
import java.util.Locale;
import java.util.Map;


public class CompanyConfigurationUtil {

    private static Log _log = LogFactoryUtil.getLog(CompanyConfigurationUtil.class);

    public static void saveCompanyConfiguration(long companyConfigId,
                                                boolean notWorkable, boolean finishDate, String periodicity,
                                                boolean maxHoursDay, boolean maxHoursWeek, boolean savePastDays,
                                                float maxHoursDayValue, float maxHoursWeekValue, int savePastDaysValue,
                                                boolean editSigns, int editSignsValue,
                                                boolean editDeleteSigns, int editDeleteSignsValue,
                                                Date startDate, Date implantationDate, Map<Locale, String> descriptionLocalized, Map<Locale,
                                                String> agreementLocalized, Map<Locale, String> observationsLocalized,
                                                boolean isWorkCenters, boolean isOwnCalendars, String soporteEmails) {

        CompanyConf companyConf = CompanyConfLocalServiceUtil.fetchCompanyConf(companyConfigId);

        companyConf.setNotificationNotWorkable(notWorkable);
        companyConf.setNotificationFinishDateSign(finishDate);
        companyConf.setNotificationAdminPeriodicity(periodicity);
        companyConf.setMaxHoursDay(maxHoursDay);
        companyConf.setMaxHoursWeek(maxHoursWeek);
        companyConf.setSavePastDays(savePastDays);
        companyConf.setMaxHoursDayValue(maxHoursDayValue);
        companyConf.setMaxHoursWeekValue(maxHoursWeekValue);
        companyConf.setSavePastDaysValue(savePastDaysValue);
        companyConf.setEditSigns(editSigns);
        companyConf.setEditSignsValue(editSignsValue);
        companyConf.setEditDeleteSigns(editDeleteSigns);
        companyConf.setEditDeleteSignsValue(editDeleteSignsValue);
        companyConf.setStartDate(startDate);
        companyConf.setImplantationDate(implantationDate);
        companyConf.setDescriptionMap(descriptionLocalized);
        companyConf.setAgreementMap(agreementLocalized);
        companyConf.setObservationsMap(observationsLocalized);
        companyConf.setIsWorkCenters(isWorkCenters);
        companyConf.setIsOwnCalendars(isOwnCalendars);
        companyConf.setSoporteEmail(soporteEmails);




        CompanyConfLocalServiceUtil.updateCompanyConf(companyConf);

    }


    public static void setCompanyLogo(long groupId, boolean privateLayout, File logo){

        try {

            LayoutSetLocalServiceUtil.updateLogo(groupId, privateLayout, true, logo);

        } catch (PortalException e) {
            e.printStackTrace();
        }

    }
}
