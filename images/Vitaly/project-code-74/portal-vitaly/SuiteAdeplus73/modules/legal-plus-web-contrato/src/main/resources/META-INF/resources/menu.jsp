<%

String tab = (String) request.getAttribute(WebContratoPortletKeys.TAB_ACTIVE);
String tabLegislaciones = (String) request.getAttribute(WebContratoPortletKeys.TAB_LEGISLACIONES);
String tabContrato = (String) request.getAttribute(WebContratoPortletKeys.TAB_CONTRACT);
String tabCalendario = (String) request.getAttribute(WebContratoPortletKeys.TAB_CALENDARIO);
String tabBack = (String) request.getAttribute(WebContratoPortletKeys.TAB_BACK);

%>

<div class="title-group mt-3">
    <a href="${tabBack}" class="toBackView">
        <i class="icon-arrow-left"></i>
        <liferay-ui:message key="company.back.title"/>
    </a>
</div>

<div class="mb-4">
    <ul class="nav nav-tabs">
      <li class="nav-item">
        <a class="nav-link <%= WebContratoPortletKeys.TAB_LEGISLACIONES.equals(tab) ? "active" : "" %>" href="<%= tabLegislaciones %>">
            <liferay-ui:message key="company.legislacion.title.tab"/>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link <%= WebContratoPortletKeys.TAB_CALENDARIO.equals(tab) ? "active" : "" %>" href="<%= tabCalendario %>">
            <liferay-ui:message key="company.calendario.title.tab"/>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link <%= WebContratoPortletKeys.TAB_CONTRACT.equals(tab) ? "active" : "" %>" href="<%= tabContrato %>">
            <liferay-ui:message key="company.contrato.title.tab"/>
        </a>
      </li>
    </ul>
</div>