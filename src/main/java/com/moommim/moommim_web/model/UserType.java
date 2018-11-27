package com.moommim.moommim_web.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "USER_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserType.findAll", query = "SELECT u FROM UserType u")
    , @NamedQuery(name = "UserType.findById", query = "SELECT u FROM UserType u WHERE u.id = :id")
    , @NamedQuery(name = "UserType.findByLevel", query = "SELECT u FROM UserType u WHERE u.level = :level")
    , @NamedQuery(name = "UserType.findByUserGroup", query = "SELECT u FROM UserType u WHERE u.userGroup = :userGroup")
    , @NamedQuery(name = "UserType.findByDiscount", query = "SELECT u FROM UserType u WHERE u.discount = :discount")
    , @NamedQuery(name = "UserType.findByMinimumPoint", query = "SELECT u FROM UserType u WHERE u.minimumPoint = :minimumPoint")})
public class UserType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "LEVEL")
    private String level;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USER_GROUP")
    private String userGroup;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DISCOUNT")
    private int discount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MINIMUM_POINT")
    private int minimumPoint;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<UserAccount> userAccountList;

    public UserType() {
    }

    public UserType(Integer id) {
        this.id = id;
    }

    public UserType(Integer id, String level, String userGroup, int discount, int minimumPoint) {
        this.id = id;
        this.level = level;
        this.userGroup = userGroup;
        this.discount = discount;
        this.minimumPoint = minimumPoint;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getMinimumPoint() {
        return minimumPoint;
    }

    public void setMinimumPoint(int minimumPoint) {
        this.minimumPoint = minimumPoint;
    }

    @XmlTransient
    public List<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(List<UserAccount> userAccountList) {
        this.userAccountList = userAccountList;
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
        if (!(object instanceof UserType)) {
            return false;
        }
        UserType other = (UserType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moommim.moommim_web.model.UserType[ id=" + id + " ]";
    }

}
