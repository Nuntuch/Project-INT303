package com.moommim.moommim_web.controller.base;

import com.google.gson.Gson;
import com.moommim.moommim_web.config.App;
import java.io.IOException;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

public abstract class BaseController extends HttpServlet {

    @PersistenceUnit(unitName = App.PERSISTANCE_NAME)
    protected EntityManagerFactory emf;

    @Resource
    protected UserTransaction utx;

    protected final void sendToPage(String path, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher(path).forward(request, response);
    }

    protected final void sendRedirectToPage(String path, HttpServletResponse response)
            throws IOException, ServletException {
        response.sendRedirect(path);
    }

    protected final void sendResponseToJson(HttpServletResponse response, Object json) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(json));
    }

}
