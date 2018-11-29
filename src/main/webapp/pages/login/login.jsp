<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master-layout 
    titlePage="${title}"
    descriptionPage="Sale more than gadget"
    keywordPage="computer, notebook, smartphone, tablet"
    contextPath="${pageContext.request.contextPath}"
    >
    <jsp:body>
        <h1>Login :</h1>
        <form action="login" method="post">

            Email : <input type="email" required name="email" />
            Password : <input type="password" required name="password" />
            <input type="submit" value="Login"/>

        </form>


    </jsp:body>
</t:master-layout>
