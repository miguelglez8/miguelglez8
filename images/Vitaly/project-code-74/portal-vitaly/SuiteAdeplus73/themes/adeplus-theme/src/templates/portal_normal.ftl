<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
    <title>${the_title} - ${company_name}</title>

    <meta content="initial-scale=1.0, width=device-width" name="viewport"/>

    <@liferay_util["include"] page=top_head_include />

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <#if gtmid != "">
       <!-- Google Tag Manager -->
           <script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
               new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
               j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
               'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
               })(window,document,'script','dataLayer','${gtmid}');</script>
       <!-- End Google Tag Manager -->

    </#if>
</head>

<body id="adeplus" class="${css_class} ${layoutName}">
    <#if gtmid != "">
        <!-- Google Tag Manager (noscript) -->
            <noscript>
                <iframe src="https://www.googletagmanager.com/ns.html?id=${gtmid}"
            height="0" width="0" style="display:none;visibility:hidden">
            </iframe></noscript>
        <!-- End Google Tag Manager (noscript) -->
    </#if>
<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />


<#if showcontrolmenu>
    <@liferay.control_menu />
</#if>
<div class="row" id="body-row">
    <#if show_navigation>
        <div id="sidebar-container" class="sidebar-expanded d-block">
            <#if show_logo>
                <div id="brand-logo" class="brand-logo">Logotipo Adeplus</div>
            </#if>
            <#if has_navigation && is_setup_complete>
                <#include "${full_templates_path}/navigation.ftl" />
            </#if>
        </div>
    </#if>

    <div class="content-wrapper" id="wrapper">
        <div id="dark-background" class="fade-in"></div>
        <#if show_header>
            <header class="header ">

                <#--<@liferay_portlet["runtime"]
                instanceId="user_menu_adeplus"
                portletName="com_liferay_site_navigation_menu_web_portlet_SiteNavigationMenuPortlet"/>-->

                <#if show_user_menu>
                    <@liferay_portlet["runtime"]
                    instanceId="user_menu_company_selector"
                    portletName="com_adeplus_liferay_portlet_company_selector_web_CompanySelectorPortlet"
                    />
                </#if>

                <#if show_user_menu && show_language_selector>
                    <div class="header-separador"></div>
                </#if>

                <#if show_language_selector>
                    <div class="ml-3">
                        <@liferay_portlet["runtime"]
                        portletProviderAction=portletProviderAction.VIEW
                        portletProviderClassName="com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry"/>
                    </div>
                </#if>


                <#--<@liferay_portlet["runtime"]
                portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
                />-->


            </header>
        </#if>

        <section <#if !login_page>id="content"</#if>>
            <h2 class="hide-accessible" role="heading" aria-level="1">${the_title}</h2>

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
        <#if show_footer>
            <footer id="footer" role="contentinfo"
                    class="footer d-flex flex-sm-column flex-md-row justify-content-lg-between align-content-center">
                <div class="brand-logo-footer pb-sm-4 pb-md-0">
                    <img src="${images_folder}/Logotipo_ADEPLUS_blanco.svg" alt="logotipo Adeplus">
                </div>

                <div class="adeplus-footer">
                    <@liferay_portlet["runtime"]
                    instanceId="footer_adeplus"
                    portletName="com_liferay_site_navigation_menu_web_portlet_SiteNavigationMenuPortlet"/>
                </div>



                <ul class="social-footer d-flex justify-content-sm-center pt-sm-4 pt-md-0">
                    <li><a href="https://www.linkedin.com/company/auditoriza-diagnostico-de-sistemas-s-l-" target="_blank"><img src="${images_folder}/Linkedin.svg"
                                                                         alt="Linkedin"/></a></li>
                    <li><a href="https://www.facebook.com/Totaldat" target="_blank"><img
                                    src="${images_folder}/Facebook.svg" alt="Facebook"/></a></li>
                </ul>
            </footer>
        </#if>
    </div>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>
<script src="${javascript_folder}/adeplus.js"></script>
<script src="${javascript_folder}/log_helper.js"></script>

</html>