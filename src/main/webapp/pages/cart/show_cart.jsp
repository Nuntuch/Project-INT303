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


        <div class="ui basic segment">
            <div class="ui breadcrumb">
                <a class="section" href="${request.contextPath}">หน้าแรก</a>
                <i class="right angle icon divider"></i>
                <div class="active section">ตระกร้าสินค้า</div>
            </div>
        </div>

        <div class="ui three top attached steps">
            <a class="active step" href="cart">
                <i class="shopping cart icon"></i>
                <div class="content">
                    <div class="title">ตระกร้าสินค้า</div>
                </div>
            </a>
            <a class="step ${empty status ? '' : 'disabled'}" href="checkout">
                <i class="payment icon"></i>
                <div class="content">
                    <div class="title">ชำระเงิน</div>
                </div>
            </a>
            <a class="disabled step">
                <i class="info icon"></i>
                <div class="content">
                    <div class="title">สั่งซื้อเสร็จสิ้น</div>
                </div>
            </a>
        </div>        
        <div class="ui attached segment">
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
                    <div class="ui four stackable cards">
                        <c:forEach items="${sessionScope.cart.cartItemList}" var="item">
                            <a class="ui card link" href="product?id=${item.product.id}">
                                <div class="image">
                                    <img src="https://via.placeholder.com/300" />
                                </div>
                                <div class="content">
                                    <div class="header">${item.product.name} | ${item.quantity} | ${item.totalPrice}</div>
                                    <div class="description">
                                        <h3 class="ui header orange"><fmt:formatNumber  type="currency" currencySymbol="฿" value="${item.product.price}"/></h3>
                                    </div> 
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</jsp:body>
</t:master-layout>
