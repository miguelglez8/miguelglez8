<#--
This file allows you to override and define new FreeMarker variables.
-->

<#assign
    layoutName = layout.getName(locale) />

<#assign showcontrolmenu = false />
<#if is_signed_in>
    <#assign    roles = user.getRoles()
                showcontrolmenu = false />

    <#list roles as role>
        <#if role.getName() == "Administrator" || role.getName() == "Site Administrator" >
            <#assign showcontrolmenu = true />
            <#break>
        </#if>
    </#list>
</#if>
<#assign gtmid = getterUtil.getString(theme_settings["gtmid"]!"") />
<#assign
    show_logo = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-logo"))
    show_footer = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-footer"))
    show_header = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-header"))
    show_navigation = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-navigation"))
    show_user_menu = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-user-menu"))
    show_language_selector = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-language-selector"))
    login_page = getterUtil.getBoolean(themeDisplay.getThemeSetting("login-page"))
    />

