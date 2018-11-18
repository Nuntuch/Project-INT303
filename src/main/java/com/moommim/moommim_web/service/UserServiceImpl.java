package com.moommim.moommim_web.service;

import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.repository.UserAccountJpaController;
import com.moommim.moommim_web.service.base.UserService;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

public class UserServiceImpl implements UserService{

    private UserAccountJpaController userAccountJpaController;

    public void setJpa(EntityManagerFactory emf, UserTransaction utx) {
        userAccountJpaController = new UserAccountJpaController(utx, emf);
    }

    @Override
    public boolean createUser(UserAccount userAccount) {
        boolean result = false;
        try {
            userAccountJpaController.create(userAccount);
            result = true;
        } catch (Exception ex) {
            result = false;
        }
        return result;
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
            if(user.getActiveStatus()!=null){
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
