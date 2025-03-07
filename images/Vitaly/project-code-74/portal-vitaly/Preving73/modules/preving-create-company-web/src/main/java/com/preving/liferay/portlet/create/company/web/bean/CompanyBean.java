package com.preving.liferay.portlet.create.company.web.bean;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.Date;

public class CompanyBean {

    private static Log _log = LogFactoryUtil.getLog(CompanyBean.class);

    private long companyId;
    private long groupId;
    private long companyConfId;

    private String name;
    private String cif;
    private String description;
    private String agreement;
    private String observations;
    private String source;
    private String friendlyURL;
    private long creatorUserId;
    private boolean active;
    private boolean notificationNotWorkable;
    private String notificationAdminPeriodicity;
    private float maxHoursDay;
    private float maxHoursWeek;
    private int savePastDays;
    private Date startDate;
    private Date createDate;
    private Date modifiedDate;
    private Date deleteDate;

    private String adminFullName;
    private String adminEmail;

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getCompanyConfId() {
        return companyConfId;
    }

    public void setCompanyConfId(long companyConfId) {
        this.companyConfId = companyConfId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFriendlyURL() {
        return friendlyURL;
    }

    public void setFriendlyURL(String friendlyURL) {
        this.friendlyURL = friendlyURL;
    }

    public long getCreatorUserId() {
        return creatorUserId;
    }

    public String getAdminFullName() {
        return adminFullName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminFullName(String adminFullName) {
        this.adminFullName = adminFullName;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public void setCreatorUserId(long creatorUserId) {

        this.creatorUserId = creatorUserId;

/*        try {
            if(creatorUserId > 0) {
                this.adminFullName = getUser().getFullName();
                this.adminEmail = getUser().getEmailAddress();
            }else{
                this.adminFullName = "";
                this.adminEmail = "";
            }
        } catch (Exception e) {
            _log.error(e);
        }*/
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public boolean isNotificationNotWorkable() {
        return notificationNotWorkable;
    }

    public void setNotificationNotWorkable(boolean notificationNotWorkable) {
        this.notificationNotWorkable = notificationNotWorkable;
    }

    public String getNotificationAdminPeriodicity() {
        return notificationAdminPeriodicity;
    }

    public void setNotificationAdminPeriodicity(String notificationAdminPeriodicity) {
        this.notificationAdminPeriodicity = notificationAdminPeriodicity;
    }

    public float getMaxHoursDay() {
        return maxHoursDay;
    }

    public void setMaxHoursDay(float maxHoursDay) {
        this.maxHoursDay = maxHoursDay;
    }

    public float getMaxHoursWeek() {
        return maxHoursWeek;
    }

    public void setMaxHoursWeek(float maxHoursWeek) {
        this.maxHoursWeek = maxHoursWeek;
    }

    public int getSavePastDays() {
        return savePastDays;
    }

    public void setSavePastDays(int savePastDays) {
        this.savePastDays = savePastDays;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public User getUser(){
        try {
            if(creatorUserId > 0) {
                return UserLocalServiceUtil.getUser(creatorUserId);
            }
        } catch (PortalException e) {
            _log.error(e);
        }
        return null;
    }


    @Override
    public String toString() {
        return "CompanyBean{" +
                "companyId=" + companyId +
                ", groupId=" + groupId +
                ", companyConfId=" + companyConfId +
                ", name='" + name + '\'' +
                ", cif='" + cif + '\'' +
                ", description='" + description + '\'' +
                ", agreement='" + agreement + '\'' +
                ", observations='" + observations + '\'' +
                ", source='" + source + '\'' +
                ", friendlyURL='" + friendlyURL + '\'' +
                ", creatorUserId=" + creatorUserId +
                ", active=" + active +
                ", notificationNotWorkable=" + notificationNotWorkable +
                ", notificationAdminPeriodicity=" + notificationAdminPeriodicity +
                ", maxHoursDay=" + maxHoursDay +
                ", maxHoursWeek=" + maxHoursWeek +
                ", savePastDays=" + savePastDays +
                ", startDate=" + startDate +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                ", deleteDate=" + deleteDate +
                '}';
    }
}
