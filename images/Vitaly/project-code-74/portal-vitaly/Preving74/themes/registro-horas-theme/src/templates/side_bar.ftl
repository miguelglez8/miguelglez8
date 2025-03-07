
 <nav class="navbar-default navbar-static-side" role="navigation" id="top-navigation">
 	<div class="" id="slimScroll">
    	<div class="sidebar-collapse">

	        <ul class="nav" id="side-menu">
				<li class="nav-header">
					<div class="dropdown profile-element row">
						<div class="col"><img alt="Preving" src="${theme_display.getPathThemeImages()+"/preving/logo_preving.png"}" style='max-width: 100%;max-height: 100%;'/></div>
					</div>
				</li>
				<li>
					<#assign userGroupRoleLocalServiceUtil = serviceLocator.findService("com.liferay.portal.kernel.service.UserGroupRoleLocalService")>

					<#assign companyCount = 0 />
					<#list user.getGroups() as company>
						<#assign isSiteAdmin = userGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), company.getGroupId(), "Preving Company Site Admin") />
						<#assign isSiteUser = userGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), company.getGroupId(), "Preving Company Site User") />
						<#if  (isSiteAdmin || isSiteUser ) >
							<#assign companyCount = companyCount + 1 />
						</#if>
					</#list>

					<#if companyCount gt 1>
						<div id="company-selector">
							<select id="select-company">
								<option value="/web/guest/home">Seleccionar</option>
								<#list user.getGroups() as company>
									<#assign isSiteAdmin = userGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), company.getGroupId(), "Preving Company Site Admin") />
									<#assign isSiteUser = userGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), company.getGroupId(), "Preving Company Site User") />
									<#if  (company.isActive() && (isSiteAdmin || isSiteUser)) >
										<option value="/group${company.getFriendlyURL()}" ${company.getGroupId()} <#if company.getGroupId() == themeDisplay.getScopeGroupId()>selected</#if>>
											${company.getName()}
										</option>
									</#if>
								</#list>
							</select>
						</div>
					</#if>

					<script>
						$('#select-company').change(function() {
							window.location = $(this).val();
						});
					</script>
					<style>
						#company-selector{margin: 8px;}
						select#select-company {height: 30px;width: 200px;}
					</style>
				</li>
	            <#assign velocityCount=0 />

	            <#if nav_items??>
		            <#list nav_items as nav_item>
						<#assign nav_item_attr_has_popup="" />
						<#assign nav_item_attr_selected="" />
						<#assign nav_item_css_class = "" />
						<#assign velocityCount = velocityCount +1 />


						<#if  (nav_item.isSelected())>
							<#assign nav_item_attr_selected="aria-selected='true'" />
							<#assign nav_item_css_class = "active" />
						</#if>

						<li class="${nav_item_css_class} item${velocityCount}" id="layout_${nav_item.getLayoutId()}" ${nav_item_attr_selected} role="presentation">
							<a aria-labelledby="layout_${nav_item.getLayoutId()}" href="${nav_item.getURL()}" ${nav_item_attr_has_popup} ${nav_item.getTarget()} role="menuitem">

								<span class="nav-label menu-name">${nav_item.getName()}</span>

							<#if  (! nav_item.hasChildren())>
								</a>
							<#else>
								<span class="fa arrow"></span>
							</a>

								<ul class="nav nav-second-level">
		            				<#list nav_item.getChildren() as nav_child>
										<#assign  nav_child_css_class = "" />
										<#if  (nav_child.isSelected())>
											<#assign nav_child_css_class = "active" />
										</#if>

                                        <#if  (nav_child.getName() != "Configurar horario" )> <!-- START -->
										    <li class="${nav_child_css_class}">
										<#else>
										     <li class="${nav_child_css_class} clsConfigurarCalendarios">
										</#if><!-- END -->

											<#if  (!nav_child.hasChildren())>
												<a href="${nav_child.getURL()}" >
													${nav_child.getName()}
												</a>
											<#else>
												<a href="${nav_child.getURL()}" >
													${nav_child.getName()}
													<span class="fa arrow"></span>
												</a>
												<ul class="nav nav-third-level">
													<#list nav_child.getChildren() as nav_child3 >
														<li><a href="${nav_child3.getURL()}">${nav_child3.getName()}</a></li>
													</#list>
												</ul>
											</#if>
										</li>
									</#list>
								</ul>
					 		</#if>
						</li>
					</#list>
				</#if>
				<#if (is_signed_in)>
					<#if user.getLocale() == "ca_ES">
						<li><a href="${themeDisplay.portalURL}/c/portal/logout"><i class="fa fa-sign-out" aria-hidden="true"></i><span class="nav-label menu-name">Sortir</span></a></li>
					<#else>
						<li><a href="${themeDisplay.portalURL}/c/portal/logout"><i class="fa fa-sign-out" aria-hidden="true"></i><span class="nav-label menu-name">Salir</span></a></li>
					</#if>
				</#if>
	        </ul>
    	</div>
    	<div id="slimScrollBar"></div>
    	<div id="slimScrollRail"></div>
	</div>
</nav>

<script>
    $(document).ready(function(){
        var url = '/api/jsonws/preving.companyconf/get-company-conf-by-company-id-and-group-id/company-id/${theme_display.getCompanyId()}/group-id/${theme_display.getScopeGroupId()}?p_auth=' + Liferay.authToken;
           $.ajax({
                url: url,
                dataType: "json",
                success: function(data) {
                    console.log ("getConfig: ")
                    console.log (data)
                    $(".clsConfigurarCalendarios").each(function(){
                        $(this).addClass("d-none");
                    });
                    if(data.isOwnCalendars && data.isOwnCalendars == true){
                        $(".clsConfigurarCalendarios").each(function(){
                            $(this).removeClass("d-none");
                        });
                    }


                }
            });

    });

</script>