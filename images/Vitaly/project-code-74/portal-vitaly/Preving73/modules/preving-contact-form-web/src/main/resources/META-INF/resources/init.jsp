<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.preving.liferay.portlet.contact.form.web.constants.ContactFormPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.preving.liferay.portlet.contact.form.web.util.PrevingUserUtil" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />