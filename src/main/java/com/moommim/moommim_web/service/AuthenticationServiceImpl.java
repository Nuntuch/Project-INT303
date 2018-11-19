package com.moommim.moommim_web.service;

import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.repository.UserAccountJpaController;
import com.moommim.moommim_web.service.base.AuthenticationService;
import com.moommim.moommim_web.util.Util;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
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
@ApplicationScoped
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserAccountJpaController userAccountJpaCtrl;

    @Override
    public UserAccount login(String username, String password) {

        if (Util.isNotEmpty(username) && Util.isNotEmpty(password)) {
            UserAccount userAccountObj = userAccountJpaCtrl.findUserAccountByEmail(username);

            if (userAccountObj != null) {
                if (userAccountObj.getEmail().equals(username)) {
                    if (userAccountObj.getPassword().equals(password)) {
                        return userAccountObj;
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

        if (Util.isNotEmpty(username)) {
            UserAccount userAccountObj = userAccountJpaCtrl.findUserAccountByEmail(username);
            if (userAccountObj != null) {
                try {
                    //คำสั่งส่งเมล
                    return true;
                } catch (Exception e) {
                    return false;

                }

            }

        }

        return false;

    }

}
