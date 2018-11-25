package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.CommonConstant;
import com.moommim.moommim_web.config.ProductStatusConstant;
import com.moommim.moommim_web.config.ServletPath;
import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.model.ProductStock;
import com.moommim.moommim_web.service.base.ProductService;
import com.moommim.moommim_web.util.Util;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "ProductCategoryServlet", urlPatterns = {"/" + ServletPath.PRODUCT_BY_CATEGORY_SERVLET})
public class ProductCategoryServlet extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(ProductCategoryServlet.class.getName());

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ProductStock> productStockListByCategory = productService.getAllProductByCategoryId(2, ProductStatusConstant.STATUS_IN_STOCK, CommonConstant.SHOW);
        if (Util.isNotEmpty(productStockListByCategory)) {
            request.setAttribute("productStockListByCategory", productStockListByCategory);
        }
        sendToPage(ViewPath.PRODUCT_BY_CATEGORY_VIEW, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
