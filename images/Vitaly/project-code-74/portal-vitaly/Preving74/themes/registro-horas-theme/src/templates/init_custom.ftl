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


<#assign gtmid = getterUtil.getString(theme_settings["gtmid"]!"") />


<#if journalContentUtil??>
	<#if journalContentUtil.getContent(group_id?long,  nav_header_profile_menu , "", "${locale}", theme_display) != null >
	    <#assign nav_header_profile_menu = journalContentUtil.getContent(group_id?long,  nav_header_profile_menu, "", "${locale}", theme_display) />
	</#if>
</#if>
	
