package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.Key;
import com.moommim.moommim_web.config.ServletPath;
import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.model.Bill;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.service.CartServiceImpl;
import com.moommim.moommim_web.service.base.BillService;
import com.moommim.moommim_web.service.base.ProductService;
import com.moommim.moommim_web.util.Util;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CheckoutServlet", urlPatterns = {"/" + ServletPath.CHECKOUT_SERVLET})
public class CheckoutServlet extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(CheckoutServlet.class.getName());

    @Inject
    BillService billService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        UserAccount userAccount = (UserAccount) session.getAttribute(Key.USER_ACCOUNT_KEY);
        if (Util.isNotEmpty(userAccount)) {
            request.setAttribute("title", "Checkout");
            sendToPage(ViewPath.CHECKOUT_VIEW, request, response);
        } else {
            sendRedirectToPage(ServletPath.LOGIN_SERVLET, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String address = request.getParameter("address");
        String cardOwner = request.getParameter("cardOwner");
        String cardExpireMonth = request.getParameter("cardExpireMonth");
        String cardExpireYear = request.getParameter("cardExpireYear");
        String cardNumber = request.getParameter("cardNumber");
        String cardCVC = request.getParameter("cardCVC");

        HttpSession session = request.getSession(false);
        UserAccount userAccount = (UserAccount) session.getAttribute(Key.USER_ACCOUNT_KEY);
        if (Util.isNotEmpty(userAccount)) {

            if (Util.isNotEmpty(address)
                    && Util.isNotEmpty(cardOwner)
                    && Util.isNotEmpty(cardExpireMonth)
                    && Util.isNotEmpty(cardExpireYear)
                    && Util.isNotEmpty(cardNumber)
                    && Util.isNotEmpty(cardCVC)) {

                CartServiceImpl cartServiceImpl = (CartServiceImpl) session.getAttribute(Key.CART_KEY);

                Bill bill = new Bill(address, new Date(), cartServiceImpl.getTotalPrice());
                bill.setUserId(userAccount);

                Bill billNew = billService.create(bill);

                if (Util.isNotEmpty(billNew)) {
                    cartServiceImpl.clearAll();
                    sendToPage(ViewPath.CHECKOUT_COMPLETE_VIEW, request, response);
                }

            }
            request.setAttribute("title", "Checkout");
            sendToPage(ViewPath.CHECKOUT_VIEW, request, response);
        } else {
            sendRedirectToPage(ServletPath.LOGIN_SERVLET, response);
        }
    }

}
