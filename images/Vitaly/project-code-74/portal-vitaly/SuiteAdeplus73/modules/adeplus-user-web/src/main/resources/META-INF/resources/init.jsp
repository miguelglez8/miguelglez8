<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.model.User" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ResourceBundle" %>

<%@ page import="java.text.SimpleDateFormat" %>

<%@ page import="com.adeplus.liferay.portlet.user.web.constants.AdeplusUsersPortletKeys" %>

<%@ page import="com.adeplus.liferay.portlet.user.web.constants.AdeplusUsersPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %>


<liferay-theme:defineObjects />

<portlet:defineObjects />