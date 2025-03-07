<#if entries?has_content>
    <#list entries as curEntry>
        <div class="video-help">
            <#assign
            assetRenderer = curEntry.getAssetRenderer()
            journalArticle = assetRenderer.getAssetObject()
            />
            <@liferay_journal["journal-article"]
            articleId=journalArticle.getArticleId()
            ddmTemplateKey=journalArticle.getDDMTemplateKey()
            groupId=journalArticle.getGroupId()
            />
        </div>
    </#list>
</#if>