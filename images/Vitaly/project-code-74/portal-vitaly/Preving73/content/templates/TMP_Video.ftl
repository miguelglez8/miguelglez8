<#assign userGroupRoleLocalServiceUtil = serviceLocator.findService("com.liferay.portal.kernel.service.UserGroupRoleLocalService")>

<#if themeDisplay.isSignedIn()>

    <#assign isSiteAdmin = userGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), themeDisplay.getScopeGroupId(), "Preving Company Site Admin") />

    <#if  (isSiteAdmin && getterUtil.getBoolean(MostrarAlAdministrador.getData()))
    || (!isSiteAdmin && getterUtil.getBoolean(MostrarAlUsuario.getData())) >
        <div class="row">
            <div class="col m-3">
                <div class="text-right">${IframeVideo.getData()}</div>
            </div>
            <div class="col m-3 float-left">
                <h2>${TituloVideo.getData()}</h2>
                <div>${DescripcionDelVideo.getData()}</div>
            </div>
        </div>
    </#if>
</#if>