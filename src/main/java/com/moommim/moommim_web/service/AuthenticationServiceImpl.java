package com.moommim.moommim_web.service;

import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.repository.UserAccountJpaController;
import com.moommim.moommim_web.service.base.AuthenticationService;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import javax.websocket.Session;
import sun.rmi.transport.Transport;

/**
 *
 * @author Nuntuch Thongyoo
 */
public class AuthenticationServiceImpl implements AuthenticationService {


    private  UserAccountJpaController userAccountJpaCtrl ;
    
    @Override
    public void setJpaController(EntityManagerFactory emf, UserTransaction utx) {
          
        userAccountJpaCtrl = new UserAccountJpaController(utx, emf);
          
    } 

    @Override
    public UserAccount login(String username, String password) {

        if (username != null && username.trim().length() > 0
                && password != null && password.trim().length() > 0) {

//            List<UserAccount> userAccountList = accountJpaController.findUserAccountEntities();
            List<UserAccount> userAccountList = null;

            for (UserAccount userAccount : userAccountList) {
                if (userAccount.getEmail().equals(username)) {
                    if (userAccount.getPassword().equals(password)) {
                        return userAccount;
                    }

                }
            }
        }
        return null;

    }

    @Override
    public boolean logout(HttpSession session) {
        try {
            session.invalidate();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean isLogin(HttpSession session) {

        if (session != null) {
            if (session.getAttribute("UserAccountObj") != null) {
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean forgotPassword(String username) {

        if (username != null && username.trim().length() > 0) {

//            List<UserAccount> userAccountList = accountJpaController.findUserAccountEntities();
            List<UserAccount> userAccountList = null;

            for (UserAccount userAccount : userAccountList) {
                if (userAccount.getEmail().equals(username)) {

                    try {
                        //คำสั่งส่งEmail
                  
                        return true;
                    } catch (Exception e) {
                        return false;
                    }

                }
            }
        }

        return false;

    }

   

}
