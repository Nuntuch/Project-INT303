package com.moommim.moommim_web.service;

import com.moommim.moommim_web.config.UserActivateConstant;
import com.moommim.moommim_web.model.MailMessage;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.repository.UserAccountRepository;
import com.moommim.moommim_web.service.base.MailService;
import com.moommim.moommim_web.service.base.UserService;
import com.moommim.moommim_web.util.Util;
import java.time.Instant;
import java.util.List;
import javax.inject.Inject;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

public class UserServiceImpl implements UserService {

    @Inject
    private UserAccountRepository userAccountRepo;

    @Inject
    private MailService mailService;
    
    @Override
    public boolean createUser(UserAccount userAccount) {
        
        if(Util.isNotEmpty(userAccountRepo.save(userAccount))){
        
              try {
                    String CreateAccountToken = new SCryptPasswordEncoder().encode(userAccount.getEmail() + Instant.now());
                    mailService.sendMail(userAccount.getEmail(), new MailMessage("Hello Form Moommim :D", "<h1>Send Mail Noti</h1><br><a href='http://localhost:8080/Project-INT303/user?token=' " + CreateAccountToken));
                    return true;
                } catch (Exception e) {
                    return false;
                }
        
        
        
        }
        return false;
        
//        return Util.isNotEmpty(userAccountRepo.save(userAccount));

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

        try {
            UserAccount user = getUserById(userId);
            if (Util.isNotEmpty(user.getActiveStatus()) && user.getActiveStatus().equals(UserActivateConstant.ACTIVATED)) {

                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean activeUser(int userId, String token) {

        try {
            UserAccount user = getUserById(userId);
            if (Util.isNotEmpty(user) && userAccountRepo.findByActiveToken(token).getId().equals(userId)) {

                    user.setActiveStatus(UserActivateConstant.ACTIVATED);
                    userAccountRepo.save(user);

                    return true;
                
            }
            return false;

        } catch (Exception e) {
            return false;
        }

    }

}
