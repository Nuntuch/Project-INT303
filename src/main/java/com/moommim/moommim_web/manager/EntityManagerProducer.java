package com.moommim.moommim_web.manager;

import com.moommim.moommim_web.config.App;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionScoped;

public class EntityManagerProducer {

    @PersistenceContext(unitName = App.PERSISTANCE_NAME)
    private EntityManager entityManager;

    @Produces
    @TransactionScoped
    public EntityManager create() {
        return entityManager;
    }

    public void close(@Disposes EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }

}
