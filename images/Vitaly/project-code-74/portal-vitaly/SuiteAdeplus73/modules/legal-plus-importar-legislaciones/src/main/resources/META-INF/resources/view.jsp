<%@ include file="/init.jsp" %>

<%

ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getLocale());
String backUrl = themeDisplay.getScopeGroup().getDisplayURL(themeDisplay, false) + LanguageUtil.get(bundle, "import.view.back.url");

%>

<div class="title-group mt-3">
    <a href="<%= backUrl %>" class="toBackView">
        <i class="icon-arrow-left"></i>
        <liferay-ui:message key="import.view.back"/>
    </a>
</div>

<liferay-ui:tabs names="import.legislacion.title.tab,import.requisito.title.tab" refresh="false">

    <liferay-ui:section>
        <%@include file="legislaciones.jspf" %>
    </liferay-ui:section>

    <liferay-ui:section>
        <%@include file="requisitos.jspf" %>
    </liferay-ui:section>

</liferay-ui:tabs>