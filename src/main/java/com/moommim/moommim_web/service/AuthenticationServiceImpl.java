package com.moommim.moommim_web.service;

import com.moommim.moommim_web.config.Key;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.repository.UserRepository;
import com.moommim.moommim_web.service.base.AuthenticationService;
import com.moommim.moommim_web.util.Util;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    UserRepository userRepository;

    @Override
    public UserAccount login(String email, String password) {
        UserAccount user = userRepository.findOptionalByEmail(email);
        if (Util.isNotEmpty(user)) {
            BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
            if (bCrypt.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean logout(HttpSession session) {
        boolean result = false;
        try {
            session.removeAttribute(Key.USER_ACCOUNT_KEY);
            result = true;
        } catch (Exception ex) {
            System.out.println("Error logout: " + ex.getMessage());
        }
        return result;
    }

}
