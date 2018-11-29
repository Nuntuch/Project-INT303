package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.ServletPath;
import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.service.base.AuthenticationService;
import com.moommim.moommim_web.util.Util;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forgot"})
public class ForgotPasswordServlet extends BaseController {
    
    @Inject
    private AuthenticationService authenticationService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String token = request.getParameter("token");
        if (Util.isNotEmpty(token)) {
            request.setAttribute("is_have_token", token);
        }
        
        sendToPage(ViewPath.FORGOTPASSWORD_VIEW, request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        if (Util.isNotEmpty(email)) {
            processForgotPassword(email, request, response);
        }
        
        String password = request.getParameter("password");
        if(Util.isNotEmpty(password)){
//        ส่งไปหา UpdatePassword ที่ UserService
        }
        sendToPage(ViewPath.FORGOTPASSWORD_VIEW, request, response);
    }
    
    private void processForgotPassword(String email, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!authenticationService.forgotPassword(email)) {
            request.setAttribute("status", "invalid email");
        } else {
            sendRedirectToPage(ServletPath.LOGIN_SERVLET, response);
            return;
        }
        
    }
}
