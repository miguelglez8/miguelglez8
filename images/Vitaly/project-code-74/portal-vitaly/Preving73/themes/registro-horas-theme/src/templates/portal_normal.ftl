<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} </title>
	<meta content="initial-scale=1.0, width=device-width" name="viewport" />
 	
 	<link href="${themeDisplay.getPathThemeRoot()}/font-awesome/css/font-awesome.css" rel="stylesheet" /> 

	<!-- Toastr style -->
    <link href="${themeDisplay.getPathThemeRoot()}/css/plugins/toastr/toastr.min.css" rel="stylesheet">

    <!-- Gritter -->
    <!-- <link href="${themeDisplay.getPathThemeRoot()}/js/plugins/gritter/jquery.gritter.css" rel="stylesheet"> -->
    
    <link href="${themeDisplay.getPathThemeRoot()}/css/animate.css" rel="stylesheet" />
	<link href="${themeDisplay.getPathThemeRoot()}/css/plugins/iCheck/custom.css" rel="stylesheet" />
	
	<@liferay_util["include"] page=top_head_include />
	
	<#assign current = portalUtil.getCurrentCompleteURL(request) />

   
	<!-- Mainly scripts -->
	<script>
         window.__define = window.define;
         window.__require = window.require;
         window.define = undefined;
         window.require = undefined;
     </script>
	
	<script src="${themeDisplay.getPathThemeRoot()}/js/plugins/jquery-ui/jquery-ui.min.js"></script>
	
	<script src="${themeDisplay.getPathThemeRoot()}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="${themeDisplay.getPathThemeRoot()}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDQTpXj82d8UpCi97wzo_nKXL7nYrd4G70" type="text/javascript"></script>
	<!-- Toastr -->
    <script src="/o/inspinia-theme/js/plugins/toastr/toastr.min.js" type="text/javascript" charset="utf-8"></script>
    
	<script src="${themeDisplay.getPathThemeRoot()}/js/custom.js"></script>
	<script>
         window.define = window.__define;
         window.require = window.__require;
         window.__define = undefined;
         window.__require = undefined;
    </script>
    <#if gtmid != "">
        <!-- Google Tag Manager -->
        <script>
            (function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
                new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
                j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
                'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
                })(window,document,'script','dataLayer','${gtmid}');
        </script>
        <!-- End Google Tag Manager -->
     </#if>
</head>

<#if getterUtil.getBoolean(theme_settings["show-welcome-message"]!"", false)>
	<script>
        $(document).ready(function() {
	        setTimeout(function() {
	            toastr.options = {closeButton: true,progressBar: true,showMethod: 'slideDown',timeOut: 4000};
	            toastr.success('${user.getFullName()}', 'Welcome to ${company_name}');
	        }, 1300);
         });
     </script>
</#if>


<#if getterUtil.getBoolean(theme_settings["no-left-menu"]!"", false)>
	<#assign css_class = css_class +" top-navigation" />
</#if>
	
<#if getterUtil.getBoolean(theme_settings['use-full-height']!"", false)>
	<#assign css_class = css_class + " fixed-sidebar full-height-layout" />
 </#if>
 
 <#if getterUtil.getBoolean(theme_settings['use-navbar-closed']!"", false)>
	<#assign css_class = css_class + " canvas-menu" />
 </#if>
  
  	<#if getterUtil.getBoolean(theme_settings['locked-page']!"", false)>
	<#assign css_class = css_class +" gray-bg locked-page" />
</#if>

<#if getterUtil.getString(theme_settings['color_schemes']!"", "") == "Default">
	<#assign css_class = css_class +" skin-0" />
<#elseif (getterUtil.getString(theme_settings['color_schemes']!"", "") == "LIGHT_BLUE")>
	<#assign css_class = css_class +" skin-1" />
<#elseif (getterUtil.getString(theme_settings['color_schemes']!"", "") == "YELLOW")>
	<#assign css_class = css_class +" skin-3" />
<#elseif (getterUtil.getString(theme_settings['color_schemes']!"", "") == "ULTRA")>
	<#assign css_class = css_class +" skin-2" />
</#if>

<body class="${css_class} site-body">
    <#if gtmid != "">
        <!-- Google Tag Manager (noscript) -->
            <noscript>
                <iframe src="https://www.googletagmanager.com/ns.html?id=${gtmid}"
            height="0" width="0" style="display:none;visibility:hidden">
            </iframe></noscript>
        <!-- End Google Tag Manager (noscript) -->
    </#if>
	<@liferay_util["include"] page=body_top_include />

	<#if showcontrolmenu>
		<@liferay.control_menu />
	</#if>
	
	<#if getterUtil.getBoolean(theme_settings['locked-page']!"", false) == false>
					
		<div id="wrapper">
			<#if getterUtil.getBoolean(theme_settings['no-left-menu']!"", false)  == false>
				<#include "${full_templates_path}/side_bar.ftl" />
			</#if>
			
			<#if getterUtil.getBoolean(theme_settings['right-sidebar-fixed']!"", false) == true>
				<div id="page-wrapper" class="gray-bg sidebar-content">
			<#else>
				<div id="page-wrapper" class="gray-bg">			
			</#if>
			
					<#if getterUtil.getBoolean(theme_settings['no-left-menu']!"", false)  == false>
						<#include "${full_templates_path}/top_header.ftl" />
					<#else>
						<#include "${full_templates_path}/top_navigation.ftl" />
					</#if>
			
					<#if selectable>
						<@liferay_util["include"] page=content_include />
					<#else>
						${portletDisplay.recycle()}
			
						${portletDisplay.setTitle(the_title)}
			
						<@liferay_theme["wrap-portlet"] page="portlet.ftl">
							<@liferay_util["include"] page=content_include />
						</@>
					</#if>

					<#if getterUtil.getBoolean(theme_settings['display-footer']!"", false) == true>
						<div class="footer">

							<#include "${full_templates_path}/footer.ftl" />

						</div>
					</#if>
				</div><!-- End page-wrapper -->	
		</div><!-- End wrapper -->	
		
		<#if getterUtil.getBoolean(theme_settings["display-right-sidebar-toggle"]!"", false) == true>
			<div id="right-sidebar">
				
				<#assign preferences = freeMarkerPortletPreferences.getPreferences("articleId", "${rightsidebar}") />
				<@liferay_portlet["runtime"]
					defaultPreferences="${preferences}"
					portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet" 
					instanceId="rightsidebar"
				/>
			</div>
		</#if>
		
		<!-- Custom and plugin javascript -->
		<script>
	         window.__define = window.define;
	         window.__require = window.require;
	         window.define = undefined;
	         window.require = undefined;
	     </script>
	     
		<script src="${themeDisplay.getPathThemeRoot()}/js/plugins/pace/pace.min.js"></script>
		
		<!-- Peity -->
	    <script src="${themeDisplay.getPathThemeRoot()}/js/plugins/peity/jquery.peity.min.js"></script>
	    <script src="${themeDisplay.getPathThemeRoot()}/js/demo/peity-demo.js"></script>
	    
		<!-- iCheck -->
    	<script src="${themeDisplay.getPathThemeRoot()}/js/plugins/iCheck/icheck.min.js"></script>
    	
    	<!-- jQuery UI custom -->
		<script src="${themeDisplay.getPathThemeRoot()}/js/jquery-ui.custom.min.js"></script>
		<script src="${themeDisplay.getPathThemeRoot()}/js/inspinia.js" ></script>
		
		<script>
	         window.define = window.__define;
	         window.require = window.__require;
	         window.__define = undefined;
	         window.__require = undefined;
	     </script>
	<#else>
		<@liferay_util["include"] page=body_top_include />
		<section id="content">
			<#if selectable>
				<@liferay_util["include"] page=content_include />
			<#else>
				${portletDisplay.recycle()}
	
				${portletDisplay.setTitle(the_title)}
	
				<@liferay_theme["wrap-portlet"] page="portlet.ftl">
					<@liferay_util["include"] page=content_include />
				</@>
			</#if>
		</section>
	</#if>
	

	<#include "${full_templates_path}/settings.ftl" />
	<#include "${full_templates_path}/liferay_settings.ftl" />
			
	<@liferay_util["include"] page=body_bottom_include />
	<@liferay_util["include"] page=bottom_include />

</body>

</html>