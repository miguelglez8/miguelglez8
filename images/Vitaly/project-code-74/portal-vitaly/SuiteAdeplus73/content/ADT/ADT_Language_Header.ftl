<#if entries?has_content>
    <div>
        <select onchange="selectLanguage.call(this, event)">
            <#list entries as curLanguage>
                <div class="d-inline ">
                    <#if curLanguage.selected>
                        <option selected>${curLanguage.shortDisplayName?upper_case}</option>
                    <#else>
                        <option value="${curLanguage.languageId}">
                            ${curLanguage.shortDisplayName?upper_case}
                        </option>
                    </#if>
                </div>
            </#list>
        </select>
    </div>
</#if>

<script>
    function selectLanguage(event) {
        var languageUrl =  "/c/portal/update_language?p_l_id=${themeDisplay.getPlid()}&redirect=/group${themeDisplay.getScopeGroup().getFriendlyURL()}${themeDisplay.getLayout().getFriendlyURL()}&languageId="+this.options[this.selectedIndex].value;
        window.location.href = languageUrl;
    }
</script>