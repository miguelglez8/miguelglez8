<%@ page import="com.liferay.portal.kernel.security.permission.PermissionChecker" %>
<%@ include file="init.jsp" %>

<c:if test="<%=PrevingUserUtil.hasSiteAdminRole(themeDisplay.getScopeGroupId(), user.getUserId())%>">
	<%@include file="/sendMailAdmin.jspf" %>
</c:if>
<c:if test="<%=!PrevingUserUtil.hasSiteAdminRole(themeDisplay.getScopeGroupId(), user.getUserId()) && permissionChecker.isOmniadmin()%>">
	<%@include file="/sendMailAdmin.jspf" %>
</c:if>
<c:if test="<%=!PrevingUserUtil.hasSiteAdminRole(themeDisplay.getScopeGroupId(), user.getUserId())%>">
	<%@include file="/sendMailUser.jspf" %>
</c:if>


