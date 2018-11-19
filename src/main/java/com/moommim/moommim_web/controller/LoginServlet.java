package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.App;
import com.moommim.moommim_web.config.ServletPath;
import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.service.AuthenticationServiceImpl;
import com.moommim.moommim_web.service.base.AuthenticationService;
import com.moommim.moommim_web.service.base.ExampleService;
import com.moommim.moommim_web.util.Util;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends BaseController {

    @Inject
    private AuthenticationServiceImpl authenticationService;

    @Override
    public void init() throws ServletException {
        authenticationService.setJpa(emf, utx);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        sendToPage(ViewPath.LOGIN_VIEW, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserAccount userAccount = authenticationService.login(email, password);

        if (Util.isNotEmpty(userAccount)) {
            System.out.println("User is not null");
            request.getSession().setAttribute("userAccount", userAccount);
            sendRedirectToPage(ServletPath.HOME_SERVLET, response);
            return;
        }
        sendToPage(ViewPath.LOGIN_VIEW, request, response);

    }

}
