<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master-layout 
    titlePage="${title}"
    descriptionPage="Sale more than gadget"
    keywordPage="computer, notebook, smartphone, tablet"
    contextPath="${pageContext.request.contextPath}"
    >
    <jsp:body>
        <h1>Register :</h1>
        <form action="register" method="post">

            อีเมล์ : <input type="email" name="email" required/>
            รหัสผ่าน : <input type="password" name="password" required/>
            ชื่อ : <input type="text" name="firstName" required/>
            นามสกุล : <input type="text"  name="lastName" required/>
            เบอร์โทรศัพท์ : <input type="phone"  name="mobile" required/>
            <input type="submit" value="สมัครสมาชิก"/>

        </form>
    </jsp:body>
</t:master-layout>
