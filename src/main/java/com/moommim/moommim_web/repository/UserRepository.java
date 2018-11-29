package com.moommim.moommim_web.repository;

import javax.transaction.Transactional;

import com.moommim.moommim_web.model.UserAccount;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Transactional
@Repository
public abstract class UserRepository extends AbstractEntityRepository<UserAccount, Integer> {

    public abstract UserAccount findOptionalById(int id);

    public abstract UserAccount findOptionalByEmail(String email);

}
