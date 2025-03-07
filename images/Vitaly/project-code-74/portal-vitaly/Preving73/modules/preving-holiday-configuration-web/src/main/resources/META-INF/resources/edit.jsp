<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.preving.liferay.portlet.holiday.configuration.web.constants.HolidayConfigurationPortletKeys" %>
<%@ include file="/init.jsp" %>

<h2><liferay-ui:message key="holiday.configuration.view.edit.title"/></h2>

<liferay-portlet:actionURL name="edit" var="editURL"/>

<%
    int numberDays = 0;
    int numberMonths = 0;

    ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

    String months[]={LanguageUtil.get(bundle, "holiday.configuration.january"),
            LanguageUtil.get(bundle, "holiday.configuration.february"),
            LanguageUtil.get(bundle, "holiday.configuration.march"),
            LanguageUtil.get(bundle, "holiday.configuration.april"),
            LanguageUtil.get(bundle, "holiday.configuration.may"),
            LanguageUtil.get(bundle, "holiday.configuration.june"),
            LanguageUtil.get(bundle, "holiday.configuration.july"),
            LanguageUtil.get(bundle, "holiday.configuration.august"),
            LanguageUtil.get(bundle, "holiday.configuration.september"),
            LanguageUtil.get(bundle, "holiday.configuration.october"),
            LanguageUtil.get(bundle, "holiday.configuration.november"),
            LanguageUtil.get(bundle, "holiday.configuration.december")};

    long holidayId = ParamUtil.getLong(request, "holidayId", 0);
    Holiday holidayEdit = HolidayLocalServiceUtil.getHoliday(holidayId);
%>

<c:if test="<%=Validator.isNotNull(holidayEdit)%>">
    <liferay-portlet:actionURL name="saveHoliday" var="saveHolidayURL"  />
    <aui:form name="edit_form" action="<%= saveHolidayURL.toString() %>" method="post">

        <aui:input name="holidayId" value="<%=holidayEdit.getHolidayId()%>" type="hidden" />

        <aui:fieldset label="">
            <div class="row">
                <div class="col-sm-3 divNombre">
                    <label><liferay-ui:message key="holiday.configuration.view.search.name"/></label>
                    <liferay-ui:input-localized id="inputName" name="name" CssClass="col-md-12" xml="<%=holidayEdit.getName()%>" maxLength="50"/>
                </div>
                <div class="col-sm-3">
                    <label for="typeHoliday" class="col-form-label" style="font-weight: normal !important;"><liferay-ui:message key="holiday.configuration.view.search.type"/></label>
                    <select id="typeHoliday" name="<portlet:namespace/>typeHoliday" class="form-control">
                        <option value="<%=HolidayConfigurationPortletKeys.HOLIDAY_TYPE_NACIONAL_VALUE%>"
                                <%=holidayEdit.getTypeHoliday().equals(HolidayConfigurationPortletKeys.HOLIDAY_TYPE_NACIONAL_VALUE)?"selected":""%>>
                            <liferay-ui:message key="holiday.configuration.nacional"/>
                        </option>
                        <option value="<%=HolidayConfigurationPortletKeys.HOLIDAY_TYPE_AUTONOMICO_VALUE%>"
                                <%=holidayEdit.getTypeHoliday().equals(HolidayConfigurationPortletKeys.HOLIDAY_TYPE_AUTONOMICO_VALUE)?"selected":""%>>
                            <liferay-ui:message key="holiday.configuration.autonomico"/>
                        </option>
                        <option value="<%=HolidayConfigurationPortletKeys.HOLIDAY_TYPE_LOCAL_VALUE%>"
                                <%=holidayEdit.getTypeHoliday().equals(HolidayConfigurationPortletKeys.HOLIDAY_TYPE_LOCAL_VALUE)?"selected":""%>>
                            <liferay-ui:message key="holiday.configuration.local"/>
                        </option>
                        <option value="<%=HolidayConfigurationPortletKeys.HOLIDAY_TYPE_COMPANY_VALUE%>"
                                <%=holidayEdit.getTypeHoliday().equals(HolidayConfigurationPortletKeys.HOLIDAY_TYPE_COMPANY_VALUE)?"selected":""%>>
                            <liferay-ui:message key="holiday.configuration.company"/>
                        </option>
                    </select>
                </div>
                <div class="col-sm-3">
                    <label for="selectDays" class="col-form-label" style="font-weight: normal !important;"><liferay-ui:message key="holiday.configuration.view.search.day"/></label>
                    <select class="form-control" id="selectDays" name="<portlet:namespace/>day" value="<%=holidayEdit.getDay()%>">
                        <option value="<%=holidayEdit.getDay()%>"><%=holidayEdit.getDay()%></option>
                        <%
                            for (numberDays = 1; numberDays < 32; numberDays++) {
                        %>
                        <option value="<%=numberDays%>"><%=numberDays%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="col-sm-3 divMes">
                    <label for="selectMonth" class="col-form-label" style="font-weight: normal !important;"><liferay-ui:message key="holiday.configuration.view.search.month"/></label>
                    <select class="form-control" id="selectMonth" name="<portlet:namespace/>month" value="<%=holidayEdit.getMonth()%>">
                        <%
                            for (numberMonths = 1; numberMonths < 13; numberMonths++) {
                        %>
                        <option value="<%=numberMonths%>" <%=holidayEdit.getMonth() == numberMonths ? "selected":""%> ><%=months[numberMonths-1]%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
            </div>
            <div class="row checkDivsHolidays">
                <div class="col-sm-3">
                    <span class="control-label activeHolDiv"><liferay-ui:message key="holiday.configuration.view.search.active"/></span>
                    <label for="radioActiveYes">
                        <input name="<portlet:namespace/>active" class="field" id="radioActiveYes"  type="radio" value="true" <%=holidayEdit.getActive() ? "checked" : ""%>>
                        <liferay-ui:message key="holiday.configuration.view.active.yes"/>
                    </label>
                    <label for="radioActiveNo">
                        <input name="<portlet:namespace/>active" class="field" id="radioActiveNo" type="radio" value="false" <%=!holidayEdit.getActive() ? "checked" : ""%>>
                        <liferay-ui:message key="holiday.configuration.view.active.no"/>
                    </label>
                </div>
                <div class="col-sm-3" style="padding:0">
                    <span class="control-label allowSignHolDiv"><liferay-ui:message key="holiday.configuration.view.allow.sign"/></span>
                    <label for="radioAllowYes">
                        <input name="<portlet:namespace/>activeAllow" class="field" id="radioAllowYes"  type="radio" value="true" <%=holidayEdit.getAllowSign() ? "checked" : ""%>>
                        <liferay-ui:message key="holiday.configuration.view.active.yes"/>
                    </label>
                    <label for="radioAllowNo">
                        <input name="<portlet:namespace/>activeAllow" class="field" id="radioAllowNo" type="radio" value="false" <%=!holidayEdit.getAllowSign() ? "checked" : ""%>>
                        <liferay-ui:message key="holiday.configuration.view.active.no"/>
                    </label>
                </div>
            </div>
        </aui:fieldset>

        <aui:button-row>
            <c:if test="<%=Validator.isNotNull(holidayEdit.getHolidayId())%>" >
                <liferay-portlet:actionURL name="delete" var="deleteURL">
                    <portlet:param name="holidayId" value="<%=String.valueOf(holidayEdit.getHolidayId())%>" />
                </liferay-portlet:actionURL>
                <a class="btn btn-danger" href="<%=deleteURL.toString()%>" role="button"><liferay-ui:message key="holiday.configuration.view.button.edit.delete"/></a>
            </c:if>
            <aui:button value="holiday.configuration.view.button.edit.accept" type="submit"/>
            <portlet:renderURL portletMode="view" var="viewURL" />
            <aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
        </aui:button-row>
    </aui:form>
</c:if>

<style>
    /*Media Queries CSS*/
    @media screen and (max-width: 576px) {
        .activeHolDiv{padding-right:16%;}
        .divMes{width:99%;}
        .divNombre{margin-left:1%;}
        .allowSignHolDiv{padding-right:1.7%;}
        .checkDivsHolidays{margin-left:2%;}
    }
</style>