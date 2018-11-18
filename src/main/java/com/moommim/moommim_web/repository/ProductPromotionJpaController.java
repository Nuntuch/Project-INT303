
package com.moommim.moommim_web.repository;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.moommim.moommim_web.model.ProductStock;
import java.util.ArrayList;
import java.util.List;
import com.moommim.moommim_web.model.ProductCategory;
import com.moommim.moommim_web.model.Bill;
import com.moommim.moommim_web.model.ProductPromotion;
import com.moommim.moommim_web.repository.exceptions.NonexistentEntityException;
import com.moommim.moommim_web.repository.exceptions.RollbackFailureException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

public class ProductPromotionJpaController implements Serializable {

    public ProductPromotionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductPromotion productPromotion) throws RollbackFailureException, Exception {
        if (productPromotion.getProductStockList() == null) {
            productPromotion.setProductStockList(new ArrayList<ProductStock>());
        }
        if (productPromotion.getProductCategoryList() == null) {
            productPromotion.setProductCategoryList(new ArrayList<ProductCategory>());
        }
        if (productPromotion.getBillList() == null) {
            productPromotion.setBillList(new ArrayList<Bill>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<ProductStock> attachedProductStockList = new ArrayList<ProductStock>();
            for (ProductStock productStockListProductStockToAttach : productPromotion.getProductStockList()) {
                productStockListProductStockToAttach = em.getReference(productStockListProductStockToAttach.getClass(), productStockListProductStockToAttach.getId());
                attachedProductStockList.add(productStockListProductStockToAttach);
            }
            productPromotion.setProductStockList(attachedProductStockList);
            List<ProductCategory> attachedProductCategoryList = new ArrayList<ProductCategory>();
            for (ProductCategory productCategoryListProductCategoryToAttach : productPromotion.getProductCategoryList()) {
                productCategoryListProductCategoryToAttach = em.getReference(productCategoryListProductCategoryToAttach.getClass(), productCategoryListProductCategoryToAttach.getId());
                attachedProductCategoryList.add(productCategoryListProductCategoryToAttach);
            }
            productPromotion.setProductCategoryList(attachedProductCategoryList);
            List<Bill> attachedBillList = new ArrayList<Bill>();
            for (Bill billListBillToAttach : productPromotion.getBillList()) {
                billListBillToAttach = em.getReference(billListBillToAttach.getClass(), billListBillToAttach.getId());
                attachedBillList.add(billListBillToAttach);
            }
            productPromotion.setBillList(attachedBillList);
            em.persist(productPromotion);
            for (ProductStock productStockListProductStock : productPromotion.getProductStockList()) {
                ProductPromotion oldPromotionIdOfProductStockListProductStock = productStockListProductStock.getPromotionId();
                productStockListProductStock.setPromotionId(productPromotion);
                productStockListProductStock = em.merge(productStockListProductStock);
                if (oldPromotionIdOfProductStockListProductStock != null) {
                    oldPromotionIdOfProductStockListProductStock.getProductStockList().remove(productStockListProductStock);
                    oldPromotionIdOfProductStockListProductStock = em.merge(oldPromotionIdOfProductStockListProductStock);
                }
            }
            for (ProductCategory productCategoryListProductCategory : productPromotion.getProductCategoryList()) {
                ProductPromotion oldPromotionIdOfProductCategoryListProductCategory = productCategoryListProductCategory.getPromotionId();
                productCategoryListProductCategory.setPromotionId(productPromotion);
                productCategoryListProductCategory = em.merge(productCategoryListProductCategory);
                if (oldPromotionIdOfProductCategoryListProductCategory != null) {
                    oldPromotionIdOfProductCategoryListProductCategory.getProductCategoryList().remove(productCategoryListProductCategory);
                    oldPromotionIdOfProductCategoryListProductCategory = em.merge(oldPromotionIdOfProductCategoryListProductCategory);
                }
            }
            for (Bill billListBill : productPromotion.getBillList()) {
                ProductPromotion oldPromotionIdOfBillListBill = billListBill.getPromotionId();
                billListBill.setPromotionId(productPromotion);
                billListBill = em.merge(billListBill);
                if (oldPromotionIdOfBillListBill != null) {
                    oldPromotionIdOfBillListBill.getBillList().remove(billListBill);
                    oldPromotionIdOfBillListBill = em.merge(oldPromotionIdOfBillListBill);
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

    public void edit(ProductPromotion productPromotion) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductPromotion persistentProductPromotion = em.find(ProductPromotion.class, productPromotion.getId());
            List<ProductStock> productStockListOld = persistentProductPromotion.getProductStockList();
            List<ProductStock> productStockListNew = productPromotion.getProductStockList();
            List<ProductCategory> productCategoryListOld = persistentProductPromotion.getProductCategoryList();
            List<ProductCategory> productCategoryListNew = productPromotion.getProductCategoryList();
            List<Bill> billListOld = persistentProductPromotion.getBillList();
            List<Bill> billListNew = productPromotion.getBillList();
            List<ProductStock> attachedProductStockListNew = new ArrayList<ProductStock>();
            for (ProductStock productStockListNewProductStockToAttach : productStockListNew) {
                productStockListNewProductStockToAttach = em.getReference(productStockListNewProductStockToAttach.getClass(), productStockListNewProductStockToAttach.getId());
                attachedProductStockListNew.add(productStockListNewProductStockToAttach);
            }
            productStockListNew = attachedProductStockListNew;
            productPromotion.setProductStockList(productStockListNew);
            List<ProductCategory> attachedProductCategoryListNew = new ArrayList<ProductCategory>();
            for (ProductCategory productCategoryListNewProductCategoryToAttach : productCategoryListNew) {
                productCategoryListNewProductCategoryToAttach = em.getReference(productCategoryListNewProductCategoryToAttach.getClass(), productCategoryListNewProductCategoryToAttach.getId());
                attachedProductCategoryListNew.add(productCategoryListNewProductCategoryToAttach);
            }
            productCategoryListNew = attachedProductCategoryListNew;
            productPromotion.setProductCategoryList(productCategoryListNew);
            List<Bill> attachedBillListNew = new ArrayList<Bill>();
            for (Bill billListNewBillToAttach : billListNew) {
                billListNewBillToAttach = em.getReference(billListNewBillToAttach.getClass(), billListNewBillToAttach.getId());
                attachedBillListNew.add(billListNewBillToAttach);
            }
            billListNew = attachedBillListNew;
            productPromotion.setBillList(billListNew);
            productPromotion = em.merge(productPromotion);
            for (ProductStock productStockListOldProductStock : productStockListOld) {
                if (!productStockListNew.contains(productStockListOldProductStock)) {
                    productStockListOldProductStock.setPromotionId(null);
                    productStockListOldProductStock = em.merge(productStockListOldProductStock);
                }
            }
            for (ProductStock productStockListNewProductStock : productStockListNew) {
                if (!productStockListOld.contains(productStockListNewProductStock)) {
                    ProductPromotion oldPromotionIdOfProductStockListNewProductStock = productStockListNewProductStock.getPromotionId();
                    productStockListNewProductStock.setPromotionId(productPromotion);
                    productStockListNewProductStock = em.merge(productStockListNewProductStock);
                    if (oldPromotionIdOfProductStockListNewProductStock != null && !oldPromotionIdOfProductStockListNewProductStock.equals(productPromotion)) {
                        oldPromotionIdOfProductStockListNewProductStock.getProductStockList().remove(productStockListNewProductStock);
                        oldPromotionIdOfProductStockListNewProductStock = em.merge(oldPromotionIdOfProductStockListNewProductStock);
                    }
                }
            }
            for (ProductCategory productCategoryListOldProductCategory : productCategoryListOld) {
                if (!productCategoryListNew.contains(productCategoryListOldProductCategory)) {
                    productCategoryListOldProductCategory.setPromotionId(null);
                    productCategoryListOldProductCategory = em.merge(productCategoryListOldProductCategory);
                }
            }
            for (ProductCategory productCategoryListNewProductCategory : productCategoryListNew) {
                if (!productCategoryListOld.contains(productCategoryListNewProductCategory)) {
                    ProductPromotion oldPromotionIdOfProductCategoryListNewProductCategory = productCategoryListNewProductCategory.getPromotionId();
                    productCategoryListNewProductCategory.setPromotionId(productPromotion);
                    productCategoryListNewProductCategory = em.merge(productCategoryListNewProductCategory);
                    if (oldPromotionIdOfProductCategoryListNewProductCategory != null && !oldPromotionIdOfProductCategoryListNewProductCategory.equals(productPromotion)) {
                        oldPromotionIdOfProductCategoryListNewProductCategory.getProductCategoryList().remove(productCategoryListNewProductCategory);
                        oldPromotionIdOfProductCategoryListNewProductCategory = em.merge(oldPromotionIdOfProductCategoryListNewProductCategory);
                    }
                }
            }
            for (Bill billListOldBill : billListOld) {
                if (!billListNew.contains(billListOldBill)) {
                    billListOldBill.setPromotionId(null);
                    billListOldBill = em.merge(billListOldBill);
                }
            }
            for (Bill billListNewBill : billListNew) {
                if (!billListOld.contains(billListNewBill)) {
                    ProductPromotion oldPromotionIdOfBillListNewBill = billListNewBill.getPromotionId();
                    billListNewBill.setPromotionId(productPromotion);
                    billListNewBill = em.merge(billListNewBill);
                    if (oldPromotionIdOfBillListNewBill != null && !oldPromotionIdOfBillListNewBill.equals(productPromotion)) {
                        oldPromotionIdOfBillListNewBill.getBillList().remove(billListNewBill);
                        oldPromotionIdOfBillListNewBill = em.merge(oldPromotionIdOfBillListNewBill);
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
                Integer id = productPromotion.getId();
                if (findProductPromotion(id) == null) {
                    throw new NonexistentEntityException("The productPromotion with id " + id + " no longer exists.");
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
            ProductPromotion productPromotion;
            try {
                productPromotion = em.getReference(ProductPromotion.class, id);
                productPromotion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productPromotion with id " + id + " no longer exists.", enfe);
            }
            List<ProductStock> productStockList = productPromotion.getProductStockList();
            for (ProductStock productStockListProductStock : productStockList) {
                productStockListProductStock.setPromotionId(null);
                productStockListProductStock = em.merge(productStockListProductStock);
            }
            List<ProductCategory> productCategoryList = productPromotion.getProductCategoryList();
            for (ProductCategory productCategoryListProductCategory : productCategoryList) {
                productCategoryListProductCategory.setPromotionId(null);
                productCategoryListProductCategory = em.merge(productCategoryListProductCategory);
            }
            List<Bill> billList = productPromotion.getBillList();
            for (Bill billListBill : billList) {
                billListBill.setPromotionId(null);
                billListBill = em.merge(billListBill);
            }
            em.remove(productPromotion);
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

    public List<ProductPromotion> findProductPromotionEntities() {
        return findProductPromotionEntities(true, -1, -1);
    }

    public List<ProductPromotion> findProductPromotionEntities(int maxResults, int firstResult) {
        return findProductPromotionEntities(false, maxResults, firstResult);
    }

    private List<ProductPromotion> findProductPromotionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductPromotion.class));
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

    public ProductPromotion findProductPromotion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductPromotion.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductPromotionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductPromotion> rt = cq.from(ProductPromotion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
