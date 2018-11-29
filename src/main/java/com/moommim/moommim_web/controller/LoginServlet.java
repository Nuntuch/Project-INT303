package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.Key;
import com.moommim.moommim_web.config.ServletPath;
import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.service.base.AuthenticationService;
import com.moommim.moommim_web.service.base.ProductService;
import com.moommim.moommim_web.service.base.UserService;
import com.moommim.moommim_web.util.Util;
import java.io.IOException;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/" + ServletPath.LOGIN_SERVLET})
public class LoginServlet extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());

    @Inject
    private AuthenticationService authenticationService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sendToPage(ViewPath.LOGIN_VIEW, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (Util.isNotEmpty(username)
                && Util.isNotEmpty(password)) {
            UserAccount user = authenticationService.login(username, password);
            if (Util.isNotEmpty(user)) {
                request.getSession(true).setAttribute(Key.USER_ACCOUNT_KEY, user);
                sendRedirectToPage(ServletPath.HOME_SERVLET, response);
                return;
            } else {
                request.setAttribute("loginStatus", "ชื่อผู้ใช้หรือรหัสผ่านผิด");
            }
        }
        sendToPage(ViewPath.LOGIN_VIEW, request, response);
    }

}
