package com.moommim.moommim_web.service;

import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.repository.UserRepository;
import com.moommim.moommim_web.service.base.UserService;
import javax.inject.Inject;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    @Override
    public UserAccount getUserById(int id) {
        return userRepository.findOptionalById(id);
    }

    @Override
    public UserAccount getUserByEmail(String email) {
        return userRepository.findOptionalByEmail(email);
    }

    @Override
    public boolean create(UserAccount user) {
        boolean result = false;
        try {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userRepository.save(user);
            result = true;
        } catch (Exception ex) {
            System.out.println("Error Create User: " + ex.getMessage());
        }
        return result;
    }

}
