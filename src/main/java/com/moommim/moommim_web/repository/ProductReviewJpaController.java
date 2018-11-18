
package com.moommim.moommim_web.repository;

import com.moommim.moommim_web.model.ProductReview;
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

public class ProductReviewJpaController implements Serializable {

    public ProductReviewJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductReview productReview) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductStock productId = productReview.getProductId();
            if (productId != null) {
                productId = em.getReference(productId.getClass(), productId.getId());
                productReview.setProductId(productId);
            }
            UserAccount userId = productReview.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getId());
                productReview.setUserId(userId);
            }
            em.persist(productReview);
            if (productId != null) {
                productId.getProductReviewList().add(productReview);
                productId = em.merge(productId);
            }
            if (userId != null) {
                userId.getProductReviewList().add(productReview);
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

    public void edit(ProductReview productReview) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductReview persistentProductReview = em.find(ProductReview.class, productReview.getId());
            ProductStock productIdOld = persistentProductReview.getProductId();
            ProductStock productIdNew = productReview.getProductId();
            UserAccount userIdOld = persistentProductReview.getUserId();
            UserAccount userIdNew = productReview.getUserId();
            if (productIdNew != null) {
                productIdNew = em.getReference(productIdNew.getClass(), productIdNew.getId());
                productReview.setProductId(productIdNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getId());
                productReview.setUserId(userIdNew);
            }
            productReview = em.merge(productReview);
            if (productIdOld != null && !productIdOld.equals(productIdNew)) {
                productIdOld.getProductReviewList().remove(productReview);
                productIdOld = em.merge(productIdOld);
            }
            if (productIdNew != null && !productIdNew.equals(productIdOld)) {
                productIdNew.getProductReviewList().add(productReview);
                productIdNew = em.merge(productIdNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getProductReviewList().remove(productReview);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getProductReviewList().add(productReview);
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
                Integer id = productReview.getId();
                if (findProductReview(id) == null) {
                    throw new NonexistentEntityException("The productReview with id " + id + " no longer exists.");
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
            ProductReview productReview;
            try {
                productReview = em.getReference(ProductReview.class, id);
                productReview.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productReview with id " + id + " no longer exists.", enfe);
            }
            ProductStock productId = productReview.getProductId();
            if (productId != null) {
                productId.getProductReviewList().remove(productReview);
                productId = em.merge(productId);
            }
            UserAccount userId = productReview.getUserId();
            if (userId != null) {
                userId.getProductReviewList().remove(productReview);
                userId = em.merge(userId);
            }
            em.remove(productReview);
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

    public List<ProductReview> findProductReviewEntities() {
        return findProductReviewEntities(true, -1, -1);
    }

    public List<ProductReview> findProductReviewEntities(int maxResults, int firstResult) {
        return findProductReviewEntities(false, maxResults, firstResult);
    }

    private List<ProductReview> findProductReviewEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductReview.class));
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

    public ProductReview findProductReview(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductReview.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductReviewCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductReview> rt = cq.from(ProductReview.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
