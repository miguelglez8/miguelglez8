<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} </title>
	<meta content="initial-scale=1.0, width=device-width" name="viewport" />
 	
 	<link href="${themeDisplay.getPathThemeRoot()}/font-awesome/css/font-awesome.css" rel="stylesheet" /> 

	<!-- Toastr style -->
    <link href="${themeDisplay.getPathThemeRoot()}/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    
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

<body class="${css_class} site-body">

	<@liferay_ui["quick-access"] contentId="#main-content" />

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
				
	<div id="wrapper">
		<#include "${full_templates_path}/side_bar.ftl" />
		
		<#if getterUtil.getBoolean(theme_settings['right-sidebar-fixed']!"", false) == true>
			<div id="page-wrapper" class="gray-bg sidebar-content">
		<#else>
			<div id="page-wrapper" class="gray-bg">			
		</#if>
				
				<#include "${full_templates_path}/top_header.ftl" />
					
				<#if selectable>
					<@liferay_util["include"] page=content_include />
				<#else>
					${portletDisplay.recycle()}
		
					${portletDisplay.setTitle(the_title)}
		
					<@liferay_theme["wrap-portlet"] page="portlet.ftl">
						<@liferay_util["include"] page=content_include />
					</@>
				</#if>

			</div><!-- End page-wrapper -->	
	</div><!-- End wrapper -->	
	
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



	<@liferay_util["include"] page=body_bottom_include />

	<@liferay_util["include"] page=bottom_include />

</body>

</html>