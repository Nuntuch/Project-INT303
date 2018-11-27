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
            <c:when test="${not empty is_have_token}" >
                
                <form action="forgot" method="post">
                    
                    New Password : <input type="password" name="password" required />
                    
                    <input type="submit" value="Save A New Password" />

                </form>

            </c:when>
            <c:otherwise>
                <form action="forgot" method="post">
                    
                    Email : <input type="email" name="email" required />
                    
                    <input type="submit" value="Forgot Password" />

                </form>

            </c:otherwise>
        </c:choose>

    </jsp:body>
</t:master-layout>
