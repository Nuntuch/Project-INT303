package com.moommim.moommim_web.repository;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.moommim.moommim_web.model.UserType;
import com.moommim.moommim_web.model.ProductStock;
import java.util.ArrayList;
import java.util.List;
import com.moommim.moommim_web.model.ProductReview;
import com.moommim.moommim_web.model.Bill;
import com.moommim.moommim_web.model.UserAddress;
import com.moommim.moommim_web.model.ProductQuestion;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.repository.exceptions.IllegalOrphanException;
import com.moommim.moommim_web.repository.exceptions.NonexistentEntityException;
import com.moommim.moommim_web.repository.exceptions.RollbackFailureException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

public class UserAccountJpaController implements Serializable {

    public UserAccountJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserAccount userAccount) throws RollbackFailureException, Exception {
        if (userAccount.getProductStockList() == null) {
            userAccount.setProductStockList(new ArrayList<ProductStock>());
        }
        if (userAccount.getProductReviewList() == null) {
            userAccount.setProductReviewList(new ArrayList<ProductReview>());
        }
        if (userAccount.getBillList() == null) {
            userAccount.setBillList(new ArrayList<Bill>());
        }
        if (userAccount.getUserAddressList() == null) {
            userAccount.setUserAddressList(new ArrayList<UserAddress>());
        }
        if (userAccount.getProductQuestionList() == null) {
            userAccount.setProductQuestionList(new ArrayList<ProductQuestion>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            UserType type = userAccount.getType();
            if (type != null) {
                type = em.getReference(type.getClass(), type.getId());
                userAccount.setType(type);
            }
            List<ProductStock> attachedProductStockList = new ArrayList<ProductStock>();
            for (ProductStock productStockListProductStockToAttach : userAccount.getProductStockList()) {
                productStockListProductStockToAttach = em.getReference(productStockListProductStockToAttach.getClass(), productStockListProductStockToAttach.getId());
                attachedProductStockList.add(productStockListProductStockToAttach);
            }
            userAccount.setProductStockList(attachedProductStockList);
            List<ProductReview> attachedProductReviewList = new ArrayList<ProductReview>();
            for (ProductReview productReviewListProductReviewToAttach : userAccount.getProductReviewList()) {
                productReviewListProductReviewToAttach = em.getReference(productReviewListProductReviewToAttach.getClass(), productReviewListProductReviewToAttach.getId());
                attachedProductReviewList.add(productReviewListProductReviewToAttach);
            }
            userAccount.setProductReviewList(attachedProductReviewList);
            List<Bill> attachedBillList = new ArrayList<Bill>();
            for (Bill billListBillToAttach : userAccount.getBillList()) {
                billListBillToAttach = em.getReference(billListBillToAttach.getClass(), billListBillToAttach.getId());
                attachedBillList.add(billListBillToAttach);
            }
            userAccount.setBillList(attachedBillList);
            List<UserAddress> attachedUserAddressList = new ArrayList<UserAddress>();
            for (UserAddress userAddressListUserAddressToAttach : userAccount.getUserAddressList()) {
                userAddressListUserAddressToAttach = em.getReference(userAddressListUserAddressToAttach.getClass(), userAddressListUserAddressToAttach.getId());
                attachedUserAddressList.add(userAddressListUserAddressToAttach);
            }
            userAccount.setUserAddressList(attachedUserAddressList);
            List<ProductQuestion> attachedProductQuestionList = new ArrayList<ProductQuestion>();
            for (ProductQuestion productQuestionListProductQuestionToAttach : userAccount.getProductQuestionList()) {
                productQuestionListProductQuestionToAttach = em.getReference(productQuestionListProductQuestionToAttach.getClass(), productQuestionListProductQuestionToAttach.getId());
                attachedProductQuestionList.add(productQuestionListProductQuestionToAttach);
            }
            userAccount.setProductQuestionList(attachedProductQuestionList);
            em.persist(userAccount);
            if (type != null) {
                type.getUserAccountList().add(userAccount);
                type = em.merge(type);
            }
            for (ProductStock productStockListProductStock : userAccount.getProductStockList()) {
                productStockListProductStock.getUserAccountList().add(userAccount);
                productStockListProductStock = em.merge(productStockListProductStock);
            }
            for (ProductReview productReviewListProductReview : userAccount.getProductReviewList()) {
                UserAccount oldUserIdOfProductReviewListProductReview = productReviewListProductReview.getUserId();
                productReviewListProductReview.setUserId(userAccount);
                productReviewListProductReview = em.merge(productReviewListProductReview);
                if (oldUserIdOfProductReviewListProductReview != null) {
                    oldUserIdOfProductReviewListProductReview.getProductReviewList().remove(productReviewListProductReview);
                    oldUserIdOfProductReviewListProductReview = em.merge(oldUserIdOfProductReviewListProductReview);
                }
            }
            for (Bill billListBill : userAccount.getBillList()) {
                UserAccount oldUserIdOfBillListBill = billListBill.getUserId();
                billListBill.setUserId(userAccount);
                billListBill = em.merge(billListBill);
                if (oldUserIdOfBillListBill != null) {
                    oldUserIdOfBillListBill.getBillList().remove(billListBill);
                    oldUserIdOfBillListBill = em.merge(oldUserIdOfBillListBill);
                }
            }
            for (UserAddress userAddressListUserAddress : userAccount.getUserAddressList()) {
                UserAccount oldUserIdOfUserAddressListUserAddress = userAddressListUserAddress.getUserId();
                userAddressListUserAddress.setUserId(userAccount);
                userAddressListUserAddress = em.merge(userAddressListUserAddress);
                if (oldUserIdOfUserAddressListUserAddress != null) {
                    oldUserIdOfUserAddressListUserAddress.getUserAddressList().remove(userAddressListUserAddress);
                    oldUserIdOfUserAddressListUserAddress = em.merge(oldUserIdOfUserAddressListUserAddress);
                }
            }
            for (ProductQuestion productQuestionListProductQuestion : userAccount.getProductQuestionList()) {
                UserAccount oldUserIdOfProductQuestionListProductQuestion = productQuestionListProductQuestion.getUserId();
                productQuestionListProductQuestion.setUserId(userAccount);
                productQuestionListProductQuestion = em.merge(productQuestionListProductQuestion);
                if (oldUserIdOfProductQuestionListProductQuestion != null) {
                    oldUserIdOfProductQuestionListProductQuestion.getProductQuestionList().remove(productQuestionListProductQuestion);
                    oldUserIdOfProductQuestionListProductQuestion = em.merge(oldUserIdOfProductQuestionListProductQuestion);
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

    public void edit(UserAccount userAccount) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            UserAccount persistentUserAccount = em.find(UserAccount.class, userAccount.getId());
            UserType typeOld = persistentUserAccount.getType();
            UserType typeNew = userAccount.getType();
            List<ProductStock> productStockListOld = persistentUserAccount.getProductStockList();
            List<ProductStock> productStockListNew = userAccount.getProductStockList();
            List<ProductReview> productReviewListOld = persistentUserAccount.getProductReviewList();
            List<ProductReview> productReviewListNew = userAccount.getProductReviewList();
            List<Bill> billListOld = persistentUserAccount.getBillList();
            List<Bill> billListNew = userAccount.getBillList();
            List<UserAddress> userAddressListOld = persistentUserAccount.getUserAddressList();
            List<UserAddress> userAddressListNew = userAccount.getUserAddressList();
            List<ProductQuestion> productQuestionListOld = persistentUserAccount.getProductQuestionList();
            List<ProductQuestion> productQuestionListNew = userAccount.getProductQuestionList();
            List<String> illegalOrphanMessages = null;
            for (ProductReview productReviewListOldProductReview : productReviewListOld) {
                if (!productReviewListNew.contains(productReviewListOldProductReview)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductReview " + productReviewListOldProductReview + " since its userId field is not nullable.");
                }
            }
            for (Bill billListOldBill : billListOld) {
                if (!billListNew.contains(billListOldBill)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Bill " + billListOldBill + " since its userId field is not nullable.");
                }
            }
            for (UserAddress userAddressListOldUserAddress : userAddressListOld) {
                if (!userAddressListNew.contains(userAddressListOldUserAddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UserAddress " + userAddressListOldUserAddress + " since its userId field is not nullable.");
                }
            }
            for (ProductQuestion productQuestionListOldProductQuestion : productQuestionListOld) {
                if (!productQuestionListNew.contains(productQuestionListOldProductQuestion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductQuestion " + productQuestionListOldProductQuestion + " since its userId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (typeNew != null) {
                typeNew = em.getReference(typeNew.getClass(), typeNew.getId());
                userAccount.setType(typeNew);
            }
            List<ProductStock> attachedProductStockListNew = new ArrayList<ProductStock>();
            for (ProductStock productStockListNewProductStockToAttach : productStockListNew) {
                productStockListNewProductStockToAttach = em.getReference(productStockListNewProductStockToAttach.getClass(), productStockListNewProductStockToAttach.getId());
                attachedProductStockListNew.add(productStockListNewProductStockToAttach);
            }
            productStockListNew = attachedProductStockListNew;
            userAccount.setProductStockList(productStockListNew);
            List<ProductReview> attachedProductReviewListNew = new ArrayList<ProductReview>();
            for (ProductReview productReviewListNewProductReviewToAttach : productReviewListNew) {
                productReviewListNewProductReviewToAttach = em.getReference(productReviewListNewProductReviewToAttach.getClass(), productReviewListNewProductReviewToAttach.getId());
                attachedProductReviewListNew.add(productReviewListNewProductReviewToAttach);
            }
            productReviewListNew = attachedProductReviewListNew;
            userAccount.setProductReviewList(productReviewListNew);
            List<Bill> attachedBillListNew = new ArrayList<Bill>();
            for (Bill billListNewBillToAttach : billListNew) {
                billListNewBillToAttach = em.getReference(billListNewBillToAttach.getClass(), billListNewBillToAttach.getId());
                attachedBillListNew.add(billListNewBillToAttach);
            }
            billListNew = attachedBillListNew;
            userAccount.setBillList(billListNew);
            List<UserAddress> attachedUserAddressListNew = new ArrayList<UserAddress>();
            for (UserAddress userAddressListNewUserAddressToAttach : userAddressListNew) {
                userAddressListNewUserAddressToAttach = em.getReference(userAddressListNewUserAddressToAttach.getClass(), userAddressListNewUserAddressToAttach.getId());
                attachedUserAddressListNew.add(userAddressListNewUserAddressToAttach);
            }
            userAddressListNew = attachedUserAddressListNew;
            userAccount.setUserAddressList(userAddressListNew);
            List<ProductQuestion> attachedProductQuestionListNew = new ArrayList<ProductQuestion>();
            for (ProductQuestion productQuestionListNewProductQuestionToAttach : productQuestionListNew) {
                productQuestionListNewProductQuestionToAttach = em.getReference(productQuestionListNewProductQuestionToAttach.getClass(), productQuestionListNewProductQuestionToAttach.getId());
                attachedProductQuestionListNew.add(productQuestionListNewProductQuestionToAttach);
            }
            productQuestionListNew = attachedProductQuestionListNew;
            userAccount.setProductQuestionList(productQuestionListNew);
            userAccount = em.merge(userAccount);
            if (typeOld != null && !typeOld.equals(typeNew)) {
                typeOld.getUserAccountList().remove(userAccount);
                typeOld = em.merge(typeOld);
            }
            if (typeNew != null && !typeNew.equals(typeOld)) {
                typeNew.getUserAccountList().add(userAccount);
                typeNew = em.merge(typeNew);
            }
            for (ProductStock productStockListOldProductStock : productStockListOld) {
                if (!productStockListNew.contains(productStockListOldProductStock)) {
                    productStockListOldProductStock.getUserAccountList().remove(userAccount);
                    productStockListOldProductStock = em.merge(productStockListOldProductStock);
                }
            }
            for (ProductStock productStockListNewProductStock : productStockListNew) {
                if (!productStockListOld.contains(productStockListNewProductStock)) {
                    productStockListNewProductStock.getUserAccountList().add(userAccount);
                    productStockListNewProductStock = em.merge(productStockListNewProductStock);
                }
            }
            for (ProductReview productReviewListNewProductReview : productReviewListNew) {
                if (!productReviewListOld.contains(productReviewListNewProductReview)) {
                    UserAccount oldUserIdOfProductReviewListNewProductReview = productReviewListNewProductReview.getUserId();
                    productReviewListNewProductReview.setUserId(userAccount);
                    productReviewListNewProductReview = em.merge(productReviewListNewProductReview);
                    if (oldUserIdOfProductReviewListNewProductReview != null && !oldUserIdOfProductReviewListNewProductReview.equals(userAccount)) {
                        oldUserIdOfProductReviewListNewProductReview.getProductReviewList().remove(productReviewListNewProductReview);
                        oldUserIdOfProductReviewListNewProductReview = em.merge(oldUserIdOfProductReviewListNewProductReview);
                    }
                }
            }
            for (Bill billListNewBill : billListNew) {
                if (!billListOld.contains(billListNewBill)) {
                    UserAccount oldUserIdOfBillListNewBill = billListNewBill.getUserId();
                    billListNewBill.setUserId(userAccount);
                    billListNewBill = em.merge(billListNewBill);
                    if (oldUserIdOfBillListNewBill != null && !oldUserIdOfBillListNewBill.equals(userAccount)) {
                        oldUserIdOfBillListNewBill.getBillList().remove(billListNewBill);
                        oldUserIdOfBillListNewBill = em.merge(oldUserIdOfBillListNewBill);
                    }
                }
            }
            for (UserAddress userAddressListNewUserAddress : userAddressListNew) {
                if (!userAddressListOld.contains(userAddressListNewUserAddress)) {
                    UserAccount oldUserIdOfUserAddressListNewUserAddress = userAddressListNewUserAddress.getUserId();
                    userAddressListNewUserAddress.setUserId(userAccount);
                    userAddressListNewUserAddress = em.merge(userAddressListNewUserAddress);
                    if (oldUserIdOfUserAddressListNewUserAddress != null && !oldUserIdOfUserAddressListNewUserAddress.equals(userAccount)) {
                        oldUserIdOfUserAddressListNewUserAddress.getUserAddressList().remove(userAddressListNewUserAddress);
                        oldUserIdOfUserAddressListNewUserAddress = em.merge(oldUserIdOfUserAddressListNewUserAddress);
                    }
                }
            }
            for (ProductQuestion productQuestionListNewProductQuestion : productQuestionListNew) {
                if (!productQuestionListOld.contains(productQuestionListNewProductQuestion)) {
                    UserAccount oldUserIdOfProductQuestionListNewProductQuestion = productQuestionListNewProductQuestion.getUserId();
                    productQuestionListNewProductQuestion.setUserId(userAccount);
                    productQuestionListNewProductQuestion = em.merge(productQuestionListNewProductQuestion);
                    if (oldUserIdOfProductQuestionListNewProductQuestion != null && !oldUserIdOfProductQuestionListNewProductQuestion.equals(userAccount)) {
                        oldUserIdOfProductQuestionListNewProductQuestion.getProductQuestionList().remove(productQuestionListNewProductQuestion);
                        oldUserIdOfProductQuestionListNewProductQuestion = em.merge(oldUserIdOfProductQuestionListNewProductQuestion);
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
                Integer id = userAccount.getId();
                if (findUserAccount(id) == null) {
                    throw new NonexistentEntityException("The userAccount with id " + id + " no longer exists.");
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
            UserAccount userAccount;
            try {
                userAccount = em.getReference(UserAccount.class, id);
                userAccount.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userAccount with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProductReview> productReviewListOrphanCheck = userAccount.getProductReviewList();
            for (ProductReview productReviewListOrphanCheckProductReview : productReviewListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UserAccount (" + userAccount + ") cannot be destroyed since the ProductReview " + productReviewListOrphanCheckProductReview + " in its productReviewList field has a non-nullable userId field.");
            }
            List<Bill> billListOrphanCheck = userAccount.getBillList();
            for (Bill billListOrphanCheckBill : billListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UserAccount (" + userAccount + ") cannot be destroyed since the Bill " + billListOrphanCheckBill + " in its billList field has a non-nullable userId field.");
            }
            List<UserAddress> userAddressListOrphanCheck = userAccount.getUserAddressList();
            for (UserAddress userAddressListOrphanCheckUserAddress : userAddressListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UserAccount (" + userAccount + ") cannot be destroyed since the UserAddress " + userAddressListOrphanCheckUserAddress + " in its userAddressList field has a non-nullable userId field.");
            }
            List<ProductQuestion> productQuestionListOrphanCheck = userAccount.getProductQuestionList();
            for (ProductQuestion productQuestionListOrphanCheckProductQuestion : productQuestionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UserAccount (" + userAccount + ") cannot be destroyed since the ProductQuestion " + productQuestionListOrphanCheckProductQuestion + " in its productQuestionList field has a non-nullable userId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            UserType type = userAccount.getType();
            if (type != null) {
                type.getUserAccountList().remove(userAccount);
                type = em.merge(type);
            }
            List<ProductStock> productStockList = userAccount.getProductStockList();
            for (ProductStock productStockListProductStock : productStockList) {
                productStockListProductStock.getUserAccountList().remove(userAccount);
                productStockListProductStock = em.merge(productStockListProductStock);
            }
            em.remove(userAccount);
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

    public List<UserAccount> findUserAccountEntities() {
        return findUserAccountEntities(true, -1, -1);
    }

    public List<UserAccount> findUserAccountEntities(int maxResults, int firstResult) {
        return findUserAccountEntities(false, maxResults, firstResult);
    }

    private List<UserAccount> findUserAccountEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserAccount.class));
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

    public UserAccount findUserAccount(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserAccount.class, id);
        } finally {
            em.close();
        }
    }

// Add Find By Email
    public UserAccount findUserAccountByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("UserAccount.findByEmail");
            UserAccount userAccount = (UserAccount) q.setParameter("email", email);
            return userAccount;
        } finally {
            em.close();
        }

    }

    public int getUserAccountCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserAccount> rt = cq.from(UserAccount.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
