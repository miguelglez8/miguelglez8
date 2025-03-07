<%@ page import="com.liferay.petra.string.StringPool" %>
<%@ page import="com.liferay.portal.kernel.exception.*" %>
<%@ page import="com.liferay.portal.kernel.model.CompanyConstants" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@ page import="com.liferay.portal.kernel.security.auth.AuthException" %>
<%@ page import="com.liferay.portal.kernel.servlet.SessionErrors" %>
<%@ page import="com.liferay.portal.kernel.servlet.SessionMessages" %>
<%@ page import="com.liferay.portal.kernel.util.*" %>
<%@ page import="com.liferay.portal.util.PropsValues" %>
<%@ page import="javax.portlet.WindowState" %>
<%@ page import="java.text.Format" %>
<%@ page import="java.util.TimeZone" %><%--
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

<%@ include file="/init.jsp" %>

<c:if test='<%= SessionMessages.contains(request, "forgotPasswordSent") %>'>
        <script>
            window.location.href = "/web/guest";
        </script>
</c:if>

<div class="card-body">
<%--    <div class="brand-logo">
        <img src="/o/adeplus-theme/images/Logotipo_ADEPLUS.png" alt="logo Adeplus">
    </div>--%>
    <c:choose>
        <c:when test="<%= themeDisplay.isSignedIn() %>">

            <%
                String signedInAs = HtmlUtil.escape(user.getFullName());
                /* START MODIFICATION*/
                /*if (themeDisplay.isShowMyAccountIcon() && (themeDisplay.getURLMyAccount() != null)) {
                    String myAccountURL = String.valueOf(themeDisplay.getURLMyAccount());
                    signedInAs = "<a class=\"signed-in\" href=\"" + HtmlUtil.escape(myAccountURL) + "\">" + signedInAs + "</a>";
                }*/
                /* END MODIFICATION*/
            %>

            <p class="mt-4 mb-4"><liferay-ui:message arguments="<%= signedInAs %>" key="you-are-signed-in-as-x" translateArguments="<%= false %>" /></p>
            <%--<p class="text-center"><a href="/dashboard" class="btn btn-outline-primary btn-sm">Mis aplicaciones</a></p>--%>
        </c:when>
        <c:otherwise>

            <%
                String formName = "loginForm";
                if (windowState.equals(LiferayWindowState.EXCLUSIVE)) {
                    formName += "Modal";
                }
                String redirect = ParamUtil.getString(request, "redirect");
                String login = (String) SessionErrors.get(renderRequest, "login");
                if (Validator.isNull(login)) {
                    login = LoginUtil.getLogin(request, "login", company);
                }
                String password = StringPool.BLANK;
                boolean rememberMe = ParamUtil.getBoolean(request, "rememberMe");
                if (Validator.isNull(authType)) {
                    authType = company.getAuthType();
                }
            %>

            <div class="login-container">
                <portlet:actionURL name="/login/login" secure="<%= PropsValues.COMPANY_SECURITY_AUTH_REQUIRES_HTTPS || request.isSecure() %>" var="loginURL">
                    <portlet:param name="mvcRenderCommandName" value="/login/login" />
                </portlet:actionURL>

                <aui:form action="<%= loginURL %>" autocomplete='<%= PropsValues.COMPANY_SECURITY_LOGIN_FORM_AUTOCOMPLETE ? "on" : "off" %>' cssClass="sign-in-form form" method="post" name="<%= formName %>" onSubmit="event.preventDefault();" validateOnBlur="<%= false %>">
                    <aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
                    <aui:input name="redirect" type="hidden" value="<%= redirect %>" />
                    <aui:input name="doActionAfterLogin" type="hidden" value="<%= portletName.equals(PortletKeys.FAST_LOGIN) ? true : false %>" />

                    <div class="inline-alert-container lfr-alert-container"></div>

                    <liferay-util:dynamic-include key="com.liferay.login.web#/login.jsp#alertPre" />

                    <c:choose>
                        <c:when test='<%= SessionMessages.contains(request, "forgotPasswordSent") %>'>
                            <div class="alert alert-success">
                                <liferay-ui:message key="your-request-completed-successfully" />
                            </div>
                        </c:when>
                        <c:when test='<%= SessionMessages.contains(request, "userAdded") %>'>

                            <%
                                String userEmailAddress = (String)SessionMessages.get(request, "userAdded");
                            %>

                            <div class="alert alert-success">
                                <liferay-ui:message key="thank-you-for-creating-an-account" />

                                <c:if test="<%= company.isStrangersVerify() %>">
                                    <liferay-ui:message arguments="<%= HtmlUtil.escape(userEmailAddress) %>" key="your-email-verification-code-was-sent-to-x" translateArguments="<%= false %>" />
                                </c:if>

                                <c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.ADMIN_EMAIL_USER_ADDED_ENABLED) %>">
                                    <c:choose>
                                        <c:when test="<%= PropsValues.LOGIN_CREATE_ACCOUNT_ALLOW_CUSTOM_PASSWORD %>">
                                            <liferay-ui:message key="use-your-password-to-login" />
                                        </c:when>
                                        <c:otherwise>
                                            <liferay-ui:message arguments="<%= HtmlUtil.escape(userEmailAddress) %>" key="you-can-set-your-password-following-instructions-sent-to-x" translateArguments="<%= false %>" />
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </div>
                        </c:when>
                        <c:when test='<%= SessionMessages.contains(request, "userPending") %>'>

                            <%
                                String userEmailAddress = (String)SessionMessages.get(request, "userPending");
                            %>

                            <div class="alert alert-success">
                                <liferay-ui:message arguments="<%= HtmlUtil.escape(userEmailAddress) %>" key="thank-you-for-creating-an-account.-you-will-be-notified-via-email-at-x-when-your-account-has-been-approved" translateArguments="<%= false %>" />
                            </div>
                        </c:when>
                    </c:choose>

                    <liferay-ui:error exception="<%= AuthException.class %>" message="authentication-failed" />
                    <liferay-ui:error exception="<%= CompanyMaxUsersException.class %>" message="unable-to-log-in-because-the-maximum-number-of-users-has-been-reached" />
                    <liferay-ui:error exception="<%= CookieNotSupportedException.class %>" message="authentication-failed-please-enable-browser-cookies" />
                    <liferay-ui:error exception="<%= NoSuchUserException.class %>" message="authentication-failed" />
                    <liferay-ui:error exception="<%= PasswordExpiredException.class %>" message="your-password-has-expired" />
                    <liferay-ui:error exception="<%= UserEmailAddressException.MustNotBeNull.class %>" message="please-enter-an-email-address" />
                    <liferay-ui:error exception="<%= UserLockoutException.LDAPLockout.class %>" message="this-account-is-locked" />

                    <liferay-ui:error exception="<%= UserLockoutException.PasswordPolicyLockout.class %>">

                        <%
                            UserLockoutException.PasswordPolicyLockout ule = (UserLockoutException.PasswordPolicyLockout)errorException;
                        %>

                        <c:choose>
                            <c:when test="<%= ule.passwordPolicy.isRequireUnlock() %>">
                                <liferay-ui:message key="this-account-is-locked" />
                            </c:when>
                            <c:otherwise>

                                <%
                                    Format dateFormat = FastDateFormatFactoryUtil.getDateTime(FastDateFormatConstants.SHORT, FastDateFormatConstants.LONG, locale, TimeZone.getTimeZone(ule.user.getTimeZoneId()));
                                %>

                                <liferay-ui:message arguments="<%= dateFormat.format(ule.user.getUnlockDate()) %>" key="this-account-is-locked-until-x" translateArguments="<%= false %>" />
                            </c:otherwise>
                        </c:choose>
                    </liferay-ui:error>

                    <liferay-ui:error exception="<%= UserPasswordException.class %>" message="authentication-failed" />
                    <liferay-ui:error exception="<%= UserScreenNameException.MustNotBeNull.class %>" message="the-screen-name-cannot-be-blank" />

                    <liferay-util:dynamic-include key="com.liferay.login.web#/login.jsp#alertPost" />

                    <aui:fieldset>

                        <%
                            String loginLabel = null;
                            if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
                                loginLabel = "email-address";
                            }
                            else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
                                loginLabel = "screen-name";
                            }
                            else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
                                loginLabel = "id";
                            }
                        %>

                        <aui:input autoFocus="<%= windowState.equals(LiferayWindowState.EXCLUSIVE) || windowState.equals(WindowState.MAXIMIZED) %>" cssClass="clearable form-control" label="<%= loginLabel %>" name="login" showRequiredLabel="<%= false %>" type="text" value="<%= login %>">
                            <aui:validator name="required" />

                            <c:if test="<%= authType.equals(CompanyConstants.AUTH_TYPE_EA) %>">
                                <aui:validator name="email" />
                            </c:if>
                        </aui:input>

                        <aui:input name="password" showRequiredLabel="<%= false %>" type="password" value="<%= password %>" cssClass="form-control">
                            <aui:validator name="required" />
                        </aui:input>

                        <span id="<portlet:namespace />passwordCapsLockSpan" style="display: none;"><liferay-ui:message key="caps-lock-is-on" /></span>

                        <c:if test="<%= company.isAutoLogin() && !PropsValues.SESSION_DISABLED %>">
                            <div class="remember-me-check">
                                <aui:input checked="<%= rememberMe %>" name="rememberMe" type="checkbox" cssClass="form-check-input" />
                            </div>
                        </c:if>

                        <div>
                            <ul class="list-unstyled taglib-icon-list">
                                <li class="" role="presentation">
                                    <a href="/web/guest/mis-aplicaciones/-/login/openid_connect_request?_com_liferay_login_web_portlet_LoginPortlet_redirect=/&amp;_com_liferay_login_web_portlet_LoginPortlet_OPEN_ID_CONNECT_PROVIDER_NAME=SuiteAdePlus_web" target="_self" class=" lfr-icon-item taglib-icon" id="_com_liferay_login_web_portlet_LoginPortlet_suxb__column2__0" role="menuitem" >
                                        <span class="taglib-text ">Acceder a la Suite Adeplus</span>
                                    </a>
                                </li>

                                <li class="" role="presentation">
                                    <a href="/web/guest/login-liferay?p_p_id=com_liferay_login_web_portlet_LoginPortlet&amp;p_p_lifecycle=0&amp;p_p_state=maximized&amp;p_p_mode=view&amp;_com_liferay_login_web_portlet_LoginPortlet_mvcRenderCommandName=%2Flogin%2Fforgot_password&amp;saveLastPath=false" target="_self" class=" lfr-icon-item taglib-icon" id="_com_liferay_login_web_portlet_LoginPortlet_gezi__column2__0" role="menuitem" >
                                        <span class="taglib-text ">He olvidado mi contrase&ntilde;a</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <%--<%@ include file="/navigation.jspf" %>--%>

                    </aui:fieldset>

                    <aui:button-row>
                        <aui:button type="submit" value="sign-in" cssClass="btn btn-secondary" primary="false" />
                    </aui:button-row>
                </aui:form>

            </div>

            <aui:script sandbox="<%= true %>">
                var form = document.getElementById('<portlet:namespace /><%= formName %>');

                if (form) {
                form.addEventListener('submit', (event) => {
                <c:if test="<%= Validator.isNotNull(redirect) %>">
                    var redirect = form.querySelector('#<portlet:namespace />redirect');

                    if (redirect) {
                    var redirectVal = redirect.getAttribute('value');

                    redirect.setAttribute('value', redirectVal + window.location.hash);
                    }
                </c:if>

                submitForm(form);
                });

                var password = form.querySelector('#<portlet:namespace />password');

                if (password) {
                password.addEventListener('keypress', (event) => {
                Liferay.Util.showCapsLock(
                event,
                '<portlet:namespace />passwordCapsLockSpan'
                );
                });
                }
                }
            </aui:script>
        </c:otherwise>
    </c:choose>
</div>