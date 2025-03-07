<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ taglib prefix="liferay" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="init.jsp" %>

<liferay-ui:success key="password-update-success"  message="password.view.update.success"/>

<liferay-ui:error key="invalid-current-password" message="password.view.invalid.current.password"/>
<liferay-ui:error key="confirm-new-password" message="password.view.confirm.new.password"/>
<liferay-ui:error key="user-password-error" message="password.view.password.error.validation"/>
<liferay-ui:error key="password-error" message="password.view.password.error"/>

<portlet:actionURL var="updatePasswordURL" name="updatePassword"/>
<aui:form method="post" action="<%=updatePasswordURL.toString() %>">
	<div class="content">
		<div class="titulo-seccion">
			<h2><liferay-ui:message key="password.view.current.title"/></h2>
		</div>

		<div class="formulario">
			<div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
				<div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">
					<div class="row">
						<div class="form-group col-md-8">
							<aui:input  name="email" type="text" label="password.view.email" value="<%=themeDisplay.getUser().getEmailAddress()%>" disabled="true" />
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-8 required">
							<aui:input  name="current" type="password" label="password.view.current.password">
								<aui:validator name="required"/>
							</aui:input>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-8 required">
							<aui:input  name="password1" type="password" label="password.view.new.password">
								<aui:validator name="required"/>
								<aui:validator name="minLength">8</aui:validator>
							</aui:input>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-8 required">
							<aui:input  name="password2" type="password" label="password.view.confirm.password">
								<aui:validator name="required"/>
								<aui:validator name="minLength">8</aui:validator>
							</aui:input>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-8">
							<span id="<portlet:namespace />passwordCapsLockSpan" style="display: none;"><liferay-ui:message key="caps-lock-is-on" /></span>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-8">
							<p><liferay-ui:message key="password.view.confirm.new.password.rules"/></p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="button-holder d-flex justify-content-center my-4">
		<aui:button-row>
			<aui:button type="submit" value="save"/>
		</aui:button-row>
	</div>

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