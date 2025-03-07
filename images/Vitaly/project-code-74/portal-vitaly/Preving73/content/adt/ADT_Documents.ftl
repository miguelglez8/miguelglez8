<#if entries?has_content>
    <#list entries as curEntry>
        <div class="documents">
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
<style>
    .document-image{
        max-width: 100%;
        max-height: 80px;
        float: right;
    }
</style>