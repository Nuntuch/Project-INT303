package com.moommim.moommim_web.controller.base;

import com.moommim.moommim_web.config.App;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServlet;
import javax.transaction.UserTransaction;

public abstract class BaseController extends HttpServlet {

    @PersistenceUnit(unitName = App.PERSISTANCE_NAME)
    protected EntityManagerFactory emf;

    @Resource
    protected UserTransaction utx;

}
