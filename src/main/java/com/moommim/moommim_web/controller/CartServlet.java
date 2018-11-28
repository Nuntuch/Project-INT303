package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.Key;
import com.moommim.moommim_web.config.ServletPath;
import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.repository.CartRepository;
import com.moommim.moommim_web.service.base.ProductService;
import com.moommim.moommim_web.util.Util;
import java.io.IOException;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CartServlet", urlPatterns = {"/" + ServletPath.CART_SERVLET})
public class CartServlet extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(CartServlet.class.getName());

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionParam = request.getParameter("action");
        String productIdParam = request.getParameter("id");
        HttpSession session = request.getSession(true);
        CartRepository cart = (CartRepository) session.getAttribute(Key.CART_KEY);
        if (Util.isEmpty(cart)) {
            cart = new CartRepository();
            session.setAttribute(Key.CART_KEY, cart);
        }
        if (Util.isEmpty(cart.getCartItemList())) {
            request.setAttribute("status", "ไม่มีสินค้าในตระกร้า");
        }
        if (Util.isNotEmpty(actionParam)) {
            if (Util.isNotEmpty(productIdParam)) {
                int productId = Integer.parseInt(productIdParam);
                updateCart(actionParam, productId, cart);
                sendRedirectToPage(ServletPath.PRODUCT_SERVLET + "?id=" + productId, response);
                return;
            } else {
                updateCart(actionParam, 0, cart);
                sendRedirectToPage(ServletPath.CART_SERVLET, response);
                return;
            }
        }
        sendToPage(ViewPath.SHOW_CART_VIEW, request, response);
    }

    private void updateCart(String action, int productId, CartRepository cart) {
        switch (action) {
            case "add":
                cart.addProduct(productService.getProductById(productId));
                break;
            case "remove":
                cart.removeProduct(productId);
                break;
            case "clear":
                cart.clearProduct(productId);
                break;
            case "clear-all":
                cart.clearAll();
                break;
        }
    }

}
