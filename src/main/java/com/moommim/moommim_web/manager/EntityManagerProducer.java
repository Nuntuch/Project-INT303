package com.moommim.moommim_web.manager;

import com.moommim.moommim_web.config.App;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {

    @PersistenceContext(unitName = App.PERSISTANCE_NAME)
    private EntityManager entityManager;

    @RequestScoped
    @Produces
    public EntityManager create() {
        return entityManager;
    }

}
