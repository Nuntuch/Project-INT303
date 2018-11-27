package com.moommim.moommim_web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PRODUCT_SALE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductSale.findAll", query = "SELECT p FROM ProductSale p")
    , @NamedQuery(name = "ProductSale.findById", query = "SELECT p FROM ProductSale p WHERE p.id = :id")
    , @NamedQuery(name = "ProductSale.findByQuantity", query = "SELECT p FROM ProductSale p WHERE p.quantity = :quantity")
    , @NamedQuery(name = "ProductSale.findByAmount", query = "SELECT p FROM ProductSale p WHERE p.amount = :amount")
    , @NamedQuery(name = "ProductSale.findByCreateAt", query = "SELECT p FROM ProductSale p WHERE p.createAt = :createAt")
    , @NamedQuery(name = "ProductSale.findByPricePerUnit", query = "SELECT p FROM ProductSale p WHERE p.pricePerUnit = :pricePerUnit")})
public class ProductSale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE_PER_UNIT")
    private BigDecimal pricePerUnit;
    @JoinColumn(name = "BILL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Bill billId;
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductStock productId;

    public ProductSale() {
    }

    public ProductSale(Integer id) {
        this.id = id;
    }

    public ProductSale(Integer id, int quantity, BigDecimal amount, Date createAt, BigDecimal pricePerUnit) {
        this.id = id;
        this.quantity = quantity;
        this.amount = amount;
        this.createAt = createAt;
        this.pricePerUnit = pricePerUnit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Bill getBillId() {
        return billId;
    }

    public void setBillId(Bill billId) {
        this.billId = billId;
    }

    public ProductStock getProductId() {
        return productId;
    }

    public void setProductId(ProductStock productId) {
        this.productId = productId;
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
        if (!(object instanceof ProductSale)) {
            return false;
        }
        ProductSale other = (ProductSale) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moommim.moommim_web.model.ProductSale[ id=" + id + " ]";
    }

}
