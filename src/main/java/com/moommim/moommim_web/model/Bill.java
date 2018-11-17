package com.moommim.moommim_web.model;

import java.io.Serializable;
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
@Table(name = "BILL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b")
    , @NamedQuery(name = "Bill.findById", query = "SELECT b FROM Bill b WHERE b.id = :id")
    , @NamedQuery(name = "Bill.findByCreateAt", query = "SELECT b FROM Bill b WHERE b.createAt = :createAt")
    , @NamedQuery(name = "Bill.findByUpdateAt", query = "SELECT b FROM Bill b WHERE b.updateAt = :updateAt")
    , @NamedQuery(name = "Bill.findByBillStatus", query = "SELECT b FROM Bill b WHERE b.billStatus = :billStatus")
    , @NamedQuery(name = "Bill.findByShippingStatus", query = "SELECT b FROM Bill b WHERE b.shippingStatus = :shippingStatus")
    , @NamedQuery(name = "Bill.findByShippingType", query = "SELECT b FROM Bill b WHERE b.shippingType = :shippingType")
    , @NamedQuery(name = "Bill.findByTotalPrice", query = "SELECT b FROM Bill b WHERE b.totalPrice = :totalPrice")})
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
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
    @Column(name = "BILL_STATUS")
    private String billStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SHIPPING_STATUS")
    private String shippingStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "SHIPPING_TYPE")
    private String shippingType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TOTAL_PRICE")
    private String totalPrice;
    @JoinColumn(name = "PROMOTION_ID", referencedColumnName = "ID")
    @ManyToOne
    private ProductPromotion promotionId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private UserAccount userId;
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private UserAddress addressId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "billId")
    private List<ProductSale> productSaleList;

    public Bill() {
    }

    public Bill(Integer id) {
        this.id = id;
    }

    public Bill(Integer id, Date createAt, Date updateAt, String billStatus, String shippingStatus, String shippingType, String totalPrice) {
        this.id = id;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.billStatus = billStatus;
        this.shippingStatus = shippingStatus;
        this.shippingType = shippingType;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ProductPromotion getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(ProductPromotion promotionId) {
        this.promotionId = promotionId;
    }

    public UserAccount getUserId() {
        return userId;
    }

    public void setUserId(UserAccount userId) {
        this.userId = userId;
    }

    public UserAddress getAddressId() {
        return addressId;
    }

    public void setAddressId(UserAddress addressId) {
        this.addressId = addressId;
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
        if (!(object instanceof Bill)) {
            return false;
        }
        Bill other = (Bill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moommim.moommim_web.model.Bill[ id=" + id + " ]";
    }
    
}
