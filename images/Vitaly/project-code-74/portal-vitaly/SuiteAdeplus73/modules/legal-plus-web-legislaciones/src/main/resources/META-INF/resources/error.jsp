<%@ include file="/init.jsp" %>

<%@ page import="com.liferay.journal.service.JournalArticleLocalServiceUtil" %>
<%@ page import="com.liferay.journal.model.JournalArticle" %>

<%

Long groupId = themeDisplay.getLayout().getGroupId();
JournalArticle article = JournalArticleLocalServiceUtil.getArticleByUrlTitle(groupId, "idioma");

%>

<liferay-journal:journal-article articleId="<%= article.getArticleId() %>" groupId="<%= groupId  %>" />