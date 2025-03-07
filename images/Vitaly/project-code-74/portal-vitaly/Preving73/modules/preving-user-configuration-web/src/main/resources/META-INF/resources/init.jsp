<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib prefix="liferay" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="com.liferay.portal.kernel.model.User" %>
<%@ page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.CompanyConf" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil" %>
<%@ page import="com.preving.liferay.portlet.user.configuration.web.constants.UserConfigurationPortletKeys" %>
<%@ page import="com.preving.liferay.portlet.user.configuration.web.util.PrevingUserUtil" %>
<%@ page import="java.util.*" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.UserData" %>
<%@ page import="com.preving.liferay.portlet.user.configuration.web.util.PrevingRoleUtil" %>
<%@ page import="com.preving.liferay.portlet.user.configuration.web.util.PrevingUserDataUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ page import="com.preving.liferay.portlet.calendar.manager.model.WorkCenters" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.service.WorkCentersLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.theme.ThemeDisplay" %>


<liferay-theme:defineObjects />

<portlet:defineObjects />