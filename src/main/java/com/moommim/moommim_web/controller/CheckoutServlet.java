package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.Key;
import com.moommim.moommim_web.config.ServletPath;
import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.model.Bill;
import com.moommim.moommim_web.model.CartItem;
import com.moommim.moommim_web.model.UserAccount;
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
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Hello");
        request.setAttribute("title", "Checkout");
        sendToPage(ViewPath.CHECKOUT_VIEW, request, response);
    }

    @Inject
    BillService billService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //                                        <textarea rows="2" name="address" placeholder="ชื่อ ที่อยู่สำหรับจัดส่งสินค้า" required></textarea>
//                                        <input type="text" name="cardOwner" placeholder="ชื่อผู้ถือบัตร" required>
//                                        <input type="number" name="cardExpireMonth" maxlength="4" placeholder="MM" required>
//                                        <input type="number" name="cardExpireYear" maxlength="4" placeholder="YY" required>
//                                        <input type="number" name="cardNumber" maxlength="16" placeholder="เลขบัตร" required>
//                                        <input type="number" name="cardCVC" maxlength="3" placeholder="CVC" required>
//                                
        String address = request.getParameter("address");
        String cardOwner = request.getParameter("cardOwner");
        String cardExpireMonth = request.getParameter("cardExpireMonth");
        String cardExpireYear = request.getParameter("cardExpireYear");
        String cardNumber = request.getParameter("cardNumber");
        String cardCVC = request.getParameter("cardCVC");

        if (Util.isNotEmpty(address)
                && Util.isNotEmpty(cardOwner)
                && Util.isNotEmpty(cardExpireMonth)
                && Util.isNotEmpty(cardExpireYear)
                && Util.isNotEmpty(cardNumber)
                && Util.isNotEmpty(cardCVC)) {

            HttpSession session = request.getSession(false);

            CartItem cartItem = (CartItem) session.getAttribute(Key.CART_KEY);
            UserAccount userAccount = (UserAccount) session.getAttribute(Key.USER_ACCOUNT_KEY);

            Bill bill = new Bill();
            bill.setAddress(address);
            bill.setCreateAt(new Date());
            bill.setTotalPrice(cartItem.getTotalPrice());
            bill.setUserId(userAccount);

            billService.AddBillToDB(bill);

            sendToPage(ServletPath.HOME_SERVLET, request, response);

        }
        sendToPage(ViewPath.CHECKOUT_VIEW, request, response);

        request.setAttribute("title", "Checkout");
    }

}
