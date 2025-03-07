<ul class="list-group">
	<li>
		<a href="#" data-toggle="sidebar-colapse" class="align-items-center justify-content-end">
			<div class="d-flex w-100 justify-content-end">
				<span class="collapse-icon adeplus-sidebar-icon-expand"></span>
				<span id="collapse-text" class="text-menu-collapsed">Collapse</span>
			</div>
		</a>
	</li>

	<#assign conta = 1 />
	<#list nav_items as nav_item>
		<#assign
			nav_item_attr_has_popup = ""
			nav_item_css_class = ""
			nav_item_layout = nav_item.getLayout()
		/>

		<#if nav_item.isSelected()>
			<#assign
				nav_item_attr_has_popup = "aria-haspopup='true'"
				nav_item_css_class = "selected active"
			/>
		</#if>

		<#if !nav_item.hasChildren()>
			<li class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation" >
				<a href="${nav_item.getURL()}" aria-expanded="false" aria-labelledby="layout_${nav_item.getLayoutId()}" ${nav_item_attr_has_popup}
				   class="list-group-item list-group-item-action flex-column align-items-start cool-link">
					<div class="d-flex w-100 align-items-center">
						<@liferay_theme["layout-icon"] layout=nav_item_layout nav_item_css_class="list-item-img" />
						<span class="list-item-text menu-collapsed">${nav_item.getName()}</span>
					</div>
				</a>
			</li>
		</#if>

		<#if nav_item.hasChildren()>
			<li>
				<a href="#submenu${conta}" data-toggle="collapse" aria-expanded="false"
				   class="list-group-item list-group-item-action flex-column align-items-start">
					<div class="d-flex w-100">
						<@liferay_theme["layout-icon"] layout=nav_item_layout nav_item_css_class="list-item-img" />
						<span class="list-item-text menu-collapsed">${nav_item.getName()}</span>
						<span class="submenu-icon ml-auto"></span>
					</div>
				</a>
				<!-- Submenu content -->
				<div id='submenu${conta}' class="collapse sidebar-submenu">
					<#list nav_item.getChildren() as nav_child>
						<#assign nav_child_css_class = "" />
						<#if nav_child.isSelected()>
							<#assign nav_child_css_class = "selected active" />
						</#if>
						<a href="${nav_child.getURL()}" class="list-group-item list-group-item-action ${nav_child_css_class}">
							<span class="menu-collapsed">${nav_child.getName()}</span>
						</a>
					</#list>
				</div>
			</li>
			<#assign conta++ />
		</#if>
	</#list>
</ul>
<@liferay_portlet["runtime"] defaultPreferences=freeMarkerPortletPreferences.getPreferences("portletSetupPortletDecoratorId", "barebone") portletProviderAction=portletProviderAction.VIEW instanceId="NoticesInstanceID" portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet" />
<@liferay_portlet["runtime"] portletName="com_adeplus_liferay_portlet_audit_nav_AuditNavPortlet" defaultPreferences=freeMarkerPortletPreferences.getPreferences("portletSetupPortletDecoratorId", "barebone") instanceId="AuditInstanceID" portletProviderAction=portletProviderAction.VIEW/>
