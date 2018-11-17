
package com.moommim.moommim_web.repository;

import com.moommim.moommim_web.model.ProductQuestion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.moommim.moommim_web.model.ProductStock;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.repository.exceptions.NonexistentEntityException;
import com.moommim.moommim_web.repository.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

public class ProductQuestionJpaController implements Serializable {

    public ProductQuestionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductQuestion productQuestion) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductStock productId = productQuestion.getProductId();
            if (productId != null) {
                productId = em.getReference(productId.getClass(), productId.getId());
                productQuestion.setProductId(productId);
            }
            UserAccount userId = productQuestion.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getId());
                productQuestion.setUserId(userId);
            }
            em.persist(productQuestion);
            if (productId != null) {
                productId.getProductQuestionList().add(productQuestion);
                productId = em.merge(productId);
            }
            if (userId != null) {
                userId.getProductQuestionList().add(productQuestion);
                userId = em.merge(userId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductQuestion productQuestion) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductQuestion persistentProductQuestion = em.find(ProductQuestion.class, productQuestion.getId());
            ProductStock productIdOld = persistentProductQuestion.getProductId();
            ProductStock productIdNew = productQuestion.getProductId();
            UserAccount userIdOld = persistentProductQuestion.getUserId();
            UserAccount userIdNew = productQuestion.getUserId();
            if (productIdNew != null) {
                productIdNew = em.getReference(productIdNew.getClass(), productIdNew.getId());
                productQuestion.setProductId(productIdNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getId());
                productQuestion.setUserId(userIdNew);
            }
            productQuestion = em.merge(productQuestion);
            if (productIdOld != null && !productIdOld.equals(productIdNew)) {
                productIdOld.getProductQuestionList().remove(productQuestion);
                productIdOld = em.merge(productIdOld);
            }
            if (productIdNew != null && !productIdNew.equals(productIdOld)) {
                productIdNew.getProductQuestionList().add(productQuestion);
                productIdNew = em.merge(productIdNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getProductQuestionList().remove(productQuestion);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getProductQuestionList().add(productQuestion);
                userIdNew = em.merge(userIdNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productQuestion.getId();
                if (findProductQuestion(id) == null) {
                    throw new NonexistentEntityException("The productQuestion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductQuestion productQuestion;
            try {
                productQuestion = em.getReference(ProductQuestion.class, id);
                productQuestion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productQuestion with id " + id + " no longer exists.", enfe);
            }
            ProductStock productId = productQuestion.getProductId();
            if (productId != null) {
                productId.getProductQuestionList().remove(productQuestion);
                productId = em.merge(productId);
            }
            UserAccount userId = productQuestion.getUserId();
            if (userId != null) {
                userId.getProductQuestionList().remove(productQuestion);
                userId = em.merge(userId);
            }
            em.remove(productQuestion);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductQuestion> findProductQuestionEntities() {
        return findProductQuestionEntities(true, -1, -1);
    }

    public List<ProductQuestion> findProductQuestionEntities(int maxResults, int firstResult) {
        return findProductQuestionEntities(false, maxResults, firstResult);
    }

    private List<ProductQuestion> findProductQuestionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductQuestion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProductQuestion findProductQuestion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductQuestion.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductQuestionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductQuestion> rt = cq.from(ProductQuestion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
