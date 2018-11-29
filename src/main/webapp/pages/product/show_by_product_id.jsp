<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master-layout 
    titlePage="${title}"
    descriptionPage="Sale more than gadget"
    keywordPage="computer, notebook, smartphone, tablet"
    contextPath="${pageContext.request.contextPath}">
    <jsp:body>

        <c:choose>
            <c:when test="${not empty status}">
                <div class="ui placeholder segment">
                    <div class="ui icon header">
                        <i class="frown outline icon"></i>
                        ${status}
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="ui basic segment">
                    <div class="ui breadcrumb">
                        <a class="section" href="${pageContext.request.contextPath}">หน้าแรก</a>
                        <i class="right angle icon divider"></i>
                        <c:if test="${not empty categoryParent}">
                            <div class="section">${categoryParent.name}</div>
                            <i class="right angle icon divider"></i>
                        </c:if>
                        <a href="category?id=${category.id}" class="section">${category.name}</a>
                        <i class="right angle icon divider"></i>
                        <div class="active section">${title}</div>
                    </div>
                </div>

                <div class="ui segment">
                    <div class="ui equal width grid">
                        <div class="column">
                            <img class="ui fluid image" src="${productById.featuredImage}" />
                        </div>
                        <div class="column">
                            <h2 class="ui header">${productById.name}</h2>
                            <h1><fmt:formatNumber  type="currency" currencySymbol="฿" value="${productById.price}"/></h1>
                            <div class="ui vertical segment">
                                <h4 class="ui header">มีสินค้าทั้งหมด ${productById.amountInStock} ชิ้น</h4>
                            </div>
                            <div class="ui vertical segment">
                                <div class="ui equal width grid">
                                    <div class="column">
                                        <c:if test="${productById.amountInStock  ne 0}">
                                            <a class="ui fluid big green button" href="cart?action=add&id=${productById.id}"><i class="cart plus icon"></i> เพิ่มลงตระกร้าสินค้า</a>
                                        </c:if> 
                                    </div>
                                    <div class="column">
                                        <a class="ui fluid big yellow button" href="fav?action=add&id=${productById.id}"><i class="heart icon"></i> เพิ่มลงรายการโปรด</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="ui top attached tabular menu">
                        <div class="active item">รายระเอียดสินค้า</div>
                    </div>
                    <div class="ui bottom attached active tab segment">
                        ${productById.detail}
                    </div>
                </div>   
            </c:otherwise>
        </c:choose>

    </jsp:body>
</t:master-layout>
