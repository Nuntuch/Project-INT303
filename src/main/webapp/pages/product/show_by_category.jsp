<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <div class="row">
            <div class="ui four stackable cards">
                <c:forEach items="${productStockListByCategory}" var="item">
                    <a class="ui card link" href="product?id=${item.id}">
                        <div class="image">
                            <img src="https://via.placeholder.com/300" />
                        </div>
                        <div class="content">
                            <div class="header">${item.name}</div>
                            <div class="description">
                                <h3 class="orange"><fmt:formatNumber  type="currency" currencySymbol="฿" value="${item.price}"/></h3>
                            </div> 
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </jsp:body>
</t:master-layout>
