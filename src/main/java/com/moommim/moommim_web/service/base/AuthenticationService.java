package com.moommim.moommim_web.service.base;

import com.moommim.moommim_web.model.UserAccount;
import javax.servlet.http.HttpSession;

public interface AuthenticationService extends BaseService{

    UserAccount login(String username, String password);
    
    boolean logout(HttpSession session); 
    
    boolean isLogin(HttpSession session);
    
    boolean forgotPassword(String username);
    
}
