package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.Path;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.service.base.AuthenticationService;
import com.moommim.moommim_web.service.base.ExampleService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends BaseController {

    @Inject
    private AuthenticationService authenticationService;

    @Override
    public void init() throws ServletException {
        authenticationService.setJpaController(emf, utx);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sendToPage(Path.INDEX_VIEW, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserAccount userAccount = authenticationService.login(email, password);

        if (userAccount != null) {
            request.getSession().setAttribute("userAccount", userAccount);
            
//        sendToPage(Path.INDEX_VIEW, request, response);

        }
//        sendToPage(Path.INDEX_VIEW, request, response);

    }

}
