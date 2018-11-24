package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.model.MailMessage;
import com.moommim.moommim_web.service.base.MailService;
import java.io.IOException;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ExampleServlet", urlPatterns = {"/example"})
public class ExampleServlet extends BaseController {
    
    private static final Logger LOGGER = Logger.getLogger(ExampleServlet.class.getName());;

    @Inject
    private MailService mailService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        if (mailService.sendMail("nainatjab999@gmail.com", new MailMessage("Hello Form Moommim :D", "<h1>Send Mail Noti</h1>"))) {
//            LOGGER.fine("Send mail is success");
//        }

        sendToPage(ViewPath.INDEX_VIEW, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
