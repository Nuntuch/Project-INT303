<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master-layout 
    titlePage="${title}"
    descriptionPage="Sale more than gadget"
    keywordPage="computer, notebook, smartphone, tablet"
    contextPath="${pageContext.request.contextPath}"
    >
    <jsp:attribute name="cssInternal">
        body > .grid {
        height: 100%;
        }
        .image {
        margin-top: -100px;
        }
        .column {
        max-width: 450px;
        }
    </jsp:attribute>

    <jsp:body>

        <div class="ui small aligned center aligned grid">
            <div class="column">
                <h2 class="ui image header">
                    <div class="content">
                        เข้าสู่ระบบ
                    </div>
                </h2>
                <form class="ui large form" action="login" method="post">
                    <c:if test="${not empty loginStatus}">
                        <div class="ui error message">
                            <i class="close icon"></i>
                            <div class="header">
                                ${loginStatus}
                            </div>
                        </div>
                    </c:if>
                    <div class="ui stacked segment">
                        <div class="field">
                            <div class="ui left icon input">
                                <i class="user icon"></i>
                                <input type="email" name="email" placeholder="อีเมล์" required>
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui left icon input">
                                <i class="lock icon"></i>
                                <input type="password" name="password" placeholder="รหัสผ่าน" required>
                            </div>
                        </div>
                        <button class="ui fluid large orange submit button">เข้าสู่ระบบ</button>
                    </div>

                </form>

                <div class="ui message">
                    ยังไม่มีบัญชี? <a href="register">สมัครสมาชิก</a>
                </div>
            </div>
        </div>

    </jsp:body>
</t:master-layout>
