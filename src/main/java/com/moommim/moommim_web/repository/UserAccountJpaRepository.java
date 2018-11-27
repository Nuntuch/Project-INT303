package com.moommim.moommim_web.repository;

import com.moommim.moommim_web.model.UserAccount;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.SingularAttribute;
import javax.transaction.Transactional;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Transactional
@Repository
public abstract class UserAccountJpaRepository extends AbstractEntityRepository<UserAccount, Integer>{

    public abstract UserAccount findByEmail(String email);
    
    
   
}