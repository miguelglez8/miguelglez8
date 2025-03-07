<#if getterUtil.getBoolean(theme_settings["use-collapsed-menu"]!"", false)>
	<#assign css_class = css_class + " collapsed-menu" />
</#if>

<#assign showcontrolmenu = false />
<#if is_signed_in>
	<#assign    roles = user.getRoles()
				showcontrolmenu = false />

	<#list roles as role>
		<#if role.getName() == "Administrator" <#--|| role.getName() == "Preving Administrator"--> >
			<#assign showcontrolmenu = true />
			<#break>
		</#if>
	</#list>
</#if>

<#assign nav_header_profile_menu =  getterUtil.getString(theme_settings["profile-left-header-menu"]!"", "") />
<#assign footer = getterUtil.getString(theme_settings["footer"]!"", "") />
<#assign rightsidebar = getterUtil.getString(theme_settings["right_sidebar"]!"", "") />
<#assign gtmid = getterUtil.getString(theme_settings["gtmid"]!"") />


<#if journalContentUtil??>
	<#if journalContentUtil.getContent(group_id?long,  nav_header_profile_menu , "", "${locale}", theme_display) != null >
	    <#assign nav_header_profile_menu = journalContentUtil.getContent(group_id?long,  nav_header_profile_menu, "", "${locale}", theme_display) />
	</#if>

	<#if getterUtil.getBoolean(theme_settings["use-global-footer"]!"", false)>
		<#if journalContentUtil.getContent(theme_display.companyGroupId?long, footer, "", "${locale}", theme_display)>
		    <#assign footer = journalContentUtil.getContent(theme_display.companyGroupId?long, footer, "", "${locale}", theme_display) />
		<#elseif journalContentUtil.getContent(group_id?long, footer, "", "${locale}", theme_display) >
			<#assign footer = journalContentUtil.getContent(group_id?long, footer, "", "${locale}", theme_display) />
		</#if>
	<#else>
		<#if journalContentUtil.getContent(group_id?long, footer, "", "${locale}", theme_display) >
			<#assign footer = journalContentUtil.getContent(group_id?long, footer, "", "${locale}", theme_display) />
		</#if>
	</#if>	
</#if>
	


