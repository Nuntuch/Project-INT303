<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master-layout 
    titlePage="${title}"
    descriptionPage="Sale more than gadget"
    keywordPage="computer, notebook, smartphone, tablet">
    <jsp:body>

        <form method="post" action="admin-upload" enctype="multipart/form-data">
            Choose a file: <input type="file" name="multiPartServlet" />
            <input type="submit" value="Upload" />
        </form>

    </jsp:body>
</t:master-layout>
