package com.moommim.moommim_web.service.base;

import com.moommim.moommim_web.model.UserAccount;
import javax.servlet.http.HttpSession;

public interface AuthenticationService {

    UserAccount login(String email, String password);

    boolean logout(HttpSession session);

}
