package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.ServletPath;
import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.model.ProductStock;
import com.moommim.moommim_web.service.base.ProductService;
import com.moommim.moommim_web.util.Util;
import java.io.IOException;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductServlet", urlPatterns = {"/" + ServletPath.PRODUCT_SERVLET})
public class ProductServlet extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(ProductServlet.class.getName());

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productIdParam = request.getParameter("id");
        if (Util.isNotEmpty(productIdParam)) {
            int productId = Integer.parseInt(productIdParam);
            ProductStock productById = productService.getProductById(productId);
            if (Util.isNotEmpty(productById)) {
                request.setAttribute("productById", productById);
                request.setAttribute("title", productById.getName());
                request.setAttribute("category", productById.getCategoryId());
            } else {
                request.setAttribute("status", "ไม่พบสินค้า");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        sendToPage(ViewPath.PRODUCT_BY_ID_VIEW, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
