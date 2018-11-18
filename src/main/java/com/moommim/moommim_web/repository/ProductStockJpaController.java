
package com.moommim.moommim_web.repository;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.moommim.moommim_web.model.ProductCategory;
import com.moommim.moommim_web.model.ProductPromotion;
import com.moommim.moommim_web.model.UserAccount;
import java.util.ArrayList;
import java.util.List;
import com.moommim.moommim_web.model.ProductReview;
import com.moommim.moommim_web.model.ProductImage;
import com.moommim.moommim_web.model.ProductSale;
import com.moommim.moommim_web.model.ProductQuestion;
import com.moommim.moommim_web.model.ProductStock;
import com.moommim.moommim_web.repository.exceptions.IllegalOrphanException;
import com.moommim.moommim_web.repository.exceptions.NonexistentEntityException;
import com.moommim.moommim_web.repository.exceptions.RollbackFailureException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

public class ProductStockJpaController implements Serializable {

    public ProductStockJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductStock productStock) throws RollbackFailureException, Exception {
        if (productStock.getUserAccountList() == null) {
            productStock.setUserAccountList(new ArrayList<UserAccount>());
        }
        if (productStock.getProductReviewList() == null) {
            productStock.setProductReviewList(new ArrayList<ProductReview>());
        }
        if (productStock.getProductImageList() == null) {
            productStock.setProductImageList(new ArrayList<ProductImage>());
        }
        if (productStock.getProductSaleList() == null) {
            productStock.setProductSaleList(new ArrayList<ProductSale>());
        }
        if (productStock.getProductQuestionList() == null) {
            productStock.setProductQuestionList(new ArrayList<ProductQuestion>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductCategory categoryId = productStock.getCategoryId();
            if (categoryId != null) {
                categoryId = em.getReference(categoryId.getClass(), categoryId.getId());
                productStock.setCategoryId(categoryId);
            }
            ProductPromotion promotionId = productStock.getPromotionId();
            if (promotionId != null) {
                promotionId = em.getReference(promotionId.getClass(), promotionId.getId());
                productStock.setPromotionId(promotionId);
            }
            List<UserAccount> attachedUserAccountList = new ArrayList<UserAccount>();
            for (UserAccount userAccountListUserAccountToAttach : productStock.getUserAccountList()) {
                userAccountListUserAccountToAttach = em.getReference(userAccountListUserAccountToAttach.getClass(), userAccountListUserAccountToAttach.getId());
                attachedUserAccountList.add(userAccountListUserAccountToAttach);
            }
            productStock.setUserAccountList(attachedUserAccountList);
            List<ProductReview> attachedProductReviewList = new ArrayList<ProductReview>();
            for (ProductReview productReviewListProductReviewToAttach : productStock.getProductReviewList()) {
                productReviewListProductReviewToAttach = em.getReference(productReviewListProductReviewToAttach.getClass(), productReviewListProductReviewToAttach.getId());
                attachedProductReviewList.add(productReviewListProductReviewToAttach);
            }
            productStock.setProductReviewList(attachedProductReviewList);
            List<ProductImage> attachedProductImageList = new ArrayList<ProductImage>();
            for (ProductImage productImageListProductImageToAttach : productStock.getProductImageList()) {
                productImageListProductImageToAttach = em.getReference(productImageListProductImageToAttach.getClass(), productImageListProductImageToAttach.getId());
                attachedProductImageList.add(productImageListProductImageToAttach);
            }
            productStock.setProductImageList(attachedProductImageList);
            List<ProductSale> attachedProductSaleList = new ArrayList<ProductSale>();
            for (ProductSale productSaleListProductSaleToAttach : productStock.getProductSaleList()) {
                productSaleListProductSaleToAttach = em.getReference(productSaleListProductSaleToAttach.getClass(), productSaleListProductSaleToAttach.getId());
                attachedProductSaleList.add(productSaleListProductSaleToAttach);
            }
            productStock.setProductSaleList(attachedProductSaleList);
            List<ProductQuestion> attachedProductQuestionList = new ArrayList<ProductQuestion>();
            for (ProductQuestion productQuestionListProductQuestionToAttach : productStock.getProductQuestionList()) {
                productQuestionListProductQuestionToAttach = em.getReference(productQuestionListProductQuestionToAttach.getClass(), productQuestionListProductQuestionToAttach.getId());
                attachedProductQuestionList.add(productQuestionListProductQuestionToAttach);
            }
            productStock.setProductQuestionList(attachedProductQuestionList);
            em.persist(productStock);
            if (categoryId != null) {
                categoryId.getProductStockList().add(productStock);
                categoryId = em.merge(categoryId);
            }
            if (promotionId != null) {
                promotionId.getProductStockList().add(productStock);
                promotionId = em.merge(promotionId);
            }
            for (UserAccount userAccountListUserAccount : productStock.getUserAccountList()) {
                userAccountListUserAccount.getProductStockList().add(productStock);
                userAccountListUserAccount = em.merge(userAccountListUserAccount);
            }
            for (ProductReview productReviewListProductReview : productStock.getProductReviewList()) {
                ProductStock oldProductIdOfProductReviewListProductReview = productReviewListProductReview.getProductId();
                productReviewListProductReview.setProductId(productStock);
                productReviewListProductReview = em.merge(productReviewListProductReview);
                if (oldProductIdOfProductReviewListProductReview != null) {
                    oldProductIdOfProductReviewListProductReview.getProductReviewList().remove(productReviewListProductReview);
                    oldProductIdOfProductReviewListProductReview = em.merge(oldProductIdOfProductReviewListProductReview);
                }
            }
            for (ProductImage productImageListProductImage : productStock.getProductImageList()) {
                ProductStock oldProductIdOfProductImageListProductImage = productImageListProductImage.getProductId();
                productImageListProductImage.setProductId(productStock);
                productImageListProductImage = em.merge(productImageListProductImage);
                if (oldProductIdOfProductImageListProductImage != null) {
                    oldProductIdOfProductImageListProductImage.getProductImageList().remove(productImageListProductImage);
                    oldProductIdOfProductImageListProductImage = em.merge(oldProductIdOfProductImageListProductImage);
                }
            }
            for (ProductSale productSaleListProductSale : productStock.getProductSaleList()) {
                ProductStock oldProductIdOfProductSaleListProductSale = productSaleListProductSale.getProductId();
                productSaleListProductSale.setProductId(productStock);
                productSaleListProductSale = em.merge(productSaleListProductSale);
                if (oldProductIdOfProductSaleListProductSale != null) {
                    oldProductIdOfProductSaleListProductSale.getProductSaleList().remove(productSaleListProductSale);
                    oldProductIdOfProductSaleListProductSale = em.merge(oldProductIdOfProductSaleListProductSale);
                }
            }
            for (ProductQuestion productQuestionListProductQuestion : productStock.getProductQuestionList()) {
                ProductStock oldProductIdOfProductQuestionListProductQuestion = productQuestionListProductQuestion.getProductId();
                productQuestionListProductQuestion.setProductId(productStock);
                productQuestionListProductQuestion = em.merge(productQuestionListProductQuestion);
                if (oldProductIdOfProductQuestionListProductQuestion != null) {
                    oldProductIdOfProductQuestionListProductQuestion.getProductQuestionList().remove(productQuestionListProductQuestion);
                    oldProductIdOfProductQuestionListProductQuestion = em.merge(oldProductIdOfProductQuestionListProductQuestion);
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

    public void edit(ProductStock productStock) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductStock persistentProductStock = em.find(ProductStock.class, productStock.getId());
            ProductCategory categoryIdOld = persistentProductStock.getCategoryId();
            ProductCategory categoryIdNew = productStock.getCategoryId();
            ProductPromotion promotionIdOld = persistentProductStock.getPromotionId();
            ProductPromotion promotionIdNew = productStock.getPromotionId();
            List<UserAccount> userAccountListOld = persistentProductStock.getUserAccountList();
            List<UserAccount> userAccountListNew = productStock.getUserAccountList();
            List<ProductReview> productReviewListOld = persistentProductStock.getProductReviewList();
            List<ProductReview> productReviewListNew = productStock.getProductReviewList();
            List<ProductImage> productImageListOld = persistentProductStock.getProductImageList();
            List<ProductImage> productImageListNew = productStock.getProductImageList();
            List<ProductSale> productSaleListOld = persistentProductStock.getProductSaleList();
            List<ProductSale> productSaleListNew = productStock.getProductSaleList();
            List<ProductQuestion> productQuestionListOld = persistentProductStock.getProductQuestionList();
            List<ProductQuestion> productQuestionListNew = productStock.getProductQuestionList();
            List<String> illegalOrphanMessages = null;
            for (ProductReview productReviewListOldProductReview : productReviewListOld) {
                if (!productReviewListNew.contains(productReviewListOldProductReview)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductReview " + productReviewListOldProductReview + " since its productId field is not nullable.");
                }
            }
            for (ProductImage productImageListOldProductImage : productImageListOld) {
                if (!productImageListNew.contains(productImageListOldProductImage)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductImage " + productImageListOldProductImage + " since its productId field is not nullable.");
                }
            }
            for (ProductSale productSaleListOldProductSale : productSaleListOld) {
                if (!productSaleListNew.contains(productSaleListOldProductSale)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductSale " + productSaleListOldProductSale + " since its productId field is not nullable.");
                }
            }
            for (ProductQuestion productQuestionListOldProductQuestion : productQuestionListOld) {
                if (!productQuestionListNew.contains(productQuestionListOldProductQuestion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductQuestion " + productQuestionListOldProductQuestion + " since its productId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (categoryIdNew != null) {
                categoryIdNew = em.getReference(categoryIdNew.getClass(), categoryIdNew.getId());
                productStock.setCategoryId(categoryIdNew);
            }
            if (promotionIdNew != null) {
                promotionIdNew = em.getReference(promotionIdNew.getClass(), promotionIdNew.getId());
                productStock.setPromotionId(promotionIdNew);
            }
            List<UserAccount> attachedUserAccountListNew = new ArrayList<UserAccount>();
            for (UserAccount userAccountListNewUserAccountToAttach : userAccountListNew) {
                userAccountListNewUserAccountToAttach = em.getReference(userAccountListNewUserAccountToAttach.getClass(), userAccountListNewUserAccountToAttach.getId());
                attachedUserAccountListNew.add(userAccountListNewUserAccountToAttach);
            }
            userAccountListNew = attachedUserAccountListNew;
            productStock.setUserAccountList(userAccountListNew);
            List<ProductReview> attachedProductReviewListNew = new ArrayList<ProductReview>();
            for (ProductReview productReviewListNewProductReviewToAttach : productReviewListNew) {
                productReviewListNewProductReviewToAttach = em.getReference(productReviewListNewProductReviewToAttach.getClass(), productReviewListNewProductReviewToAttach.getId());
                attachedProductReviewListNew.add(productReviewListNewProductReviewToAttach);
            }
            productReviewListNew = attachedProductReviewListNew;
            productStock.setProductReviewList(productReviewListNew);
            List<ProductImage> attachedProductImageListNew = new ArrayList<ProductImage>();
            for (ProductImage productImageListNewProductImageToAttach : productImageListNew) {
                productImageListNewProductImageToAttach = em.getReference(productImageListNewProductImageToAttach.getClass(), productImageListNewProductImageToAttach.getId());
                attachedProductImageListNew.add(productImageListNewProductImageToAttach);
            }
            productImageListNew = attachedProductImageListNew;
            productStock.setProductImageList(productImageListNew);
            List<ProductSale> attachedProductSaleListNew = new ArrayList<ProductSale>();
            for (ProductSale productSaleListNewProductSaleToAttach : productSaleListNew) {
                productSaleListNewProductSaleToAttach = em.getReference(productSaleListNewProductSaleToAttach.getClass(), productSaleListNewProductSaleToAttach.getId());
                attachedProductSaleListNew.add(productSaleListNewProductSaleToAttach);
            }
            productSaleListNew = attachedProductSaleListNew;
            productStock.setProductSaleList(productSaleListNew);
            List<ProductQuestion> attachedProductQuestionListNew = new ArrayList<ProductQuestion>();
            for (ProductQuestion productQuestionListNewProductQuestionToAttach : productQuestionListNew) {
                productQuestionListNewProductQuestionToAttach = em.getReference(productQuestionListNewProductQuestionToAttach.getClass(), productQuestionListNewProductQuestionToAttach.getId());
                attachedProductQuestionListNew.add(productQuestionListNewProductQuestionToAttach);
            }
            productQuestionListNew = attachedProductQuestionListNew;
            productStock.setProductQuestionList(productQuestionListNew);
            productStock = em.merge(productStock);
            if (categoryIdOld != null && !categoryIdOld.equals(categoryIdNew)) {
                categoryIdOld.getProductStockList().remove(productStock);
                categoryIdOld = em.merge(categoryIdOld);
            }
            if (categoryIdNew != null && !categoryIdNew.equals(categoryIdOld)) {
                categoryIdNew.getProductStockList().add(productStock);
                categoryIdNew = em.merge(categoryIdNew);
            }
            if (promotionIdOld != null && !promotionIdOld.equals(promotionIdNew)) {
                promotionIdOld.getProductStockList().remove(productStock);
                promotionIdOld = em.merge(promotionIdOld);
            }
            if (promotionIdNew != null && !promotionIdNew.equals(promotionIdOld)) {
                promotionIdNew.getProductStockList().add(productStock);
                promotionIdNew = em.merge(promotionIdNew);
            }
            for (UserAccount userAccountListOldUserAccount : userAccountListOld) {
                if (!userAccountListNew.contains(userAccountListOldUserAccount)) {
                    userAccountListOldUserAccount.getProductStockList().remove(productStock);
                    userAccountListOldUserAccount = em.merge(userAccountListOldUserAccount);
                }
            }
            for (UserAccount userAccountListNewUserAccount : userAccountListNew) {
                if (!userAccountListOld.contains(userAccountListNewUserAccount)) {
                    userAccountListNewUserAccount.getProductStockList().add(productStock);
                    userAccountListNewUserAccount = em.merge(userAccountListNewUserAccount);
                }
            }
            for (ProductReview productReviewListNewProductReview : productReviewListNew) {
                if (!productReviewListOld.contains(productReviewListNewProductReview)) {
                    ProductStock oldProductIdOfProductReviewListNewProductReview = productReviewListNewProductReview.getProductId();
                    productReviewListNewProductReview.setProductId(productStock);
                    productReviewListNewProductReview = em.merge(productReviewListNewProductReview);
                    if (oldProductIdOfProductReviewListNewProductReview != null && !oldProductIdOfProductReviewListNewProductReview.equals(productStock)) {
                        oldProductIdOfProductReviewListNewProductReview.getProductReviewList().remove(productReviewListNewProductReview);
                        oldProductIdOfProductReviewListNewProductReview = em.merge(oldProductIdOfProductReviewListNewProductReview);
                    }
                }
            }
            for (ProductImage productImageListNewProductImage : productImageListNew) {
                if (!productImageListOld.contains(productImageListNewProductImage)) {
                    ProductStock oldProductIdOfProductImageListNewProductImage = productImageListNewProductImage.getProductId();
                    productImageListNewProductImage.setProductId(productStock);
                    productImageListNewProductImage = em.merge(productImageListNewProductImage);
                    if (oldProductIdOfProductImageListNewProductImage != null && !oldProductIdOfProductImageListNewProductImage.equals(productStock)) {
                        oldProductIdOfProductImageListNewProductImage.getProductImageList().remove(productImageListNewProductImage);
                        oldProductIdOfProductImageListNewProductImage = em.merge(oldProductIdOfProductImageListNewProductImage);
                    }
                }
            }
            for (ProductSale productSaleListNewProductSale : productSaleListNew) {
                if (!productSaleListOld.contains(productSaleListNewProductSale)) {
                    ProductStock oldProductIdOfProductSaleListNewProductSale = productSaleListNewProductSale.getProductId();
                    productSaleListNewProductSale.setProductId(productStock);
                    productSaleListNewProductSale = em.merge(productSaleListNewProductSale);
                    if (oldProductIdOfProductSaleListNewProductSale != null && !oldProductIdOfProductSaleListNewProductSale.equals(productStock)) {
                        oldProductIdOfProductSaleListNewProductSale.getProductSaleList().remove(productSaleListNewProductSale);
                        oldProductIdOfProductSaleListNewProductSale = em.merge(oldProductIdOfProductSaleListNewProductSale);
                    }
                }
            }
            for (ProductQuestion productQuestionListNewProductQuestion : productQuestionListNew) {
                if (!productQuestionListOld.contains(productQuestionListNewProductQuestion)) {
                    ProductStock oldProductIdOfProductQuestionListNewProductQuestion = productQuestionListNewProductQuestion.getProductId();
                    productQuestionListNewProductQuestion.setProductId(productStock);
                    productQuestionListNewProductQuestion = em.merge(productQuestionListNewProductQuestion);
                    if (oldProductIdOfProductQuestionListNewProductQuestion != null && !oldProductIdOfProductQuestionListNewProductQuestion.equals(productStock)) {
                        oldProductIdOfProductQuestionListNewProductQuestion.getProductQuestionList().remove(productQuestionListNewProductQuestion);
                        oldProductIdOfProductQuestionListNewProductQuestion = em.merge(oldProductIdOfProductQuestionListNewProductQuestion);
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
                Integer id = productStock.getId();
                if (findProductStock(id) == null) {
                    throw new NonexistentEntityException("The productStock with id " + id + " no longer exists.");
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
            ProductStock productStock;
            try {
                productStock = em.getReference(ProductStock.class, id);
                productStock.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productStock with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProductReview> productReviewListOrphanCheck = productStock.getProductReviewList();
            for (ProductReview productReviewListOrphanCheckProductReview : productReviewListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProductStock (" + productStock + ") cannot be destroyed since the ProductReview " + productReviewListOrphanCheckProductReview + " in its productReviewList field has a non-nullable productId field.");
            }
            List<ProductImage> productImageListOrphanCheck = productStock.getProductImageList();
            for (ProductImage productImageListOrphanCheckProductImage : productImageListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProductStock (" + productStock + ") cannot be destroyed since the ProductImage " + productImageListOrphanCheckProductImage + " in its productImageList field has a non-nullable productId field.");
            }
            List<ProductSale> productSaleListOrphanCheck = productStock.getProductSaleList();
            for (ProductSale productSaleListOrphanCheckProductSale : productSaleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProductStock (" + productStock + ") cannot be destroyed since the ProductSale " + productSaleListOrphanCheckProductSale + " in its productSaleList field has a non-nullable productId field.");
            }
            List<ProductQuestion> productQuestionListOrphanCheck = productStock.getProductQuestionList();
            for (ProductQuestion productQuestionListOrphanCheckProductQuestion : productQuestionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProductStock (" + productStock + ") cannot be destroyed since the ProductQuestion " + productQuestionListOrphanCheckProductQuestion + " in its productQuestionList field has a non-nullable productId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            ProductCategory categoryId = productStock.getCategoryId();
            if (categoryId != null) {
                categoryId.getProductStockList().remove(productStock);
                categoryId = em.merge(categoryId);
            }
            ProductPromotion promotionId = productStock.getPromotionId();
            if (promotionId != null) {
                promotionId.getProductStockList().remove(productStock);
                promotionId = em.merge(promotionId);
            }
            List<UserAccount> userAccountList = productStock.getUserAccountList();
            for (UserAccount userAccountListUserAccount : userAccountList) {
                userAccountListUserAccount.getProductStockList().remove(productStock);
                userAccountListUserAccount = em.merge(userAccountListUserAccount);
            }
            em.remove(productStock);
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

    public List<ProductStock> findProductStockEntities() {
        return findProductStockEntities(true, -1, -1);
    }

    public List<ProductStock> findProductStockEntities(int maxResults, int firstResult) {
        return findProductStockEntities(false, maxResults, firstResult);
    }

    private List<ProductStock> findProductStockEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductStock.class));
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

    public ProductStock findProductStock(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductStock.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductStockCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductStock> rt = cq.from(ProductStock.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
