<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="com.liferay.portal.kernel.util.LocalizationUtil" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.preving.liferay.portlet.contact.form.web.util.PrevingCategoryUtil" %>
<%@ include file="/init.jsp" %>

<%
    PortletPreferences preferences = renderRequest.getPreferences();
    String portletResource = ParamUtil.getString(request, "portletResource");
    if (Validator.isNotNull(portletResource)) {
        preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
    }

    //String categoriesLocalized = preferences.getValue(ContactFormPortletKeys.CATEGORIES_CONFIGURATION, "");
    Map<Locale, String> categoriesLocalized = LocalizationUtil.getLocalizationMap(preferences, ContactFormPortletKeys.CATEGORIES_CONFIGURATION);

    String categories = LocalizationUtil.getPreferencesValue(preferences, ContactFormPortletKeys.CATEGORIES_CONFIGURATION, themeDisplay.getUser().getLanguageId());
    List<String> categoriesList = Arrays.asList(categories.split(";"));

    String emails = preferences.getValue(ContactFormPortletKeys.TO_EMAIL_CONFIGURATION, "");
    List<String> emailList = Arrays.asList(emails.split(";"));

    int i = 0;

%>

<div class="lfr-form-content">
    <div class="sheet sheet-lg">
        <div class="row">
            <h2><liferay-ui:message key="contact.form.config.title" /></h2>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
                <aui:form action="<%= configurationURL.toString() %>" method="post" >

                    <label><liferay-ui:message key="contact.form.config.categories"/></label>
                    <liferay-ui:input-localized id="categories" name="categories" CssClass="col-md-12" xml="<%=PrevingCategoryUtil.getXmlFromMap(categoriesLocalized)%>" maxLength="100" helpMessage="contact.form.config.categories.help"/>

                    <aui:input name="toEmails" type="text" label="contact.form.config.emails" helpMessage="contact.form.config.emails.help" value="<%=emails%>">
                        <aui:validator name="required" />
                    </aui:input>

                    <h3><liferay-ui:message key="contact.form.config.parser" /></h3>
                    <ul>
                        <% for(String mail:emailList){ %>
                            <c:if test="<%=!Validator.isBlank(mail)%>">
                                <li>

                                    <%if(Validator.isNotNull(categoriesList) && categoriesList.size() > i){%>
                                        <%=categoriesList.get(i)%>
                                    <%} i++;%>

                                    - <span><%=mail%></span>
                                    <c:if test="<%=Validator.isEmailAddress(mail)%>"><liferay-ui:icon image="check" /></c:if>
                                    <c:if test="<%=!Validator.isEmailAddress(mail)%>"><liferay-ui:icon image="close" />*</c:if>
                                </li>
                            </c:if>
                        <%}%>
                    </ul>
                    <p><liferay-ui:message key="contact.form.config.parser.obs" /></p>

                    <aui:button-row>
                        <aui:button type="submit" />
                    </aui:button-row>
                </aui:form>
            </div>
        </div>
    </div>
</div>