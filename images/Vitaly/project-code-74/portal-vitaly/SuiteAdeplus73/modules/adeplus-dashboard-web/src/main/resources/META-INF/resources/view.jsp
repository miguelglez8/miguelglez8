<%@ page import="com.adeplus.liferay.portlet.dashboard.web.constants.AdeplusDashboardPortletKeys" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Application" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Comp" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.CompApplicationLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.UserApplicationLocalServiceUtil" %>
<%@ page import="com.liferay.document.library.kernel.model.DLFileEntry" %>
<%@ page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ include file="/init.jsp" %>

<%
    ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

    Comp comp = (Comp) request.getAttribute(AdeplusDashboardPortletKeys.USER_COMP);
    List<Application> applicationList = (List<Application>) request.getAttribute(AdeplusDashboardPortletKeys.APPLICATIONS_LIST);
    List<Long> applicationUser = (List<Long>) request.getAttribute(AdeplusDashboardPortletKeys.USER_APPS);
%>

<div class="dashboard">
    <div class="clientes-wrapper">
        <div class="titulo-seccion">
            <h2><liferay-ui:message key="dashboard.view.title"/></h2>
        </div>
        <div class="clientes">
            <c:forEach items="<%=applicationList%>" var="application">
                <%
                    boolean hasPermission = false;
                    Application applicationDetail = (Application) pageContext.getAttribute("application");

                    if (Validator.isNotNull(comp)
                            && applicationUser.contains(applicationDetail.getApplicationId())) {
                        hasPermission = true;
                    }
                %>

                <c:if test="<%=Validator.isNotNull(applicationDetail) && applicationDetail.getLogo() > 0%>">
                    <%
                        DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(applicationDetail.getLogo());
                        String url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + "/" +
                                dlFileEntry.getFolderId() + "/" + dlFileEntry.getTitle();
                    %>
                    <div class="card-flip">
                        <div class="card">
                            <div class="<%=hasPermission?"frente-active":"frente"%>">
                                <img src="<%=url%>"/>
                                <h4><%=applicationDetail.getName()%>
                                </h4>
                            </div>
                            <div class="<%=hasPermission?"dorso-active":"dorso"%>">
                                <div class="descripcion">
                                    <h4><%=applicationDetail.getName()%>
                                    </h4>
                                    <p><%=applicationDetail.getDescription()%></p>
                                </div>
                                <c:if test="<%=hasPermission%>">
                                    <a href="<%=applicationDetail.getUrl()%>" target="_blank"
                                       class="btn btn-outline-light btn-secondary"><liferay-ui:message key="dashboard.view.enter"/></a>
                                </c:if>
                                <c:if test="<%=!hasPermission%>">
                                    <a href="${infoURL}" target="_blank" class="btn btn-outline-light"><liferay-ui:message key="dashboard.view.more.info.action"/></a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>
