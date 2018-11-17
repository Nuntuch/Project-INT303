
package com.moommim.moommim_web.repository;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.model.Bill;
import com.moommim.moommim_web.model.UserAddress;
import com.moommim.moommim_web.repository.exceptions.IllegalOrphanException;
import com.moommim.moommim_web.repository.exceptions.NonexistentEntityException;
import com.moommim.moommim_web.repository.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

public class UserAddressJpaController implements Serializable {

    public UserAddressJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserAddress userAddress) throws RollbackFailureException, Exception {
        if (userAddress.getBillList() == null) {
            userAddress.setBillList(new ArrayList<Bill>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            UserAccount userId = userAddress.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getId());
                userAddress.setUserId(userId);
            }
            List<Bill> attachedBillList = new ArrayList<Bill>();
            for (Bill billListBillToAttach : userAddress.getBillList()) {
                billListBillToAttach = em.getReference(billListBillToAttach.getClass(), billListBillToAttach.getId());
                attachedBillList.add(billListBillToAttach);
            }
            userAddress.setBillList(attachedBillList);
            em.persist(userAddress);
            if (userId != null) {
                userId.getUserAddressList().add(userAddress);
                userId = em.merge(userId);
            }
            for (Bill billListBill : userAddress.getBillList()) {
                UserAddress oldAddressIdOfBillListBill = billListBill.getAddressId();
                billListBill.setAddressId(userAddress);
                billListBill = em.merge(billListBill);
                if (oldAddressIdOfBillListBill != null) {
                    oldAddressIdOfBillListBill.getBillList().remove(billListBill);
                    oldAddressIdOfBillListBill = em.merge(oldAddressIdOfBillListBill);
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

    public void edit(UserAddress userAddress) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            UserAddress persistentUserAddress = em.find(UserAddress.class, userAddress.getId());
            UserAccount userIdOld = persistentUserAddress.getUserId();
            UserAccount userIdNew = userAddress.getUserId();
            List<Bill> billListOld = persistentUserAddress.getBillList();
            List<Bill> billListNew = userAddress.getBillList();
            List<String> illegalOrphanMessages = null;
            for (Bill billListOldBill : billListOld) {
                if (!billListNew.contains(billListOldBill)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Bill " + billListOldBill + " since its addressId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getId());
                userAddress.setUserId(userIdNew);
            }
            List<Bill> attachedBillListNew = new ArrayList<Bill>();
            for (Bill billListNewBillToAttach : billListNew) {
                billListNewBillToAttach = em.getReference(billListNewBillToAttach.getClass(), billListNewBillToAttach.getId());
                attachedBillListNew.add(billListNewBillToAttach);
            }
            billListNew = attachedBillListNew;
            userAddress.setBillList(billListNew);
            userAddress = em.merge(userAddress);
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getUserAddressList().remove(userAddress);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getUserAddressList().add(userAddress);
                userIdNew = em.merge(userIdNew);
            }
            for (Bill billListNewBill : billListNew) {
                if (!billListOld.contains(billListNewBill)) {
                    UserAddress oldAddressIdOfBillListNewBill = billListNewBill.getAddressId();
                    billListNewBill.setAddressId(userAddress);
                    billListNewBill = em.merge(billListNewBill);
                    if (oldAddressIdOfBillListNewBill != null && !oldAddressIdOfBillListNewBill.equals(userAddress)) {
                        oldAddressIdOfBillListNewBill.getBillList().remove(billListNewBill);
                        oldAddressIdOfBillListNewBill = em.merge(oldAddressIdOfBillListNewBill);
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
                Integer id = userAddress.getId();
                if (findUserAddress(id) == null) {
                    throw new NonexistentEntityException("The userAddress with id " + id + " no longer exists.");
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
            UserAddress userAddress;
            try {
                userAddress = em.getReference(UserAddress.class, id);
                userAddress.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userAddress with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Bill> billListOrphanCheck = userAddress.getBillList();
            for (Bill billListOrphanCheckBill : billListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UserAddress (" + userAddress + ") cannot be destroyed since the Bill " + billListOrphanCheckBill + " in its billList field has a non-nullable addressId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            UserAccount userId = userAddress.getUserId();
            if (userId != null) {
                userId.getUserAddressList().remove(userAddress);
                userId = em.merge(userId);
            }
            em.remove(userAddress);
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

    public List<UserAddress> findUserAddressEntities() {
        return findUserAddressEntities(true, -1, -1);
    }

    public List<UserAddress> findUserAddressEntities(int maxResults, int firstResult) {
        return findUserAddressEntities(false, maxResults, firstResult);
    }

    private List<UserAddress> findUserAddressEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserAddress.class));
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

    public UserAddress findUserAddress(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserAddress.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserAddressCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserAddress> rt = cq.from(UserAddress.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
