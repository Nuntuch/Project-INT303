package com.moommim.moommim_web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "PRODUCT_PROMOTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductPromotion.findAll", query = "SELECT p FROM ProductPromotion p")
    , @NamedQuery(name = "ProductPromotion.findById", query = "SELECT p FROM ProductPromotion p WHERE p.id = :id")
    , @NamedQuery(name = "ProductPromotion.findByName", query = "SELECT p FROM ProductPromotion p WHERE p.name = :name")
    , @NamedQuery(name = "ProductPromotion.findByDescription", query = "SELECT p FROM ProductPromotion p WHERE p.description = :description")
    , @NamedQuery(name = "ProductPromotion.findByMinimumPaid", query = "SELECT p FROM ProductPromotion p WHERE p.minimumPaid = :minimumPaid")
    , @NamedQuery(name = "ProductPromotion.findByRate", query = "SELECT p FROM ProductPromotion p WHERE p.rate = :rate")
    , @NamedQuery(name = "ProductPromotion.findByType", query = "SELECT p FROM ProductPromotion p WHERE p.type = :type")
    , @NamedQuery(name = "ProductPromotion.findByNumberCanUse", query = "SELECT p FROM ProductPromotion p WHERE p.numberCanUse = :numberCanUse")
    , @NamedQuery(name = "ProductPromotion.findByStartDate", query = "SELECT p FROM ProductPromotion p WHERE p.startDate = :startDate")
    , @NamedQuery(name = "ProductPromotion.findByEndDate", query = "SELECT p FROM ProductPromotion p WHERE p.endDate = :endDate")})
public class ProductPromotion implements Serializable {

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
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MINIMUM_PAID")
    private BigDecimal minimumPaid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RATE")
    private int rate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMBER_CAN_USE")
    private int numberCanUse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @OneToMany(mappedBy = "promotionId")
    private List<ProductStock> productStockList;
    @OneToMany(mappedBy = "promotionId")
    private List<ProductCategory> productCategoryList;
    @OneToMany(mappedBy = "promotionId")
    private List<Bill> billList;

    public ProductPromotion() {
    }

    public ProductPromotion(Integer id) {
        this.id = id;
    }

    public ProductPromotion(Integer id, String name, int rate, String type, int numberCanUse, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.type = type;
        this.numberCanUse = numberCanUse;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMinimumPaid() {
        return minimumPaid;
    }

    public void setMinimumPaid(BigDecimal minimumPaid) {
        this.minimumPaid = minimumPaid;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberCanUse() {
        return numberCanUse;
    }

    public void setNumberCanUse(int numberCanUse) {
        this.numberCanUse = numberCanUse;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @XmlTransient
    public List<ProductStock> getProductStockList() {
        return productStockList;
    }

    public void setProductStockList(List<ProductStock> productStockList) {
        this.productStockList = productStockList;
    }

    @XmlTransient
    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }

    @XmlTransient
    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
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
        if (!(object instanceof ProductPromotion)) {
            return false;
        }
        ProductPromotion other = (ProductPromotion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moommim.moommim_web.model.ProductPromotion[ id=" + id + " ]";
    }

}
