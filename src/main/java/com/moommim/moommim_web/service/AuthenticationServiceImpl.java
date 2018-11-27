package com.moommim.moommim_web.service;

import com.moommim.moommim_web.config.App;
import com.moommim.moommim_web.config.Key;
import com.moommim.moommim_web.model.MailMessage;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.repository.MailRepository;
import com.moommim.moommim_web.repository.UserAccountJpaRepository;
import com.moommim.moommim_web.service.base.AuthenticationService;
import com.moommim.moommim_web.service.base.MailService;
import com.moommim.moommim_web.util.Util;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthenticationServiceImpl implements AuthenticationService {

    private final static Logger LOGGER = Logger.getLogger(AuthenticationServiceImpl.class.getName());

    @Inject
    private UserAccountJpaRepository userAccountRepo;

    @Override
    public UserAccount login(String username, String password) {
        if (Util.isNotEmpty(username) && Util.isNotEmpty(password)) {
            UserAccount userAccountObj = userAccountRepo.findByEmail(username);
            if (Util.isNotEmpty(userAccountObj)) {
                if (App.IS_DEV_MODE) {
                    LOGGER.log(Level.SEVERE, "User is logged");
                }
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
            if (App.IS_DEV_MODE) {
                LOGGER.log(Level.SEVERE, "User is logout");
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isLogin(HttpSession session) {
        if (Util.isNotEmpty(session)) {
            if (Util.isNotEmpty(session.getAttribute(Key.USER_ACCOUNT_KEY))) {
                return true;
            }
        }
        return false;
    }

    @Inject
    private MailService mailService;

    @Override
    public boolean forgotPassword(String username) {
        if (Util.isNotEmpty(username)) {
            UserAccount userAccountObj = userAccountRepo.findByEmail(username);
            if (Util.isNotEmpty(userAccountObj)) {
                try {
                    mailService.sendMail(username, new MailMessage("Hello Form Moommim :D", "<h1>Send Mail Noti</h1>"));
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }

}
