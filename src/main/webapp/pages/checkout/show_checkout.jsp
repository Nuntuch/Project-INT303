<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master-layout 
    titlePage="${title}"
    descriptionPage="Sale more than gadget"
    keywordPage="computer, notebook, smartphone, tablet">
    <jsp:body>
        <div class="ui basic segment">
            <div class="ui breadcrumb">
                <a class="section" href="${request.contextPath}">หน้าแรก</a>
                <i class="right angle icon divider"></i>
                <div class="active section">ชำระเงิน</div>
            </div>
        </div>

        <div class="ui three top attached steps">
            <a href="cart" class="step">
                <i class="shopping cart icon"></i>
                <div class="content">
                    <div class="title">ตระกร้าสินค้า</div>
                </div>
            </a>
            <div class="active step">
                <i class="payment icon"></i>
                <div class="content">
                    <div class="title">ชำระเงิน</div>
                </div>
            </div>
            <a class="disabled step">
                <i class="info icon"></i>
                <div class="content">
                    <div class="title">สั่งซื้อเสร็จสิ้น</div>
                </div>
            </a>
        </div>        
        <div class="ui attached segment">
            <form action="checkout" method="post">
                <div class="ui two column centered grid">
                    <div class="seven wide column">
                        <div class="ui segment">
                            <div class="ui form">
                                <h4 class="ui dividing header">ที่อยู่จัดส่ง</h4>

                            </div>
                        </div>
                        <div class="ui segment">
                            <div class="ui form">
                                <h4 class="ui dividing header">บัตรเครดิต</h4>
                                <div class="fields">
                                    <div class="ten wide field">
                                        <input type="text" name="cardOwner" placeholder="ชื่อผู้ถือบัตร" required>
                                    </div>
                                    <div class="three wide field">
                                        <input type="number" name="cardExpireMonth" maxlength="4" placeholder="MM" required>
                                    </div>
                                    <div class="three wide field">
                                        <input type="number" name="cardExpireYear" maxlength="4" placeholder="YY" required>
                                    </div>
                                </div>

                                <div class="fields">
                                    <div class="twelve wide field">
                                        <input type="number" name="cardNumber" maxlength="16" placeholder="เลขบัตร" required>
                                    </div>
                                    <div class="four wide field">
                                        <input type="number" name="cardCVC" maxlength="3" placeholder="CVC" required>
                                    </div>
                                </div>

                                <button type="submit" class="ui orange fluid large button">
                                    <i class="credit card icon"></i>
                                    ยืนยันการชำระเงิน
                                </button>

                            </div>
                        </div>
                    </div>
                    <div class="nine wide column">
                        <div class="ui segment">
                            <h4 class="ui dividing header">สรุปคำสั่งซื้อ</h4>
                            <ol>
                                <c:forEach items="${sessionScope.cart.cartItemList}" var="item" varStatus="itemNo">
                                    <li>${item.product.name} -
                                        <fmt:formatNumber currencySymbol="฿" type="currency" value="${item.product.price}" /> x ${item.quantity} : <b class="ui tiny header"><fmt:formatNumber currencySymbol="฿" type="currency" value="${item.totalPrice}" /></b>
                                    </li>
                                </c:forEach>
                            </ol>
                            <h3 class="ui header">รวมทั้งหมด: <span class="ui orange header"><fmt:formatNumber currencySymbol="฿" type="currency" value="${sessionScope.cart.totalPrice}" /></span></h3>
                        </div>
                    </div>
                </div>
            </form>
        </jsp:body>
    </t:master-layout>
