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
@Table(name = "USER_ACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAccount.findAll", query = "SELECT u FROM UserAccount u")
    , @NamedQuery(name = "UserAccount.findById", query = "SELECT u FROM UserAccount u WHERE u.id = :id")
    , @NamedQuery(name = "UserAccount.findByEmail", query = "SELECT u FROM UserAccount u WHERE u.email = :email")
    , @NamedQuery(name = "UserAccount.findByPassword", query = "SELECT u FROM UserAccount u WHERE u.password = :password")
    , @NamedQuery(name = "UserAccount.findByFirstName", query = "SELECT u FROM UserAccount u WHERE u.firstName = :firstName")
    , @NamedQuery(name = "UserAccount.findByLastName", query = "SELECT u FROM UserAccount u WHERE u.lastName = :lastName")
    , @NamedQuery(name = "UserAccount.findByDob", query = "SELECT u FROM UserAccount u WHERE u.dob = :dob")
    , @NamedQuery(name = "UserAccount.findByMobile", query = "SELECT u FROM UserAccount u WHERE u.mobile = :mobile")
    , @NamedQuery(name = "UserAccount.findByGender", query = "SELECT u FROM UserAccount u WHERE u.gender = :gender")
    , @NamedQuery(name = "UserAccount.findByActiveToken", query = "SELECT u FROM UserAccount u WHERE u.activeToken = :activeToken")
    , @NamedQuery(name = "UserAccount.findByActiveStatus", query = "SELECT u FROM UserAccount u WHERE u.activeStatus = :activeStatus")
    , @NamedQuery(name = "UserAccount.findByForgotPasswordToken", query = "SELECT u FROM UserAccount u WHERE u.forgotPasswordToken = :forgotPasswordToken")
    , @NamedQuery(name = "UserAccount.findByTotalPoint", query = "SELECT u FROM UserAccount u WHERE u.totalPoint = :totalPoint")
    , @NamedQuery(name = "UserAccount.findByIsSubscribe", query = "SELECT u FROM UserAccount u WHERE u.isSubscribe = :isSubscribe")})
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MOBILE")
    private String mobile;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GENDER")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "ACTIVE_TOKEN")
    private String activeToken;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACTIVE_STATUS")
    private String activeStatus;
    @Size(max = 60)
    @Column(name = "FORGOT_PASSWORD_TOKEN")
    private String forgotPasswordToken;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_POINT")
    private int totalPoint;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IS_SUBSCRIBE")
    private String isSubscribe;
    @ManyToMany(mappedBy = "userAccountList")
    private List<ProductStock> productStockList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<ProductReview> productReviewList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Bill> billList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<UserAddress> userAddressList;
    @JoinColumn(name = "TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private UserType type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<ProductQuestion> productQuestionList;

    public UserAccount() {
    }

    public UserAccount(Integer id) {
        this.id = id;
    }

    public UserAccount(Integer id, String email, String password, String firstName, String lastName, String mobile, String gender, String activeToken, String activeStatus, int totalPoint, String isSubscribe) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.gender = gender;
        this.activeToken = activeToken;
        this.activeStatus = activeStatus;
        this.totalPoint = totalPoint;
        this.isSubscribe = isSubscribe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getActiveToken() {
        return activeToken;
    }

    public void setActiveToken(String activeToken) {
        this.activeToken = activeToken;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getForgotPasswordToken() {
        return forgotPasswordToken;
    }

    public void setForgotPasswordToken(String forgotPasswordToken) {
        this.forgotPasswordToken = forgotPasswordToken;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    @XmlTransient
    public List<ProductStock> getProductStockList() {
        return productStockList;
    }

    public void setProductStockList(List<ProductStock> productStockList) {
        this.productStockList = productStockList;
    }

    @XmlTransient
    public List<ProductReview> getProductReviewList() {
        return productReviewList;
    }

    public void setProductReviewList(List<ProductReview> productReviewList) {
        this.productReviewList = productReviewList;
    }

    @XmlTransient
    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    @XmlTransient
    public List<UserAddress> getUserAddressList() {
        return userAddressList;
    }

    public void setUserAddressList(List<UserAddress> userAddressList) {
        this.userAddressList = userAddressList;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @XmlTransient
    public List<ProductQuestion> getProductQuestionList() {
        return productQuestionList;
    }

    public void setProductQuestionList(List<ProductQuestion> productQuestionList) {
        this.productQuestionList = productQuestionList;
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
        if (!(object instanceof UserAccount)) {
            return false;
        }
        UserAccount other = (UserAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moommim.moommim_web.model.UserAccount[ id=" + id + " ]";
    }

}
