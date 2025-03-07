<#if entries?has_content>
    <#list entries as navigationEntry>
        <a href="${navigationEntry.getURL()}">
            <#if navigationEntry.iconURL() != "" >
                <img height="35px" src="${navigationEntry.iconURL()}" />
            <#else>
                ${navigationEntry.getName()}
            </#if>
        </a>
    </#list>
</#if>