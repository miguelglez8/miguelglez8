<%@ page import="com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil" %>
<%@ page import="com.preving.liferay.portlet.calendar.manager.model.UserData" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ taglib prefix="liferay" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="init.jsp" %>

<%
    UserData userData = UserDataLocalServiceUtil.getUserDataByGroupIdAndUserId(scopeGroupId, user.getUserId());
%>

<liferay-ui:success key="password-update-success"  message="user.password.update.success"/>

<liferay-ui:error key="invalid-current-password" message="user.password.invalid.current.password"/>
<liferay-ui:error key="confirm-new-password" message="user.password.confirm.new.password"/>
<liferay-ui:error key="user-password-error" message="user.password.password.error.validation"/>
<liferay-ui:error key="password-error" message="user.password.password.error"/>

<h2><liferay-ui:message key="user.password.current.title"/></h2>

<div class="row">
    <div class="col-6">
        <p><liferay-ui:message key="user.password.confirm.name"/>: <%=Validator.isNotNull(userData)?userData.getName()+" "+userData.getLastName():user.getFullName()%></p>
        <p><liferay-ui:message key="user.password.confirm.mail"/>: <%=Validator.isNotNull(userData)?userData.getEmail():user.getEmailAddress()%></p>
    </div>
</div>

<p><liferay-ui:message key="user.password.confirm.new.password.rules"/></p>

<portlet:actionURL var="updatePasswordURL" name="updatePassword"/>
<aui:form method="post" action="<%=updatePasswordURL.toString() %>">

<%--    <div class="row">
        <div class="col-6">
            <aui:input  name="current" type="password" label="user.password.current.password">
                <aui:validator name="required"/>
            </aui:input>
        </div>
    </div>--%>
    <div class="row">
        <div class="col-6">
            <aui:input  name="password1" type="password" label="user.password.new.password">
                <aui:validator name="required"/>
                <aui:validator name="minLength">8</aui:validator>
            </aui:input>
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <aui:input  name="password2" type="password" label="user.password.confirm.password">
                <aui:validator name="required"/>
                <aui:validator name="minLength">8</aui:validator>
            </aui:input>
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <span id="<portlet:namespace />passwordCapsLockSpan" style="display: none;"><liferay-ui:message key="caps-lock-is-on" /></span>
        </div>
    </div>
    <aui:button-row>
        <aui:button type="submit" value="save"/>
    </aui:button-row>

</aui:form>

<aui:script use="aui-base">
    var current = A.one('#<portlet:namespace/>current');
    var password1 = A.one('#<portlet:namespace/>password1');
    var password2 = A.one('#<portlet:namespace/>password2');

    if (current) {
        current.on(
            'keypress',
            function(event) {
            Liferay.Util.showCapsLock(event, '<portlet:namespace/>passwordCapsLockSpan');
            }
        );
    }

    if (password1) {
        password1.on(
            'keypress',
            function(event) {
            Liferay.Util.showCapsLock(event, '<portlet:namespace/>passwordCapsLockSpan');
            }
        );
    }

    if (password2) {
        password2.on(
            'keypress',
            function(event) {
              Liferay.Util.showCapsLock(event, '<portlet:namespace/>passwordCapsLockSpan');
            }
        );
    }
</aui:script>