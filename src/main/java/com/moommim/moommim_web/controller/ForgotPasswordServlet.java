package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.ServletPath;
import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.service.AuthenticationServiceImpl;
import com.moommim.moommim_web.util.Util;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forgotpassword"})
public class ForgotPasswordServlet extends BaseController {
    
    @Inject
    private AuthenticationServiceImpl authenticationService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        sendToPage(ViewPath.FORGOTPASSWORD_VIEW, request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        
        if (Util.isNotEmpty(email)) {
            
            authenticationService.forgotPassword(email);
            
            sendToPage(ServletPath.LOGIN_SERVLET, request, response);
            return;
        }
        sendToPage(ViewPath.FORGOTPASSWORD_VIEW, request, response);
    }
}
