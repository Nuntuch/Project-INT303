package com.moommim.moommim_web.service;

import com.moommim.moommim_web.config.App;
import com.moommim.moommim_web.config.Key;
import com.moommim.moommim_web.model.MailMessage;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.repository.MailRepository;
import com.moommim.moommim_web.repository.UserAccountRepository;
import com.moommim.moommim_web.service.base.AuthenticationService;
import com.moommim.moommim_web.service.base.MailService;
import com.moommim.moommim_web.util.Util;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

public class AuthenticationServiceImpl implements AuthenticationService {

    private final static Logger LOGGER = Logger.getLogger(AuthenticationServiceImpl.class.getName());

    @Inject
    private UserAccountRepository userAccountRepo;

    @Override
    public UserAccount login(String username, String password) {
        if (Util.isNotEmpty(username) && Util.isNotEmpty(password)) {
            UserAccount userAccountObj = userAccountRepo.findOptionalByEmail(username);
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
            UserAccount userAccountObj = userAccountRepo.findOptionalByEmail(username);
            if (Util.isNotEmpty(userAccountObj)) {
                try {
                    String forgotToken = new SCryptPasswordEncoder().encode(username + Instant.now());
                    mailService.sendMail(username, new MailMessage("Hello Form Moommim :D", "<h1>Send Mail Noti</h1><br><a href='http://localhost:8080/Project-INT303/forgot?token=' " + forgotToken));
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }

}
