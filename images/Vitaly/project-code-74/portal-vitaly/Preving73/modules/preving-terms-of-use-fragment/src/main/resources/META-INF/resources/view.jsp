<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>
<%@ page import="com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.model.Role" %>
<%@ page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil" %>

<%@ include file="/init.jsp" %>

<%
    long termsOfUseGroupId = PrefsPropsUtil.getLong(themeDisplay.getCompanyId(), JournalServiceConfigurationKeys.TERMS_OF_USE_JOURNAL_ARTICLE_GROUP_ID, JournalServiceConfigurationValues.TERMS_OF_USE_JOURNAL_ARTICLE_GROUP_ID);
    String termsOfUseArticleId = PrefsPropsUtil.getString(themeDisplay.getCompanyId(), JournalServiceConfigurationKeys.TERMS_OF_USE_JOURNAL_ARTICLE_ID, JournalServiceConfigurationValues.TERMS_OF_USE_JOURNAL_ARTICLE_ID);
    JournalArticle journalArticle = null;



    if ((termsOfUseGroupId > 0) && Validator.isNotNull(termsOfUseArticleId)) {
        journalArticle = JournalArticleLocalServiceUtil.fetchArticle(termsOfUseGroupId, termsOfUseArticleId);
    }

    //Fragment new start
    String TERMS_OF_USE_JOURNAL_ARTICLE_ID_ADMIN = JournalServiceConfigurationKeys.TERMS_OF_USE_JOURNAL_ARTICLE_ID + ".admin";
    String termsOfUseArticleIdAdmin = PrefsPropsUtil.getString(themeDisplay.getCompanyId(), TERMS_OF_USE_JOURNAL_ARTICLE_ID_ADMIN, JournalServiceConfigurationValues.TERMS_OF_USE_JOURNAL_ARTICLE_ID);

    String roleAdminName = PrefsPropsUtil.getString("preving.roles.company.admin.name");
    Role role = RoleLocalServiceUtil.fetchRole(themeDisplay.getCompanyId(), roleAdminName);
    boolean hasAdminRole = RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), role.getRoleId());

    String roleSiteAdminName = PrefsPropsUtil.getString("preving.roles.company.site.admin.name");
    boolean hasSiteAdminRole = UserGroupRoleLocalServiceUtil.hasUserGroupRole(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(), roleAdminName);

/*    System.out.println("termsOfUseArticleIdAdmin " + termsOfUseArticleIdAdmin);
    System.out.println("termsOfUseArticleId " + termsOfUseArticleId);
    System.out.println("termsOfUseGroupId " + termsOfUseGroupId);
    System.out.println("userId " + themeDisplay.getUserId());
    for(Role rol:themeDisplay.getUser().getRoles()){
        System.out.println(" - user role: " + rol.getName());
    }
    if(role != null){
        System.out.println("role " + role.getRoleId() +" " + role.getDescriptiveName()+" " + role.getName());
    }else{
        System.out.println("role not exists.");
    }
    System.out.println("roleAdminName " + roleAdminName);
    System.out.println("hasAdminRole " + hasAdminRole);
    System.out.println("hasSiteAdminRole " + hasSiteAdminRole);
    System.out.println("isGroupOwner " + permissionChecker.isGroupOwner(themeDisplay.getScopeGroupId()));
    System.out.println("isGroupAdmin " + permissionChecker.isGroupAdmin(themeDisplay.getScopeGroupId()));*/

    if (termsOfUseGroupId > 0 && termsOfUseArticleIdAdmin != ""
            && (permissionChecker.isGroupAdmin(themeDisplay.getScopeGroupId())
                || permissionChecker.isGroupOwner(themeDisplay.getScopeGroupId())
                || hasAdminRole
                || hasSiteAdminRole)) {
        System.out.println("The user is admin for terms of use.");
        journalArticle = JournalArticleLocalServiceUtil.fetchArticle(termsOfUseGroupId, termsOfUseArticleIdAdmin);
    }
    //Fragment new end
%>

<c:choose>
    <c:when test="<%= journalArticle != null %>">
        <liferay-asset:asset-display
                className="<%= JournalArticle.class.getName() %>"
                classPK="<%= journalArticle.getResourcePrimKey() %>"
                template="<%= AssetRenderer.TEMPLATE_FULL_CONTENT %>"
        />
    </c:when>
    <c:otherwise>
        <liferay-util:include page="/html/portal/terms_of_use_default.jsp" />
    </c:otherwise>
</c:choose>

<!-- Fragment new start -->
<style type="text/css">
    .journal-content-article dl dt {display:none;}
</style>
<!-- Fragment new end -->