package com.moommim.moommim_web.service;

import com.moommim.moommim_web.repository.BillJpaController;
import com.moommim.moommim_web.service.base.ExampleService;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

@ApplicationScoped
public class ExampleServiceImpl implements ExampleService {
    
    private BillJpaController billJpaController;
           
    @Override
    public void setJpa(EntityManagerFactory emf, UserTransaction utx) {
        billJpaController = new BillJpaController(utx, emf);
    }
    
    @Override
    public String getGreeting() {
        billJpaController.getBillCount();
        return "Hello World";
    }

}
