package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.ServletPath;
import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.model.ProductCategory;
import com.moommim.moommim_web.model.ProductStock;
import com.moommim.moommim_web.service.base.ProductCategoryService;
import com.moommim.moommim_web.service.base.ProductService;
import com.moommim.moommim_web.util.Util;
import java.io.IOException;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CartServlet", urlPatterns = {"/" + ServletPath.CART_SERVLET})
public class CartServlet extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(CartServlet.class.getName());

    @Inject
    private ProductService productService;

    @Inject
    private ProductCategoryService productCategoryService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionParam = request.getParameter("action");
        String productIdParam = request.getParameter("id");
        if (Util.isNotEmpty(actionParam)) {
            if (Util.isNotEmpty(productIdParam)) {
                switch (actionParam) {
                    case "add":
                        System.out.println("Add : " + productIdParam);
                        break;
                    case "remove":
                        System.out.println("Remove : " + productIdParam);
                        break;
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        } else {
            request.setAttribute("status", "ไม่มีสินค้าในตระกร้า");
        }
        sendToPage(ViewPath.SHOW_CART_VIEW, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
