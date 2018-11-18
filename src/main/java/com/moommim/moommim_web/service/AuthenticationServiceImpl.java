package com.moommim.moommim_web.service;

import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.model.controller.UserAccountJpaController;
import com.moommim.moommim_web.service.base.AuthenticationService;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author Nuntuch Thongyoo
 */
public class AuthenticationServiceImpl implements AuthenticationService{

     @PersistenceUnit(unitName = "com.moommim_moommim_web_war_1.0-SNAPSHOTPU")
    EntityManagerFactory emf;

    @Resource
    UserTransaction utx;
    
    @Override
    public UserAccount login(String username, String password) {
        UserAccountJpaController accountJpaController = new UserAccountJpaController(utx, emf);
        List <UserAccount> userAccountList = accountJpaController.findUserAccountEntities();
        
        for (UserAccount userAccount : userAccountList) {
            if(userAccount.getEmail().equals(username)){
                if(userAccount.getPassword().equals(password)){
                return userAccount;
                }
                
            }
        }
        return null;
        
    }

    @Override
    public boolean logout(HttpSession session) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isLogin(HttpSession session) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ForgoutPassword(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
