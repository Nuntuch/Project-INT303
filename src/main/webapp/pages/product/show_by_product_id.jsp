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
                        <i class="frown outline icon"></i>
                        ${status}
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="ui basic segment">
                    <div class="ui breadcrumb">
                        <a class="section" href="${request.contextPath}">หน้าแรก</a>
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

                <h2 class="ui center aligned segment header">
                    <div class="content ">
                        ${productById.name}
                    </div>
                </h2>

                <div class="ui basic segment">
                    ${productById.name} |  ${productById.price}
                </div>
            </c:otherwise>
        </c:choose>

    </jsp:body>
</t:master-layout>
