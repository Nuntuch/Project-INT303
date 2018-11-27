package com.moommim.moommim_web.service;

import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.repository.UserAccountJpaRepository;
import com.moommim.moommim_web.service.base.UserService;
import com.moommim.moommim_web.util.Util;
import java.util.List;
import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    @Inject
    private UserAccountJpaRepository userAccountRepo;

    @Override
    public boolean createUser(UserAccount userAccount) {

        UserAccount userAccountObj = userAccountRepo.save(userAccount);

        if (Util.isNotEmpty(userAccountObj)) {
            return true;

        }
        return false;

    }

    @Override
    public boolean editUser(UserAccount editAccount) {
        boolean result = false;
        try {
            userAccountJpaController.edit(editAccount);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean deleteUserById(int userId) {
        boolean result = false;
        try {
            userAccountJpaController.destroy(userId);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public UserAccount getUserById(int userId) {
        UserAccount user = userAccountJpaController.findUserAccount(userId);
        return user;
    }

    @Override
    public List<UserAccount> getAllUser() {
        List<UserAccount> allUser = userAccountJpaController.findUserAccountEntities();
        return allUser;
    }

    @Override
    public boolean isActivate(int userId) {
        boolean result = false;
        try {
            UserAccount user = userAccountJpaController.findUserAccount(userId);
            if (user.getActiveStatus() != null) {
                user.setActiveToken("activeToken");
                result = true;
            }

        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean activeUser(int userId, String token) {
        boolean result = false;
        try {
            UserAccount user = userAccountJpaController.findUserAccount(userId);
            user.getActiveToken();
            user.setActiveStatus("status");
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

}
