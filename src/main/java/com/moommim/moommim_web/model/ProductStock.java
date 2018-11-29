
package com.moommim.moommim_web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "PRODUCT_STOCK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductStock.findAll", query = "SELECT p FROM ProductStock p")
    , @NamedQuery(name = "ProductStock.findById", query = "SELECT p FROM ProductStock p WHERE p.id = :id")
    , @NamedQuery(name = "ProductStock.findByName", query = "SELECT p FROM ProductStock p WHERE p.name = :name")
    , @NamedQuery(name = "ProductStock.findByDetail", query = "SELECT p FROM ProductStock p WHERE p.detail = :detail")
    , @NamedQuery(name = "ProductStock.findByAmountInStock", query = "SELECT p FROM ProductStock p WHERE p.amountInStock = :amountInStock")
    , @NamedQuery(name = "ProductStock.findByBrand", query = "SELECT p FROM ProductStock p WHERE p.brand = :brand")
    , @NamedQuery(name = "ProductStock.findByPrice", query = "SELECT p FROM ProductStock p WHERE p.price = :price")
    , @NamedQuery(name = "ProductStock.findByStatus", query = "SELECT p FROM ProductStock p WHERE p.status = :status")
    , @NamedQuery(name = "ProductStock.findByCreateAt", query = "SELECT p FROM ProductStock p WHERE p.createAt = :createAt")
    , @NamedQuery(name = "ProductStock.findByUpdateAt", query = "SELECT p FROM ProductStock p WHERE p.updateAt = :updateAt")
    , @NamedQuery(name = "ProductStock.findByIsShow", query = "SELECT p FROM ProductStock p WHERE p.isShow = :isShow")})
public class ProductStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME")
    private String name;
    @Size(max = 500)
    @Column(name = "DETAIL")
    private String detail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT_IN_STOCK")
    private int amountInStock;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "BRAND")
    private String brand;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UPDATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IS_SHOW")
    private String isShow;
    @JoinTable(name = "PRODUCT_FAVORITE", joinColumns = {
        @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "USER_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<UserAccount> userAccountList;
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductCategory categoryId;
    @JoinColumn(name = "FEATURED_IMAGE_ID", referencedColumnName = "ID")
    @ManyToOne
    private ProductImage featuredImageId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<ProductImage> productImageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<ProductSale> productSaleList;

    public ProductStock() {
    }

    public ProductStock(Integer id) {
        this.id = id;
    }

    public ProductStock(Integer id, String name, int amountInStock, String brand, BigDecimal price, String status, Date createAt, Date updateAt, String isShow) {
        this.id = id;
        this.name = name;
        this.amountInStock = amountInStock;
        this.brand = brand;
        this.price = price;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.isShow = isShow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    @XmlTransient
    public List<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(List<UserAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }

    public ProductCategory getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(ProductCategory categoryId) {
        this.categoryId = categoryId;
    }

    public ProductImage getFeaturedImageId() {
        return featuredImageId;
    }

    public void setFeaturedImageId(ProductImage featuredImageId) {
        this.featuredImageId = featuredImageId;
    }

    @XmlTransient
    public List<ProductImage> getProductImageList() {
        return productImageList;
    }

    public void setProductImageList(List<ProductImage> productImageList) {
        this.productImageList = productImageList;
    }

    @XmlTransient
    public List<ProductSale> getProductSaleList() {
        return productSaleList;
    }

    public void setProductSaleList(List<ProductSale> productSaleList) {
        this.productSaleList = productSaleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductStock)) {
            return false;
        }
        ProductStock other = (ProductStock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moommim.moommim_web.model.ProductStock[ id=" + id + " ]";
    }

}
