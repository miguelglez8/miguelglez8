<#assign userGroupRoleLocalServiceUtil = serviceLocator.findService("com.liferay.portal.kernel.service.UserGroupRoleLocalService")>

<#if themeDisplay.isSignedIn()>
    <#assign isSiteAdmin = userGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), themeDisplay.getScopeGroupId(), "Preving Company Site Admin") />

    <#if (isSiteAdmin && getterUtil.getBoolean(MostrarAlAdministrador.getData()))
    || (!isSiteAdmin && getterUtil.getBoolean(MostrarAlUsuario.getData()))  >
        <div class="row">
            <div class="col-sm-1 m-3 ">
                <#if VistaPrevia.getData()?? && VistaPrevia.getData() != "">
                    <img alt="${VistaPrevia.getAttribute("alt")}" data-fileentryid="${VistaPrevia.getAttribute("fileEntryId")}" src="${VistaPrevia.getData()}" class="document-image" />
                </#if>
            </div>
            <div class="col-sm-5 m-3">
                <h2>${TítuloDocumento.getData()}</h2>
                <div>${Descripcion.getData()}</div>
            </div>
            <div class="col-sm-2 m-3">

                <div class="col align-self-center text-center  my-2">
                    <button class="dt-button buttons-csv buttons-html5" type="button" onclick="location.href='${Documento.getData()}'">
                        <span>${languageUtil.format(locale, "download-x", "Documento", false)}</span>
                    </button>
                </div>

            </div>
        </div>
    </#if>
</#if>