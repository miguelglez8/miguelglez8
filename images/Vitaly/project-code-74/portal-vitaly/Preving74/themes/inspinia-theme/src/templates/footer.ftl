<div class="footer-links py-3">
    <#assign urlSite = "/group" />
    <#if themeDisplay.getLayout().isPublicLayout()>
        <#assign urlSite = "/web" />
    </#if>

    <#if user.getLocale() == "ca_ES">
        <ul>
            <li class="p-2"><a href="${urlSite}${themeDisplay.getScopeGroup().getFriendlyURL()}/aviso-legal">Avís Legal</a></li>
            <li class="p-2"><a href="${urlSite}${themeDisplay.getScopeGroup().getFriendlyURL()}/condiciones-de-uso">Condicions d'ús</a></li>
        </ul>
    <#else>
        <ul>
            <li class="p-2"><a href="${urlSite}${themeDisplay.getScopeGroup().getFriendlyURL()}/aviso-legal">Aviso legal</a></li>
            <li class="p-2"><a href="${urlSite}${themeDisplay.getScopeGroup().getFriendlyURL()}/condiciones-de-uso">Condiciones de uso</a></li>
        </ul>
    </#if>
</div>