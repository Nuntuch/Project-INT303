package com.moommim.moommim_web.model;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PRODUCT_QUESTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductQuestion.findAll", query = "SELECT p FROM ProductQuestion p")
    , @NamedQuery(name = "ProductQuestion.findById", query = "SELECT p FROM ProductQuestion p WHERE p.id = :id")
    , @NamedQuery(name = "ProductQuestion.findByQuestion", query = "SELECT p FROM ProductQuestion p WHERE p.question = :question")
    , @NamedQuery(name = "ProductQuestion.findByAnswer", query = "SELECT p FROM ProductQuestion p WHERE p.answer = :answer")
    , @NamedQuery(name = "ProductQuestion.findByCreateAt", query = "SELECT p FROM ProductQuestion p WHERE p.createAt = :createAt")
    , @NamedQuery(name = "ProductQuestion.findByUpdateAt", query = "SELECT p FROM ProductQuestion p WHERE p.updateAt = :updateAt")})
public class ProductQuestion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "QUESTION")
    private String question;
    @Size(max = 200)
    @Column(name = "ANSWER")
    private String answer;
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
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductStock productId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private UserAccount userId;

    public ProductQuestion() {
    }

    public ProductQuestion(Integer id) {
        this.id = id;
    }

    public ProductQuestion(Integer id, String question, Date createAt, Date updateAt) {
        this.id = id;
        this.question = question;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public ProductStock getProductId() {
        return productId;
    }

    public void setProductId(ProductStock productId) {
        this.productId = productId;
    }

    public UserAccount getUserId() {
        return userId;
    }

    public void setUserId(UserAccount userId) {
        this.userId = userId;
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
        if (!(object instanceof ProductQuestion)) {
            return false;
        }
        ProductQuestion other = (ProductQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moommim.moommim_web.model.ProductQuestion[ id=" + id + " ]";
    }
    
}
