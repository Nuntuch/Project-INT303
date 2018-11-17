package com.moommim.moommim_web.service.base;

import com.moommim.moommim_web.model.UserAccount;
import java.util.List;

public interface UserService {

    boolean createUser(UserAccount userAccount);
    boolean editUser(UserAccount editAccount);
    boolean deleteUser(UserAccount deleteAccount);
    UserAccount getUserById(int userId);
    List<UserAccount> getAllUser();
    boolean isActivate(String isActivateUser);
    boolean activeUser(String activeUser);
}
