<#if entries?has_content>
    <div class="mx-5">
        <#list entries as curLanguage>
            <div class="p-2">
                <input type="radio" id="${curLanguage.languageId}" name="language" value="${curLanguage.languageId}" ${curLanguage.selected?then('checked', '')} />
                <label for="${curLanguage.languageId }">
                    <#if curLanguage.selected>
                        ${curLanguage.longDisplayName?capitalize}
                    <#else>
                        <a href="/c/portal/update_language?p_l_id=${themeDisplay.getPlid()}&amp;redirect=/group${themeDisplay.getScopeGroup().getFriendlyURL()}${themeDisplay.getLayout().getFriendlyURL()}&amp;languageId=${curLanguage.languageId}" lang="${curLanguage.languageId}">${curLanguage.longDisplayName?capitalize}</a>
                    </#if>
                </label>
            </div>
        </#list>
    </div>
</#if>