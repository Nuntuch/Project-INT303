package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.ServletPath;
import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.service.base.ProductService;
import com.moommim.moommim_web.util.Util;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "CheckoutServlet", urlPatterns = {"/" + ServletPath.CHECKOUT_SERVLET})
public class CheckoutServlet extends BaseController {
    
    private static final Logger LOGGER = Logger.getLogger(CheckoutServlet.class.getName());
    
    @Inject
    private ProductService productService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Hello");
        request.setAttribute("title", "Checkout");
        sendToPage(ViewPath.CHECKOUT_VIEW, request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("title", "Checkout");
    }
    
}
