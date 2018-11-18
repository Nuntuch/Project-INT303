
package com.moommim.moommim_web.repository;

import com.moommim.moommim_web.model.Bill;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.moommim.moommim_web.model.ProductPromotion;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.model.UserAddress;
import com.moommim.moommim_web.model.ProductSale;
import com.moommim.moommim_web.repository.exceptions.IllegalOrphanException;
import com.moommim.moommim_web.repository.exceptions.NonexistentEntityException;
import com.moommim.moommim_web.repository.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

public class BillJpaController implements Serializable {

    public BillJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bill bill) throws RollbackFailureException, Exception {
        if (bill.getProductSaleList() == null) {
            bill.setProductSaleList(new ArrayList<ProductSale>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductPromotion promotionId = bill.getPromotionId();
            if (promotionId != null) {
                promotionId = em.getReference(promotionId.getClass(), promotionId.getId());
                bill.setPromotionId(promotionId);
            }
            UserAccount userId = bill.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getId());
                bill.setUserId(userId);
            }
            UserAddress addressId = bill.getAddressId();
            if (addressId != null) {
                addressId = em.getReference(addressId.getClass(), addressId.getId());
                bill.setAddressId(addressId);
            }
            List<ProductSale> attachedProductSaleList = new ArrayList<ProductSale>();
            for (ProductSale productSaleListProductSaleToAttach : bill.getProductSaleList()) {
                productSaleListProductSaleToAttach = em.getReference(productSaleListProductSaleToAttach.getClass(), productSaleListProductSaleToAttach.getId());
                attachedProductSaleList.add(productSaleListProductSaleToAttach);
            }
            bill.setProductSaleList(attachedProductSaleList);
            em.persist(bill);
            if (promotionId != null) {
                promotionId.getBillList().add(bill);
                promotionId = em.merge(promotionId);
            }
            if (userId != null) {
                userId.getBillList().add(bill);
                userId = em.merge(userId);
            }
            if (addressId != null) {
                addressId.getBillList().add(bill);
                addressId = em.merge(addressId);
            }
            for (ProductSale productSaleListProductSale : bill.getProductSaleList()) {
                Bill oldBillIdOfProductSaleListProductSale = productSaleListProductSale.getBillId();
                productSaleListProductSale.setBillId(bill);
                productSaleListProductSale = em.merge(productSaleListProductSale);
                if (oldBillIdOfProductSaleListProductSale != null) {
                    oldBillIdOfProductSaleListProductSale.getProductSaleList().remove(productSaleListProductSale);
                    oldBillIdOfProductSaleListProductSale = em.merge(oldBillIdOfProductSaleListProductSale);
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

    public void edit(Bill bill) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Bill persistentBill = em.find(Bill.class, bill.getId());
            ProductPromotion promotionIdOld = persistentBill.getPromotionId();
            ProductPromotion promotionIdNew = bill.getPromotionId();
            UserAccount userIdOld = persistentBill.getUserId();
            UserAccount userIdNew = bill.getUserId();
            UserAddress addressIdOld = persistentBill.getAddressId();
            UserAddress addressIdNew = bill.getAddressId();
            List<ProductSale> productSaleListOld = persistentBill.getProductSaleList();
            List<ProductSale> productSaleListNew = bill.getProductSaleList();
            List<String> illegalOrphanMessages = null;
            for (ProductSale productSaleListOldProductSale : productSaleListOld) {
                if (!productSaleListNew.contains(productSaleListOldProductSale)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductSale " + productSaleListOldProductSale + " since its billId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (promotionIdNew != null) {
                promotionIdNew = em.getReference(promotionIdNew.getClass(), promotionIdNew.getId());
                bill.setPromotionId(promotionIdNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getId());
                bill.setUserId(userIdNew);
            }
            if (addressIdNew != null) {
                addressIdNew = em.getReference(addressIdNew.getClass(), addressIdNew.getId());
                bill.setAddressId(addressIdNew);
            }
            List<ProductSale> attachedProductSaleListNew = new ArrayList<ProductSale>();
            for (ProductSale productSaleListNewProductSaleToAttach : productSaleListNew) {
                productSaleListNewProductSaleToAttach = em.getReference(productSaleListNewProductSaleToAttach.getClass(), productSaleListNewProductSaleToAttach.getId());
                attachedProductSaleListNew.add(productSaleListNewProductSaleToAttach);
            }
            productSaleListNew = attachedProductSaleListNew;
            bill.setProductSaleList(productSaleListNew);
            bill = em.merge(bill);
            if (promotionIdOld != null && !promotionIdOld.equals(promotionIdNew)) {
                promotionIdOld.getBillList().remove(bill);
                promotionIdOld = em.merge(promotionIdOld);
            }
            if (promotionIdNew != null && !promotionIdNew.equals(promotionIdOld)) {
                promotionIdNew.getBillList().add(bill);
                promotionIdNew = em.merge(promotionIdNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getBillList().remove(bill);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getBillList().add(bill);
                userIdNew = em.merge(userIdNew);
            }
            if (addressIdOld != null && !addressIdOld.equals(addressIdNew)) {
                addressIdOld.getBillList().remove(bill);
                addressIdOld = em.merge(addressIdOld);
            }
            if (addressIdNew != null && !addressIdNew.equals(addressIdOld)) {
                addressIdNew.getBillList().add(bill);
                addressIdNew = em.merge(addressIdNew);
            }
            for (ProductSale productSaleListNewProductSale : productSaleListNew) {
                if (!productSaleListOld.contains(productSaleListNewProductSale)) {
                    Bill oldBillIdOfProductSaleListNewProductSale = productSaleListNewProductSale.getBillId();
                    productSaleListNewProductSale.setBillId(bill);
                    productSaleListNewProductSale = em.merge(productSaleListNewProductSale);
                    if (oldBillIdOfProductSaleListNewProductSale != null && !oldBillIdOfProductSaleListNewProductSale.equals(bill)) {
                        oldBillIdOfProductSaleListNewProductSale.getProductSaleList().remove(productSaleListNewProductSale);
                        oldBillIdOfProductSaleListNewProductSale = em.merge(oldBillIdOfProductSaleListNewProductSale);
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
                Integer id = bill.getId();
                if (findBill(id) == null) {
                    throw new NonexistentEntityException("The bill with id " + id + " no longer exists.");
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
            Bill bill;
            try {
                bill = em.getReference(Bill.class, id);
                bill.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bill with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProductSale> productSaleListOrphanCheck = bill.getProductSaleList();
            for (ProductSale productSaleListOrphanCheckProductSale : productSaleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Bill (" + bill + ") cannot be destroyed since the ProductSale " + productSaleListOrphanCheckProductSale + " in its productSaleList field has a non-nullable billId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            ProductPromotion promotionId = bill.getPromotionId();
            if (promotionId != null) {
                promotionId.getBillList().remove(bill);
                promotionId = em.merge(promotionId);
            }
            UserAccount userId = bill.getUserId();
            if (userId != null) {
                userId.getBillList().remove(bill);
                userId = em.merge(userId);
            }
            UserAddress addressId = bill.getAddressId();
            if (addressId != null) {
                addressId.getBillList().remove(bill);
                addressId = em.merge(addressId);
            }
            em.remove(bill);
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

    public List<Bill> findBillEntities() {
        return findBillEntities(true, -1, -1);
    }

    public List<Bill> findBillEntities(int maxResults, int firstResult) {
        return findBillEntities(false, maxResults, firstResult);
    }

    private List<Bill> findBillEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bill.class));
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

    public Bill findBill(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bill.class, id);
        } finally {
            em.close();
        }
    }

    public int getBillCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bill> rt = cq.from(Bill.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
