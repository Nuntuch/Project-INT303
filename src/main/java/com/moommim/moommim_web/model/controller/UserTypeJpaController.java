package com.moommim.moommim_web.model.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.moommim.moommim_web.model.UserAccount;
import com.moommim.moommim_web.model.UserType;
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
public class UserTypeJpaController implements Serializable {

    public UserTypeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserType userType) throws RollbackFailureException, Exception {
        if (userType.getUserAccountList() == null) {
            userType.setUserAccountList(new ArrayList<UserAccount>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<UserAccount> attachedUserAccountList = new ArrayList<UserAccount>();
            for (UserAccount userAccountListUserAccountToAttach : userType.getUserAccountList()) {
                userAccountListUserAccountToAttach = em.getReference(userAccountListUserAccountToAttach.getClass(), userAccountListUserAccountToAttach.getId());
                attachedUserAccountList.add(userAccountListUserAccountToAttach);
            }
            userType.setUserAccountList(attachedUserAccountList);
            em.persist(userType);
            for (UserAccount userAccountListUserAccount : userType.getUserAccountList()) {
                UserType oldTypeOfUserAccountListUserAccount = userAccountListUserAccount.getType();
                userAccountListUserAccount.setType(userType);
                userAccountListUserAccount = em.merge(userAccountListUserAccount);
                if (oldTypeOfUserAccountListUserAccount != null) {
                    oldTypeOfUserAccountListUserAccount.getUserAccountList().remove(userAccountListUserAccount);
                    oldTypeOfUserAccountListUserAccount = em.merge(oldTypeOfUserAccountListUserAccount);
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

    public void edit(UserType userType) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            UserType persistentUserType = em.find(UserType.class, userType.getId());
            List<UserAccount> userAccountListOld = persistentUserType.getUserAccountList();
            List<UserAccount> userAccountListNew = userType.getUserAccountList();
            List<String> illegalOrphanMessages = null;
            for (UserAccount userAccountListOldUserAccount : userAccountListOld) {
                if (!userAccountListNew.contains(userAccountListOldUserAccount)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UserAccount " + userAccountListOldUserAccount + " since its type field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UserAccount> attachedUserAccountListNew = new ArrayList<UserAccount>();
            for (UserAccount userAccountListNewUserAccountToAttach : userAccountListNew) {
                userAccountListNewUserAccountToAttach = em.getReference(userAccountListNewUserAccountToAttach.getClass(), userAccountListNewUserAccountToAttach.getId());
                attachedUserAccountListNew.add(userAccountListNewUserAccountToAttach);
            }
            userAccountListNew = attachedUserAccountListNew;
            userType.setUserAccountList(userAccountListNew);
            userType = em.merge(userType);
            for (UserAccount userAccountListNewUserAccount : userAccountListNew) {
                if (!userAccountListOld.contains(userAccountListNewUserAccount)) {
                    UserType oldTypeOfUserAccountListNewUserAccount = userAccountListNewUserAccount.getType();
                    userAccountListNewUserAccount.setType(userType);
                    userAccountListNewUserAccount = em.merge(userAccountListNewUserAccount);
                    if (oldTypeOfUserAccountListNewUserAccount != null && !oldTypeOfUserAccountListNewUserAccount.equals(userType)) {
                        oldTypeOfUserAccountListNewUserAccount.getUserAccountList().remove(userAccountListNewUserAccount);
                        oldTypeOfUserAccountListNewUserAccount = em.merge(oldTypeOfUserAccountListNewUserAccount);
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
                Integer id = userType.getId();
                if (findUserType(id) == null) {
                    throw new NonexistentEntityException("The userType with id " + id + " no longer exists.");
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
            UserType userType;
            try {
                userType = em.getReference(UserType.class, id);
                userType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UserAccount> userAccountListOrphanCheck = userType.getUserAccountList();
            for (UserAccount userAccountListOrphanCheckUserAccount : userAccountListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UserType (" + userType + ") cannot be destroyed since the UserAccount " + userAccountListOrphanCheckUserAccount + " in its userAccountList field has a non-nullable type field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(userType);
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

    public List<UserType> findUserTypeEntities() {
        return findUserTypeEntities(true, -1, -1);
    }

    public List<UserType> findUserTypeEntities(int maxResults, int firstResult) {
        return findUserTypeEntities(false, maxResults, firstResult);
    }

    private List<UserType> findUserTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserType.class));
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

    public UserType findUserType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserType.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserType> rt = cq.from(UserType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
