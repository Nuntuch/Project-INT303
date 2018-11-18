package com.moommim.moommim_web.controller.base;

import com.moommim.moommim_web.config.App;
import java.io.IOException;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

public abstract class BaseController extends HttpServlet {

    @PersistenceUnit(unitName = App.PERSISTANCE_NAME)
    protected EntityManagerFactory emf;

    @Resource
    protected UserTransaction utx;

    protected final void sendResponseToJson(HttpServletResponse response, String json) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

}
