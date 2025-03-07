<div class="row border-bottom white-bg horiz-menu">
	<div class="right-navs-container">
	    <ul class="nav-right navbar-top-links horiz-navs">
	        <#if getterUtil.getBoolean(theme_settings["display-right-sidebar-toggle"]!"", false) == true>
	        	<li><a class="right-sidebar-toggle"><i class="fa fa-tasks"></i> </a></li>
			</#if>
			
			<#if getterUtil.getBoolean(theme_settings['show-language-choice']!"", false) == true >
	            <li id="Language-choices">
					<@liferay_ui["language"]/>
				</li>
			</#if>
	    </ul>
	    
	    <div class="navbar-search horiz-navs">
	        <div class="navbar-form-custom"><@liferay.search /></div>
	    </div>
    </div>
	<nav class="navbar navbar-static-top" role="navigation" id="top-navigation">
	    <div class="navbar-header">
	        <button class="navbar-toggle collapse-menu" type="button">
	            <i class="fa fa-reorder"></i>
	        </button>
	    </div>
	    <div class="navbar-collapse collapse navbar-top-navigation top-navigation-ul" id="navbar">
	        <ul class="nav navbar-nav">
	        	<#if nav_items??>
		            <#list nav_items as nav_item>
		            	<#assign nav_item_attr_has_popup="" />
						<#assign nav_item_attr_selected="" />
						<#assign nav_item_css_class = "" />
		
						<#if  nav_item.isSelected()>
							<#assign nav_item_attr_selected="aria-selected='true'" />
							<#assign nav_item_css_class = "active" />
						</#if>
						
						
						<#if ! nav_item.hasChildren()>
							<li class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" ${nav_item_attr_selected} role="presentation">
								<a aria-expanded="false" role="button" aria-labelledby="layout_${nav_item.getLayoutId()}" href="${nav_item.getURL()}" ${nav_item_attr_has_popup} ${nav_item.getTarget()} >
									<span class="nav-label">${nav_item.getName()}</span>
								</a>
							</li>
						<#else>
							<li class="${nav_item_css_class} dropdown" id="layout_${nav_item.getLayoutId()}" ${nav_item_attr_selected} role="presentation">
								<a aria-labelledby="layout_${nav_item.getLayoutId()}" ${nav_item_attr_has_popup} ${nav_item.getTarget()} aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown">
									<span class="nav-label">${nav_item.getName()}<span class="caret"></span></span>
								</a>
								<ul role="menu" class="dropdown-menu">
									<#list nav_item.getChildren() as nav_child>
										<li>
											<#if  (!nav_child.hasChildren())>
												<a href="${nav_child.getURL()}" > ${nav_child.getName()} </a>
											<#else>
												<a href="${nav_child.getURL()}" > ${nav_child.getName()} <span class="fa arrow"></span> </a>
												<ul class="nav nav-third-level">
													<#list nav_child.getChildren() as nav_child3 >
														<li><a href="${nav_child3.getURL()}">${nav_child3.getName()}</a></li>
													</#list>
												</ul>
											</#if>
										</li>
									</#list>
								</ul>
							</li>
					 	</#if>		
					</#list>
			 	</#if>		
			 <#if ! is_signed_in>
				<li><a data-redirect="false" href="${sign_in_url}" rel="nofollow" id="sign-in">${sign_in_text}</a></li>
            </#if>
	        </ul>	        
	    </div>
	</nav>
</div>


<script>
	$(document).ready(function(){
		var w = $('#top-navigation .navbar-top-navigation .top-navigation-ul').width();
		var total = $(window).width() - $('#top-navigation .navbar-brand').width();
		
		if(w > total){
			$('.horiz-menu').addClass("large-menu");
		}
		$('.collapse-menu').click(function(){
			$('#navbar').toggleClass('visible');
		});
	});
</script>