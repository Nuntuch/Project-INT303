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
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SearchServlet", urlPatterns = {"/" + ServletPath.SEARCH_SERVLET})
public class SearchServlet extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(SearchServlet.class.getName());

    @Inject
    private ProductService productService;

    @Inject
    private ProductCategoryService productCategoryService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keywordParam = request.getParameter("keyword");
        if (Util.isNotEmpty(keywordParam)) {
            List<ProductStock> productStockListBySearch = productService.getProductByName(keywordParam.toLowerCase());
            request.setAttribute("productStockListBySearch", productStockListBySearch);
            request.setAttribute("title", keywordParam + " - ");
            request.setAttribute("keyword", keywordParam);
            if (Util.isNotEmpty(productStockListBySearch)) {
                request.setAttribute("totalItem", productStockListBySearch.size());
            } else {
                request.setAttribute("totalItem", 0);
            }
            sendToPage(ViewPath.SEARCH_VIEW, request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
