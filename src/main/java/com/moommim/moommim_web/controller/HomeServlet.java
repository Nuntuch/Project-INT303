package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.ServletPath;
import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.model.ProductStock;
import com.moommim.moommim_web.service.base.ProductService;
import com.moommim.moommim_web.util.Util;
import java.io.IOException;
import java.util.List;
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
        request.setAttribute("title", "MoomMim Sale More Gadget");
        List<ProductStock> productStockList = productService.getAllProduct();
        if (Util.isNotEmpty(productStockList)) {
            request.setAttribute("productStockList", productStockList);
        }
        sendToPage(ViewPath.INDEX_VIEW, request, response);
    }

}
