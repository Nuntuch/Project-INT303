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
        
        <c:choose>
            <c:when test="${not empty status}">
                <div class="ui placeholder segment">
                    <div class="ui icon header">
                        <i class="shopping cart icon"></i>
                        ${status}
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="ui basic segment">
                    <div class="ui breadcrumb">
                        <a class="section" href="${request.contextPath}">หน้าแรก</a>
                        <i class="right angle icon divider"></i>
                        <div class="active section">${categoryName}</div>
                    </div>
                </div>

                <h2 class="ui center aligned segment header">
                    <div class="content ">
                        ${categoryName}
                    </div>
                </h2>

                <div class="ui four stackable cards">
                    <c:forEach items="${productStockListByCategory}" var="item">
                        <a class="ui card link" href="product?id=${item.id}">
                            <div class="image">
                                <img src="https://via.placeholder.com/300" />
                            </div>
                            <div class="content">
                                <div class="header">${item.name}</div>
                                <div class="description">
                                    <h3 class="ui header orange"><fmt:formatNumber  type="currency" currencySymbol="฿" value="${item.price}"/></h3>
                                </div> 
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
        
    </jsp:body>
</t:master-layout>
