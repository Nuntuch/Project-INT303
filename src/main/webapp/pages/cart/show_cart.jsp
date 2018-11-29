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
        <div class="ui basic segment">
            <div class="ui breadcrumb">
                <a class="section" href="${pageContext.request.contextPath}">หน้าแรก</a>
                <i class="right angle icon divider"></i>
                <div class="active section">ตระกร้าสินค้า</div>
            </div>
        </div>

        <div class="ui three top attached steps">
            <div class="active step">
                <i class="shopping cart icon"></i>
                <div class="content">
                    <div class="title">ตระกร้าสินค้า</div>
                </div>
            </div>
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
                    <table class="ui unstackable table center aligned">
                        <thead>
                            <tr>
                                <th class="collapsing"></th>
                                <th class="collapsing">#</th>
                                <th>รายการ</th>
                                <th>ราคา</th>
                                <th>จำนวน</th>
                                <th>รวม</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.cart.cartItemList}" var="item" varStatus="itemNo">
                                <tr>
                                    <td>
                                        <a href="cart?action=clear&id=${item.product.id}&redirect=false" class="ui mini icon button">
                                            <i class="times circle icon"></i>
                                        </a>
                                    </td>
                                    <td>${itemNo.count}</td>
                                    <td class="left aligned">${item.product.name}</td>
                                    <td><fmt:formatNumber currencySymbol="฿" type="currency" value="${item.product.price}" /></td>
                                    <td>
                                        <a href="cart?action=remove&id=${item.product.id}&redirect=false" class="ui mini icon button">
                                            <i class="minus icon"></i>
                                        </a>
                                        ${item.quantity}
                                        <a href="cart?action=add&id=${item.product.id}&redirect=false" class="ui mini icon button">
                                            <i class="plus icon"></i>
                                        </a>
                                    </td>
                                    <td>
                                        <fmt:formatNumber currencySymbol="฿" type="currency" value="${item.totalPrice}" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th colspan="4">รวมทั้งหมด</th>
                                <th>${sessionScope.cart.totalQuantity}</th>
                                <th>
                                    <h4>
                                        <fmt:formatNumber currencySymbol="฿" type="currency" value="${sessionScope.cart.totalPrice}" />
                                    </h4>
                                </th>
                            </tr>
                        </tfoot>
                    </table>

                    <div class="ui equal width grid">
                        <div class="column">
                            <a href="cart?action=clear-all" class="ui red large right aligned button">
                                <i class="times circle icon"></i>
                                ล้างตระกร้า
                            </a>
                        </div>
                        <div class="column">
                            <a href="checkout" class="ui orange large right floated button">
                                <i class="credit card icon"></i>
                                ชำระเงิน
                            </a>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</div>
</jsp:body>
</t:master-layout>
