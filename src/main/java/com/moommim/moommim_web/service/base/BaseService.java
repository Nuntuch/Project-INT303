package com.moommim.moommim_web.service.base;

import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

public interface BaseService {
    
    void setJpa(EntityManagerFactory emf, UserTransaction utx);
    
}
