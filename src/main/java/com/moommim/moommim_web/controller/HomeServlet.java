package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.ServletPath;
import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.service.base.ProductService;
import java.io.IOException;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HomeServlet", urlPatterns = {"/" + ServletPath.HOME_SERVLET})
public class HomeServlet extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(HomeServlet.class.getName());

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sendToPage(ViewPath.INDEX_VIEW, request, response);
    }

}