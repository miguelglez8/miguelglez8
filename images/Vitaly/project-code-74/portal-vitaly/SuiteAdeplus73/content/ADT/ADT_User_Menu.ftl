<#if themeDisplay.isSignedIn()>
    <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            ${themeDisplay.getUser().getFullName()}
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <#if entries?has_content>
                <#list entries as navigationEntry>
                    <a class="dropdown-item" href="${navigationEntry.getURL()}">${navigationEntry.getName()}</a>
                </#list>
            </#if>
        </div>
    </div>
</#if>