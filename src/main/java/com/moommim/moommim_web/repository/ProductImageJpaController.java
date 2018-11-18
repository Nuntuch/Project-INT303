
package com.moommim.moommim_web.repository;

import com.moommim.moommim_web.model.ProductImage;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.moommim.moommim_web.model.ProductStock;
import com.moommim.moommim_web.repository.exceptions.NonexistentEntityException;
import com.moommim.moommim_web.repository.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

public class ProductImageJpaController implements Serializable {

    public ProductImageJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductImage productImage) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductStock productId = productImage.getProductId();
            if (productId != null) {
                productId = em.getReference(productId.getClass(), productId.getId());
                productImage.setProductId(productId);
            }
            em.persist(productImage);
            if (productId != null) {
                productId.getProductImageList().add(productImage);
                productId = em.merge(productId);
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

    public void edit(ProductImage productImage) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductImage persistentProductImage = em.find(ProductImage.class, productImage.getId());
            ProductStock productIdOld = persistentProductImage.getProductId();
            ProductStock productIdNew = productImage.getProductId();
            if (productIdNew != null) {
                productIdNew = em.getReference(productIdNew.getClass(), productIdNew.getId());
                productImage.setProductId(productIdNew);
            }
            productImage = em.merge(productImage);
            if (productIdOld != null && !productIdOld.equals(productIdNew)) {
                productIdOld.getProductImageList().remove(productImage);
                productIdOld = em.merge(productIdOld);
            }
            if (productIdNew != null && !productIdNew.equals(productIdOld)) {
                productIdNew.getProductImageList().add(productImage);
                productIdNew = em.merge(productIdNew);
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
                Integer id = productImage.getId();
                if (findProductImage(id) == null) {
                    throw new NonexistentEntityException("The productImage with id " + id + " no longer exists.");
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
            ProductImage productImage;
            try {
                productImage = em.getReference(ProductImage.class, id);
                productImage.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productImage with id " + id + " no longer exists.", enfe);
            }
            ProductStock productId = productImage.getProductId();
            if (productId != null) {
                productId.getProductImageList().remove(productImage);
                productId = em.merge(productId);
            }
            em.remove(productImage);
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

    public List<ProductImage> findProductImageEntities() {
        return findProductImageEntities(true, -1, -1);
    }

    public List<ProductImage> findProductImageEntities(int maxResults, int firstResult) {
        return findProductImageEntities(false, maxResults, firstResult);
    }

    private List<ProductImage> findProductImageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductImage.class));
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

    public ProductImage findProductImage(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductImage.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductImageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductImage> rt = cq.from(ProductImage.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
