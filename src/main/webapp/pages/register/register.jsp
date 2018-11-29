<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master-layout 
    titlePage="${title}"
    descriptionPage="Sale more than gadget"
    keywordPage="computer, notebook, smartphone, tablet"
    contextPath="${pageContext.request.contextPath}"
    >
    <jsp:body>
        <div class="ui basic segment">
            <div class="ui breadcrumb">
                <a class="section" href="${pageContext.request.contextPath}">หน้าแรก</a>
                <i class="right angle icon divider"></i>
                <div class="active section">สมัครสมาชิก</div>
            </div>
        </div>

        <div class="ui placeholder segment">
            <div class="ui two column very relaxed stackable grid">
                <div class="column">
                    <h4 class="ui dividing header">สมัครสมาชิก</h4>
                    <form action="register" method="post">
                        <c:if test="${not empty emailStatus}">
                            <div class="ui error message">
                                <i class="close icon"></i>
                                <div class="header">
                                    ${emailStatus}
                                </div>
                            </div>
                        </c:if>
                        <div class="ui form">
                            <div class="eight wide field">
                                <label>อีเมล์</label>
                                <input type="email" name="email" required/>
                            </div>
                            <div class="eight wide field">
                                <label>รหัสผ่าน</label>
                                <input type="password" name="password" required/>
                            </div>
                            <div class="eight wide field">
                                <label>ชื่อ</label>
                                <input type="text" name="firstName" required/>
                            </div>
                            <div class="eight wide field">
                                <label>นามสกุล</label>
                                <input type="text"  name="lastName" required/>
                            </div>
                            <div class="eight wide field">
                                <label>เบอร์โทรศัพท์</label>
                                <input type="tel"  name="mobile" required/>
                            </div>
                            <button class="ui large orange button" type="submit">สมัครสมาชิก</button>
                        </div>
                    </form>
                </div>
                <div class="middle aligned column">
                    <a class="ui big button" href="login">เข้าสู่ระบบ</a>
                </div>
            </div>
            <div class="ui vertical divider">
                หรือ
            </div>
        </div>        
    </jsp:body>
</t:master-layout>
