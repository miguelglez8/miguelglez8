<%@ page import="com.liferay.portal.kernel.model.Group" %>
<%@ page import="com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.CompanyConf" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil" %>
<%@ include file="/init.jsp" %>

<%

     List<Group> groupsFilter = new ArrayList<>();

     for(Group group:user.getGroups()){
         boolean siteAdmin = UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), group.getGroupId(), "Preving Company Site Admin");
         boolean siteUser = UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), group.getGroupId(), "Preving Company Site User");

         if(group.isActive() && group.getFriendlyURL() != "/guest" && user.isActive() && (siteAdmin || siteUser || permissionChecker.isOmniadmin())){
             groupsFilter.add(group);
         }
     }

    themeDisplay.getScopeGroup().getFriendlyURL();
%>


<p><liferay-ui:message key="user.sites.go"/></p>

<c:forEach var="group" items="<%=groupsFilter%>" varStatus="loop">
    <%
        Group g = (Group) pageContext.getAttribute("group");

        CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCompanyIdAndGroupId(company.getCompanyId(), g.getGroupId());


        boolean hasSiteAdminRole = UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), g.getGroupId(), "Preving Company Site Admin");
        boolean hasSiteUserRole = UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), g.getGroupId(), "Preving Company Site User");
    %>
    <c:if test="<%=hasSiteAdminRole || hasSiteUserRole%>">
        <div class="row company-user">
            <div class="col-md-4 text-right">
                <div class="company-logo">
                    <c:if test="${not empty group.getLogoURL(themeDisplay, true)}">
                        <img src="${group.getLogoURL(themeDisplay, true)}" alt="${group.getName(locale)}" style="max-height: 100px;max-width: 150px;"/>
                    </c:if>
                </div>
            </div>
            <div class="col-md-8">
                <div class="company-name">
                    <h3><a href="${group.getPathFriendlyURL(true,themeDisplay)}${group.friendlyURL}">${group.getName(locale)}</a></h3>
                    <c:if test="<%=hasSiteAdminRole%>">
                        <p><span class="label label-success"><liferay-ui:message key="user.sites.administrator"/></span></p>
                    </c:if>
                    <p><%=companyConf.getDescription(themeDisplay.getLocale())%></p>

                    <p>
                        <button class="dt-button buttons-csv buttons-html5" type="button" onclick="location.href='${group.getPathFriendlyURL(true,themeDisplay)}${group.friendlyURL}/registro-horario'">
                            <span><liferay-ui:message key="user.sites.register"/></span>
                        </button>
                    </p>
                </div>
            </div>
        </div>
    </c:if>
</c:forEach>
