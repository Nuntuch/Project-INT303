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
                <div class="active section">สั่งซื้อเสร็จสิ้น</div>
            </div>
        </div>

        <div class="ui three top attached steps">
            <a class="disabled step">
                <i class="shopping cart icon"></i>
                <div class="content">
                    <div class="title">ตระกร้าสินค้า</div>
                </div>
            </a>
            <div class="disabled step">
                <i class="payment icon"></i>
                <div class="content">
                    <div class="title">ชำระเงิน</div>
                </div>
            </div>
            <a class="active step">
                <i class="info icon"></i>
                <div class="content">
                    <div class="title">สั่งซื้อเสร็จสิ้น</div>
                </div>
            </a>
        </div>        
        <div class="ui placeholder segment">
            <div class="ui icon header">
                <i class="handshake outline icon"></i>
                ขอบคุณที่สั่งซื้อสินค้ากับเรา
            </div>
            <a href="${pageContext.request.contextPath}" class="ui primary button">ซื้อสินค้าต่อ</a>
        </div>
    </jsp:body>
</t:master-layout>
