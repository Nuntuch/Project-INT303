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
        showProductWithCategory(request, response);
    }

    private void showProductWithCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String categoryIdParam = request.getParameter("id");
        if (Util.isNotEmpty(categoryIdParam)) {
            int categoryId = Integer.parseInt(categoryIdParam);
            List<ProductStock> productStockListByCategory = productService.getAllProductByCategoryId(categoryId, CommonConstant.SHOW);
            if (Util.isNotEmpty(productStockListByCategory)) {
                request.setAttribute("categoryName", productStockListByCategory.get(0).getCategoryId().getName());
                request.setAttribute("productStockListByCategory", productStockListByCategory);
            } else {
                request.setAttribute("status", "ไม่พบสินค้า");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        sendToPage(ViewPath.PRODUCT_BY_CATEGORY_VIEW, request, response);
    }

}
