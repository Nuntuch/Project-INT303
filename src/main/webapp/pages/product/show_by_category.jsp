<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master-layout 
    titlePage="${title}"
    descriptionPage="Sale more than gadget"
    keywordPage="computer, notebook, smartphone, tablet">
    <jsp:attribute name="header">

    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>
        <c:forEach items="${productStockListByCategory}" var="item">
            <li>${item.name} | ${item.categoryId.name} => ${item.isShow}</li>
        </c:forEach>
    </jsp:body>
</t:master-layout>
