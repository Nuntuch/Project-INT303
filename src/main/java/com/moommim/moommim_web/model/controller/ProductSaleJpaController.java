package com.moommim.moommim_web.model.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.moommim.moommim_web.model.Bill;
import com.moommim.moommim_web.model.ProductSale;
import com.moommim.moommim_web.model.ProductStock;
import com.moommim.moommim_web.model.controller.exceptions.NonexistentEntityException;
import com.moommim.moommim_web.model.controller.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Nuntuch Thongyoo
 */
public class ProductSaleJpaController implements Serializable {

    public ProductSaleJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductSale productSale) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Bill billId = productSale.getBillId();
            if (billId != null) {
                billId = em.getReference(billId.getClass(), billId.getId());
                productSale.setBillId(billId);
            }
            ProductStock productId = productSale.getProductId();
            if (productId != null) {
                productId = em.getReference(productId.getClass(), productId.getId());
                productSale.setProductId(productId);
            }
            em.persist(productSale);
            if (billId != null) {
                billId.getProductSaleList().add(productSale);
                billId = em.merge(billId);
            }
            if (productId != null) {
                productId.getProductSaleList().add(productSale);
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

    public void edit(ProductSale productSale) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductSale persistentProductSale = em.find(ProductSale.class, productSale.getId());
            Bill billIdOld = persistentProductSale.getBillId();
            Bill billIdNew = productSale.getBillId();
            ProductStock productIdOld = persistentProductSale.getProductId();
            ProductStock productIdNew = productSale.getProductId();
            if (billIdNew != null) {
                billIdNew = em.getReference(billIdNew.getClass(), billIdNew.getId());
                productSale.setBillId(billIdNew);
            }
            if (productIdNew != null) {
                productIdNew = em.getReference(productIdNew.getClass(), productIdNew.getId());
                productSale.setProductId(productIdNew);
            }
            productSale = em.merge(productSale);
            if (billIdOld != null && !billIdOld.equals(billIdNew)) {
                billIdOld.getProductSaleList().remove(productSale);
                billIdOld = em.merge(billIdOld);
            }
            if (billIdNew != null && !billIdNew.equals(billIdOld)) {
                billIdNew.getProductSaleList().add(productSale);
                billIdNew = em.merge(billIdNew);
            }
            if (productIdOld != null && !productIdOld.equals(productIdNew)) {
                productIdOld.getProductSaleList().remove(productSale);
                productIdOld = em.merge(productIdOld);
            }
            if (productIdNew != null && !productIdNew.equals(productIdOld)) {
                productIdNew.getProductSaleList().add(productSale);
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
                Integer id = productSale.getId();
                if (findProductSale(id) == null) {
                    throw new NonexistentEntityException("The productSale with id " + id + " no longer exists.");
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
            ProductSale productSale;
            try {
                productSale = em.getReference(ProductSale.class, id);
                productSale.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productSale with id " + id + " no longer exists.", enfe);
            }
            Bill billId = productSale.getBillId();
            if (billId != null) {
                billId.getProductSaleList().remove(productSale);
                billId = em.merge(billId);
            }
            ProductStock productId = productSale.getProductId();
            if (productId != null) {
                productId.getProductSaleList().remove(productSale);
                productId = em.merge(productId);
            }
            em.remove(productSale);
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

    public List<ProductSale> findProductSaleEntities() {
        return findProductSaleEntities(true, -1, -1);
    }

    public List<ProductSale> findProductSaleEntities(int maxResults, int firstResult) {
        return findProductSaleEntities(false, maxResults, firstResult);
    }

    private List<ProductSale> findProductSaleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductSale.class));
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

    public ProductSale findProductSale(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductSale.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductSaleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductSale> rt = cq.from(ProductSale.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
