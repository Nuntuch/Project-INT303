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
                        <div class="active section">ประวัติสั่งซื้อ</div>
                    </div>
                </div>

                <div class="ui basic segment">
                    <div class="ui four stackable cards">
                        <table class="ui unstackable table center aligned">
                            <thead>
                                <tr>
                                    <th class="collapsing">#</th>
                                    <th>รหัสบิล</th>
                                    <th>วันที่</th>
                                    <th>ยอดชำระ</th>
                                    <th>ที่อยู่ในการจัดส่ง</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${billList}" var="item" varStatus="itemNo">
                                    <tr>
                                        <td>${itemNo.count}</td>
                                        <td>${item.id}</td>
                                        <td><fmt:formatDate type="both" value="${item.createAt}" />  </td>
                                        <td><fmt:formatNumber currencySymbol="฿" type="currency" value="${item.totalPrice}" /></td>
                                        <td>${item.address}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

    </jsp:body>
</t:master-layout>
