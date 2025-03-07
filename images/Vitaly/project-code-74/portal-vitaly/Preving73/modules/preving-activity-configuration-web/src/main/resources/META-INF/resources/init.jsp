<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.preving.liferay.portlet.calendar.manager.model.Activity" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.CompanyConf" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />