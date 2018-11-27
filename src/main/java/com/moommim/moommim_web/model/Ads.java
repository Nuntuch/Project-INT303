package com.moommim.moommim_web.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ADS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ads.findAll", query = "SELECT a FROM Ads a")
    , @NamedQuery(name = "Ads.findById", query = "SELECT a FROM Ads a WHERE a.id = :id")
    , @NamedQuery(name = "Ads.findByName", query = "SELECT a FROM Ads a WHERE a.name = :name")
    , @NamedQuery(name = "Ads.findByImage", query = "SELECT a FROM Ads a WHERE a.image = :image")
    , @NamedQuery(name = "Ads.findByType", query = "SELECT a FROM Ads a WHERE a.type = :type")
    , @NamedQuery(name = "Ads.findByPosition", query = "SELECT a FROM Ads a WHERE a.position = :position")
    , @NamedQuery(name = "Ads.findByGenderTarget", query = "SELECT a FROM Ads a WHERE a.genderTarget = :genderTarget")
    , @NamedQuery(name = "Ads.findByIsShow", query = "SELECT a FROM Ads a WHERE a.isShow = :isShow")})
public class Ads implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "IMAGE")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "POSITION")
    private String position;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GENDER_TARGET")
    private String genderTarget;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IS_SHOW")
    private String isShow;

    public Ads() {
    }

    public Ads(Integer id) {
        this.id = id;
    }

    public Ads(Integer id, String name, String image, String type, String position, String genderTarget, String isShow) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.type = type;
        this.position = position;
        this.genderTarget = genderTarget;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGenderTarget() {
        return genderTarget;
    }

    public void setGenderTarget(String genderTarget) {
        this.genderTarget = genderTarget;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
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
        if (!(object instanceof Ads)) {
            return false;
        }
        Ads other = (Ads) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moommim.moommim_web.model.Ads[ id=" + id + " ]";
    }

}
