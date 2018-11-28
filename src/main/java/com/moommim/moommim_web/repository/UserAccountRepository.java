package com.moommim.moommim_web.repository;

import com.moommim.moommim_web.model.UserAccount;
import javax.transaction.Transactional;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Transactional
@Repository
public abstract class UserAccountRepository extends AbstractEntityRepository<UserAccount, Integer>{

    public abstract UserAccount findOptionalByEmail(String email);
    
    public abstract boolean removeById(int id);
    
    public abstract UserAccount findByActiveToken(String token);
    
}