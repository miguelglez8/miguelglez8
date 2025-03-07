<div class="row border-bottom" xmlns:c="http://www.w3.org/1999/html">
        <nav class="navbar navbar-static-top  " role="navigation" style="margin-bottom: 0;  z-index: 111;">
	

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

            </div>
        </nav>
</div>
