package com.moommim.moommim_web.service.base;

import com.moommim.moommim_web.model.UserAccount;

public interface UserService {

    UserAccount getUserById(int id);
    
    UserAccount getUserByEmail(String email);
    
    boolean create(UserAccount user);

}
