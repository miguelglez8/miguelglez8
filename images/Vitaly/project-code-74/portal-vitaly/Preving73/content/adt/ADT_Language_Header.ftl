<#if entries?has_content>
    <div>
        <#if themeDisplay.isSignedIn()>
            <div class="d-inline pr-4">${themeDisplay.getUser().getFullName()}</div>
        </#if>
        <#list entries as curLanguage>
            <div class="d-inline ">
                <#if curLanguage.selected>
                    <strong>${curLanguage.longDisplayName?capitalize}</strong>
                <#else>
                    <a href="/c/portal/update_language?p_l_id=${themeDisplay.getPlid()}&amp;redirect=/group${themeDisplay.getScopeGroup().getFriendlyURL()}${themeDisplay.getLayout().getFriendlyURL()}&amp;languageId=${curLanguage.languageId}" lang="${curLanguage.languageId}">${curLanguage.longDisplayName?capitalize}</a>
                </#if>
                <#if !curLanguage?is_last>
                    |
                </#if>
            </div>
        </#list>
    </div>
</#if>

<style>
    #portlet_com_liferay_site_navigation_language_web_portlet_SiteNavigationLanguagePortlet .portlet-header {
        display: none;
    }

    #portlet_com_liferay_site_navigation_language_web_portlet_SiteNavigationLanguagePortlet .portlet-content {
        padding:0 !important;
    }
</style>