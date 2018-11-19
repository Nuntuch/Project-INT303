package com.moommim.moommim_web.service;

import com.moommim.moommim_web.config.App;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.repository.UserAccountJpaController;
import com.moommim.moommim_web.service.base.AuthenticationService;
import com.moommim.moommim_web.util.Util;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthenticationServiceImpl implements AuthenticationService {

    private UserAccountJpaController userAccountJpaCtrl;

    public void setJpa(EntityManagerFactory emf, UserTransaction utx) {

        userAccountJpaCtrl = new UserAccountJpaController(utx, emf);

    }

    @Override
    public UserAccount login(String username, String password) {
        if (Util.isNotEmpty(username) && Util.isNotEmpty(password)) {
            UserAccount userAccountObj = userAccountJpaCtrl.findUserAccountByEmail(username);
            if (userAccountObj != null) {
                System.out.println("Email : " + userAccountObj.getEmail());
                BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
                String passwordFromDB = userAccountObj.getPassword();
                if (bCrypt.matches(password + App.SALT, passwordFromDB)) {
                    return userAccountObj;
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
