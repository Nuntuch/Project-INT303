package com.moommim.moommim_web.controller;

import com.moommim.moommim_web.config.Key;
import com.moommim.moommim_web.config.ServletPath;
import com.moommim.moommim_web.config.ViewPath;
import com.moommim.moommim_web.controller.base.BaseController;
import com.moommim.moommim_web.model.Bill;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.service.base.BillService;
import com.moommim.moommim_web.util.Util;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "HistoryServlet", urlPatterns = {"/" + ServletPath.HISTORY_SERVLET})
public class HistoryServlet extends BaseController {
    
    private static final Logger LOGGER = Logger.getLogger(HistoryServlet.class.getName());
    
    @Inject
    private BillService billService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        UserAccount userAccount = (UserAccount) session.getAttribute(Key.USER_ACCOUNT_KEY);
        if (Util.isNotEmpty(userAccount)) {
            List<Bill> billList = billService.getBillByUserId(userAccount.getId());
            if (Util.isNotEmpty(billList)) {
                request.setAttribute("billList", billList);
            } else {
                request.setAttribute("status", "ไม่มีรายการสั่งซื้อ");
            }
            sendToPage(ViewPath.HISTORY_VIEW, request, response);
        } else {
            sendRedirectToPage(ServletPath.LOGIN_SERVLET, response);
        }
    }
    
}
