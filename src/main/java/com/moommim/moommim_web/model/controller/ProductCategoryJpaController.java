package com.moommim.moommim_web.model.controller;

import com.moommim.moommim_web.model.ProductCategory;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.moommim.moommim_web.model.ProductPromotion;
import com.moommim.moommim_web.model.ProductStock;
import com.moommim.moommim_web.model.controller.exceptions.IllegalOrphanException;
import com.moommim.moommim_web.model.controller.exceptions.NonexistentEntityException;
import com.moommim.moommim_web.model.controller.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Nuntuch Thongyoo
 */
public class ProductCategoryJpaController implements Serializable {

    public ProductCategoryJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductCategory productCategory) throws RollbackFailureException, Exception {
        if (productCategory.getProductStockList() == null) {
            productCategory.setProductStockList(new ArrayList<ProductStock>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductPromotion promotionId = productCategory.getPromotionId();
            if (promotionId != null) {
                promotionId = em.getReference(promotionId.getClass(), promotionId.getId());
                productCategory.setPromotionId(promotionId);
            }
            List<ProductStock> attachedProductStockList = new ArrayList<ProductStock>();
            for (ProductStock productStockListProductStockToAttach : productCategory.getProductStockList()) {
                productStockListProductStockToAttach = em.getReference(productStockListProductStockToAttach.getClass(), productStockListProductStockToAttach.getId());
                attachedProductStockList.add(productStockListProductStockToAttach);
            }
            productCategory.setProductStockList(attachedProductStockList);
            em.persist(productCategory);
            if (promotionId != null) {
                promotionId.getProductCategoryList().add(productCategory);
                promotionId = em.merge(promotionId);
            }
            for (ProductStock productStockListProductStock : productCategory.getProductStockList()) {
                ProductCategory oldCategoryIdOfProductStockListProductStock = productStockListProductStock.getCategoryId();
                productStockListProductStock.setCategoryId(productCategory);
                productStockListProductStock = em.merge(productStockListProductStock);
                if (oldCategoryIdOfProductStockListProductStock != null) {
                    oldCategoryIdOfProductStockListProductStock.getProductStockList().remove(productStockListProductStock);
                    oldCategoryIdOfProductStockListProductStock = em.merge(oldCategoryIdOfProductStockListProductStock);
                }
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

    public void edit(ProductCategory productCategory) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductCategory persistentProductCategory = em.find(ProductCategory.class, productCategory.getId());
            ProductPromotion promotionIdOld = persistentProductCategory.getPromotionId();
            ProductPromotion promotionIdNew = productCategory.getPromotionId();
            List<ProductStock> productStockListOld = persistentProductCategory.getProductStockList();
            List<ProductStock> productStockListNew = productCategory.getProductStockList();
            List<String> illegalOrphanMessages = null;
            for (ProductStock productStockListOldProductStock : productStockListOld) {
                if (!productStockListNew.contains(productStockListOldProductStock)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductStock " + productStockListOldProductStock + " since its categoryId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (promotionIdNew != null) {
                promotionIdNew = em.getReference(promotionIdNew.getClass(), promotionIdNew.getId());
                productCategory.setPromotionId(promotionIdNew);
            }
            List<ProductStock> attachedProductStockListNew = new ArrayList<ProductStock>();
            for (ProductStock productStockListNewProductStockToAttach : productStockListNew) {
                productStockListNewProductStockToAttach = em.getReference(productStockListNewProductStockToAttach.getClass(), productStockListNewProductStockToAttach.getId());
                attachedProductStockListNew.add(productStockListNewProductStockToAttach);
            }
            productStockListNew = attachedProductStockListNew;
            productCategory.setProductStockList(productStockListNew);
            productCategory = em.merge(productCategory);
            if (promotionIdOld != null && !promotionIdOld.equals(promotionIdNew)) {
                promotionIdOld.getProductCategoryList().remove(productCategory);
                promotionIdOld = em.merge(promotionIdOld);
            }
            if (promotionIdNew != null && !promotionIdNew.equals(promotionIdOld)) {
                promotionIdNew.getProductCategoryList().add(productCategory);
                promotionIdNew = em.merge(promotionIdNew);
            }
            for (ProductStock productStockListNewProductStock : productStockListNew) {
                if (!productStockListOld.contains(productStockListNewProductStock)) {
                    ProductCategory oldCategoryIdOfProductStockListNewProductStock = productStockListNewProductStock.getCategoryId();
                    productStockListNewProductStock.setCategoryId(productCategory);
                    productStockListNewProductStock = em.merge(productStockListNewProductStock);
                    if (oldCategoryIdOfProductStockListNewProductStock != null && !oldCategoryIdOfProductStockListNewProductStock.equals(productCategory)) {
                        oldCategoryIdOfProductStockListNewProductStock.getProductStockList().remove(productStockListNewProductStock);
                        oldCategoryIdOfProductStockListNewProductStock = em.merge(oldCategoryIdOfProductStockListNewProductStock);
                    }
                }
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
                Integer id = productCategory.getId();
                if (findProductCategory(id) == null) {
                    throw new NonexistentEntityException("The productCategory with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductCategory productCategory;
            try {
                productCategory = em.getReference(ProductCategory.class, id);
                productCategory.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productCategory with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProductStock> productStockListOrphanCheck = productCategory.getProductStockList();
            for (ProductStock productStockListOrphanCheckProductStock : productStockListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProductCategory (" + productCategory + ") cannot be destroyed since the ProductStock " + productStockListOrphanCheckProductStock + " in its productStockList field has a non-nullable categoryId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            ProductPromotion promotionId = productCategory.getPromotionId();
            if (promotionId != null) {
                promotionId.getProductCategoryList().remove(productCategory);
                promotionId = em.merge(promotionId);
            }
            em.remove(productCategory);
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

    public List<ProductCategory> findProductCategoryEntities() {
        return findProductCategoryEntities(true, -1, -1);
    }

    public List<ProductCategory> findProductCategoryEntities(int maxResults, int firstResult) {
        return findProductCategoryEntities(false, maxResults, firstResult);
    }

    private List<ProductCategory> findProductCategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductCategory.class));
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

    public ProductCategory findProductCategory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductCategory.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductCategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductCategory> rt = cq.from(ProductCategory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
