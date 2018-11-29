package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.Key;
import com.moommim.moommim_web.config.ServletPath;
import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.service.base.UserService;
import com.moommim.moommim_web.util.Util;
import java.io.IOException;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/" + ServletPath.REGISTER_SERVLET})
public class RegisterServlet extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class.getName());

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sendToPage(ViewPath.REGISTER_VIEW, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String mobile = request.getParameter("mobile");

        if (Util.isNotEmpty(email)) {
            if (Util.isNotEmpty(userService.getUserByEmail(email))) {
                request.setAttribute("emailStatus", "อีเมล์นี้มีผู้ใช้งานแล้ว");
            }
            if (Util.isNotEmpty(password)
                    && Util.isNotEmpty(firstName)
                    && Util.isNotEmpty(lastName)
                    && Util.isNotEmpty(mobile)) {
                UserAccount newUser = new UserAccount(email, password, firstName, lastName, mobile);
                if (userService.create(newUser)) {
                    request.getSession(true).setAttribute(Key.USER_ACCOUNT_KEY, newUser);
                    sendRedirectToPage(ServletPath.HOME_SERVLET, response);
                    return;
                }
            }
        }
        sendToPage(ViewPath.REGISTER_VIEW, request, response);
    }

}
