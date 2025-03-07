<div class="row border-bottom" xmlns:c="http://www.w3.org/1999/html">
    <#if getterUtil.getBoolean(theme_settings['white-top-header']!"", false) == true>
    	<nav class="navbar navbar-static-top  white-bg" role="navigation" style="margin-bottom: 0;  z-index: 111;">
	<#else>
        <nav class="navbar navbar-static-top  " role="navigation" style="margin-bottom: 0;  z-index: 111;">
	</#if>

            <div class="navbar-header navbar-search w-50">
                <div class="row">
                    <div class="col-1">
                        <a class="navbar-minimalize minimalize-styl-2 btn btn-primary mt-0" id="btnClose" href="#"><i
                                class="fa fa-bars"></i> </a>
                    </div>
                    <div class="col-11">
                        <h1>Mi registro de horas</h1>
                        <div>
                            <@liferay_portlet["runtime"]
                                portletProviderAction=portletProviderAction.VIEW
                                portletProviderClassName="com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry"/>
                        </div>
                    </div>
                </div>

                <#--<div class="navbar-form-custom" role="search"> <@liferay.search /></div>-->
            </div>
            <div class="header-right right w-50">
                <div class="row text-right">
                    <div class="col-9">
                        <#if themeDisplay.scopeGroupName != "Liferay">
                            <div style="padding-top: 5%;"><h1>${themeDisplay.scopeGroupName}</h1></div>
                        </#if>
                    </div>
                    <div class="col-3">
                        <img alt="Preving" src="${themeDisplay.scopeGroup.getLogoURL(themeDisplay, true)}"
                             style='max-width: 100%;max-height: 80px; float: right;'/>
                    </div>
                </div>

<#--                    <li>
                        <span class="m-r-sm text-muted welcome-message">Welcome to ${company_name}.</span>
                    </li>
                    <#if !is_signed_in>
                        <#if getterUtil.getString(theme_settings['login-page']!"", "") == "">
                            <li><a data-redirect="false" href="${sign_in_url}" rel="nofollow"
                                   id="sign-in">${sign_in_text}</a></li>
                        <#else>
                            <li><a data-redirect="true"
                                   href="${getterUtil.getString(theme_settings['login-page']!"", "")}" rel="nofollow"
                                   id="sign-in">${sign_in_text}</a></li>
                        </#if>
                    </#if>

                    <#if getterUtil.getBoolean(theme_settings['display-right-sidebar-toggle']!"", false) == true>
                        <li>
                            <a class="right-sidebar-toggle">
                                <i class="fa fa-tasks"></i>
                            </a>
                        </li>
                    </#if>


                <#if getterUtil.getBoolean(theme_settings['show-language-choice']!"", false) ==true>
                    <div class="clearfix"></div>
                    <div id="Language-choices">
                        <#if taglibLiferay??>
                            ${taglibLiferay.language("fm",null,"_82_languageId",0)}
                        </#if>
                    </div>
                </#if>-->

            </div>
        </nav>
</div>

<#if getterUtil.getBoolean(theme_settings['show-breadcrumbs']!"", false) == true>
    <div class="row wrapper border-bottom white-bg page-heading row-breadcrumbs" style="display: block !important;">
        <div class="col-lg-10">
            <@liferay.breadcrumbs />
        </div>
    </div>
</#if>
