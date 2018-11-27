package com.moommim.moommim_web.service;

import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.repository.UserAccountRepository;
import com.moommim.moommim_web.service.base.UserService;
import com.moommim.moommim_web.util.Util;
import java.util.List;
import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    @Inject
    private UserAccountRepository userAccountRepo;

    @Override
    public boolean createUser(UserAccount userAccount) {

        return Util.isNotEmpty(userAccountRepo.save(userAccount));

    }

    @Override
    public boolean editUser(UserAccount editAccount) {

        return Util.isNotEmpty(userAccountRepo.save(editAccount));

    }

    @Override
    public boolean deleteUserById(int userId) {

//        UserAccount userAccountObj = userAccountRepo.findBy(userId);
//        try {
//            userAccountRepo.remove(userAccountObj);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
        return userAccountRepo.removeById(userId);
    }

    @Override
    public UserAccount getUserById(int userId) {

        return userAccountRepo.findBy(userId);
    }

    @Override
    public List<UserAccount> getAllUser() {

        return userAccountRepo.findAll();

    }

    @Override
    public boolean isActivate(int userId) {

        
//        เช็คก่อนว่าเราหา user จาก id นี้เจอ
//                ถ้าเจอ ให้เช็คว่า active ยัง
//                        ไมเจอ return false
//                        เจอ เข็คว่า Active ยัง
//                              เจอ return true
//                              ไม่เจอ false
//                        
//                        
//                        
//                        
        
        
        
        
        System.out.println("Test");
        
        try {
            UserAccount user = userAccountRepo.findBy(userId);
            if (Util.isNotEmpty(user.getActiveStatus())) {
////                user.setActiveToken("activeToken");
//                userAccountRepo.save(user);
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean activeUser(int userId, String token) {
//        boolean result = false;
//        try {
//             UserAccount user = userAccountRepo.findBy(userId);
//            user.getActiveToken();
//            user.setActiveStatus("status");
//            result = true;
//        } catch (Exception e) {
//            result = false;
//        }
//        return result;
    
         try {
            UserAccount user = userAccountRepo.findBy(userId);
            if (Util.isNotEmpty(user.getActiveStatus())) {
//                user.setActiveToken("activeToken");
                user.setActiveToken(token);
                userAccountRepo.save(user);
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    
    
    }

}
