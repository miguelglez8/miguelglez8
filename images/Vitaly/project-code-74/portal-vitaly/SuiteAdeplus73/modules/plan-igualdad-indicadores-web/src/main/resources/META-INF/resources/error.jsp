<%@ include file="/init.jsp" %>

<c:choose>
    <c:when test="<%= hasRole %>">
        <p><liferay-ui:message key="indicadores.error"/></p>
    </c:when>
    <c:otherwise>
        <p><liferay-ui:message key="indicadores.error.cliente"/></p>
    </c:otherwise>
</c:choose>